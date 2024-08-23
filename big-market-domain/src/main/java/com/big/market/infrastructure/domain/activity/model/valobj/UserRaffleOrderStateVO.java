package com.big.market.infrastructure.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 莱特0905
 * @Description: 用户抽奖订单状态值对象
 * @Date: 2024/08/23 15:54:09
 */
@Getter
@AllArgsConstructor
public enum UserRaffleOrderStateVO {
    create("create", "创建"),
    used("used", "已使用"),
    cancel("cancel", "已作废"),
    ;

    private final String code;
    private final String desc;

}
