package com.big.market.infrastructure.domain.strategy.service.rule.impl;

import com.big.market.infrastructure.domain.strategy.model.entity.RuleMatterEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RuleActionEntity;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.annotation.LogicStrategy;
import com.big.market.infrastructure.domain.strategy.service.rule.ILogicFilter;
import com.big.market.infrastructure.domain.strategy.service.rule.factory.DefaultLogicFactory;
import com.big.market.infrastructure.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author LYT0905
 * @Description: 【抽奖前规则】黑名单用户过滤规则
 * @Date: 2024/07/16 11:32:51
 */

@Slf4j
@Component
@LogicStrategy(logicModel = DefaultLogicFactory.LogicModel.RULE_BLACKLIST)
public class RuleBackListLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {
    @Resource
    private IStrategyRepository repository;

    /**
     * 黑名单过滤
     * @param ruleMatterEntity  规则物料实体对象，用于过滤规则的必要参数信息。
     * @return 过滤结果
     */
    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-黑名单 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

        String userId = ruleMatterEntity.getUserId();
        // 查找规则值 rule_value字段
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());

        // 此时黑名单拆分应该是 split[0] = 100 split[1] = user01, user02, user03
        String[] split = ruleValue.split(Constants.COLON);

        // 获取奖品id
        Integer awardId = Integer.parseInt(split[0]);
        // 拆分黑名单用户
        String[] userBlackIds = split[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            // 如果用户是黑名单里的一员
            if (userBlackId.equals(userId)){
                return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                        .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                        .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                        .ruleModel(DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode())
                        .data(RuleActionEntity.RaffleBeforeEntity.builder()
                                .strategyId(ruleMatterEntity.getStrategyId())
                                .awardId(awardId)
                                .build())
                        .build();
            }
        }
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }
}
