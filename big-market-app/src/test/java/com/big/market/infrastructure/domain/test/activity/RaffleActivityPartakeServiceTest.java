package com.big.market.infrastructure.domain.test.activity;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.domain.activity.model.entity.PartakeRaffleActivityEntity;
import com.big.market.infrastructure.domain.activity.model.entity.UserRaffleOrderEntity;
import com.big.market.infrastructure.domain.activity.service.IRaffleActivityPartakeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author 莱特0905
 * @Description: 抽奖活动订单单测
 * @Date: 2024/08/24 22:30:28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityPartakeServiceTest {

    @Resource
    private IRaffleActivityPartakeService raffleActivityPartakeService;

    @Test
    public void test_createOrder() throws InterruptedException {
        // 请求参数
        PartakeRaffleActivityEntity partakeRaffleActivityEntity = new PartakeRaffleActivityEntity();
        partakeRaffleActivityEntity.setUserId("lyt");
        partakeRaffleActivityEntity.setActivityId(101L);
        // 调用接口
        UserRaffleOrderEntity userRaffleOrder = raffleActivityPartakeService.createOrder(partakeRaffleActivityEntity);
        log.info("请求参数：{}", JSON.toJSONString(partakeRaffleActivityEntity));
        log.info("测试结果：{}", JSON.toJSONString(userRaffleOrder));
        new CountDownLatch(1).await();
    }

}

