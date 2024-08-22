package com.big.market.infrastructure.domain.award.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 莱特0905
 * @Description: 奖品活动状态值对象
 * @Date: 2024/08/22 14:28:00
 */
@Getter
@AllArgsConstructor
public enum AwardStateVO {
    create("create", "创建"),
    completed("completed", "发奖完成"),
    fail("fail", "发奖失败"),
            ;

    private final String code;
    private final String desc;
}
