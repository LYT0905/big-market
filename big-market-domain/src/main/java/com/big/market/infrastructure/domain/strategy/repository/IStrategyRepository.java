package com.big.market.infrastructure.domain.strategy.repository;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyRuleEntity;
import com.big.market.infrastructure.domain.strategy.model.valobj.RuleTreeVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import com.big.market.infrastructure.domain.strategy.model.valobj.StrategyRuleModelVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略服务仓储接口
 * @Date: 2024/07/14 10:49:56
 */
public interface IStrategyRepository {
    /**
     * 查询某策略id下所有策略奖品配置信息
     * @param strategyId 策略id
     */
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    /**
     * 存储到redis
     * @param key 策略id
     * @param shuffleStrategyAwardSearchRateTablesSize  策略概率范围
     * @param shuffleStrategyAwardSearchRateTables 存储了乱序概率的table
     */
    void storeStrategyAwardSearchRateTables(String key, int shuffleStrategyAwardSearchRateTablesSize, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables);

    /**
     * 获取概率范围
     * @param strategyId 策略id
     */
    int getRateRange(Long strategyId);

    /**
     * 获取概率范围
     * @param key 策略id
     */
    int getRateRange(String key);

    /**
     * 获取策略奖品
     * @param key 策略id
     * @param rateKey 概率范围
     */
    Integer getStrategyAwardAssemble(String key, int rateKey);

    /**
     * 根据策略id查找策略实体
     * @param strategyId 策略id
     */
    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    /**
     * 根据策略id和抽奖规则类型查找策略
     * @param strategyId 策略id
     * @param ruleModel  抽奖规则类型
     */
    StrategyRuleEntity queryStrategyRuleEntity(Long strategyId, String ruleModel);

    /**
     * 查找规则值(rule_value)
     * @param strategyId 策略id
     * @param awardId    奖品id
     * @param ruleModel  规则模型
     */
    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    /**
     * 查找规则值(rule_value)
     * @param strategyId 策略id
     * @param ruleModel  规则模型
     */
    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    /**
     * 根据策略id和奖品id查找规则模型
     * @param strategyId 策略id
     * @param awardId  奖品id
     */
    StrategyRuleModelVO queryStrategyRuleModels(Long strategyId, Integer awardId);

    /**
     * 根据树id查找规则树
     * @param treeId 树id
     * @return 规则树
     */
    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    /**
     * 对奖品库存信息进行装配
     * @param cacheKey 缓存key
     * @param awardCount  奖品库存
     */
    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    /**
     * 扣减库存
     * @param cacheKey 缓存key
     * @return 库存是否扣减成功
     */
    Boolean subtractionAwardCount(String cacheKey);

    /**
     * 奖品库存信息消费
     * @param strategyAwardStockKeyVO  策略奖品库存key标识对象
     */
    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    /**
     * 获取奖品库存消耗队列
     *
     * @return 奖品库存Key信息
     */
    StrategyAwardStockKeyVO takeQueueValue();

    /**
     * 更新奖品库存消耗记录
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);

    /**
     * 根据奖品id和策略id查找抽奖奖品实体
     *
     * @param strategyId 策略id
     * @param awardId    奖品id
     */
    StrategyAwardEntity queryStrategyAwardEntity(Long strategyId, Integer awardId);

    /**
     * 根据活动 ID 查找策略 ID
     * @param activityId 活动 ID
     * @return 策略 ID
     */
    Long queryStrategyIdByActivityId(Long activityId);

    /**
     * 根据用户 ID 和 策略 ID 查找当天用户抽奖次数
     * @param userId 用户 ID
     * @param strategyId 策略 ID
     * @return 抽奖次数
     */
    Integer queryTodayUserRaffleCount(String userId, Long strategyId);
}
