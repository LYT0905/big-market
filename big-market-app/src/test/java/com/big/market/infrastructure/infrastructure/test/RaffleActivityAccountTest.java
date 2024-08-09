package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivityAccountDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivityAccount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖活动账户测试类
 * @Date: 2024/08/09 21:36:20
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RaffleActivityAccountTest {

    @Resource
    private IRaffleActivityAccountDao raffleActivityAccountDao;

    @Test
    public void test_queryAllRaffleActivityAccount(){
        List<RaffleActivityAccount> raffleActivityAccounts = raffleActivityAccountDao.queryAllRaffleActivityAccount();
        log.info("抽奖活动账户有{}", JSON.toJSONString(raffleActivityAccounts));
    }

}
