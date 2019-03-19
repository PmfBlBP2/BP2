CREATE DATABASE  IF NOT EXISTS `filmovi_2019` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `filmovi_2019`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: var_filmovi
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `drzava`
--

DROP TABLE IF EXISTS `drzava`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drzava` (
  `DrzavaId` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`DrzavaId`),
  UNIQUE KEY `Naziv` (`Naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drzava`
--

LOCK TABLES `drzava` WRITE;
/*!40000 ALTER TABLE `drzava` DISABLE KEYS */;
INSERT INTO `drzava` VALUES (2,'SFRJ'),(4,'UK'),(1,'USA'),(3,'Србија');
/*!40000 ALTER TABLE `drzava` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `film` (
  `FilmId` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ZanrId` int(11) NOT NULL,
  `Trajanje` int(11) DEFAULT '90' COMMENT 'Trajanje u minutama',
  `Godina` smallint(6) DEFAULT NULL COMMENT 'Godina proizvodnje',
  `DrzavaId` int(11) NOT NULL,
  `Ocena` float DEFAULT NULL,
  `Opis` text COLLATE utf8_unicode_ci,
  `Zarada` double DEFAULT NULL,
  PRIMARY KEY (`FilmId`),
  KEY `ZanrId` (`ZanrId`),
  KEY `DrzavaId` (`DrzavaId`),
  CONSTRAINT `film_ibfk_1` FOREIGN KEY (`ZanrId`) REFERENCES `zanr` (`ZanrId`),
  CONSTRAINT `film_ibfk_2` FOREIGN KEY (`DrzavaId`) REFERENCES `drzava` (`DrzavaId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Последњи круг у Монци',2,102,1989,2,7.8,'Interesantno...',1000000),(2,'Ко то тамо пева',5,86,1980,2,8.9,NULL,2000000),(3,'Platoon',4,120,1986,1,8.1,'Rat u Vijetnamu iz ugla 19-godišnjeg regruta',9000000),(4,'Професионалац',5,95,2003,3,7,'Po pozorišnoj predstavi',1500000),(5,'Лепа села лепо горе',4,90,1995,3,8.43,NULL,2200000),(7,'Ми нисмо анђели',5,92,1994,3,7.7,NULL,4000000),(9,'Big Lebowski',5,90,1991,1,10,NULL,2000000),(10,'Full Metal Jacket',4,97,1987,1,8.3,'rat + drama A pragmatic U.S. Marine observes the dehumanizing effects the U.S.-Vietnam War has on his fellow recruits from their brutal boot camp training to the bloody street fighting in Hue.',1300000);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glumac`
--

DROP TABLE IF EXISTS `glumac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `glumac` (
  `GlumacId` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Nadimak` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Prezime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DrzavaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`GlumacId`),
  UNIQUE KEY `ReziserId_UNIQUE` (`GlumacId`),
  KEY `DrzavaId` (`DrzavaId`),
  CONSTRAINT `glumac_ibfk_1` FOREIGN KEY (`DrzavaId`) REFERENCES `drzava` (`DrzavaId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glumac`
--

LOCK TABLES `glumac` WRITE;
/*!40000 ALTER TABLE `glumac` DISABLE KEYS */;
INSERT INTO `glumac` VALUES (1,'Драган','','Николић',3),(2,'Јосиф','','Татић',3),(3,'Предраг','','Ејдус',3),(4,'Данило','Бата','Стојковић',3),(5,'Мики',NULL,'Крстовић',3),(6,'Павле','Паја','Вујисић',3),(7,'Неда',NULL,'Арнедић',3),(8,'Славко',NULL,'Штимац',3),(9,'Александар',NULL,'Берчек',3),(10,'Charlie ',NULL,'Sheen',1),(11,'William ',NULL,'Defo',1),(12,'Tom',NULL,'Beringer',1),(13,'Бора',NULL,'Тодоровић',3),(14,'Бранислав ',NULL,'Лечић',3),(15,'Драган ',NULL,'Јовановић',3),(16,'Никола ',NULL,'Којо',3),(17,'Зоран ',NULL,'Цвијановић',3),(18,'Драган',NULL,'Бјелогрлић',3),(19,'Милорад','Манда','Мандић',3),(20,'Предраг','Мики','Манојловић',3),(21,'Весна',NULL,'Тривалић',3),(22,'Соња',NULL,'Савић',3),(23,'Jeff ',NULL,'Bridges',1),(24,'John',NULL,'Torturro',1),(25,'Слободан',NULL,'Алигрудић',3),(26,'John',NULL,'Goodman',1);
/*!40000 ALTER TABLE `glumac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glumio`
--

DROP TABLE IF EXISTS `glumio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `glumio` (
  `GlumacId` int(11) NOT NULL,
  `FilmId` int(11) NOT NULL,
  UNIQUE KEY `GlumacId_2` (`GlumacId`,`FilmId`),
  KEY `GlumacId` (`GlumacId`),
  KEY `FilmId` (`FilmId`),
  CONSTRAINT `glumio_ibfk_1` FOREIGN KEY (`GlumacId`) REFERENCES `glumac` (`GlumacId`),
  CONSTRAINT `glumio_ibfk_2` FOREIGN KEY (`FilmId`) REFERENCES `film` (`FilmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glumio`
--

LOCK TABLES `glumio` WRITE;
/*!40000 ALTER TABLE `glumio` DISABLE KEYS */;
INSERT INTO `glumio` VALUES (1,1),(1,2),(2,1),(2,4),(3,1),(4,1),(4,2),(5,1),(5,4),(6,2),(7,2),(7,4),(8,2),(9,2),(10,3),(11,3),(12,3),(13,4),(14,4),(15,4),(16,5),(17,5),(17,7),(18,5),(18,7),(19,5),(19,7),(20,7),(21,7),(22,7),(23,9),(24,9),(25,2),(26,9);
/*!40000 ALTER TABLE `glumio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pisao_scenario`
--

DROP TABLE IF EXISTS `pisao_scenario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pisao_scenario` (
  `ScenaristaId` int(11) NOT NULL,
  `FilmId` int(11) NOT NULL,
  UNIQUE KEY `ScenaristaId_2` (`ScenaristaId`,`FilmId`),
  KEY `ScenaristaId` (`ScenaristaId`),
  KEY `FilmId` (`FilmId`),
  CONSTRAINT `pisao_scenario_ibfk_1` FOREIGN KEY (`ScenaristaId`) REFERENCES `scenarista` (`ScenaristaId`),
  CONSTRAINT `pisao_scenario_ibfk_2` FOREIGN KEY (`FilmId`) REFERENCES `film` (`FilmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pisao_scenario`
--

LOCK TABLES `pisao_scenario` WRITE;
/*!40000 ALTER TABLE `pisao_scenario` DISABLE KEYS */;
INSERT INTO `pisao_scenario` VALUES (1,2),(1,4),(2,2),(3,3),(4,9),(5,9),(6,10),(7,10),(8,1);
/*!40000 ALTER TABLE `pisao_scenario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezirao`
--

DROP TABLE IF EXISTS `rezirao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezirao` (
  `ReziserId` int(11) NOT NULL,
  `FilmId` int(11) NOT NULL,
  UNIQUE KEY `ReziserId_2` (`ReziserId`,`FilmId`),
  KEY `ReziserId` (`ReziserId`),
  KEY `FilmId` (`FilmId`),
  CONSTRAINT `rezirao_ibfk_1` FOREIGN KEY (`ReziserId`) REFERENCES `reziser` (`ReziserId`),
  CONSTRAINT `rezirao_ibfk_2` FOREIGN KEY (`FilmId`) REFERENCES `film` (`FilmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezirao`
--

LOCK TABLES `rezirao` WRITE;
/*!40000 ALTER TABLE `rezirao` DISABLE KEYS */;
INSERT INTO `rezirao` VALUES (1,5),(1,7),(2,1),(3,2),(4,4),(5,3),(6,9),(7,9),(8,10);
/*!40000 ALTER TABLE `rezirao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reziser`
--

DROP TABLE IF EXISTS `reziser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reziser` (
  `ReziserId` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DrzavaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ReziserId`),
  UNIQUE KEY `ReziserId_UNIQUE` (`ReziserId`),
  KEY `DrzavaId` (`DrzavaId`),
  CONSTRAINT `reziser_ibfk_1` FOREIGN KEY (`DrzavaId`) REFERENCES `drzava` (`DrzavaId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reziser`
--

LOCK TABLES `reziser` WRITE;
/*!40000 ALTER TABLE `reziser` DISABLE KEYS */;
INSERT INTO `reziser` VALUES (1,'Срђан','Драгојевић',3),(2,'Александар ','Бошковић',3),(3,'Слободан','Шијан',2),(4,'Душан','Ковачевић',3),(5,'Oliver','Stone',1),(6,'Ethan','Koen',1),(7,'Joel','Koen',1),(8,'Stanley','Kubrick',4);
/*!40000 ALTER TABLE `reziser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scenarista`
--

DROP TABLE IF EXISTS `scenarista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scenarista` (
  `ScenaristaId` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DrzavaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ScenaristaId`),
  UNIQUE KEY `ReziserId_UNIQUE` (`ScenaristaId`),
  KEY `DrzavaId` (`DrzavaId`),
  CONSTRAINT `scenarista_ibfk_1` FOREIGN KEY (`DrzavaId`) REFERENCES `drzava` (`DrzavaId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scenarista`
--

LOCK TABLES `scenarista` WRITE;
/*!40000 ALTER TABLE `scenarista` DISABLE KEYS */;
INSERT INTO `scenarista` VALUES (1,'Душан','Ковачевић',3),(2,'Вања','Булић',3),(3,'Oliver','Stone',1),(4,'Joel','Coen',1),(5,'Ethan','Coen',1),(6,'Stanlie','Kubric',4),(7,'Gustav','Hasfold',1),(8,'Душан','Прелевић',3),(9,'Владимир','Кецмановић',NULL);
/*!40000 ALTER TABLE `scenarista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zanr`
--

DROP TABLE IF EXISTS `zanr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zanr` (
  `ZanrId` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ZanrId`),
  UNIQUE KEY `Naziv_2` (`Naziv`),
  KEY `Naziv` (`Naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zanr`
--

LOCK TABLES `zanr` WRITE;
/*!40000 ALTER TABLE `zanr` DISABLE KEYS */;
INSERT INTO `zanr` VALUES (2,'акциони'),(3,'драма'),(5,'комедија'),(1,'криминалистички'),(7,'научна фантастика'),(4,'ратни'),(6,'фантастика');
/*!40000 ALTER TABLE `zanr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'var_filmovi'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-19 10:34:14
