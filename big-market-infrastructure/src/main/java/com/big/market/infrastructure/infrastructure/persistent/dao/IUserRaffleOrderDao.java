package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.UserRaffleOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 用户抽奖订单表Dao
 * @Date: 2024/08/18 14:11:55
 */
@Mapper
public interface IUserRaffleOrderDao {
    List<UserRaffleOrder> queryAllUserRaffleOrder();

    /**
     * 查询未使用的活动订单
     * @return 用户抽奖订单实体对象
     */
    UserRaffleOrder queryNoUsedRaffleOrder(UserRaffleOrder userRaffleOrder);

    /**
     * 插入用户抽奖订单记录
     * @param userRaffleOrder 用户抽奖订单表实体对象
     */
    void insert(UserRaffleOrder userRaffleOrder);

    /**
     * 修改用户订单状态为已使用
     * @param userRaffleOrder 用户抽奖订单表实体对象
     * @return 影响行数
     */
    int updateUserRaffleOrderStatusUsed(UserRaffleOrder userRaffleOrder);
}
