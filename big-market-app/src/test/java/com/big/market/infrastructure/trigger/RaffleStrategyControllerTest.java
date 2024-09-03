package com.big.market.infrastructure.trigger;

import com.alibaba.fastjson2.JSON;
import com.big.market.api.IRaffleStrategyService;
import com.big.market.api.dto.RaffleAwardListRequestDTO;
import com.big.market.api.dto.RaffleAwardListResponseDTO;
import com.big.market.infrastructure.types.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莱特0905
 * @Description: 营销抽奖服务测试
 * @Date: 2024/09/03 20:08:21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyControllerTest {

    @Resource
    private IRaffleStrategyService raffleStrategyService;

    @Test
    public void test_queryRaffleAwardList() {
        RaffleAwardListRequestDTO request = new RaffleAwardListRequestDTO();
        request.setUserId("lyt");
        request.setActivityId(101L);
        Response<List<RaffleAwardListResponseDTO>> response = raffleStrategyService.queryRaffleAwardList(request);

        log.info("请求参数：{}", JSON.toJSONString(request));
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

}

