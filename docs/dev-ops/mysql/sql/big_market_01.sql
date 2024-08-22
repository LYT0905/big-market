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
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `topic` varchar(32) NOT NULL COMMENT '消息主题',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `message` varchar(512) NOT NULL COMMENT '消息主体',
  `message_id` varchar(11) DEFAULT NULL COMMENT '消息编号',
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
) ENGINE=InnoDB AUTO_INCREMENT=1033419035117072385 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户中奖记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_award_record_0`
--

LOCK TABLES `user_award_record_0` WRITE;
/*!40000 ALTER TABLE `user_award_record_0` DISABLE KEYS */;
INSERT INTO `user_award_record_0` VALUES (1033414719668989952,'lyt',100301,100006,'182653505955',101,'OpenAI 增加使用次数','2024-08-22 08:21:08','create','2024-08-22 16:21:10','2024-08-22 16:21:10'),(1033414726069497856,'lyt',100301,100006,'182653506951',101,'OpenAI 增加使用次数','2024-08-22 08:21:11','create','2024-08-22 16:21:11','2024-08-22 16:21:11'),(1033414730758729728,'lyt',100301,100006,'182653507420',101,'OpenAI 增加使用次数','2024-08-22 08:21:12','create','2024-08-22 16:21:12','2024-08-22 16:21:12'),(1033414735720591360,'lyt',100301,100006,'182653507917',101,'OpenAI 增加使用次数','2024-08-22 08:21:13','create','2024-08-22 16:21:13','2024-08-22 16:21:13'),(1033414740497903616,'lyt',100301,100006,'182653508394',101,'OpenAI 增加使用次数','2024-08-22 08:21:14','create','2024-08-22 16:21:14','2024-08-22 16:21:14'),(1033414745606565888,'lyt',100301,100006,'182653508905',101,'OpenAI 增加使用次数','2024-08-22 08:21:15','create','2024-08-22 16:21:16','2024-08-22 16:21:16'),(1033414749968642048,'lyt',100301,100006,'182653509341',101,'OpenAI 增加使用次数','2024-08-22 08:21:16','create','2024-08-22 16:21:17','2024-08-22 16:21:17'),(1033414755119247360,'lyt',100301,100006,'182653509856',101,'OpenAI 增加使用次数','2024-08-22 08:21:18','create','2024-08-22 16:21:18','2024-08-22 16:21:18'),(1033414759959474176,'lyt',100301,100006,'182653510340',101,'OpenAI 增加使用次数','2024-08-22 08:21:19','create','2024-08-22 16:21:19','2024-08-22 16:21:19'),(1033414765235908608,'lyt',100301,100006,'182653510868',101,'OpenAI 增加使用次数','2024-08-22 08:21:20','create','2024-08-22 16:21:20','2024-08-22 16:21:20'),(1033414770281656320,'lyt',100301,100006,'182653511373',101,'OpenAI 增加使用次数','2024-08-22 08:21:21','create','2024-08-22 16:21:22','2024-08-22 16:21:22'),(1033414775105105920,'lyt',100301,100006,'182653511855',101,'OpenAI 增加使用次数','2024-08-22 08:21:22','create','2024-08-22 16:21:23','2024-08-22 16:21:23'),(1033414780142465024,'lyt',100301,100006,'182653512359',101,'OpenAI 增加使用次数','2024-08-22 08:21:24','create','2024-08-22 16:21:24','2024-08-22 16:21:24'),(1033414785221767168,'lyt',100301,100006,'182653512867',101,'OpenAI 增加使用次数','2024-08-22 08:21:25','create','2024-08-22 16:21:25','2024-08-22 16:21:25'),(1033414790200406016,'lyt',100301,100006,'182653513365',101,'OpenAI 增加使用次数','2024-08-22 08:21:26','create','2024-08-22 16:21:26','2024-08-22 16:21:26'),(1033414795250348032,'lyt',100301,100006,'182653513869',101,'OpenAI 增加使用次数','2024-08-22 08:21:27','create','2024-08-22 16:21:27','2024-08-22 16:21:27'),(1033414800405147648,'lyt',100301,100006,'182653514385',101,'OpenAI 增加使用次数','2024-08-22 08:21:28','create','2024-08-22 16:21:29','2024-08-22 16:21:29'),(1033414805392175104,'lyt',100301,100006,'182653514884',101,'OpenAI 增加使用次数','2024-08-22 08:21:30','create','2024-08-22 16:21:30','2024-08-22 16:21:30'),(1033414810744107008,'lyt',100301,100006,'182653515419',101,'OpenAI 增加使用次数','2024-08-22 08:21:31','create','2024-08-22 16:21:31','2024-08-22 16:21:31'),(1033414815450116096,'lyt',100301,100006,'182653515890',101,'OpenAI 增加使用次数','2024-08-22 08:21:32','create','2024-08-22 16:21:32','2024-08-22 16:21:32'),(1033414820097404928,'lyt',100301,100006,'182653516354',101,'OpenAI 增加使用次数','2024-08-22 08:21:33','create','2024-08-22 16:21:33','2024-08-22 16:21:33'),(1033414825076043776,'lyt',100301,100006,'182653516852',101,'OpenAI 增加使用次数','2024-08-22 08:21:34','create','2024-08-22 16:21:35','2024-08-22 16:21:35'),(1033414829723332608,'lyt',100301,100006,'182653517317',101,'OpenAI 增加使用次数','2024-08-22 08:21:35','create','2024-08-22 16:21:36','2024-08-22 16:21:36'),(1033414834303512576,'lyt',100301,100006,'182653517775',101,'OpenAI 增加使用次数','2024-08-22 08:21:36','create','2024-08-22 16:21:37','2024-08-22 16:21:37'),(1033414839517032448,'lyt',100301,100006,'182653518296',101,'OpenAI 增加使用次数','2024-08-22 08:21:38','create','2024-08-22 16:21:38','2024-08-22 16:21:38'),(1033414844663443456,'lyt',100301,100006,'182653518811',101,'OpenAI 增加使用次数','2024-08-22 08:21:39','create','2024-08-22 16:21:39','2024-08-22 16:21:39'),(1033414849491087360,'lyt',100301,100006,'182653519294',101,'OpenAI 增加使用次数','2024-08-22 08:21:40','create','2024-08-22 16:21:40','2024-08-22 16:21:40'),(1033414854411005952,'lyt',100301,100006,'182653519786',101,'OpenAI 增加使用次数','2024-08-22 08:21:41','create','2024-08-22 16:21:42','2024-08-22 16:21:42'),(1033414859276398592,'lyt',100301,100006,'182653520272',101,'OpenAI 增加使用次数','2024-08-22 08:21:42','create','2024-08-22 16:21:43','2024-08-22 16:21:43'),(1033414864401838080,'lyt',100301,100006,'182653520785',101,'OpenAI 增加使用次数','2024-08-22 08:21:44','create','2024-08-22 16:21:44','2024-08-22 16:21:44'),(1033414868973629440,'lyt',100301,100006,'182653521242',101,'OpenAI 增加使用次数','2024-08-22 08:21:45','create','2024-08-22 16:21:45','2024-08-22 16:21:45'),(1033414873868382208,'lyt',100301,100006,'182653521732',101,'OpenAI 增加使用次数','2024-08-22 08:21:46','create','2024-08-22 16:21:46','2024-08-22 16:21:46'),(1033414878515671040,'lyt',100301,100006,'182653522196',101,'OpenAI 增加使用次数','2024-08-22 08:21:47','create','2024-08-22 16:21:47','2024-08-22 16:21:47'),(1033414883360092160,'lyt',100301,100006,'182653522681',101,'OpenAI 增加使用次数','2024-08-22 08:21:48','create','2024-08-22 16:21:48','2024-08-22 16:21:48'),(1033414887952855040,'lyt',100301,100006,'182653523140',101,'OpenAI 增加使用次数','2024-08-22 08:21:49','create','2024-08-22 16:21:50','2024-08-22 16:21:50'),(1033414892788887552,'lyt',100301,100006,'182653523623',101,'OpenAI 增加使用次数','2024-08-22 08:21:50','create','2024-08-22 16:21:51','2024-08-22 16:21:51'),(1033414897490702336,'lyt',100301,100006,'182653524094',101,'OpenAI 增加使用次数','2024-08-22 08:21:51','create','2024-08-22 16:21:52','2024-08-22 16:21:52'),(1033414902519672832,'lyt',100301,100006,'182653524597',101,'OpenAI 增加使用次数','2024-08-22 08:21:53','create','2024-08-22 16:21:53','2024-08-22 16:21:53'),(1033414907431202816,'lyt',100301,100006,'182653525088',101,'OpenAI 增加使用次数','2024-08-22 08:21:54','create','2024-08-22 16:21:54','2024-08-22 16:21:54'),(1033414912250458112,'lyt',100301,100006,'182653525570',101,'OpenAI 增加使用次数','2024-08-22 08:21:55','create','2024-08-22 16:21:55','2024-08-22 16:21:55'),(1033414916843220992,'lyt',100301,100006,'182653526029',101,'OpenAI 增加使用次数','2024-08-22 08:21:56','create','2024-08-22 16:21:56','2024-08-22 16:21:56'),(1033414921670864896,'lyt',100301,100006,'182653526512',101,'OpenAI 增加使用次数','2024-08-22 08:21:57','create','2024-08-22 16:21:58','2024-08-22 16:21:58'),(1033414926460760064,'lyt',100301,100006,'182653526991',101,'OpenAI 增加使用次数','2024-08-22 08:21:58','create','2024-08-22 16:21:59','2024-08-22 16:21:59'),(1033414931418427392,'lyt',100301,100006,'182653527487',101,'OpenAI 增加使用次数','2024-08-22 08:22:00','create','2024-08-22 16:22:00','2024-08-22 16:22:00'),(1033414936355123200,'lyt',100301,100006,'182653527980',101,'OpenAI 增加使用次数','2024-08-22 08:22:01','create','2024-08-22 16:22:01','2024-08-22 16:22:01'),(1033414941384093696,'lyt',100301,100006,'182653528483',101,'OpenAI 增加使用次数','2024-08-22 08:22:02','create','2024-08-22 16:22:02','2024-08-22 16:22:02'),(1033414946358538240,'lyt',100301,100006,'182653528981',101,'OpenAI 增加使用次数','2024-08-22 08:22:03','create','2024-08-22 16:22:03','2024-08-22 16:22:03'),(1033414950938718208,'lyt',100301,100006,'182653529439',101,'OpenAI 增加使用次数','2024-08-22 08:22:04','create','2024-08-22 16:22:05','2024-08-22 16:22:05'),(1033414955657310208,'lyt',100301,100006,'182653529911',101,'OpenAI 增加使用次数','2024-08-22 08:22:05','create','2024-08-22 16:22:06','2024-08-22 16:22:06'),(1033414960073912320,'lyt',100301,100006,'182653530352',101,'OpenAI 增加使用次数','2024-08-22 08:22:06','create','2024-08-22 16:22:07','2024-08-22 16:22:07'),(1033415701689774080,'lyt',100301,100006,'182653604119',101,'OpenAI 增加使用次数','2024-08-22 08:25:02','create','2024-08-22 16:25:04','2024-08-22 16:25:04'),(1033415708430020608,'lyt',100301,100006,'182653605187',101,'OpenAI 增加使用次数','2024-08-22 08:25:05','create','2024-08-22 16:25:05','2024-08-22 16:25:05'),(1033415713404465152,'lyt',100301,100006,'182653605685',101,'OpenAI 增加使用次数','2024-08-22 08:25:06','create','2024-08-22 16:25:06','2024-08-22 16:25:06'),(1033415718261469184,'lyt',100301,100006,'182653606171',101,'OpenAI 增加使用次数','2024-08-22 08:25:07','create','2024-08-22 16:25:08','2024-08-22 16:25:08'),(1033415723114278912,'lyt',100301,100006,'182653606656',101,'OpenAI 增加使用次数','2024-08-22 08:25:08','create','2024-08-22 16:25:09','2024-08-22 16:25:09'),(1033415728030003200,'lyt',100301,100006,'182653607148',101,'OpenAI 增加使用次数','2024-08-22 08:25:09','create','2024-08-22 16:25:10','2024-08-22 16:25:10'),(1033415733092528128,'lyt',100301,100006,'182653607653',101,'OpenAI 增加使用次数','2024-08-22 08:25:11','create','2024-08-22 16:25:11','2024-08-22 16:25:11'),(1033415737794342912,'lyt',100301,100006,'182653608124',101,'OpenAI 增加使用次数','2024-08-22 08:25:12','create','2024-08-22 16:25:12','2024-08-22 16:25:12'),(1033415742500352000,'lyt',100301,100006,'182653608595',101,'OpenAI 增加使用次数','2024-08-22 08:25:13','create','2024-08-22 16:25:13','2024-08-22 16:25:13'),(1033415747340578816,'lyt',100301,100006,'182653609079',101,'OpenAI 增加使用次数','2024-08-22 08:25:14','create','2024-08-22 16:25:14','2024-08-22 16:25:14'),(1033415752126279680,'lyt',100301,100006,'182653609558',101,'OpenAI 增加使用次数','2024-08-22 08:25:15','create','2024-08-22 16:25:16','2024-08-22 16:25:16'),(1033415756370915328,'lyt',100301,100006,'182653609982',101,'OpenAI 增加使用次数','2024-08-22 08:25:16','create','2024-08-22 16:25:17','2024-08-22 16:25:17'),(1033415761219530752,'lyt',100301,100006,'182653610467',101,'OpenAI 增加使用次数','2024-08-22 08:25:17','create','2024-08-22 16:25:18','2024-08-22 16:25:18'),(1033415766265278464,'lyt',100301,100006,'182653610971',101,'OpenAI 增加使用次数','2024-08-22 08:25:19','create','2024-08-22 16:25:19','2024-08-22 16:25:19'),(1033415771227140096,'lyt',100301,100006,'182653611467',101,'OpenAI 增加使用次数','2024-08-22 08:25:20','create','2024-08-22 16:25:20','2024-08-22 16:25:20'),(1033415776390328320,'lyt',100301,100006,'182653611983',101,'OpenAI 增加使用次数','2024-08-22 08:25:21','create','2024-08-22 16:25:21','2024-08-22 16:25:21'),(1033415781188612096,'lyt',100301,100006,'182653612463',101,'OpenAI 增加使用次数','2024-08-22 08:25:22','create','2024-08-22 16:25:23','2024-08-22 16:25:23'),(1033415785831706624,'lyt',100301,100006,'182653612928',101,'OpenAI 增加使用次数','2024-08-22 08:25:23','create','2024-08-22 16:25:24','2024-08-22 16:25:24'),(1033415790730653696,'lyt',100301,100006,'182653613418',101,'OpenAI 增加使用次数','2024-08-22 08:25:24','create','2024-08-22 16:25:25','2024-08-22 16:25:25'),(1033415795499577344,'lyt',100301,100006,'182653613895',101,'OpenAI 增加使用次数','2024-08-22 08:25:26','create','2024-08-22 16:25:26','2024-08-22 16:25:26'),(1033415800012648448,'lyt',100301,100006,'182653614346',101,'OpenAI 增加使用次数','2024-08-22 08:25:27','create','2024-08-22 16:25:27','2024-08-22 16:25:27'),(1033415804810932224,'lyt',100301,100006,'182653614826',101,'OpenAI 增加使用次数','2024-08-22 08:25:28','create','2024-08-22 16:25:28','2024-08-22 16:25:28'),(1033415809449832448,'lyt',100301,100006,'182653615289',101,'OpenAI 增加使用次数','2024-08-22 08:25:29','create','2024-08-22 16:25:29','2024-08-22 16:25:29'),(1033415814508163072,'lyt',100301,100006,'182653615796',101,'OpenAI 增加使用次数','2024-08-22 08:25:30','create','2024-08-22 16:25:30','2024-08-22 16:25:30'),(1033415819209977856,'lyt',100301,100006,'182653616266',101,'OpenAI 增加使用次数','2024-08-22 08:25:31','create','2024-08-22 16:25:32','2024-08-22 16:25:32'),(1033415823865655296,'lyt',100301,100006,'182653616731',101,'OpenAI 增加使用次数','2024-08-22 08:25:32','create','2024-08-22 16:25:33','2024-08-22 16:25:33'),(1033415828642967552,'lyt',100301,100006,'182653617209',101,'OpenAI 增加使用次数','2024-08-22 08:25:33','create','2024-08-22 16:25:34','2024-08-22 16:25:34'),(1033415833978122240,'lyt',100301,100006,'182653617742',101,'OpenAI 增加使用次数','2024-08-22 08:25:35','create','2024-08-22 16:25:35','2024-08-22 16:25:35'),(1033415839602683904,'lyt',100301,100006,'182653618305',101,'OpenAI 增加使用次数','2024-08-22 08:25:36','create','2024-08-22 16:25:37','2024-08-22 16:25:37'),(1033416864581529600,'lyt',100301,100006,'182653675524',101,'OpenAI 增加使用次数','2024-08-22 08:27:53','create','2024-08-22 16:29:41','2024-08-22 16:29:41'),(1033417026636853248,'lyt',100301,100006,'182653732239',101,'OpenAI 增加使用次数','2024-08-22 08:30:08','create','2024-08-22 16:30:19','2024-08-22 16:30:19'),(1033417031649046528,'lyt',100301,100006,'182653737509',101,'OpenAI 增加使用次数','2024-08-22 08:30:20','create','2024-08-22 16:30:21','2024-08-22 16:30:21'),(1033417036174700544,'lyt',100301,100006,'182653737962',101,'OpenAI 增加使用次数','2024-08-22 08:30:21','create','2024-08-22 16:30:22','2024-08-22 16:30:22'),(1033417041098813440,'lyt',100301,100006,'182653738454',101,'OpenAI 增加使用次数','2024-08-22 08:30:23','create','2024-08-22 16:30:23','2024-08-22 16:30:23'),(1033417045947428864,'lyt',100301,100006,'182653738940',101,'OpenAI 增加使用次数','2024-08-22 08:30:24','create','2024-08-22 16:30:24','2024-08-22 16:30:24'),(1033417238449205248,'lyt',100301,100006,'182653757834',101,'OpenAI 增加使用次数','2024-08-22 08:31:09','create','2024-08-22 16:31:10','2024-08-22 16:31:10'),(1033417244870684672,'lyt',100301,100006,'182653758831',101,'OpenAI 增加使用次数','2024-08-22 08:31:11','create','2024-08-22 16:31:12','2024-08-22 16:31:12'),(1033417249710911488,'lyt',100301,100006,'182653759316',101,'OpenAI 增加使用次数','2024-08-22 08:31:12','create','2024-08-22 16:31:13','2024-08-22 16:31:13'),(1033417254505000960,'lyt',100301,100006,'182653759795',101,'OpenAI 增加使用次数','2024-08-22 08:31:13','create','2024-08-22 16:31:14','2024-08-22 16:31:14'),(1033417259252953088,'lyt',100301,100006,'182653760270',101,'OpenAI 增加使用次数','2024-08-22 08:31:15','create','2024-08-22 16:31:15','2024-08-22 16:31:15'),(1033417263887659008,'lyt',100301,100006,'182653760733',101,'OpenAI 增加使用次数','2024-08-22 08:31:16','create','2024-08-22 16:31:16','2024-08-22 16:31:16'),(1033417268597862400,'lyt',100301,100006,'182653761204',101,'OpenAI 增加使用次数','2024-08-22 08:31:17','create','2024-08-22 16:31:17','2024-08-22 16:31:17'),(1033417273299677184,'lyt',100301,100006,'182653761674',101,'OpenAI 增加使用次数','2024-08-22 08:31:18','create','2024-08-22 16:31:18','2024-08-22 16:31:18'),(1033417278777438208,'lyt',100301,100006,'182653762222',101,'OpenAI 增加使用次数','2024-08-22 08:31:19','create','2024-08-22 16:31:20','2024-08-22 16:31:20'),(1033417283873517568,'lyt',100301,100006,'182653762732',101,'OpenAI 增加使用次数','2024-08-22 08:31:20','create','2024-08-22 16:31:21','2024-08-22 16:31:21'),(1033417288671801344,'lyt',100301,100006,'182653763212',101,'OpenAI 增加使用次数','2024-08-22 08:31:22','create','2024-08-22 16:31:22','2024-08-22 16:31:22'),(1033417293331673088,'lyt',100301,100006,'182653763678',101,'OpenAI 增加使用次数','2024-08-22 08:31:23','create','2024-08-22 16:31:23','2024-08-22 16:31:23'),(1033417609179541504,'lyt',100301,100006,'182653794911',101,'OpenAI 增加使用次数','2024-08-22 08:32:37','create','2024-08-22 16:32:39','2024-08-22 16:32:39'),(1033417615848484864,'lyt',100301,100006,'182653795929',101,'OpenAI 增加使用次数','2024-08-22 08:32:40','create','2024-08-22 16:32:40','2024-08-22 16:32:40'),(1033417621041033216,'lyt',100301,100006,'182653796449',101,'OpenAI 增加使用次数','2024-08-22 08:32:41','create','2024-08-22 16:32:41','2024-08-22 16:32:41'),(1033417626451685376,'lyt',100301,100006,'182653796990',101,'OpenAI 增加使用次数','2024-08-22 08:32:42','create','2024-08-22 16:32:43','2024-08-22 16:32:43'),(1033417631757479936,'lyt',100301,100006,'182653797520',101,'OpenAI 增加使用次数','2024-08-22 08:32:43','create','2024-08-22 16:32:44','2024-08-22 16:32:44'),(1033417636769673216,'lyt',100301,100006,'182653798021',101,'OpenAI 增加使用次数','2024-08-22 08:32:45','create','2024-08-22 16:32:45','2024-08-22 16:32:45'),(1033417641903501312,'lyt',100301,100006,'182653798535',101,'OpenAI 增加使用次数','2024-08-22 08:32:46','create','2024-08-22 16:32:46','2024-08-22 16:32:46'),(1033417647075078144,'lyt',100301,100006,'182653799052',101,'OpenAI 增加使用次数','2024-08-22 08:32:47','create','2024-08-22 16:32:47','2024-08-22 16:32:47'),(1033417652036939776,'lyt',100301,100006,'182653799549',101,'OpenAI 增加使用次数','2024-08-22 08:32:48','create','2024-08-22 16:32:49','2024-08-22 16:32:49'),(1033417657237876736,'lyt',100301,100006,'182653800068',101,'OpenAI 增加使用次数','2024-08-22 08:32:49','create','2024-08-22 16:32:50','2024-08-22 16:32:50'),(1033417662346539008,'lyt',100301,100006,'182653800579',101,'OpenAI 增加使用次数','2024-08-22 08:32:51','create','2024-08-22 16:32:51','2024-08-22 16:32:51'),(1033417667711053824,'lyt',100301,100006,'182653801116',101,'OpenAI 增加使用次数','2024-08-22 08:32:52','create','2024-08-22 16:32:52','2024-08-22 16:32:52'),(1033417672488366080,'lyt',100301,100006,'182653801593',101,'OpenAI 增加使用次数','2024-08-22 08:32:53','create','2024-08-22 16:32:53','2024-08-22 16:32:53'),(1033417793988964352,'lyt',100301,100006,'182653813383',101,'OpenAI 增加使用次数','2024-08-22 08:33:21','create','2024-08-22 16:33:23','2024-08-22 16:33:23'),(1033417800221700096,'lyt',100301,100006,'182653814367',101,'OpenAI 增加使用次数','2024-08-22 08:33:24','create','2024-08-22 16:33:24','2024-08-22 16:33:24'),(1033417805120647168,'lyt',100301,100006,'182653814857',101,'OpenAI 增加使用次数','2024-08-22 08:33:25','create','2024-08-22 16:33:25','2024-08-22 16:33:25'),(1033417810082508800,'lyt',100301,100006,'182653815353',101,'OpenAI 增加使用次数','2024-08-22 08:33:26','create','2024-08-22 16:33:26','2024-08-22 16:33:26'),(1033417815182782464,'lyt',100301,100006,'182653815863',101,'OpenAI 增加使用次数','2024-08-22 08:33:27','create','2024-08-22 16:33:27','2024-08-22 16:33:27'),(1033417820224335872,'lyt',100301,100006,'182653816367',101,'OpenAI 增加使用次数','2024-08-22 08:33:28','create','2024-08-22 16:33:29','2024-08-22 16:33:29'),(1033417824917762048,'lyt',100301,100006,'182653816836',101,'OpenAI 增加使用次数','2024-08-22 08:33:29','create','2024-08-22 16:33:30','2024-08-22 16:33:30'),(1033417829627965440,'lyt',100301,100006,'182653817307',101,'OpenAI 增加使用次数','2024-08-22 08:33:31','create','2024-08-22 16:33:31','2024-08-22 16:33:31'),(1033417834455609344,'lyt',100301,100006,'182653817790',101,'OpenAI 增加使用次数','2024-08-22 08:33:32','create','2024-08-22 16:33:32','2024-08-22 16:33:32'),(1033417840134696960,'lyt',100301,100006,'182653818358',101,'OpenAI 增加使用次数','2024-08-22 08:33:33','create','2024-08-22 16:33:33','2024-08-22 16:33:33'),(1033417845088169984,'lyt',100301,100006,'182653818853',101,'OpenAI 增加使用次数','2024-08-22 08:33:34','create','2024-08-22 16:33:35','2024-08-22 16:33:35'),(1033417850192637952,'lyt',100301,100006,'182653819364',101,'OpenAI 增加使用次数','2024-08-22 08:33:35','create','2024-08-22 16:33:36','2024-08-22 16:33:36'),(1033417855162888192,'lyt',100301,100006,'182653819861',101,'OpenAI 增加使用次数','2024-08-22 08:33:37','create','2024-08-22 16:33:37','2024-08-22 16:33:37'),(1033417860355436544,'lyt',100301,100006,'182653820380',101,'OpenAI 增加使用次数','2024-08-22 08:33:38','create','2024-08-22 16:33:38','2024-08-22 16:33:38'),(1033417865124360192,'lyt',100301,100006,'182653820857',101,'OpenAI 增加使用次数','2024-08-22 08:33:39','create','2024-08-22 16:33:39','2024-08-22 16:33:39'),(1033417869964587008,'lyt',100301,100006,'182653821341',101,'OpenAI 增加使用次数','2024-08-22 08:33:40','create','2024-08-22 16:33:41','2024-08-22 16:33:41'),(1033418990061858816,'lyt',100301,100006,'182653932962',101,'OpenAI 增加使用次数','2024-08-22 08:38:06','create','2024-08-22 16:38:08','2024-08-22 16:38:08'),(1033418995757723648,'lyt',100301,100006,'182653933920',101,'OpenAI 增加使用次数','2024-08-22 08:38:09','create','2024-08-22 16:38:09','2024-08-22 16:38:09'),(1033418999939444736,'lyt',100301,100006,'182653934338',101,'OpenAI 增加使用次数','2024-08-22 08:38:10','create','2024-08-22 16:38:10','2024-08-22 16:38:10'),(1033419004498653184,'lyt',100301,100006,'182653934795',101,'OpenAI 增加使用次数','2024-08-22 08:38:11','create','2024-08-22 16:38:11','2024-08-22 16:38:11'),(1033419008764260352,'lyt',100301,100006,'182653935221',101,'OpenAI 增加使用次数','2024-08-22 08:38:12','create','2024-08-22 16:38:12','2024-08-22 16:38:12'),(1033419013260554240,'lyt',100301,100006,'182653935670',101,'OpenAI 增加使用次数','2024-08-22 08:38:13','create','2024-08-22 16:38:13','2024-08-22 16:38:13'),(1033419017492606976,'lyt',100301,100006,'182653936094',101,'OpenAI 增加使用次数','2024-08-22 08:38:14','create','2024-08-22 16:38:14','2024-08-22 16:38:14'),(1033419021930180608,'lyt',100301,100006,'182653936538',101,'OpenAI 增加使用次数','2024-08-22 08:38:15','create','2024-08-22 16:38:15','2024-08-22 16:38:15'),(1033419026237730816,'lyt',100301,100006,'182653936968',101,'OpenAI 增加使用次数','2024-08-22 08:38:16','create','2024-08-22 16:38:16','2024-08-22 16:38:16'),(1033419030545281024,'lyt',100301,100006,'182653937399',101,'OpenAI 增加使用次数','2024-08-22 08:38:17','create','2024-08-22 16:38:17','2024-08-22 16:38:17'),(1033419035117072384,'lyt',100301,100006,'182653937857',101,'OpenAI 增加使用次数','2024-08-22 08:38:18','create','2024-08-22 16:38:18','2024-08-22 16:38:18');
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

-- Dump completed on 2024-08-22 16:44:05
