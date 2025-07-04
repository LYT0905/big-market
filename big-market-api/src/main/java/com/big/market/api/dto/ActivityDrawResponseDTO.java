package com.big.market.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 莱特0905
 * @Description: 活动抽奖响应实体
 * @Date: 2024/09/02 19:15:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDrawResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 奖品标题
    private String awardTitle;
    // 排序编号【策略奖品配置的奖品顺序编号】
    private Integer awardIndex;

}
