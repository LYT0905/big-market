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

    /**
     * 根据活动次数id 查询次数信息（用户在活动上可参与的次数）
     * @param activityCountId 活动次数id
     * @return 次数信息（用户在活动上可参与的次数）
     */
    RaffleActivityCount queryRaffleActivityCountByActivityCountId(Long activityCountId);
}
