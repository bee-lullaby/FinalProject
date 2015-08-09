-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 09, 2015 at 08:35 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `buildings`
--

INSERT INTO `buildings` (`building_id`, `building_code`) VALUES
(3, 'HB'),
(4, 'GD');

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
) ENGINE=InnoDB AUTO_INCREMENT=456 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_course_semester`
--

INSERT INTO `class_course_semester` (`class_course_semester_id`, `class_semester_id`, `course_semester_id`, `block_condition`, `is_semester_long`) VALUES
(321, 81, 243, 0, 0),
(322, 81, 242, 0, 0),
(323, 81, 263, 0, 0),
(324, 81, 251, 0, 0),
(325, 81, 257, 0, 1),
(326, 82, 243, 0, 0),
(327, 82, 242, 0, 0),
(328, 82, 263, 0, 0),
(329, 82, 251, 0, 0),
(330, 82, 257, 0, 1),
(331, 83, 243, 0, 0),
(332, 83, 242, 0, 0),
(333, 83, 263, 0, 0),
(334, 83, 251, 0, 0),
(335, 83, 257, 0, 1),
(336, 84, 243, 0, 0),
(337, 84, 242, 0, 0),
(338, 84, 263, 0, 0),
(339, 84, 251, 0, 0),
(340, 84, 257, 0, 1),
(341, 85, 243, 0, 0),
(342, 85, 242, 0, 0),
(343, 85, 263, 0, 0),
(344, 85, 251, 0, 0),
(345, 85, 257, 0, 1),
(346, 86, 243, 0, 0),
(347, 86, 242, 0, 0),
(348, 86, 263, 0, 0),
(349, 86, 251, 0, 0),
(350, 86, 257, 0, 1),
(351, 87, 263, 0, 0),
(352, 87, 243, 0, 0),
(353, 87, 257, 0, 0),
(354, 87, 251, 0, 0),
(355, 87, 266, 0, 1),
(356, 88, 243, 0, 0),
(357, 88, 242, 0, 0),
(358, 88, 263, 0, 0),
(359, 88, 251, 0, 0),
(360, 88, 257, 0, 1),
(361, 89, 244, 0, 0),
(362, 89, 247, 0, 0),
(363, 89, 275, 0, 0),
(364, 89, 264, 0, 0),
(365, 89, 255, 0, 1),
(366, 90, 244, 0, 0),
(367, 90, 247, 0, 0),
(368, 90, 275, 0, 0),
(369, 90, 264, 0, 0),
(370, 90, 255, 0, 1),
(371, 91, 244, 0, 0),
(372, 91, 247, 0, 0),
(373, 91, 275, 0, 0),
(374, 91, 264, 0, 0),
(375, 91, 255, 0, 1),
(376, 92, 244, 0, 0),
(377, 92, 247, 0, 0),
(378, 92, 275, 0, 0),
(379, 92, 264, 0, 0),
(380, 92, 255, 0, 1),
(381, 93, 260, 0, 0),
(382, 93, 248, 0, 0),
(383, 93, 250, 1, 0),
(384, 93, 252, 1, 0),
(385, 93, 258, 0, 1),
(386, 94, 260, 0, 0),
(387, 94, 248, 0, 0),
(388, 94, 250, 1, 0),
(389, 94, 252, 1, 0),
(390, 94, 258, 0, 1),
(391, 95, 260, 0, 0),
(392, 95, 248, 0, 0),
(393, 95, 250, 1, 0),
(394, 95, 252, 1, 0),
(395, 95, 258, 0, 1),
(396, 96, 256, 0, 0),
(397, 96, 248, 0, 0),
(398, 96, 250, 1, 0),
(399, 96, 252, 1, 0),
(400, 96, 258, 0, 1),
(401, 97, 275, 0, 0),
(402, 97, 249, 0, 0),
(403, 97, 250, 1, 0),
(404, 97, 253, 1, 0),
(405, 97, 262, 0, 1),
(406, 98, 275, 0, 0),
(407, 98, 249, 0, 0),
(408, 98, 250, 1, 0),
(409, 98, 253, 1, 0),
(410, 98, 262, 0, 1),
(411, 99, 275, 0, 0),
(412, 99, 249, 0, 0),
(413, 99, 250, 1, 0),
(414, 99, 253, 1, 0),
(415, 99, 262, 0, 1),
(416, 100, 275, 0, 0),
(417, 100, 249, 0, 0),
(418, 100, 250, 1, 0),
(419, 100, 253, 1, 0),
(420, 100, 262, 0, 1),
(421, 101, 275, 0, 0),
(422, 101, 249, 0, 0),
(423, 101, 250, 1, 0),
(424, 101, 253, 1, 0),
(425, 101, 262, 0, 1),
(426, 102, 246, 0, 0),
(427, 102, 275, 0, 0),
(428, 102, 271, 0, 0),
(429, 102, 253, 1, 0),
(430, 102, 259, 0, 1),
(431, 103, 246, 0, 0),
(432, 103, 275, 0, 0),
(433, 103, 271, 0, 0),
(434, 103, 253, 1, 0),
(435, 103, 259, 0, 1),
(436, 104, 267, 0, 0),
(437, 104, 269, 0, 0),
(438, 104, 261, 0, 0),
(439, 104, 265, 0, 0),
(440, 104, 254, 0, 1),
(441, 105, 267, 0, 0),
(442, 105, 269, 0, 0),
(443, 105, 261, 0, 0),
(444, 105, 265, 0, 0),
(445, 105, 254, 0, 1),
(446, 106, 267, 0, 0),
(447, 106, 269, 0, 0),
(448, 106, 261, 0, 0),
(449, 106, 265, 0, 0),
(450, 106, 254, 0, 1),
(451, 107, 268, 0, 0),
(452, 107, 270, 0, 0),
(453, 107, 272, 0, 0),
(454, 107, 273, 0, 0),
(455, 107, 274, 0, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_semester`
--

INSERT INTO `class_semester` (`class_semester_id`, `class_id`, `semester_id`, `semester`) VALUES
(81, 84, 2, 1),
(82, 85, 2, 1),
(83, 86, 2, 1),
(84, 87, 2, 1),
(85, 88, 2, 1),
(86, 89, 2, 1),
(87, 90, 2, 1),
(88, 91, 2, 1),
(89, 92, 2, 2),
(90, 93, 2, 2),
(91, 94, 2, 2),
(92, 95, 2, 2),
(93, 96, 2, 3),
(94, 97, 2, 3),
(95, 98, 2, 3),
(96, 99, 2, 3),
(97, 100, 2, 4),
(98, 101, 2, 4),
(99, 102, 2, 4),
(100, 103, 2, 4),
(101, 104, 2, 4),
(102, 105, 2, 4),
(103, 106, 2, 4),
(104, 107, 2, 5),
(105, 108, 2, 5),
(106, 109, 2, 5),
(107, 110, 2, 5);

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
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `code`, `type`, `specialized_id`, `detail_specialized_id`, `course_id`, `batch`, `batch_char`, `number`) VALUES
(84, 'SE1007', 'Specialized', 1, 1, NULL, 10, 'C', 7),
(85, 'SE1008', 'Specialized', 1, 1, NULL, 10, 'C', 8),
(86, 'SE1009', 'Specialized', 1, 1, NULL, 10, 'C', 9),
(87, 'SE1010', 'Specialized', 1, 1, NULL, 10, 'C', 10),
(88, 'SE1011', 'Specialized', 1, 1, NULL, 10, 'C', 11),
(89, 'SE1012', 'Specialized', 1, 1, NULL, 10, 'C', 12),
(90, 'EC1003', 'Specialized', 1, 8, NULL, 10, 'C', 3),
(91, 'IA1003', 'Specialized', 1, 7, NULL, 10, 'C', 3),
(92, 'SE1004', 'Specialized', 1, 1, NULL, 10, 'B', 4),
(93, 'SE1005', 'Specialized', 1, 1, NULL, 10, 'B', 5),
(94, 'SE1006', 'Specialized', 1, 1, NULL, 10, 'B', 6),
(95, 'IA1002', 'Specialized', 1, 7, NULL, 10, 'B', 2),
(96, 'SE1001', 'Specialized', 1, 1, NULL, 9, 'A', 1),
(97, 'SE1002', 'Specialized', 1, 1, NULL, 9, 'A', 2),
(98, 'SE1003', 'Specialized', 1, 1, NULL, 9, 'A', 3),
(99, 'EC1001', 'Specialized', 1, 8, NULL, 9, 'A', 1),
(100, 'SE0907', 'Specialized', 1, 1, NULL, 9, 'C', 7),
(101, 'SE0908', 'Specialized', 1, 1, NULL, 9, 'C', 8),
(102, 'SE0909', 'Specialized', 1, 1, NULL, 9, 'C', 9),
(103, 'SE0910', 'Specialized', 1, 1, NULL, 9, 'C', 10),
(104, 'SE0911', 'Specialized', 1, 1, NULL, 9, 'C', 11),
(105, 'IA0902', 'Specialized', 1, 7, NULL, 9, 'C', 2),
(106, 'IA0903', 'Specialized', 1, 7, NULL, 9, 'C', 3),
(107, 'SE0904', 'Specialized', 1, 1, NULL, 9, 'B', 4),
(108, 'SE0905', 'Specialized', 1, 1, NULL, 9, 'B', 5),
(109, 'SE0906', 'Specialized', 1, 1, NULL, 9, 'B', 6),
(110, 'IA10901', 'Specialized', 1, 7, NULL, 9, 'B', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_semester`
--

INSERT INTO `course_semester` (`course_semester_id`, `course_id`, `semester_id`, `slots`, `course_condition_id`) VALUES
(242, 242, 2, 30, NULL),
(243, 243, 2, 60, NULL),
(244, 244, 2, 30, NULL),
(245, 245, 2, 30, NULL),
(246, 246, 2, 30, NULL),
(247, 247, 2, 30, NULL),
(248, 248, 2, 30, NULL),
(249, 249, 2, 30, NULL),
(250, 250, 2, 30, 247),
(251, 251, 2, 30, NULL),
(252, 252, 2, 30, 247),
(253, 253, 2, 30, 248),
(254, 254, 2, 30, 251),
(255, 255, 2, 30, 251),
(256, 256, 2, 30, NULL),
(257, 257, 2, 30, NULL),
(258, 258, 2, 30, 247),
(259, 259, 2, 30, NULL),
(260, 260, 2, 30, NULL),
(261, 261, 2, 30, NULL),
(262, 262, 2, 30, 260),
(263, 263, 2, 30, NULL),
(264, 264, 2, 30, NULL),
(265, 265, 2, 30, NULL),
(266, 266, 2, 30, NULL),
(267, 267, 2, 30, NULL),
(268, 268, 2, 30, NULL),
(269, 269, 2, 30, NULL),
(270, 270, 2, 30, NULL),
(271, 271, 2, 30, NULL),
(272, 272, 2, 30, NULL),
(273, 273, 2, 30, NULL),
(274, 274, 2, 30, NULL),
(275, 275, 2, 30, NULL),
(276, 276, 2, 30, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `code`, `name`, `department_id`) VALUES
(242, 'SSG101', 'Working in Group', 25),
(243, 'CSI101', 'Introduction to Computing', 15),
(244, 'PRO201', 'Front-end Web Development', 15),
(245, 'DBI201', 'Introduction to Databases', 15),
(246, 'CSD202', 'Data structures and algorithms (In C++)', 15),
(247, 'PRO192', 'Object-Oriented Programming with Java', 15),
(248, 'OSG202', 'Operating Systems', 15),
(249, 'PRN292', 'C# and .Net Technology', 15),
(250, 'CSD201', 'Data Structures and Algorithms', 15),
(251, 'PRF192', 'Programming fundamentals using C', 15),
(252, 'PRJ311', 'Desktop Java Applications', 15),
(253, 'NWC202', 'Computer Networking', 15),
(254, 'PRJ321', 'Web-based Java Applications', 15),
(255, 'LAB101', 'Lab 1', 18),
(256, 'IAO101', 'Information Assurance Overview', 21),
(257, 'CEA201', 'Computer Organization and Architecture', 21),
(258, 'LAB211', 'Pre-intermediate Practicing', 18),
(259, 'CES201', 'System support and Trouble shooting for Windows Server 2008', 21),
(260, 'JPD111', 'Japanese 1', 22),
(261, 'JPS132', 'Japanese 3', 22),
(262, 'JPD131', 'Industry driven course (Japanese 3)', 22),
(263, 'MAE101', 'Mathematics for Engineering', 23),
(264, 'MAD101', 'Discrete mathematics', 23),
(265, 'MAS291', 'Probability and Applied Statistics', 23),
(266, 'PHY101', 'Physics', 23),
(267, 'SWR301', 'Software Requirements', 24),
(268, 'IAA301', 'Phân tích thông tin\nIntelligence Analysics', 24),
(269, 'SWQ391', 'Software Quality Assurance and Testing', 24),
(270, 'IAA201', 'Phân tích lỗ hổng và rủi ro\nRisk-Vulnerability Analysis', 24),
(271, 'IAP201', 'Privacy and IT Ethics', 24),
(272, 'IAP301', 'Phát triển chính sách ATTT\nPolicy Development in Information Assurance', 24),
(273, 'SRE301', 'Kỹ thuật dịch ngược \nSoftware Revert Engineering', 24),
(274, 'OSP201', 'Hệ thống nguồn mở và quản trị mạng\nOpen Source Platform and Network Administration', 24),
(275, 'DBI202', 'DBI202', 15),
(276, 'SWD391', 'SWD391', 24);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
`department_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `code`, `name`) VALUES
(14, 'BA', 'BA'),
(15, 'CF', 'CF'),
(16, 'CS', 'CS'),
(17, 'ENG', 'ENG'),
(18, 'FSOFT', 'FSOFT'),
(19, 'GD', 'GD'),
(20, 'HS', 'HS'),
(21, 'ITS', 'ITS'),
(22, 'JPN', 'JPN'),
(23, 'Math', 'Math'),
(24, 'SE', 'SE'),
(25, 'SS', 'SS'),
(26, 'TI', 'TI');

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_semester`
--

INSERT INTO `program_semester` (`program_semester_id`, `semester_id`, `current_semester`, `specialized_id`, `detail_specialized_id`, `batch`) VALUES
(13, 2, 1, 1, 1, 10),
(14, 2, 2, 1, 1, 10),
(15, 2, 3, 1, 1, 10),
(16, 2, 4, 1, 1, 9),
(17, 2, 5, 1, 1, 9),
(18, 2, 1, 1, 7, 10),
(19, 2, 2, 1, 7, 10),
(20, 2, 3, 1, 8, 10),
(21, 2, 4, 1, 7, 9),
(22, 2, 5, 1, 7, 9),
(23, 2, 1, 1, 8, 10);

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
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `program_semester_detail`
--

INSERT INTO `program_semester_detail` (`program_semester_detail_id`, `program_semester_id`, `course_semester_id`, `is_semester_long`) VALUES
(52, 13, 243, 0),
(53, 13, 242, 0),
(54, 13, 263, 0),
(55, 13, 251, 0),
(56, 13, 257, 1),
(57, 14, 244, 0),
(58, 14, 247, 0),
(59, 14, 275, 0),
(60, 14, 264, 0),
(61, 14, 255, 1),
(62, 15, 260, 0),
(63, 15, 248, 0),
(64, 15, 250, 0),
(65, 15, 252, 0),
(66, 15, 258, 1),
(67, 16, 275, 0),
(68, 16, 249, 0),
(69, 16, 250, 0),
(70, 16, 253, 0),
(71, 16, 262, 1),
(72, 17, 267, 0),
(73, 17, 269, 0),
(74, 17, 261, 0),
(75, 17, 265, 0),
(76, 17, 254, 1),
(77, 18, 243, 0),
(78, 18, 242, 0),
(79, 18, 263, 0),
(80, 18, 251, 0),
(81, 18, 257, 1),
(82, 19, 244, 0),
(83, 19, 247, 0),
(84, 19, 275, 0),
(85, 19, 264, 0),
(86, 19, 255, 1),
(87, 20, 256, 0),
(88, 20, 248, 0),
(89, 20, 250, 0),
(90, 20, 252, 0),
(91, 20, 258, 1),
(92, 21, 246, 0),
(93, 21, 275, 0),
(94, 21, 271, 0),
(95, 21, 253, 0),
(96, 21, 259, 1),
(97, 22, 268, 0),
(98, 22, 270, 0),
(99, 22, 272, 0),
(100, 22, 273, 0),
(101, 22, 274, 1),
(102, 23, 263, 0),
(103, 23, 243, 0),
(104, 23, 257, 0),
(105, 23, 251, 0),
(106, 23, 266, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `code`, `courses`, `capacity`, `building_id`) VALUES
(50, 'P201', 'SSG101, SSC102, SSC101', 30, NULL),
(51, 'P202', 'SSG101, SSC102, SSC101', 30, NULL),
(52, 'P203', 'SSG101, SSC102, SSC101', 30, NULL),
(53, 'P204', NULL, 30, NULL),
(54, 'P205', NULL, 30, NULL),
(55, 'P209', NULL, 30, NULL),
(56, 'P211', NULL, 30, NULL),
(57, 'P212', NULL, 30, NULL),
(58, 'P215', NULL, 30, NULL),
(59, 'P216', NULL, 30, NULL),
(60, 'P217', NULL, 30, NULL),
(61, 'P218', NULL, 30, NULL),
(62, 'P219', NULL, 30, NULL),
(63, 'P220', NULL, 30, NULL),
(64, 'P221', NULL, 30, NULL),
(65, 'P301', NULL, 30, NULL),
(66, 'P302', NULL, 30, NULL),
(67, 'P303', NULL, 30, NULL),
(68, 'P304', NULL, 30, NULL),
(69, 'P305', NULL, 30, NULL),
(70, 'P306', NULL, 30, NULL),
(71, 'P307', NULL, 30, NULL),
(72, 'P308', NULL, 30, NULL),
(73, 'P309', NULL, 30, NULL),
(74, 'P310', NULL, 30, NULL),
(75, 'P311', NULL, 31, NULL),
(76, 'P312', NULL, 32, NULL),
(77, 'P313', NULL, 30, NULL),
(78, 'P314', NULL, 30, NULL),
(79, 'P315', NULL, 30, NULL),
(80, 'P316', NULL, 30, NULL),
(81, 'P317', NULL, 30, NULL),
(82, 'P318', NULL, 30, NULL),
(83, 'P319', NULL, 30, NULL),
(84, 'P320', NULL, 31, NULL),
(85, 'P321', NULL, 30, NULL),
(86, 'P411', NULL, 32, NULL),
(87, 'HB/201L', NULL, 33, NULL),
(88, 'HB/203L', NULL, 30, NULL),
(89, 'HB/204L', NULL, 30, NULL),
(90, 'HB/207L', NULL, 30, NULL),
(91, 'HB/301R', NULL, 30, NULL),
(92, 'HB/302R', NULL, 30, NULL),
(93, 'HB/303R-305R', NULL, 70, NULL),
(94, 'HB/304R', NULL, 30, NULL),
(95, 'HB/307R', NULL, 30, NULL),
(96, 'HB/309R', NULL, 30, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `semesters`
--

INSERT INTO `semesters` (`semester_id`, `code`, `name`, `semester_year`, `start_date`, `end_date`) VALUES
(2, 'FA15', 'Fall 2015', 0, '2015-09-07', '2016-01-02');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialized`
--

INSERT INTO `specialized` (`specialized_id`, `code`, `name`, `is_detail_specialized`) VALUES
(1, 'SE', 'SE', 0),
(2, 'IS1', 'IS1', 1),
(3, 'IS2', 'IS2', 1),
(4, 'ES1', 'ES1', 1),
(5, 'ES2', 'ES2', 1),
(6, 'JS', 'JS', 1),
(7, 'IA', 'IA', 1),
(8, 'EC', 'EC', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `account`, `name`, `email`, `account_type`) VALUES
(3, 'hansse02618', 'Nguyen Son Ha', 'hansse02618@fpt.edu.vn', 'staff'),
(4, 'duchmse02551', 'Hoang Minh Duc', 'duchmse02551@fpt.edu.vn', 'staff');

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
-- Table structure for table `teacher_course_semester`
--

DROP TABLE IF EXISTS `teacher_course_semester`;
CREATE TABLE IF NOT EXISTS `teacher_course_semester` (
`teacher_course_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=756 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_course_semester`
--

INSERT INTO `teacher_course_semester` (`teacher_course_semester_id`, `teacher_semester_id`, `course_semester_id`) VALUES
(687, 40, 242),
(688, 58, 242),
(689, 41, 243),
(690, 42, 243),
(691, 42, 244),
(692, 59, 244),
(693, 42, 275),
(694, 59, 275),
(695, 43, 246),
(696, 42, 246),
(697, 43, 247),
(698, 42, 247),
(699, 59, 247),
(700, 41, 247),
(701, 44, 248),
(702, 60, 248),
(703, 45, 249),
(704, 61, 249),
(705, 43, 250),
(706, 42, 250),
(707, 41, 251),
(708, 52, 251),
(709, 42, 252),
(710, 62, 252),
(711, 44, 252),
(712, 43, 252),
(713, 44, 253),
(714, 60, 253),
(715, 42, 254),
(716, 62, 254),
(717, 46, 255),
(718, 47, 256),
(719, 48, 257),
(720, 63, 257),
(721, 46, 258),
(722, 49, 259),
(723, 50, 260),
(724, 64, 260),
(725, 69, 260),
(726, 72, 260),
(727, 50, 261),
(728, 64, 261),
(729, 69, 261),
(730, 72, 261),
(731, 50, 262),
(732, 64, 262),
(733, 69, 262),
(734, 72, 262),
(735, 51, 263),
(736, 65, 263),
(737, 70, 263),
(738, 51, 264),
(739, 65, 264),
(740, 71, 264),
(741, 51, 265),
(742, 66, 265),
(743, 71, 265),
(744, 52, 266),
(745, 67, 266),
(746, 53, 267),
(747, 55, 267),
(748, 54, 268),
(749, 53, 269),
(750, 68, 269),
(751, 54, 270),
(752, 55, 271),
(753, 55, 272),
(754, 56, 273),
(755, 57, 274);

-- --------------------------------------------------------

--
-- Table structure for table `teacher_semester`
--

DROP TABLE IF EXISTS `teacher_semester`;
CREATE TABLE IF NOT EXISTS `teacher_semester` (
`teacher_semester_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_semester`
--

INSERT INTO `teacher_semester` (`teacher_semester_id`, `teacher_id`, `semester_id`) VALUES
(40, 40, 2),
(41, 41, 2),
(42, 42, 2),
(43, 43, 2),
(44, 44, 2),
(45, 45, 2),
(46, 46, 2),
(47, 47, 2),
(48, 48, 2),
(49, 49, 2),
(50, 50, 2),
(51, 51, 2),
(52, 52, 2),
(53, 53, 2),
(54, 54, 2),
(55, 55, 2),
(56, 56, 2),
(57, 57, 2),
(58, 58, 2),
(59, 59, 2),
(60, 60, 2),
(61, 61, 2),
(62, 62, 2),
(63, 63, 2),
(64, 64, 2),
(65, 65, 2),
(66, 66, 2),
(67, 67, 2),
(68, 68, 2),
(69, 69, 2),
(70, 70, 2),
(71, 71, 2),
(72, 72, 2);

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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `account`, `name`, `email`, `account_type`) VALUES
(40, 'MaiTT', 'MaiTT', 'MaiTT@fpt.edu.vn', 'teacher'),
(41, 'DungLM', 'DungLM', 'DungLM@fpt.edu.vn', 'teacher'),
(42, 'DuyDT', 'DuyDT', 'DuyDT@fpt.edu.vn', 'teacher'),
(43, 'CauPD', 'CauPD', 'CauPD@fpt.edu.vn', 'teacher'),
(44, 'VuNH', 'VuNH', 'VuNH@fpt.edu.vn', 'teacher'),
(45, 'HuongNT7', 'HuongNT7', 'HuongNT7@fpt.edu.vn', 'teacher'),
(46, 'HaNS2', 'HaNS2', 'HaNS2@fpt.edu.vn', 'teacher'),
(47, 'HieuLD2', 'HieuLD2', 'HieuLD2@fpt.edu.vn', 'teacher'),
(48, 'HungPD', 'HungPD', 'HungPD@fpt.edu.vn', 'teacher'),
(49, 'TrungNQ', 'TrungNQ', 'TrungNQ@fpt.edu.vn', 'teacher'),
(50, 'CuongN', 'CuongN', 'CuongN@fpt.edu.vn', 'teacher'),
(51, 'TrungDT', 'TrungDT', 'TrungDT@fpt.edu.vn', 'teacher'),
(52, 'KhuongPD', 'KhuongPD', 'KhuongPD@fpt.edu.vn', 'teacher'),
(53, 'SangNV', 'SangNV', 'SangNV@fpt.edu.vn', 'teacher'),
(54, 'DucHM2', 'DucHM2', 'DucHM2@fpt.edu.vn', 'teacher'),
(55, 'AnhPT', 'AnhPT', 'AnhPT@fpt.edu.vn', 'teacher'),
(56, 'CongLT2', 'CongLT2', 'CongLT2@fpt.edu.vn', 'teacher'),
(57, 'HoaiNV2', 'HoaiNV2', 'HoaiNV2@fpt.edu.vn', 'teacher'),
(58, 'HienDTT', 'HienDTT', 'HienDTT@fpt.edu.vn', 'teacher'),
(59, 'ChiLP', 'ChiLP', 'ChiLP@fpt.edu.vn', 'teacher'),
(60, 'BangBH', 'BangBH', 'BangBH@fpt.edu.vn', 'teacher'),
(61, 'LamPT', 'LamPT', 'LamPT@fpt.edu.vn', 'teacher'),
(62, 'BinhNV2', 'BinhNV2', 'BinhNV2@fpt.edu.vn', 'teacher'),
(63, 'SonHX', 'SonHX', 'SonHX@fpt.edu.vn', 'teacher'),
(64, 'ThuLX', 'ThuLX', 'ThuLX@fpt.edu.vn', 'teacher'),
(65, 'MaiVTT', 'MaiVTT', 'MaiVTT@fpt.edu.vn', 'teacher'),
(66, 'VietNK', 'VietNK', 'VietNK@fpt.edu.vn', 'teacher'),
(67, 'DuanTC', 'DuanTC', 'DuanTC@fpt.edu.vn', 'teacher'),
(68, 'TrungNT', 'TrungNT', 'TrungNT@fpt.edu.vn', 'teacher'),
(69, 'VanDT', 'VanDT', 'VanDT@fpt.edu.vn', 'teacher'),
(70, 'QuyPH', 'QuyPH', 'QuyPH@fpt.edu.vn', 'teacher'),
(71, 'DungNT', 'DungNT', 'DungNT@fpt.edu.vn', 'teacher'),
(72, 'HuyenTT', 'HuyenTT', 'HuyenTT@fpt.edu.vn', 'teacher');

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
-- Indexes for table `classes`
--
ALTER TABLE `classes`
 ADD PRIMARY KEY (`class_id`), ADD UNIQUE KEY `code` (`code`), ADD KEY `specialized_id` (`specialized_id`), ADD KEY `course_id` (`course_id`), ADD KEY `detail_specialized_id` (`detail_specialized_id`);

--
-- Indexes for table `course_semester`
--
ALTER TABLE `course_semester`
 ADD PRIMARY KEY (`course_semester_id`), ADD UNIQUE KEY `my_idx` (`course_id`,`semester_id`), ADD KEY `course_condition_id` (`course_condition_id`), ADD KEY `course_id` (`course_id`), ADD KEY `semester_id` (`semester_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
 ADD PRIMARY KEY (`course_id`), ADD UNIQUE KEY `code` (`code`), ADD KEY `department_id` (`department_id`);

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
-- Indexes for table `student_course`
--
ALTER TABLE `student_course`
 ADD PRIMARY KEY (`student_course_id`), ADD KEY `student_id` (`student_id`,`course_id`,`class_semester_id`), ADD KEY `course_id` (`course_id`), ADD KEY `class_semester_id` (`class_semester_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
 ADD PRIMARY KEY (`student_id`), ADD UNIQUE KEY `account` (`account`,`email`,`student_code`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `specialized_id` (`specialized_id`), ADD KEY `detail_specialized_id` (`detail_specialized_id`);

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
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
 ADD PRIMARY KEY (`teacher_id`), ADD UNIQUE KEY `account` (`account`), ADD UNIQUE KEY `email` (`email`);

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
MODIFY `building_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=456;
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
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=108;
--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=111;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=277;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=277;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `program_semester`
--
ALTER TABLE `program_semester`
MODIFY `program_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `program_semester_detail`
--
ALTER TABLE `program_semester_detail`
MODIFY `program_semester_detail_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=107;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=97;
--
-- AUTO_INCREMENT for table `semesters`
--
ALTER TABLE `semesters`
MODIFY `semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `specialized`
--
ALTER TABLE `specialized`
MODIFY `specialized_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
MODIFY `student_course_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
MODIFY `teacher_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=756;
--
-- AUTO_INCREMENT for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
MODIFY `teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=73;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=73;
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
-- Constraints for table `classes`
--
ALTER TABLE `classes`
ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`),
ADD CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `classes_ibfk_3` FOREIGN KEY (`detail_specialized_id`) REFERENCES `specialized` (`specialized_id`);

--
-- Constraints for table `course_semester`
--
ALTER TABLE `course_semester`
ADD CONSTRAINT `course_semester_ibfk_3` FOREIGN KEY (`course_condition_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `course_semester_ibfk_4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `course_semester_ibfk_5` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`);

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
ADD CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`);

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
-- Constraints for table `student_course`
--
ALTER TABLE `student_course`
ADD CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
ADD CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `student_course_ibfk_3` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`class_semester_id`) REFERENCES `class_semester` (`class_semester_id`),
ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`specialized_id`) REFERENCES `specialized` (`specialized_id`),
ADD CONSTRAINT `students_ibfk_3` FOREIGN KEY (`detail_specialized_id`) REFERENCES `specialized` (`specialized_id`);

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
--
-- Database: `cdcol`
--
CREATE DATABASE IF NOT EXISTS `cdcol` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;
USE `cdcol`;

-- --------------------------------------------------------

--
-- Table structure for table `cds`
--

DROP TABLE IF EXISTS `cds`;
CREATE TABLE IF NOT EXISTS `cds` (
  `titel` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `interpret` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `jahr` int(11) DEFAULT NULL,
`id` bigint(20) unsigned NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `cds`
--

INSERT INTO `cds` (`titel`, `interpret`, `jahr`, `id`) VALUES
('Beauty', 'Ryuichi Sakamoto', 1990, 1),
('Goodbye Country (Hello Nightclub)', 'Groove Armada', 2001, 4),
('Glee', 'Bran Van 3000', 1997, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cds`
--
ALTER TABLE `cds`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cds`
--
ALTER TABLE `cds`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;--
-- Database: `contacts`
--
CREATE DATABASE IF NOT EXISTS `contacts` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `contacts`;

-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
CREATE TABLE IF NOT EXISTS `contacts` (
`id` mediumint(8) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`id`, `name`, `phone`, `email`, `street`, `place`) VALUES
(1, 'Brynne Z. Reed', '056 9077 2466', 'dis@nonfeugiatnec.ca', '801-6008 Ligula. Road', 'Mauritius'),
(2, 'Ivan G. Mercer', '0800 1111', 'ligula.eu@euaccumsansed.com', 'Ap #572-9739 Sit St.', 'Togo'),
(3, 'Brennan K. Hampton', '055 4668 0543', 'nec.orci.Donec@enimgravidasit.org', 'Ap #415-2472 Ultrices Street', 'Albania'),
(4, 'Wing P. Greene', '07115 568749', 'dolor.Fusce.feugiat@ullamcorperDuis.ca', '3813 Quisque Avenue', 'Saint Lucia'),
(5, 'Adena C. Garza', '(01070) 85936', 'porttitor.interdum.Sed@euismod.edu', '2557 Enim. Av.', 'Congo, the Democratic Republic of the'),
(6, 'Daniel Q. Woodward', '(014575) 47955', 'ut@Aliquamfringillacursus.ca', 'Ap #783-5383 Massa. Avenue', 'Afghanistan'),
(7, 'Xyla Z. Kirk', '0500 944869', 'sed.facilisis@Proinvelnisl.edu', '387-7139 Posuere Street', 'Czech Republic'),
(8, 'Shelby Q. Espinoza', '(01640) 23589', 'parturient.montes.nascetur@mieleifend.edu', '8880 Non Road', 'Ethiopia'),
(9, 'Richard B. George', '(0111) 895 9689', 'mauris.aliquam.eu@fringilla.net', 'P.O. Box 195, 5269 Metus Rd.', 'Sierra Leone'),
(10, 'Hilary U. Young', '(010265) 48551', 'quis.massa.Mauris@natoquepenatibus.org', '6773 At Avenue', 'Madagascar'),
(11, 'Colin Y. Ross', '0805 259 2474', 'Aliquam.adipiscing@turpisIn.net', '570-3800 Suspendisse Rd.', 'Lithuania'),
(12, 'Dominique W. Donovan', '(016977) 2745', 'Sed.neque.Sed@habitant.edu', '1212 Ipsum Ave', 'China'),
(13, 'May R. Meyer', '0346 449 3662', 'et@adipiscingelit.co.uk', 'P.O. Box 306, 570 Et, Rd.', 'Kazakhstan'),
(14, 'Caldwell Q. Hopper', '0901 316 0982', 'purus.Maecenas@Donec.com', '8673 Libero Avenue', 'Dominica'),
(15, 'Karyn R. Griffin', '(020) 5373 4276', 'per.inceptos@diam.org', '452-2216 Consectetuer, Rd.', 'Honduras'),
(16, 'Kyle H. Everett', '076 4491 7505', 'eget@tellus.ca', '6801 Suspendisse Av.', 'Slovakia'),
(17, 'Isaiah O. Slater', '(01682) 49972', 'nibh.Aliquam@lectus.net', 'Ap #616-6597 Adipiscing, St.', 'Angola'),
(18, 'Bell N. Flores', '(022) 1450 2914', 'Nunc.ac@dictumeu.org', '810-1974 Lacus. Avenue', 'Peru'),
(19, 'Guy C. Good', '07530 530575', 'Nam@libero.com', 'P.O. Box 537, 5628 Ullamcorper Avenue', 'Poland'),
(20, 'Jerome N. Wyatt', '0373 667 1753', 'pede.nonummy@fringillaornareplacerat.net', '1144 Iaculis St.', 'Brunei'),
(21, 'Erasmus R. Hood', '07624 596543', 'sem.egestas.blandit@adipiscinglacusUt.net', 'P.O. Box 361, 7929 Donec Road', 'Japan'),
(22, 'Hayes B. Ellison', '056 7069 6003', 'cursus.vestibulum@netusetmalesuada.edu', 'Ap #880-5290 Ac Road', 'Panama'),
(23, 'Hyatt Y. Delgado', '0992 794 3350', 'massa@necdiam.org', 'P.O. Box 501, 1215 Feugiat Av.', 'Gibraltar'),
(24, 'Lois M. Allison', '(0111) 491 2064', 'turpis@volutpatornare.ca', '7643 Blandit Rd.', 'Sri Lanka'),
(25, 'Murphy Z. Crane', '0800 440 4370', 'nisl.sem@utipsum.net', 'P.O. Box 997, 6509 Eu St.', 'El Salvador'),
(26, 'Rogan V. Porter', '(029) 9924 3510', 'ornare.lectus@habitant.net', '482-8690 Eget Av.', 'Bolivia'),
(27, 'Ursula T. White', '0329 247 5308', 'lacus@eget.com', '308-4898 Sodales St.', 'Slovakia'),
(28, 'Imani F. Glass', '(0121) 524 0803', 'dictum@aliquam.org', 'Ap #266-4515 Sapien. St.', 'Jordan'),
(29, 'Quintessa Z. Golden', '(0118) 811 7733', 'cursus@suscipitnonummy.ca', '5184 Scelerisque St.', 'Anguilla'),
(30, 'Rowan R. Hinton', '(025) 6051 9978', 'ipsum@Nunc.co.uk', 'Ap #524-5705 Elit Rd.', 'Lebanon'),
(31, 'Jamal Y. Aguirre', '0845 46 47', 'magna@velesttempor.com', 'Ap #702-4797 Ac Ave', 'Malawi'),
(32, 'Lunea W. Neal', '0800 817 6017', 'Sed.neque@ligula.edu', 'Ap #898-615 Nunc Road', 'Martinique'),
(33, 'Kyle T. Barnett', '0500 969259', 'nec@inmolestie.org', 'P.O. Box 273, 9738 Aliquam St.', 'Saint Pierre and Miquelon'),
(34, 'Uta Q. Sanchez', '(0119) 734 1162', 'ut.pharetra.sed@quamquis.edu', 'Ap #186-3850 Ac Road', 'Uruguay'),
(35, 'Zoe B. Maddox', '07480 836510', 'erat@erosturpis.net', 'Ap #350-9901 Duis Av.', 'Kuwait'),
(36, 'Plato U. Rice', '(0111) 564 1889', 'dolor@Donec.edu', '806 Suspendisse Rd.', 'Liberia'),
(37, 'Madaline Z. Clark', '(0151) 783 9522', 'commodo.ipsum@semvitae.edu', '3443 Aliquam Rd.', 'Bonaire, Sint Eustatius and Saba'),
(38, 'Ori P. Henderson', '076 5201 2065', 'at@metus.com', '6010 Tincidunt Road', 'Moldova'),
(39, 'Yvette G. Glenn', '076 4244 0099', 'ac.tellus.Suspendisse@pedenecante.ca', 'P.O. Box 390, 5962 Lorem, Rd.', 'Bulgaria'),
(40, 'Eric P. Nolan', '0800 561170', 'id.erat@quis.co.uk', 'Ap #551-8371 Nec Avenue', 'Jersey'),
(41, 'Zahir X. Mullins', '0800 170481', 'Suspendisse@lacinia.net', 'Ap #913-6705 Consequat St.', 'Indonesia'),
(42, 'Ori U. Farrell', '(01995) 23168', 'sodales@Aeneaneget.org', '8982 Non, Avenue', 'Fiji'),
(43, 'Amaya A. Talley', '(0117) 403 1291', 'ac@Nunc.com', 'Ap #987-7113 Sed St.', 'Saint Vincent and The Grenadines'),
(44, 'Iris O. Harrington', '076 5047 2532', 'tellus@Loremipsum.ca', '193-3823 Tristique Avenue', 'Paraguay'),
(45, 'Kennedy D. Rocha', '(01823) 72623', 'nunc.nulla.vulputate@nuncullamcorpereu.ca', '9384 Adipiscing Ave', 'Zambia'),
(46, 'Reuben Y. Leonard', '056 5987 5229', 'consectetuer@egestasurnajusto.edu', 'P.O. Box 774, 9685 Diam Avenue', 'Zimbabwe'),
(47, 'Jin N. Marsh', '0800 658399', 'neque.venenatis@orciDonec.org', 'Ap #261-6832 Aenean Av.', 'Albania'),
(48, 'Acton P. Maldonado', '0800 1111', 'massa@blanditatnisi.net', 'P.O. Box 253, 8695 Vel Road', 'Nepal'),
(49, 'Suki O. Goodman', '076 4269 5092', 'Donec.consectetuer@erosnectellus.edu', '545-284 Rutrum Rd.', 'Angola'),
(50, 'Adrian N. Barlow', '0500 939685', 'lectus.ante@euismodetcommodo.net', '443-8962 Varius. St.', 'Albania'),
(51, 'Wade T. Graves', '0934 161 6266', 'enim@adipiscingelit.com', 'Ap #620-1539 Mauris Rd.', 'Tajikistan'),
(52, 'Elizabeth R. Mullins', '(011846) 80388', 'amet.consectetuer.adipiscing@nullaatsem.net', '7888 Semper Avenue', 'Ethiopia'),
(53, 'Eaton B. Walters', '07486 163868', 'vulputate.nisi.sem@vitae.net', 'P.O. Box 775, 2548 Odio Rd.', 'Falkland Islands'),
(54, 'Ishmael D. Hill', '070 6796 0145', 'Maecenas.mi.felis@natoquepenatibus.org', '385-6003 Sed St.', 'Uganda'),
(55, 'Ainsley A. Langley', '056 1717 3144', 'tellus@ante.co.uk', '3803 Metus St.', 'Serbia'),
(56, 'Wade N. Myers', '0368 576 7431', 'ac.mattis.velit@hendreritaarcu.edu', 'P.O. Box 773, 6980 Non, Av.', 'Mexico'),
(57, 'Kibo G. Reid', '076 1813 9531', 'tincidunt@metus.org', 'P.O. Box 730, 7146 In Ave', 'Norfolk Island'),
(58, 'Craig V. Horn', '070 7599 2630', 'magnis.dis@quisarcu.ca', 'Ap #882-9767 Tincidunt Rd.', 'Philippines'),
(59, 'Roary E. Blackwell', '0884 926 8671', 'in.dolor.Fusce@Utnec.co.uk', 'Ap #834-7245 Quisque Rd.', 'Spain'),
(60, 'Ina X. Solomon', '(0116) 384 0006', 'non@enimEtiam.net', 'P.O. Box 449, 9838 Egestas. Road', 'Jamaica'),
(61, 'Kevin D. Wright', '07348 223301', 'elit@ornarefacilisis.org', 'Ap #651-4892 Arcu. Ave', 'Maldives'),
(62, 'Quamar J. Ferrell', '(0112) 480 0581', 'Donec@Aliquamerat.ca', '395-1142 Cursus Avenue', 'Italy'),
(63, 'Rafael B. Garner', '(029) 7037 0360', 'Morbi@pretium.net', 'Ap #722-5518 Ante. Av.', 'Aruba'),
(64, 'Emma R. Weaver', '0500 111534', 'Cras@massaSuspendisse.net', 'Ap #689-5400 Lorem, Rd.', 'Lebanon'),
(65, 'Ifeoma W. Schmidt', '(010186) 62779', 'sodales.nisi@fringillapurus.com', 'Ap #522-8749 Pede, Rd.', 'Namibia'),
(66, 'Keegan G. Chavez', '0374 263 4983', 'In.at.pede@sitamet.edu', 'Ap #923-8091 Ultrices. St.', 'Finland'),
(67, 'Cameron W. Hinton', '(01526) 989059', 'feugiat.nec@insodales.co.uk', '177-802 Sem. Av.', 'Yemen'),
(68, 'Wade W. Valentine', '(01216) 09915', 'non@dui.co.uk', '7927 Penatibus Street', 'New Caledonia'),
(69, 'Davis K. Rosario', '0903 115 1152', 'metus.facilisis.lorem@magnaNamligula.edu', 'P.O. Box 703, 3275 Lectus Av.', 'Yemen'),
(70, 'Simon M. Cain', '(0112) 292 4563', 'mauris.aliquam.eu@enim.co.uk', 'P.O. Box 935, 6521 Posuere Street', 'Venezuela'),
(71, 'Rafael A. Clements', '(01316) 367646', 'leo@lacinia.ca', '736-9183 Et, Av.', 'Mexico'),
(72, 'Carly P. Jarvis', '055 9162 0004', 'semper@nostraper.ca', 'P.O. Box 537, 8042 Pede. Ave', 'Seychelles'),
(73, 'Otto U. Skinner', '0889 748 5368', 'nec@Crasdolordolor.com', 'P.O. Box 494, 4120 Eget, St.', 'Timor-Leste'),
(74, 'Stuart C. Lancaster', '0845 46 48', 'habitant.morbi.tristique@mauris.ca', '350 Enim. St.', 'Ecuador'),
(75, 'Nita T. Shannon', '07624 076639', 'velit.Sed.malesuada@lobortisrisus.net', 'P.O. Box 228, 2926 Torquent St.', 'New Caledonia'),
(76, 'George W. Gardner', '0500 551641', 'et.ultrices@hendreritid.com', '866-3694 Ut St.', 'El Salvador'),
(77, 'Cherokee B. Webb', '(01845) 709468', 'tellus.lorem.eu@nasceturridiculus.co.uk', 'P.O. Box 334, 9159 Vehicula Rd.', 'Tanzania'),
(78, 'Keegan K. Sims', '0345 640 5881', 'a@velesttempor.net', 'P.O. Box 615, 7952 Amet Av.', 'China'),
(79, 'Josephine J. Fisher', '(016977) 5496', 'placerat@orcilacusvestibulum.net', '7734 Diam Av.', 'Bonaire, Sint Eustatius and Saba'),
(80, 'Derek Q. Calhoun', '(0111) 395 9549', 'dolor.Quisque.tincidunt@venenatisa.net', 'P.O. Box 672, 5752 Ut, St.', 'Peru'),
(81, 'Clark R. Pitts', '(01993) 18993', 'eleifend@sitametdiam.net', 'P.O. Box 372, 5874 Eu, St.', 'Madagascar'),
(82, 'Shana F. Brock', '0364 517 4491', 'arcu.iaculis.enim@turpisegestasFusce.co.uk', '317-3919 Pede Road', 'Colombia'),
(83, 'Germane P. Bolton', '070 5929 5332', 'eget.odio@Phaselluslibero.ca', 'Ap #570-9932 Sit Road', 'Belgium'),
(84, 'Ora L. Duke', '070 0617 9989', 'aliquam@nibhPhasellusnulla.com', '439-4954 Aliquam Av.', 'Norfolk Island'),
(85, 'Ori S. Wagner', '0978 224 7540', 'Duis@nullaat.com', 'Ap #240-5210 Et, Rd.', 'CuraÃ§ao'),
(86, 'Yoshio U. Fulton', '0500 613084', 'pede.et.risus@amet.co.uk', 'P.O. Box 684, 3987 Tempor, Street', 'Burkina Faso'),
(87, 'Nichole G. Roy', '056 3708 6424', 'quam@justoProin.ca', '596-4368 Nec Rd.', 'Antigua and Barbuda'),
(88, 'Ingrid J. Sheppard', '0845 46 48', 'consectetuer@eleifend.com', 'P.O. Box 163, 2034 A St.', 'Lebanon'),
(89, 'Dieter I. Brock', '0301 058 4857', 'Integer@dolor.ca', 'Ap #157-5099 Curabitur Rd.', 'Mongolia'),
(90, 'Austin S. Harrington', '(0101) 111 3868', 'neque.sed@aliquetliberoInteger.org', '856-8675 Est Ave', 'Dominica'),
(91, 'Judah L. Hooper', '0880 147 5890', 'neque.sed@erosnectellus.co.uk', '3144 Aliquet Street', 'Gambia'),
(92, 'Fulton P. Rutledge', '055 7859 3982', 'dignissim@Phasellus.co.uk', '4607 Curabitur Road', 'Morocco'),
(93, 'Hilel Y. Freeman', '070 7302 6233', 'bibendum.fermentum@vel.co.uk', '6827 Neque. Road', 'Qatar'),
(94, 'Brynne R. Erickson', '0800 534774', 'ligula.eu@sapien.edu', 'Ap #491-4212 Lorem Road', 'Cuba'),
(95, 'Harding R. Guerra', '0930 218 5633', 'facilisis.facilisis.magna@sed.com', 'P.O. Box 421, 6892 Vulputate Avenue', 'Malawi'),
(96, 'Quin E. Dotson', '070 4439 0974', 'In.tincidunt.congue@eu.com', 'Ap #993-8211 Ante St.', 'Belarus'),
(97, 'Aimee P. Noel', '056 8284 7563', 'lectus.a@nonummyultricies.org', '8811 Fusce Road', 'Azerbaijan'),
(98, 'Illiana K. Martinez', '076 5436 1681', 'hendrerit.Donec.porttitor@incursuset.net', 'Ap #566-8238 Mauris, St.', 'Macao'),
(99, 'Wyoming B. Wilson', '(029) 1092 5974', 'ante@nibhDonecest.net', '179-7364 Semper Avenue', 'China'),
(100, 'Devin L. Burris', '0340 261 5978', 'risus.In.mi@et.com', 'P.O. Box 906, 4386 Nec, Avenue', 'Niue');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contacts`
--
ALTER TABLE `contacts`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contacts`
--
ALTER TABLE `contacts`
MODIFY `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=101;--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Table structure for table `pma_bookmark`
--

DROP TABLE IF EXISTS `pma_bookmark`;
CREATE TABLE IF NOT EXISTS `pma_bookmark` (
`id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Table structure for table `pma_column_info`
--

DROP TABLE IF EXISTS `pma_column_info`;
CREATE TABLE IF NOT EXISTS `pma_column_info` (
`id` int(5) unsigned NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_designer_coords`
--

DROP TABLE IF EXISTS `pma_designer_coords`;
CREATE TABLE IF NOT EXISTS `pma_designer_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `v` tinyint(4) DEFAULT NULL,
  `h` tinyint(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for Designer';

-- --------------------------------------------------------

--
-- Table structure for table `pma_history`
--

DROP TABLE IF EXISTS `pma_history`;
CREATE TABLE IF NOT EXISTS `pma_history` (
`id` bigint(20) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_navigationhiding`
--

DROP TABLE IF EXISTS `pma_navigationhiding`;
CREATE TABLE IF NOT EXISTS `pma_navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

--
-- Dumping data for table `pma_navigationhiding`
--

INSERT INTO `pma_navigationhiding` (`username`, `item_name`, `item_type`, `db_name`, `table_name`) VALUES
('root', 'otps', 'table', 'otp', '');

-- --------------------------------------------------------

--
-- Table structure for table `pma_pdf_pages`
--

DROP TABLE IF EXISTS `pma_pdf_pages`;
CREATE TABLE IF NOT EXISTS `pma_pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
`page_nr` int(10) unsigned NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_recent`
--

DROP TABLE IF EXISTS `pma_recent`;
CREATE TABLE IF NOT EXISTS `pma_recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data for table `pma_recent`
--

INSERT INTO `pma_recent` (`username`, `tables`) VALUES
('root', '[{"db":"academic_portal","table":"specialized"},{"db":"academic_portal","table":"teacher_course_semester"},{"db":"academic_portal","table":"staff"}]');

-- --------------------------------------------------------

--
-- Table structure for table `pma_relation`
--

DROP TABLE IF EXISTS `pma_relation`;
CREATE TABLE IF NOT EXISTS `pma_relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Table structure for table `pma_savedsearches`
--

DROP TABLE IF EXISTS `pma_savedsearches`;
CREATE TABLE IF NOT EXISTS `pma_savedsearches` (
`id` int(5) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_coords`
--

DROP TABLE IF EXISTS `pma_table_coords`;
CREATE TABLE IF NOT EXISTS `pma_table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float unsigned NOT NULL DEFAULT '0',
  `y` float unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_info`
--

DROP TABLE IF EXISTS `pma_table_info`;
CREATE TABLE IF NOT EXISTS `pma_table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_table_uiprefs`
--

DROP TABLE IF EXISTS `pma_table_uiprefs`;
CREATE TABLE IF NOT EXISTS `pma_table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

--
-- Dumping data for table `pma_table_uiprefs`
--

INSERT INTO `pma_table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('root', 'contacts', 'contacts', '[]', '2015-03-09 10:33:28'),
('root', 'academic_portal', 'courses', '{"sorted_col":"`courses`.`code` ASC"}', '2015-08-08 17:12:03'),
('root', 'academic_portal', 'class_course_teacher_semester', '[]', '2015-06-15 09:16:36'),
('root', 'academic_portal', 'classes', '{"sorted_col":"`class_id` ASC"}', '2015-08-05 16:43:51'),
('root', 'academic_portal', 'class_course_semester', '{"sorted_col":"`class_semester_id` ASC"}', '2015-08-09 10:24:17'),
('root', 'academic_portal', 'teacher_course_semester', '{"sorted_col":"`course_semester_id` ASC"}', '2015-08-09 18:23:08'),
('root', 'academic_portal', 'time_table', '{"sorted_col":"`class_course_semester_id` ASC"}', '2015-07-24 05:18:15'),
('root', 'academic_portal', 'students', '{"sorted_col":"`students`.`account` ASC"}', '2015-07-27 14:44:02'),
('root', 'academic_portal', 'class_semester', '{"sorted_col":"`semester_id` ASC"}', '2015-08-07 16:24:18');

-- --------------------------------------------------------

--
-- Table structure for table `pma_tracking`
--

DROP TABLE IF EXISTS `pma_tracking`;
CREATE TABLE IF NOT EXISTS `pma_tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) unsigned NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma_userconfig`
--

DROP TABLE IF EXISTS `pma_userconfig`;
CREATE TABLE IF NOT EXISTS `pma_userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Dumping data for table `pma_userconfig`
--

INSERT INTO `pma_userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2015-03-09 09:37:28', '{"collation_connection":"utf8mb4_general_ci"}');

-- --------------------------------------------------------

--
-- Table structure for table `pma_usergroups`
--

DROP TABLE IF EXISTS `pma_usergroups`;
CREATE TABLE IF NOT EXISTS `pma_usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Table structure for table `pma_users`
--

DROP TABLE IF EXISTS `pma_users`;
CREATE TABLE IF NOT EXISTS `pma_users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma_bookmark`
--
ALTER TABLE `pma_bookmark`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma_column_info`
--
ALTER TABLE `pma_column_info`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma_designer_coords`
--
ALTER TABLE `pma_designer_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma_history`
--
ALTER TABLE `pma_history`
 ADD PRIMARY KEY (`id`), ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma_navigationhiding`
--
ALTER TABLE `pma_navigationhiding`
 ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma_pdf_pages`
--
ALTER TABLE `pma_pdf_pages`
 ADD PRIMARY KEY (`page_nr`), ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma_recent`
--
ALTER TABLE `pma_recent`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma_relation`
--
ALTER TABLE `pma_relation`
 ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`), ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma_savedsearches`
--
ALTER TABLE `pma_savedsearches`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma_table_coords`
--
ALTER TABLE `pma_table_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma_table_info`
--
ALTER TABLE `pma_table_info`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma_table_uiprefs`
--
ALTER TABLE `pma_table_uiprefs`
 ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma_tracking`
--
ALTER TABLE `pma_tracking`
 ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma_userconfig`
--
ALTER TABLE `pma_userconfig`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma_usergroups`
--
ALTER TABLE `pma_usergroups`
 ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma_users`
--
ALTER TABLE `pma_users`
 ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pma_bookmark`
--
ALTER TABLE `pma_bookmark`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_column_info`
--
ALTER TABLE `pma_column_info`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `pma_history`
--
ALTER TABLE `pma_history`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_pdf_pages`
--
ALTER TABLE `pma_pdf_pages`
MODIFY `page_nr` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma_savedsearches`
--
ALTER TABLE `pma_savedsearches`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT;--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
--
-- Database: `webauth`
--
CREATE DATABASE IF NOT EXISTS `webauth` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;
USE `webauth`;

-- --------------------------------------------------------

--
-- Table structure for table `user_pwd`
--

DROP TABLE IF EXISTS `user_pwd`;
CREATE TABLE IF NOT EXISTS `user_pwd` (
  `name` char(30) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `pass` char(32) COLLATE latin1_general_ci NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `user_pwd`
--

INSERT INTO `user_pwd` (`name`, `pass`) VALUES
('xampp', 'wampp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_pwd`
--
ALTER TABLE `user_pwd`
 ADD PRIMARY KEY (`name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
