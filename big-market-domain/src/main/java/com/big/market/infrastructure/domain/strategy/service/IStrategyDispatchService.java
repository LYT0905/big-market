package com.big.market.infrastructure.domain.strategy.service;

/**
 * @author LYT0905
 * @Description: 策略抽奖调度
 * @Date: 2024/07/15 10:45:44
 */
public interface IStrategyDispatchService {
    /**
     * 获取随机奖品id
     * @param strategyId 策略id
     */
    Integer getRandomAwardId(Long strategyId);

    /**
     * 根据策略id和权重值获取随机奖品id
     * @param strategyId 策略id
     * @param ruleWeightValue 权重值
     * @return
     */
    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);
}
