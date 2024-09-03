package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccountDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户表-日次数 Dao
 * @Date: 2024/08/18 14:17:23
 */
@Mapper
public interface IRaffleActivityAccountDayDao {
    List<RaffleActivityAccountDay> queryAllRaffleActivityAccountDay();

    /**
     * 查询账户日额度
     * @return 活动账户（日）实体对象
     */
    RaffleActivityAccountDay queryActivityAccountDayByUserId(RaffleActivityAccountDay raffleActivityAccountDay);

    /**
     * 更新账户日额度
     * @param raffleActivityAccountDay 抽奖活动账户表-日次数实体对象
     * @return 影响行数
     */
    int updateActivityAccountDaySubtractionQuota(RaffleActivityAccountDay raffleActivityAccountDay);

    /**
     * 新增账户日额度
     * @param raffleActivityAccountDay 抽奖活动账户表-日次数实体对象
     */
    void insertActivityAccountDay(RaffleActivityAccountDay raffleActivityAccountDay);

    /**
     * 查询用户当天抽奖次数
     * @param raffleActivityAccountDay 请求参数
     * @return 当天抽奖次数
     */
    Integer queryRaffleActivityAccountDayPartakeCount(RaffleActivityAccountDay raffleActivityAccountDay);
}
