-- MySQL dump 10.13  Distrib 8.0.24, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: jthink
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jk_comments`
--

DROP TABLE IF EXISTS `jk_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment_post_ID` bigint DEFAULT NULL COMMENT '对应文章ID',
  `comment_author` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '评论者',
  `comment_author_email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '评论者邮箱',
  `comment_author_url` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '评论者网址',
  `comment_author_IP` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '评论者IP',
  `comment_date` datetime DEFAULT NULL,
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '评论正文',
  `comment_approved` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '评论是否被批准',
  `comment_parent_id` bigint DEFAULT NULL COMMENT '父评论ID',
  `taxonomy` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '评论的类型分类法',
  `user_id` int DEFAULT NULL COMMENT '评论者用户ID（不一定存在）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_comments`
--

LOCK TABLES `jk_comments` WRITE;
/*!40000 ALTER TABLE `jk_comments` DISABLE KEYS */;
INSERT INTO `jk_comments` VALUES (1,22,'admin','admin@123.com',NULL,NULL,NULL,'comment1','approved',NULL,'post',1,'2021-02-24 08:58:19',NULL),(2,22,'admin','admin@123.com',NULL,NULL,NULL,'comment2','approved',NULL,'post',2,'2021-02-24 08:58:19',NULL),(3,22,'admin','admin@123.com',NULL,NULL,NULL,'comment3','auditing',NULL,'post',1,'2021-02-24 08:58:19',NULL),(4,24,'admin','admin@123.com',NULL,NULL,NULL,'comment4','approved',NULL,'post',1,'2021-02-24 08:58:19',NULL),(5,22,'管理员admin',NULL,NULL,NULL,NULL,'我想要试试这个评论哦 呢','auditing',NULL,'post',1,'2021-03-26 02:24:26',NULL),(6,33,'管理员admin',NULL,NULL,NULL,NULL,NULL,'auditing',NULL,'post',1,'2021-03-26 02:48:44',NULL),(7,22,'管理员admin',NULL,NULL,NULL,NULL,'4看看这是要的','auditing',NULL,'post',1,'2021-03-26 02:54:00',NULL),(8,22,'管理员admin',NULL,NULL,NULL,NULL,'我来必发一个','auditing',NULL,'post',1,'2021-03-26 03:03:04',NULL),(9,22,'管理员admin',NULL,NULL,NULL,NULL,'你好 我的评论','auditing',NULL,'post',1,'2021-03-26 03:03:41',NULL),(10,22,'管理员admin',NULL,NULL,NULL,NULL,'完成后刷新评论','refuesd',NULL,'post',18,'2021-03-26 03:03:59','2021-04-06 06:45:41'),(11,24,'管理员admin',NULL,NULL,NULL,NULL,'这是另一个文章 的','approved',NULL,'post',18,'2021-03-26 03:04:21','2021-04-06 07:06:15'),(15,22,'管理员admin',NULL,NULL,NULL,NULL,'fdasfdsaAAAACCCC','approved',NULL,'post',18,'2021-04-06 05:55:41','2021-04-06 06:36:22'),(16,33,NULL,NULL,NULL,NULL,NULL,'管理员评论了','approved',NULL,'post',NULL,'2021-04-08 08:19:30',NULL),(17,33,'freeedom',NULL,NULL,NULL,NULL,'fdasfdsafdsa','auditing',NULL,'product',18,'2021-05-17 01:17:55',NULL),(18,24,'freeedom',NULL,NULL,NULL,NULL,'wagtestdfdsafdsa','auditing',NULL,'product',18,'2021-05-17 01:27:57',NULL),(19,24,'freeedom',NULL,NULL,NULL,NULL,'我的新手评论了','auditing',NULL,'product',18,'2021-05-17 01:37:12',NULL),(20,31,'freeedom',NULL,NULL,NULL,NULL,'评论商品内容','auditing',NULL,'product',18,'2021-05-17 01:56:14',NULL),(21,31,'freeedom',NULL,NULL,NULL,NULL,'我感觉真的是不错的','auditing',NULL,'product',18,'2021-05-17 01:56:40',NULL),(22,31,'freeedom',NULL,NULL,NULL,NULL,'看评论内容 的了的柘城','approved',NULL,'product',18,'2021-05-17 02:04:29','2021-05-17 02:05:06'),(23,82,'freeedom',NULL,NULL,NULL,NULL,'您的评论魂牵梦萦地大','auditing',NULL,'product',18,'2021-05-17 14:23:45',NULL),(24,82,'freeedom',NULL,NULL,NULL,NULL,'aadsadsa','auditing',NULL,'product',18,'2021-05-17 14:25:31',NULL),(25,82,'freeedom',NULL,NULL,NULL,NULL,'您的评论FDSAF','auditing',NULL,'product',18,'2021-05-17 14:29:37',NULL),(27,44,'freeedom',NULL,NULL,NULL,NULL,'效果颜色评论','approved',NULL,'product',18,'2021-05-18 01:00:52','2021-05-18 01:02:04'),(28,44,'freeedom',NULL,NULL,NULL,NULL,'您的评论今天 的098','approved',NULL,'product',18,'2021-05-18 02:43:53','2021-05-18 02:44:31');
/*!40000 ALTER TABLE `jk_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_coupon`
--

DROP TABLE IF EXISTS `jk_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_coupon` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '例如：无门槛50元优惠券 | 单品最高减2000元''',
  `icon` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` tinyint DEFAULT NULL COMMENT '1满减券  2叠加满减券  3无门槛券  ',
  `with_amount` decimal(10,2) DEFAULT NULL COMMENT '满多少金额',
  `with_award` tinyint(1) DEFAULT NULL COMMENT '是否是推广奖励券',
  `with_owner` tinyint(1) DEFAULT NULL COMMENT '是不是只有领取人可用，如果不是，领取人可以随便给其他人用',
  `amount` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '优惠券金额',
  `quota` int unsigned NOT NULL COMMENT '配额：发券数量',
  `take_count` int unsigned DEFAULT '0' COMMENT '已领取的优惠券数量',
  `used_count` int unsigned DEFAULT '0' COMMENT '已使用的优惠券数量',
  `start_time` datetime DEFAULT NULL COMMENT '发放开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '发放结束时间',
  `valid_type` tinyint DEFAULT NULL COMMENT '时效:1绝对时效（XXX-XXX时间段有效）  2相对时效（领取后N天有效）',
  `valid_start_time` datetime DEFAULT NULL COMMENT '使用开始时间',
  `valid_end_time` datetime DEFAULT NULL COMMENT '使用结束时间',
  `valid_days` int DEFAULT NULL COMMENT '自领取之日起有效天数',
  `create_user_id` int unsigned DEFAULT NULL COMMENT '创建用户',
  `cou_status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态 0锁定 1有效',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '兑换码(可选，如果有值说明支持兑换码领取此类型优惠券)',
  `products_type` smallint DEFAULT NULL COMMENT '商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。',
  `products_value` varchar(1023) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '[]' COMMENT '商品限制值，products_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。',
  `options` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='优惠券类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_coupon`
--

LOCK TABLES `jk_coupon` WRITE;
/*!40000 ALTER TABLE `jk_coupon` DISABLE KEYS */;
INSERT INTO `jk_coupon` VALUES (2,'50元满减','',2,34.00,NULL,NULL,44.00,34,0,0,'2021-04-29 12:49:00','2021-04-18 05:50:00',NULL,'2021-04-09 12:49:00','2021-04-09 12:49:00',NULL,1,'1','',0,NULL,'这个是一个50元起用的满减叠加券','2021-04-29 12:52:54','2021-05-01 14:21:53'),(4,'武术器材','',1,33.00,NULL,NULL,22.00,22,0,0,'2021-04-29 12:58:00','2021-04-29 12:58:00',NULL,'2021-05-08 12:58:00','2021-04-11 12:58:00',NULL,1,'1','3455',0,NULL,NULL,'2021-04-29 13:00:24','2021-04-29 13:00:24');
/*!40000 ALTER TABLE `jk_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_coupon_member`
--

DROP TABLE IF EXISTS `jk_coupon_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_coupon_member` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` int unsigned DEFAULT NULL COMMENT '类型ID',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '优惠券标题',
  `user_id` int unsigned DEFAULT NULL COMMENT '领取用户ID',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `cou_status` tinyint DEFAULT NULL COMMENT '状态 1 有人领取、正常使用  2 未有人领取不能使用  3 已经使用，不能被再次使用  9 已经被认为标识不可用',
  `used_order_id` int DEFAULT NULL COMMENT '订单ID',
  `order_ns` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `user_payment_id` int DEFAULT NULL COMMENT '支付的ID',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `get_time` datetime DEFAULT NULL COMMENT '领取时间',
  `send_uid` int DEFAULT NULL COMMENT '如果是由后台管理员发放，则记录下由哪位管理员发放的。前台用户自己领取的，此字段将为空。',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，创建时可能不会有人领取',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='优惠券领取记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_coupon_member`
--

LOCK TABLES `jk_coupon_member` WRITE;
/*!40000 ALTER TABLE `jk_coupon_member` DISABLE KEYS */;
INSERT INTO `jk_coupon_member` VALUES (9,2,'50元满减',18,'undead',1,NULL,NULL,NULL,NULL,'2021-04-29 14:50:43',1,'2021-04-29 14:50:43'),(10,4,'武术器材',18,'undead',1,NULL,NULL,NULL,NULL,'2021-04-29 14:51:14',1,'2021-04-29 14:51:14');
/*!40000 ALTER TABLE `jk_coupon_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_delivery_company`
--

DROP TABLE IF EXISTS `jk_delivery_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_delivery_company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '快递公司名称',
  `telphone` varchar(45) DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='快递公司维护';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_delivery_company`
--

LOCK TABLES `jk_delivery_company` WRITE;
/*!40000 ALTER TABLE `jk_delivery_company` DISABLE KEYS */;
INSERT INTO `jk_delivery_company` VALUES (1,'顺丰快递','90044','2020-02-04 07:08:58'),(2,'圆通快递','89434','2020-02-04 07:08:58'),(3,'A物流公司','2345','2020-02-04 07:08:58');
/*!40000 ALTER TABLE `jk_delivery_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_image`
--

DROP TABLE IF EXISTS `jk_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `src` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `order_num` int DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_image`
--

LOCK TABLES `jk_image` WRITE;
/*!40000 ALTER TABLE `jk_image` DISABLE KEYS */;
INSERT INTO `jk_image` VALUES (7,'articlebtn11.png','/Supermarket/image/catalog/demo/product/fashion/1.jpg',1,'2021-04-16 02:35:27'),(8,'erweima.jpg','/Supermarket/image/catalog/demo/product/fashion/2.jpg',2,'2021-04-16 02:35:27'),(9,'test.jpg','/Supermarket/image/catalog/demo/product/fashion/3.jpg',3,'2021-04-16 02:38:00'),(10,'test.jpg','/Supermarket/image/catalog/demo/product/fashion/4.jpg',4,'2021-04-16 02:38:00'),(11,'about_01.jpg','/Supermarket/image/catalog/demo/product/fashion/5.jpg',5,'2021-04-16 02:38:00'),(12,'fut2.jpg','/Supermarket/image/catalog/demo/product/270/fu2.jpg',6,'2021-04-16 02:38:00'),(13,'ht22.jpg','/Supermarket/image/catalog/demo/product/270/h2.jpg',7,'2021-04-16 02:38:00'),(14,'e11.jpg','/Supermarket/image/catalog/demo/product/270/e1.jpg',8,'2021-04-16 02:38:00');
/*!40000 ALTER TABLE `jk_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_links`
--

DROP TABLE IF EXISTS `jk_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_links` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link_url` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '链接URL\n',
  `link_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '链接标题',
  `link_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '链接图片',
  `link_target` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '链接打开方式',
  `link_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '链接描述',
  `link_visible` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否可见（Y/N）',
  `link_owner` int DEFAULT NULL COMMENT '添加者用户ID',
  `link_rating` int DEFAULT NULL COMMENT '评分等级',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='链接信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_links`
--

LOCK TABLES `jk_links` WRITE;
/*!40000 ALTER TABLE `jk_links` DISABLE KEYS */;
INSERT INTO `jk_links` VALUES (1,'http://www.google.cn','公司1',NULL,'_blank','测试链接','y',NULL,NULL,'2021-02-24 08:58:19','2021-02-24 09:16:20'),(2,'https://www.oschina.net','开源社区','','_blank','开源社区','n',NULL,NULL,'2021-02-26 00:39:08','2021-03-24 01:03:14'),(3,'https://www.jfinal.com','Jfinal社区','','_blank','Jfinal社区','n',NULL,NULL,'2021-03-06 03:14:37','2021-03-24 01:03:19'),(79,'/category/73','促销分类1','/Supermarket/image/catalog/slideshow/home1/slider-1.jpg','_blank','','n',NULL,NULL,'2021-05-14 00:14:41',NULL),(80,'/category/74','促销分类2','/Supermarket/image/catalog/slideshow/home1/slider-2.jpg','_blank','促销分类2','n',NULL,NULL,'2021-05-14 00:15:12',NULL),(81,'/category/75','促销分类3','/Supermarket/image/catalog/slideshow/home1/slider-3.jpg','_blank','促销分类3','n',NULL,NULL,'2021-05-14 00:15:31',NULL);
/*!40000 ALTER TABLE `jk_links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_member`
--

DROP TABLE IF EXISTS `jk_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sstate` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态 0锁定 1有效',
  `ssex` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '性别 0男 1女',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '个人描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_member`
--

LOCK TABLES `jk_member` WRITE;
/*!40000 ALTER TABLE `jk_member` DISABLE KEYS */;
INSERT INTO `jk_member` VALUES (18,'undead','freeedom','MGN1MWxmOTQ1NDJiMWI0ZTk4NGVkOTBiMWZjNGJjNzFmN2FlNDM0NTc1NGE4YjVkMzg4MWVmYzVlNmQ2YWEwMTJkMThj','freeedom@134.com','14586594459','1','1',NULL,'2021-04-12 06:21:08','2021-04-30 04:31:12',NULL);
/*!40000 ALTER TABLE `jk_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_member_address`
--

DROP TABLE IF EXISTS `jk_member_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_member_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `contact_name` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_mobile` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `zipcode` varchar(45) DEFAULT NULL COMMENT '邮编',
  `province` varchar(45) DEFAULT NULL COMMENT '省',
  `city` varchar(45) DEFAULT NULL COMMENT '市 ',
  `districts` varchar(45) DEFAULT NULL COMMENT '区县',
  `addr_details` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户个人地址信息表(一个用户存在多个快递信息)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_member_address`
--

LOCK TABLES `jk_member_address` WRITE;
/*!40000 ALTER TABLE `jk_member_address` DISABLE KEYS */;
INSERT INTO `jk_member_address` VALUES (4,18,'刘先生','14586495568','100945','北京','北京','北京','朝阳区XX路XXX楼1单元893','2021-04-21 12:34:56'),(13,18,'李先生','1368183940','0968885','北京市','北京城区','我的街道地址','1368183940','2021-04-25 10:19:59'),(14,18,'来行生','14586795543','059000','北京市','北京城区','我的街道地址234','1368183940','2021-04-26 07:31:25');
/*!40000 ALTER TABLE `jk_member_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_member_favourite`
--

DROP TABLE IF EXISTS `jk_member_favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_member_favourite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL COMMENT '收藏类型(product,post)',
  `title` varchar(100) DEFAULT NULL COMMENT '内容标题',
  `favourite_id` int DEFAULT NULL COMMENT '所收藏的内容的主键ID',
  `user_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_member_favourite`
--

LOCK TABLES `jk_member_favourite` WRITE;
/*!40000 ALTER TABLE `jk_member_favourite` DISABLE KEYS */;
INSERT INTO `jk_member_favourite` VALUES (12,'product','商品1',31,18,'2021-05-10 08:19:34');
/*!40000 ALTER TABLE `jk_member_favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_member_order`
--

DROP TABLE IF EXISTS `jk_member_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_member_order` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `ns` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `product_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品的类型',
  `order_title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商品的名称',
  `order_summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `buyer_id` int unsigned DEFAULT NULL COMMENT '购买人',
  `buyer_nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人昵称',
  `buyer_msg` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户留言',
  `delivery_fee` decimal(10,2) DEFAULT NULL COMMENT '快递费',
  `order_total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额，购买人员应该付款的金额(包含快递费delivery_fee)',
  `order_real_amount` decimal(10,2) DEFAULT NULL COMMENT '订单的真实金额，销售人员可以在后台修改支付金额，一般情况下 order_real_amount = order_total_amount',
  `coupon_user_id` int DEFAULT NULL COMMENT '优惠券ID',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_status` tinyint DEFAULT NULL COMMENT '支付状态：1未付款、 2支付宝、3微信支付 、4网银（线上支付)5、余额支付',
  `pay_success_amount` decimal(10,2) DEFAULT NULL COMMENT '支付成功的金额',
  `pay_success_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_success_proof` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付证明，手动入账时需要截图',
  `pay_success_remarks` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付备注',
  `payment_id` int unsigned DEFAULT NULL COMMENT '支付记录',
  `payment_outer_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方订单号',
  `payment_outer_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方支付用户，一般情况下是用户的openId',
  `delivery_addr_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人地址',
  `delivery_addr_mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人手机号（电话）',
  `delivery_addr_province` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人省',
  `delivery_addr_city` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人的城市',
  `delivery_addr_district` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人的区（县）',
  `delivery_addr_detail` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人的详细地址',
  `delivery_addr_zipcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人地址邮政编码',
  `invoice_id` int unsigned DEFAULT NULL COMMENT '发票',
  `invoice_status` tinyint DEFAULT NULL COMMENT '发票开具状态：1 未申请发票、 2 发票申请中、 3 发票开具中、 8 无需开具发票、 9发票已经开具',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '管理员后台备注',
  `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT 'json字段扩展',
  `trade_status` tinyint DEFAULT NULL COMMENT '交易状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束',
  `del_status` tinyint DEFAULT NULL COMMENT '删除状态：1 正常 ，2 回收站 3 已经删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ns` (`ns`),
  KEY `buyer_id` (`buyer_id`),
  KEY `payment_id` (`payment_id`),
  KEY `buyer_status` (`buyer_id`,`trade_status`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_member_order`
--

LOCK TABLES `jk_member_order` WRITE;
/*!40000 ALTER TABLE `jk_member_order` DISABLE KEYS */;
INSERT INTO `jk_member_order` VALUES (52,'FZ590262601087463424',NULL,'商品3','2021-05-13 16:53undead生成订单 \n <br/>',18,'undead',NULL,10.00,99.00,99.00,NULL,0.00,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'刘先生','14586495568','北京','北京','北京','朝阳区XX路XXX楼1单元893','100945',NULL,1,'红色 大号',NULL,1,NULL,'2021-05-13 08:53:56','2021-05-13 08:53:56'),(53,'FZ590263099182034944',NULL,'头盔001','2021-05-13 16:55&nbsp;&nbsp;undead生成订单 \n <br/>',18,'undead',NULL,10.00,43.00,43.00,NULL,0.00,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'刘先生','14586495568','北京','北京','北京','朝阳区XX路XXX楼1单元893','100945',NULL,1,NULL,NULL,1,NULL,'2021-05-13 08:55:55','2021-05-13 08:55:55'),(54,'UP590263299829149696',NULL,'护腰001','2021-05-13 16:56&nbsp;&nbsp;undead&nbsp;&nbsp;生成订单 \n <br/>2021-05-13 16:57&nbsp;&nbsp;管理员更新订单状态为交易完成\n <br/>2021-05-13 17:09管理员(admin)更新快递状态为交易完成\n <br/>',18,'undead',NULL,10.00,56.00,56.00,NULL,0.00,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'刘先生','14586495568','北京','北京','北京','朝阳区XX路XXX楼1单元893','100945',NULL,1,NULL,NULL,2,NULL,'2021-05-13 08:56:43','2021-05-13 08:57:35'),(55,'OP591444934981332992',NULL,'商品2','2021-05-16 23:12&nbsp;&nbsp;undead&nbsp;&nbsp;生成订单 \n <br/>2021-05-17 13:51&nbsp;&nbsp;管理员更新订单状态为交易完成\n <br/>',18,'undead',NULL,10.00,99.00,99.00,NULL,0.00,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'刘先生','14586495568','北京','北京','北京','朝阳区XX路XXX楼1单元893','100945',NULL,1,NULL,NULL,2,NULL,'2021-05-16 15:12:07','2021-05-17 05:51:34');
/*!40000 ALTER TABLE `jk_member_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_member_order_delivery`
--

DROP TABLE IF EXISTS `jk_member_order_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_member_order_delivery` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作用户名',
  `user_id` int DEFAULT NULL COMMENT '操作用户ID',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `delivery_id` int DEFAULT NULL COMMENT '快递公司ID',
  `company` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '快递公司',
  `delivery_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '快递单号',
  `start_time` datetime DEFAULT NULL COMMENT '快递发货时间',
  `finish_time` datetime DEFAULT NULL COMMENT '快递送达时间',
  `addr_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人地址',
  `addr_mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人手机号（电话）',
  `addr_province` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人省',
  `addr_city` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人的城市',
  `addr_district` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人的区（县）',
  `addr_detail` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人的详细地址',
  `addr_zipcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人地址邮政编码',
  `delivery_status` tinyint DEFAULT NULL COMMENT '发货状态1.已发货(待运输)2.运输中3.已收货',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '运输信息备注',
  `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT 'json字段扩展',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='发货信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_member_order_delivery`
--

LOCK TABLES `jk_member_order_delivery` WRITE;
/*!40000 ALTER TABLE `jk_member_order_delivery` DISABLE KEYS */;
INSERT INTO `jk_member_order_delivery` VALUES (19,'admin',1,54,1,'顺丰快递','SF00234','2021-05-13 08:57:18',NULL,'刘先生','14586495568','北京','北京','北京','朝阳区XX路XXX楼1单元893','100945',2,'fdsafdasfdas',NULL,NULL,'2021-05-13 08:57:18'),(20,'admin',1,55,1,'顺丰快递','SF00134','2021-05-17 05:51:50',NULL,'刘先生','14586495568','北京','北京','北京','朝阳区XX路XXX楼1单元893','100945',1,'',NULL,NULL,'2021-05-17 05:51:50');
/*!40000 ALTER TABLE `jk_member_order_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_member_order_invoice`
--

DROP TABLE IF EXISTS `jk_member_order_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_member_order_invoice` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发票类型(普通发票、增值税专用发票)',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发票抬头',
  `content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发票内容',
  `identity` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '纳税人识别号',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单位名称',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发票收取人手机号',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发票收取人邮箱',
  `invoice_status` tinyint DEFAULT NULL COMMENT '发票状态',
  `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT 'json字段扩展',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='发票信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_member_order_invoice`
--

LOCK TABLES `jk_member_order_invoice` WRITE;
/*!40000 ALTER TABLE `jk_member_order_invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `jk_member_order_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_member_order_item`
--

DROP TABLE IF EXISTS `jk_member_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_member_order_item` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int unsigned NOT NULL COMMENT '订单id',
  `order_ns` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单号',
  `buyer_id` int unsigned NOT NULL COMMENT '购买人',
  `buyer_nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买人昵称',
  `buyer_msg` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户留言（备注）',
  `seller_id` int unsigned DEFAULT NULL COMMENT '卖家id',
  `dist_user_id` int unsigned DEFAULT NULL COMMENT '分销员',
  `dist_amount` decimal(10,2) DEFAULT NULL COMMENT '分销金额',
  `product_id` int unsigned DEFAULT NULL COMMENT '产品id',
  `product_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品的类别，默认是 product ，但是未来可能是 模板、文件、视频等等...',
  `product_type_text` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品标题',
  `product_summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_sku_id` int DEFAULT NULL COMMENT '商品规格表(t_product_sku)id',
  `product_spec` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品规格(不同规格价格不同)',
  `product_thumbnail` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品缩略图',
  `product_link` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品链接',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '产品价格',
  `product_count` int DEFAULT NULL COMMENT '产品数量',
  `with_virtual` tinyint(1) DEFAULT NULL COMMENT '是否是虚拟产品，1是，0不是。虚拟产品支付完毕后立即交易完成。是虚拟产品，虚拟产品支付完毕后立即交易完成',
  `with_refund` tinyint(1) DEFAULT NULL COMMENT '是否支持退款，1支持，0不支持。',
  `delivery_cost` decimal(10,2) DEFAULT NULL COMMENT '快递费',
  `delivery_id` int unsigned DEFAULT NULL COMMENT '快递单 id',
  `other_cost` decimal(10,2) DEFAULT NULL COMMENT '其它费用',
  `other_cost_remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '其它费用说明',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '具体金额 = 产品价格+运费+其他费用- 分销金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额 = 产品价格+运费+其他价格',
  `view_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '查看的网址路径，访问时时，会添加orderid',
  `view_text` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '查看的文章内容，比如：查看、下载',
  `view_effective_time` int unsigned DEFAULT NULL COMMENT '可访问的有效时间，单位秒',
  `comment_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论的路径',
  `invoice_id` int unsigned DEFAULT NULL COMMENT '发票',
  `invoice_status` tinyint DEFAULT NULL,
  `item_status` tinyint DEFAULT NULL COMMENT '状态：1交易中、 2交易完成（但是可以申请退款） 、3取消交易 、4申请退款、 5拒绝退款、 6退款中、 7退款完成、 9交易结束',
  `refund_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '退款订单号',
  `refund_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `refund_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '退款描述',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_member_order_item`
--

LOCK TABLES `jk_member_order_item` WRITE;
/*!40000 ALTER TABLE `jk_member_order_item` DISABLE KEYS */;
INSERT INTO `jk_member_order_item` VALUES (54,52,'FZ590262601087463424',18,'undead',NULL,NULL,NULL,NULL,33,'product','产品','商品3',NULL,NULL,NULL,NULL,NULL,89.00,1,NULL,NULL,NULL,NULL,NULL,NULL,89.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,53,'FZ590263099182034944',18,'undead',NULL,NULL,NULL,NULL,71,'product','产品','头盔001',NULL,NULL,NULL,NULL,NULL,33.00,1,NULL,NULL,NULL,NULL,NULL,NULL,33.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,54,'UP590263299829149696',18,'undead',NULL,NULL,NULL,NULL,72,'product','产品','护腰001',NULL,NULL,NULL,NULL,NULL,46.00,1,NULL,NULL,NULL,NULL,NULL,NULL,46.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,55,'OP591444934981332992',18,'undead',NULL,NULL,NULL,NULL,32,'product','产品','商品2',NULL,NULL,NULL,NULL,NULL,89.00,1,NULL,NULL,NULL,NULL,NULL,NULL,89.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `jk_member_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_options`
--

DROP TABLE IF EXISTS `jk_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_options` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int DEFAULT '0' COMMENT '博客ID，用于多用户博客，默认0',
  `option_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `option_value` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `autoload` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '在WordPress载入时自动载入（yes/no）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='基本配置信息表，通常通过get_option来操作，该表通常作为插件存储数据的一个地方。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_options`
--

LOCK TABLES `jk_options` WRITE;
/*!40000 ALTER TABLE `jk_options` DISABLE KEYS */;
INSERT INTO `jk_options` VALUES (1,0,'site_theme','defaults','true','2021-02-20 17:23:03',NULL),(2,0,'site_title','jthink网上商城系统','true','2021-02-20 17:23:03',NULL),(3,0,'site_logo','/home/images/logo.png','true','2021-02-20 17:23:03',NULL),(4,0,'site_keywords','cms,体育,汽车','true','2021-02-20 17:23:03',NULL),(5,0,'descriptions','jthink博客系统 是一款基本SpringBoot框架为基础，结合mybatis 、satoken、bootstrap模板组合完成的一套简单的博客管理系统','true','2021-02-20 17:23:03',NULL),(6,0,'site_copyright','Jthink版权所有','true','2021-02-20 17:23:03',NULL),(7,0,'site_icp','ICP备00000XXX号','true','2021-02-20 17:23:03',NULL),(8,0,'site_ switch','on','true','2021-02-20 17:23:03',NULL);
/*!40000 ALTER TABLE `jk_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_permissions`
--

DROP TABLE IF EXISTS `jk_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_permissions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `pid` int DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `perms` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `icon` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '类型 0目录 1菜单 2按钮',
  `is_show` int DEFAULT '1' COMMENT '0隐藏,1显示',
  `order_num` int DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_permissions`
--

LOCK TABLES `jk_permissions` WRITE;
/*!40000 ALTER TABLE `jk_permissions` DISABLE KEYS */;
INSERT INTO `jk_permissions` VALUES (11,'系统设置',0,'','','mdi mdi-account-network',0,1,0,'2020-12-27 03:09:51','2021-05-21 14:42:51'),(12,'用户管理',11,'/system/user/index','','',1,1,0,'2020-12-27 03:12:00','2021-04-08 15:20:20'),(13,'角色管理',11,'/system/role/index','','mdi mdi-account-multiple-plus',1,1,0,'2020-12-27 03:17:45','2021-04-08 15:20:26'),(14,'权限管理',11,'/system/permission/index','','mdi mdi-air-conditioner',1,1,0,'2020-12-27 03:18:35','2021-04-06 01:00:11'),(15,'内容管理',0,'','','mdi mdi-book-open-variant',0,1,0,'2020-12-27 03:19:28',NULL),(16,'新增用户',12,'','sys:user:add','',2,1,1,'2020-12-28 09:17:43','2021-02-04 01:05:18'),(17,'文章管理',15,'/system/cms/post/index','','mdi mdi-arrange-bring-forward',1,1,1,'2020-12-28 10:34:54','2021-02-19 14:25:24'),(18,'参数配置',0,'/system/setting/index','','mdi mdi-view-parallel',1,1,2,'2021-01-01 15:05:48','2021-04-08 15:20:33'),(19,'查询角色',13,'','sys:role:list','',2,1,1,'2021-01-01 15:58:57',NULL),(20,'用户列表',12,'','sys:user:list','',2,1,1,'2021-01-01 15:59:28',NULL),(21,'页面管理',15,'/system/cms/page/index','','mdi mdi-format-page-break',1,1,3,'2021-01-02 12:25:50','2021-02-25 01:50:45'),(22,'分类管理',15,'/system/cms/category/index','','mdi mdi-content-duplicate',1,1,0,'2021-01-03 15:08:24','2021-04-08 15:21:07'),(30,'链接管理',55,'/system/appearance/linkcategory','','mdi mdi-account-check',1,1,2,'2021-02-22 07:01:40','2021-04-08 08:35:40'),(31,'导航菜单',55,'/system/appearance/navcategory/index','','mdi mdi-arrange-bring-to-front',1,1,4,'2021-02-25 07:23:22','2021-04-08 08:35:13'),(33,'系统监控',0,'/system/sysinfo','','mdi mdi-airplay',1,1,1,'2021-03-10 09:25:24','2021-04-08 15:20:39'),(34,'模板管理',55,'/system/appearance/themeList','','mdi mdi-account-card-details',1,1,3,'2021-03-19 14:55:14','2021-04-12 07:25:57'),(35,'新增',22,'','category:add','',2,1,1,'2021-04-06 00:49:06',NULL),(36,'更新分类',22,'','category:update','',2,1,2,'2021-04-06 00:49:42',NULL),(37,'删除',22,'','category:delete','',2,1,4,'2021-04-06 00:50:06',NULL),(38,'新增',21,'','page:add','',2,1,1,'2021-04-06 00:57:35',NULL),(39,'更新',21,'','page:update','',2,1,3,'2021-04-06 00:58:01',NULL),(40,'删除',21,'','page:delete','',2,1,4,'2021-04-06 00:58:15',NULL),(41,'新增',17,'','post:add','',2,1,1,'2021-04-06 00:59:07',NULL),(42,'更新',17,'','post:update','',2,1,5,'2021-04-06 00:59:23',NULL),(43,'删除',17,'','post:delete','',2,1,NULL,'2021-04-06 00:59:38',NULL),(44,'新增',30,'','linkcat:add','',2,1,1,'2021-04-06 01:08:44',NULL),(45,'更新',30,'','linkcat:update','',2,1,3,'2021-04-06 01:09:03',NULL),(46,'删除',30,'','linkcat:delete','',2,1,4,'2021-04-06 01:09:21',NULL),(47,'链接内容',30,'/system/cms/jlink','','',1,0,1,'2021-04-06 01:10:26','2021-04-06 03:01:59'),(48,'新增',47,'','link:add','',2,1,1,'2021-04-06 01:11:38',NULL),(49,'更新',47,'','link:update','',2,1,2,'2021-04-06 01:11:51',NULL),(50,'删除',47,'','link:delete','',2,1,3,'2021-04-06 01:12:06',NULL),(51,'超级管理员',NULL,'','superadmin','',2,1,1,'2021-04-06 01:17:15',NULL),(52,'商城管理',NULL,'','','mdi mdi-shopping',0,1,1,'2021-04-08 07:26:50','2021-04-08 07:28:25'),(53,'分类管理',52,'/system/shop/category','productcategory','mdi mdi-content-duplicate',1,1,NULL,'2021-04-08 07:28:07','2021-04-08 07:29:01'),(54,'商品管理',52,'/system/shop/product','productsmanage','mdi mdi-medical-bag',1,1,2,'2021-04-08 07:49:09',NULL),(55,'外观',NULL,'','','mdi mdi-alarm-bell',0,1,2,'2021-04-08 08:32:40',NULL),(56,'会员管理',0,'/system/member/index','','mdi mdi-account-multiple-outline',1,1,NULL,'2021-04-12 06:15:49',NULL),(57,'订单管理',52,'/system/shop/order','','',1,1,NULL,'2021-04-27 23:02:19',NULL),(58,'优惠券管理',52,'/system/shop/coupon/index','','',1,1,NULL,'2021-04-29 08:10:23','2021-04-29 11:14:24'),(59,'规格维护',52,'/system/shop/spu/index','','',1,1,NULL,'2021-05-04 10:21:02',NULL);
/*!40000 ALTER TABLE `jk_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_post_image`
--

DROP TABLE IF EXISTS `jk_post_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_post_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `img_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `imguq` (`post_id`,`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_post_image`
--

LOCK TABLES `jk_post_image` WRITE;
/*!40000 ALTER TABLE `jk_post_image` DISABLE KEYS */;
INSERT INTO `jk_post_image` VALUES (21,32,7,'2021-04-16 02:35:27'),(22,32,8,'2021-04-16 02:35:27'),(23,32,9,'2021-04-16 02:35:27'),(24,32,10,'2021-04-16 02:35:27'),(25,32,11,'2021-04-16 02:35:27'),(36,81,9,'2021-04-16 02:35:27'),(37,81,10,'2021-04-16 02:35:27'),(38,81,11,'2021-04-16 02:35:27'),(39,81,12,'2021-04-16 02:35:27'),(40,81,13,'2021-04-16 02:35:27'),(41,81,14,'2021-04-16 02:35:27'),(47,44,7,'2021-05-21 23:19:48'),(48,44,8,'2021-05-21 23:19:48'),(49,44,9,'2021-05-21 23:19:48'),(50,44,10,'2021-05-21 23:19:48'),(51,44,11,'2021-05-21 23:19:48'),(52,44,12,'2021-05-21 23:19:48'),(57,31,7,'2021-05-22 02:26:06'),(58,31,8,'2021-05-22 02:26:06'),(59,31,9,'2021-05-22 02:26:06'),(60,31,10,'2021-05-22 02:26:06'),(61,31,11,'2021-05-22 02:26:06'),(62,45,8,'2021-05-22 02:32:44'),(63,45,9,'2021-05-22 02:32:44'),(64,45,10,'2021-05-22 02:32:44'),(65,45,12,'2021-05-22 02:32:44');
/*!40000 ALTER TABLE `jk_post_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_postmeta`
--

DROP TABLE IF EXISTS `jk_postmeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_postmeta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint DEFAULT NULL COMMENT '对应文章ID',
  `meta_key` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '键名',
  `meta_value` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '键值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文章额外数据表，例如文章浏览次数，文章的自定义字段等都存储在这里。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_postmeta`
--

LOCK TABLES `jk_postmeta` WRITE;
/*!40000 ALTER TABLE `jk_postmeta` DISABLE KEYS */;
INSERT INTO `jk_postmeta` VALUES (1,22,'articlePreview','/aaaa/ddd.jpg');
/*!40000 ALTER TABLE `jk_postmeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_posts`
--

DROP TABLE IF EXISTS `jk_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_author` int DEFAULT NULL COMMENT '对应作者ID',
  `post_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '正文',
  `post_title` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `seo_keywords` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'seo关键字',
  `post_excerpt` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '摘录',
  `post_status` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'wordpress发布文章时会有各种文章状态。在采集时可以设置为草稿或者待审状态供编辑进行修改校对\n1、pending：待审\n2、draft：草稿\n3、auto-draft：自动保存的草稿\n4、inherit：修订版本\n5、trash：回收站\n6、publish：已发布\n7、future：定时\n8、private：私有',
  `comment_status` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '评论状态（open/closed）',
  `ping_status` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'PING状态（open/closed）',
  `post_password` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文章密码',
  `post_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `pinged` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '已经PING过的链接',
  `post_parent` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '父文章，主要用于PAGE',
  `post_order` int DEFAULT '0' COMMENT '排序ID',
  `post_type` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文章类型（post/page/nav_menu_item）',
  `img_preview` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '预览图片地址',
  `template` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '使用的模板',
  `post_mime_type` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'MIME类型',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
  `origin_price` decimal(10,2) DEFAULT '0.00' COMMENT '原始价格',
  `stocks` int DEFAULT '0' COMMENT '库存',
  `sale_counts` int DEFAULT '0' COMMENT '销量',
  `view_counts` int DEFAULT '0' COMMENT '点击量',
  `comment_count` int DEFAULT '0' COMMENT '评论总数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='存储文章（包括页面、上传文件、修订）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_posts`
--

LOCK TABLES `jk_posts` WRITE;
/*!40000 ALTER TABLE `jk_posts` DISABLE KEYS */;
INSERT INTO `jk_posts` VALUES (2,NULL,'<figure class=\"image\"><img src=\"http://127.0.0.1:8081/Supermarket/image/catalog/blog/4.jpg\" alt=\"\"></figure><p>&nbsp;</p><p>&nbsp;</p><p>今日，蔚来宣布将在北京时间周三20: 00召开第二季度财报业绩电话会议。昨日，蔚来汽车公布了截至 2019 年 6 月 30 日未经审计的第二季度财报。财报财报显示，蔚来 2019 第二季度获得总营收15. 086 亿元，环比下降7.5%同比增长3180.1%;净亏损32. 858 亿元，环比增长25.2%，同比增长83.1%。今日，蔚来宣布将在北京时间周三20: 00召开第二季度财报业绩电话会议。昨日，蔚来汽车公布了截至 2019 年 6 月 30 日未经审计的第二季度财报。财报财报显示，蔚来 2019 第二季度获得总营收15. 086 亿元，环比下降7.5%同比增长3180.1%;净亏损32. 858 亿元，环比增长25.2%，同比增长83.1%。今日，蔚来宣布将在北京时间周三20: 00召开第二季度财报业绩电话会议。昨日，蔚来汽车公布了截至 2019 年 6 月 30 日未经审计的第二季度财报。财报财报显示，蔚来 2019 第二季度获得总营收15. 086 亿元，环比下降7.5%同比增长3180.1%;净亏损32. 858 亿元，环比增长25.2%，同比增长83.1%。今日，蔚来宣布将在北京时间周三20: 00召开第二季度财报业绩电话会议。昨日，蔚来汽车公布了截至 2019 年 6 月 30 日未经审计的第二季度财报。财报财报显示，蔚来 2019 第二季度获得总营收15. 086 亿元，环比下降7.5%同比增长3180.1%;净亏损32. 858 亿元，环比增长25.2%，同比增长83.1%。今日，蔚来宣布将在北京时间周三20: 00召开第二季度财报业绩电话会议。昨日，蔚来汽车公布了截至 2019 年 6 月 30 日未经审计的第二季度财报。财报财报显示，蔚来 2019 第二季度获得总营收15. 086 亿元，环比下降7.5%同比增长3180.1%;净亏损32. 858 亿元，环比增长25.2%，同比增长83.1%。</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>','蔚来汽车宣布将在今日20点召开第二季度财报业绩电话会','gghy6','今日，蔚来宣布将在北京时间周三20: 00召开第二季度财报业绩电话会议。昨日，蔚来汽车公布了截至 2019 年 6 月 30 日未经审计的第二季度财报。财报财报显示，蔚来 2019 第二季度获得总营收15. 086 亿元，环比下降7.5%同比增长3180.1%;净亏损32. 858 亿元，环比增长25.2%，同比增长83.1%。','publish','open',NULL,NULL,NULL,NULL,NULL,0,'posts','/defaults/images/blog/post-1.png','post',NULL,0.00,0.00,100,0,0,0,'2021-02-20 17:23:03','2021-05-15 00:29:33'),(4,NULL,'<figure class=\"image\"><img src=\"http://127.0.0.1:8081/Supermarket/image/demo/about/about-us-demo4.jpg\"></figure><p>&nbsp;</p><p>本模板采用，笔下光年的开发的博客模板，只有三个页面，首页，详细页和About页面，样式和js都不多，比较简单。</p><p>本站采用了编辑器ckeditor5</p><p>字体图标采用Material Design Icons，其实模板本身用到的图标并不多，大家可以采用替代方案。<a href=\"http://fontello.com/\">http://fontello.com/</a> 网站可以打包下载自己所需要的字体图标，这样就会大大减小字体图标的加载时间。</p><p><strong>特别鸣谢</strong></p><ul><li>Bootstrap</li><li>JQuery</li><li>Material Design Icons</li><li>highlight</li></ul>','关于我们','关于','关于关于关于关于关于','publish','open',NULL,NULL,NULL,NULL,NULL,0,'page','/Supermarket/image/catalog/blog/2.jpg','page',NULL,0.00,0.00,100,0,0,0,'2021-03-04 02:09:17','2021-05-17 06:59:53'),(22,NULL,'<p>这里是真正的文章一号</p><p><span style=\"background-color:rgb(255,255,255);color:rgb(77,82,89);\">去年 9 月在杭州云栖大会上，阿里巴巴正式宣布成立芯片公司“平头哥半导体有限公司”，它由阿里去年 4 月收购的国产芯片企业中天微与阿里旗下达摩院芯片团队整合而成，“平头哥”自此横空出世。</span></p><h3>生活有度，人生添寿。</h3><p>今年 7 月，<a href=\"http://localhost:8081/post/22#\">平头哥正式发布玄铁 910</a>，据称这是目前业界最强的 RISC-V 处理器。</p><p>时隔两个月，今天在杭州举办的云栖大会上，“平头哥”推出了高性能 AI 推理芯片含光 800。含光 800 是阿里成立平头哥芯片公司后第一款正式流片的芯片。这是阿里里巴巴第一次使用了自己的一套硬件架构，也是互联网公司研发的第一款大芯片。</p><p><code>System.out.println(\"世界你好\");</code></p><pre><code class=\"language-javascript\">function $initHighlight(block, cls) {\r\n\r\n  try {\r\n\r\n    if (cls.search(/\\bno\\-highlight\\b/) != -1)\r\n\r\n      return process(block, true, 0x0F) +\r\n\r\n             ` class=\"${cls}\"`;\r\n\r\n  } catch (e) {\r\n\r\n    /* handle exception */\r\n\r\n  }\r\n\r\n  for (var i = 0 / 2; i &lt; classes.length; i++) {\r\n\r\n    if (checkCondition(classes[i]) === undefined)\r\n\r\n      console.log(\'undefined\');\r\n\r\n  }\r\n\r\n}\r\n\r\n \r\n\r\nexport  $initHighlight;</code></pre><h3>排版</h3><p>达摩院院长张建锋宣布了这则消息，并表示：“在全球芯片领域，阿里巴巴是一个新人，玄铁和含光 800 是平头哥的万里长征第一步，我们还有很长的路要走。”</p><p>据了解，在业界标准的 ResNet-50 测试中，含光 800 推理性能达到 78563 IPS，比目前业界最好的 AI 芯片性能高 4 倍；能效比 500 IPS/W，是第二名的 3.3 倍。在杭州城市大脑的业务测试中，1 颗含光 800 的算力相当于 10 颗 GPU。</p><p>含光 800 性能的突破得益于软硬件的协同创新：硬件层面采用自研芯片架构，通过推理加速等技术有效解决芯片性能瓶颈问题；软件层面集成了达摩院先进算法，针对 CNN 及视觉类算法深度优化计算、存储密度，可实现大网络模型在一颗 NPU 上完成计算。</p><p>引用示例:</p><blockquote><p><span style=\"background-color:rgb(255,255,255);color:rgb(77,82,89);\">土地是以它的肥沃和收获而被估价的；才能也是土地，不过它生产的不是粮食，而是真理。如果只能滋生瞑想和幻想的话，即使再大的才能也只是砂地或盐池，那上面连小草也长不出来的。</span></p><p><span style=\"background-color:rgb(255,255,255);color:rgb(108,117,125);\">-别林斯基</span></p></blockquote><p><span style=\"background-color:rgb(255,255,255);color:rgb(108,117,125);\">文章结束</span></p>','文章一号','经济,编程,创业','文章一号','publish','open',NULL,NULL,NULL,NULL,NULL,1,'posts','/Supermarket/image/catalog/blog/10.jpg','post',NULL,0.00,0.00,100,0,0,0,'2021-03-07 05:29:17','2021-03-22 10:06:02'),(23,NULL,'<p>eerty67eerty67eerty67</p>','eerty67','eerty67','eerty67','publish','open',NULL,NULL,NULL,NULL,NULL,0,'posts','/Supermarket/image/catalog/blog/4.jpg','post',NULL,0.00,0.00,100,1,0,0,'2021-03-07 06:35:35','2021-03-07 06:36:26'),(24,NULL,'<p>去年 9 月在杭州云栖大会上，阿里巴巴正式宣布成立芯片公司“平头哥半导体有限公司”，它由阿里去年 4 月收购的国产芯片企业中天微与阿里旗下达摩院芯片团队整合而成，“平头哥”自此横空出世。去年 9 月在杭州云栖大会上，阿里巴巴正式宣布成立芯片公司“平头哥半导体有限公司”，它由阿里去年 4 月收购的国产芯片企业中天微与阿里旗下达摩院芯片团队整合而成，“平头哥”自此横空出世。</p>','苹果 CEO 库克：Apple 已完全使用可再生能源供电','','去年 9 月在杭州云栖大会上，阿里巴巴正式宣布成立芯片公司“平头哥半导体有限公司”，它由阿里去年 4 月收购的国产芯片企业中天微与阿里旗下达摩院芯片团队整合而成，“平头哥”自此横空出世。','publish',NULL,NULL,NULL,NULL,NULL,NULL,0,'posts','/Supermarket/image/catalog/blog/3.jpg','post',NULL,0.00,0.00,100,0,0,0,'2021-03-17 14:54:00',NULL),(31,NULL,'<p>fdsafdsafdsafdas</p><p>fdsafdas</p><p>fdas</p><p>f</p><p>dsa</p><p>fd</p><p>safdsa</p>','运动单车','aaaa','fdsafdas','publish',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'product','/demo/imgs/danche.jpeg','product',NULL,44.00,44.00,98,0,0,1,'2021-04-08 08:01:41','2021-05-22 02:26:06'),(32,NULL,'<p>商品2商品2商品2商品2商品2商品2商品2商品2商品2商品2</p>','商品2','商品2','商品2商品2商品2商品2','publish','open',NULL,NULL,NULL,NULL,NULL,NULL,'product','/Supermarket/image/catalog/demo/product/270/f2.jpg','product',NULL,89.00,89.00,989,1,0,0,'2021-04-08 08:04:56',NULL),(33,NULL,'<p>商品3商品3商品3商品3商品3商品3商品3商品3商品3商品3商品3</p>','商品3','324324','23432432','publish','closed',NULL,NULL,NULL,NULL,NULL,55,'product','/Supermarket/image/catalog/demo/product/270/fu2.jpg','product',NULL,89.00,993.00,92,1,0,0,'2021-04-08 08:06:55','2021-04-14 01:29:48'),(44,NULL,'<p>商品rt234商品rt234商品rt234商品rt234商品rt234商品rt234商品rt234</p>','跑步机','','商品rt234','publish','closed',NULL,NULL,NULL,NULL,NULL,45,'product','/Supermarket/image/catalog/demo/product/fashion/1.jpg','product',NULL,33.00,45.00,99,1,0,2,'2021-05-07 15:13:27','2021-05-21 23:19:48'),(45,NULL,'<p>健身111健身111健身111健身111健身111</p>','握力器','','健身111健身111','publish',NULL,NULL,NULL,NULL,NULL,NULL,3,'product','/demo/imgs/woliqi.jpeg','product',NULL,56.00,78.00,97,3,0,0,'2021-05-07 15:15:47','2021-05-22 02:32:44'),(46,NULL,'<p>hhuiohhuiohhuiohhuio</p>','李宁篮球','hhuiohhuio','hhuiohhuiohhuio','publish',NULL,NULL,NULL,NULL,NULL,NULL,4,'product','/demo/imgs/lanqiujia.jpeg','product',NULL,67.00,89.00,100,0,0,0,'2021-05-07 15:17:28','2021-05-22 01:31:05'),(47,NULL,'/index','首页',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',1,'nav_menu_item','',NULL,'link_category',0.00,0.00,100,0,0,0,'2021-05-08 07:44:15',NULL),(48,NULL,'24','运动健身',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',2,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 07:52:51',NULL),(49,NULL,'20','武术器材',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',3,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 08:04:40',NULL),(50,NULL,'2','健身科谱',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',4,'nav_menu_item','',NULL,'category',0.00,0.00,100,0,0,0,'2021-05-08 08:11:48',NULL),(51,NULL,'4','关于我们',NULL,'关于我们','publish',NULL,NULL,NULL,NULL,NULL,'0',5,'nav_menu_item','',NULL,'page',0.00,0.00,100,0,0,0,'2021-05-08 08:14:05',NULL),(56,NULL,'39','健康饮食',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,1,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 12:22:54','2021-05-11 01:26:10'),(57,NULL,'31','体育休闲',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,2,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 12:23:09',NULL),(58,NULL,'33','运动包/户外包/配件',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,3,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 12:25:22',NULL),(59,NULL,'34','运动护具',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',4,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 12:33:46',NULL),(60,NULL,'35','智能装备',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',5,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 12:36:38',NULL),(61,NULL,'20','武术器材',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',6,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-08 12:39:56','2021-05-11 09:43:59'),(62,NULL,'25','篮球',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'57',8,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-09 14:53:37',NULL),(65,NULL,'37','限时抢购',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'0',1,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-10 09:39:56',NULL),(66,NULL,'41','低脂面包',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'56',2,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-11 01:26:37',NULL),(67,NULL,'42','套餐',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'56',3,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-11 01:26:53',NULL),(68,NULL,'43','全麦面包',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'66',3,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-11 01:41:21',NULL),(69,NULL,'<p>护膝001护膝001护膝001护膝001护膝001护膝001护膝001护膝001</p>','护膝001','护膝001','护膝001护膝001','publish','open',NULL,NULL,NULL,NULL,NULL,NULL,'product','/demo/imgs/huuxi001.jpeg','product',NULL,23.00,34.00,100,0,0,0,'2021-05-12 00:31:21',NULL),(70,NULL,'<p>护腕002护腕002护腕002护腕002护腕002护腕002护腕002</p>','护腕002','护腕002','护腕002护腕002','publish','open',NULL,NULL,NULL,NULL,NULL,4,'product','/demo/imgs/huxi002.jpeg','product',NULL,11.00,23.00,100,0,0,0,'2021-05-12 00:32:23',NULL),(71,NULL,'<p>头盔001头盔001头盔001头盔001头盔001头盔001头盔001头盔001头盔001头盔001头盔001头盔001头盔001</p>','头盔001','头盔001','头盔001头盔001','publish','open',NULL,NULL,NULL,NULL,NULL,3,'product','/Supermarket/image/catalog/demo/product/270/e2.jpg','product',NULL,33.00,45.00,99,1,0,0,'2021-05-12 00:33:00',NULL),(72,NULL,'<p>小板凳小板凳小板凳小板凳小板凳小板凳小板凳小板凳小板凳小板凳</p>','小板凳','','小板凳小板凳小板凳','publish','open',NULL,NULL,NULL,NULL,NULL,3,'product','/Supermarket/image/catalog/demo/product/270/fu8.jpg','product',NULL,46.00,55.00,99,1,0,0,'2021-05-12 00:33:34','2021-05-22 04:53:22'),(73,NULL,'49','促销分类1',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,1,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-13 02:55:52',NULL),(74,NULL,'50','促销分类2',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,2,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-13 02:56:03',NULL),(75,NULL,'51','促销分类3',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,3,'nav_menu_item','',NULL,'product_category',0.00,0.00,100,0,0,0,'2021-05-13 02:56:17',NULL),(76,NULL,'1','经济',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,1,'nav_menu_item','',NULL,'category',NULL,NULL,0,0,0,0,'2021-05-13 13:51:28',NULL),(77,NULL,'4','国内经济',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,'76',2,'nav_menu_item','',NULL,'category',NULL,NULL,0,0,0,0,'2021-05-13 13:51:48',NULL),(78,NULL,'2','技术开发',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,3,'nav_menu_item','',NULL,'category',NULL,NULL,0,0,0,0,'2021-05-13 13:52:00',NULL),(79,NULL,'3','健身',NULL,'','publish',NULL,NULL,NULL,NULL,NULL,NULL,4,'nav_menu_item','',NULL,'category',NULL,NULL,0,0,0,0,'2021-05-13 13:52:12',NULL),(80,NULL,'37','限时抢购',NULL,'限时抢购','publish',NULL,NULL,NULL,NULL,NULL,NULL,1,'nav_menu_item','',NULL,'product_category',NULL,NULL,0,0,0,0,'2021-05-14 00:54:57',NULL),(81,NULL,'<figure class=\"image\"><img src=\"/Supermarket/image/catalog/demo/product/270/e3.jpg\"></figure><p>&nbsp;</p><p>运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表运动手表</p>','运动手表','运动手表','运动手表运动手表运动手表','publish','open',NULL,NULL,NULL,NULL,NULL,23,'product','/Supermarket/image/catalog/demo/product/270/e3.jpg','product',NULL,19.90,45.00,0,0,0,0,'2021-05-15 10:18:08',NULL),(82,NULL,'<p>斯耿sing斯耿sing斯耿sing斯耿sing斯耿sing斯耿sing斯耿sing斯耿sing斯耿sing斯耿sing斯耿sing</p>','太极服','斯耿sing','斯耿sing斯耿sing斯耿sing','publish',NULL,NULL,NULL,NULL,NULL,NULL,3,'product','/Supermarket/image/catalog/demo/product/270/e9.jpg','product',NULL,33.00,45.00,0,0,0,0,'2021-05-17 14:23:03','2021-05-21 23:21:15');
/*!40000 ALTER TABLE `jk_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_product_sku`
--

DROP TABLE IF EXISTS `jk_product_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_product_sku` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `spuval_ids` varchar(55) COLLATE utf8_bin DEFAULT NULL COMMENT '规格ID组合',
  `spuval_str` varchar(1023) COLLATE utf8_bin NOT NULL COMMENT '商品规格值列表，采用JSON数组格式',
  `origin_price` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品货品价格',
  `stock` int NOT NULL DEFAULT '0' COMMENT '商品货品数量',
  `img_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '商品货品图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku_unique` (`product_id`,`spuval_ids`) COMMENT '一个商品每一个规格SKU信息只能有一个',
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品库存表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_product_sku`
--

LOCK TABLES `jk_product_sku` WRITE;
/*!40000 ALTER TABLE `jk_product_sku` DISABLE KEYS */;
INSERT INTO `jk_product_sku` VALUES (28,32,'176;181','尺寸:s,颜色:红',67.00,56.00,12,NULL,'2021-05-07 06:47:06',NULL),(29,32,'177;182','尺寸:m,颜色:白',76.00,45.00,78,NULL,'2021-05-07 06:47:20',NULL),(30,32,'179;183','尺寸:xl,颜色:黑',77.00,47.00,65,NULL,'2021-05-07 06:47:36',NULL),(31,32,'176;185','尺寸:s,颜色:绿',73.00,46.00,44,NULL,'2021-05-07 06:48:10',NULL),(32,32,'177;184','尺寸:m,颜色:蓝',79.00,55.00,44,NULL,'2021-05-07 06:48:24',NULL),(33,33,'177;183','尺寸:m,颜色:黑',56.00,34.00,33,NULL,'2021-05-21 23:18:46',NULL),(34,45,'164','规格:常规',45.00,33.00,34,NULL,'2021-05-21 23:44:59',NULL),(35,46,'181','颜色:红',54.00,12.00,23,NULL,'2021-05-21 23:45:14',NULL),(36,46,'182','颜色:白',45.00,22.00,45,NULL,'2021-05-21 23:45:24',NULL),(37,82,'176;181','尺寸:s,颜色:红',33.00,23.00,23,NULL,'2021-05-21 23:46:02',NULL),(38,82,'177;183','尺寸:m,颜色:黑',56.00,34.00,32,NULL,'2021-05-21 23:46:13',NULL);
/*!40000 ALTER TABLE `jk_product_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_role`
--

DROP TABLE IF EXISTS `jk_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `note` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_role`
--

LOCK TABLES `jk_role` WRITE;
/*!40000 ALTER TABLE `jk_role` DISABLE KEYS */;
INSERT INTO `jk_role` VALUES (1,'superadmin','系统管理员','2018-08-22 11:37:47','2021-04-06 01:25:26'),(4,'frontUser','前台注册的用户没有后台权限','2021-01-01 15:54:32','2021-02-03 08:57:16');
/*!40000 ALTER TABLE `jk_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_role_permission`
--

DROP TABLE IF EXISTS `jk_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `permission_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniques` (`role_id`,`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_role_permission`
--

LOCK TABLES `jk_role_permission` WRITE;
/*!40000 ALTER TABLE `jk_role_permission` DISABLE KEYS */;
INSERT INTO `jk_role_permission` VALUES (289,1,51);
/*!40000 ALTER TABLE `jk_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_spu`
--

DROP TABLE IF EXISTS `jk_spu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_spu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `spu` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '商品规格名称',
  `value` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '商品规格值',
  `pic_url` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '商品规格图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品规格表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_spu`
--

LOCK TABLES `jk_spu` WRITE;
/*!40000 ALTER TABLE `jk_spu` DISABLE KEYS */;
INSERT INTO `jk_spu` VALUES (19,'规格','常规','','2021-05-04 13:41:53',NULL),(20,'尺寸','s,m,l,xl,xxl','','2021-05-04 13:43:02','2021-05-04 13:49:52'),(21,'颜色','红,白,黑,蓝,绿,紫','','2021-05-04 13:51:10',NULL),(23,'长度','0.5米,1米,2米,3米','','2021-05-21 23:33:08',NULL);
/*!40000 ALTER TABLE `jk_spu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_spu_relation`
--

DROP TABLE IF EXISTS `jk_spu_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_spu_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `spu_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_spu_relation`
--

LOCK TABLES `jk_spu_relation` WRITE;
/*!40000 ALTER TABLE `jk_spu_relation` DISABLE KEYS */;
INSERT INTO `jk_spu_relation` VALUES (1,19,31),(2,20,31),(3,21,31),(4,20,32),(5,21,32),(6,20,33),(7,21,33),(8,21,46),(9,19,45),(10,20,82),(11,21,82);
/*!40000 ALTER TABLE `jk_spu_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_spu_value`
--

DROP TABLE IF EXISTS `jk_spu_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_spu_value` (
  `id` int NOT NULL AUTO_INCREMENT,
  `spu_id` int DEFAULT NULL COMMENT '规格ID(t_product_specification表主键)',
  `spu_value` varchar(55) DEFAULT NULL,
  `seq_num` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_spu_value`
--

LOCK TABLES `jk_spu_value` WRITE;
/*!40000 ALTER TABLE `jk_spu_value` DISABLE KEYS */;
INSERT INTO `jk_spu_value` VALUES (164,19,'常规',0,'2021-05-04 13:41:53'),(176,20,'s',0,'2021-05-04 13:49:52'),(177,20,'m',1,'2021-05-04 13:49:52'),(178,20,'l',2,'2021-05-04 13:49:52'),(179,20,'xl',3,'2021-05-04 13:49:52'),(180,20,'xxl',4,'2021-05-04 13:49:52'),(181,21,'红',0,'2021-05-04 13:51:10'),(182,21,'白',1,'2021-05-04 13:51:10'),(183,21,'黑',2,'2021-05-04 13:51:10'),(184,21,'蓝',3,'2021-05-04 13:51:10'),(185,21,'绿',4,'2021-05-04 13:51:10'),(186,21,'紫',5,'2021-05-04 13:51:10'),(188,23,'0.5米',0,'2021-05-21 23:33:08'),(189,23,'1米',1,'2021-05-21 23:33:08'),(190,23,'2米',2,'2021-05-21 23:33:08'),(191,23,'3米',3,'2021-05-21 23:33:08');
/*!40000 ALTER TABLE `jk_spu_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_spuval_relation`
--

DROP TABLE IF EXISTS `jk_spuval_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_spuval_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `spu_id` int DEFAULT NULL COMMENT 'jk_spu 主键',
  `spuval_id` int DEFAULT NULL COMMENT 'jk_spu_value 主键',
  `product_id` int DEFAULT NULL COMMENT 'jk_posts 商品表主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_spuval_relation`
--

LOCK TABLES `jk_spuval_relation` WRITE;
/*!40000 ALTER TABLE `jk_spuval_relation` DISABLE KEYS */;
INSERT INTO `jk_spuval_relation` VALUES (57,20,176,32),(58,21,181,32),(59,21,185,32),(60,20,177,32),(61,21,182,32),(62,21,184,32),(63,20,179,32),(64,21,183,32),(65,20,177,33),(66,21,183,33),(67,19,164,45),(69,21,181,46),(70,21,182,46),(73,20,176,82),(74,21,181,82),(75,20,177,82),(76,21,183,82);
/*!40000 ALTER TABLE `jk_spuval_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_term`
--

DROP TABLE IF EXISTS `jk_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_term` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分类名',
  `slug` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '缩略名和分类法组合,要求唯一不重复',
  `template` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分类所使用的模板',
  `icon` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分类图标',
  `link_image` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '预览图片',
  `taxonomy` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分类方法,有category、link_category、tag、nav、meau 四种分类类型',
  `parent_id` int DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `counts` int DEFAULT '0' COMMENT '分类项目中包含的文章数量',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `term_order` int DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug_UNIQUE` (`slug`,`taxonomy`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文章分类、链接分类、标签的信息表。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_term`
--

LOCK TABLES `jk_term` WRITE;
/*!40000 ALTER TABLE `jk_term` DISABLE KEYS */;
INSERT INTO `jk_term` VALUES (1,'经济','jingjicat','postList','',NULL,'category',NULL,'',0,'2021-02-07 10:00:23','2021-03-17 06:09:31',0),(2,'技术开发','jishukaifa','postList','',NULL,'category',NULL,'',0,'2021-02-07 10:00:23','2021-03-18 09:28:11',0),(3,'健身','jianshen','postList','',NULL,'category',NULL,'',1,'2021-02-18 08:32:17','2021-03-17 06:10:32',0),(4,'国内经济','guoneijingji','postList','icon-bbu',NULL,'category',1,'',0,'2021-02-18 09:46:20','2021-03-17 06:09:51',0),(5,'友情链接','friendlink',NULL,NULL,NULL,'link_category',NULL,NULL,0,'2021-02-23 10:04:24','2021-02-24 08:00:11',1),(6,'合作伙伴链接','hezuohuoban',NULL,NULL,NULL,'link_category',NULL,NULL,0,'2021-02-23 10:05:53','2021-02-24 00:59:12',2),(9,'顶部导航','topnav',NULL,NULL,NULL,'nav_menu',NULL,'顶部',0,'2021-02-25 09:59:05',NULL,2),(10,'底部导航','footernav',NULL,NULL,NULL,'nav_menu',NULL,'底部导航',0,'2021-03-04 03:20:53',NULL,2),(11,'java',NULL,'postList',NULL,NULL,'post_tag',NULL,'java',7,'2021-03-07 06:35:35',NULL,NULL),(12,'mysql',NULL,'postList',NULL,NULL,'post_tag',NULL,'mysql',0,'2021-03-07 06:35:35',NULL,NULL),(13,'spring',NULL,'postList',NULL,NULL,'post_tag',NULL,'spring',0,'2021-03-07 06:35:35',NULL,NULL),(14,'',NULL,NULL,NULL,NULL,'post_tag',NULL,'',42,'2021-03-08 15:46:18',NULL,NULL),(15,'体育',NULL,'postList',NULL,NULL,'post_tag',NULL,'体育',3,'2021-03-08 15:46:46',NULL,NULL),(16,'经济',NULL,'postList',NULL,NULL,'post_tag',NULL,'经济',3,'2021-03-08 15:46:46',NULL,NULL),(18,'web开发',NULL,'postList',NULL,NULL,'post_tag',NULL,'web开发',0,'2021-03-20 00:29:07',NULL,NULL),(19,'计算机',NULL,'postList',NULL,NULL,'post_tag',NULL,'计算机',10,'2021-03-20 02:09:26',NULL,NULL),(20,'武术器材','wushuqicai','productList','','','product_category',NULL,'武术器材',NULL,'2021-04-08 07:30:09','2021-05-11 09:43:47',1),(23,'商品分类','indexCategorys',NULL,NULL,NULL,'nav_menu',NULL,'首页导航分类',NULL,'2021-04-13 01:21:48','2021-05-08 12:25:46',2),(24,'健身','jianshen','productList','','','product_category',0,'',NULL,'2021-05-07 09:38:38',NULL,NULL),(25,'篮球','lanqiu','productList','','','product_category',0,'',NULL,'2021-05-07 09:39:14',NULL,5),(26,'太极','taiji','productList','','','product_category',20,'',NULL,'2021-05-07 09:39:35',NULL,6),(31,'体育休闲','SportsLeisure','productList','','','product_category',NULL,'',NULL,'2021-05-08 12:22:32',NULL,NULL),(32,'健身器材','fitness','productList','','','product_category',NULL,'',NULL,'2021-05-08 12:24:16',NULL,NULL),(33,'运动包/户外包/配件','bagandsport','productList','','','product_category',NULL,'',NULL,'2021-05-08 12:25:02',NULL,NULL),(34,'运动护具','yundonghuju','productList','','','product_category',NULL,'',NULL,'2021-05-08 12:33:24',NULL,NULL),(35,'智能装备','zhinengzhuangbei','productList','','','product_category',NULL,'',NULL,'2021-05-08 12:36:20',NULL,NULL),(36,'球类运动','qiuleiyundong','productList','','','product_category',NULL,'',NULL,'2021-05-08 12:38:36',NULL,NULL),(37,'限时抢购','xianshiqianggou','productList','','','product_category',NULL,'',NULL,'2021-05-10 09:38:33',NULL,1),(39,'健康饮食','','productList','','','product_category',NULL,'',NULL,'2021-05-11 01:23:07',NULL,NULL),(41,'低脂面包','dizhimianbao','productList','','','product_category',39,'',NULL,'2021-05-11 01:23:36',NULL,NULL),(42,'套餐','dizhitaocan','productList','','','product_category',39,'',NULL,'2021-05-11 01:24:01',NULL,NULL),(43,'全麦面包','quanmaimianbao','productList','','','product_category',41,'',NULL,'2021-05-11 01:27:56',NULL,NULL),(44,'护膝','huxi','productList','','','product_category',34,'',NULL,'2021-05-12 00:19:45',NULL,NULL),(45,'护腕','huwan','productList','','','product_category',34,'',NULL,'2021-05-12 00:20:10',NULL,NULL),(46,'头盔','toukui','productList','','','product_category',34,'',NULL,'2021-05-12 00:22:24',NULL,NULL),(47,'护腰','huyao','productList','','','product_category',34,'',NULL,'2021-05-12 00:22:47',NULL,NULL),(48,'首页推荐','shouyetuijian','productList','','','product_category',NULL,'',NULL,'2021-05-13 00:12:11',NULL,NULL),(49,'促销分类1','cxflone','productList','','','product_category',NULL,'',NULL,'2021-05-13 01:32:14',NULL,NULL),(50,'促销分类2','cxfltwo','productList','','','product_category',NULL,'',NULL,'2021-05-13 01:32:29',NULL,NULL),(51,'促销分类3','cxflthree','productList','','','product_category',NULL,'',NULL,'2021-05-13 01:32:46',NULL,NULL),(52,'首页轮播','shouyelunbo',NULL,NULL,NULL,'nav_menu',NULL,'首页轮播',NULL,'2021-05-13 02:55:29',NULL,3),(53,'详情文章分类','xqwzfl',NULL,NULL,NULL,'nav_menu',NULL,'详情文章分类',NULL,'2021-05-13 13:51:13',NULL,6),(54,'首页轮播图','indexBanners',NULL,NULL,NULL,'link_category',NULL,'首页轮播图',NULL,'2021-05-14 00:14:04',NULL,3),(56,'独立导航','dulidaohang',NULL,NULL,NULL,'nav_menu',NULL,'独立导航',NULL,'2021-05-14 00:54:40',NULL,8),(57,'智能',NULL,'postList',NULL,NULL,'post_tag',NULL,'智能',1,'2021-05-15 10:18:08',NULL,NULL),(58,'运动',NULL,'postList',NULL,NULL,'post_tag',NULL,'运动',1,'2021-05-15 10:18:08',NULL,NULL),(59,'手表',NULL,'postList',NULL,NULL,'post_tag',NULL,'手表',1,'2021-05-15 10:18:08',NULL,NULL),(60,'服装',NULL,'postList',NULL,NULL,'post_tag',NULL,'服装',2,'2021-05-15 11:30:18',NULL,NULL),(61,'汽车',NULL,'postList',NULL,NULL,'post_tag',NULL,'汽车',2,'2021-05-15 11:30:18',NULL,NULL),(62,'水果',NULL,'postList',NULL,NULL,'post_tag',NULL,'水果',2,'2021-05-15 11:30:18',NULL,NULL);
/*!40000 ALTER TABLE `jk_term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_term_relationships`
--

DROP TABLE IF EXISTS `jk_term_relationships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_term_relationships` (
  `id` int NOT NULL AUTO_INCREMENT,
  `object_id` int DEFAULT NULL COMMENT '对应文章ID/链接ID',
  `term_id` int DEFAULT NULL COMMENT '对应分类ID,链接到jk_term表的主键',
  `term_order` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `objunique` (`object_id`,`term_id`)
) ENGINE=InnoDB AUTO_INCREMENT=436 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='分类与文章信息表（jk_posts）、链接表(jk_links)的关联表。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_term_relationships`
--

LOCK TABLES `jk_term_relationships` WRITE;
/*!40000 ALTER TABLE `jk_term_relationships` DISABLE KEYS */;
INSERT INTO `jk_term_relationships` VALUES (36,1,12,2),(37,2,5,3),(38,3,NULL,NULL),(39,5,NULL,NULL),(55,3,5,3),(64,23,1,0),(65,23,11,NULL),(66,23,12,NULL),(67,23,13,NULL),(109,24,1,0),(110,24,2,0),(111,24,14,NULL),(153,12,9,NULL),(174,22,1,1),(175,22,2,1),(176,22,11,NULL),(177,22,19,NULL),(178,4,NULL,NULL),(179,5,NULL,NULL),(180,6,NULL,NULL),(181,7,NULL,NULL),(182,8,NULL,NULL),(183,9,NULL,NULL),(184,10,NULL,NULL),(185,11,NULL,NULL),(186,12,NULL,NULL),(187,13,NULL,NULL),(188,14,NULL,NULL),(189,15,NULL,NULL),(190,16,NULL,NULL),(191,17,NULL,NULL),(192,18,NULL,NULL),(193,19,NULL,NULL),(194,20,NULL,NULL),(195,21,NULL,NULL),(196,22,NULL,NULL),(197,23,NULL,NULL),(198,24,NULL,NULL),(199,25,NULL,NULL),(200,26,NULL,NULL),(201,27,NULL,NULL),(202,28,NULL,NULL),(203,29,NULL,NULL),(204,30,NULL,NULL),(205,31,NULL,NULL),(206,32,NULL,NULL),(207,33,NULL,NULL),(208,34,NULL,NULL),(209,35,NULL,NULL),(210,36,NULL,NULL),(211,37,NULL,NULL),(212,38,NULL,NULL),(213,39,NULL,NULL),(214,40,NULL,NULL),(215,41,NULL,NULL),(216,42,NULL,NULL),(217,43,NULL,NULL),(218,44,NULL,NULL),(219,45,NULL,NULL),(220,46,NULL,NULL),(221,47,NULL,NULL),(222,48,NULL,NULL),(223,49,NULL,NULL),(224,50,NULL,NULL),(225,51,NULL,NULL),(226,52,NULL,NULL),(227,53,NULL,NULL),(228,54,NULL,NULL),(229,55,NULL,NULL),(230,56,NULL,NULL),(231,57,NULL,NULL),(232,58,NULL,NULL),(233,59,NULL,NULL),(234,60,NULL,NULL),(235,61,NULL,NULL),(236,62,NULL,NULL),(237,63,NULL,NULL),(238,64,NULL,NULL),(239,65,NULL,NULL),(240,66,NULL,NULL),(241,67,NULL,NULL),(242,68,NULL,NULL),(243,69,NULL,NULL),(244,70,NULL,NULL),(245,71,NULL,NULL),(246,72,NULL,NULL),(247,73,NULL,NULL),(248,74,NULL,NULL),(251,29,20,1),(252,29,14,NULL),(253,30,20,1),(254,30,14,NULL),(258,32,14,NULL),(263,35,14,NULL),(265,36,14,NULL),(267,37,14,NULL),(269,38,14,NULL),(282,32,20,0),(284,24,3,0),(287,33,20,55),(288,33,14,NULL),(306,39,14,NULL),(314,47,9,NULL),(315,48,9,NULL),(316,49,9,NULL),(317,50,9,NULL),(318,51,9,NULL),(324,57,23,NULL),(325,58,23,NULL),(326,59,23,NULL),(327,60,23,NULL),(329,62,23,NULL),(332,32,37,0),(337,33,37,0),(338,65,38,NULL),(339,56,23,NULL),(340,66,23,NULL),(341,67,23,NULL),(342,68,23,NULL),(343,61,23,NULL),(345,69,34,NULL),(346,69,44,NULL),(347,69,14,NULL),(348,70,34,4),(349,70,45,4),(350,70,14,NULL),(351,71,34,3),(352,71,46,3),(353,71,14,NULL),(358,70,48,0),(360,75,17,NULL),(361,76,17,3),(362,77,17,NULL),(363,78,17,NULL),(364,73,52,NULL),(365,74,52,NULL),(366,75,52,NULL),(367,76,53,NULL),(368,77,53,NULL),(369,78,53,NULL),(370,79,53,NULL),(371,79,54,NULL),(372,80,54,2),(373,81,54,4),(375,80,56,NULL),(381,2,1,0),(382,2,2,0),(383,2,15,NULL),(384,2,16,NULL),(385,2,19,NULL),(386,81,35,23),(387,81,57,NULL),(388,81,58,NULL),(389,81,59,NULL),(398,83,14,NULL),(399,44,20,45),(400,44,24,45),(401,44,25,45),(402,44,37,45),(403,44,48,45),(404,44,14,NULL),(414,82,26,3),(415,82,25,3),(416,82,37,3),(417,82,14,NULL),(418,46,26,4),(419,46,37,4),(420,46,14,NULL),(421,31,26,NULL),(422,31,37,NULL),(423,31,60,NULL),(424,31,61,NULL),(425,31,62,NULL),(426,45,20,3),(427,45,26,3),(428,45,24,3),(429,45,25,3),(430,45,37,3),(431,45,48,3),(432,45,14,NULL),(433,72,34,3),(434,72,47,3),(435,72,14,NULL);
/*!40000 ALTER TABLE `jk_term_relationships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_user`
--

DROP TABLE IF EXISTS `jk_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sstate` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态 0锁定 1有效',
  `ssex` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '性别 0男 1女',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '个人描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_user`
--

LOCK TABLES `jk_user` WRITE;
/*!40000 ALTER TABLE `jk_user` DISABLE KEYS */;
INSERT INTO `jk_user` VALUES (1,'admin','管理员admin','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','admin@qq.com','13584391138','1','0','我的介绍 了,2021继续自律','2018-08-22 11:37:47','2020-12-31 01:20:21','2018-08-22 11:37:47'),(2,'test33','测试用户23','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','283857153@qq.com','1368183940','0','1',NULL,'2018-08-22 11:37:47','2021-03-16 10:46:37',NULL),(5,'aaa1','aaa1','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','2838wer3@qq.com','1362343940','1','0',NULL,'2021-01-31 02:31:26','2021-04-12 02:21:19',NULL),(6,'vv1','vv1','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','fdsafdsa@qq.com','34567892234','1','0',NULL,'2021-01-31 02:31:55',NULL,NULL),(7,'vv2','vv2','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','vv2@ww.com','56789345567','1','0',NULL,'2021-01-31 02:32:18',NULL,NULL),(8,'vv3','vv3','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','vv3@qq.com','2367891127','1','2',NULL,'2021-01-31 02:32:41',NULL,NULL),(9,'vv4','vv4','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','vv4@ee.com','36789231145','1','2',NULL,'2021-01-31 02:33:06',NULL,NULL),(10,'vv5','vv5','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','vv5@ww.com','98763548829','1','0',NULL,'2021-01-31 02:33:31',NULL,NULL),(11,'vv8','vv8','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','vv9@ww.com','18764392219','1','0',NULL,'2021-01-31 02:34:08',NULL,NULL),(12,'vv9','vv9','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','vv9@qq.com','17836352274','1','0',NULL,'2021-01-31 02:37:08',NULL,NULL),(13,'bb5','bb5','anFzMWRjM2YxYzhiMmQ0NTMzYWYxY2YwZmFkMWIwNmVkZjMxMjAxNDIxOGVlMTQ5NDQ2ZGRhOTI5ZjZmYmE0MWYyNzEz','bb5@qq.com','13784393328','1','0',NULL,'2021-01-31 02:39:41',NULL,NULL);
/*!40000 ALTER TABLE `jk_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jk_user_role`
--

DROP TABLE IF EXISTS `jk_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jk_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jk_user_role`
--

LOCK TABLES `jk_user_role` WRITE;
/*!40000 ALTER TABLE `jk_user_role` DISABLE KEYS */;
INSERT INTO `jk_user_role` VALUES (38,1,1),(46,8,4),(58,2,1),(59,5,4);
/*!40000 ALTER TABLE `jk_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-24  9:17:13
