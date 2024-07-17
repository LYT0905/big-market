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
}
