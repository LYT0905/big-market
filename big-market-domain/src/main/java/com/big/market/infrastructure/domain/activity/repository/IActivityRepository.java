package com.big.market.infrastructure.domain.activity.repository;

import com.big.market.infrastructure.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.entity.*;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 活动服务仓储接口
 * @Date: 2024/08/13 19:30:09
 */
public interface IActivityRepository {
    /**
     * 通过sku查询活动信息
     * @param sku 商品 sku
     * @return 活动 Sku 实体
     */
    ActivitySkuEntity queryActivitySku(Long sku);

    /**
     * 查询活动信息
     * @param activityId 活动 ID
     * @return 活动信息实体
     */
    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    /**
     * 查询次数信息（用户在活动上可参与的次数）
     * @param activityCountId 活动次数 ID
     * @return 次数信息
     */
    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

    /**
     * 保存订单
     * @param orderAggregate 订单聚合对象
     */
    void doSaveOrder(CreateQuotaOrderAggregate orderAggregate);

    /**
     * 缓存活动sku库存信息
     * @param cacheKey 缓存key
     * @param stockCountSurplus 库存信息
     */
    void cacheActivitySkuStockCount(String cacheKey, Integer stockCountSurplus);

    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     *
     * @param sku 互动SKU
     * @param cacheKey 缓存key
     * @param endDateTime 活动结束时间，根据结束时间设置加锁的key为结束时间
     * @return 扣减结果
     */
    boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);

    /**
     * 延迟消费更新库存记录
     * @param activitySkuStockKeyVO 活动 id 和 sku id
     */
    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO activitySkuStockKeyVO);

    /**
     * 获取活动sku库存消耗队列
     *
     * @return 奖品库存Key信息
     */
    ActivitySkuStockKeyVO takeQueueValue();

    /**
     * 获取活动sku库存消耗队列
     * @param sku 活动商品
     * @return 奖品库存Key信息
     */
    ActivitySkuStockKeyVO takeQueueValue(Long sku);

    /**
     * 清空队列
     */
    void clearQueueValue();

    /**
     * 清空队列
     * @param sku 活动商品
     */
    void clearQueueValue(Long sku);

    /**
     * 延迟队列 + 任务趋势更新活动sku库存
     *
     * @param sku 活动商品
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 缓存库存以消耗完毕，清空数据库库存
     *
     * @param sku 活动商品
     */
    void clearActivitySkuStock(Long sku);

    /**
     * 查询活动商品列表
     * @return 活动商品列表
     */
    List<Long> querySkuList();

    /**
     * 查询未使用的活动订单
     * @param partakeRaffleActivityEntity 参与抽奖活动实体对象
     * @return 用户抽奖订单实体对象
     */
    UserRaffleOrderEntity queryNoUsedRaffleOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);

    /**
     * 查询用户总账户额度
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 活动账户实体对象
     */
    ActivityAccountEntity queryActivityAccountByUserId(String userId, Long activityId);

    /**
     * 查询账户月额度
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param month 月份
     * @return 活动账户（月）实体对象
     */
    ActivityAccountMonthEntity queryActivityAccountMonthByUserId(String userId, Long activityId, String month);

    /**
     * 查询账户日额度
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param day 当天
     * @return 活动账户（日）实体对象
     */
    ActivityAccountDayEntity queryActivityAccountDayByUserId(String userId, Long activityId, String day);

    /**
     * 保存聚合对象
     * @param createPartakeOrderAggregate 参与活动订单聚合对象
     */
    void saveCreatePartakeOrderAggregate(CreatePartakeOrderAggregate createPartakeOrderAggregate);

    /**
     * 根据活动 ID 查找活动 SKU 集合
     * @param activityId 活动 ID
     * @return 活动 SKU 集合
     */
    List<ActivitySkuEntity> queryActivitySkuListByActivityId(Long activityId);

    /**
     * 查询用户当天抽奖次数
     * @param userId 用户 ID
     * @param activityId 活动 ID
     * @return 抽奖次数
     */
    Integer queryRaffleActivityAccountDayPartakeCount(String userId, Long activityId);
}
