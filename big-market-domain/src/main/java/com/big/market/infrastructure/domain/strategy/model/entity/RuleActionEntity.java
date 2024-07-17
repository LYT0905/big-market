package com.big.market.infrastructure.domain.strategy.model.entity;

import com.big.market.infrastructure.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @author LYT0905
 * @Description: 规则动作实体
 * @Date: 2024/07/16 11:05:12
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    // 规则模型 rule_weight rule_blacklist
    private String ruleModel;
    private T data;

    public static class RaffleEntity{

    }

    /**
     * 抽奖之前奖品规则过滤
     */
    @EqualsAndHashCode(callSuper = true)
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity{
        /**
         * 策略ID
         */
        private Long strategyId;

        /**
         * 权重值Key；用于抽奖时可以选择权重抽奖。
         */
        private String ruleWeightValueKey;

        /**
         * 奖品ID；
         */
        private Integer awardId;
    }


    /**
     * 抽奖中奖品规则过滤
     */
    static public class RaffleMiddleEntity extends RaffleEntity{

    }
}
