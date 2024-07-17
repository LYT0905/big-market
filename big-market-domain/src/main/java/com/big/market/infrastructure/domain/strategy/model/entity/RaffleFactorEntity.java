package com.big.market.infrastructure.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYT0905
 * @Description: 抽奖因子实体
 * @Date: 2024/07/16 10:49:37
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 策略id
     */
    private Long strategyId;
}
