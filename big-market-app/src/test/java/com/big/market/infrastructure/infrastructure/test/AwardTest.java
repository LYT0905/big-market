package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IAwardDao;
import com.big.market.infrastructure.infrastructure.persistent.po.Award;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LYT0905
 * @Description: 奖品测试类
 * @Date: 2024/07/13 17:26:04
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class AwardTest {

    @Resource
    private IAwardDao awardDao;

    @Test
    public void test_queryAwardList(){
        List<Award> awards = awardDao.queryAwardList();
        log.info("奖品有{}", JSON.toJSONString(awards));
    }
}
