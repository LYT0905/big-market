package com.big.market.infrastructure.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 抽奖活动单实体对象
 * @Date: 2024/08/09 21:15:01
 */
@Data
public class RaffleActivityOrder {
    /** 自增ID */
    private Long id;
    /** 用户ID */
    private String userId;
    /** 活动ID */
    private Long activityId;
    /** 活动名称 */
    private String activityName;
    /**  */
    private Long strategyId;
    /** 抽奖策略ID */
    private String orderId;
    /** 订单ID */
    private Date orderTime;
    /** 下单时间 */
    private String state;
    /** 订单状态（not_used、used、expire） */
    private Date createTime;
    /** 创建时间 */
    private Date updateTime;
}
