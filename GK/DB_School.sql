-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               9.4.0 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for SchoolDB
CREATE DATABASE IF NOT EXISTS `SchoolDB` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `SchoolDB`;

-- Dumping structure for table SchoolDB.SCORES
CREATE TABLE IF NOT EXISTS `SCORES` (
  `score_id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(8) NOT NULL,
  `subject_name` varchar(50) NOT NULL,
  `score` decimal(3,1) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `exam_date` date NOT NULL,
  `score_type` varchar(20) DEFAULT 'FINAL',
  `notes` text,
  PRIMARY KEY (`score_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `SCORES_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `STUDENTS` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table SchoolDB.SCORES: ~10 rows (approximately)
INSERT INTO `SCORES` (`score_id`, `student_id`, `subject_name`, `score`, `semester`, `exam_date`, `score_type`, `notes`) VALUES
	(1, '20220001', 'Database Systems', 8.5, '2023A', '2023-06-15', 'FINAL', 'Thi cuối kỳ'),
	(2, '20220001', 'Algorithms', 7.8, '2023A', '2023-06-20', 'FINAL', 'Thi cuối kỳ'),
	(3, '20220002', 'Database Systems', 9.0, '2023A', '2023-06-15', 'FINAL', 'Rất tốt'),
	(4, '20220002', 'Operating Systems', 8.2, '2023A', '2023-06-18', 'FINAL', 'Thi giữa kỳ'),
	(5, '20230001', 'Computer Networks', 7.5, '2023B', '2023-12-10', 'FINAL', 'Thi cuối kỳ'),
	(6, '20230001', 'Artificial Intelligence', 8.9, '2023B', '2023-12-12', 'FINAL', 'Dự án nhóm'),
	(7, '20210001', 'Software Engineering', 9.2, '2022B', '2022-12-05', 'FINAL', 'Bảo vệ đồ án'),
	(8, '20210001', 'Database Systems', 8.0, '2022B', '2022-12-07', 'FINAL', 'Thi cuối kỳ'),
	(9, '20230002', 'Data Mining', 8.7, '2023A', '2023-06-25', 'FINAL', 'Bài tập lớn'),
	(10, '20230002', 'Machine Learning', 9.1, '2023A', '2023-06-28', 'FINAL', 'Thi cuối kỳ');

-- Dumping structure for table SchoolDB.STUDENTS
CREATE TABLE IF NOT EXISTS `STUDENTS` (
  `student_id` varchar(8) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `class_name` varchar(20) NOT NULL,
  `major` varchar(50) NOT NULL,
  `enrollment_year` int NOT NULL,
  `status` varchar(20) DEFAULT 'ACTIVE',
  `created_date` date DEFAULT (curdate()),
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table SchoolDB.STUDENTS: ~5 rows (approximately)
INSERT INTO `STUDENTS` (`student_id`, `full_name`, `email`, `phone`, `class_name`, `major`, `enrollment_year`, `status`, `created_date`) VALUES
	('20210001', 'Pham Thi Dung', 'dung.pham@example.com', '0934567890', 'IT303', 'Information Technology', 2021, 'ACTIVE', '2025-09-24'),
	('20220001', 'Nguyen Van An', 'an.nguyen@example.com', '0901234567', 'SE101', 'Software Engineering', 2022, 'ACTIVE', '2025-09-24'),
	('20220002', 'Tran Thi Binh', 'binh.tran@example.com', '0912345678', 'SE101', 'Software Engineering', 2022, 'ACTIVE', '2025-09-24'),
	('20230001', 'Le Van Cuong', 'cuong.le@example.com', '0923456789', 'CS202', 'Computer Science', 2023, 'ACTIVE', '2025-09-24'),
	('20230002', 'Hoang Van Em', 'em.hoang@example.com', '0945678901', 'DS404', 'Data Science', 2023, 'ACTIVE', '2025-09-24');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
