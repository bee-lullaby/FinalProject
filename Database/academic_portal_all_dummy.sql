-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2015 at 12:48 PM
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
CREATE DATABASE IF NOT EXISTS `academic_portal` DEFAULT CHARACTER SET utf8 COLLATE utf8_vietnamese_ci;
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
  `detail_specialized_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `batch_char` varchar(1) NOT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `code`, `type`, `specialized_id`, `detail_specialized_id`, `course_id`, `batch`, `batch_char`, `number`) VALUES
(1, 'SE1001', 'Specialized', 1, NULL, NULL, 10, 'A', 1),
(2, 'SE1002', 'Specialized', 1, NULL, NULL, 10, 'A', 2),
(3, 'SE1003', 'Specialized', 1, NULL, NULL, 10, 'A', 3),
(4, 'SE1004', 'Specialized', 1, NULL, NULL, 10, 'B', 4),
(5, 'SE1005', 'Specialized', 1, NULL, NULL, 10, 'B', 5),
(6, 'SE1006', 'Specialized', 1, NULL, NULL, 10, 'B', 6),
(7, 'SE1007', 'Specialized', 1, NULL, NULL, 10, 'C', 7),
(8, 'SE1008', 'Specialized', 1, NULL, NULL, 10, 'C', 8),
(9, 'SE1009', 'Specialized', 1, NULL, NULL, 10, 'C', 9),
(10, 'SE1010', 'Specialized', 1, NULL, NULL, 10, 'C', 10),
(11, 'SE1011', 'Specialized', 1, NULL, NULL, 10, 'C', 11),
(12, 'SE1012', 'Specialized', 1, NULL, NULL, 10, 'C', 12),
(13, 'SE1013', 'Specialized', 1, NULL, NULL, 10, 'C', 13),
(14, 'EC1001', 'Specialized', 2, NULL, NULL, 10, 'A', 1),
(15, 'EC1002', 'Specialized', 2, NULL, NULL, 10, 'B', 2),
(16, 'EC1003', 'Specialized', 2, NULL, NULL, 10, 'C', 3),
(17, 'IA1001', 'Specialized', 3, NULL, NULL, 10, 'A', 1),
(18, 'IA1002', 'Specialized', 3, NULL, NULL, 10, 'B', 2),
(19, 'IA1003', 'Specialized', 3, NULL, NULL, 10, 'C', 3),
(20, 'GD1001', 'Specialized', 4, NULL, NULL, 10, 'B', 1),
(21, 'GD1002', 'Specialized', 4, NULL, NULL, 10, 'C', 2),
(22, 'BA1001', 'Specialized', 5, NULL, NULL, 10, 'A', 1),
(23, 'BA1002', 'Specialized', 5, NULL, NULL, 10, 'B', 2),
(24, 'FIN0903', 'Specialized', 5, 7, NULL, 9, 'C', 3),
(25, 'IBA0902', 'Specialized', 5, 10, NULL, 9, 'B', 2),
(26, 'IBA1001', 'Specialized', 5, 10, NULL, 10, 'A', 1),
(27, 'MKT0902', 'Specialized', 5, 11, NULL, 9, 'B', 2),
(28, 'COB0903', 'Specialized', 6, 9, NULL, 9, 'C', 3),
(29, 'COF0903', 'Specialized', 6, 8, NULL, 9, 'C', 3),
(30, 'FB1001', 'Specialized', 6, NULL, NULL, 10, 'A', 1),
(31, 'FB1002', 'Specialized', 6, NULL, NULL, 10, 'B', 2),
(33, 'SE1014', 'Specialized', 1, NULL, NULL, 10, '\0', 14),
(34, 'SE1015', 'Specialized', 1, NULL, NULL, 10, '\0', 15),
(35, 'SE0901', 'Specialized', 1, NULL, NULL, 9, '\0', 1),
(36, 'SE1016', 'Specialized', 1, NULL, NULL, 10, '\0', 16),
(37, 'SE1017', 'Specialized', 1, NULL, NULL, 10, '\0', 17),
(38, 'SE0902', 'Specialized', 1, NULL, NULL, 9, '\0', 2);

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
  `is_semester_long` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_course_semester`
--

INSERT INTO `class_course_semester` (`class_course_semester_id`, `class_semester_id`, `course_semester_id`, `block_condition`, `is_semester_long`) VALUES
(1, 7, 11, 0, 0),
(2, 7, 41, 0, 0),
(3, 7, 32, 0, 0),
(4, 7, 4, 0, 0),
(5, 7, 37, 0, 1),
(6, 8, 11, 0, 0),
(7, 8, 41, 0, 0),
(8, 8, 32, 0, 0),
(9, 8, 4, 0, 0),
(10, 8, 37, 0, 1),
(11, 9, 11, 0, 0),
(12, 9, 41, 0, 0),
(13, 9, 32, 0, 0),
(14, 9, 4, 0, 0),
(15, 9, 37, 0, 1),
(16, 10, 11, 0, 0),
(17, 10, 41, 0, 0),
(18, 10, 32, 0, 0),
(19, 10, 4, 0, 0),
(20, 10, 37, 0, 1),
(21, 11, 11, 0, 0),
(22, 11, 41, 0, 0),
(23, 11, 32, 0, 0),
(24, 11, 4, 0, 0),
(25, 11, 37, 0, 1),
(26, 12, 11, 0, 0),
(27, 12, 41, 0, 0),
(28, 12, 32, 0, 0),
(29, 12, 4, 0, 0),
(30, 12, 37, 0, 1),
(31, 13, 11, 0, 0),
(32, 13, 41, 0, 0),
(33, 13, 32, 0, 0),
(34, 13, 4, 0, 0),
(35, 13, 37, 0, 1),
(36, 16, 32, 0, 0),
(37, 16, 11, 0, 0),
(38, 16, 4, 0, 0),
(39, 16, 37, 0, 0),
(40, 16, 36, 0, 1),
(41, 19, 11, 0, 0),
(42, 19, 41, 0, 0),
(43, 19, 32, 0, 0),
(44, 19, 4, 0, 0),
(45, 19, 37, 0, 1),
(46, 21, 16, 1, 0),
(47, 21, 25, 0, 0),
(48, 21, 15, 2, 0),
(49, 21, 2, 0, 0),
(50, 21, 19, 0, 1),
(51, 4, 40, 0, 0),
(52, 4, 39, 0, 0),
(53, 4, 12, 0, 0),
(54, 4, 32, 0, 0),
(55, 4, 28, 0, 1),
(56, 5, 40, 0, 0),
(57, 5, 39, 0, 0),
(58, 5, 12, 0, 0),
(59, 5, 32, 0, 0),
(60, 5, 28, 0, 1),
(61, 6, 40, 0, 0),
(62, 6, 39, 0, 0),
(63, 6, 12, 0, 0),
(64, 6, 32, 0, 0),
(65, 6, 28, 0, 1),
(66, 15, 40, 0, 0),
(67, 15, 31, 0, 0),
(68, 15, 39, 0, 0),
(69, 15, 5, 0, 0),
(70, 15, 28, 0, 1),
(71, 18, 40, 0, 0),
(72, 18, 39, 0, 0),
(73, 18, 12, 0, 0),
(74, 18, 31, 0, 0),
(75, 18, 28, 0, 1),
(76, 20, 14, 2, 0),
(77, 20, 3, 1, 0),
(78, 20, 24, 0, 0),
(79, 20, 42, 0, 0),
(80, 20, 20, 0, 1),
(81, 23, 1, 0, 0),
(82, 23, 17, 0, 0),
(83, 23, 30, 0, 0),
(84, 23, 20, 0, 0),
(85, 23, 34, 0, 1),
(86, 31, 1, 0, 0),
(87, 31, 17, 0, 0),
(88, 31, 30, 0, 0),
(89, 31, 20, 0, 0),
(90, 31, 34, 0, 1),
(91, 1, 27, 0, 0),
(92, 1, 35, 0, 0),
(93, 1, 9, 0, 0),
(94, 1, 38, 0, 0),
(95, 1, 29, 0, 1),
(96, 2, 27, 0, 0),
(97, 2, 35, 0, 0),
(98, 2, 9, 0, 0),
(99, 2, 38, 0, 0),
(100, 2, 29, 0, 1),
(101, 3, 27, 0, 0),
(102, 3, 35, 0, 0),
(103, 3, 9, 0, 0),
(104, 3, 38, 0, 0),
(105, 3, 29, 0, 1),
(106, 14, 18, 0, 0),
(107, 14, 13, 0, 0),
(108, 14, 9, 0, 0),
(109, 14, 27, 0, 0),
(110, 14, 29, 0, 1),
(111, 17, 26, 0, 0),
(112, 17, 35, 0, 0),
(113, 17, 10, 0, 0),
(114, 17, 38, 0, 0),
(115, 17, 29, 0, 1),
(116, 22, 22, 0, 0),
(117, 22, 23, 0, 0),
(118, 22, 33, 0, 0),
(119, 22, 21, 0, 0),
(120, 22, 6, 0, 1),
(121, 30, 22, 0, 0),
(122, 30, 23, 0, 0),
(123, 30, 33, 0, 0),
(124, 30, 21, 0, 0),
(125, 30, 6, 0, 1),
(126, 26, 22, 0, 0),
(127, 26, 23, 0, 0),
(128, 26, 33, 0, 0),
(129, 26, 21, 0, 0),
(130, 26, 6, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `class_course_student_semester`
--

DROP TABLE IF EXISTS `class_course_student_semester`;
CREATE TABLE IF NOT EXISTS `class_course_student_semester` (
`class_course_student_semester_id` int(11) NOT NULL,
  `class_course_semester_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_course_student_semester`
--

INSERT INTO `class_course_student_semester` (`class_course_student_semester_id`, `class_course_semester_id`, `student_id`) VALUES
(135, 1, 23),
(136, 2, 23),
(137, 3, 23),
(138, 4, 23),
(139, 5, 23),
(140, 51, 24),
(141, 52, 24),
(142, 53, 24),
(143, 54, 24),
(144, 55, 24),
(145, 91, 25),
(146, 92, 25),
(147, 93, 25),
(148, 94, 25),
(149, 95, 25);

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

-- --------------------------------------------------------

--
-- Table structure for table `class_semester`
--

DROP TABLE IF EXISTS `class_semester`;
CREATE TABLE IF NOT EXISTS `class_semester` (
`class_semester_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `semester` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_semester`
--

INSERT INTO `class_semester` (`class_semester_id`, `class_id`, `semester_id`, `semester`) VALUES
(1, 1, 1, 3),
(2, 2, 1, 3),
(3, 3, 1, 3),
(4, 4, 1, 2),
(5, 5, 1, 2),
(6, 6, 1, 2),
(7, 7, 1, 1),
(8, 8, 1, 1),
(9, 9, 1, 1),
(10, 10, 1, 1),
(11, 11, 1, 1),
(12, 12, 1, 1),
(13, 13, 1, 1),
(14, 14, 1, 3),
(15, 15, 1, 2),
(16, 16, 1, 1),
(17, 17, 1, 3),
(18, 18, 1, 2),
(19, 19, 1, 1),
(20, 20, 1, 2),
(21, 21, 1, 1),
(22, 22, 1, 3),
(23, 23, 1, 2),
(24, 24, 1, 4),
(25, 25, 1, 5),
(26, 26, 1, 3),
(27, 27, 1, 5),
(28, 28, 1, 4),
(29, 29, 1, 4),
(30, 30, 1, 3),
(31, 31, 1, 2);

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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

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
(42, 'VCM201', 'VCM201', 2),
(43, 'FIN303', 'FIN303', 1),
(44, 'FIN301', 'FIN301', 1),
(45, 'MAS201', 'MAS201', 8),
(46, 'ENM301', 'ENM301', 6),
(47, 'BKG201', 'BKG201', 4),
(48, 'SSC101', 'SSC101', 4),
(49, 'MKT202', 'MKT202', 4),
(50, 'MKT301', 'MKT301', 4),
(51, 'ENM401', 'ENM401', 6);

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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

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
(42, 42, 1, 30, NULL),
(43, 43, 1, 30, NULL),
(44, 44, 1, 30, NULL),
(45, 45, 1, 30, NULL),
(46, 46, 1, 30, NULL),
(47, 47, 1, 30, NULL),
(48, 48, 1, 30, NULL),
(49, 49, 1, 30, NULL),
(50, 50, 1, 30, NULL),
(51, 51, 1, 30, NULL);

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
  `specialized_id` int(11) NOT NULL,
  `detail_specialized_id` int(11) DEFAULT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_semester`
--

INSERT INTO `program_semester` (`program_semester_id`, `semester_id`, `current_semester`, `specialized_id`, `detail_specialized_id`, `batch`) VALUES
(1, 1, 1, 1, NULL, 10),
(2, 1, 1, 2, NULL, 10),
(3, 1, 1, 3, NULL, 10),
(4, 1, 1, 4, NULL, 10),
(5, 1, 2, 1, NULL, 10),
(6, 1, 2, 2, NULL, 10),
(7, 1, 2, 3, NULL, 10),
(8, 1, 2, 4, NULL, 10),
(9, 1, 2, 5, NULL, 10),
(10, 1, 2, 6, NULL, 10),
(11, 1, 3, 1, NULL, 9),
(12, 1, 3, 2, NULL, 9),
(13, 1, 3, 3, NULL, 9),
(14, 1, 3, 5, NULL, 9),
(15, 1, 3, 6, NULL, 9),
(16, 1, 4, 5, 7, 9),
(17, 1, 4, 6, 8, 9),
(18, 1, 4, 6, 9, 9),
(19, 1, 5, 5, 10, 9),
(20, 1, 5, 5, 11, 9),
(21, 1, 3, 5, 10, 9);

-- --------------------------------------------------------

--
-- Table structure for table `program_semester_detail`
--

DROP TABLE IF EXISTS `program_semester_detail`;
CREATE TABLE IF NOT EXISTS `program_semester_detail` (
`program_semester_detail_id` int(11) NOT NULL,
  `program_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL,
  `is_semester_long` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_semester_detail`
--

INSERT INTO `program_semester_detail` (`program_semester_detail_id`, `program_semester_id`, `course_semester_id`, `is_semester_long`) VALUES
(1, 1, 11, 0),
(2, 1, 41, 0),
(3, 1, 32, 0),
(4, 1, 4, 0),
(5, 1, 37, 0),
(6, 2, 32, 0),
(7, 2, 11, 0),
(8, 2, 4, 0),
(9, 2, 37, 0),
(10, 2, 36, 0),
(11, 3, 11, 0),
(12, 3, 41, 0),
(13, 3, 32, 0),
(14, 3, 4, 0),
(15, 3, 37, 0),
(16, 4, 16, 0),
(17, 4, 25, 0),
(18, 4, 15, 0),
(19, 4, 2, 0),
(20, 4, 19, 0),
(21, 5, 40, 0),
(22, 5, 39, 0),
(23, 5, 12, 0),
(24, 5, 32, 0),
(25, 5, 28, 0),
(26, 6, 40, 0),
(27, 6, 31, 0),
(28, 6, 39, 0),
(29, 6, 5, 0),
(30, 6, 28, 0),
(31, 7, 40, 0),
(32, 7, 39, 0),
(33, 7, 12, 0),
(34, 7, 31, 0),
(35, 7, 28, 0),
(36, 8, 14, 0),
(37, 8, 3, 0),
(38, 8, 24, 0),
(39, 8, 42, 0),
(40, 8, 20, 0),
(41, 9, 1, 0),
(42, 9, 17, 0),
(43, 9, 30, 0),
(44, 9, 20, 0),
(45, 9, 34, 0),
(46, 10, 1, 0),
(47, 10, 17, 0),
(48, 10, 30, 0),
(49, 10, 20, 0),
(50, 10, 34, 0),
(51, 11, 27, 0),
(52, 11, 35, 0),
(53, 11, 9, 0),
(54, 11, 38, 0),
(55, 11, 29, 0),
(56, 12, 18, 0),
(57, 12, 13, 0),
(58, 12, 9, 0),
(59, 12, 27, 0),
(60, 12, 29, 0),
(61, 13, 26, 0),
(62, 13, 35, 0),
(63, 13, 10, 0),
(64, 13, 38, 0),
(65, 13, 29, 0),
(66, 14, 22, 0),
(67, 14, 23, 0),
(68, 14, 33, 0),
(69, 14, 21, 0),
(70, 14, 6, 0),
(71, 15, 22, 0),
(72, 15, 23, 0),
(73, 15, 33, 0),
(74, 15, 21, 0),
(75, 15, 6, 0),
(76, 16, 43, 0),
(77, 16, 44, 0),
(78, 16, 45, 0),
(79, 16, 46, 0),
(80, 16, 7, 0),
(81, 17, 43, 0),
(82, 17, 44, 0),
(83, 17, 45, 0),
(84, 17, 46, 0),
(85, 17, 7, 0),
(86, 18, 44, 0),
(87, 18, 45, 0),
(88, 18, 46, 0),
(89, 18, 47, 0),
(90, 18, 7, 0),
(91, 19, 48, 0),
(92, 19, 49, 0),
(93, 19, 50, 0),
(94, 19, 20, 0),
(95, 19, 8, 0),
(96, 20, 48, 0),
(97, 20, 49, 0),
(98, 20, 50, 0),
(99, 20, 51, 0),
(100, 20, 8, 0),
(101, 21, 22, 0),
(102, 21, 23, 0),
(103, 21, 33, 0),
(104, 21, 21, 0),
(105, 21, 6, 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `code`, `room_type`, `capacity`) VALUES
(1, 'room1', 'room', 30);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `semesters`
--

INSERT INTO `semesters` (`semester_id`, `code`, `name`, `semester_year`, `start_date`, `end_date`) VALUES
(1, 'FALL15', 'Fall 2015', 2015, '2015-09-07', '2015-12-31'),
(3, '1', '1', 1, '2015-07-01', '2015-07-15');

-- --------------------------------------------------------

--
-- Table structure for table `specialized`
--

DROP TABLE IF EXISTS `specialized`;
CREATE TABLE IF NOT EXISTS `specialized` (
`specialized_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `is_detail_specialized` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialized`
--

INSERT INTO `specialized` (`specialized_id`, `code`, `name`, `is_detail_specialized`) VALUES
(1, 'SE', 'SE', 0),
(2, 'EC', 'EC', 0),
(3, 'IA', 'IA', 0),
(4, 'GD', 'GD', 0),
(5, 'BA', 'BA', 0),
(6, 'FB', 'FB', 0),
(7, 'FIN', 'FIN', 1),
(8, 'COF', 'COF', 1),
(9, 'COB', 'COB', 1),
(10, 'IBA', 'IBA', 1),
(11, 'MKT', 'MKT', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `account`, `name`, `email`, `account_type`) VALUES
(1, 'duchmse02551', 'Hoang Minh Duc', 'duchmse02551@fpt.edu.vn', 'staff'),
(2, 'hansse02618', 'hans', 'hansse02618@fpt.edu.vn', 'staff');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
`student_id` int(11) NOT NULL,
  `account` varchar(20) CHARACTER SET latin1 NOT NULL,
  `name` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 NOT NULL,
  `student_code` varchar(10) CHARACTER SET latin1 NOT NULL,
  `specialized_id` int(11) NOT NULL,
  `detail_specialized_id` int(11) DEFAULT NULL,
  `batch` varchar(10) CHARACTER SET latin1 NOT NULL,
  `semester` int(11) NOT NULL,
  `class_semester_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=284 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(23, 'DucHMSE00000', 'Hoàng Minh Đức', 'DucHMSE00000@fpt.edu.vn', 'SE00000', 1, NULL, '7B', 1, 7),
(24, 'CongLTSE00001', 'Lưu Thành Công', 'CongLTSE00001@fpt.edu.vn', 'SE00001', 1, NULL, '7B', 2, 4),
(25, 'ThanhVCSE00002', 'Vũ Công Thành', 'ThanhVCSE00002@fpt.edu.vn', 'SE00002', 1, NULL, '7B', 3, 1),
(26, 'DucDASE01769', 'Đồng Anh Đức', 'DucDASE01769@fpt.edu.vn', 'SE01769', 1, NULL, 'hameo', 1, NULL),
(27, 'GiangNQSE01772', 'Nguyễn Quốc Giang', 'GiangNQSE01772@fpt.edu.vn', 'SE01772', 1, NULL, 'hameo', 1, NULL),
(28, 'AnhNDSE01790', 'Nguyễn Duy Anh', 'AnhNDSE01790@fpt.edu.vn', 'SE01790', 1, NULL, 'hameo', 1, NULL),
(29, 'LiemBMSE01829', 'Bùi Minh Liêm', 'LiemBMSE01829@fpt.edu.vn', 'SE01829', 1, NULL, 'hameo', 1, NULL),
(30, 'HungNDSE01832', 'Ngô Duy Hùng', 'HungNDSE01832@fpt.edu.vn', 'SE01832', 1, NULL, 'hameo', 1, NULL),
(31, 'TuanTASE01833', 'Trần Anh Tuấn', 'TuanTASE01833@fpt.edu.vn', 'SE01833', 1, NULL, 'hameo', 1, NULL),
(32, 'HoangTLSE01882', 'Trần Lê Hoàng', 'HoangTLSE01882@fpt.edu.vn', 'SE01882', 1, NULL, 'hameo', 1, NULL),
(33, 'DungBPSE01900', 'Bùi Phương Dung', 'DungBPSE01900@fpt.edu.vn', 'SE01900', 1, NULL, 'hameo', 1, NULL),
(34, 'LinhNTTSE01913', 'Nguyễn Thị Thùy Linh', 'LinhNTTSE01913@fpt.edu.vn', 'SE01913', 1, NULL, 'hameo', 1, NULL),
(35, 'DungNVSE01928', 'Nguyễn Văn Dũng', 'DungNVSE01928@fpt.edu.vn', 'SE01928', 1, NULL, 'hameo', 1, NULL),
(36, 'ToanNNSE01950', 'Nguyễn Như Toàn', 'ToanNNSE01950@fpt.edu.vn', 'SE01950', 1, NULL, 'hameo', 1, NULL),
(37, 'DucDASE01970', 'Đỗ Anh Đức', 'DucDASE01970@fpt.edu.vn', 'SE01970', 1, NULL, 'hameo', 1, NULL),
(38, 'TungTTSE01971', 'Trịnh Thanh Tùng', 'TungTTSE01971@fpt.edu.vn', 'SE01971', 1, NULL, 'hameo', 1, NULL),
(39, 'TrucTVSE01972', 'Trần Văn Trúc', 'TrucTVSE01972@fpt.edu.vn', 'SE01972', 1, NULL, 'hameo', 1, NULL),
(40, 'HiepDHSE01022', 'Đỗ Hoàng Hiệp', 'HiepDHSE01022@fpt.edu.vn', 'SE01022', 1, NULL, 'hameo', 1, NULL),
(41, 'TuLDSE01061', 'Lưu Đức Tú', 'TuLDSE01061@fpt.edu.vn', 'SE01061', 1, NULL, 'hameo', 1, NULL),
(42, 'LongPHSE01071', 'Phan Hoàng Long', 'LongPHSE01071@fpt.edu.vn', 'SE01071', 1, NULL, 'hameo', 1, NULL),
(43, 'TungNCSE01079', 'Nguyễn Công Tùng', 'TungNCSE01079@fpt.edu.vn', 'SE01079', 1, NULL, 'hameo', 1, NULL),
(44, 'DungVVSE01085', 'Vũ Việt Dũng', 'DungVVSE01085@fpt.edu.vn', 'SE01085', 1, NULL, 'hameo', 1, NULL),
(45, 'BachBXSE01093', 'Bùi Xuân Bách', 'BachBXSE01093@fpt.edu.vn', 'SE01093', 1, NULL, 'hameo', 1, NULL),
(46, 'KhanhPVSE01101', 'Phạm Văn Khánh', 'KhanhPVSE01101@fpt.edu.vn', 'SE01101', 1, NULL, 'hameo', 1, NULL),
(47, 'HuyHQSE01103', 'Hà Quốc Huy', 'HuyHQSE01103@fpt.edu.vn', 'SE01103', 1, NULL, 'hameo', 1, NULL),
(48, 'KhanhNVSE01127', 'Nguyễn Văn Khánh', 'KhanhNVSE01127@fpt.edu.vn', 'SE01127', 1, NULL, 'hameo', 1, NULL),
(49, 'HuongNTTSE01136', 'Ngô Thị Thu Hương', 'HuongNTTSE01136@fpt.edu.vn', 'SE01136', 1, NULL, 'hameo', 1, NULL),
(50, 'ToanTDSE01152', 'Trần Đức Toàn', 'ToanTDSE01152@fpt.edu.vn', 'SE01152', 1, NULL, 'hameo', 1, NULL),
(51, 'NgocLVSE01160', 'Lê Văn Ngọc', 'NgocLVSE01160@fpt.edu.vn', 'SE01160', 1, NULL, 'hameo', 1, NULL),
(52, 'NgocNVSE01192', 'Nguyễn Văn Ngọc', 'NgocNVSE01192@fpt.edu.vn', 'SE01192', 1, NULL, 'hameo', 1, NULL),
(53, 'DungNVSE01225', 'Nguyễn Văn Dũng', 'DungNVSE01225@fpt.edu.vn', 'SE01225', 1, NULL, 'hameo', 1, NULL),
(54, 'DungMTSE01229', 'Mai Trung Dũng', 'DungMTSE01229@fpt.edu.vn', 'SE01229', 1, NULL, 'hameo', 1, NULL),
(55, 'DungDASE01238', 'Dương Anh Dũng', 'DungDASE01238@fpt.edu.vn', 'SE01238', 1, NULL, 'hameo', 1, NULL),
(56, 'CuongBHSE01245', 'Bùi Huy Cường', 'CuongBHSE01245@fpt.edu.vn', 'SE01245', 1, NULL, 'hameo', 1, NULL),
(57, 'LinhNVSE01250', 'Nguyễn Văn Linh', 'LinhNVSE01250@fpt.edu.vn', 'SE01250', 1, NULL, 'hameo', 1, NULL),
(58, 'TrungLTSE01251', 'Lê Toàn Trung', 'TrungLTSE01251@fpt.edu.vn', 'SE01251', 1, NULL, 'hameo', 1, NULL),
(59, 'LamVVSE01253', 'Vũ Văn Lâm', 'LamVVSE01253@fpt.edu.vn', 'SE01253', 1, NULL, 'hameo', 1, NULL),
(60, 'AnhLHSE01271', 'Lê Hoàng Anh', 'AnhLHSE01271@fpt.edu.vn', 'SE01271', 1, NULL, 'hameo', 1, NULL),
(61, 'TuanVASE01312', 'Võ Anh Tuấn', 'TuanVASE01312@fpt.edu.vn', 'SE01312', 1, NULL, 'hameo', 1, NULL),
(62, 'TanTDSE01315', 'Trịnh Duy Tân', 'TanTDSE01315@fpt.edu.vn', 'SE01315', 1, NULL, 'hameo', 1, NULL),
(63, 'HuyNTSE01333', 'Nguyễn Tiệp Huy', 'HuyNTSE01333@fpt.edu.vn', 'SE01333', 1, NULL, 'hameo', 1, NULL),
(64, 'QuyetPMSE01345', 'Phùng Minh Quyết', 'QuyetPMSE01345@fpt.edu.vn', 'SE01345', 1, NULL, 'hameo', 1, NULL),
(65, 'ManhPTSE01365', 'Phạm Tiến Mạnh', 'ManhPTSE01365@fpt.edu.vn', 'SE01365', 1, NULL, 'hameo', 1, NULL),
(66, 'HuynhNTSE01384', 'Nguyễn Thế Huỳnh', 'HuynhNTSE01384@fpt.edu.vn', 'SE01384', 1, NULL, 'hameo', 1, NULL),
(67, 'HanhNTSE01404', 'Nguyễn Tiến Hạnh', 'HanhNTSE01404@fpt.edu.vn', 'SE01404', 1, NULL, 'hameo', 1, NULL),
(68, 'DuyNTSE01409', 'Nguyễn Thành Duy', 'DuyNTSE01409@fpt.edu.vn', 'SE01409', 1, NULL, 'hameo', 1, NULL),
(69, 'HungTQSE01418', 'Trần Quốc Hưng', 'HungTQSE01418@fpt.edu.vn', 'SE01418', 1, NULL, 'hameo', 1, NULL),
(70, 'ThangDCSE01425', 'Đỗ Chiến Thắng', 'ThangDCSE01425@fpt.edu.vn', 'SE01425', 1, NULL, 'hameo', 1, NULL),
(71, 'HuyNQSE01448', 'Nguyễn Quang Huy', 'HuyNQSE01448@fpt.edu.vn', 'SE01448', 1, NULL, 'hameo', 1, NULL),
(72, 'HuynhNASE01461', 'Nguyễn Anh Huỳnh', 'HuynhNASE01461@fpt.edu.vn', 'SE01461', 1, NULL, 'hameo', 1, NULL),
(73, 'NguyenPVSE01929', 'Phạm Văn Nguyên', 'NguyenPVSE01929@fpt.edu.vn', 'SE01929', 1, NULL, 'hameo', 1, NULL),
(74, 'TrangPDSE01892', 'Phạm Đình Tráng', 'TrangPDSE01892@fpt.edu.vn', 'SE01892', 1, NULL, 'hameo', 1, NULL),
(75, 'ThanhNDSE01182', 'Nguyễn Đăng Thanh', 'ThanhNDSE01182@fpt.edu.vn', 'SE01182', 1, NULL, 'hameo', 1, NULL),
(76, 'HungNTSE01838', 'Nguyễn Trường Hưng', 'HungNTSE01838@fpt.edu.vn', 'SE01838', 1, NULL, 'hameo', 1, NULL),
(77, 'LinhNTSE01867', 'Nguyễn Tất Linh', 'LinhNTSE01867@fpt.edu.vn', 'SE01867', 1, NULL, 'hameo', 1, NULL),
(78, 'TrungNQSE01976', 'Nguyễn Quang Trung', 'TrungNQSE01976@fpt.edu.vn', 'SE01976', 1, NULL, 'hameo', 1, NULL),
(79, 'DungDTSE01996', 'Đào Thế Dũng', 'DungDTSE01996@fpt.edu.vn', 'SE01996', 1, NULL, 'hameo', 1, NULL),
(80, 'HoangTDSE01012', 'Trương Đăng Hoàng', 'HoangTDSE01012@fpt.edu.vn', 'SE01012', 1, NULL, 'hameo', 1, NULL),
(81, 'TrungHBSE01051', 'Hoàng Bảo Trung', 'TrungHBSE01051@fpt.edu.vn', 'SE01051', 1, NULL, 'hameo', 1, NULL),
(82, 'HuyenNTSE01080', 'Nguyễn Thanh Huyền', 'HuyenNTSE01080@fpt.edu.vn', 'SE01080', 1, NULL, 'hameo', 1, NULL),
(83, 'NgocDBSE01129', 'Dương Bảo Ngọc', 'NgocDBSE01129@fpt.edu.vn', 'SE01129', 1, NULL, 'hameo', 1, NULL),
(84, 'HuyenPTSE01161', 'Phan Thanh Huyền', 'HuyenPTSE01161@fpt.edu.vn', 'SE01161', 1, NULL, 'hameo', 1, NULL),
(85, 'QuanDASE01240', 'Đỗ Anh Quân', 'QuanDASE01240@fpt.edu.vn', 'SE01240', 1, NULL, 'hameo', 1, NULL),
(86, 'HoangPMSE01336', 'Phạm Minh Hoàng', 'HoangPMSE01336@fpt.edu.vn', 'SE01336', 1, NULL, 'hameo', 1, NULL),
(87, 'TungLXSE01424', 'Lê Xuân Tùng', 'TungLXSE01424@fpt.edu.vn', 'SE01424', 1, NULL, 'hameo', 1, NULL),
(88, 'HuongLTTSE01445', 'Lê Thị Thu Hương', 'HuongLTTSE01445@fpt.edu.vn', 'SE01445', 1, NULL, 'hameo', 1, NULL),
(89, 'HieuTTSE01679', 'Triệu Trung Hiếu', 'HieuTTSE01679@fpt.edu.vn', 'SE01679', 1, NULL, 'hameo', 1, NULL),
(90, 'TanLDSE01800', 'Lê Duy Tân', 'TanLDSE01800@fpt.edu.vn', 'SE01800', 1, NULL, 'hameo', 1, NULL),
(91, 'ThangNHSE01802', 'Nguyễn Hữu Thắng', 'ThangNHSE01802@fpt.edu.vn', 'SE01802', 1, NULL, 'hameo', 1, NULL),
(92, 'ThoiNXSE01861', 'Nguyễn Xuân Thời', 'ThoiNXSE01861@fpt.edu.vn', 'SE01861', 1, NULL, 'hameo', 1, NULL),
(93, 'OanhDVSE01872', 'Đỗ Văn Oánh', 'OanhDVSE01872@fpt.edu.vn', 'SE01872', 1, NULL, 'hameo', 1, NULL),
(94, 'AnhPTSE01973', 'Phạm Tuấn Anh', 'AnhPTSE01973@fpt.edu.vn', 'SE01973', 1, NULL, 'hameo', 1, NULL),
(95, 'ThangNCTSE01984', 'Nguyễn Công Tôn Thắng', 'ThangNCTSE01984@fpt.edu.vn', 'SE01984', 1, NULL, 'hameo', 1, NULL),
(96, 'DungBVSE01008', 'Bùi Văn Dũng', 'DungBVSE01008@fpt.edu.vn', 'SE01008', 1, NULL, 'hameo', 1, NULL),
(97, 'HungNTSE01014', 'Nguyễn Tiến Hùng', 'HungNTSE01014@fpt.edu.vn', 'SE01014', 1, NULL, 'hameo', 1, NULL),
(98, 'LinhVNSE01138', 'Vũ Ngọc Linh', 'LinhVNSE01138@fpt.edu.vn', 'SE01138', 1, NULL, 'hameo', 1, NULL),
(99, 'SonNTSE01141', 'Nguyễn Trường Sơn', 'SonNTSE01141@fpt.edu.vn', 'SE01141', 1, NULL, 'hameo', 1, NULL),
(100, 'DungBVSE01197', 'Bùi Việt Dũng', 'DungBVSE01197@fpt.edu.vn', 'SE01197', 1, NULL, 'hameo', 1, NULL),
(101, 'KhanhNDSE01241', 'Nguyễn Duy Khánh', 'KhanhNDSE01241@fpt.edu.vn', 'SE01241', 1, NULL, 'hameo', 1, NULL),
(102, 'TungNSSE01249', 'Nguyễn Sơn Tùng', 'TungNSSE01249@fpt.edu.vn', 'SE01249', 1, NULL, 'hameo', 1, NULL),
(103, 'NhatNMSE01273', 'Nguyễn Minh Nhật', 'NhatNMSE01273@fpt.edu.vn', 'SE01273', 1, NULL, 'hameo', 1, NULL),
(104, 'NamNHSE01283', 'Nguyễn Hồng Nam', 'NamNHSE01283@fpt.edu.vn', 'SE01283', 1, NULL, 'hameo', 1, NULL),
(105, 'HaiHVSE01306', 'Hà Việt Hải', 'HaiHVSE01306@fpt.edu.vn', 'SE01306', 1, NULL, 'hameo', 1, NULL),
(106, 'DatDCSE01346', 'Dương Công Đạt', 'DatDCSE01346@fpt.edu.vn', 'SE01346', 1, NULL, 'hameo', 1, NULL),
(107, 'ChungKVSE01348', 'Kiều Văn Chung', 'ChungKVSE01348@fpt.edu.vn', 'SE01348', 1, NULL, 'hameo', 1, NULL),
(108, 'HaiNTSE01354', 'Nguyễn Thanh Hải', 'HaiNTSE01354@fpt.edu.vn', 'SE01354', 1, NULL, 'hameo', 1, NULL),
(109, 'HungTSE01381', 'Trần Hưng', 'HungTSE01381@fpt.edu.vn', 'SE01381', 1, NULL, 'hameo', 1, NULL),
(110, 'HuyLMSE01391', 'Lê Minh Huy', 'HuyLMSE01391@fpt.edu.vn', 'SE01391', 1, NULL, 'hameo', 1, NULL),
(111, 'KienNTSE01412', 'Nguyễn Trung Kiên', 'KienNTSE01412@fpt.edu.vn', 'SE01412', 1, NULL, 'hameo', 1, NULL),
(112, 'NamLTSE01427', 'Lý Thanh Nam', 'NamLTSE01427@fpt.edu.vn', 'SE01427', 1, NULL, 'hameo', 1, NULL),
(113, 'ThangNDSE01439', 'Nguyễn Đức Thắng', 'ThangNDSE01439@fpt.edu.vn', 'SE01439', 1, NULL, 'hameo', 1, NULL),
(114, 'HieuNTSE01444', 'Nguyễn Trung Hiếu', 'HieuNTSE01444@fpt.edu.vn', 'SE01444', 1, NULL, 'hameo', 1, NULL),
(115, 'AnhDDSE01637', 'Dương Duy Anh', 'AnhDDSE01637@fpt.edu.vn', 'SE01637', 1, NULL, 'hameo', 1, NULL),
(116, 'AnhHTSE01675', 'Hoàng Tuấn Anh', 'AnhHTSE01675@fpt.edu.vn', 'SE01675', 1, NULL, 'hameo', 1, NULL),
(117, 'AnhVDSE01688', 'Võ Đức Anh', 'AnhVDSE01688@fpt.edu.vn', 'SE01688', 1, NULL, 'hameo', 1, NULL),
(118, 'DungNNSE01588', 'Nguyễn Ngọc Dung', 'DungNNSE01588@fpt.edu.vn', 'SE01588', 1, NULL, 'hameo', 1, NULL),
(119, 'DungNVSE01712', 'Nguyễn Văn Dũng', 'DungNVSE01712@fpt.edu.vn', 'SE01712', 1, NULL, 'hameo', 1, NULL),
(120, 'DuyVTSE01614', 'Vũ Tiến Duy', 'DuyVTSE01614@fpt.edu.vn', 'SE01614', 1, NULL, 'hameo', 1, NULL),
(121, 'DuongHSE01587', 'Hoàng Dương', 'DuongHSE01587@fpt.edu.vn', 'SE01587', 1, NULL, 'hameo', 1, NULL),
(122, 'DaiTVSE01620', 'Trần Văn Đại', 'DaiTVSE01620@fpt.edu.vn', 'SE01620', 1, NULL, 'hameo', 1, NULL),
(123, 'DatHQSE01667', 'Hoàng Quốc Đạt', 'DatHQSE01667@fpt.edu.vn', 'SE01667', 1, NULL, 'hameo', 1, NULL),
(124, 'DucTMSE01600', 'Trần Minh Đức', 'DucTMSE01600@fpt.edu.vn', 'SE01600', 1, NULL, 'hameo', 1, NULL),
(125, 'HaiNPSE01640', 'Nguyễn Phú Hải', 'HaiNPSE01640@fpt.edu.vn', 'SE01640', 1, NULL, 'hameo', 1, NULL),
(126, 'HaoVQSE01417', 'Vy Quang Hào', 'HaoVQSE01417@fpt.edu.vn', 'SE01417', 1, NULL, 'hameo', 1, NULL),
(127, 'HieuNMSE01684', 'Nguyễn Minh Hiếu', 'HieuNMSE01684@fpt.edu.vn', 'SE01684', 1, NULL, 'hameo', 1, NULL),
(128, 'KhaiHMSE01732', 'Hoàng Minh Khải', 'KhaiHMSE01732@fpt.edu.vn', 'SE01732', 1, NULL, 'hameo', 1, NULL),
(129, 'LamNTSE01632', 'Nguyễn Tùng Lâm', 'LamNTSE01632@fpt.edu.vn', 'SE01632', 1, NULL, 'hameo', 1, NULL),
(130, 'LeVTSE01618', 'Vũ Thị Lê', 'LeVTSE01618@fpt.edu.vn', 'SE01618', 1, NULL, 'hameo', 1, NULL),
(131, 'LinhNHSE01733', 'Nguyễn Hoài Linh', 'LinhNHSE01733@fpt.edu.vn', 'SE01733', 1, NULL, 'hameo', 1, NULL),
(132, 'LongLBSE01680', 'Lê Bá Long', 'LongLBSE01680@fpt.edu.vn', 'SE01680', 1, NULL, 'hameo', 1, NULL),
(133, 'LongLHSE01695', 'Lê Hải Long', 'LongLHSE01695@fpt.edu.vn', 'SE01695', 1, NULL, 'hameo', 1, NULL),
(134, 'LongNPSE01691', 'Nghiêm Phi Long', 'LongNPSE01691@fpt.edu.vn', 'SE01691', 1, NULL, 'hameo', 1, NULL),
(135, 'NamNHSE01702', 'Nguyễn Hải Nam', 'NamNHSE01702@fpt.edu.vn', 'SE01702', 1, NULL, 'hameo', 1, NULL),
(136, 'NguyenTPSE01761', 'Trần Phú Nguyên', 'NguyenTPSE01761@fpt.edu.vn', 'SE01761', 1, NULL, 'hameo', 1, NULL),
(137, 'PhuongHMSE01405', 'Hà Minh Phương', 'PhuongHMSE01405@fpt.edu.vn', 'SE01405', 1, NULL, 'hameo', 1, NULL),
(138, 'TuanNASE01751', 'Nguyễn Anh Tuấn', 'TuanNASE01751@fpt.edu.vn', 'SE01751', 1, NULL, 'hameo', 1, NULL),
(139, 'TrungNVSE01689', 'Nguyễn Văn Trung', 'TrungNVSE01689@fpt.edu.vn', 'SE01689', 1, NULL, 'hameo', 1, NULL),
(140, 'QuyenTCSE01644', 'Trần Chí Quyền', 'QuyenTCSE01644@fpt.edu.vn', 'SE01644', 1, NULL, 'hameo', 1, NULL),
(141, 'TamLMSE01603', 'Lê Minh Tâm', 'TamLMSE01603@fpt.edu.vn', 'SE01603', 1, NULL, 'hameo', 1, NULL),
(142, 'TamNXSE01674', 'Nguyễn Xuân Tâm', 'TamNXSE01674@fpt.edu.vn', 'SE01674', 1, NULL, 'hameo', 1, NULL),
(143, 'ThanhNHSE01743', 'Nguyễn Hoàng Thanh', 'ThanhNHSE01743@fpt.edu.vn', 'SE01743', 1, NULL, 'hameo', 1, NULL),
(144, 'ThangCQSE01710', 'Cao Quyết Thắng', 'ThangCQSE01710@fpt.edu.vn', 'SE01710', 1, NULL, 'hameo', 1, NULL),
(145, 'TuyenNVSE01646', 'Nguyễn Viết Tuyên', 'TuyenNVSE01646@fpt.edu.vn', 'SE01646', 1, NULL, 'hameo', 1, NULL),
(146, 'ThietNVSE01662', 'Nguyễn Văn Thiết', 'ThietNVSE01662@fpt.edu.vn', 'SE01662', 1, NULL, 'hameo', 1, NULL),
(147, 'ThuNTMSE01633', 'Nguyễn Thị Minh Thu', 'ThuNTMSE01633@fpt.edu.vn', 'SE01633', 1, NULL, 'hameo', 1, NULL),
(148, 'TrungLTSE01729', 'Lê Thành Trung', 'TrungLTSE01729@fpt.edu.vn', 'SE01729', 1, NULL, 'hameo', 1, NULL),
(149, 'TungNHSE01647', 'Nguyễn Hoàng Tùng', 'TungNHSE01647@fpt.edu.vn', 'SE01647', 1, NULL, 'hameo', 1, NULL),
(150, 'TuanDASE01666', 'Đoàn Anh Tuấn', 'TuanDASE01666@fpt.edu.vn', 'SE01666', 1, NULL, 'hameo', 1, NULL),
(151, 'TungHMSE01589', 'Hồ Minh Tùng', 'TungHMSE01589@fpt.edu.vn', 'SE01589', 1, NULL, 'hameo', 1, NULL),
(152, 'TungLTSE01616', 'Lê Thanh Tùng', 'TungLTSE01616@fpt.edu.vn', 'SE01616', 1, NULL, 'hameo', 1, NULL),
(153, 'TungNDSE01672', 'Nguyễn Duy Tùng', 'TungNDSE01672@fpt.edu.vn', 'SE01672', 1, NULL, 'hameo', 1, NULL),
(154, 'VinhVTSE01682', 'Vũ Thế Vịnh', 'VinhVTSE01682@fpt.edu.vn', 'SE01682', 1, NULL, 'hameo', 1, NULL),
(155, 'VietPHSE01627', 'Phạm Hữu Việt', 'VietPHSE01627@fpt.edu.vn', 'SE01627', 1, NULL, 'hameo', 1, NULL),
(156, 'VuNASE01610', 'Nguyễn Anh Vũ', 'VuNASE01610@fpt.edu.vn', 'SE01610', 1, NULL, 'hameo', 1, NULL),
(157, 'YenNHSE01636', 'Nguyễn Hải Yến', 'YenNHSE01636@fpt.edu.vn', 'SE01636', 1, NULL, 'hameo', 1, NULL),
(158, 'TungNTSE01750', 'Nguyễn Thanh Tùng', 'TungNTSE01750@fpt.edu.vn', 'SE01750', 1, NULL, 'hameo', 1, NULL),
(159, 'SonLTSE01383', 'Lại Tuấn Sơn', 'SonLTSE01383@fpt.edu.vn', 'SE01383', 1, NULL, 'hameo', 1, NULL),
(160, 'TanNNSE01720', 'Nguyễn Nhật Tân', 'TanNNSE01720@fpt.edu.vn', 'SE01720', 1, NULL, 'hameo', 1, NULL),
(161, 'TienDMSE01671', 'Đào Minh Tiến', 'TienDMSE01671@fpt.edu.vn', 'SE01671', 1, NULL, 'hameo', 1, NULL),
(162, 'AnhNHSE01748', 'Nguyễn Hoài Anh', 'AnhNHSE01748@fpt.edu.vn', 'SE01748', 1, NULL, 'hameo', 1, NULL),
(163, 'CuongTDSE01728', 'Trần Duy Cường', 'CuongTDSE01728@fpt.edu.vn', 'SE01728', 1, NULL, 'hameo', 1, NULL),
(164, 'DuyNTSE01652', 'Nguyễn Trần Duy', 'DuyNTSE01652@fpt.edu.vn', 'SE01652', 1, NULL, 'hameo', 1, NULL),
(165, 'DuongTHTSE01703', 'Trần Hoàng Thùy Dương', 'DuongTHTSE01703@fpt.edu.vn', 'SE01703', 3, NULL, 'hameo', 2, NULL),
(166, 'DatTXSE01607', 'Trần Xuân Đạt', 'DatTXSE01607@fpt.edu.vn', 'SE01607', 3, NULL, 'hameo', 2, NULL),
(167, 'DinhNDSE01681', 'Nguyễn Đăng Định', 'DinhNDSE01681@fpt.edu.vn', 'SE01681', 3, NULL, 'hameo', 2, NULL),
(168, 'DucNMSE01586', 'Ngô Minh Đức', 'DucNMSE01586@fpt.edu.vn', 'SE01586', 3, NULL, 'hameo', 2, NULL),
(169, 'DucTMSE01643', 'Trần Minh Đức', 'DucTMSE01643@fpt.edu.vn', 'SE01643', 3, NULL, 'hameo', 2, NULL),
(170, 'HaNHSE01740', 'Nguyễn Hải Hà', 'HaNHSE01740@fpt.edu.vn', 'SE01740', 3, NULL, 'hameo', 2, NULL),
(171, 'HaiBTSE01758', 'Bùi Thanh Hải', 'HaiBTSE01758@fpt.edu.vn', 'SE01758', 3, NULL, 'hameo', 2, NULL),
(172, 'HaiPMSE01598', 'Phan Minh Hải', 'HaiPMSE01598@fpt.edu.vn', 'SE01598', 3, NULL, 'hameo', 2, NULL),
(173, 'HangPTSE01592', 'Phạm Thị Hằng', 'HangPTSE01592@fpt.edu.vn', 'SE01592', 3, NULL, 'hameo', 2, NULL),
(174, 'HauCMSE01723', 'Cao Minh Hậu', 'HauCMSE01723@fpt.edu.vn', 'SE01723', 3, NULL, 'hameo', 2, NULL),
(175, 'HiepNVSE01602', 'Nguyễn Văn Hiệp', 'HiepNVSE01602@fpt.edu.vn', 'SE01602', 3, NULL, 'hameo', 2, NULL),
(176, 'HoangNHSE01604', 'Ngô Huy Hoàng', 'HoangNHSE01604@fpt.edu.vn', 'SE01604', 3, NULL, 'hameo', 2, NULL),
(177, 'HuanVMSE01641', 'Vũ Mạnh Huân', 'HuanVMSE01641@fpt.edu.vn', 'SE01641', 3, NULL, 'hameo', 2, NULL),
(178, 'HueBTSE01656', 'Bùi Thị Huệ', 'HueBTSE01656@fpt.edu.vn', 'SE01656', 4, NULL, 'hameo', 2, NULL),
(179, 'HungDMSE01713', 'Đào Mạnh Hùng', 'HungDMSE01713@fpt.edu.vn', 'SE01713', 4, NULL, 'hameo', 2, NULL),
(180, 'HungHHSE01676', 'Hoàng Hữu Hùng', 'HungHHSE01676@fpt.edu.vn', 'SE01676', 4, NULL, 'hameo', 2, NULL),
(181, 'TrungNMSE01766', 'Ngô Minh Trung', 'TrungNMSE01766@fpt.edu.vn', 'SE01766', 4, NULL, 'hameo', 2, NULL),
(182, 'HuyTQSE01697', 'Trần Quốc Huy', 'HuyTQSE01697@fpt.edu.vn', 'SE01697', 4, NULL, 'hameo', 2, NULL),
(183, 'HuyenNTTSE01686', 'Nguyễn Thị Thu Huyền', 'HuyenNTTSE01686@fpt.edu.vn', 'SE01686', 4, NULL, 'hameo', 2, NULL),
(184, 'HuyenNTTSE01752', 'Nguyễn Thị Thu Huyền', 'HuyenNTTSE01752@fpt.edu.vn', 'SE01752', 4, NULL, 'hameo', 2, NULL),
(185, 'HuongNTTSE01650', 'Nguyễn Thị Thu Hương', 'HuongNTTSE01650@fpt.edu.vn', 'SE01650', 4, NULL, 'hameo', 2, NULL),
(186, 'HuuMQSE01731', 'Mai Quốc Hữu', 'HuuMQSE01731@fpt.edu.vn', 'SE01731', 4, NULL, 'hameo', 2, NULL),
(187, 'KhuNKSE01707', 'Nguyễn Khắc Khu', 'KhuNKSE01707@fpt.edu.vn', 'SE01707', 4, NULL, 'hameo', 2, NULL),
(188, 'KhueVNBA01621', 'Vũ Ngọc Khuê', 'KhueVNBA01621@fpt.edu.vn', 'BA01621', 5, NULL, 'hameo', 2, NULL),
(189, 'KienTTBA01584', 'Tạ Trung Kiên', 'KienTTBA01584@fpt.edu.vn', 'BA01584', 5, NULL, 'hameo', 2, NULL),
(190, 'KieuPDMBA01763', 'Phan Dung Mỹ Kiều', 'KieuPDMBA01763@fpt.edu.vn', 'BA01763', 5, NULL, 'hameo', 2, NULL),
(191, 'LinhCDBA01591', 'Chu Duy Linh', 'LinhCDBA01591@fpt.edu.vn', 'BA01591', 5, NULL, 'hameo', 2, NULL),
(192, 'LinhDHBA01596', 'Dương Huệ Linh', 'LinhDHBA01596@fpt.edu.vn', 'BA01596', 5, NULL, 'hameo', 2, NULL),
(193, 'LoanNTPBA01725', 'Nguyễn Thị Phương Loan', 'LoanNTPBA01725@fpt.edu.vn', 'BA01725', 5, NULL, 'hameo', 2, NULL),
(194, 'MaiBTBA01698', 'Bùi Thị Mai', 'MaiBTBA01698@fpt.edu.vn', 'BA01698', 5, NULL, 'hameo', 2, NULL),
(195, 'MyLHBA01749', 'Lưu Hằng My', 'MyLHBA01749@fpt.edu.vn', 'BA01749', 5, NULL, 'hameo', 2, NULL),
(196, 'MyNTDBA01746', 'Nguyễn Thị Diệu My', 'MyNTDBA01746@fpt.edu.vn', 'BA01746', 5, NULL, 'hameo', 2, NULL),
(197, 'NguyenNBBA01726', 'Nguyễn Bá Nguyên', 'NguyenNBBA01726@fpt.edu.vn', 'BA01726', 5, NULL, 'hameo', 2, NULL),
(198, 'PhucNVBA01715', 'Nguyễn Viết Phúc', 'PhucNVBA01715@fpt.edu.vn', 'BA01715', 5, NULL, 'hameo', 2, NULL),
(199, 'PhuongNCBA01711', 'Nguyễn Cảnh Phương', 'PhuongNCBA01711@fpt.edu.vn', 'BA01711', 5, NULL, 'hameo', 2, NULL),
(200, 'QuanLVBA01625', 'Lê Văn Quân', 'QuanLVBA01625@fpt.edu.vn', 'BA01625', 5, NULL, 'hameo', 2, NULL),
(201, 'QuanNABA01669', 'Nguyễn Anh Quân', 'QuanNABA01669@fpt.edu.vn', 'BA01669', 5, NULL, 'hameo', 2, NULL),
(202, 'QuyenNHBA01741', 'Nguyễn Hữu Quyên', 'QuyenNHBA01741@fpt.edu.vn', 'BA01741', 5, NULL, 'hameo', 2, NULL),
(203, 'ThaiBVBA01714', 'Bùi Văn Thái', 'ThaiBVBA01714@fpt.edu.vn', 'BA01714', 5, NULL, 'hameo', 2, NULL),
(204, 'ThaoDVBA01651', 'Đỗ Văn Thảo', 'ThaoDVBA01651@fpt.edu.vn', 'BA01651', 5, NULL, 'hameo', 2, NULL),
(205, 'ThaoHPBA01615', 'Hoàng Phương Thảo', 'ThaoHPBA01615@fpt.edu.vn', 'BA01615', 5, NULL, 'hameo', 2, NULL),
(206, 'TuyenNCBA01657', 'Nguyễn Công Tuyến', 'TuyenNCBA01657@fpt.edu.vn', 'BA01657', 5, NULL, 'hameo', 2, NULL),
(207, 'TuNMBA01687', 'Nguyễn Mạnh Tú', 'TuNMBA01687@fpt.edu.vn', 'BA01687', 5, NULL, 'hameo', 2, NULL),
(208, 'ThiBTBA01721', 'Bùi Tường Thi', 'ThiBTBA01721@fpt.edu.vn', 'BA01721', 5, NULL, 'hameo', 2, NULL),
(209, 'TienNMBA01655', 'Nguyễn Mạnh Tiến', 'TienNMBA01655@fpt.edu.vn', 'BA01655', 5, NULL, 'hameo', 2, NULL),
(210, 'TiepNVBA01653', 'Nguyễn Văn Tiệp', 'TiepNVBA01653@fpt.edu.vn', 'BA01653', 5, NULL, 'hameo', 2, NULL),
(211, 'ToanPKBA01630', 'Phùng Khắc Toàn', 'ToanPKBA01630@fpt.edu.vn', 'BA01630', 5, NULL, 'hameo', 2, NULL),
(212, 'TungNSBA01457', 'Nguyễn Sơn Tùng', 'TungNSBA01457@fpt.edu.vn', 'BA01457', 5, NULL, 'hameo', 3, NULL),
(213, 'TungTTBA01668', 'Thái Thanh Tùng', 'TungTTBA01668@fpt.edu.vn', 'BA01668', 5, NULL, 'hameo', 3, NULL),
(214, 'VinhTQBA01654', 'Trần Quang Vinh', 'VinhTQBA01654@fpt.edu.vn', 'BA01654', 5, NULL, 'hameo', 3, NULL),
(215, 'TriNTBA01738', 'Nguyễn Tấn Trí', 'TriNTBA01738@fpt.edu.vn', 'BA01738', 5, NULL, 'hameo', 3, NULL),
(216, 'LongNNBA01724', 'Nguyễn Ngọc Long', 'LongNNBA01724@fpt.edu.vn', 'BA01724', 5, NULL, 'hameo', 3, NULL),
(217, 'ThanhNDBA01626', 'Nguyễn Đức Thành', 'ThanhNDBA01626@fpt.edu.vn', 'BA01626', 5, NULL, 'hameo', 3, NULL),
(218, 'ChienVQBA01696', 'Vũ Quang Chiến', 'ChienVQBA01696@fpt.edu.vn', 'BA01696', 5, NULL, 'hameo', 3, NULL),
(219, 'CuongNDBA01629', 'Nguyễn Duy Cương', 'CuongNDBA01629@fpt.edu.vn', 'BA01629', 5, NULL, 'hameo', 3, NULL),
(220, 'DungVVBA01628', 'Vũ Việt Dũng', 'DungVVBA01628@fpt.edu.vn', 'BA01628', 5, NULL, 'hameo', 3, NULL),
(221, 'DaiTKBA01736', 'Trần Kiều Đại', 'DaiTKBA01736@fpt.edu.vn', 'BA01736', 5, NULL, 'hameo', 3, NULL),
(222, 'DangDTBA01700', 'Đào Thiện Đăng', 'DangDTBA01700@fpt.edu.vn', 'BA01700', 5, NULL, 'hameo', 3, NULL),
(223, 'DinhNXBA01692', 'Nguyễn Xuân Đỉnh', 'DinhNXBA01692@fpt.edu.vn', 'BA01692', 5, NULL, 'hameo', 3, NULL),
(224, 'HieuPTBA01612', 'Phạm Trung Hiếu', 'HieuPTBA01612@fpt.edu.vn', 'BA01612', 5, NULL, 'hameo', 3, NULL),
(225, 'HoaNTBA01624', 'Nguyễn Thị Hòa', 'HoaNTBA01624@fpt.edu.vn', 'BA01624', 5, NULL, 'hameo', 3, NULL),
(226, 'HuyNQBA01609', 'Nguyễn Quang Huy', 'HuyNQBA01609@fpt.edu.vn', 'BA01609', 5, NULL, 'hameo', 3, NULL),
(227, 'HuongVDBA01706', 'Vũ Diệu Hương', 'HuongVDBA01706@fpt.edu.vn', 'BA01706', 5, NULL, 'hameo', 3, NULL),
(228, 'KhanhNQBA01595', 'Nguyễn Quốc Khánh', 'KhanhNQBA01595@fpt.edu.vn', 'BA01595', 5, NULL, 'hameo', 3, NULL),
(229, 'LeNTBA01597', 'Nguyễn Thị Lê', 'LeNTBA01597@fpt.edu.vn', 'BA01597', 5, NULL, 'hameo', 3, NULL),
(230, 'LinhNMHBA01678', 'Nguyễn Mậu Hoàng Linh', 'LinhNMHBA01678@fpt.edu.vn', 'BA01678', 5, NULL, 'hameo', 3, NULL),
(231, 'LongLKBA01658', 'Lê Kinh Long', 'LongLKBA01658@fpt.edu.vn', 'BA01658', 5, NULL, 'hameo', 3, NULL),
(232, 'LuanDTBA01623', 'Đào Trọng Luân', 'LuanDTBA01623@fpt.edu.vn', 'BA01623', 5, NULL, 'hameo', 3, NULL),
(233, 'ManhNHBA01611', 'Nguyễn Hoàng Mạnh', 'ManhNHBA01611@fpt.edu.vn', 'BA01611', 5, NULL, 'hameo', 3, NULL),
(234, 'ChungNKBA01605', 'Nguyễn Khoa Chung', 'ChungNKBA01605@fpt.edu.vn', 'BA01605', 5, NULL, 'hameo', 3, NULL),
(235, 'TrucNDBA01744', 'Nguyễn Đình Trúc', 'TrucNDBA01744@fpt.edu.vn', 'BA01744', 5, NULL, 'hameo', 3, NULL),
(236, 'NamNPBA01648', 'Nguyễn Phan Nam', 'NamNPBA01648@fpt.edu.vn', 'BA01648', 5, NULL, 'hameo', 3, NULL),
(237, 'NamTHBA01699', 'Trần Hán Nam', 'NamTHBA01699@fpt.edu.vn', 'BA01699', 5, NULL, 'hameo', 3, NULL),
(238, 'NganDTTBA01661', 'Đặng Thị Thùy Ngân', 'NganDTTBA01661@fpt.edu.vn', 'BA01661', 5, NULL, 'hameo', 3, NULL),
(239, 'NghiaHHBA01760', 'Huỳnh Hiếu Nghĩa', 'NghiaHHBA01760@fpt.edu.vn', 'BA01760', 6, NULL, 'hameo', 2, NULL),
(240, 'NhungTHBA01717', 'Trần Hồng Nhung', 'NhungTHBA01717@fpt.edu.vn', 'BA01717', 6, NULL, 'hameo', 2, NULL),
(241, 'PhongVBBA01765', 'Vũ Bảo Phong', 'PhongVBBA01765@fpt.edu.vn', 'BA01765', 6, NULL, 'hameo', 2, NULL),
(242, 'PhucNHBA01764', 'Nguyễn Hoàng Phúc', 'PhucNHBA01764@fpt.edu.vn', 'BA01764', 6, NULL, 'hameo', 2, NULL),
(243, 'PhuongDQBA01638', 'Đặng Quốc Phương', 'PhuongDQBA01638@fpt.edu.vn', 'BA01638', 6, NULL, 'hameo', 2, NULL),
(244, 'QuangTDTBA01756', 'Trần Đức Trí Quang', 'QuangTDTBA01756@fpt.edu.vn', 'BA01756', 6, NULL, 'hameo', 2, NULL),
(245, 'QuanDTBA01705', 'Đỗ Trung Quân', 'QuanDTBA01705@fpt.edu.vn', 'BA01705', 6, NULL, 'hameo', 2, NULL),
(246, 'QuanNABA01663', 'Nguyễn Anh Quân', 'QuanNABA01663@fpt.edu.vn', 'BA01663', 6, NULL, 'hameo', 2, NULL),
(247, 'QuanNHBA01634', 'Nguyễn Hồng Quân', 'QuanNHBA01634@fpt.edu.vn', 'BA01634', 6, NULL, 'hameo', 2, NULL),
(248, 'ThaiBHBA01639', 'Bùi Hà Thái', 'ThaiBHBA01639@fpt.edu.vn', 'BA01639', 6, NULL, 'hameo', 2, NULL),
(249, 'ThanhDTBA01730', 'Đặng Tuấn Thành', 'ThanhDTBA01730@fpt.edu.vn', 'BA01730', 6, NULL, 'hameo', 2, NULL),
(250, 'ThanhHNBA01685', 'Hà Nhật Thành', 'ThanhHNBA01685@fpt.edu.vn', 'BA01685', 6, NULL, 'hameo', 2, NULL),
(251, 'ThanhHXBA01719', 'Hoàng Xuân Thành', 'ThanhHXBA01719@fpt.edu.vn', 'BA01719', 6, NULL, 'hameo', 2, NULL),
(252, 'DangTHBA01606', 'Trần Hải Đăng', 'DangTHBA01606@fpt.edu.vn', 'BA01606', 6, NULL, 'hameo', 2, NULL),
(253, 'DungHVBA01659', 'Hướng Việt Dũng', 'DungHVBA01659@fpt.edu.vn', 'BA01659', 6, NULL, 'hameo', 2, NULL),
(254, 'TungNTBA01635', 'Nguyễn Thanh Tùng', 'TungNTBA01635@fpt.edu.vn', 'BA01635', 6, NULL, 'hameo', 2, NULL),
(255, 'ThangDNBA01392', 'Đặng Ngọc Thắng', 'ThangDNBA01392@fpt.edu.vn', 'BA01392', 6, NULL, 'hameo', 2, NULL),
(256, 'ThuanNBBA01701', 'Nguyễn Bá Thuận', 'ThuanNBBA01701@fpt.edu.vn', 'BA01701', 6, NULL, 'hameo', 2, NULL),
(257, 'ThuyPTBBA01708', 'Phạm Thị Bích Thủy', 'ThuyPTBBA01708@fpt.edu.vn', 'BA01708', 6, NULL, 'hameo', 3, NULL),
(258, 'TienNMBA01458', 'Nguyễn Mạnh Tiến', 'TienNMBA01458@fpt.edu.vn', 'BA01458', 6, NULL, 'hameo', 3, NULL),
(259, 'TienNMBA01642', 'Nguyễn Minh Tiến', 'TienNMBA01642@fpt.edu.vn', 'BA01642', 6, NULL, 'hameo', 3, NULL),
(260, 'TrangLTBA01601', 'Lê Tố Trang', 'TrangLTBA01601@fpt.edu.vn', 'BA01601', 6, NULL, 'hameo', 3, NULL),
(261, 'TramNNMBA01762', 'Nguyễn Ngọc Minh Trâm', 'TramNNMBA01762@fpt.edu.vn', 'BA01762', 6, NULL, 'hameo', 3, NULL),
(262, 'TranDHBBA01759', 'Dương Hoàng Bảo Trân', 'TranDHBBA01759@fpt.edu.vn', 'BA01759', 6, NULL, 'hameo', 3, NULL),
(263, 'TrungNTBA01645', 'Nguyễn Thành Trung', 'TrungNTBA01645@fpt.edu.vn', 'BA01645', 6, NULL, 'hameo', 3, NULL),
(264, 'TrungPDBA01694', 'Phạm Đức Trung', 'TrungPDBA01694@fpt.edu.vn', 'BA01694', 6, NULL, 'hameo', 3, NULL),
(265, 'TuanDDBA01722', 'Đinh Duy Tuấn', 'TuanDDBA01722@fpt.edu.vn', 'BA01722', 6, NULL, 'hameo', 3, NULL),
(266, 'TuanLVBA01613', 'Lê Văn Tuấn', 'TuanLVBA01613@fpt.edu.vn', 'BA01613', 6, NULL, 'hameo', 3, NULL),
(267, 'TuanNABA01617', 'Nguyễn Anh Tuấn', 'TuanNABA01617@fpt.edu.vn', 'BA01617', 6, NULL, 'hameo', 3, NULL),
(268, 'TuanNQBA01594', 'Nguyễn Quốc Tuấn', 'TuanNQBA01594@fpt.edu.vn', 'BA01594', 6, NULL, 'hameo', 3, NULL),
(269, 'VinhNTBA01619', 'Ngô Thành Vinh', 'VinhNTBA01619@fpt.edu.vn', 'BA01619', 6, NULL, 'hameo', 3, NULL),
(270, 'TuyenHVBA01608', 'Hoàng Văn Tuyền', 'TuyenHVBA01608@fpt.edu.vn', 'BA01608', 6, NULL, 'hameo', 3, NULL),
(271, 'QuyenNDSE03812', 'Nguyễn Duy Quyền', 'QuyenNDSE03812@fpt.edu.vn', 'SE03812', 2, NULL, 'hameo', 1, NULL),
(272, 'QuynhNTTSE03847', 'Nguyễn Thị Thúy Quỳnh', 'QuynhNTTSE03847@fpt.edu.vn', 'SE03847', 2, NULL, 'hameo', 1, NULL),
(273, 'SonLDSE03865', 'Lê Đình Sơn', 'SonLDSE03865@fpt.edu.vn', 'SE03865', 2, NULL, 'hameo', 1, NULL),
(274, 'TamVMSE03709', 'Vũ Minh Tâm', 'TamVMSE03709@fpt.edu.vn', 'SE03709', 2, NULL, 'hameo', 1, NULL),
(275, 'TanBHSE04171', 'Bùi Hải Tân', 'TanBHSE04171@fpt.edu.vn', 'SE04171', 2, NULL, 'hameo', 1, NULL),
(276, 'ThaoPTPSE04227', 'Phạm Thị Phương Thảo', 'ThaoPTPSE04227@fpt.edu.vn', 'SE04227', 2, NULL, 'hameo', 1, NULL),
(277, 'ThangLVSE04159', 'Lê Việt Thắng', 'ThangLVSE04159@fpt.edu.vn', 'SE04159', 2, NULL, 'hameo', 1, NULL),
(278, 'ThangNMSE04235', 'Nguyễn Mạnh Thắng', 'ThangNMSE04235@fpt.edu.vn', 'SE04235', 2, NULL, 'hameo', 1, NULL),
(279, 'ThangTMSE04040', 'Trần Minh Thắng', 'ThangTMSE04040@fpt.edu.vn', 'SE04040', 2, NULL, 'hameo', 1, NULL),
(280, 'ThinhNTSE04073', 'Nguyễn Thái Thịnh', 'ThinhNTSE04073@fpt.edu.vn', 'SE04073', 2, NULL, 'hameo', 1, NULL),
(281, 'ThoDDSE04155', 'Dương Đình Thơ', 'ThoDDSE04155@fpt.edu.vn', 'SE04155', 2, NULL, 'hameo', 1, NULL),
(282, 'ThuongTHSE04229', 'Trần Huy Thường', 'ThuongTHSE04229@fpt.edu.vn', 'SE04229', 2, NULL, 'hameo', 1, NULL),
(283, 'TienPNSE04397', 'Phạm Ngọc Tiến', 'TienPNSE04397@fpt.edu.vn', 'SE04397', 2, NULL, 'hameo', 1, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `account`, `name`, `email`, `account_type`) VALUES
(1, 'TrangTK', 'TrangTK', 'TrangTK', 'teacher'),
(2, 'NamVX', 'NamVX', 'NamVX', 'teacher'),
(3, 'HungPD', 'HungPD', 'HungPD', 'teacher'),
(4, 'LanTV', 'LanTV', 'LanTV', 'teacher'),
(5, 'DungNT3', 'DungNT3', 'DungNT3', 'teacher'),
(6, 'LoanNTP', 'LoanNTP', 'LoanNTP', 'teacher'),
(7, 'CauPD', 'CauPD', 'CauPD', 'teacher'),
(8, 'ChiLP', 'ChiLP', 'ChiLP', 'teacher'),
(9, 'QuyenTTL', 'QuyenTTL', 'QuyenTTL', 'teacher'),
(10, 'TamNT', 'TamNT', 'TamNT', 'teacher'),
(11, 'SonHX', 'SonHX', 'SonHX', 'teacher'),
(12, 'NgaNTT', 'NgaNTT', 'NgaNTT', 'teacher'),
(13, 'DungDV', 'DungDV', 'DungDV', 'teacher'),
(14, 'NamPT', 'NamPT', 'NamPT', 'teacher'),
(15, 'HieuLD2', 'HieuLD2', 'HieuLD2', 'teacher'),
(16, 'CuongN', 'CuongN', 'CuongN', 'teacher'),
(17, 'TrungDT', 'TrungDT', 'TrungDT', 'teacher'),
(18, 'NguyenLTT', 'NguyenLTT', 'NguyenLTT', 'teacher'),
(19, 'HaiNM', 'HaiNM', 'HaiNM', 'teacher'),
(20, 'VuNH', 'VuNH', 'VuNH', 'teacher'),
(21, 'VietTK', 'VietTK', 'VietTK', 'teacher'),
(22, 'DuyDT', 'DuyDT', 'DuyDT', 'teacher'),
(23, 'ChinhVV', 'ChinhVV', 'ChinhVV', 'teacher'),
(24, 'LinhLHM', 'LinhLHM', 'LinhLHM', 'teacher'),
(25, 'MaiVTT', 'MaiVTT', 'MaiVTT', 'teacher'),
(26, 'MaiTT', 'MaiTT', 'MaiTT', 'teacher');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_course_semester`
--

DROP TABLE IF EXISTS `teacher_course_semester`;
CREATE TABLE IF NOT EXISTS `teacher_course_semester` (
`teacher_course_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_course_semester`
--

INSERT INTO `teacher_course_semester` (`teacher_course_semester_id`, `teacher_semester_id`, `course_semester_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 2, 3),
(4, 3, 4),
(5, 4, 4),
(6, 4, 5),
(7, 11, 5),
(8, 5, 6),
(9, 5, 7),
(10, 6, 7),
(11, 6, 8),
(12, 7, 9),
(13, 22, 9),
(14, 7, 10),
(15, 8, 11),
(16, 22, 11),
(17, 8, 12),
(18, 3, 13),
(19, 9, 14),
(20, 9, 15),
(21, 9, 16),
(22, 10, 17),
(23, 11, 18),
(24, 12, 19),
(25, 24, 19),
(26, 12, 20),
(27, 24, 20),
(28, 23, 20),
(29, 12, 21),
(30, 23, 21),
(31, 10, 22),
(32, 13, 23),
(33, 14, 24),
(34, 14, 25),
(35, 15, 26),
(36, 16, 27),
(37, 17, 30),
(38, 25, 30),
(39, 17, 31),
(40, 17, 32),
(41, 18, 33),
(42, 19, 34),
(43, 20, 35),
(44, 21, 36),
(45, 20, 37),
(46, 7, 37),
(47, 8, 37),
(48, 22, 38),
(49, 7, 38),
(50, 7, 39),
(51, 8, 39),
(52, 8, 40),
(53, 22, 40),
(54, 19, 41),
(55, 26, 41),
(56, 9, 42);

-- --------------------------------------------------------

--
-- Table structure for table `teacher_semester`
--

DROP TABLE IF EXISTS `teacher_semester`;
CREATE TABLE IF NOT EXISTS `teacher_semester` (
`teacher_semester_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_semester`
--

INSERT INTO `teacher_semester` (`teacher_semester_id`, `teacher_id`, `semester_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 11, 1),
(12, 12, 1),
(13, 13, 1),
(14, 14, 1),
(15, 15, 1),
(16, 16, 1),
(17, 17, 1),
(18, 18, 1),
(19, 19, 1),
(20, 20, 1),
(21, 21, 1),
(22, 22, 1),
(23, 23, 1),
(24, 24, 1),
(25, 25, 1),
(26, 26, 1);

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
  `teacher_semester_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `time_table`
--

INSERT INTO `time_table` (`time_table_id`, `class_course_semester_id`, `date`, `slot`, `room_id`, `teacher_semester_id`) VALUES
(5, 1, '2015-09-07', 1, 1, 8),
(6, 2, '2015-09-07', 2, 1, 19);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
 ADD PRIMARY KEY (`class_id`), ADD UNIQUE KEY `code` (`code`), ADD KEY `specialized_id` (`specialized_id`), ADD KEY `course_id` (`course_id`), ADD KEY `detail_specialized_id` (`detail_specialized_id`);

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
 ADD PRIMARY KEY (`program_semester_id`), ADD UNIQUE KEY `semester_id_2` (`semester_id`,`current_semester`,`specialized_id`,`detail_specialized_id`), ADD KEY `semester_id` (`semester_id`), ADD KEY `specialized_id` (`specialized_id`), ADD KEY `detail_specialized_id` (`detail_specialized_id`);

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
 ADD PRIMARY KEY (`student_id`), ADD UNIQUE KEY `account` (`account`,`email`,`student_code`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `specialized_id` (`specialized_id`), ADD KEY `detail_specialized_id` (`detail_specialized_id`);

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
 ADD PRIMARY KEY (`time_table_id`), ADD UNIQUE KEY `date` (`date`,`slot`,`class_course_semester_id`), ADD KEY `class_course_semester_id` (`class_course_semester_id`), ADD KEY `room_id` (`room_id`), ADD KEY `teacher_id` (`teacher_semester_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=161;
--
-- AUTO_INCREMENT for table `class_course_student_semester`
--
ALTER TABLE `class_course_student_semester`
MODIFY `class_course_student_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=150;
--
-- AUTO_INCREMENT for table `class_course_teacher_semester`
--
ALTER TABLE `class_course_teacher_semester`
MODIFY `class_course_teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `class_semester`
--
ALTER TABLE `class_semester`
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `program_semester`
--
ALTER TABLE `program_semester`
MODIFY `program_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `program_semester_detail`
--
ALTER TABLE `program_semester_detail`
MODIFY `program_semester_detail_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=106;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `semesters`
--
ALTER TABLE `semesters`
MODIFY `semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `specialized`
--
ALTER TABLE `specialized`
MODIFY `specialized_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=284;
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
MODIFY `student_course_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
MODIFY `teacher_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
MODIFY `teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `time_table`
--
ALTER TABLE `time_table`
MODIFY `time_table_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`),
ADD CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `classes_ibfk_3` FOREIGN KEY (`detail_specialized_id`) REFERENCES `specialized` (`specialized_id`);

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
ADD CONSTRAINT `program_semester_ibfk_1` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`),
ADD CONSTRAINT `program_semester_ibfk_2` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`),
ADD CONSTRAINT `program_semester_ibfk_3` FOREIGN KEY (`detail_specialized_id`) REFERENCES `specialized` (`specialized_id`);

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
ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`),
ADD CONSTRAINT `students_ibfk_3` FOREIGN KEY (`detail_specialized_id`) REFERENCES `specialized` (`specialized_id`);

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
ADD CONSTRAINT `time_table_ibfk_3` FOREIGN KEY (`teacher_semester_id`) REFERENCES `teacher_semester` (`teacher_semester_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
