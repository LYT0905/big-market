package com.big.market.infrastructure.domain.strategy.repository;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyRuleEntity;

import java.util.HashMap;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略服务仓储接口
 * @Date: 2024/07/14 10:49:56
 */
public interface IStrategyRepository {
    /**
     * 查询某策略id下所有策略奖品配置信息
     * @param strategyId 策略id
     */
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    /**
     * 存储到redis
     * @param key 策略id
     * @param shuffleStrategyAwardSearchRateTablesSize  策略概率范围
     * @param shuffleStrategyAwardSearchRateTables 存储了乱序概率的table
     */
    void storeStrategyAwardSearchRateTables(String key, int shuffleStrategyAwardSearchRateTablesSize, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables);

    /**
     * 获取概率范围
     * @param strategyId 策略id
     */
    int getRateRange(Long strategyId);

    /**
     * 获取概率范围
     * @param key 策略id
     */
    int getRateRange(String key);

    /**
     * 获取策略奖品
     * @param key 策略id
     * @param rateKey 概率范围
     */
    Integer getStrategyAwardAssemble(String key, int rateKey);

    /**
     * 根据策略id查找策略实体
     * @param strategyId 策略id
     */
    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    /**
     * 根据策略id和抽奖规则类型查找策略
     * @param strategyId 策略id
     * @param ruleModel  抽奖规则类型
     */
    StrategyRuleEntity queryStrategyRuleEntity(Long strategyId, String ruleModel);

    /**
     * 查找规则值(rule_value)
     * @param strategyId 策略id
     * @param awardId    奖品id
     * @param ruleModel  规则模型
     */
    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);
}
