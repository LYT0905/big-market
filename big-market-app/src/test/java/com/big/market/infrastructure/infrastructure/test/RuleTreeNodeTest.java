package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRuleTreeNodeDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RuleTreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 规则树节点测试类
 * @Date: 2024/07/23 21:52:22
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RuleTreeNodeTest {
    @Resource
    private IRuleTreeNodeDao ruleTreeNodeDao;

    @Test
    public void test_queryRuleTreeNodeList(){
        List<RuleTreeNode> ruleTreeNodes = ruleTreeNodeDao.queryRuleTreeNodeListByTreeId("tree_lock");
        log.info("规则树节点有{}", JSON.toJSONString(ruleTreeNodes));
    }
}
