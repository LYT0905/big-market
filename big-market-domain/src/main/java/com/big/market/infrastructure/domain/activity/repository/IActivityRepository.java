package com.big.market.infrastructure.domain.activity.repository;

import com.big.market.infrastructure.domain.activity.model.aggregate.CreateOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityCountEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivitySkuEntity;

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
}
