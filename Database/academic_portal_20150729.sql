-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2015 at 05:26 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `code`, `type`, `specialized_id`, `detail_specialized_id`, `course_id`, `batch`, `batch_char`, `number`) VALUES
(4, 'IS10801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(5, 'IS20801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(6, 'ES10801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(7, 'JS0801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(8, 'JS0802', 'Specialized', NULL, NULL, NULL, 8, 'A', 2),
(9, 'IS10802', 'Specialized', NULL, NULL, NULL, 8, 'A', 2),
(10, 'IS20802', 'Specialized', NULL, NULL, NULL, 8, 'A', 2),
(11, 'ES20802', 'Specialized', NULL, NULL, NULL, 8, 'A', 2),
(12, 'JS0803', 'Specialized', NULL, NULL, NULL, 8, 'A', 3),
(13, 'CS0801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(14, 'EC0802', 'Specialized', NULL, NULL, NULL, 8, 'A', 2),
(15, 'ISE0801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(16, 'IS10803', 'Specialized', NULL, NULL, NULL, 8, 'A', 3),
(17, 'IS20803', 'Specialized', NULL, NULL, NULL, 8, 'A', 3),
(18, 'ES20803', 'Specialized', NULL, NULL, NULL, 8, 'A', 3),
(19, 'ES20804', 'Specialized', NULL, NULL, NULL, 8, 'A', 4),
(20, 'JS0804', 'Specialized', NULL, NULL, NULL, 8, 'A', 4),
(21, 'JS0805', 'Specialized', NULL, NULL, NULL, 8, 'A', 5),
(22, 'SE0904', 'Specialized', NULL, NULL, NULL, 9, 'A', 4),
(23, 'SE0906', 'Specialized', NULL, NULL, NULL, 9, 'A', 6),
(24, 'SE0905', 'Specialized', NULL, NULL, NULL, 9, 'A', 5),
(25, 'IA10901', 'Specialized', NULL, NULL, NULL, 9, 'A', 1),
(26, 'IA20901', 'Specialized', NULL, NULL, NULL, 9, 'A', 1),
(27, 'GD0901', 'Specialized', NULL, NULL, NULL, 9, 'A', 1),
(28, 'SE0907', 'Specialized', NULL, NULL, NULL, 9, 'A', 7),
(29, 'SE0908', 'Specialized', NULL, NULL, NULL, 9, 'A', 8),
(30, 'SE0909', 'Specialized', NULL, NULL, NULL, 9, 'A', 9),
(31, 'SE0910', 'Specialized', NULL, NULL, NULL, 9, 'A', 10),
(32, 'SE0911', 'Specialized', NULL, NULL, NULL, 9, 'A', 11),
(33, 'PRN292.M', 'Course', NULL, NULL, NULL, NULL, '\0', 0),
(34, 'EC0902', 'Specialized', NULL, NULL, NULL, 9, 'A', 2),
(35, 'IA0902', 'Specialized', NULL, NULL, NULL, 9, 'A', 2),
(36, 'IA0903', 'Specialized', NULL, NULL, NULL, 9, 'A', 3),
(37, 'GD0902', 'Specialized', NULL, NULL, NULL, 9, 'A', 2),
(38, 'SE1001', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(39, 'SE1002', 'Specialized', NULL, NULL, NULL, 10, 'A', 2),
(40, 'SE1003', 'Specialized', NULL, NULL, NULL, 10, 'A', 3),
(41, 'EC1001', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(42, 'IA1001', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(43, 'JPN1001 & JPN1002', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(44, 'SE1004', 'Specialized', NULL, NULL, NULL, 10, 'A', 4),
(45, 'SE1005', 'Specialized', NULL, NULL, NULL, 10, 'A', 5),
(46, 'SE1006', 'Specialized', NULL, NULL, NULL, 10, 'A', 6),
(47, 'IA1002', 'Specialized', NULL, NULL, NULL, 10, 'A', 2),
(48, 'ISE1001', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(49, 'GD1001', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(50, 'SE1007', 'Specialized', NULL, NULL, NULL, 10, 'A', 7),
(51, 'SE1008', 'Specialized', NULL, NULL, NULL, 10, 'A', 8),
(52, 'SE1009', 'Specialized', NULL, NULL, NULL, 10, 'A', 9),
(53, 'SE1010', 'Specialized', NULL, NULL, NULL, 10, 'A', 10),
(54, 'SE1011', 'Specialized', NULL, NULL, NULL, 10, 'A', 11),
(55, 'SE1012', 'Specialized', NULL, NULL, NULL, 10, 'A', 12),
(56, 'EC1003', 'Specialized', NULL, NULL, NULL, 10, 'A', 3),
(57, 'IA1003', 'Specialized', NULL, NULL, NULL, 10, 'A', 3),
(58, 'IA1004', 'Specialized', NULL, NULL, NULL, 10, 'A', 4),
(59, 'GD1002', 'Specialized', NULL, NULL, NULL, 10, 'A', 2),
(60, 'ISE1002', 'Specialized', NULL, NULL, NULL, 10, 'A', 2),
(61, 'BIS', 'Course', NULL, NULL, 204, NULL, '\0', 0),
(62, 'FIN0801&MKT0801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(63, 'COB0801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(64, 'COF0801', 'Specialized', NULL, NULL, NULL, 8, 'A', 1),
(65, 'MKT0802', 'Specialized', NULL, NULL, NULL, 8, 'A', 2),
(66, 'COF0802&COB0802&FIN0802', 'Specialized', NULL, NULL, NULL, 8, 'A', 2),
(67, 'FIN0803', 'Specialized', NULL, NULL, NULL, 8, 'A', 3),
(68, 'MKT0803', 'Specialized', NULL, NULL, NULL, 8, 'A', 3),
(69, 'COF0803', 'Specialized', NULL, NULL, NULL, 8, 'A', 3),
(70, 'COF0804', 'Specialized', NULL, NULL, NULL, 8, 'A', 4),
(71, 'MKT0902', 'Specialized', NULL, NULL, NULL, 9, 'A', 2),
(72, 'FIN0902', 'Specialized', NULL, NULL, NULL, 9, 'A', 2),
(73, 'COF0902', 'Specialized', NULL, NULL, NULL, 9, 'A', 2),
(74, 'MKT0903', 'Specialized', NULL, NULL, NULL, 9, 'A', 3),
(75, 'FIN0903', 'Specialized', NULL, NULL, NULL, 9, 'A', 3),
(76, 'COF0903&FIN0903', 'Specialized', NULL, NULL, NULL, 9, 'A', 3),
(77, 'BA1001', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(78, 'FB1001', 'Specialized', NULL, NULL, NULL, 10, 'A', 1),
(79, 'BA1002&FB1002', 'Specialized', NULL, NULL, NULL, 10, 'A', 2),
(80, 'IBA1002', 'Specialized', NULL, NULL, NULL, 10, 'A', 2),
(81, 'BA1003', 'Specialized', NULL, NULL, NULL, 10, 'A', 3),
(82, 'FB1003', 'Specialized', NULL, NULL, NULL, 10, 'A', 3),
(83, 'IBA1003', 'Specialized', NULL, NULL, NULL, 10, 'A', 3);

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
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_course_semester`
--

INSERT INTO `class_course_semester` (`class_course_semester_id`, `class_semester_id`, `course_semester_id`, `block_condition`, `is_semester_long`) VALUES
(1, 1, 224, 0, 1),
(2, 1, 129, 0, 1),
(3, 1, 194, 0, 1),
(4, 2, 196, 0, 1),
(5, 2, 224, 0, 1),
(6, 2, 129, 0, 1),
(7, 3, 224, 0, 1),
(8, 3, 194, 0, 1),
(9, 3, 186, 0, 1),
(10, 4, 206, 0, 1),
(11, 4, 193, 0, 1),
(12, 5, 206, 0, 1),
(13, 5, 193, 0, 1),
(14, 6, 123, 0, 1),
(15, 6, 181, 0, 1),
(16, 6, 227, 0, 1),
(17, 6, 180, 0, 1),
(18, 6, 239, 0, 1),
(19, 7, 180, 0, 1),
(20, 7, 227, 0, 1),
(21, 7, 239, 0, 1),
(22, 7, 181, 0, 1),
(23, 7, 123, 0, 1),
(24, 8, 189, 0, 1),
(25, 9, 205, 0, 1),
(26, 9, 200, 0, 1),
(27, 9, 227, 0, 1),
(28, 10, 145, 0, 1),
(29, 10, 146, 0, 1),
(30, 10, 143, 0, 1),
(31, 11, 198, 0, 1),
(32, 11, 199, 0, 1),
(33, 12, 181, 0, 1),
(34, 12, 239, 0, 1),
(35, 12, 123, 0, 1),
(36, 12, 180, 0, 1),
(37, 12, 227, 0, 1),
(38, 13, 186, 0, 1),
(39, 13, 228, 0, 1),
(40, 13, 89, 0, 1),
(41, 13, 179, 0, 1),
(42, 13, 225, 0, 1),
(43, 14, 228, 0, 1),
(44, 14, 179, 0, 1),
(45, 14, 89, 0, 1),
(46, 14, 225, 0, 1),
(47, 14, 191, 0, 1),
(48, 15, 225, 0, 1),
(49, 15, 184, 0, 1),
(50, 15, 179, 0, 1),
(51, 15, 228, 0, 1),
(52, 15, 190, 0, 1),
(53, 16, 190, 0, 1),
(54, 16, 225, 0, 1),
(55, 16, 184, 0, 1),
(56, 16, 228, 0, 1),
(57, 16, 179, 0, 1),
(58, 17, 239, 0, 1),
(59, 17, 202, 0, 1),
(60, 17, 228, 0, 1),
(61, 17, 203, 0, 1),
(62, 18, 202, 0, 1),
(63, 18, 228, 0, 1),
(64, 18, 203, 0, 1),
(65, 18, 239, 0, 1),
(66, 19, 219, 0, 1),
(67, 19, 230, 0, 1),
(68, 19, 229, 0, 1),
(69, 19, 201, 0, 1),
(70, 19, 140, 0, 1),
(71, 20, 201, 0, 1),
(72, 20, 229, 0, 1),
(73, 20, 219, 0, 1),
(74, 20, 140, 0, 1),
(75, 20, 230, 0, 1),
(76, 21, 201, 0, 1),
(77, 21, 230, 0, 1),
(78, 21, 140, 0, 1),
(79, 21, 229, 0, 1),
(80, 21, 219, 0, 1),
(81, 22, 232, 0, 1),
(82, 22, 234, 0, 1),
(83, 22, 233, 0, 1),
(84, 22, 235, 0, 1),
(85, 22, 236, 0, 1),
(86, 23, 233, 0, 1),
(87, 23, 235, 0, 1),
(88, 23, 236, 0, 1),
(89, 23, 232, 0, 1),
(90, 23, 234, 0, 1),
(91, 24, 173, 0, 1),
(92, 24, 184, 0, 1),
(93, 24, 115, 0, 1),
(94, 24, 175, 0, 1),
(95, 25, 127, 0, 1),
(96, 25, 125, 0, 1),
(97, 25, 124, 0, 1),
(98, 25, 211, 0, 1),
(99, 25, 133, 0, 1),
(100, 26, 124, 0, 1),
(101, 26, 127, 0, 1),
(102, 26, 125, 0, 1),
(103, 26, 133, 0, 1),
(104, 26, 211, 0, 1),
(105, 27, 133, 0, 1),
(106, 27, 125, 0, 1),
(107, 27, 211, 0, 1),
(108, 27, 124, 0, 1),
(109, 27, 127, 0, 1),
(110, 28, 211, 0, 1),
(111, 28, 133, 0, 1),
(112, 28, 125, 0, 1),
(113, 28, 124, 0, 1),
(114, 28, 127, 0, 1),
(115, 29, 133, 0, 1),
(116, 29, 127, 0, 1),
(117, 29, 211, 0, 1),
(118, 29, 125, 0, 1),
(119, 29, 124, 0, 1),
(120, 30, 124, 0, 1),
(121, 31, 185, 0, 1),
(122, 32, 128, 0, 1),
(123, 32, 125, 0, 1),
(124, 32, 133, 0, 1),
(125, 32, 238, 0, 1),
(126, 32, 231, 0, 1),
(127, 33, 231, 0, 1),
(128, 33, 238, 0, 1),
(129, 33, 133, 0, 1),
(130, 33, 128, 0, 1),
(131, 33, 125, 0, 1),
(132, 34, 171, 0, 1),
(133, 34, 172, 0, 1),
(134, 34, 169, 0, 1),
(135, 34, 170, 0, 1),
(136, 35, 214, 0, 1),
(137, 35, 141, 0, 1),
(138, 35, 156, 0, 1),
(139, 35, 136, 0, 1),
(140, 35, 127, 0, 1),
(141, 36, 141, 0, 1),
(142, 36, 136, 0, 1),
(143, 36, 214, 0, 1),
(144, 36, 127, 0, 1),
(145, 36, 156, 0, 1),
(146, 37, 127, 0, 1),
(147, 37, 156, 0, 1),
(148, 37, 214, 0, 1),
(149, 37, 141, 0, 1),
(150, 37, 136, 0, 1),
(151, 38, 187, 0, 1),
(152, 39, 136, 0, 1),
(153, 39, 127, 0, 1),
(154, 39, 156, 0, 1),
(155, 39, 197, 0, 1),
(156, 39, 141, 0, 1),
(157, 40, 151, 0, 1),
(158, 40, 212, 0, 1),
(159, 40, 213, 0, 1),
(160, 41, 133, 0, 1),
(161, 41, 138, 0, 1),
(162, 41, 139, 0, 1),
(163, 41, 155, 0, 1),
(164, 41, 222, 0, 1),
(165, 42, 222, 0, 1),
(166, 42, 155, 0, 1),
(167, 42, 133, 0, 1),
(168, 42, 139, 0, 1),
(169, 42, 138, 0, 1),
(170, 43, 138, 0, 1),
(171, 43, 139, 0, 1),
(172, 43, 222, 0, 1),
(173, 43, 155, 0, 1),
(174, 43, 133, 0, 1),
(175, 44, 139, 0, 1),
(176, 44, 133, 0, 1),
(177, 44, 222, 0, 1),
(178, 44, 138, 0, 1),
(179, 44, 155, 0, 1),
(180, 45, 155, 0, 1),
(181, 45, 138, 0, 1),
(182, 45, 154, 0, 1),
(183, 45, 139, 0, 1),
(184, 45, 133, 0, 1),
(185, 45, 222, 0, 1),
(186, 46, 164, 0, 1),
(187, 46, 163, 0, 1),
(188, 46, 159, 0, 1),
(189, 47, 131, 0, 1),
(190, 47, 221, 0, 1),
(191, 47, 137, 0, 1),
(192, 47, 115, 0, 1),
(193, 47, 182, 0, 1),
(194, 48, 221, 0, 1),
(195, 48, 131, 0, 1),
(196, 48, 137, 0, 1),
(197, 48, 115, 0, 1),
(198, 48, 182, 0, 1),
(199, 49, 137, 0, 1),
(200, 49, 115, 0, 1),
(201, 49, 182, 0, 1),
(202, 49, 221, 0, 1),
(203, 49, 131, 0, 1),
(204, 50, 221, 0, 1),
(205, 50, 131, 0, 1),
(206, 50, 137, 0, 1),
(207, 50, 115, 0, 1),
(208, 50, 182, 0, 1),
(209, 51, 115, 0, 1),
(210, 51, 182, 0, 1),
(211, 51, 131, 0, 1),
(212, 51, 221, 0, 1),
(213, 51, 137, 0, 1),
(214, 52, 137, 0, 1),
(215, 52, 115, 0, 1),
(216, 52, 131, 0, 1),
(217, 52, 221, 0, 1),
(218, 52, 182, 0, 1),
(219, 53, 182, 0, 1),
(220, 53, 221, 0, 1),
(221, 53, 137, 0, 1),
(222, 53, 131, 0, 1),
(223, 53, 217, 0, 1),
(224, 54, 115, 0, 1),
(225, 54, 182, 0, 1),
(226, 54, 137, 0, 1),
(227, 54, 221, 0, 1),
(228, 54, 131, 0, 1),
(229, 55, 131, 0, 1),
(230, 55, 137, 0, 1),
(231, 55, 182, 0, 1),
(232, 55, 115, 0, 1),
(233, 55, 221, 0, 1),
(234, 56, 115, 0, 1),
(235, 56, 160, 0, 1),
(236, 56, 170, 0, 1),
(237, 56, 161, 0, 1),
(238, 56, 177, 0, 1),
(239, 57, 218, 0, 1),
(240, 57, 135, 0, 1),
(241, 58, 204, 0, 1),
(242, 59, 117, 0, 1),
(243, 59, 116, 0, 1),
(244, 60, 116, 0, 1),
(245, 61, 116, 0, 1),
(246, 62, 104, 0, 1),
(247, 62, 121, 0, 1),
(248, 62, 110, 0, 1),
(249, 62, 120, 0, 1),
(250, 63, 118, 0, 1),
(251, 63, 119, 0, 1),
(252, 63, 104, 0, 1),
(253, 63, 110, 0, 1),
(254, 64, 98, 0, 1),
(255, 64, 93, 0, 1),
(256, 64, 105, 0, 1),
(257, 65, 101, 0, 1),
(258, 65, 105, 0, 1),
(259, 65, 98, 0, 1),
(260, 65, 93, 0, 1),
(261, 66, 93, 0, 1),
(262, 66, 105, 0, 1),
(263, 66, 106, 0, 1),
(264, 66, 97, 0, 1),
(265, 67, 179, 0, 1),
(266, 67, 97, 0, 1),
(267, 67, 93, 0, 1),
(268, 67, 105, 0, 1),
(269, 67, 106, 0, 1),
(270, 68, 152, 0, 1),
(271, 68, 96, 0, 1),
(272, 68, 130, 0, 1),
(273, 68, 113, 0, 1),
(274, 69, 100, 0, 1),
(275, 70, 99, 0, 1),
(276, 71, 151, 0, 1),
(277, 71, 220, 0, 1),
(278, 71, 113, 0, 1),
(279, 71, 130, 0, 1),
(280, 71, 95, 0, 1),
(281, 72, 130, 0, 1),
(282, 73, 95, 0, 1),
(283, 73, 100, 0, 1),
(284, 73, 151, 0, 1),
(285, 73, 220, 0, 1),
(286, 74, 107, 0, 1),
(287, 74, 94, 0, 1),
(288, 74, 150, 0, 1),
(289, 74, 109, 0, 1),
(290, 74, 112, 0, 1),
(291, 75, 109, 0, 1),
(292, 75, 150, 0, 1),
(293, 75, 112, 0, 1),
(294, 75, 94, 0, 1),
(295, 75, 107, 0, 1),
(296, 76, 148, 0, 1),
(297, 76, 90, 0, 1),
(298, 76, 215, 0, 1),
(299, 76, 89, 0, 1),
(300, 76, 111, 0, 1),
(301, 77, 89, 0, 1),
(302, 77, 148, 0, 1),
(303, 77, 215, 0, 1),
(304, 77, 111, 0, 1),
(305, 77, 90, 0, 1),
(306, 78, 115, 0, 1),
(307, 78, 147, 0, 1),
(308, 78, 132, 0, 1),
(309, 78, 91, 0, 1),
(310, 78, 102, 0, 1),
(311, 79, 91, 0, 1),
(312, 79, 147, 0, 1),
(313, 79, 132, 0, 1),
(314, 79, 102, 0, 1),
(315, 79, 115, 0, 1),
(316, 80, 102, 0, 1),
(317, 80, 154, 0, 1),
(318, 80, 115, 0, 1),
(319, 80, 91, 0, 1),
(320, 80, 132, 0, 1);

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

--
-- Dumping data for table `class_course_semester_merge`
--

INSERT INTO `class_course_semester_merge` (`class_course_semester_merge_id`, `class_course_semester_id_1`, `class_course_semester_id_2`) VALUES
(1, 40, 45),
(3, 309, 311);

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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_semester`
--

INSERT INTO `class_semester` (`class_semester_id`, `class_id`, `semester_id`, `semester`) VALUES
(1, 4, 1, 0),
(2, 5, 1, 0),
(3, 6, 1, 0),
(4, 7, 1, 0),
(5, 8, 1, 0),
(6, 9, 1, 0),
(7, 10, 1, 0),
(8, 11, 1, 0),
(9, 12, 1, 0),
(10, 13, 1, 0),
(11, 14, 1, 0),
(12, 15, 1, 0),
(13, 16, 1, 0),
(14, 17, 1, 0),
(15, 18, 1, 0),
(16, 19, 1, 0),
(17, 20, 1, 0),
(18, 21, 1, 0),
(19, 22, 1, 0),
(20, 23, 1, 0),
(21, 24, 1, 0),
(22, 25, 1, 0),
(23, 26, 1, 0),
(24, 27, 1, 0),
(25, 28, 1, 0),
(26, 29, 1, 0),
(27, 30, 1, 0),
(28, 31, 1, 0),
(29, 32, 1, 0),
(30, 33, 1, 0),
(31, 34, 1, 0),
(32, 35, 1, 0),
(33, 36, 1, 0),
(34, 37, 1, 0),
(35, 38, 1, 0),
(36, 39, 1, 0),
(37, 40, 1, 0),
(38, 41, 1, 0),
(39, 42, 1, 0),
(40, 43, 1, 0),
(41, 44, 1, 0),
(42, 45, 1, 0),
(43, 46, 1, 0),
(44, 47, 1, 0),
(45, 48, 1, 0),
(46, 49, 1, 0),
(47, 50, 1, 0),
(48, 51, 1, 0),
(49, 52, 1, 0),
(50, 53, 1, 0),
(51, 54, 1, 0),
(52, 55, 1, 0),
(53, 56, 1, 0),
(54, 57, 1, 0),
(55, 58, 1, 0),
(56, 59, 1, 0),
(57, 60, 1, 0),
(58, 61, 1, 0),
(59, 62, 1, 0),
(60, 63, 1, 0),
(61, 64, 1, 0),
(62, 65, 1, 0),
(63, 66, 1, 0),
(64, 67, 1, 0),
(65, 68, 1, 0),
(66, 69, 1, 0),
(67, 70, 1, 0),
(68, 71, 1, 0),
(69, 72, 1, 0),
(70, 73, 1, 0),
(71, 74, 1, 0),
(72, 75, 1, 0),
(73, 76, 1, 0),
(74, 77, 1, 0),
(75, 78, 1, 0),
(76, 79, 1, 0),
(77, 80, 1, 0),
(78, 81, 1, 0),
(79, 82, 1, 0),
(80, 83, 1, 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=latin1;

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
(180, 'VNR201/ HCM201', 'Revolutionary Lines of CPV', 7),
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
(241, 'ÐSA101', 'Sáo Trúc', 13);

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
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=latin1;

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
(241, 241, 1, 30, NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(7, 'ISE', 'ISE', 0),
(8, 'CS', 'CS', 1),
(9, 'EC', 'EC', 0),
(10, 'IA1', 'IA1', 1),
(11, 'IA2', 'IA2', 1),
(12, 'GD', 'GD', 0),
(13, 'IA', 'IA', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=3121 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(1945, '1SSE00000', 'Student 1', '1SSE00000@fpt.edu.vn', 'SE00000', 1, 4, '8A', 9, NULL),
(1946, '2SSE00001', 'Student 2', '2SSE00001@fpt.edu.vn', 'SE00001', 1, 4, '8A', 9, NULL),
(1947, '3SSE00002', 'Student 3', '3SSE00002@fpt.edu.vn', 'SE00002', 1, 4, '8A', 9, NULL),
(1948, '4SSE00003', 'Student 4', '4SSE00003@fpt.edu.vn', 'SE00003', 1, 4, '8A', 9, NULL),
(1949, '5SSE00004', 'Student 5', '5SSE00004@fpt.edu.vn', 'SE00004', 1, 4, '8A', 9, NULL),
(1950, '6SSE00005', 'Student 6', '6SSE00005@fpt.edu.vn', 'SE00005', 1, 4, '8A', 9, NULL),
(1951, '7SSE00006', 'Student 7', '7SSE00006@fpt.edu.vn', 'SE00006', 1, 4, '8A', 9, NULL),
(1952, '8SSE00007', 'Student 8', '8SSE00007@fpt.edu.vn', 'SE00007', 1, 4, '8A', 9, NULL),
(1953, '9SSE00008', 'Student 9', '9SSE00008@fpt.edu.vn', 'SE00008', 1, 4, '8A', 9, NULL),
(1954, '10SSE00009', 'Student 10', '10SSE00009@fpt.edu.vn', 'SE00009', 1, 4, '8A', 9, NULL),
(1955, '11SSE00010', 'Student 11', '11SSE00010@fpt.edu.vn', 'SE00010', 1, 4, '8A', 9, NULL),
(1956, '12SSE00011', 'Student 12', '12SSE00011@fpt.edu.vn', 'SE00011', 1, 4, '8A', 9, NULL),
(1957, '13SSE00012', 'Student 13', '13SSE00012@fpt.edu.vn', 'SE00012', 1, 4, '8A', 9, NULL),
(1958, '14SSE00013', 'Student 14', '14SSE00013@fpt.edu.vn', 'SE00013', 1, 4, '8A', 9, NULL),
(1959, '15SSE00014', 'Student 15', '15SSE00014@fpt.edu.vn', 'SE00014', 1, 4, '8A', 9, NULL),
(1960, '16SSE00015', 'Student 16', '16SSE00015@fpt.edu.vn', 'SE00015', 1, 4, '8A', 9, NULL),
(1961, '17SSE00016', 'Student 17', '17SSE00016@fpt.edu.vn', 'SE00016', 1, 4, '8A', 9, NULL),
(1962, '18SSE00017', 'Student 18', '18SSE00017@fpt.edu.vn', 'SE00017', 1, 4, '8A', 9, NULL),
(1963, '19SSE00018', 'Student 19', '19SSE00018@fpt.edu.vn', 'SE00018', 1, 4, '8A', 9, NULL),
(1964, '20SSE00019', 'Student 20', '20SSE00019@fpt.edu.vn', 'SE00019', 1, 5, '8A', 9, NULL),
(1965, '21SSE00020', 'Student 21', '21SSE00020@fpt.edu.vn', 'SE00020', 1, 5, '8A', 9, NULL),
(1966, '22SSE00021', 'Student 22', '22SSE00021@fpt.edu.vn', 'SE00021', 1, 5, '8A', 9, NULL),
(1967, '23SSE00022', 'Student 23', '23SSE00022@fpt.edu.vn', 'SE00022', 1, 5, '8A', 9, NULL),
(1968, '24SSE00023', 'Student 24', '24SSE00023@fpt.edu.vn', 'SE00023', 1, 5, '8A', 9, NULL),
(1969, '25SSE00024', 'Student 25', '25SSE00024@fpt.edu.vn', 'SE00024', 1, 5, '8A', 9, NULL),
(1970, '26SSE00025', 'Student 26', '26SSE00025@fpt.edu.vn', 'SE00025', 1, 5, '8A', 9, NULL),
(1971, '27SSE00026', 'Student 27', '27SSE00026@fpt.edu.vn', 'SE00026', 1, 5, '8A', 9, NULL),
(1972, '28SSE00027', 'Student 28', '28SSE00027@fpt.edu.vn', 'SE00027', 1, 5, '8A', 9, NULL),
(1973, '29SSE00028', 'Student 29', '29SSE00028@fpt.edu.vn', 'SE00028', 1, 5, '8A', 9, NULL),
(1974, '30SSE00029', 'Student 30', '30SSE00029@fpt.edu.vn', 'SE00029', 1, 5, '8A', 9, NULL),
(1975, '31SSE00030', 'Student 31', '31SSE00030@fpt.edu.vn', 'SE00030', 1, 5, '8A', 9, NULL),
(1976, '32SSE00031', 'Student 32', '32SSE00031@fpt.edu.vn', 'SE00031', 1, 5, '8A', 9, NULL),
(1977, '33SSE00032', 'Student 33', '33SSE00032@fpt.edu.vn', 'SE00032', 1, 5, '8A', 9, NULL),
(1978, '34SSE00033', 'Student 34', '34SSE00033@fpt.edu.vn', 'SE00033', 1, 5, '8A', 9, NULL),
(1979, '35SSE00034', 'Student 35', '35SSE00034@fpt.edu.vn', 'SE00034', 1, 5, '8A', 9, NULL),
(1980, '36SSE00035', 'Student 36', '36SSE00035@fpt.edu.vn', 'SE00035', 1, 5, '8A', 9, NULL),
(1981, '37SSE00036', 'Student 37', '37SSE00036@fpt.edu.vn', 'SE00036', 1, 5, '8A', 9, NULL),
(1982, '38SSE00037', 'Student 38', '38SSE00037@fpt.edu.vn', 'SE00037', 1, 5, '8A', 9, NULL),
(1983, '39SSE00038', 'Student 39', '39SSE00038@fpt.edu.vn', 'SE00038', 1, 5, '8A', 9, NULL),
(1984, '40SSE00039', 'Student 40', '40SSE00039@fpt.edu.vn', 'SE00039', 1, 5, '8A', 9, NULL),
(1985, '41SSE00040', 'Student 41', '41SSE00040@fpt.edu.vn', 'SE00040', 1, 5, '8A', 9, NULL),
(1986, '42SSE00041', 'Student 42', '42SSE00041@fpt.edu.vn', 'SE00041', 1, 5, '8A', 9, NULL),
(1987, '43SSE00042', 'Student 43', '43SSE00042@fpt.edu.vn', 'SE00042', 1, 5, '8A', 9, NULL),
(1988, '44SSE00043', 'Student 44', '44SSE00043@fpt.edu.vn', 'SE00043', 1, 5, '8A', 9, NULL),
(1989, '45SSE00044', 'Student 45', '45SSE00044@fpt.edu.vn', 'SE00044', 1, 5, '8A', 9, NULL),
(1990, '46SSE00045', 'Student 46', '46SSE00045@fpt.edu.vn', 'SE00045', 1, 5, '8A', 9, NULL),
(1991, '47SSE00046', 'Student 47', '47SSE00046@fpt.edu.vn', 'SE00046', 1, 5, '8A', 9, NULL),
(1992, '48SSE00047', 'Student 48', '48SSE00047@fpt.edu.vn', 'SE00047', 1, 5, '8A', 9, NULL),
(1993, '49SSE00048', 'Student 49', '49SSE00048@fpt.edu.vn', 'SE00048', 1, 2, '8A', 9, NULL),
(1994, '50SSE00049', 'Student 50', '50SSE00049@fpt.edu.vn', 'SE00049', 1, 2, '8A', 9, NULL),
(1995, '51SSE00050', 'Student 51', '51SSE00050@fpt.edu.vn', 'SE00050', 1, 2, '8A', 9, NULL),
(1996, '52SSE00051', 'Student 52', '52SSE00051@fpt.edu.vn', 'SE00051', 1, 2, '8A', 9, NULL),
(1997, '53SSE00052', 'Student 53', '53SSE00052@fpt.edu.vn', 'SE00052', 1, 3, '8A', 9, NULL),
(1998, '54SSE00053', 'Student 54', '54SSE00053@fpt.edu.vn', 'SE00053', 1, 3, '8A', 9, NULL),
(1999, '55SSE00054', 'Student 55', '55SSE00054@fpt.edu.vn', 'SE00054', 1, 3, '8A', 9, NULL),
(2000, '56SSE00055', 'Student 56', '56SSE00055@fpt.edu.vn', 'SE00055', 1, 3, '8A', 9, NULL),
(2001, '57SSE00056', 'Student 57', '57SSE00056@fpt.edu.vn', 'SE00056', 1, 3, '8A', 9, NULL),
(2002, '58SSE00057', 'Student 58', '58SSE00057@fpt.edu.vn', 'SE00057', 1, 3, '8A', 9, NULL),
(2003, '59SSE00058', 'Student 59', '59SSE00058@fpt.edu.vn', 'SE00058', 1, 3, '8A', 9, NULL),
(2004, '60SSE00059', 'Student 60', '60SSE00059@fpt.edu.vn', 'SE00059', 1, 3, '8A', 9, NULL),
(2005, '61SSE00060', 'Student 61', '61SSE00060@fpt.edu.vn', 'SE00060', 1, 3, '8A', 9, NULL),
(2006, '62SSE00061', 'Student 62', '62SSE00061@fpt.edu.vn', 'SE00061', 1, 6, '8A', 9, NULL),
(2007, '63SSE00062', 'Student 63', '63SSE00062@fpt.edu.vn', 'SE00062', 1, 6, '8A', 9, NULL),
(2008, '64SSE00063', 'Student 64', '64SSE00063@fpt.edu.vn', 'SE00063', 1, 6, '8A', 9, NULL),
(2009, '65SSE00064', 'Student 65', '65SSE00064@fpt.edu.vn', 'SE00064', 1, 6, '8A', 9, NULL),
(2010, '66SSE00065', 'Student 66', '66SSE00065@fpt.edu.vn', 'SE00065', 1, 6, '8A', 9, NULL),
(2011, '67SSE00066', 'Student 67', '67SSE00066@fpt.edu.vn', 'SE00066', 1, 6, '8A', 9, NULL),
(2012, '68SSE00067', 'Student 68', '68SSE00067@fpt.edu.vn', 'SE00067', 1, 6, '8A', 9, NULL),
(2013, '69SSE00068', 'Student 69', '69SSE00068@fpt.edu.vn', 'SE00068', 1, 6, '8A', 9, NULL),
(2014, '70SSE00069', 'Student 70', '70SSE00069@fpt.edu.vn', 'SE00069', 1, 6, '8A', 9, NULL),
(2015, '71SSE00070', 'Student 71', '71SSE00070@fpt.edu.vn', 'SE00070', 1, 6, '8A', 9, NULL),
(2016, '72SSE00071', 'Student 72', '72SSE00071@fpt.edu.vn', 'SE00071', 1, 6, '8A', 9, NULL),
(2017, '73SSE00072', 'Student 73', '73SSE00072@fpt.edu.vn', 'SE00072', 1, 6, '8A', 9, NULL),
(2018, '74SSE00073', 'Student 74', '74SSE00073@fpt.edu.vn', 'SE00073', 1, 6, '8A', 9, NULL),
(2019, '75SSE00074', 'Student 75', '75SSE00074@fpt.edu.vn', 'SE00074', 1, 6, '8A', 9, NULL),
(2020, '76SSE00075', 'Student 76', '76SSE00075@fpt.edu.vn', 'SE00075', 1, 6, '8A', 9, NULL),
(2021, '77SSE00076', 'Student 77', '77SSE00076@fpt.edu.vn', 'SE00076', 1, 6, '8A', 9, NULL),
(2022, '78SSE00077', 'Student 78', '78SSE00077@fpt.edu.vn', 'SE00077', 1, 6, '8A', 9, NULL),
(2023, '79SSE00078', 'Student 79', '79SSE00078@fpt.edu.vn', 'SE00078', 1, 6, '8A', 9, NULL),
(2024, '80SSE00079', 'Student 80', '80SSE00079@fpt.edu.vn', 'SE00079', 1, 6, '8A', 9, NULL),
(2025, '81SSE00080', 'Student 81', '81SSE00080@fpt.edu.vn', 'SE00080', 1, 6, '8A', 9, NULL),
(2026, '82SSE00081', 'Student 82', '82SSE00081@fpt.edu.vn', 'SE00081', 1, 6, '8A', 9, NULL),
(2027, '83SSE00082', 'Student 83', '83SSE00082@fpt.edu.vn', 'SE00082', 1, 6, '8A', 9, NULL),
(2028, '84SSE00083', 'Student 84', '84SSE00083@fpt.edu.vn', 'SE00083', 1, 6, '8A', 9, NULL),
(2029, '85SSE00084', 'Student 85', '85SSE00084@fpt.edu.vn', 'SE00084', 1, 6, '8A', 9, NULL),
(2030, '86SSE00085', 'Student 86', '86SSE00085@fpt.edu.vn', 'SE00085', 1, 6, '8A', 9, NULL),
(2031, '87SSE00086', 'Student 87', '87SSE00086@fpt.edu.vn', 'SE00086', 1, 6, '8A', 9, NULL),
(2032, '88SSE00087', 'Student 88', '88SSE00087@fpt.edu.vn', 'SE00087', 1, 6, '8A', 9, NULL),
(2033, '89SSE00088', 'Student 89', '89SSE00088@fpt.edu.vn', 'SE00088', 1, 6, '8A', 9, NULL),
(2034, '90SSE00089', 'Student 90', '90SSE00089@fpt.edu.vn', 'SE00089', 1, 6, '8A', 9, NULL),
(2035, '91SSE00090', 'Student 91', '91SSE00090@fpt.edu.vn', 'SE00090', 1, 6, '8A', 9, NULL),
(2036, '92SSE00091', 'Student 92', '92SSE00091@fpt.edu.vn', 'SE00091', 1, 6, '8A', 9, NULL),
(2037, '93SSE00092', 'Student 93', '93SSE00092@fpt.edu.vn', 'SE00092', 1, 6, '8A', 9, NULL),
(2038, '94SSE00093', 'Student 94', '94SSE00093@fpt.edu.vn', 'SE00093', 1, 6, '8A', 9, NULL),
(2039, '95SSE00094', 'Student 95', '95SSE00094@fpt.edu.vn', 'SE00094', 1, 4, '8B', 8, NULL),
(2040, '96SSE00095', 'Student 96', '96SSE00095@fpt.edu.vn', 'SE00095', 1, 4, '8B', 8, NULL),
(2041, '97SSE00096', 'Student 97', '97SSE00096@fpt.edu.vn', 'SE00096', 1, 4, '8B', 8, NULL),
(2042, '98SSE00097', 'Student 98', '98SSE00097@fpt.edu.vn', 'SE00097', 1, 4, '8B', 8, NULL),
(2043, '99SSE00098', 'Student 99', '99SSE00098@fpt.edu.vn', 'SE00098', 1, 4, '8B', 8, NULL),
(2044, '100SSE00099', 'Student 100', '100SSE00099@fpt.edu.vn', 'SE00099', 1, 4, '8B', 8, NULL),
(2045, '101SSE00100', 'Student 101', '101SSE00100@fpt.edu.vn', 'SE00100', 1, 4, '8B', 8, NULL),
(2046, '102SSE00101', 'Student 102', '102SSE00101@fpt.edu.vn', 'SE00101', 1, 4, '8B', 8, NULL),
(2047, '103SSE00102', 'Student 103', '103SSE00102@fpt.edu.vn', 'SE00102', 1, 4, '8B', 8, NULL),
(2048, '104SSE00103', 'Student 104', '104SSE00103@fpt.edu.vn', 'SE00103', 1, 4, '8B', 8, NULL),
(2049, '105SSE00104', 'Student 105', '105SSE00104@fpt.edu.vn', 'SE00104', 1, 4, '8B', 8, NULL),
(2050, '106SSE00105', 'Student 106', '106SSE00105@fpt.edu.vn', 'SE00105', 1, 4, '8B', 8, NULL),
(2051, '107SSE00106', 'Student 107', '107SSE00106@fpt.edu.vn', 'SE00106', 1, 4, '8B', 8, NULL),
(2052, '108SSE00107', 'Student 108', '108SSE00107@fpt.edu.vn', 'SE00107', 1, 4, '8B', 8, NULL),
(2053, '109SSE00108', 'Student 109', '109SSE00108@fpt.edu.vn', 'SE00108', 1, 4, '8B', 8, NULL),
(2054, '110SSE00109', 'Student 110', '110SSE00109@fpt.edu.vn', 'SE00109', 1, 4, '8B', 8, NULL),
(2055, '111SSE00110', 'Student 111', '111SSE00110@fpt.edu.vn', 'SE00110', 1, 4, '8B', 8, NULL),
(2056, '112SSE00111', 'Student 112', '112SSE00111@fpt.edu.vn', 'SE00111', 1, 4, '8B', 8, NULL),
(2057, '113SSE00112', 'Student 113', '113SSE00112@fpt.edu.vn', 'SE00112', 1, 4, '8B', 8, NULL),
(2058, '114SSE00113', 'Student 114', '114SSE00113@fpt.edu.vn', 'SE00113', 1, 4, '8B', 8, NULL),
(2059, '115SSE00114', 'Student 115', '115SSE00114@fpt.edu.vn', 'SE00114', 1, 4, '8B', 8, NULL),
(2060, '116SSE00115', 'Student 116', '116SSE00115@fpt.edu.vn', 'SE00115', 1, 4, '8B', 8, NULL),
(2061, '117SSE00116', 'Student 117', '117SSE00116@fpt.edu.vn', 'SE00116', 1, 4, '8B', 8, NULL),
(2062, '118SSE00117', 'Student 118', '118SSE00117@fpt.edu.vn', 'SE00117', 1, 4, '8B', 8, NULL),
(2063, '119SSE00118', 'Student 119', '119SSE00118@fpt.edu.vn', 'SE00118', 1, 4, '8B', 8, NULL),
(2064, '120SSE00119', 'Student 120', '120SSE00119@fpt.edu.vn', 'SE00119', 1, 4, '8B', 8, NULL),
(2065, '121SSE00120', 'Student 121', '121SSE00120@fpt.edu.vn', 'SE00120', 1, 4, '8B', 8, NULL),
(2066, '122SSE00121', 'Student 122', '122SSE00121@fpt.edu.vn', 'SE00121', 1, 4, '8B', 8, NULL),
(2067, '123SSE00122', 'Student 123', '123SSE00122@fpt.edu.vn', 'SE00122', 1, 4, '8B', 8, NULL),
(2068, '124SSE00123', 'Student 124', '124SSE00123@fpt.edu.vn', 'SE00123', 1, 5, '8B', 8, NULL),
(2069, '125SSE00124', 'Student 125', '125SSE00124@fpt.edu.vn', 'SE00124', 1, 5, '8B', 8, NULL),
(2070, '126SSE00125', 'Student 126', '126SSE00125@fpt.edu.vn', 'SE00125', 1, 5, '8B', 8, NULL),
(2071, '127SSE00126', 'Student 127', '127SSE00126@fpt.edu.vn', 'SE00126', 1, 5, '8B', 8, NULL),
(2072, '128SSE00127', 'Student 128', '128SSE00127@fpt.edu.vn', 'SE00127', 1, 5, '8B', 8, NULL),
(2073, '129SSE00128', 'Student 129', '129SSE00128@fpt.edu.vn', 'SE00128', 1, 5, '8B', 8, NULL),
(2074, '130SSE00129', 'Student 130', '130SSE00129@fpt.edu.vn', 'SE00129', 1, 5, '8B', 8, NULL),
(2075, '131SSE00130', 'Student 131', '131SSE00130@fpt.edu.vn', 'SE00130', 1, 5, '8B', 8, NULL),
(2076, '132SSE00131', 'Student 132', '132SSE00131@fpt.edu.vn', 'SE00131', 1, 5, '8B', 8, NULL),
(2077, '133SSE00132', 'Student 133', '133SSE00132@fpt.edu.vn', 'SE00132', 1, 5, '8B', 8, NULL),
(2078, '134SSE00133', 'Student 134', '134SSE00133@fpt.edu.vn', 'SE00133', 1, 5, '8B', 8, NULL),
(2079, '135SSE00134', 'Student 135', '135SSE00134@fpt.edu.vn', 'SE00134', 1, 5, '8B', 8, NULL),
(2080, '136SSE00135', 'Student 136', '136SSE00135@fpt.edu.vn', 'SE00135', 1, 5, '8B', 8, NULL),
(2081, '137SSE00136', 'Student 137', '137SSE00136@fpt.edu.vn', 'SE00136', 1, 5, '8B', 8, NULL),
(2082, '138SSE00137', 'Student 138', '138SSE00137@fpt.edu.vn', 'SE00137', 1, 5, '8B', 8, NULL),
(2083, '139SSE00138', 'Student 139', '139SSE00138@fpt.edu.vn', 'SE00138', 1, 5, '8B', 8, NULL),
(2084, '140SSE00139', 'Student 140', '140SSE00139@fpt.edu.vn', 'SE00139', 1, 5, '8B', 8, NULL),
(2085, '141SSE00140', 'Student 141', '141SSE00140@fpt.edu.vn', 'SE00140', 1, 5, '8B', 8, NULL),
(2086, '142SSE00141', 'Student 142', '142SSE00141@fpt.edu.vn', 'SE00141', 1, 5, '8B', 8, NULL),
(2087, '143SSE00142', 'Student 143', '143SSE00142@fpt.edu.vn', 'SE00142', 1, 2, '8B', 8, NULL),
(2088, '144SSE00143', 'Student 144', '144SSE00143@fpt.edu.vn', 'SE00143', 1, 2, '8B', 8, NULL),
(2089, '145SSE00144', 'Student 145', '145SSE00144@fpt.edu.vn', 'SE00144', 1, 3, '8B', 8, NULL),
(2090, '146SSE00145', 'Student 146', '146SSE00145@fpt.edu.vn', 'SE00145', 1, 3, '8B', 8, NULL),
(2091, '147SSE00146', 'Student 147', '147SSE00146@fpt.edu.vn', 'SE00146', 1, 3, '8B', 8, NULL),
(2092, '148SSE00147', 'Student 148', '148SSE00147@fpt.edu.vn', 'SE00147', 1, 3, '8B', 8, NULL),
(2093, '149SSE00148', 'Student 149', '149SSE00148@fpt.edu.vn', 'SE00148', 1, 3, '8B', 8, NULL),
(2094, '150SSE00149', 'Student 150', '150SSE00149@fpt.edu.vn', 'SE00149', 1, 3, '8B', 8, NULL),
(2095, '151SSE00150', 'Student 151', '151SSE00150@fpt.edu.vn', 'SE00150', 1, 6, '8B', 8, NULL),
(2096, '152SSE00151', 'Student 152', '152SSE00151@fpt.edu.vn', 'SE00151', 1, 6, '8B', 8, NULL),
(2097, '153SSE00152', 'Student 153', '153SSE00152@fpt.edu.vn', 'SE00152', 1, 6, '8B', 8, NULL),
(2098, '154SSE00153', 'Student 154', '154SSE00153@fpt.edu.vn', 'SE00153', 1, 6, '8B', 8, NULL),
(2099, '155SSE00154', 'Student 155', '155SSE00154@fpt.edu.vn', 'SE00154', 1, 6, '8B', 8, NULL),
(2100, '156SSE00155', 'Student 156', '156SSE00155@fpt.edu.vn', 'SE00155', 1, 6, '8B', 8, NULL),
(2101, '157SSE00156', 'Student 157', '157SSE00156@fpt.edu.vn', 'SE00156', 1, 6, '8B', 8, NULL),
(2102, '158SSE00157', 'Student 158', '158SSE00157@fpt.edu.vn', 'SE00157', 1, 6, '8B', 8, NULL),
(2103, '159SSE00158', 'Student 159', '159SSE00158@fpt.edu.vn', 'SE00158', 1, 6, '8B', 8, NULL),
(2104, '160SSE00159', 'Student 160', '160SSE00159@fpt.edu.vn', 'SE00159', 1, 6, '8B', 8, NULL),
(2105, '161SSE00160', 'Student 161', '161SSE00160@fpt.edu.vn', 'SE00160', 1, 6, '8B', 8, NULL),
(2106, '162SSE00161', 'Student 162', '162SSE00161@fpt.edu.vn', 'SE00161', 1, 6, '8B', 8, NULL),
(2107, '163SSE00162', 'Student 163', '163SSE00162@fpt.edu.vn', 'SE00162', 1, 6, '8B', 8, NULL),
(2108, '164SSE00163', 'Student 164', '164SSE00163@fpt.edu.vn', 'SE00163', 1, 6, '8B', 8, NULL),
(2109, '165SSE00164', 'Student 165', '165SSE00164@fpt.edu.vn', 'SE00164', 1, 6, '8B', 8, NULL),
(2110, '166SSE00165', 'Student 166', '166SSE00165@fpt.edu.vn', 'SE00165', 1, 6, '8B', 8, NULL),
(2111, '167SSE00166', 'Student 167', '167SSE00166@fpt.edu.vn', 'SE00166', 1, 6, '8B', 8, NULL),
(2112, '168SSE00167', 'Student 168', '168SSE00167@fpt.edu.vn', 'SE00167', 1, 6, '8B', 8, NULL),
(2113, '169SSE00168', 'Student 169', '169SSE00168@fpt.edu.vn', 'SE00168', 1, 6, '8B', 8, NULL),
(2114, '170SSE00169', 'Student 170', '170SSE00169@fpt.edu.vn', 'SE00169', 1, 6, '8B', 8, NULL),
(2115, '171SSE00170', 'Student 171', '171SSE00170@fpt.edu.vn', 'SE00170', 1, 6, '8B', 8, NULL),
(2116, '172SSE00171', 'Student 172', '172SSE00171@fpt.edu.vn', 'SE00171', 1, 6, '8B', 8, NULL),
(2117, '173SSE00172', 'Student 173', '173SSE00172@fpt.edu.vn', 'SE00172', 1, 6, '8B', 8, NULL),
(2118, '174SSE00173', 'Student 174', '174SSE00173@fpt.edu.vn', 'SE00173', 1, 6, '8B', 8, NULL),
(2119, '175SSE00174', 'Student 175', '175SSE00174@fpt.edu.vn', 'SE00174', 1, 6, '8B', 8, NULL),
(2120, '176SSE00175', 'Student 176', '176SSE00175@fpt.edu.vn', 'SE00175', 1, 6, '8B', 8, NULL),
(2121, '177SSE00176', 'Student 177', '177SSE00176@fpt.edu.vn', 'SE00176', 1, 6, '8B', 8, NULL),
(2122, '178SSE00177', 'Student 178', '178SSE00177@fpt.edu.vn', 'SE00177', 1, 6, '8B', 8, NULL),
(2123, '179SSE00178', 'Student 179', '179SSE00178@fpt.edu.vn', 'SE00178', 1, 6, '8B', 8, NULL),
(2124, '180SSE00179', 'Student 180', '180SSE00179@fpt.edu.vn', 'SE00179', 1, 6, '8B', 8, NULL),
(2125, '181SSE00180', 'Student 181', '181SSE00180@fpt.edu.vn', 'SE00180', 1, 6, '8B', 8, NULL),
(2126, '182SSE00181', 'Student 182', '182SSE00181@fpt.edu.vn', 'SE00181', 1, 6, '8B', 8, NULL),
(2127, '183SSE00182', 'Student 183', '183SSE00182@fpt.edu.vn', 'SE00182', 1, 8, '8B', 8, NULL),
(2128, '184SSE00183', 'Student 184', '184SSE00183@fpt.edu.vn', 'SE00183', 1, 8, '8B', 8, NULL),
(2129, '185SSE00184', 'Student 185', '185SSE00184@fpt.edu.vn', 'SE00184', 1, 8, '8B', 8, NULL),
(2130, '186SSE00185', 'Student 186', '186SSE00185@fpt.edu.vn', 'SE00185', 1, 8, '8B', 8, NULL),
(2131, '187SSE00186', 'Student 187', '187SSE00186@fpt.edu.vn', 'SE00186', 1, 8, '8B', 8, NULL),
(2132, '188SSE00187', 'Student 188', '188SSE00187@fpt.edu.vn', 'SE00187', 1, 8, '8B', 8, NULL),
(2133, '189SSE00188', 'Student 189', '189SSE00188@fpt.edu.vn', 'SE00188', 1, 8, '8B', 8, NULL),
(2134, '190SSE00189', 'Student 190', '190SSE00189@fpt.edu.vn', 'SE00189', 1, 8, '8B', 8, NULL),
(2135, '191SSE00190', 'Student 191', '191SSE00190@fpt.edu.vn', 'SE00190', 1, 8, '8B', 8, NULL),
(2136, '192SSE00191', 'Student 192', '192SSE00191@fpt.edu.vn', 'SE00191', 1, 8, '8B', 8, NULL),
(2137, '193SEC00000', 'Student 193', '193SEC00000@fpt.edu.vn', 'EC00000', 9, NULL, '8B', 8, NULL),
(2138, '194SEC00001', 'Student 194', '194SEC00001@fpt.edu.vn', 'EC00001', 9, NULL, '8B', 8, NULL),
(2139, '195SEC00002', 'Student 195', '195SEC00002@fpt.edu.vn', 'EC00002', 9, NULL, '8B', 8, NULL),
(2140, '196SEC00003', 'Student 196', '196SEC00003@fpt.edu.vn', 'EC00003', 9, NULL, '8B', 8, NULL),
(2141, '197SEC00004', 'Student 197', '197SEC00004@fpt.edu.vn', 'EC00004', 9, NULL, '8B', 8, NULL),
(2142, '198SEC00005', 'Student 198', '198SEC00005@fpt.edu.vn', 'EC00005', 9, NULL, '8B', 8, NULL),
(2143, '199SEC00006', 'Student 199', '199SEC00006@fpt.edu.vn', 'EC00006', 9, NULL, '8B', 8, NULL),
(2144, '200SEC00007', 'Student 200', '200SEC00007@fpt.edu.vn', 'EC00007', 9, NULL, '8B', 8, NULL),
(2145, '201SEC00008', 'Student 201', '201SEC00008@fpt.edu.vn', 'EC00008', 9, NULL, '8B', 8, NULL),
(2146, '202SEC00009', 'Student 202', '202SEC00009@fpt.edu.vn', 'EC00009', 9, NULL, '8B', 8, NULL),
(2147, '203SEC00010', 'Student 203', '203SEC00010@fpt.edu.vn', 'EC00010', 9, NULL, '8B', 8, NULL),
(2148, '204SEC00011', 'Student 204', '204SEC00011@fpt.edu.vn', 'EC00011', 9, NULL, '8B', 8, NULL),
(2149, '205SEC00012', 'Student 205', '205SEC00012@fpt.edu.vn', 'EC00012', 9, NULL, '8B', 8, NULL),
(2150, '206SEC00013', 'Student 206', '206SEC00013@fpt.edu.vn', 'EC00013', 9, NULL, '8B', 8, NULL),
(2151, '207SEC00014', 'Student 207', '207SEC00014@fpt.edu.vn', 'EC00014', 9, NULL, '8B', 8, NULL),
(2152, '208SEC00015', 'Student 208', '208SEC00015@fpt.edu.vn', 'EC00015', 9, NULL, '8B', 8, NULL),
(2153, '209SSE00192', 'Student 209', '209SSE00192@fpt.edu.vn', 'SE00192', 1, 7, '8A', 7, NULL),
(2154, '210SSE00193', 'Student 210', '210SSE00193@fpt.edu.vn', 'SE00193', 1, 7, '8A', 7, NULL),
(2155, '211SSE00194', 'Student 211', '211SSE00194@fpt.edu.vn', 'SE00194', 1, 7, '8A', 7, NULL),
(2156, '212SSE00195', 'Student 212', '212SSE00195@fpt.edu.vn', 'SE00195', 1, 7, '8A', 7, NULL),
(2157, '213SSE00196', 'Student 213', '213SSE00196@fpt.edu.vn', 'SE00196', 1, 7, '8A', 7, NULL),
(2158, '214SSE00197', 'Student 214', '214SSE00197@fpt.edu.vn', 'SE00197', 1, 7, '8A', 7, NULL),
(2159, '215SSE00198', 'Student 215', '215SSE00198@fpt.edu.vn', 'SE00198', 1, 7, '8A', 7, NULL),
(2160, '216SSE00199', 'Student 216', '216SSE00199@fpt.edu.vn', 'SE00199', 1, 7, '8A', 7, NULL),
(2161, '217SSE00200', 'Student 217', '217SSE00200@fpt.edu.vn', 'SE00200', 1, 7, '8A', 7, NULL),
(2162, '218SSE00201', 'Student 218', '218SSE00201@fpt.edu.vn', 'SE00201', 1, 7, '8A', 7, NULL),
(2163, '219SSE00202', 'Student 219', '219SSE00202@fpt.edu.vn', 'SE00202', 1, 7, '8A', 7, NULL),
(2164, '220SSE00203', 'Student 220', '220SSE00203@fpt.edu.vn', 'SE00203', 1, 7, '8A', 7, NULL),
(2165, '221SSE00204', 'Student 221', '221SSE00204@fpt.edu.vn', 'SE00204', 1, 7, '8A', 7, NULL),
(2166, '222SSE00205', 'Student 222', '222SSE00205@fpt.edu.vn', 'SE00205', 1, 7, '8A', 7, NULL),
(2167, '223SSE00206', 'Student 223', '223SSE00206@fpt.edu.vn', 'SE00206', 1, 7, '8A', 7, NULL),
(2168, '224SSE00207', 'Student 224', '224SSE00207@fpt.edu.vn', 'SE00207', 1, 7, '8A', 7, NULL),
(2169, '225SSE00208', 'Student 225', '225SSE00208@fpt.edu.vn', 'SE00208', 1, 7, '8A', 7, NULL),
(2170, '226SSE00209', 'Student 226', '226SSE00209@fpt.edu.vn', 'SE00209', 1, 7, '8A', 7, NULL),
(2171, '227SSE00210', 'Student 227', '227SSE00210@fpt.edu.vn', 'SE00210', 1, 7, '8A', 7, NULL),
(2172, '228SSE00211', 'Student 228', '228SSE00211@fpt.edu.vn', 'SE00211', 1, 7, '8A', 7, NULL),
(2173, '229SSE00212', 'Student 229', '229SSE00212@fpt.edu.vn', 'SE00212', 1, 7, '8A', 7, NULL),
(2174, '230SSE00213', 'Student 230', '230SSE00213@fpt.edu.vn', 'SE00213', 1, 7, '8A', 7, NULL),
(2175, '231SSE00214', 'Student 231', '231SSE00214@fpt.edu.vn', 'SE00214', 1, 7, '8A', 7, NULL),
(2176, '232SSE00215', 'Student 232', '232SSE00215@fpt.edu.vn', 'SE00215', 1, 7, '8A', 7, NULL),
(2177, '233SSE00216', 'Student 233', '233SSE00216@fpt.edu.vn', 'SE00216', 1, 7, '8A', 7, NULL),
(2178, '234SSE00217', 'Student 234', '234SSE00217@fpt.edu.vn', 'SE00217', 1, 7, '8A', 7, NULL),
(2179, '235SSE00218', 'Student 235', '235SSE00218@fpt.edu.vn', 'SE00218', 1, 7, '8A', 7, NULL),
(2180, '236SSE00219', 'Student 236', '236SSE00219@fpt.edu.vn', 'SE00219', 1, 7, '8A', 7, NULL),
(2181, '237SSE00220', 'Student 237', '237SSE00220@fpt.edu.vn', 'SE00220', 1, 7, '8A', 7, NULL),
(2182, '238SSE00221', 'Student 238', '238SSE00221@fpt.edu.vn', 'SE00221', 1, 4, '8C', 7, NULL),
(2183, '239SSE00222', 'Student 239', '239SSE00222@fpt.edu.vn', 'SE00222', 1, 4, '8C', 7, NULL),
(2184, '240SSE00223', 'Student 240', '240SSE00223@fpt.edu.vn', 'SE00223', 1, 4, '8C', 7, NULL),
(2185, '241SSE00224', 'Student 241', '241SSE00224@fpt.edu.vn', 'SE00224', 1, 4, '8C', 7, NULL),
(2186, '242SSE00225', 'Student 242', '242SSE00225@fpt.edu.vn', 'SE00225', 1, 4, '8C', 7, NULL),
(2187, '243SSE00226', 'Student 243', '243SSE00226@fpt.edu.vn', 'SE00226', 1, 4, '8C', 7, NULL),
(2188, '244SSE00227', 'Student 244', '244SSE00227@fpt.edu.vn', 'SE00227', 1, 4, '8C', 7, NULL),
(2189, '245SSE00228', 'Student 245', '245SSE00228@fpt.edu.vn', 'SE00228', 1, 4, '8C', 7, NULL),
(2190, '246SSE00229', 'Student 246', '246SSE00229@fpt.edu.vn', 'SE00229', 1, 4, '8C', 7, NULL),
(2191, '247SSE00230', 'Student 247', '247SSE00230@fpt.edu.vn', 'SE00230', 1, 4, '8C', 7, NULL),
(2192, '248SSE00231', 'Student 248', '248SSE00231@fpt.edu.vn', 'SE00231', 1, 4, '8C', 7, NULL),
(2193, '249SSE00232', 'Student 249', '249SSE00232@fpt.edu.vn', 'SE00232', 1, 4, '8C', 7, NULL),
(2194, '250SSE00233', 'Student 250', '250SSE00233@fpt.edu.vn', 'SE00233', 1, 4, '8C', 7, NULL),
(2195, '251SSE00234', 'Student 251', '251SSE00234@fpt.edu.vn', 'SE00234', 1, 4, '8C', 7, NULL),
(2196, '252SSE00235', 'Student 252', '252SSE00235@fpt.edu.vn', 'SE00235', 1, 4, '8C', 7, NULL),
(2197, '253SSE00236', 'Student 253', '253SSE00236@fpt.edu.vn', 'SE00236', 1, 4, '8C', 7, NULL),
(2198, '254SSE00237', 'Student 254', '254SSE00237@fpt.edu.vn', 'SE00237', 1, 4, '8C', 7, NULL),
(2199, '255SSE00238', 'Student 255', '255SSE00238@fpt.edu.vn', 'SE00238', 1, 4, '8C', 7, NULL),
(2200, '256SSE00239', 'Student 256', '256SSE00239@fpt.edu.vn', 'SE00239', 1, 4, '8C', 7, NULL),
(2201, '257SSE00240', 'Student 257', '257SSE00240@fpt.edu.vn', 'SE00240', 1, 4, '8C', 7, NULL),
(2202, '258SSE00241', 'Student 258', '258SSE00241@fpt.edu.vn', 'SE00241', 1, 4, '8C', 7, NULL),
(2203, '259SSE00242', 'Student 259', '259SSE00242@fpt.edu.vn', 'SE00242', 1, 4, '8C', 7, NULL),
(2204, '260SSE00243', 'Student 260', '260SSE00243@fpt.edu.vn', 'SE00243', 1, 4, '8C', 7, NULL),
(2205, '261SSE00244', 'Student 261', '261SSE00244@fpt.edu.vn', 'SE00244', 1, 5, '8C', 7, NULL),
(2206, '262SSE00245', 'Student 262', '262SSE00245@fpt.edu.vn', 'SE00245', 1, 5, '8C', 7, NULL),
(2207, '263SSE00246', 'Student 263', '263SSE00246@fpt.edu.vn', 'SE00246', 1, 5, '8C', 7, NULL),
(2208, '264SSE00247', 'Student 264', '264SSE00247@fpt.edu.vn', 'SE00247', 1, 5, '8C', 7, NULL),
(2209, '265SSE00248', 'Student 265', '265SSE00248@fpt.edu.vn', 'SE00248', 1, 5, '8C', 7, NULL),
(2210, '266SSE00249', 'Student 266', '266SSE00249@fpt.edu.vn', 'SE00249', 1, 5, '8C', 7, NULL),
(2211, '267SSE00250', 'Student 267', '267SSE00250@fpt.edu.vn', 'SE00250', 1, 5, '8C', 7, NULL),
(2212, '268SSE00251', 'Student 268', '268SSE00251@fpt.edu.vn', 'SE00251', 1, 5, '8C', 7, NULL),
(2213, '269SSE00252', 'Student 269', '269SSE00252@fpt.edu.vn', 'SE00252', 1, 5, '8C', 7, NULL),
(2214, '270SSE00253', 'Student 270', '270SSE00253@fpt.edu.vn', 'SE00253', 1, 5, '8C', 7, NULL),
(2215, '271SSE00254', 'Student 271', '271SSE00254@fpt.edu.vn', 'SE00254', 1, 5, '8C', 7, NULL),
(2216, '272SSE00255', 'Student 272', '272SSE00255@fpt.edu.vn', 'SE00255', 1, 5, '8C', 7, NULL),
(2217, '273SSE00256', 'Student 273', '273SSE00256@fpt.edu.vn', 'SE00256', 1, 5, '8C', 7, NULL),
(2218, '274SSE00257', 'Student 274', '274SSE00257@fpt.edu.vn', 'SE00257', 1, 5, '8C', 7, NULL),
(2219, '275SSE00258', 'Student 275', '275SSE00258@fpt.edu.vn', 'SE00258', 1, 5, '8C', 7, NULL),
(2220, '276SSE00259', 'Student 276', '276SSE00259@fpt.edu.vn', 'SE00259', 1, 5, '8C', 7, NULL),
(2221, '277SSE00260', 'Student 277', '277SSE00260@fpt.edu.vn', 'SE00260', 1, 5, '8C', 7, NULL),
(2222, '278SSE00261', 'Student 278', '278SSE00261@fpt.edu.vn', 'SE00261', 1, 5, '8C', 7, NULL),
(2223, '279SSE00262', 'Student 279', '279SSE00262@fpt.edu.vn', 'SE00262', 1, 5, '8C', 7, NULL),
(2224, '280SSE00263', 'Student 280', '280SSE00263@fpt.edu.vn', 'SE00263', 1, 5, '8C', 7, NULL),
(2225, '281SSE00264', 'Student 281', '281SSE00264@fpt.edu.vn', 'SE00264', 1, 5, '8C', 7, NULL),
(2226, '282SSE00265', 'Student 282', '282SSE00265@fpt.edu.vn', 'SE00265', 1, 5, '8C', 7, NULL),
(2227, '283SSE00266', 'Student 283', '283SSE00266@fpt.edu.vn', 'SE00266', 1, 5, '8C', 7, NULL),
(2228, '284SSE00267', 'Student 284', '284SSE00267@fpt.edu.vn', 'SE00267', 1, 5, '8C', 7, NULL),
(2229, '285SSE00268', 'Student 285', '285SSE00268@fpt.edu.vn', 'SE00268', 1, 5, '8C', 7, NULL),
(2230, '286SSE00269', 'Student 286', '286SSE00269@fpt.edu.vn', 'SE00269', 1, 3, '8C', 7, NULL),
(2231, '287SSE00270', 'Student 287', '287SSE00270@fpt.edu.vn', 'SE00270', 1, 3, '8C', 7, NULL),
(2232, '288SSE00271', 'Student 288', '288SSE00271@fpt.edu.vn', 'SE00271', 1, 3, '8C', 7, NULL),
(2233, '289SSE00272', 'Student 289', '289SSE00272@fpt.edu.vn', 'SE00272', 1, 3, '8C', 7, NULL),
(2234, '290SSE00273', 'Student 290', '290SSE00273@fpt.edu.vn', 'SE00273', 1, 3, '8C', 7, NULL),
(2235, '291SSE00274', 'Student 291', '291SSE00274@fpt.edu.vn', 'SE00274', 1, 3, '8C', 7, NULL),
(2236, '292SSE00275', 'Student 292', '292SSE00275@fpt.edu.vn', 'SE00275', 1, 3, '8C', 7, NULL),
(2237, '293SSE00276', 'Student 293', '293SSE00276@fpt.edu.vn', 'SE00276', 1, 3, '8C', 7, NULL),
(2238, '294SSE00277', 'Student 294', '294SSE00277@fpt.edu.vn', 'SE00277', 1, 3, '8C', 7, NULL),
(2239, '295SSE00278', 'Student 295', '295SSE00278@fpt.edu.vn', 'SE00278', 1, 3, '8C', 7, NULL),
(2240, '296SSE00279', 'Student 296', '296SSE00279@fpt.edu.vn', 'SE00279', 1, 3, '8C', 7, NULL),
(2241, '297SSE00280', 'Student 297', '297SSE00280@fpt.edu.vn', 'SE00280', 1, 3, '8C', 7, NULL),
(2242, '298SSE00281', 'Student 298', '298SSE00281@fpt.edu.vn', 'SE00281', 1, 3, '8C', 7, NULL),
(2243, '299SSE00282', 'Student 299', '299SSE00282@fpt.edu.vn', 'SE00282', 1, 3, '8C', 7, NULL),
(2244, '300SSE00283', 'Student 300', '300SSE00283@fpt.edu.vn', 'SE00283', 1, 3, '8C', 7, NULL),
(2245, '301SSE00284', 'Student 301', '301SSE00284@fpt.edu.vn', 'SE00284', 1, 3, '8C', 7, NULL),
(2246, '302SSE00285', 'Student 302', '302SSE00285@fpt.edu.vn', 'SE00285', 1, 3, '8C', 7, NULL),
(2247, '303SSE00286', 'Student 303', '303SSE00286@fpt.edu.vn', 'SE00286', 1, 3, '8C', 7, NULL),
(2248, '304SSE00287', 'Student 304', '304SSE00287@fpt.edu.vn', 'SE00287', 1, 3, '8C', 7, NULL),
(2249, '305SSE00288', 'Student 305', '305SSE00288@fpt.edu.vn', 'SE00288', 1, 3, '8C', 7, NULL),
(2250, '306SSE00289', 'Student 306', '306SSE00289@fpt.edu.vn', 'SE00289', 1, 3, '8C', 7, NULL),
(2251, '307SSE00290', 'Student 307', '307SSE00290@fpt.edu.vn', 'SE00290', 1, 3, '8C', 7, NULL),
(2252, '308SSE00291', 'Student 308', '308SSE00291@fpt.edu.vn', 'SE00291', 1, 3, '8C', 7, NULL),
(2253, '309SSE00292', 'Student 309', '309SSE00292@fpt.edu.vn', 'SE00292', 1, 3, '8C', 7, NULL),
(2254, '310SSE00293', 'Student 310', '310SSE00293@fpt.edu.vn', 'SE00293', 1, 3, '8C', 7, NULL),
(2255, '311SSE00294', 'Student 311', '311SSE00294@fpt.edu.vn', 'SE00294', 1, 3, '8C', 7, NULL),
(2256, '312SSE00295', 'Student 312', '312SSE00295@fpt.edu.vn', 'SE00295', 1, 3, '8C', 7, NULL),
(2257, '313SSE00296', 'Student 313', '313SSE00296@fpt.edu.vn', 'SE00296', 1, 3, '8C', 7, NULL),
(2258, '314SSE00297', 'Student 314', '314SSE00297@fpt.edu.vn', 'SE00297', 1, 3, '8C', 7, NULL),
(2259, '315SSE00298', 'Student 315', '315SSE00298@fpt.edu.vn', 'SE00298', 1, 3, '8C', 7, NULL),
(2260, '316SSE00299', 'Student 316', '316SSE00299@fpt.edu.vn', 'SE00299', 1, 3, '8C', 7, NULL),
(2261, '317SSE00300', 'Student 317', '317SSE00300@fpt.edu.vn', 'SE00300', 1, 3, '8C', 7, NULL),
(2262, '318SSE00301', 'Student 318', '318SSE00301@fpt.edu.vn', 'SE00301', 1, 6, '8C', 7, NULL),
(2263, '319SSE00302', 'Student 319', '319SSE00302@fpt.edu.vn', 'SE00302', 1, 6, '8C', 7, NULL),
(2264, '320SSE00303', 'Student 320', '320SSE00303@fpt.edu.vn', 'SE00303', 1, 6, '8C', 7, NULL),
(2265, '321SSE00304', 'Student 321', '321SSE00304@fpt.edu.vn', 'SE00304', 1, 6, '8C', 7, NULL),
(2266, '322SSE00305', 'Student 322', '322SSE00305@fpt.edu.vn', 'SE00305', 1, 6, '8C', 7, NULL),
(2267, '323SSE00306', 'Student 323', '323SSE00306@fpt.edu.vn', 'SE00306', 1, 6, '8C', 7, NULL),
(2268, '324SSE00307', 'Student 324', '324SSE00307@fpt.edu.vn', 'SE00307', 1, 6, '8C', 7, NULL),
(2269, '325SSE00308', 'Student 325', '325SSE00308@fpt.edu.vn', 'SE00308', 1, 6, '8C', 7, NULL),
(2270, '326SSE00309', 'Student 326', '326SSE00309@fpt.edu.vn', 'SE00309', 1, 6, '8C', 7, NULL),
(2271, '327SSE00310', 'Student 327', '327SSE00310@fpt.edu.vn', 'SE00310', 1, 6, '8C', 7, NULL),
(2272, '328SSE00311', 'Student 328', '328SSE00311@fpt.edu.vn', 'SE00311', 1, 6, '8C', 7, NULL),
(2273, '329SSE00312', 'Student 329', '329SSE00312@fpt.edu.vn', 'SE00312', 1, 6, '8C', 7, NULL),
(2274, '330SSE00313', 'Student 330', '330SSE00313@fpt.edu.vn', 'SE00313', 1, 6, '8C', 7, NULL),
(2275, '331SSE00314', 'Student 331', '331SSE00314@fpt.edu.vn', 'SE00314', 1, 6, '8C', 7, NULL),
(2276, '332SSE00315', 'Student 332', '332SSE00315@fpt.edu.vn', 'SE00315', 1, 6, '8C', 7, NULL),
(2277, '333SSE00316', 'Student 333', '333SSE00316@fpt.edu.vn', 'SE00316', 1, 6, '8C', 7, NULL),
(2278, '334SSE00317', 'Student 334', '334SSE00317@fpt.edu.vn', 'SE00317', 1, 6, '8C', 7, NULL),
(2279, '335SSE00318', 'Student 335', '335SSE00318@fpt.edu.vn', 'SE00318', 1, 6, '8C', 7, NULL),
(2280, '336SSE00319', 'Student 336', '336SSE00319@fpt.edu.vn', 'SE00319', 1, 6, '8C', 7, NULL),
(2281, '337SSE00320', 'Student 337', '337SSE00320@fpt.edu.vn', 'SE00320', 1, 6, '8C', 7, NULL),
(2282, '338SSE00321', 'Student 338', '338SSE00321@fpt.edu.vn', 'SE00321', 1, 6, '8C', 7, NULL),
(2283, '339SSE00322', 'Student 339', '339SSE00322@fpt.edu.vn', 'SE00322', 1, 6, '8C', 7, NULL),
(2284, '340SSE00323', 'Student 340', '340SSE00323@fpt.edu.vn', 'SE00323', 1, 6, '8C', 7, NULL),
(2285, '341SSE00324', 'Student 341', '341SSE00324@fpt.edu.vn', 'SE00324', 1, 6, '8C', 7, NULL),
(2286, '342SSE00325', 'Student 342', '342SSE00325@fpt.edu.vn', 'SE00325', 1, 6, '8C', 7, NULL),
(2287, '343SSE00326', 'Student 343', '343SSE00326@fpt.edu.vn', 'SE00326', 1, 6, '8C', 7, NULL),
(2288, '344SSE00327', 'Student 344', '344SSE00327@fpt.edu.vn', 'SE00327', 1, 6, '8C', 7, NULL),
(2289, '345SSE00328', 'Student 345', '345SSE00328@fpt.edu.vn', 'SE00328', 1, 6, '8C', 7, NULL),
(2290, '346SSE00329', 'Student 346', '346SSE00329@fpt.edu.vn', 'SE00329', 1, 6, '8C', 7, NULL),
(2291, '347SSE00330', 'Student 347', '347SSE00330@fpt.edu.vn', 'SE00330', 1, 6, '8C', 7, NULL),
(2292, '348SSE00331', 'Student 348', '348SSE00331@fpt.edu.vn', 'SE00331', 1, 6, '8C', 7, NULL),
(2293, '349SSE00332', 'Student 349', '349SSE00332@fpt.edu.vn', 'SE00332', 1, 6, '8C', 7, NULL),
(2294, '350SSE00333', 'Student 350', '350SSE00333@fpt.edu.vn', 'SE00333', 1, 6, '8C', 7, NULL),
(2295, '351SSE00334', 'Student 351', '351SSE00334@fpt.edu.vn', 'SE00334', 1, 6, '8C', 7, NULL),
(2296, '352SSE00335', 'Student 352', '352SSE00335@fpt.edu.vn', 'SE00335', 1, 6, '8C', 7, NULL),
(2297, '353SSE00336', 'Student 353', '353SSE00336@fpt.edu.vn', 'SE00336', 1, 6, '8C', 7, NULL),
(2298, '354SSE00337', 'Student 354', '354SSE00337@fpt.edu.vn', 'SE00337', 1, 6, '8C', 7, NULL),
(2299, '355SSE00338', 'Student 355', '355SSE00338@fpt.edu.vn', 'SE00338', 1, 6, '8C', 7, NULL),
(2300, '356SSE00339', 'Student 356', '356SSE00339@fpt.edu.vn', 'SE00339', 1, 6, '8C', 7, NULL),
(2301, '357SSE00340', 'Student 357', '357SSE00340@fpt.edu.vn', 'SE00340', 1, 6, '8C', 7, NULL),
(2302, '358SSE00341', 'Student 358', '358SSE00341@fpt.edu.vn', 'SE00341', 1, 6, '8C', 7, NULL),
(2303, '359SSE00342', 'Student 359', '359SSE00342@fpt.edu.vn', 'SE00342', 1, 6, '8C', 7, NULL),
(2304, '360SSE00343', 'Student 360', '360SSE00343@fpt.edu.vn', 'SE00343', 1, 6, '8C', 7, NULL),
(2305, '361SSE00344', 'Student 361', '361SSE00344@fpt.edu.vn', 'SE00344', 1, 6, '8C', 7, NULL),
(2306, '362SSE00345', 'Student 362', '362SSE00345@fpt.edu.vn', 'SE00345', 1, 6, '8C', 7, NULL),
(2307, '363SSE00346', 'Student 363', '363SSE00346@fpt.edu.vn', 'SE00346', 1, 6, '8C', 7, NULL),
(2308, '364SEC00016', 'Student 364', '364SEC00016@fpt.edu.vn', 'EC00016', 9, NULL, '8C', 7, NULL),
(2309, '365SEC00017', 'Student 365', '365SEC00017@fpt.edu.vn', 'EC00017', 9, NULL, '8C', 7, NULL),
(2310, '366SEC00018', 'Student 366', '366SEC00018@fpt.edu.vn', 'EC00018', 9, NULL, '8C', 7, NULL),
(2311, '367SEC00019', 'Student 367', '367SEC00019@fpt.edu.vn', 'EC00019', 9, NULL, '8C', 7, NULL),
(2312, '368SEC00020', 'Student 368', '368SEC00020@fpt.edu.vn', 'EC00020', 9, NULL, '8C', 7, NULL),
(2313, '369SEC00021', 'Student 369', '369SEC00021@fpt.edu.vn', 'EC00021', 9, NULL, '8C', 7, NULL),
(2314, '370SEC00022', 'Student 370', '370SEC00022@fpt.edu.vn', 'EC00022', 9, NULL, '8C', 7, NULL),
(2315, '371SEC00023', 'Student 371', '371SEC00023@fpt.edu.vn', 'EC00023', 9, NULL, '8C', 7, NULL),
(2316, '372SSE00347', 'Student 372', '372SSE00347@fpt.edu.vn', 'SE00347', 1, 7, '9A', 6, NULL),
(2317, '373SSE00348', 'Student 373', '373SSE00348@fpt.edu.vn', 'SE00348', 1, 7, '9A', 6, NULL),
(2318, '374SSE00349', 'Student 374', '374SSE00349@fpt.edu.vn', 'SE00349', 1, 7, '9A', 6, NULL),
(2319, '375SSE00350', 'Student 375', '375SSE00350@fpt.edu.vn', 'SE00350', 1, 7, '9A', 6, NULL),
(2320, '376SSE00351', 'Student 376', '376SSE00351@fpt.edu.vn', 'SE00351', 1, 7, '9A', 6, NULL),
(2321, '377SSE00352', 'Student 377', '377SSE00352@fpt.edu.vn', 'SE00352', 1, NULL, '9B', 5, NULL),
(2322, '378SSE00353', 'Student 378', '378SSE00353@fpt.edu.vn', 'SE00353', 1, NULL, '9B', 5, NULL),
(2323, '379SSE00354', 'Student 379', '379SSE00354@fpt.edu.vn', 'SE00354', 1, NULL, '9B', 5, NULL),
(2324, '380SSE00355', 'Student 380', '380SSE00355@fpt.edu.vn', 'SE00355', 1, NULL, '9B', 5, NULL),
(2325, '381SSE00356', 'Student 381', '381SSE00356@fpt.edu.vn', 'SE00356', 1, NULL, '9B', 5, NULL),
(2326, '382SSE00357', 'Student 382', '382SSE00357@fpt.edu.vn', 'SE00357', 1, NULL, '9B', 5, NULL),
(2327, '383SSE00358', 'Student 383', '383SSE00358@fpt.edu.vn', 'SE00358', 1, NULL, '9B', 5, NULL),
(2328, '384SSE00359', 'Student 384', '384SSE00359@fpt.edu.vn', 'SE00359', 1, NULL, '9B', 5, NULL),
(2329, '385SSE00360', 'Student 385', '385SSE00360@fpt.edu.vn', 'SE00360', 1, NULL, '9B', 5, NULL),
(2330, '386SSE00361', 'Student 386', '386SSE00361@fpt.edu.vn', 'SE00361', 1, NULL, '9B', 5, NULL),
(2331, '387SSE00362', 'Student 387', '387SSE00362@fpt.edu.vn', 'SE00362', 1, NULL, '9B', 5, NULL),
(2332, '388SSE00363', 'Student 388', '388SSE00363@fpt.edu.vn', 'SE00363', 1, NULL, '9B', 5, NULL),
(2333, '389SSE00364', 'Student 389', '389SSE00364@fpt.edu.vn', 'SE00364', 1, NULL, '9B', 5, NULL),
(2334, '390SSE00365', 'Student 390', '390SSE00365@fpt.edu.vn', 'SE00365', 1, NULL, '9B', 5, NULL),
(2335, '391SSE00366', 'Student 391', '391SSE00366@fpt.edu.vn', 'SE00366', 1, NULL, '9B', 5, NULL),
(2336, '392SSE00367', 'Student 392', '392SSE00367@fpt.edu.vn', 'SE00367', 1, NULL, '9B', 5, NULL),
(2337, '393SSE00368', 'Student 393', '393SSE00368@fpt.edu.vn', 'SE00368', 1, NULL, '9B', 5, NULL),
(2338, '394SSE00369', 'Student 394', '394SSE00369@fpt.edu.vn', 'SE00369', 1, NULL, '9B', 5, NULL),
(2339, '395SSE00370', 'Student 395', '395SSE00370@fpt.edu.vn', 'SE00370', 1, NULL, '9B', 5, NULL),
(2340, '396SSE00371', 'Student 396', '396SSE00371@fpt.edu.vn', 'SE00371', 1, NULL, '9B', 5, NULL),
(2341, '397SSE00372', 'Student 397', '397SSE00372@fpt.edu.vn', 'SE00372', 1, NULL, '9B', 5, NULL),
(2342, '398SSE00373', 'Student 398', '398SSE00373@fpt.edu.vn', 'SE00373', 1, NULL, '9B', 5, NULL),
(2343, '399SSE00374', 'Student 399', '399SSE00374@fpt.edu.vn', 'SE00374', 1, NULL, '9B', 5, NULL),
(2344, '400SSE00375', 'Student 400', '400SSE00375@fpt.edu.vn', 'SE00375', 1, NULL, '9B', 5, NULL),
(2345, '401SSE00376', 'Student 401', '401SSE00376@fpt.edu.vn', 'SE00376', 1, NULL, '9B', 5, NULL),
(2346, '402SSE00377', 'Student 402', '402SSE00377@fpt.edu.vn', 'SE00377', 1, NULL, '9B', 5, NULL),
(2347, '403SSE00378', 'Student 403', '403SSE00378@fpt.edu.vn', 'SE00378', 1, NULL, '9B', 5, NULL),
(2348, '404SSE00379', 'Student 404', '404SSE00379@fpt.edu.vn', 'SE00379', 1, NULL, '9B', 5, NULL),
(2349, '405SSE00380', 'Student 405', '405SSE00380@fpt.edu.vn', 'SE00380', 1, NULL, '9B', 5, NULL),
(2350, '406SSE00381', 'Student 406', '406SSE00381@fpt.edu.vn', 'SE00381', 1, NULL, '9B', 5, NULL),
(2351, '407SSE00382', 'Student 407', '407SSE00382@fpt.edu.vn', 'SE00382', 1, NULL, '9B', 5, NULL),
(2352, '408SSE00383', 'Student 408', '408SSE00383@fpt.edu.vn', 'SE00383', 1, NULL, '9B', 5, NULL),
(2353, '409SSE00384', 'Student 409', '409SSE00384@fpt.edu.vn', 'SE00384', 1, NULL, '9B', 5, NULL),
(2354, '410SSE00385', 'Student 410', '410SSE00385@fpt.edu.vn', 'SE00385', 1, NULL, '9B', 5, NULL),
(2355, '411SSE00386', 'Student 411', '411SSE00386@fpt.edu.vn', 'SE00386', 1, NULL, '9B', 5, NULL),
(2356, '412SSE00387', 'Student 412', '412SSE00387@fpt.edu.vn', 'SE00387', 1, NULL, '9B', 5, NULL),
(2357, '413SSE00388', 'Student 413', '413SSE00388@fpt.edu.vn', 'SE00388', 1, NULL, '9B', 5, NULL),
(2358, '414SSE00389', 'Student 414', '414SSE00389@fpt.edu.vn', 'SE00389', 1, NULL, '9B', 5, NULL),
(2359, '415SSE00390', 'Student 415', '415SSE00390@fpt.edu.vn', 'SE00390', 1, NULL, '9B', 5, NULL),
(2360, '416SSE00391', 'Student 416', '416SSE00391@fpt.edu.vn', 'SE00391', 1, NULL, '9B', 5, NULL),
(2361, '417SSE00392', 'Student 417', '417SSE00392@fpt.edu.vn', 'SE00392', 1, NULL, '9B', 5, NULL),
(2362, '418SSE00393', 'Student 418', '418SSE00393@fpt.edu.vn', 'SE00393', 1, NULL, '9B', 5, NULL),
(2363, '419SSE00394', 'Student 419', '419SSE00394@fpt.edu.vn', 'SE00394', 1, NULL, '9B', 5, NULL),
(2364, '420SSE00395', 'Student 420', '420SSE00395@fpt.edu.vn', 'SE00395', 1, NULL, '9B', 5, NULL),
(2365, '421SSE00396', 'Student 421', '421SSE00396@fpt.edu.vn', 'SE00396', 1, NULL, '9B', 5, NULL),
(2366, '422SSE00397', 'Student 422', '422SSE00397@fpt.edu.vn', 'SE00397', 1, NULL, '9B', 5, NULL),
(2367, '423SSE00398', 'Student 423', '423SSE00398@fpt.edu.vn', 'SE00398', 1, NULL, '9B', 5, NULL),
(2368, '424SSE00399', 'Student 424', '424SSE00399@fpt.edu.vn', 'SE00399', 1, NULL, '9B', 5, NULL),
(2369, '425SSE00400', 'Student 425', '425SSE00400@fpt.edu.vn', 'SE00400', 1, NULL, '9B', 5, NULL),
(2370, '426SSE00401', 'Student 426', '426SSE00401@fpt.edu.vn', 'SE00401', 1, NULL, '9B', 5, NULL),
(2371, '427SSE00402', 'Student 427', '427SSE00402@fpt.edu.vn', 'SE00402', 1, NULL, '9B', 5, NULL),
(2372, '428SSE00403', 'Student 428', '428SSE00403@fpt.edu.vn', 'SE00403', 1, NULL, '9B', 5, NULL),
(2373, '429SSE00404', 'Student 429', '429SSE00404@fpt.edu.vn', 'SE00404', 1, NULL, '9B', 5, NULL),
(2374, '430SSE00405', 'Student 430', '430SSE00405@fpt.edu.vn', 'SE00405', 1, NULL, '9B', 5, NULL),
(2375, '431SSE00406', 'Student 431', '431SSE00406@fpt.edu.vn', 'SE00406', 1, NULL, '9B', 5, NULL),
(2376, '432SSE00407', 'Student 432', '432SSE00407@fpt.edu.vn', 'SE00407', 1, NULL, '9B', 5, NULL),
(2377, '433SSE00408', 'Student 433', '433SSE00408@fpt.edu.vn', 'SE00408', 1, NULL, '9B', 5, NULL),
(2378, '434SSE00409', 'Student 434', '434SSE00409@fpt.edu.vn', 'SE00409', 1, NULL, '9B', 5, NULL),
(2379, '435SSE00410', 'Student 435', '435SSE00410@fpt.edu.vn', 'SE00410', 1, NULL, '9B', 5, NULL),
(2380, '436SSE00411', 'Student 436', '436SSE00411@fpt.edu.vn', 'SE00411', 1, NULL, '9B', 5, NULL),
(2381, '437SEC00024', 'Student 437', '437SEC00024@fpt.edu.vn', 'EC00024', 9, NULL, '9B', 5, NULL),
(2382, '438SEC00025', 'Student 438', '438SEC00025@fpt.edu.vn', 'EC00025', 9, NULL, '9B', 5, NULL),
(2383, '439SEC00026', 'Student 439', '439SEC00026@fpt.edu.vn', 'EC00026', 9, NULL, '9B', 5, NULL),
(2384, '440SEC00027', 'Student 440', '440SEC00027@fpt.edu.vn', 'EC00027', 9, NULL, '9B', 5, NULL),
(2385, '441SEC00028', 'Student 441', '441SEC00028@fpt.edu.vn', 'EC00028', 9, NULL, '9B', 5, NULL),
(2386, '442SIA00000', 'Student 442', '442SIA00000@fpt.edu.vn', 'IA00000', 13, 10, '9B', 5, NULL),
(2387, '443SIA00001', 'Student 443', '443SIA00001@fpt.edu.vn', 'IA00001', 13, 10, '9B', 5, NULL),
(2388, '444SIA00002', 'Student 444', '444SIA00002@fpt.edu.vn', 'IA00002', 13, 10, '9B', 5, NULL),
(2389, '445SIA00003', 'Student 445', '445SIA00003@fpt.edu.vn', 'IA00003', 13, 10, '9B', 5, NULL),
(2390, '446SIA00004', 'Student 446', '446SIA00004@fpt.edu.vn', 'IA00004', 13, 10, '9B', 5, NULL),
(2391, '447SIA00005', 'Student 447', '447SIA00005@fpt.edu.vn', 'IA00005', 13, 10, '9B', 5, NULL),
(2392, '448SIA00006', 'Student 448', '448SIA00006@fpt.edu.vn', 'IA00006', 13, 10, '9B', 5, NULL),
(2393, '449SIA00007', 'Student 449', '449SIA00007@fpt.edu.vn', 'IA00007', 13, 10, '9B', 5, NULL),
(2394, '450SIA00008', 'Student 450', '450SIA00008@fpt.edu.vn', 'IA00008', 13, 10, '9B', 5, NULL),
(2395, '451SIA00009', 'Student 451', '451SIA00009@fpt.edu.vn', 'IA00009', 13, 10, '9B', 5, NULL),
(2396, '452SIA00010', 'Student 452', '452SIA00010@fpt.edu.vn', 'IA00010', 13, 10, '9B', 5, NULL),
(2397, '453SIA00011', 'Student 453', '453SIA00011@fpt.edu.vn', 'IA00011', 13, 10, '9B', 5, NULL),
(2398, '454SIA00012', 'Student 454', '454SIA00012@fpt.edu.vn', 'IA00012', 13, 10, '9B', 5, NULL),
(2399, '455SIA00013', 'Student 455', '455SIA00013@fpt.edu.vn', 'IA00013', 13, 10, '9B', 5, NULL),
(2400, '456SIA00014', 'Student 456', '456SIA00014@fpt.edu.vn', 'IA00014', 13, 10, '9B', 5, NULL),
(2401, '457SIA00015', 'Student 457', '457SIA00015@fpt.edu.vn', 'IA00015', 13, 10, '9B', 5, NULL),
(2402, '458SIA00016', 'Student 458', '458SIA00016@fpt.edu.vn', 'IA00016', 13, 10, '9B', 5, NULL),
(2403, '459SIA00017', 'Student 459', '459SIA00017@fpt.edu.vn', 'IA00017', 13, 10, '9B', 5, NULL),
(2404, '460SIA00018', 'Student 460', '460SIA00018@fpt.edu.vn', 'IA00018', 13, 10, '9B', 5, NULL),
(2405, '461SIA00019', 'Student 461', '461SIA00019@fpt.edu.vn', 'IA00019', 13, 10, '9B', 5, NULL),
(2406, '462SIA00020', 'Student 462', '462SIA00020@fpt.edu.vn', 'IA00020', 13, 10, '9B', 5, NULL),
(2407, '463SIA00021', 'Student 463', '463SIA00021@fpt.edu.vn', 'IA00021', 13, 10, '9B', 5, NULL),
(2408, '464SIA00022', 'Student 464', '464SIA00022@fpt.edu.vn', 'IA00022', 13, 10, '9B', 5, NULL),
(2409, '465SIA00023', 'Student 465', '465SIA00023@fpt.edu.vn', 'IA00023', 13, 11, '9B', 5, NULL),
(2410, '466SIA00024', 'Student 466', '466SIA00024@fpt.edu.vn', 'IA00024', 13, 11, '9B', 5, NULL),
(2411, '467SIA00025', 'Student 467', '467SIA00025@fpt.edu.vn', 'IA00025', 13, 11, '9B', 5, NULL),
(2412, '468SIA00026', 'Student 468', '468SIA00026@fpt.edu.vn', 'IA00026', 13, 11, '9B', 5, NULL),
(2413, '469SIA00027', 'Student 469', '469SIA00027@fpt.edu.vn', 'IA00027', 13, 11, '9B', 5, NULL),
(2414, '470SIA00028', 'Student 470', '470SIA00028@fpt.edu.vn', 'IA00028', 13, 11, '9B', 5, NULL),
(2415, '471SIA00029', 'Student 471', '471SIA00029@fpt.edu.vn', 'IA00029', 13, 11, '9B', 5, NULL),
(2416, '472SIA00030', 'Student 472', '472SIA00030@fpt.edu.vn', 'IA00030', 13, 11, '9B', 5, NULL),
(2417, '473SIA00031', 'Student 473', '473SIA00031@fpt.edu.vn', 'IA00031', 13, 11, '9B', 5, NULL),
(2418, '474SIA00032', 'Student 474', '474SIA00032@fpt.edu.vn', 'IA00032', 13, 11, '9B', 5, NULL),
(2419, '475SIA00033', 'Student 475', '475SIA00033@fpt.edu.vn', 'IA00033', 13, 11, '9B', 5, NULL),
(2420, '476SIA00034', 'Student 476', '476SIA00034@fpt.edu.vn', 'IA00034', 13, 11, '9B', 5, NULL),
(2421, '477SIA00035', 'Student 477', '477SIA00035@fpt.edu.vn', 'IA00035', 13, 11, '9B', 5, NULL),
(2422, '478SIA00036', 'Student 478', '478SIA00036@fpt.edu.vn', 'IA00036', 13, 11, '9B', 5, NULL),
(2423, '479SIA00037', 'Student 479', '479SIA00037@fpt.edu.vn', 'IA00037', 13, 11, '9B', 5, NULL),
(2424, '480SIA00038', 'Student 480', '480SIA00038@fpt.edu.vn', 'IA00038', 13, 11, '9B', 5, NULL),
(2425, '481SIA00039', 'Student 481', '481SIA00039@fpt.edu.vn', 'IA00039', 13, 11, '9B', 5, NULL),
(2426, '482SIA00040', 'Student 482', '482SIA00040@fpt.edu.vn', 'IA00040', 13, 11, '9B', 5, NULL),
(2427, '483SIA00041', 'Student 483', '483SIA00041@fpt.edu.vn', 'IA00041', 13, 11, '9B', 5, NULL),
(2428, '484SIA00042', 'Student 484', '484SIA00042@fpt.edu.vn', 'IA00042', 13, 11, '9B', 5, NULL),
(2429, '485SIA00043', 'Student 485', '485SIA00043@fpt.edu.vn', 'IA00043', 13, 11, '9B', 5, NULL),
(2430, '486SIA00044', 'Student 486', '486SIA00044@fpt.edu.vn', 'IA00044', 13, 11, '9B', 5, NULL),
(2431, '487SIA00045', 'Student 487', '487SIA00045@fpt.edu.vn', 'IA00045', 13, 11, '9B', 5, NULL),
(2432, '488SSE00412', 'Student 488', '488SSE00412@fpt.edu.vn', 'SE00412', 1, 7, '9B', 5, NULL),
(2433, '489SSE00413', 'Student 489', '489SSE00413@fpt.edu.vn', 'SE00413', 1, 7, '9B', 5, NULL),
(2434, '490SSE00414', 'Student 490', '490SSE00414@fpt.edu.vn', 'SE00414', 1, 7, '9B', 5, NULL),
(2435, '491SSE00415', 'Student 491', '491SSE00415@fpt.edu.vn', 'SE00415', 1, 7, '9B', 5, NULL),
(2436, '492SSE00416', 'Student 492', '492SSE00416@fpt.edu.vn', 'SE00416', 1, NULL, '9C', 4, NULL),
(2437, '493SSE00417', 'Student 493', '493SSE00417@fpt.edu.vn', 'SE00417', 1, NULL, '9C', 4, NULL),
(2438, '494SSE00418', 'Student 494', '494SSE00418@fpt.edu.vn', 'SE00418', 1, NULL, '9C', 4, NULL),
(2439, '495SSE00419', 'Student 495', '495SSE00419@fpt.edu.vn', 'SE00419', 1, NULL, '9C', 4, NULL),
(2440, '496SSE00420', 'Student 496', '496SSE00420@fpt.edu.vn', 'SE00420', 1, NULL, '9C', 4, NULL),
(2441, '497SSE00421', 'Student 497', '497SSE00421@fpt.edu.vn', 'SE00421', 1, NULL, '9C', 4, NULL),
(2442, '498SSE00422', 'Student 498', '498SSE00422@fpt.edu.vn', 'SE00422', 1, NULL, '9C', 4, NULL),
(2443, '499SSE00423', 'Student 499', '499SSE00423@fpt.edu.vn', 'SE00423', 1, NULL, '9C', 4, NULL),
(2444, '500SSE00424', 'Student 500', '500SSE00424@fpt.edu.vn', 'SE00424', 1, NULL, '9C', 4, NULL),
(2445, '501SSE00425', 'Student 501', '501SSE00425@fpt.edu.vn', 'SE00425', 1, NULL, '9C', 4, NULL),
(2446, '502SSE00426', 'Student 502', '502SSE00426@fpt.edu.vn', 'SE00426', 1, NULL, '9C', 4, NULL),
(2447, '503SSE00427', 'Student 503', '503SSE00427@fpt.edu.vn', 'SE00427', 1, NULL, '9C', 4, NULL),
(2448, '504SSE00428', 'Student 504', '504SSE00428@fpt.edu.vn', 'SE00428', 1, NULL, '9C', 4, NULL),
(2449, '505SSE00429', 'Student 505', '505SSE00429@fpt.edu.vn', 'SE00429', 1, NULL, '9C', 4, NULL),
(2450, '506SSE00430', 'Student 506', '506SSE00430@fpt.edu.vn', 'SE00430', 1, NULL, '9C', 4, NULL),
(2451, '507SSE00431', 'Student 507', '507SSE00431@fpt.edu.vn', 'SE00431', 1, NULL, '9C', 4, NULL),
(2452, '508SSE00432', 'Student 508', '508SSE00432@fpt.edu.vn', 'SE00432', 1, NULL, '9C', 4, NULL),
(2453, '509SSE00433', 'Student 509', '509SSE00433@fpt.edu.vn', 'SE00433', 1, NULL, '9C', 4, NULL),
(2454, '510SSE00434', 'Student 510', '510SSE00434@fpt.edu.vn', 'SE00434', 1, NULL, '9C', 4, NULL),
(2455, '511SSE00435', 'Student 511', '511SSE00435@fpt.edu.vn', 'SE00435', 1, NULL, '9C', 4, NULL),
(2456, '512SSE00436', 'Student 512', '512SSE00436@fpt.edu.vn', 'SE00436', 1, NULL, '9C', 4, NULL),
(2457, '513SSE00437', 'Student 513', '513SSE00437@fpt.edu.vn', 'SE00437', 1, NULL, '9C', 4, NULL),
(2458, '514SSE00438', 'Student 514', '514SSE00438@fpt.edu.vn', 'SE00438', 1, NULL, '9C', 4, NULL),
(2459, '515SSE00439', 'Student 515', '515SSE00439@fpt.edu.vn', 'SE00439', 1, NULL, '9C', 4, NULL),
(2460, '516SSE00440', 'Student 516', '516SSE00440@fpt.edu.vn', 'SE00440', 1, NULL, '9C', 4, NULL),
(2461, '517SSE00441', 'Student 517', '517SSE00441@fpt.edu.vn', 'SE00441', 1, NULL, '9C', 4, NULL),
(2462, '518SSE00442', 'Student 518', '518SSE00442@fpt.edu.vn', 'SE00442', 1, NULL, '9C', 4, NULL),
(2463, '519SSE00443', 'Student 519', '519SSE00443@fpt.edu.vn', 'SE00443', 1, NULL, '9C', 4, NULL),
(2464, '520SSE00444', 'Student 520', '520SSE00444@fpt.edu.vn', 'SE00444', 1, NULL, '9C', 4, NULL),
(2465, '521SSE00445', 'Student 521', '521SSE00445@fpt.edu.vn', 'SE00445', 1, NULL, '9C', 4, NULL),
(2466, '522SSE00446', 'Student 522', '522SSE00446@fpt.edu.vn', 'SE00446', 1, NULL, '9C', 4, NULL),
(2467, '523SSE00447', 'Student 523', '523SSE00447@fpt.edu.vn', 'SE00447', 1, NULL, '9C', 4, NULL),
(2468, '524SSE00448', 'Student 524', '524SSE00448@fpt.edu.vn', 'SE00448', 1, NULL, '9C', 4, NULL),
(2469, '525SSE00449', 'Student 525', '525SSE00449@fpt.edu.vn', 'SE00449', 1, NULL, '9C', 4, NULL),
(2470, '526SSE00450', 'Student 526', '526SSE00450@fpt.edu.vn', 'SE00450', 1, NULL, '9C', 4, NULL),
(2471, '527SSE00451', 'Student 527', '527SSE00451@fpt.edu.vn', 'SE00451', 1, NULL, '9C', 4, NULL),
(2472, '528SSE00452', 'Student 528', '528SSE00452@fpt.edu.vn', 'SE00452', 1, NULL, '9C', 4, NULL);
INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(2473, '529SSE00453', 'Student 529', '529SSE00453@fpt.edu.vn', 'SE00453', 1, NULL, '9C', 4, NULL),
(2474, '530SSE00454', 'Student 530', '530SSE00454@fpt.edu.vn', 'SE00454', 1, NULL, '9C', 4, NULL),
(2475, '531SSE00455', 'Student 531', '531SSE00455@fpt.edu.vn', 'SE00455', 1, NULL, '9C', 4, NULL),
(2476, '532SSE00456', 'Student 532', '532SSE00456@fpt.edu.vn', 'SE00456', 1, NULL, '9C', 4, NULL),
(2477, '533SSE00457', 'Student 533', '533SSE00457@fpt.edu.vn', 'SE00457', 1, NULL, '9C', 4, NULL),
(2478, '534SSE00458', 'Student 534', '534SSE00458@fpt.edu.vn', 'SE00458', 1, NULL, '9C', 4, NULL),
(2479, '535SSE00459', 'Student 535', '535SSE00459@fpt.edu.vn', 'SE00459', 1, NULL, '9C', 4, NULL),
(2480, '536SSE00460', 'Student 536', '536SSE00460@fpt.edu.vn', 'SE00460', 1, NULL, '9C', 4, NULL),
(2481, '537SSE00461', 'Student 537', '537SSE00461@fpt.edu.vn', 'SE00461', 1, NULL, '9C', 4, NULL),
(2482, '538SSE00462', 'Student 538', '538SSE00462@fpt.edu.vn', 'SE00462', 1, NULL, '9C', 4, NULL),
(2483, '539SSE00463', 'Student 539', '539SSE00463@fpt.edu.vn', 'SE00463', 1, NULL, '9C', 4, NULL),
(2484, '540SSE00464', 'Student 540', '540SSE00464@fpt.edu.vn', 'SE00464', 1, NULL, '9C', 4, NULL),
(2485, '541SSE00465', 'Student 541', '541SSE00465@fpt.edu.vn', 'SE00465', 1, NULL, '9C', 4, NULL),
(2486, '542SSE00466', 'Student 542', '542SSE00466@fpt.edu.vn', 'SE00466', 1, NULL, '9C', 4, NULL),
(2487, '543SSE00467', 'Student 543', '543SSE00467@fpt.edu.vn', 'SE00467', 1, NULL, '9C', 4, NULL),
(2488, '544SSE00468', 'Student 544', '544SSE00468@fpt.edu.vn', 'SE00468', 1, NULL, '9C', 4, NULL),
(2489, '545SSE00469', 'Student 545', '545SSE00469@fpt.edu.vn', 'SE00469', 1, NULL, '9C', 4, NULL),
(2490, '546SSE00470', 'Student 546', '546SSE00470@fpt.edu.vn', 'SE00470', 1, NULL, '9C', 4, NULL),
(2491, '547SSE00471', 'Student 547', '547SSE00471@fpt.edu.vn', 'SE00471', 1, NULL, '9C', 4, NULL),
(2492, '548SSE00472', 'Student 548', '548SSE00472@fpt.edu.vn', 'SE00472', 1, NULL, '9C', 4, NULL),
(2493, '549SSE00473', 'Student 549', '549SSE00473@fpt.edu.vn', 'SE00473', 1, NULL, '9C', 4, NULL),
(2494, '550SSE00474', 'Student 550', '550SSE00474@fpt.edu.vn', 'SE00474', 1, NULL, '9C', 4, NULL),
(2495, '551SSE00475', 'Student 551', '551SSE00475@fpt.edu.vn', 'SE00475', 1, NULL, '9C', 4, NULL),
(2496, '552SSE00476', 'Student 552', '552SSE00476@fpt.edu.vn', 'SE00476', 1, NULL, '9C', 4, NULL),
(2497, '553SSE00477', 'Student 553', '553SSE00477@fpt.edu.vn', 'SE00477', 1, NULL, '9C', 4, NULL),
(2498, '554SSE00478', 'Student 554', '554SSE00478@fpt.edu.vn', 'SE00478', 1, NULL, '9C', 4, NULL),
(2499, '555SSE00479', 'Student 555', '555SSE00479@fpt.edu.vn', 'SE00479', 1, NULL, '9C', 4, NULL),
(2500, '556SSE00480', 'Student 556', '556SSE00480@fpt.edu.vn', 'SE00480', 1, NULL, '9C', 4, NULL),
(2501, '557SSE00481', 'Student 557', '557SSE00481@fpt.edu.vn', 'SE00481', 1, NULL, '9C', 4, NULL),
(2502, '558SSE00482', 'Student 558', '558SSE00482@fpt.edu.vn', 'SE00482', 1, NULL, '9C', 4, NULL),
(2503, '559SSE00483', 'Student 559', '559SSE00483@fpt.edu.vn', 'SE00483', 1, NULL, '9C', 4, NULL),
(2504, '560SSE00484', 'Student 560', '560SSE00484@fpt.edu.vn', 'SE00484', 1, NULL, '9C', 4, NULL),
(2505, '561SSE00485', 'Student 561', '561SSE00485@fpt.edu.vn', 'SE00485', 1, NULL, '9C', 4, NULL),
(2506, '562SSE00486', 'Student 562', '562SSE00486@fpt.edu.vn', 'SE00486', 1, NULL, '9C', 4, NULL),
(2507, '563SSE00487', 'Student 563', '563SSE00487@fpt.edu.vn', 'SE00487', 1, NULL, '9C', 4, NULL),
(2508, '564SSE00488', 'Student 564', '564SSE00488@fpt.edu.vn', 'SE00488', 1, NULL, '9C', 4, NULL),
(2509, '565SSE00489', 'Student 565', '565SSE00489@fpt.edu.vn', 'SE00489', 1, NULL, '9C', 4, NULL),
(2510, '566SSE00490', 'Student 566', '566SSE00490@fpt.edu.vn', 'SE00490', 1, NULL, '9C', 4, NULL),
(2511, '567SSE00491', 'Student 567', '567SSE00491@fpt.edu.vn', 'SE00491', 1, NULL, '9C', 4, NULL),
(2512, '568SSE00492', 'Student 568', '568SSE00492@fpt.edu.vn', 'SE00492', 1, NULL, '9C', 4, NULL),
(2513, '569SSE00493', 'Student 569', '569SSE00493@fpt.edu.vn', 'SE00493', 1, NULL, '9C', 4, NULL),
(2514, '570SSE00494', 'Student 570', '570SSE00494@fpt.edu.vn', 'SE00494', 1, NULL, '9C', 4, NULL),
(2515, '571SSE00495', 'Student 571', '571SSE00495@fpt.edu.vn', 'SE00495', 1, NULL, '9C', 4, NULL),
(2516, '572SSE00496', 'Student 572', '572SSE00496@fpt.edu.vn', 'SE00496', 1, NULL, '9C', 4, NULL),
(2517, '573SSE00497', 'Student 573', '573SSE00497@fpt.edu.vn', 'SE00497', 1, NULL, '9C', 4, NULL),
(2518, '574SSE00498', 'Student 574', '574SSE00498@fpt.edu.vn', 'SE00498', 1, NULL, '9C', 4, NULL),
(2519, '575SSE00499', 'Student 575', '575SSE00499@fpt.edu.vn', 'SE00499', 1, NULL, '9C', 4, NULL),
(2520, '576SSE00500', 'Student 576', '576SSE00500@fpt.edu.vn', 'SE00500', 1, NULL, '9C', 4, NULL),
(2521, '577SSE00501', 'Student 577', '577SSE00501@fpt.edu.vn', 'SE00501', 1, NULL, '9C', 4, NULL),
(2522, '578SSE00502', 'Student 578', '578SSE00502@fpt.edu.vn', 'SE00502', 1, NULL, '9C', 4, NULL),
(2523, '579SSE00503', 'Student 579', '579SSE00503@fpt.edu.vn', 'SE00503', 1, NULL, '9C', 4, NULL),
(2524, '580SSE00504', 'Student 580', '580SSE00504@fpt.edu.vn', 'SE00504', 1, NULL, '9C', 4, NULL),
(2525, '581SSE00505', 'Student 581', '581SSE00505@fpt.edu.vn', 'SE00505', 1, NULL, '9C', 4, NULL),
(2526, '582SSE00506', 'Student 582', '582SSE00506@fpt.edu.vn', 'SE00506', 1, NULL, '9C', 4, NULL),
(2527, '583SSE00507', 'Student 583', '583SSE00507@fpt.edu.vn', 'SE00507', 1, NULL, '9C', 4, NULL),
(2528, '584SSE00508', 'Student 584', '584SSE00508@fpt.edu.vn', 'SE00508', 1, NULL, '9C', 4, NULL),
(2529, '585SSE00509', 'Student 585', '585SSE00509@fpt.edu.vn', 'SE00509', 1, NULL, '9C', 4, NULL),
(2530, '586SSE00510', 'Student 586', '586SSE00510@fpt.edu.vn', 'SE00510', 1, NULL, '9C', 4, NULL),
(2531, '587SSE00511', 'Student 587', '587SSE00511@fpt.edu.vn', 'SE00511', 1, NULL, '9C', 4, NULL),
(2532, '588SSE00512', 'Student 588', '588SSE00512@fpt.edu.vn', 'SE00512', 1, NULL, '9C', 4, NULL),
(2533, '589SSE00513', 'Student 589', '589SSE00513@fpt.edu.vn', 'SE00513', 1, NULL, '9C', 4, NULL),
(2534, '590SSE00514', 'Student 590', '590SSE00514@fpt.edu.vn', 'SE00514', 1, NULL, '9C', 4, NULL),
(2535, '591SSE00515', 'Student 591', '591SSE00515@fpt.edu.vn', 'SE00515', 1, NULL, '9C', 4, NULL),
(2536, '592SSE00516', 'Student 592', '592SSE00516@fpt.edu.vn', 'SE00516', 1, NULL, '9C', 4, NULL),
(2537, '593SSE00517', 'Student 593', '593SSE00517@fpt.edu.vn', 'SE00517', 1, NULL, '9C', 4, NULL),
(2538, '594SSE00518', 'Student 594', '594SSE00518@fpt.edu.vn', 'SE00518', 1, NULL, '9C', 4, NULL),
(2539, '595SSE00519', 'Student 595', '595SSE00519@fpt.edu.vn', 'SE00519', 1, NULL, '9C', 4, NULL),
(2540, '596SSE00520', 'Student 596', '596SSE00520@fpt.edu.vn', 'SE00520', 1, NULL, '9C', 4, NULL),
(2541, '597SSE00521', 'Student 597', '597SSE00521@fpt.edu.vn', 'SE00521', 1, NULL, '9C', 4, NULL),
(2542, '598SSE00522', 'Student 598', '598SSE00522@fpt.edu.vn', 'SE00522', 1, NULL, '9C', 4, NULL),
(2543, '599SSE00523', 'Student 599', '599SSE00523@fpt.edu.vn', 'SE00523', 1, NULL, '9C', 4, NULL),
(2544, '600SSE00524', 'Student 600', '600SSE00524@fpt.edu.vn', 'SE00524', 1, NULL, '9C', 4, NULL),
(2545, '601SSE00525', 'Student 601', '601SSE00525@fpt.edu.vn', 'SE00525', 1, NULL, '9C', 4, NULL),
(2546, '602SSE00526', 'Student 602', '602SSE00526@fpt.edu.vn', 'SE00526', 1, NULL, '9C', 4, NULL),
(2547, '603SSE00527', 'Student 603', '603SSE00527@fpt.edu.vn', 'SE00527', 1, NULL, '9C', 4, NULL),
(2548, '604SSE00528', 'Student 604', '604SSE00528@fpt.edu.vn', 'SE00528', 1, NULL, '9C', 4, NULL),
(2549, '605SSE00529', 'Student 605', '605SSE00529@fpt.edu.vn', 'SE00529', 1, NULL, '9C', 4, NULL),
(2550, '606SSE00530', 'Student 606', '606SSE00530@fpt.edu.vn', 'SE00530', 1, NULL, '9C', 4, NULL),
(2551, '607SSE00531', 'Student 607', '607SSE00531@fpt.edu.vn', 'SE00531', 1, NULL, '9C', 4, NULL),
(2552, '608SSE00532', 'Student 608', '608SSE00532@fpt.edu.vn', 'SE00532', 1, NULL, '9C', 4, NULL),
(2553, '609SSE00533', 'Student 609', '609SSE00533@fpt.edu.vn', 'SE00533', 1, NULL, '9C', 4, NULL),
(2554, '610SSE00534', 'Student 610', '610SSE00534@fpt.edu.vn', 'SE00534', 1, NULL, '9C', 4, NULL),
(2555, '611SSE00535', 'Student 611', '611SSE00535@fpt.edu.vn', 'SE00535', 1, NULL, '9C', 4, NULL),
(2556, '612SSE00536', 'Student 612', '612SSE00536@fpt.edu.vn', 'SE00536', 1, NULL, '9C', 4, NULL),
(2557, '613SSE00537', 'Student 613', '613SSE00537@fpt.edu.vn', 'SE00537', 1, NULL, '9C', 4, NULL),
(2558, '614SSE00538', 'Student 614', '614SSE00538@fpt.edu.vn', 'SE00538', 1, NULL, '9C', 4, NULL),
(2559, '615SSE00539', 'Student 615', '615SSE00539@fpt.edu.vn', 'SE00539', 1, NULL, '9C', 4, NULL),
(2560, '616SSE00540', 'Student 616', '616SSE00540@fpt.edu.vn', 'SE00540', 1, NULL, '9C', 4, NULL),
(2561, '617SSE00541', 'Student 617', '617SSE00541@fpt.edu.vn', 'SE00541', 1, NULL, '9C', 4, NULL),
(2562, '618SSE00542', 'Student 618', '618SSE00542@fpt.edu.vn', 'SE00542', 1, NULL, '9C', 4, NULL),
(2563, '619SSE00543', 'Student 619', '619SSE00543@fpt.edu.vn', 'SE00543', 1, NULL, '9C', 4, NULL),
(2564, '620SSE00544', 'Student 620', '620SSE00544@fpt.edu.vn', 'SE00544', 1, NULL, '9C', 4, NULL),
(2565, '621SSE00545', 'Student 621', '621SSE00545@fpt.edu.vn', 'SE00545', 1, NULL, '9C', 4, NULL),
(2566, '622SSE00546', 'Student 622', '622SSE00546@fpt.edu.vn', 'SE00546', 1, NULL, '9C', 4, NULL),
(2567, '623SSE00547', 'Student 623', '623SSE00547@fpt.edu.vn', 'SE00547', 1, NULL, '9C', 4, NULL),
(2568, '624SSE00548', 'Student 624', '624SSE00548@fpt.edu.vn', 'SE00548', 1, NULL, '9C', 4, NULL),
(2569, '625SSE00549', 'Student 625', '625SSE00549@fpt.edu.vn', 'SE00549', 1, NULL, '9C', 4, NULL),
(2570, '626SSE00550', 'Student 626', '626SSE00550@fpt.edu.vn', 'SE00550', 1, NULL, '9C', 4, NULL),
(2571, '627SSE00551', 'Student 627', '627SSE00551@fpt.edu.vn', 'SE00551', 1, NULL, '9C', 4, NULL),
(2572, '628SSE00552', 'Student 628', '628SSE00552@fpt.edu.vn', 'SE00552', 1, 7, '9C', 4, NULL),
(2573, '629SSE00553', 'Student 629', '629SSE00553@fpt.edu.vn', 'SE00553', 1, 7, '9C', 4, NULL),
(2574, '630SSE00554', 'Student 630', '630SSE00554@fpt.edu.vn', 'SE00554', 1, 7, '9C', 4, NULL),
(2575, '631SSE00555', 'Student 631', '631SSE00555@fpt.edu.vn', 'SE00555', 1, 7, '9C', 4, NULL),
(2576, '632SSE00556', 'Student 632', '632SSE00556@fpt.edu.vn', 'SE00556', 1, 7, '9C', 4, NULL),
(2577, '633SSE00557', 'Student 633', '633SSE00557@fpt.edu.vn', 'SE00557', 1, 7, '9C', 4, NULL),
(2578, '634SEC00029', 'Student 634', '634SEC00029@fpt.edu.vn', 'EC00029', 9, NULL, '9C', 4, NULL),
(2579, '635SEC00030', 'Student 635', '635SEC00030@fpt.edu.vn', 'EC00030', 9, NULL, '9C', 4, NULL),
(2580, '636SEC00031', 'Student 636', '636SEC00031@fpt.edu.vn', 'EC00031', 9, NULL, '9C', 4, NULL),
(2581, '637SEC00032', 'Student 637', '637SEC00032@fpt.edu.vn', 'EC00032', 9, NULL, '9C', 4, NULL),
(2582, '638SEC00033', 'Student 638', '638SEC00033@fpt.edu.vn', 'EC00033', 9, NULL, '9C', 4, NULL),
(2583, '639SEC00034', 'Student 639', '639SEC00034@fpt.edu.vn', 'EC00034', 9, NULL, '9C', 4, NULL),
(2584, '640SIA00046', 'Student 640', '640SIA00046@fpt.edu.vn', 'IA00046', 13, NULL, '9C', 4, NULL),
(2585, '641SIA00047', 'Student 641', '641SIA00047@fpt.edu.vn', 'IA00047', 13, NULL, '9C', 4, NULL),
(2586, '642SIA00048', 'Student 642', '642SIA00048@fpt.edu.vn', 'IA00048', 13, NULL, '9C', 4, NULL),
(2587, '643SIA00049', 'Student 643', '643SIA00049@fpt.edu.vn', 'IA00049', 13, NULL, '9C', 4, NULL),
(2588, '644SIA00050', 'Student 644', '644SIA00050@fpt.edu.vn', 'IA00050', 13, NULL, '9C', 4, NULL),
(2589, '645SIA00051', 'Student 645', '645SIA00051@fpt.edu.vn', 'IA00051', 13, NULL, '9C', 4, NULL),
(2590, '646SIA00052', 'Student 646', '646SIA00052@fpt.edu.vn', 'IA00052', 13, NULL, '9C', 4, NULL),
(2591, '647SIA00053', 'Student 647', '647SIA00053@fpt.edu.vn', 'IA00053', 13, NULL, '9C', 4, NULL),
(2592, '648SIA00054', 'Student 648', '648SIA00054@fpt.edu.vn', 'IA00054', 13, NULL, '9C', 4, NULL),
(2593, '649SIA00055', 'Student 649', '649SIA00055@fpt.edu.vn', 'IA00055', 13, NULL, '9C', 4, NULL),
(2594, '650SIA00056', 'Student 650', '650SIA00056@fpt.edu.vn', 'IA00056', 13, NULL, '9C', 4, NULL),
(2595, '651SIA00057', 'Student 651', '651SIA00057@fpt.edu.vn', 'IA00057', 13, NULL, '9C', 4, NULL),
(2596, '652SIA00058', 'Student 652', '652SIA00058@fpt.edu.vn', 'IA00058', 13, NULL, '9C', 4, NULL),
(2597, '653SIA00059', 'Student 653', '653SIA00059@fpt.edu.vn', 'IA00059', 13, NULL, '9C', 4, NULL),
(2598, '654SIA00060', 'Student 654', '654SIA00060@fpt.edu.vn', 'IA00060', 13, NULL, '9C', 4, NULL),
(2599, '655SIA00061', 'Student 655', '655SIA00061@fpt.edu.vn', 'IA00061', 13, NULL, '9C', 4, NULL),
(2600, '656SIA00062', 'Student 656', '656SIA00062@fpt.edu.vn', 'IA00062', 13, NULL, '9C', 4, NULL),
(2601, '657SIA00063', 'Student 657', '657SIA00063@fpt.edu.vn', 'IA00063', 13, NULL, '9C', 4, NULL),
(2602, '658SIA00064', 'Student 658', '658SIA00064@fpt.edu.vn', 'IA00064', 13, NULL, '9C', 4, NULL),
(2603, '659SIA00065', 'Student 659', '659SIA00065@fpt.edu.vn', 'IA00065', 13, NULL, '9C', 4, NULL),
(2604, '660SIA00066', 'Student 660', '660SIA00066@fpt.edu.vn', 'IA00066', 13, NULL, '9C', 4, NULL),
(2605, '661SIA00067', 'Student 661', '661SIA00067@fpt.edu.vn', 'IA00067', 13, NULL, '9C', 4, NULL),
(2606, '662SIA00068', 'Student 662', '662SIA00068@fpt.edu.vn', 'IA00068', 13, NULL, '9C', 4, NULL),
(2607, '663SIA00069', 'Student 663', '663SIA00069@fpt.edu.vn', 'IA00069', 13, NULL, '9C', 4, NULL),
(2608, '664SIA00070', 'Student 664', '664SIA00070@fpt.edu.vn', 'IA00070', 13, NULL, '9C', 4, NULL),
(2609, '665SIA00071', 'Student 665', '665SIA00071@fpt.edu.vn', 'IA00071', 13, NULL, '9C', 4, NULL),
(2610, '666SIA00072', 'Student 666', '666SIA00072@fpt.edu.vn', 'IA00072', 13, NULL, '9C', 4, NULL),
(2611, '667SIA00073', 'Student 667', '667SIA00073@fpt.edu.vn', 'IA00073', 13, NULL, '9C', 4, NULL),
(2612, '668SIA00074', 'Student 668', '668SIA00074@fpt.edu.vn', 'IA00074', 13, NULL, '9C', 4, NULL),
(2613, '669SIA00075', 'Student 669', '669SIA00075@fpt.edu.vn', 'IA00075', 13, NULL, '9C', 4, NULL),
(2614, '670SIA00076', 'Student 670', '670SIA00076@fpt.edu.vn', 'IA00076', 13, NULL, '9C', 4, NULL),
(2615, '671SIA00077', 'Student 671', '671SIA00077@fpt.edu.vn', 'IA00077', 13, NULL, '9C', 4, NULL),
(2616, '672SIA00078', 'Student 672', '672SIA00078@fpt.edu.vn', 'IA00078', 13, NULL, '9C', 4, NULL),
(2617, '673SIA00079', 'Student 673', '673SIA00079@fpt.edu.vn', 'IA00079', 13, NULL, '9C', 4, NULL),
(2618, '674SIA00080', 'Student 674', '674SIA00080@fpt.edu.vn', 'IA00080', 13, NULL, '9C', 4, NULL),
(2619, '675SIA00081', 'Student 675', '675SIA00081@fpt.edu.vn', 'IA00081', 13, NULL, '9C', 4, NULL),
(2620, '676SIA00082', 'Student 676', '676SIA00082@fpt.edu.vn', 'IA00082', 13, NULL, '9C', 4, NULL),
(2621, '677SIA00083', 'Student 677', '677SIA00083@fpt.edu.vn', 'IA00083', 13, NULL, '9C', 4, NULL),
(2622, '678SIA00084', 'Student 678', '678SIA00084@fpt.edu.vn', 'IA00084', 13, NULL, '9C', 4, NULL),
(2623, '679SIA00085', 'Student 679', '679SIA00085@fpt.edu.vn', 'IA00085', 13, NULL, '9C', 4, NULL),
(2624, '680SIA00086', 'Student 680', '680SIA00086@fpt.edu.vn', 'IA00086', 13, NULL, '9C', 4, NULL),
(2625, '681SGD00000', 'Student 681', '681SGD00000@fpt.edu.vn', 'GD00000', 12, NULL, '9C', 4, NULL),
(2626, '682SGD00001', 'Student 682', '682SGD00001@fpt.edu.vn', 'GD00001', 12, NULL, '9C', 4, NULL),
(2627, '683SGD00002', 'Student 683', '683SGD00002@fpt.edu.vn', 'GD00002', 12, NULL, '9C', 4, NULL),
(2628, '684SGD00003', 'Student 684', '684SGD00003@fpt.edu.vn', 'GD00003', 12, NULL, '9C', 4, NULL),
(2629, '685SGD00004', 'Student 685', '685SGD00004@fpt.edu.vn', 'GD00004', 12, NULL, '9C', 4, NULL),
(2630, '686SGD00005', 'Student 686', '686SGD00005@fpt.edu.vn', 'GD00005', 12, NULL, '9C', 4, NULL),
(2631, '687SGD00006', 'Student 687', '687SGD00006@fpt.edu.vn', 'GD00006', 12, NULL, '9C', 4, NULL),
(2632, '688SGD00007', 'Student 688', '688SGD00007@fpt.edu.vn', 'GD00007', 12, NULL, '9C', 4, NULL),
(2633, '689SGD00008', 'Student 689', '689SGD00008@fpt.edu.vn', 'GD00008', 12, NULL, '9C', 4, NULL),
(2634, '690SGD00009', 'Student 690', '690SGD00009@fpt.edu.vn', 'GD00009', 12, NULL, '9C', 4, NULL),
(2635, '691SGD00010', 'Student 691', '691SGD00010@fpt.edu.vn', 'GD00010', 12, NULL, '9C', 4, NULL),
(2636, '692SGD00011', 'Student 692', '692SGD00011@fpt.edu.vn', 'GD00011', 12, NULL, '9C', 4, NULL),
(2637, '693SGD00012', 'Student 693', '693SGD00012@fpt.edu.vn', 'GD00012', 12, NULL, '9C', 4, NULL),
(2638, '694SGD00013', 'Student 694', '694SGD00013@fpt.edu.vn', 'GD00013', 12, NULL, '9C', 4, NULL),
(2639, '695SGD00014', 'Student 695', '695SGD00014@fpt.edu.vn', 'GD00014', 12, NULL, '9C', 4, NULL),
(2640, '696SGD00015', 'Student 696', '696SGD00015@fpt.edu.vn', 'GD00015', 12, NULL, '9C', 4, NULL),
(2641, '697SSE00558', 'Student 697', '697SSE00558@fpt.edu.vn', 'SE00558', 1, NULL, '10A', 3, NULL),
(2642, '698SSE00559', 'Student 698', '698SSE00559@fpt.edu.vn', 'SE00559', 1, NULL, '10A', 3, NULL),
(2643, '699SSE00560', 'Student 699', '699SSE00560@fpt.edu.vn', 'SE00560', 1, NULL, '10A', 3, NULL),
(2644, '700SSE00561', 'Student 700', '700SSE00561@fpt.edu.vn', 'SE00561', 1, NULL, '10A', 3, NULL),
(2645, '701SSE00562', 'Student 701', '701SSE00562@fpt.edu.vn', 'SE00562', 1, NULL, '10A', 3, NULL),
(2646, '702SSE00563', 'Student 702', '702SSE00563@fpt.edu.vn', 'SE00563', 1, NULL, '10A', 3, NULL),
(2647, '703SSE00564', 'Student 703', '703SSE00564@fpt.edu.vn', 'SE00564', 1, NULL, '10A', 3, NULL),
(2648, '704SSE00565', 'Student 704', '704SSE00565@fpt.edu.vn', 'SE00565', 1, NULL, '10A', 3, NULL),
(2649, '705SSE00566', 'Student 705', '705SSE00566@fpt.edu.vn', 'SE00566', 1, NULL, '10A', 3, NULL),
(2650, '706SSE00567', 'Student 706', '706SSE00567@fpt.edu.vn', 'SE00567', 1, NULL, '10A', 3, NULL),
(2651, '707SSE00568', 'Student 707', '707SSE00568@fpt.edu.vn', 'SE00568', 1, NULL, '10A', 3, NULL),
(2652, '708SSE00569', 'Student 708', '708SSE00569@fpt.edu.vn', 'SE00569', 1, NULL, '10A', 3, NULL),
(2653, '709SSE00570', 'Student 709', '709SSE00570@fpt.edu.vn', 'SE00570', 1, NULL, '10A', 3, NULL),
(2654, '710SSE00571', 'Student 710', '710SSE00571@fpt.edu.vn', 'SE00571', 1, NULL, '10A', 3, NULL),
(2655, '711SSE00572', 'Student 711', '711SSE00572@fpt.edu.vn', 'SE00572', 1, NULL, '10A', 3, NULL),
(2656, '712SSE00573', 'Student 712', '712SSE00573@fpt.edu.vn', 'SE00573', 1, NULL, '10A', 3, NULL),
(2657, '713SSE00574', 'Student 713', '713SSE00574@fpt.edu.vn', 'SE00574', 1, NULL, '10A', 3, NULL),
(2658, '714SSE00575', 'Student 714', '714SSE00575@fpt.edu.vn', 'SE00575', 1, NULL, '10A', 3, NULL),
(2659, '715SSE00576', 'Student 715', '715SSE00576@fpt.edu.vn', 'SE00576', 1, NULL, '10A', 3, NULL),
(2660, '716SSE00577', 'Student 716', '716SSE00577@fpt.edu.vn', 'SE00577', 1, NULL, '10A', 3, NULL),
(2661, '717SSE00578', 'Student 717', '717SSE00578@fpt.edu.vn', 'SE00578', 1, NULL, '10A', 3, NULL),
(2662, '718SSE00579', 'Student 718', '718SSE00579@fpt.edu.vn', 'SE00579', 1, NULL, '10A', 3, NULL),
(2663, '719SSE00580', 'Student 719', '719SSE00580@fpt.edu.vn', 'SE00580', 1, NULL, '10A', 3, NULL),
(2664, '720SSE00581', 'Student 720', '720SSE00581@fpt.edu.vn', 'SE00581', 1, NULL, '10A', 3, NULL),
(2665, '721SSE00582', 'Student 721', '721SSE00582@fpt.edu.vn', 'SE00582', 1, NULL, '10A', 3, NULL),
(2666, '722SSE00583', 'Student 722', '722SSE00583@fpt.edu.vn', 'SE00583', 1, NULL, '10A', 3, NULL),
(2667, '723SSE00584', 'Student 723', '723SSE00584@fpt.edu.vn', 'SE00584', 1, NULL, '10A', 3, NULL),
(2668, '724SSE00585', 'Student 724', '724SSE00585@fpt.edu.vn', 'SE00585', 1, NULL, '10A', 3, NULL),
(2669, '725SSE00586', 'Student 725', '725SSE00586@fpt.edu.vn', 'SE00586', 1, NULL, '10A', 3, NULL),
(2670, '726SSE00587', 'Student 726', '726SSE00587@fpt.edu.vn', 'SE00587', 1, NULL, '10A', 3, NULL),
(2671, '727SSE00588', 'Student 727', '727SSE00588@fpt.edu.vn', 'SE00588', 1, NULL, '10A', 3, NULL),
(2672, '728SSE00589', 'Student 728', '728SSE00589@fpt.edu.vn', 'SE00589', 1, NULL, '10A', 3, NULL),
(2673, '729SSE00590', 'Student 729', '729SSE00590@fpt.edu.vn', 'SE00590', 1, NULL, '10A', 3, NULL),
(2674, '730SSE00591', 'Student 730', '730SSE00591@fpt.edu.vn', 'SE00591', 1, NULL, '10A', 3, NULL),
(2675, '731SSE00592', 'Student 731', '731SSE00592@fpt.edu.vn', 'SE00592', 1, NULL, '10A', 3, NULL),
(2676, '732SSE00593', 'Student 732', '732SSE00593@fpt.edu.vn', 'SE00593', 1, NULL, '10A', 3, NULL),
(2677, '733SSE00594', 'Student 733', '733SSE00594@fpt.edu.vn', 'SE00594', 1, NULL, '10A', 3, NULL),
(2678, '734SSE00595', 'Student 734', '734SSE00595@fpt.edu.vn', 'SE00595', 1, NULL, '10A', 3, NULL),
(2679, '735SSE00596', 'Student 735', '735SSE00596@fpt.edu.vn', 'SE00596', 1, NULL, '10A', 3, NULL),
(2680, '736SSE00597', 'Student 736', '736SSE00597@fpt.edu.vn', 'SE00597', 1, NULL, '10A', 3, NULL),
(2681, '737SSE00598', 'Student 737', '737SSE00598@fpt.edu.vn', 'SE00598', 1, NULL, '10A', 3, NULL),
(2682, '738SSE00599', 'Student 738', '738SSE00599@fpt.edu.vn', 'SE00599', 1, NULL, '10A', 3, NULL),
(2683, '739SSE00600', 'Student 739', '739SSE00600@fpt.edu.vn', 'SE00600', 1, NULL, '10A', 3, NULL),
(2684, '740SSE00601', 'Student 740', '740SSE00601@fpt.edu.vn', 'SE00601', 1, NULL, '10A', 3, NULL),
(2685, '741SSE00602', 'Student 741', '741SSE00602@fpt.edu.vn', 'SE00602', 1, NULL, '10A', 3, NULL),
(2686, '742SSE00603', 'Student 742', '742SSE00603@fpt.edu.vn', 'SE00603', 1, NULL, '10A', 3, NULL),
(2687, '743SSE00604', 'Student 743', '743SSE00604@fpt.edu.vn', 'SE00604', 1, NULL, '10A', 3, NULL),
(2688, '744SSE00605', 'Student 744', '744SSE00605@fpt.edu.vn', 'SE00605', 1, NULL, '10A', 3, NULL),
(2689, '745SSE00606', 'Student 745', '745SSE00606@fpt.edu.vn', 'SE00606', 1, NULL, '10A', 3, NULL),
(2690, '746SSE00607', 'Student 746', '746SSE00607@fpt.edu.vn', 'SE00607', 1, NULL, '10A', 3, NULL),
(2691, '747SSE00608', 'Student 747', '747SSE00608@fpt.edu.vn', 'SE00608', 1, NULL, '10A', 3, NULL),
(2692, '748SSE00609', 'Student 748', '748SSE00609@fpt.edu.vn', 'SE00609', 1, NULL, '10A', 3, NULL),
(2693, '749SSE00610', 'Student 749', '749SSE00610@fpt.edu.vn', 'SE00610', 1, NULL, '10A', 3, NULL),
(2694, '750SSE00611', 'Student 750', '750SSE00611@fpt.edu.vn', 'SE00611', 1, NULL, '10A', 3, NULL),
(2695, '751SSE00612', 'Student 751', '751SSE00612@fpt.edu.vn', 'SE00612', 1, NULL, '10A', 3, NULL),
(2696, '752SSE00613', 'Student 752', '752SSE00613@fpt.edu.vn', 'SE00613', 1, NULL, '10A', 3, NULL),
(2697, '753SSE00614', 'Student 753', '753SSE00614@fpt.edu.vn', 'SE00614', 1, NULL, '10A', 3, NULL),
(2698, '754SSE00615', 'Student 754', '754SSE00615@fpt.edu.vn', 'SE00615', 1, NULL, '10A', 3, NULL),
(2699, '755SSE00616', 'Student 755', '755SSE00616@fpt.edu.vn', 'SE00616', 1, NULL, '10A', 3, NULL),
(2700, '756SSE00617', 'Student 756', '756SSE00617@fpt.edu.vn', 'SE00617', 1, NULL, '10A', 3, NULL),
(2701, '757SSE00618', 'Student 757', '757SSE00618@fpt.edu.vn', 'SE00618', 1, NULL, '10A', 3, NULL),
(2702, '758SSE00619', 'Student 758', '758SSE00619@fpt.edu.vn', 'SE00619', 1, NULL, '10A', 3, NULL),
(2703, '759SSE00620', 'Student 759', '759SSE00620@fpt.edu.vn', 'SE00620', 1, NULL, '10A', 3, NULL),
(2704, '760SSE00621', 'Student 760', '760SSE00621@fpt.edu.vn', 'SE00621', 1, NULL, '10A', 3, NULL),
(2705, '761SSE00622', 'Student 761', '761SSE00622@fpt.edu.vn', 'SE00622', 1, NULL, '10A', 3, NULL),
(2706, '762SSE00623', 'Student 762', '762SSE00623@fpt.edu.vn', 'SE00623', 1, NULL, '10A', 3, NULL),
(2707, '763SSE00624', 'Student 763', '763SSE00624@fpt.edu.vn', 'SE00624', 1, NULL, '10A', 3, NULL),
(2708, '764SSE00625', 'Student 764', '764SSE00625@fpt.edu.vn', 'SE00625', 1, NULL, '10A', 3, NULL),
(2709, '765SSE00626', 'Student 765', '765SSE00626@fpt.edu.vn', 'SE00626', 1, NULL, '10A', 3, NULL),
(2710, '766SSE00627', 'Student 766', '766SSE00627@fpt.edu.vn', 'SE00627', 1, NULL, '10A', 3, NULL),
(2711, '767SSE00628', 'Student 767', '767SSE00628@fpt.edu.vn', 'SE00628', 1, NULL, '10A', 3, NULL),
(2712, '768SSE00629', 'Student 768', '768SSE00629@fpt.edu.vn', 'SE00629', 1, NULL, '10A', 3, NULL),
(2713, '769SEC00035', 'Student 769', '769SEC00035@fpt.edu.vn', 'EC00035', 9, NULL, '10A', 3, NULL),
(2714, '770SEC00036', 'Student 770', '770SEC00036@fpt.edu.vn', 'EC00036', 9, NULL, '10A', 3, NULL),
(2715, '771SEC00037', 'Student 771', '771SEC00037@fpt.edu.vn', 'EC00037', 9, NULL, '10A', 3, NULL),
(2716, '772SEC00038', 'Student 772', '772SEC00038@fpt.edu.vn', 'EC00038', 9, NULL, '10A', 3, NULL),
(2717, '773SIA00087', 'Student 773', '773SIA00087@fpt.edu.vn', 'IA00087', 13, NULL, '10A', 3, NULL),
(2718, '774SIA00088', 'Student 774', '774SIA00088@fpt.edu.vn', 'IA00088', 13, NULL, '10A', 3, NULL),
(2719, '775SIA00089', 'Student 775', '775SIA00089@fpt.edu.vn', 'IA00089', 13, NULL, '10A', 3, NULL),
(2720, '776SIA00090', 'Student 776', '776SIA00090@fpt.edu.vn', 'IA00090', 13, NULL, '10A', 3, NULL),
(2721, '777SIA00091', 'Student 777', '777SIA00091@fpt.edu.vn', 'IA00091', 13, NULL, '10A', 3, NULL),
(2722, '778SIA00092', 'Student 778', '778SIA00092@fpt.edu.vn', 'IA00092', 13, NULL, '10A', 3, NULL),
(2723, '779SIA00093', 'Student 779', '779SIA00093@fpt.edu.vn', 'IA00093', 13, NULL, '10A', 3, NULL),
(2724, '780SIA00094', 'Student 780', '780SIA00094@fpt.edu.vn', 'IA00094', 13, NULL, '10A', 3, NULL),
(2725, '781SIA00095', 'Student 781', '781SIA00095@fpt.edu.vn', 'IA00095', 13, NULL, '10A', 3, NULL),
(2726, '782SIA00096', 'Student 782', '782SIA00096@fpt.edu.vn', 'IA00096', 13, NULL, '10A', 3, NULL),
(2727, '783SIA00097', 'Student 783', '783SIA00097@fpt.edu.vn', 'IA00097', 13, NULL, '10A', 3, NULL),
(2728, '784SIA00098', 'Student 784', '784SIA00098@fpt.edu.vn', 'IA00098', 13, NULL, '10A', 3, NULL),
(2729, '785SIA00099', 'Student 785', '785SIA00099@fpt.edu.vn', 'IA00099', 13, NULL, '10A', 3, NULL),
(2730, '786SIA00100', 'Student 786', '786SIA00100@fpt.edu.vn', 'IA00100', 13, NULL, '10A', 3, NULL),
(2731, '787SIA00101', 'Student 787', '787SIA00101@fpt.edu.vn', 'IA00101', 13, NULL, '10A', 3, NULL),
(2732, '788SIA00102', 'Student 788', '788SIA00102@fpt.edu.vn', 'IA00102', 13, NULL, '10A', 3, NULL),
(2733, '789SIA00103', 'Student 789', '789SIA00103@fpt.edu.vn', 'IA00103', 13, NULL, '10A', 3, NULL),
(2734, '790SSE00630', 'Student 790', '790SSE00630@fpt.edu.vn', 'SE00630', 1, NULL, '10B', 2, NULL),
(2735, '791SSE00631', 'Student 791', '791SSE00631@fpt.edu.vn', 'SE00631', 1, NULL, '10B', 2, NULL),
(2736, '792SSE00632', 'Student 792', '792SSE00632@fpt.edu.vn', 'SE00632', 1, NULL, '10B', 2, NULL),
(2737, '793SSE00633', 'Student 793', '793SSE00633@fpt.edu.vn', 'SE00633', 1, NULL, '10B', 2, NULL),
(2738, '794SSE00634', 'Student 794', '794SSE00634@fpt.edu.vn', 'SE00634', 1, NULL, '10B', 2, NULL),
(2739, '795SSE00635', 'Student 795', '795SSE00635@fpt.edu.vn', 'SE00635', 1, NULL, '10B', 2, NULL),
(2740, '796SSE00636', 'Student 796', '796SSE00636@fpt.edu.vn', 'SE00636', 1, NULL, '10B', 2, NULL),
(2741, '797SSE00637', 'Student 797', '797SSE00637@fpt.edu.vn', 'SE00637', 1, NULL, '10B', 2, NULL),
(2742, '798SSE00638', 'Student 798', '798SSE00638@fpt.edu.vn', 'SE00638', 1, NULL, '10B', 2, NULL),
(2743, '799SSE00639', 'Student 799', '799SSE00639@fpt.edu.vn', 'SE00639', 1, NULL, '10B', 2, NULL),
(2744, '800SSE00640', 'Student 800', '800SSE00640@fpt.edu.vn', 'SE00640', 1, NULL, '10B', 2, NULL),
(2745, '801SSE00641', 'Student 801', '801SSE00641@fpt.edu.vn', 'SE00641', 1, NULL, '10B', 2, NULL),
(2746, '802SSE00642', 'Student 802', '802SSE00642@fpt.edu.vn', 'SE00642', 1, NULL, '10B', 2, NULL),
(2747, '803SSE00643', 'Student 803', '803SSE00643@fpt.edu.vn', 'SE00643', 1, NULL, '10B', 2, NULL),
(2748, '804SSE00644', 'Student 804', '804SSE00644@fpt.edu.vn', 'SE00644', 1, NULL, '10B', 2, NULL),
(2749, '805SSE00645', 'Student 805', '805SSE00645@fpt.edu.vn', 'SE00645', 1, NULL, '10B', 2, NULL),
(2750, '806SSE00646', 'Student 806', '806SSE00646@fpt.edu.vn', 'SE00646', 1, NULL, '10B', 2, NULL),
(2751, '807SSE00647', 'Student 807', '807SSE00647@fpt.edu.vn', 'SE00647', 1, NULL, '10B', 2, NULL),
(2752, '808SSE00648', 'Student 808', '808SSE00648@fpt.edu.vn', 'SE00648', 1, NULL, '10B', 2, NULL),
(2753, '809SSE00649', 'Student 809', '809SSE00649@fpt.edu.vn', 'SE00649', 1, NULL, '10B', 2, NULL),
(2754, '810SSE00650', 'Student 810', '810SSE00650@fpt.edu.vn', 'SE00650', 1, NULL, '10B', 2, NULL),
(2755, '811SSE00651', 'Student 811', '811SSE00651@fpt.edu.vn', 'SE00651', 1, NULL, '10B', 2, NULL),
(2756, '812SSE00652', 'Student 812', '812SSE00652@fpt.edu.vn', 'SE00652', 1, NULL, '10B', 2, NULL),
(2757, '813SSE00653', 'Student 813', '813SSE00653@fpt.edu.vn', 'SE00653', 1, NULL, '10B', 2, NULL),
(2758, '814SSE00654', 'Student 814', '814SSE00654@fpt.edu.vn', 'SE00654', 1, NULL, '10B', 2, NULL),
(2759, '815SSE00655', 'Student 815', '815SSE00655@fpt.edu.vn', 'SE00655', 1, NULL, '10B', 2, NULL),
(2760, '816SSE00656', 'Student 816', '816SSE00656@fpt.edu.vn', 'SE00656', 1, NULL, '10B', 2, NULL),
(2761, '817SSE00657', 'Student 817', '817SSE00657@fpt.edu.vn', 'SE00657', 1, NULL, '10B', 2, NULL),
(2762, '818SSE00658', 'Student 818', '818SSE00658@fpt.edu.vn', 'SE00658', 1, NULL, '10B', 2, NULL),
(2763, '819SSE00659', 'Student 819', '819SSE00659@fpt.edu.vn', 'SE00659', 1, NULL, '10B', 2, NULL),
(2764, '820SSE00660', 'Student 820', '820SSE00660@fpt.edu.vn', 'SE00660', 1, NULL, '10B', 2, NULL),
(2765, '821SSE00661', 'Student 821', '821SSE00661@fpt.edu.vn', 'SE00661', 1, NULL, '10B', 2, NULL),
(2766, '822SSE00662', 'Student 822', '822SSE00662@fpt.edu.vn', 'SE00662', 1, NULL, '10B', 2, NULL),
(2767, '823SSE00663', 'Student 823', '823SSE00663@fpt.edu.vn', 'SE00663', 1, NULL, '10B', 2, NULL),
(2768, '824SSE00664', 'Student 824', '824SSE00664@fpt.edu.vn', 'SE00664', 1, NULL, '10B', 2, NULL),
(2769, '825SSE00665', 'Student 825', '825SSE00665@fpt.edu.vn', 'SE00665', 1, NULL, '10B', 2, NULL),
(2770, '826SSE00666', 'Student 826', '826SSE00666@fpt.edu.vn', 'SE00666', 1, NULL, '10B', 2, NULL),
(2771, '827SSE00667', 'Student 827', '827SSE00667@fpt.edu.vn', 'SE00667', 1, NULL, '10B', 2, NULL),
(2772, '828SSE00668', 'Student 828', '828SSE00668@fpt.edu.vn', 'SE00668', 1, NULL, '10B', 2, NULL),
(2773, '829SSE00669', 'Student 829', '829SSE00669@fpt.edu.vn', 'SE00669', 1, NULL, '10B', 2, NULL),
(2774, '830SSE00670', 'Student 830', '830SSE00670@fpt.edu.vn', 'SE00670', 1, NULL, '10B', 2, NULL),
(2775, '831SSE00671', 'Student 831', '831SSE00671@fpt.edu.vn', 'SE00671', 1, NULL, '10B', 2, NULL),
(2776, '832SSE00672', 'Student 832', '832SSE00672@fpt.edu.vn', 'SE00672', 1, NULL, '10B', 2, NULL),
(2777, '833SSE00673', 'Student 833', '833SSE00673@fpt.edu.vn', 'SE00673', 1, NULL, '10B', 2, NULL),
(2778, '834SSE00674', 'Student 834', '834SSE00674@fpt.edu.vn', 'SE00674', 1, NULL, '10B', 2, NULL),
(2779, '835SSE00675', 'Student 835', '835SSE00675@fpt.edu.vn', 'SE00675', 1, NULL, '10B', 2, NULL),
(2780, '836SSE00676', 'Student 836', '836SSE00676@fpt.edu.vn', 'SE00676', 1, NULL, '10B', 2, NULL),
(2781, '837SSE00677', 'Student 837', '837SSE00677@fpt.edu.vn', 'SE00677', 1, NULL, '10B', 2, NULL),
(2782, '838SSE00678', 'Student 838', '838SSE00678@fpt.edu.vn', 'SE00678', 1, NULL, '10B', 2, NULL),
(2783, '839SSE00679', 'Student 839', '839SSE00679@fpt.edu.vn', 'SE00679', 1, NULL, '10B', 2, NULL),
(2784, '840SSE00680', 'Student 840', '840SSE00680@fpt.edu.vn', 'SE00680', 1, NULL, '10B', 2, NULL),
(2785, '841SSE00681', 'Student 841', '841SSE00681@fpt.edu.vn', 'SE00681', 1, NULL, '10B', 2, NULL),
(2786, '842SSE00682', 'Student 842', '842SSE00682@fpt.edu.vn', 'SE00682', 1, NULL, '10B', 2, NULL),
(2787, '843SSE00683', 'Student 843', '843SSE00683@fpt.edu.vn', 'SE00683', 1, NULL, '10B', 2, NULL),
(2788, '844SSE00684', 'Student 844', '844SSE00684@fpt.edu.vn', 'SE00684', 1, NULL, '10B', 2, NULL),
(2789, '845SSE00685', 'Student 845', '845SSE00685@fpt.edu.vn', 'SE00685', 1, NULL, '10B', 2, NULL),
(2790, '846SSE00686', 'Student 846', '846SSE00686@fpt.edu.vn', 'SE00686', 1, NULL, '10B', 2, NULL),
(2791, '847SSE00687', 'Student 847', '847SSE00687@fpt.edu.vn', 'SE00687', 1, NULL, '10B', 2, NULL),
(2792, '848SSE00688', 'Student 848', '848SSE00688@fpt.edu.vn', 'SE00688', 1, NULL, '10B', 2, NULL),
(2793, '849SSE00689', 'Student 849', '849SSE00689@fpt.edu.vn', 'SE00689', 1, NULL, '10B', 2, NULL),
(2794, '850SSE00690', 'Student 850', '850SSE00690@fpt.edu.vn', 'SE00690', 1, NULL, '10B', 2, NULL),
(2795, '851SSE00691', 'Student 851', '851SSE00691@fpt.edu.vn', 'SE00691', 1, NULL, '10B', 2, NULL),
(2796, '852SSE00692', 'Student 852', '852SSE00692@fpt.edu.vn', 'SE00692', 1, NULL, '10B', 2, NULL),
(2797, '853SSE00693', 'Student 853', '853SSE00693@fpt.edu.vn', 'SE00693', 1, NULL, '10B', 2, NULL),
(2798, '854SSE00694', 'Student 854', '854SSE00694@fpt.edu.vn', 'SE00694', 1, NULL, '10B', 2, NULL),
(2799, '855SSE00695', 'Student 855', '855SSE00695@fpt.edu.vn', 'SE00695', 1, NULL, '10B', 2, NULL),
(2800, '856SSE00696', 'Student 856', '856SSE00696@fpt.edu.vn', 'SE00696', 1, NULL, '10B', 2, NULL),
(2801, '857SSE00697', 'Student 857', '857SSE00697@fpt.edu.vn', 'SE00697', 1, NULL, '10B', 2, NULL),
(2802, '858SIA00104', 'Student 858', '858SIA00104@fpt.edu.vn', 'IA00104', 13, NULL, '10B', 2, NULL),
(2803, '859SIA00105', 'Student 859', '859SIA00105@fpt.edu.vn', 'IA00105', 13, NULL, '10B', 2, NULL),
(2804, '860SIA00106', 'Student 860', '860SIA00106@fpt.edu.vn', 'IA00106', 13, NULL, '10B', 2, NULL),
(2805, '861SIA00107', 'Student 861', '861SIA00107@fpt.edu.vn', 'IA00107', 13, NULL, '10B', 2, NULL),
(2806, '862SIA00108', 'Student 862', '862SIA00108@fpt.edu.vn', 'IA00108', 13, NULL, '10B', 2, NULL),
(2807, '863SIA00109', 'Student 863', '863SIA00109@fpt.edu.vn', 'IA00109', 13, NULL, '10B', 2, NULL),
(2808, '864SIA00110', 'Student 864', '864SIA00110@fpt.edu.vn', 'IA00110', 13, NULL, '10B', 2, NULL),
(2809, '865SIA00111', 'Student 865', '865SIA00111@fpt.edu.vn', 'IA00111', 13, NULL, '10B', 2, NULL),
(2810, '866SIA00112', 'Student 866', '866SIA00112@fpt.edu.vn', 'IA00112', 13, NULL, '10B', 2, NULL),
(2811, '867SIA00113', 'Student 867', '867SIA00113@fpt.edu.vn', 'IA00113', 13, NULL, '10B', 2, NULL),
(2812, '868SIA00114', 'Student 868', '868SIA00114@fpt.edu.vn', 'IA00114', 13, NULL, '10B', 2, NULL),
(2813, '869SIA00115', 'Student 869', '869SIA00115@fpt.edu.vn', 'IA00115', 13, NULL, '10B', 2, NULL),
(2814, '870SIA00116', 'Student 870', '870SIA00116@fpt.edu.vn', 'IA00116', 13, NULL, '10B', 2, NULL),
(2815, '871SIA00117', 'Student 871', '871SIA00117@fpt.edu.vn', 'IA00117', 13, NULL, '10B', 2, NULL),
(2816, '872SEC00039', 'Student 872', '872SEC00039@fpt.edu.vn', 'EC00039', 9, NULL, '10B', 2, NULL),
(2817, '873SEC00040', 'Student 873', '873SEC00040@fpt.edu.vn', 'EC00040', 9, NULL, '10B', 2, NULL),
(2818, '874SEC00041', 'Student 874', '874SEC00041@fpt.edu.vn', 'EC00041', 9, NULL, '10B', 2, NULL),
(2819, '875SEC00042', 'Student 875', '875SEC00042@fpt.edu.vn', 'EC00042', 9, NULL, '10B', 2, NULL),
(2820, '876SEC00043', 'Student 876', '876SEC00043@fpt.edu.vn', 'EC00043', 9, NULL, '10B', 2, NULL),
(2821, '877SEC00044', 'Student 877', '877SEC00044@fpt.edu.vn', 'EC00044', 9, NULL, '10B', 2, NULL),
(2822, '878SEC00045', 'Student 878', '878SEC00045@fpt.edu.vn', 'EC00045', 9, NULL, '10B', 2, NULL),
(2823, '879SEC00046', 'Student 879', '879SEC00046@fpt.edu.vn', 'EC00046', 9, NULL, '10B', 2, NULL),
(2824, '880SSE00698', 'Student 880', '880SSE00698@fpt.edu.vn', 'SE00698', 1, 7, '10B', 2, NULL),
(2825, '881SSE00699', 'Student 881', '881SSE00699@fpt.edu.vn', 'SE00699', 1, 7, '10B', 2, NULL),
(2826, '882SSE00700', 'Student 882', '882SSE00700@fpt.edu.vn', 'SE00700', 1, 7, '10B', 2, NULL),
(2827, '883SSE00701', 'Student 883', '883SSE00701@fpt.edu.vn', 'SE00701', 1, 7, '10B', 2, NULL),
(2828, '884SSE00702', 'Student 884', '884SSE00702@fpt.edu.vn', 'SE00702', 1, 7, '10B', 2, NULL),
(2829, '885SSE00703', 'Student 885', '885SSE00703@fpt.edu.vn', 'SE00703', 1, 7, '10B', 2, NULL),
(2830, '886SSE00704', 'Student 886', '886SSE00704@fpt.edu.vn', 'SE00704', 1, 7, '10B', 2, NULL),
(2831, '887SSE00705', 'Student 887', '887SSE00705@fpt.edu.vn', 'SE00705', 1, 7, '10B', 2, NULL),
(2832, '888SSE00706', 'Student 888', '888SSE00706@fpt.edu.vn', 'SE00706', 1, 7, '10B', 2, NULL),
(2833, '889SSE00707', 'Student 889', '889SSE00707@fpt.edu.vn', 'SE00707', 1, 7, '10B', 2, NULL),
(2834, '890SSE00708', 'Student 890', '890SSE00708@fpt.edu.vn', 'SE00708', 1, 7, '10B', 2, NULL),
(2835, '891SSE00709', 'Student 891', '891SSE00709@fpt.edu.vn', 'SE00709', 1, 7, '10B', 2, NULL),
(2836, '892SSE00710', 'Student 892', '892SSE00710@fpt.edu.vn', 'SE00710', 1, 7, '10B', 2, NULL),
(2837, '893SSE00711', 'Student 893', '893SSE00711@fpt.edu.vn', 'SE00711', 1, 7, '10B', 2, NULL),
(2838, '894SSE00712', 'Student 894', '894SSE00712@fpt.edu.vn', 'SE00712', 1, 7, '10B', 2, NULL),
(2839, '895SSE00713', 'Student 895', '895SSE00713@fpt.edu.vn', 'SE00713', 1, 7, '10B', 2, NULL),
(2840, '896SSE00714', 'Student 896', '896SSE00714@fpt.edu.vn', 'SE00714', 1, 7, '10B', 2, NULL),
(2841, '897SSE00715', 'Student 897', '897SSE00715@fpt.edu.vn', 'SE00715', 1, 7, '10B', 2, NULL),
(2842, '898SSE00716', 'Student 898', '898SSE00716@fpt.edu.vn', 'SE00716', 1, 7, '10B', 2, NULL),
(2843, '899SSE00717', 'Student 899', '899SSE00717@fpt.edu.vn', 'SE00717', 1, 7, '10B', 2, NULL),
(2844, '900SSE00718', 'Student 900', '900SSE00718@fpt.edu.vn', 'SE00718', 1, 7, '10B', 2, NULL),
(2845, '901SGD00016', 'Student 901', '901SGD00016@fpt.edu.vn', 'GD00016', 12, NULL, '10B', 2, NULL),
(2846, '902SGD00017', 'Student 902', '902SGD00017@fpt.edu.vn', 'GD00017', 12, NULL, '10B', 2, NULL),
(2847, '903SGD00018', 'Student 903', '903SGD00018@fpt.edu.vn', 'GD00018', 12, NULL, '10B', 2, NULL),
(2848, '904SGD00019', 'Student 904', '904SGD00019@fpt.edu.vn', 'GD00019', 12, NULL, '10B', 2, NULL),
(2849, '905SGD00020', 'Student 905', '905SGD00020@fpt.edu.vn', 'GD00020', 12, NULL, '10B', 2, NULL),
(2850, '906SGD00021', 'Student 906', '906SGD00021@fpt.edu.vn', 'GD00021', 12, NULL, '10B', 2, NULL),
(2851, '907SGD00022', 'Student 907', '907SGD00022@fpt.edu.vn', 'GD00022', 12, NULL, '10B', 2, NULL),
(2852, '908SGD00023', 'Student 908', '908SGD00023@fpt.edu.vn', 'GD00023', 12, NULL, '10B', 2, NULL),
(2853, '909SGD00024', 'Student 909', '909SGD00024@fpt.edu.vn', 'GD00024', 12, NULL, '10B', 2, NULL),
(2854, '910SGD00025', 'Student 910', '910SGD00025@fpt.edu.vn', 'GD00025', 12, NULL, '10B', 2, NULL),
(2855, '911SGD00026', 'Student 911', '911SGD00026@fpt.edu.vn', 'GD00026', 12, NULL, '10B', 2, NULL),
(2856, '912SGD00027', 'Student 912', '912SGD00027@fpt.edu.vn', 'GD00027', 12, NULL, '10B', 2, NULL),
(2857, '913SGD00028', 'Student 913', '913SGD00028@fpt.edu.vn', 'GD00028', 12, NULL, '10B', 2, NULL),
(2858, '914SGD00029', 'Student 914', '914SGD00029@fpt.edu.vn', 'GD00029', 12, NULL, '10B', 2, NULL),
(2859, '915SGD00030', 'Student 915', '915SGD00030@fpt.edu.vn', 'GD00030', 12, NULL, '10B', 2, NULL),
(2860, '916SGD00031', 'Student 916', '916SGD00031@fpt.edu.vn', 'GD00031', 12, NULL, '10B', 2, NULL),
(2861, '917SGD00032', 'Student 917', '917SGD00032@fpt.edu.vn', 'GD00032', 12, NULL, '10B', 2, NULL),
(2862, '918SSE00719', 'Student 918', '918SSE00719@fpt.edu.vn', 'SE00719', 1, NULL, '10C', 1, NULL),
(2863, '919SSE00720', 'Student 919', '919SSE00720@fpt.edu.vn', 'SE00720', 1, NULL, '10C', 1, NULL),
(2864, '920SSE00721', 'Student 920', '920SSE00721@fpt.edu.vn', 'SE00721', 1, NULL, '10C', 1, NULL),
(2865, '921SSE00722', 'Student 921', '921SSE00722@fpt.edu.vn', 'SE00722', 1, NULL, '10C', 1, NULL),
(2866, '922SSE00723', 'Student 922', '922SSE00723@fpt.edu.vn', 'SE00723', 1, NULL, '10C', 1, NULL),
(2867, '923SSE00724', 'Student 923', '923SSE00724@fpt.edu.vn', 'SE00724', 1, NULL, '10C', 1, NULL),
(2868, '924SSE00725', 'Student 924', '924SSE00725@fpt.edu.vn', 'SE00725', 1, NULL, '10C', 1, NULL),
(2869, '925SSE00726', 'Student 925', '925SSE00726@fpt.edu.vn', 'SE00726', 1, NULL, '10C', 1, NULL),
(2870, '926SSE00727', 'Student 926', '926SSE00727@fpt.edu.vn', 'SE00727', 1, NULL, '10C', 1, NULL),
(2871, '927SSE00728', 'Student 927', '927SSE00728@fpt.edu.vn', 'SE00728', 1, NULL, '10C', 1, NULL),
(2872, '928SSE00729', 'Student 928', '928SSE00729@fpt.edu.vn', 'SE00729', 1, NULL, '10C', 1, NULL),
(2873, '929SSE00730', 'Student 929', '929SSE00730@fpt.edu.vn', 'SE00730', 1, NULL, '10C', 1, NULL),
(2874, '930SSE00731', 'Student 930', '930SSE00731@fpt.edu.vn', 'SE00731', 1, NULL, '10C', 1, NULL),
(2875, '931SSE00732', 'Student 931', '931SSE00732@fpt.edu.vn', 'SE00732', 1, NULL, '10C', 1, NULL),
(2876, '932SSE00733', 'Student 932', '932SSE00733@fpt.edu.vn', 'SE00733', 1, NULL, '10C', 1, NULL),
(2877, '933SSE00734', 'Student 933', '933SSE00734@fpt.edu.vn', 'SE00734', 1, NULL, '10C', 1, NULL),
(2878, '934SSE00735', 'Student 934', '934SSE00735@fpt.edu.vn', 'SE00735', 1, NULL, '10C', 1, NULL),
(2879, '935SSE00736', 'Student 935', '935SSE00736@fpt.edu.vn', 'SE00736', 1, NULL, '10C', 1, NULL),
(2880, '936SSE00737', 'Student 936', '936SSE00737@fpt.edu.vn', 'SE00737', 1, NULL, '10C', 1, NULL),
(2881, '937SSE00738', 'Student 937', '937SSE00738@fpt.edu.vn', 'SE00738', 1, NULL, '10C', 1, NULL),
(2882, '938SSE00739', 'Student 938', '938SSE00739@fpt.edu.vn', 'SE00739', 1, NULL, '10C', 1, NULL),
(2883, '939SSE00740', 'Student 939', '939SSE00740@fpt.edu.vn', 'SE00740', 1, NULL, '10C', 1, NULL),
(2884, '940SSE00741', 'Student 940', '940SSE00741@fpt.edu.vn', 'SE00741', 1, NULL, '10C', 1, NULL),
(2885, '941SSE00742', 'Student 941', '941SSE00742@fpt.edu.vn', 'SE00742', 1, NULL, '10C', 1, NULL),
(2886, '942SSE00743', 'Student 942', '942SSE00743@fpt.edu.vn', 'SE00743', 1, NULL, '10C', 1, NULL),
(2887, '943SSE00744', 'Student 943', '943SSE00744@fpt.edu.vn', 'SE00744', 1, NULL, '10C', 1, NULL),
(2888, '944SSE00745', 'Student 944', '944SSE00745@fpt.edu.vn', 'SE00745', 1, NULL, '10C', 1, NULL),
(2889, '945SSE00746', 'Student 945', '945SSE00746@fpt.edu.vn', 'SE00746', 1, NULL, '10C', 1, NULL),
(2890, '946SSE00747', 'Student 946', '946SSE00747@fpt.edu.vn', 'SE00747', 1, NULL, '10C', 1, NULL),
(2891, '947SSE00748', 'Student 947', '947SSE00748@fpt.edu.vn', 'SE00748', 1, NULL, '10C', 1, NULL),
(2892, '948SSE00749', 'Student 948', '948SSE00749@fpt.edu.vn', 'SE00749', 1, NULL, '10C', 1, NULL),
(2893, '949SSE00750', 'Student 949', '949SSE00750@fpt.edu.vn', 'SE00750', 1, NULL, '10C', 1, NULL),
(2894, '950SSE00751', 'Student 950', '950SSE00751@fpt.edu.vn', 'SE00751', 1, NULL, '10C', 1, NULL),
(2895, '951SSE00752', 'Student 951', '951SSE00752@fpt.edu.vn', 'SE00752', 1, NULL, '10C', 1, NULL),
(2896, '952SSE00753', 'Student 952', '952SSE00753@fpt.edu.vn', 'SE00753', 1, NULL, '10C', 1, NULL),
(2897, '953SSE00754', 'Student 953', '953SSE00754@fpt.edu.vn', 'SE00754', 1, NULL, '10C', 1, NULL),
(2898, '954SSE00755', 'Student 954', '954SSE00755@fpt.edu.vn', 'SE00755', 1, NULL, '10C', 1, NULL),
(2899, '955SSE00756', 'Student 955', '955SSE00756@fpt.edu.vn', 'SE00756', 1, NULL, '10C', 1, NULL),
(2900, '956SSE00757', 'Student 956', '956SSE00757@fpt.edu.vn', 'SE00757', 1, NULL, '10C', 1, NULL),
(2901, '957SSE00758', 'Student 957', '957SSE00758@fpt.edu.vn', 'SE00758', 1, NULL, '10C', 1, NULL),
(2902, '958SSE00759', 'Student 958', '958SSE00759@fpt.edu.vn', 'SE00759', 1, NULL, '10C', 1, NULL),
(2903, '959SSE00760', 'Student 959', '959SSE00760@fpt.edu.vn', 'SE00760', 1, NULL, '10C', 1, NULL),
(2904, '960SSE00761', 'Student 960', '960SSE00761@fpt.edu.vn', 'SE00761', 1, NULL, '10C', 1, NULL),
(2905, '961SSE00762', 'Student 961', '961SSE00762@fpt.edu.vn', 'SE00762', 1, NULL, '10C', 1, NULL),
(2906, '962SSE00763', 'Student 962', '962SSE00763@fpt.edu.vn', 'SE00763', 1, NULL, '10C', 1, NULL),
(2907, '963SSE00764', 'Student 963', '963SSE00764@fpt.edu.vn', 'SE00764', 1, NULL, '10C', 1, NULL),
(2908, '964SSE00765', 'Student 964', '964SSE00765@fpt.edu.vn', 'SE00765', 1, NULL, '10C', 1, NULL),
(2909, '965SSE00766', 'Student 965', '965SSE00766@fpt.edu.vn', 'SE00766', 1, NULL, '10C', 1, NULL),
(2910, '966SSE00767', 'Student 966', '966SSE00767@fpt.edu.vn', 'SE00767', 1, NULL, '10C', 1, NULL),
(2911, '967SSE00768', 'Student 967', '967SSE00768@fpt.edu.vn', 'SE00768', 1, NULL, '10C', 1, NULL),
(2912, '968SSE00769', 'Student 968', '968SSE00769@fpt.edu.vn', 'SE00769', 1, NULL, '10C', 1, NULL),
(2913, '969SSE00770', 'Student 969', '969SSE00770@fpt.edu.vn', 'SE00770', 1, NULL, '10C', 1, NULL),
(2914, '970SSE00771', 'Student 970', '970SSE00771@fpt.edu.vn', 'SE00771', 1, NULL, '10C', 1, NULL),
(2915, '971SSE00772', 'Student 971', '971SSE00772@fpt.edu.vn', 'SE00772', 1, NULL, '10C', 1, NULL),
(2916, '972SSE00773', 'Student 972', '972SSE00773@fpt.edu.vn', 'SE00773', 1, NULL, '10C', 1, NULL),
(2917, '973SSE00774', 'Student 973', '973SSE00774@fpt.edu.vn', 'SE00774', 1, NULL, '10C', 1, NULL),
(2918, '974SSE00775', 'Student 974', '974SSE00775@fpt.edu.vn', 'SE00775', 1, NULL, '10C', 1, NULL),
(2919, '975SSE00776', 'Student 975', '975SSE00776@fpt.edu.vn', 'SE00776', 1, NULL, '10C', 1, NULL),
(2920, '976SSE00777', 'Student 976', '976SSE00777@fpt.edu.vn', 'SE00777', 1, NULL, '10C', 1, NULL),
(2921, '977SSE00778', 'Student 977', '977SSE00778@fpt.edu.vn', 'SE00778', 1, NULL, '10C', 1, NULL),
(2922, '978SSE00779', 'Student 978', '978SSE00779@fpt.edu.vn', 'SE00779', 1, NULL, '10C', 1, NULL),
(2923, '979SSE00780', 'Student 979', '979SSE00780@fpt.edu.vn', 'SE00780', 1, NULL, '10C', 1, NULL),
(2924, '980SSE00781', 'Student 980', '980SSE00781@fpt.edu.vn', 'SE00781', 1, NULL, '10C', 1, NULL),
(2925, '981SSE00782', 'Student 981', '981SSE00782@fpt.edu.vn', 'SE00782', 1, NULL, '10C', 1, NULL),
(2926, '982SSE00783', 'Student 982', '982SSE00783@fpt.edu.vn', 'SE00783', 1, NULL, '10C', 1, NULL),
(2927, '983SSE00784', 'Student 983', '983SSE00784@fpt.edu.vn', 'SE00784', 1, NULL, '10C', 1, NULL),
(2928, '984SSE00785', 'Student 984', '984SSE00785@fpt.edu.vn', 'SE00785', 1, NULL, '10C', 1, NULL),
(2929, '985SSE00786', 'Student 985', '985SSE00786@fpt.edu.vn', 'SE00786', 1, NULL, '10C', 1, NULL),
(2930, '986SSE00787', 'Student 986', '986SSE00787@fpt.edu.vn', 'SE00787', 1, NULL, '10C', 1, NULL),
(2931, '987SSE00788', 'Student 987', '987SSE00788@fpt.edu.vn', 'SE00788', 1, NULL, '10C', 1, NULL),
(2932, '988SSE00789', 'Student 988', '988SSE00789@fpt.edu.vn', 'SE00789', 1, NULL, '10C', 1, NULL),
(2933, '989SSE00790', 'Student 989', '989SSE00790@fpt.edu.vn', 'SE00790', 1, NULL, '10C', 1, NULL),
(2934, '990SSE00791', 'Student 990', '990SSE00791@fpt.edu.vn', 'SE00791', 1, NULL, '10C', 1, NULL),
(2935, '991SSE00792', 'Student 991', '991SSE00792@fpt.edu.vn', 'SE00792', 1, NULL, '10C', 1, NULL),
(2936, '992SSE00793', 'Student 992', '992SSE00793@fpt.edu.vn', 'SE00793', 1, NULL, '10C', 1, NULL),
(2937, '993SSE00794', 'Student 993', '993SSE00794@fpt.edu.vn', 'SE00794', 1, NULL, '10C', 1, NULL),
(2938, '994SSE00795', 'Student 994', '994SSE00795@fpt.edu.vn', 'SE00795', 1, NULL, '10C', 1, NULL),
(2939, '995SSE00796', 'Student 995', '995SSE00796@fpt.edu.vn', 'SE00796', 1, NULL, '10C', 1, NULL),
(2940, '996SSE00797', 'Student 996', '996SSE00797@fpt.edu.vn', 'SE00797', 1, NULL, '10C', 1, NULL),
(2941, '997SSE00798', 'Student 997', '997SSE00798@fpt.edu.vn', 'SE00798', 1, NULL, '10C', 1, NULL),
(2942, '998SSE00799', 'Student 998', '998SSE00799@fpt.edu.vn', 'SE00799', 1, NULL, '10C', 1, NULL),
(2943, '999SSE00800', 'Student 999', '999SSE00800@fpt.edu.vn', 'SE00800', 1, NULL, '10C', 1, NULL),
(2944, '1000SSE00801', 'Student 1000', '1000SSE00801@fpt.edu.vn', 'SE00801', 1, NULL, '10C', 1, NULL),
(2945, '1001SSE00802', 'Student 1001', '1001SSE00802@fpt.edu.vn', 'SE00802', 1, NULL, '10C', 1, NULL),
(2946, '1002SSE00803', 'Student 1002', '1002SSE00803@fpt.edu.vn', 'SE00803', 1, NULL, '10C', 1, NULL),
(2947, '1003SSE00804', 'Student 1003', '1003SSE00804@fpt.edu.vn', 'SE00804', 1, NULL, '10C', 1, NULL),
(2948, '1004SSE00805', 'Student 1004', '1004SSE00805@fpt.edu.vn', 'SE00805', 1, NULL, '10C', 1, NULL),
(2949, '1005SSE00806', 'Student 1005', '1005SSE00806@fpt.edu.vn', 'SE00806', 1, NULL, '10C', 1, NULL),
(2950, '1006SSE00807', 'Student 1006', '1006SSE00807@fpt.edu.vn', 'SE00807', 1, NULL, '10C', 1, NULL),
(2951, '1007SSE00808', 'Student 1007', '1007SSE00808@fpt.edu.vn', 'SE00808', 1, NULL, '10C', 1, NULL),
(2952, '1008SSE00809', 'Student 1008', '1008SSE00809@fpt.edu.vn', 'SE00809', 1, NULL, '10C', 1, NULL),
(2953, '1009SSE00810', 'Student 1009', '1009SSE00810@fpt.edu.vn', 'SE00810', 1, NULL, '10C', 1, NULL),
(2954, '1010SSE00811', 'Student 1010', '1010SSE00811@fpt.edu.vn', 'SE00811', 1, NULL, '10C', 1, NULL),
(2955, '1011SSE00812', 'Student 1011', '1011SSE00812@fpt.edu.vn', 'SE00812', 1, NULL, '10C', 1, NULL),
(2956, '1012SSE00813', 'Student 1012', '1012SSE00813@fpt.edu.vn', 'SE00813', 1, NULL, '10C', 1, NULL),
(2957, '1013SSE00814', 'Student 1013', '1013SSE00814@fpt.edu.vn', 'SE00814', 1, NULL, '10C', 1, NULL),
(2958, '1014SSE00815', 'Student 1014', '1014SSE00815@fpt.edu.vn', 'SE00815', 1, NULL, '10C', 1, NULL),
(2959, '1015SSE00816', 'Student 1015', '1015SSE00816@fpt.edu.vn', 'SE00816', 1, NULL, '10C', 1, NULL),
(2960, '1016SSE00817', 'Student 1016', '1016SSE00817@fpt.edu.vn', 'SE00817', 1, NULL, '10C', 1, NULL),
(2961, '1017SSE00818', 'Student 1017', '1017SSE00818@fpt.edu.vn', 'SE00818', 1, NULL, '10C', 1, NULL),
(2962, '1018SSE00819', 'Student 1018', '1018SSE00819@fpt.edu.vn', 'SE00819', 1, NULL, '10C', 1, NULL),
(2963, '1019SSE00820', 'Student 1019', '1019SSE00820@fpt.edu.vn', 'SE00820', 1, NULL, '10C', 1, NULL),
(2964, '1020SSE00821', 'Student 1020', '1020SSE00821@fpt.edu.vn', 'SE00821', 1, NULL, '10C', 1, NULL),
(2965, '1021SSE00822', 'Student 1021', '1021SSE00822@fpt.edu.vn', 'SE00822', 1, NULL, '10C', 1, NULL),
(2966, '1022SSE00823', 'Student 1022', '1022SSE00823@fpt.edu.vn', 'SE00823', 1, NULL, '10C', 1, NULL),
(2967, '1023SSE00824', 'Student 1023', '1023SSE00824@fpt.edu.vn', 'SE00824', 1, NULL, '10C', 1, NULL),
(2968, '1024SSE00825', 'Student 1024', '1024SSE00825@fpt.edu.vn', 'SE00825', 1, NULL, '10C', 1, NULL),
(2969, '1025SSE00826', 'Student 1025', '1025SSE00826@fpt.edu.vn', 'SE00826', 1, NULL, '10C', 1, NULL),
(2970, '1026SSE00827', 'Student 1026', '1026SSE00827@fpt.edu.vn', 'SE00827', 1, NULL, '10C', 1, NULL),
(2971, '1027SSE00828', 'Student 1027', '1027SSE00828@fpt.edu.vn', 'SE00828', 1, NULL, '10C', 1, NULL),
(2972, '1028SSE00829', 'Student 1028', '1028SSE00829@fpt.edu.vn', 'SE00829', 1, NULL, '10C', 1, NULL),
(2973, '1029SSE00830', 'Student 1029', '1029SSE00830@fpt.edu.vn', 'SE00830', 1, NULL, '10C', 1, NULL),
(2974, '1030SSE00831', 'Student 1030', '1030SSE00831@fpt.edu.vn', 'SE00831', 1, NULL, '10C', 1, NULL),
(2975, '1031SSE00832', 'Student 1031', '1031SSE00832@fpt.edu.vn', 'SE00832', 1, NULL, '10C', 1, NULL),
(2976, '1032SSE00833', 'Student 1032', '1032SSE00833@fpt.edu.vn', 'SE00833', 1, NULL, '10C', 1, NULL),
(2977, '1033SSE00834', 'Student 1033', '1033SSE00834@fpt.edu.vn', 'SE00834', 1, NULL, '10C', 1, NULL),
(2978, '1034SSE00835', 'Student 1034', '1034SSE00835@fpt.edu.vn', 'SE00835', 1, NULL, '10C', 1, NULL),
(2979, '1035SSE00836', 'Student 1035', '1035SSE00836@fpt.edu.vn', 'SE00836', 1, NULL, '10C', 1, NULL),
(2980, '1036SSE00837', 'Student 1036', '1036SSE00837@fpt.edu.vn', 'SE00837', 1, NULL, '10C', 1, NULL);
INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(2981, '1037SSE00838', 'Student 1037', '1037SSE00838@fpt.edu.vn', 'SE00838', 1, NULL, '10C', 1, NULL),
(2982, '1038SSE00839', 'Student 1038', '1038SSE00839@fpt.edu.vn', 'SE00839', 1, NULL, '10C', 1, NULL),
(2983, '1039SSE00840', 'Student 1039', '1039SSE00840@fpt.edu.vn', 'SE00840', 1, NULL, '10C', 1, NULL),
(2984, '1040SSE00841', 'Student 1040', '1040SSE00841@fpt.edu.vn', 'SE00841', 1, NULL, '10C', 1, NULL),
(2985, '1041SSE00842', 'Student 1041', '1041SSE00842@fpt.edu.vn', 'SE00842', 1, NULL, '10C', 1, NULL),
(2986, '1042SSE00843', 'Student 1042', '1042SSE00843@fpt.edu.vn', 'SE00843', 1, NULL, '10C', 1, NULL),
(2987, '1043SSE00844', 'Student 1043', '1043SSE00844@fpt.edu.vn', 'SE00844', 1, NULL, '10C', 1, NULL),
(2988, '1044SSE00845', 'Student 1044', '1044SSE00845@fpt.edu.vn', 'SE00845', 1, NULL, '10C', 1, NULL),
(2989, '1045SSE00846', 'Student 1045', '1045SSE00846@fpt.edu.vn', 'SE00846', 1, NULL, '10C', 1, NULL),
(2990, '1046SSE00847', 'Student 1046', '1046SSE00847@fpt.edu.vn', 'SE00847', 1, NULL, '10C', 1, NULL),
(2991, '1047SSE00848', 'Student 1047', '1047SSE00848@fpt.edu.vn', 'SE00848', 1, NULL, '10C', 1, NULL),
(2992, '1048SSE00849', 'Student 1048', '1048SSE00849@fpt.edu.vn', 'SE00849', 1, NULL, '10C', 1, NULL),
(2993, '1049SSE00850', 'Student 1049', '1049SSE00850@fpt.edu.vn', 'SE00850', 1, NULL, '10C', 1, NULL),
(2994, '1050SSE00851', 'Student 1050', '1050SSE00851@fpt.edu.vn', 'SE00851', 1, NULL, '10C', 1, NULL),
(2995, '1051SSE00852', 'Student 1051', '1051SSE00852@fpt.edu.vn', 'SE00852', 1, NULL, '10C', 1, NULL),
(2996, '1052SSE00853', 'Student 1052', '1052SSE00853@fpt.edu.vn', 'SE00853', 1, NULL, '10C', 1, NULL),
(2997, '1053SSE00854', 'Student 1053', '1053SSE00854@fpt.edu.vn', 'SE00854', 1, NULL, '10C', 1, NULL),
(2998, '1054SSE00855', 'Student 1054', '1054SSE00855@fpt.edu.vn', 'SE00855', 1, NULL, '10C', 1, NULL),
(2999, '1055SSE00856', 'Student 1055', '1055SSE00856@fpt.edu.vn', 'SE00856', 1, NULL, '10C', 1, NULL),
(3000, '1056SSE00857', 'Student 1056', '1056SSE00857@fpt.edu.vn', 'SE00857', 1, NULL, '10C', 1, NULL),
(3001, '1057SSE00858', 'Student 1057', '1057SSE00858@fpt.edu.vn', 'SE00858', 1, NULL, '10C', 1, NULL),
(3002, '1058SSE00859', 'Student 1058', '1058SSE00859@fpt.edu.vn', 'SE00859', 1, NULL, '10C', 1, NULL),
(3003, '1059SSE00860', 'Student 1059', '1059SSE00860@fpt.edu.vn', 'SE00860', 1, NULL, '10C', 1, NULL),
(3004, '1060SSE00861', 'Student 1060', '1060SSE00861@fpt.edu.vn', 'SE00861', 1, NULL, '10C', 1, NULL),
(3005, '1061SSE00862', 'Student 1061', '1061SSE00862@fpt.edu.vn', 'SE00862', 1, NULL, '10C', 1, NULL),
(3006, '1062SSE00863', 'Student 1062', '1062SSE00863@fpt.edu.vn', 'SE00863', 1, NULL, '10C', 1, NULL),
(3007, '1063SSE00864', 'Student 1063', '1063SSE00864@fpt.edu.vn', 'SE00864', 1, NULL, '10C', 1, NULL),
(3008, '1064SSE00865', 'Student 1064', '1064SSE00865@fpt.edu.vn', 'SE00865', 1, NULL, '10C', 1, NULL),
(3009, '1065SSE00866', 'Student 1065', '1065SSE00866@fpt.edu.vn', 'SE00866', 1, NULL, '10C', 1, NULL),
(3010, '1066SSE00867', 'Student 1066', '1066SSE00867@fpt.edu.vn', 'SE00867', 1, NULL, '10C', 1, NULL),
(3011, '1067SSE00868', 'Student 1067', '1067SSE00868@fpt.edu.vn', 'SE00868', 1, NULL, '10C', 1, NULL),
(3012, '1068SSE00869', 'Student 1068', '1068SSE00869@fpt.edu.vn', 'SE00869', 1, NULL, '10C', 1, NULL),
(3013, '1069SSE00870', 'Student 1069', '1069SSE00870@fpt.edu.vn', 'SE00870', 1, NULL, '10C', 1, NULL),
(3014, '1070SSE00871', 'Student 1070', '1070SSE00871@fpt.edu.vn', 'SE00871', 1, NULL, '10C', 1, NULL),
(3015, '1071SSE00872', 'Student 1071', '1071SSE00872@fpt.edu.vn', 'SE00872', 1, NULL, '10C', 1, NULL),
(3016, '1072SSE00873', 'Student 1072', '1072SSE00873@fpt.edu.vn', 'SE00873', 1, NULL, '10C', 1, NULL),
(3017, '1073SSE00874', 'Student 1073', '1073SSE00874@fpt.edu.vn', 'SE00874', 1, NULL, '10C', 1, NULL),
(3018, '1074SSE00875', 'Student 1074', '1074SSE00875@fpt.edu.vn', 'SE00875', 1, NULL, '10C', 1, NULL),
(3019, '1075SSE00876', 'Student 1075', '1075SSE00876@fpt.edu.vn', 'SE00876', 1, NULL, '10C', 1, NULL),
(3020, '1076SSE00877', 'Student 1076', '1076SSE00877@fpt.edu.vn', 'SE00877', 1, NULL, '10C', 1, NULL),
(3021, '1077SSE00878', 'Student 1077', '1077SSE00878@fpt.edu.vn', 'SE00878', 1, NULL, '10C', 1, NULL),
(3022, '1078SSE00879', 'Student 1078', '1078SSE00879@fpt.edu.vn', 'SE00879', 1, NULL, '10C', 1, NULL),
(3023, '1079SSE00880', 'Student 1079', '1079SSE00880@fpt.edu.vn', 'SE00880', 1, NULL, '10C', 1, NULL),
(3024, '1080SSE00881', 'Student 1080', '1080SSE00881@fpt.edu.vn', 'SE00881', 1, NULL, '10C', 1, NULL),
(3025, '1081SSE00882', 'Student 1081', '1081SSE00882@fpt.edu.vn', 'SE00882', 1, NULL, '10C', 1, NULL),
(3026, '1082SSE00883', 'Student 1082', '1082SSE00883@fpt.edu.vn', 'SE00883', 1, NULL, '10C', 1, NULL),
(3027, '1083SSE00884', 'Student 1083', '1083SSE00884@fpt.edu.vn', 'SE00884', 1, NULL, '10C', 1, NULL),
(3028, '1084SSE00885', 'Student 1084', '1084SSE00885@fpt.edu.vn', 'SE00885', 1, NULL, '10C', 1, NULL),
(3029, '1085SSE00886', 'Student 1085', '1085SSE00886@fpt.edu.vn', 'SE00886', 1, NULL, '10C', 1, NULL),
(3030, '1086SEC00047', 'Student 1086', '1086SEC00047@fpt.edu.vn', 'EC00047', 9, NULL, '10C', 1, NULL),
(3031, '1087SEC00048', 'Student 1087', '1087SEC00048@fpt.edu.vn', 'EC00048', 9, NULL, '10C', 1, NULL),
(3032, '1088SEC00049', 'Student 1088', '1088SEC00049@fpt.edu.vn', 'EC00049', 9, NULL, '10C', 1, NULL),
(3033, '1089SEC00050', 'Student 1089', '1089SEC00050@fpt.edu.vn', 'EC00050', 9, NULL, '10C', 1, NULL),
(3034, '1090SEC00051', 'Student 1090', '1090SEC00051@fpt.edu.vn', 'EC00051', 9, NULL, '10C', 1, NULL),
(3035, '1091SEC00052', 'Student 1091', '1091SEC00052@fpt.edu.vn', 'EC00052', 9, NULL, '10C', 1, NULL),
(3036, '1092SEC00053', 'Student 1092', '1092SEC00053@fpt.edu.vn', 'EC00053', 9, NULL, '10C', 1, NULL),
(3037, '1093SEC00054', 'Student 1093', '1093SEC00054@fpt.edu.vn', 'EC00054', 9, NULL, '10C', 1, NULL),
(3038, '1094SEC00055', 'Student 1094', '1094SEC00055@fpt.edu.vn', 'EC00055', 9, NULL, '10C', 1, NULL),
(3039, '1095SEC00056', 'Student 1095', '1095SEC00056@fpt.edu.vn', 'EC00056', 9, NULL, '10C', 1, NULL),
(3040, '1096SEC00057', 'Student 1096', '1096SEC00057@fpt.edu.vn', 'EC00057', 9, NULL, '10C', 1, NULL),
(3041, '1097SEC00058', 'Student 1097', '1097SEC00058@fpt.edu.vn', 'EC00058', 9, NULL, '10C', 1, NULL),
(3042, '1098SEC00059', 'Student 1098', '1098SEC00059@fpt.edu.vn', 'EC00059', 9, NULL, '10C', 1, NULL),
(3043, '1099SEC00060', 'Student 1099', '1099SEC00060@fpt.edu.vn', 'EC00060', 9, NULL, '10C', 1, NULL),
(3044, '1100SEC00061', 'Student 1100', '1100SEC00061@fpt.edu.vn', 'EC00061', 9, NULL, '10C', 1, NULL),
(3045, '1101SEC00062', 'Student 1101', '1101SEC00062@fpt.edu.vn', 'EC00062', 9, NULL, '10C', 1, NULL),
(3046, '1102SEC00063', 'Student 1102', '1102SEC00063@fpt.edu.vn', 'EC00063', 9, NULL, '10C', 1, NULL),
(3047, '1103SEC00064', 'Student 1103', '1103SEC00064@fpt.edu.vn', 'EC00064', 9, NULL, '10C', 1, NULL),
(3048, '1104SEC00065', 'Student 1104', '1104SEC00065@fpt.edu.vn', 'EC00065', 9, NULL, '10C', 1, NULL),
(3049, '1105SEC00066', 'Student 1105', '1105SEC00066@fpt.edu.vn', 'EC00066', 9, NULL, '10C', 1, NULL),
(3050, '1106SEC00067', 'Student 1106', '1106SEC00067@fpt.edu.vn', 'EC00067', 9, NULL, '10C', 1, NULL),
(3051, '1107SEC00068', 'Student 1107', '1107SEC00068@fpt.edu.vn', 'EC00068', 9, NULL, '10C', 1, NULL),
(3052, '1108SEC00069', 'Student 1108', '1108SEC00069@fpt.edu.vn', 'EC00069', 9, NULL, '10C', 1, NULL),
(3053, '1109SEC00070', 'Student 1109', '1109SEC00070@fpt.edu.vn', 'EC00070', 9, NULL, '10C', 1, NULL),
(3054, '1110SEC00071', 'Student 1110', '1110SEC00071@fpt.edu.vn', 'EC00071', 9, NULL, '10C', 1, NULL),
(3055, '1111SEC00072', 'Student 1111', '1111SEC00072@fpt.edu.vn', 'EC00072', 9, NULL, '10C', 1, NULL),
(3056, '1112SEC00073', 'Student 1112', '1112SEC00073@fpt.edu.vn', 'EC00073', 9, NULL, '10C', 1, NULL),
(3057, '1113SIA00118', 'Student 1113', '1113SIA00118@fpt.edu.vn', 'IA00118', 13, NULL, '10C', 1, NULL),
(3058, '1114SIA00119', 'Student 1114', '1114SIA00119@fpt.edu.vn', 'IA00119', 13, NULL, '10C', 1, NULL),
(3059, '1115SIA00120', 'Student 1115', '1115SIA00120@fpt.edu.vn', 'IA00120', 13, NULL, '10C', 1, NULL),
(3060, '1116SIA00121', 'Student 1116', '1116SIA00121@fpt.edu.vn', 'IA00121', 13, NULL, '10C', 1, NULL),
(3061, '1117SIA00122', 'Student 1117', '1117SIA00122@fpt.edu.vn', 'IA00122', 13, NULL, '10C', 1, NULL),
(3062, '1118SIA00123', 'Student 1118', '1118SIA00123@fpt.edu.vn', 'IA00123', 13, NULL, '10C', 1, NULL),
(3063, '1119SIA00124', 'Student 1119', '1119SIA00124@fpt.edu.vn', 'IA00124', 13, NULL, '10C', 1, NULL),
(3064, '1120SIA00125', 'Student 1120', '1120SIA00125@fpt.edu.vn', 'IA00125', 13, NULL, '10C', 1, NULL),
(3065, '1121SIA00126', 'Student 1121', '1121SIA00126@fpt.edu.vn', 'IA00126', 13, NULL, '10C', 1, NULL),
(3066, '1122SIA00127', 'Student 1122', '1122SIA00127@fpt.edu.vn', 'IA00127', 13, NULL, '10C', 1, NULL),
(3067, '1123SIA00128', 'Student 1123', '1123SIA00128@fpt.edu.vn', 'IA00128', 13, NULL, '10C', 1, NULL),
(3068, '1124SIA00129', 'Student 1124', '1124SIA00129@fpt.edu.vn', 'IA00129', 13, NULL, '10C', 1, NULL),
(3069, '1125SIA00130', 'Student 1125', '1125SIA00130@fpt.edu.vn', 'IA00130', 13, NULL, '10C', 1, NULL),
(3070, '1126SIA00131', 'Student 1126', '1126SIA00131@fpt.edu.vn', 'IA00131', 13, NULL, '10C', 1, NULL),
(3071, '1127SIA00132', 'Student 1127', '1127SIA00132@fpt.edu.vn', 'IA00132', 13, NULL, '10C', 1, NULL),
(3072, '1128SIA00133', 'Student 1128', '1128SIA00133@fpt.edu.vn', 'IA00133', 13, NULL, '10C', 1, NULL),
(3073, '1129SIA00134', 'Student 1129', '1129SIA00134@fpt.edu.vn', 'IA00134', 13, NULL, '10C', 1, NULL),
(3074, '1130SIA00135', 'Student 1130', '1130SIA00135@fpt.edu.vn', 'IA00135', 13, NULL, '10C', 1, NULL),
(3075, '1131SIA00136', 'Student 1131', '1131SIA00136@fpt.edu.vn', 'IA00136', 13, NULL, '10C', 1, NULL),
(3076, '1132SIA00137', 'Student 1132', '1132SIA00137@fpt.edu.vn', 'IA00137', 13, NULL, '10C', 1, NULL),
(3077, '1133SIA00138', 'Student 1133', '1133SIA00138@fpt.edu.vn', 'IA00138', 13, NULL, '10C', 1, NULL),
(3078, '1134SIA00139', 'Student 1134', '1134SIA00139@fpt.edu.vn', 'IA00139', 13, NULL, '10C', 1, NULL),
(3079, '1135SIA00140', 'Student 1135', '1135SIA00140@fpt.edu.vn', 'IA00140', 13, NULL, '10C', 1, NULL),
(3080, '1136SIA00141', 'Student 1136', '1136SIA00141@fpt.edu.vn', 'IA00141', 13, NULL, '10C', 1, NULL),
(3081, '1137SIA00142', 'Student 1137', '1137SIA00142@fpt.edu.vn', 'IA00142', 13, NULL, '10C', 1, NULL),
(3082, '1138SIA00143', 'Student 1138', '1138SIA00143@fpt.edu.vn', 'IA00143', 13, NULL, '10C', 1, NULL),
(3083, '1139SIA00144', 'Student 1139', '1139SIA00144@fpt.edu.vn', 'IA00144', 13, NULL, '10C', 1, NULL),
(3084, '1140SIA00145', 'Student 1140', '1140SIA00145@fpt.edu.vn', 'IA00145', 13, NULL, '10C', 1, NULL),
(3085, '1141SIA00146', 'Student 1141', '1141SIA00146@fpt.edu.vn', 'IA00146', 13, NULL, '10C', 1, NULL),
(3086, '1142SIA00147', 'Student 1142', '1142SIA00147@fpt.edu.vn', 'IA00147', 13, NULL, '10C', 1, NULL),
(3087, '1143SIA00148', 'Student 1143', '1143SIA00148@fpt.edu.vn', 'IA00148', 13, NULL, '10C', 1, NULL),
(3088, '1144SIA00149', 'Student 1144', '1144SIA00149@fpt.edu.vn', 'IA00149', 13, NULL, '10C', 1, NULL),
(3089, '1145SGD00033', 'Student 1145', '1145SGD00033@fpt.edu.vn', 'GD00033', 12, NULL, '10C', 1, NULL),
(3090, '1146SGD00034', 'Student 1146', '1146SGD00034@fpt.edu.vn', 'GD00034', 12, NULL, '10C', 1, NULL),
(3091, '1147SGD00035', 'Student 1147', '1147SGD00035@fpt.edu.vn', 'GD00035', 12, NULL, '10C', 1, NULL),
(3092, '1148SGD00036', 'Student 1148', '1148SGD00036@fpt.edu.vn', 'GD00036', 12, NULL, '10C', 1, NULL),
(3093, '1149SGD00037', 'Student 1149', '1149SGD00037@fpt.edu.vn', 'GD00037', 12, NULL, '10C', 1, NULL),
(3094, '1150SGD00038', 'Student 1150', '1150SGD00038@fpt.edu.vn', 'GD00038', 12, NULL, '10C', 1, NULL),
(3095, '1151SGD00039', 'Student 1151', '1151SGD00039@fpt.edu.vn', 'GD00039', 12, NULL, '10C', 1, NULL),
(3096, '1152SGD00040', 'Student 1152', '1152SGD00040@fpt.edu.vn', 'GD00040', 12, NULL, '10C', 1, NULL),
(3097, '1153SGD00041', 'Student 1153', '1153SGD00041@fpt.edu.vn', 'GD00041', 12, NULL, '10C', 1, NULL),
(3098, '1154SGD00042', 'Student 1154', '1154SGD00042@fpt.edu.vn', 'GD00042', 12, NULL, '10C', 1, NULL),
(3099, '1155SGD00043', 'Student 1155', '1155SGD00043@fpt.edu.vn', 'GD00043', 12, NULL, '10C', 1, NULL),
(3100, '1156SGD00044', 'Student 1156', '1156SGD00044@fpt.edu.vn', 'GD00044', 12, NULL, '10C', 1, NULL),
(3101, '1157SSE00887', 'Student 1157', '1157SSE00887@fpt.edu.vn', 'SE00887', 1, 7, '0', 0, NULL),
(3102, '1158SSE00888', 'Student 1158', '1158SSE00888@fpt.edu.vn', 'SE00888', 1, 7, '0', 0, NULL),
(3103, '1159SSE00889', 'Student 1159', '1159SSE00889@fpt.edu.vn', 'SE00889', 1, 7, '0', 0, NULL),
(3104, '1160SSE00890', 'Student 1160', '1160SSE00890@fpt.edu.vn', 'SE00890', 1, 7, '0', 0, NULL),
(3105, '1161SSE00891', 'Student 1161', '1161SSE00891@fpt.edu.vn', 'SE00891', 1, 7, '0', 0, NULL),
(3106, '1162SSE00892', 'Student 1162', '1162SSE00892@fpt.edu.vn', 'SE00892', 1, 7, '0', 0, NULL),
(3107, '1163SSE00893', 'Student 1163', '1163SSE00893@fpt.edu.vn', 'SE00893', 1, 7, '0', 0, NULL),
(3108, '1164SSE00894', 'Student 1164', '1164SSE00894@fpt.edu.vn', 'SE00894', 1, 7, '0', 0, NULL),
(3109, '1165SSE00895', 'Student 1165', '1165SSE00895@fpt.edu.vn', 'SE00895', 1, 7, '0', 0, NULL),
(3110, '1166SSE00896', 'Student 1166', '1166SSE00896@fpt.edu.vn', 'SE00896', 1, 7, '0', 0, NULL),
(3111, '1167SSE00897', 'Student 1167', '1167SSE00897@fpt.edu.vn', 'SE00897', 1, 7, '0', 0, NULL),
(3112, '1168SSE00898', 'Student 1168', '1168SSE00898@fpt.edu.vn', 'SE00898', 1, 7, '0', 0, NULL),
(3113, '1169SSE00899', 'Student 1169', '1169SSE00899@fpt.edu.vn', 'SE00899', 1, 7, '0', 0, NULL),
(3114, '1170SSE00900', 'Student 1170', '1170SSE00900@fpt.edu.vn', 'SE00900', 1, 7, '0', 0, NULL),
(3115, '1171SSE00901', 'Student 1171', '1171SSE00901@fpt.edu.vn', 'SE00901', 1, 7, '0', 0, NULL),
(3116, '1172SSE00902', 'Student 1172', '1172SSE00902@fpt.edu.vn', 'SE00902', 1, 7, '0', 0, NULL),
(3117, '1173SSE00903', 'Student 1173', '1173SSE00903@fpt.edu.vn', 'SE00903', 1, 7, '0', 0, NULL),
(3118, '1174SSE00904', 'Student 1174', '1174SSE00904@fpt.edu.vn', 'SE00904', 1, 7, '0', 0, NULL),
(3119, '1175SSE00905', 'Student 1175', '1175SSE00905@fpt.edu.vn', 'SE00905', 1, 7, '0', 0, NULL),
(3120, '1176SSE00906', 'Student 1176', '1176SSE00906@fpt.edu.vn', 'SE00906', 1, 7, '0', 0, NULL);

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
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=84;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=321;
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
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=81;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=242;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=242;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
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
MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3121;
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
