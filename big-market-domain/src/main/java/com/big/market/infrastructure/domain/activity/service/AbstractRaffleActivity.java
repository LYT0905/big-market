package com.big.market.infrastructure.domain.activity.service;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.domain.activity.model.entity.*;
import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 抽奖活动抽象类，定义标准的流程
 * @Date: 2024/08/13 19:32:14
 */
@Slf4j
public class AbstractRaffleActivity implements IRaffleOrder{

    protected IActivityRepository activityRepository;

    public AbstractRaffleActivity(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity) {
        // 通过sku查询活动信息
        ActivitySkuEntity activitySkuEntity = activityRepository.queryActivitySku(activityShopCartEntity.getSku());
        // 查询活动信息
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        // 查询次数信息（用户在活动上可参与的次数）
        ActivityCountEntity activityCountEntity = activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());

        log.info("查询结果：{} {} {}", JSON.toJSONString(activitySkuEntity), JSON.toJSONString(activityEntity), JSON.toJSONString(activityCountEntity));

        return ActivityOrderEntity.builder().build();

    }
}
