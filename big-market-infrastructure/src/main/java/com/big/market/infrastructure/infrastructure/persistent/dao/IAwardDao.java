package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LYT0905
 * @Description: 奖品Dao
 * @Date: 2024/07/13 17:16:46
 */
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
