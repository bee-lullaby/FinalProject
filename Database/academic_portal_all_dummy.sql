-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2015 at 09:34 PM
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
  `is_semester_long` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

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
(46, 21, 16, 0, 0),
(47, 21, 25, 0, 0),
(48, 21, 15, 0, 0),
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
(76, 20, 14, 0, 0),
(77, 20, 3, 0, 0),
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `semester` int(11) NOT NULL,
  `students` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

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
(46, 'ENM301', 'ENM301', 6);

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

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
(46, 46, 1, 30, NULL);

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
  `detail_specialized_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_semester`
--

INSERT INTO `program_semester` (`program_semester_id`, `semester_id`, `current_semester`, `specialized_id`, `detail_specialized_id`) VALUES
(1, 1, 1, 1, NULL),
(2, 1, 1, 2, NULL),
(3, 1, 1, 3, NULL),
(4, 1, 1, 4, NULL),
(5, 1, 2, 1, NULL),
(6, 1, 2, 2, NULL),
(7, 1, 2, 3, NULL),
(8, 1, 2, 4, NULL),
(9, 1, 2, 5, NULL),
(10, 1, 2, 6, NULL),
(11, 1, 3, 1, NULL),
(12, 1, 3, 2, NULL),
(13, 1, 3, 3, NULL),
(14, 1, 3, 5, NULL),
(15, 1, 3, 6, NULL),
(16, 1, 4, 5, 7),
(17, 1, 4, 6, 8),
(18, 1, 4, 6, 9),
(19, 1, 5, 5, 10),
(20, 1, 5, 5, 11);

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
-- Dumping data for table `semesters`
--

INSERT INTO `semesters` (`semester_id`, `code`, `name`, `semester_year`, `start_date`, `end_date`) VALUES
(1, 'FALL15', 'Fall 2015', 2015, '2015-09-07', '2015-12-31');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `account`, `name`, `email`, `account_type`) VALUES
(1, 'duchmse02551', 'Hoang Minh Duc', 'duchmse02551@fpt.edu.vn', 'staff');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
 ADD PRIMARY KEY (`time_table_id`), ADD UNIQUE KEY `date` (`date`,`slot`,`room_id`), ADD UNIQUE KEY `date_2` (`date`,`slot`,`class_course_semester_id`), ADD KEY `class_course_semester_id` (`class_course_semester_id`), ADD KEY `room_id` (`room_id`), ADD KEY `teacher_id` (`teacher_semester_id`);

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
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=131;
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
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `program_semester`
--
ALTER TABLE `program_semester`
MODIFY `program_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
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
MODIFY `specialized_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
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
ADD CONSTRAINT `time_table_ibfk_3` FOREIGN KEY (`teacher_semester_id`) REFERENCES `teacher_semester` (`teacher_semester_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
