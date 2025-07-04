package com.big.market.infrastructure.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 抽奖活动次数配置表实体对象
 * @Date: 2024/08/09 20:59:18
 */
@Data
public class RaffleActivityCount {

    /** 自增ID */
    private Long id;
    /** 活动次数编号 */
    private Long activityCountId;
    /** 总次数 */
    private Integer totalCount;
    /** 日次数 */
    private Integer dayCount;
    /** 月次数 */
    private Integer monthCount;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
