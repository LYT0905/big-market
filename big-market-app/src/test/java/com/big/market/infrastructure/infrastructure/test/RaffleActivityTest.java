package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动测试类
 * @Date: 2024/08/09 20:50:51
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RaffleActivityTest {

    @Resource
    private IRaffleActivityDao raffleActivityDao;

    @Test
    public void test_queryAllRaffleActivity(){
        List<RaffleActivity> raffleActivities = raffleActivityDao.queryAllRaffleActivity();
        log.info("抽奖活动有{}", JSON.toJSONString(raffleActivities));
    }

}
