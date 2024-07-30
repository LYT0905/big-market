package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.StrategyAward;
import com.big.market.infrastructure.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略奖品Dao
 * @Date: 2024/07/13 17:17:51
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();

    List<StrategyAward> queryStrategyAwardListByStrategyId(@Param("strategyId") Long strategyId);

    /**
     * 根据策略id和奖品id查找规则模型
     */
    String queryStrategyRuleModels(StrategyAward strategyAward);

    /**
     * 更新奖品库存消耗记录
     *
     */
    void updateStrategyAwardStock(StrategyAward strategyAward);

    /**
     * 根据策略id和奖品id查找奖品信息
     * @param strategyId 策略id
     * @param awardId   奖品id
     */
    StrategyAward queryStrategyAward(@Param("strategyId") Long strategyId, @Param("awardId") Integer awardId);

    /**
     * 根据策略id和奖品id查找库存
     * @param strategyAward
     * @return
     */
    Integer queryStrategyAwardSurplus(StrategyAward strategyAward);
}
