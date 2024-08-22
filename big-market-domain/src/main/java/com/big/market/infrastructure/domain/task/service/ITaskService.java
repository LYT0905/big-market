package com.big.market.infrastructure.domain.task.service;



import com.big.market.infrastructure.domain.task.mdoel.entity.TaskEntity;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 任务接口
 * @Date: 2024/08/22 15:38:40
 */
public interface ITaskService {
    /**
     * 查找没有发送消息的任务消息
     * @return 任务集合
     */
    List<TaskEntity> queryNoSendMessageTakeList();

    /**
     * 发送消息
     * @param task 任务
     */
    void sendMessage(TaskEntity task);

    /**
     * 更新任务发送消息发送完成
     * @param userId 用户ID
     * @param messageId 消息编号
     */
    void updateTaskSendMessageCompleted(String userId, String messageId);

    /**
     * 更新任务发送消息发送失败
     * @param userId 用户ID
     * @param messageId 消息编号
     */
    void updateTaskSendMessageFail(String userId, String messageId);
}
