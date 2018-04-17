-- MySQL dump 10.13  Distrib 5.7.21, for Linux (i686)
--
-- Host: localhost    Database: auction
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auctions`
--

DROP TABLE IF EXISTS `auctions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auctions` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_id` int(11) NOT NULL,
  `a_start_time` datetime NOT NULL,
  `a_end_time` datetime DEFAULT NULL,
  `a_status` enum('PENDING_PAYMENT','COMPLETED','ACTIVE') NOT NULL DEFAULT 'ACTIVE',
  `a_minimum_price` decimal(19,2) NOT NULL,
  `auctions_type_at_id` int(11) NOT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `a_id_UNIQUE` (`a_id`),
  KEY `fk_auctions_auctions_type1_idx` (`l_id`),
  KEY `fk_auctions_lots1_idx` (`l_id`),
  KEY `fk_auctions_1_idx` (`auctions_type_at_id`),
  CONSTRAINT `fk_auction_lots1` FOREIGN KEY (`l_id`) REFERENCES `lots` (`l_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_auctions_auctions_type1` FOREIGN KEY (`auctions_type_at_id`) REFERENCES `auctions_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (1,1,'2015-12-12 00:00:00','2018-03-28 15:59:00','COMPLETED',1.00,1),(2,3,'2018-04-10 21:55:06',NULL,'COMPLETED',12.00,2),(4,8,'2018-03-22 00:00:00',NULL,'COMPLETED',12.00,1),(5,11,'2018-03-22 00:00:00',NULL,'COMPLETED',12.00,1),(6,16,'2018-04-04 00:00:00',NULL,'COMPLETED',12.00,2),(7,17,'2018-04-09 09:07:24','2018-04-09 04:02:11','PENDING_PAYMENT',12.00,1),(8,33,'2018-04-11 15:46:48','2018-04-17 18:19:22','PENDING_PAYMENT',12.00,2),(9,34,'2018-04-12 20:38:27',NULL,'COMPLETED',12.00,2),(10,35,'2018-04-12 16:35:22',NULL,'COMPLETED',12.00,2),(11,52,'2018-04-12 17:00:03',NULL,'COMPLETED',12.00,2),(12,53,'2018-04-14 13:15:45',NULL,'COMPLETED',1.00,2),(13,54,'2018-04-14 13:23:45',NULL,'PENDING_PAYMENT',1.00,2),(14,55,'2018-04-14 13:28:22',NULL,'PENDING_PAYMENT',1.00,2),(15,56,'2018-04-14 13:30:34',NULL,'ACTIVE',1.00,2),(16,57,'2018-04-14 13:32:25',NULL,'PENDING_PAYMENT',1.00,2),(17,58,'2018-04-14 13:38:41',NULL,'COMPLETED',1.00,2),(18,59,'2018-04-14 13:44:13',NULL,'PENDING_PAYMENT',1.00,2),(19,60,'2018-04-14 13:45:45',NULL,'PENDING_PAYMENT',1.00,2),(20,61,'2018-04-14 14:22:57',NULL,'COMPLETED',1.00,2);
/*!40000 ALTER TABLE `auctions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auctions_type`
--

DROP TABLE IF EXISTS `auctions_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auctions_type` (
  `at_id` int(11) NOT NULL,
  `at_type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`at_id`),
  UNIQUE KEY `lt_type_name_UNIQUE` (`at_type_name`),
  UNIQUE KEY `lt_id_UNIQUE` (`at_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions_type`
--

LOCK TABLES `auctions_type` WRITE;
/*!40000 ALTER TABLE `auctions_type` DISABLE KEYS */;
INSERT INTO `auctions_type` VALUES (2,'ENGLISH'),(1,'ONLINE');
/*!40000 ALTER TABLE `auctions_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lots`
--

DROP TABLE IF EXISTS `lots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lots` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_name` longtext NOT NULL,
  `l_description` longtext NOT NULL,
  `l_quantity` int(11) NOT NULL,
  `l_picture` varchar(45) DEFAULT 'images/no-img.jpg',
  `su_owner_login` varchar(45) NOT NULL,
  `l_date_added` datetime NOT NULL,
  `l_status` enum('BLOCKED','SOLED','ACTIVE','CONFIRMING','READY') NOT NULL DEFAULT 'CONFIRMING',
  `l_type` varchar(45) NOT NULL,
  `l_locale` enum('en','ru') DEFAULT NULL,
  PRIMARY KEY (`l_id`),
  UNIQUE KEY `l_id_UNIQUE` (`l_id`),
  KEY `fk_lots_site_users1_idx` (`su_owner_login`),
  CONSTRAINT `fk_lots_site_users1` FOREIGN KEY (`su_owner_login`) REFERENCES `site_users` (`su_login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lots`
--

LOCK TABLES `lots` WRITE;
/*!40000 ALTER TABLE `lots` DISABLE KEYS */;
INSERT INTO `lots` VALUES (1,'уцйуйцуйцу','q',2,'q','Admin','2018-04-09 03:10:38','ACTIVE','CAR','ru'),(3,'eee','q',1,'qqwwwq','Admin','2018-04-10 23:55:51','CONFIRMING','CAR','en'),(8,'a','a',1,'a','Admin','2018-03-22 00:00:00','SOLED','CAR','ru'),(11,'c','c',1,'c','Admin','2018-03-22 00:00:00','ACTIVE','CAR','en'),(12,'s','s',1,'s','Admin','2018-03-23 00:00:00','ACTIVE','CAR','ru'),(13,'q','q',1,'q','Admin','2018-03-23 00:00:00','ACTIVE','CAR','en'),(16,'q','q',1,'Q','Admin','2018-04-04 00:00:00','SOLED','CAR','en'),(17,'Ñ?Ñ?Ð¹','Ñ?Ð¹Ñ?',1,'Ñ?Ð¹Ñ?','Admin','2018-04-09 03:02:11','ACTIVE','CAR','en'),(18,'ãæÙ','ãÙæ',1,'ãæÙ','Admin','2018-04-11 11:54:55','READY','CAR','en'),(19,'ãæÙ','ãæÙ',1,'ãÙæ','Admin','2018-04-11 11:55:29','READY','CAR','ru'),(20,'???','???',1,'???','Admin','2018-04-11 11:58:11','READY','CAR','ru'),(21,'???','???',1,'???','Admin','2018-04-11 11:59:38','READY','CAR','ru'),(22,'&#1091;&#1081;&#1094;','&#1091;&#1081;',1,'eqw','Admin','2018-04-11 13:57:04','READY','CAR','en'),(23,'\0&\0#\01\00\09\01\0;\0&\0#\01\00\08\01\0;','&#1091;&#1081;&#1094;',1,'&#1091;&#1094;&#1081;','Admin','2018-04-11 13:59:19','READY','CAR','en'),(24,'&#1091;&#1081;&#1094;','&#1091;&#1094;&#1081;',1,'eqw','Admin','2018-04-11 14:02:43','READY','CAR','en'),(25,'&#1091;&#1081;','&#1091;&#1081;&#1094;',1,'&#1091;&#1081;&#1094;','Admin','2018-04-11 14:04:04','READY','CAR','en'),(26,'&#1091;&#1094;&#1081;','&#1091;&#1094;&#1081;',1,'&#1091;&#1081;&#1094;','Admin','2018-04-11 14:22:10','READY','CAR','en'),(27,'???','&#1091;&#1094;&#1081;',1,'&#1091;&#1094;&#1081;','Admin','2018-04-11 14:29:59','READY','CAR','en'),(33,'AAAAAAAAAAAAA','eqw',1,'','Admin','2018-04-11 15:46:48','ACTIVE','CAR','en'),(34,'QQQQQQQQQQQQ','eqw',1,'','Admin','2018-04-11 15:53:10','SOLED','CAR','en'),(35,'???','???',1,NULL,'Admin','2018-04-12 16:35:22','SOLED','CAR','ru'),(36,'??','???',1,NULL,'Admin','2018-04-12 15:37:21','READY','CAR','ru'),(37,'???','???\r\n',1,NULL,'Admin','2018-04-12 15:55:16','READY','CAR','ru'),(38,'???','???\r\n\r\n',1,NULL,'Admin','2018-04-12 15:58:51','READY','CAR','ru'),(39,'Ñ?Ð¹','Ñ?Ð¹Ñ?',1,NULL,'Admin','2018-04-12 15:59:41','READY','CAR','ru'),(40,'ãÙæ','ãÙæ',1,NULL,'Admin','2018-04-12 16:01:09','READY','CAR','ru'),(41,'&#1081;&#1094;','&#1091;&#1081;',1,NULL,'Admin','2018-04-12 16:01:25','READY','CAR','ru'),(42,'&#1091;&#1081;&#1094;','&#1091;',1,NULL,'Admin','2018-04-12 16:11:44','READY','CAR','ru'),(43,'&#1091;&#1081;&#1094;','&#1091;&#1081;&#1094;\r\n',1,NULL,'Admin','2018-04-12 16:14:33','READY','CAR','ru'),(44,'&#1091;&#1094;&#1081;','&#1091;&#1081;&#1094;\r\n',1,NULL,'Admin','2018-04-12 16:15:16','READY','CAR','ru'),(47,'???','???\r\n\r\n',1,NULL,'Admin','2018-04-12 16:22:44','READY','CAR','ru'),(48,'???','123qwe_+???????????????????????????????????.\r\n',1,NULL,'Admin','2018-04-12 16:23:13','READY','CAR','ru'),(49,'&#1091;&#1094;&#1081;&#1091;&#1081;&#1094;&#1091;&#1081;&#1094;&#1091;&#1081;&#1094;&#1091;&#1081;&#1094;&#1091;&#1081;&#1094;&#1091;&#1081;&#1094;','&#1091;&#1081;&#1094;&#1091;&#1094;&#1081;&#1091;&#1081;&#1094;&#1091;&#1081;&#1094;&#1091;&#1094;&#1081;&#1091;&#1081;&#1094;&#1091;&#1094;&#1081;&#1091;&#1094;&#1081;',1,NULL,'Admin','2018-04-12 16:31:17','READY','CAR','ru'),(50,'?????????????????','???????????????????',1,NULL,'Admin','2018-04-12 16:31:43','READY','CAR','ru'),(51,'Ñ?Ð¹Ñ?Ñ?Ð¹Ñ?Ñ?Ð¹Ñ?Ñ?Ñ?Ð¹Ñ?Ñ?Ð¹Ñ?Ð¹Ñ?','Ñ?Ñ?Ð¹Ñ?Ð¹Ñ?Ñ?Ñ?Ð¹Ñ?Ñ?Ð¹Ñ?Ð¹Ñ?Ñ?Ð¹Ñ?Ñ?\r\n',1,'images/a_082c3b7b_1.jpg','Admin','2018-04-12 16:32:29','READY','CAR','ru'),(52,'Ñ?Ð¹Ñ?','Ñ?Ñ?Ð¹',1,NULL,'Admin','2018-04-12 21:17:57','SOLED','CAR','ru'),(53,'ewq','ewq',1,NULL,'Admin','2018-04-14 13:15:45','SOLED','CAR','ru'),(54,'eqw','eqw',1,NULL,'Admin','2018-04-14 13:23:45','ACTIVE','CAR','ru'),(55,'q','q',1,NULL,'Admin','2018-04-14 13:28:22','ACTIVE','CAR','ru'),(56,'qe','eq',1,NULL,'Admin','2018-04-14 13:30:34','ACTIVE','CAR','ru'),(57,'eqw','eqw',1,NULL,'Admin','2018-04-14 13:32:25','ACTIVE','CAR','ru'),(58,'eqw','eqw',1,NULL,'Admin','2018-04-14 13:38:41','SOLED','CAR','ru'),(59,'eqw','ewq',1,NULL,'Admin','2018-04-14 13:44:13','ACTIVE','CAR','ru'),(60,'eeeee','eeee',1,NULL,'Admin','2018-04-14 13:45:45','ACTIVE','CAR','ru'),(61,'eqw','ewq',1,NULL,'Admin','2018-04-14 14:22:57','SOLED','CAR','ru'),(62,'ewq','eqw',1,'images/no-img.jpg','Admin','2018-04-17 22:56:22','READY','CAR','en');
/*!40000 ALTER TABLE `lots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_users`
--

DROP TABLE IF EXISTS `site_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_users` (
  `su_login` varchar(45) NOT NULL,
  `su_surname` longtext NOT NULL,
  `su_name` longtext NOT NULL,
  `su_password` longtext NOT NULL,
  `su_email` longtext NOT NULL,
  `su_phone` longtext NOT NULL,
  `su_passport_id` longtext NOT NULL,
  `su_passport_issued_by` longtext NOT NULL,
  `uc_id` int(11) NOT NULL,
  `sur_id` int(11) NOT NULL DEFAULT '1',
  `su_blocked` tinyint(1) NOT NULL DEFAULT '0',
  `su_picture` varchar(45) DEFAULT 'images/no-img.jpg',
  PRIMARY KEY (`su_login`),
  UNIQUE KEY `su_login_UNIQUE` (`su_login`),
  KEY `fk_site_users_users_countries_idx` (`uc_id`),
  KEY `fk_site_users_site_users_role1_idx` (`sur_id`),
  CONSTRAINT `fk_site_users_site_users_role1` FOREIGN KEY (`sur_id`) REFERENCES `site_users_role` (`sur_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_site_users_users_countries` FOREIGN KEY (`uc_id`) REFERENCES `users_countries` (`uc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_users`
--

LOCK TABLES `site_users` WRITE;
/*!40000 ALTER TABLE `site_users` DISABLE KEYS */;
INSERT INTO `site_users` VALUES ('Admin','Admin','Admin','e3afed0047b08059d0fada10f400c1e5','Admin@admin.com','1234567899','Admin','Admin',1,0,0,'images/a_082c3b7b_1.jpg'),('eqw','eqw','eqw','6bdcbb606161c47eab0615ff6e13313f','eqw@w.wq','1234567899','eqw','eqw',1,1,0,'images/a_082c3b7b_1.jpg'),('ewq','eqw','eqw','6bdcbb606161c47eab0615ff6e13313f','qw@eqw.eq','1234567899','ewq','eqw',1,1,0,'images/no-img.jpg'),('q','q','q','7694f4a66316e53c8cdd9d9954bd611d','qw@eqw.eq','1234567899','q','q',1,1,1,'images/no-img.jpg'),('qwe','&#1091;&#1094;&#1081;','?????????¹','006d2143154327a64d86a264aea225f3','qw@eqw.eq','1234567899','??????????¹????','??????????¹????',1,1,0,'images/no-img.jpg'),('qwerty','Ñ?Ñ?Ð¹','Ñ?Ñ?Ð¹','34a96e81cd2ba1cdafb2cb71a7a8040d','qw@eqw.eq','1234567899','Ð¹Ñ?Ñ?','Ñ?Ð¹Ñ?',1,1,0,'images/no-img.jpg'),('Roma','Ð Ð¾Ð¾Ð¾Ð¾Ð¼Ð°','Ð Ð¾Ð¼Ð°','f5b499002e7414b68682730818874f7a','roma@roma.by','1234567899','Ð Ð¾Ð¼Ð°','Ð Ð¾Ð¼Ð°',1,1,0,'images/no-img.jpg');
/*!40000 ALTER TABLE `site_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_users_role`
--

DROP TABLE IF EXISTS `site_users_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_users_role` (
  `sur_id` int(11) NOT NULL,
  `sur_role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`sur_id`),
  UNIQUE KEY `sur_role_name_UNIQUE` (`sur_role_name`),
  UNIQUE KEY `sur_id_UNIQUE` (`sur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_users_role`
--

LOCK TABLES `site_users_role` WRITE;
/*!40000 ALTER TABLE `site_users_role` DISABLE KEYS */;
INSERT INTO `site_users_role` VALUES (0,'ADMIN'),(1,'USER');
/*!40000 ALTER TABLE `site_users_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_participation_in_bidding`
--

DROP TABLE IF EXISTS `user_participation_in_bidding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_participation_in_bidding` (
  `su_login` varchar(45) NOT NULL,
  `a_id` int(11) NOT NULL,
  `upib_status` enum('WON','LOST','ACTIVE') NOT NULL,
  `upib_bet` decimal(19,2) DEFAULT NULL,
  `upib_last_bet_time` datetime DEFAULT NULL,
  PRIMARY KEY (`su_login`,`a_id`),
  KEY `fk_user_participation_in_bidding_site_users1_idx` (`su_login`),
  KEY `fk_user_participation_in_bidding_auctions1_idx` (`a_id`),
  CONSTRAINT `fk_user_participation_in_bidding_auctions1` FOREIGN KEY (`a_id`) REFERENCES `auctions` (`a_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_participation_in_bidding_site_users1` FOREIGN KEY (`su_login`) REFERENCES `site_users` (`su_login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_participation_in_bidding`
--

LOCK TABLES `user_participation_in_bidding` WRITE;
/*!40000 ALTER TABLE `user_participation_in_bidding` DISABLE KEYS */;
INSERT INTO `user_participation_in_bidding` VALUES ('Admin',1,'WON',11111.00,NULL),('Admin',2,'WON',0.00,NULL),('Admin',8,'WON',12.00,'2018-04-17 18:07:51'),('Admin',9,'WON',0.00,NULL),('Admin',11,'WON',0.00,NULL),('Admin',12,'WON',200.00,'2018-04-14 13:17:12'),('Admin',13,'WON',5.00,'2018-04-14 13:24:13'),('Admin',14,'WON',6.00,'2018-04-14 13:28:57'),('Admin',15,'ACTIVE',NULL,NULL),('Admin',16,'WON',1.00,'2018-04-14 13:34:17'),('Admin',17,'WON',1.00,'2018-04-14 13:38:48'),('Admin',18,'WON',1.00,'2018-04-14 13:44:19'),('Admin',19,'WON',1.00,'2018-04-14 13:45:50'),('Admin',20,'WON',3.00,'2018-04-14 14:23:04'),('eqw',1,'LOST',9999999999.00,NULL);
/*!40000 ALTER TABLE `user_participation_in_bidding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_countries`
--

DROP TABLE IF EXISTS `users_countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_countries` (
  `uc_id` int(11) NOT NULL,
  `uc_name` varchar(45) NOT NULL,
  PRIMARY KEY (`uc_id`),
  UNIQUE KEY `uc_name_UNIQUE` (`uc_name`),
  UNIQUE KEY `uc_id_UNIQUE` (`uc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_countries`
--

LOCK TABLES `users_countries` WRITE;
/*!40000 ALTER TABLE `users_countries` DISABLE KEYS */;
INSERT INTO `users_countries` VALUES (1,'Belarus'),(3,'Russia'),(2,'USA');
/*!40000 ALTER TABLE `users_countries` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-17 22:58:47
