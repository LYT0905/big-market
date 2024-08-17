package com.big.market.infrastructure.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 莱特0905
 * @Description: 活动 sku 库存 key 值对象
 * @Date: 2024/08/17 19:53:17
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActivitySkuStockKeyVO {

    /** 商品sku */
    private Long sku;
    /** 活动ID */
    private Long activityId;
}
