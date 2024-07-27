package com.big.market.infrastructure.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYT0905
 * @Description: 策略奖品库存key标识对象
 * @Date: 2024/07/25 22:57:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    /** 策略id */
    private Long strategyId;
    /** 奖品id */
    private Integer awardId;
}
