package com.big.market.infrastructure.domain.strategy.service.rule.chain.impl;

import com.big.market.infrastructure.domain.strategy.service.armory.IStrategyDispatchService;
import com.big.market.infrastructure.domain.strategy.service.rule.chain.AbstractLogicChain;
import com.big.market.infrastructure.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author LYT0905
 * @Description: 兜底,默认的责任链「作为最后一个链」
 * @Date: 2024/07/18 10:24:54
 */
@Slf4j
@Component("rule_default")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyDispatchService dispatchService;

    /**
     * 默认规则，直接抽奖
     *
     * @param userId     用户id
     * @param strategyId 策略id
     * @return 奖品id
     */
    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        Integer awardId = dispatchService.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
        return DefaultChainFactory.StrategyAwardVO.builder()
                .awardId(awardId)
                .logicModel(ruleModel())
                .build();
    }

    /**
     * 规则模型
     * @return 规则模型
     */
    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_DEFAULT.getCode();
    }
}
