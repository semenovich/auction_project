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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (1,3,'2018-04-10 23:55:51',NULL,'ACTIVE',12.00,2),(2,4,'2018-04-10 23:55:51','2018-04-10 23:55:51','COMPLETED',12.00,2),(3,5,'2018-04-10 23:55:51','2018-04-10 23:55:51','ACTIVE',12.00,2),(4,6,'2018-04-10 23:55:51',NULL,'ACTIVE',12.00,2),(5,7,'2018-04-10 23:55:51','2018-04-22 02:20:40','PENDING_PAYMENT',12.00,2),(6,15,'2018-04-10 23:55:51','2018-04-10 23:55:51','COMPLETED',12.00,2),(7,16,'2018-04-10 23:55:51','2018-04-10 23:55:51','COMPLETED',12.00,2),(8,12,'2018-04-10 23:55:51','2018-04-10 23:55:51','ACTIVE',12.00,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lots`
--

LOCK TABLES `lots` WRITE;
/*!40000 ALTER TABLE `lots` DISABLE KEYS */;
INSERT INTO `lots` VALUES (1,'BlockLot','q',2,'q','Admin','2018-04-09 03:10:38','CONFIRMING','CAR','ru'),(2,'UnblockLot','q',1,'qqwwwq','Admin','2018-04-10 23:55:51','BLOCKED','CAR','en'),(3,'AuctionLot','q',1,'q','Admin','2018-04-10 23:55:51','ACTIVE','CAR','en'),(4,'AuctionPendingLot','q',1,'q','Admin','2018-04-10 23:55:51','ACTIVE','CAR','en'),(5,'AuctionCurrentBet','q',1,'q','Admin','2018-04-10 23:55:51','ACTIVE','CAR','en'),(6,'ActiveAuctionLot','q',1,'q','Admin','2018-04-10 23:55:51','ACTIVE','CAR','en'),(7,'CompleteAuctionLot','q',1,'q','Admin','2018-04-10 23:55:51','ACTIVE','CAR','en'),(8,'InfoLot','InfoLot',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(9,'Delete','q',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(10,'EditLot','EditLot',2,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(11,'ListLot','q',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(12,'SearchLot','q',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(13,'TypeLot','q',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(14,'WaitingLot','q',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(15,'UserWinLot','q',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en'),(16,'InfoAuction','q',1,'q','Admin','2018-04-10 23:55:51','READY','CAR','en');
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
INSERT INTO `site_users` VALUES ('Admin','Admin','Admin','e3afed0047b08059d0fada10f400c1e5','Admin@admin.com','1234567899','Admin','Admin',1,0,0,'images/a_082c3b7b_1.jpg'),('BlockedUser','BlockedUser','BlockedUser','6bdcbb606161c47eab0615ff6e13313f','q@q.q','1234567899','q','q',1,1,1,'images/no-img.jpg'),('BlockUser','BlockUser','BlockUser','6bdcbb606161c47eab0615ff6e13313f','eqw@w.wq','1234567899','eqw','eqw',1,1,0,'images/a_082c3b7b_1.jpg'),('EditUser','EditUser','EditUser','EditUser','EditUser','EditUser','EditUser','EditUser',1,1,0,'images/no-img.jpg'),('InfoUser','InfoUser','InfoUser','6bdcbb606161c47eab0615ff6e13313f','InfoUser@InfoUser.InfoUser','InfoUser','InfoUser','InfoUser',1,1,0,'images/no-img.jpg'),('LoginUser','LoginUser','LoginUser','e3afed0047b08059d0fada10f400c1e5','qw@eqw.eq','1234567899','ewq','eqw',1,1,0,'images/no-img.jpg'),('SearchUser1','SearchUser1','SearchUser1','SearchUser','SearchUser@SearchUser.SearchUser','1234567899','SearchUser','SearchUser',1,1,0,'images/no-img.jpg'),('SearchUser2','SearchUser2','SearchUser2','SearchUser','SearchUser@SearchUser.SearchUser','1234567899','SearchUser','SearchUser',1,1,0,'images/no-img.jpg'),('UnblockUser','UnblockUser','UnblockUser','6bdcbb606161c47eab0615ff6e13313f','qw@eqw.eq','1234567899','ewq','eqw',1,1,1,'images/no-img.jpg');
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
INSERT INTO `user_participation_in_bidding` VALUES ('Admin',3,'ACTIVE',20.00,'2018-04-10 23:55:51'),('Admin',5,'ACTIVE',20.00,'2018-04-10 23:55:51'),('Admin',6,'WON',20.00,'2018-04-10 23:55:51'),('Admin',7,'WON',20.00,'2018-04-10 23:55:51');
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

-- Dump completed on 2018-04-22 13:30:47
