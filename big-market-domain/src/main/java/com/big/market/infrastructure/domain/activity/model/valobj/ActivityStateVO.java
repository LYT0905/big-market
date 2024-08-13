package com.big.market.infrastructure.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 莱特0905
 * @Description: 活动状态值对象
 * @Date: 2024/08/13 19:31:48
 */
@Getter
@AllArgsConstructor
public enum ActivityStateVO {
    create("create", "创建");

    private final String code;
    private final String desc;

}
