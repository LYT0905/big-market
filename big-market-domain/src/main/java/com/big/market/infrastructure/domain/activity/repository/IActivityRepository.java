package com.big.market.infrastructure.domain.activity.repository;

import com.big.market.infrastructure.domain.activity.model.aggregate.CreateOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityCountEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivitySkuEntity;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 活动服务仓储接口
 * @Date: 2024/08/13 19:30:09
 */
public interface IActivityRepository {
    /**
     * 通过sku查询活动信息
     * @param sku 商品 sku
     * @return 活动 Sku 实体
     */
    ActivitySkuEntity queryActivitySku(Long sku);

    /**
     * 查询活动信息
     * @param activityId 活动 ID
     * @return 活动信息实体
     */
    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    /**
     * 查询次数信息（用户在活动上可参与的次数）
     * @param activityCountId 活动次数 ID
     * @return 次数信息
     */
    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

    /**
     * 保存订单
     * @param orderAggregate 订单聚合对象
     */
    void doSaveOrder(CreateOrderAggregate orderAggregate);

    /**
     * 缓存活动sku库存信息
     * @param cacheKey 缓存key
     * @param stockCountSurplus 库存信息
     */
    void cacheActivitySkuStockCount(String cacheKey, Integer stockCountSurplus);

    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     *
     * @param sku 互动SKU
     * @param cacheKey 缓存key
     * @param endDateTime 活动结束时间，根据结束时间设置加锁的key为结束时间
     * @return 扣减结果
     */
    boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);

    /**
     * 延迟消费更新库存记录
     * @param activitySkuStockKeyVO 活动 id 和 sku id
     */
    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO activitySkuStockKeyVO);

    /**
     * 获取活动sku库存消耗队列
     *
     * @return 奖品库存Key信息
     */
    ActivitySkuStockKeyVO takeQueueValue();

    /**
     * 清空队列
     */
    void clearQueueValue();

    /**
     * 延迟队列 + 任务趋势更新活动sku库存
     *
     * @param sku 活动商品
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 缓存库存以消耗完毕，清空数据库库存
     *
     * @param sku 活动商品
     */
    void clearActivitySkuStock(Long sku);
}
