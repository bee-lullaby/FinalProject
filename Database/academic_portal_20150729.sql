-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 01, 2015 at 07:08 PM
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `class_course_student_semester`
--

DROP TABLE IF EXISTS `class_course_student_semester`;
CREATE TABLE IF NOT EXISTS `class_course_student_semester` (
`class_course_student_semester_id` int(11) NOT NULL,
  `class_course_semester_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2745 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

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
MODIFY `class_course_semester_merge_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `class_course_student_semester`
--
ALTER TABLE `class_course_student_semester`
MODIFY `class_course_student_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2745;
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
MODIFY `specialized_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
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
