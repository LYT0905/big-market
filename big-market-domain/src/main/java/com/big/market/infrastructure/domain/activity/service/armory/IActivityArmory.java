package com.big.market.infrastructure.domain.activity.service.armory;

/**
 * @author 莱特0905
 * @Description: 活动装配预热
 * @Date: 2024/08/17 19:28:44
 */
public interface IActivityArmory {

    /**
     * 根据活动 ID 装配活动 sku
     * @param activityId 活动 ID
     * @return 是否装配成功
     */
    boolean assembleActivitySkuByActivityId(Long activityId);

    /**
     * 装配活动 sku
     * @param sku sku
     * @return 是否装配成功
     */
    boolean assembleActivitySku(Long sku);

}
