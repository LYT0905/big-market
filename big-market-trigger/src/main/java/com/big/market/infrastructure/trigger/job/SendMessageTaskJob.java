package com.big.market.infrastructure.trigger.job;

import com.big.market.infrastructure.domain.task.mdoel.entity.TaskEntity;
import com.big.market.infrastructure.domain.task.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 莱特0905
 * @Description: 发送MQ消息任务队列
 * @Date: 2024/08/22 15:36:45
 */
@Slf4j
@Component
public class SendMessageTaskJob {

    @Resource
    private ITaskService taskService;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec(){
        // 逐个扫描表
        try {
            threadPoolExecutor.execute(() -> {
                List<TaskEntity> taskEntityList = taskService.queryNoSendMessageTakeList();
                if (taskEntityList.isEmpty()){
                    return;
                }
                for (TaskEntity taskEntity : taskEntityList) {
                    // 开启线程发送，提高发送效率，配置的线程池策略为 CallerRunsPolicy
                    threadPoolExecutor.execute(() -> {
                        try {
                            taskService.sendMessage(taskEntity);
                            taskService.updateTaskSendMessageCompleted(taskEntity.getUserId(), taskEntity.getMessageId());
                        }catch (Throwable ex){
                            log.error("定时任务，发送MQ消息失败 userId:{} topic:{}", taskEntity.getUserId(), taskEntity.getTopic());
                            taskService.updateTaskSendMessageFail(taskEntity.getUserId(), taskEntity.getMessageId());
                        }
                    });
                }
            });
        }catch (Throwable ex){
            log.error("定时任务，扫描MQ任务表发送任务失败");
        }
    }
}
