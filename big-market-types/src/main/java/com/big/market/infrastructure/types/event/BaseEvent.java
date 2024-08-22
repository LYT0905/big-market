package com.big.market.infrastructure.types.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 基础事件
 * @Date: 2024/08/17 20:01:25
 */
@Data
public abstract class BaseEvent<T> {


    public abstract EventMessage<T> buildEventMessage(T data);
    public abstract String topic();

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class EventMessage<T>{
        private String id;
        private Date timeStamp;
        private T data;
    }

}
