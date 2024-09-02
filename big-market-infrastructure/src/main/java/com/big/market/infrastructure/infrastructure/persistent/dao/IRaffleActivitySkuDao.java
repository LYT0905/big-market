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

    /**
     * 根据商品 sku 查找活动 Sku 实体
     * @param sku 商品 sku
     * @return 活动 Sku 实体
     */
    RaffleActivitySku queryActivitySku(Long sku);

    /**
     * 更新活动 sku 库存
     * @param sku
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 清空活动 sku 库存
     * @param sku
     */
    void clearActivitySkuStock(Long sku);

    /**
     * 根据活动 ID 查找活动 SKU 集合
     * @param activityId 活动 ID
     * @return 活动 SKU 集合
     */
    List<RaffleActivitySku> queryActivitySkuListByActivityId(Long activityId);
}
