package com.big.market.infrastructure.domain.strategy.service;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
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
public class StrategyArmoryService implements IStrategyArmoryService {

    @Resource
    private IStrategyRepository strategyRepository;

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
        return true;
    }

    /**
     * 获取随机奖品id
     * @param strategyId 策略id
     */
    @Override
    public Integer getRandomAwardId(Long strategyId) {
        // 分布式部署下，不一定为当前应用做的策略装配。也就是值不一定会保存到本应用，而是分布式应用，所以需要从 Redis 中获取。
        int rateRange = strategyRepository.getRandomRange(strategyId);
        // 通过生成的随机值，获取概率值奖品查找表的结果
        return strategyRepository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
