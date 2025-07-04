package com.big.market.infrastructure.domain.activity.service;

import com.big.market.infrastructure.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 活动sku库存处理接口
 * @Date: 2024/08/17 20:28:19
 */
public interface IRaffleActivitySkuStockService {

    /**
     * 获取活动sku库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */
    ActivitySkuStockKeyVO takeQueueValue(Long sku) throws InterruptedException;

    /**
     * 清空队列
     * @param sku 活动商品
     */
    void clearQueueValue(Long sku);

    /**
     * 延迟队列 + 任务趋势更新活动sku库存
     *
     * @param sku 活动商品
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 缓存库存以消耗完毕，清空数据库库存
     *
     * @param sku 活动商品
     */
    void clearActivitySkuStock(Long sku);

    /**
     * 查询活动商品列表
     * @return 活动商品列表
     */
    List<Long> querySkuList();
}
