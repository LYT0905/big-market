package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityCountDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityCount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动次数配置测试类
 * @Date: 2024/08/09 21:06:56
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RaffleActivityCountTest {

    @Resource
    private IRaffleActivityCountDao raffleActivityCountDao;

    @Test
    public void test_queryAllRaffleActivityCount(){
        List<RaffleActivityCount> raffleActivityCounts = raffleActivityCountDao.queryAllRaffleActivityCount();
        log.info("抽奖活动次数配置有{}", JSON.toJSONString(raffleActivityCounts));
    }

}
