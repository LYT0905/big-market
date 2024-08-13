package com.big.market.infrastructure.domain.activity.model.aggregate;

import com.big.market.infrastructure.domain.activity.model.entity.ActivityAccountEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 莱特0905
 * @Description: 下单聚合对象
 * @Date: 2024/08/13 19:31:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderAggregate {
    /**
     * 活动账户实体
     */
    private ActivityAccountEntity activityAccountEntity;
    /**
     * 活动订单实体
     */
    private ActivityOrderEntity activityOrderEntity;
}
