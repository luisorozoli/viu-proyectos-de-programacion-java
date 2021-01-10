-- MariaDB dump 10.17  Distrib 10.4.8-MariaDB, for Win64 (AMD64)
--
-- Host: proyprog20-21giin.cydvwi2rvkii.us-east-1.rds.amazonaws.com    Database: supercomputacion
-- ------------------------------------------------------
-- Server version	10.4.8-MariaDB-log

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
-- Table structure for table `asignartrabajos`
--

DROP TABLE IF EXISTS `asignartrabajos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignartrabajos` (
  `trabajoid` int(11) NOT NULL AUTO_INCREMENT,
  `centroid` int(11) NOT NULL,
  `estadotrabajo` varchar(45) NOT NULL,
  PRIMARY KEY (`trabajoid`,`centroid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignartrabajos`
--

LOCK TABLES `asignartrabajos` WRITE;
/*!40000 ALTER TABLE `asignartrabajos` DISABLE KEYS */;
/*!40000 ALTER TABLE `asignartrabajos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centros`
--

DROP TABLE IF EXISTS `centros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centros` (
  `idcentro` int(11) NOT NULL AUTO_INCREMENT,
  `identificador` varchar(30) NOT NULL,
  `capacidadprocesamiento` int(11) NOT NULL,
  `tamanomaxcola` int(11) NOT NULL,
  `administrador` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`idcentro`),
  UNIQUE KEY `identificador` (`identificador`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centros`
--

LOCK TABLES `centros` WRITE;
/*!40000 ALTER TABLE `centros` DISABLE KEYS */;
INSERT INTO `centros` VALUES (3,'centro2',500,20,'usuario5'),(4,'centro3',100,101,'usuario5'),(11,'centro4',400,20,'usuario2'),(14,'centro5',999,215,'usuario6');
/*!40000 ALTER TABLE `centros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procesamiento`
--

DROP TABLE IF EXISTS `procesamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procesamiento` (
  `idprocesamiento` int(11) NOT NULL AUTO_INCREMENT,
  `idtrabajo` int(11) NOT NULL,
  `idcentro` int(11) NOT NULL,
  `estadotrabajo` varchar(45) NOT NULL,
  PRIMARY KEY (`idprocesamiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procesamiento`
--

LOCK TABLES `procesamiento` WRITE;
/*!40000 ALTER TABLE `procesamiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `procesamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajos`
--

DROP TABLE IF EXISTS `trabajos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajos` (
  `idtrabajos` int(11) NOT NULL AUTO_INCREMENT,
  `identificador` varchar(30) NOT NULL,
  `cantidadoperaciones` double NOT NULL,
  `propietario` varchar(10) NOT NULL,
  `centroTrabajo` varchar(30) NOT NULL,
  PRIMARY KEY (`idtrabajos`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajos`
--

LOCK TABLES `trabajos` WRITE;
/*!40000 ALTER TABLE `trabajos` DISABLE KEYS */;
INSERT INTO `trabajos` VALUES (2,'trabajo1',20000,'usuario4','11'),(4,'trabajoUsuario3',3000000,'usuario3','centro3'),(8,'trabajo para usuario3',21010100,'usuario3','centro2'),(9,'prueba',2132340,'usuario3','centro4');
/*!40000 ALTER TABLE `trabajos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `identificador` varchar(10) NOT NULL DEFAULT '',
  `clave` varchar(8) NOT NULL DEFAULT '',
  `tipousuario` varchar(25) NOT NULL DEFAULT '',
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `identificador` (`identificador`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'usuario1','clave1','Administrador'),(2,'usuario2','clave2','AdministradorCentro'),(21,'usuario3','clave3','Usuario'),(24,'usuario4','clave4','Usuario'),(28,'usuario5','clave5','AdministradorCentro'),(30,'usuario6','clave6','AdministradorCentro');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-10 19:46:56
