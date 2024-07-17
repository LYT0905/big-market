package com.big.market.infrastructure.domain.strategy.service.annotation;

import com.big.market.infrastructure.domain.strategy.service.rule.factory.DefaultLogicFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LYT0905
 * @Description: 策略自定义枚举
 * @Date: 2024/07/16 11:18:51
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LogicStrategy {

    DefaultLogicFactory.LogicModel logicModel();
}
