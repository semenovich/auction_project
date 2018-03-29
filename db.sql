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
  `a_status` enum('PENDING_PAYMENT','COMPLETED','ACTIVE','WITHDRAW') NOT NULL DEFAULT 'ACTIVE',
  `a_minimum_price` decimal(19,2) NOT NULL,
  `a_current_price` decimal(19,2) NOT NULL DEFAULT '0.00',
  `su_login_last_bet` varchar(45) DEFAULT NULL,
  `auctions_type_at_id` int(11) NOT NULL,
  `a_last_bet_time` datetime DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `a_id_UNIQUE` (`a_id`),
  KEY `fk_auctions_site_users1_idx` (`su_login_last_bet`),
  KEY `fk_auctions_auctions_type1_idx` (`l_id`),
  KEY `fk_auctions_lots1_idx` (`l_id`),
  KEY `fk_auctions_1_idx` (`auctions_type_at_id`),
  CONSTRAINT `fk_auction_lots1` FOREIGN KEY (`l_id`) REFERENCES `lots` (`l_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_auctions_auctions_type1` FOREIGN KEY (`auctions_type_at_id`) REFERENCES `auctions_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_auctions_site_users1` FOREIGN KEY (`su_login_last_bet`) REFERENCES `site_users` (`su_login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (1,1,'2015-12-12 00:00:00','2018-03-28 15:59:00','PENDING_PAYMENT',1.00,1.00,'Admin',1,NULL),(2,3,'2018-03-21 00:00:00',NULL,'PENDING_PAYMENT',12.00,0.00,'Admin',2,'2018-03-28 16:00:00'),(4,8,'2018-03-22 00:00:00',NULL,'PENDING_PAYMENT',12.00,10.00,'Admin',1,'2018-03-25 00:00:00'),(5,11,'2018-03-22 00:00:00',NULL,'PENDING_PAYMENT',12.00,0.00,NULL,1,NULL);
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
  `at_type_name` enum('ONLINE','ENGLISH','BLITZ') NOT NULL,
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
INSERT INTO `auctions_type` VALUES (1,'ONLINE'),(2,'ENGLISH'),(3,'BLITZ');
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
  `l_name` varchar(45) NOT NULL,
  `l_description` longtext NOT NULL,
  `l_quantity` int(11) NOT NULL,
  `l_picture` longtext,
  `su_owner_login` varchar(45) NOT NULL,
  `l_date_added` date NOT NULL,
  `l_status` enum('BLOCKED','SOLED','ACTIVE','CONFIRMING','READY') NOT NULL DEFAULT 'CONFIRMING',
  `l_type` varchar(45) NOT NULL,
  PRIMARY KEY (`l_id`),
  UNIQUE KEY `l_id_UNIQUE` (`l_id`),
  KEY `fk_lots_site_users1_idx` (`su_owner_login`),
  CONSTRAINT `fk_lots_site_users1` FOREIGN KEY (`su_owner_login`) REFERENCES `site_users` (`su_login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lots`
--

LOCK TABLES `lots` WRITE;
/*!40000 ALTER TABLE `lots` DISABLE KEYS */;
INSERT INTO `lots` VALUES (1,'q','q',2,'q','Admin','2017-05-08','ACTIVE','CAR'),(3,'q','q',1,'q','Admin','2018-03-21','CONFIRMING','CAR'),(8,'a','a',1,'a','Admin','2018-03-22','SOLED','CAR'),(11,'c','c',1,'c','Admin','2018-03-22','ACTIVE','CAR'),(12,'s','s',1,'s','Admin','2018-03-23','ACTIVE','CAR'),(13,'q','q',1,'q','Admin','2018-03-23','ACTIVE','CAR');
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
  `su_surname` varchar(45) NOT NULL,
  `su_name` varchar(45) NOT NULL,
  `su_password` varchar(45) NOT NULL,
  `su_email` varchar(45) NOT NULL,
  `su_phone` varchar(45) NOT NULL,
  `su_passport_id` varchar(45) DEFAULT NULL,
  `su_passport_issued_by` varchar(45) NOT NULL,
  `uc_id` int(11) NOT NULL,
  `sur_id` int(11) NOT NULL DEFAULT '1',
  `su_blocked` tinyint(1) NOT NULL DEFAULT '0',
  `su_picture` longtext,
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
INSERT INTO `site_users` VALUES ('Admin','Admin','Admin','e3afed0047b08059d0fada10f400c1e5','Admin@admin.com','1234567899','Admin','Admin',1,0,0,NULL),('eqw','eqw','eqw','6bdcbb606161c47eab0615ff6e13313f','eqw@w.wq','1234567899','eqw','eqw',1,1,0,'eqw'),('q','q','q','7694f4a66316e53c8cdd9d9954bd611d','qw@eqw.eq','1234567899','q','q',1,1,0,'q');
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
INSERT INTO `user_participation_in_bidding` VALUES ('Admin',1,'WON'),('Admin',2,'WON'),('eqw',1,'LOST');
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

-- Dump completed on 2018-03-30  1:40:54
