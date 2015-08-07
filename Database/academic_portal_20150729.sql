-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 07, 2015 at 05:06 AM
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
-- Table structure for table `buildings`
--

DROP TABLE IF EXISTS `buildings`;
CREATE TABLE IF NOT EXISTS `buildings` (
`building_id` int(11) NOT NULL,
  `building_code` varchar(11) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `buildings`
--

INSERT INTO `buildings` (`building_id`, `building_code`) VALUES
(1, 'GD'),
(2, 'HB');

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
`class_id` int(11) NOT NULL,
  `code` varchar(30) NOT NULL,
  `type` varchar(11) NOT NULL,
  `specialized_id` int(11) DEFAULT NULL,
  `detail_specialized_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `batch_char` varchar(1) DEFAULT NULL,
  `number` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `code`, `type`, `specialized_id`, `detail_specialized_id`, `course_id`, `batch`, `batch_char`, `number`) VALUES
(208, 'SE1007', 'Specialized', 1, 1, NULL, 10, 'C', 7),
(209, 'SE1008', 'Specialized', 1, 1, NULL, 10, 'C', 8),
(210, 'SE1009', 'Specialized', 1, 1, NULL, 10, 'C', 9),
(211, 'SE1010', 'Specialized', 1, 1, NULL, 10, 'C', 10),
(212, 'SE1011', 'Specialized', 1, 1, NULL, 10, 'C', 11),
(213, 'SE1012', 'Specialized', 1, 1, NULL, 10, 'C', 12),
(214, 'SE1004', 'Specialized', 1, 1, NULL, 10, 'B', 4),
(215, 'SE1005', 'Specialized', 1, 1, NULL, 10, 'B', 5),
(216, 'SE1006', 'Specialized', 1, 1, NULL, 10, 'B', 6),
(217, 'SE1001', 'Specialized', 1, 1, NULL, 10, 'A', 1),
(218, 'SE1002', 'Specialized', 1, 1, NULL, 10, 'A', 2),
(219, 'SE1003', 'Specialized', 1, 1, NULL, 10, 'A', 3),
(220, 'SE0907', 'Specialized', 1, 1, NULL, 9, 'C', 7),
(221, 'SE0908', 'Specialized', 1, 1, NULL, 9, 'C', 8),
(222, 'SE0909', 'Specialized', 1, 1, NULL, 9, 'C', 9),
(223, 'SE0910', 'Specialized', 1, 1, NULL, 9, 'C', 10),
(224, 'SE0911', 'Specialized', 1, 1, NULL, 9, 'C', 11),
(225, 'SE0904', 'Specialized', 1, 1, NULL, 9, 'B', 4),
(226, 'SE0905', 'Specialized', 1, 1, NULL, 9, 'B', 5),
(227, 'SE0906', 'Specialized', 1, 1, NULL, 9, 'B', 6);

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
) ENGINE=InnoDB AUTO_INCREMENT=926 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_course_semester`
--

INSERT INTO `class_course_semester` (`class_course_semester_id`, `class_semester_id`, `course_semester_id`, `block_condition`, `is_semester_long`) VALUES
(826, 205, 131, 0, 0),
(827, 205, 115, 0, 0),
(828, 205, 221, 0, 0),
(829, 205, 137, 0, 0),
(830, 205, 182, 0, 0),
(831, 206, 131, 0, 0),
(832, 206, 115, 0, 0),
(833, 206, 221, 0, 0),
(834, 206, 137, 0, 0),
(835, 206, 182, 0, 0),
(836, 207, 131, 0, 0),
(837, 207, 115, 0, 0),
(838, 207, 221, 0, 0),
(839, 207, 137, 0, 0),
(840, 207, 182, 0, 0),
(841, 208, 131, 0, 0),
(842, 208, 115, 0, 0),
(843, 208, 221, 0, 0),
(844, 208, 137, 0, 0),
(845, 208, 182, 0, 0),
(846, 209, 131, 0, 0),
(847, 209, 115, 0, 0),
(848, 209, 221, 0, 0),
(849, 209, 137, 0, 0),
(850, 209, 182, 0, 0),
(851, 210, 131, 0, 0),
(852, 210, 115, 0, 0),
(853, 210, 221, 0, 0),
(854, 210, 137, 0, 0),
(855, 210, 182, 0, 0),
(856, 211, 138, 0, 0),
(857, 211, 139, 0, 0),
(858, 211, 133, 0, 0),
(859, 211, 222, 0, 0),
(860, 211, 155, 0, 0),
(861, 212, 138, 0, 0),
(862, 212, 139, 0, 0),
(863, 212, 133, 0, 0),
(864, 212, 222, 0, 0),
(865, 212, 155, 0, 0),
(866, 213, 138, 0, 0),
(867, 213, 139, 0, 0),
(868, 213, 133, 0, 0),
(869, 213, 222, 0, 0),
(870, 213, 155, 0, 0),
(871, 214, 214, 0, 0),
(872, 214, 136, 0, 0),
(873, 214, 127, 0, 0),
(874, 214, 141, 0, 0),
(875, 214, 156, 0, 0),
(876, 215, 214, 0, 0),
(877, 215, 136, 0, 0),
(878, 215, 127, 0, 0),
(879, 215, 141, 0, 0),
(880, 215, 156, 0, 0),
(881, 216, 214, 0, 0),
(882, 216, 136, 0, 0),
(883, 216, 127, 0, 0),
(884, 216, 141, 0, 0),
(885, 216, 156, 0, 0),
(886, 217, 133, 0, 0),
(887, 217, 124, 0, 0),
(888, 217, 127, 0, 0),
(889, 217, 125, 0, 0),
(890, 217, 211, 0, 0),
(891, 218, 133, 0, 0),
(892, 218, 124, 0, 0),
(893, 218, 127, 0, 0),
(894, 218, 125, 0, 0),
(895, 218, 211, 0, 0),
(896, 219, 133, 0, 0),
(897, 219, 124, 0, 0),
(898, 219, 127, 0, 0),
(899, 219, 125, 0, 0),
(900, 219, 211, 0, 0),
(901, 220, 133, 0, 0),
(902, 220, 124, 0, 0),
(903, 220, 127, 0, 0),
(904, 220, 125, 0, 0),
(905, 220, 211, 0, 0),
(906, 221, 133, 0, 0),
(907, 221, 124, 0, 0),
(908, 221, 127, 0, 0),
(909, 221, 125, 0, 0),
(910, 221, 211, 0, 0),
(911, 222, 230, 0, 0),
(912, 222, 229, 0, 0),
(913, 222, 201, 0, 0),
(914, 222, 219, 0, 0),
(915, 222, 140, 0, 0),
(916, 223, 230, 0, 0),
(917, 223, 229, 0, 0),
(918, 223, 201, 0, 0),
(919, 223, 219, 0, 0),
(920, 223, 140, 0, 0),
(921, 224, 230, 0, 0),
(922, 224, 229, 0, 0),
(923, 224, 201, 0, 0),
(924, 224, 219, 0, 0),
(925, 224, 140, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `class_course_semester_merge`
--

DROP TABLE IF EXISTS `class_course_semester_merge`;
CREATE TABLE IF NOT EXISTS `class_course_semester_merge` (
`class_course_semester_merge_id` int(11) NOT NULL,
  `class_course_semester_id_1` int(11) NOT NULL,
  `class_course_semester_id_2` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

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
  `semester` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_semester`
--

INSERT INTO `class_semester` (`class_semester_id`, `class_id`, `semester_id`, `semester`) VALUES
(205, 208, 1, 1),
(206, 209, 1, 1),
(207, 210, 1, 1),
(208, 211, 1, 1),
(209, 212, 1, 1),
(210, 213, 1, 1),
(211, 214, 1, 2),
(212, 215, 1, 2),
(213, 216, 1, 2),
(214, 217, 1, 3),
(215, 218, 1, 3),
(216, 219, 1, 3),
(217, 220, 1, 4),
(218, 221, 1, 4),
(219, 222, 1, 4),
(220, 223, 1, 4),
(221, 224, 1, 4),
(222, 225, 1, 5),
(223, 226, 1, 5),
(224, 227, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
`course_id` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(500) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `code`, `name`, `department_id`) VALUES
(89, 'ACC101', 'Accounting Principles', 1),
(90, 'ECO121', 'Basic Macro Economics', 1),
(91, 'ECO111', 'Basic Micro Economics', 1),
(92, 'SSC101', 'Business Communication for BA', 1),
(93, 'LAW101', 'Business Law Fundamentals', 1),
(94, 'CHN111', 'Chinese 1', 1),
(95, 'CHN122', 'Chinese 2', 1),
(96, 'CHN132', 'Chinese Elementary 3', 1),
(97, 'FIN402', 'Derivatives', 1),
(98, 'ETR401', 'Entrepreneurship', 1),
(99, 'FIN301', 'Financial Market & Institutions', 1),
(100, 'ACC305', 'Financial Statement Analysis', 1),
(101, 'MKT304', 'Integrated Marketing Communications', 1),
(102, 'MGT101', 'Introduction to Management', 1),
(103, 'MG101', 'Introduction to Management', 1),
(104, 'ITA202', 'MANAGEMENT INFORMATION SYSTEMS', 1),
(105, 'SSM201', 'Management Skills', 1),
(106, 'ACC302', 'Managerial Accouting', 1),
(107, 'MKT101', 'Marketing Principles', 1),
(108, 'MKT301', 'Marketing Research', 1),
(109, 'FIN201', 'Monetary Economics and Global Economy', 1),
(110, 'SSN301', 'NEGOTIATION', 1),
(111, 'OBE101', 'Organization Behavior', 1),
(112, 'FIN202', 'Principles of Corporate Finance', 1),
(113, 'MKT202', 'Services Marketing Management', 1),
(114, 'FIM301', 'Valuation and financial modeling', 1),
(115, 'SSG101', 'Working in Group', 1),
(116, 'BUE201', 'Business ethics', 1),
(117, 'ISC302', 'E-Commerce', 1),
(118, 'RMB301', 'Business Research Methods', 1),
(119, 'BKG302', 'Investment Project Appraisal', 1),
(120, 'MKT305', 'Marketing Strategy', 1),
(121, 'GDP101', 'Applied Graphic Design', 1),
(122, 'SSG101-BA', 'SSG101-BA', 1),
(123, 'DBD301', 'Advanced Database', 2),
(124, 'PRN292', 'C# and .Net Technology', 2),
(125, 'NWC202', 'Computer Networking', 2),
(126, 'PRJ101', 'Core Java', 2),
(127, 'CSD201', 'Data Structures and Algorithms', 2),
(128, 'CSD202', 'Data structures and algorithms (In C++)', 2),
(129, 'DBW301', 'Data Warehouse', 2),
(130, 'PRE201', 'ICT elective', 2),
(131, 'CSI101', 'Introduction to Computing', 2),
(132, 'CSI102', 'Introduction to Computing for Business', 2),
(133, 'DBI202', 'Introduction to Databases', 2),
(134, 'DBI201', 'Introduction to Databases', 2),
(135, 'PRO001', 'Learning to Program with Alice', 2),
(136, 'OSG202', 'Operating Systems', 2),
(137, 'PRF192', 'Programming fundamentals using C', 2),
(138, 'PRO201', 'Front-end Web Development', 2),
(139, 'PRO192', 'Object-Oriented Programming with Java', 2),
(140, 'PRJ321', 'Web-based Java Applications', 2),
(141, 'PRJ311', 'Desktop Java Applications', 2),
(142, 'MAO301', 'Combinatorial Optimizations', 3),
(143, 'AIL301', 'Machine Learning', 3),
(144, 'CSL201', 'Programming languages', 3),
(145, 'SCI201', 'Information Security', 3),
(146, 'CGG201', 'Computer Graphics', 3),
(147, 'ENL111', 'Academic English 1', 4),
(148, 'ENL112', 'Academic English 2', 4),
(149, 'ENI401', 'Business English - Upper-Intermediate', 4),
(150, 'ENM201', 'Business English 2 - MKLPre-Intermediate', 4),
(151, 'ENM301', 'Business English 3- MKL Intermediate', 4),
(152, 'ENM401', 'Business English 4 - MKL upper-intermediate', 4),
(153, 'VNL121', 'Vietnamese 2', 4),
(154, 'VNL111', 'Vietnamese Fundamentals 1', 4),
(155, 'LAB101', 'Lab 1', 5),
(156, 'LAB211', 'Pre-intermediate Practicing', 5),
(157, 'AIG201', 'Artificial Intelligence', 6),
(158, 'HOD101', 'Design History', 6),
(159, 'DRF201', 'Drawing - Figure drawing', 6),
(160, 'DRS101', 'Drawing - Form, Still-life', 6),
(161, 'DRP101', 'Drawing - Plaster Statue, Portrait', 6),
(162, 'GDF201', 'Fundamental of Graphic Design', 6),
(163, 'GDF101', 'Fundamental of Graphic Design', 6),
(164, 'AFA201', 'Human Anatory for Artis', 6),
(165, 'DTG101', 'Digital Tool - 2D', 6),
(166, 'TPG201', 'Typography 1', 6),
(167, 'CAD201', 'Color and Design', 6),
(168, 'DRD201', 'Drawing - Speed drawing', 6),
(169, 'CAA201', 'Communications and advertising', 6),
(170, 'PST201', 'Perspective', 6),
(171, 'DRA301', 'Draw Animation', 6),
(172, 'GDS301', 'Scenario Design & Animation', 6),
(173, 'GDG401', 'Design 2: Game Design', 6),
(174, 'DTG201', 'Digital Tool - 3D', 6),
(175, 'WMT201', 'Web and Mobility Technology', 6),
(176, 'VCP201', 'Visual Computing', 6),
(177, 'VNC101', 'Cơ sở văn hóa Việt Nam\nVietnamese Cultural', 6),
(178, 'HCM201', 'Ho Chi Minh Ideology', 7),
(179, 'MLN101', 'Principles of Marxism - Leninism', 7),
(180, 'VNR201/HCM201', 'Revolutionary Lines of CPV', 7),
(181, 'PRX301', 'Advanced XML', 8),
(182, 'CEA201', 'Computer Organization and Architecture', 8),
(183, 'DGT202', 'Digital Design', 8),
(184, 'DGT201', 'Digital Fundamentals', 8),
(185, 'DGT301', 'Digital Signal Processing', 8),
(186, 'ISC301', 'E-Commerce', 8),
(187, 'EEA211', 'Electronics 1', 8),
(188, 'EEA221', 'Electronics 2', 8),
(189, 'ESS301', 'Embedded Software Development', 8),
(190, 'ESH201', 'Embedded System Hardware', 8),
(191, 'ISM301', 'ERP Fundamentals', 8),
(192, 'ITA201', 'IT Application in Enterprise', 8),
(193, 'JFE301', 'Japanese IT Fundamentals', 8),
(194, 'PRM391', 'Mobile Programming', 8),
(195, 'PHY201', 'Modern physics', 8),
(196, 'PRC391', 'PRC391', 8),
(197, 'IAO101', 'Information Assurance Overview', 8),
(198, 'ECM391', 'Wireless Mobile Communications', 8),
(199, 'OFC311', 'Optical Communications', 8),
(200, 'JIT301', 'Information Technology Japanese', 9),
(201, 'JPS132', 'Japanese 3', 9),
(202, 'JPS142', 'Japanese 4', 9),
(203, 'JPS152', 'Japanese 5', 9),
(204, 'JPS122', 'Japanese Elementary 2', 9),
(205, 'JPS212', 'Japanese Intermediate 1', 9),
(206, 'JPS222', 'Japanese Intermediate 2', 9),
(207, 'JPS221', 'Japanese Intermediate 2', 9),
(208, 'JPD222', 'Elementary Japanese 2.1', 9),
(209, 'JPD121', 'Elementary Japanese 2.1', 9),
(210, 'JPD223', 'JPD223', 9),
(211, 'JPD131', 'Industry driven course (Japanese 3)', 9),
(212, 'JPD322', 'Elementary Japanese 3.1', 9),
(213, 'JPD323', 'Elementary Japanese 3.2', 9),
(214, 'JPD111', 'Japanese 1', 9),
(215, 'MAC102', 'Advanced Mathematics for Business', 10),
(216, 'MAD111', 'Discrete Mathematics 1', 10),
(217, 'PHY101', 'Physics', 10),
(218, 'MAC001', 'Precalculus', 10),
(219, 'MAS291', 'Probability and Applied Statistics', 10),
(220, 'MAS201', 'Statistics and data processing', 10),
(221, 'MAE101', 'Mathematics for Engineering', 10),
(222, 'MAD101', 'Discrete mathematics', 10),
(223, 'ITE301', 'Ethics in Information Technology', 11),
(224, 'ITE302', 'Ethics in Information Technology', 11),
(225, 'HCI201', 'Human Computer Interface', 11),
(226, 'SWE102', 'Introduction to Software Engineering', 11),
(227, 'SWM301', 'IT Project Management', 11),
(228, 'SWD391', 'Software Architecture and Design', 11),
(229, 'SWQ391', 'Software Quality Assurance and Testing', 11),
(230, 'SWR301', 'Software Requirements', 11),
(231, 'CES201', 'System support and Trouble shooting for Windows Server 2008', 11),
(232, 'IAA301', 'Phân tích thông tin\nIntelligence Analysics', 11),
(233, 'IAA201', 'Phân tích lỗ hổng và rủi ro\nRisk-Vulnerability Analysis', 11),
(234, 'IAP301', 'Phát triển chính sách ATTT\nPolicy Development in Information Assurance', 11),
(235, 'SRE301', 'Kỹ thuật dịch ngược \nSoftware Revert Engineering', 11),
(236, 'OSP201', 'Hệ thống nguồn mở và quản trị mạng\nOpen Source Platform and Network Administration', 11),
(237, 'IAO201', 'Information Assurance Overview', 11),
(238, 'IAP201', 'Privacy and IT Ethics', 11),
(239, 'SSC102', 'Business Communication', 12),
(240, 'SSG101-SE', 'SSG101-SE', 12),
(241, 'ÐSA101', 'Sáo Trúc', 13),
(243, 'SWP490', 'SW Capstone Project', 11),
(244, 'SWP493', 'SW Capstone Project', 11),
(245, 'HOA101', 'HOA101', 6),
(246, 'VCM201', 'VCM201', 6),
(247, 'DGT101', 'DGT101', 6);

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
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_semester`
--

INSERT INTO `course_semester` (`course_semester_id`, `course_id`, `semester_id`, `slots`, `course_condition_id`) VALUES
(89, 89, 1, 30, NULL),
(90, 90, 1, 30, NULL),
(91, 91, 1, 30, NULL),
(92, 92, 1, 30, NULL),
(93, 93, 1, 30, NULL),
(94, 94, 1, 30, NULL),
(95, 95, 1, 30, NULL),
(96, 96, 1, 30, NULL),
(97, 97, 1, 30, NULL),
(98, 98, 1, 30, NULL),
(99, 99, 1, 30, NULL),
(100, 100, 1, 30, NULL),
(101, 101, 1, 30, NULL),
(102, 102, 1, 30, NULL),
(103, 103, 1, 30, NULL),
(104, 104, 1, 30, NULL),
(105, 105, 1, 30, NULL),
(106, 106, 1, 30, NULL),
(107, 107, 1, 30, NULL),
(108, 108, 1, 30, NULL),
(109, 109, 1, 30, NULL),
(110, 110, 1, 30, NULL),
(111, 111, 1, 30, NULL),
(112, 112, 1, 30, NULL),
(113, 113, 1, 30, NULL),
(114, 114, 1, 30, NULL),
(115, 115, 1, 30, NULL),
(116, 116, 1, 30, NULL),
(117, 117, 1, 30, NULL),
(118, 118, 1, 30, NULL),
(119, 119, 1, 30, NULL),
(120, 120, 1, 30, NULL),
(121, 121, 1, 30, NULL),
(122, 122, 1, 30, NULL),
(123, 123, 1, 30, NULL),
(124, 124, 1, 30, NULL),
(125, 125, 1, 30, NULL),
(126, 126, 1, 60, NULL),
(127, 127, 1, 60, NULL),
(128, 128, 1, 60, NULL),
(129, 129, 1, 60, NULL),
(130, 130, 1, 60, NULL),
(131, 131, 1, 60, NULL),
(132, 132, 1, 30, NULL),
(133, 133, 1, 30, NULL),
(134, 134, 1, 30, NULL),
(135, 135, 1, 30, NULL),
(136, 136, 1, 30, NULL),
(137, 137, 1, 30, NULL),
(138, 138, 1, 30, NULL),
(139, 139, 1, 30, NULL),
(140, 140, 1, 30, NULL),
(141, 141, 1, 30, NULL),
(142, 142, 1, 30, NULL),
(143, 143, 1, 30, NULL),
(144, 144, 1, 30, NULL),
(145, 145, 1, 30, NULL),
(146, 146, 1, 30, NULL),
(147, 147, 1, 30, NULL),
(148, 148, 1, 30, NULL),
(149, 149, 1, 30, NULL),
(150, 150, 1, 30, NULL),
(151, 151, 1, 30, NULL),
(152, 152, 1, 30, NULL),
(153, 153, 1, 30, NULL),
(154, 154, 1, 30, NULL),
(155, 155, 1, 30, NULL),
(156, 156, 1, 30, NULL),
(157, 157, 1, 30, NULL),
(158, 158, 1, 30, NULL),
(159, 159, 1, 30, NULL),
(160, 160, 1, 30, NULL),
(161, 161, 1, 30, NULL),
(162, 162, 1, 30, NULL),
(163, 163, 1, 30, NULL),
(164, 164, 1, 30, NULL),
(165, 165, 1, 30, NULL),
(166, 166, 1, 30, NULL),
(167, 167, 1, 30, NULL),
(168, 168, 1, 30, NULL),
(169, 169, 1, 30, NULL),
(170, 170, 1, 30, NULL),
(171, 171, 1, 30, NULL),
(172, 172, 1, 30, NULL),
(173, 173, 1, 30, NULL),
(174, 174, 1, 30, NULL),
(175, 175, 1, 30, NULL),
(176, 176, 1, 30, NULL),
(177, 177, 1, 30, NULL),
(178, 178, 1, 30, NULL),
(179, 179, 1, 30, NULL),
(180, 180, 1, 30, NULL),
(181, 181, 1, 30, NULL),
(182, 182, 1, 30, NULL),
(183, 183, 1, 30, NULL),
(184, 184, 1, 30, NULL),
(185, 185, 1, 30, NULL),
(186, 186, 1, 30, NULL),
(187, 187, 1, 30, NULL),
(188, 188, 1, 30, NULL),
(189, 189, 1, 30, NULL),
(190, 190, 1, 30, NULL),
(191, 191, 1, 30, NULL),
(192, 192, 1, 30, NULL),
(193, 193, 1, 30, NULL),
(194, 194, 1, 30, NULL),
(195, 195, 1, 30, NULL),
(196, 196, 1, 30, NULL),
(197, 197, 1, 30, NULL),
(198, 198, 1, 30, NULL),
(199, 199, 1, 30, NULL),
(200, 200, 1, 30, NULL),
(201, 201, 1, 30, NULL),
(202, 202, 1, 30, NULL),
(203, 203, 1, 30, NULL),
(204, 204, 1, 30, NULL),
(205, 205, 1, 30, NULL),
(206, 206, 1, 30, NULL),
(207, 207, 1, 30, NULL),
(208, 208, 1, 30, NULL),
(209, 209, 1, 30, NULL),
(210, 210, 1, 30, NULL),
(211, 211, 1, 30, NULL),
(212, 212, 1, 30, NULL),
(213, 213, 1, 30, NULL),
(214, 214, 1, 30, NULL),
(215, 215, 1, 30, NULL),
(216, 216, 1, 30, NULL),
(217, 217, 1, 30, NULL),
(218, 218, 1, 30, NULL),
(219, 219, 1, 30, NULL),
(220, 220, 1, 30, NULL),
(221, 221, 1, 30, NULL),
(222, 222, 1, 30, NULL),
(223, 223, 1, 30, NULL),
(224, 224, 1, 30, NULL),
(225, 225, 1, 30, NULL),
(226, 226, 1, 30, NULL),
(227, 227, 1, 30, NULL),
(228, 228, 1, 30, NULL),
(229, 229, 1, 30, NULL),
(230, 230, 1, 30, NULL),
(231, 231, 1, 30, NULL),
(232, 232, 1, 30, NULL),
(233, 233, 1, 30, NULL),
(234, 234, 1, 30, NULL),
(235, 235, 1, 30, NULL),
(236, 236, 1, 30, NULL),
(237, 237, 1, 30, NULL),
(238, 238, 1, 30, NULL),
(239, 239, 1, 30, NULL),
(240, 240, 1, 30, NULL),
(241, 241, 1, 30, NULL),
(242, 243, 1, 15, NULL),
(243, 244, 1, 15, NULL),
(244, 245, 1, 30, NULL),
(245, 246, 1, 30, NULL),
(246, 247, 1, 30, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
`department_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `code`, `name`) VALUES
(1, 'BA', 'BA'),
(2, 'CF', 'CF'),
(3, 'CS', 'CS'),
(4, 'ENG', 'ENG'),
(5, 'FSOFT', 'FSOFT'),
(6, 'GD', 'GD'),
(7, 'HS', 'HS'),
(8, 'ITS', 'ITS'),
(9, 'JPN', 'JPN'),
(10, 'Math', 'Math'),
(11, 'SE', 'SE'),
(12, 'SS', 'SS'),
(13, 'TI', 'TI');

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
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_semester`
--

INSERT INTO `program_semester` (`program_semester_id`, `semester_id`, `current_semester`, `specialized_id`, `detail_specialized_id`, `batch`) VALUES
(91, 1, 1, 1, 1, 10),
(92, 1, 2, 1, 1, 10),
(93, 1, 3, 1, 1, 10),
(94, 1, 4, 1, 1, 9),
(95, 1, 5, 1, 1, 9),
(96, 1, 7, 1, 4, 8),
(97, 1, 7, 1, 5, 8),
(98, 1, 7, 1, 2, 8),
(99, 1, 7, 1, 3, 8),
(100, 1, 7, 1, 6, 8),
(101, 1, 8, 1, 4, 8),
(102, 1, 8, 1, 5, 8),
(103, 1, 8, 1, 2, 8),
(104, 1, 8, 1, 3, 8),
(105, 1, 8, 1, 6, 8),
(106, 1, 9, 1, 4, 8),
(107, 1, 9, 1, 5, 8),
(108, 1, 9, 1, 2, 8),
(109, 1, 9, 1, 3, 8),
(110, 1, 9, 1, 6, 8);

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
) ENGINE=InnoDB AUTO_INCREMENT=495 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_semester_detail`
--

INSERT INTO `program_semester_detail` (`program_semester_detail_id`, `program_semester_id`, `course_semester_id`, `is_semester_long`) VALUES
(411, 91, 131, 0),
(412, 91, 115, 0),
(413, 91, 221, 0),
(414, 91, 137, 0),
(415, 91, 182, 0),
(416, 92, 138, 0),
(417, 92, 139, 0),
(418, 92, 134, 0),
(419, 92, 222, 0),
(420, 92, 155, 0),
(421, 93, 214, 0),
(422, 93, 136, 0),
(423, 93, 127, 0),
(424, 93, 141, 0),
(425, 93, 156, 0),
(426, 94, 133, 0),
(427, 94, 124, 0),
(428, 94, 127, 0),
(429, 94, 125, 0),
(430, 94, 211, 0),
(431, 95, 230, 0),
(432, 95, 229, 0),
(433, 95, 201, 0),
(434, 95, 219, 0),
(435, 95, 140, 0),
(436, 96, 228, 0),
(437, 96, 179, 0),
(438, 96, 225, 0),
(439, 96, 89, 0),
(440, 96, 186, 0),
(441, 97, 228, 0),
(442, 97, 179, 0),
(443, 97, 225, 0),
(444, 97, 89, 0),
(445, 97, 191, 0),
(446, 98, 228, 0),
(447, 98, 179, 0),
(448, 98, 225, 0),
(449, 98, 184, 0),
(450, 98, 190, 0),
(451, 99, 228, 0),
(452, 99, 179, 0),
(453, 99, 225, 0),
(454, 99, 184, 0),
(455, 99, 190, 0),
(456, 100, 228, 0),
(457, 100, 179, 0),
(458, 100, 202, 0),
(459, 100, 203, 0),
(460, 100, 239, 0),
(461, 101, 227, 0),
(462, 101, 180, 0),
(463, 101, 239, 0),
(464, 101, 181, 0),
(465, 101, 123, 0),
(466, 102, 227, 0),
(467, 102, 180, 0),
(468, 102, 239, 0),
(469, 102, 181, 0),
(470, 102, 123, 0),
(471, 103, 227, 0),
(472, 103, 180, 0),
(473, 103, 239, 0),
(474, 103, 189, 0),
(475, 103, 185, 0),
(476, 104, 227, 0),
(477, 104, 180, 0),
(478, 104, 239, 0),
(479, 104, 189, 0),
(480, 104, 194, 0),
(481, 105, 227, 0),
(482, 105, 180, 0),
(483, 105, 205, 0),
(484, 105, 200, 0),
(485, 106, 129, 0),
(486, 106, 194, 0),
(487, 107, 129, 0),
(488, 107, 196, 0),
(489, 108, 186, 0),
(490, 108, 194, 0),
(491, 109, 186, 0),
(492, 109, 181, 0),
(493, 110, 193, 0),
(494, 110, 206, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
`room_id` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `courses` varchar(100) DEFAULT NULL,
  `capacity` int(11) NOT NULL,
  `building_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `code`, `courses`, `capacity`, `building_id`) VALUES
(1, 'P201', 'SSG101, SSC102, SSC101', 30, 1),
(2, 'P202', 'SSG101, SSC102, SSC101', 30, 1),
(3, 'P203', 'SSG101, SSC102, SSC101', 30, 1),
(4, 'P204', NULL, 30, 1),
(5, 'P205', NULL, 30, 1),
(6, 'P209', NULL, 30, 1),
(7, 'P211', NULL, 30, 1),
(8, 'P212', NULL, 30, 1),
(9, 'P215', NULL, 30, 1),
(10, 'P216', NULL, 30, 1),
(11, 'P217', NULL, 30, 1),
(12, 'P218', NULL, 30, 1),
(13, 'P219', NULL, 30, 1),
(14, 'P220', NULL, 30, 1),
(15, 'P221', NULL, 30, 1),
(16, 'P301', NULL, 30, 1),
(17, 'P302', NULL, 30, 1),
(18, 'P303', NULL, 30, 1),
(19, 'P304', NULL, 30, 1),
(20, 'P305', NULL, 30, 1),
(21, 'P306', NULL, 30, 1),
(22, 'P307', NULL, 30, 1),
(23, 'P308', NULL, 30, 1),
(24, 'P309', NULL, 30, 1),
(25, 'P310', NULL, 30, 1),
(26, 'P311', NULL, 31, 1),
(27, 'P312', NULL, 32, 1),
(28, 'P313', NULL, 30, 1),
(29, 'P314', NULL, 30, 1),
(30, 'P315', NULL, 30, 1),
(31, 'P316', NULL, 30, 1),
(32, 'P317', NULL, 30, 1),
(33, 'P318', NULL, 30, 1),
(34, 'P319', NULL, 30, 1),
(35, 'P320', NULL, 31, 1),
(36, 'P321', NULL, 0, 1),
(37, 'P411', NULL, 32, 1),
(38, 'HB/201L', NULL, 33, 1),
(39, 'HB/203L', NULL, 30, 1),
(40, 'HB/204L', NULL, 30, 1),
(41, 'HB/205L', NULL, 30, 1),
(42, 'HB/206L', NULL, 30, 1),
(43, 'HB/207L', NULL, 30, 1),
(44, 'HB/301R', NULL, 30, 1),
(45, 'HB/302R', NULL, 30, 1),
(46, 'HB/303R-305R', NULL, 70, 1),
(47, 'HB/304R', NULL, 30, 1),
(48, 'HB/307R', NULL, 30, 1),
(49, 'HB/309R', NULL, 30, 1);

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
(1, 'FA15', 'FALL 2015', 2015, '2015-09-07', '2016-01-02');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialized`
--

INSERT INTO `specialized` (`specialized_id`, `code`, `name`, `is_detail_specialized`) VALUES
(1, 'SE', 'SE', 0),
(2, 'ES1', 'ES1', 1),
(3, 'ES2', 'ES2', 1),
(4, 'IS1', 'IS1', 1),
(5, 'IS2', 'IS2', 1),
(6, 'JS', 'JS', 1),
(7, 'ISE', 'ISE', 1),
(8, 'CS', 'CS', 1),
(9, 'EC', 'EC', 1),
(10, 'IA1', 'IA1', 1),
(11, 'IA2', 'IA2', 1),
(12, 'GD', 'GD', 1),
(13, 'IA', 'IA', 1);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
`staff_id` int(11) NOT NULL,
  `account` varchar(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(50) NOT NULL,
  `account_type` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `account`, `name`, `email`, `account_type`) VALUES
(1, 'hansse02618', 'HaNS', 'hansse02618@fpt.edu.vn', 'staff'),
(2, 'duchmse02551', 'Hoàng Minh Đức', 'duchmse02551@fpt.edu.vn', 'staff');

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
) ENGINE=InnoDB AUTO_INCREMENT=3641 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(3121, 'DinhNTSE04486', 'Nguyễn Thị Dinh', 'DinhNTSE04486@fpt.edu.vn', 'SE04486', 1, 1, 'NotSet', 3, NULL),
(3122, 'HuynhDTSE03573', 'Đỗ Thiện Huỳnh', 'HuynhDTSE03573@fpt.edu.vn', 'SE03573', 1, 1, 'NotSet', 4, NULL),
(3123, 'QuyNHSE03631', 'Nguyễn Hồng Quý', 'QuyNHSE03631@fpt.edu.vn', 'SE03631', 1, 1, 'NotSet', 4, NULL),
(3124, 'TuanDASE03644', 'Đào Anh Tuấn', 'TuanDASE03644@fpt.edu.vn', 'SE03644', 1, 1, 'NotSet', 4, NULL),
(3125, 'LuongNVSE03673', 'Nguyễn Văn Lượng', 'LuongNVSE03673@fpt.edu.vn', 'SE03673', 1, 1, 'NotSet', 3, NULL),
(3126, 'DongNPSE03794', 'Nguyễn Phương Đông', 'DongNPSE03794@fpt.edu.vn', 'SE03794', 1, 1, 'NotSet', 4, NULL),
(3127, 'TrungNDSE03802', 'Nguyễn Đức Trung', 'TrungNDSE03802@fpt.edu.vn', 'SE03802', 1, 1, 'NotSet', 4, NULL),
(3128, 'NamNPSE03883', 'Nguyễn Phương Nam', 'NamNPSE03883@fpt.edu.vn', 'SE03883', 1, 1, 'NotSet', 4, NULL),
(3129, 'AnhPBSE04073', 'Phạm Bảo Anh', 'AnhPBSE04073@fpt.edu.vn', 'SE04073', 1, 1, 'NotSet', 2, NULL),
(3130, 'DangVHSE04121', 'Vũ Hải Đăng', 'DangVHSE04121@fpt.edu.vn', 'SE04121', 1, 1, 'NotSet', 2, NULL),
(3131, 'MungDVSE03914', 'Đoàn Văn Mừng', 'MungDVSE03914@fpt.edu.vn', 'SE03914', 1, 1, 'NotSet', 3, NULL),
(3132, 'DatLHSE03841', 'Lê Huy Đạt', 'DatLHSE03841@fpt.edu.vn', 'SE03841', 1, 1, 'NotSet', 4, NULL),
(3133, 'AnhCVTSE03893', 'Chu Vũ Tùng Anh', 'AnhCVTSE03893@fpt.edu.vn', 'SE03893', 1, 1, 'NotSet', 4, NULL),
(3134, 'AnhNTSE03679', 'Ngô Tuấn Anh', 'AnhNTSE03679@fpt.edu.vn', 'SE03679', 1, 1, 'NotSet', 3, NULL),
(3135, 'SonHHSE03835', 'Hồ Hồng Sơn', 'SonHHSE03835@fpt.edu.vn', 'SE03835', 1, 1, 'NotSet', 2, NULL),
(3136, 'BaoNVSE03858', 'Nguyễn Văn Bảo', 'BaoNVSE03858@fpt.edu.vn', 'SE03858', 1, 1, 'NotSet', 2, NULL),
(3137, 'HungTVSE03537', 'Trần Văn Hùng', 'HungTVSE03537@fpt.edu.vn', 'SE03537', 1, 1, 'NotSet', 3, NULL),
(3138, 'TungNDSE03588', 'Nguyễn Đức Tùng', 'TungNDSE03588@fpt.edu.vn', 'SE03588', 1, 1, 'NotSet', 2, NULL),
(3139, 'CuongTVSE03663', 'Thân Văn Cường', 'CuongTVSE03663@fpt.edu.vn', 'SE03663', 1, 1, 'NotSet', 3, NULL),
(3140, 'TuanLMSE03688', 'Lê Minh Tuấn', 'TuanLMSE03688@fpt.edu.vn', 'SE03688', 1, 1, 'NotSet', 3, NULL),
(3141, 'HaiPVSE03698', 'Phạm Viết Hải', 'HaiPVSE03698@fpt.edu.vn', 'SE03698', 1, 1, 'NotSet', 2, NULL),
(3142, 'TuanHDSE03730', 'Hoàng Đăng Tuân', 'TuanHDSE03730@fpt.edu.vn', 'SE03730', 1, 1, 'NotSet', 3, NULL),
(3143, 'TrungVHSE03751', 'Vũ Huy Trung', 'TrungVHSE03751@fpt.edu.vn', 'SE03751', 1, 1, 'NotSet', 3, NULL),
(3144, 'TrongDTSE03761', 'Đồng Tuấn Trọng', 'TrongDTSE03761@fpt.edu.vn', 'SE03761', 1, 1, 'NotSet', 2, NULL),
(3145, 'DungNDSE03796', 'Nguyễn Đình Dũng', 'DungNDSE03796@fpt.edu.vn', 'SE03796', 1, 1, 'NotSet', 2, NULL),
(3146, 'ThanhPTSE03828', 'Phạm Trọng Thành', 'ThanhPTSE03828@fpt.edu.vn', 'SE03828', 1, 1, 'NotSet', 3, NULL),
(3147, 'AnhLVSE03908', 'Lê Việt Anh', 'AnhLVSE03908@fpt.edu.vn', 'SE03908', 1, 1, 'NotSet', 3, NULL),
(3148, 'HungNTSE03913', 'Nguyễn Thế Hùng', 'HungNTSE03913@fpt.edu.vn', 'SE03913', 1, 1, 'NotSet', 2, NULL),
(3149, 'TungNTSE03924', 'Nguyễn Thanh Tùng', 'TungNTSE03924@fpt.edu.vn', 'SE03924', 1, 1, 'NotSet', 3, NULL),
(3150, 'TuyenPKSE03937', 'Phạm Kim Tuyến', 'TuyenPKSE03937@fpt.edu.vn', 'SE03937', 1, 1, 'NotSet', 3, NULL),
(3151, 'KhoaTDSE03372', 'Trần Đăng Khoa', 'KhoaTDSE03372@fpt.edu.vn', 'SE03372', 1, 1, 'NotSet', 3, NULL),
(3152, 'HieuTTSE03468', 'Trần Trung Hiếu', 'HieuTTSE03468@fpt.edu.vn', 'SE03468', 1, 1, 'NotSet', 3, NULL),
(3153, 'AnhNCSE03485', 'Nguyễn Công Anh', 'AnhNCSE03485@fpt.edu.vn', 'SE03485', 1, 1, 'NotSet', 3, NULL),
(3154, 'TuanTASE03554', 'Trần Anh Tuấn', 'TuanTASE03554@fpt.edu.vn', 'SE03554', 1, 1, 'NotSet', 3, NULL),
(3155, 'HiepNHSE03561', 'Nguyễn Hữu Hiệp', 'HiepNHSE03561@fpt.edu.vn', 'SE03561', 1, 1, 'NotSet', 3, NULL),
(3156, 'TrungNHSE03608', 'Nguyễn Hoàng Trung', 'TrungNHSE03608@fpt.edu.vn', 'SE03608', 1, 1, 'NotSet', 3, NULL),
(3157, 'HoaNKSE03609', 'Nguyễn Khánh Hòa', 'HoaNKSE03609@fpt.edu.vn', 'SE03609', 1, 1, 'NotSet', 3, NULL),
(3158, 'TruongNXSE03627', 'Nguyễn Xuân Trường', 'TruongNXSE03627@fpt.edu.vn', 'SE03627', 1, 1, 'NotSet', 3, NULL),
(3159, 'KhanhCVSE03642', 'Chu Văn Khánh', 'KhanhCVSE03642@fpt.edu.vn', 'SE03642', 1, 1, 'NotSet', 3, NULL),
(3160, 'DungPTSE03655', 'Phạm Tuấn Dũng', 'DungPTSE03655@fpt.edu.vn', 'SE03655', 1, 1, 'NotSet', 3, NULL),
(3161, 'HoangTVSE03656', 'Trần Việt Hoàng', 'HoangTVSE03656@fpt.edu.vn', 'SE03656', 1, 1, 'NotSet', 3, NULL),
(3162, 'AnhVNSE03674', 'Văn Ngọc Ánh', 'AnhVNSE03674@fpt.edu.vn', 'SE03674', 1, 1, 'NotSet', 3, NULL),
(3163, 'BangNVSE03684', 'Nguyễn Văn Bằng', 'BangNVSE03684@fpt.edu.vn', 'SE03684', 1, 1, 'NotSet', 3, NULL),
(3164, 'DuongHTNSE03716', 'Hoàng Trần Nhật Dương', 'DuongHTNSE03716@fpt.edu.vn', 'SE03716', 1, 1, 'NotSet', 3, NULL),
(3165, 'DucHASE03729', 'Hồ Anh Đức', 'DucHASE03729@fpt.edu.vn', 'SE03729', 1, 1, 'NotSet', 3, NULL),
(3166, 'HiepDHSE03731', 'Đặng Hoàng Hiệp', 'HiepDHSE03731@fpt.edu.vn', 'SE03731', 1, 1, 'NotSet', 3, NULL),
(3167, 'AnhVMSE03758', 'Vũ Minh Ánh', 'AnhVMSE03758@fpt.edu.vn', 'SE03758', 1, 1, 'NotSet', 3, NULL),
(3168, 'AnhNVSE03759', 'Nguyễn Việt Anh', 'AnhNVSE03759@fpt.edu.vn', 'SE03759', 1, 1, 'NotSet', 3, NULL),
(3169, 'HaiNNSE03772', 'Nguyễn Ngọc Hải', 'HaiNNSE03772@fpt.edu.vn', 'SE03772', 1, 1, 'NotSet', 3, NULL),
(3170, 'QuangNHSE03792', 'Nguyễn Huy Quang', 'QuangNHSE03792@fpt.edu.vn', 'SE03792', 1, 1, 'NotSet', 2, NULL),
(3171, 'HoanLTSE03813', 'Lê Trần Hoàn', 'HoanLTSE03813@fpt.edu.vn', 'SE03813', 1, 1, 'NotSet', 3, NULL),
(3172, 'TruongTVSE03815', 'Trần Văn Trường', 'TruongTVSE03815@fpt.edu.vn', 'SE03815', 1, 1, 'NotSet', 3, NULL),
(3173, 'AnhNNTSE03823', 'Nguyễn Ngọc Tuấn Anh', 'AnhNNTSE03823@fpt.edu.vn', 'SE03823', 1, 1, 'NotSet', 2, NULL),
(3174, 'TuanLASE03831', 'Lê Anh Tuấn', 'TuanLASE03831@fpt.edu.vn', 'SE03831', 1, 1, 'NotSet', 3, NULL),
(3175, 'NamLASE03836', 'Lê Anh Nam', 'NamLASE03836@fpt.edu.vn', 'SE03836', 1, 1, 'NotSet', 3, NULL),
(3176, 'SonPBSE03907', 'Phan Bảo Sơn', 'SonPBSE03907@fpt.edu.vn', 'SE03907', 1, 1, 'NotSet', 3, NULL),
(3177, 'ThaiHTSE03926', 'Hoàng Tuấn Thái', 'ThaiHTSE03926@fpt.edu.vn', 'SE03926', 1, 1, 'NotSet', 3, NULL),
(3178, 'VinhNVSE03927', 'Nguyễn Văn Vinh', 'VinhNVSE03927@fpt.edu.vn', 'SE03927', 1, 1, 'NotSet', 3, NULL),
(3179, 'CongLDSE03929', 'Lê Duy Công', 'CongLDSE03929@fpt.edu.vn', 'SE03929', 1, 1, 'NotSet', 3, NULL),
(3180, 'HanhNTSE03934', 'Nguyễn Thị Hạnh', 'HanhNTSE03934@fpt.edu.vn', 'SE03934', 1, 1, 'NotSet', 3, NULL),
(3181, 'TinhNXSE03935', 'Nguyễn Xuân Tỉnh', 'TinhNXSE03935@fpt.edu.vn', 'SE03935', 1, 1, 'NotSet', 3, NULL),
(3182, 'AnhDTSE03942', 'Đỗ Tuấn Anh', 'AnhDTSE03942@fpt.edu.vn', 'SE03942', 1, 1, 'NotSet', 3, NULL),
(3183, 'TungVKSE02372', 'Vũ Khánh Tùng', 'TungVKSE02372@fpt.edu.vn', 'SE02372', 1, 1, 'NotSet', 2, NULL),
(3184, 'TinNVSE03660', 'Nguyễn Văn Tín', 'TinNVSE03660@fpt.edu.vn', 'SE03660', 1, 1, 'NotSet', 3, NULL),
(3185, 'QuanTDSE03667', 'Trần Đăng Quân', 'QuanTDSE03667@fpt.edu.vn', 'SE03667', 1, 1, 'NotSet', 2, NULL),
(3186, 'NgocNVSE03390', 'Nguyễn Văn Ngọc', 'NgocNVSE03390@fpt.edu.vn', 'SE03390', 1, 1, 'NotSet', 3, NULL),
(3187, 'TungNDSE03526', 'Nguyễn Đức Tùng', 'TungNDSE03526@fpt.edu.vn', 'SE03526', 1, 1, 'NotSet', 3, NULL),
(3188, 'LucDVSE03546', 'Đinh Văn Lực', 'LucDVSE03546@fpt.edu.vn', 'SE03546', 1, 1, 'NotSet', 3, NULL),
(3189, 'ThinhNTSE03548', 'Nguyễn Tiến Thịnh', 'ThinhNTSE03548@fpt.edu.vn', 'SE03548', 1, 1, 'NotSet', 3, NULL),
(3190, 'LinhNTSE03586', 'Nguyễn Thế Linh', 'LinhNTSE03586@fpt.edu.vn', 'SE03586', 1, 1, 'NotSet', 3, NULL),
(3191, 'ManhDKSE03686', 'Đào Khắc Mạnh', 'ManhDKSE03686@fpt.edu.vn', 'SE03686', 1, 1, 'NotSet', 3, NULL),
(3192, 'HinhNXSE03687', 'Nguyễn Xuân Hinh', 'HinhNXSE03687@fpt.edu.vn', 'SE03687', 1, 1, 'NotSet', 3, NULL),
(3193, 'GiangNTSE03695', 'Nguyễn Trường Giang', 'GiangNTSE03695@fpt.edu.vn', 'SE03695', 1, 1, 'NotSet', 3, NULL),
(3194, 'QuanTHSE03733', 'Trần Hồng Quân', 'QuanTHSE03733@fpt.edu.vn', 'SE03733', 1, 1, 'NotSet', 3, NULL),
(3195, 'NamPTSE04116', 'Phan Thành Nam', 'NamPTSE04116@fpt.edu.vn', 'SE04116', 1, 1, 'NotSet', 2, NULL),
(3196, 'PhuongNMSE04196', 'Nguyễn Minh Phương', 'PhuongNMSE04196@fpt.edu.vn', 'SE04196', 1, 1, 'NotSet', 3, NULL),
(3197, 'HaDNSE04048', 'Đặng Nhật Hạ', 'HaDNSE04048@fpt.edu.vn', 'SE04048', 1, 1, 'NotSet', 3, NULL),
(3198, 'MinhNTSE04083', 'Ngô Thế Minh', 'MinhNTSE04083@fpt.edu.vn', 'SE04083', 1, 1, 'NotSet', 3, NULL),
(3199, 'TuanTASE04118', 'Trần Anh Tuấn', 'TuanTASE04118@fpt.edu.vn', 'SE04118', 1, 1, 'NotSet', 3, NULL),
(3200, 'DungLHSE04230', 'Lê Hoàng Dũng', 'DungLHSE04230@fpt.edu.vn', 'SE04230', 1, 1, 'NotSet', 3, NULL),
(3201, 'TungVDSE04074', 'Võ Đức Tùng', 'TungVDSE04074@fpt.edu.vn', 'SE04074', 1, 1, 'NotSet', 3, NULL),
(3202, 'TrungBDSE04114', 'Bùi Đức Trung', 'TrungBDSE04114@fpt.edu.vn', 'SE04114', 1, 1, 'NotSet', 3, NULL),
(3203, 'ManhNTSE04004', 'Nguyễn Tiến Mạnh', 'ManhNTSE04004@fpt.edu.vn', 'SE04004', 1, 1, 'NotSet', 3, NULL),
(3204, 'TuTHSE04063', 'Trần Hữu Tú', 'TuTHSE04063@fpt.edu.vn', 'SE04063', 1, 1, 'NotSet', 3, NULL),
(3205, 'ManhHDSE04394', 'Hoàng Đức Mạnh', 'ManhHDSE04394@fpt.edu.vn', 'SE04394', 1, 1, 'NotSet', 3, NULL),
(3206, 'HoangVVSE04088', 'Vương Viết Hoàng', 'HoangVVSE04088@fpt.edu.vn', 'SE04088', 1, 1, 'NotSet', 3, NULL),
(3207, 'CuongNTSE03238', 'Nguyễn Thịnh Cường', 'CuongNTSE03238@fpt.edu.vn', 'SE03238', 1, 1, 'NotSet', 4, NULL),
(3208, 'NguyenTCSE03248', 'Trần Công Nguyện', 'NguyenTCSE03248@fpt.edu.vn', 'SE03248', 1, 1, 'NotSet', 4, NULL),
(3209, 'DungPASE03281', 'Phùng Anh Dũng', 'DungPASE03281@fpt.edu.vn', 'SE03281', 1, 1, 'NotSet', 3, NULL),
(3210, 'LongNHSE03299', 'Nguyễn Hoàng Long', 'LongNHSE03299@fpt.edu.vn', 'SE03299', 1, 1, 'NotSet', 4, NULL),
(3211, 'LamNTSE03307', 'Nguyễn Tùng Lâm', 'LamNTSE03307@fpt.edu.vn', 'SE03307', 1, 1, 'NotSet', 2, NULL),
(3212, 'HoangLNSE03385', 'Lê Nhật Hoàng', 'HoangLNSE03385@fpt.edu.vn', 'SE03385', 1, 1, 'NotSet', 4, NULL),
(3213, 'LinhNTLSE04216', 'Nguyễn Thị Lý Linh', 'LinhNTLSE04216@fpt.edu.vn', 'SE04216', 1, 1, 'NotSet', 4, NULL),
(3214, 'AnhLTSE03448', 'Lê Tuấn Anh', 'AnhLTSE03448@fpt.edu.vn', 'SE03448', 1, 1, 'NotSet', 4, NULL),
(3215, 'HoangNMSE03455', 'Nguyễn Minh Hoàng', 'HoangNMSE03455@fpt.edu.vn', 'SE03455', 1, 1, 'NotSet', 4, NULL),
(3216, 'VietDXSE03456', 'Đỗ Xuân Việt', 'VietDXSE03456@fpt.edu.vn', 'SE03456', 1, 1, 'NotSet', 4, NULL),
(3217, 'QuangHDSE03459', 'Hoàng Đình Quang', 'QuangHDSE03459@fpt.edu.vn', 'SE03459', 1, 1, 'NotSet', 4, NULL),
(3218, 'PhuongNTMSE03461', 'Nguyễn Thị Mai Phương', 'PhuongNTMSE03461@fpt.edu.vn', 'SE03461', 1, 1, 'NotSet', 4, NULL),
(3219, 'HiepBHSE03463', 'Bùi Hoàng Hiệp', 'HiepBHSE03463@fpt.edu.vn', 'SE03463', 1, 1, 'NotSet', 4, NULL),
(3220, 'BachVNSE03480', 'Vũ Ngọc Bách', 'BachVNSE03480@fpt.edu.vn', 'SE03480', 1, 1, 'NotSet', 4, NULL),
(3221, 'AnhPTSE03482', 'Phạm Tuấn Anh', 'AnhPTSE03482@fpt.edu.vn', 'SE03482', 1, 1, 'NotSet', 4, NULL),
(3222, 'ChinhNDSE03483', 'Nguyễn Đức Chính', 'ChinhNDSE03483@fpt.edu.vn', 'SE03483', 1, 1, 'NotSet', 4, NULL),
(3223, 'HaNXSE03484', 'Nguyễn Xuân Hà', 'HaNXSE03484@fpt.edu.vn', 'SE03484', 1, 1, 'NotSet', 4, NULL),
(3224, 'ThanhNVSE03492', 'Nguyễn Văn Thành', 'ThanhNVSE03492@fpt.edu.vn', 'SE03492', 1, 1, 'NotSet', 4, NULL),
(3225, 'DongNASE03494', 'Nguyễn Á Đông', 'DongNASE03494@fpt.edu.vn', 'SE03494', 1, 1, 'NotSet', 4, NULL),
(3226, 'TungNHHSE03498', 'Nguyễn Hữu Hoàng Tùng', 'TungNHHSE03498@fpt.edu.vn', 'SE03498', 1, 1, 'NotSet', 4, NULL),
(3227, 'DatNTSE03522', 'Ngô Tiến Đạt', 'DatNTSE03522@fpt.edu.vn', 'SE03522', 1, 1, 'NotSet', 4, NULL),
(3228, 'ThangLDSE03529', 'Lâm Đức Thắng', 'ThangLDSE03529@fpt.edu.vn', 'SE03529', 1, 1, 'NotSet', 4, NULL),
(3229, 'ManhDDSE03539', 'Đặng Đức Mạnh', 'ManhDDSE03539@fpt.edu.vn', 'SE03539', 1, 1, 'NotSet', 4, NULL),
(3230, 'HoangVSE03540', 'Vũ Hoàng', 'HoangVSE03540@fpt.edu.vn', 'SE03540', 1, 1, 'NotSet', 4, NULL),
(3231, 'ThuongNTHSE03542', 'Nông Thị Hoài Thương', 'ThuongNTHSE03542@fpt.edu.vn', 'SE03542', 1, 1, 'NotSet', 4, NULL),
(3232, 'TruongDBSE03543', 'Dương Bá Trường', 'TruongDBSE03543@fpt.edu.vn', 'SE03543', 1, 1, 'NotSet', 4, NULL),
(3233, 'TuNTSE03545', 'Nguyễn Tuấn Tú', 'TuNTSE03545@fpt.edu.vn', 'SE03545', 1, 1, 'NotSet', 3, NULL),
(3234, 'PhatNHSE03551', 'Nguyễn Huy Phát', 'PhatNHSE03551@fpt.edu.vn', 'SE03551', 1, 1, 'NotSet', 4, NULL),
(3235, 'LongPTSE03552', 'Phạm Tiến Long', 'LongPTSE03552@fpt.edu.vn', 'SE03552', 1, 1, 'NotSet', 4, NULL),
(3236, 'SonLASE03553', 'Lê Anh Sơn', 'SonLASE03553@fpt.edu.vn', 'SE03553', 1, 1, 'NotSet', 4, NULL),
(3237, 'DaiTQSE03557', 'Trịnh Quang Đại', 'DaiTQSE03557@fpt.edu.vn', 'SE03557', 1, 1, 'NotSet', 4, NULL),
(3238, 'AnhTTSE03560', 'Trương Tuấn Anh', 'AnhTTSE03560@fpt.edu.vn', 'SE03560', 1, 1, 'NotSet', 4, NULL),
(3239, 'SonDCSE03564', 'Đặng Công Sơn', 'SonDCSE03564@fpt.edu.vn', 'SE03564', 1, 1, 'NotSet', 4, NULL),
(3240, 'DucNTSE03565', 'Nguyễn Trung Đức', 'DucNTSE03565@fpt.edu.vn', 'SE03565', 1, 1, 'NotSet', 4, NULL),
(3241, 'TuanNASE03567', 'Nguyễn Anh Tuấn', 'TuanNASE03567@fpt.edu.vn', 'SE03567', 1, 1, 'NotSet', 4, NULL),
(3242, 'ThuongTXSE03568', 'Trần Xuân Thưởng', 'ThuongTXSE03568@fpt.edu.vn', 'SE03568', 1, 1, 'NotSet', 2, NULL),
(3243, 'LinhDQSE03570', 'Đào Quang Linh', 'LinhDQSE03570@fpt.edu.vn', 'SE03570', 1, 1, 'NotSet', 4, NULL),
(3244, 'HoangNASE03574', 'Nguyễn Anh Hoàng', 'HoangNASE03574@fpt.edu.vn', 'SE03574', 1, 1, 'NotSet', 4, NULL),
(3245, 'QuanTMSE03575', 'Trần Mạnh Quân', 'QuanTMSE03575@fpt.edu.vn', 'SE03575', 1, 1, 'NotSet', 4, NULL),
(3246, 'VinhKMSE03576', 'Khuất Mạnh Vinh', 'VinhKMSE03576@fpt.edu.vn', 'SE03576', 1, 1, 'NotSet', 4, NULL),
(3247, 'HungNVSE03577', 'Nguyễn Việt Hưng', 'HungNVSE03577@fpt.edu.vn', 'SE03577', 1, 1, 'NotSet', 4, NULL),
(3248, 'AnhNPSE03583', 'Nguyễn Phan Anh', 'AnhNPSE03583@fpt.edu.vn', 'SE03583', 1, 1, 'NotSet', 4, NULL),
(3249, 'QuanTMSE03585', 'Tạ Minh Quân', 'QuanTMSE03585@fpt.edu.vn', 'SE03585', 1, 1, 'NotSet', 4, NULL),
(3250, 'TriBMSE03590', 'Bùi Mạnh Trí', 'TriBMSE03590@fpt.edu.vn', 'SE03590', 1, 1, 'NotSet', 4, NULL),
(3251, 'TamNASE03594', 'Nguyễn Anh Tâm', 'TamNASE03594@fpt.edu.vn', 'SE03594', 1, 1, 'NotSet', 4, NULL),
(3252, 'NamNVSE03599', 'Nguyễn Viết Nam', 'NamNVSE03599@fpt.edu.vn', 'SE03599', 1, 1, 'NotSet', 4, NULL),
(3253, 'ThangTCSE03602', 'Trần Công Thắng', 'ThangTCSE03602@fpt.edu.vn', 'SE03602', 1, 1, 'NotSet', 3, NULL),
(3254, 'AnhGTSE03603', 'Giang Tuấn Anh', 'AnhGTSE03603@fpt.edu.vn', 'SE03603', 1, 1, 'NotSet', 4, NULL),
(3255, 'AnhPTSE03615', 'Phạm Tuấn Anh', 'AnhPTSE03615@fpt.edu.vn', 'SE03615', 1, 1, 'NotSet', 4, NULL),
(3256, 'KhangPQSE03624', 'Phạm Quang Khang', 'KhangPQSE03624@fpt.edu.vn', 'SE03624', 1, 1, 'NotSet', 4, NULL),
(3257, 'HungDXSE03628', 'Đào Xuân Hưng', 'HungDXSE03628@fpt.edu.vn', 'SE03628', 1, 1, 'NotSet', 4, NULL),
(3258, 'VietPQSE03630', 'Phạm Quốc Việt', 'VietPQSE03630@fpt.edu.vn', 'SE03630', 1, 1, 'NotSet', 4, NULL),
(3259, 'DucNTSE03632', 'Nguyễn Trung Đức', 'DucNTSE03632@fpt.edu.vn', 'SE03632', 1, 1, 'NotSet', 4, NULL),
(3260, 'TuanNSSE03635', 'Nguyễn Sĩ Tuấn', 'TuanNSSE03635@fpt.edu.vn', 'SE03635', 1, 1, 'NotSet', 4, NULL),
(3261, 'TungNTSE03636', 'Nguyễn Thanh Tùng', 'TungNTSE03636@fpt.edu.vn', 'SE03636', 1, 1, 'NotSet', 4, NULL),
(3262, 'HocNNSE03646', 'Nguyễn Như Học', 'HocNNSE03646@fpt.edu.vn', 'SE03646', 1, 1, 'NotSet', 2, NULL),
(3263, 'KienHTSE03661', 'Hoàng Trung Kiên', 'KienHTSE03661@fpt.edu.vn', 'SE03661', 1, 1, 'NotSet', 4, NULL),
(3264, 'CuongNMSE03666', 'Nguyễn Mạnh Cường', 'CuongNMSE03666@fpt.edu.vn', 'SE03666', 1, 1, 'NotSet', 4, NULL),
(3265, 'VuNSSE03669', 'Nguyễn Sơn Vũ', 'VuNSSE03669@fpt.edu.vn', 'SE03669', 1, 1, 'NotSet', 4, NULL),
(3266, 'ThaiNGDSE03670', 'Nguyễn Gia Duy Thái', 'ThaiNGDSE03670@fpt.edu.vn', 'SE03670', 1, 1, 'NotSet', 4, NULL),
(3267, 'HungTNSE03671', 'Trần Ngọc Hùng', 'HungTNSE03671@fpt.edu.vn', 'SE03671', 1, 1, 'NotSet', 4, NULL),
(3268, 'ToanCVSE03675', 'Chu Văn Toàn', 'ToanCVSE03675@fpt.edu.vn', 'SE03675', 1, 1, 'NotSet', 4, NULL),
(3269, 'QuangTMSE03677', 'Trần Minh Quang', 'QuangTMSE03677@fpt.edu.vn', 'SE03677', 1, 1, 'NotSet', 4, NULL),
(3270, 'GiangNDSE03680', 'Nguyễn Đức Giang', 'GiangNDSE03680@fpt.edu.vn', 'SE03680', 1, 1, 'NotSet', 4, NULL),
(3271, 'CongNCSE03681', 'Nguyễn Chí Công', 'CongNCSE03681@fpt.edu.vn', 'SE03681', 1, 1, 'NotSet', 3, NULL),
(3272, 'DatBTSE03683', 'Bùi Tất Đạt', 'DatBTSE03683@fpt.edu.vn', 'SE03683', 1, 1, 'NotSet', 4, NULL),
(3273, 'LongNHSE03685', 'Nguyễn Hoàng Long', 'LongNHSE03685@fpt.edu.vn', 'SE03685', 1, 1, 'NotSet', 4, NULL),
(3274, 'PhongNHSE03697', 'Nguyễn Huy Phong', 'PhongNHSE03697@fpt.edu.vn', 'SE03697', 1, 1, 'NotSet', 4, NULL),
(3275, 'BachNKXSE03699', 'Nguyễn Khắc Xuân Bách', 'BachNKXSE03699@fpt.edu.vn', 'SE03699', 1, 1, 'NotSet', 4, NULL),
(3276, 'DucLTSE03700', 'Lưu Trung Đức', 'DucLTSE03700@fpt.edu.vn', 'SE03700', 1, 1, 'NotSet', 4, NULL),
(3277, 'AnhNNTSE03702', 'Nguyễn Nghiêm Tuấn Anh', 'AnhNNTSE03702@fpt.edu.vn', 'SE03702', 1, 1, 'NotSet', 4, NULL),
(3278, 'LongMHSE03705', 'Mai Hoàng Long', 'LongMHSE03705@fpt.edu.vn', 'SE03705', 1, 1, 'NotSet', 4, NULL),
(3279, 'HuyTQSE03706', 'Tạ Quang Huy', 'HuyTQSE03706@fpt.edu.vn', 'SE03706', 1, 1, 'NotSet', 4, NULL),
(3280, 'HaVKSE03708', 'Vương Khải Hà', 'HaVKSE03708@fpt.edu.vn', 'SE03708', 1, 1, 'NotSet', 4, NULL),
(3281, 'PhuongHASE03715', 'Hoàng Anh Phương', 'PhuongHASE03715@fpt.edu.vn', 'SE03715', 1, 1, 'NotSet', 4, NULL),
(3282, 'TuanHMSE03722', 'Hồ Minh Tuấn', 'TuanHMSE03722@fpt.edu.vn', 'SE03722', 1, 1, 'NotSet', 4, NULL),
(3283, 'QuanLHSE03726', 'Lã Hồng Quân', 'QuanLHSE03726@fpt.edu.vn', 'SE03726', 1, 1, 'NotSet', 4, NULL),
(3284, 'QuanNHSE03727', 'Nguyễn Hữu Quân', 'QuanNHSE03727@fpt.edu.vn', 'SE03727', 1, 1, 'NotSet', 3, NULL),
(3285, 'DatNTSE03734', 'Nguyễn Thành Đạt', 'DatNTSE03734@fpt.edu.vn', 'SE03734', 1, 1, 'NotSet', 4, NULL),
(3286, 'KienNTSE03736', 'Ngô Trung Kiên', 'KienNTSE03736@fpt.edu.vn', 'SE03736', 1, 1, 'NotSet', 4, NULL),
(3287, 'TuanCASE03737', 'Cao Anh Tuấn', 'TuanCASE03737@fpt.edu.vn', 'SE03737', 1, 1, 'NotSet', 4, NULL),
(3288, 'HieuDVSE03739', 'Đoàn Văn Hiếu', 'HieuDVSE03739@fpt.edu.vn', 'SE03739', 1, 1, 'NotSet', 4, NULL),
(3289, 'MinhVHSE03742', 'Vũ Hoàng Minh', 'MinhVHSE03742@fpt.edu.vn', 'SE03742', 1, 1, 'NotSet', 4, NULL),
(3290, 'TungNTSE03745', 'Nguyễn Tuấn Tùng', 'TungNTSE03745@fpt.edu.vn', 'SE03745', 1, 1, 'NotSet', 4, NULL),
(3291, 'ThanhNNSE03750', 'Nguyễn Ngọc Thanh', 'ThanhNNSE03750@fpt.edu.vn', 'SE03750', 1, 1, 'NotSet', 3, NULL),
(3292, 'KhoaPDSE03752', 'Phạm Đức Khoa', 'KhoaPDSE03752@fpt.edu.vn', 'SE03752', 1, 1, 'NotSet', 4, NULL),
(3293, 'AnhLTSE03760', 'Lê Tuấn Anh', 'AnhLTSE03760@fpt.edu.vn', 'SE03760', 1, 1, 'NotSet', 4, NULL),
(3294, 'PhuongPTMSE03773', 'Phạm Thị Minh Phương', 'PhuongPTMSE03773@fpt.edu.vn', 'SE03773', 1, 1, 'NotSet', 4, NULL),
(3295, 'SonDHSE03775', 'Đỗ Hồng Sơn', 'SonDHSE03775@fpt.edu.vn', 'SE03775', 1, 1, 'NotSet', 4, NULL),
(3296, 'QuangNDSE03776', 'Nguyễn Đăng Quang', 'QuangNDSE03776@fpt.edu.vn', 'SE03776', 1, 1, 'NotSet', 4, NULL),
(3297, 'DatDHSE03781', 'Đào Huy Đạt', 'DatDHSE03781@fpt.edu.vn', 'SE03781', 1, 1, 'NotSet', 4, NULL),
(3298, 'ThanhNCSE03786', 'Nguyễn Công Thành', 'ThanhNCSE03786@fpt.edu.vn', 'SE03786', 1, 1, 'NotSet', 4, NULL),
(3299, 'PhucLASE03793', 'Lê Anh Phúc', 'PhucLASE03793@fpt.edu.vn', 'SE03793', 1, 1, 'NotSet', 4, NULL),
(3300, 'LinhTNSE03795', 'Trần Nhật Linh', 'LinhTNSE03795@fpt.edu.vn', 'SE03795', 1, 1, 'NotSet', 4, NULL),
(3301, 'LongNBSE03804', 'Nguyễn Bảo Long', 'LongNBSE03804@fpt.edu.vn', 'SE03804', 1, 1, 'NotSet', 4, NULL),
(3302, 'CongHPASE03806', 'Hà Phan Anh Công', 'CongHPASE03806@fpt.edu.vn', 'SE03806', 1, 1, 'NotSet', 4, NULL),
(3303, 'HieuNMSE03810', 'Nguyễn Minh Hiếu', 'HieuNMSE03810@fpt.edu.vn', 'SE03810', 1, 1, 'NotSet', 4, NULL),
(3304, 'TrinhDNSE03817', 'Đỗ Ngọc Trình', 'TrinhDNSE03817@fpt.edu.vn', 'SE03817', 1, 1, 'NotSet', 4, NULL),
(3305, 'LoiTDSE03822', 'Trần Đăng Lợi', 'LoiTDSE03822@fpt.edu.vn', 'SE03822', 1, 1, 'NotSet', 4, NULL),
(3306, 'DoPKSE03824', 'Phạm Khắc Độ', 'DoPKSE03824@fpt.edu.vn', 'SE03824', 1, 1, 'NotSet', 4, NULL),
(3307, 'CuongNCSE03825', 'Nguyễn Chí Cường', 'CuongNCSE03825@fpt.edu.vn', 'SE03825', 1, 1, 'NotSet', 4, NULL),
(3308, 'AnhNDSE03826', 'Nguyễn Duy Anh', 'AnhNDSE03826@fpt.edu.vn', 'SE03826', 1, 1, 'NotSet', 3, NULL),
(3309, 'QuanNHSE03830', 'Ngô Hữu Quân', 'QuanNHSE03830@fpt.edu.vn', 'SE03830', 1, 1, 'NotSet', 4, NULL),
(3310, 'PhuocVVSE03837', 'Vũ Vạn Phước', 'PhuocVVSE03837@fpt.edu.vn', 'SE03837', 1, 1, 'NotSet', 4, NULL),
(3311, 'ManhHTSE03844', 'Hoàng Thế Mạnh', 'ManhHTSE03844@fpt.edu.vn', 'SE03844', 1, 1, 'NotSet', 4, NULL),
(3312, 'LamVTSE03846', 'Võ Thế Lâm', 'LamVTSE03846@fpt.edu.vn', 'SE03846', 1, 1, 'NotSet', 4, NULL),
(3313, 'VuongTVSE03854', 'Trần Viết Vương', 'VuongTVSE03854@fpt.edu.vn', 'SE03854', 1, 1, 'NotSet', 4, NULL),
(3314, 'HieuPVDSE03855', 'Phan Võ Đình Hiếu', 'HieuPVDSE03855@fpt.edu.vn', 'SE03855', 1, 1, 'NotSet', 4, NULL),
(3315, 'HaoHQSE03856', 'Hồ Quang Hảo', 'HaoHQSE03856@fpt.edu.vn', 'SE03856', 1, 1, 'NotSet', 4, NULL),
(3316, 'HungPPSE03862', 'Phạm Phi Hùng', 'HungPPSE03862@fpt.edu.vn', 'SE03862', 1, 1, 'NotSet', 4, NULL),
(3317, 'TuyenLASE03863', 'Lê Anh Tuyên', 'TuyenLASE03863@fpt.edu.vn', 'SE03863', 1, 1, 'NotSet', 4, NULL),
(3318, 'HieuNMSE03870', 'Nguyễn Mạnh Hiếu', 'HieuNMSE03870@fpt.edu.vn', 'SE03870', 1, 1, 'NotSet', 4, NULL),
(3319, 'TuanTQSE03873', 'Trần Quốc Tuấn', 'TuanTQSE03873@fpt.edu.vn', 'SE03873', 1, 1, 'NotSet', 4, NULL),
(3320, 'DatBVSE03875', 'Bùi Vinh Đạt', 'DatBVSE03875@fpt.edu.vn', 'SE03875', 1, 1, 'NotSet', 4, NULL),
(3321, 'TrungVTSE03880', 'Vũ Trí Trung', 'TrungVTSE03880@fpt.edu.vn', 'SE03880', 1, 1, 'NotSet', 4, NULL),
(3322, 'HaNTTSE03882', 'Nguyễn Thị Thu Hà', 'HaNTTSE03882@fpt.edu.vn', 'SE03882', 1, 1, 'NotSet', 4, NULL),
(3323, 'QuangVNSE03887', 'Vũ Ngọc Quang', 'QuangVNSE03887@fpt.edu.vn', 'SE03887', 1, 1, 'NotSet', 4, NULL),
(3324, 'ChuongHVSE03891', 'Hoàng Văn Chương', 'ChuongHVSE03891@fpt.edu.vn', 'SE03891', 1, 1, 'NotSet', 4, NULL),
(3325, 'TrungNQSE03897', 'Nguyễn Quang Trung', 'TrungNQSE03897@fpt.edu.vn', 'SE03897', 1, 1, 'NotSet', 4, NULL),
(3326, 'LinhVVSE03899', 'Vũ Văn Linh', 'LinhVVSE03899@fpt.edu.vn', 'SE03899', 1, 1, 'NotSet', 4, NULL),
(3327, 'LucNVSE03901', 'Nguyễn Văn Lực', 'LucNVSE03901@fpt.edu.vn', 'SE03901', 1, 1, 'NotSet', 4, NULL),
(3328, 'TungTTSE03909', 'Trần Thanh Tùng', 'TungTTSE03909@fpt.edu.vn', 'SE03909', 1, 1, 'NotSet', 4, NULL),
(3329, 'HoangVTSE03931', 'Vũ Trần Hoàng', 'HoangVTSE03931@fpt.edu.vn', 'SE03931', 1, 1, 'NotSet', 4, NULL),
(3330, 'KhoiDHSE03944', 'Đỗ Hồng Khôi', 'KhoiDHSE03944@fpt.edu.vn', 'SE03944', 1, 1, 'NotSet', 4, NULL),
(3331, 'SangTMSE03273', 'Trần Minh Sáng', 'SangTMSE03273@fpt.edu.vn', 'SE03273', 1, 1, 'NotSet', 4, NULL),
(3332, 'ThanhNHSE03110', 'Ngô Hữu Thành', 'ThanhNHSE03110@fpt.edu.vn', 'SE03110', 1, 1, 'NotSet', 4, NULL),
(3333, 'DuyLNSE03114', 'Lê Ngọc Duy', 'DuyLNSE03114@fpt.edu.vn', 'SE03114', 1, 1, 'NotSet', 5, NULL),
(3334, 'NhatLTSE03124', 'Lại Thế Nhật', 'NhatLTSE03124@fpt.edu.vn', 'SE03124', 1, 1, 'NotSet', 4, NULL),
(3335, 'TruongVNSE03130', 'Vũ Ngọc Trương', 'TruongVNSE03130@fpt.edu.vn', 'SE03130', 1, 1, 'NotSet', 5, NULL),
(3336, 'TuanNTSE03136', 'Nguyễn Tài Tuấn', 'TuanNTSE03136@fpt.edu.vn', 'SE03136', 1, 1, 'NotSet', 5, NULL),
(3337, 'ThaiNHSE03151', 'Nguyễn Hữu Thái', 'ThaiNHSE03151@fpt.edu.vn', 'SE03151', 1, 1, 'NotSet', 5, NULL),
(3338, 'TienNVSE03185', 'Nguyễn Văn Tiến', 'TienNVSE03185@fpt.edu.vn', 'SE03185', 1, 1, 'NotSet', 5, NULL),
(3339, 'PhongDCSE03196', 'Đỗ Cao Phong', 'PhongDCSE03196@fpt.edu.vn', 'SE03196', 1, 1, 'NotSet', 5, NULL),
(3340, 'HoangLGSE03200', 'Lê Gia Hoàng', 'HoangLGSE03200@fpt.edu.vn', 'SE03200', 1, 1, 'NotSet', 5, NULL),
(3341, 'MinhTDSE03276', 'Trần Đức Minh', 'MinhTDSE03276@fpt.edu.vn', 'SE03276', 1, 1, 'NotSet', 5, NULL),
(3342, 'DuongLVSE03290', 'Lê Văn Dương', 'DuongLVSE03290@fpt.edu.vn', 'SE03290', 1, 1, 'NotSet', 5, NULL),
(3343, 'BachLVSE03304', 'La Văn Bách', 'BachLVSE03304@fpt.edu.vn', 'SE03304', 1, 1, 'NotSet', 5, NULL),
(3344, 'TruongTHSE03310', 'Trần Hán Trường', 'TruongTHSE03310@fpt.edu.vn', 'SE03310', 1, 1, 'NotSet', 5, NULL),
(3345, 'AnhLQDSE03347', 'Lê Quý Đức Anh', 'AnhLQDSE03347@fpt.edu.vn', 'SE03347', 1, 1, 'NotSet', 5, NULL),
(3346, 'HuongLXSE03388', 'Lê Xuân Hướng', 'HuongLXSE03388@fpt.edu.vn', 'SE03388', 1, 1, 'NotSet', 5, NULL),
(3347, 'SiDQSE03425', 'Đặng Quang Sĩ', 'SiDQSE03425@fpt.edu.vn', 'SE03425', 1, 1, 'NotSet', 5, NULL),
(3348, 'ThanhPSSE03430', 'Phạm Sĩ Thanh', 'ThanhPSSE03430@fpt.edu.vn', 'SE03430', 1, 1, 'NotSet', 5, NULL),
(3349, 'AnhDTSE03435', 'Đinh Tuấn Anh', 'AnhDTSE03435@fpt.edu.vn', 'SE03435', 1, 1, 'NotSet', 5, NULL),
(3350, 'HaiPTSE02802', 'Phạm Thanh Hải', 'HaiPTSE02802@fpt.edu.vn', 'SE02802', 1, 1, 'NotSet', 4, NULL),
(3351, 'TrinhNVSE02680', 'Nguyễn Văn Trinh', 'TrinhNVSE02680@fpt.edu.vn', 'SE02680', 1, 1, 'NotSet', 5, NULL),
(3352, 'DucDVSE03458', 'Đỗ Văn Đức', 'DucDVSE03458@fpt.edu.vn', 'SE03458', 1, 1, 'NotSet', 5, NULL),
(3353, 'HuyVKSE03478', 'Vũ Kim Huy', 'HuyVKSE03478@fpt.edu.vn', 'SE03478', 1, 1, 'NotSet', 5, NULL),
(3354, 'AnhNQSE03490', 'Nguyễn Quang Anh', 'AnhNQSE03490@fpt.edu.vn', 'SE03490', 1, 1, 'NotSet', 5, NULL),
(3355, 'NguyenVBASE03496', 'Vũ Bá Anh Nguyên', 'NguyenVBASE03496@fpt.edu.vn', 'SE03496', 1, 1, 'NotSet', 5, NULL),
(3356, 'TruongTNSE03527', 'Trần Nhật Trường', 'TruongTNSE03527@fpt.edu.vn', 'SE03527', 1, 1, 'NotSet', 5, NULL),
(3357, 'YenNHSE03536', 'Nguyễn Hải Yến', 'YenNHSE03536@fpt.edu.vn', 'SE03536', 1, 1, 'NotSet', 4, NULL),
(3358, 'TuDTSE03559', 'Đinh Tuấn Tú', 'TuDTSE03559@fpt.edu.vn', 'SE03559', 1, 1, 'NotSet', 5, NULL),
(3359, 'ChauBHHSE03571', 'Bùi Hoàng Hà Châu', 'ChauBHHSE03571@fpt.edu.vn', 'SE03571', 1, 1, 'NotSet', 5, NULL),
(3360, 'MinhNLSE03580', 'Nguyễn Lê Minh', 'MinhNLSE03580@fpt.edu.vn', 'SE03580', 1, 1, 'NotSet', 5, NULL),
(3361, 'AnhNHSE03584', 'Nguyễn Hoàng Anh', 'AnhNHSE03584@fpt.edu.vn', 'SE03584', 1, 1, 'NotSet', 5, NULL),
(3362, 'TuDNSE03591', 'Đặng Ngọc Tú', 'TuDNSE03591@fpt.edu.vn', 'SE03591', 1, 1, 'NotSet', 5, NULL),
(3363, 'MinhNHSE03596', 'Nguyễn Hữu Minh', 'MinhNHSE03596@fpt.edu.vn', 'SE03596', 1, 1, 'NotSet', 5, NULL),
(3364, 'BaoPQSE03606', 'Phan Quốc Bảo', 'BaoPQSE03606@fpt.edu.vn', 'SE03606', 1, 1, 'NotSet', 5, NULL),
(3365, 'ManhNDSE03610', 'Nguyễn Đức Mạnh', 'ManhNDSE03610@fpt.edu.vn', 'SE03610', 1, 1, 'NotSet', 5, NULL),
(3366, 'AnhPDSE03611', 'Phạm Đức Anh', 'AnhPDSE03611@fpt.edu.vn', 'SE03611', 1, 1, 'NotSet', 5, NULL),
(3367, 'ToanNVSE03619', 'Ngô Văn Toàn', 'ToanNVSE03619@fpt.edu.vn', 'SE03619', 1, 1, 'NotSet', 5, NULL),
(3368, 'TungNTSE03621', 'Nguyễn Thanh Tùng', 'TungNTSE03621@fpt.edu.vn', 'SE03621', 1, 1, 'NotSet', 5, NULL),
(3369, 'HungNDSE03647', 'Nguyễn Đức Hùng', 'HungNDSE03647@fpt.edu.vn', 'SE03647', 1, 1, 'NotSet', 4, NULL),
(3370, 'AnhNQSE03650', 'Nguyễn Quốc Anh', 'AnhNQSE03650@fpt.edu.vn', 'SE03650', 1, 1, 'NotSet', 5, NULL),
(3371, 'SonDNSE03703', 'Đoàn Ngọc Sơn', 'SonDNSE03703@fpt.edu.vn', 'SE03703', 1, 1, 'NotSet', 5, NULL),
(3372, 'BachNSSE03704', 'Nguyễn Sỹ Bách', 'BachNSSE03704@fpt.edu.vn', 'SE03704', 1, 1, 'NotSet', 5, NULL),
(3373, 'HuyDNQSE03714', 'Đoàn Nguyễn Quang Huy', 'HuyDNQSE03714@fpt.edu.vn', 'SE03714', 1, 1, 'NotSet', 4, NULL),
(3374, 'HieuLTSE03719', 'Lâm Trung Hiếu', 'HieuLTSE03719@fpt.edu.vn', 'SE03719', 1, 1, 'NotSet', 4, NULL),
(3375, 'AnhLBSE03747', 'Luyện Bảo Anh', 'AnhLBSE03747@fpt.edu.vn', 'SE03747', 1, 1, 'NotSet', 5, NULL),
(3376, 'CuongVMSE03763', 'Võ Mạnh Cường', 'CuongVMSE03763@fpt.edu.vn', 'SE03763', 1, 1, 'NotSet', 4, NULL),
(3377, 'HoangPMSE03769', 'Phạm Minh Hoàng', 'HoangPMSE03769@fpt.edu.vn', 'SE03769', 1, 1, 'NotSet', 5, NULL),
(3378, 'HueNTSE03789', 'Nguyễn Thị Huế', 'HueNTSE03789@fpt.edu.vn', 'SE03789', 1, 1, 'NotSet', 5, NULL),
(3379, 'VuCLSE03800', 'Cao Lâm Vũ', 'VuCLSE03800@fpt.edu.vn', 'SE03800', 1, 1, 'NotSet', 5, NULL),
(3380, 'TuanDLSE03807', 'Đặng Lê Tuấn', 'TuanDLSE03807@fpt.edu.vn', 'SE03807', 1, 1, 'NotSet', 5, NULL),
(3381, 'CuongTDSE03827', 'Trần Đức Cường', 'CuongTDSE03827@fpt.edu.vn', 'SE03827', 1, 1, 'NotSet', 5, NULL),
(3382, 'HaiNMSE03832', 'Nguyễn Minh Hải', 'HaiNMSE03832@fpt.edu.vn', 'SE03832', 1, 1, 'NotSet', 5, NULL),
(3383, 'AnhLHSE03838', 'Lê Hoàng Anh', 'AnhLHSE03838@fpt.edu.vn', 'SE03838', 1, 1, 'NotSet', 5, NULL),
(3384, 'HaiTPTSE03859', 'Trần Phạm Triều Hải', 'HaiTPTSE03859@fpt.edu.vn', 'SE03859', 1, 1, 'NotSet', 4, NULL),
(3385, 'CanhNHTSE03860', 'Nguyễn Hữu Thanh Cảnh', 'CanhNHTSE03860@fpt.edu.vn', 'SE03860', 1, 1, 'NotSet', 5, NULL),
(3386, 'LongNBSE03877', 'Nguyễn Bảo Long', 'LongNBSE03877@fpt.edu.vn', 'SE03877', 1, 1, 'NotSet', 5, NULL),
(3387, 'DatDMSE03884', 'Đoàn Mạnh Đạt', 'DatDMSE03884@fpt.edu.vn', 'SE03884', 1, 1, 'NotSet', 5, NULL),
(3388, 'NamNVSE03888', 'Nguyễn Văn Nam', 'NamNVSE03888@fpt.edu.vn', 'SE03888', 1, 1, 'NotSet', 5, NULL),
(3389, 'LamLTSE03936', 'Lê Thanh Lâm', 'LamLTSE03936@fpt.edu.vn', 'SE03936', 1, 1, 'NotSet', 5, NULL),
(3390, 'GiangTDSE03127', 'Trần Đại Giang', 'GiangTDSE03127@fpt.edu.vn', 'SE03127', 1, 1, 'NotSet', 4, NULL),
(3391, 'SonPMSE03298', 'Phạm Minh Sơn', 'SonPMSE03298@fpt.edu.vn', 'SE03298', 1, 1, 'NotSet', 4, NULL),
(3392, 'ThanhNTSE03301', 'Nguyễn Trung Thành', 'ThanhNTSE03301@fpt.edu.vn', 'SE03301', 1, 1, 'NotSet', 5, NULL),
(3393, 'HaTTSE03361', 'Trần Thị Hà', 'HaTTSE03361@fpt.edu.vn', 'SE03361', 1, 1, 'NotSet', 5, NULL),
(3394, 'LongNVSE03428', 'Nguyễn Văn Long', 'LongNVSE03428@fpt.edu.vn', 'SE03428', 1, 1, 'NotSet', 5, NULL),
(3395, 'TrongHDSE02889', 'Hoàng Đức Trọng', 'TrongHDSE02889@fpt.edu.vn', 'SE02889', 1, 1, 'NotSet', 4, NULL),
(3396, 'TrungTCSE02306', 'Tạ Cao Trung', 'TrungTCSE02306@fpt.edu.vn', 'SE02306', 1, 1, 'NotSet', 5, NULL),
(3397, 'CuongDVHSE02662', 'Đoàn Văn Hùng Cường', 'CuongDVHSE02662@fpt.edu.vn', 'SE02662', 1, 1, 'NotSet', 5, NULL),
(3398, 'TuanLTSE02697', 'Lê Thanh Tuấn', 'TuanLTSE02697@fpt.edu.vn', 'SE02697', 1, 1, 'NotSet', 5, NULL),
(3399, 'TuTHSE02922', 'Trần Hoàng Tú', 'TuTHSE02922@fpt.edu.vn', 'SE02922', 1, 1, 'NotSet', 5, NULL),
(3400, 'ThangLTSE03240', 'Lê Tất Thắng', 'ThangLTSE03240@fpt.edu.vn', 'SE03240', 1, 1, 'NotSet', 5, NULL),
(3401, 'GiangNHSE60950', 'Nguyễn Hà Giang', 'GiangNHSE60950@fpt.edu.vn', 'SE60950', 1, 1, 'NotSet', 5, NULL),
(3402, 'SonTNSE04223', 'Trần Nguyên Sơn', 'SonTNSE04223@fpt.edu.vn', 'SE04223', 1, 1, 'NotSet', 1, NULL),
(3403, 'CuongNMSE04273', 'Nguyễn Mạnh Cường', 'CuongNMSE04273@fpt.edu.vn', 'SE04273', 1, 1, 'NotSet', 2, NULL),
(3404, 'BinhNNSE04190', 'Nguyễn Ngọc Bình', 'BinhNNSE04190@fpt.edu.vn', 'SE04190', 1, 1, 'NotSet', 2, NULL),
(3405, 'DatPTSE04332', 'Phạm Tiến Đạt', 'DatPTSE04332@fpt.edu.vn', 'SE04332', 1, 1, 'NotSet', 1, NULL),
(3406, 'HuyNGSE03985', 'Nguyễn Gia Huy', 'HuyNGSE03985@fpt.edu.vn', 'SE03985', 1, 1, 'NotSet', 1, NULL),
(3407, 'HungLTSE04132', 'Lại Thành Hưng', 'HungLTSE04132@fpt.edu.vn', 'SE04132', 1, 1, 'NotSet', 2, NULL),
(3408, 'HiepNVSE04146', 'Nguyễn Vũ Hiệp', 'HiepNVSE04146@fpt.edu.vn', 'SE04146', 1, 1, 'NotSet', 2, NULL),
(3409, 'TuyenDDSE04046', 'Dương Đình Tuyên', 'TuyenDDSE04046@fpt.edu.vn', 'SE04046', 1, 1, 'NotSet', 2, NULL),
(3410, 'HieuTXSE04374', 'Trịnh Xuân Hiếu', 'HieuTXSE04374@fpt.edu.vn', 'SE04374', 1, 1, 'NotSet', 2, NULL),
(3411, 'HauPHSE04126', 'Phạm Hữu Hậu', 'HauPHSE04126@fpt.edu.vn', 'SE04126', 1, 1, 'NotSet', 2, NULL),
(3412, 'MinhHVCSE04183', 'Hồ Viết Công Minh', 'MinhHVCSE04183@fpt.edu.vn', 'SE04183', 1, 1, 'NotSet', 1, NULL),
(3413, 'NgocBTMSE04052', 'Bùi Thị Minh Ngọc', 'NgocBTMSE04052@fpt.edu.vn', 'SE04052', 1, 1, 'NotSet', 2, NULL),
(3414, 'SonHSE04107', 'Hoàng Sơn', 'SonHSE04107@fpt.edu.vn', 'SE04107', 1, 1, 'NotSet', 2, NULL),
(3415, 'HieuDCSE04338', 'Đinh Công Hiếu', 'HieuDCSE04338@fpt.edu.vn', 'SE04338', 1, 1, 'NotSet', 2, NULL),
(3416, 'HungTMSE04400', 'Trịnh Minh Hùng', 'HungTMSE04400@fpt.edu.vn', 'SE04400', 1, 1, 'NotSet', 2, NULL),
(3417, 'QuanDLSE04215', 'Đỗ Long Quân', 'QuanDLSE04215@fpt.edu.vn', 'SE04215', 1, 1, 'NotSet', 2, NULL),
(3418, 'HungNDSE04035', 'Nguyễn Đăng Hưng', 'HungNDSE04035@fpt.edu.vn', 'SE04035', 1, 1, 'NotSet', 2, NULL),
(3419, 'SonVHSE04276', 'Vũ Hoàng Sơn', 'SonVHSE04276@fpt.edu.vn', 'SE04276', 1, 1, 'NotSet', 2, NULL),
(3420, 'NhungGTHSE04110', 'Giáp Thị Hồng Nhung', 'NhungGTHSE04110@fpt.edu.vn', 'SE04110', 1, 1, 'NotSet', 2, NULL),
(3421, 'HienNVSE04388', 'Nguyễn Văn Hiến', 'HienNVSE04388@fpt.edu.vn', 'SE04388', 1, 1, 'NotSet', 2, NULL),
(3422, 'KhanhLXSE03999', 'Lã Xuân Khánh', 'KhanhLXSE03999@fpt.edu.vn', 'SE03999', 1, 1, 'NotSet', 2, NULL),
(3423, 'CuongNVSE04047', 'Nguyễn Văn Cường', 'CuongNVSE04047@fpt.edu.vn', 'SE04047', 1, 1, 'NotSet', 2, NULL),
(3424, 'BaoPNSE04414', 'Phan Nguyên Bảo', 'BaoPNSE04414@fpt.edu.vn', 'SE04414', 1, 1, 'NotSet', 2, NULL),
(3425, 'LongNCSE03983', 'Nguyễn Công Long', 'LongNCSE03983@fpt.edu.vn', 'SE03983', 1, 1, 'NotSet', 2, NULL),
(3426, 'GiangTHSE04148', 'Trần Hoàng Giang', 'GiangTHSE04148@fpt.edu.vn', 'SE04148', 1, 1, 'NotSet', 2, NULL),
(3427, 'TrangDNPSE04416', 'Đặng Nguyễn Phương Trang', 'TrangDNPSE04416@fpt.edu.vn', 'SE04416', 1, 1, 'NotSet', 2, NULL),
(3428, 'ChauDNTSE04187', 'Dương Nguyên Thảo Châu', 'ChauDNTSE04187@fpt.edu.vn', 'SE04187', 1, 1, 'NotSet', 2, NULL),
(3429, 'DungNTKSE03989', 'Nguyễn Thị Kim Dung', 'DungNTKSE03989@fpt.edu.vn', 'SE03989', 1, 1, 'NotSet', 2, NULL),
(3430, 'HoangNLSE04303', 'Nguyễn Lê Hoàng', 'HoangNLSE04303@fpt.edu.vn', 'SE04303', 1, 1, 'NotSet', 2, NULL),
(3431, 'TungLDSE04071', 'Lê Đăng Tùng', 'TungLDSE04071@fpt.edu.vn', 'SE04071', 1, 1, 'NotSet', 2, NULL),
(3432, 'LanNHSE04186', 'Ngô Hoàng Lan', 'LanNHSE04186@fpt.edu.vn', 'SE04186', 1, 1, 'NotSet', 2, NULL),
(3433, 'DungDCSE03691', 'Đào Công Dũng', 'DungDCSE03691@fpt.edu.vn', 'SE03691', 1, 1, 'NotSet', 2, NULL),
(3434, 'KhueNDSE03287', 'Nguyễn Đoàn Khuê', 'KhueNDSE03287@fpt.edu.vn', 'SE03287', 1, 1, 'NotSet', 2, NULL),
(3435, 'QuanNHSE04103', 'Nguyễn Hà Quân', 'QuanNHSE04103@fpt.edu.vn', 'SE04103', 1, 1, 'NotSet', 1, NULL),
(3436, 'SonNVSE04038', 'Nguyễn Văn Sơn', 'SonNVSE04038@fpt.edu.vn', 'SE04038', 1, 1, 'NotSet', 2, NULL),
(3437, 'DucDHVSE04075', 'Đào Hoàng Việt Đức', 'DucDHVSE04075@fpt.edu.vn', 'SE04075', 1, 1, 'NotSet', 2, NULL),
(3438, 'ThachVNSE04094', 'Vũ Ngọc Thạch', 'ThachVNSE04094@fpt.edu.vn', 'SE04094', 1, 1, 'NotSet', 2, NULL),
(3439, 'DatNTSE04096', 'Nguyễn Trọng Đạt', 'DatNTSE04096@fpt.edu.vn', 'SE04096', 1, 1, 'NotSet', 2, NULL),
(3440, 'TrungNTSE04160', 'Nguyễn Tiến Trung', 'TrungNTSE04160@fpt.edu.vn', 'SE04160', 1, 1, 'NotSet', 2, NULL),
(3441, 'NghiaTTSE04301', 'Trần Tuấn Nghĩa', 'NghiaTTSE04301@fpt.edu.vn', 'SE04301', 1, 1, 'NotSet', 2, NULL),
(3442, 'ThinhDNSE04178', 'Đỗ Ngọc Thịnh', 'ThinhDNSE04178@fpt.edu.vn', 'SE04178', 1, 1, 'NotSet', 2, NULL),
(3443, 'LongPTSE04304', 'Phạm Thanh Long', 'LongPTSE04304@fpt.edu.vn', 'SE04304', 1, 1, 'NotSet', 2, NULL),
(3444, 'HanhLMSE04255', 'Lê Minh Hạnh', 'HanhLMSE04255@fpt.edu.vn', 'SE04255', 1, 1, 'NotSet', 2, NULL),
(3445, 'ThuyPMSE03992', 'Phạm Minh Thúy', 'ThuyPMSE03992@fpt.edu.vn', 'SE03992', 1, 1, 'NotSet', 2, NULL),
(3446, 'HoangNSE03997', 'Nguyễn Hoàng', 'HoangNSE03997@fpt.edu.vn', 'SE03997', 1, 1, 'NotSet', 2, NULL),
(3447, 'DuongNVSE04356', 'Nguyễn Văn Dương', 'DuongNVSE04356@fpt.edu.vn', 'SE04356', 1, 1, 'NotSet', 2, NULL),
(3448, 'QuanNMSE04141', 'Nguyễn Minh Quân', 'QuanNMSE04141@fpt.edu.vn', 'SE04141', 1, 1, 'NotSet', 2, NULL),
(3449, 'AnhNNSE04387', 'Nguyễn Nam Anh', 'AnhNNSE04387@fpt.edu.vn', 'SE04387', 1, 1, 'NotSet', 2, NULL),
(3450, 'TruongNXSE04362', 'Nguyễn Xuân Trường', 'TruongNXSE04362@fpt.edu.vn', 'SE04362', 1, 1, 'NotSet', 2, NULL),
(3451, 'LinhLNSE04405', 'Lê Nguyên Linh', 'LinhLNSE04405@fpt.edu.vn', 'SE04405', 1, 1, 'NotSet', 2, NULL),
(3452, 'TungNTSE04084', 'Nguyễn Tuấn Tùng', 'TungNTSE04084@fpt.edu.vn', 'SE04084', 1, 1, 'NotSet', 2, NULL),
(3453, 'HungBNSE04268', 'Bùi Nguyên Hùng', 'HungBNSE04268@fpt.edu.vn', 'SE04268', 1, 1, 'NotSet', 2, NULL),
(3454, 'DungNTTSE03851', 'Nguyễn Thị Thùy Dung', 'DungNTTSE03851@fpt.edu.vn', 'SE03851', 1, 1, 'NotSet', 2, NULL),
(3455, 'BachNGSE04422', 'Nguyễn Gia Bách', 'BachNGSE04422@fpt.edu.vn', 'SE04422', 1, 1, 'NotSet', 3, NULL),
(3456, 'NghiaDPSE04444', 'Đặng Phúc Nghĩa', 'NghiaDPSE04444@fpt.edu.vn', 'SE04444', 1, 1, 'NotSet', 2, NULL),
(3457, 'KhanhTVSE02814', 'Trần Văn Khánh', 'KhanhTVSE02814@fpt.edu.vn', 'SE02814', 1, 1, 'NotSet', 4, NULL),
(3458, 'ThangMPSE61502', 'Mai Phi Thắng', 'ThangMPSE61502@fpt.edu.vn', 'SE61502', 1, 1, 'NotSet', 3, NULL),
(3459, 'LongHDSE61702', 'Hoàng Đức Long', 'LongHDSE61702@fpt.edu.vn', 'SE61702', 1, 1, 'NotSet', 5, NULL),
(3460, 'Nhat HuyenTNSE61693', 'Trần Ngọc Nhật Huyền', 'Nhat HuyenTNSE61693@fpt.edu.vn', 'SE61693', 1, 1, 'NotSet', 4, NULL),
(3461, 'NamLHSE61565', 'Lê Hoàng Nam', 'NamLHSE61565@fpt.edu.vn', 'SE61565', 1, 1, 'NotSet', 4, NULL),
(3462, 'QuangLVSE03783', 'Lâm Việt Quang', 'QuangLVSE03783@fpt.edu.vn', 'SE03783', 1, 1, 'NotSet', 3, NULL),
(3463, 'BinhNTSE03718', 'Nguyễn Thanh Bình', 'BinhNTSE03718@fpt.edu.vn', 'SE03718', 1, 1, 'NotSet', 3, NULL),
(3464, 'LamPSE03555', 'Phan Lâm', 'LamPSE03555@fpt.edu.vn', 'SE03555', 1, 1, 'NotSet', 3, NULL),
(3465, 'TaiNH01795', 'Nguyễn Hữu Tài', 'TaiNH01795@fpt.edu.vn', '01795', 1, 1, 'NotSet', 5, NULL),
(3466, 'KhanhPDSE03812', 'Phạm Duy Khánh', 'KhanhPDSE03812@fpt.edu.vn', 'SE03812', 1, 9, 'NotSet', 1, NULL),
(3467, 'HoangVMSE04349', 'Vũ Minh Hoàng', 'HoangVMSE04349@fpt.edu.vn', 'SE04349', 1, 9, 'NotSet', 1, NULL),
(3468, 'TungDVSE04367', 'Đặng Văn Tùng', 'TungDVSE04367@fpt.edu.vn', 'SE04367', 1, 9, 'NotSet', 1, NULL),
(3469, 'MinhDNNSE04062', 'Đỗ Nguyễn Nhật Minh', 'MinhDNNSE04062@fpt.edu.vn', 'SE04062', 1, 9, 'NotSet', 1, NULL),
(3470, 'NghiaNCSE04270', 'Nguyễn Chí Nghĩa', 'NghiaNCSE04270@fpt.edu.vn', 'SE04270', 1, 9, 'NotSet', 1, NULL),
(3471, 'LucTTSE04138', 'Tô Tiến Lực', 'LucTTSE04138@fpt.edu.vn', 'SE04138', 1, 9, 'NotSet', 1, NULL),
(3472, 'HieuLTSE04326', 'Lê Trung Hiếu', 'HieuLTSE04326@fpt.edu.vn', 'SE04326', 1, 9, 'NotSet', 1, NULL),
(3473, 'ChiNNSE62028', 'Nguyễn Ngọc Chí', 'ChiNNSE62028@fpt.edu.vn', 'SE62028', 1, 9, 'NotSet', 1, NULL),
(3474, 'DucNTSE04072', 'Nguyễn Trọng Đức', 'DucNTSE04072@fpt.edu.vn', 'SE04072', 1, 9, 'NotSet', 1, NULL),
(3475, 'LamHASE04171', 'Hoàng Anh Lâm', 'LamHASE04171@fpt.edu.vn', 'SE04171', 1, 9, 'NotSet', 1, NULL),
(3476, 'AnhNDPSE04235', 'Ninh Đại Phước Anh', 'AnhNDPSE04235@fpt.edu.vn', 'SE04235', 1, 9, 'NotSet', 1, NULL),
(3477, 'HuyNLQSE04182', 'Nguyễn Lê Quang Huy', 'HuyNLQSE04182@fpt.edu.vn', 'SE04182', 1, 1, 'NotSet', 1, NULL),
(3478, 'TanNVSE03778', 'Nguyễn Văn Tân', 'TanNVSE03778@fpt.edu.vn', 'SE03778', 1, 1, 'NotSet', 1, NULL),
(3479, 'TungVSSE04113', 'Vũ Sỹ Tùng', 'TungVSSE04113@fpt.edu.vn', 'SE04113', 1, 1, 'NotSet', 1, NULL),
(3480, 'MinhNNSE04119', 'Nguyễn Ngọc Minh', 'MinhNNSE04119@fpt.edu.vn', 'SE04119', 1, 1, 'NotSet', 1, NULL),
(3481, 'SonNKSE04290', 'Nguyễn Khánh Sơn', 'SonNKSE04290@fpt.edu.vn', 'SE04290', 1, 1, 'NotSet', 1, NULL),
(3482, 'CuongDVSE03978', 'Đặng Văn Cương', 'CuongDVSE03978@fpt.edu.vn', 'SE03978', 1, 1, 'NotSet', 1, NULL),
(3483, 'NhatDTSE03990', 'Dương Tuấn Nhật', 'NhatDTSE03990@fpt.edu.vn', 'SE03990', 1, 1, 'NotSet', 1, NULL),
(3484, 'ThuanDBSE04389', 'Đỗ Bình Thuận', 'ThuanDBSE04389@fpt.edu.vn', 'SE04389', 1, 1, 'NotSet', 1, NULL),
(3485, 'TuNVSE04009', 'Nguyễn Văn Tú', 'TuNVSE04009@fpt.edu.vn', 'SE04009', 1, 1, 'NotSet', 1, NULL),
(3486, 'HuyLVSE03982', 'Lê Văn Huy', 'HuyLVSE03982@fpt.edu.vn', 'SE03982', 1, 1, 'NotSet', 1, NULL),
(3487, 'DoanCVSE04054', 'Cấn Việt Đoàn', 'DoanCVSE04054@fpt.edu.vn', 'SE04054', 1, 1, 'NotSet', 1, NULL),
(3488, 'HungHPSE04137', 'Hoàng Phi Hùng', 'HungHPSE04137@fpt.edu.vn', 'SE04137', 1, 1, 'NotSet', 1, NULL),
(3489, 'TungNTSE04120', 'Nguyễn Thanh Tùng', 'TungNTSE04120@fpt.edu.vn', 'SE04120', 1, 1, 'NotSet', 1, NULL),
(3490, 'HieuVDSE04025', 'Vũ Đức Hiếu', 'HieuVDSE04025@fpt.edu.vn', 'SE04025', 1, 1, 'NotSet', 1, NULL),
(3491, 'AnhDVSE04154', 'Dương Việt Anh', 'AnhDVSE04154@fpt.edu.vn', 'SE04154', 1, 1, 'NotSet', 1, NULL),
(3492, 'DatPTSE04037', 'Phạm Tiến Đạt', 'DatPTSE04037@fpt.edu.vn', 'SE04037', 1, 1, 'NotSet', 1, NULL),
(3493, 'NamLTSE04085', 'Lã Trung Nam', 'NamLTSE04085@fpt.edu.vn', 'SE04085', 1, 1, 'NotSet', 1, NULL),
(3494, 'DatLTSE04086', 'Lê Tuấn Đạt', 'DatLTSE04086@fpt.edu.vn', 'SE04086', 1, 1, 'NotSet', 1, NULL),
(3495, 'ThanhPKSE04131', 'Phùng Khắc Thành', 'ThanhPKSE04131@fpt.edu.vn', 'SE04131', 1, 1, 'NotSet', 1, NULL),
(3496, 'LongNHSE04143', 'Nguyễn Hoàng Long', 'LongNHSE04143@fpt.edu.vn', 'SE04143', 1, 1, 'NotSet', 1, NULL),
(3497, 'KyBHSE04210', 'Bùi Hoàng Kỳ', 'KyBHSE04210@fpt.edu.vn', 'SE04210', 1, 1, 'NotSet', 1, NULL),
(3498, 'HaiTNSE04386', 'Triệu Nam Hải', 'HaiTNSE04386@fpt.edu.vn', 'SE04386', 1, 1, 'NotSet', 1, NULL),
(3499, 'KhoiNDSE04028', 'Nguyễn Đăng Khôi', 'KhoiNDSE04028@fpt.edu.vn', 'SE04028', 1, 1, 'NotSet', 1, NULL),
(3500, 'BachNVSE04221', 'Nguyễn Viết Bách', 'BachNVSE04221@fpt.edu.vn', 'SE04221', 1, 1, 'NotSet', 1, NULL),
(3501, 'BinhPTSE04222', 'Phùng Thanh Bình', 'BinhPTSE04222@fpt.edu.vn', 'SE04222', 1, 1, 'NotSet', 1, NULL),
(3502, 'QuangTMSE04402', 'Trần Minh Quang', 'QuangTMSE04402@fpt.edu.vn', 'SE04402', 1, 1, 'NotSet', 1, NULL),
(3503, 'HaoDHSE04064', 'Đặng Hòa Hảo', 'HaoDHSE04064@fpt.edu.vn', 'SE04064', 1, 1, 'NotSet', 1, NULL),
(3504, 'TuongDVSE04317', 'Đỗ Vĩnh Tưởng', 'TuongDVSE04317@fpt.edu.vn', 'SE04317', 1, 1, 'NotSet', 1, NULL),
(3505, 'QuynhDTTSE04133', 'Đỗ Thị Thu Quỳnh', 'QuynhDTTSE04133@fpt.edu.vn', 'SE04133', 1, 1, 'NotSet', 1, NULL),
(3506, 'KhoaTNSE04180', 'Trần Nguyên Khoa', 'KhoaTNSE04180@fpt.edu.vn', 'SE04180', 1, 1, 'NotSet', 1, NULL),
(3507, 'LamNPTSE04049', 'Nghiêm Phú Tùng Lâm', 'LamNPTSE04049@fpt.edu.vn', 'SE04049', 1, 1, 'NotSet', 1, NULL),
(3508, 'DatNQSE04108', 'Nguyễn Quốc Đạt', 'DatNQSE04108@fpt.edu.vn', 'SE04108', 1, 1, 'NotSet', 1, NULL),
(3509, 'BinhNVSE04381', 'Nguyễn Vũ Bình', 'BinhNVSE04381@fpt.edu.vn', 'SE04381', 1, 1, 'NotSet', 1, NULL),
(3510, 'HaHPSE04181', 'Huỳnh Phương Hà', 'HaHPSE04181@fpt.edu.vn', 'SE04181', 1, 1, 'NotSet', 1, NULL),
(3511, 'DungNQSE04355', 'Nguyễn Quốc Dũng', 'DungNQSE04355@fpt.edu.vn', 'SE04355', 1, 1, 'NotSet', 1, NULL),
(3512, 'TrungNSSE04051', 'Nguyễn Sỹ Trung', 'TrungNSSE04051@fpt.edu.vn', 'SE04051', 1, 1, 'NotSet', 1, NULL),
(3513, 'HieuNTSE04238', 'Nguyễn Trung Hiếu', 'HieuNTSE04238@fpt.edu.vn', 'SE04238', 1, 1, 'NotSet', 1, NULL),
(3514, 'KienNDSE04193', 'Nguyễn Đức Kiên', 'KienNDSE04193@fpt.edu.vn', 'SE04193', 1, 1, 'NotSet', 1, NULL),
(3515, 'DuyLCSE04194', 'Lưu Cao Duy', 'DuyLCSE04194@fpt.edu.vn', 'SE04194', 1, 1, 'NotSet', 1, NULL),
(3516, 'TuanLVSE04092', 'Lê Văn Tuấn', 'TuanLVSE04092@fpt.edu.vn', 'SE04092', 1, 1, 'NotSet', 1, NULL),
(3517, 'LanNTMSE04069', 'Nguyễn Thị Mai Lan', 'LanNTMSE04069@fpt.edu.vn', 'SE04069', 1, 1, 'NotSet', 1, NULL),
(3518, 'VuNHSE04013', 'Nguyễn Hải Vũ', 'VuNHSE04013@fpt.edu.vn', 'SE04013', 1, 1, 'NotSet', 1, NULL),
(3519, 'KienNKSE04150', 'Nguyễn Khắc Kiên', 'KienNKSE04150@fpt.edu.vn', 'SE04150', 1, 1, 'NotSet', 1, NULL),
(3520, 'DucPMSE04093', 'Phan Minh Đức', 'DucPMSE04093@fpt.edu.vn', 'SE04093', 1, 1, 'NotSet', 1, NULL),
(3521, 'TienPMSE03947', 'Phạm Minh Tiến', 'TienPMSE03947@fpt.edu.vn', 'SE03947', 1, 1, 'NotSet', 1, NULL),
(3522, 'LamNNSE04185', 'Ngô Ngọc Lâm', 'LamNNSE04185@fpt.edu.vn', 'SE04185', 1, 1, 'NotSet', 1, NULL),
(3523, 'HungVHSE04429', 'Vũ Hoàng Hưng', 'HungVHSE04429@fpt.edu.vn', 'SE04429', 1, 1, 'NotSet', 1, NULL),
(3524, 'ThangNDSE04441', 'Nguyễn Đại Thắng', 'ThangNDSE04441@fpt.edu.vn', 'SE04441', 1, 1, 'NotSet', 1, NULL),
(3525, 'TungNDSE03988', 'Nguyễn Đông Tùng', 'TungNDSE03988@fpt.edu.vn', 'SE03988', 1, 1, 'NotSet', 1, NULL),
(3526, 'AnhHNSE61724', 'Huỳnh Nhật Anh', 'AnhHNSE61724@fpt.edu.vn', 'SE61724', 1, 13, 'NotSet', 1, NULL),
(3527, 'AnhVQSE04377', 'Võ Quốc Anh', 'AnhVQSE04377@fpt.edu.vn', 'SE04377', 1, 13, 'NotSet', 1, NULL),
(3528, 'LinhNXSE04363', 'Nguyễn Xuân Linh', 'LinhNXSE04363@fpt.edu.vn', 'SE04363', 1, 13, 'NotSet', 1, NULL),
(3529, 'AnhDDSE04067', 'Đỗ Đức Anh', 'AnhDDSE04067@fpt.edu.vn', 'SE04067', 1, 13, 'NotSet', 1, NULL),
(3530, 'DatNTSE04286', 'Nguyễn Tiến Đạt', 'DatNTSE04286@fpt.edu.vn', 'SE04286', 1, 13, 'NotSet', 1, NULL),
(3531, 'VinhNTSE04274', 'Nguyễn Thành Vinh', 'VinhNTSE04274@fpt.edu.vn', 'SE04274', 1, 13, 'NotSet', 1, NULL),
(3532, 'HoangNMSE03998', 'Nguyễn Minh Hoàng', 'HoangNMSE03998@fpt.edu.vn', 'SE03998', 1, 13, 'NotSet', 1, NULL),
(3533, 'AnhNTSE04003', 'Nguyễn Tuấn Anh', 'AnhNTSE04003@fpt.edu.vn', 'SE04003', 1, 13, 'NotSet', 1, NULL),
(3534, 'PhuNVSE04123', 'Ngô Văn Phú', 'PhuNVSE04123@fpt.edu.vn', 'SE04123', 1, 13, 'NotSet', 1, NULL),
(3535, 'CongNTSE04104', 'Nguyễn Thành Công', 'CongNTSE04104@fpt.edu.vn', 'SE04104', 1, 13, 'NotSet', 1, NULL),
(3536, 'AnhHKSE04034', 'Hồ Kim Anh', 'AnhHKSE04034@fpt.edu.vn', 'SE04034', 1, 13, 'NotSet', 1, NULL),
(3537, 'QuyNCSE03957', 'Nguyễn Chí Quý', 'QuyNCSE03957@fpt.edu.vn', 'SE03957', 1, 13, 'NotSet', 1, NULL),
(3538, 'ThangNDSE04090', 'Nguyễn Đức Thắng', 'ThangNDSE04090@fpt.edu.vn', 'SE04090', 1, 13, 'NotSet', 1, NULL),
(3539, 'ThanhPDSE04309', 'Phan Duy Thành', 'ThanhPDSE04309@fpt.edu.vn', 'SE04309', 1, 13, 'NotSet', 1, NULL),
(3540, 'AnhVVSE04447', 'Vũ Việt Anh', 'AnhVVSE04447@fpt.edu.vn', 'SE04447', 1, 13, 'NotSet', 1, NULL),
(3541, 'HoangNTSE03960', 'Nguyễn Trọng Hoàng', 'HoangNTSE03960@fpt.edu.vn', 'SE03960', 1, 13, 'NotSet', 1, NULL),
(3542, 'HaVMSE03868', 'Vũ Mạnh Hà', 'HaVMSE03868@fpt.edu.vn', 'SE03868', 1, 9, 'NotSet', 1, NULL),
(3543, 'TungNXSE04275', 'Nguyễn Xuân Tùng', 'TungNXSE04275@fpt.edu.vn', 'SE04275', 1, 9, 'NotSet', 1, NULL),
(3544, 'ThangNVSE04385', 'Ngụy Văn Thắng', 'ThangNVSE04385@fpt.edu.vn', 'SE04385', 1, 9, 'NotSet', 1, NULL),
(3545, 'ThangTQSE04101', 'Tiêu Quang Thắng', 'ThangTQSE04101@fpt.edu.vn', 'SE04101', 1, 9, 'NotSet', 1, NULL),
(3546, 'SonNHSE03996', 'Nguyễn Hồng Sơn', 'SonNHSE03996@fpt.edu.vn', 'SE03996', 1, 9, 'NotSet', 1, NULL),
(3547, 'QuangCXSE04220', 'Cấn Xuân Quang', 'QuangCXSE04220@fpt.edu.vn', 'SE04220', 1, 9, 'NotSet', 1, NULL),
(3548, 'LongNDSE04039', 'Nguyễn Đình Long', 'LongNDSE04039@fpt.edu.vn', 'SE04039', 1, 9, 'NotSet', 1, NULL),
(3549, 'DuyNDSE04192', 'Nguyễn Đức Duy', 'DuyNDSE04192@fpt.edu.vn', 'SE04192', 1, 9, 'NotSet', 1, NULL),
(3550, 'ThanhPTSE04200', 'Phùng Tuấn Thanh', 'ThanhPTSE04200@fpt.edu.vn', 'SE04200', 1, 9, 'NotSet', 1, NULL),
(3551, 'QuanNASE04316', 'Nguyễn Anh Quân', 'QuanNASE04316@fpt.edu.vn', 'SE04316', 1, 9, 'NotSet', 1, NULL),
(3552, 'QuangTLSE04109', 'Trần Long Quang', 'QuangTLSE04109@fpt.edu.vn', 'SE04109', 1, 9, 'NotSet', 1, NULL),
(3553, 'CuongLVSE04314', 'Lê Văn Cường', 'CuongLVSE04314@fpt.edu.vn', 'SE04314', 1, 9, 'NotSet', 1, NULL),
(3554, 'LongNCSE04205', 'Nguyễn Cao Long', 'LongNCSE04205@fpt.edu.vn', 'SE04205', 1, 9, 'NotSet', 1, NULL),
(3555, 'CanhNDSE04042', 'Nguyễn Đức Cảnh', 'CanhNDSE04042@fpt.edu.vn', 'SE04042', 1, 9, 'NotSet', 1, NULL),
(3556, 'HieuTMSE04225', 'Tăng Minh Hiếu', 'HieuTMSE04225@fpt.edu.vn', 'SE04225', 1, 9, 'NotSet', 1, NULL),
(3557, 'HaHTSE04467', 'Hoàng Trọng Hà', 'HaHTSE04467@fpt.edu.vn', 'SE04467', 1, 9, 'NotSet', 1, NULL),
(3558, 'VinhBDSE04008', 'Bùi Đức Vinh', 'VinhBDSE04008@fpt.edu.vn', 'SE04008', 1, 13, 'NotSet', 2, NULL),
(3559, 'HuongLTSE03949', 'Lưu Thị Hưởng', 'HuongLTSE03949@fpt.edu.vn', 'SE03949', 1, 13, 'NotSet', 2, NULL),
(3560, 'PhuLHDSE61898', 'Lưu Huỳnh Đức Phú', 'PhuLHDSE61898@fpt.edu.vn', 'SE61898', 1, 13, 'NotSet', 2, NULL),
(3561, 'SonNVSE03984', 'Ngô Việt Sơn', 'SonNVSE03984@fpt.edu.vn', 'SE03984', 1, 13, 'NotSet', 2, NULL),
(3562, 'DucLHSE04203', 'Lê Hồng Đức', 'DucLHSE04203@fpt.edu.vn', 'SE04203', 1, 13, 'NotSet', 2, NULL),
(3563, 'BinhVQSE03979', 'Vũ Quang Bình', 'BinhVQSE03979@fpt.edu.vn', 'SE03979', 1, 13, 'NotSet', 2, NULL),
(3564, 'AnhNTLSE04122', 'Nguyễn Thị Lan Anh', 'AnhNTLSE04122@fpt.edu.vn', 'SE04122', 1, 13, 'NotSet', 2, NULL),
(3565, 'PhuNDSE04284', 'Nguyễn Danh Phú', 'PhuNDSE04284@fpt.edu.vn', 'SE04284', 1, 13, 'NotSet', 2, NULL),
(3566, 'HungTXSE04334', 'Trịnh Xuân Hưng', 'HungTXSE04334@fpt.edu.vn', 'SE04334', 1, 13, 'NotSet', 2, NULL),
(3567, 'NamNHSE04050', 'Nguyễn Hữu Nam', 'NamNHSE04050@fpt.edu.vn', 'SE04050', 1, 13, 'NotSet', 2, NULL),
(3568, 'DatVVSE61792', 'Vòng Vĩnh Đạt', 'DatVVSE61792@fpt.edu.vn', 'SE61792', 1, 13, 'NotSet', 2, NULL),
(3569, 'QuyTCSE04022', 'Tạ Chu Quý', 'QuyTCSE04022@fpt.edu.vn', 'SE04022', 1, 13, 'NotSet', 2, NULL),
(3570, 'HungNHSE04359', 'Nguyễn Hoàng Hưng', 'HungNHSE04359@fpt.edu.vn', 'SE04359', 1, 13, 'NotSet', 2, NULL),
(3571, 'DucNTSE04128', 'Nguyễn Tài Đức', 'DucNTSE04128@fpt.edu.vn', 'SE04128', 1, 13, 'NotSet', 2, NULL),
(3572, 'AnhCHSE03808', 'Cao Hoàng Anh', 'AnhCHSE03808@fpt.edu.vn', 'SE03808', 1, 9, 'NotSet', 3, NULL),
(3573, 'SonPNSE03916', 'Phạm Ngọc Sơn', 'SonPNSE03916@fpt.edu.vn', 'SE03916', 1, 9, 'NotSet', 3, NULL),
(3574, 'AnhNVVSE04271', 'Ngô Vi Việt Anh', 'AnhNVVSE04271@fpt.edu.vn', 'SE04271', 1, 9, 'NotSet', 3, NULL);
INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(3575, 'HanhNNSE03797', 'Nguyễn Ngọc Hạnh', 'HanhNNSE03797@fpt.edu.vn', 'SE03797', 1, 9, 'NotSet', 3, NULL),
(3576, 'CongNTSE03805', 'Nguyễn Thành Công', 'CongNTSE03805@fpt.edu.vn', 'SE03805', 1, 13, 'NotSet', 4, NULL),
(3577, 'ToanCGKSE03814', 'Chu Gia Khánh Toàn', 'ToanCGKSE03814@fpt.edu.vn', 'SE03814', 1, 13, 'NotSet', 4, NULL),
(3578, 'QuangTHSE03486', 'Trần Hồng Quang', 'QuangTHSE03486@fpt.edu.vn', 'SE03486', 1, 13, 'NotSet', 4, NULL),
(3579, 'HuyPQSE03487', 'Phạm Quang Huy', 'HuyPQSE03487@fpt.edu.vn', 'SE03487', 1, 13, 'NotSet', 4, NULL),
(3580, 'DucLASE03549', 'Lê Anh Đức', 'DucLASE03549@fpt.edu.vn', 'SE03549', 1, 13, 'NotSet', 4, NULL),
(3581, 'MinhNNSE03550', 'Nguyễn Nhật Minh', 'MinhNNSE03550@fpt.edu.vn', 'SE03550', 1, 13, 'NotSet', 4, NULL),
(3582, 'LeNHSE03612', 'Nguyễn Hữu Lê', 'LeNHSE03612@fpt.edu.vn', 'SE03612', 1, 13, 'NotSet', 4, NULL),
(3583, 'PhucNVSE03616', 'Nguyễn Văn Phúc', 'PhucNVSE03616@fpt.edu.vn', 'SE03616', 1, 13, 'NotSet', 4, NULL),
(3584, 'LinhNQSE03645', 'Nguyễn Quang Linh', 'LinhNQSE03645@fpt.edu.vn', 'SE03645', 1, 13, 'NotSet', 4, NULL),
(3585, 'HoanNTSE03654', 'Nguyễn Trọng Hoàn', 'HoanNTSE03654@fpt.edu.vn', 'SE03654', 1, 13, 'NotSet', 4, NULL),
(3586, 'TuanTMSE03657', 'Trần Mạnh Tuấn', 'TuanTMSE03657@fpt.edu.vn', 'SE03657', 1, 13, 'NotSet', 4, NULL),
(3587, 'HuyVDSE03665', 'Vũ Đức Huy', 'HuyVDSE03665@fpt.edu.vn', 'SE03665', 1, 13, 'NotSet', 4, NULL),
(3588, 'DuyTTSE03672', 'Trần Trung Duy', 'DuyTTSE03672@fpt.edu.vn', 'SE03672', 1, 13, 'NotSet', 4, NULL),
(3589, 'HangVKSE03676', 'Vũ Khánh Hằng', 'HangVKSE03676@fpt.edu.vn', 'SE03676', 1, 13, 'NotSet', 4, NULL),
(3590, 'TungDXSE03682', 'Đinh Xuân Tùng', 'TungDXSE03682@fpt.edu.vn', 'SE03682', 1, 13, 'NotSet', 4, NULL),
(3591, 'ThaoNDSE03717', 'Ngọ Đức Thảo', 'ThaoNDSE03717@fpt.edu.vn', 'SE03717', 1, 13, 'NotSet', 4, NULL),
(3592, 'AnhNTSE03748', 'Nguyễn Thế Anh', 'AnhNTSE03748@fpt.edu.vn', 'SE03748', 1, 13, 'NotSet', 4, NULL),
(3593, 'PhuHMSE03757', 'Hồ Minh Phú', 'PhuHMSE03757@fpt.edu.vn', 'SE03757', 1, 13, 'NotSet', 4, NULL),
(3594, 'HieuNTSE03782', 'Nguyễn Trung Hiếu', 'HieuNTSE03782@fpt.edu.vn', 'SE03782', 1, 13, 'NotSet', 4, NULL),
(3595, 'CuongCHSE03788', 'Chu Hoàng Cường', 'CuongCHSE03788@fpt.edu.vn', 'SE03788', 1, 13, 'NotSet', 4, NULL),
(3596, 'ToanTVSE03872', 'Trần Văn Toản', 'ToanTVSE03872@fpt.edu.vn', 'SE03872', 1, 13, 'NotSet', 4, NULL),
(3597, 'MyVTSE03878', 'Vũ Thị My', 'MyVTSE03878@fpt.edu.vn', 'SE03878', 1, 13, 'NotSet', 4, NULL),
(3598, 'DuyDDSE03881', 'Đào Đức Duy', 'DuyDDSE03881@fpt.edu.vn', 'SE03881', 1, 13, 'NotSet', 4, NULL),
(3599, 'NghiaNDSE03915', 'Nguyễn Đức Nghĩa', 'NghiaNDSE03915@fpt.edu.vn', 'SE03915', 1, 13, 'NotSet', 4, NULL),
(3600, 'LuanNTSE03925', 'Nguyễn Thành Luân', 'LuanNTSE03925@fpt.edu.vn', 'SE03925', 1, 13, 'NotSet', 4, NULL),
(3601, 'TruongNMSE03938', 'Nguyễn Mạnh Trường', 'TruongNMSE03938@fpt.edu.vn', 'SE03938', 1, 13, 'NotSet', 4, NULL),
(3602, 'DucNASE03952', 'Ngọ Anh Đức', 'DucNASE03952@fpt.edu.vn', 'SE03952', 1, 13, 'NotSet', 4, NULL),
(3603, 'AnhLDSE03953', 'Lê Duy Anh', 'AnhLDSE03953@fpt.edu.vn', 'SE03953', 1, 13, 'NotSet', 4, NULL),
(3604, 'AnhDTSE03956', 'Đinh Tuấn Anh', 'AnhDTSE03956@fpt.edu.vn', 'SE03956', 1, 13, 'NotSet', 4, NULL),
(3605, 'ThanhPTSE61729', 'Phạm Tiến Thành', 'ThanhPTSE61729@fpt.edu.vn', 'SE61729', 1, 13, 'NotSet', 4, NULL),
(3606, 'HuyVTSE61529', 'Võ Thanh Huy', 'HuyVTSE61529@fpt.edu.vn', 'SE61529', 1, 13, 'NotSet', 4, NULL),
(3607, 'TrungNMQSE61433', 'Nguyễn Minh Quốc Trung', 'TrungNMQSE61433@fpt.edu.vn', 'SE61433', 1, 13, 'NotSet', 4, NULL),
(3608, 'KhoaPASE61403', 'Phạm Anh Khoa', 'KhoaPASE61403@fpt.edu.vn', 'SE61403', 1, 13, 'NotSet', 4, NULL),
(3609, 'TrungHTSE61581', 'Huỳnh Thành Trung', 'TrungHTSE61581@fpt.edu.vn', 'SE61581', 1, 13, 'NotSet', 4, NULL),
(3610, 'QuanNHSE61560', 'Nguyễn Hoàng Quân', 'QuanNHSE61560@fpt.edu.vn', 'SE61560', 1, 13, 'NotSet', 4, NULL),
(3611, 'SinhDBTSE61674', 'Đinh Bá Thi Sinh', 'SinhDBTSE61674@fpt.edu.vn', 'SE61674', 1, 13, 'NotSet', 4, NULL),
(3612, 'ThaoPTSE03601', 'Phạm Thu Thảo', 'ThaoPTSE03601@fpt.edu.vn', 'SE03601', 1, 13, 'NotSet', 4, NULL),
(3613, 'HieuKNSE03605', 'Kiều Ngọc Hiệu', 'HieuKNSE03605@fpt.edu.vn', 'SE03605', 1, 13, 'NotSet', 4, NULL),
(3614, 'NhatBVSE03740', 'Bùi Văn Nhật', 'NhatBVSE03740@fpt.edu.vn', 'SE03740', 1, 13, 'NotSet', 4, NULL),
(3615, 'NguyenLSE03803', 'Lê Nguyên', 'NguyenLSE03803@fpt.edu.vn', 'SE03803', 1, 13, 'NotSet', 4, NULL),
(3616, 'PhongDDSE03906', 'Đinh Duy Phong', 'PhongDDSE03906@fpt.edu.vn', 'SE03906', 1, 13, 'NotSet', 4, NULL),
(3617, 'HungVNSE03969', 'Vũ Ngọc Hưng', 'HungVNSE03969@fpt.edu.vn', 'SE03969', 1, 13, 'NotSet', 5, NULL),
(3618, 'KhanhPBSE03453', 'Phạm Bảo Khánh', 'KhanhPBSE03453@fpt.edu.vn', 'SE03453', 1, 13, 'NotSet', 5, NULL),
(3619, 'TungTTSE03495', 'Trần Thanh Tùng', 'TungTTSE03495@fpt.edu.vn', 'SE03495', 1, 13, 'NotSet', 5, NULL),
(3620, 'ThanhNMSE03534', 'Nguyễn Minh Thành', 'ThanhNMSE03534@fpt.edu.vn', 'SE03534', 1, 13, 'NotSet', 5, NULL),
(3621, 'HieuTTSE03538', 'Trần Trung Hiếu', 'HieuTTSE03538@fpt.edu.vn', 'SE03538', 1, 13, 'NotSet', 5, NULL),
(3622, 'TriLMSE03613', 'Lưu Minh Trí', 'TriLMSE03613@fpt.edu.vn', 'SE03613', 1, 13, 'NotSet', 5, NULL),
(3623, 'LinhDBSE03640', 'Đoàn Bá Linh', 'LinhDBSE03640@fpt.edu.vn', 'SE03640', 1, 13, 'NotSet', 5, NULL),
(3624, 'MinhHHSE03643', 'Hoàng Hải Minh', 'MinhHHSE03643@fpt.edu.vn', 'SE03643', 1, 13, 'NotSet', 5, NULL),
(3625, 'AnhLDSE03662', 'Lê Đức Anh', 'AnhLDSE03662@fpt.edu.vn', 'SE03662', 1, 13, 'NotSet', 5, NULL),
(3626, 'DuyDNSE03721', 'Đặng Ngọc Duy', 'DuyDNSE03721@fpt.edu.vn', 'SE03721', 1, 13, 'NotSet', 5, NULL),
(3627, 'AnhHTSE03756', 'Hoàng Tuấn Anh', 'AnhHTSE03756@fpt.edu.vn', 'SE03756', 1, 13, 'NotSet', 5, NULL),
(3628, 'AnhHDSE03777', 'Hoàng Đức Anh', 'AnhHDSE03777@fpt.edu.vn', 'SE03777', 1, 13, 'NotSet', 5, NULL),
(3629, 'DungLTSE03784', 'Lê Trung Dũng', 'DungLTSE03784@fpt.edu.vn', 'SE03784', 1, 13, 'NotSet', 5, NULL),
(3630, 'DucTQSE03819', 'Tạ Quang Đức', 'DucTQSE03819@fpt.edu.vn', 'SE03819', 1, 13, 'NotSet', 5, NULL),
(3631, 'ToanPVNSE03821', 'Phạm Vũ Nhật Toàn', 'ToanPVNSE03821@fpt.edu.vn', 'SE03821', 1, 13, 'NotSet', 5, NULL),
(3632, 'HoangVMSE03833', 'Vũ Minh  Hoàng', 'HoangVMSE03833@fpt.edu.vn', 'SE03833', 1, 13, 'NotSet', 5, NULL),
(3633, 'VinhNQSE03886', 'Nguyễn Quang Vinh', 'VinhNQSE03886@fpt.edu.vn', 'SE03886', 1, 13, 'NotSet', 5, NULL),
(3634, 'HaVHSE03912', 'Vũ Hồng Hà', 'HaVHSE03912@fpt.edu.vn', 'SE03912', 1, 13, 'NotSet', 5, NULL),
(3635, 'HongNTSE03940', 'Ngô Thu Hồng', 'HongNTSE03940@fpt.edu.vn', 'SE03940', 1, 13, 'NotSet', 5, NULL),
(3636, 'SuongPTSSE61497', 'Phạm Thị Sương Sương', 'SuongPTSSE61497@fpt.edu.vn', 'SE61497', 1, 13, 'NotSet', 5, NULL),
(3637, 'MinhPASE61477', 'Phạm Anh Minh', 'MinhPASE61477@fpt.edu.vn', 'SE61477', 1, 13, 'NotSet', 5, NULL),
(3638, 'DuyDTTSE61484', 'Đào Trần Tuấn Duy', 'DuyDTTSE61484@fpt.edu.vn', 'SE61484', 1, 13, 'NotSet', 5, NULL),
(3639, 'TruongPMSE02605', 'Phan Mạnh Trường', 'TruongPMSE02605@fpt.edu.vn', 'SE02605', 1, 13, 'NotSet', 5, NULL),
(3640, 'hameoSE62029', 'hameo', 'hameoSE62029@fpt.edu.vn', 'SE62029', 1, 8, '123', 1, NULL);

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
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(50) NOT NULL,
  `account_type` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `account`, `name`, `email`, `account_type`) VALUES
(1, 'LoanNTP', 'LoanNTP', 'LoanNTP@fpt.edu.vn', 'teacher'),
(2, 'TamNT', 'TamNT', 'TamNT@fpt.edu.vn', 'teacher'),
(3, 'DungDV', 'DungDV', 'DungDV@fpt.edu.vn', 'teacher'),
(4, 'NguyenLTT', 'NguyenLTT', 'NguyenLTT@fpt.edu.vn', 'teacher'),
(5, 'HaiNM', 'HaiNM', 'HaiNM@fpt.edu.vn', 'teacher'),
(6, 'DungNT3', 'DungNT3', 'DungNT3@fpt.edu.vn', 'teacher'),
(7, 'ChiLP', 'ChiLP', 'ChiLP@fpt.edu.vn', 'teacher'),
(8, 'DuyDT', 'DuyDT', 'DuyDT@fpt.edu.vn', 'teacher'),
(9, 'CauPD', 'CauPD', 'CauPD@fpt.edu.vn', 'teacher'),
(10, 'VuNH', 'VuNH', 'VuNH@fpt.edu.vn', 'teacher'),
(11, 'TrungNT', 'TrungNT', 'TrungNT@fpt.edu.vn', 'teacher'),
(12, 'TrungTT', 'TrungTT', 'TrungTT@fpt.edu.vn', 'teacher'),
(13, 'NgaNTT', 'NgaNTT', 'NgaNTT@fpt.edu.vn', 'teacher'),
(14, 'LinhLHM', 'LinhLHM', 'LinhLHM@fpt.edu.vn', 'teacher'),
(15, 'ChinhVV', 'ChinhVV', 'ChinhVV@fpt.edu.vn', 'teacher'),
(16, 'HaNS2', 'HaNS2', 'HaNS2@fpt.edu.vn', 'teacher'),
(17, 'DucHM2', 'DucHM2', 'DucHM2@fpt.edu.vn', 'teacher'),
(18, 'NamVX', 'NamVX', 'NamVX@fpt.edu.vn', 'teacher'),
(19, 'QuyenTTL', 'QuyenTTL', 'QuyenTTL@fpt.edu.vn', 'teacher'),
(20, 'NamPT', 'NamPT', 'NamPT@fpt.edu.vn', 'teacher'),
(21, 'HoaiNV', 'HoaiNV', 'HoaiNV@fpt.edu.vn', 'teacher'),
(22, 'LucPT', 'LucPT', 'LucPT@fpt.edu.vn', 'teacher'),
(23, 'LanTV', 'LanTV', 'LanTV@fpt.edu.vn', 'teacher'),
(24, 'SonHX', 'SonHX', 'SonHX@fpt.edu.vn', 'teacher'),
(25, 'HungPD', 'HungPD', 'HungPD@fpt.edu.vn', 'teacher'),
(26, 'HieuLD2', 'HieuLD2', 'HieuLD2@fpt.edu.vn', 'teacher'),
(27, 'CuongN', 'CuongN', 'CuongN@fpt.edu.vn', 'teacher'),
(28, 'CuongN1', 'CuongN1', 'CuongN1@fpt.edu.vn', 'teacher'),
(29, 'CuongN2', 'CuongN2', 'CuongN2@fpt.edu.vn', 'teacher'),
(30, 'TrungDT', 'TrungDT', 'TrungDT@fpt.edu.vn', 'teacher'),
(31, 'MaiVTT', 'MaiVTT', 'MaiVTT@fpt.edu.vn', 'teacher'),
(32, 'VietTK', 'VietTK', 'VietTK@fpt.edu.vn', 'teacher'),
(33, 'HaiDT', 'HaiDT', 'HaiDT@fpt.edu.vn', 'teacher'),
(34, 'CongLT', 'CongLT', 'CongLT@fpt.edu.vn', 'teacher'),
(35, 'ThanhVC', 'ThanhVC', 'ThanhVC@fpt.edu.vn', 'teacher'),
(36, 'HieuTM', 'HieuTM', 'HieuTM@fpt.edu.vn', 'teacher'),
(37, 'HoangNM', 'HoangNM', 'HoangNM@fpt.edu.vn', 'teacher'),
(38, 'MaiTT', 'MaiTT', 'MaiTT@fpt.edu.vn', 'teacher'),
(39, 'CuongDM', 'CuongDM', 'CuongDM@fpt.edu.vn', 'teacher');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_course_semester`
--

DROP TABLE IF EXISTS `teacher_course_semester`;
CREATE TABLE IF NOT EXISTS `teacher_course_semester` (
`teacher_course_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=618 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_course_semester`
--

INSERT INTO `teacher_course_semester` (`teacher_course_semester_id`, `teacher_semester_id`, `course_semester_id`) VALUES
(1, 6, 89),
(2, 1, 89),
(3, 2, 89),
(4, 3, 89),
(5, 4, 89),
(6, 5, 89),
(7, 6, 90),
(8, 1, 90),
(9, 2, 90),
(10, 3, 90),
(11, 4, 90),
(12, 5, 90),
(13, 6, 91),
(14, 1, 91),
(15, 2, 91),
(16, 3, 91),
(17, 4, 91),
(18, 5, 91),
(19, 6, 92),
(20, 1, 92),
(21, 2, 92),
(22, 3, 92),
(23, 4, 92),
(24, 5, 92),
(25, 6, 93),
(26, 1, 93),
(27, 2, 93),
(28, 3, 93),
(29, 4, 93),
(30, 5, 93),
(31, 6, 94),
(32, 1, 94),
(33, 2, 94),
(34, 3, 94),
(35, 4, 94),
(36, 5, 94),
(37, 6, 95),
(38, 1, 95),
(39, 2, 95),
(40, 3, 95),
(41, 4, 95),
(42, 5, 95),
(43, 6, 96),
(44, 1, 96),
(45, 2, 96),
(46, 3, 96),
(47, 4, 96),
(48, 5, 96),
(49, 6, 97),
(50, 1, 97),
(51, 2, 97),
(52, 3, 97),
(53, 4, 97),
(54, 5, 97),
(55, 6, 98),
(56, 1, 98),
(57, 2, 98),
(58, 3, 98),
(59, 4, 98),
(60, 5, 98),
(61, 6, 99),
(62, 1, 99),
(63, 2, 99),
(64, 3, 99),
(65, 4, 99),
(66, 5, 99),
(67, 6, 100),
(68, 1, 100),
(69, 2, 100),
(70, 3, 100),
(71, 4, 100),
(72, 5, 100),
(73, 6, 101),
(74, 1, 101),
(75, 2, 101),
(76, 3, 101),
(77, 4, 101),
(78, 5, 101),
(79, 6, 102),
(80, 1, 102),
(81, 2, 102),
(82, 3, 102),
(83, 4, 102),
(84, 5, 102),
(85, 6, 103),
(86, 1, 103),
(87, 2, 103),
(88, 3, 103),
(89, 4, 103),
(90, 5, 103),
(91, 6, 104),
(92, 1, 104),
(93, 2, 104),
(94, 3, 104),
(95, 4, 104),
(96, 5, 104),
(97, 6, 105),
(98, 1, 105),
(99, 2, 105),
(100, 3, 105),
(101, 4, 105),
(102, 5, 105),
(103, 6, 106),
(104, 1, 106),
(105, 2, 106),
(106, 3, 106),
(107, 4, 106),
(108, 5, 106),
(109, 6, 107),
(110, 1, 107),
(111, 2, 107),
(112, 3, 107),
(113, 4, 107),
(114, 5, 107),
(115, 6, 108),
(116, 1, 108),
(117, 2, 108),
(118, 3, 108),
(119, 4, 108),
(120, 5, 108),
(121, 6, 109),
(122, 1, 109),
(123, 2, 109),
(124, 3, 109),
(125, 4, 109),
(126, 5, 109),
(127, 6, 110),
(128, 1, 110),
(129, 2, 110),
(130, 3, 110),
(131, 4, 110),
(132, 5, 110),
(133, 6, 111),
(134, 1, 111),
(135, 2, 111),
(136, 3, 111),
(137, 4, 111),
(138, 5, 111),
(139, 6, 112),
(140, 1, 112),
(141, 2, 112),
(142, 3, 112),
(143, 4, 112),
(144, 5, 112),
(145, 6, 113),
(146, 1, 113),
(147, 2, 113),
(148, 3, 113),
(149, 4, 113),
(150, 5, 113),
(151, 6, 114),
(152, 1, 114),
(153, 2, 114),
(154, 3, 114),
(155, 4, 114),
(156, 5, 114),
(157, 6, 115),
(158, 1, 115),
(159, 2, 115),
(160, 3, 115),
(161, 4, 115),
(162, 5, 115),
(163, 6, 116),
(164, 1, 116),
(165, 2, 116),
(166, 3, 116),
(167, 4, 116),
(168, 5, 116),
(169, 6, 117),
(170, 1, 117),
(171, 2, 117),
(172, 3, 117),
(173, 4, 117),
(174, 5, 117),
(175, 6, 118),
(176, 1, 118),
(177, 2, 118),
(178, 3, 118),
(179, 4, 118),
(180, 5, 118),
(181, 6, 119),
(182, 1, 119),
(183, 2, 119),
(184, 3, 119),
(185, 4, 119),
(186, 5, 119),
(187, 6, 120),
(188, 1, 120),
(189, 2, 120),
(190, 3, 120),
(191, 4, 120),
(192, 5, 120),
(193, 6, 121),
(194, 1, 121),
(195, 2, 121),
(196, 3, 121),
(197, 4, 121),
(198, 5, 121),
(199, 6, 122),
(200, 1, 122),
(201, 2, 122),
(202, 3, 122),
(203, 4, 122),
(204, 5, 122),
(205, 7, 123),
(206, 8, 123),
(207, 9, 123),
(208, 10, 123),
(209, 7, 124),
(210, 8, 124),
(211, 9, 124),
(212, 10, 124),
(213, 7, 125),
(214, 8, 125),
(215, 9, 125),
(216, 10, 125),
(217, 7, 126),
(218, 8, 126),
(219, 9, 126),
(220, 10, 126),
(221, 7, 127),
(222, 8, 127),
(223, 9, 127),
(224, 10, 127),
(225, 7, 128),
(226, 8, 128),
(227, 9, 128),
(228, 10, 128),
(229, 7, 129),
(230, 8, 129),
(231, 9, 129),
(232, 10, 129),
(233, 7, 130),
(234, 8, 130),
(235, 9, 130),
(236, 10, 130),
(237, 7, 131),
(238, 8, 131),
(239, 9, 131),
(240, 10, 131),
(241, 7, 132),
(242, 8, 132),
(243, 9, 132),
(244, 10, 132),
(245, 7, 133),
(246, 8, 133),
(247, 9, 133),
(248, 10, 133),
(249, 7, 134),
(250, 8, 134),
(251, 9, 134),
(252, 10, 134),
(253, 7, 135),
(254, 8, 135),
(255, 9, 135),
(256, 10, 135),
(257, 7, 136),
(258, 8, 136),
(259, 9, 136),
(260, 10, 136),
(261, 7, 137),
(262, 8, 137),
(263, 9, 137),
(264, 10, 137),
(265, 7, 138),
(266, 8, 138),
(267, 9, 138),
(268, 10, 138),
(269, 7, 139),
(270, 8, 139),
(271, 9, 139),
(272, 10, 139),
(273, 7, 140),
(274, 8, 140),
(275, 9, 140),
(276, 10, 140),
(277, 7, 141),
(278, 8, 141),
(279, 9, 141),
(280, 10, 141),
(281, 11, 142),
(282, 12, 142),
(283, 11, 143),
(284, 12, 143),
(285, 11, 144),
(286, 12, 144),
(287, 11, 145),
(288, 12, 145),
(289, 11, 146),
(290, 12, 146),
(291, 13, 147),
(292, 14, 147),
(293, 15, 147),
(294, 13, 148),
(295, 14, 148),
(296, 15, 148),
(297, 13, 149),
(298, 14, 149),
(299, 15, 149),
(300, 13, 150),
(301, 14, 150),
(302, 15, 150),
(303, 13, 151),
(304, 14, 151),
(305, 15, 151),
(306, 13, 152),
(307, 14, 152),
(308, 15, 152),
(309, 13, 153),
(310, 14, 153),
(311, 15, 153),
(312, 13, 154),
(313, 14, 154),
(314, 15, 154),
(315, 16, 155),
(316, 17, 155),
(317, 16, 156),
(318, 17, 156),
(319, 18, 157),
(320, 19, 157),
(321, 20, 157),
(322, 18, 158),
(323, 19, 158),
(324, 20, 158),
(325, 18, 159),
(326, 19, 159),
(327, 20, 159),
(328, 18, 160),
(329, 19, 160),
(330, 20, 160),
(331, 18, 161),
(332, 19, 161),
(333, 20, 161),
(334, 18, 162),
(335, 19, 162),
(336, 20, 162),
(337, 18, 163),
(338, 19, 163),
(339, 20, 163),
(340, 18, 164),
(341, 19, 164),
(342, 20, 164),
(343, 18, 165),
(344, 19, 165),
(345, 20, 165),
(346, 18, 166),
(347, 19, 166),
(348, 20, 166),
(349, 18, 167),
(350, 19, 167),
(351, 20, 167),
(352, 18, 168),
(353, 19, 168),
(354, 20, 168),
(355, 18, 169),
(356, 19, 169),
(357, 20, 169),
(358, 18, 170),
(359, 19, 170),
(360, 20, 170),
(361, 18, 171),
(362, 19, 171),
(363, 20, 171),
(364, 18, 172),
(365, 19, 172),
(366, 20, 172),
(367, 18, 173),
(368, 19, 173),
(369, 20, 173),
(370, 18, 174),
(371, 19, 174),
(372, 20, 174),
(373, 18, 175),
(374, 19, 175),
(375, 20, 175),
(376, 18, 176),
(377, 19, 176),
(378, 20, 176),
(379, 18, 177),
(380, 19, 177),
(381, 20, 177),
(382, 21, 178),
(383, 22, 178),
(384, 21, 179),
(385, 22, 179),
(386, 21, 180),
(387, 22, 180),
(388, 23, 181),
(389, 24, 181),
(390, 25, 181),
(391, 26, 181),
(392, 23, 182),
(393, 24, 182),
(394, 25, 182),
(395, 26, 182),
(396, 23, 183),
(397, 24, 183),
(398, 25, 183),
(399, 26, 183),
(400, 23, 184),
(401, 24, 184),
(402, 25, 184),
(403, 26, 184),
(404, 23, 185),
(405, 24, 185),
(406, 25, 185),
(407, 26, 185),
(408, 23, 186),
(409, 24, 186),
(410, 25, 186),
(411, 26, 186),
(412, 23, 187),
(413, 24, 187),
(414, 25, 187),
(415, 26, 187),
(416, 23, 188),
(417, 24, 188),
(418, 25, 188),
(419, 26, 188),
(420, 23, 189),
(421, 24, 189),
(422, 25, 189),
(423, 26, 189),
(424, 23, 190),
(425, 24, 190),
(426, 25, 190),
(427, 26, 190),
(428, 23, 191),
(429, 24, 191),
(430, 25, 191),
(431, 26, 191),
(432, 23, 192),
(433, 24, 192),
(434, 25, 192),
(435, 26, 192),
(436, 23, 193),
(437, 24, 193),
(438, 25, 193),
(439, 26, 193),
(440, 23, 194),
(441, 24, 194),
(442, 25, 194),
(443, 26, 194),
(444, 23, 195),
(445, 24, 195),
(446, 25, 195),
(447, 26, 195),
(448, 23, 196),
(449, 24, 196),
(450, 25, 196),
(451, 26, 196),
(452, 23, 197),
(453, 24, 197),
(454, 25, 197),
(455, 26, 197),
(456, 23, 198),
(457, 24, 198),
(458, 25, 198),
(459, 26, 198),
(460, 23, 199),
(461, 24, 199),
(462, 25, 199),
(463, 26, 199),
(464, 27, 200),
(465, 28, 200),
(466, 29, 200),
(467, 27, 201),
(468, 28, 201),
(469, 29, 201),
(470, 27, 202),
(471, 28, 202),
(472, 29, 202),
(473, 27, 203),
(474, 28, 203),
(475, 29, 203),
(476, 27, 204),
(477, 28, 204),
(478, 29, 204),
(479, 27, 205),
(480, 28, 205),
(481, 29, 205),
(482, 27, 206),
(483, 28, 206),
(484, 29, 206),
(485, 27, 207),
(486, 28, 207),
(487, 29, 207),
(488, 27, 208),
(489, 28, 208),
(490, 29, 208),
(491, 27, 209),
(492, 28, 209),
(493, 29, 209),
(494, 27, 210),
(495, 28, 210),
(496, 29, 210),
(497, 27, 211),
(498, 28, 211),
(499, 29, 211),
(500, 27, 212),
(501, 28, 212),
(502, 29, 212),
(503, 27, 213),
(504, 28, 213),
(505, 29, 213),
(506, 27, 214),
(507, 28, 214),
(508, 29, 214),
(509, 30, 215),
(510, 31, 215),
(511, 32, 215),
(512, 30, 216),
(513, 31, 216),
(514, 32, 216),
(515, 30, 217),
(516, 31, 217),
(517, 32, 217),
(518, 30, 218),
(519, 31, 218),
(520, 32, 218),
(521, 30, 219),
(522, 31, 219),
(523, 32, 219),
(524, 30, 220),
(525, 31, 220),
(526, 32, 220),
(527, 30, 221),
(528, 31, 221),
(529, 32, 221),
(530, 30, 222),
(531, 31, 222),
(532, 32, 222),
(533, 33, 223),
(534, 34, 223),
(535, 35, 223),
(536, 36, 223),
(537, 37, 223),
(538, 33, 224),
(539, 34, 224),
(540, 35, 224),
(541, 36, 224),
(542, 37, 224),
(543, 33, 225),
(544, 34, 225),
(545, 35, 225),
(546, 36, 225),
(547, 37, 225),
(548, 33, 226),
(549, 34, 226),
(550, 35, 226),
(551, 36, 226),
(552, 37, 226),
(553, 33, 227),
(554, 34, 227),
(555, 35, 227),
(556, 36, 227),
(557, 37, 227),
(558, 33, 228),
(559, 34, 228),
(560, 35, 228),
(561, 36, 228),
(562, 37, 228),
(563, 33, 229),
(564, 34, 229),
(565, 35, 229),
(566, 36, 229),
(567, 37, 229),
(568, 33, 230),
(569, 34, 230),
(570, 35, 230),
(571, 36, 230),
(572, 37, 230),
(573, 33, 231),
(574, 34, 231),
(575, 35, 231),
(576, 36, 231),
(577, 37, 231),
(578, 33, 232),
(579, 34, 232),
(580, 35, 232),
(581, 36, 232),
(582, 37, 232),
(583, 33, 233),
(584, 34, 233),
(585, 35, 233),
(586, 36, 233),
(587, 37, 233),
(588, 33, 234),
(589, 34, 234),
(590, 35, 234),
(591, 36, 234),
(592, 37, 234),
(593, 33, 235),
(594, 34, 235),
(595, 35, 235),
(596, 36, 235),
(597, 37, 235),
(598, 33, 236),
(599, 34, 236),
(600, 35, 236),
(601, 36, 236),
(602, 37, 236),
(603, 33, 237),
(604, 34, 237),
(605, 35, 237),
(606, 36, 237),
(607, 37, 237),
(608, 33, 238),
(609, 34, 238),
(610, 35, 238),
(611, 36, 238),
(612, 37, 238),
(613, 5, 239),
(614, 38, 239),
(615, 5, 240),
(616, 38, 240),
(617, 39, 241);

-- --------------------------------------------------------

--
-- Table structure for table `teacher_semester`
--

DROP TABLE IF EXISTS `teacher_semester`;
CREATE TABLE IF NOT EXISTS `teacher_semester` (
`teacher_semester_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

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
(26, 26, 1),
(27, 27, 1),
(28, 28, 1),
(29, 29, 1),
(30, 30, 1),
(31, 31, 1),
(32, 32, 1),
(33, 33, 1),
(34, 34, 1),
(35, 35, 1),
(36, 36, 1),
(37, 37, 1),
(38, 38, 1),
(39, 39, 1);

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
  `room_id` int(11) DEFAULT NULL,
  `teacher_semester_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buildings`
--
ALTER TABLE `buildings`
 ADD PRIMARY KEY (`building_id`);

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
-- Indexes for table `class_course_semester_merge`
--
ALTER TABLE `class_course_semester_merge`
 ADD PRIMARY KEY (`class_course_semester_merge_id`), ADD KEY `class_course_semester_id_1` (`class_course_semester_id_1`), ADD KEY `class_course_semester_id_2` (`class_course_semester_id_2`);

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
 ADD PRIMARY KEY (`room_id`), ADD UNIQUE KEY `code` (`code`), ADD KEY `building_id` (`building_id`);

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
-- AUTO_INCREMENT for table `buildings`
--
ALTER TABLE `buildings`
MODIFY `building_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=228;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=926;
--
-- AUTO_INCREMENT for table `class_course_semester_merge`
--
ALTER TABLE `class_course_semester_merge`
MODIFY `class_course_semester_merge_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
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
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=225;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=248;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=247;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `program_semester`
--
ALTER TABLE `program_semester`
MODIFY `program_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=111;
--
-- AUTO_INCREMENT for table `program_semester_detail`
--
ALTER TABLE `program_semester_detail`
MODIFY `program_semester_detail_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=495;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50;
--
-- AUTO_INCREMENT for table `semesters`
--
ALTER TABLE `semesters`
MODIFY `semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `specialized`
--
ALTER TABLE `specialized`
MODIFY `specialized_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3641;
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
MODIFY `student_course_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
MODIFY `teacher_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=618;
--
-- AUTO_INCREMENT for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
MODIFY `teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
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
ADD CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `classes_ibfk_3` FOREIGN KEY (`detail_specialized_id`) REFERENCES `specialized` (`specialized_id`);

--
-- Constraints for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
ADD CONSTRAINT `class_course_semester_ibfk_1` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`),
ADD CONSTRAINT `class_course_semester_ibfk_2` FOREIGN KEY (`course_semester_id`) REFERENCES `course_semester` (`course_semester_id`);

--
-- Constraints for table `class_course_semester_merge`
--
ALTER TABLE `class_course_semester_merge`
ADD CONSTRAINT `class_course_semester_merge_ibfk_1` FOREIGN KEY (`class_course_semester_id_1`) REFERENCES `class_course_semester` (`class_course_semester_id`),
ADD CONSTRAINT `class_course_semester_merge_ibfk_2` FOREIGN KEY (`class_course_semester_id_2`) REFERENCES `class_course_semester` (`class_course_semester_id`);

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
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`building_id`);

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
