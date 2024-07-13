package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LYT0905
 * @Description: 策略规则Dao
 * @Date: 2024/07/13 17:18:06
 */
@Mapper
public interface IStrategyRuleDao {
    List<StrategyRule> queryStrategyList();
}
