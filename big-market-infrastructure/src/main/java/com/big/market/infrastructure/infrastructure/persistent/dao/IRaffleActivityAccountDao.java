package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 创建账户
     * @param raffleActivityAccount 抽奖活动账户实体对象
     */
    void insert(RaffleActivityAccount raffleActivityAccount);

    /**
     * 更新账户
     * @param raffleActivityAccount 抽奖活动账户实体对象
     * @return 影响行数
     */
    int updateAccountQuota(RaffleActivityAccount raffleActivityAccount);

    /**
     * 查询用户总账户额度
     * @return 活动账户实体对象
     */
    RaffleActivityAccount queryActivityAccountByUserId(RaffleActivityAccount raffleActivityAccount);

    /**
     * 更新用户账户总额度
     * @param raffleActivityAccount
     * @return
     */
    int updateActivityAccountSubtractionQuota(RaffleActivityAccount raffleActivityAccount);

    /**
     * 更新总账户额度中的月镜像额度
     * @param raffleActivityAccountMonthImageQuota 抽奖活动账户表实体对象
     */
    void updateActivityAccountMonthSurplusImageQuota(RaffleActivityAccount raffleActivityAccountMonthImageQuota);

    /**
     * 更新总账户额度中的日镜像额度
     * @param raffleActivityAccountDayImageQuota 抽奖活动账户表实体对象
     */
    void updateActivityAccountDaySurplusImageQuota(RaffleActivityAccount raffleActivityAccountDayImageQuota);
}
