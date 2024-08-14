package com.big.market.infrastructure.domain.activity.service.rule;

/**
 * @author 莱特0905
 * @Description: 下单规则责任链抽象类
 * @Date: 2024/08/14 21:18:13
 */
public abstract class AbstractActionChain implements IActionChain{

    private IActionChain next;

    @Override
    public IActionChain next() {
        return next;
    }

    @Override
    public IActionChain appendNext(IActionChain next) {
        this.next = next;
        return next;
    }
}
