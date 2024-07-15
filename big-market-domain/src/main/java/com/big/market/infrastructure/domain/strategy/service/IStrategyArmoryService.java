package com.big.market.infrastructure.domain.strategy.service;

/**
 * @author LYT0905
 * @Description: 策略装配库(兵工厂)，负责初始化策略计算
 * @Date: 2024/07/14 10:45:19
 */
public interface IStrategyArmoryService {
    /**
     * 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     * @param strategyId 策略id
     */
    boolean assembleLotteryStrategy(Long strategyId);
}
