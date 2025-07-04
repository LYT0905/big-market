package com.big.market.infrastructure.trigger.listener;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.big.market.infrastructure.types.event.BaseEvent;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 莱特0905
 * @Description: 用户奖品记录消息消费者
 * @Date: 2024/08/22 16:10:04
 */
@Slf4j
@Component
public class SendAwardCustomer {
    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_award}"))
    public void listener(String message){
        try {
            log.info("监听用户奖品发送消息 topic:{} message:{}", topic, message);
        }catch (Throwable ex){
            log.info("监听用户奖品发送消息 消费失败 topic:{} message:{}", topic, message);
            throw ex;
        }
    }
}
