package com.big.market.infrastructure.domain.strategy.service.raffle;

import com.big.market.infrastructure.domain.strategy.model.entity.RaffleAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RaffleFactorEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RuleActionEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyEntity;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.StrategyRuleModelVO;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.domain.strategy.service.IRaffleStrategy;
import com.big.market.infrastructure.domain.strategy.service.armory.IStrategyDispatchService;
import com.big.market.infrastructure.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

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

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatchService strategyDispatchService) {
        this.repository = repository;
        this.strategyDispatchService = strategyDispatchService;
    }


    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity){
        // 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        // 查询策略
        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        // 抽奖前 - 规则过滤
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = this.doCheckRaffleBeforeLogic(
                RaffleFactorEntity.builder().userId(userId).strategyId(strategyId).build(),
                strategyEntity.ruleModels());
        // 如果被规则接管
        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionEntity.getCode())) {
            if (DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 黑名单返回固定的奖品ID
                return RaffleAwardEntity.builder()
                        .awardId(ruleActionEntity.getData().getAwardId())
                        .build();
            } else if (DefaultLogicFactory.LogicModel.RULE_WEIGHT.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 权重根据返回的信息进行抽奖
                RuleActionEntity.RaffleBeforeEntity raffleBeforeEntity = ruleActionEntity.getData();
                String ruleWeightValueKey = raffleBeforeEntity.getRuleWeightValueKey();
                Integer awardId = strategyDispatchService.getRandomAwardId(strategyId, ruleWeightValueKey);
                return RaffleAwardEntity.builder()
                        .awardId(awardId)
                        .build();
            }
        }

        // 没有被规则接管走默认抽奖流程
        Integer awardId = strategyDispatchService.getRandomAwardId(strategyId);

        // 查询奖品规则「抽奖中（拿到奖品ID时，过滤规则）、抽奖后（扣减完奖品库存后过滤，抽奖中拦截和无库存则走兜底）」
        StrategyRuleModelVO strategyRuleModelVO = repository.queryStrategyRuleModels(strategyId, awardId);

        RuleActionEntity<RuleActionEntity.RaffleMiddleEntity> raffleMiddleEntityRuleActionEntity = this.doCheckRaffleMiddleLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .awardId(awardId)
                .strategyId(strategyId)
                .build(),
                strategyRuleModelVO.raffleCenterRuleModelList());
        // 如果不符合规则
        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals( raffleMiddleEntityRuleActionEntity.getCode())){
            log.info("【临时日志】中奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励。");
            return RaffleAwardEntity.builder()
                    .awardDesc("中奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励。")
                    .build();
        }


        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();

    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);
    protected abstract RuleActionEntity<RuleActionEntity.RaffleMiddleEntity> doCheckRaffleMiddleLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

}
