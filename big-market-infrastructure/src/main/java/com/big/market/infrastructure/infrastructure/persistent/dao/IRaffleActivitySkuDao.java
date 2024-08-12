package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivitySku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 活动 SKU Dao
 * @Date: 2024/08/12 22:37:50
 */
@Mapper
public interface IRaffleActivitySkuDao {

    List<RaffleActivitySku> queryAllRaffleActivitySku();

}
