package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户 Dao
 * @Date: 2024/08/09 21:31:34
 */
@Mapper
public interface IRaffleActivityAccountDao {
    /**
     * 查询所有抽奖活动账户
     * @return 所有抽奖活动账户
     */
    List<RaffleActivityAccount> queryAllRaffleActivityAccount();
}
