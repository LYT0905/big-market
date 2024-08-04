package com.big.market.infrastructure.domain.strategy.service.rule.chain.factory;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyEntity;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.rule.chain.ILogicChain;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LYT0905
 * @Description: 默认责任链工厂
 * @Date: 2024/07/18 10:54:49
 */

@Service
public class DefaultChainFactory {
    // 原型模式获取对象
    private final ApplicationContext applicationContext;
    // 存放策略链，策略ID -> 责任链
    private final Map<Long, ILogicChain> logicChainMap;
    private final IStrategyRepository repository;

    public DefaultChainFactory(ApplicationContext applicationContext, IStrategyRepository repository) {
        this.applicationContext = applicationContext;
        this.logicChainMap = new ConcurrentHashMap<>();
        this.repository = repository;
    }

    /**
     * 通过策略ID，构建责任链
     *
     * @param strategyId 策略ID
     * @return LogicChain
     */
    public ILogicChain openLogicChain(Long strategyId){
        ILogicChain cacheLogicChain = logicChainMap.get(strategyId);
        if (cacheLogicChain != null){
            return cacheLogicChain;
        }

        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategy.ruleModels();
        // 如果没有规则就走默认责任链逻辑
        if (ruleModels == null || ruleModels.length == 0){
            ILogicChain ruleDefaultLogicChain = applicationContext.getBean(LogicModel.RULE_DEFAULT.getCode(), ILogicChain.class);
            // 写入缓存
            logicChainMap.put(strategyId, ruleDefaultLogicChain);
            return ruleDefaultLogicChain;
        }
        // 获取第一个规则
        ILogicChain logicChain = applicationContext.getBean(ruleModels[0], ILogicChain.class);
        ILogicChain current = logicChain;
        // 开始进行构建
        for (int i = 1; i < ruleModels.length; i++){
            ILogicChain nextChain = applicationContext.getBean(ruleModels[i], ILogicChain.class);
            current = current.appendNext(nextChain);
        }
        // 最后添加一个默认的责任
        current.appendNext(applicationContext.getBean(LogicModel.RULE_DEFAULT.getCode(), ILogicChain.class));
        // 写入缓存
        logicChainMap.put(strategyId, logicChain);
        return logicChain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {
        /** 抽奖奖品ID - 内部流转使用 */
        private Integer awardId;
        /**  */
        private String logicModel;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_DEFAULT("rule_default", "默认抽奖"),
        RULE_BLACKLIST("rule_blacklist", "黑名单抽奖"),
        RULE_WEIGHT("rule_weight", "权重规则"),
        ;

        private final String code;
        private final String info;

    }

}
