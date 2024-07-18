package com.big.market.infrastructure.domain.strategy.service.rule.chain;

/**
 * @author LYT0905
 * @Description: 责任链过滤
 * @Date: 2024/07/18 10:16:43
 */
public interface ILogicChain extends ILogicChainArmory{

    /**
     * 责任链接口
     * @param userId  用户id
     * @param strategyId  策略id
     * @return 奖品id
     */
    Integer logic(String userId, Long strategyId);
}
