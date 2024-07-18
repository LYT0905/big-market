package com.big.market.infrastructure.domain.strategy.service.rule.chain;

/**
 * @author LYT0905
 * @Description: 责任链装配接口
 * @Date: 2024/07/18 11:06:33
 */
public interface ILogicChainArmory {
    /**
     * 添加下一个节点
     *
     * @param next 下一个接口
     * @return
     */
    ILogicChain appendNext(ILogicChain next);

    /**
     * 获取next节点
     * @return next节点
     */
    ILogicChain next();
}
