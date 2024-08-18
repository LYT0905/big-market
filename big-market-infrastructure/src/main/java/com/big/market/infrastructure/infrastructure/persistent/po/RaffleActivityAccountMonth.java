package com.big.market.infrastructure.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户表-月次数实体对象
 * @Date: 2024/08/18 13:47:11
 */
@Data
public class RaffleActivityAccountMonth {
    /** 自增ID */
    private Integer id;
    /** 用户ID */
    private String userId;
    /** 活动ID */
    private Long activityId;
    /** 月（yyyy-mm） */
    private String month;
    /** 月次数 */
    private Integer monthCount;
    /** 月次数-剩余 */
    private Integer monthCountSurplus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
