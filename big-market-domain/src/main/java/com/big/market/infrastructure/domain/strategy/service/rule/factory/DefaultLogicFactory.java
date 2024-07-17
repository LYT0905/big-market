package com.big.market.infrastructure.domain.strategy.service.rule.factory;

import com.alibaba.fastjson2.util.AnnotationUtils;
import com.big.market.infrastructure.domain.strategy.model.entity.RuleActionEntity;
import com.big.market.infrastructure.domain.strategy.service.annotation.LogicStrategy;
import com.big.market.infrastructure.domain.strategy.service.rule.ILogicFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LYT0905
 * @Description: 规则工厂
 * @Date: 2024/07/16 11:20:21
 */

@Service
public class DefaultLogicFactory {
    private Map<String, ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters){
        // 根据传过来的过滤列表进行遍历
        logicFilters.forEach(logicFilter ->
                {
                    // 查找是否有自定义注解
                    LogicStrategy annotation = AnnotationUtils.findAnnotation(logicFilter.getClass(), LogicStrategy.class);
                    if (annotation != null){
                        // key 为 注解的 规则模型 rule_weight rule_blacklist ,value 为此时的过滤
                        logicFilterMap.put(annotation.logicModel().getCode(), logicFilter);
                    }
                }
                );
    }

    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        return (Map<String, ILogicFilter<T>>) (Map<?, ?>) logicFilterMap;
    }



    @Getter
    @AllArgsConstructor
    public enum LogicModel{
        RULE_WEIGHT("rule_weight","【抽奖前规则】根据抽奖权重返回可抽奖范围KEY", "before"),
        RULE_BLACKLIST("rule_blacklist","【抽奖前规则】黑名单规则过滤，命中黑名单则直接返回", "before"),
        RULE_LOCK("rule_lock", "【抽奖中规则】抽奖n次后，对应奖品可解锁抽奖", "middle"),
        RULE_LUCK_AWARD("rule_luck_award", "【抽奖后规则】抽奖n次后，对应奖品可解锁抽奖", "after"),
        ;

        private final String code;
        private final String info;
        private final String type;

        public static boolean isMiddle(String code){
            return "middle".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

    }


}
