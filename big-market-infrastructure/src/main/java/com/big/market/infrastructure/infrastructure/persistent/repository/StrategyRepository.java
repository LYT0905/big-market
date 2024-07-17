package com.big.market.infrastructure.infrastructure.persistent.repository;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyRuleEntity;
import com.big.market.infrastructure.domain.strategy.model.valobj.StrategyRuleModelVO;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.infrastructure.persistent.dao.IStrategyAwardDao;
import com.big.market.infrastructure.infrastructure.persistent.dao.IStrategyDao;
import com.big.market.infrastructure.infrastructure.persistent.dao.IStrategyRuleDao;
import com.big.market.infrastructure.infrastructure.persistent.po.Strategy;
import com.big.market.infrastructure.infrastructure.persistent.po.StrategyAward;
import com.big.market.infrastructure.infrastructure.persistent.po.StrategyRule;
import com.big.market.infrastructure.infrastructure.persistent.redis.IRedisService;
import com.big.market.infrastructure.types.common.Constants;
import org.redisson.api.RMap;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略服务仓储实现
 * @Date: 2024/07/14 10:50:33
 */

@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IRedisService redisService;
    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyRuleDao strategyRuleDao;

    /**
     * 查询某策略id下所有策略奖品配置信息
     * @param strategyId 策略id
     */
    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        // 首先判断redis中是否存在
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntities = redisService.getValue(cacheKey);
        // 存在返回
        if (!CollectionUtils.isEmpty(strategyAwardEntities)){
            return strategyAwardEntities;
        }
        // 从数据库中查询
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        // 如果没有直接返回null
        if (CollectionUtils.isEmpty(strategyAwards)){
            return null;
        }
        // 因为没有走缓存，所以这个肯定为空
        strategyAwardEntities = new ArrayList<>(strategyAwards.size());
        // 进行赋值
        for (StrategyAward strategyAward : strategyAwards) {
            StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                    .awardCount(strategyAward.getAwardCount())
                    .awardId(strategyAward.getAwardId())
                    .awardRate(strategyAward.getAwardRate())
                    .awardCountSurplus(strategyAward.getAwardCountSurplus())
                    .strategyId(strategyAward.getStrategyId())
                    .build();
            strategyAwardEntities.add(strategyAwardEntity);
        }
        redisService.setValue(cacheKey, strategyAwardEntities);
        return strategyAwardEntities;
    }

    /**
     * 存储到redis
     * @param key 策略id
     * @param  shuffleStrategyAwardSearchRateTablesSize  策略概率范围
     * @param shuffleStrategyAwardSearchRateTables 存储了乱序概率的map
     */
    @Override
    public void storeStrategyAwardSearchRateTables(String key, int  shuffleStrategyAwardSearchRateTablesSize, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables) {
        // 存储抽奖范围概率值, 如10000,用于生成1000的范围随机数
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key,  shuffleStrategyAwardSearchRateTablesSize);
        // 存储概率查找表
        RMap<Integer, Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key);
        cacheRateTable.putAll(shuffleStrategyAwardSearchRateTables);
    }

    /**
     * 获取随机范围
     * @param strategyId 策略id
     */
    @Override
    public int getRateRange(Long strategyId) {
        return getRateRange(String.valueOf(strategyId));
    }

    /**
     * 获取随机范围
     * @param key 策略id
     */
    @Override
    public int getRateRange(String key) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key);
    }

    /**
     * 获取策略奖品
     * @param key 策略id
     * @param rateKey 概率范围
     */
    @Override
    public Integer getStrategyAwardAssemble(String key, int rateKey) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key, rateKey);
    }

    /**
     * 根据策略id查找策略实体
     * @param strategyId 策略id
     * @return
     */
    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = redisService.getValue(cacheKey);
        if (strategyEntity != null){
            return strategyEntity;
        }
        Strategy strategy = strategyDao.queryStrategyEntityByStrategyId(strategyId);
        strategyEntity = StrategyEntity.builder()
                .ruleModels(strategy.getRuleModels())
                .strategyDesc(strategy.getStrategyDesc())
                .strategyId(strategy.getStrategyId())
                .build();
        redisService.setValue(cacheKey, strategyEntity);
        return strategyEntity;
    }

    /**
     * 根据策略id和抽奖规则类型查找策略
     * @param strategyId 策略id
     * @param ruleModel  抽奖规则类型
     */
    @Override
    public StrategyRuleEntity queryStrategyRuleEntity(Long strategyId, String ruleModel) {
        String cacheKey = Constants.RedisKey.STRATEGY_RULE_KEY + strategyId + "_" + ruleModel;
        StrategyRuleEntity strategyRuleEntity = redisService.getValue(cacheKey);
        if (strategyRuleEntity != null){
            return strategyRuleEntity;
        }
        StrategyRule strategyRule = strategyRuleDao.queryStrategyRuleEntity(strategyId, ruleModel);
        strategyRuleEntity = StrategyRuleEntity.builder()
                .awardId(strategyRule.getAwardId())
                .ruleDesc(strategyRule.getRuleDesc())
                .ruleValue(strategyRule.getRuleValue())
                .ruleType(strategyRule.getRuleType())
                .ruleModel(strategyRule.getRuleModel())
                .strategyId(strategyRule.getStrategyId())
                .build();
        return strategyRuleEntity;
    }

    /**
     * 查找规则值(rule_value)
     * @param strategyId 策略id
     * @param awardId    奖品id
     * @param ruleModel  规则模型
     */
    @Override
    public String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel) {
        StrategyRule strategyRule = new StrategyRule();
        strategyRule.setStrategyId(strategyId);
        strategyRule.setAwardId(awardId);
        strategyRule.setRuleModel(ruleModel);
        return strategyRuleDao.queryStrategyRuleValue(strategyRule);
    }

    /**
     * 根据策略id和奖品id查找规则模型
     * @param strategyId 策略id
     * @param awardId  奖品id
     */
    @Override
    public StrategyRuleModelVO queryStrategyRuleModels(Long strategyId, Integer awardId) {
        StrategyAward strategyAward = new StrategyAward();
        strategyAward.setStrategyId(strategyId);
        strategyAward.setAwardId(awardId);
        String ruleModels = strategyAwardDao.queryStrategyRuleModels(strategyAward);
        return StrategyRuleModelVO.builder()
                .ruleModels(ruleModels)
                .build();
    }
}
