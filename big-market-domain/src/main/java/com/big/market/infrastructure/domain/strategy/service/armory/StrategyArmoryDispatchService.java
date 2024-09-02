package com.big.market.infrastructure.domain.strategy.service.armory;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyRuleEntity;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.types.common.Constants;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author LYT0905
 * @Description: 策略装配库(兵工厂)，负责初始化策略计算
 * @Date: 2024/07/14 10:45:38
 */

@Service
@Slf4j
public class StrategyArmoryDispatchService implements IStrategyArmoryService, IStrategyDispatchService {

    @Resource
    private IStrategyRepository strategyRepository;

    /**
     * 根据 活动id 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     * @param activityId 活动id
     */
    @Override
    public boolean assembleLotteryStrategyByActivityId(Long activityId) {
        Long strategyId = strategyRepository.queryStrategyIdByActivityId(activityId);
        return assembleLotteryStrategy(strategyId);
    }

    /**
     * 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     * @param strategyId 策略id
     */
    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        //  1. 查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities = strategyRepository.queryStrategyAwardList(strategyId);
        if (CollectionUtils.isEmpty(strategyAwardEntities)){
            return false;
        }

        // 对库存进行装配
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Integer awardId = strategyAwardEntity.getAwardId();
            // 库存
            Integer awardCount = strategyAwardEntity.getAwardCount();
            cacheStrategyAwardCount(strategyId, awardId, awardCount);
        }


        // 全量初始化
        assembleLotteryStrategy(String.valueOf(strategyId), strategyAwardEntities);
        // 权重策略配置 - 适用于 rule_weight 权重规则配置
        StrategyEntity strategyEntity = strategyRepository.queryStrategyEntityByStrategyId(strategyId);
        String ruleWeight = strategyEntity.getRuleWeight();
        if (StringUtil.isNullOrEmpty(ruleWeight)){
            return false;
        }
        StrategyRuleEntity strategyRuleEntity = strategyRepository.queryStrategyRuleEntity(strategyId, ruleWeight);
        if (strategyRuleEntity == null){
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(), ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());
        }
        // 获取权重值map
        Map<String, List<Integer>> ruleWeightValuesMap = strategyRuleEntity.getRuleWeightValues();
        Set<String> ruleWeightValuesKeys = ruleWeightValuesMap.keySet();
        for (String key : ruleWeightValuesKeys) {
            // key[0] = 3000:102,103
            // list = 102, 103
            List<Integer> ruleWeightValues = ruleWeightValuesMap.get(key);
            ArrayList<StrategyAwardEntity> strategyAwardEntitiesClone = new ArrayList<>(strategyAwardEntities);
            strategyAwardEntitiesClone.removeIf(entity -> !ruleWeightValues.contains(entity.getAwardId()));
            assembleLotteryStrategy(String.valueOf(strategyId).concat(Constants.UNDERLINE).concat(key), strategyAwardEntitiesClone);
        }
        return true;
    }

    /**
     * 对奖品库存信息进行装配
     * @param strategyId 策略id
     * @param awardId    奖品id
     * @param awardCount  奖品库存
     */
    private void cacheStrategyAwardCount(Long strategyId, Integer awardId, Integer awardCount) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        strategyRepository.cacheStrategyAwardCount(cacheKey, awardCount);
    }

    /**
     * 获取随机奖品id
     * @param strategyId 策略id
     */
    @Override
    public Integer getRandomAwardId(Long strategyId) {
        // 分布式部署下，不一定为当前应用做的策略装配。也就是值不一定会保存到本应用，而是分布式应用，所以需要从 Redis 中获取。
        int rateRange = strategyRepository.getRateRange(strategyId);
        // 通过生成的随机值，获取概率值奖品查找表的结果
        return strategyRepository.getStrategyAwardAssemble(String.valueOf(strategyId), new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {
        String key = String.valueOf(strategyId).concat("_").concat(ruleWeightValue);
        // 分布式部署下，不一定为当前应用做的策略装配。也就是值不一定会保存到本应用，而是分布式应用，所以需要从 Redis 中获取。
        int rateRange = strategyRepository.getRateRange(key);
        // 通过生成的随机值，获取概率值奖品查找表的结果
        return strategyRepository.getStrategyAwardAssemble(key, new SecureRandom().nextInt(rateRange));

    }

    private void assembleLotteryStrategy(String strategyId, List<StrategyAwardEntity> strategyAwardEntities){
        // 查找最小概率值
        BigDecimal minRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        // 获取概率总和
        BigDecimal totalRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 获取范围，是千分位、万分位或者十分位(1 % 0.0001)
        if (BigDecimal.ZERO.compareTo(minRate) == 0){
            // minRate 是 0，将其最后一个精度位加 1
            //String minRateStr = minRate.toPlainString();
            int scale = minRate.scale();
            minRate = minRate.add(BigDecimal.ONE.movePointLeft(scale));
            //minRate = minRate.add(BigDecimal.valueOf(0.1));
        }
        BigDecimal rangeRate = totalRate.divide(minRate, 0, RoundingMode.CEILING);

        // 生成策略
        ArrayList<Integer> strategyAwardSearchRateTables = new ArrayList<>(rangeRate.intValue());
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Integer awardId = strategyAwardEntity.getAwardId();
            BigDecimal awardRate = strategyAwardEntity.getAwardRate();

            // 计算每个概率范围所需要的存放查找表的数量
            for (int i = 0; i < rangeRate.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++){
                strategyAwardSearchRateTables.add(awardId);
            }
        }

        // 乱序
        Collections.shuffle(strategyAwardSearchRateTables);

        HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables = new HashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardSearchRateTables.put(i, strategyAwardSearchRateTables.get(i));
        }

        // 存到redis
        strategyRepository.storeStrategyAwardSearchRateTables(strategyId, shuffleStrategyAwardSearchRateTables.size(), shuffleStrategyAwardSearchRateTables);
    }

    /**
     * 扣减库存
     * @param strategyId 策略id
     * @param awardId 奖品id
     * @return 库存是否扣减成功
     */
    @Override
    public Boolean subtractionAwardCount(Long strategyId, Integer awardId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        return strategyRepository.subtractionAwardCount(cacheKey);
    }
}
