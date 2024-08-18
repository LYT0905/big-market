package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IUserRaffleOrderDao;
import com.big.market.infrastructure.infrastructure.persistent.po.UserRaffleOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 用户抽奖订单测试类
 * @Date: 2024/08/18 14:15:21
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class UserRaffleOrderTest {

    @Resource
    private IUserRaffleOrderDao userRaffleOrderDao;

    @Test
    public void test_queryAllUserRaffleOrder(){
        List<UserRaffleOrder> userRaffleOrders = userRaffleOrderDao.queryAllUserRaffleOrder();
        log.info("用户抽奖订单有:{}", JSON.toJSONString(userRaffleOrders));
    }
}
