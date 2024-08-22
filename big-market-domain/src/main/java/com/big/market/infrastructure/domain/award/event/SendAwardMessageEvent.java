package com.big.market.infrastructure.domain.award.event;

import com.big.market.infrastructure.types.event.BaseEvent;
import com.big.market.infrastructure.types.utils.SnowflakeUUIDUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 发送奖品信息事件
 * @Date: 2024/08/22 14:31:27
 */
@Component
@Slf4j
public class SendAwardMessageEvent extends BaseEvent<SendAwardMessageEvent.SendAwardMessage> {

    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;

    @Override
    public EventMessage<SendAwardMessage> buildEventMessage(SendAwardMessage data) {

        try {
            return EventMessage.<SendAwardMessage>builder()
                    .id(SnowflakeUUIDUtils.generateId(11))
                    .timeStamp(new Date())
                    .data(data)
                    .build();
        } catch (IllegalAccessException e) {
            log.error("奖品消息发送时，构建ID出错");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String topic() {
        return topic;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class SendAwardMessage{
        /** 用户ID */
        private String userId;
        /** 奖品ID */
        private Integer awardId;
        /** 奖品标题(名称) */
        private String awardTitle;
    }
}
