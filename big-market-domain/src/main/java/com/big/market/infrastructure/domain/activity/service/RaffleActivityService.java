package com.big.market.infrastructure.domain.activity.service;

import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * @author 莱特0905
 * @Description: 抽奖活动服务
 * @Date: 2024/08/13 19:32:36
 */
@Service
public class RaffleActivityService extends AbstractRaffleActivity {
    public RaffleActivityService(IActivityRepository activityRepository){
        super(activityRepository);
    }

}
