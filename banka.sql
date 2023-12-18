CREATE DATABASE  IF NOT EXISTS `banka` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `banka`;

-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: banka
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `kartica`
--

DROP TABLE IF EXISTS `kartica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kartica` (
  `id_kartica` bigint NOT NULL AUTO_INCREMENT,
  `broj_kartice` int NOT NULL,
  `vrsta_kartice` tinyint NOT NULL,
  `datum_kreiranja` datetime NOT NULL,
  `datum_isteka` datetime NOT NULL,
  `CVV` int NOT NULL,
  `limit` bigint DEFAULT NULL,
  `status` tinyint DEFAULT '0',
  `id_racun` bigint NOT NULL,
  PRIMARY KEY (`id_kartica`),
  UNIQUE KEY `brojKartice_UNIQUE` (`broj_kartice`),
  KEY `fk_kartica_racun_idx` (`id_racun`),
  CONSTRAINT `fk_kartica_racun` FOREIGN KEY (`id_racun`) REFERENCES `racun` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kartica`
--

LOCK TABLES `kartica` WRITE;
/*!40000 ALTER TABLE `kartica` DISABLE KEYS */;
/*!40000 ALTER TABLE `kartica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klijent`
--

DROP TABLE IF EXISTS `klijent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `klijent` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rejting` bigint DEFAULT NULL,
  `id_osoba` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_klijent_osoba_idx` (`id_osoba`),
  CONSTRAINT `fk_klijent_osoba` FOREIGN KEY (`id_osoba`) REFERENCES `osoba` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klijent`
--

LOCK TABLES `klijent` WRITE;
/*!40000 ALTER TABLE `klijent` DISABLE KEYS */;
/*!40000 ALTER TABLE `klijent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kredit`
--

DROP TABLE IF EXISTS `kredit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kredit` (
  `id_kredit` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `iznos_kredita` bigint NOT NULL,
  `period_otplate` int NOT NULL,
  `nks` bigint NOT NULL,
  `eks` bigint NOT NULL,
  `datum_ugovaranja` datetime NOT NULL,
  `datum_dospeca` datetime DEFAULT NULL,
  `iznos_rate` bigint NOT NULL,
  `datum_sl_rate` datetime DEFAULT NULL,
  `dug` bigint DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `id_racun` bigint NOT NULL,
  `id_zaposleni` bigint DEFAULT NULL,
  PRIMARY KEY (`id_kredit`),
  KEY `fk_kredit_racun_idx` (`id_racun`),
  KEY `fk_kredit_zaposleni_idx` (`id_zaposleni`),
  CONSTRAINT `fk_kredit_racun` FOREIGN KEY (`id_racun`) REFERENCES `racun` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_kredit_zaposleni` FOREIGN KEY (`id_zaposleni`) REFERENCES `zaposleni` (`id_zaposleni`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kredit`
--

LOCK TABLES `kredit` WRITE;
/*!40000 ALTER TABLE `kredit` DISABLE KEYS */;
/*!40000 ALTER TABLE `kredit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osoba`
--

DROP TABLE IF EXISTS `osoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `osoba` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `datumRodjenja` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefon` varchar(20) NOT NULL,
  `adresa` varchar(100) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telefon_UNIQUE` (`telefon`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osoba`
--

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;
/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racun`
--

DROP TABLE IF EXISTS `racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `racun` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brojRacuna` varchar(18) NOT NULL,
  `stanje` bigint DEFAULT NULL,
  `raspolozivoStanje` bigint DEFAULT NULL,
  `datumKreiranja` datetime NOT NULL,
  `datumIsteka` datetime NOT NULL,
  `tip` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `vrstaPaketa` tinyint NOT NULL,
  `kamatnaStopa` bigint DEFAULT NULL,
  `odrzavanje` bigint NOT NULL,
  `id_vlasnik` bigint NOT NULL,
  `id_bankar` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `brojRacuna_UNIQUE` (`brojRacuna`),
  KEY `fk_racun_vlasnik_idx` (`id_vlasnik`),
  KEY `fk_racun_bankar_idx` (`id_bankar`),
  CONSTRAINT `fk_racun_bankar` FOREIGN KEY (`id_bankar`) REFERENCES `zaposleni` (`id_zaposleni`) ON UPDATE CASCADE,
  CONSTRAINT `fk_racun_vlasnik` FOREIGN KEY (`id_vlasnik`) REFERENCES `klijent` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racun`
--

LOCK TABLES `racun` WRITE;
/*!40000 ALTER TABLE `racun` DISABLE KEYS */;
/*!40000 ALTER TABLE `racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transakcija`
--

DROP TABLE IF EXISTS `transakcija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transakcija` (
  `id_transakcija` bigint NOT NULL AUTO_INCREMENT,
  `naziv_primaoca` varchar(50) NOT NULL,
  `racun_primaoca` varchar(18) NOT NULL,
  `iznos` bigint NOT NULL,
  `poziv_na_broj` int DEFAULT NULL,
  `sifra_placanja` int NOT NULL,
  `svrha` varchar(100) NOT NULL,
  `model` tinyint DEFAULT NULL,
  `id_kartica` bigint DEFAULT NULL,
  PRIMARY KEY (`id_transakcija`),
  KEY `fk_transakcija_kartica_idx` (`id_kartica`),
  CONSTRAINT `fk_transakcija_kartica` FOREIGN KEY (`id_kartica`) REFERENCES `kartica` (`id_kartica`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transakcija`
--

LOCK TABLES `transakcija` WRITE;
/*!40000 ALTER TABLE `transakcija` DISABLE KEYS */;
/*!40000 ALTER TABLE `transakcija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transakcija_racun`
--

DROP TABLE IF EXISTS `transakcija_racun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transakcija_racun` (
  `idtransakcija_racun` bigint NOT NULL AUTO_INCREMENT,
  `datum_transakcije` datetime NOT NULL,
  `id_racun` bigint NOT NULL,
  `id_transakcije` bigint NOT NULL,
  PRIMARY KEY (`idtransakcija_racun`),
  KEY `fk_tr_racun_idx` (`id_racun`),
  KEY `fk_tr_transakcija_idx` (`id_transakcije`),
  CONSTRAINT `fk_tr_racun` FOREIGN KEY (`id_racun`) REFERENCES `racun` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tr_transakcija` FOREIGN KEY (`id_transakcije`) REFERENCES `transakcija` (`id_transakcija`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transakcija_racun`
--

LOCK TABLES `transakcija_racun` WRITE;
/*!40000 ALTER TABLE `transakcija_racun` DISABLE KEYS */;
/*!40000 ALTER TABLE `transakcija_racun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zaposleni`
--

DROP TABLE IF EXISTS `zaposleni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaposleni` (
  `id_zaposleni` bigint NOT NULL AUTO_INCREMENT,
  `pozicija` varchar(50) NOT NULL,
  `departman` varchar(50) NOT NULL,
  `id_osoba` bigint NOT NULL,
  PRIMARY KEY (`id_zaposleni`),
  KEY `fk_zaposleni_osoba_idx` (`id_osoba`),
  CONSTRAINT `fk_zaposleni_osoba` FOREIGN KEY (`id_osoba`) REFERENCES `osoba` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposleni`
--

LOCK TABLES `zaposleni` WRITE;
/*!40000 ALTER TABLE `zaposleni` DISABLE KEYS */;
/*!40000 ALTER TABLE `zaposleni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-12 16:13:33
