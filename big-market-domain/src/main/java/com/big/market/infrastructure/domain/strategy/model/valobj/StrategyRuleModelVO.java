package com.big.market.infrastructure.domain.strategy.model.valobj;

import com.big.market.infrastructure.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.big.market.infrastructure.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 抽奖策略规则规则值对象；值对象，没有唯一ID，仅限于从数据库查询对象
 * @Date: 2024/07/17 10:14:19
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleModelVO {

    private String ruleModels;

    /**
     * 获取抽奖中规则
     */
    public String[] raffleCenterRuleModelList(){
        List<String> result = new ArrayList<>();
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isMiddle(ruleModelValue)){
                result.add(ruleModelValue);
            }
        }
        return result.toArray(new String[0]);
    }
}
