package com.big.market.infrastructure.domain.activity.service.rule;

/**
 * @author 莱特0905
 * @Description: 责任链装配
 * @Date: 2024/08/14 21:14:54
 */
public interface IActionChainArmory {
    /**
     * 获取责任链下一个节点
     * @return 责任链下一个节点
     */
    IActionChain next();

    /**
     * 装配责任链下一个节点
     * @param next 下一个节点
     * @return
     */
    IActionChain appendNext(IActionChain next);

}
