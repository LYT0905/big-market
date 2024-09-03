package com.big.market.infrastructure.trigger.http;

import com.big.market.api.IRaffleActivityService;
import com.big.market.api.IRaffleStrategyService;
import com.big.market.api.dto.ActivityDrawRequestDTO;
import com.big.market.api.dto.ActivityDrawResponseDTO;
import com.big.market.infrastructure.domain.activity.model.entity.UserRaffleOrderEntity;
import com.big.market.infrastructure.domain.activity.service.IRaffleActivityPartakeService;
import com.big.market.infrastructure.domain.activity.service.armory.IActivityArmory;
import com.big.market.infrastructure.domain.award.model.entity.UserAwardRecordEntity;
import com.big.market.infrastructure.domain.award.model.valobj.AwardStateVO;
import com.big.market.infrastructure.domain.award.service.AwardService;
import com.big.market.infrastructure.domain.award.service.IAwardService;
import com.big.market.infrastructure.domain.strategy.model.entity.RaffleAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RaffleFactorEntity;
import com.big.market.infrastructure.domain.strategy.service.IRaffleAward;
import com.big.market.infrastructure.domain.strategy.service.IRaffleStrategy;
import com.big.market.infrastructure.domain.strategy.service.armory.IStrategyArmoryService;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.exception.AppException;
import com.big.market.infrastructure.types.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 莱特0905
 * @Description: 抽奖活动服务
 * @Date: 2024/09/02 19:19:43
 */
@Slf4j
@RestController()
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/activity")
public class RaffleActivityController implements IRaffleActivityService {

    @Resource
    private IActivityArmory activityArmory;
    @Resource
    private IStrategyArmoryService strategyArmoryService;
    @Resource
    private IRaffleActivityPartakeService raffleActivityPartakeService;
    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private IAwardService awardService;

    /**
     * 活动装配 - 数据预热 | 把活动配置的对应的 sku 一起装配
     *
     * @param activityId 活动ID
     * @return 装配结果
     * <p>
     * 接口：<a href="http://localhost:8091/api/v1/raffle/activity/armory">/api/v1/raffle/activity/armory</a>
     * 入参：{"activityId":100001,"userId":"xiaofuge"}
     *
     * curl --request GET \
     *   --url 'http://localhost:8091/api/v1/raffle/activity/armory?activityId=100301'
     */

    @GetMapping("/armory")
    @Override
    public Response<Boolean> armory(@RequestParam("activityId") Long activityId) {
        try {
            log.info("活动装配，数据预热，开始 activityId:{}", activityId);
            // 1. 活动装配
            activityArmory.assembleActivitySkuByActivityId(activityId);
            // 2. 策略装配
            strategyArmoryService.assembleLotteryStrategyByActivityId(activityId);
            Response<Boolean> response = Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
            log.info("活动装配，数据预热，完成 activityId:{}", activityId);
            return response;
        }catch (Throwable ex){
            log.error("活动装配，数据预热，失败 activityId:{}", activityId, ex);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();

        }
    }

    /**
     * 抽奖接口
     *
     * @param requestParam 请求对象
     * @return 抽奖结果
     * <p>
     * 接口：<a href="http://localhost:8091/api/v1/raffle/activity/draw">/api/v1/raffle/activity/draw</a>
     * 入参：{"activityId":100001,"userId":"xiaofuge"}
     *
     * curl --request POST \
     *   --url http://localhost:8091/api/v1/raffle/activity/draw \
     *   --header 'content-type: application/json' \
     *   --data '{
     *     "userId":"xiaofuge",
     *     "activityId": 100301
     * }'
     */
    @PostMapping("/draw")
    @Override
    public Response<ActivityDrawResponseDTO> draw(@RequestBody ActivityDrawRequestDTO requestParam) {
        try {
            log.info("活动抽奖 userId:{} activityId:{}", requestParam.getUserId(), requestParam.getActivityId());
            // 1. 参数校验
            if (StringUtils.isEmpty(requestParam.getUserId()) || requestParam.getActivityId() == null){
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
            }
            // 2. 参与活动 - 创建参与记录订单
            UserRaffleOrderEntity orderEntity = raffleActivityPartakeService.createOrder(requestParam.getUserId(), requestParam.getActivityId());
            log.info("活动抽奖，创建订单 userId:{} activityId:{} orderId:{}", requestParam.getUserId(), requestParam.getActivityId(), orderEntity.getOrderId());
            // 3. 抽奖策略 - 执行抽奖
            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(RaffleFactorEntity.builder()
                    .strategyId(orderEntity.getStrategyId())
                    .userId(requestParam.getUserId())
                    .endDateTime(orderEntity.getEndDateTime())
                    .build());
            // 4. 存放结果 - 写入中奖记录
            UserAwardRecordEntity userAwardRecordEntity = UserAwardRecordEntity.builder()
                    .userId(requestParam.getUserId())
                    .activityId(requestParam.getActivityId())
                    .awardId(raffleAwardEntity.getAwardId())
                    .awardTitle(raffleAwardEntity.getAwardTitle())
                    .awardState(AwardStateVO.create)
                    .awardTime(new Date())
                    .orderId(orderEntity.getOrderId())
                    .strategyId(orderEntity.getStrategyId())
                    .build();
            awardService.saveUserAwardRecord(userAwardRecordEntity);
            // 5. 返回结果
            return Response.<ActivityDrawResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(ActivityDrawResponseDTO.builder()
                            .awardId(raffleAwardEntity.getAwardId())
                            .awardTitle(raffleAwardEntity.getAwardTitle())
                            .awardIndex(raffleAwardEntity.getSort())
                            .build())
                    .build();

        }catch (AppException ex) {
            log.error("活动抽奖失败 userId:{} activityId:{}", requestParam.getUserId(), requestParam.getActivityId(), ex);
            return Response.<ActivityDrawResponseDTO>builder()
                    .code(ex.getCode())
                    .info(ex.getInfo())
                    .build();
        }catch (Throwable ex){
            log.error("活动抽奖失败 userId:{} activityId:{}", requestParam.getUserId(), requestParam.getActivityId(), ex);
            return Response.<ActivityDrawResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();

        }
    }
}
