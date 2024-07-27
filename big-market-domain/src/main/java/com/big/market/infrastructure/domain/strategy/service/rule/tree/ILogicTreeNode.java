package com.big.market.infrastructure.domain.strategy.service.rule.tree;

import com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author LYT0905
 * @Description: 规则树接口
 * @Date: 2024/07/22 21:28:07
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);
}
