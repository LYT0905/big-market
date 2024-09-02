package com.big.market.infrastructure.domain.activity.service;

import com.big.market.infrastructure.domain.activity.model.entity.PartakeRaffleActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.UserRaffleOrderEntity;

/**
 * @author 莱特0905
 * @Description: 抽奖活动参与服务
 * @Date: 2024/08/23 15:50:30
 */
public interface IRaffleActivityPartakeService {

    /**
     * 创建抽奖单；用户参与抽奖活动，扣减活动账户库存，产生抽奖单。如存在未被使用的抽奖单则直接返回已存在的抽奖单。
     *
     * @param userId 用户 ID
     * @param activityId 活动 ID
     * @return 用户抽奖订单实体对象
     */
    UserRaffleOrderEntity createOrder(String userId, Long activityId);

    /**
     * 创建抽奖单；用户参与抽奖活动，扣减活动账户库存，产生抽奖单。如存在未被使用的抽奖单则直接返回已存在的抽奖单。
     *
     * @param partakeRaffleActivityEntity 参与抽奖活动实体对象
     * @return 用户抽奖订单实体对象
     */
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);
}
