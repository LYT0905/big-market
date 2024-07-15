package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略Dao
 * @Date: 2024/07/13 17:17:27
 */
@Mapper
public interface IStrategyDao {
    /**
     * 查询所有的策略
     * @return
     */
    List<Strategy> queryStrategyList();

    /**
     * 根据策略id查找策略
     * @param strategyId 策略id
     * @return
     */
    Strategy queryStrategyEntityByStrategyId(@Param("strategyId") Long strategyId);
}
