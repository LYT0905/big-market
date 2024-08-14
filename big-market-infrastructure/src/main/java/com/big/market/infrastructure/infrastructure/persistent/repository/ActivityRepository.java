package com.big.market.infrastructure.infrastructure.persistent.repository;

import com.big.market.infrastructure.domain.activity.model.aggregate.CreateOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityCountEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityOrderEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivitySkuEntity;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivityStateVO;
import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import com.big.market.infrastructure.infrastructure.persistent.dao.*;
import com.big.market.infrastructure.infrastructure.persistent.po.*;
import com.big.market.infrastructure.infrastructure.persistent.redis.RedissonService;
import com.big.market.infrastructure.types.common.Constants;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.client.RedisClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

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
    public void doSaveOrder(CreateOrderAggregate orderAggregate) {
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
}
