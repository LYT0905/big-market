package com.big.market.infrastructure.domain.activity.model.aggregate;

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
public class CreateQuotaOrderAggregate {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 增加总次数
     */
    private Integer totalCount;

    /**
     * 增加月次数
     */
    private Integer monthCount;

    /**
     * 增加日次数
     */
    private Integer dayCount;

    /**
     * 活动订单实体
     */
    private ActivityOrderEntity activityOrderEntity;
}
