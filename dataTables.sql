CREATE DATABASE  IF NOT EXISTS `tlf_resource` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tlf_resource`;
-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: tlf_resource
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `idbranch` int(11) NOT NULL,
  `next_branch_id` int(3) DEFAULT NULL,
  `branch_name` varchar(100) DEFAULT NULL,
  `idline` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbranch`),
  KEY `idline_idx` (`idline`),
  CONSTRAINT `idline` FOREIGN KEY (`idline`) REFERENCES `line` (`idline`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,3,'heathrow 2',21),(2,3,'heathrow terminal 5',21),(3,5,'heathrow',21),(4,5,'uxbridge',21),(5,0,'main',21),(6,99,'ruislip',34),(7,99,'ealing',34),(8,99,'main',34),(9,99,'woodford',34),(10,99,'epping',34);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distruption_message`
--

DROP TABLE IF EXISTS `distruption_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distruption_message` (
  `iddistruption_message` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`iddistruption_message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distruption_message`
--

LOCK TABLES `distruption_message` WRITE;
/*!40000 ALTER TABLE `distruption_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `distruption_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line`
--

DROP TABLE IF EXISTS `line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line` (
  `idline` int(11) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(200) DEFAULT NULL,
  `line_direction` varchar(50) DEFAULT NULL,
  `line_type` varchar(50) DEFAULT NULL,
  `branch` int(3) DEFAULT NULL,
  `next_branch` int(3) DEFAULT NULL,
  PRIMARY KEY (`idline`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line`
--

LOCK TABLES `line` WRITE;
/*!40000 ALTER TABLE `line` DISABLE KEYS */;
INSERT INTO `line` VALUES (1,'northern','north','underground',NULL,NULL),(2,'northern','south','underground',NULL,NULL),(20,'picadilly','westbound','underground',NULL,NULL),(21,'picadilly','eastbound','underground',NULL,NULL),(22,'victoria','eastbound','underground',NULL,NULL),(23,'victoria','westbound','underground',NULL,NULL),(24,'metropolitan','eastbound','underground',NULL,NULL),(25,'metropolitan','westbound','underground',NULL,NULL),(26,'jubilee','eastbound','underground',NULL,NULL),(27,'jubilee','westbound','underground',NULL,NULL),(28,'hammersmith','eastbound','underground',NULL,NULL),(29,'hammersmith','westbound','underground',NULL,NULL),(30,'district','eastbound','underground',NULL,NULL),(31,'district','westbound','underground',NULL,NULL),(32,'circle','clockwise','underground',NULL,NULL),(33,'circle','counter clockwise','underground',NULL,NULL),(34,'central','eastbound','underground',NULL,NULL),(35,'central','westbound','underground',NULL,NULL),(36,'bakerloo','north','underground',1,3),(37,'bakerloo','south','underground',NULL,NULL),(38,'bakerloo','north','underground',3,0);
/*!40000 ALTER TABLE `line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `next_branch`
--

DROP TABLE IF EXISTS `next_branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `next_branch` (
  `idnext_branch` int(11) NOT NULL AUTO_INCREMENT,
  `idbranch` int(11) DEFAULT NULL,
  `next_branch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`idnext_branch`),
  UNIQUE KEY `idnext_branch_UNIQUE` (`idnext_branch`),
  KEY `next_branch_idx` (`idbranch`),
  CONSTRAINT `next_branch` FOREIGN KEY (`idbranch`) REFERENCES `branch` (`idbranch`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `next_branch`
--

LOCK TABLES `next_branch` WRITE;
/*!40000 ALTER TABLE `next_branch` DISABLE KEYS */;
INSERT INTO `next_branch` VALUES (1,6,8),(2,7,8),(3,8,9),(4,8,10),(5,9,0),(6,10,0),(7,1,3),(8,2,3),(9,3,5),(10,4,5),(11,5,0);
/*!40000 ALTER TABLE `next_branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `idstation` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`idstation`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Heathorw 123'),(2,'Heathrow 5'),(3,'Heathrow 4'),(4,'Hatton Cross'),(5,'Hounslow West'),(6,'Hounslow Central'),(7,'Hounslow East'),(8,'Osterley'),(9,'Boston Manor'),(10,'Northfields'),(11,'South Ealing');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station_uniq`
--

DROP TABLE IF EXISTS `station_uniq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station_uniq` (
  `idstation_uniq` int(11) NOT NULL AUTO_INCREMENT,
  `idbranch` int(11) DEFAULT NULL,
  `idstation` int(11) DEFAULT NULL,
  `station_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`idstation_uniq`),
  UNIQUE KEY `idstation_uniq_UNIQUE` (`idstation_uniq`),
  KEY `idline_idx` (`idbranch`),
  KEY `idstation_idx` (`idstation`),
  CONSTRAINT `idbranch` FOREIGN KEY (`idbranch`) REFERENCES `branch` (`idbranch`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idstation_un` FOREIGN KEY (`idstation`) REFERENCES `station` (`idstation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_uniq`
--

LOCK TABLES `station_uniq` WRITE;
/*!40000 ALTER TABLE `station_uniq` DISABLE KEYS */;
INSERT INTO `station_uniq` VALUES (1,1,2,1),(2,1,1,2),(3,2,3,1),(4,2,1,2),(5,3,4,1),(6,3,5,2),(7,3,6,3),(8,3,7,4),(9,3,8,5),(10,3,9,6),(11,3,10,7),(12,3,11,8);
/*!40000 ALTER TABLE `station_uniq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_stations`
--

DROP TABLE IF EXISTS `user_stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_stations` (
  `iduser_stations` int(11) NOT NULL,
  `idstations` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  PRIMARY KEY (`iduser_stations`),
  KEY `userid_idx` (`iduser`),
  KEY `idstation_idx` (`idstations`),
  CONSTRAINT `idstation` FOREIGN KEY (`idstations`) REFERENCES `station` (`idstation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `iduser` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_stations`
--

LOCK TABLES `user_stations` WRITE;
/*!40000 ALTER TABLE `user_stations` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_stations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-22 19:54:57
