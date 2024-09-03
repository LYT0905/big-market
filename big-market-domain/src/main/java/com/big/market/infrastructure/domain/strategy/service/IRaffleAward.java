package com.big.market.infrastructure.domain.strategy.service;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 策略奖品接口
 * @Date: 2024/07/27 21:13:37
 */
public interface IRaffleAward {
    /**
     * 根据策略ID查询抽奖奖品列表配置
     *
     * @param strategyId 策略ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);

    /**
     * 根据活动ID查询抽奖奖品列表配置
     *
     * @param activityId 活动ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardListByActivityId(Long activityId);
}
