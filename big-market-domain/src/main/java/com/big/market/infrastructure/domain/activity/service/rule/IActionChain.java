package com.big.market.infrastructure.domain.activity.service.rule;

import com.big.market.infrastructure.domain.activity.model.entity.ActivityCountEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @author 莱特0905
 * @Description: 下单规则过滤接口
 * @Date: 2024/08/14 21:13:21
 */
public interface IActionChain extends IActionChainArmory{
    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
