package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LYT0905
 * @Description: 规则树节点Dao
 * @Date: 2024/07/23 21:48:36
 */
@Mapper
public interface IRuleTreeNodeDao {
    /**
     * 根据规则树 ID 查询规则树节点
     * @param treeId 规则树 ID
     * @return 规则树节点
     */
    List<RuleTreeNode> queryRuleTreeNodeListByTreeId(@Param("treeId") String treeId);
    /**
     * 根据规则树 ID 查询次数限制
     * @param treeIds 规则树 ID
     */
    List<RuleTreeNode> queryRuleLocks(String[] treeIds);
}
