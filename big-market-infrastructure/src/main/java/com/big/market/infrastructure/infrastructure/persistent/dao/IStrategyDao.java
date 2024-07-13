package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略Dao
 * @Date: 2024/07/13 17:17:27
 */
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();
}
