package com.big.market.infrastructure.infrastructure.persistent.repository;

import com.big.market.infrastructure.domain.activity.event.ActivitySkuStockZeroMessageEvent;
import com.big.market.infrastructure.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.entity.*;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivitySkuStockKeyVO;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivityStateVO;
import com.big.market.infrastructure.domain.activity.model.valobj.UserRaffleOrderStateVO;
import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import com.big.market.infrastructure.infrastructure.persistent.dao.*;
import com.big.market.infrastructure.infrastructure.persistent.event.EventPublisher;
import com.big.market.infrastructure.infrastructure.persistent.po.*;
import com.big.market.infrastructure.infrastructure.persistent.redis.RedissonService;
import com.big.market.infrastructure.types.common.Constants;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 莱特0905
 * @Description: 活动服务仓储实现
 * @Date: 2024/08/13 19:47:43
 */
@Slf4j
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IRaffleActivityOrderDao raffleActivityOrderDao;
    @Resource
    private IRaffleActivityAccountDao raffleActivityAccountDao;
    @Resource
    private IRaffleActivitySkuDao raffleActivitySkuDao;
    @Resource
    private IRaffleActivityDao raffleActivityDao;
    @Resource
    private IRaffleActivityCountDao raffleActivityCountDao;
    @Resource
    private RedissonService redissonService;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private EventPublisher eventPublisher;
    @Resource
    private ActivitySkuStockZeroMessageEvent activitySkuStockZeroMessageEvent;
    @Resource
    private IUserRaffleOrderDao userRaffleOrderDao;
    @Resource
    private IRaffleActivityAccountMonthDao raffleActivityAccountMonthDao;
    @Resource
    private IRaffleActivityAccountDayDao raffleActivityAccountDayDao;

    /**
     * 根据商品 sku 查找活动 Sku 实体
     * @param sku 商品 sku
     * @return 活动 Sku 实体
     */
    @Override
    public ActivitySkuEntity queryActivitySku(Long sku) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_KEY + sku;
        ActivitySkuEntity activitySkuEntity = redissonService.getValue(cacheKey);
        if (activitySkuEntity != null){
            return activitySkuEntity;
        }
        RaffleActivitySku raffleActivitySku = raffleActivitySkuDao.queryActivitySku(sku);
        activitySkuEntity = ActivitySkuEntity.builder()
                .activityCountId(raffleActivitySku.getActivityCountId())
                .activityId(raffleActivitySku.getActivityId())
                .sku(sku)
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .stockCount(raffleActivitySku.getStockCount())
                .build();
        redissonService.setValue(cacheKey, activitySkuEntity);
        return activitySkuEntity;
    }

    /**
     * 根据活动 ID查询活动信息
     * @param activityId 活动 ID
     * @return 活动信息
     */
    @Override
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redissonService.getValue(cacheKey);
        if (activityEntity != null){
            return activityEntity;
        }
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityCountId(raffleActivity.getActivityCountId())
                .activityDesc(raffleActivity.getActivityDesc())
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityStateVO.valueOf(raffleActivity.getState()))
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .build();
        redissonService.setValue(cacheKey, activityEntity);
        return activityEntity;
    }

    /**
     * 查询次数信息（用户在活动上可参与的次数）
     * @param activityCountId 活动次数 ID
     * @return 次数信息
     */
    @Override
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {

        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redissonService.getValue(cacheKey);
        if (activityCountEntity != null){
            return activityCountEntity;
        }

        RaffleActivityCount raffleActivityCount = raffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);

        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(activityCountId)
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .totalCount(raffleActivityCount.getTotalCount())
                .build();
        redissonService.setValue(cacheKey, activityCountEntity);
        return activityCountEntity;
    }

    /**
     * 保存订单
     * @param orderAggregate 订单聚合对象
     */
    @Override
    public void doSaveOrder(CreateQuotaOrderAggregate orderAggregate) {
        // 订单对象
        ActivityOrderEntity activityOrderEntity = orderAggregate.getActivityOrderEntity();
        RaffleActivityOrder raffleActivityOrder = new RaffleActivityOrder();
        raffleActivityOrder.setUserId(activityOrderEntity.getUserId());
        raffleActivityOrder.setSku(activityOrderEntity.getSku());
        raffleActivityOrder.setActivityId(activityOrderEntity.getActivityId());
        raffleActivityOrder.setActivityName(activityOrderEntity.getActivityName());
        raffleActivityOrder.setStrategyId(activityOrderEntity.getStrategyId());
        raffleActivityOrder.setOrderId(activityOrderEntity.getOrderId());
        raffleActivityOrder.setOrderTime(activityOrderEntity.getOrderTime());
        raffleActivityOrder.setTotalCount(activityOrderEntity.getTotalCount());
        raffleActivityOrder.setDayCount(activityOrderEntity.getDayCount());
        raffleActivityOrder.setMonthCount(activityOrderEntity.getMonthCount());
        raffleActivityOrder.setTotalCount(orderAggregate.getTotalCount());
        raffleActivityOrder.setDayCount(orderAggregate.getDayCount());
        raffleActivityOrder.setMonthCount(orderAggregate.getMonthCount());
        raffleActivityOrder.setState(activityOrderEntity.getState().getCode());
        raffleActivityOrder.setOutBusinessNo(activityOrderEntity.getOutBusinessNo());

        // 账户对象
        RaffleActivityAccount raffleActivityAccount = new RaffleActivityAccount();
        raffleActivityAccount.setUserId(orderAggregate.getUserId());
        raffleActivityAccount.setActivityId(orderAggregate.getActivityId());
        raffleActivityAccount.setTotalCount(orderAggregate.getTotalCount());
        raffleActivityAccount.setTotalCountSurplus(orderAggregate.getTotalCount());
        raffleActivityAccount.setDayCount(orderAggregate.getDayCount());
        raffleActivityAccount.setDayCountSurplus(orderAggregate.getDayCount());
        raffleActivityAccount.setMonthCount(orderAggregate.getMonthCount());
        raffleActivityAccount.setMonthCountSurplus(orderAggregate.getMonthCount());
        // 编程式事务
        transactionTemplate.execute(status -> {
            try {
                // 写入订单
                raffleActivityOrderDao.insert(raffleActivityOrder);
                // 更新账户
                int count = raffleActivityAccountDao.updateAccountQuota(raffleActivityAccount);
                // 创建账户 - 更新为0，则账户不存在，创新新账户。
                if (0 == count) {
                    raffleActivityAccountDao.insert(raffleActivityAccount);
                }
                return 1;

            }catch (DuplicateKeyException ex){
                status.setRollbackOnly();
                log.error("写入订单记录，唯一索引冲突 userId: {} activityId: {} sku: {}", activityOrderEntity.getUserId(),
                        activityOrderEntity.getActivityId(), activityOrderEntity.getSku(), ex);
                throw new AppException(ResponseCode.INDEX_DUP.getCode());
            }
        });
    }

    /**
     * 缓存活动sku库存信息
     * @param cacheKey 缓存key
     * @param stockCountSurplus 库存信息
     */
    @Override
    public void cacheActivitySkuStockCount(String cacheKey, Integer stockCountSurplus) {
        if (redissonService.isExists(cacheKey)){
            return;
        }
        redissonService.setAtomicLong(cacheKey, stockCountSurplus);
    }

    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     *
     * @param sku 互动SKU
     * @param cacheKey 缓存key
     * @param endDateTime 活动结束时间，根据结束时间设置加锁的key为结束时间
     * @return 扣减结果
     */
    @Override
    public boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime) {
        long surplus = redissonService.decr(cacheKey);
        if (surplus == 0){
            // 库存没了，发送MQ，更新数据库库存
            eventPublisher.publish(activitySkuStockZeroMessageEvent.topic(), activitySkuStockZeroMessageEvent.buildEventMessage(sku));
            return false;
        }else if (surplus < 0){
            redissonService.setAtomicLong(cacheKey, 0);
            return false;
        }
        // 1. 按照cacheKey decr 后的值，如 99、98、97 和 key 组成为库存锁的key进行使用。
        // 2. 加锁为了兜底，如果后续有恢复库存，手动处理等【运营是人来操作，会有这种情况发放，系统要做防护】，也不会超卖。因为所有的可用库存key，都被加锁了。
        // 3. 设置加锁时间为活动到期 + 延迟1天
        String lockKey = cacheKey + Constants.UNDERLINE + surplus;
        long expiredMillis = endDateTime.getTime() - System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1);

        Boolean lock = redissonService.setNx(lockKey, expiredMillis, TimeUnit.MILLISECONDS);
        if (!lock){
            log.info("活动sku库存加锁失败 {}", lockKey);
        }
        return lock;
    }

    /**
     * 延迟消费更新库存记录
     * @param activitySkuStockKeyVO 活动 id 和 sku id
     */
    @Override
    public void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO activitySkuStockKeyVO) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_COUNT_QUERY_KEY;
        RBlockingQueue<Object> blockingQueue = redissonService.getBlockingQueue(cacheKey);
        RDelayedQueue<Object> delayedQueue = redissonService.getDelayedQueue(blockingQueue);
        delayedQueue.offer(activitySkuStockKeyVO, 3, TimeUnit.SECONDS);
    }

    /**
     * 获取活动sku库存消耗队列
     *
     * @return 奖品库存Key信息
     */
    @Override
    public ActivitySkuStockKeyVO takeQueueValue() {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_COUNT_QUERY_KEY;
        RBlockingQueue<ActivitySkuStockKeyVO> blockingQueue = redissonService.getBlockingQueue(cacheKey);
        return blockingQueue.poll();
    }

    /**
     * 清空队列
     */
    @Override
    public void clearQueueValue() {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_COUNT_QUERY_KEY;
        RBlockingQueue<Object> blockingQueue = redissonService.getBlockingQueue(cacheKey);
        RDelayedQueue<Object> delayedQueue = redissonService.getDelayedQueue(blockingQueue);
        delayedQueue.clear();
        blockingQueue.clear();
    }

    /**
     * 延迟队列 + 任务趋势更新活动sku库存
     *
     * @param sku 活动商品
     */
    @Override
    public void updateActivitySkuStock(Long sku) {
        raffleActivitySkuDao.updateActivitySkuStock(sku);
    }

    /**
     * 缓存库存以消耗完毕，清空数据库库存
     *
     * @param sku 活动商品
     */
    @Override
    public void clearActivitySkuStock(Long sku) {
        raffleActivitySkuDao.clearActivitySkuStock(sku);
    }

    /**
     * 查询未使用的活动订单
     * @param partakeRaffleActivityEntity 参与抽奖活动实体对象
     * @return 用户抽奖订单实体对象
     */
    @Override
    public UserRaffleOrderEntity queryNoUsedRaffleOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity) {
        UserRaffleOrder userRaffleOrder = new UserRaffleOrder();
        userRaffleOrder.setUserId(partakeRaffleActivityEntity.getUserId());
        userRaffleOrder.setActivityId(partakeRaffleActivityEntity.getActivityId());

        UserRaffleOrder userRaffleOrderRes = userRaffleOrderDao.queryNoUsedRaffleOrder(userRaffleOrder);

        if (userRaffleOrderRes == null){
            return null;
        }

        return UserRaffleOrderEntity.builder()
                .userId(userRaffleOrder.getUserId())
                .orderId(userRaffleOrderRes.getOrderId())
                .activityId(userRaffleOrderRes.getActivityId())
                .orderTime(userRaffleOrderRes.getOrderTime())
                .orderState(UserRaffleOrderStateVO.valueOf(userRaffleOrderRes.getOrderState()))
                .activityName(userRaffleOrderRes.getActivityName())
                .strategyId(userRaffleOrderRes.getStrategyId())
                .build();
    }

    /**
     * 查询用户总账户额度
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 活动账户实体对象
     */
    @Override
    public ActivityAccountEntity queryActivityAccountByUserId(String userId, Long activityId) {

        RaffleActivityAccount raffleActivityAccount = new RaffleActivityAccount();
        raffleActivityAccount.setUserId(userId);
        raffleActivityAccount.setActivityId(activityId);

        RaffleActivityAccount raffleActivityAccountRes = raffleActivityAccountDao.queryActivityAccountByUserId(raffleActivityAccount);

        if (raffleActivityAccountRes == null){
            return null;
        }
        return ActivityAccountEntity.builder()
                .dayCountSurplus(raffleActivityAccountRes.getDayCountSurplus())
                .monthCountSurplus(raffleActivityAccountRes.getMonthCountSurplus())
                .totalCountSurplus(raffleActivityAccountRes.getTotalCountSurplus())
                .totalCount(raffleActivityAccountRes.getTotalCount())
                .userId(userId)
                .activityId(activityId)
                .monthCount(raffleActivityAccountRes.getMonthCount())
                .dayCount(raffleActivityAccountRes.getDayCount())
                .build();
    }

    /**
     * 查询账户月额度
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param month 月份
     * @return 活动账户（月）实体对象
     */
    @Override
    public ActivityAccountMonthEntity queryActivityAccountMonthByUserId(String userId, Long activityId, String month) {

        RaffleActivityAccountMonth raffleActivityAccountMonth = new RaffleActivityAccountMonth();
        raffleActivityAccountMonth.setUserId(userId);
        raffleActivityAccountMonth.setActivityId(activityId);
        raffleActivityAccountMonth.setMonth(month);

        RaffleActivityAccountMonth raffleActivityAccountMonthRes = raffleActivityAccountMonthDao.queryActivityAccountMonthByUserId(raffleActivityAccountMonth);
        if (raffleActivityAccountMonthRes == null){
            return null;
        }
        return ActivityAccountMonthEntity.builder()
                .month(month)
                .monthCountSurplus(raffleActivityAccountMonthRes.getMonthCountSurplus())
                .monthCount(raffleActivityAccountMonthRes.getMonthCount())
                .activityId(activityId)
                .userId(userId)
                .build();
    }

    /**
     * 查询账户日额度
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param day 当天
     * @return 活动账户（日）实体对象
     */
    @Override
    public ActivityAccountDayEntity queryActivityAccountDayByUserId(String userId, Long activityId, String day) {

        RaffleActivityAccountDay raffleActivityAccountDay = new RaffleActivityAccountDay();
        raffleActivityAccountDay.setActivityId(activityId);
        raffleActivityAccountDay.setUserId(userId);
        raffleActivityAccountDay.setDay(day);

        RaffleActivityAccountDay raffleActivityAccountDayRes = raffleActivityAccountDayDao.queryActivityAccountDayByUserId(raffleActivityAccountDay);

        if(raffleActivityAccountDayRes == null){
            return null;
        }

        return ActivityAccountDayEntity.builder()
                .activityId(activityId)
                .userId(userId)
                .day(day)
                .dayCount(raffleActivityAccountDayRes.getDayCount())
                .dayCountSurplus(raffleActivityAccountDayRes.getDayCountSurplus())
                .build();
    }

    /**
     * 保存聚合对象
     * @param createPartakeOrderAggregate 参与活动订单聚合对象
     */
    @Override
    public void saveCreatePartakeOrderAggregate(CreatePartakeOrderAggregate createPartakeOrderAggregate) {
        String userId = createPartakeOrderAggregate.getUserId();
        Long activityId = createPartakeOrderAggregate.getActivityId();
        ActivityAccountEntity activityAccountEntity = createPartakeOrderAggregate.getActivityAccountEntity();
        UserRaffleOrderEntity userRaffleOrderEntity = createPartakeOrderAggregate.getUserRaffleOrderEntity();
        ActivityAccountDayEntity activityAccountDayEntity = createPartakeOrderAggregate.getActivityAccountDayEntity();
        ActivityAccountMonthEntity activityAccountMonthEntity = createPartakeOrderAggregate.getActivityAccountMonthEntity();
        transactionTemplate.execute(status -> {
            try{
                // 更新总账户额度
                RaffleActivityAccount raffleActivityAccount = new RaffleActivityAccount();
                raffleActivityAccount.setUserId(userId);
                raffleActivityAccount.setActivityId(activityId);
                int totalAffectRow = raffleActivityAccountDao.updateActivityAccountSubtractionQuota(raffleActivityAccount);
                if (totalAffectRow != 1){
                    status.setRollbackOnly();
                    log.warn("写入创建参与活动记录，更新总账户额度不足，异常 userId: {} activityId: {}", userId, activityId);
                    throw new AppException(ResponseCode.ACCOUNT_QUOTA_ERROR.getCode(), ResponseCode.ACCOUNT_QUOTA_ERROR.getInfo());
                }
                // 创建或更新月账户，true - 存在则更新，false - 不存在则插入
                if (createPartakeOrderAggregate.isExistAccountMonth()){
                    RaffleActivityAccountMonth activityAccountMonth = new RaffleActivityAccountMonth();
                    activityAccountMonth.setMonth(activityAccountMonthEntity.getMonth());
                    activityAccountMonth.setUserId(userId);
                    activityAccountMonth.setActivityId(activityId);
                    int monthAffectRow = raffleActivityAccountMonthDao.updateActivityAccountMonthSubtractionQuota(activityAccountMonth);
                    if (monthAffectRow != 1){
                        status.setRollbackOnly();
                        log.warn("写入创建参与活动记录，更新总账户额度不足，异常 userId: {} activityId: {} month:{}", userId, activityId, activityAccountMonthEntity.getMonth());
                        throw new AppException(ResponseCode.ACCOUNT_MONTH_QUOTA_ERROR.getCode(), ResponseCode.ACCOUNT_MONTH_QUOTA_ERROR.getInfo());
                    }
                }else {
                    RaffleActivityAccountMonth activityAccountMonth = new RaffleActivityAccountMonth();
                    activityAccountMonth.setMonth(activityAccountMonthEntity.getMonth());
                    activityAccountMonth.setUserId(userId);
                    activityAccountMonth.setActivityId(activityId);
                    activityAccountMonth.setMonthCount(activityAccountMonthEntity.getMonthCount());
                    activityAccountMonth.setMonthCountSurplus(activityAccountMonthEntity.getMonthCountSurplus() - 1);
                    raffleActivityAccountMonthDao.insertActivityAccountMonth(activityAccountMonth);
                    // 新创建月账户，则更新总账表中月镜像额度
                    RaffleActivityAccount raffleActivityAccountMonthImageQuota = new RaffleActivityAccount();
                    raffleActivityAccountMonthImageQuota.setUserId(userId);
                    raffleActivityAccountMonthImageQuota.setActivityId(activityId);
                    raffleActivityAccountMonthImageQuota.setMonthCountSurplus(activityAccountEntity.getMonthCountSurplus() - 1);
                    raffleActivityAccountDao.updateActivityAccountMonthSurplusImageQuota(raffleActivityAccountMonthImageQuota);
                }
                // 创建或更新日账户，true - 存在则更新，false - 不存在则插入
                if (createPartakeOrderAggregate.isExistAccountDay()){
                    RaffleActivityAccountDay raffleActivityAccountDay = new RaffleActivityAccountDay();
                    raffleActivityAccountDay.setDay(activityAccountDayEntity.getDay());
                    raffleActivityAccountDay.setUserId(userId);
                    raffleActivityAccountDay.setActivityId(activityId);
                    int dayAffectRow = raffleActivityAccountDayDao.updateActivityAccountDaySubtractionQuota(raffleActivityAccountDay);
                    if (dayAffectRow != 1){
                        // 未更新成功则回滚
                        status.setRollbackOnly();
                        log.warn("写入创建参与活动记录，更新日账户额度不足，异常 userId: {} activityId: {} day: {}", userId, activityId, activityAccountDayEntity.getDay());
                        throw new AppException(ResponseCode.ACCOUNT_DAY_QUOTA_ERROR.getCode(), ResponseCode.ACCOUNT_DAY_QUOTA_ERROR.getInfo());
                    }
                }else {
                    RaffleActivityAccountDay raffleActivityAccountDay = new RaffleActivityAccountDay();
                    raffleActivityAccountDay.setDay(activityAccountDayEntity.getDay());
                    raffleActivityAccountDay.setUserId(userId);
                    raffleActivityAccountDay.setActivityId(activityId);
                    raffleActivityAccountDay.setDayCountSurplus(activityAccountDayEntity.getDayCountSurplus() - 1);
                    raffleActivityAccountDay.setDayCount(activityAccountDayEntity.getDayCount());
                    raffleActivityAccountDayDao.insertActivityAccountDay(raffleActivityAccountDay);
                    // 新创建日账户，则更新总账表中日镜像额度
                    RaffleActivityAccount raffleActivityAccountDayImageQuota = new RaffleActivityAccount();
                    raffleActivityAccountDayImageQuota.setUserId(userId);
                    raffleActivityAccountDayImageQuota.setActivityId(activityId);
                    raffleActivityAccountDayImageQuota.setDayCountSurplus(activityAccountEntity.getDayCountSurplus() - 1);
                    raffleActivityAccountDao.updateActivityAccountDaySurplusImageQuota(raffleActivityAccountDayImageQuota);
                }
                // 写入参与活动订单
                UserRaffleOrder userRaffleOrder = new UserRaffleOrder();
                userRaffleOrder.setActivityId(activityId);
                userRaffleOrder.setUserId(userId);
                userRaffleOrder.setActivityName(userRaffleOrderEntity.getActivityName());
                userRaffleOrder.setStrategyId(userRaffleOrderEntity.getStrategyId());
                userRaffleOrder.setOrderId(userRaffleOrderEntity.getOrderId());
                userRaffleOrder.setOrderTime(userRaffleOrderEntity.getOrderTime());
                userRaffleOrder.setOrderState(userRaffleOrderEntity.getOrderState().getCode());
                userRaffleOrderDao.insert(userRaffleOrder);
                return 1;
            }catch (DuplicateKeyException ex){
                status.setRollbackOnly();
                log.error("写入创建参与活动记录，唯一索引冲突 userId:{} activityId:{}", userId, activityId, ex);
                throw new AppException(ResponseCode.INDEX_DUP.getCode(), ResponseCode.INDEX_DUP.getInfo());
            }
        });
    }

    /**
     * 根据活动 ID 查找活动 SKU 集合
     * @param activityId 活动 ID
     * @return 活动 SKU 集合
     */
    @Override
    public List<ActivitySkuEntity> queryActivitySkuListByActivityId(Long activityId) {
        List<RaffleActivitySku> raffleActivitySkus = raffleActivitySkuDao.queryActivitySkuListByActivityId(activityId);
        List<ActivitySkuEntity> result = new ArrayList<>(raffleActivitySkus.size());
        for (RaffleActivitySku activitySkus : raffleActivitySkus) {
            ActivitySkuEntity activitySkuEntity = ActivitySkuEntity.builder()
                    .activityCountId(activitySkus.getActivityCountId())
                    .sku(activitySkus.getSku())
                    .activityId(activitySkus.getActivityId())
                    .stockCount(activitySkus.getStockCount())
                    .stockCountSurplus(activitySkus.getStockCountSurplus())
                    .build();
            result.add(activitySkuEntity);
        }
        return result;
    }

    /**
     * 查询用户当天抽奖次数
     * @param userId 用户 ID
     * @param activityId 活动 ID
     * @return 抽奖次数
     */
    @Override
    public Integer queryRaffleActivityAccountDayPartakeCount(String userId, Long activityId) {
        RaffleActivityAccountDay raffleActivityAccountDay = new RaffleActivityAccountDay();
        raffleActivityAccountDay.setUserId(userId);
        raffleActivityAccountDay.setActivityId(activityId);
        raffleActivityAccountDay.setDay(raffleActivityAccountDay.currentDay());
        Integer dayPartakeCount = raffleActivityAccountDayDao.queryRaffleActivityAccountDayPartakeCount(raffleActivityAccountDay);
        return dayPartakeCount == null ? 0 : dayPartakeCount;
    }
}
