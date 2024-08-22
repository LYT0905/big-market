package com.big.market.infrastructure.domain.award.service;

import com.big.market.infrastructure.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author 莱特0905
 * @Description: 奖品服务接口
 * @Date: 2024/08/22 14:23:58
 */
public interface IAwardService {
    /**
     * 保存用户获奖记录
     */
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);
}
