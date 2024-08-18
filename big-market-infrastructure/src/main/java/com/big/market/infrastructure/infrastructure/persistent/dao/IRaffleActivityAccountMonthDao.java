package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccountMonth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户表-月次数 Dao
 * @Date: 2024/08/18 14:24:08
 */
@Mapper
public interface IRaffleActivityAccountMonthDao {
    List<RaffleActivityAccountMonth> queryAllRaffleActivityAccountMonth();
}
