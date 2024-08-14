package com.big.market.infrastructure.types.utils;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 莱特0905
 * @Description: 生成数字的雪花算法工具类
 * @Date: 2024/08/14 22:41:28
 */
@Slf4j
public class SnowflakeUUIDUtils {

    public static String generateId(Integer number){
        // 初始化雪花算法生成器
        long workerId = 1;  // 机器ID
        long datacenterId = 1;  // 数据中心ID
        long snowflakeId = IdUtil.getSnowflake(workerId, datacenterId).nextId();

        // 转换成字符串并截取前12位
        String uniqueID = String.valueOf(snowflakeId).substring(0, number);

        log.info("{}位数字的UUID:{}", number, uniqueID);
        return uniqueID;
    }
}
