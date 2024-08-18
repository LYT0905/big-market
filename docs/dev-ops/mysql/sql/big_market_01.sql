-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: 8.138.41.137    Database: big_market_01
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_account_0`
--

LOCK TABLES `raffle_activity_account_0` WRITE;
/*!40000 ALTER TABLE `raffle_activity_account_0` DISABLE KEYS */;
INSERT INTO `raffle_activity_account_0` VALUES (1,'lyt',101,20,20,20,20,20,20,'2024-08-14 22:29:02','2024-08-14 22:45:33');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动账户表';
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='抽奖活动单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle_activity_order_0`
--

LOCK TABLES `raffle_activity_order_0` WRITE;
/*!40000 ALTER TABLE `raffle_activity_order_0` DISABLE KEYS */;
INSERT INTO `raffle_activity_order_0` VALUES (4,'lyt',9011,101,'测试',100004,'182373269313','2024-08-14 14:45:32',10,10,10,'completed','700091009111','2024-08-14 22:45:33','2024-08-14 22:45:33');
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
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `topic` varchar(32) NOT NULL COMMENT '消息主题',
  `message` varchar(512) NOT NULL COMMENT '消息主体',
  `state` varchar(16) NOT NULL DEFAULT 'create' COMMENT '任务状态；create-创建、completed-完成、fail-失败',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务表，发送MQ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_award_record_0`
--

DROP TABLE IF EXISTS `user_award_record_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_award_record_0` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
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
  `id` int unsigned NOT NULL AUTO_INCREMENT,
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
  `id` int unsigned NOT NULL AUTO_INCREMENT,
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
  `id` int unsigned NOT NULL AUTO_INCREMENT,
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

-- Dump completed on 2024-08-18 13:43:16
