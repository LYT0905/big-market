package com.big.market.infrastructure.trigger.listener;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.big.market.infrastructure.domain.activity.service.IRaffleActivitySkuStockService;
import com.big.market.infrastructure.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 活动sku库存耗尽
 * @Date: 2024/08/17 20:45:32
 */
@Slf4j
@Component
public class ActivitySkuStockZeroCustomer {

    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;

    @Resource
    private IRaffleActivitySkuStockService skuStock;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.activity_sku_stock_zero}"))
    public void listener(String message){
        try {
            log.info("监听活动sku库存消耗为0消息 topic: {} message: {}", topic, message);
            // 格式转换
            BaseEvent.EventMessage<Long> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<Long>>() {
            }.getType());

            Long sku =  eventMessage.getData();
            // 更新库存,设置库存为 0
            skuStock.clearActivitySkuStock(sku);
            // 清空队列 「此时就不需要延迟更新数据库记录了」
            skuStock.clearQueueValue(sku);

        }catch (Throwable ex){
            log.error("监听活动sku库存消耗为0消息，消费失败 topic: {} message: {}", topic, message);
            throw ex;
        }
    }

}
