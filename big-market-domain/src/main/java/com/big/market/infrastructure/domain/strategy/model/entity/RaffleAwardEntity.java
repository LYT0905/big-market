package com.big.market.infrastructure.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LYT0905
 * @Description: 抽奖奖品实体
 * @Date: 2024/07/16 10:56:17
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {

    /** 抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /** 奖品标题（名称） */
    private String awardTitle;
    /** 奖品配置信息 */
    private String awardConfig;
    /** 排序编号 */
    private Integer sort;
}
