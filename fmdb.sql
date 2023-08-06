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
-- Table structure for table `account_root`
--

DROP TABLE IF EXISTS `account_root`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_root` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `image` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
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
-- Table structure for table `account_type`
--

DROP TABLE IF EXISTS `account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `account_root` int NOT NULL,
  `image` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  `description` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`),
  KEY `fk_account_account_type_idx` (`account_root`),
  CONSTRAINT `fk_account_account_type` FOREIGN KEY (`account_root`) REFERENCES `account_root` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_type`
--

LOCK TABLES `account_type` WRITE;
/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;
INSERT INTO `account_type` VALUES (1,'Tiền mặt',1,NULL,NULL),(2,'Agribank',2,NULL,'Ngân hàng Nông nghiệp và Phát triển nông thôn Việt Nam'),(3,'Vietcombank',2,NULL,'Ngân hàng TMCP Ngoại thương Việt Nam'),(4,'Nam A Bank',2,NULL,'Ngân hàng TMCP Nam Á'),(5,'Momo',3,NULL,NULL),(6,'Moca',3,NULL,NULL),(7,'ShoppePay',3,NULL,NULL),(8,'ViettelPay',3,NULL,NULL),(9,'VnPay',3,NULL,NULL),(10,'ZaloPay',3,NULL,NULL),(11,'Khác',1,NULL,NULL),(12,'Mặc định',1,NULL,NULL),(20,'ACB',2,NULL,'Ngân hàng TMCP Á Châu'),(21,'BIDV',2,NULL,'Ngân hàng TMCP Đầu tư và Phát triển Việt Nam'),(22,'MB',2,NULL,'Ngân hàng TMCP Quân đội'),(23,'OCB',2,NULL,'Ngân hàng TMCP Phương Đông'),(24,'Vietinbank',2,NULL,'Ngân hàng TMCP Công Thương Việt Nam'),(25,'TPBank',2,NULL,'Ngân hàng TMCP Tiên Phong'),(26,'Techcombank',2,NULL,'Ngân hàng TMCP Kỹ Thương Việt Nam'),(27,'VIB',2,NULL,'Ngân hàng TMCP Quốc tế Việt Nam'),(28,'VPBank',2,NULL,'Ngân hàng TMCP Việt Nam Thịnh Vượng');
/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_root`
--

DROP TABLE IF EXISTS `category_root`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_root` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `image` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
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
-- Table structure for table `category_type`
--

DROP TABLE IF EXISTS `category_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `category_root` int NOT NULL,
  `image` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`),
  KEY `fk_category_category_type_idx` (`category_root`),
  CONSTRAINT `fk_category_type_category_root` FOREIGN KEY (`category_root`) REFERENCES `category_root` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_type`
--

LOCK TABLES `category_type` WRITE;
/*!40000 ALTER TABLE `category_type` DISABLE KEYS */;
INSERT INTO `category_type` VALUES (1,'Ăn sáng',1,NULL),(2,'Ăn trưa',1,NULL),(3,'Ăn tối',1,NULL),(4,'Ăn tiệm',1,NULL),(5,'Cafe',1,NULL),(6,'Đồ chơi',2,NULL),(7,'Học phí',2,NULL),(8,'Sách vở',2,NULL),(9,'Tiền tiêu vặt',2,NULL),(10,'Điện',3,NULL),(11,'Phí điện thoại cố định',3,NULL),(12,'Phí điện thoại di động',3,NULL),(13,'Gas',3,NULL),(14,'Internet',3,NULL),(15,'Tiền nước',3,NULL),(16,'Thuê người giúp việc',3,NULL),(17,'Truyền hình',3,NULL),(18,'Bảo hiểm xe',4,NULL),(19,'Gửi xe',4,NULL),(20,'Rửa xe',4,NULL),(21,'Sửa chữa, bảo dưỡng xe',4,NULL),(22,'Taxi/thuê xe',4,NULL),(23,'Xăng xe',4,''),(24,'Biếu tặng',5,NULL),(25,'Cưới xin',5,NULL),(26,'Ma chay',5,NULL),(27,'Thăm hỏi',5,NULL),(28,'Du lịch',6,NULL),(29,'Làm đẹp',6,NULL),(30,'Mỹ phẩm',6,NULL),(31,'Phim ảnh ca nhạc',6,NULL),(32,'Vui chơi giải trí',6,NULL),(33,'Phí chuyển khoản',7,NULL),(34,'Mua sắm đồ đạc',8,NULL),(35,'Sữa chữa nhà cửa',8,NULL),(36,'Thuê nhà',8,NULL),(37,'Học hành',9,NULL),(38,'Giao lưu, quan hệ',9,NULL),(39,'Khám chữa bệnh',10,NULL),(40,'Thể thao',10,NULL),(41,'Thuốc men',10,NULL),(42,'Giày dép',11,NULL),(43,'Phụ kiện khác',11,NULL),(44,'Quần áo',11,NULL),(45,'Trả nợ',23,NULL),(46,'Thu nợ',22,NULL),(47,'Được cho, tặng',22,NULL),(48,'Lãi ngân hàng',13,NULL),(49,'Lãi đầu tư',13,NULL),(50,'Lương',12,NULL),(51,'Thưởng',12,NULL),(52,'Đi vay',22,NULL),(53,'Cho vay',23,NULL),(54,'Chỉnh sửa ví',22,NULL),(55,'Chỉnh sửa ví',23,NULL);
/*!40000 ALTER TABLE `category_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  `category_type` int DEFAULT NULL,
  `created_date` mediumtext COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `wallet` int DEFAULT NULL,
  `total` bigint DEFAULT NULL,
  `image` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`),
  KEY `fk_category_id_idx` (`category_type`),
  KEY `fk_transaction_wallet_idx` (`wallet`),
  CONSTRAINT `fk_transaction_category_type` FOREIGN KEY (`category_type`) REFERENCES `category_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_transaction_wallet` FOREIGN KEY (`wallet`) REFERENCES `wallet` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (4,'Ăn bún bò',3,'1688124480',3,30000,NULL),(5,'Mua nước suối Circle K',5,'1688178660',4,6000,NULL),(6,'Đi xem phim',31,'1684505040',4,139000,NULL),(7,'Ăn trưa',2,'1687924980',6,25000,NULL),(8,'Đổ xăng',23,'1688266980',5,50000,NULL),(9,'Đi grab',22,'1687165200',5,39000,NULL),(10,'Mua mỹ phẩm',29,'1685880502',7,230000,NULL),(11,'Đóng trọ',36,'1688178630',7,1000000,NULL),(12,'Nhận học bổng',51,'1687399320',8,5000000,NULL),(63,'Hihi',2,'1690708140',7,20000,''),(74,'Cơm trưa',2,'1683705120',2,20000,''),(90,'Ăn trưa',2,'1691058300',1,20000,'');
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
  `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `lastname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `gender` tinyint DEFAULT '1',
  `career` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `image_link` longtext COLLATE utf8mb4_vietnamese_ci,
  `role` enum('ROLE_ADMIN','ROLE_USER') COLLATE utf8mb4_vietnamese_ci DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nguyễn','Anh','anhnguyen','1234','anh@gmail.com',1,'Sinh viên','https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/338018331_254856326897520_3856794107188959630_n.jpg?_nc_cat=101&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=polDCS2paJIAX-YHF2O&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfBCQKct3Ww1dHczh1Kd_GfEaX_IeQHTQlGZDByMX2EdhQ&oe=64C280E6','ROLE_USER'),(2,'Đạt','Lương','luongdat','1234','dat@gmail.com',1,'Sinh viên','https://scontent.fsgn2-3.fna.fbcdn.net/v/t39.30808-6/341033378_927338095248252_3614182632098763100_n.jpg?stp=cp6_dst-jpg&_nc_cat=107&cb=99be929b-59f725be&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=XmCsXQ0m5CAAX_GGXZK&_nc_ht=scontent.fsgn2-3.fna&oh=00_AfDZeMDi5voLjp0VVnocfW3S9dvqxn8SuA7osUzJCHuIPw&oe=64C1C686','ROLE_USER'),(3,'Mãi','Đặng','maidang','1234','mai@gmail.com',1,'Sinh viên','https://scontent.fsgn2-5.fna.fbcdn.net/v/t39.30808-6/280334526_126618866654533_5990798067395328668_n.jpg?_nc_cat=104&cb=99be929b-59f725be&ccb=1-7&_nc_sid=174925&_nc_ohc=Fj-XSnen9HcAX_FAirq&_nc_ht=scontent.fsgn2-5.fna&oh=00_AfAOw1xW4QJZQ9Aw6p2b1yOFExjQaoK7_yJMU_g-Ri77Kg&oe=64C23B2F','ROLE_USER'),(4,'Quỳnh','Đào','quynhdao','1234','quynh@gmail.com',0,'Sinh viên','https://scontent.fsgn2-6.fna.fbcdn.net/v/t39.30808-6/325171580_507602668030371_976961888220416543_n.jpg?stp=cp6_dst-jpg&_nc_cat=111&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=U4-_UUoVMfUAX-YQWS4&_nc_ht=scontent.fsgn2-6.fna&oh=00_AfCG4-EjM3ZpGMqDepOBDmrWML1hlVKa1kuy2VS_qCEX1w&oe=64C15278','ROLE_USER'),(5,'Manager','Admin','admin','admin','admin@gmail.com',1,NULL,NULL,'ROLE_ADMIN'),(19,'Phan Bảo Toàn','Trần','toantran','12345','toan@gmail.com',1,'','https://res.cloudinary.com/dcheizves/image/upload/v1691289436/mobile-assets/blank_avatar_gvpfut.jpg','ROLE_USER');
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
  `user` int NOT NULL,
  `account_type` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `balance` bigint NOT NULL,
  `description` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  PRIMARY KEY (`id`),
  KEY `fk_wallet_account_idx` (`account_type`),
  KEY `fk_wallet_user_idx` (`user`),
  CONSTRAINT `fk_wallet_account_type` FOREIGN KEY (`account_type`) REFERENCES `account_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_wallet_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES (1,1,1,'Tiền ăn uống',2258000,'Chi phí ăn uống hàng tháng'),(2,1,5,'Ví Momo',2201071,'Gửi túi thần tài'),(3,2,1,'Tiền mặt',4240000,'Tiền hàng tháng'),(4,2,4,'Thẻ Nam Á',1506000,'Tiền trong thẻ'),(5,3,1,'Tiền đi lại',140000,'Xăng xe, di chuyển'),(6,3,2,'Tiền chi tiêu',5600000,'Ăn uống, sinh hoạt'),(7,4,1,'Tiền tiêu mẹ cho',12100000,'Phí sinh hoạt'),(8,4,4,'Học bổng',12000000,'Học bổng DHM');
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

-- Dump completed on 2023-08-06 15:10:49
