package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRuleTreeDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RuleTree;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 规则树测试类
 * @Date: 2024/07/23 21:45:41
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RuleTreeTest {
    @Resource
    private IRuleTreeDao ruleTreeDao;

    @Test
    public void test_queryRuleTreeList(){
        RuleTree ruleTree = ruleTreeDao.queryRuleTreeByTreeId("tree_lock");
        log.info("规则树有{}", JSON.toJSONString(ruleTree));
    }
}
