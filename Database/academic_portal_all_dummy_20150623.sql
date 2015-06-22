-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2015 at 07:06 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `academic_portal`
--
CREATE DATABASE IF NOT EXISTS `academic_portal` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `academic_portal`;

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
`class_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `type` varchar(11) NOT NULL,
  `specialized_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `classes`
--

TRUNCATE TABLE `classes`;
--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `code`, `type`, `specialized_id`, `course_id`, `batch`, `number`) VALUES
(1, 'SE1001', 'Specialized', 1, NULL, 10, 1),
(2, 'SE1002', 'Specialized', 1, NULL, 10, 2),
(3, 'SE1003', 'Specialized', 1, NULL, 10, 3),
(4, 'SE1004', 'Specialized', 1, NULL, 10, 4),
(5, 'SE1005', 'Specialized', 1, NULL, 10, 5),
(6, 'SE1006', 'Specialized', 1, NULL, 10, 6),
(7, 'SE1007', 'Specialized', 1, NULL, 10, 7),
(8, 'SE1008', 'Specialized', 1, NULL, 10, 8),
(9, 'SE1009', 'Specialized', 1, NULL, 10, 9),
(10, 'SE1010', 'Specialized', 1, NULL, 10, 10),
(11, 'SE1011', 'Specialized', 1, NULL, 10, 11),
(12, 'SE1012', 'Specialized', 1, NULL, 10, 12),
(13, 'SE1013', 'Specialized', 1, NULL, 10, 13),
(14, 'EC1001', 'Specialized', 2, NULL, 10, 1),
(15, 'EC1002', 'Specialized', 2, NULL, 10, 2),
(16, 'EC1003', 'Specialized', 2, NULL, 10, 3),
(17, 'IA1001', 'Specialized', 3, NULL, 10, 1),
(18, 'IA1002', 'Specialized', 3, NULL, 10, 2),
(19, 'IA1003', 'Specialized', 3, NULL, 10, 3),
(20, 'GD1001', 'Specialized', 4, NULL, 10, 1),
(21, 'GD1002', 'Specialized', 4, NULL, 10, 2),
(22, 'BA1001', 'Specialized', 5, NULL, 10, 1),
(23, 'BA1002', 'Specialized', 5, NULL, 10, 2),
(24, 'FIN0803', 'Specialized', 5, NULL, 8, 3),
(25, 'IBA0902', 'Specialized', 5, NULL, 9, 2),
(26, 'IBA1001', 'Specialized', 5, NULL, 10, 1),
(27, 'MKT0902', 'Specialized', 5, NULL, 9, 2),
(28, 'COB0803', 'Specialized', 6, NULL, 8, 3),
(29, 'COF0803', 'Specialized', 6, NULL, 8, 3),
(30, 'FB1001', 'Specialized', 6, NULL, 10, 1),
(31, 'FB1002', 'Specialized', 6, NULL, 10, 2);

-- --------------------------------------------------------

--
-- Table structure for table `class_course_semester`
--

DROP TABLE IF EXISTS `class_course_semester`;
CREATE TABLE IF NOT EXISTS `class_course_semester` (
`class_course_semester_id` int(11) NOT NULL,
  `class_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL,
  `block_condition` int(11) DEFAULT NULL,
  `semester_long` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1560 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `class_course_semester`
--

TRUNCATE TABLE `class_course_semester`;
--
-- Dumping data for table `class_course_semester`
--

INSERT INTO `class_course_semester` (`class_course_semester_id`, `class_semester_id`, `course_semester_id`, `block_condition`, `semester_long`) VALUES
(1430, 7, 11, 0, 0),
(1431, 7, 41, 0, 0),
(1432, 7, 32, 0, 0),
(1433, 7, 4, 0, 0),
(1434, 7, 37, 0, 1),
(1435, 8, 11, 0, 0),
(1436, 8, 41, 0, 0),
(1437, 8, 32, 0, 0),
(1438, 8, 4, 0, 0),
(1439, 8, 37, 0, 1),
(1440, 9, 11, 0, 0),
(1441, 9, 41, 0, 0),
(1442, 9, 32, 0, 0),
(1443, 9, 4, 0, 0),
(1444, 9, 37, 0, 1),
(1445, 10, 11, 0, 0),
(1446, 10, 41, 0, 0),
(1447, 10, 32, 0, 0),
(1448, 10, 4, 0, 0),
(1449, 10, 37, 0, 1),
(1450, 11, 11, 0, 0),
(1451, 11, 41, 0, 0),
(1452, 11, 32, 0, 0),
(1453, 11, 4, 0, 0),
(1454, 11, 37, 0, 1),
(1455, 12, 11, 0, 0),
(1456, 12, 41, 0, 0),
(1457, 12, 32, 0, 0),
(1458, 12, 4, 0, 0),
(1459, 12, 37, 0, 1),
(1460, 13, 11, 0, 0),
(1461, 13, 41, 0, 0),
(1462, 13, 32, 0, 0),
(1463, 13, 4, 0, 0),
(1464, 13, 37, 0, 1),
(1465, 16, 32, 0, 0),
(1466, 16, 11, 0, 0),
(1467, 16, 4, 0, 0),
(1468, 16, 37, 0, 0),
(1469, 16, 36, 0, 1),
(1470, 19, 11, 0, 0),
(1471, 19, 41, 0, 0),
(1472, 19, 32, 0, 0),
(1473, 19, 4, 0, 0),
(1474, 19, 37, 0, 1),
(1475, 21, 16, 0, 0),
(1476, 21, 25, 0, 0),
(1477, 21, 15, 0, 0),
(1478, 21, 2, 0, 0),
(1479, 21, 19, 0, 1),
(1480, 4, 40, 0, 0),
(1481, 4, 39, 0, 0),
(1482, 4, 12, 0, 0),
(1483, 4, 32, 0, 0),
(1484, 4, 28, 0, 1),
(1485, 5, 40, 0, 0),
(1486, 5, 39, 0, 0),
(1487, 5, 12, 0, 0),
(1488, 5, 32, 0, 0),
(1489, 5, 28, 0, 1),
(1490, 6, 40, 0, 0),
(1491, 6, 39, 0, 0),
(1492, 6, 12, 0, 0),
(1493, 6, 32, 0, 0),
(1494, 6, 28, 0, 1),
(1495, 15, 40, 0, 0),
(1496, 15, 31, 0, 0),
(1497, 15, 39, 0, 0),
(1498, 15, 5, 0, 0),
(1499, 15, 28, 0, 1),
(1500, 18, 40, 0, 0),
(1501, 18, 39, 0, 0),
(1502, 18, 12, 0, 0),
(1503, 18, 31, 0, 0),
(1504, 18, 28, 0, 1),
(1505, 20, 14, 0, 0),
(1506, 20, 3, 0, 0),
(1507, 20, 24, 0, 0),
(1508, 20, 42, 0, 0),
(1509, 20, 20, 0, 1),
(1510, 23, 1, 0, 0),
(1511, 23, 17, 0, 0),
(1512, 23, 30, 0, 0),
(1513, 23, 20, 0, 0),
(1514, 23, 34, 0, 1),
(1515, 31, 1, 0, 0),
(1516, 31, 17, 0, 0),
(1517, 31, 30, 0, 0),
(1518, 31, 20, 0, 0),
(1519, 31, 34, 0, 1),
(1520, 1, 27, 0, 0),
(1521, 1, 35, 0, 0),
(1522, 1, 9, 0, 0),
(1523, 1, 38, 0, 0),
(1524, 1, 29, 0, 1),
(1525, 2, 27, 0, 0),
(1526, 2, 35, 0, 0),
(1527, 2, 9, 0, 0),
(1528, 2, 38, 0, 0),
(1529, 2, 29, 0, 1),
(1530, 3, 27, 0, 0),
(1531, 3, 35, 0, 0),
(1532, 3, 9, 0, 0),
(1533, 3, 38, 0, 0),
(1534, 3, 29, 0, 1),
(1535, 14, 18, 0, 0),
(1536, 14, 13, 0, 0),
(1537, 14, 9, 0, 0),
(1538, 14, 27, 0, 0),
(1539, 14, 29, 0, 1),
(1540, 17, 26, 0, 0),
(1541, 17, 35, 0, 0),
(1542, 17, 10, 0, 0),
(1543, 17, 38, 0, 0),
(1544, 17, 29, 0, 1),
(1545, 22, 22, 0, 0),
(1546, 22, 23, 0, 0),
(1547, 22, 33, 0, 0),
(1548, 22, 21, 0, 0),
(1549, 22, 6, 0, 1),
(1550, 30, 22, 0, 0),
(1551, 30, 23, 0, 0),
(1552, 30, 33, 0, 0),
(1553, 30, 21, 0, 0),
(1554, 30, 6, 0, 1),
(1555, 26, 22, 0, 0),
(1556, 26, 23, 0, 0),
(1557, 26, 33, 0, 0),
(1558, 26, 21, 0, 0),
(1559, 26, 6, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `class_course_student_semester`
--

DROP TABLE IF EXISTS `class_course_student_semester`;
CREATE TABLE IF NOT EXISTS `class_course_student_semester` (
`class_course_student_semester_id` int(11) NOT NULL,
  `class_course_semester_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `class_course_student_semester`
--

TRUNCATE TABLE `class_course_student_semester`;
-- --------------------------------------------------------

--
-- Table structure for table `class_course_teacher_semester`
--

DROP TABLE IF EXISTS `class_course_teacher_semester`;
CREATE TABLE IF NOT EXISTS `class_course_teacher_semester` (
`class_course_teacher_semester_id` int(11) NOT NULL,
  `class_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `class_course_teacher_semester`
--

TRUNCATE TABLE `class_course_teacher_semester`;
-- --------------------------------------------------------

--
-- Table structure for table `class_semester`
--

DROP TABLE IF EXISTS `class_semester`;
CREATE TABLE IF NOT EXISTS `class_semester` (
`class_semester_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `semester` int(11) NOT NULL,
  `students` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `class_semester`
--

TRUNCATE TABLE `class_semester`;
--
-- Dumping data for table `class_semester`
--

INSERT INTO `class_semester` (`class_semester_id`, `class_id`, `semester_id`, `semester`, `students`) VALUES
(1, 1, 1, 0, 0),
(2, 2, 1, 0, 0),
(3, 3, 1, 0, 0),
(4, 4, 1, 0, 0),
(5, 5, 1, 0, 0),
(6, 6, 1, 0, 0),
(7, 7, 1, 0, 0),
(8, 8, 1, 0, 0),
(9, 9, 1, 0, 0),
(10, 10, 1, 0, 0),
(11, 11, 1, 0, 0),
(12, 12, 1, 0, 0),
(13, 13, 1, 0, 0),
(14, 14, 1, 0, 0),
(15, 15, 1, 0, 0),
(16, 16, 1, 0, 0),
(17, 17, 1, 0, 0),
(18, 18, 1, 0, 0),
(19, 19, 1, 0, 0),
(20, 20, 1, 0, 0),
(21, 21, 1, 0, 0),
(22, 22, 1, 0, 0),
(23, 23, 1, 0, 0),
(24, 24, 1, 0, 0),
(25, 25, 1, 0, 0),
(26, 26, 1, 0, 0),
(27, 27, 1, 0, 0),
(28, 28, 1, 0, 0),
(29, 29, 1, 0, 0),
(30, 30, 1, 0, 0),
(31, 31, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
`course_id` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `courses`
--

TRUNCATE TABLE `courses`;
--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `code`, `name`, `department_id`) VALUES
(1, 'ACC101', 'ACC101', 1),
(2, 'AET101/HOD101', 'AET101/HOD101', 2),
(3, 'AFA201', 'AFA201', 2),
(4, 'CEA201', 'CEA201', 3),
(5, 'CEC201', 'CEC201', 3),
(6, 'CHN111', 'CHN111', 4),
(7, 'CHN122', 'CHN122', 4),
(8, 'CHN132', 'CHN132', 4),
(9, 'CSD201', 'CSD201', 5),
(10, 'CSD202', 'CSD202', 5),
(11, 'CSI101', 'CSI101', 5),
(12, 'DBI202', 'DBI202', 5),
(13, 'DGT202', 'DGT202', 3),
(14, 'DRF201', 'DRF201', 2),
(15, 'DRP101', 'DRP101', 2),
(16, 'DRS101', 'DRS101', 2),
(17, 'ECO121', 'ECO121', 4),
(18, 'EEA211', 'EEA211', 3),
(19, 'ENL111', 'ENL111', 6),
(20, 'ENL112', 'ENL112', 6),
(21, 'ENM201', 'ENM201', 6),
(22, 'FIN201', 'FIN201', 4),
(23, 'FIN202', 'FIN202', 4),
(24, 'GDF101', 'GDF101', 2),
(25, 'HOA101', 'HOA101', 2),
(26, 'IAO201', 'IAO201', 3),
(27, 'JPD111', 'JPD111', 7),
(28, 'LAB101', 'LAB101', 3),
(29, 'LAB211', 'LAB211', 3),
(30, 'MAC102', 'MAC102', 8),
(31, 'MAD101', 'MAD101', 8),
(32, 'MAE101', 'MAE101', 8),
(33, 'MKT101', 'MKT101', 4),
(34, 'OBE101', 'OBE101', 4),
(35, 'OSG202', 'OSG202', 5),
(36, 'PHY101', 'PHY101', 8),
(37, 'PRF192', 'PRF192', 5),
(38, 'PRJ311', 'PRJ311', 5),
(39, 'PRO192', 'PRO192', 5),
(40, 'PRO201', 'PRO201', 5),
(41, 'SSG101', 'SSG101', 9),
(42, 'VCM201', 'VCM201', 2);

-- --------------------------------------------------------

--
-- Table structure for table `course_semester`
--

DROP TABLE IF EXISTS `course_semester`;
CREATE TABLE IF NOT EXISTS `course_semester` (
`course_semester_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `slots` int(11) NOT NULL,
  `course_condition_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `course_semester`
--

TRUNCATE TABLE `course_semester`;
--
-- Dumping data for table `course_semester`
--

INSERT INTO `course_semester` (`course_semester_id`, `course_id`, `semester_id`, `slots`, `course_condition_id`) VALUES
(1, 1, 1, 30, NULL),
(2, 2, 1, 30, NULL),
(3, 3, 1, 30, NULL),
(4, 4, 1, 30, NULL),
(5, 5, 1, 30, NULL),
(6, 6, 1, 30, NULL),
(7, 7, 1, 30, NULL),
(8, 8, 1, 30, NULL),
(9, 9, 1, 30, NULL),
(10, 10, 1, 30, NULL),
(11, 11, 1, 30, NULL),
(12, 12, 1, 30, NULL),
(13, 13, 1, 30, NULL),
(14, 14, 1, 30, 3),
(15, 15, 1, 30, 16),
(16, 16, 1, 30, NULL),
(17, 17, 1, 30, NULL),
(18, 18, 1, 30, NULL),
(19, 19, 1, 30, NULL),
(20, 20, 1, 30, NULL),
(21, 21, 1, 30, NULL),
(22, 22, 1, 30, NULL),
(23, 23, 1, 30, NULL),
(24, 24, 1, 30, NULL),
(25, 25, 1, 30, NULL),
(26, 26, 1, 30, NULL),
(27, 27, 1, 30, NULL),
(28, 28, 1, 30, NULL),
(29, 29, 1, 30, NULL),
(30, 30, 1, 30, NULL),
(31, 31, 1, 30, NULL),
(32, 32, 1, 30, NULL),
(33, 33, 1, 30, NULL),
(34, 34, 1, 30, NULL),
(35, 35, 1, 30, NULL),
(36, 36, 1, 30, NULL),
(37, 37, 1, 30, NULL),
(38, 38, 1, 30, NULL),
(39, 39, 1, 30, NULL),
(40, 40, 1, 30, NULL),
(41, 41, 1, 30, NULL),
(42, 42, 1, 30, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
`department_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `department`
--

TRUNCATE TABLE `department`;
--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `code`, `name`) VALUES
(1, 'FIN', 'FIN'),
(2, 'GD', 'GD'),
(3, 'ITS', 'ITS'),
(4, 'BA', 'BA'),
(5, 'CF', 'CF'),
(6, 'ENG', 'ENG'),
(7, 'JPN', 'JPN'),
(8, 'MATH', 'MATH'),
(9, 'SS', 'SS');

-- --------------------------------------------------------

--
-- Table structure for table `program_semester`
--

DROP TABLE IF EXISTS `program_semester`;
CREATE TABLE IF NOT EXISTS `program_semester` (
`program_semester_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `current_semester` int(11) NOT NULL,
  `specialized_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `program_semester`
--

TRUNCATE TABLE `program_semester`;
-- --------------------------------------------------------

--
-- Table structure for table `program_semester_detail`
--

DROP TABLE IF EXISTS `program_semester_detail`;
CREATE TABLE IF NOT EXISTS `program_semester_detail` (
`program_semester_detail_id` int(11) NOT NULL,
  `program_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `program_semester_detail`
--

TRUNCATE TABLE `program_semester_detail`;
-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
`room_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `room_type` varchar(100) NOT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `rooms`
--

TRUNCATE TABLE `rooms`;
-- --------------------------------------------------------

--
-- Table structure for table `semesters`
--

DROP TABLE IF EXISTS `semesters`;
CREATE TABLE IF NOT EXISTS `semesters` (
`semester_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `semester_year` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `semesters`
--

TRUNCATE TABLE `semesters`;
--
-- Dumping data for table `semesters`
--

INSERT INTO `semesters` (`semester_id`, `code`, `name`, `semester_year`, `start_date`, `end_date`) VALUES
(1, 'SP15', 'Spring 2015', 2015, '2015-09-07', '2015-12-31');

-- --------------------------------------------------------

--
-- Table structure for table `specialized`
--

DROP TABLE IF EXISTS `specialized`;
CREATE TABLE IF NOT EXISTS `specialized` (
`specialized_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `specialized`
--

TRUNCATE TABLE `specialized`;
--
-- Dumping data for table `specialized`
--

INSERT INTO `specialized` (`specialized_id`, `code`, `name`) VALUES
(1, 'SE', 'SE'),
(2, 'EC', 'EC'),
(3, 'IA', 'IA'),
(4, 'GD', 'GD'),
(5, 'BA', 'BA'),
(6, 'FB', 'FB');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
`staff_id` int(11) NOT NULL,
  `account` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `account_type` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `staff`
--

TRUNCATE TABLE `staff`;
-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
`student_id` int(11) NOT NULL,
  `account` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `student_code` varchar(10) NOT NULL,
  `specialized_id` int(11) NOT NULL,
  `batch` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `class_semester_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `students`
--

TRUNCATE TABLE `students`;
-- --------------------------------------------------------

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
CREATE TABLE IF NOT EXISTS `student_course` (
`student_course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `class_semester_id` int(11) NOT NULL,
  `grade` int(11) NOT NULL,
  `passed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `student_course`
--

TRUNCATE TABLE `student_course`;
-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE IF NOT EXISTS `teachers` (
`teacher_id` int(11) NOT NULL,
  `account` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `account_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `teachers`
--

TRUNCATE TABLE `teachers`;
-- --------------------------------------------------------

--
-- Table structure for table `teacher_course_semester`
--

DROP TABLE IF EXISTS `teacher_course_semester`;
CREATE TABLE IF NOT EXISTS `teacher_course_semester` (
`teacher_course_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `teacher_course_semester`
--

TRUNCATE TABLE `teacher_course_semester`;
-- --------------------------------------------------------

--
-- Table structure for table `teacher_semester`
--

DROP TABLE IF EXISTS `teacher_semester`;
CREATE TABLE IF NOT EXISTS `teacher_semester` (
`teacher_semester_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `teacher_semester`
--

TRUNCATE TABLE `teacher_semester`;
-- --------------------------------------------------------

--
-- Table structure for table `time_table`
--

DROP TABLE IF EXISTS `time_table`;
CREATE TABLE IF NOT EXISTS `time_table` (
`time_table_id` int(11) NOT NULL,
  `class_course_semester_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `slot` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncate table before insert `time_table`
--

TRUNCATE TABLE `time_table`;
--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
 ADD PRIMARY KEY (`class_id`), ADD UNIQUE KEY `code` (`code`), ADD KEY `specialized_id` (`specialized_id`), ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
 ADD PRIMARY KEY (`class_course_semester_id`), ADD UNIQUE KEY `class_course_semester_idx` (`class_semester_id`,`course_semester_id`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`);

--
-- Indexes for table `class_course_student_semester`
--
ALTER TABLE `class_course_student_semester`
 ADD PRIMARY KEY (`class_course_student_semester_id`), ADD UNIQUE KEY `class_course_student_semester_idx` (`class_course_semester_id`,`student_id`), ADD KEY `class_course_semester_id` (`class_course_semester_id`), ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `class_course_teacher_semester`
--
ALTER TABLE `class_course_teacher_semester`
 ADD PRIMARY KEY (`class_course_teacher_semester_id`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`), ADD KEY `teacher_semester_id` (`teacher_semester_id`);

--
-- Indexes for table `class_semester`
--
ALTER TABLE `class_semester`
 ADD PRIMARY KEY (`class_semester_id`), ADD UNIQUE KEY `class_id` (`class_id`,`semester_id`), ADD KEY `semester_id` (`semester_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
 ADD PRIMARY KEY (`course_id`), ADD UNIQUE KEY `code` (`code`), ADD KEY `department_id` (`department_id`);

--
-- Indexes for table `course_semester`
--
ALTER TABLE `course_semester`
 ADD PRIMARY KEY (`course_semester_id`), ADD UNIQUE KEY `my_idx` (`course_id`,`semester_id`), ADD KEY `course_condition_id` (`course_condition_id`), ADD KEY `course_id` (`course_id`), ADD KEY `semester_id` (`semester_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
 ADD PRIMARY KEY (`department_id`), ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `program_semester`
--
ALTER TABLE `program_semester`
 ADD PRIMARY KEY (`program_semester_id`), ADD UNIQUE KEY `semester_id` (`semester_id`,`current_semester`,`specialized_id`), ADD KEY `specialized_id` (`specialized_id`);

--
-- Indexes for table `program_semester_detail`
--
ALTER TABLE `program_semester_detail`
 ADD PRIMARY KEY (`program_semester_detail_id`), ADD UNIQUE KEY `program_semester_id_2` (`program_semester_id`,`course_semester_id`), ADD KEY `program_semester_id` (`program_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
 ADD PRIMARY KEY (`room_id`), ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `semesters`
--
ALTER TABLE `semesters`
 ADD PRIMARY KEY (`semester_id`), ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `specialized`
--
ALTER TABLE `specialized`
 ADD PRIMARY KEY (`specialized_id`), ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
 ADD PRIMARY KEY (`staff_id`), ADD UNIQUE KEY `account` (`account`,`email`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
 ADD PRIMARY KEY (`student_id`), ADD UNIQUE KEY `account` (`account`,`email`,`student_code`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `specialized_id` (`specialized_id`);

--
-- Indexes for table `student_course`
--
ALTER TABLE `student_course`
 ADD PRIMARY KEY (`student_course_id`), ADD KEY `student_id` (`student_id`,`course_id`,`class_semester_id`), ADD KEY `course_id` (`course_id`), ADD KEY `class_semester_id` (`class_semester_id`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
 ADD PRIMARY KEY (`teacher_id`), ADD UNIQUE KEY `account` (`account`), ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
 ADD PRIMARY KEY (`teacher_course_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`), ADD KEY `teacher_semester_id` (`teacher_semester_id`);

--
-- Indexes for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
 ADD PRIMARY KEY (`teacher_semester_id`), ADD UNIQUE KEY `teacher_id` (`teacher_id`,`semester_id`), ADD KEY `semester_id` (`semester_id`);

--
-- Indexes for table `time_table`
--
ALTER TABLE `time_table`
 ADD PRIMARY KEY (`time_table_id`), ADD UNIQUE KEY `date` (`date`,`slot`,`room_id`), ADD UNIQUE KEY `date_2` (`date`,`slot`,`class_course_semester_id`), ADD KEY `class_course_semester_id` (`class_course_semester_id`), ADD KEY `room_id` (`room_id`), ADD KEY `teacher_id` (`teacher_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1560;
--
-- AUTO_INCREMENT for table `class_course_student_semester`
--
ALTER TABLE `class_course_student_semester`
MODIFY `class_course_student_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `class_course_teacher_semester`
--
ALTER TABLE `class_course_teacher_semester`
MODIFY `class_course_teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `class_semester`
--
ALTER TABLE `class_semester`
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `program_semester`
--
ALTER TABLE `program_semester`
MODIFY `program_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `program_semester_detail`
--
ALTER TABLE `program_semester_detail`
MODIFY `program_semester_detail_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `semesters`
--
ALTER TABLE `semesters`
MODIFY `semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `specialized`
--
ALTER TABLE `specialized`
MODIFY `specialized_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
MODIFY `student_course_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
MODIFY `teacher_course_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
MODIFY `teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `time_table`
--
ALTER TABLE `time_table`
MODIFY `time_table_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`),
ADD CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`);

--
-- Constraints for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
ADD CONSTRAINT `class_course_semester_ibfk_1` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`),
ADD CONSTRAINT `class_course_semester_ibfk_2` FOREIGN KEY (`course_semester_id`) REFERENCES `course_semester` (`course_semester_id`);

--
-- Constraints for table `class_course_student_semester`
--
ALTER TABLE `class_course_student_semester`
ADD CONSTRAINT `class_course_student_semester_ibfk_1` FOREIGN KEY (`class_course_semester_id`) REFERENCES `class_course_semester` (`class_course_semester_id`),
ADD CONSTRAINT `class_course_student_semester_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);

--
-- Constraints for table `class_course_teacher_semester`
--
ALTER TABLE `class_course_teacher_semester`
ADD CONSTRAINT `class_course_teacher_semester_ibfk_1` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`),
ADD CONSTRAINT `class_course_teacher_semester_ibfk_2` FOREIGN KEY (`course_semester_id`) REFERENCES `course_semester` (`course_semester_id`),
ADD CONSTRAINT `class_course_teacher_semester_ibfk_3` FOREIGN KEY (`teacher_semester_id`) REFERENCES `teacher_semester` (`teacher_semester_id`);

--
-- Constraints for table `class_semester`
--
ALTER TABLE `class_semester`
ADD CONSTRAINT `class_semester_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`),
ADD CONSTRAINT `class_semester_ibfk_2` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`);

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
ADD CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`);

--
-- Constraints for table `course_semester`
--
ALTER TABLE `course_semester`
ADD CONSTRAINT `course_semester_ibfk_3` FOREIGN KEY (`course_condition_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `course_semester_ibfk_4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `course_semester_ibfk_5` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`);

--
-- Constraints for table `program_semester`
--
ALTER TABLE `program_semester`
ADD CONSTRAINT `program_semester_ibfk_1` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`),
ADD CONSTRAINT `program_semester_ibfk_2` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`);

--
-- Constraints for table `program_semester_detail`
--
ALTER TABLE `program_semester_detail`
ADD CONSTRAINT `program_semester_detail_ibfk_1` FOREIGN KEY (`program_semester_id`) REFERENCES `program_semester` (`program_semester_id`),
ADD CONSTRAINT `program_semester_detail_ibfk_2` FOREIGN KEY (`course_semester_id`) REFERENCES `course_semester` (`course_semester_id`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`),
ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`);

--
-- Constraints for table `student_course`
--
ALTER TABLE `student_course`
ADD CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
ADD CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `student_course_ibfk_3` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`);

--
-- Constraints for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
ADD CONSTRAINT `teacher_course_semester_ibfk_1` FOREIGN KEY (`course_semester_id`) REFERENCES `course_semester` (`course_semester_id`),
ADD CONSTRAINT `teacher_course_semester_ibfk_2` FOREIGN KEY (`teacher_semester_id`) REFERENCES `teacher_semester` (`teacher_semester_id`);

--
-- Constraints for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
ADD CONSTRAINT `teacher_semester_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`),
ADD CONSTRAINT `teacher_semester_ibfk_2` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`);

--
-- Constraints for table `time_table`
--
ALTER TABLE `time_table`
ADD CONSTRAINT `time_table_ibfk_1` FOREIGN KEY (`class_course_semester_id`) REFERENCES `class_course_semester` (`class_course_semester_id`),
ADD CONSTRAINT `time_table_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`),
ADD CONSTRAINT `time_table_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
