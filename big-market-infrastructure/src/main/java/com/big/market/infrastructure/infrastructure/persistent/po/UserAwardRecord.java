package com.big.market.infrastructure.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 用户中奖记录表实体对象
 * @Date: 2024/08/18 13:50:32
 */
@Data
public class UserAwardRecord {
    /** 自增ID */
    private Integer id;
    /** 用户ID */
    private String userId;
    /** 活动ID */
    private Long activityId;
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖订单ID【作为幂等使用】 */
    private String orderId;
    /** 奖品ID */
    private Integer awardId;
    /** 奖品标题（名称） */
    private String awardTitle;
    /** 中奖时间 */
    private Date awardTime;
    /** 奖品状态；create-创建、completed-发奖完成 */
    private String awardState;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
