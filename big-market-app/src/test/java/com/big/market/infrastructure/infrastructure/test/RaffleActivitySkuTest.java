package com.big.market.infrastructure.infrastructure.test;

import com.alibaba.fastjson2.JSON;
import com.big.market.infrastructure.infrastructure.persistent.dao.IRaffleActivitySkuDao;
import com.big.market.infrastructure.infrastructure.persistent.po.RaffleActivitySku;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 活动 SKU 测试类
 * @Date: 2024/08/12 22:42:33
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RaffleActivitySkuTest {

    @Resource
    private IRaffleActivitySkuDao raffleActivitySkuDao;

    @Test
    public void test_queryAllRaffleActivitySku(){
        List<RaffleActivitySku> raffleActivitySkus = raffleActivitySkuDao.queryAllRaffleActivitySku();
        log.info("活动 SKU 有{}", JSON.toJSONString(raffleActivitySkus));
    }

}
