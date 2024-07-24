package com.big.market.infrastructure.infrastructure.test;

import com.big.market.infrastructure.domain.strategy.model.valobj.RuleTreeVO;
import com.big.market.infrastructure.infrastructure.persistent.repository.StrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author LYT0905
 * @Description: 仓储测试类
 * @Date: 2024/07/23 22:22:00
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class StrategyRepositoryTest {

    @Resource
    private StrategyRepository repository;

    @Test
    public void test_queryRuleTreeVOByTreeId(){
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId("tree_lock");
        log.info("测试结果为{}", ruleTreeVO);
    }

}
