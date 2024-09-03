package com.big.market.api.dto;

import lombok.Data;

/**
 * @author 莱特0905
 * @Description: 抽奖奖品列表，请求对象
 * @Date: 2024/07/27 20:52:23
 */
@Data
public class RaffleAwardListRequestDTO {
    @Deprecated
    // 抽奖策略ID
    private Long strategyId;
    // 活动 ID
    private Long activityId;
    // 用户 ID
    private String userId;
}
