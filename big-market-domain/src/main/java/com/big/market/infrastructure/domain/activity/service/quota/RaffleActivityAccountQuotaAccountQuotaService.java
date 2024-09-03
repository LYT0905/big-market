package com.big.market.infrastructure.domain.activity.service.quota;

import com.big.market.infrastructure.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.entity.*;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivitySkuStockKeyVO;
import com.big.market.infrastructure.domain.activity.model.valobj.OrderStateVO;
import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import com.big.market.infrastructure.domain.activity.service.IRaffleActivitySkuStockService;
import com.big.market.infrastructure.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;
import com.big.market.infrastructure.types.utils.SnowflakeUUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动服务
 * @Date: 2024/08/13 19:32:36
 */
@Service
@Slf4j
public class RaffleActivityAccountQuotaAccountQuotaService extends AbstractRaffleActivityAccountQuotaAccountQuota implements IRaffleActivitySkuStockService {


    public RaffleActivityAccountQuotaAccountQuotaService(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        super(activityRepository, defaultActivityChainFactory);
    }

    /**
     * 构建订单聚合对象
     * @param skuRechargeEntity 活动商品充值实体对象
     * @param activitySkuEntity 活动sku实体对象
     * @param activityEntity 活动实体对象
     * @param activityCountEntity 活动次数实体对象
     * @return 订单聚合对象
     */
    @Override
    protected CreateQuotaOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {

        ActivityOrderEntity activityOrderEntity = null;
        try {
            activityOrderEntity = ActivityOrderEntity.builder()
                    .activityId(activitySkuEntity.getActivityId())
                    // 公司里一般会有专门的雪花算法UUID服务，我们这里直接生成个12位就可以了。
                    .orderId(SnowflakeUUIDUtils.generateId(12))
                    .userId(skuRechargeEntity.getUserId())
                    .activityName(activityEntity.getActivityName())
                    .dayCount(activityCountEntity.getDayCount())
                    .monthCount(activityCountEntity.getMonthCount())
                    .totalCount(activityCountEntity.getTotalCount())
                    .orderTime(new Date())
                    .strategyId(activityEntity.getStrategyId())
                    .outBusinessNo(skuRechargeEntity.getOutBusinessNo())
                    .sku(skuRechargeEntity.getSku())
                    .state(OrderStateVO.completed)
                    .build();
        }catch (Throwable ex){
            log.error("订单ID生成错误");
        }


        return CreateQuotaOrderAggregate.builder()
                .activityId(activitySkuEntity.getActivityId())
                .userId(skuRechargeEntity.getUserId())
                .dayCount(activityCountEntity.getDayCount())
                .monthCount(activityCountEntity.getMonthCount())
                .totalCount(activityCountEntity.getTotalCount())
                .activityOrderEntity(activityOrderEntity)
                .build();
    }

    /**
     * 保存订单
     * @param orderAggregate 订单聚合对象
     */
    @Override
    protected void doSaveOrder(CreateQuotaOrderAggregate orderAggregate) {
        activityRepository.doSaveOrder(orderAggregate);
    }

    /**
     * 获取活动sku库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */
    @Override
    public ActivitySkuStockKeyVO takeQueueValue(Long sku) throws InterruptedException {
        return activityRepository.takeQueueValue(sku);
    }

    /**
     * 清空队列
     */
    @Override
    public void clearQueueValue(Long sku) {
        activityRepository.clearQueueValue(sku);
    }

    /**
     * 延迟队列 + 任务趋势更新活动sku库存
     *
     * @param sku 活动商品
     */
    @Override
    public void updateActivitySkuStock(Long sku) {
        activityRepository.updateActivitySkuStock(sku);
    }

    /**
     * 缓存库存以消耗完毕，清空数据库库存
     *
     * @param sku 活动商品
     */
    @Override
    public void clearActivitySkuStock(Long sku) {
        activityRepository.clearActivitySkuStock(sku);
    }

    /**
     * 查询用户当天抽奖次数
     * @param userId 用户 ID
     * @param activityId 活动 ID
     * @return 抽奖次数
     */
    @Override
    public Integer queryRaffleActivityAccountDayPartakeCount(String userId, Long activityId) {
        return activityRepository.queryRaffleActivityAccountDayPartakeCount(userId, activityId);
    }

    /**
     * 查询活动商品列表
     * @return 活动商品列表
     */
    @Override
    public List<Long> querySkuList() {
        return activityRepository.querySkuList();
    }
}
