package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.ITaskDao;
import com.big.market.infrastructure.infrastructure.persistent.po.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 任务表，发送MQ测试类
 * @Date: 2024/08/18 14:01:58
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class TaskTest {

    @Resource
    private ITaskDao taskDao;

    @Test
    public void test_queryAllTask(){
        List<Task> tasks = taskDao.queryAllTask();
        log.info("任务表数据有:{}", JSON.toJSONString(tasks));
    }

}
