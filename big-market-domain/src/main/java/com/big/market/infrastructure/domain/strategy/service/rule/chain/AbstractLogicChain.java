package com.big.market.infrastructure.domain.strategy.service.rule.chain;

/**
 * @author LYT0905
 * @Description: 抽奖策略责任链，判断走那种抽奖策略。如；默认抽象、权重抽奖、黑名单抽奖
 * @Date: 2024/07/18 10:21:03
 */
public abstract class AbstractLogicChain implements ILogicChain{

    private ILogicChain next;

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }

    /**
     * 规则模型
     * @return 规则模型
     */
    protected abstract String ruleModel();
}
