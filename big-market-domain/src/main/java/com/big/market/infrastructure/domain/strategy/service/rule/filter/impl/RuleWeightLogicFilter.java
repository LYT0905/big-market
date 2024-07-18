package com.big.market.infrastructure.domain.strategy.service.rule.filter.impl;

import com.big.market.infrastructure.domain.strategy.model.entity.RuleActionEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RuleMatterEntity;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.annotation.LogicStrategy;
import com.big.market.infrastructure.domain.strategy.service.rule.filter.ILogicFilter;
import com.big.market.infrastructure.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import com.big.market.infrastructure.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author LYT0905
 * @Description: 【抽奖前规则】抽奖权重过滤规则
 * @Date: 2024/07/16 11:58:33
 */

@Slf4j
@Component
@LogicStrategy(logicModel = DefaultLogicFactory.LogicModel.RULE_WEIGHT)
public class RuleWeightLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {
    @Resource
    private IStrategyRepository repository;
    private Long userScore = 4500L;

    /**
     * 权重规则过滤；
     * 1. 权重规则格式；3000:102,103 4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     * 2. 解析数据格式；判断哪个范围符合用户的特定抽奖范围
     * @param ruleMatterEntity  规则物料实体对象，用于过滤规则的必要参数信息。
     * @return 过滤结果
     */
    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-权重范围 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());
        String userId = ruleMatterEntity.getUserId();
        Long strategyId = ruleMatterEntity.getStrategyId();

        // 1. 根据用户ID查询用户抽奖消耗的积分值，本章节我们先写死为固定的值。后续需要从数据库中查询。
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        Map<Long, String> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if (null == analyticalValueGroup || analyticalValueGroup.isEmpty()) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 2. 转换Keys值，并默认排序
        ArrayList<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        // 先从小到大
//        Collections.sort(analyticalSortedKeys);
//        // 接着反转，因为要从大到小进行比较，这样子找出来的才是第一个比用户积分大的 3000, 4000, 5000; 假设用户现在4500，那么如果不从大到小的话，就是找到3000
//        Collections.reverse(analyticalSortedKeys);

        analyticalSortedKeys.sort((var1, var2) -> Math.toIntExact(var2 - var1));
        
        // 3. 找出最小符合的值，也就是【4500 积分，能找到 4000:102,103,104,105】、【5000 积分，能找到 5000:102,103,104,105,106,107】
        Long nextValue = analyticalSortedKeys.stream()
                .filter(key -> userScore >= key)
                .findFirst()
                .orElse(null);

        if (nextValue != null){
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .ruleModel(DefaultLogicFactory.LogicModel.RULE_WEIGHT.getCode())
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .ruleWeightValueKey(analyticalValueGroup.get(nextValue))
                            .awardId(ruleMatterEntity.getAwardId())
                            .strategyId(strategyId)
                            .build())
                    .build();
        }


        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();

    }

    private Map<Long, String> getAnalyticalValue(String ruleValue){
        // 首先根据空格分隔 ruleValueGroups[0] = 3000:102,103 ruleValueGroups[1] = 4000:102,103,104,105
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long, String> ruleValueMap = new HashMap<>();
        for (String ruleValueKey : ruleValueGroups) {
            // 检查输入是否为空
            if (ruleValueKey == null || ruleValueKey.isEmpty()) {
                return ruleValueMap;
            }
            // 分割字符串以获取键和值 parts[0] = 3000  parts[1] = 102,103
            String[] parts = ruleValueKey.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueKey);
            }
            ruleValueMap.put(Long.parseLong(parts[0]), ruleValueKey);
        }
        return ruleValueMap;

    }
}
