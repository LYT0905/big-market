package com.big.market.infrastructure.trigger;

import com.alibaba.fastjson2.JSON;
import com.big.market.api.IRaffleActivityService;
import com.big.market.api.dto.ActivityDrawRequestDTO;
import com.big.market.api.dto.ActivityDrawResponseDTO;
import com.big.market.infrastructure.types.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 莱特0905
 * @Description: 抽奖活动服务测试
 * @Date: 2024/09/03 20:33:28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityControllerTest {

    @Resource
    private IRaffleActivityService raffleActivityService;

    @Test
    public void test_armory() {
        Response<Boolean> response = raffleActivityService.armory(101L);
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_draw() {
        ActivityDrawRequestDTO request = new ActivityDrawRequestDTO();
        request.setActivityId(101L);
        request.setUserId("lyt");
        Response<ActivityDrawResponseDTO> response = raffleActivityService.draw(request);

        log.info("请求参数：{}", JSON.toJSONString(request));
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

}

