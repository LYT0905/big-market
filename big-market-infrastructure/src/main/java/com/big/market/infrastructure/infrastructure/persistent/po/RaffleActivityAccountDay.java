package com.big.market.infrastructure.infrastructure.persistent.po;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户表-日次数实体对象
 * @Date: 2024/08/18 13:44:30
 */
@Data
public class RaffleActivityAccountDay {

    private SimpleDateFormat dateSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /** 自增ID */
    private Integer id;
    /** 用户ID */
    private String userId;
    /** 活动ID */
    private Long activityId;
    /** 日期（yyyy-mm-dd） */
    private String day;
    /** 日次数 */
    private Integer dayCount;
    /** 日次数-剩余 */
    private Integer dayCountSurplus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 当前日子的时期 */
    public String currentDay() {
        return dateSimpleDateFormat.format(new Date());
    }
}
