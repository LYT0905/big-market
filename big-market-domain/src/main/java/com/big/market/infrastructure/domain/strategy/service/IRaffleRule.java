package com.big.market.infrastructure.domain.strategy.service;

import java.util.Map;

/**
 * @author 莱特0905
 * @Description: 抽奖规则接口
 * @Date: 2024/09/03 19:41:42
 */
public interface IRaffleRule {
    /**
     * 根据规则树 ID 查询配置的抽奖次数限制
     * @param treeIds 规则树 ID
     * @return Map集合
     */
    Map<String, Integer> queryAwardRuleLockCount(String[] treeIds);
}
