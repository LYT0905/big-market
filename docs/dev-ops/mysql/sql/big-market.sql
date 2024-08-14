-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: 8.138.41.137    Database: big_market
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `award`
--

DROP TABLE IF EXISTS `award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `award` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `award_id` int NOT NULL COMMENT '抽奖奖品ID - 内部流转使用',
  `award_key` varchar(32) NOT NULL COMMENT '奖品对接标识 - 每一个都是一个对应的发奖策略',
  `award_config` varchar(32) NOT NULL COMMENT '奖品配置信息',
  `award_desc` varchar(128) NOT NULL COMMENT '奖品内容描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `award`
--

LOCK TABLES `award` WRITE;
/*!40000 ALTER TABLE `award` DISABLE KEYS */;
INSERT INTO `award` VALUES (1,101,'user_credit_random','1,100','用户积分【优先透彻规则范围，如果没有则走配置】','2023-12-09 11:07:06','2023-12-09 11:21:31'),(2,102,'openai_use_count','5','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:59'),(3,103,'openai_use_count','10','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:59'),(4,104,'openai_use_count','20','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:58'),(5,105,'openai_model','gpt-4','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:01'),(6,106,'openai_model','dall-e-2','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:08'),(7,107,'openai_model','dall-e-3','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:10'),(8,108,'openai_use_count','100','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:12:55'),(9,109,'openai_model','gpt-4,dall-e-2,dall-e-3','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:12:39');
/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity`
--

DROP TABLE IF EXISTS `raffle_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
  `activity_desc` varchar(128) NOT NULL COMMENT '活动描述',
  `begin_date_time` datetime NOT NULL COMMENT '开始时间',
  `end_date_time` datetime NOT NULL COMMENT '结束时间',
  `stock_count` int NOT NULL COMMENT '库存总量',
  `stock_count_surplus` int NOT NULL COMMENT '剩余库存',
  `activity_count_id` bigint NOT NULL COMMENT '活动参与次数配置',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `state` varchar(8) NOT NULL COMMENT '活动状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_activity_id` (`activity_id`),
  KEY `idx_begin_date_time` (`begin_date_time`),
  KEY `idx_end_date_time` (`end_date_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity`
--

LOCK TABLES `raffle_activity` WRITE;
/*!40000 ALTER TABLE `raffle_activity` DISABLE KEYS */;
INSERT INTO `raffle_activity` VALUES (2,101,'测试','Test','2024-08-13 20:22:03','2024-08-16 20:22:05',10,10,101,100004,'create','2024-08-13 20:22:29','2024-08-13 20:22:29');
/*!40000 ALTER TABLE `raffle_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_0`
--

DROP TABLE IF EXISTS `raffle_activity_account_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_0` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `total_count` int NOT NULL COMMENT '总次数',
  `total_count_surplus` int NOT NULL COMMENT '总次数-剩余',
  `day_count` int NOT NULL COMMENT '日次数',
  `day_count_surplus` int NOT NULL COMMENT '日次数-剩余',
  `month_count` int NOT NULL COMMENT '月次数',
  `month_count_surplus` int NOT NULL COMMENT '月次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id` (`user_id`,`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_0`
--

LOCK TABLES `raffle_activity_account_0` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_1`
--

DROP TABLE IF EXISTS `raffle_activity_account_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_1` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `total_count` int NOT NULL COMMENT '总次数',
  `total_count_surplus` int NOT NULL COMMENT '总次数-剩余',
  `day_count` int NOT NULL COMMENT '日次数',
  `day_count_surplus` int NOT NULL COMMENT '日次数-剩余',
  `month_count` int NOT NULL COMMENT '月次数',
  `month_count_surplus` int NOT NULL COMMENT '月次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id` (`user_id`,`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_1`
--

LOCK TABLES `raffle_activity_account_1` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_2`
--

DROP TABLE IF EXISTS `raffle_activity_account_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_2` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `total_count` int NOT NULL COMMENT '总次数',
  `total_count_surplus` int NOT NULL COMMENT '总次数-剩余',
  `day_count` int NOT NULL COMMENT '日次数',
  `day_count_surplus` int NOT NULL COMMENT '日次数-剩余',
  `month_count` int NOT NULL COMMENT '月次数',
  `month_count_surplus` int NOT NULL COMMENT '月次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id` (`user_id`,`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_2`
--

LOCK TABLES `raffle_activity_account_2` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_count`
--

DROP TABLE IF EXISTS `raffle_activity_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_count` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_count_id` bigint NOT NULL COMMENT '活动次数编号',
  `total_count` int NOT NULL COMMENT '总次数',
  `day_count` int NOT NULL COMMENT '日次数',
  `month_count` int NOT NULL COMMENT '月次数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_activity_count_id` (`activity_count_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动次数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_count`
--

LOCK TABLES `raffle_activity_count` WRITE;
/*!40000 ALTER TABLE `raffle_activity_count` DISABLE KEYS */;
INSERT INTO `raffle_activity_count` VALUES (2,101,10,10,10,'2024-08-13 20:22:58','2024-08-13 20:22:58');
/*!40000 ALTER TABLE `raffle_activity_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_order_0`
--

DROP TABLE IF EXISTS `raffle_activity_order_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_order_0` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `sku` bigint NOT NULL COMMENT '商品sku',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '订单ID',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `total_count` int NOT NULL COMMENT '总次数',
  `day_count` int NOT NULL COMMENT '日次数',
  `month_count` int NOT NULL COMMENT '月次数',
  `state` varchar(8) NOT NULL DEFAULT 'complete' COMMENT '订单状态（complete）',
  `out_business_no` varchar(64) DEFAULT NULL COMMENT '业务防重ID-外部透传的，确保幂等',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  UNIQUE KEY `uniq_obn_key` (`out_business_no`),
  KEY `idx_user_id_activity_id` (`user_id`,`activity_id`,`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_order_0`
--

LOCK TABLES `raffle_activity_order_0` WRITE;
/*!40000 ALTER TABLE `raffle_activity_order_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_order_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_order_1`
--

DROP TABLE IF EXISTS `raffle_activity_order_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_order_1` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `sku` bigint NOT NULL COMMENT '商品sku',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '订单ID',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `total_count` int NOT NULL COMMENT '总次数',
  `day_count` int NOT NULL COMMENT '日次数',
  `month_count` int NOT NULL COMMENT '月次数',
  `state` varchar(8) NOT NULL DEFAULT 'complete' COMMENT '订单状态（complete）',
  `out_business_no` varchar(64) DEFAULT NULL COMMENT '业务防重ID-外部透传的，确保幂等',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  UNIQUE KEY `uniq_obn_key` (`out_business_no`),
  KEY `idx_user_id_activity_id` (`user_id`,`activity_id`,`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_order_1`
--

LOCK TABLES `raffle_activity_order_1` WRITE;
/*!40000 ALTER TABLE `raffle_activity_order_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_order_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_order_2`
--

DROP TABLE IF EXISTS `raffle_activity_order_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_order_2` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `sku` bigint NOT NULL COMMENT '商品sku',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '订单ID',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `total_count` int NOT NULL COMMENT '总次数',
  `day_count` int NOT NULL COMMENT '日次数',
  `month_count` int NOT NULL COMMENT '月次数',
  `state` varchar(8) NOT NULL DEFAULT 'complete' COMMENT '订单状态（complete）',
  `out_business_no` varchar(64) DEFAULT NULL COMMENT '业务防重ID-外部透传的，确保幂等',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  UNIQUE KEY `uniq_obn_key` (`out_business_no`),
  KEY `idx_user_id_activity_id` (`user_id`,`activity_id`,`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_order_2`
--

LOCK TABLES `raffle_activity_order_2` WRITE;
/*!40000 ALTER TABLE `raffle_activity_order_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_order_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_sku`
--

DROP TABLE IF EXISTS `raffle_activity_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_sku` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `sku` bigint NOT NULL COMMENT '商品sku - 把每一个组合当做一个商品',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_count_id` bigint NOT NULL COMMENT '活动个人参与次数ID',
  `stock_count` int NOT NULL COMMENT '商品库存',
  `stock_count_surplus` int NOT NULL COMMENT '剩余库存',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_sku` (`sku`),
  KEY `idx_activity_id_activity_count_id` (`activity_id`,`activity_count_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_sku`
--

LOCK TABLES `raffle_activity_sku` WRITE;
/*!40000 ALTER TABLE `raffle_activity_sku` DISABLE KEYS */;
INSERT INTO `raffle_activity_sku` VALUES (1,9011,101,101,10,10,'2024-08-13 20:21:34','2024-08-13 20:21:34');
/*!40000 ALTER TABLE `raffle_activity_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule_tree`
--

DROP TABLE IF EXISTS `rule_tree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rule_tree` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tree_id` varchar(32) NOT NULL COMMENT '规则树ID',
  `tree_name` varchar(64) NOT NULL COMMENT '规则树名称',
  `tree_desc` varchar(128) DEFAULT NULL COMMENT '规则树描述',
  `tree_root_rule_key` varchar(32) NOT NULL COMMENT '规则树根入口规则',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_tree_id` (`tree_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_tree`
--

LOCK TABLES `rule_tree` WRITE;
/*!40000 ALTER TABLE `rule_tree` DISABLE KEYS */;
INSERT INTO `rule_tree` VALUES (1,'tree_lock','规则树','规则树','rule_lock','2024-01-27 10:01:59','2024-02-03 10:39:54');
/*!40000 ALTER TABLE `rule_tree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule_tree_node`
--

DROP TABLE IF EXISTS `rule_tree_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rule_tree_node` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tree_id` varchar(32) NOT NULL COMMENT '规则树ID',
  `rule_key` varchar(32) NOT NULL COMMENT '规则Key',
  `rule_desc` varchar(64) NOT NULL COMMENT '规则描述',
  `rule_value` varchar(128) DEFAULT NULL COMMENT '规则比值',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_tree_node`
--

LOCK TABLES `rule_tree_node` WRITE;
/*!40000 ALTER TABLE `rule_tree_node` DISABLE KEYS */;
INSERT INTO `rule_tree_node` VALUES (1,'tree_lock','rule_lock','限定用户已完成N次抽奖后解锁','1','2024-01-27 10:03:09','2024-02-03 10:40:18'),(2,'tree_lock','rule_luck_award','兜底奖品随机积分','100:1,100','2024-01-27 10:03:09','2024-07-31 23:58:52'),(3,'tree_lock','rule_stock','库存扣减规则',NULL,'2024-01-27 10:04:43','2024-02-03 10:40:21');
/*!40000 ALTER TABLE `rule_tree_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule_tree_node_line`
--

DROP TABLE IF EXISTS `rule_tree_node_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rule_tree_node_line` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tree_id` varchar(32) NOT NULL COMMENT '规则树ID',
  `rule_node_from` varchar(32) NOT NULL COMMENT '规则Key节点 From',
  `rule_node_to` varchar(32) NOT NULL COMMENT '规则Key节点 To',
  `rule_limit_type` varchar(8) NOT NULL COMMENT '限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];',
  `rule_limit_value` varchar(32) NOT NULL COMMENT '限定值（到下个节点）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_tree_node_line`
--

LOCK TABLES `rule_tree_node_line` WRITE;
/*!40000 ALTER TABLE `rule_tree_node_line` DISABLE KEYS */;
INSERT INTO `rule_tree_node_line` VALUES (1,'tree_lock','rule_lock','rule_stock','EQUAL','ALLOW','2024-07-31 23:58:23','2024-07-31 23:58:23'),(2,'tree_lock','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2024-07-31 23:58:23','2024-07-31 23:58:23'),(3,'tree_lock','rule_stock','rule_luck_award','EQUAL','TAKE_OVER','2024-07-31 23:58:23','2024-07-31 23:58:23');
/*!40000 ALTER TABLE `rule_tree_node_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy`
--

DROP TABLE IF EXISTS `strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `strategy` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `strategy_desc` varchar(128) NOT NULL COMMENT '抽奖策略描述',
  `rule_models` varchar(255) DEFAULT NULL COMMENT '规则模型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_strategy_id` (`strategy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy`
--

LOCK TABLES `strategy` WRITE;
/*!40000 ALTER TABLE `strategy` DISABLE KEYS */;
INSERT INTO `strategy` VALUES (1,100001,'抽奖策略','rule_blacklist,rule_weight','2024-07-31 23:57:42','2024-08-04 20:21:06'),(2,100003,'抽奖策略-验证lock',NULL,'2024-07-31 23:57:42','2024-07-31 23:57:42'),(3,100002,'抽奖策略-非完整1概率',NULL,'2024-07-31 23:57:42','2024-07-31 23:57:42'),(4,100004,'策略树测试',NULL,'2024-07-31 23:59:26','2024-07-31 23:59:26');
/*!40000 ALTER TABLE `strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy_award`
--

DROP TABLE IF EXISTS `strategy_award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `strategy_award` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `award_id` bigint NOT NULL COMMENT '抽奖奖品ID - 内部流转使用',
  `award_title` varchar(128) NOT NULL COMMENT '抽奖奖品标题',
  `award_subtitle` varchar(128) DEFAULT NULL COMMENT '抽奖奖品副标题',
  `award_count` int NOT NULL DEFAULT '0' COMMENT '奖品库存总量',
  `award_count_surplus` int NOT NULL DEFAULT '0' COMMENT '奖品库存剩余',
  `award_rate` decimal(6,2) NOT NULL COMMENT '奖品中奖概率',
  `rule_models` varchar(256) DEFAULT NULL COMMENT '规则模型，rule配置的模型同步到此表，便于使用',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_strategy_id_award_id` (`strategy_id`,`award_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy_award`
--

LOCK TABLES `strategy_award` WRITE;
/*!40000 ALTER TABLE `strategy_award` DISABLE KEYS */;
INSERT INTO `strategy_award` VALUES (1,100001,101,'随机积分',NULL,80000,80000,80.00,'rule_random,rule_luck_award',1,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(2,100001,102,'5次使用',NULL,10000,10000,10.00,'rule_luck_award',2,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(3,100001,103,'10次使用',NULL,5000,5000,5.00,'rule_luck_award',3,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(4,100001,104,'20次使用',NULL,4000,4000,4.00,'rule_luck_award',4,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(5,100001,105,'增加gpt-4对话模型',NULL,600,600,0.60,'rule_luck_award',5,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(6,100001,106,'增加dall-e-2画图模型',NULL,200,200,0.20,'rule_luck_award',6,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(7,100001,107,'增加dall-e-3画图模型','抽奖1次后解锁',200,200,0.20,'rule_lock,rule_luck_award',7,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(8,100001,108,'增加100次使用','抽奖2次后解锁',199,199,0.20,'rule_lock,rule_luck_award',8,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(9,100001,109,'解锁全部模型','抽奖6次后解锁',1,1,0.10,'rule_lock,rule_luck_award',9,'2024-07-31 23:57:44','2024-08-04 20:33:36'),(10,100002,101,'随机积分',NULL,1,1,0.50,'rule_random,rule_luck_award',1,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(11,100002,102,'5次使用',NULL,1,1,0.10,'rule_random,rule_luck_award',2,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(12,100002,106,'增加dall-e-2画图模型',NULL,1,1,0.10,'rule_random,rule_luck_award',3,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(13,100003,107,'增加dall-e-3画图模型','抽奖1次后解锁',200,200,0.04,'rule_lock,rule_luck_award',7,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(14,100003,108,'增加100次使用','抽奖2次后解锁',199,199,0.09,'rule_lock,rule_luck_award',8,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(15,100003,109,'解锁全部模型','抽奖6次后解锁',1,1,0.01,'rule_lock,rule_luck_award',9,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(16,100004,100,'随机积分',NULL,100000,99898,0.07,'tree_lock',1,'2024-08-01 00:02:16','2024-08-09 15:17:10'),(17,100004,101,'10000积分',NULL,100000,99930,0.01,'tree_lock',2,'2024-08-01 00:04:42','2024-08-10 16:29:05'),(18,100004,102,'公仔一只',NULL,400,391,0.04,'tree_lock',3,'2024-08-01 14:09:58','2024-08-09 15:17:15'),(19,100004,103,'台灯一个',NULL,200,192,0.03,'tree_lock',4,'2024-08-01 14:12:47','2024-08-05 19:57:45'),(20,100004,104,'增加5次抽奖机会',NULL,100,89,0.02,'tree_lock',5,'2024-08-01 14:13:48','2024-08-05 19:59:20');
/*!40000 ALTER TABLE `strategy_award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy_rule`
--

DROP TABLE IF EXISTS `strategy_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `strategy_rule` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `award_id` bigint DEFAULT NULL COMMENT '抽奖奖品ID【规则类型为策略，则不需要奖品ID】',
  `rule_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '抽象规则类型；1-策略规则、2-奖品规则',
  `rule_model` varchar(16) NOT NULL COMMENT '抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】',
  `rule_value` varchar(256) NOT NULL COMMENT '抽奖规则比值',
  `rule_desc` varchar(128) NOT NULL COMMENT '抽奖规则描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_strategy_id_award_id` (`strategy_id`,`award_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy_rule`
--

LOCK TABLES `strategy_rule` WRITE;
/*!40000 ALTER TABLE `strategy_rule` DISABLE KEYS */;
INSERT INTO `strategy_rule` VALUES (1,100001,101,2,'rule_random','1,1000','随机积分策略','2024-07-31 23:57:46','2024-07-31 23:57:46'),(2,100001,107,2,'rule_lock','1','抽奖1次后解锁','2024-07-31 23:57:46','2024-07-31 23:57:46'),(3,100001,108,2,'rule_lock','2','抽奖2次后解锁','2024-07-31 23:57:46','2024-07-31 23:57:46'),(4,100001,109,2,'rule_lock','6','抽奖6次后解锁','2024-07-31 23:57:46','2024-07-31 23:57:46'),(5,100001,107,2,'rule_luck_award','1,100','兜底奖品100以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(6,100001,108,2,'rule_luck_award','1,100','兜底奖品100以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(7,100001,101,2,'rule_luck_award','1,10','兜底奖品10以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(8,100001,102,2,'rule_luck_award','1,20','兜底奖品20以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(9,100001,103,2,'rule_luck_award','1,30','兜底奖品30以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(10,100001,104,2,'rule_luck_award','1,40','兜底奖品40以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(11,100001,105,2,'rule_luck_award','1,50','兜底奖品50以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(12,100001,106,2,'rule_luck_award','1,60','兜底奖品60以内随机积分','2024-07-31 23:57:46','2024-07-31 23:57:46'),(13,100001,NULL,1,'rule_weight','3000:102,103,104 4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109','消耗6000分，必中奖范围','2024-07-31 23:57:46','2024-08-04 20:57:55'),(14,100001,NULL,1,'rule_blacklist','100:user01,user02,user03','黑名单抽奖，积分兜底','2024-07-31 23:57:46','2024-08-04 20:25:33'),(15,100003,107,2,'rule_lock','1','抽奖1次后解锁','2024-07-31 23:57:46','2024-07-31 23:57:46'),(16,100003,108,2,'rule_lock','2','抽奖2次后解锁','2024-07-31 23:57:46','2024-07-31 23:57:46'),(17,100003,109,2,'rule_lock','6','抽奖6次后解锁','2024-07-31 23:57:46','2024-07-31 23:57:46');
/*!40000 ALTER TABLE `strategy_rule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-14 20:38:11
