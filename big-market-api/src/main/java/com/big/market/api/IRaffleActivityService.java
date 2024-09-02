package com.big.market.api;

import com.big.market.api.dto.ActivityDrawRequestDTO;
import com.big.market.api.dto.ActivityDrawResponseDTO;
import com.big.market.infrastructure.types.model.Response;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author 莱特0905
 * @Description: 抽奖活动服务
 * @Date: 2024/09/02 19:13:29
 */
public interface IRaffleActivityService {

    /**
     * 活动装配，数据预热缓存
     * @param activityId 活动 ID
     * @return 是否预热成功
     */
    Response<Boolean> armory(Long activityId);

    /**
     * 活动抽奖接口
     * @param requestParam 活动抽奖请求参数
     * @return 活动抽奖响应
     */
    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO requestParam);
}
