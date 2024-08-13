package com.big.market.infrastructure.domain.test.activity;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityOrderEntity;
import com.big.market.infrastructure.domain.activity.model.entity.ActivityShopCartEntity;
import com.big.market.infrastructure.domain.activity.service.IRaffleOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 抽奖活动订单单测
 * @Date: 2024/08/13 20:19:32
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleOrderTest {

    @Resource
    private IRaffleOrder raffleOrder;

    @Test
    public void test_createRaffleActivityOrder() {
        ActivityShopCartEntity activityShopCartEntity = new ActivityShopCartEntity();
        activityShopCartEntity.setUserId("lyt");
        activityShopCartEntity.setSku(9011L);
        ActivityOrderEntity raffleActivityOrder = raffleOrder.createRaffleActivityOrder(activityShopCartEntity);
        log.info("测试结果：{}", JSON.toJSONString(raffleActivityOrder));
    }

}

