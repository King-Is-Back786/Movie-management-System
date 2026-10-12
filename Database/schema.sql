-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: movie_ticket_system
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `booking_seats`
--

DROP TABLE IF EXISTS `booking_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_seats` (
  `booking_seat_id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `seat_id` int NOT NULL,
  PRIMARY KEY (`booking_seat_id`),
  UNIQUE KEY `unique_booking_seat` (`booking_id`,`seat_id`),
  KEY `seat_id` (`seat_id`),
  CONSTRAINT `booking_seats_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`) ON DELETE CASCADE,
  CONSTRAINT `booking_seats_ibfk_2` FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_seats`
--

LOCK TABLES `booking_seats` WRITE;
/*!40000 ALTER TABLE `booking_seats` DISABLE KEYS */;
INSERT INTO `booking_seats` VALUES (9,6,1),(10,6,2),(11,7,3);
/*!40000 ALTER TABLE `booking_seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `show_id` int NOT NULL,
  `total_amount` decimal(8,2) NOT NULL,
  `booking_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`booking_id`),
  KEY `user_id` (`user_id`),
  KEY `show_id` (`show_id`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`show_id`) REFERENCES `shows` (`show_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (6,1,1,700.00,'2026-10-12 05:21:12'),(7,2,1,350.00,'2026-10-12 06:12:16');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers_address`
--

DROP TABLE IF EXISTS `customers_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers_address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `state` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(200) NOT NULL,
  `house_no` varchar(10) NOT NULL,
  `landmark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `customers_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers_address`
--

LOCK TABLES `customers_address` WRITE;
/*!40000 ALTER TABLE `customers_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_cast`
--

DROP TABLE IF EXISTS `movie_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_cast` (
  `cast_id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `role` varchar(100) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cast_id`),
  KEY `movie_id` (`movie_id`),
  CONSTRAINT `movie_cast_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_cast`
--

LOCK TABLES `movie_cast` WRITE;
/*!40000 ALTER TABLE `movie_cast` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `language` varchar(50) DEFAULT 'Hindi',
  `duration_mins` int DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'3 Idiots','Comedy','Hindi',170),(2,'Lagaan','Drama','Hindi',224),(3,'Sholay','Action','Hindi',204),(4,'Gangs of Wasseypur','Crime','Hindi',321),(5,'Taare Zameen Par','Drama','Hindi',165),(6,'Andhadhun','Thriller','Hindi',139),(7,'Swades','Drama','Hindi',210),(8,'Tumbbad','Horror','Hindi',104),(9,'Zindagi Na Milegi Dobara','Drama','Hindi',155),(10,'Drishyam','Thriller','Hindi',163),(11,'Pulp Fiction','Crime','English',154),(12,'The Matrix','Sci-Fi','English',136),(13,'Fight Club','Drama','English',139),(14,'Gladiator','Action','English',155),(15,'The Shawshank Redemption','Drama','English',142),(16,'Baahubali 2: The Conclusion','Action','Telugu',167),(17,'Pushpa: The Rise','Action','Telugu',179),(18,'K.G.F: Chapter 1','Action','Kannada',156),(19,'K.G.F: Chapter 2','Action','Kannada',168),(20,'777 Charlie','Adventure','Kannada',164),(21,'The Lion King','Animation','English',88),(22,'Spider-Man: Into the Spider-Verse','Animation','English',117),(23,'Your Name','Animation','Japanese',107),(24,'WALL-E','Animation','English',98),(25,'Toy Story','Animation','English',81);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `screens`
--

DROP TABLE IF EXISTS `screens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `screens` (
  `screen_id` int NOT NULL AUTO_INCREMENT,
  `theatre_id` int NOT NULL,
  `screen_name` varchar(50) NOT NULL,
  PRIMARY KEY (`screen_id`),
  KEY `theatre_id` (`theatre_id`),
  CONSTRAINT `screens_ibfk_1` FOREIGN KEY (`theatre_id`) REFERENCES `theatres` (`theatre_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `screens`
--

LOCK TABLES `screens` WRITE;
/*!40000 ALTER TABLE `screens` DISABLE KEYS */;
INSERT INTO `screens` VALUES (1,1,'Audi 1'),(2,1,'Audi 2'),(3,2,'Audi 1 - IMAX'),(4,2,'Audi 2 - Insignia'),(5,3,'Screen 1 - 4DX'),(6,3,'Screen 2 - VIP'),(7,4,'Audi 1 - Laser IMAX'),(8,4,'Audi 2 - Atmos'),(9,5,'Screen 1 - Atmos'),(10,5,'Screen 2'),(11,6,'Audi 1 - IMAX with Laser'),(12,6,'Audi 2 - Director\'s Cut'),(13,6,'Audi 3 - Dolby Atmos'),(14,7,'Screen 1 - 4DX'),(15,7,'Screen 2 - IMAX'),(16,8,'Audi 1 - Insignia'),(17,8,'Audi 2 - Dolby'),(18,9,'Audi 1 - Platinum Suite'),(19,9,'Audi 2 - Luxury Atmos'),(20,10,'Screen 1 - Heritage'),(21,11,'Audi 1 - 4DX'),(22,12,'Audi 1 - IMAX'),(23,12,'Audi 2 - PXL'),(24,12,'Audi 3 - Gold Class'),(25,13,'Audi 1 - Atmos'),(26,14,'Screen 1 - Giant Screen (PCX)'),(27,14,'Screen 2 - Dolby 7.1'),(28,15,'Audi 1 - 4DX'),(29,15,'Audi 2 - IMAX'),(30,16,'Audi 1 - RDDO (Main Screen)'),(31,16,'Audi 2 - Seasons');
/*!40000 ALTER TABLE `screens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `screen_id` int NOT NULL,
  `seat_number` varchar(10) NOT NULL,
  `seat_type` varchar(20) DEFAULT 'Regular',
  PRIMARY KEY (`seat_id`),
  KEY `screen_id` (`screen_id`),
  CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`screen_id`) REFERENCES `screens` (`screen_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,1,'A1','Platinum'),(2,1,'A2','Platinum'),(3,1,'A3','Platinum'),(4,1,'A4','Platinum'),(5,1,'A5','Platinum'),(6,1,'A6','Platinum'),(7,1,'A7','Platinum'),(8,1,'A8','Platinum'),(9,1,'A9','Platinum'),(10,1,'A10','Platinum'),(11,1,'B1','Gold'),(12,1,'B2','Gold'),(13,1,'B3','Gold'),(14,1,'B4','Gold'),(15,1,'B5','Gold'),(16,1,'B6','Gold'),(17,1,'B7','Gold'),(18,1,'B8','Gold'),(19,1,'B9','Gold'),(20,1,'B10','Gold'),(21,1,'C1','Silver'),(22,1,'C2','Silver'),(23,1,'C3','Silver'),(24,1,'C4','Silver'),(25,1,'C5','Silver'),(26,1,'C6','Silver'),(27,1,'C7','Silver'),(28,1,'C8','Silver'),(29,1,'C9','Silver'),(30,1,'C10','Silver');
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shows` (
  `show_id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `screen_id` int NOT NULL,
  `show_time` datetime NOT NULL,
  PRIMARY KEY (`show_id`),
  KEY `movie_id` (`movie_id`),
  KEY `screen_id` (`screen_id`),
  CONSTRAINT `shows_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE CASCADE,
  CONSTRAINT `shows_ibfk_2` FOREIGN KEY (`screen_id`) REFERENCES `screens` (`screen_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shows`
--

LOCK TABLES `shows` WRITE;
/*!40000 ALTER TABLE `shows` DISABLE KEYS */;
INSERT INTO `shows` VALUES (1,1,1,'2026-10-12 18:30:00'),(2,1,2,'2026-10-12 21:45:00'),(3,2,3,'2026-10-12 19:00:00'),(4,4,4,'2026-10-12 22:15:00'),(5,4,1,'2026-10-13 14:00:00'),(6,1,5,'2026-10-12 15:30:00'),(7,2,7,'2026-10-12 18:00:00'),(8,4,7,'2026-10-12 21:30:00'),(9,1,8,'2026-10-13 11:00:00'),(10,4,9,'2026-10-13 19:30:00'),(11,2,10,'2026-10-13 22:45:00'),(12,2,11,'2026-10-12 16:00:00'),(13,2,11,'2026-10-12 20:30:00'),(14,1,12,'2026-10-12 19:00:00'),(15,4,13,'2026-10-13 13:30:00'),(16,1,14,'2026-10-13 17:00:00'),(17,2,15,'2026-10-13 21:00:00'),(18,4,16,'2026-10-13 18:45:00'),(19,1,18,'2026-10-12 17:15:00'),(20,2,18,'2026-10-12 21:00:00'),(21,4,19,'2026-10-13 14:30:00'),(22,1,20,'2026-10-13 18:00:00'),(23,2,21,'2026-10-13 20:30:00'),(24,2,22,'2026-10-12 15:00:00'),(25,2,22,'2026-10-12 19:15:00'),(26,1,23,'2026-10-12 21:30:00'),(27,4,24,'2026-10-13 16:00:00'),(28,1,25,'2026-10-13 19:45:00'),(29,4,26,'2026-10-12 14:00:00'),(30,2,26,'2026-10-12 18:30:00'),(31,1,27,'2026-10-12 21:45:00'),(32,4,29,'2026-10-13 15:30:00'),(33,4,30,'2026-10-13 19:00:00'),(34,1,31,'2026-10-13 22:00:00');
/*!40000 ALTER TABLE `shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatres`
--

DROP TABLE IF EXISTS `theatres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatres` (
  `theatre_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  PRIMARY KEY (`theatre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatres`
--

LOCK TABLES `theatres` WRITE;
/*!40000 ALTER TABLE `theatres` DISABLE KEYS */;
INSERT INTO `theatres` VALUES (1,'PVR Inorbit Mall','Vadodara'),(2,'INOX Race Course','Vadodara'),(3,'Cinepolis Nexus Mall','Ahmedabad'),(4,'PVR Acropolis Mall','Ahmedabad'),(5,'Rajhans Prime Cinemas','Surat'),(6,'PVR ICON Phoenix Palladium','Mumbai'),(7,'Cinepolis Viviana Mall','Thane'),(8,'INOX Bund Garden','Pune'),(9,'PVR Director\'s Cut Ambience Mall','Gurugram'),(10,'PVR Plaza Connaught Place','New Delhi'),(11,'Wave Cinemas Sector 18','Noida'),(12,'PVR Forum Mall Koramangala','Bengaluru'),(13,'INOX Garuda Mall','Bengaluru'),(14,'Prasad Multiplex (Prasads IMAX)','Hyderabad'),(15,'PVR Nexus Hyderabad','Hyderabad'),(16,'Sathyam Cinemas (SPI)','Chennai');
/*!40000 ALTER TABLE `theatres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_no` (`phone_no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Rahul Sharma','rahul@test.com','mypassword123','9876543210'),(2,'Virat Kohli','virat.kohli@cricket.in','vk18king','9811000001'),(3,'Rohit Sharma','rohit.sharma@cricket.in','hitman45','9811000002'),(4,'MS Dhoni','ms.dhoni@cricket.in','mahi007thala','9811000003'),(5,'Sachin Tendulkar','sachin.t@cricket.in','godofcricket10','9811000004'),(6,'Hardik Pandya','hardik.pandya@cricket.in','kungfupandya','9811000005'),(7,'Jasprit Bumrah','jasprit.bumrah@cricket.in','yorkerking93','9811000006'),(8,'Ravindra Jadeja','ravindra.jadeja@cricket.in','sirjadeja08','9811000007'),(9,'KL Rahul','kl.rahul@cricket.in','klclassy01','9811000008'),(10,'Shubman Gill','shubman.gill@cricket.in','princegill77','9811000009'),(11,'Rishabh Pant','rishabh.pant@cricket.in','spidermanpant','9811000010'),(12,'Lionel Messi','lionel.messi@football.com','goat10inter','9822000001'),(13,'Cristiano Ronaldo','cristiano.ronaldo@football.com','cr7siuuu','9822000002'),(14,'Neymar Jr','neymar.jr@football.com','samba11magic','9822000003'),(15,'Kylian Mbappe','kylian.mbappe@football.com','km7speed','9822000004'),(16,'Erling Haaland','erling.haaland@football.com','terminator09','9822000005'),(17,'Luka Modric','luka.modric@football.com','maestro10lukita','9822000006'),(18,'Kevin De Bruyne','kevin.debruyne@football.com','kdbassist17','9822000007'),(19,'Robert Lewandowski','robert.lewandowski@football.com','lewa9goal','9822000008'),(20,'Zlatan Ibrahimovic','zlatan.ibrahimovic@football.com','iamzlatan11','9822000009'),(21,'Sunil Chhetri','sunil.chhetri@football.com','captainchhetri11','9822000010');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-12 12:31:33
