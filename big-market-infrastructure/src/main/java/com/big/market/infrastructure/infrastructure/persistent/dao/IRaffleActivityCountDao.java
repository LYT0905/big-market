package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动次数配置Dao
 * @Date: 2024/08/09 21:01:37
 */
@Mapper
public interface IRaffleActivityCountDao {

    /**
     * 查询所有抽奖活动次数配置
     * @return 所有抽奖活动次数配置
     */
    List<RaffleActivityCount> queryAllRaffleActivityCount();
}
