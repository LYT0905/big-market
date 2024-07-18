package com.big.market.infrastructure.domain.strategy.service.rule.chain.impl;

import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.rule.chain.AbstractLogicChain;
import com.big.market.infrastructure.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author LYT0905
 * @Description: 黑名单规则过滤责任链
 * @Date: 2024/07/18 10:24:34
 */

@Slf4j
@Component("rule_blacklist")
public class RuleBlackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Override
    public Integer logic(String userId, Long strategyId) {
        log.info("抽奖责任链-黑名单开始 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        // 此时黑名单拆分应该是 split[0] = 100 split[1] = user01, user02, user03
        String[] split = ruleValue.split(Constants.COLON);

        // 获取奖品id
        Integer awardId = Integer.parseInt(split[0]);
        // 拆分黑名单用户
        String[] userBlackIds = split[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            // 如果用户是黑名单里的一员
            if (userBlackId.equals(userId)) {
                log.info("抽奖责任链-黑名单接管 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
                return awardId;
            }
        }
        // 过滤其他责任链
        log.info("抽奖责任链-黑名单放行 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    /**
     * 规则模型
     * @return 规则模型
     */
    @Override
    protected String ruleModel() {
        return "rule_blacklist";
    }
}
