package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityOrderDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动订单类
 * @Date: 2024/08/09 21:25:14
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RaffleActivityOrderTest {

    @Resource
    private IRaffleActivityOrderDao raffleActivityOrderDao;

    @Test
    public void test_queryAllRaffleActivityOrder(){
        List<RaffleActivityOrder> raffleActivityOrders = raffleActivityOrderDao.queryAllRaffleActivityOrder();
        log.info("抽奖活动订单有{}", JSON.toJSONString(raffleActivityOrders));
    }

}
