-- MySQL dump 10.13  Distrib 5.7.22, for Linux (i686)
--
-- Host: localhost    Database: auction
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
  `a_minimum_price` decimal(60,2) NOT NULL,
  `auctions_type_at_id` int(11) NOT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `a_id_UNIQUE` (`a_id`),
  KEY `fk_auctions_auctions_type1_idx` (`l_id`),
  KEY `fk_auctions_lots1_idx` (`l_id`),
  KEY `fk_auctions_1_idx` (`auctions_type_at_id`),
  CONSTRAINT `fk_auction_lots1` FOREIGN KEY (`l_id`) REFERENCES `lots` (`l_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_auctions_auctions_type1` FOREIGN KEY (`auctions_type_at_id`) REFERENCES `auctions_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (21,74,'2018-05-09 20:58:46','2018-05-09 22:01:28','PENDING_PAYMENT',15.00,1),(22,73,'2018-05-09 20:59:22','2018-05-09 21:26:37','COMPLETED',22200.00,2),(23,72,'2018-05-09 20:59:49',NULL,'ACTIVE',900000.00,2),(24,71,'2018-05-09 21:00:23','2018-05-09 22:01:28','PENDING_PAYMENT',50.00,1),(25,70,'2018-05-09 21:00:41','2018-05-09 21:26:37','COMPLETED',1000000.00,2),(26,69,'2018-05-09 21:01:06','2018-05-09 21:26:37','COMPLETED',999900.00,2),(27,67,'2018-05-09 21:01:46','2018-05-09 21:21:37','COMPLETED',230000.00,2),(28,66,'2018-05-09 21:02:08','2018-05-09 22:06:28','PENDING_PAYMENT',30.00,1),(29,65,'2018-05-09 21:02:25','2018-05-09 21:21:37','COMPLETED',80.00,2),(30,85,'2018-05-09 21:03:03','2018-05-09 21:26:37','COMPLETED',600000.00,2),(31,83,'2018-05-09 21:03:16','2018-05-09 21:31:37','COMPLETED',8800000.00,2),(32,82,'2018-05-09 21:03:36','2018-05-09 21:26:37','COMPLETED',600.00,2),(33,80,'2018-05-09 21:03:59','2018-05-09 22:06:28','PENDING_PAYMENT',200000.00,1),(34,78,'2018-05-09 21:04:17','2018-05-09 21:21:37','COMPLETED',1000000.00,2),(35,77,'2018-05-09 21:04:43','2018-05-09 21:21:37','COMPLETED',80000.00,2),(36,75,'2018-05-09 21:05:25','2018-05-09 21:26:37','COMPLETED',5000.00,2),(37,63,'2018-05-09 21:05:54','2018-05-09 22:06:28','COMPLETED',90000.00,1);
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
  `l_picture` varchar(255) DEFAULT 'images/no-img.jpg',
  `su_owner_login` varchar(255) NOT NULL,
  `l_date_added` datetime NOT NULL,
  `l_status` enum('BLOCKED','SOLED','ACTIVE','CONFIRMING','READY') NOT NULL DEFAULT 'CONFIRMING',
  `l_type` enum('CAR','JET','ART','SPORT','REALTY') NOT NULL,
  `l_locale` enum('EN','RU') DEFAULT NULL,
  PRIMARY KEY (`l_id`),
  UNIQUE KEY `l_id_UNIQUE` (`l_id`),
  KEY `fk_lots_site_users1_idx` (`su_owner_login`),
  CONSTRAINT `fk_lots_site_users1` FOREIGN KEY (`su_owner_login`) REFERENCES `site_users` (`su_login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lots`
--

LOCK TABLES `lots` WRITE;
/*!40000 ALTER TABLE `lots` DISABLE KEYS */;
INSERT INTO `lots` VALUES (63,'BMW X7','Новый BMW, прямо с завода. Продаю, потому что подписал контракт с Jaguar.',1,'images/bmw-x7-concept-g07.jpg','user1','2018-05-09 14:48:18','SOLED','CAR','en'),(64,'ИЛ-86','Первый советский широкофюзеляжный пассажирский самолёт для авиалиний средней протяжённости.',1,'images/il86.jpg','user1','2018-05-09 14:50:27','CONFIRMING','JET','ru'),(65,'Хоккейная кофта','Хоккейная форма, купленная 5 лет назад. Ни разу не надевалась',5,'images/hockeyform.jpg','user1','2018-05-09 14:51:19','SOLED','SPORT','ru'),(66,'Боксерские перчатки','Покупал 5 лет назад, так и валялись в шкафу.',1,'images/boxinggloves.jpg','user1','2018-05-09 14:52:42','ACTIVE','SPORT','ru'),(67,'Дом под Минском','Дача под минском, состояние хорошее. Мебель и техника в наличии',1,'images/house1.jpg','user1','2018-05-09 14:53:33','SOLED','REALTY','ru'),(68,'Ferrari','Состояние хорошее, машина после ремонта. Была помята правая дверь',1,'images/ferrari.jpeg','user2','2018-05-09 14:54:31','CONFIRMING','CAR','ru'),(69,'Ан-24','Ан-24 — турбовинтовой пассажирский самолёт для линий малой и средней протяжённости. Дальность полёта: 2000 км. Крейсерская скорость: 450 км/ч. Максимальная взлётная масса: 21 т.',1,'images/an24.jpg','user2','2018-05-09 14:55:00','SOLED','JET','ru'),(70,'Чёрный квадрат','Работа Казимира Малевича, созданная в 1915 году:53, одна из самых обсуждаемых и самых известных картин в русском искусстве.',1,'images/blacksquare.jpeg','user2','2018-05-09 15:06:45','SOLED','ART','ru'),(71,'Мяч (футбольный, nike)','Мяч с Евро-2008',1,'images/footballboll.jpeg','user2','2018-05-09 15:07:18','ACTIVE','SPORT','ru'),(72,'Коттедж в Новгороде','Состояние новое, никто не жил, присутсвует не вся мебель.',1,'images/house4.jpeg','user2','2018-05-09 15:07:52','ACTIVE','REALTY','ru'),(73,'Audi a8','Машина в идеальном состоянии,в короткой базе,богатая комплектация,установлен автозапуск с любой точки мира,дожимы дверей,эл.багажник,родной пробег,Автомобиль обслуживается только в Ауди ',1,'images/audia8.jpeg','user2','2018-05-09 15:08:27','SOLED','CAR','ru'),(74,'Бюст Цезаря','Античный бюст римского государственного деятеля и полководца Гая Юлия Цезаря (100 год до н. э. — 44 года до н. э.), из берлинского Античного собрания.',1,'images/caesar.jpg','user2','2018-05-09 15:09:17','ACTIVE','ART','ru'),(75,'Mercedes w124','A car was bought in 2017 year, stood in the garage. No one was driving.',1,'images/mercedesw124.jpg','user4','2018-05-09 15:14:15','SOLED','CAR','en'),(76,'BMW e34','The engine does not work. The rest is good.',1,'images/bmwe34.jpeg','user4','2018-05-09 15:16:32','CONFIRMING','CAR','en'),(77,'Boeing 747','Aircraft are sold because of the purchase of new aircraft.',5,'images/boeing747.jpg','user4','2018-05-09 15:22:09','SOLED','JET','en'),(78,'Boeing 737','Aircraft are sold because of the purchase of new aircraft',9,'images/boeing737.jpg','user4','2018-05-09 15:25:02','SOLED','JET','en'),(79,'Football boots (NIKE)','New football boots. Made by Nike.',3,'images/footballboots.jpg','user4','2018-05-09 15:26:46','CONFIRMING','SPORT','en'),(80,'Portrait of Alexander 1','portrait of alexander 1',1,'images/aleksandr1.jpg','user4','2018-05-09 15:27:52','ACTIVE','ART','en'),(81,'House in LA','My ex house. Now the house is empty.',1,'images/house5.jpg','user4','2018-05-09 15:28:47','CONFIRMING','REALTY','en'),(82,'Basketball ball (old type)','Old type of a basketball ball. It\'s the ball of Michael Jordan',1,'images/basketballball.jpg','user4','2018-05-09 15:30:09','SOLED','SPORT','en'),(83,'Boeing 787','Jets from the factory. Written off.',1,'images/boeing787.jpeg','user4','2018-05-09 15:31:21','SOLED','JET','en'),(84,'Hockey skates','New hockey skates. Bought more than needed.',4,'images/skates.jpeg','user4','2018-05-09 15:32:45','CONFIRMING','SPORT','en'),(85,'A house in Germany','A new house. Was build in 2016. ',1,'images/house5.jpg','user4','2018-05-09 15:34:04','SOLED','REALTY','en');
/*!40000 ALTER TABLE `lots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_users`
--

DROP TABLE IF EXISTS `site_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_users` (
  `su_login` varchar(255) NOT NULL,
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
  `su_picture` varchar(255) DEFAULT 'images/no-img.jpg',
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
INSERT INTO `site_users` VALUES ('Admin','Admin','Admin','e3afed0047b08059d0fada10f400c1e5','Admin@auction.com','1234567899','MC9999999999','Minsk REC',1,0,0,'images/ninja_admin_regular_size_display.png'),('user1','Климук','Петр','24c9e15e52afc47c225b757e7bee1f9d','user1@auction.com','1234567899','MC321321312','Минское РУВД',1,1,0,'images/avatar.jpg'),('user2','Пелевин','Виктор','7e58d63b60197ceb55a1c487989a3720','user2@auction.com','1234567899','RU21321312321','Московское РУВД',3,1,0,'images/FaceCo-Homme.png'),('user3','Smith','John','92877af70a45fd6a2ed7fe81e1236b78','user3@auction.com','1234567899','QW12333321','New York',2,1,0,'images/no-img.jpg'),('user4','Cyrus','Miley','3f02ebe3d7929b091e3d8ccfde2f3bc6','user4@auction.com','1234567899','QQ123321312','Los Angeles',2,1,0,'images/avatar-7.png');
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
  `sur_role_name` varchar(255) NOT NULL,
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
  `su_login` varchar(255) NOT NULL,
  `a_id` int(11) NOT NULL,
  `upib_status` enum('WON','LOST','ACTIVE') NOT NULL,
  `upib_bet` decimal(60,2) DEFAULT NULL,
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
INSERT INTO `user_participation_in_bidding` VALUES ('user1',21,'LOST',30.00,'2018-05-09 21:10:10'),('user1',24,'WON',60.00,'2018-05-09 21:09:52'),('user1',27,'LOST',230000.00,'2018-05-09 21:09:39'),('user1',28,'LOST',35.00,'2018-05-09 21:09:10'),('user1',29,'LOST',85.00,'2018-05-09 21:08:48'),('user1',30,'LOST',700000.00,'2018-05-09 21:08:12'),('user1',33,'WON',2000000.00,'2018-05-09 21:07:56'),('user1',34,'WON',1111125.00,'2018-05-09 21:07:21'),('user1',35,'WON',9000000.00,'2018-05-09 21:07:03'),('user1',36,'LOST',6000.00,'2018-05-09 21:06:38'),('user1',37,'LOST',100000.00,'2018-05-09 21:06:18'),('user2',21,'WON',60.00,'2018-05-09 21:12:08'),('user2',22,'WON',30000.00,'2018-05-09 21:11:57'),('user2',25,'WON',1000000.00,'2018-05-09 21:11:43'),('user2',26,'WON',9990000.00,'2018-05-09 21:12:50'),('user2',27,'WON',230010.00,'2018-05-09 21:11:29'),('user2',28,'WON',45.00,'2018-05-09 21:11:13'),('user2',29,'WON',90.00,'2018-05-09 21:11:05'),('user2',30,'LOST',8000000.00,'2018-05-09 21:14:04'),('user2',32,'LOST',600.00,'2018-05-09 21:13:54'),('user2',36,'LOST',70000.00,'2018-05-09 21:13:31'),('user2',37,'LOST',1000005.00,'2018-05-09 21:13:16'),('user3',30,'WON',8000050.00,'2018-05-09 21:16:01'),('user3',32,'WON',700.00,'2018-05-09 21:15:46'),('user3',36,'WON',70050.00,'2018-05-09 21:15:30'),('user3',37,'LOST',1111111.00,'2018-05-09 21:15:02'),('user4',31,'WON',8800000.00,'2018-05-09 21:19:24'),('user4',34,'LOST',1111116.00,'2018-05-09 21:18:24'),('user4',37,'WON',1111116.00,'2018-05-09 21:18:04');
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

-- Dump completed on 2018-05-09 22:09:36
