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
}
