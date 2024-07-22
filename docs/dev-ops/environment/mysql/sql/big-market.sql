create table strategy(
                         id bigint(11) unsigned not null auto_increment comment '自增ID',
                         strategy_id bigint(8) not null comment '抽奖策略ID',
                         strategy_desc varchar(128) not null comment '抽奖策略描述',
                         rule_models varchar(255) default null comment '规则模型',
                         create_time datetime not null default CURRENT_TIMESTAMP comment '创建时间',
                         update_time datetime not null default CURRENT_TIMESTAMP on update current_timestamp comment '更新时间',
                         primary key (id),
                         KEY `idx_strategy_id` (`strategy_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
insert into strategy
(id, strategy_id, strategy_desc) values
    (null, 100001, '抽奖策略');


INSERT INTO `strategy` (`id`, `strategy_id`, `strategy_desc`, `rule_models`)
VALUES
    (2,100003,'抽奖策略-验证lock',NULL),
    (3,100002,'抽奖策略-非完整1概率',NULL);

create table strategy_award(
                               id bigint(11) unsigned not null auto_increment comment '自增ID',
                               strategy_id bigint(8) not null comment '抽奖策略ID',
                               award_id bigint(8) not null comment '抽奖奖品ID - 内部流转使用',
                               award_title varchar(128) not null comment '抽奖奖品标题',
                               award_subtitle varchar(128) default null comment '抽奖奖品副标题',
                               award_count int(8) not null default '0' comment '奖品库存总量',
                               award_count_surplus int(8) not null default '0' comment '奖品库存剩余',
                               award_rate decimal(6, 2) not null comment '奖品中奖概率',
                               rule_models varchar(256) default null comment '规则模型，rule配置的模型同步到此表，便于使用',
                               `sort` int(2) NOT NULL DEFAULT '0' COMMENT '排序',
                               create_time datetime not null default CURRENT_TIMESTAMP comment '创建时间',
                               update_time datetime not null default CURRENT_TIMESTAMP on update current_timestamp comment '更新时间',
                               primary key (id),
                               key idx_strategy_id_award_id(strategy_id, award_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `strategy_award` DISABLE KEYS */;

INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `rule_models`, `sort`)
VALUES
    (1,100001,101,'随机积分',NULL,80000,80000,80.0000,'rule_random,rule_luck_award',1),
    (2,100001,102,'5次使用',NULL,10000,10000,10.0000,'rule_luck_award',2),
    (3,100001,103,'10次使用',NULL,5000,5000,5.0000,'rule_luck_award',3),
    (4,100001,104,'20次使用',NULL,4000,4000,4.0000,'rule_luck_award',4),
    (5,100001,105,'增加gpt-4对话模型',NULL,600,600,0.6000,'rule_luck_award',5),
    (6,100001,106,'增加dall-e-2画图模型',NULL,200,200,0.2000,'rule_luck_award',6),
    (7,100001,107,'增加dall-e-3画图模型','抽奖1次后解锁',200,200,0.2000,'rule_lock,rule_luck_award',7),
    (8,100001,108,'增加100次使用','抽奖2次后解锁',199,199,0.1999,'rule_lock,rule_luck_award',8),
    (9,100001,109,'解锁全部模型','抽奖6次后解锁',1,1,0.0001,'rule_lock,rule_luck_award',9);

INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `rule_models`, `sort`)
VALUES
    (10,100002,101,'随机积分',NULL,1,1,0.5000,'rule_random,rule_luck_award',1),
    (11,100002,102,'5次使用',NULL,1,1,0.1000,'rule_random,rule_luck_award',2),
    (12,100002,106,'增加dall-e-2画图模型',NULL,1,1,0.10,'rule_random,rule_luck_award',3),
    (13,100003,107,'增加dall-e-3画图模型','抽奖1次后解锁',200,200,0.04,'rule_lock,rule_luck_award',7),
    (14,100003,108,'增加100次使用','抽奖2次后解锁',199,199,0.09,'rule_lock,rule_luck_award',8),
    (15,100003,109,'解锁全部模型','抽奖6次后解锁',1,1,0.01,'rule_lock,rule_luck_award',9);

create table strategy_rule(
                              id bigint(11) unsigned not null auto_increment comment '自增ID',
                              strategy_id bigint(8) not null comment '抽奖策略ID',
                              award_id bigint(8) default null comment '抽奖奖品ID【规则类型为策略，则不需要奖品ID】',
                              `rule_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '抽象规则类型；1-策略规则、2-奖品规则',
                              `rule_model` varchar(16) NOT NULL COMMENT '抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】',
                              rule_value varchar(64) not null comment '抽奖规则比值',
                              rule_desc varchar(128) not null comment '抽奖规则描述',
                              create_time datetime not null default CURRENT_TIMESTAMP comment '创建时间',
                              update_time datetime not null default CURRENT_TIMESTAMP on update current_timestamp comment '更新时间',
                              primary key (id),
                              key idx_strategy_id_award_id(strategy_id, award_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40000 ALTER TABLE `strategy_rule` DISABLE KEYS */;

INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`)
VALUES
    (1,100001,101,2,'rule_random','1,1000','随机积分策略'),
    (2,100001,107,2,'rule_lock','1','抽奖1次后解锁'),
    (3,100001,108,2,'rule_lock','2','抽奖2次后解锁'),
    (4,100001,109,2,'rule_lock','6','抽奖6次后解锁'),
    (5,100001,107,2,'rule_luck_award','1,100','兜底奖品100以内随机积分'),
    (6,100001,108,2,'rule_luck_award','1,100','兜底奖品100以内随机积分'),
    (7,100001,101,2,'rule_luck_award','1,10','兜底奖品10以内随机积分'),
    (8,100001,102,2,'rule_luck_award','1,20','兜底奖品20以内随机积分'),
    (9,100001,103,2,'rule_luck_award','1,30','兜底奖品30以内随机积分'),
    (10,100001,104,2,'rule_luck_award','1,40','兜底奖品40以内随机积分'),
    (11,100001,105,2,'rule_luck_award','1,50','兜底奖品50以内随机积分'),
    (12,100001,106,2,'rule_luck_award','1,60','兜底奖品60以内随机积分'),
    (13,100001,NULL,1,'rule_weight','6000,102,103,104,105,106,107,108,109','消耗6000分，必中奖范围'),
    (14,100001,NULL,1,'rule_blacklist','1','黑名单抽奖，积分兜底');


INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`)
VALUES
    (15,100003,107,2,'rule_lock','1','抽奖1次后解锁'),
    (16,100003,108,2,'rule_lock','2','抽奖2次后解锁'),
    (17,100003,109,2,'rule_lock','6','抽奖6次后解锁');

CREATE TABLE `award` (
                         `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                         `award_id` int(8) NOT NULL COMMENT '抽奖奖品ID - 内部流转使用',
                         `award_key` varchar(32) NOT NULL COMMENT '奖品对接标识 - 每一个都是一个对应的发奖策略',
                         `award_config` varchar(32) NOT NULL COMMENT '奖品配置信息',
                         `award_desc` varchar(128) NOT NULL COMMENT '奖品内容描述',
                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


/*!40000 ALTER TABLE `award` DISABLE KEYS */;

INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`)
VALUES
    (1,101,'user_credit_random','1,100','用户积分【优先透彻规则范围，如果没有则走配置】','2023-12-09 11:07:06','2023-12-09 11:21:31'),
    (2,102,'openai_use_count','5','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:59'),
    (3,103,'openai_use_count','10','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:59'),
    (4,104,'openai_use_count','20','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:58'),
    (5,105,'openai_model','gpt-4','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:01'),
    (6,106,'openai_model','dall-e-2','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:08'),
    (7,107,'openai_model','dall-e-3','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:10'),
    (8,108,'openai_use_count','100','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:55'),
    (9,109,'openai_model','gpt-4,dall-e-2,dall-e-3','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:39');


