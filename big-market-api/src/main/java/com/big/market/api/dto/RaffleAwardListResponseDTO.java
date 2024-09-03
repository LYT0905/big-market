package com.big.market.api.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 莱特0905
 * @Description: 抽奖奖品列表，应答对象
 * @Date: 2024/07/27 20:54:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardListResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 奖品标题
    private String awardTitle;
    // 奖品副标题【抽奖1次后解锁】
    private String awardSubtitle;
    // 排序编号
    private Integer sort;
    // 奖品次数规则 - 抽奖 N 次后解锁，未配置则未空
    private Integer awardRuleLockCount;
    // 奖品是否解锁 true 已解锁 false 未解锁
    private Boolean isAwardUnlock;
    // 等待解锁次数 = 规定的需要抽奖的次数 - 用户已经抽奖的次数
    private Integer waitUnlockCount;
}
