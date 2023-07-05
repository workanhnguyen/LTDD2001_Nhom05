-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: fmdb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `account_children`
--

DROP TABLE IF EXISTS `account_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_children` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `account_type_id` int NOT NULL,
  `image` tinytext COLLATE utf8mb4_vietnamese_ci,
  `description` tinytext COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`),
  KEY `fk_account_account_type_idx` (`account_type_id`),
  CONSTRAINT `fk_account_account_type` FOREIGN KEY (`account_type_id`) REFERENCES `account_root` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_children`
--

LOCK TABLES `account_children` WRITE;
/*!40000 ALTER TABLE `account_children` DISABLE KEYS */;
INSERT INTO `account_children` VALUES (1,'Tiền mặt',1,NULL,NULL),(2,'Agribank',2,NULL,'Ngân hàng Nông nghiệp và Phát triển nông thôn Việt Nam'),(3,'Vietcombank',2,NULL,'Ngân hàng TMCP Ngoại thương Việt Nam'),(4,'Nam A Bank',2,NULL,'Ngân hàng TMCP Nam Á'),(5,'Momo',3,NULL,NULL),(6,'Moca',3,NULL,NULL),(7,'ShoppePay',3,NULL,NULL),(8,'ViettelPay',3,NULL,NULL),(9,'VnPay',3,NULL,NULL),(10,'ZaloPay',3,NULL,NULL),(11,'Khác',1,NULL,NULL);
/*!40000 ALTER TABLE `account_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_root`
--

DROP TABLE IF EXISTS `account_root`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_root` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `image` tinytext COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_root`
--

LOCK TABLES `account_root` WRITE;
/*!40000 ALTER TABLE `account_root` DISABLE KEYS */;
INSERT INTO `account_root` VALUES (1,'Phổ biến',''),(2,'Tài khoản ngân hàng',''),(3,'Ví điện tử','');
/*!40000 ALTER TABLE `account_root` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_children`
--

DROP TABLE IF EXISTS `category_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_children` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `category_root_id` int NOT NULL,
  `image` tinytext COLLATE utf8mb4_vietnamese_ci,
  `description` tinytext COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`),
  KEY `fk_category_category_type_idx` (`category_root_id`),
  CONSTRAINT `fk_category_children_category_root` FOREIGN KEY (`category_root_id`) REFERENCES `category_root` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_children`
--

LOCK TABLES `category_children` WRITE;
/*!40000 ALTER TABLE `category_children` DISABLE KEYS */;
INSERT INTO `category_children` VALUES (1,'Ăn sáng',1,NULL,NULL),(2,'Ăn trưa',1,NULL,NULL),(3,'Ăn tối',1,NULL,NULL),(4,'Ăn tiệm',1,NULL,NULL),(5,'Cafe',1,NULL,NULL),(6,'Đồ chơi',2,NULL,NULL),(7,'Học phí',2,NULL,NULL),(8,'Sách vở',2,NULL,NULL),(9,'Tiền tiêu vặt',2,NULL,NULL),(10,'Điện',3,NULL,NULL),(11,'Phí điện thoại cố định',3,NULL,NULL),(12,'Phí điện thoại di động',3,NULL,NULL),(13,'Gas',3,NULL,NULL),(14,'Internet',3,NULL,NULL),(15,'Tiền nước',3,NULL,NULL),(16,'Thuê người giúp việc',3,NULL,NULL),(17,'Truyền hình',3,NULL,NULL),(18,'Bảo hiểm xe',4,NULL,NULL),(19,'Gửi xe',4,NULL,NULL),(20,'Rửa xe',4,NULL,NULL),(21,'Sửa chữa, bảo dưỡng xe',4,NULL,NULL),(22,'Taxi/thuê xe',4,NULL,NULL),(23,'Xăng xe',4,'',NULL),(24,'Biếu tặng',5,NULL,NULL),(25,'Cưới xin',5,NULL,NULL),(26,'Ma chay',5,NULL,NULL),(27,'Thăm hỏi',5,NULL,NULL),(28,'Du lịch',6,NULL,NULL),(29,'Làm đẹp',6,NULL,NULL),(30,'Mỹ phẩm',6,NULL,NULL),(31,'Phim ảnh ca nhạc',6,NULL,NULL),(32,'Vui chơi giải trí',6,NULL,NULL),(33,'Phí chuyển khoản',7,NULL,NULL),(34,'Mua sắm đồ đạc',8,NULL,NULL),(35,'Sữa chữa nhà cửa',8,NULL,NULL),(36,'Thuê nhà',8,NULL,NULL),(37,'Học hành',9,NULL,NULL),(38,'Giao lưu, quan hệ',9,NULL,NULL),(39,'Khám chữa bệnh',10,NULL,NULL),(40,'Thể thao',10,NULL,NULL),(41,'Thuốc men',10,NULL,NULL),(42,'Giày dép',11,NULL,NULL),(43,'Phụ kiện khác',11,NULL,NULL),(44,'Quần áo',11,NULL,NULL),(45,'Trả nợ',23,NULL,NULL),(46,'Thu nợ',22,NULL,NULL),(47,'Được cho, tặng',22,NULL,NULL),(48,'Lãi ngân hàng',13,NULL,NULL),(49,'Lãi đầu tư',13,NULL,NULL),(50,'Lương',12,NULL,NULL),(51,'Thưởng',12,NULL,NULL),(52,'Đi vay',22,NULL,NULL),(53,'Cho vay',23,NULL,NULL);
/*!40000 ALTER TABLE `category_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_root`
--

DROP TABLE IF EXISTS `category_root`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_root` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `image` tinytext COLLATE utf8mb4_vietnamese_ci,
  `type` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_root`
--

LOCK TABLES `category_root` WRITE;
/*!40000 ALTER TABLE `category_root` DISABLE KEYS */;
INSERT INTO `category_root` VALUES (1,'Ăn uống',NULL,'EXPENSE'),(2,'Con cái',NULL,'EXPENSE'),(3,'Dịch vụ sinh hoạt',NULL,'EXPENSE'),(4,'Đi lại',NULL,'EXPENSE'),(5,'Hiếu hỉ',NULL,'EXPENSE'),(6,'Hưởng thụ',NULL,'EXPENSE'),(7,'Ngân hàng',NULL,'EXPENSE'),(8,'Nhà cửa',NULL,'EXPENSE'),(9,'Phát triển bản thân',NULL,'EXPENSE'),(10,'Sức khỏe',NULL,'EXPENSE'),(11,'Trang phục',NULL,'EXPENSE'),(12,'Thu nhập',NULL,'INCOME'),(13,'Tiền lãi',NULL,'INCOME'),(22,'Khác',NULL,'INCOME'),(23,'Khác',NULL,'EXPENSE');
/*!40000 ALTER TABLE `category_root` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `description` tinytext COLLATE utf8mb4_vietnamese_ci,
  `category_children_id` int DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `wallet_id` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_user_idx` (`user_id`),
  KEY `fk_category_id_idx` (`category_children_id`),
  KEY `fk_transaction_wallet_idx` (`wallet_id`),
  CONSTRAINT `fk_transaction_category_children` FOREIGN KEY (`category_children_id`) REFERENCES `category_children` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_transaction_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_transaction_wallet` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,'Ăn sáng bánh mì',1,'2023-07-05 07:31:00.000000',1,15000),(2,1,'Đổ xăng đầy bình',23,'2023-06-15 20:19:00.000000',1,80000),(3,1,'Mua cà phê',5,'2023-07-04 07:00:04.000000',2,15000),(4,2,'Ăn bún bò',3,'2023-06-30 18:28:00.000000',3,30000),(5,2,'Mua nước suối Circle K',5,'2023-07-01 09:31:00.000000',4,6000),(6,2,'Đi xem phim',31,'2023-05-19 21:04:00.000000',4,139000),(7,3,'Ăn trưa',2,'2023-06-28 11:03:00.000000',6,25000),(8,3,'Đổ xăng',23,'2023-07-02 10:03:00.000000',5,50000),(9,3,'Đi grab',22,'2023-06-19 16:00:00.000000',5,39000),(10,4,'Mua mỹ phẩm',29,'2023-06-04 19:08:22.000000',7,230000),(11,4,'Đóng trọ',36,'2023-07-01 09:30:30.000000',7,1000000),(12,4,'Nhận học bổng',51,'2023-06-22 09:02:00.000000',8,5000000);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Anh','Nguyễn','anhnguyen','1234','anh@gmail.com'),(2,'Đạt','Lương','luongdat','1234','dat@gmail.com'),(3,'Mãi','Đặng','maidang','1234','mai@gmail.com'),(4,'Quỳnh','Đào','quynhdao','1234','quynh@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `account_children_id` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `balance` bigint NOT NULL,
  `description` tinytext COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`),
  KEY `fk_wallet_account_idx` (`account_children_id`),
  KEY `fk_wallet_user_idx` (`user_id`),
  CONSTRAINT `fk_wallet_account_children` FOREIGN KEY (`account_children_id`) REFERENCES `account_children` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_wallet_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES (1,1,1,'Tiền ăn uống',2378000,'Chi phí ăn uống hàng tháng'),(2,1,5,'Ví Momo',3297000,'Gửi túi thần tài'),(3,2,1,'Tiền mặt',4240000,'Tiền hàng tháng'),(4,2,4,'Thẻ Nam Á',1506000,'Tiền trong thẻ'),(5,3,1,'Tiền đi lại',140000,'Xăng xe, di chuyển'),(6,3,2,'Tiền chi tiêu',5600000,'Ăn uống, sinh hoạt'),(7,4,1,'Tiền tiêu mẹ cho',12100000,'Phí sinh hoạt'),(8,4,4,'Học bổng',12000000,'Học bổng DHM');
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-05 19:55:52
