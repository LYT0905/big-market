package com.big.market.infrastructure.domain.award.model.aggregate;

import com.big.market.infrastructure.domain.award.model.entity.TaskEntity;
import com.big.market.infrastructure.domain.award.model.entity.UserAwardRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 莱特0905
 * @Description: 用户中奖记录聚合对象(聚合代表一个事务操作)
 * @Date: 2024/08/22 14:46:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAwardRecordAggregate {
    /** 用户中奖记录实体对象 */
    private UserAwardRecordEntity userAwardRecordEntity;
    /** 任务实体 */
    private TaskEntity taskEntity;
}
