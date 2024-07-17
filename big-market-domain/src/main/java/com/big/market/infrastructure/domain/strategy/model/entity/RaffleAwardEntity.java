package com.big.market.infrastructure.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYT0905
 * @Description: 抽奖奖品实体
 * @Date: 2024/07/16 10:56:17
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {
    /** 策略 ID */
    private Long strategyId;
    /** 抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /** 奖品对接标识 - 每一个都是一个对应的发奖策略 */
    private String awardKey;
    /** 奖品配置信息 */
    private String awardConfig;
    /** 奖品内容描述 */
    private String awardDesc;
}
