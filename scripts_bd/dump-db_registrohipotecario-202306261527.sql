-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 150.240.68.245    Database: db_registrohipotecario
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
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cliente` (
  `idCli` varchar(11) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidoPat` varchar(100) NOT NULL,
  `apellidoMat` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `genero` varchar(15) NOT NULL,
  PRIMARY KEY (`idCli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES ('41600156','FELIPE','SEBASTIANI','SOBENES','gold','felipesebastiani@gmail.com','+51997356386','MASCULINO'),('42572727','JAMES','AIBO','PEREZ','gold','sdstiani@gmail.com','+51997356386','MASCULINO'),('44818542','JUANA','LOPEZ','ORTIZ','gold','juana@gmail.com','+51997356311','MASCULINO'),('44841525','JAMES','BOND','PEREZ','gold','dsfsdani@gmail.com','+51997356386','MASCULINO');
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_creditohipotecario`
--

DROP TABLE IF EXISTS `tb_creditohipotecario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_creditohipotecario` (
  `idCre` varchar(45) NOT NULL,
  `idCli` varchar(11) NOT NULL,
  `tipoCre` varchar(100) NOT NULL,
  `montoInicial` float NOT NULL,
  `montoFinanciar` float NOT NULL,
  `plazoMeses` int NOT NULL,
  `sueldoBruto` float NOT NULL,
  `costoInmueble` float NOT NULL,
  `tasa` float NOT NULL,
  PRIMARY KEY (`idCre`),
  KEY `idCli` (`idCli`),
  CONSTRAINT `tb_creditohipotecario_ibfk_1` FOREIGN KEY (`idCli`) REFERENCES `tb_cliente` (`idCli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_creditohipotecario`
--

LOCK TABLES `tb_creditohipotecario` WRITE;
/*!40000 ALTER TABLE `tb_creditohipotecario` DISABLE KEYS */;
INSERT INTO `tb_creditohipotecario` VALUES ('null','44818542','DEPARTAMENTO',15,22500,12,6000,150000,10.5),('S2045023','42572727','DEPARTAMENTO',15,68107.8,12,7680,454052,10.5),('S2303382','44841525','DEPARTAMENTO',30,75000,111,15000,250000,10.5),('S4321717','41600156','CASA',15,22500,28,100000,150000,10.5);
/*!40000 ALTER TABLE `tb_creditohipotecario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_solicitud`
--

DROP TABLE IF EXISTS `tb_solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_solicitud` (
  `idSol` varchar(20) NOT NULL,
  `idCre` varchar(45) NOT NULL,
  `resultado` varchar(100) NOT NULL,
  PRIMARY KEY (`idSol`),
  KEY `idCre` (`idCre`),
  CONSTRAINT `tb_solicitud_ibfk_1` FOREIGN KEY (`idCre`) REFERENCES `tb_creditohipotecario` (`idCre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_solicitud`
--

LOCK TABLES `tb_solicitud` WRITE;
/*!40000 ALTER TABLE `tb_solicitud` DISABLE KEYS */;
INSERT INTO `tb_solicitud` VALUES ('null','S4321717','null'),('S2045023','S2045023','null');
/*!40000 ALTER TABLE `tb_solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_registrohipotecario'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-26 15:27:40
