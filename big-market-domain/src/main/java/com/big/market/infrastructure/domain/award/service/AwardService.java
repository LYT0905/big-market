package com.big.market.infrastructure.domain.award.service;

import com.big.market.infrastructure.domain.award.event.SendAwardMessageEvent;
import com.big.market.infrastructure.domain.award.model.aggregate.UserAwardRecordAggregate;
import com.big.market.infrastructure.domain.award.model.entity.TaskEntity;
import com.big.market.infrastructure.domain.award.model.entity.UserAwardRecordEntity;
import com.big.market.infrastructure.domain.award.model.valobj.AwardStateVO;
import com.big.market.infrastructure.domain.award.model.valobj.TaskStateVO;
import com.big.market.infrastructure.domain.award.repository.IAwardRepository;
import com.big.market.infrastructure.types.event.BaseEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 奖品服务实现
 * @Date: 2024/08/22 14:49:40
 */
@Service
public class AwardService implements IAwardService{

    @Resource
    private IAwardRepository awardRepository;
    @Resource
    private SendAwardMessageEvent sendAwardMessageEvent;

    /**
     * 保存用户获奖记录
     */
    @Override
    public void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity) {
        // 构建消息对象
        SendAwardMessageEvent.SendAwardMessage sendAwardMessage = SendAwardMessageEvent.SendAwardMessage.builder()
                .userId(userAwardRecordEntity.getUserId())
                .awardId(userAwardRecordEntity.getAwardId())
                .awardTitle(userAwardRecordEntity.getAwardTitle())
                .build();
        BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> sendAwardMessageEventMessage = sendAwardMessageEvent.buildEventMessage(sendAwardMessage);

        // 构建任务对象
        TaskEntity taskEntity = TaskEntity.builder()
                .userId(userAwardRecordEntity.getUserId())
                .topic(sendAwardMessageEvent.topic())
                .state(TaskStateVO.create)
                .message(sendAwardMessageEventMessage)
                .messageId(sendAwardMessageEventMessage.getId())
                .build();

        // 构建用户中奖记录聚合对象
        UserAwardRecordAggregate userAwardRecordAggregate = UserAwardRecordAggregate.builder()
                .taskEntity(taskEntity)
                .userAwardRecordEntity(userAwardRecordEntity)
                .build();

        // 存储聚合对象:一个事务下，用户的中奖记录
        awardRepository.saveUserAwardRecord(userAwardRecordAggregate);
    }
}
