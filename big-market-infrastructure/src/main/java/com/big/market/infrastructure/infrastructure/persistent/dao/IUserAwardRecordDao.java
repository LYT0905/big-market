package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.UserAwardRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 用户中奖记录表 Dao
 * @Date: 2024/08/18 14:04:35
 */
@Mapper
public interface IUserAwardRecordDao {
    List<UserAwardRecord> queryAllUserAwardRecord();

    /**
     * 插入用户中奖记录
     * @param userAwardRecord 用户中奖记录
     */
    void insert(UserAwardRecord userAwardRecord);
}
