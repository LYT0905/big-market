package com.big.market.infrastructure.domain.strategy.model.entity;

import com.big.market.infrastructure.types.common.Constants;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LYT0905
 * @Description: 策略规则实体
 * @Date: 2024/07/15 11:25:14
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {

    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID【规则类型为策略，则不需要奖品ID】 */
    private Integer awardId;
    /** 抽象规则类型；1-策略规则、2-奖品规则 */
    private Integer ruleType;
    /** 抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】 */
    private String ruleModel;
    /** 抽奖规则比值 */
    private String ruleValue;
    /** 抽奖规则描述 */
    private String ruleDesc;

    /**
     * 获取权重值
     * 3000:102,103 4000:102,103,104,105
     */
    public Map<String, List<Integer>> getRuleWeightValues(){
        if (!Constants.RULE_WEIGHT.equals(ruleModel)){
            return null;
        }
        // 根据空格分隔开, 此时应该是 str[0] = 3000:102,103 str[1] = 4000:102,103,104,105
        String[] ruleValueGroups = this.ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            if (StringUtil.isNullOrEmpty(ruleValueGroup)){
                return resultMap;
            }
            // 根据冒号分隔，获取单个的权重 比如此时 ruleValueGroups = str[0] 分割之后 ruleValueGroup[0] = 3000 ruleValueGroup[1] = 102,103
            String[] splits = ruleValueGroup.split(Constants.COLON);
            if (splits.length != 2){
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueGroup);
            }
            // ruleValueGroup[1] = 102,103 分割之后 weightValues[0] = 102 weightValues[1] = 103
            String[] weightValues = splits[1].split(Constants.SPLIT);
            List<Integer> list = new ArrayList<>();
            for (String weightValue : weightValues) {
                list.add(Integer.parseInt(weightValue));
            }
            resultMap.put(ruleValueGroup, list);
        }
        return resultMap;
    }

}
