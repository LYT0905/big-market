package com.big.market.infrastructure.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 活动 SKU 表实体对象
 * @Date: 2024/08/12 22:28:57
 */
@Data
public class RaffleActivitySku {
    /** 自增ID */
    private Integer id;
    /** 商品sku - 把每一个组合当做一个商品 */
    private Long sku;
    /** 活动ID */
    private Long activityId;
    /** 活动个人参与次数ID */
    private Long activityCountId;
    /** 商品库存 */
    private Integer stockCount;
    /** 剩余库存 */
    private Integer stockCountSurplus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
