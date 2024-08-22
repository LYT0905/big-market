package com.big.market.infrastructure.infrastructure.persistent.repository;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.domain.award.model.aggregate.UserAwardRecordAggregate;
import com.big.market.infrastructure.domain.award.model.entity.TaskEntity;
import com.big.market.infrastructure.domain.award.model.entity.UserAwardRecordEntity;
import com.big.market.infrastructure.domain.award.repository.IAwardRepository;
import com.big.market.infrastructure.infrastructure.persistent.dao.ITaskDao;
import com.big.market.infrastructure.infrastructure.persistent.dao.IUserAwardRecordDao;
import com.big.market.infrastructure.infrastructure.persistent.event.EventPublisher;
import com.big.market.infrastructure.infrastructure.persistent.po.Task;
import com.big.market.infrastructure.infrastructure.persistent.po.UserAwardRecord;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 奖品仓储服务实现
 * @Date: 2024/08/22 15:02:20
 */
@Repository
@Slf4j
public class AwardRepository implements IAwardRepository {

    @Resource
    private ITaskDao taskDao;
    @Resource
    private IUserAwardRecordDao userAwardRecordDao;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private EventPublisher eventPublisher;

    /**
     * 保存用户获奖记录
     */
    @Override
    public void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate) {
        UserAwardRecordEntity userAwardRecordEntity = userAwardRecordAggregate.getUserAwardRecordEntity();
        TaskEntity taskEntity = userAwardRecordAggregate.getTaskEntity();
        String userId = userAwardRecordEntity.getUserId();
        Long activityId = userAwardRecordEntity.getActivityId();
        Integer awardId = userAwardRecordEntity.getAwardId();

        UserAwardRecord userAwardRecord = new UserAwardRecord();
        userAwardRecord.setUserId(userId);
        userAwardRecord.setAwardId(awardId);
        userAwardRecord.setActivityId(activityId);
        userAwardRecord.setAwardState(userAwardRecordEntity.getAwardState().getCode());
        userAwardRecord.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        userAwardRecord.setAwardTime(userAwardRecordEntity.getAwardTime());
        userAwardRecord.setStrategyId(userAwardRecordEntity.getStrategyId());
        userAwardRecord.setOrderId(userAwardRecordEntity.getOrderId());


        Task task = new Task();
        task.setUserId(userId);
        task.setState(taskEntity.getState().getCode());
        task.setTopic(taskEntity.getTopic());
        task.setMessage(JSON.toJSONString(taskEntity.getMessage()));
        task.setMessageId(taskEntity.getMessageId());


        transactionTemplate.execute(status -> {
            try{
                // 写入记录
                userAwardRecordDao.insert(userAwardRecord);
                // 写入任务
                taskDao.insert(task);
                return 1;
            }catch (DuplicateKeyException ex){
                status.setRollbackOnly();
                log.error("写入中奖记录，唯一索引冲突 userId:{} activityId:{} awardId:{}", userId, activityId, awardId);
                throw new AppException(ResponseCode.INDEX_DUP.getCode());
            }
        });

        try {
            // 发送消息(在事务外执行，如果失败还有任务补偿)
            eventPublisher.publish(task.getTopic(), task.getMessage());
            // 更新数据库记录
            taskDao.updateTaskSendMessageCompleted(task);
        }catch (Throwable ex){
            log.error("写入中奖记录，发送MQ消息失败 userId:{} topic:{}", userId, task.getTopic());
            taskDao.updateTaskSendMessageFail(task);
        }

    }
}
