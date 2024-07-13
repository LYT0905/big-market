package com.big.market.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IStrategyRuleDao;
import com.big.market.infrastructure.infrastructure.persistent.po.StrategyRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LYT0905
 * @Description:
 * @Date: 2024/07/13 17:51:00
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class StrategyRuleTest {

    @Resource
    private IStrategyRuleDao strategyRuleDao;

    @Test
    public void test_queryStrategyList(){
        List<StrategyRule> strategyRules = strategyRuleDao.queryStrategyList();
        log.info("策略规则有{}", JSON.toJSONString(strategyRules));
    }
}
