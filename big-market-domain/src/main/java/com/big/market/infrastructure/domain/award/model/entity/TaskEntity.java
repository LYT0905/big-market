package com.big.market.infrastructure.domain.award.model.entity;

import com.big.market.infrastructure.domain.award.event.SendAwardMessageEvent;
import com.big.market.infrastructure.domain.award.model.valobj.TaskStateVO;
import com.big.market.infrastructure.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 莱特0905
 * @Description: 任务实体
 * @Date: 2024/08/22 14:42:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    /** 消息主题 */
    private String topic;
    /** 用户ID */
    private String userId;
    /** 消息主体 */
    private BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> message;
    /** 消息编号 */
    private String messageId;
    /** 任务状态；create-创建、completed-完成、fail-失败 */
    private TaskStateVO state;
}
