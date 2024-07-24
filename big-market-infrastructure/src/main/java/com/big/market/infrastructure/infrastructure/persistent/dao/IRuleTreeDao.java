package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LYT0905
 * @Description: 规则树Dao
 * @Date: 2024/07/23 21:39:20
 */

@Mapper
public interface IRuleTreeDao {
    RuleTree queryRuleTreeByTreeId(@Param("treeId") String treeId);
}
