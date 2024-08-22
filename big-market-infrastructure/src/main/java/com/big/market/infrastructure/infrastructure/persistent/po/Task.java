package com.big.market.infrastructure.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 任务表，发送MQ实体对象
 * @Date: 2024/08/18 13:56:43
 */
@Data
public class Task {
    /** 自增ID */
    private Integer id;
    /** 消息主题 */
    private String topic;
    /** 用户ID */
    private String userId;
    /** 消息主体 */
    private String message;
    /** 消息编号 */
    private String messageId;
    /** 任务状态；create-创建、completed-完成、fail-失败 */
    private String state;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
