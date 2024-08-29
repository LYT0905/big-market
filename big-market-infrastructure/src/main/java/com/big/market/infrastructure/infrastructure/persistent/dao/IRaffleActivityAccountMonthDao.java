package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccountDay;
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

    /**
     * 查询账户月额度
     * @return 活动账户（月）实体对象
     */
    RaffleActivityAccountMonth queryActivityAccountMonthByUserId(RaffleActivityAccountMonth raffleActivityAccountMonth);

    /**
     * 更新月账户额度
     * @param activityAccountMonth 抽奖活动账户表-月次数实体对象
     * @return 影响行数
     */
    int updateActivityAccountMonthSubtractionQuota(RaffleActivityAccountMonth activityAccountMonth);

    /**
     * 插入月额度记录
     * @param activityAccountMonth 抽奖活动账户表-月次数实体对象
     */
    void insertActivityAccountMonth(RaffleActivityAccountMonth activityAccountMonth);
}
