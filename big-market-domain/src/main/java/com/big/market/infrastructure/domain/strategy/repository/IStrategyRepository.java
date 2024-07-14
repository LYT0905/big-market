package com.big.market.infrastructure.domain.strategy.repository;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;

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
     * @param strategyId 策略id
     * @param shuffleStrategyAwardSearchRateTablesSize  策略概率范围
     * @param shuffleStrategyAwardSearchRateTables 存储了乱序概率的table
     */
    void storeStrategyAwardSearchRateTables(Long strategyId, int shuffleStrategyAwardSearchRateTablesSize, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables);

    /**
     * 获取随机范围
     * @param strategyId 策略id
     */
    int getRandomRange(Long strategyId);

    /**
     * 获取策略奖品
     * @param strategyId 策略id
     * @param rateKey 概率范围
     */
    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
