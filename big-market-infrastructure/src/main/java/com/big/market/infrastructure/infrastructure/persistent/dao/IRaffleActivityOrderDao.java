package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动单 Dao
 * @Date: 2024/08/09 21:17:55
 */
@Mapper
public interface IRaffleActivityOrderDao {
    /**
     * 查找所有的抽奖活动单
     * @return 所有的抽奖活动单
     */
    List<RaffleActivityOrder> queryAllRaffleActivityOrder();

}
