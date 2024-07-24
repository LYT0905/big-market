package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRuleTreeNodeLineDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RuleTreeNodeLine;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 规则树节点连接测试类
 * @Date: 2024/07/23 22:09:02
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RuleTreeNodeLineTest {
    @Resource
    private IRuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Test
    public void test_queryRuleTreeNodeLineList(){
        List<RuleTreeNodeLine> ruleTreeNodeLines = ruleTreeNodeLineDao.queryRuleTreeNodeLineListByTreeId("tree_lock");
        log.info("规则树节点连接有{}", JSON.toJSONString(ruleTreeNodeLines));
    }
}
