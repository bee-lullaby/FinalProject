-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2015 at 04:48 PM
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
  `code` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `code`) VALUES
(1, 'ADV2.1'),
(2, 'BA0901'),
(3, 'BA0902'),
(4, 'BA0903'),
(5, 'BA0904'),
(6, 'BE3.1'),
(7, 'CJ1.1'),
(8, 'Cnet1.1'),
(9, 'COB0701'),
(10, 'COF0701'),
(11, 'COF0702'),
(12, 'COF0802'),
(13, 'COF0803'),
(14, 'COF0804'),
(15, 'COV111.1'),
(16, 'COV111.M1'),
(17, 'COV111.M2'),
(18, 'COV121.1'),
(19, 'COV121.E'),
(20, 'COV121.M'),
(21, 'COV131.1'),
(22, 'COV131.2'),
(23, 'COV131.M'),
(24, 'CS0701'),
(25, 'CS0801'),
(26, 'DBD1.1'),
(167, 'ÐNG101.E'),
(168, 'ÐNG101.M'),
(169, 'ÐNH101.E'),
(170, 'ÐNH101.M'),
(171, 'ÐSA101.E'),
(172, 'ÐSA101.M'),
(173, 'ÐTR101.E'),
(174, 'ÐTR101.M'),
(27, 'EC0701'),
(28, 'EC0702'),
(29, 'EC0801'),
(30, 'EC0802'),
(31, 'EC0902'),
(32, 'EC10901'),
(33, 'EC20901'),
(34, 'ENL1.1'),
(35, 'ES10701'),
(36, 'ES10702'),
(37, 'ES20701'),
(38, 'ES20702'),
(39, 'ES20703'),
(40, 'FB0901'),
(41, 'FB0902'),
(42, 'FB0903'),
(43, 'FIN0701'),
(44, 'FIN0802'),
(45, 'GD0902'),
(46, 'GD10901'),
(47, 'GD20901'),
(48, 'IA0902'),
(49, 'IA0903'),
(50, 'IA10901'),
(51, 'IA20901'),
(52, 'IA30901'),
(53, 'IBA0801'),
(54, 'IBA0901'),
(55, 'IBA0903'),
(56, 'IOOP1.1'),
(57, 'IS10701'),
(58, 'IS10702'),
(59, 'IS10703'),
(60, 'IS20701'),
(61, 'IS20702'),
(62, 'ISE0801'),
(63, 'ISE0802'),
(64, 'ISE0901'),
(65, 'ISE0902'),
(66, 'JPN111.1'),
(67, 'JPN2.1'),
(68, 'JS0701'),
(69, 'JS0702'),
(70, 'JS0703'),
(71, 'MKT0701'),
(72, 'MKT0802'),
(73, 'MKT0803'),
(74, 'OJB211.1'),
(75, 'OJB211.2'),
(76, 'OJS201.1'),
(77, 'OJS201.2'),
(78, 'OJT201.1'),
(79, 'OOP1.1'),
(80, 'PC0903'),
(81, 'PC0904'),
(82, 'PC0905'),
(83, 'PC0906'),
(84, 'PC0907'),
(85, 'PC0908'),
(86, 'PC0919'),
(87, 'PC1001'),
(88, 'PC1002'),
(89, 'PC1003'),
(90, 'PC1004'),
(91, 'PC1005'),
(92, 'PC1006'),
(93, 'PC1007'),
(94, 'PC1008'),
(95, 'PC1009'),
(96, 'PC1010'),
(97, 'PC1011'),
(98, 'PC1012'),
(99, 'PC1013'),
(100, 'PC1014'),
(101, 'PC1015'),
(102, 'PC1016'),
(103, 'PC1017'),
(104, 'PC1018'),
(105, 'PC1019'),
(106, 'PC1020'),
(107, 'PC1021'),
(108, 'PC1022'),
(109, 'PC1023'),
(110, 'PFC1.1'),
(111, 'SE0803'),
(112, 'SE0804'),
(113, 'SE0805'),
(114, 'SE0806'),
(115, 'SE0807'),
(116, 'SE0808'),
(117, 'SE0809'),
(118, 'SE0810'),
(119, 'SE0811'),
(120, 'SE0812'),
(121, 'SE0901'),
(122, 'SE0902'),
(123, 'SE0903'),
(124, 'SE0904'),
(125, 'SE0905'),
(126, 'SE0906'),
(127, 'SE0907'),
(128, 'SE0908'),
(129, 'SE0909'),
(130, 'SE0910'),
(131, 'SE0911'),
(132, 'Sum1.1'),
(133, 'Sum2.1'),
(134, 'Sum2.2'),
(135, 'SYB301.1'),
(136, 'SYB301.2'),
(137, 'SYB301.3'),
(138, 'SYB301.4'),
(139, 'VNL111.EX'),
(140, 'VOV112.E'),
(141, 'VOV112.M'),
(142, 'VOV113.M'),
(143, 'VOV122.1'),
(144, 'VOV122.2'),
(145, 'VOV122.E'),
(146, 'VOV123.1'),
(147, 'VOV123.2'),
(148, 'VOV123.E'),
(149, 'VOV123.M'),
(150, 'VOV132.1'),
(151, 'VOV132.2'),
(152, 'VOV132.3'),
(153, 'VOV132.4'),
(154, 'VOV132.E'),
(155, 'VOV132.M1'),
(156, 'VOV132.M2'),
(157, 'VOV133.1'),
(158, 'VOV133.2'),
(159, 'VOV133.3'),
(160, 'VOV133.4'),
(161, 'VOV133.5'),
(162, 'VOV133.E1'),
(163, 'VOV133.E2'),
(164, 'VOV133.E3'),
(165, 'VOV133.M1'),
(166, 'VOV133.M2');

-- --------------------------------------------------------

--
-- Table structure for table `class_course_semester`
--

DROP TABLE IF EXISTS `class_course_semester`;
CREATE TABLE IF NOT EXISTS `class_course_semester` (
`class_course_semester_id` int(11) NOT NULL,
  `class_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `class_course_teacher_semester`
--

DROP TABLE IF EXISTS `class_course_teacher_semester`;
CREATE TABLE IF NOT EXISTS `class_course_teacher_semester` (
`class_course_semester_id` int(11) NOT NULL,
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
  `students` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_semester`
--

INSERT INTO `class_semester` (`class_semester_id`, `class_id`, `semester_id`, `students`) VALUES
(1, 1, 1, 30),
(2, 2, 1, 30),
(3, 3, 1, 30),
(4, 4, 1, 30),
(5, 5, 1, 30),
(6, 6, 1, 30),
(7, 7, 1, 30),
(8, 8, 1, 30),
(9, 9, 1, 30),
(10, 10, 1, 30),
(11, 11, 1, 30),
(12, 12, 1, 30),
(13, 13, 1, 30),
(14, 14, 1, 30),
(15, 15, 1, 30),
(16, 16, 1, 30),
(17, 17, 1, 30),
(18, 18, 1, 30),
(19, 19, 1, 30),
(20, 20, 1, 30),
(21, 21, 1, 30),
(22, 22, 1, 30),
(23, 23, 1, 30),
(24, 24, 1, 30),
(25, 25, 1, 30),
(26, 26, 1, 30),
(27, 167, 1, 30),
(28, 168, 1, 30),
(29, 169, 1, 30),
(30, 170, 1, 30),
(31, 171, 1, 30),
(32, 172, 1, 30),
(33, 173, 1, 30),
(34, 174, 1, 30),
(35, 27, 1, 30),
(36, 28, 1, 30),
(37, 29, 1, 30),
(38, 30, 1, 30),
(39, 31, 1, 30),
(40, 32, 1, 30),
(41, 33, 1, 30),
(42, 34, 1, 30),
(43, 35, 1, 30),
(44, 36, 1, 30),
(45, 37, 1, 30),
(46, 38, 1, 30),
(47, 39, 1, 30),
(48, 40, 1, 30),
(49, 41, 1, 30),
(50, 42, 1, 30),
(51, 43, 1, 30),
(52, 44, 1, 30),
(53, 45, 1, 30),
(54, 46, 1, 30),
(55, 47, 1, 30),
(56, 48, 1, 30),
(57, 49, 1, 30),
(58, 50, 1, 30),
(59, 51, 1, 30),
(60, 52, 1, 30),
(61, 53, 1, 30),
(62, 54, 1, 30),
(63, 55, 1, 30),
(64, 56, 1, 30),
(65, 57, 1, 30),
(66, 58, 1, 30),
(67, 59, 1, 30),
(68, 60, 1, 30),
(69, 61, 1, 30),
(70, 62, 1, 30),
(71, 63, 1, 30),
(72, 64, 1, 30),
(73, 65, 1, 30),
(74, 66, 1, 30),
(75, 67, 1, 30),
(76, 68, 1, 30),
(77, 69, 1, 30),
(78, 70, 1, 30),
(79, 71, 1, 30),
(80, 72, 1, 30),
(81, 73, 1, 30),
(82, 74, 1, 30),
(83, 75, 1, 30),
(84, 76, 1, 30),
(85, 77, 1, 30),
(86, 78, 1, 30),
(87, 79, 1, 30),
(88, 80, 1, 30),
(89, 81, 1, 30),
(90, 82, 1, 30),
(91, 83, 1, 30),
(92, 84, 1, 30),
(93, 85, 1, 30),
(94, 86, 1, 30),
(95, 87, 1, 30),
(96, 88, 1, 30),
(97, 89, 1, 30),
(98, 90, 1, 30),
(99, 91, 1, 30),
(100, 92, 1, 30),
(101, 93, 1, 30),
(102, 94, 1, 30),
(103, 95, 1, 30),
(104, 96, 1, 30),
(105, 97, 1, 30),
(106, 98, 1, 30),
(107, 99, 1, 30),
(108, 100, 1, 30),
(109, 101, 1, 30),
(110, 102, 1, 30),
(111, 103, 1, 30),
(112, 104, 1, 30),
(113, 105, 1, 30),
(114, 106, 1, 30),
(115, 107, 1, 30),
(116, 108, 1, 30),
(117, 109, 1, 30),
(118, 110, 1, 30),
(119, 111, 1, 30),
(120, 112, 1, 30),
(121, 113, 1, 30),
(122, 114, 1, 30),
(123, 115, 1, 30),
(124, 116, 1, 30),
(125, 117, 1, 30),
(126, 118, 1, 30),
(127, 119, 1, 30),
(128, 120, 1, 30),
(129, 121, 1, 30),
(130, 122, 1, 30),
(131, 123, 1, 30),
(132, 124, 1, 30),
(133, 125, 1, 30),
(134, 126, 1, 30),
(135, 127, 1, 30),
(136, 128, 1, 30),
(137, 129, 1, 30),
(138, 130, 1, 30),
(139, 131, 1, 30),
(140, 132, 1, 30),
(141, 133, 1, 30),
(142, 134, 1, 30),
(143, 135, 1, 30),
(144, 136, 1, 30),
(145, 137, 1, 30),
(146, 138, 1, 30),
(147, 139, 1, 30),
(148, 140, 1, 30),
(149, 141, 1, 30),
(150, 142, 1, 30),
(151, 143, 1, 30),
(152, 144, 1, 30),
(153, 145, 1, 30),
(154, 146, 1, 30),
(155, 147, 1, 30),
(156, 148, 1, 30),
(157, 149, 1, 30),
(158, 150, 1, 30),
(159, 151, 1, 30),
(160, 152, 1, 30),
(161, 153, 1, 30),
(162, 154, 1, 30),
(163, 155, 1, 30),
(164, 156, 1, 30),
(165, 157, 1, 30),
(166, 158, 1, 30),
(167, 159, 1, 30),
(168, 160, 1, 30),
(169, 161, 1, 30),
(170, 162, 1, 30),
(171, 163, 1, 30),
(172, 164, 1, 30),
(173, 165, 1, 30),
(174, 166, 1, 30);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
`course_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `slots` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `code`, `name`, `slots`) VALUES
(1, 'ACC101', 'Accounting Principles', 30),
(2, 'ACC302', 'Managerial Accouting', 30),
(3, 'ACC305', 'Financial Statement Analysis', 30),
(4, 'BKG304', 'Bank Lending', 30),
(5, 'CHN111', 'Chinese Elementary 1', 30),
(6, 'CHN122', 'Chinees Elementary 2', 30),
(7, 'CHN132', 'Chinese Elementary 3', 30),
(8, 'ECO111', 'Basic Micro Economics', 30),
(9, 'ECO121', 'Basic Macro Economics', 30),
(10, 'ETR401', 'Entrepreneurship', 30),
(11, 'FIM301', 'Valuation and Financial Modeling', 30),
(12, 'FIN201', 'Monetary Economics and Global Economy', 30),
(13, 'FIN202', 'Corporate Finance', 30),
(14, 'FIN301', 'Finanical Market & Institutions', 30),
(15, 'FIN303', 'Advanced Corporate Finance', 30),
(16, 'FIN402', 'Derivatives', 30),
(17, 'LAW101', 'Business Law Fundamentals', 30),
(18, 'MGT101', 'Introduction to Management', 30),
(19, 'MKT101', 'Marketing Principles', 30),
(20, 'MKT201', 'Consumer Behavior', 30),
(21, 'MKT202', 'Service Marketing Management', 30),
(22, 'MKT301', 'Marketing Research', 30),
(23, 'MKT304', 'Integrated Marketing Communications', 30),
(24, 'OBE101', 'Organization Behavior', 30),
(25, 'SSC101', 'Business Communication for BA', 30),
(26, 'SSG101', 'Working in Group for BA', 30),
(27, 'SSM201', 'Management Skills', 30),
(28, 'CSD201', 'Data Structures and Algorithms', 30),
(29, 'CSD202', 'Data structures and Algorithms (C++)', 30),
(30, 'CSD301', 'Advanced Algorithms', 30),
(31, 'CSI101', 'Introduction to Computing', 30),
(32, 'CSI102', 'Introduction to Computing for Business', 30),
(33, 'DBD301', 'Advanced Database', 30),
(34, 'DBI202', 'Introduction to Database', 30),
(35, 'NWC202', 'Networking', 30),
(36, 'OSG202', 'Operating Systems', 30),
(37, 'PRE201', 'Excel Programming', 30),
(38, 'PRF192', 'Programming fundamentals using C', 30),
(39, 'PRJ101', 'Core Java', 30),
(40, 'PRJ201', 'Advanced Java', 30),
(41, 'PRN292', 'C# and .NET', 30),
(42, 'PRO001', 'Learning to Program with Alice', 13),
(43, 'PRO191', 'Object Oriented Programming', 30),
(44, 'ENI201', 'Business English - Pre-Intermediate', 30),
(45, 'ENI301', 'Business English - Intermediate', 30),
(46, 'ENI401', 'Business English - Upper-Intermediate', 30),
(47, 'ENL111', 'Academic English 1', 30),
(48, 'ENL112', 'Advanced Academic English', 30),
(49, 'ENM201', 'Pre-Intermediate Business English', 30),
(50, 'ENM301', 'Intermediate Business English', 30),
(51, 'ENM401', 'Upper intermediate Business English', 30),
(52, 'ENT102', 'Top Notch 1', 70),
(53, 'ENT103', 'Top Notch 1 + Fundamentals', 100),
(54, 'ENT202', 'Top Notch 2', 70),
(55, 'ENT302', 'Top Notch 3', 70),
(56, 'ENT401', 'English 5 - Summit 1 from 2012', 70),
(57, 'ENT501', 'Summit 2 from Block 3-4 Fall 2011', 70),
(58, 'AET101', 'Aesthetic', 15),
(59, 'AFA201', 'Human Anatory for Artis', 30),
(60, 'DRF201', 'Drawing - Figure drawing', 30),
(61, 'DRP101', 'Drawing - Plaster Statue, Portrait', 30),
(62, 'DRS101', 'Drawing - Form, Still-life', 30),
(63, 'GDF101', 'Fundamental of Graphic Design', 30),
(64, 'HOA101', 'Art History', 30),
(65, 'HOD101', 'Design History', 15),
(66, 'VCM201', 'Visual Communication', 30),
(67, 'AIL301', 'Machine Learning', 30),
(68, 'CEA201', 'Computer Organization and Architecture', 30),
(69, 'CEC201', 'Circuits and Signals', 30),
(70, 'CGG201', 'Computer Graphics', 30),
(71, 'DGT201', 'Digital Fundamentals', 30),
(72, 'DGT301', 'Digital Signal Processing', 30),
(73, 'ECM391', 'Wireless Mobile Communications', 30),
(74, 'ECS211', 'Communications Systems 1', 30),
(75, 'EEA221', 'Electronics 2', 30),
(76, 'ESH201', 'Embedded System Hardware', 30),
(77, 'ESS301', 'Embedded Software Development', 30),
(78, 'ISC301', 'E-Commerce', 30),
(79, 'ISM301', 'ERP Fundamentals', 30),
(80, 'OFC311', 'Optical Communications', 30),
(81, 'PRM391', 'Mobile Programming', 30),
(82, 'PRX301', 'Advanced XML', 30),
(83, 'SCI201', 'Information Security', 30),
(84, 'SYB301', 'Start your business', 27),
(85, 'JIT301', 'Information Technology Japanese', 30),
(86, 'JPS112', 'Japanese Elementery 1', 30),
(87, 'JPS122', 'Japanese 2', 30),
(88, 'JPS132', 'Japanese 3', 30),
(89, 'JPS142', 'Japanese 4', 30),
(90, 'JPS152', 'Japanese 5', 30),
(91, 'JPS212', 'Japanese Intermediate 1', 60),
(92, 'MAA101', 'Advanced Mathematics 2', 30),
(93, 'MAC101', 'Advanced Mathematics 1', 30),
(94, 'MAC102', 'Advanced Mathematics for Business', 30),
(95, 'MAD111', 'Discrete Mathematics 1', 30),
(96, 'MAD121', 'Discrete Mathematics 2', 30),
(97, 'MAN201', 'Numerical Methods', 30),
(98, 'MAS201', 'Statistics & data processing', 30),
(99, 'MAS291', 'Probability and Applied Statistics', 30),
(100, 'PHY101', 'Physics', 30),
(101, 'COV111', 'Chess 1', 24),
(102, 'COV121', 'Chess 2', 24),
(103, 'COV131', 'Chess 3', 24),
(104, 'VOV112', 'Vovinam 1', 20),
(105, 'VOV113', 'Vovinam 2', 20),
(106, 'VOV122', 'Vovinam 3', 20),
(107, 'VOV123', 'Vovinam 4', 20),
(108, 'VOV132', 'Vovinam 5', 20),
(109, 'VOV133', 'Vovinam 6', 20),
(110, 'AVC101', 'Asia Culture', 13),
(111, 'HCM201', 'Ho Chi Minh Ideology', 12),
(112, 'MLN101', 'Principles of Marxism - Leninism', 30),
(113, 'SA', 'Self awareness', 6),
(114, 'SSC102', 'Business Communication', 30),
(115, 'VNR201', 'Revolutionary Lines of CPV', 18),
(116, 'HCI201', 'Human Computer Interaction', 30),
(117, 'ITE302', 'Ethics in Information Technology', 30),
(118, 'SWD391', 'Software Architecture and Design', 30),
(119, 'SWE102', 'Introduction to Software Engineering', 30),
(120, 'SWM301', 'IT Project Management', 30),
(121, 'SWQ391', 'Software Quality Assurance and Testing', 30),
(122, 'SWR301', 'Software Requirements', 30),
(123, 'ÐNG101', '?àn Nguy?t', 30),
(124, 'ÐNH101', '?àn Nh?', 30),
(125, 'ÐSA101', 'Sáo trúc', 30),
(126, 'ÐTR101', '?àn Tranh', 30),
(127, 'VNL111', 'Vietnamese - Fundamentals 1', 30),
(128, 'VNL121', 'Vietnamese Language 2', 30),
(129, 'VNL131', 'Vietnamese 3', 30);

-- --------------------------------------------------------

--
-- Table structure for table `course_semester`
--

DROP TABLE IF EXISTS `course_semester`;
CREATE TABLE IF NOT EXISTS `course_semester` (
`course_semester_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_semester`
--

INSERT INTO `course_semester` (`course_semester_id`, `course_id`, `semester_id`) VALUES
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
(39, 39, 1),
(40, 40, 1),
(41, 41, 1),
(42, 42, 1),
(43, 43, 1),
(44, 44, 1),
(45, 45, 1),
(46, 46, 1),
(47, 47, 1),
(48, 48, 1),
(49, 49, 1),
(50, 50, 1),
(51, 51, 1),
(52, 52, 1),
(53, 53, 1),
(54, 54, 1),
(55, 55, 1),
(56, 56, 1),
(57, 57, 1),
(58, 58, 1),
(59, 59, 1),
(60, 60, 1),
(61, 61, 1),
(62, 62, 1),
(63, 63, 1),
(64, 64, 1),
(65, 65, 1),
(66, 66, 1),
(67, 67, 1),
(68, 68, 1),
(69, 69, 1),
(70, 70, 1),
(71, 71, 1),
(72, 72, 1),
(73, 73, 1),
(74, 74, 1),
(75, 75, 1),
(76, 76, 1),
(77, 77, 1),
(78, 78, 1),
(79, 79, 1),
(80, 80, 1),
(81, 81, 1),
(82, 82, 1),
(83, 83, 1),
(84, 84, 1),
(85, 85, 1),
(86, 86, 1),
(87, 87, 1),
(88, 88, 1),
(89, 89, 1),
(90, 90, 1),
(91, 91, 1),
(92, 92, 1),
(93, 93, 1),
(94, 94, 1),
(95, 95, 1),
(96, 96, 1),
(97, 97, 1),
(98, 98, 1),
(99, 99, 1),
(100, 100, 1),
(101, 101, 1),
(102, 102, 1),
(103, 103, 1),
(104, 104, 1),
(105, 105, 1),
(106, 106, 1),
(107, 107, 1),
(108, 108, 1),
(109, 109, 1),
(110, 110, 1),
(111, 111, 1),
(112, 112, 1),
(113, 113, 1),
(114, 114, 1),
(115, 115, 1),
(116, 116, 1),
(117, 117, 1),
(118, 118, 1),
(119, 119, 1),
(120, 120, 1),
(121, 121, 1),
(122, 122, 1),
(123, 123, 1),
(124, 124, 1),
(125, 125, 1),
(126, 126, 1),
(127, 127, 1),
(128, 128, 1),
(129, 129, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `code`, `room_type`, `capacity`) VALUES
(1, 'P103', 'Class room', 30),
(2, 'P105', 'Class room', 32),
(3, 'P107', 'Class room', 24),
(4, 'P110', 'Class room', 30),
(5, 'P113', 'Class room', 32),
(6, 'P115', 'Class room', 32),
(7, 'P122', 'Meeting room', 32),
(8, 'P125', 'Class room', 30),
(9, 'P127', 'Class room', 30),
(10, 'P128', 'Theoretical theatre', 70),
(11, 'P129', 'Class room', 30),
(12, 'P131', 'Class room', 30),
(13, 'P133', 'Class room', 30),
(14, 'P135', 'Class room', 30),
(15, 'P137', 'Class room', 30),
(16, 'P139', 'Class room', 30),
(17, 'P202', 'Theoretical theatre', 130),
(18, 'P203', 'Meeting room', 35),
(19, 'P204', 'Theoretical theatre', 130),
(20, 'P206', 'Class room', 30),
(21, 'P208', 'Class room', 30),
(22, 'P210', 'Class room', 30),
(23, 'P212', 'Class room', 30),
(24, 'P213', 'Class room', 30),
(25, 'P214', 'Class room', 30),
(26, 'P215', 'Class room', 30),
(27, 'P216', 'Class room', 30),
(28, 'P217', 'Class room', 30),
(29, 'P218', 'Class room', 30),
(30, 'P219', 'Class room', 30),
(31, 'P220', 'Class room', 30),
(32, 'P221', 'Class room', 30),
(33, 'P223', 'Class room', 30),
(34, 'P225', 'Class room', 30),
(35, 'P227', 'Class room', 30),
(36, 'P228', 'Class room', 30),
(37, 'P229', 'Class room', 30),
(38, 'P230', 'Class room', 30),
(39, 'P231', 'Class room', 30),
(40, 'P232', 'Class room', 30),
(41, 'P233', 'Class room', 30),
(42, 'P234', 'Class room', 30),
(43, 'P235', 'Class room', 30),
(44, 'P236', 'Class room', 30),
(45, 'P237', 'Class room', 30),
(46, 'P239', 'Class room', 30),
(47, 'P412', 'Class room', 30),
(48, 'P413', 'Class room', 30),
(49, 'P414', 'Class room', 30),
(50, 'P415', 'Class room', 30),
(51, 'P416', 'Class room', 30),
(52, 'P417', 'Class room', 30),
(53, 'P418', 'Class room', 30),
(54, 'P419', 'Class room', 30);

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
(1, 'SM15', 'Summer2015', 2015, '2015-11-05', '2017-06-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `account`, `name`, `email`, `account_type`) VALUES
(1, 'AnhNN3 ', 'AnhNN3 ', 'AnhNN3@fpt.edu.vn ', 'teacher'),
(2, 'AnhNTP', 'AnhNTP', 'AnhNTP@fpt.edu.vn', 'teacher'),
(3, 'AnhPN ', 'AnhPN ', 'AnhPN@fpt.edu.vn ', 'teacher'),
(4, 'AnhPT', 'AnhPT ', 'AnhPT@fpt.edu.vn ', 'teacher'),
(5, 'BangBH ', 'BangBH ', 'BangBH@fpt.edu.vn ', 'teacher'),
(6, 'BinhHN ', 'BinhHN ', 'BinhHN@fpt.edu.vn ', 'teacher'),
(7, 'BinhNV2 ', 'BinhNV2 ', 'BinhNV2@fpt.edu.vn ', 'teacher'),
(8, 'CamNV ', 'CamNV ', 'CamNV@fpt.edu.vn ', 'teacher'),
(9, 'CauPD', 'CauPD ', 'CauPD@fpt.edu.vn ', 'teacher'),
(10, 'ChienBD ', 'ChienBD ', 'ChienBD@fpt.edu.vn ', 'teacher'),
(11, 'ChiLP ', 'ChiLP ', 'ChiLP@fpt.edu.vn ', 'teacher'),
(12, 'ChinhVV ', 'ChinhVV ', 'ChinhVV@fpt.edu.vn ', 'teacher'),
(13, 'CongNM ', 'CongNM ', 'CongNM@fpt.edu.vn ', 'teacher'),
(14, 'CuongN ', 'CuongN ', 'CuongN@fpt.edu.vn ', 'teacher'),
(15, 'Darlenne ', 'Darlenne ', 'Darlenne@fpt.edu.vn ', 'teacher'),
(16, 'DiepDN ', 'DiepDN ', 'DiepDN@fpt.edu.vn ', 'teacher'),
(17, 'DiepPTB ', 'DiepPTB ', 'DiepPTB@fpt.edu.vn ', 'teacher'),
(18, 'DoLQ ', 'DoLQ ', 'DoLQ@fpt.edu.vn ', 'teacher'),
(19, 'DuanTC', 'DuanTC', 'DuanTC@fpt.edu.vn', 'teacher'),
(20, 'DungDV', 'DungDV', 'DungDV@fpt.edu.vn', 'teacher'),
(21, 'DungHA ', 'DungHA ', 'DungHA@fpt.edu.vn', 'teacher'),
(22, 'DungLM ', 'DungLM ', 'DungLM@fpt.edu.vn ', 'teacher'),
(23, 'DungND ', 'DungND ', 'DungND@fpt.edu.vn ', 'teacher'),
(24, 'DungNT ', 'DungNT ', 'DungNT@fpt.edu.vn ', 'teacher'),
(25, 'DungNT3', 'DungNT3', 'DungNT3@fpt.edu.vn', 'teacher'),
(26, 'DungNT6 ', 'DungNT6 ', 'DungNT6@fpt.edu.vn ', 'teacher'),
(27, 'DuongTB ', 'DuongTB ', 'DuongTB@fpt.edu.vn ', 'teacher'),
(28, 'duydt ', 'duydt', 'duydt@fpt.edu.vn', 'teacher'),
(29, 'Guuswink ', 'Guuswink ', 'Guuswink@fpt.edu.vn ', 'teacher'),
(30, 'HaDT2', 'HaDT2', 'HaDT2@fpt.edu.vn', 'teacher'),
(31, 'HaDTT ', 'HaDTT ', 'HaDTT@fpt.edu.vn ', 'teacher'),
(32, 'HaiNM', 'HaiNM', 'HaiNM@fpt.edu.vn', 'teacher'),
(33, 'HangNL ', 'HangNL ', 'HangNL@fpt.edu.vn ', 'teacher'),
(34, 'HangNT ', 'HangNT ', 'HangNT@fpt.edu.vn ', 'teacher'),
(35, 'HangTTT2 ', 'HangTTT2 ', 'HangTTT2@fpt.edu.vn ', 'teacher'),
(36, 'HanhTH ', 'HanhTH ', 'HanhTH@fpt.edu.vn ', 'teacher'),
(37, 'HaPTP ', 'HaPTP ', 'HaPTP@fpt.edu.vn ', 'teacher'),
(38, 'HienDTT ', 'HienDTT ', 'HienDTT@fpt.edu.vn ', 'teacher'),
(39, 'HienNTT ', 'HienNTT ', 'HienNTT@fpt.edu.vn ', 'teacher'),
(40, 'HienVT2', 'HienVT2', 'HienVT2@fpt.edu.vn', 'teacher'),
(41, 'HiepVTB ', 'HiepVTB ', 'HiepVTB@fpt.edu.vn ', 'teacher'),
(42, 'HieuLD2', 'HieuLD2 ', 'HieuLD2@fpt.edu.vn ', 'teacher'),
(43, 'HoaHQ2 ', 'HoaHQ2 ', 'HoaHQ2@fpt.edu.vn ', 'teacher'),
(44, 'HoaiNT3', 'HoaiNT3', 'HoaiNT3@fpt.edu.vn', 'teacher'),
(45, 'HoaNTQ ', 'HoaNTQ ', 'HoaNTQ@fpt.edu.vn ', 'teacher'),
(46, 'HungLV ', 'HungLV ', 'HungLV@fpt.edu.vn ', 'teacher'),
(47, 'HungPD', 'HungPD ', 'HungPD@fpt.edu.vn ', 'teacher'),
(48, 'HuongDT ', 'HuongDT ', 'HuongDT@fpt.edu.vn ', 'teacher'),
(49, 'HuongKT ', 'HuongKT ', 'HuongKT@fpt.edu.vn ', 'teacher'),
(50, 'HuongNT2 ', 'HuongNT2 ', 'HuongNT2@fpt.edu.vn ', 'teacher'),
(51, 'HuongNT7 ', 'HuongNT7 ', 'HuongNT7@fpt.edu.vn', 'teacher'),
(52, 'HuongTTT3 ', 'HuongTTT3 ', 'HuongTTT3@fpt.edu.vn ', 'teacher'),
(53, 'HuyenTT ', 'HuyenTT ', 'HuyenTT@fpt.edu.vn ', 'teacher'),
(54, 'Jet ', 'Jet ', 'Jet@fpt.edu.vn ', 'teacher'),
(55, 'KhaDK ', 'KhaDK ', 'KhaDK@fpt.edu.vn ', 'teacher'),
(56, 'KhuongPD ', 'KhuongPD ', 'KhuongPD@fpt.edu.vn ', 'teacher'),
(57, 'KienLT ', 'KienLT ', 'KienLT@fpt.edu.vn ', 'teacher'),
(58, 'KienVC ', 'KienVC ', 'KienVC@fpt.edu.vn ', 'teacher'),
(59, 'LamPT', 'LamPT', 'LamPT@fpt.edu.vn', 'teacher'),
(60, 'LanNTM', 'LanNTM', 'LanNTM@fpt.edu.vn', 'teacher'),
(61, 'LanNTT', 'LanNTT ', 'LanNTT@fpt.edu.vn ', 'teacher'),
(62, 'LanTV', 'LanTV', 'LanTV@fpt.edu.vn', 'teacher'),
(63, 'LapPT ', 'LapPT ', 'LapPT@fpt.edu.vn ', 'teacher'),
(64, 'LienNP', 'LienNP', 'LienNP@fpt.edu.vn', 'teacher'),
(65, 'LienNQ', 'LienNQ', 'LienNQ@fpt.edu.vn', 'teacher'),
(66, 'LinhLHM ', 'LinhLHM ', 'LinhLHM@fpt.edu.vn ', 'teacher'),
(67, 'LinhPT3', 'LinhPT3', 'LinhPT3@fpt.edu.vn', 'teacher'),
(68, 'LoanNTP', 'LoanNTP', 'LoanNTP@fpt.edu.vn', 'teacher'),
(69, 'LongDH ', 'LongDH', 'LongDH@fpt.edu.vn', 'teacher'),
(70, 'LuDTH', 'LuDTH', 'LuDTH@fpt.edu.vn', 'teacher'),
(71, 'MaiTT ', 'MaiTT ', 'MaiTT@fpt.edu.vn ', 'teacher'),
(72, 'MaiVTT ', 'MaiVTT ', 'MaiVTT@fpt.edu.vn ', 'teacher'),
(73, 'MaoPV', 'MaoPV', 'MaoPV@fpt.edu.vn', 'teacher'),
(74, 'NaHA ', 'NaHA ', 'NaHA@fpt.edu.vn ', 'teacher'),
(75, 'Nam', 'Nam', 'Nam@fpt.edu.vn', 'teacher'),
(76, 'NamKV ', 'NamKV ', 'NamKV@fpt.edu.vn ', 'teacher'),
(77, 'NamTG', 'NamTG', 'NamTG@fpt.edu.vn', 'teacher'),
(78, 'NgaNTT', 'NgaNTT', 'NgaNTT@fpt.edu.vn', 'teacher'),
(79, 'NghiaHT', 'NghiaHT', 'NghiaHT@fpt.edu.vn', 'teacher'),
(80, 'NguyenLTT', 'NguyenLTT', 'NguyenLTT@fpt.edu.vn', 'teacher'),
(81, 'NguyetNN', 'NguyetNN', 'NguyetNN@fpt.edu.vn', 'teacher'),
(82, 'NguyetNT2', 'NguyetNT2', 'NguyetNT2@fpt.edu.vn', 'teacher'),
(83, 'NguyetNTM ', 'NguyetNTM', 'NguyetNTM@fpt.edu.vn', 'teacher'),
(84, 'NhienNT ', 'NhienNT ', 'NhienNT@fpt.edu.vn ', 'teacher'),
(85, 'NhungBH ', 'NhungBH', 'NhungBH@fpt.edu.vn', 'teacher'),
(86, 'PhuDV ', 'PhuDV ', 'PhuDV@fpt.edu.vn', 'teacher'),
(87, 'PhuongLH2', 'PhuongLH2', 'PhuongLH2@fpt.edu.vn', 'teacher'),
(88, 'PhuongNT ', 'PhuongNT ', 'PhuongNT@fpt.edu.vn ', 'teacher'),
(89, 'QuanNN', 'QuanNN', 'QuanNN@fpt.edu.vn', 'teacher'),
(90, 'QuyenTTL', 'QuyenTTL', 'QuyenTTL@fpt.edu.vn', 'teacher'),
(91, 'QuynhNTX ', 'QuynhNTX ', 'QuynhNTX@fpt.edu.vn ', 'teacher'),
(92, 'QuyPH ', 'QuyPH ', 'QuyPH@fpt.edu.vn ', 'teacher'),
(93, 'SangNV ', 'SangNV', 'SangNV@fpt.edu.vn', 'teacher'),
(94, 'Setaita ', 'Setaita ', 'Setaita@fpt.edu.vn ', 'teacher'),
(95, 'SonHN ', 'SonHN ', 'SonHN@fpt.edu.vn ', 'teacher'),
(96, 'SonHX', 'SonHX', 'SonHX@fpt.edu.vn', 'teacher'),
(97, 'Susana', 'Susana ', 'Susana@fpt.edu.vn ', 'teacher'),
(98, 'TamNT', 'TamNT ', 'TamNT@fpt.edu.vn ', 'teacher'),
(99, 'TanNTT', 'TanNTT', 'TanNTT@fpt.edu.vn', 'teacher'),
(100, 'ThangNB ', 'ThangNB ', 'ThangNB@fpt.edu.vn ', 'teacher'),
(101, 'ThanhNN', 'ThanhNN ', 'ThanhNN@fpt.edu.vn', 'teacher'),
(102, 'ThienNV ', 'ThienNV ', 'ThienNV@fpt.edu.vn ', 'teacher'),
(103, 'ThuLX', 'ThuLX', 'ThuLX@fpt.edu.vn', 'teacher'),
(104, 'ThuyDTP', 'ThuyDTP ', 'ThuyDTP@fpt.edu.vn ', 'teacher'),
(105, 'ThuyNT8', 'ThuyNT8', 'ThuyNT8@fpt.edu.vn', 'teacher'),
(106, 'TinhLT2 ', 'TinhLT2 ', 'TinhLT2@fpt.edu.vn', 'teacher'),
(107, 'TrangBT4 ', 'TrangBT4 ', 'TrangBT4@fpt.edu.vn ', 'teacher'),
(108, 'TrangLQ ', 'TrangLQ ', 'TrangLQ@fpt.edu.vn ', 'teacher'),
(109, 'TrangLTT', 'TrangLTT', 'TrangLTT@fpt.edu.vn', 'teacher'),
(110, 'TrangNTT3', 'TrangNTT3', 'TrangNTT3@fpt.edu.vn', 'teacher'),
(111, 'TrangTK', 'TrangTK', 'TrangTK@fpt.edu.vn', 'teacher'),
(112, 'TraTT', 'TraTT', 'TraTT@fpt.edu.vn', 'teacher'),
(113, 'TriTD', 'TriTD', 'TriTD@fpt.edu.vn', 'teacher'),
(114, 'TrungCV', 'TrungCV', 'TrungCV@fpt.edu.vn', 'teacher'),
(115, 'TrungDT', 'TrungDT ', 'TrungDT@fpt.edu.vn ', 'teacher'),
(116, 'TrungNN', 'TrungNN', 'TrungNN@fpt.edu.vn', 'teacher'),
(117, 'TrungNT', 'TrungNT', 'TrungNT@fpt.edu.vn', 'teacher'),
(118, 'TuanHM', 'TuanHM', 'TuanHM@fpt.edu.vn', 'teacher'),
(119, 'TuanTD', 'TuanTD', 'TuanTD@fpt.edu.vn', 'teacher'),
(120, 'TuanVM ', 'TuanVM ', 'TuanVM@fpt.edu.vn ', 'teacher'),
(121, 'TungPT2', 'TungPT2', 'TungPT2@fpt.edu.vn', 'teacher'),
(122, 'TuVT', 'TuVT', 'TuVT@fpt.edu.vn', 'teacher'),
(123, 'TuyetLTA', 'TuyetLTA', 'TuyetLTA@fpt.edu.vn', 'teacher'),
(124, 'VanDTH', 'VanDTH', 'VanDTH@fpt.edu.vn', 'teacher'),
(125, 'VanDTT', 'VanDTT ', 'VanDTT@fpt.edu.vn ', 'teacher'),
(126, 'VietNK', 'VietNK', 'VietNK@fpt.edu.vn', 'teacher'),
(127, 'VinhPT', 'VinhPT', 'VinhPT@fpt.edu.vn', 'teacher'),
(128, 'VuDA', 'VuDA', 'VuDA@fpt.edu.vn', 'teacher'),
(129, 'VuNH', 'VuNH', 'VuNH@fpt.edu.vn', 'teacher'),
(130, 'XuanLT', 'XuanLT', 'XuanLT@fpt.edu.vn', 'teacher');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_busy_semester`
--

DROP TABLE IF EXISTS `teacher_busy_semester`;
CREATE TABLE IF NOT EXISTS `teacher_busy_semester` (
`teacher_busy_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `date_busy` date NOT NULL,
  `slot_busy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teacher_course_semester`
--

DROP TABLE IF EXISTS `teacher_course_semester`;
CREATE TABLE IF NOT EXISTS `teacher_course_semester` (
`course_teacher_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teacher_semester`
--

DROP TABLE IF EXISTS `teacher_semester`;
CREATE TABLE IF NOT EXISTS `teacher_semester` (
`teacher_semester_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

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
(39, 39, 1),
(40, 40, 1),
(41, 41, 1),
(42, 42, 1),
(43, 43, 1),
(44, 44, 1),
(45, 45, 1),
(46, 46, 1),
(47, 47, 1),
(48, 48, 1),
(49, 49, 1),
(50, 50, 1),
(51, 51, 1),
(52, 52, 1),
(53, 53, 1),
(54, 54, 1),
(55, 55, 1),
(56, 56, 1),
(57, 57, 1),
(58, 58, 1),
(59, 59, 1),
(60, 60, 1),
(61, 61, 1),
(62, 62, 1),
(63, 63, 1),
(64, 64, 1),
(65, 65, 1),
(66, 66, 1),
(67, 67, 1),
(68, 68, 1),
(69, 69, 1),
(70, 70, 1),
(71, 71, 1),
(72, 72, 1),
(73, 73, 1),
(74, 74, 1),
(75, 75, 1),
(76, 76, 1),
(77, 77, 1),
(78, 78, 1),
(79, 79, 1),
(80, 80, 1),
(81, 81, 1),
(82, 82, 1),
(83, 83, 1),
(84, 84, 1),
(85, 85, 1),
(86, 86, 1),
(87, 87, 1),
(88, 88, 1),
(89, 89, 1),
(90, 90, 1),
(91, 91, 1),
(92, 92, 1),
(93, 93, 1),
(94, 94, 1),
(95, 95, 1),
(96, 96, 1),
(97, 97, 1),
(98, 98, 1),
(99, 99, 1),
(100, 100, 1),
(101, 101, 1),
(102, 102, 1),
(103, 103, 1),
(104, 104, 1),
(105, 105, 1),
(106, 106, 1),
(107, 107, 1),
(108, 108, 1),
(109, 109, 1),
(110, 110, 1),
(111, 111, 1),
(112, 112, 1),
(113, 113, 1),
(114, 114, 1),
(115, 115, 1),
(116, 116, 1),
(117, 117, 1),
(118, 118, 1),
(119, 119, 1),
(120, 120, 1),
(121, 121, 1),
(122, 122, 1),
(123, 123, 1),
(124, 124, 1),
(125, 125, 1),
(126, 126, 1),
(127, 127, 1),
(128, 128, 1),
(129, 129, 1),
(130, 130, 1);

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
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
 ADD PRIMARY KEY (`class_id`), ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
 ADD PRIMARY KEY (`class_course_semester_id`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`);

--
-- Indexes for table `class_course_teacher_semester`
--
ALTER TABLE `class_course_teacher_semester`
 ADD PRIMARY KEY (`class_course_semester_id`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`), ADD KEY `teacher_semester_id` (`teacher_semester_id`);

--
-- Indexes for table `class_semester`
--
ALTER TABLE `class_semester`
 ADD PRIMARY KEY (`class_semester_id`), ADD UNIQUE KEY `class_id` (`class_id`,`semester_id`), ADD KEY `semester_id` (`semester_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
 ADD PRIMARY KEY (`course_id`), ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `course_semester`
--
ALTER TABLE `course_semester`
 ADD PRIMARY KEY (`course_semester_id`), ADD UNIQUE KEY `course_id` (`course_id`,`semester_id`), ADD KEY `semester_id` (`semester_id`);

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
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
 ADD PRIMARY KEY (`teacher_id`), ADD UNIQUE KEY `account` (`account`), ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `teacher_busy_semester`
--
ALTER TABLE `teacher_busy_semester`
 ADD PRIMARY KEY (`teacher_busy_semester_id`), ADD UNIQUE KEY `teacher_semester_id` (`teacher_semester_id`);

--
-- Indexes for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
 ADD PRIMARY KEY (`course_teacher_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`), ADD KEY `teacher_semester_id` (`teacher_semester_id`);

--
-- Indexes for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
 ADD PRIMARY KEY (`teacher_semester_id`), ADD UNIQUE KEY `teacher_id` (`teacher_id`,`semester_id`), ADD KEY `semester_id` (`semester_id`);

--
-- Indexes for table `time_table`
--
ALTER TABLE `time_table`
 ADD PRIMARY KEY (`time_table_id`), ADD UNIQUE KEY `date` (`date`,`slot`,`room_id`), ADD UNIQUE KEY `date_2` (`date`,`slot`,`class_course_semester_id`), ADD KEY `class_course_semester_id` (`class_course_semester_id`), ADD KEY `room_id` (`room_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=175;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `class_course_teacher_semester`
--
ALTER TABLE `class_course_teacher_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `class_semester`
--
ALTER TABLE `class_semester`
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=175;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=130;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=130;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=55;
--
-- AUTO_INCREMENT for table `semesters`
--
ALTER TABLE `semesters`
MODIFY `semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=131;
--
-- AUTO_INCREMENT for table `teacher_busy_semester`
--
ALTER TABLE `teacher_busy_semester`
MODIFY `teacher_busy_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
MODIFY `course_teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
MODIFY `teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=131;
--
-- AUTO_INCREMENT for table `time_table`
--
ALTER TABLE `time_table`
MODIFY `time_table_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
ADD CONSTRAINT `class_course_semester_ibfk_1` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`),
ADD CONSTRAINT `class_course_semester_ibfk_2` FOREIGN KEY (`course_semester_id`) REFERENCES `course_semester` (`course_semester_id`);

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
-- Constraints for table `course_semester`
--
ALTER TABLE `course_semester`
ADD CONSTRAINT `course_semester_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `course_semester_ibfk_2` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`);

--
-- Constraints for table `teacher_busy_semester`
--
ALTER TABLE `teacher_busy_semester`
ADD CONSTRAINT `teacher_busy_semester_ibfk_1` FOREIGN KEY (`teacher_semester_id`) REFERENCES `teacher_semester` (`teacher_semester_id`);

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
ADD CONSTRAINT `time_table_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
