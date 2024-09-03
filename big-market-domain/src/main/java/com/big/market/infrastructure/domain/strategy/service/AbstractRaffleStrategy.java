package com.big.market.infrastructure.domain.strategy.service;

import com.big.market.infrastructure.domain.strategy.model.entity.RaffleAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RaffleFactorEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.armory.IStrategyDispatchService;
import com.big.market.infrastructure.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import com.big.market.infrastructure.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author LYT0905
 * @Description: 抽奖策略抽象类，定义抽奖的标准流程
 * @Date: 2024/07/16 11:01:23
 */

@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    // 策略仓储服务 -> domain层像一个大厨，仓储层提供米面粮油
    protected IStrategyRepository repository;
    // 策略调度服务 -> 只负责抽奖处理，通过新增接口的方式，隔离职责，不需要使用方关心或者调用抽奖的初始化
    protected IStrategyDispatchService strategyDispatchService;
    // 默认责任链构建工厂
    protected DefaultChainFactory defaultChainFactory;
    // 抽奖的决策树 -> 负责抽奖中到抽奖后的规则过滤，如抽奖到A奖品ID，之后要做次数的判断和库存的扣减等。
    protected DefaultTreeFactory defaultTreeFactory;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatchService strategyDispatchService, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        this.repository = repository;
        this.strategyDispatchService = strategyDispatchService;
        this.defaultChainFactory = defaultChainFactory;
        this.defaultTreeFactory = defaultTreeFactory;
    }


    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity){
        // 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. 责任链抽奖计算【这步拿到的是初步的抽奖ID，之后需要根据ID处理抽奖】注意；黑名单、权重等非默认抽奖的直接返回抽奖结果
        DefaultChainFactory.StrategyAwardVO logicChainStrategyAwardVO = raffleLogicChain(userId, strategyId);
        if (!DefaultChainFactory.LogicModel.RULE_DEFAULT.getCode().equals(logicChainStrategyAwardVO.getLogicModel())){

            return buildRaffleAwardEntity(strategyId, logicChainStrategyAwardVO.getAwardId(), null);

        }

        // 3. 规则树抽奖过滤【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
        DefaultTreeFactory.StrategyAwardVO logicTreeStrategyAwardVO = raffleLogicTree(userId, strategyId, logicChainStrategyAwardVO.getAwardId(), raffleFactorEntity.getEndDateTime());
        log.info("抽奖策略计算-规则树 {} {} {} {}", userId, strategyId, logicTreeStrategyAwardVO.getAwardId(), logicTreeStrategyAwardVO.getAwardRuleValue());


        return buildRaffleAwardEntity(strategyId, logicTreeStrategyAwardVO.getAwardId(), logicTreeStrategyAwardVO.getAwardRuleValue());

    }

    /**
     * 统一返回，抽取出来
     * @param strategyId 策略id
     * @param awardId    奖品id
     * @param awardConfig 奖品配置信息
     */
    private RaffleAwardEntity buildRaffleAwardEntity(Long strategyId, Integer awardId, String awardConfig){
        StrategyAwardEntity strategyAwardEntity = repository.queryStrategyAwardEntity(strategyId, awardId);
        return RaffleAwardEntity.builder()
                .sort(strategyAwardEntity.getSort())
                .awardId(awardId)
                .awardTitle(strategyAwardEntity.getAwardTitle())
                .awardConfig(awardConfig)
                .build();
    }

    /**
     * 抽奖计算，责任链抽象方法
     * @param userId 用户id
     * @param strategyId 奖品id
     */
    protected abstract DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId);

    /**
     * 抽奖结果过滤，决策树抽象方法
     * @param userId 用户id
     * @param awardId 奖品id
     * @param strategyId 策略id
     * @return 过滤结果【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
     */
    protected abstract DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId);

    /**
     * 抽奖结果过滤，决策树抽象方法
     * @param userId 用户id
     * @param awardId 奖品id
     * @param strategyId 策略id
     * @param endDateTime 活动结束时间
     * @return 过滤结果【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
     */
    protected abstract DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId, Date endDateTime);
}
