package com.big.market.infrastructure.domain.activity.model.entity;

import lombok.Data;

/**
 * @author 莱特0905
 * @Description: 参与抽奖活动实体对象
 * @Date: 2024/08/23 16:08:46
 */
@Data
public class PartakeRaffleActivityEntity {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

}
