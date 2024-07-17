package com.big.market.infrastructure.domain.strategy.service.rule.impl;

import com.big.market.infrastructure.domain.strategy.model.entity.RuleActionEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RuleMatterEntity;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.annotation.LogicStrategy;
import com.big.market.infrastructure.domain.strategy.service.rule.ILogicFilter;
import com.big.market.infrastructure.domain.strategy.service.rule.factory.DefaultLogicFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author LYT0905
 * @Description: 抽奖中置规则过滤(抽奖多少次才可以解锁)
 * @Date: 2024/07/17 09:51:39
 */
@Slf4j
@Component
@LogicStrategy(logicModel = DefaultLogicFactory.LogicModel.RULE_LOCK)
public class RuleLockLogicFilter implements ILogicFilter<RuleActionEntity.RaffleMiddleEntity> {
    @Resource
    private IStrategyRepository repository;
    // 用户抽奖次数，后续完成这部分流程开发的时候，从数据库/Redis中读取
    private Long userRaffleCount = 0L;


    @Override
    public RuleActionEntity<RuleActionEntity.RaffleMiddleEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());
        // 找出该规则下应该抽奖多少次解锁
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        long raffleCount = Long.parseLong(ruleValue);
        // 用户抽奖次数与应该要大于等于规定的次数(可以抽奖这个奖品)
        if (userRaffleCount >= raffleCount){
            return RuleActionEntity.<RuleActionEntity.RaffleMiddleEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 用户抽奖次数小于规则限定值，规则拦截
        return RuleActionEntity.<RuleActionEntity.RaffleMiddleEntity>builder()
                .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                .build();
    }
}
