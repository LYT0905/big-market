package com.big.market.infrastructure.infrastructure.persistent.dao;

import com.big.market.infrastructure.infrastructure.persistent.po.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莱特0905
 * @Description: 任务表，发送MQ Dao
 * @Date: 2024/08/18 13:58:43
 */
@Mapper
public interface ITaskDao {
    List<Task> queryAllTask();
}
