package com.big.market.infrastructure.domain.activity.event;

import com.big.market.infrastructure.types.event.BaseEvent;
import com.big.market.infrastructure.types.utils.SnowflakeUUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 活动 sku 库存清空信息
 * @Date: 2024/08/17 20:12:47
 */
@Component
@Slf4j
public class ActivitySkuStockZeroMessageEvent extends BaseEvent<Long> {

    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;

    @Override
    public EventMessage<Long> buildEventMessage(Long sku)  {
        try {
            return EventMessage.<Long>builder()
                    .id(SnowflakeUUIDUtils.generateId(11))
                    .timeStamp(new Date())
                    .data(sku)
                    .build();
        }catch (Throwable ex){
            log.error("构建事件消息时出错活动 sku:{}", sku, ex);
            try {
                throw ex;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String topic() {
        return topic;
    }
}
