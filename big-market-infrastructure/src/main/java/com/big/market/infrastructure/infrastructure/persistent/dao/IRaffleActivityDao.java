package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动Dao
 * @Date: 2024/08/09 20:40:14
 */
@Mapper
public interface IRaffleActivityDao {

    /**
     * 查询所有抽奖活动
     * @return 所有抽奖活动
     */
    List<RaffleActivity> queryAllRaffleActivity();

}
