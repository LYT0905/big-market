package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityAccountMonthDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccountMonth;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户表-月次数测试类
 * @Date: 2024/08/18 14:27:57
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RaffleActivityAccountMonthTest {

    @Resource
    private IRaffleActivityAccountMonthDao raffleActivityAccountMonthDao;

    @Test
    public void test_queryAllRaffleActivityAccountMonth(){
        List<RaffleActivityAccountMonth> raffleActivityAccountMonths = raffleActivityAccountMonthDao.queryAllRaffleActivityAccountMonth();
        log.info("抽奖活动账户表-月次数记录有:{}", JSON.toJSONString(raffleActivityAccountMonths));
    }
}
