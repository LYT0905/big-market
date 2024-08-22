package com.big.market.infrastructure.domain.task.service;

import com.big.market.infrastructure.domain.task.mdoel.entity.TaskEntity;
import com.big.market.infrastructure.domain.task.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 任务接口实现
 * @Date: 2024/08/22 15:48:31
 */
@Service
public class TaskService implements ITaskService{

    @Resource
    private ITaskRepository taskRepository;

    /**
     * 查找没有发送消息的任务消息
     * @return 任务集合
     */
    @Override
    public List<TaskEntity> queryNoSendMessageTakeList() {
        return taskRepository.queryNoSendMessageTakeList();
    }

    /**
     * 发送消息
     * @param task 任务
     */
    @Override
    public void sendMessage(TaskEntity task) {
        taskRepository.sendMessage(task);
    }

    /**
     * 更新任务发送消息发送完成
     * @param userId 用户ID
     * @param messageId 消息编号
     */
    @Override
    public void updateTaskSendMessageCompleted(String userId, String messageId) {
        taskRepository.updateTaskSendMessageCompleted(userId, messageId);
    }

    /**
     * 更新任务发送消息发送失败
     * @param userId 用户ID
     * @param messageId 消息编号
     */
    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        taskRepository.updateTaskSendMessageFail(userId, messageId);
    }
}
