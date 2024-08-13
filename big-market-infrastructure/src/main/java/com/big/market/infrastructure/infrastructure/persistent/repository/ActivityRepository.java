package com.big.market.infrastructure.infrastructure.persistent.repository;

import com.big.market.infrastructure.domain.activity.model.entity.ActivityCountEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivitySkuEntity;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivityStateVO;
import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityCountDao;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityDao;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivitySkuDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivity;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityCount;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivitySku;
import com.big.market.infrastructure.infrastructure.persistent.redis.RedissonService;
import com.big.market.infrastructure.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.redisson.client.RedisClient;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 活动服务仓储实现
 * @Date: 2024/08/13 19:47:43
 */
@Slf4j
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IRaffleActivitySkuDao raffleActivitySkuDao;
    @Resource
    private IRaffleActivityDao raffleActivityDao;
    @Resource
    private IRaffleActivityCountDao raffleActivityCountDao;
    @Resource
    private RedissonService redissonService;

    /**
     * 根据商品 sku 查找活动 Sku 实体
     * @param sku 商品 sku
     * @return 活动 Sku 实体
     */
    @Override
    public ActivitySkuEntity queryActivitySku(Long sku) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_KEY + sku;
        ActivitySkuEntity activitySkuEntity = redissonService.getValue(cacheKey);
        if (activitySkuEntity != null){
            return activitySkuEntity;
        }
        RaffleActivitySku raffleActivitySku = raffleActivitySkuDao.queryActivitySku(sku);
        activitySkuEntity = ActivitySkuEntity.builder()
                .activityCountId(raffleActivitySku.getActivityCountId())
                .activityId(raffleActivitySku.getActivityId())
                .sku(sku)
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .stockCount(raffleActivitySku.getStockCount())
                .build();
        redissonService.setValue(cacheKey, activitySkuEntity);
        return activitySkuEntity;
    }

    /**
     * 根据活动 ID查询活动信息
     * @param activityId 活动 ID
     * @return 活动信息
     */
    @Override
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redissonService.getValue(cacheKey);
        if (activityEntity != null){
            return activityEntity;
        }
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityCountId(raffleActivity.getActivityCountId())
                .activityDesc(raffleActivity.getActivityDesc())
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityStateVO.valueOf(raffleActivity.getState()))
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .build();
        redissonService.setValue(cacheKey, activityEntity);
        return activityEntity;
    }

    /**
     * 查询次数信息（用户在活动上可参与的次数）
     * @param activityCountId 活动次数 ID
     * @return 次数信息
     */
    @Override
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {

        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redissonService.getValue(cacheKey);
        if (activityCountEntity != null){
            return activityCountEntity;
        }

        RaffleActivityCount raffleActivityCount = raffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);

        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(activityCountId)
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .totalCount(raffleActivityCount.getTotalCount())
                .build();
        redissonService.setValue(cacheKey, activityCountEntity);
        return activityCountEntity;
    }
}
