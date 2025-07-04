package com.big.market.infrastructure.trigger.http;

import com.alibaba.fastjson.JSON;
import com.big.market.api.IRaffleStrategyService;
import com.big.market.api.dto.RaffleAwardListRequestDTO;
import com.big.market.api.dto.RaffleAwardListResponseDTO;
import com.big.market.api.dto.RaffleStrategyRequestDTO;
import com.big.market.api.dto.RaffleStrategyResponseDTO;
import com.big.market.infrastructure.domain.activity.service.IRaffleActivityAccountQuotaService;
import com.big.market.infrastructure.domain.strategy.model.entity.RaffleAwardEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.RaffleFactorEntity;
import com.big.market.infrastructure.domain.strategy.model.entity.StrategyAwardEntity;
import com.big.market.infrastructure.domain.strategy.service.IRaffleAward;
import com.big.market.infrastructure.domain.strategy.service.IRaffleRule;
import com.big.market.infrastructure.domain.strategy.service.IRaffleStrategy;
import com.big.market.infrastructure.domain.strategy.service.armory.IStrategyArmoryService;
import com.big.market.infrastructure.types.enums.ResponseCode;
import com.big.market.infrastructure.types.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 莱特0905
 * @Description: 抽奖服务
 * @Date: 2024/07/27 20:58:10
 */


@Slf4j
@RestController()
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/strategy")
public class RaffleStrategyController implements IRaffleStrategyService {

    @Resource
    private IStrategyArmoryService strategyArmoryService;
    @Resource
    private IRaffleAward raffleAward;
    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private IRaffleRule raffleRule;
    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;


    /**
     * 策略装配，将策略信息装配到缓存中
     * <a href="http://localhost:8091/api/v1/raffle/strategy_armory">/api/v1/raffle/strategy_armory</a>
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    @GetMapping("/strategy_armory")
    @Override
    public Response<Boolean> strategyArmory(@RequestParam Long strategyId) {
        try {
            log.info("抽奖策略装配开始 strategyId：{}", strategyId);
            boolean armoryStatus = strategyArmoryService.assembleLotteryStrategy(strategyId);
            Response<Boolean> response = Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(armoryStatus)
                    .build();
            log.info("抽奖策略装配完成 strategyId：{} response: {}", strategyId, JSON.toJSONString(response));
            return response;
        }catch (Throwable ex){
            log.error("抽奖策略装配失败 strategyId：{}", strategyId, ex);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * 查询奖品列表
     * <a href="http://localhost:8091/api/v1/raffle/query_raffle_award_list">/api/v1/raffle/query_raffle_award_list</a>
     * 请求参数 raw json
     *
     * @param requestDTO {"strategyId":1000001}
     * @return 奖品列表
     */
    @PostMapping("/query_raffle_award_list")
    @Override
    public Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(@RequestBody RaffleAwardListRequestDTO requestDTO) {
        try {
            log.info("查询抽奖奖品列表配开始 userId:{} activityId:{}", requestDTO.getUserId(), requestDTO.getActivityId());
            // 参数校验
            if (StringUtils.isEmpty(requestDTO.getUserId()) || requestDTO.getActivityId() == null){
                return Response.<List<RaffleAwardListResponseDTO>>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                        .build();
            }
            // 查询奖品配置
            List<StrategyAwardEntity> strategyAwardEntities = raffleAward.queryRaffleStrategyAwardListByActivityId(requestDTO.getActivityId());
            // 获取规则配置
            String[] treeIds = strategyAwardEntities.stream()
                    .map(StrategyAwardEntity::getRuleModels)
                    .filter(ruleModel -> ruleModel != null && !ruleModel.isEmpty())
                    .toArray(String[]::new);
            // 查询规则配置，获取奖品抽奖的解锁限制，抽奖 N 次后解锁
            Map<String, Integer> ruleLockCountMap = raffleRule.queryAwardRuleLockCount(treeIds);
            // 查询抽奖次数 - 用户已经抽奖的次数
            Integer dayPartakeCount = raffleActivityAccountQuotaService.queryRaffleActivityAccountDayPartakeCount(requestDTO.getUserId(), requestDTO.getActivityId());
            // 遍历填充数据
            List<RaffleAwardListResponseDTO> result = new ArrayList<>();
            for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
                Integer awardRuleLockCount = ruleLockCountMap.get(strategyAwardEntity.getRuleModels());
                RaffleAwardListResponseDTO responseDTO = RaffleAwardListResponseDTO.builder()
                        .awardId(strategyAwardEntity.getAwardId())
                        .awardTitle(strategyAwardEntity.getAwardTitle())
                        .awardSubtitle(strategyAwardEntity.getAwardSubtitle())
                        .sort(strategyAwardEntity.getSort())
                        .awardRuleLockCount(awardRuleLockCount)
                        .isAwardUnlock(awardRuleLockCount == null || dayPartakeCount > awardRuleLockCount)
                        .waitUnlockCount(awardRuleLockCount == null || dayPartakeCount >= awardRuleLockCount ? 0 : awardRuleLockCount - dayPartakeCount)
                        .build();
                result.add(responseDTO);
            }
            Response<List<RaffleAwardListResponseDTO>> response = Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(result)
                    .build();
            log.info("查询抽奖奖品列表配置完成 userId:{} activityId:{} response: {}", requestDTO.getUserId(), requestDTO.getActivityId(), JSON.toJSONString(response));
            // 返回结果
            return response;
        }catch (Throwable ex){
            log.error("查询抽奖奖品列表配置失败 userId:{} activityId:{}", requestDTO.getUserId(), requestDTO.getActivityId(), ex);
            return Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * 随机抽奖接口
     * <a href="http://localhost:8091/api/v1/raffle/random_raffle">/api/v1/raffle/random_raffle</a>
     *
     * @param requestDTO 请求参数 {"strategyId":1000001}
     * @return 抽奖结果
     */
    @PostMapping("/random_raffle")
    @Override
    public Response<RaffleStrategyResponseDTO> randomRaffle(@RequestBody RaffleStrategyRequestDTO requestDTO) {
        try {
            log.info("随机抽奖开始 strategyId: {}", requestDTO.getStrategyId());
            // 调用抽奖接口
            RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                    .userId("system")
                    .strategyId(requestDTO.getStrategyId())
                    .build();
            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);
            RaffleStrategyResponseDTO responseDTO = RaffleStrategyResponseDTO.builder()
                    .awardId(raffleAwardEntity.getAwardId())
                    .awardIndex(raffleAwardEntity.getSort())
                    .build();
            Response<RaffleStrategyResponseDTO> response = Response.<RaffleStrategyResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(responseDTO)
                    .build();
            log.info("随机抽奖完成 strategyId: {} response: {}", requestDTO.getStrategyId(), JSON.toJSONString(response));
            return response;
        }catch (Throwable ex){
            log.error("随机抽奖失败 strategyId：{}", requestDTO.getStrategyId(), ex);
            return Response.<RaffleStrategyResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
