package com.big.market.infrastructure.infrastructure.persistent.event;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 消息发送
 * @Date: 2024/08/17 20:04:59
 */
@Slf4j
@Component
public class EventPublisher {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void publish(String topic, BaseEvent.EventMessage<?> eventMessage){
        try {
            String messageJson = JSON.toJSONString(eventMessage);
            rabbitTemplate.convertAndSend(topic, messageJson);
            log.info("发送 MQ 消息 topic:{}, message:{}", topic, messageJson);
        }catch (Throwable ex){
            log.error("发送 MQ 消息失败 topic:{}, message:{}", topic, JSON.toJSONString(eventMessage), ex);
            throw ex;
        }
    }

    public void publish(String topic, String messageJson){
        try {
            rabbitTemplate.convertAndSend(topic, messageJson);
            log.info("发送 MQ 消息 topic:{}, message:{}", topic, messageJson);
        }catch (Throwable ex){
            log.error("发送 MQ 消息失败 topic:{}, message:{}", topic, messageJson, ex);
            throw ex;
        }
    }
}
