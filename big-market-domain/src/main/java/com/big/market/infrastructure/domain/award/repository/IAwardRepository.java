package com.big.market.infrastructure.domain.award.repository;

import com.big.market.infrastructure.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @author 莱特0905
 * @Description: 奖品仓储服务
 * @Date: 2024/08/22 14:48:31
 */
public interface IAwardRepository {
    /**
     * 保存用户获奖记录
     */
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);
}
