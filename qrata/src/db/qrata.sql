-- MySQL dump 10.13  Distrib 8.0.17, for Linux (x86_64)
--
-- Host: localhost    Database: qrata
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `category_type` smallint(6) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsaok720gsu4u2wrgbk10b5n8d` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,NULL,NULL,NULL,1,'1234564',1,'Body & Soul',NULL),(2,NULL,NULL,NULL,1,'45488',1,'Life Stages',NULL),(3,NULL,'2019-07-25 14:34:17','2019-07-25 14:34:17',1,'213',1,'Good Life',NULL),(4,NULL,'2019-07-26 11:53:01','2019-07-26 11:53:01',1,'456',1,'Home Life',3),(5,NULL,'2019-07-26 18:18:49','2019-07-26 18:18:49',1,'658',1,'Technology',3),(6,NULL,'2019-07-26 18:19:40','2019-07-26 18:19:40',1,'486',1,'Sports, Games & Cars',3),(7,NULL,'2019-07-26 18:20:53','2019-07-26 18:20:53',1,'6589',1,'Money & Business',3),(8,NULL,'2019-07-26 18:21:54',NULL,1,'965',2,'Learn How & Why',2),(112,NULL,NULL,NULL,NULL,NULL,NULL,'Countries & Cultures',111),(113,NULL,NULL,NULL,NULL,NULL,NULL,'Africa',112),(114,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Africa',113),(115,NULL,NULL,NULL,NULL,NULL,NULL,'Famous People - Africa',113),(117,NULL,NULL,NULL,NULL,NULL,NULL,'African Resources',113),(118,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Africa',113),(119,NULL,NULL,NULL,NULL,NULL,NULL,'Australia',112),(122,NULL,NULL,NULL,NULL,NULL,NULL,'Geography & Landmarks - Austrailia',119),(123,NULL,NULL,NULL,NULL,NULL,NULL,'History - General',119),(124,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Australia',119),(125,NULL,NULL,NULL,NULL,NULL,NULL,'Central America',112),(128,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Central America',125),(130,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Central America',125),(131,NULL,NULL,NULL,NULL,NULL,NULL,'Asia (Central)',112),(132,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Central Asia',131),(134,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Central Asia',131),(137,NULL,NULL,NULL,NULL,NULL,NULL,'Asia (East) - General',112),(143,NULL,NULL,NULL,NULL,NULL,NULL,'Europe',112),(144,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Europe General',143),(146,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Europe',143),(149,NULL,NULL,NULL,NULL,NULL,NULL,'Travel Resources',112),(155,NULL,NULL,NULL,NULL,NULL,NULL,'Middle East',112),(156,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Middle East',155),(157,NULL,NULL,NULL,NULL,NULL,NULL,'Famous People - Middle East',155),(158,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Middle East (1)',155),(159,NULL,NULL,NULL,NULL,NULL,NULL,'History - Middle East (1)',155),(160,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Middle East',155),(167,NULL,NULL,NULL,NULL,NULL,NULL,'Pacific (North)',112),(168,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Pacific General',167),(170,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - South Pacific (1)',167),(172,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Hawaii',167),(173,NULL,NULL,NULL,NULL,NULL,NULL,'Famous People',111),(174,NULL,NULL,NULL,NULL,NULL,NULL,'Arts',173),(177,NULL,NULL,NULL,NULL,NULL,NULL,'Painting',174),(178,NULL,NULL,NULL,NULL,NULL,NULL,'Photography - General',174),(180,NULL,NULL,NULL,NULL,NULL,NULL,'Business',173),(185,NULL,NULL,NULL,NULL,NULL,NULL,'Media',180),(186,NULL,NULL,NULL,NULL,NULL,NULL,'Entertainment',173),(187,NULL,NULL,NULL,NULL,NULL,NULL,'Movies - General',186),(188,NULL,NULL,NULL,NULL,NULL,NULL,'Music - General',186),(191,NULL,NULL,NULL,NULL,NULL,NULL,'TV',186),(192,NULL,NULL,NULL,NULL,NULL,NULL,'General',173),(193,NULL,NULL,NULL,NULL,NULL,NULL,'African-Americans',192),(194,NULL,NULL,NULL,NULL,NULL,NULL,'Asian-Americans',192),(195,NULL,NULL,NULL,NULL,NULL,NULL,'Education',192),(196,NULL,NULL,NULL,NULL,NULL,NULL,'European-Americans',192),(198,NULL,NULL,NULL,NULL,NULL,NULL,'Gossip',192),(199,NULL,NULL,NULL,NULL,NULL,NULL,'Hispanic-Americans',192),(200,NULL,NULL,NULL,NULL,NULL,NULL,'Law',192),(202,NULL,NULL,NULL,NULL,NULL,NULL,'Native Americans',192),(203,NULL,NULL,NULL,NULL,NULL,NULL,'History',173),(204,NULL,NULL,NULL,NULL,NULL,NULL,'Ancient - Famous People',203),(205,NULL,NULL,NULL,NULL,NULL,NULL,'Medieval - Famous People',203),(206,NULL,NULL,NULL,NULL,NULL,NULL,'Renaissance - Famous People',203),(207,NULL,NULL,NULL,NULL,NULL,NULL,'Industrial Era - Famous People',203),(208,NULL,NULL,NULL,NULL,NULL,NULL,'Modern - Famous People',203),(209,NULL,NULL,NULL,NULL,NULL,NULL,'Inventors',173),(210,NULL,NULL,NULL,NULL,NULL,NULL,'Communications',209),(211,NULL,NULL,NULL,NULL,NULL,NULL,'Electronics',209),(212,NULL,NULL,NULL,NULL,NULL,NULL,'Energy',209),(213,NULL,NULL,NULL,NULL,NULL,NULL,'Information Technology',209),(214,NULL,NULL,NULL,NULL,NULL,NULL,'Manufacturing',209),(215,NULL,NULL,NULL,NULL,NULL,NULL,'Materials',209),(216,NULL,NULL,NULL,NULL,NULL,NULL,'Transportation',209),(217,NULL,NULL,NULL,NULL,NULL,NULL,'Politics & Government & Philosophy',173),(218,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - Country - General',217),(219,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - Historical - General',217),(221,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - Global - General',217),(222,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - US',217),(223,NULL,NULL,NULL,NULL,NULL,NULL,'Science',173),(224,NULL,NULL,NULL,NULL,NULL,NULL,'Astronomy',223),(226,NULL,NULL,NULL,NULL,NULL,NULL,'Chemistry',223),(228,NULL,NULL,NULL,NULL,NULL,NULL,'Physics - General',223),(230,NULL,NULL,NULL,NULL,NULL,NULL,'Sports',173),(236,NULL,NULL,NULL,NULL,NULL,NULL,'Olympics - Famous People',230),(239,NULL,NULL,NULL,NULL,NULL,NULL,'Tennis - Players',230),(240,NULL,NULL,NULL,NULL,NULL,NULL,'Women',173),(241,NULL,NULL,NULL,NULL,NULL,NULL,'Arts - Women',240),(242,NULL,NULL,NULL,NULL,NULL,NULL,'Business - Women',240),(244,NULL,NULL,NULL,NULL,NULL,NULL,'Historical - Women',240),(246,NULL,NULL,NULL,NULL,NULL,NULL,'Science - Women',240),(248,NULL,NULL,NULL,NULL,NULL,NULL,'Geography & Landmarks',111),(249,NULL,NULL,NULL,NULL,NULL,NULL,'Africa',248),(256,NULL,NULL,NULL,NULL,NULL,NULL,'Asia',248),(262,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks - Asia',256),(263,NULL,NULL,NULL,NULL,NULL,NULL,'Australia & Antarctica',248),(270,NULL,NULL,NULL,NULL,NULL,NULL,'Canada',248),(271,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Agricultural',270),(272,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Canadian Cities',270),(273,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Climate',270),(274,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Country',270),(275,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Geological',270),(277,NULL,NULL,NULL,NULL,NULL,NULL,'Central & S. America',248),(284,NULL,NULL,NULL,NULL,NULL,NULL,'Europe',248),(291,NULL,NULL,NULL,NULL,NULL,NULL,'Middle East',248),(293,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Middle East Cities',291),(295,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Country',291),(297,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Middle East (2)',291),(298,NULL,NULL,NULL,NULL,NULL,NULL,'Oceans & Seas',248),(305,NULL,NULL,NULL,NULL,NULL,NULL,'Pacific Islands',248),(312,NULL,NULL,NULL,NULL,NULL,NULL,'United States',248),(314,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - US Cities',312),(318,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks - US',312),(319,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - US States',312),(321,NULL,NULL,NULL,NULL,NULL,NULL,'History',111),(322,NULL,NULL,NULL,NULL,NULL,NULL,'Africa',321),(323,NULL,NULL,NULL,NULL,NULL,NULL,'Africa - Central',322),(324,NULL,NULL,NULL,NULL,NULL,NULL,'History - Cultural - Africa',322),(325,NULL,NULL,NULL,NULL,NULL,NULL,'History - Economic',322),(326,NULL,NULL,NULL,NULL,NULL,NULL,'African Resources',322),(327,NULL,NULL,NULL,NULL,NULL,NULL,'History - African Military & Political',322),(520,NULL,NULL,NULL,NULL,NULL,NULL,'Classic',201657),(528,NULL,NULL,NULL,NULL,NULL,NULL,'Clothes',201657),(537,NULL,NULL,NULL,NULL,NULL,NULL,'Cosmetics & Body Art',201657),(547,NULL,NULL,NULL,NULL,NULL,NULL,'Designers & Coutures',201657),(554,NULL,NULL,NULL,NULL,NULL,NULL,'Events & Resources',201657),(328,NULL,NULL,NULL,NULL,NULL,NULL,'Africa - Northern',322),(329,NULL,NULL,NULL,NULL,NULL,NULL,'Africa - Southern',322),(330,NULL,NULL,NULL,NULL,NULL,NULL,'Asia',321),(331,NULL,NULL,NULL,NULL,NULL,NULL,'History - China',330),(332,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Asia',330),(336,NULL,NULL,NULL,NULL,NULL,NULL,'History - India',330),(340,NULL,NULL,NULL,NULL,NULL,NULL,'Australia',321),(346,NULL,NULL,NULL,NULL,NULL,NULL,'Canada',321),(347,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Canada',346),(348,NULL,NULL,NULL,NULL,NULL,NULL,'Economic History - Canada',346),(349,NULL,NULL,NULL,NULL,NULL,NULL,'History - Canada (1)',346),(351,NULL,NULL,NULL,NULL,NULL,NULL,'History - Ontario Canada',346),(352,NULL,NULL,NULL,NULL,NULL,NULL,'History - Other Provinces',346),(353,NULL,NULL,NULL,NULL,NULL,NULL,'History - Quebec',346),(354,NULL,NULL,NULL,NULL,NULL,NULL,'Central America',321),(357,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Central America',354),(358,NULL,NULL,NULL,NULL,NULL,NULL,'Economic History - Central America',354),(361,NULL,NULL,NULL,NULL,NULL,NULL,'History - Central American Military & Political',354),(363,NULL,NULL,NULL,NULL,NULL,NULL,'Europe',321),(201604,NULL,NULL,NULL,NULL,NULL,NULL,'Famous People - Europe General',10248),(366,NULL,NULL,NULL,NULL,NULL,NULL,'Economic History - Europe',363),(369,NULL,NULL,NULL,NULL,NULL,NULL,'History - European (2)',363),(371,NULL,NULL,NULL,NULL,NULL,NULL,'History - European Military & Political',363),(373,NULL,NULL,NULL,NULL,NULL,NULL,'Middle East',321),(374,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Middle East',373),(376,NULL,NULL,NULL,NULL,NULL,NULL,'History - Egypt',373),(377,NULL,NULL,NULL,NULL,NULL,NULL,'History - Middle East (2)',373),(378,NULL,NULL,NULL,NULL,NULL,NULL,'History - Israel',373),(379,NULL,NULL,NULL,NULL,NULL,NULL,'History - Middle East Military & Political',373),(380,NULL,NULL,NULL,NULL,NULL,NULL,'History - Other Middle East Countries',373),(381,NULL,NULL,NULL,NULL,NULL,NULL,'Religion - Middle East History',373),(382,NULL,NULL,NULL,NULL,NULL,NULL,'Pacific Islands',321),(385,NULL,NULL,NULL,NULL,NULL,NULL,'History - Hawaii General',382),(388,NULL,NULL,NULL,NULL,NULL,NULL,'World History',321),(389,NULL,NULL,NULL,NULL,NULL,NULL,'Paleontology & Archaeology',388),(390,NULL,NULL,NULL,NULL,NULL,NULL,'Civilization',388),(201631,NULL,NULL,NULL,NULL,NULL,NULL,'Prehistory - Paleolithic',277),(393,NULL,NULL,NULL,NULL,NULL,NULL,'Evolution - Human',388),(397,NULL,NULL,NULL,NULL,NULL,NULL,'Travel',111),(398,NULL,NULL,NULL,NULL,NULL,NULL,'Accommodations',397),(399,NULL,NULL,NULL,NULL,NULL,NULL,'Bed & Breakfast',398),(400,NULL,NULL,NULL,NULL,NULL,NULL,'Home Exchanges',398),(401,NULL,NULL,NULL,NULL,NULL,NULL,'Hotel Discounters',398),(402,NULL,NULL,NULL,NULL,NULL,NULL,'Hotels/Resorts - Luxury',398),(403,NULL,NULL,NULL,NULL,NULL,NULL,'Apartments, Villas, Cottages',398),(404,NULL,NULL,NULL,NULL,NULL,NULL,'Agents, Reservations & Tours',397),(405,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Business (2)',404),(406,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Culture & Science',404),(408,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Family Vacations',404),(409,NULL,NULL,NULL,NULL,NULL,NULL,'US Government Travel Alerts',404),(410,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Nature',404),(411,NULL,NULL,NULL,NULL,NULL,NULL,'Auctions - Travel',404),(412,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Specialized Packages',404),(414,NULL,NULL,NULL,NULL,NULL,NULL,'Alerts - Automatic (Air Travel)',413),(415,NULL,NULL,NULL,NULL,NULL,NULL,'Airline Rules',413),(419,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Last Minute',413),(421,NULL,NULL,NULL,NULL,NULL,NULL,'Travelers\' Assistance, Insurance, & Complaints',397),(423,NULL,NULL,NULL,NULL,NULL,NULL,'Complaints - General',421),(426,NULL,NULL,NULL,NULL,NULL,NULL,'Insurance - Travel (1)',421),(427,NULL,NULL,NULL,NULL,NULL,NULL,'Complaints - Travel & Vacations',421),(428,NULL,NULL,NULL,NULL,NULL,NULL,'City Transit',397),(429,NULL,NULL,NULL,NULL,NULL,NULL,'Auto Rentals',428),(430,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Bus & Subway Guides',428),(431,NULL,NULL,NULL,NULL,NULL,NULL,'City Guides - General',428),(435,NULL,NULL,NULL,NULL,NULL,NULL,'Embassies & Tourism Offices',397),(436,NULL,NULL,NULL,NULL,NULL,NULL,'Foreign Consulates (1)',435),(437,NULL,NULL,NULL,NULL,NULL,NULL,'Customs Requirements',435),(438,NULL,NULL,NULL,NULL,NULL,NULL,'US Embassies - Foreign',435),(439,NULL,NULL,NULL,NULL,NULL,NULL,'Foreign Consulates (2)',435),(440,NULL,NULL,NULL,NULL,NULL,NULL,'Passports & Visas (2)',435),(442,NULL,NULL,NULL,NULL,NULL,NULL,'US Tourism Office',435),(443,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks & Travelogues',397),(444,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Asia',443),(445,NULL,NULL,NULL,NULL,NULL,NULL,'Customized Trips',443),(446,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Europe (1)',443),(447,NULL,NULL,NULL,NULL,NULL,NULL,'Events & Festivals',443),(448,NULL,NULL,NULL,NULL,NULL,NULL,'Museums - General (2)',443),(450,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Travel Tips (1)',443),(451,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Tropical Islands',443),(452,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - United States',443),(453,NULL,NULL,NULL,NULL,NULL,NULL,'Health & Safety',397),(454,NULL,NULL,NULL,NULL,NULL,NULL,'Airline Safety',453),(456,NULL,NULL,NULL,NULL,NULL,NULL,'Health Advice - Foreign',453);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories_id_seq`
--

DROP TABLE IF EXISTS `categories_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_id_seq`
--

LOCK TABLES `categories_id_seq` WRITE;
/*!40000 ALTER TABLE `categories_id_seq` DISABLE KEYS */;
INSERT INTO `categories_id_seq` VALUES (3);
/*!40000 ALTER TABLE `categories_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratingcriteria`
--

DROP TABLE IF EXISTS `ratingcriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratingcriteria` (
  `id` int(11) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `bookmark` varchar(50) DEFAULT NULL,
  `criteria_type` smallint(6) DEFAULT NULL,
  `description` text,
  `name` varchar(50) DEFAULT NULL,
  `priority` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo2lt6r0xunccncu4vv19606a2` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratingcriteria`
--

LOCK TABLES `ratingcriteria` WRITE;
/*!40000 ALTER TABLE `ratingcriteria` DISABLE KEYS */;
INSERT INTO `ratingcriteria` VALUES (1,1,'2019-07-26 15:14:15',NULL,1,'789','',1,'<ul>\\r                                                                                                        \n         <li>Are all the required information elements in the record complete and accessible?</li>\\r           \n         <li>Are all the required information elements needed for a complete search capability?</li>\\r         \n         <li>Does the site advise users about missing information elements and the consequences?</li>\\r        \n         <li>Does the site advise users in advance about information that is only accessible for a fee?</li>\\r \n         <li>Is the target user audience well served by this site?</li>\\r                                      \n </ul>\\r','Completeness',1,1),(2,1,'2019-07-26 18:37:41',NULL,1,'4536',NULL,1,' <ul>\\r                                                                                                        +\n         <li>Are the registration and log-in procedures easy?</li>\\r                                           +\n         <li>Are the site&#39;s navigational directions and indicators clear and intuitive?</li>\\r             +\n         <li>Is the overall design functional and intuitive?</li>\\r                                            +\n         <li>Are the help and FAQ services sufficient, clear, and responsive?</li>\\r                           +\n         <li>Can the site be personalized, customized, or preferences stored?</li>\\r                           +\n         <li>Is contact and feedback information adequate and responsive?</li>\\r                               +\n         <li>Is there an excessive amount of ads and/or pop-ups?</li>\\r                                        +\n         <li>Can a user&#39;s browser be seized or hijacked to another site?</li>\\r                            +\n         <li>Is the target user audience well served by this site?</li>\\r                                      +\n </ul>\\r ','Navigation',1,1),(3,1,NULL,NULL,1,'865',NULL,1,NULL,'Content',1,NULL),(4,1,NULL,NULL,NULL,'658',NULL,1,NULL,'Privacy',1,NULL);
/*!40000 ALTER TABLE `ratingcriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratingcriteria_id_seq`
--

DROP TABLE IF EXISTS `ratingcriteria_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratingcriteria_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratingcriteria_id_seq`
--

LOCK TABLES `ratingcriteria_id_seq` WRITE;
/*!40000 ALTER TABLE `ratingcriteria_id_seq` DISABLE KEYS */;
INSERT INTO `ratingcriteria_id_seq` VALUES (1);
/*!40000 ALTER TABLE `ratingcriteria_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `related_topics`
--

DROP TABLE IF EXISTS `related_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `related_topics` (
  `id` varchar(255) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `relatedtopic_id` int(11) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdro07uteaelr0h6wk32bjtft3` (`relatedtopic_id`),
  KEY `FK6wxnncc8l4bbsgcb02yyah3u7` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `related_topics`
--

LOCK TABLES `related_topics` WRITE;
/*!40000 ALTER TABLE `related_topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `related_topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relatedtopics_id_seq`
--

DROP TABLE IF EXISTS `relatedtopics_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relatedtopics_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relatedtopics_id_seq`
--

LOCK TABLES `relatedtopics_id_seq` WRITE;
/*!40000 ALTER TABLE `relatedtopics_id_seq` DISABLE KEYS */;
INSERT INTO `relatedtopics_id_seq` VALUES (1);
/*!40000 ALTER TABLE `relatedtopics_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` smallint(6) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_id_seq`
--

DROP TABLE IF EXISTS `roles_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_id_seq`
--

LOCK TABLES `roles_id_seq` WRITE;
/*!40000 ALTER TABLE `roles_id_seq` DISABLE KEYS */;
INSERT INTO `roles_id_seq` VALUES (1);
/*!40000 ALTER TABLE `roles_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereview_comments`
--

DROP TABLE IF EXISTS `sitereview_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereview_comments` (
  `id` int(11) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `comment` text NOT NULL,
  `read_status` int(11) NOT NULL,
  `commented_by` bigint(20) NOT NULL,
  `sitereview_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl84pyctxshytvtapgmfrd1gvl` (`commented_by`),
  KEY `FK6qcckpxuxg6n18lykmuo9f5v9` (`sitereview_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereview_comments`
--

LOCK TABLES `sitereview_comments` WRITE;
/*!40000 ALTER TABLE `sitereview_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `sitereview_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereviewratingcriteria_id_seq`
--

DROP TABLE IF EXISTS `sitereviewratingcriteria_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereviewratingcriteria_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereviewratingcriteria_id_seq`
--

LOCK TABLES `sitereviewratingcriteria_id_seq` WRITE;
/*!40000 ALTER TABLE `sitereviewratingcriteria_id_seq` DISABLE KEYS */;
INSERT INTO `sitereviewratingcriteria_id_seq` VALUES (1);
/*!40000 ALTER TABLE `sitereviewratingcriteria_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereviewratingcriteriavoting_id_seq`
--

DROP TABLE IF EXISTS `sitereviewratingcriteriavoting_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereviewratingcriteriavoting_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereviewratingcriteriavoting_id_seq`
--

LOCK TABLES `sitereviewratingcriteriavoting_id_seq` WRITE;
/*!40000 ALTER TABLE `sitereviewratingcriteriavoting_id_seq` DISABLE KEYS */;
INSERT INTO `sitereviewratingcriteriavoting_id_seq` VALUES (1);
/*!40000 ALTER TABLE `sitereviewratingcriteriavoting_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereviews`
--

DROP TABLE IF EXISTS `sitereviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereviews` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `description` text,
  `evaluation` text,
  `read_status` int(11) DEFAULT NULL,
  `review_status` smallint(6) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `searchvector` varchar(255) DEFAULT NULL,
  `status_code` int(11) DEFAULT NULL,
  `strength` text,
  `weakness` text,
  `site_id` bigint(20) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKby0vo1kxg3je8xjidab4v0fs2` (`site_id`),
  KEY `FKneu0tvqjkrqsd4ekd2huthpxv` (`topic_id`),
  KEY `FKqun7qpqpi0lgl9etg5bh4w8pa` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereviews`
--

LOCK TABLES `sitereviews` WRITE;
/*!40000 ALTER TABLE `sitereviews` DISABLE KEYS */;
INSERT INTO `sitereviews` VALUES (1,1,'2019-07-29 15:14:22',NULL,1,'123','basic site','these are details about basic site',1,1,11,NULL,1,NULL,NULL,1,3,1),(2,1,'2019-07-31 16:22:47',NULL,1,'5632','advanced site','details about java advanced site',0,1,50,NULL,1,NULL,NULL,1,3,1);
/*!40000 ALTER TABLE `sitereviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereviews_comments_id_seq`
--

DROP TABLE IF EXISTS `sitereviews_comments_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereviews_comments_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereviews_comments_id_seq`
--

LOCK TABLES `sitereviews_comments_id_seq` WRITE;
/*!40000 ALTER TABLE `sitereviews_comments_id_seq` DISABLE KEYS */;
INSERT INTO `sitereviews_comments_id_seq` VALUES (1);
/*!40000 ALTER TABLE `sitereviews_comments_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereviews_id_seq`
--

DROP TABLE IF EXISTS `sitereviews_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereviews_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereviews_id_seq`
--

LOCK TABLES `sitereviews_id_seq` WRITE;
/*!40000 ALTER TABLE `sitereviews_id_seq` DISABLE KEYS */;
INSERT INTO `sitereviews_id_seq` VALUES (1);
/*!40000 ALTER TABLE `sitereviews_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereviews_ratingcriteria`
--

DROP TABLE IF EXISTS `sitereviews_ratingcriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereviews_ratingcriteria` (
  `id` bigint(20) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  `ratingcriteria_id` int(11) DEFAULT NULL,
  `sitereview_id` bigint(20) DEFAULT NULL,
  `site_id` bigint(20) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpg0dwboa4t13gjuciyklq9lm0` (`ratingcriteria_id`),
  KEY `FK67qd80l4ixd9a0k0sw7dqlsvu` (`sitereview_id`),
  KEY `FKa5wpq540t4i5m0jw3he8hi063` (`site_id`),
  KEY `FKhuomrirmcgcyw8pc7nxardsct` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereviews_ratingcriteria`
--

LOCK TABLES `sitereviews_ratingcriteria` WRITE;
/*!40000 ALTER TABLE `sitereviews_ratingcriteria` DISABLE KEYS */;
INSERT INTO `sitereviews_ratingcriteria` VALUES (1,10,1,1,1,3);
/*!40000 ALTER TABLE `sitereviews_ratingcriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sitereviews_ratingcriteria_voting`
--

DROP TABLE IF EXISTS `sitereviews_ratingcriteria_voting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sitereviews_ratingcriteria_voting` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `criteria_voting_decision` smallint(6) NOT NULL,
  `sitereviewrating_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb685iinx0os7oarwrmki29eu1` (`sitereviewrating_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sitereviews_ratingcriteria_voting`
--

LOCK TABLES `sitereviews_ratingcriteria_voting` WRITE;
/*!40000 ALTER TABLE `sitereviews_ratingcriteria_voting` DISABLE KEYS */;
/*!40000 ALTER TABLE `sitereviews_ratingcriteria_voting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sites`
--

DROP TABLE IF EXISTS `sites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sites` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `backup_rootsite_id` bigint(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `rootsite_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs7i8unjoco05k4a9j6ev94f43` (`backup_rootsite_id`),
  KEY `FKbg8dqb3iy3y0fs72hr3xlu8m2` (`category_id`),
  KEY `FKm7jtou775sv064trqhbm5fg2d` (`rootsite_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sites`
--

LOCK TABLES `sites` WRITE;
/*!40000 ALTER TABLE `sites` DISABLE KEYS */;
INSERT INTO `sites` VALUES (1,1,'2019-07-29 16:07:25',NULL,1,'456','sample image','No path','Kaldin sites','www.kaldin.com',NULL,1,NULL);
/*!40000 ALTER TABLE `sites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sites_id_seq`
--

DROP TABLE IF EXISTS `sites_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sites_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sites_id_seq`
--

LOCK TABLES `sites_id_seq` WRITE;
/*!40000 ALTER TABLE `sites_id_seq` DISABLE KEYS */;
INSERT INTO `sites_id_seq` VALUES (1);
/*!40000 ALTER TABLE `sites_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_expert_assignment`
--

DROP TABLE IF EXISTS `topic_expert_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_expert_assignment` (
  `id` int(11) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `assigned_by_userid` bigint(20) NOT NULL,
  `expert_id` bigint(20) NOT NULL,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe9ua2x1l26eakim80vcbyn8ub` (`assigned_by_userid`),
  KEY `FK875rdhs5u9w2elrvxgtwndgw` (`expert_id`),
  KEY `FKdoi0rp37htfheejn6hdkcxurj` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_expert_assignment`
--

LOCK TABLES `topic_expert_assignment` WRITE;
/*!40000 ALTER TABLE `topic_expert_assignment` DISABLE KEYS */;
INSERT INTO `topic_expert_assignment` VALUES (1,1,'2019-07-25 18:30:39',NULL,1,'123',1,1,1);
/*!40000 ALTER TABLE `topic_expert_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_expert_assignment_id_seq`
--

DROP TABLE IF EXISTS `topic_expert_assignment_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_expert_assignment_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_expert_assignment_id_seq`
--

LOCK TABLES `topic_expert_assignment_id_seq` WRITE;
/*!40000 ALTER TABLE `topic_expert_assignment_id_seq` DISABLE KEYS */;
INSERT INTO `topic_expert_assignment_id_seq` VALUES (1);
/*!40000 ALTER TABLE `topic_expert_assignment_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics` (
  `id` int(11) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `leaf_node` bit(1) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `topic_type` smallint(6) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `parent_topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3mind1ka66asw07vi2t2gxalm` (`category_id`),
  KEY `FKpd5mo30erv4abuyhk94iatmcx` (`parent_topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (1,1,'2019-07-25 18:23:19',NULL,1,'456',NULL,'sampletopic',4,4,NULL),(2,1,'2019-07-26 13:56:26',NULL,1,'482',NULL,'SubTopic',5,4,1),(3,1,'2019-07-26 18:27:52',NULL,1,'9856',_binary '\0','SubTopic2',5,4,NULL);
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics_id_seq`
--

DROP TABLE IF EXISTS `topics_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics_id_seq`
--

LOCK TABLES `topics_id_seq` WRITE;
/*!40000 ALTER TABLE `topics_id_seq` DISABLE KEYS */;
INSERT INTO `topics_id_seq` VALUES (1);
/*!40000 ALTER TABLE `topics_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics_ratingcriteria`
--

DROP TABLE IF EXISTS `topics_ratingcriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics_ratingcriteria` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `ratingcriteria_id` int(11) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1kpt5yitpwan2ar2le8txb2yd` (`ratingcriteria_id`),
  KEY `FKcjcke05o5ht0i9mggn7q6gg07` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics_ratingcriteria`
--

LOCK TABLES `topics_ratingcriteria` WRITE;
/*!40000 ALTER TABLE `topics_ratingcriteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `topics_ratingcriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics_sites`
--

DROP TABLE IF EXISTS `topics_sites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics_sites` (
  `site_id` bigint(20) NOT NULL,
  `topic_id` int(11) NOT NULL,
  KEY `FK8idg2stl1xo6ywpfcih99w6r4` (`topic_id`),
  KEY `FKt09s74g0vvx95c4v6bmi78hf3` (`site_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics_sites`
--

LOCK TABLES `topics_sites` WRITE;
/*!40000 ALTER TABLE `topics_sites` DISABLE KEYS */;
/*!40000 ALTER TABLE `topics_sites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topicsratingcriteria_id_seq`
--

DROP TABLE IF EXISTS `topicsratingcriteria_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topicsratingcriteria_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topicsratingcriteria_id_seq`
--

LOCK TABLES `topicsratingcriteria_id_seq` WRITE;
/*!40000 ALTER TABLE `topicsratingcriteria_id_seq` DISABLE KEYS */;
INSERT INTO `topicsratingcriteria_id_seq` VALUES (1);
/*!40000 ALTER TABLE `topicsratingcriteria_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'2019-07-25 15:28:02','2019-07-25 15:28:02',1,'123','admin','admin'),(2,NULL,'2019-07-25 17:38:06','2019-07-25 17:38:06',1,'456','dev','dev');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_id_seq`
--

DROP TABLE IF EXISTS `user_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_id_seq`
--

LOCK TABLES `user_id_seq` WRITE;
/*!40000 ALTER TABLE `user_id_seq` DISABLE KEYS */;
INSERT INTO `user_id_seq` VALUES (1);
/*!40000 ALTER TABLE `user_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` smallint(6) NOT NULL,
  PRIMARY KEY (`users_id`,`roles_id`),
  KEY `FKdbv8tdyltxa1qjmfnj9oboxse` (`roles_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `bio` text,
  `email` varchar(100) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mr4rtvun5wakeubj0tvjr98l8` (`email`),
  KEY `FKjd1oomh3nusy0svs6x6u3hfmf` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,1,'2019-07-25 15:29:21',NULL,1,'123',NULL,'kaldin@gmail.com','kaldin',NULL,NULL,'solution',1),(2,1,'2019-07-25 17:37:21',NULL,1,'456',NULL,'test@gmail.com','Dev','',NULL,'Dev Test',2);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo_id_seq`
--

DROP TABLE IF EXISTS `userinfo_id_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo_id_seq`
--

LOCK TABLES `userinfo_id_seq` WRITE;
/*!40000 ALTER TABLE `userinfo_id_seq` DISABLE KEYS */;
INSERT INTO `userinfo_id_seq` VALUES (1);
/*!40000 ALTER TABLE `userinfo_id_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` smallint(6) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'qrata'
--

--
-- Dumping routines for database 'qrata'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-11 17:52:01
