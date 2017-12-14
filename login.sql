-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: login
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `u_permission`
--

DROP TABLE IF EXISTS `u_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `u_permission`
--

LOCK TABLES `u_permission` WRITE;
/*!40000 ALTER TABLE `u_permission` DISABLE KEYS */;
INSERT INTO `u_permission` VALUES (1,'查看'),(2,'创建'),(3,'删除');
/*!40000 ALTER TABLE `u_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_role`
--

DROP TABLE IF EXISTS `u_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `u_role`
--

LOCK TABLES `u_role` WRITE;
/*!40000 ALTER TABLE `u_role` DISABLE KEYS */;
INSERT INTO `u_role` VALUES (1,'开发','user'),(2,'测试','user'),(3,'产品','user');
/*!40000 ALTER TABLE `u_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_role_permission`
--

DROP TABLE IF EXISTS `u_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_role_permission` (
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `pid` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`rid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `u_role_permission`
--

LOCK TABLES `u_role_permission` WRITE;
/*!40000 ALTER TABLE `u_role_permission` DISABLE KEYS */;
INSERT INTO `u_role_permission` VALUES (1,1),(1,2),(1,3),(2,1),(3,1),(3,2);
/*!40000 ALTER TABLE `u_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_user`
--

DROP TABLE IF EXISTS `u_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `u_user`
--

LOCK TABLES `u_user` WRITE;
/*!40000 ALTER TABLE `u_user` DISABLE KEYS */;
INSERT INTO `u_user` VALUES (1,'xiao','389855286@qq.com','123456','2017-12-13 08:27:46',NULL,0),(2,'fan','xiao@163.com','123456','2017-12-13 08:27:46',NULL,1),(3,'qqq','lll@163.com','123456','2017-12-13 08:27:46',NULL,0),(4,'admin','admin@163.com','123456','2017-12-13 08:27:46',NULL,0);
/*!40000 ALTER TABLE `u_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_user_role`
--

DROP TABLE IF EXISTS `u_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_role` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `u_user_role`
--

LOCK TABLES `u_user_role` WRITE;
/*!40000 ALTER TABLE `u_user_role` DISABLE KEYS */;
INSERT INTO `u_user_role` VALUES (1,1),(2,2),(3,3),(4,1),(4,2),(4,3);
/*!40000 ALTER TABLE `u_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-14 17:57:20
