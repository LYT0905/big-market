package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IStrategyAwardDao;
import com.big.market.infrastructure.infrastructure.persistent.po.StrategyAward;
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
 * @Description: 策略奖品dao的测试类
 * @Date: 2024/07/13 17:36:26
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class StrategyAwardTest {
    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Test
    public void test_queryStrategyAwardList(){
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardList();
        log.info("策略奖品有{}", JSON.toJSONString(strategyAwards));
    }

    @Test
    public void test_queryStrategyAwardListByStrategyId(){
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(100001L);
        log.info("策略ID为{},策略奖品有{}", 100001, JSON.toJSONString(strategyAwards));
    }
    @Test
    public void test_queryStrategyRuleModels(){
        StrategyAward strategyAward = new StrategyAward();
        strategyAward.setStrategyId(100001L);
        strategyAward.setAwardId(107);
        String ruleModels = strategyAwardDao.queryStrategyRuleModels(strategyAward);
        log.info("规则模型有{}", ruleModels);
    }

}
