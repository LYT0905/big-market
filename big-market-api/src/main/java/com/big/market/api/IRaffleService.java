package com.big.market.api;

import com.big.market.api.dto.RaffleAwardListRequestDTO;
import com.big.market.api.dto.RaffleAwardListResponseDTO;
import com.big.market.api.dto.RaffleRequestDTO;
import com.big.market.api.dto.RaffleResponseDTO;
import com.big.market.infrastructure.types.model.Response;


import java.util.List;

/**
 * @author 莱特0905
 * @Description: 抽奖接口
 * @Date: 2024/07/27 20:49:49
 */
public interface IRaffleService {

    /**
     * 策略装配接口
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);


    /**
     * 查询抽奖奖品列表配置
     *
     * @param requestDTO 抽奖奖品列表查询请求参数
     * @return 奖品列表数据
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    /**
     * 随机抽奖接口
     *
     * @param requestDTO 请求参数
     * @return 抽奖结果
     */
    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO requestDTO);



}
