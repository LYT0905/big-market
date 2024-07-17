package com.big.market.infrastructure.domain.strategy.service;

import com.big.market.infrastructure.domain.strategy.model.entity.RaffleAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @author LYT0905
 * @Description: 抽奖接口
 * @Date: 2024/07/16 10:48:54
 */
public interface IRaffleStrategy {

    /**
     * 执行抽奖；用抽奖因子入参，执行抽奖计算，返回奖品信息
     *
     * @param raffleFactorEntity 抽奖因子实体对象，根据入参信息计算抽奖结果
     * @return 抽奖的奖品
     */
    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
