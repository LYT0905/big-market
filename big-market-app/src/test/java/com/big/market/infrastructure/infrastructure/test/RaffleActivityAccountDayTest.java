package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityAccountDayDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccountDay;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户表-日次数测试类
 * @Date: 2024/08/18 14:22:21
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RaffleActivityAccountDayTest {

    @Resource
    IRaffleActivityAccountDayDao raffleActivityAccountDayDao;

    @Test
    public void test_queryAllRaffleActivityAccountDay(){
        List<RaffleActivityAccountDay> raffleActivityAccountDays = raffleActivityAccountDayDao.queryAllRaffleActivityAccountDay();
        log.info("抽奖活动账户表-日次数表记录有:{}", JSON.toJSONString(raffleActivityAccountDays));
    }
}
