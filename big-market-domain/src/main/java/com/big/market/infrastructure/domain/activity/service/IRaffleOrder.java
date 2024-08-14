package com.big.market.infrastructure.domain.activity.service;

import com.big.market.infrastructure.domain.activity.model.entity.SkuRechargeEntity;

/**
 * @author 莱特0905
 * @Description: 抽奖活动订单接口
 * @Date: 2024/08/13 19:32:23
 */
public interface IRaffleOrder {

    /**
     * 创建活动商品充值订单
     * <p>
     * 1. 在【打卡、签到、分享、对话、积分兑换】等行为动作下，创建出活动订单，给用户的活动账户【日、月】充值可用的抽奖次数。
     * 2. 对于用户可获得的抽奖次数，比如首次进来就有一次，则是依赖于运营配置的动作，在前端页面上。用户点击后，可以获得一次抽奖次数。
     * @param skuRechargeEntity 活动商品充值实体对象
     * @return 订单单号
     */
    String createSkuRechargeOrder(SkuRechargeEntity skuRechargeEntity);
}
