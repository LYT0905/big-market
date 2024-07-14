package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.domain.strategy.service.IStrategyArmoryService;
import com.big.market.infrastructure.infrastructure.persistent.dao.IStrategyDao;
import com.big.market.infrastructure.infrastructure.persistent.po.Strategy;
import com.big.market.infrastructure.infrastructure.persistent.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author LYT0905
 * @Description:
 * @Date: 2024/07/13 17:45:15
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class StrategyTest {

    @Resource
    private IStrategyDao strategyDao;

    @Test
    public void test_queryStrategyList(){
        List<Strategy> strategies = strategyDao.queryStrategyList();
        log.info("策略有{}", JSON.toJSONString(strategies));
    }
}


