package com.big.market.infrastructure.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 莱特0905
 * @Description: 活动商品充值实体对象
 * @Date: 2024/08/14 20:54:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuRechargeEntity {
    /** 用户ID */
    private String userId;
    /** 商品SKU - activity + activity count */
    private Long sku;
    /** 业务防重ID 外部谁充值谁透传，这样来保证幂等（多次调用也能确保结果唯一，不会多次充值） */
    private String outBusinessNo;
}
