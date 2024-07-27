package com.big.market.infrastructure.domain.strategy.service.raffle;


import com.big.market.infrastructure.domain.strategy.model.valobj.RuleTreeVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.StrategyRuleModelVO;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.AbstractRaffleStrategy;
import com.big.market.infrastructure.domain.strategy.service.armory.IStrategyDispatchService;
import com.big.market.infrastructure.domain.strategy.service.rule.chain.ILogicChain;
import com.big.market.infrastructure.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author LYT0905
 * @Description: 默认抽奖实现
 * @Date: 2024/07/16 12:25:43
 */

@Service
@Slf4j
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {


    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatchService strategyDispatchService, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        super(repository, strategyDispatchService, defaultChainFactory, defaultTreeFactory);
    }

    /**
     * 抽奖计算，责任链抽象方法
     * @param userId 用户id
     * @param strategyId 奖品id
     */
    @Override
    protected DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
         return logicChain.logic(userId, strategyId);
    }

    /**
     * 抽奖结果过滤，决策树抽象方法
     * @param userId 用户id
     * @param awardId 奖品id
     * @param strategyId 策略id
     * @return 过滤结果【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
     */
    @Override
    protected DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId) {
        StrategyRuleModelVO strategyRuleModelVO = repository.queryStrategyRuleModels(strategyId, awardId);
        if (strategyRuleModelVO == null){
            return DefaultTreeFactory.StrategyAwardVO.builder()
                    .awardId(awardId)
                    .build();
        }
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId(strategyRuleModelVO.getRuleModels());
        if (ruleTreeVO == null){
            throw new RuntimeException("存在抽奖策略配置的规则模型 Key，未在库表 rule_tree、rule_tree_node、rule_tree_line 配置对应的规则树信息 " + strategyRuleModelVO.getRuleModels());
        }
        IDecisionTreeEngine decisionTreeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return decisionTreeEngine.process(userId, strategyId, awardId);
    }

    /**
     * 获取奖品库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */
    @Override
    public StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException {
        return repository.takeQueueValue();
    }

    /**
     * 更新奖品库存消耗记录
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    @Override
    public void updateStrategyAwardStock(Long strategyId, Integer awardId) {
        repository.updateStrategyAwardStock(strategyId, awardId);
    }
}
