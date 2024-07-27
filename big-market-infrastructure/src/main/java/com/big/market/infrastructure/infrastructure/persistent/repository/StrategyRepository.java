package com.big.market.infrastructure.infrastructure.persistent.repository;

import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyRuleEntity;
import com.big.market.infrastructure.domain.strategy.model.valobj.*;
import com.big.market.infrastructure.domain.strategy.repository.IStrategyRepository;
import com.big.market.infrastructure.infrastructure.persistent.dao.*;
import com.big.market.infrastructure.infrastructure.persistent.po.*;
import com.big.market.infrastructure.infrastructure.persistent.redis.IRedisService;
import com.big.market.infrastructure.types.common.Constants;
import com.big.market.infrastructure.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RMap;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author LYT0905
 * @Description: 策略服务仓储实现
 * @Date: 2024/07/14 10:50:33
 */
@Slf4j
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
    @Resource
    private IRuleTreeDao ruleTreeDao;
    @Resource
    private IRuleTreeNodeDao ruleTreeNodeDao;
    @Resource
    private IRuleTreeNodeLineDao ruleTreeNodeLineDao;

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
        redisService.setValue(cacheKey, strategyAwardEntities, 86400000L);
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
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key,  shuffleStrategyAwardSearchRateTablesSize, 86400000L);
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
        redisService.setValue(cacheKey, strategyEntity, 86400000L);
        return strategyEntity;
    }

    /**
     * 根据策略id和抽奖规则类型查找策略
     * @param strategyId 策略id
     * @param ruleModel  抽奖规则类型
     */
    @Override
    public StrategyRuleEntity queryStrategyRuleEntity(Long strategyId, String ruleModel) {
        String cacheKey = Constants.RedisKey.STRATEGY_RULE_KEY + strategyId + Constants.UNDERLINE + ruleModel;
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
        redisService.setValue(cacheKey, strategyRuleEntity, 86400000L);
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
     * 查找规则值(rule_value)
     * @param strategyId 策略id
     * @param ruleModel  规则模型
     */
    @Override
    public String queryStrategyRuleValue(Long strategyId, String ruleModel){
        return queryStrategyRuleValue(strategyId, null, ruleModel);
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

    /**
     * 根据树id查找规则树
     * @param treeId 树id
     * @return 规则树
     */
    @Override
    public RuleTreeVO queryRuleTreeVOByTreeId(String treeId) {
        String cacheKey = Constants.RedisKey.RULE_TREE_VO_KEY + treeId;
        RuleTreeVO ruleTreeVO = redisService.getValue(cacheKey);
        if (ruleTreeVO != null){
            return ruleTreeVO;
        }

        RuleTree ruleTree = ruleTreeDao.queryRuleTreeByTreeId(treeId);
        List<RuleTreeNode> ruleTreeNodes = ruleTreeNodeDao.queryRuleTreeNodeListByTreeId(treeId);
        List<RuleTreeNodeLine> ruleTreeNodeLines = ruleTreeNodeLineDao.queryRuleTreeNodeLineListByTreeId(treeId);

        if (ruleTree == null || ruleTreeNodes.size() == 0 || ruleTreeNodeLines.size() == 0){
            return null;
        }


        // tree node line 转换Map结构
        Map<String, List<RuleTreeNodeLineVO>> treeNodeLineVOMap = new HashMap<>();
        for (RuleTreeNodeLine ruleTreeNodeLine : ruleTreeNodeLines) {
            RuleTreeNodeLineVO ruleTreeNodeLineVO = RuleTreeNodeLineVO.builder()
                    .treeId(treeId)
                    .ruleNodeFrom(ruleTreeNodeLine.getRuleNodeFrom())
                    .ruleNodeTo(ruleTreeNodeLine.getRuleNodeTo())
                    .ruleLimitType(RuleLimitTypeVO.valueOf(ruleTreeNodeLine.getRuleLimitType()))
                    .ruleLimitValue(RuleLogicCheckTypeVO.valueOf(ruleTreeNodeLine.getRuleLimitValue()))
                    .build();
            List<RuleTreeNodeLineVO> ruleTreeNodeLineVOList = treeNodeLineVOMap.computeIfAbsent(ruleTreeNodeLine.getRuleNodeFrom(), k -> new ArrayList<>());
            ruleTreeNodeLineVOList.add(ruleTreeNodeLineVO);
        }

        // tree node 转换为Map结构
        Map<String, RuleTreeNodeVO> treeNodeMap = new HashMap<>();
        for (RuleTreeNode ruleTreeNode : ruleTreeNodes) {
            RuleTreeNodeVO ruleTreeNodeVO = RuleTreeNodeVO.builder()
                    .treeId(treeId)
                    .ruleKey(ruleTreeNode.getRuleKey())
                    .ruleValue(ruleTreeNode.getRuleValue())
                    .ruleDesc(ruleTreeNode.getRuleDesc())
                    .treeNodeLineVOList(treeNodeLineVOMap.get(ruleTreeNode.getRuleKey()))
                    .build();
            treeNodeMap.put(ruleTreeNode.getRuleKey(), ruleTreeNodeVO);
        }


        // 构建 Rule Tree
        ruleTreeVO = RuleTreeVO.builder()
                .treeDesc(ruleTree.getTreeDesc())
                .treeId(treeId)
                .treeName(ruleTree.getTreeName())
                .treeRootRuleNode(ruleTree.getTreeRootRuleKey())
                .treeNodeMap(treeNodeMap)
                .build();
        redisService.setValue(cacheKey, ruleTreeVO, 86400000L);
        return ruleTreeVO;
    }

    /**
     * 对奖品库存信息进行装配
     * @param cacheKey 缓存key
     * @param awardCount  奖品库存
     */
    @Override
    public void cacheStrategyAwardCount(String cacheKey, Integer awardCount) {
        if (redisService.getValue(cacheKey) != null){
            return;
        }
        redisService.setAtomicLong(cacheKey, awardCount);
    }

    /**
     * 扣减库存
     * @param cacheKey 缓存key
     * @return 库存是否扣减成功
     */
    @Override
    public Boolean subtractionAwardCount(String cacheKey) {
        // 对库存进行扣减，返回的是剩余的库存
        long surplus = redisService.decr(cacheKey);
        // 没有库存
        if (surplus < 0){
            redisService.setValue(cacheKey, 0);
            return false;
        }
        //  对此时的库存进行加锁，防止后续对这个库存进行操作
        String lockKey = cacheKey + Constants.UNDERLINE + surplus;
        Boolean lock = redisService.setNx(lockKey);
        if (!lock){
            log.error("库存{},加锁失败", surplus);
        }
        return lock;
    }

    /**
     * 奖品库存信息消费
     * @param strategyAwardStockKeyVO  策略奖品库存key标识对象
     */
    @Override
    public void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_QUEUE_KEY;
        // 获取阻塞队列
        RBlockingQueue<StrategyAwardStockKeyVO> blockingQueue = redisService.getBlockingQueue(cacheKey);
        // 获取延迟队列
        RDelayedQueue<StrategyAwardStockKeyVO> delayedQueue = redisService.getDelayedQueue(blockingQueue);
        delayedQueue.offer(strategyAwardStockKeyVO, 3, TimeUnit.SECONDS);
    }

    /**
     * 获取奖品库存消耗队列
     *
     * @return 奖品库存Key信息
     */
    @Override
    public StrategyAwardStockKeyVO takeQueueValue() {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_QUEUE_KEY;
        // 获取阻塞队列
        RBlockingQueue<StrategyAwardStockKeyVO> blockingQueue = redisService.getBlockingQueue(cacheKey);
        return blockingQueue.poll();
    }

    /**
     * 更新奖品库存消耗记录
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    @Override
    public void updateStrategyAwardStock(Long strategyId, Integer awardId) {
        StrategyAward strategyAward = new StrategyAward();
        strategyAward.setStrategyId(strategyId);
        strategyAward.setAwardId(awardId);
        strategyAwardDao.updateStrategyAwardStock(strategyAward);
    }
}
