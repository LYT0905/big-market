package com.big.market.infrastructure.domain.strategy.service.rule;

import com.big.market.infrastructure.domain.strategy.model.entity.RuleMatterEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RuleActionEntity;

/**
 * @author LYT0905
 * @Description: 抽奖规则过滤接口
 * @Date: 2024/07/16 11:03:09
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    /**
     * 规则过滤
     * @param ruleMatterEntity  规则物料实体对象，用于过滤规则的必要参数信息。
     * @return 规则动作实体
     */
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
