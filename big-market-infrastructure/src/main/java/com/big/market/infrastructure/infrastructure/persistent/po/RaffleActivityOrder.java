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
    /** 商品sku */
    private Long sku;
    /** 活动ID */
    private Long activityId;
    /** 活动名称 */
    private String activityName;
    /** 抽奖策略ID  */
    private Long strategyId;
    /** 订单ID */
    private String orderId;
    /** 订单ID */
    private Date orderTime;
    /** 总次数 */
    private Integer totalCount;
    /** 日次数 */
    private Integer dayCount;
    /** 月次数 */
    private Integer monthCount;
    /** 下单时间 */
    private String state;
    /** 业务防重ID */
    private String outBusinessNo;
    /** 订单状态（not_used、used、expire） */
    private Date createTime;
    /** 创建时间 */
    private Date updateTime;
}
