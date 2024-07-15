package com.big.market.infrastructure.domain.strategy.model.entity;

import com.big.market.infrastructure.types.common.Constants;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author LYT0905
 * @Description: 策略实体
 * @Date: 2024/07/15 10:53:09
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;
    /** 抽奖规则模型 */
    private String ruleModels;

    /**
     * 把抽奖规则根据空格分隔
     */
    public String[] ruleModels(){
        if (StringUtil.isNullOrEmpty(ruleModels)){
            return null;
        }
        return ruleModels.split(Constants.SPLIT);
    }


    /**
     * 获取规则权重
     */
    public String getRuleWeight(){
        String[] ruleModels = this.ruleModels();
       for (String ruleModel : ruleModels) {
           if (Constants.RULE_WEIGHT.equals(ruleModel)){
               return ruleModel;
           }
       }
       return null;
    }
}
