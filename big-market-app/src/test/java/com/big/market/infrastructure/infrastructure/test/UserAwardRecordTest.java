package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IUserAwardRecordDao;
import com.big.market.infrastructure.infrastructure.persistent.po.UserAwardRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 用户中奖记录测试类
 * @Date: 2024/08/18 14:09:21
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class UserAwardRecordTest {

    @Resource
    private IUserAwardRecordDao userAwardRecordDao;

    @Test
    public void test_queryAllUserAwardRecord(){
        List<UserAwardRecord> userAwardRecords = userAwardRecordDao.queryAllUserAwardRecord();
        log.info("用户中奖记录有:{}", JSON.toJSONString(userAwardRecords));
    }
}
