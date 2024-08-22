package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 任务表，发送MQ Dao
 * @Date: 2024/08/18 13:58:43
 */
@Mapper
public interface ITaskDao {
    List<Task> queryAllTask();

    /**
     * 插入任务记录
     * @param task 任务记录
     */
    void insert(Task task);

    /**
     * 更新任务发送消息发送完成
     * @param task 任务
     */
    void updateTaskSendMessageCompleted(Task task);

    /**
     * 更新任务发送消息发送失败
     * @param task 任务
     */
    void updateTaskSendMessageFail(Task task);

    /**
     * 查找没有发送消息的任务消息
     * @return 任务集合
     */
    List<Task> queryNoSendMessageTakeList();
}
