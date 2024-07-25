package com.big.market.infrastructure.domain.strategy.model.valobj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

}
