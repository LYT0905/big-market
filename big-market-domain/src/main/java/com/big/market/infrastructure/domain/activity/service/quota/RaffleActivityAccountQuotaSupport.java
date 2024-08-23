package com.big.market.infrastructure.domain.activity.service.quota;

import com.big.market.infrastructure.domain.activity.model.entity.ActivityCountEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivitySkuEntity;
import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import com.big.market.infrastructure.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;

/**
 * @author 莱特0905
 * @Description: 抽奖活动的支撑类
 * @Date: 2024/08/14 21:06:17
 */
public class RaffleActivityAccountQuotaSupport {

    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivityAccountQuotaSupport(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        this.activityRepository = activityRepository;
        this.defaultActivityChainFactory = defaultActivityChainFactory;
    }


    /**
     * 通过sku查询活动信息
     * @param sku sku
     * @return 活动信息
     */
    public ActivitySkuEntity queryActivitySku(Long sku){
        return activityRepository.queryActivitySku(sku);
    }

    /**
     * 查询活动信息
     * @param activityId 活动ID
     * @return 活动信息
     */
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId){
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    /**
     * 查询次数信息（用户在活动上可参与的次数）
     * @param activityCountId 活动个人参数ID；在这个活动上，一个人可参与多少次活动（总、日、月）
     * @return 次数信息（用户在活动上可参与的次数）
     */
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId){
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }
}
