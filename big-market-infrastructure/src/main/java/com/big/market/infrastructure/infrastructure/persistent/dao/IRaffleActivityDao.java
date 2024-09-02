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

    /**
     * 根据活动 ID查询活动信息
     * @param activityId 活动 ID
     * @return 活动信息
     */
    RaffleActivity queryRaffleActivityByActivityId(Long activityId);

    /**
     * 根据活动 ID 查找策略 ID
     * @param activityId 活动 ID
     * @return 策略 ID
     */
    Long queryStrategyIdByActivityId(Long activityId);

    Long queryActivityIdByStrategyId(Long strategyId);
}
