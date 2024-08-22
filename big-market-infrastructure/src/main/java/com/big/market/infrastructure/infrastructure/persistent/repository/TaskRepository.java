package com.big.market.infrastructure.infrastructure.persistent.repository;

import com.big.market.infrastructure.domain.task.mdoel.entity.TaskEntity;
import com.big.market.infrastructure.domain.task.repository.ITaskRepository;
import com.big.market.infrastructure.infrastructure.persistent.dao.ITaskDao;
import com.big.market.infrastructure.infrastructure.persistent.event.EventPublisher;
import com.big.market.infrastructure.infrastructure.persistent.po.Task;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 任务仓储接口实现
 * @Date: 2024/08/22 15:42:44
 */
@Repository
public class TaskRepository implements ITaskRepository {
    @Resource
    private ITaskDao taskDao;
    @Resource
    private EventPublisher eventPublisher;

    /**
     * 查找没有发送消息的任务消息
     * @return 任务集合
     */
    @Override
    public List<TaskEntity> queryNoSendMessageTakeList() {
        List<Task> taskList = taskDao.queryNoSendMessageTakeList();
        List<TaskEntity> taskEntityList = new ArrayList<>();
        for (Task task : taskList) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setUserId(task.getUserId());
            taskEntity.setMessageId(task.getMessageId());
            taskEntity.setTopic(task.getTopic());
            taskEntity.setMessage(task.getMessage());
            taskEntityList.add(taskEntity);
        }
        return taskEntityList;
    }

    /**
     * 发送消息
     * @param task 任务
     */
    @Override
    public void sendMessage(TaskEntity task) {
        eventPublisher.publish(task.getTopic(), task.getMessage());
    }

    /**
     * 更新任务发送消息发送完成
     * @param userId 用户ID
     * @param messageId 消息编号
     */
    @Override
    public void updateTaskSendMessageCompleted(String userId, String messageId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setMessageId(messageId);
        taskDao.updateTaskSendMessageCompleted(task);
    }

    /**
     * 更新任务发送消息发送失败
     * @param userId 用户ID
     * @param messageId 消息编号
     */
    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setMessageId(messageId);
        taskDao.updateTaskSendMessageFail(task);
    }
}
