package com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.engine.impl;

import com.big.market.infrastructure.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleTreeNodeLineVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleTreeNodeVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleTreeVO;
import com.big.market.infrastructure.domain.strategy.service.rule.tree.ILogicTreeNode;
import com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author LYT0905
 * @Description: 决策树引擎
 * @Date: 2024/07/22 21:40:43
 */
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {

    private final Map<String, ILogicTreeNode> logicTreeNodeMap;
    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeMap, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeMap = logicTreeNodeMap;
        this.ruleTreeVO = ruleTreeVO;
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId, Date endDateTime) {
        DefaultTreeFactory.StrategyAwardVO strategyAwardData = null;

        // 获取基础信息
        String nextTreeNode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();

        // 获取起始节点「根节点记录了第一个要执行的规则」
        RuleTreeNodeVO ruleTreeNodeVO = treeNodeMap.get(nextTreeNode);


        while (nextTreeNode != null){
            // 获取决策节点
            ILogicTreeNode logicTreeNode = logicTreeNodeMap.get(ruleTreeNodeVO.getRuleKey());
            String ruleValue = ruleTreeNodeVO.getRuleValue();
            // 决策节点计算
            DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId, strategyId, awardId, ruleValue, endDateTime);
            strategyAwardData = logicEntity.getStrategyAwardVO();
            RuleLogicCheckTypeVO logicCheckTypeVO = logicEntity.getRuleLogicCheckType();
            log.info("决策树引擎【{}】treeId:{} node:{} code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(), nextTreeNode, logicCheckTypeVO.getCode());

            // 获取下个节点
            nextTreeNode = nextNode(logicCheckTypeVO.getCode(), ruleTreeNodeVO.getTreeNodeLineVOList());
            ruleTreeNodeVO = treeNodeMap.get(nextTreeNode);
        }
        return strategyAwardData;
    }

    /**
     * 获取下一个节点
     * @param matterValue 物料值
     * @param ruleTreeNodeLineVOList
     * @return
     */
    public String nextNode(String matterValue, List<RuleTreeNodeLineVO> ruleTreeNodeLineVOList){
        if (ruleTreeNodeLineVOList == null || ruleTreeNodeLineVOList.size() == 0){
            return null;
        }
        for (RuleTreeNodeLineVO ruleTreeNodeLineVO : ruleTreeNodeLineVOList) {
            if (decisionLogic(matterValue, ruleTreeNodeLineVO)){
                return ruleTreeNodeLineVO.getRuleNodeTo();
            }
        }
        return null;
    }


    /**
     * 逻辑决定
     * @param matterValue 物料值
     * @param nodeLine 规则树节点指向线对象。用于衔接 from->to 节点链路关系
     */
    public boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            // 以下规则暂时不需要实现
            case GT:
            case LT:
            case GE:
            case LE:
            default:
                return false;
        }
    }

}
