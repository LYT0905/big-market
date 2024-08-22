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
INSERT INTO `raffle_activity` VALUES (2,101,'测试','Test','2024-08-13 20:22:03','2024-09-16 20:22:05',20,20,101,100004,'open','2024-08-13 20:22:29','2024-08-17 21:00:29');
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
-- Table structure for table `raffle_activity_account_day_0`
--

DROP TABLE IF EXISTS `raffle_activity_account_day_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_day_0` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `day` varchar(10) NOT NULL COMMENT '日期（yyyy-mm-dd）',
  `day_count` int NOT NULL COMMENT '日次数',
  `day_count_surplus` int NOT NULL COMMENT '日次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id_day` (`user_id`,`activity_id`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表-日次数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_day_0`
--

LOCK TABLES `raffle_activity_account_day_0` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_day_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_day_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_day_1`
--

DROP TABLE IF EXISTS `raffle_activity_account_day_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_day_1` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `day` varchar(10) NOT NULL COMMENT '日期（yyyy-mm-dd）',
  `day_count` int NOT NULL COMMENT '日次数',
  `day_count_surplus` int NOT NULL COMMENT '日次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id_day` (`user_id`,`activity_id`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表-日次数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_day_1`
--

LOCK TABLES `raffle_activity_account_day_1` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_day_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_day_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_day_2`
--

DROP TABLE IF EXISTS `raffle_activity_account_day_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_day_2` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `day` varchar(10) NOT NULL COMMENT '日期（yyyy-mm-dd）',
  `day_count` int NOT NULL COMMENT '日次数',
  `day_count_surplus` int NOT NULL COMMENT '日次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id_day` (`user_id`,`activity_id`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表-日次数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_day_2`
--

LOCK TABLES `raffle_activity_account_day_2` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_day_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_day_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_month_0`
--

DROP TABLE IF EXISTS `raffle_activity_account_month_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_month_0` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `month` varchar(7) NOT NULL COMMENT '月（yyyy-mm）',
  `month_count` int NOT NULL COMMENT '月次数',
  `month_count_surplus` int NOT NULL COMMENT '月次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id_month` (`user_id`,`activity_id`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表-月次数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_month_0`
--

LOCK TABLES `raffle_activity_account_month_0` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_month_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_month_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_month_1`
--

DROP TABLE IF EXISTS `raffle_activity_account_month_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_month_1` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `month` varchar(7) NOT NULL COMMENT '月（yyyy-mm）',
  `month_count` int NOT NULL COMMENT '月次数',
  `month_count_surplus` int NOT NULL COMMENT '月次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id_month` (`user_id`,`activity_id`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表-月次数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_month_1`
--

LOCK TABLES `raffle_activity_account_month_1` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_month_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_month_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle_activity_account_month_2`
--

DROP TABLE IF EXISTS `raffle_activity_account_month_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle_activity_account_month_2` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `month` varchar(7) NOT NULL COMMENT '月（yyyy-mm）',
  `month_count` int NOT NULL COMMENT '月次数',
  `month_count_surplus` int NOT NULL COMMENT '月次数-剩余',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_id_activity_id_month` (`user_id`,`activity_id`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表-月次数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_month_2`
--

LOCK TABLES `raffle_activity_account_month_2` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_month_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `raffle_activity_account_month_2` ENABLE KEYS */;
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
  `state` varchar(12) NOT NULL DEFAULT 'complete' COMMENT '订单状态（complete）',
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
  `state` varchar(12) NOT NULL DEFAULT 'complete' COMMENT '订单状态（complete）',
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
  `state` varchar(12) NOT NULL DEFAULT 'complete' COMMENT '订单状态（complete）',
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
INSERT INTO `raffle_activity_sku` VALUES (1,9011,101,101,20,20,'2024-08-13 20:21:34','2024-08-17 21:12:05');
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
INSERT INTO `rule_tree_node_line` VALUES (1,'tree_lock','rule_lock','rule_stock','EQUAL','ALLOW','2024-07-31 23:58:23','2024-07-31 23:58:23'),(2,'tree_lock','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2024-07-31 23:58:23','2024-07-31 23:58:23'),(3,'tree_lock','rule_stock','rule_luck_award','EQUAL','ALLOW','2024-07-31 23:58:23','2024-08-17 14:20:36');
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
INSERT INTO `strategy_award` VALUES (1,100001,101,'随机积分',NULL,80000,80000,80.00,'rule_random,rule_luck_award',1,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(2,100001,102,'5次使用',NULL,10000,10000,10.00,'rule_luck_award',2,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(3,100001,103,'10次使用',NULL,5000,5000,5.00,'rule_luck_award',3,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(4,100001,104,'20次使用',NULL,4000,4000,4.00,'rule_luck_award',4,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(5,100001,105,'增加gpt-4对话模型',NULL,600,600,0.60,'rule_luck_award',5,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(6,100001,106,'增加dall-e-2画图模型',NULL,200,200,0.20,'rule_luck_award',6,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(7,100001,107,'增加dall-e-3画图模型','抽奖1次后解锁',200,200,0.20,'rule_lock,rule_luck_award',7,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(8,100001,108,'增加100次使用','抽奖2次后解锁',199,199,0.20,'rule_lock,rule_luck_award',8,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(9,100001,109,'解锁全部模型','抽奖6次后解锁',1,1,0.10,'rule_lock,rule_luck_award',9,'2024-07-31 23:57:44','2024-08-04 20:33:36'),(10,100002,101,'随机积分',NULL,1,1,0.50,'rule_random,rule_luck_award',1,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(11,100002,102,'5次使用',NULL,1,1,0.10,'rule_random,rule_luck_award',2,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(12,100002,106,'增加dall-e-2画图模型',NULL,1,1,0.10,'rule_random,rule_luck_award',3,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(13,100003,107,'增加dall-e-3画图模型','抽奖1次后解锁',200,200,0.04,'rule_lock,rule_luck_award',7,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(14,100003,108,'增加100次使用','抽奖2次后解锁',199,199,0.09,'rule_lock,rule_luck_award',8,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(15,100003,109,'解锁全部模型','抽奖6次后解锁',1,1,0.01,'rule_lock,rule_luck_award',9,'2024-07-31 23:57:44','2024-07-31 23:57:44'),(16,100004,100,'随机积分',NULL,100000,99896,0.07,'tree_lock',1,'2024-08-01 00:02:16','2024-08-17 19:19:20'),(17,100004,101,'10000积分',NULL,100000,99926,0.01,'tree_lock',2,'2024-08-01 00:04:42','2024-08-17 14:21:00'),(18,100004,102,'公仔一只',NULL,400,389,0.04,'tree_lock',3,'2024-08-01 14:09:58','2024-08-17 14:21:40'),(19,100004,103,'台灯一个',NULL,200,192,0.03,'tree_lock',4,'2024-08-01 14:12:47','2024-08-05 19:57:45'),(20,100004,104,'增加5次抽奖机会',NULL,100,88,0.02,'tree_lock',5,'2024-08-01 14:13:48','2024-08-17 14:22:10');
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

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `topic` varchar(32) NOT NULL COMMENT '消息主题',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `message` varchar(512) NOT NULL COMMENT '消息主体',
  `message_id` varchar(11) DEFAULT NULL COMMENT '消息编号',
  `state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '任务状态；create-创建、completed-完成、fail-失败',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1033419035968516098 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务表，发送MQ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1033417240110149633,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265375783\",\"timeStamp\":\"2024-08-22 16:31:08.764\"}','18265375783','completed','2024-08-22 16:31:10','2024-08-22 16:31:11'),(1033417245705351169,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265375883\",\"timeStamp\":\"2024-08-22 16:31:11.14\"}','18265375883','completed','2024-08-22 16:31:12','2024-08-22 16:31:12'),(1033417250528800769,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265375931\",\"timeStamp\":\"2024-08-22 16:31:12.294\"}','18265375931','completed','2024-08-22 16:31:13','2024-08-22 16:31:13'),(1033417255226421249,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265375979\",\"timeStamp\":\"2024-08-22 16:31:13.437\"}','18265375979','completed','2024-08-22 16:31:14','2024-08-22 16:31:14'),(1033417259911458817,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376027\",\"timeStamp\":\"2024-08-22 16:31:14.569\"}','18265376027','completed','2024-08-22 16:31:15','2024-08-22 16:31:15'),(1033417264571330561,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376073\",\"timeStamp\":\"2024-08-22 16:31:15.674\"}','18265376073','completed','2024-08-22 16:31:16','2024-08-22 16:31:16'),(1033417269394780161,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376120\",\"timeStamp\":\"2024-08-22 16:31:16.797\"}','18265376120','completed','2024-08-22 16:31:17','2024-08-22 16:31:18'),(1033417274125955073,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376167\",\"timeStamp\":\"2024-08-22 16:31:17.918\"}','18265376167','completed','2024-08-22 16:31:19','2024-08-22 16:31:19'),(1033417279754711041,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376222\",\"timeStamp\":\"2024-08-22 16:31:19.224\"}','18265376222','completed','2024-08-22 16:31:20','2024-08-22 16:31:20'),(1033417284498468865,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376273\",\"timeStamp\":\"2024-08-22 16:31:20.439\"}','18265376273','completed','2024-08-22 16:31:21','2024-08-22 16:31:21'),(1033417289363861505,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376321\",\"timeStamp\":\"2024-08-22 16:31:21.584\"}','18265376321','completed','2024-08-22 16:31:22','2024-08-22 16:31:22'),(1033417294057287681,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265376367\",\"timeStamp\":\"2024-08-22 16:31:22.694\"}','18265376367','completed','2024-08-22 16:31:23','2024-08-22 16:31:24'),(1033417610966315009,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379491\",\"timeStamp\":\"2024-08-22 16:32:37.162\"}','18265379491','completed','2024-08-22 16:32:39','2024-08-22 16:32:39'),(1033417616561516545,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379592\",\"timeStamp\":\"2024-08-22 16:32:39.587\"}','18265379592','completed','2024-08-22 16:32:40','2024-08-22 16:32:41'),(1033417622047666177,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379644\",\"timeStamp\":\"2024-08-22 16:32:40.827\"}','18265379644','completed','2024-08-22 16:32:41','2024-08-22 16:32:42'),(1033417627529621505,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379699\",\"timeStamp\":\"2024-08-22 16:32:42.116\"}','18265379699','completed','2024-08-22 16:32:43','2024-08-22 16:32:43'),(1033417632764112897,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379752\",\"timeStamp\":\"2024-08-22 16:32:43.381\"}','18265379752','completed','2024-08-22 16:32:44','2024-08-22 16:32:44'),(1033417637671448577,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379802\",\"timeStamp\":\"2024-08-22 16:32:44.576\"}','18265379802','completed','2024-08-22 16:32:45','2024-08-22 16:32:45'),(1033417642855608321,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379853\",\"timeStamp\":\"2024-08-22 16:32:45.8\"}','18265379853','completed','2024-08-22 16:32:46','2024-08-22 16:32:47'),(1033417647825858561,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379905\",\"timeStamp\":\"2024-08-22 16:32:47.033\"}','18265379905','completed','2024-08-22 16:32:48','2024-08-22 16:32:48'),(1033417653022601217,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265379954\",\"timeStamp\":\"2024-08-22 16:32:48.217\"}','18265379954','completed','2024-08-22 16:32:49','2024-08-22 16:32:49'),(1033417658219343873,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265380006\",\"timeStamp\":\"2024-08-22 16:32:49.456\"}','18265380006','completed','2024-08-22 16:32:50','2024-08-22 16:32:50'),(1033417663537721345,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265380057\",\"timeStamp\":\"2024-08-22 16:32:50.674\"}','18265380057','completed','2024-08-22 16:32:51','2024-08-22 16:32:52'),(1033417668537331713,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265380111\",\"timeStamp\":\"2024-08-22 16:32:51.953\"}','18265380111','completed','2024-08-22 16:32:53','2024-08-22 16:32:53'),(1033417673276895233,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265380159\",\"timeStamp\":\"2024-08-22 16:32:53.092\"}','18265380159','completed','2024-08-22 16:32:54','2024-08-22 16:33:26'),(1033417795595382785,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381338\",\"timeStamp\":\"2024-08-22 16:33:21.204\"}','18265381338','completed','2024-08-22 16:33:23','2024-08-22 16:33:23'),(1033417800976674817,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381436\",\"timeStamp\":\"2024-08-22 16:33:23.546\"}','18265381436','completed','2024-08-22 16:33:24','2024-08-22 16:33:24'),(1033417805967896577,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381485\",\"timeStamp\":\"2024-08-22 16:33:24.714\"}','18265381485','completed','2024-08-22 16:33:25','2024-08-22 16:33:26'),(1033417811001061377,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381535\",\"timeStamp\":\"2024-08-22 16:33:25.897\"}','18265381535','completed','2024-08-22 16:33:27','2024-08-22 16:33:27'),(1033417815912591361,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381586\",\"timeStamp\":\"2024-08-22 16:33:27.113\"}','18265381586','completed','2024-08-22 16:33:28','2024-08-22 16:33:28'),(1033417821004476417,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381636\",\"timeStamp\":\"2024-08-22 16:33:28.315\"}','18265381636','completed','2024-08-22 16:33:29','2024-08-22 16:33:29'),(1033417825626599425,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381683\",\"timeStamp\":\"2024-08-22 16:33:29.434\"}','18265381683','completed','2024-08-22 16:33:30','2024-08-22 16:33:30'),(1033417830416494593,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381730\",\"timeStamp\":\"2024-08-22 16:33:30.557\"}','18265381730','completed','2024-08-22 16:33:31','2024-08-22 16:33:31'),(1033417835571294209,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381779\",\"timeStamp\":\"2024-08-22 16:33:31.708\"}','18265381779','completed','2024-08-22 16:33:32','2024-08-22 16:33:33'),(1033417841002917889,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381835\",\"timeStamp\":\"2024-08-22 16:33:33.062\"}','18265381835','completed','2024-08-22 16:33:34','2024-08-22 16:33:34'),(1033417845943808001,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381885\",\"timeStamp\":\"2024-08-22 16:33:34.242\"}','18265381885','completed','2024-08-22 16:33:35','2024-08-22 16:33:35'),(1033417850880503809,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381936\",\"timeStamp\":\"2024-08-22 16:33:35.46\"}','18265381936','completed','2024-08-22 16:33:36','2024-08-22 16:33:36'),(1033417855951417345,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265381986\",\"timeStamp\":\"2024-08-22 16:33:36.645\"}','18265381986','completed','2024-08-22 16:33:37','2024-08-22 16:33:38'),(1033417861122994177,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265382038\",\"timeStamp\":\"2024-08-22 16:33:37.884\"}','18265382038','completed','2024-08-22 16:33:38','2024-08-22 16:33:39'),(1033417865816420353,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265382085\",\"timeStamp\":\"2024-08-22 16:33:39.021\"}','18265382085','completed','2024-08-22 16:33:40','2024-08-22 16:33:40'),(1033417870522429441,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265382134\",\"timeStamp\":\"2024-08-22 16:33:40.173\"}','18265382134','completed','2024-08-22 16:33:41','2024-08-22 16:38:11'),(1033418991815077889,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393296\",\"timeStamp\":\"2024-08-22 16:38:06.302\"}','18265393296','completed','2024-08-22 16:38:08','2024-08-22 16:38:08'),(1033418996332343297,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393392\",\"timeStamp\":\"2024-08-22 16:38:08.584\"}','18265393392','completed','2024-08-22 16:38:09','2024-08-22 16:38:09'),(1033419000774111233,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393433\",\"timeStamp\":\"2024-08-22 16:38:09.581\"}','18265393433','completed','2024-08-22 16:38:10','2024-08-22 16:38:10'),(1033419005056495617,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393479\",\"timeStamp\":\"2024-08-22 16:38:10.669\"}','18265393479','completed','2024-08-22 16:38:11','2024-08-22 16:38:11'),(1033419009498263553,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393522\",\"timeStamp\":\"2024-08-22 16:38:11.685\"}','18265393522','completed','2024-08-22 16:38:12','2024-08-22 16:38:13'),(1033419013981974529,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393567\",\"timeStamp\":\"2024-08-22 16:38:12.757\"}','18265393567','completed','2024-08-22 16:38:13','2024-08-22 16:38:14'),(1033419018297913345,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393609\",\"timeStamp\":\"2024-08-22 16:38:13.766\"}','18265393609','completed','2024-08-22 16:38:14','2024-08-22 16:38:15'),(1033419022622240769,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393653\",\"timeStamp\":\"2024-08-22 16:38:14.824\"}','18265393653','completed','2024-08-22 16:38:15','2024-08-22 16:38:16'),(1033419026963345409,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393696\",\"timeStamp\":\"2024-08-22 16:38:15.851\"}','18265393696','completed','2024-08-22 16:38:16','2024-08-22 16:38:17'),(1033419031468027905,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393739\",\"timeStamp\":\"2024-08-22 16:38:16.878\"}','18265393739','completed','2024-08-22 16:38:17','2024-08-22 16:38:18'),(1033419035968516097,'send_award','lyt','{\"data\":{\"awardId\":101,\"awardTitle\":\"OpenAI 增加使用次数\",\"userId\":\"lyt\"},\"id\":\"18265393785\",\"timeStamp\":\"2024-08-22 16:38:17.969\"}','18265393785','create','2024-08-22 16:38:19','2024-08-22 16:38:19');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_award_record_0`
--

DROP TABLE IF EXISTS `user_award_record_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_award_record_0` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '抽奖订单ID【作为幂等使用】',
  `award_id` int NOT NULL COMMENT '奖品ID',
  `award_title` varchar(128) NOT NULL COMMENT '奖品标题（名称）',
  `award_time` datetime NOT NULL COMMENT '中奖时间',
  `award_state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '奖品状态；create-创建、completed-发奖完成',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_activity_id` (`activity_id`),
  KEY `idx_award_id` (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户中奖记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_award_record_0`
--

LOCK TABLES `user_award_record_0` WRITE;
/*!40000 ALTER TABLE `user_award_record_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_award_record_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_award_record_1`
--

DROP TABLE IF EXISTS `user_award_record_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_award_record_1` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '抽奖订单ID【作为幂等使用】',
  `award_id` int NOT NULL COMMENT '奖品ID',
  `award_title` varchar(128) NOT NULL COMMENT '奖品标题（名称）',
  `award_time` datetime NOT NULL COMMENT '中奖时间',
  `award_state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '奖品状态；create-创建、completed-发奖完成',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_activity_id` (`activity_id`),
  KEY `idx_award_id` (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户中奖记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_award_record_1`
--

LOCK TABLES `user_award_record_1` WRITE;
/*!40000 ALTER TABLE `user_award_record_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_award_record_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_award_record_2`
--

DROP TABLE IF EXISTS `user_award_record_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_award_record_2` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '抽奖订单ID【作为幂等使用】',
  `award_id` int NOT NULL COMMENT '奖品ID',
  `award_title` varchar(128) NOT NULL COMMENT '奖品标题（名称）',
  `award_time` datetime NOT NULL COMMENT '中奖时间',
  `award_state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '奖品状态；create-创建、completed-发奖完成',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_activity_id` (`activity_id`),
  KEY `idx_award_id` (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户中奖记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_award_record_2`
--

LOCK TABLES `user_award_record_2` WRITE;
/*!40000 ALTER TABLE `user_award_record_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_award_record_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_raffle_order_0`
--

DROP TABLE IF EXISTS `user_raffle_order_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_raffle_order_0` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '订单ID',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `order_state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '订单状态；create-创建、used-已使用、cancel-已作废',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  KEY `idx_user_id_activity_id` (`user_id`,`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户抽奖订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_raffle_order_0`
--

LOCK TABLES `user_raffle_order_0` WRITE;
/*!40000 ALTER TABLE `user_raffle_order_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_raffle_order_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_raffle_order_1`
--

DROP TABLE IF EXISTS `user_raffle_order_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_raffle_order_1` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '订单ID',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `order_state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '订单状态；create-创建、used-已使用、cancel-已作废',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  KEY `idx_user_id_activity_id` (`user_id`,`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户抽奖订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_raffle_order_1`
--

LOCK TABLES `user_raffle_order_1` WRITE;
/*!40000 ALTER TABLE `user_raffle_order_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_raffle_order_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_raffle_order_2`
--

DROP TABLE IF EXISTS `user_raffle_order_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_raffle_order_2` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',
  `strategy_id` bigint NOT NULL COMMENT '抽奖策略ID',
  `order_id` varchar(12) NOT NULL COMMENT '订单ID',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `order_state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '订单状态；create-创建、used-已使用、cancel-已作废',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_id` (`order_id`),
  KEY `idx_user_id_activity_id` (`user_id`,`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户抽奖订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_raffle_order_2`
--

LOCK TABLES `user_raffle_order_2` WRITE;
/*!40000 ALTER TABLE `user_raffle_order_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_raffle_order_2` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-22 16:44:21
