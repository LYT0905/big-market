package com.big.market.infrastructure.domain.task.mdoel.entity;

import lombok.Data;

/**
 * @author 莱特0905
 * @Description: 任务实体
 * @Date: 2024/08/22 15:40:26
 */
@Data
public class TaskEntity {
    /** 消息主题 */
    private String topic;
    /** 用户ID */
    private String userId;
    /** 消息主体 */
    private String message;
    /** 消息编号 */
    private String messageId;
}
