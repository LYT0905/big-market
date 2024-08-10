package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccountFlow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户流水 Dao
 * @Date: 2024/08/09 21:43:09
 */
@Mapper
public interface IRaffleActivityAccountFlowDao {

    /**
     * 查询所有抽奖活动账户流水
     * @return 所有抽奖活动账户流水
     */
    List<RaffleActivityAccountFlow> queryAllRaffleActivityAccountFlow();

}
