package com.big.market.infrastructure.domain.activity.service.partake;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.PartakeRaffleActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.UserRaffleOrderEntity;
import com.big.market.infrastructure.domain.activity.model.valobj.ActivityStateVO;
import com.big.market.infrastructure.domain.activity.repository.IActivityRepository;
import com.big.market.infrastructure.domain.activity.service.IRaffleActivityPartakeService;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 抽奖活动参与抽奖类
 * @Date: 2024/08/24 19:51:21
 */
@Slf4j
public abstract class AbstractRaffleActivityPartake implements IRaffleActivityPartakeService {

    protected final IActivityRepository activityRepository;

    public AbstractRaffleActivityPartake(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity) {
        String userId = partakeRaffleActivityEntity.getUserId();
        Long activityId = partakeRaffleActivityEntity.getActivityId();
        Date currentDate = new Date();
        // 查询活动
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);
        // 活动状态校验
        if (!ActivityStateVO.open.equals(activityEntity.getState())){
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(), ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        // 活动时间校验
        if (activityEntity.getBeginDateTime().after(currentDate) || activityEntity.getEndDateTime().before(currentDate)){
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(), ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }

        // 查询未使用的活动订单
        UserRaffleOrderEntity userRaffleOrderEntity = activityRepository.queryNoUsedRaffleOrder(partakeRaffleActivityEntity);

        if (userRaffleOrderEntity != null){
            log.info("创建参与活动【已存在未消费】userId:{} activityId:{} userRaffleEntity:{}", userId, activityId, JSON.toJSONString(userRaffleOrderEntity));
            return userRaffleOrderEntity;
        }

        // 账户额度过滤&&返回账户构建对象
        CreatePartakeOrderAggregate createPartakeOrderAggregate = this.doFilter(userId, activityId, currentDate);
        // 构建用户抽奖订单
        UserRaffleOrderEntity userRaffleOrder = this.buildUserRaffleOrder(userId, activityId, currentDate);

        // 填充抽奖单实体对象
        createPartakeOrderAggregate.setUserRaffleOrderEntity(userRaffleOrder);

        // 保存聚合对象，一个领域内的一个聚合是一个事务操作
        activityRepository.saveCreatePartakeOrderAggregate(createPartakeOrderAggregate);
        return userRaffleOrder;
    }

    protected abstract UserRaffleOrderEntity buildUserRaffleOrder(String userId, Long activityId, Date currentDate);

    protected abstract CreatePartakeOrderAggregate doFilter(String userId, Long activityId, Date currentDate);
}
