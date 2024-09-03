package com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.engine;

import com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

import java.util.Date;

/**
 * @author LYT0905
 * @Description: 规则树组合接口
 * @Date: 2024/07/22 21:39:18
 */
public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId, Date endDateTime);
}
