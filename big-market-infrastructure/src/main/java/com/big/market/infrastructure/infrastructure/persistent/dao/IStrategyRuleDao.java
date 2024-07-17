package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略规则Dao
 * @Date: 2024/07/13 17:18:06
 */
@Mapper
public interface IStrategyRuleDao {
    /**
     * 查找所有策略规则
     */
    List<StrategyRule> queryStrategyList();

    /**
     * 根据策略id和抽奖规则类型查找策略
     * @param strategyId 策略id
     * @param ruleModel  抽奖规则类型
     */
    StrategyRule queryStrategyRuleEntity(@Param("strategyId") Long strategyId, @Param("ruleModel") String ruleModel);

    /**
     * 查找规则值(rule_value)
     */
    String queryStrategyRuleValue(StrategyRule strategyRule);
}
