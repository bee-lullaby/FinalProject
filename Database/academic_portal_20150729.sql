-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2015 at 05:37 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `class_course_semester_merge`
--

INSERT INTO `class_course_semester_merge` (`class_course_semester_merge_id`, `class_course_semester_id_1`, `class_course_semester_id_2`) VALUES
(1, 321, 326);

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
(243, 243, 2, 30, NULL),
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
  `courses` varchar(1000) DEFAULT NULL,
  `capacity` int(11) NOT NULL,
  `building_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `code`, `courses`, `capacity`, `building_id`) VALUES
(50, 'P201', 'SSG101, SSC102, SSC101', 30, 4),
(51, 'P202', 'SSG101, SSC102, SSC101', 30, 4),
(52, 'P203', 'SSG101, SSC102, SSC101', 30, 4),
(53, 'P204', NULL, 30, 4),
(54, 'P205', NULL, 30, 4),
(55, 'P209', NULL, 30, 4),
(56, 'P211', NULL, 30, 4),
(57, 'P212', NULL, 30, 4),
(58, 'P215', NULL, 30, 4),
(59, 'P216', NULL, 30, 4),
(60, 'P217', NULL, 30, 4),
(61, 'P218', NULL, 30, 4),
(62, 'P219', NULL, 30, 4),
(63, 'P220', NULL, 30, 4),
(64, 'P221', NULL, 30, 4),
(65, 'P301', NULL, 30, 4),
(66, 'P302', NULL, 30, 4),
(67, 'P303', NULL, 30, 4),
(68, 'P304', NULL, 30, 4),
(69, 'P305', NULL, 30, 4),
(70, 'P306', NULL, 30, 4),
(71, 'P307', NULL, 30, 4),
(72, 'P308', NULL, 30, 4),
(73, 'P309', NULL, 30, 4),
(74, 'P310', NULL, 30, 4),
(75, 'P311', NULL, 31, 4),
(76, 'P312', NULL, 32, 4),
(77, 'P313', NULL, 30, 4),
(78, 'P314', NULL, 30, 4),
(79, 'P315', NULL, 30, 4),
(80, 'P316', NULL, 30, 4),
(81, 'P317', NULL, 30, 4),
(82, 'P318', NULL, 30, 4),
(83, 'P319', NULL, 30, 4),
(84, 'P320', NULL, 31, 4),
(85, 'P321', NULL, 30, 4),
(86, 'P411', NULL, 32, 4),
(87, 'HB/201L', NULL, 33, 3),
(88, 'HB/203L', NULL, 30, 3),
(89, 'HB/204L', NULL, 30, 3),
(90, 'HB/207L', NULL, 30, 3),
(91, 'HB/301R', NULL, 30, 3),
(92, 'HB/302R', NULL, 30, 3),
(93, 'HB/303R-305R', NULL, 70, 3),
(94, 'HB/304R', 'GDG401, WMT201, DRA301, GDS301, CAA201, PST201, AFA201, GDF101, HOA101, DRF201, VCM201, DRS101, DGT101, PST201, DRP101, VNC101', 30, 3),
(95, 'HB/307R', NULL, 30, 3),
(96, 'HB/309R', 'GDG401, WMT201, DRA301, GDS301, CAA201, PST201, AFA201, GDF101, HOA101, DRF201, VCM201, DRS101, DGT101, PST201, DRP101, VNC101', 30, 3),
(97, 'HB/202R', 'LAB101, LAB211', 30, 3);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `account`, `name`, `email`, `account_type`) VALUES
(4, 'duchmse02551', 'Hoang Minh Duc', 'duchmse02551@fpt.edu.vn', 'staff'),
(5, 'hansse02618', 'hansse02618', 'hansse02618@fpt.edu.vn', 'staff');

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
) ENGINE=InnoDB AUTO_INCREMENT=647 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(1, 'DinhNTSE04486', 'Nguyễn Thị Dinh', 'DinhNTSE04486@fpt.edu.vn', 'SE04486', 1, 1, 'NotSet', 3, NULL),
(2, 'HuynhDTSE03573', 'Đỗ Thiện Huỳnh', 'HuynhDTSE03573@fpt.edu.vn', 'SE03573', 1, 1, 'NotSet', 4, NULL),
(3, 'QuyNHSE03631', 'Nguyễn Hồng Quý', 'QuyNHSE03631@fpt.edu.vn', 'SE03631', 1, 1, 'NotSet', 4, NULL),
(4, 'TuanDASE03644', 'Đào Anh Tuấn', 'TuanDASE03644@fpt.edu.vn', 'SE03644', 1, 1, 'NotSet', 4, NULL),
(5, 'LuongNVSE03673', 'Nguyễn Văn Lượng', 'LuongNVSE03673@fpt.edu.vn', 'SE03673', 1, 1, 'NotSet', 3, NULL),
(6, 'DongNPSE03794', 'Nguyễn Phương Đông', 'DongNPSE03794@fpt.edu.vn', 'SE03794', 1, 1, 'NotSet', 4, NULL),
(7, 'TrungNDSE03802', 'Nguyễn Đức Trung', 'TrungNDSE03802@fpt.edu.vn', 'SE03802', 1, 1, 'NotSet', 4, NULL),
(8, 'NamNPSE03883', 'Nguyễn Phương Nam', 'NamNPSE03883@fpt.edu.vn', 'SE03883', 1, 1, 'NotSet', 4, NULL),
(9, 'AnhPBSE04073', 'Phạm Bảo Anh', 'AnhPBSE04073@fpt.edu.vn', 'SE04073', 1, 1, 'NotSet', 2, NULL),
(10, 'DangVHSE04121', 'Vũ Hải Đăng', 'DangVHSE04121@fpt.edu.vn', 'SE04121', 1, 1, 'NotSet', 2, NULL),
(11, 'MungDVSE03914', 'Đoàn Văn Mừng', 'MungDVSE03914@fpt.edu.vn', 'SE03914', 1, 1, 'NotSet', 3, NULL),
(12, 'DatLHSE03841', 'Lê Huy Đạt', 'DatLHSE03841@fpt.edu.vn', 'SE03841', 1, 1, 'NotSet', 4, NULL),
(13, 'AnhCVTSE03893', 'Chu Vũ Tùng Anh', 'AnhCVTSE03893@fpt.edu.vn', 'SE03893', 1, 1, 'NotSet', 4, NULL),
(14, 'AnhNTSE03679', 'Ngô Tuấn Anh', 'AnhNTSE03679@fpt.edu.vn', 'SE03679', 1, 1, 'NotSet', 3, NULL),
(15, 'SonHHSE03835', 'Hồ Hồng Sơn', 'SonHHSE03835@fpt.edu.vn', 'SE03835', 1, 1, 'NotSet', 2, NULL),
(16, 'BaoNVSE03858', 'Nguyễn Văn Bảo', 'BaoNVSE03858@fpt.edu.vn', 'SE03858', 1, 1, 'NotSet', 2, NULL),
(17, 'HungTVSE03537', 'Trần Văn Hùng', 'HungTVSE03537@fpt.edu.vn', 'SE03537', 1, 1, 'NotSet', 3, NULL),
(18, 'TungNDSE03588', 'Nguyễn Đức Tùng', 'TungNDSE03588@fpt.edu.vn', 'SE03588', 1, 1, 'NotSet', 2, NULL),
(19, 'CuongTVSE03663', 'Thân Văn Cường', 'CuongTVSE03663@fpt.edu.vn', 'SE03663', 1, 1, 'NotSet', 3, NULL),
(20, 'TuanLMSE03688', 'Lê Minh Tuấn', 'TuanLMSE03688@fpt.edu.vn', 'SE03688', 1, 1, 'NotSet', 3, NULL),
(21, 'HaiPVSE03698', 'Phạm Viết Hải', 'HaiPVSE03698@fpt.edu.vn', 'SE03698', 1, 1, 'NotSet', 2, NULL),
(22, 'TuanHDSE03730', 'Hoàng Đăng Tuân', 'TuanHDSE03730@fpt.edu.vn', 'SE03730', 1, 1, 'NotSet', 3, NULL),
(23, 'TrungVHSE03751', 'Vũ Huy Trung', 'TrungVHSE03751@fpt.edu.vn', 'SE03751', 1, 1, 'NotSet', 3, NULL),
(24, 'TrongDTSE03761', 'Đồng Tuấn Trọng', 'TrongDTSE03761@fpt.edu.vn', 'SE03761', 1, 1, 'NotSet', 2, NULL),
(25, 'DungNDSE03796', 'Nguyễn Đình Dũng', 'DungNDSE03796@fpt.edu.vn', 'SE03796', 1, 1, 'NotSet', 2, NULL),
(26, 'ThanhPTSE03828', 'Phạm Trọng Thành', 'ThanhPTSE03828@fpt.edu.vn', 'SE03828', 1, 1, 'NotSet', 3, NULL),
(27, 'AnhLVSE03908', 'Lê Việt Anh', 'AnhLVSE03908@fpt.edu.vn', 'SE03908', 1, 1, 'NotSet', 3, NULL),
(28, 'HungNTSE03913', 'Nguyễn Thế Hùng', 'HungNTSE03913@fpt.edu.vn', 'SE03913', 1, 1, 'NotSet', 2, NULL),
(29, 'TungNTSE03924', 'Nguyễn Thanh Tùng', 'TungNTSE03924@fpt.edu.vn', 'SE03924', 1, 1, 'NotSet', 3, NULL),
(30, 'TuyenPKSE03937', 'Phạm Kim Tuyến', 'TuyenPKSE03937@fpt.edu.vn', 'SE03937', 1, 1, 'NotSet', 3, NULL),
(31, 'KhoaTDSE03372', 'Trần Đăng Khoa', 'KhoaTDSE03372@fpt.edu.vn', 'SE03372', 1, 1, 'NotSet', 3, NULL),
(32, 'HieuTTSE03468', 'Trần Trung Hiếu', 'HieuTTSE03468@fpt.edu.vn', 'SE03468', 1, 1, 'NotSet', 3, NULL),
(33, 'AnhNCSE03485', 'Nguyễn Công Anh', 'AnhNCSE03485@fpt.edu.vn', 'SE03485', 1, 1, 'NotSet', 3, NULL),
(34, 'TuanTASE03554', 'Trần Anh Tuấn', 'TuanTASE03554@fpt.edu.vn', 'SE03554', 1, 1, 'NotSet', 3, NULL),
(35, 'HiepNHSE03561', 'Nguyễn Hữu Hiệp', 'HiepNHSE03561@fpt.edu.vn', 'SE03561', 1, 1, 'NotSet', 3, NULL),
(36, 'TrungNHSE03608', 'Nguyễn Hoàng Trung', 'TrungNHSE03608@fpt.edu.vn', 'SE03608', 1, 1, 'NotSet', 3, NULL),
(37, 'HoaNKSE03609', 'Nguyễn Khánh Hòa', 'HoaNKSE03609@fpt.edu.vn', 'SE03609', 1, 1, 'NotSet', 3, NULL),
(38, 'TruongNXSE03627', 'Nguyễn Xuân Trường', 'TruongNXSE03627@fpt.edu.vn', 'SE03627', 1, 1, 'NotSet', 3, NULL),
(39, 'KhanhCVSE03642', 'Chu Văn Khánh', 'KhanhCVSE03642@fpt.edu.vn', 'SE03642', 1, 1, 'NotSet', 3, NULL),
(40, 'DungPTSE03655', 'Phạm Tuấn Dũng', 'DungPTSE03655@fpt.edu.vn', 'SE03655', 1, 1, 'NotSet', 3, NULL),
(41, 'HoangTVSE03656', 'Trần Việt Hoàng', 'HoangTVSE03656@fpt.edu.vn', 'SE03656', 1, 1, 'NotSet', 3, NULL),
(42, 'AnhVNSE03674', 'Văn Ngọc Ánh', 'AnhVNSE03674@fpt.edu.vn', 'SE03674', 1, 1, 'NotSet', 3, NULL),
(43, 'BangNVSE03684', 'Nguyễn Văn Bằng', 'BangNVSE03684@fpt.edu.vn', 'SE03684', 1, 1, 'NotSet', 3, NULL),
(44, 'DuongHTNSE03716', 'Hoàng Trần Nhật Dương', 'DuongHTNSE03716@fpt.edu.vn', 'SE03716', 1, 1, 'NotSet', 3, NULL),
(45, 'DucHASE03729', 'Hồ Anh Đức', 'DucHASE03729@fpt.edu.vn', 'SE03729', 1, 1, 'NotSet', 3, NULL),
(46, 'HiepDHSE03731', 'Đặng Hoàng Hiệp', 'HiepDHSE03731@fpt.edu.vn', 'SE03731', 1, 1, 'NotSet', 3, NULL),
(47, 'AnhVMSE03758', 'Vũ Minh Ánh', 'AnhVMSE03758@fpt.edu.vn', 'SE03758', 1, 1, 'NotSet', 3, NULL),
(48, 'AnhNVSE03759', 'Nguyễn Việt Anh', 'AnhNVSE03759@fpt.edu.vn', 'SE03759', 1, 1, 'NotSet', 3, NULL),
(49, 'HaiNNSE03772', 'Nguyễn Ngọc Hải', 'HaiNNSE03772@fpt.edu.vn', 'SE03772', 1, 1, 'NotSet', 3, NULL),
(50, 'QuangNHSE03792', 'Nguyễn Huy Quang', 'QuangNHSE03792@fpt.edu.vn', 'SE03792', 1, 1, 'NotSet', 2, NULL),
(51, 'HoanLTSE03813', 'Lê Trần Hoàn', 'HoanLTSE03813@fpt.edu.vn', 'SE03813', 1, 1, 'NotSet', 3, NULL),
(52, 'TruongTVSE03815', 'Trần Văn Trường', 'TruongTVSE03815@fpt.edu.vn', 'SE03815', 1, 1, 'NotSet', 3, NULL),
(53, 'AnhNNTSE03823', 'Nguyễn Ngọc Tuấn Anh', 'AnhNNTSE03823@fpt.edu.vn', 'SE03823', 1, 1, 'NotSet', 2, NULL),
(54, 'TuanLASE03831', 'Lê Anh Tuấn', 'TuanLASE03831@fpt.edu.vn', 'SE03831', 1, 1, 'NotSet', 3, NULL),
(55, 'NamLASE03836', 'Lê Anh Nam', 'NamLASE03836@fpt.edu.vn', 'SE03836', 1, 1, 'NotSet', 3, NULL),
(56, 'SonPBSE03907', 'Phan Bảo Sơn', 'SonPBSE03907@fpt.edu.vn', 'SE03907', 1, 1, 'NotSet', 3, NULL),
(57, 'ThaiHTSE03926', 'Hoàng Tuấn Thái', 'ThaiHTSE03926@fpt.edu.vn', 'SE03926', 1, 1, 'NotSet', 3, NULL),
(58, 'VinhNVSE03927', 'Nguyễn Văn Vinh', 'VinhNVSE03927@fpt.edu.vn', 'SE03927', 1, 1, 'NotSet', 3, NULL),
(59, 'CongLDSE03929', 'Lê Duy Công', 'CongLDSE03929@fpt.edu.vn', 'SE03929', 1, 1, 'NotSet', 3, NULL),
(60, 'HanhNTSE03934', 'Nguyễn Thị Hạnh', 'HanhNTSE03934@fpt.edu.vn', 'SE03934', 1, 1, 'NotSet', 3, NULL),
(61, 'TinhNXSE03935', 'Nguyễn Xuân Tỉnh', 'TinhNXSE03935@fpt.edu.vn', 'SE03935', 1, 1, 'NotSet', 3, NULL),
(62, 'AnhDTSE03942', 'Đỗ Tuấn Anh', 'AnhDTSE03942@fpt.edu.vn', 'SE03942', 1, 1, 'NotSet', 3, NULL),
(63, 'TungVKSE02372', 'Vũ Khánh Tùng', 'TungVKSE02372@fpt.edu.vn', 'SE02372', 1, 1, 'NotSet', 2, NULL),
(64, 'TinNVSE03660', 'Nguyễn Văn Tín', 'TinNVSE03660@fpt.edu.vn', 'SE03660', 1, 1, 'NotSet', 3, NULL),
(65, 'QuanTDSE03667', 'Trần Đăng Quân', 'QuanTDSE03667@fpt.edu.vn', 'SE03667', 1, 1, 'NotSet', 2, NULL),
(66, 'NgocNVSE03390', 'Nguyễn Văn Ngọc', 'NgocNVSE03390@fpt.edu.vn', 'SE03390', 1, 1, 'NotSet', 3, NULL),
(67, 'TungNDSE03526', 'Nguyễn Đức Tùng', 'TungNDSE03526@fpt.edu.vn', 'SE03526', 1, 1, 'NotSet', 3, NULL),
(68, 'LucDVSE03546', 'Đinh Văn Lực', 'LucDVSE03546@fpt.edu.vn', 'SE03546', 1, 1, 'NotSet', 3, NULL),
(69, 'ThinhNTSE03548', 'Nguyễn Tiến Thịnh', 'ThinhNTSE03548@fpt.edu.vn', 'SE03548', 1, 1, 'NotSet', 3, NULL),
(70, 'LinhNTSE03586', 'Nguyễn Thế Linh', 'LinhNTSE03586@fpt.edu.vn', 'SE03586', 1, 1, 'NotSet', 3, NULL),
(71, 'ManhDKSE03686', 'Đào Khắc Mạnh', 'ManhDKSE03686@fpt.edu.vn', 'SE03686', 1, 1, 'NotSet', 3, NULL),
(72, 'HinhNXSE03687', 'Nguyễn Xuân Hinh', 'HinhNXSE03687@fpt.edu.vn', 'SE03687', 1, 1, 'NotSet', 3, NULL),
(73, 'GiangNTSE03695', 'Nguyễn Trường Giang', 'GiangNTSE03695@fpt.edu.vn', 'SE03695', 1, 1, 'NotSet', 3, NULL),
(74, 'QuanTHSE03733', 'Trần Hồng Quân', 'QuanTHSE03733@fpt.edu.vn', 'SE03733', 1, 1, 'NotSet', 3, NULL),
(75, 'NamPTSE04116', 'Phan Thành Nam', 'NamPTSE04116@fpt.edu.vn', 'SE04116', 1, 1, 'NotSet', 2, NULL),
(76, 'PhuongNMSE04196', 'Nguyễn Minh Phương', 'PhuongNMSE04196@fpt.edu.vn', 'SE04196', 1, 1, 'NotSet', 3, NULL),
(77, 'HaDNSE04048', 'Đặng Nhật Hạ', 'HaDNSE04048@fpt.edu.vn', 'SE04048', 1, 1, 'NotSet', 3, NULL),
(78, 'MinhNTSE04083', 'Ngô Thế Minh', 'MinhNTSE04083@fpt.edu.vn', 'SE04083', 1, 1, 'NotSet', 3, NULL),
(79, 'TuanTASE04118', 'Trần Anh Tuấn', 'TuanTASE04118@fpt.edu.vn', 'SE04118', 1, 1, 'NotSet', 3, NULL),
(80, 'DungLHSE04230', 'Lê Hoàng Dũng', 'DungLHSE04230@fpt.edu.vn', 'SE04230', 1, 1, 'NotSet', 3, NULL),
(81, 'TungVDSE04074', 'Võ Đức Tùng', 'TungVDSE04074@fpt.edu.vn', 'SE04074', 1, 1, 'NotSet', 3, NULL),
(82, 'TrungBDSE04114', 'Bùi Đức Trung', 'TrungBDSE04114@fpt.edu.vn', 'SE04114', 1, 1, 'NotSet', 3, NULL),
(83, 'ManhNTSE04004', 'Nguyễn Tiến Mạnh', 'ManhNTSE04004@fpt.edu.vn', 'SE04004', 1, 1, 'NotSet', 3, NULL),
(84, 'TuTHSE04063', 'Trần Hữu Tú', 'TuTHSE04063@fpt.edu.vn', 'SE04063', 1, 1, 'NotSet', 3, NULL),
(85, 'ManhHDSE04394', 'Hoàng Đức Mạnh', 'ManhHDSE04394@fpt.edu.vn', 'SE04394', 1, 1, 'NotSet', 3, NULL),
(86, 'HoangVVSE04088', 'Vương Viết Hoàng', 'HoangVVSE04088@fpt.edu.vn', 'SE04088', 1, 1, 'NotSet', 3, NULL),
(87, 'CuongNTSE03238', 'Nguyễn Thịnh Cường', 'CuongNTSE03238@fpt.edu.vn', 'SE03238', 1, 1, 'NotSet', 4, NULL),
(88, 'NguyenTCSE03248', 'Trần Công Nguyện', 'NguyenTCSE03248@fpt.edu.vn', 'SE03248', 1, 1, 'NotSet', 4, NULL),
(89, 'DungPASE03281', 'Phùng Anh Dũng', 'DungPASE03281@fpt.edu.vn', 'SE03281', 1, 1, 'NotSet', 3, NULL),
(90, 'LongNHSE03299', 'Nguyễn Hoàng Long', 'LongNHSE03299@fpt.edu.vn', 'SE03299', 1, 1, 'NotSet', 4, NULL),
(91, 'LamNTSE03307', 'Nguyễn Tùng Lâm', 'LamNTSE03307@fpt.edu.vn', 'SE03307', 1, 1, 'NotSet', 2, NULL),
(92, 'HoangLNSE03385', 'Lê Nhật Hoàng', 'HoangLNSE03385@fpt.edu.vn', 'SE03385', 1, 1, 'NotSet', 4, NULL),
(93, 'LinhNTLSE04216', 'Nguyễn Thị Lý Linh', 'LinhNTLSE04216@fpt.edu.vn', 'SE04216', 1, 1, 'NotSet', 4, NULL),
(94, 'AnhLTSE03448', 'Lê Tuấn Anh', 'AnhLTSE03448@fpt.edu.vn', 'SE03448', 1, 1, 'NotSet', 4, NULL),
(95, 'HoangNMSE03455', 'Nguyễn Minh Hoàng', 'HoangNMSE03455@fpt.edu.vn', 'SE03455', 1, 1, 'NotSet', 4, NULL),
(96, 'VietDXSE03456', 'Đỗ Xuân Việt', 'VietDXSE03456@fpt.edu.vn', 'SE03456', 1, 1, 'NotSet', 4, NULL),
(97, 'QuangHDSE03459', 'Hoàng Đình Quang', 'QuangHDSE03459@fpt.edu.vn', 'SE03459', 1, 1, 'NotSet', 4, NULL),
(98, 'PhuongNTMSE03461', 'Nguyễn Thị Mai Phương', 'PhuongNTMSE03461@fpt.edu.vn', 'SE03461', 1, 1, 'NotSet', 4, NULL),
(99, 'HiepBHSE03463', 'Bùi Hoàng Hiệp', 'HiepBHSE03463@fpt.edu.vn', 'SE03463', 1, 1, 'NotSet', 4, NULL),
(100, 'BachVNSE03480', 'Vũ Ngọc Bách', 'BachVNSE03480@fpt.edu.vn', 'SE03480', 1, 1, 'NotSet', 4, NULL),
(101, 'AnhPTSE03482', 'Phạm Tuấn Anh', 'AnhPTSE03482@fpt.edu.vn', 'SE03482', 1, 1, 'NotSet', 4, NULL),
(102, 'ChinhNDSE03483', 'Nguyễn Đức Chính', 'ChinhNDSE03483@fpt.edu.vn', 'SE03483', 1, 1, 'NotSet', 4, NULL),
(103, 'HaNXSE03484', 'Nguyễn Xuân Hà', 'HaNXSE03484@fpt.edu.vn', 'SE03484', 1, 1, 'NotSet', 4, NULL),
(104, 'ThanhNVSE03492', 'Nguyễn Văn Thành', 'ThanhNVSE03492@fpt.edu.vn', 'SE03492', 1, 1, 'NotSet', 4, NULL),
(105, 'DongNASE03494', 'Nguyễn Á Đông', 'DongNASE03494@fpt.edu.vn', 'SE03494', 1, 1, 'NotSet', 4, NULL),
(106, 'TungNHHSE03498', 'Nguyễn Hữu Hoàng Tùng', 'TungNHHSE03498@fpt.edu.vn', 'SE03498', 1, 1, 'NotSet', 4, NULL),
(107, 'DatNTSE03522', 'Ngô Tiến Đạt', 'DatNTSE03522@fpt.edu.vn', 'SE03522', 1, 1, 'NotSet', 4, NULL),
(108, 'ThangLDSE03529', 'Lâm Đức Thắng', 'ThangLDSE03529@fpt.edu.vn', 'SE03529', 1, 1, 'NotSet', 4, NULL),
(109, 'ManhDDSE03539', 'Đặng Đức Mạnh', 'ManhDDSE03539@fpt.edu.vn', 'SE03539', 1, 1, 'NotSet', 4, NULL),
(110, 'HoangVSE03540', 'Vũ Hoàng', 'HoangVSE03540@fpt.edu.vn', 'SE03540', 1, 1, 'NotSet', 4, NULL),
(111, 'ThuongNTHSE03542', 'Nông Thị Hoài Thương', 'ThuongNTHSE03542@fpt.edu.vn', 'SE03542', 1, 1, 'NotSet', 4, NULL),
(112, 'TruongDBSE03543', 'Dương Bá Trường', 'TruongDBSE03543@fpt.edu.vn', 'SE03543', 1, 1, 'NotSet', 4, NULL),
(113, 'TuNTSE03545', 'Nguyễn Tuấn Tú', 'TuNTSE03545@fpt.edu.vn', 'SE03545', 1, 1, 'NotSet', 3, NULL),
(114, 'PhatNHSE03551', 'Nguyễn Huy Phát', 'PhatNHSE03551@fpt.edu.vn', 'SE03551', 1, 1, 'NotSet', 4, NULL),
(115, 'LongPTSE03552', 'Phạm Tiến Long', 'LongPTSE03552@fpt.edu.vn', 'SE03552', 1, 1, 'NotSet', 4, NULL),
(116, 'SonLASE03553', 'Lê Anh Sơn', 'SonLASE03553@fpt.edu.vn', 'SE03553', 1, 1, 'NotSet', 4, NULL),
(117, 'DaiTQSE03557', 'Trịnh Quang Đại', 'DaiTQSE03557@fpt.edu.vn', 'SE03557', 1, 1, 'NotSet', 4, NULL),
(118, 'AnhTTSE03560', 'Trương Tuấn Anh', 'AnhTTSE03560@fpt.edu.vn', 'SE03560', 1, 1, 'NotSet', 4, NULL),
(119, 'SonDCSE03564', 'Đặng Công Sơn', 'SonDCSE03564@fpt.edu.vn', 'SE03564', 1, 1, 'NotSet', 4, NULL),
(120, 'DucNTSE03565', 'Nguyễn Trung Đức', 'DucNTSE03565@fpt.edu.vn', 'SE03565', 1, 1, 'NotSet', 4, NULL),
(121, 'TuanNASE03567', 'Nguyễn Anh Tuấn', 'TuanNASE03567@fpt.edu.vn', 'SE03567', 1, 1, 'NotSet', 4, NULL),
(122, 'ThuongTXSE03568', 'Trần Xuân Thưởng', 'ThuongTXSE03568@fpt.edu.vn', 'SE03568', 1, 1, 'NotSet', 2, NULL),
(123, 'LinhDQSE03570', 'Đào Quang Linh', 'LinhDQSE03570@fpt.edu.vn', 'SE03570', 1, 1, 'NotSet', 4, NULL),
(124, 'HoangNASE03574', 'Nguyễn Anh Hoàng', 'HoangNASE03574@fpt.edu.vn', 'SE03574', 1, 1, 'NotSet', 4, NULL),
(125, 'QuanTMSE03575', 'Trần Mạnh Quân', 'QuanTMSE03575@fpt.edu.vn', 'SE03575', 1, 1, 'NotSet', 4, NULL),
(126, 'VinhKMSE03576', 'Khuất Mạnh Vinh', 'VinhKMSE03576@fpt.edu.vn', 'SE03576', 1, 1, 'NotSet', 4, NULL),
(127, 'HungNVSE03577', 'Nguyễn Việt Hưng', 'HungNVSE03577@fpt.edu.vn', 'SE03577', 1, 1, 'NotSet', 4, NULL),
(128, 'AnhNPSE03583', 'Nguyễn Phan Anh', 'AnhNPSE03583@fpt.edu.vn', 'SE03583', 1, 1, 'NotSet', 4, NULL),
(129, 'QuanTMSE03585', 'Tạ Minh Quân', 'QuanTMSE03585@fpt.edu.vn', 'SE03585', 1, 1, 'NotSet', 4, NULL),
(130, 'TriBMSE03590', 'Bùi Mạnh Trí', 'TriBMSE03590@fpt.edu.vn', 'SE03590', 1, 1, 'NotSet', 4, NULL),
(131, 'TamNASE03594', 'Nguyễn Anh Tâm', 'TamNASE03594@fpt.edu.vn', 'SE03594', 1, 1, 'NotSet', 4, NULL),
(132, 'NamNVSE03599', 'Nguyễn Viết Nam', 'NamNVSE03599@fpt.edu.vn', 'SE03599', 1, 1, 'NotSet', 4, NULL),
(133, 'ThangTCSE03602', 'Trần Công Thắng', 'ThangTCSE03602@fpt.edu.vn', 'SE03602', 1, 1, 'NotSet', 3, NULL),
(134, 'AnhGTSE03603', 'Giang Tuấn Anh', 'AnhGTSE03603@fpt.edu.vn', 'SE03603', 1, 1, 'NotSet', 4, NULL),
(135, 'AnhPTSE03615', 'Phạm Tuấn Anh', 'AnhPTSE03615@fpt.edu.vn', 'SE03615', 1, 1, 'NotSet', 4, NULL),
(136, 'KhangPQSE03624', 'Phạm Quang Khang', 'KhangPQSE03624@fpt.edu.vn', 'SE03624', 1, 1, 'NotSet', 4, NULL),
(137, 'HungDXSE03628', 'Đào Xuân Hưng', 'HungDXSE03628@fpt.edu.vn', 'SE03628', 1, 1, 'NotSet', 4, NULL),
(138, 'VietPQSE03630', 'Phạm Quốc Việt', 'VietPQSE03630@fpt.edu.vn', 'SE03630', 1, 1, 'NotSet', 4, NULL),
(139, 'DucNTSE03632', 'Nguyễn Trung Đức', 'DucNTSE03632@fpt.edu.vn', 'SE03632', 1, 1, 'NotSet', 4, NULL),
(140, 'TuanNSSE03635', 'Nguyễn Sĩ Tuấn', 'TuanNSSE03635@fpt.edu.vn', 'SE03635', 1, 1, 'NotSet', 4, NULL),
(141, 'TungNTSE03636', 'Nguyễn Thanh Tùng', 'TungNTSE03636@fpt.edu.vn', 'SE03636', 1, 1, 'NotSet', 4, NULL),
(142, 'HocNNSE03646', 'Nguyễn Như Học', 'HocNNSE03646@fpt.edu.vn', 'SE03646', 1, 1, 'NotSet', 2, NULL),
(143, 'KienHTSE03661', 'Hoàng Trung Kiên', 'KienHTSE03661@fpt.edu.vn', 'SE03661', 1, 1, 'NotSet', 4, NULL),
(144, 'CuongNMSE03666', 'Nguyễn Mạnh Cường', 'CuongNMSE03666@fpt.edu.vn', 'SE03666', 1, 1, 'NotSet', 4, NULL),
(145, 'VuNSSE03669', 'Nguyễn Sơn Vũ', 'VuNSSE03669@fpt.edu.vn', 'SE03669', 1, 1, 'NotSet', 4, NULL),
(146, 'ThaiNGDSE03670', 'Nguyễn Gia Duy Thái', 'ThaiNGDSE03670@fpt.edu.vn', 'SE03670', 1, 1, 'NotSet', 4, NULL),
(147, 'HungTNSE03671', 'Trần Ngọc Hùng', 'HungTNSE03671@fpt.edu.vn', 'SE03671', 1, 1, 'NotSet', 4, NULL),
(148, 'ToanCVSE03675', 'Chu Văn Toàn', 'ToanCVSE03675@fpt.edu.vn', 'SE03675', 1, 1, 'NotSet', 4, NULL),
(149, 'QuangTMSE03677', 'Trần Minh Quang', 'QuangTMSE03677@fpt.edu.vn', 'SE03677', 1, 1, 'NotSet', 4, NULL),
(150, 'GiangNDSE03680', 'Nguyễn Đức Giang', 'GiangNDSE03680@fpt.edu.vn', 'SE03680', 1, 1, 'NotSet', 4, NULL),
(151, 'CongNCSE03681', 'Nguyễn Chí Công', 'CongNCSE03681@fpt.edu.vn', 'SE03681', 1, 1, 'NotSet', 3, NULL),
(152, 'DatBTSE03683', 'Bùi Tất Đạt', 'DatBTSE03683@fpt.edu.vn', 'SE03683', 1, 1, 'NotSet', 4, NULL),
(153, 'LongNHSE03685', 'Nguyễn Hoàng Long', 'LongNHSE03685@fpt.edu.vn', 'SE03685', 1, 1, 'NotSet', 4, NULL),
(154, 'PhongNHSE03697', 'Nguyễn Huy Phong', 'PhongNHSE03697@fpt.edu.vn', 'SE03697', 1, 1, 'NotSet', 4, NULL),
(155, 'BachNKXSE03699', 'Nguyễn Khắc Xuân Bách', 'BachNKXSE03699@fpt.edu.vn', 'SE03699', 1, 1, 'NotSet', 4, NULL),
(156, 'DucLTSE03700', 'Lưu Trung Đức', 'DucLTSE03700@fpt.edu.vn', 'SE03700', 1, 1, 'NotSet', 4, NULL),
(157, 'AnhNNTSE03702', 'Nguyễn Nghiêm Tuấn Anh', 'AnhNNTSE03702@fpt.edu.vn', 'SE03702', 1, 1, 'NotSet', 4, NULL),
(158, 'LongMHSE03705', 'Mai Hoàng Long', 'LongMHSE03705@fpt.edu.vn', 'SE03705', 1, 1, 'NotSet', 4, NULL),
(159, 'HuyTQSE03706', 'Tạ Quang Huy', 'HuyTQSE03706@fpt.edu.vn', 'SE03706', 1, 1, 'NotSet', 4, NULL),
(160, 'HaVKSE03708', 'Vương Khải Hà', 'HaVKSE03708@fpt.edu.vn', 'SE03708', 1, 1, 'NotSet', 4, NULL),
(161, 'PhuongHASE03715', 'Hoàng Anh Phương', 'PhuongHASE03715@fpt.edu.vn', 'SE03715', 1, 1, 'NotSet', 4, NULL),
(162, 'TuanHMSE03722', 'Hồ Minh Tuấn', 'TuanHMSE03722@fpt.edu.vn', 'SE03722', 1, 1, 'NotSet', 4, NULL),
(163, 'QuanLHSE03726', 'Lã Hồng Quân', 'QuanLHSE03726@fpt.edu.vn', 'SE03726', 1, 1, 'NotSet', 4, NULL),
(164, 'QuanNHSE03727', 'Nguyễn Hữu Quân', 'QuanNHSE03727@fpt.edu.vn', 'SE03727', 1, 1, 'NotSet', 3, NULL),
(165, 'DatNTSE03734', 'Nguyễn Thành Đạt', 'DatNTSE03734@fpt.edu.vn', 'SE03734', 1, 1, 'NotSet', 4, NULL),
(166, 'KienNTSE03736', 'Ngô Trung Kiên', 'KienNTSE03736@fpt.edu.vn', 'SE03736', 1, 1, 'NotSet', 4, NULL),
(167, 'TuanCASE03737', 'Cao Anh Tuấn', 'TuanCASE03737@fpt.edu.vn', 'SE03737', 1, 1, 'NotSet', 4, NULL),
(168, 'HieuDVSE03739', 'Đoàn Văn Hiếu', 'HieuDVSE03739@fpt.edu.vn', 'SE03739', 1, 1, 'NotSet', 4, NULL),
(169, 'MinhVHSE03742', 'Vũ Hoàng Minh', 'MinhVHSE03742@fpt.edu.vn', 'SE03742', 1, 1, 'NotSet', 4, NULL),
(170, 'TungNTSE03745', 'Nguyễn Tuấn Tùng', 'TungNTSE03745@fpt.edu.vn', 'SE03745', 1, 1, 'NotSet', 4, NULL),
(171, 'ThanhNNSE03750', 'Nguyễn Ngọc Thanh', 'ThanhNNSE03750@fpt.edu.vn', 'SE03750', 1, 1, 'NotSet', 3, NULL),
(172, 'KhoaPDSE03752', 'Phạm Đức Khoa', 'KhoaPDSE03752@fpt.edu.vn', 'SE03752', 1, 1, 'NotSet', 4, NULL),
(173, 'AnhLTSE03760', 'Lê Tuấn Anh', 'AnhLTSE03760@fpt.edu.vn', 'SE03760', 1, 1, 'NotSet', 4, NULL),
(174, 'PhuongPTMSE03773', 'Phạm Thị Minh Phương', 'PhuongPTMSE03773@fpt.edu.vn', 'SE03773', 1, 1, 'NotSet', 4, NULL),
(175, 'SonDHSE03775', 'Đỗ Hồng Sơn', 'SonDHSE03775@fpt.edu.vn', 'SE03775', 1, 1, 'NotSet', 4, NULL),
(176, 'QuangNDSE03776', 'Nguyễn Đăng Quang', 'QuangNDSE03776@fpt.edu.vn', 'SE03776', 1, 1, 'NotSet', 4, NULL),
(177, 'DatDHSE03781', 'Đào Huy Đạt', 'DatDHSE03781@fpt.edu.vn', 'SE03781', 1, 1, 'NotSet', 4, NULL),
(178, 'ThanhNCSE03786', 'Nguyễn Công Thành', 'ThanhNCSE03786@fpt.edu.vn', 'SE03786', 1, 1, 'NotSet', 4, NULL),
(179, 'PhucLASE03793', 'Lê Anh Phúc', 'PhucLASE03793@fpt.edu.vn', 'SE03793', 1, 1, 'NotSet', 4, NULL),
(180, 'LinhTNSE03795', 'Trần Nhật Linh', 'LinhTNSE03795@fpt.edu.vn', 'SE03795', 1, 1, 'NotSet', 4, NULL),
(181, 'LongNBSE03804', 'Nguyễn Bảo Long', 'LongNBSE03804@fpt.edu.vn', 'SE03804', 1, 1, 'NotSet', 4, NULL),
(182, 'CongHPASE03806', 'Hà Phan Anh Công', 'CongHPASE03806@fpt.edu.vn', 'SE03806', 1, 1, 'NotSet', 4, NULL),
(183, 'HieuNMSE03810', 'Nguyễn Minh Hiếu', 'HieuNMSE03810@fpt.edu.vn', 'SE03810', 1, 1, 'NotSet', 4, NULL),
(184, 'TrinhDNSE03817', 'Đỗ Ngọc Trình', 'TrinhDNSE03817@fpt.edu.vn', 'SE03817', 1, 1, 'NotSet', 4, NULL),
(185, 'LoiTDSE03822', 'Trần Đăng Lợi', 'LoiTDSE03822@fpt.edu.vn', 'SE03822', 1, 1, 'NotSet', 4, NULL),
(186, 'DoPKSE03824', 'Phạm Khắc Độ', 'DoPKSE03824@fpt.edu.vn', 'SE03824', 1, 1, 'NotSet', 4, NULL),
(187, 'CuongNCSE03825', 'Nguyễn Chí Cường', 'CuongNCSE03825@fpt.edu.vn', 'SE03825', 1, 1, 'NotSet', 4, NULL),
(188, 'AnhNDSE03826', 'Nguyễn Duy Anh', 'AnhNDSE03826@fpt.edu.vn', 'SE03826', 1, 1, 'NotSet', 3, NULL),
(189, 'QuanNHSE03830', 'Ngô Hữu Quân', 'QuanNHSE03830@fpt.edu.vn', 'SE03830', 1, 1, 'NotSet', 4, NULL),
(190, 'PhuocVVSE03837', 'Vũ Vạn Phước', 'PhuocVVSE03837@fpt.edu.vn', 'SE03837', 1, 1, 'NotSet', 4, NULL),
(191, 'ManhHTSE03844', 'Hoàng Thế Mạnh', 'ManhHTSE03844@fpt.edu.vn', 'SE03844', 1, 1, 'NotSet', 4, NULL),
(192, 'LamVTSE03846', 'Võ Thế Lâm', 'LamVTSE03846@fpt.edu.vn', 'SE03846', 1, 1, 'NotSet', 4, NULL),
(193, 'VuongTVSE03854', 'Trần Viết Vương', 'VuongTVSE03854@fpt.edu.vn', 'SE03854', 1, 1, 'NotSet', 4, NULL),
(194, 'HieuPVDSE03855', 'Phan Võ Đình Hiếu', 'HieuPVDSE03855@fpt.edu.vn', 'SE03855', 1, 1, 'NotSet', 4, NULL),
(195, 'HaoHQSE03856', 'Hồ Quang Hảo', 'HaoHQSE03856@fpt.edu.vn', 'SE03856', 1, 1, 'NotSet', 4, NULL),
(196, 'HungPPSE03862', 'Phạm Phi Hùng', 'HungPPSE03862@fpt.edu.vn', 'SE03862', 1, 1, 'NotSet', 4, NULL),
(197, 'TuyenLASE03863', 'Lê Anh Tuyên', 'TuyenLASE03863@fpt.edu.vn', 'SE03863', 1, 1, 'NotSet', 4, NULL),
(198, 'HieuNMSE03870', 'Nguyễn Mạnh Hiếu', 'HieuNMSE03870@fpt.edu.vn', 'SE03870', 1, 1, 'NotSet', 4, NULL),
(199, 'TuanTQSE03873', 'Trần Quốc Tuấn', 'TuanTQSE03873@fpt.edu.vn', 'SE03873', 1, 1, 'NotSet', 4, NULL),
(200, 'DatBVSE03875', 'Bùi Vinh Đạt', 'DatBVSE03875@fpt.edu.vn', 'SE03875', 1, 1, 'NotSet', 4, NULL),
(201, 'TrungVTSE03880', 'Vũ Trí Trung', 'TrungVTSE03880@fpt.edu.vn', 'SE03880', 1, 1, 'NotSet', 4, NULL),
(202, 'HaNTTSE03882', 'Nguyễn Thị Thu Hà', 'HaNTTSE03882@fpt.edu.vn', 'SE03882', 1, 1, 'NotSet', 4, NULL),
(203, 'QuangVNSE03887', 'Vũ Ngọc Quang', 'QuangVNSE03887@fpt.edu.vn', 'SE03887', 1, 1, 'NotSet', 4, NULL),
(204, 'ChuongHVSE03891', 'Hoàng Văn Chương', 'ChuongHVSE03891@fpt.edu.vn', 'SE03891', 1, 1, 'NotSet', 4, NULL),
(205, 'TrungNQSE03897', 'Nguyễn Quang Trung', 'TrungNQSE03897@fpt.edu.vn', 'SE03897', 1, 1, 'NotSet', 4, NULL),
(206, 'LinhVVSE03899', 'Vũ Văn Linh', 'LinhVVSE03899@fpt.edu.vn', 'SE03899', 1, 1, 'NotSet', 4, NULL),
(207, 'LucNVSE03901', 'Nguyễn Văn Lực', 'LucNVSE03901@fpt.edu.vn', 'SE03901', 1, 1, 'NotSet', 4, NULL),
(208, 'TungTTSE03909', 'Trần Thanh Tùng', 'TungTTSE03909@fpt.edu.vn', 'SE03909', 1, 1, 'NotSet', 4, NULL),
(209, 'HoangVTSE03931', 'Vũ Trần Hoàng', 'HoangVTSE03931@fpt.edu.vn', 'SE03931', 1, 1, 'NotSet', 4, NULL),
(210, 'KhoiDHSE03944', 'Đỗ Hồng Khôi', 'KhoiDHSE03944@fpt.edu.vn', 'SE03944', 1, 1, 'NotSet', 4, NULL),
(211, 'SangTMSE03273', 'Trần Minh Sáng', 'SangTMSE03273@fpt.edu.vn', 'SE03273', 1, 1, 'NotSet', 4, NULL),
(212, 'ThanhNHSE03110', 'Ngô Hữu Thành', 'ThanhNHSE03110@fpt.edu.vn', 'SE03110', 1, 1, 'NotSet', 4, NULL),
(213, 'DuyLNSE03114', 'Lê Ngọc Duy', 'DuyLNSE03114@fpt.edu.vn', 'SE03114', 1, 1, 'NotSet', 5, NULL),
(214, 'NhatLTSE03124', 'Lại Thế Nhật', 'NhatLTSE03124@fpt.edu.vn', 'SE03124', 1, 1, 'NotSet', 4, NULL),
(215, 'TruongVNSE03130', 'Vũ Ngọc Trương', 'TruongVNSE03130@fpt.edu.vn', 'SE03130', 1, 1, 'NotSet', 5, NULL),
(216, 'TuanNTSE03136', 'Nguyễn Tài Tuấn', 'TuanNTSE03136@fpt.edu.vn', 'SE03136', 1, 1, 'NotSet', 5, NULL),
(217, 'ThaiNHSE03151', 'Nguyễn Hữu Thái', 'ThaiNHSE03151@fpt.edu.vn', 'SE03151', 1, 1, 'NotSet', 5, NULL),
(218, 'TienNVSE03185', 'Nguyễn Văn Tiến', 'TienNVSE03185@fpt.edu.vn', 'SE03185', 1, 1, 'NotSet', 5, NULL),
(219, 'PhongDCSE03196', 'Đỗ Cao Phong', 'PhongDCSE03196@fpt.edu.vn', 'SE03196', 1, 1, 'NotSet', 5, NULL),
(220, 'HoangLGSE03200', 'Lê Gia Hoàng', 'HoangLGSE03200@fpt.edu.vn', 'SE03200', 1, 1, 'NotSet', 5, NULL),
(221, 'MinhTDSE03276', 'Trần Đức Minh', 'MinhTDSE03276@fpt.edu.vn', 'SE03276', 1, 1, 'NotSet', 5, NULL),
(222, 'DuongLVSE03290', 'Lê Văn Dương', 'DuongLVSE03290@fpt.edu.vn', 'SE03290', 1, 1, 'NotSet', 5, NULL),
(223, 'BachLVSE03304', 'La Văn Bách', 'BachLVSE03304@fpt.edu.vn', 'SE03304', 1, 1, 'NotSet', 5, NULL),
(224, 'TruongTHSE03310', 'Trần Hán Trường', 'TruongTHSE03310@fpt.edu.vn', 'SE03310', 1, 1, 'NotSet', 5, NULL),
(225, 'AnhLQDSE03347', 'Lê Quý Đức Anh', 'AnhLQDSE03347@fpt.edu.vn', 'SE03347', 1, 1, 'NotSet', 5, NULL),
(226, 'HuongLXSE03388', 'Lê Xuân Hướng', 'HuongLXSE03388@fpt.edu.vn', 'SE03388', 1, 1, 'NotSet', 5, NULL),
(227, 'SiDQSE03425', 'Đặng Quang Sĩ', 'SiDQSE03425@fpt.edu.vn', 'SE03425', 1, 1, 'NotSet', 5, NULL),
(228, 'ThanhPSSE03430', 'Phạm Sĩ Thanh', 'ThanhPSSE03430@fpt.edu.vn', 'SE03430', 1, 1, 'NotSet', 5, NULL),
(229, 'AnhDTSE03435', 'Đinh Tuấn Anh', 'AnhDTSE03435@fpt.edu.vn', 'SE03435', 1, 1, 'NotSet', 5, NULL),
(230, 'HaiPTSE02802', 'Phạm Thanh Hải', 'HaiPTSE02802@fpt.edu.vn', 'SE02802', 1, 1, 'NotSet', 4, NULL),
(231, 'TrinhNVSE02680', 'Nguyễn Văn Trinh', 'TrinhNVSE02680@fpt.edu.vn', 'SE02680', 1, 1, 'NotSet', 5, NULL),
(232, 'DucDVSE03458', 'Đỗ Văn Đức', 'DucDVSE03458@fpt.edu.vn', 'SE03458', 1, 1, 'NotSet', 5, NULL),
(233, 'HuyVKSE03478', 'Vũ Kim Huy', 'HuyVKSE03478@fpt.edu.vn', 'SE03478', 1, 1, 'NotSet', 5, NULL),
(234, 'AnhNQSE03490', 'Nguyễn Quang Anh', 'AnhNQSE03490@fpt.edu.vn', 'SE03490', 1, 1, 'NotSet', 5, NULL),
(235, 'NguyenVBASE03496', 'Vũ Bá Anh Nguyên', 'NguyenVBASE03496@fpt.edu.vn', 'SE03496', 1, 1, 'NotSet', 5, NULL),
(236, 'TruongTNSE03527', 'Trần Nhật Trường', 'TruongTNSE03527@fpt.edu.vn', 'SE03527', 1, 1, 'NotSet', 5, NULL),
(237, 'YenNHSE03536', 'Nguyễn Hải Yến', 'YenNHSE03536@fpt.edu.vn', 'SE03536', 1, 1, 'NotSet', 4, NULL),
(238, 'TuDTSE03559', 'Đinh Tuấn Tú', 'TuDTSE03559@fpt.edu.vn', 'SE03559', 1, 1, 'NotSet', 5, NULL),
(239, 'ChauBHHSE03571', 'Bùi Hoàng Hà Châu', 'ChauBHHSE03571@fpt.edu.vn', 'SE03571', 1, 1, 'NotSet', 5, NULL),
(240, 'MinhNLSE03580', 'Nguyễn Lê Minh', 'MinhNLSE03580@fpt.edu.vn', 'SE03580', 1, 1, 'NotSet', 5, NULL),
(241, 'AnhNHSE03584', 'Nguyễn Hoàng Anh', 'AnhNHSE03584@fpt.edu.vn', 'SE03584', 1, 1, 'NotSet', 5, NULL),
(242, 'TuDNSE03591', 'Đặng Ngọc Tú', 'TuDNSE03591@fpt.edu.vn', 'SE03591', 1, 1, 'NotSet', 5, NULL),
(243, 'MinhNHSE03596', 'Nguyễn Hữu Minh', 'MinhNHSE03596@fpt.edu.vn', 'SE03596', 1, 1, 'NotSet', 5, NULL),
(244, 'BaoPQSE03606', 'Phan Quốc Bảo', 'BaoPQSE03606@fpt.edu.vn', 'SE03606', 1, 1, 'NotSet', 5, NULL),
(245, 'ManhNDSE03610', 'Nguyễn Đức Mạnh', 'ManhNDSE03610@fpt.edu.vn', 'SE03610', 1, 1, 'NotSet', 5, NULL),
(246, 'AnhPDSE03611', 'Phạm Đức Anh', 'AnhPDSE03611@fpt.edu.vn', 'SE03611', 1, 1, 'NotSet', 5, NULL),
(247, 'ToanNVSE03619', 'Ngô Văn Toàn', 'ToanNVSE03619@fpt.edu.vn', 'SE03619', 1, 1, 'NotSet', 5, NULL),
(248, 'TungNTSE03621', 'Nguyễn Thanh Tùng', 'TungNTSE03621@fpt.edu.vn', 'SE03621', 1, 1, 'NotSet', 5, NULL),
(249, 'HungNDSE03647', 'Nguyễn Đức Hùng', 'HungNDSE03647@fpt.edu.vn', 'SE03647', 1, 1, 'NotSet', 4, NULL),
(250, 'AnhNQSE03650', 'Nguyễn Quốc Anh', 'AnhNQSE03650@fpt.edu.vn', 'SE03650', 1, 1, 'NotSet', 5, NULL),
(251, 'SonDNSE03703', 'Đoàn Ngọc Sơn', 'SonDNSE03703@fpt.edu.vn', 'SE03703', 1, 1, 'NotSet', 5, NULL),
(252, 'BachNSSE03704', 'Nguyễn Sỹ Bách', 'BachNSSE03704@fpt.edu.vn', 'SE03704', 1, 1, 'NotSet', 5, NULL),
(253, 'HuyDNQSE03714', 'Đoàn Nguyễn Quang Huy', 'HuyDNQSE03714@fpt.edu.vn', 'SE03714', 1, 1, 'NotSet', 4, NULL),
(254, 'HieuLTSE03719', 'Lâm Trung Hiếu', 'HieuLTSE03719@fpt.edu.vn', 'SE03719', 1, 1, 'NotSet', 4, NULL),
(255, 'AnhLBSE03747', 'Luyện Bảo Anh', 'AnhLBSE03747@fpt.edu.vn', 'SE03747', 1, 1, 'NotSet', 5, NULL),
(256, 'CuongVMSE03763', 'Võ Mạnh Cường', 'CuongVMSE03763@fpt.edu.vn', 'SE03763', 1, 1, 'NotSet', 4, NULL),
(257, 'HoangPMSE03769', 'Phạm Minh Hoàng', 'HoangPMSE03769@fpt.edu.vn', 'SE03769', 1, 1, 'NotSet', 5, NULL),
(258, 'HueNTSE03789', 'Nguyễn Thị Huế', 'HueNTSE03789@fpt.edu.vn', 'SE03789', 1, 1, 'NotSet', 5, NULL),
(259, 'VuCLSE03800', 'Cao Lâm Vũ', 'VuCLSE03800@fpt.edu.vn', 'SE03800', 1, 1, 'NotSet', 5, NULL),
(260, 'TuanDLSE03807', 'Đặng Lê Tuấn', 'TuanDLSE03807@fpt.edu.vn', 'SE03807', 1, 1, 'NotSet', 5, NULL),
(261, 'CuongTDSE03827', 'Trần Đức Cường', 'CuongTDSE03827@fpt.edu.vn', 'SE03827', 1, 1, 'NotSet', 5, NULL),
(262, 'HaiNMSE03832', 'Nguyễn Minh Hải', 'HaiNMSE03832@fpt.edu.vn', 'SE03832', 1, 1, 'NotSet', 5, NULL),
(263, 'AnhLHSE03838', 'Lê Hoàng Anh', 'AnhLHSE03838@fpt.edu.vn', 'SE03838', 1, 1, 'NotSet', 5, NULL),
(264, 'HaiTPTSE03859', 'Trần Phạm Triều Hải', 'HaiTPTSE03859@fpt.edu.vn', 'SE03859', 1, 1, 'NotSet', 4, NULL),
(265, 'CanhNHTSE03860', 'Nguyễn Hữu Thanh Cảnh', 'CanhNHTSE03860@fpt.edu.vn', 'SE03860', 1, 1, 'NotSet', 5, NULL),
(266, 'LongNBSE03877', 'Nguyễn Bảo Long', 'LongNBSE03877@fpt.edu.vn', 'SE03877', 1, 1, 'NotSet', 5, NULL),
(267, 'DatDMSE03884', 'Đoàn Mạnh Đạt', 'DatDMSE03884@fpt.edu.vn', 'SE03884', 1, 1, 'NotSet', 5, NULL),
(268, 'NamNVSE03888', 'Nguyễn Văn Nam', 'NamNVSE03888@fpt.edu.vn', 'SE03888', 1, 1, 'NotSet', 5, NULL),
(269, 'LamLTSE03936', 'Lê Thanh Lâm', 'LamLTSE03936@fpt.edu.vn', 'SE03936', 1, 1, 'NotSet', 5, NULL),
(270, 'GiangTDSE03127', 'Trần Đại Giang', 'GiangTDSE03127@fpt.edu.vn', 'SE03127', 1, 1, 'NotSet', 4, NULL),
(271, 'SonPMSE03298', 'Phạm Minh Sơn', 'SonPMSE03298@fpt.edu.vn', 'SE03298', 1, 1, 'NotSet', 4, NULL),
(272, 'ThanhNTSE03301', 'Nguyễn Trung Thành', 'ThanhNTSE03301@fpt.edu.vn', 'SE03301', 1, 1, 'NotSet', 5, NULL),
(273, 'HaTTSE03361', 'Trần Thị Hà', 'HaTTSE03361@fpt.edu.vn', 'SE03361', 1, 1, 'NotSet', 5, NULL),
(274, 'LongNVSE03428', 'Nguyễn Văn Long', 'LongNVSE03428@fpt.edu.vn', 'SE03428', 1, 1, 'NotSet', 5, NULL),
(275, 'TrongHDSE02889', 'Hoàng Đức Trọng', 'TrongHDSE02889@fpt.edu.vn', 'SE02889', 1, 1, 'NotSet', 4, NULL),
(276, 'TrungTCSE02306', 'Tạ Cao Trung', 'TrungTCSE02306@fpt.edu.vn', 'SE02306', 1, 1, 'NotSet', 5, NULL),
(277, 'CuongDVHSE02662', 'Đoàn Văn Hùng Cường', 'CuongDVHSE02662@fpt.edu.vn', 'SE02662', 1, 1, 'NotSet', 5, NULL),
(278, 'TuanLTSE02697', 'Lê Thanh Tuấn', 'TuanLTSE02697@fpt.edu.vn', 'SE02697', 1, 1, 'NotSet', 5, NULL),
(279, 'TuTHSE02922', 'Trần Hoàng Tú', 'TuTHSE02922@fpt.edu.vn', 'SE02922', 1, 1, 'NotSet', 5, NULL),
(280, 'ThangLTSE03240', 'Lê Tất Thắng', 'ThangLTSE03240@fpt.edu.vn', 'SE03240', 1, 1, 'NotSet', 5, NULL),
(281, 'GiangNHSE60950', 'Nguyễn Hà Giang', 'GiangNHSE60950@fpt.edu.vn', 'SE60950', 1, 1, 'NotSet', 5, NULL),
(282, 'CuongNMSE04273', 'Nguyễn Mạnh Cường', 'CuongNMSE04273@fpt.edu.vn', 'SE04273', 1, 1, 'NotSet', 2, NULL),
(283, 'BinhNNSE04190', 'Nguyễn Ngọc Bình', 'BinhNNSE04190@fpt.edu.vn', 'SE04190', 1, 1, 'NotSet', 2, NULL),
(284, 'HungLTSE04132', 'Lại Thành Hưng', 'HungLTSE04132@fpt.edu.vn', 'SE04132', 1, 1, 'NotSet', 2, NULL),
(285, 'HiepNVSE04146', 'Nguyễn Vũ Hiệp', 'HiepNVSE04146@fpt.edu.vn', 'SE04146', 1, 1, 'NotSet', 2, NULL),
(286, 'TuyenDDSE04046', 'Dương Đình Tuyên', 'TuyenDDSE04046@fpt.edu.vn', 'SE04046', 1, 1, 'NotSet', 2, NULL),
(287, 'HieuTXSE04374', 'Trịnh Xuân Hiếu', 'HieuTXSE04374@fpt.edu.vn', 'SE04374', 1, 1, 'NotSet', 2, NULL),
(288, 'HauPHSE04126', 'Phạm Hữu Hậu', 'HauPHSE04126@fpt.edu.vn', 'SE04126', 1, 1, 'NotSet', 2, NULL),
(289, 'NgocBTMSE04052', 'Bùi Thị Minh Ngọc', 'NgocBTMSE04052@fpt.edu.vn', 'SE04052', 1, 1, 'NotSet', 2, NULL),
(290, 'SonHSE04107', 'Hoàng Sơn', 'SonHSE04107@fpt.edu.vn', 'SE04107', 1, 1, 'NotSet', 2, NULL),
(291, 'HieuDCSE04338', 'Đinh Công Hiếu', 'HieuDCSE04338@fpt.edu.vn', 'SE04338', 1, 1, 'NotSet', 2, NULL),
(292, 'HungTMSE04400', 'Trịnh Minh Hùng', 'HungTMSE04400@fpt.edu.vn', 'SE04400', 1, 1, 'NotSet', 2, NULL),
(293, 'QuanDLSE04215', 'Đỗ Long Quân', 'QuanDLSE04215@fpt.edu.vn', 'SE04215', 1, 1, 'NotSet', 2, NULL),
(294, 'HungNDSE04035', 'Nguyễn Đăng Hưng', 'HungNDSE04035@fpt.edu.vn', 'SE04035', 1, 1, 'NotSet', 2, NULL),
(295, 'SonVHSE04276', 'Vũ Hoàng Sơn', 'SonVHSE04276@fpt.edu.vn', 'SE04276', 1, 1, 'NotSet', 2, NULL),
(296, 'NhungGTHSE04110', 'Giáp Thị Hồng Nhung', 'NhungGTHSE04110@fpt.edu.vn', 'SE04110', 1, 1, 'NotSet', 2, NULL),
(297, 'HienNVSE04388', 'Nguyễn Văn Hiến', 'HienNVSE04388@fpt.edu.vn', 'SE04388', 1, 1, 'NotSet', 2, NULL),
(298, 'KhanhLXSE03999', 'Lã Xuân Khánh', 'KhanhLXSE03999@fpt.edu.vn', 'SE03999', 1, 1, 'NotSet', 2, NULL),
(299, 'CuongNVSE04047', 'Nguyễn Văn Cường', 'CuongNVSE04047@fpt.edu.vn', 'SE04047', 1, 1, 'NotSet', 2, NULL),
(300, 'BaoPNSE04414', 'Phan Nguyên Bảo', 'BaoPNSE04414@fpt.edu.vn', 'SE04414', 1, 1, 'NotSet', 2, NULL),
(301, 'LongNCSE03983', 'Nguyễn Công Long', 'LongNCSE03983@fpt.edu.vn', 'SE03983', 1, 1, 'NotSet', 2, NULL),
(302, 'GiangTHSE04148', 'Trần Hoàng Giang', 'GiangTHSE04148@fpt.edu.vn', 'SE04148', 1, 1, 'NotSet', 2, NULL),
(303, 'TrangDNPSE04416', 'Đặng Nguyễn Phương Trang', 'TrangDNPSE04416@fpt.edu.vn', 'SE04416', 1, 1, 'NotSet', 2, NULL),
(304, 'ChauDNTSE04187', 'Dương Nguyên Thảo Châu', 'ChauDNTSE04187@fpt.edu.vn', 'SE04187', 1, 1, 'NotSet', 2, NULL),
(305, 'DungNTKSE03989', 'Nguyễn Thị Kim Dung', 'DungNTKSE03989@fpt.edu.vn', 'SE03989', 1, 1, 'NotSet', 2, NULL),
(306, 'HoangNLSE04303', 'Nguyễn Lê Hoàng', 'HoangNLSE04303@fpt.edu.vn', 'SE04303', 1, 1, 'NotSet', 2, NULL),
(307, 'TungLDSE04071', 'Lê Đăng Tùng', 'TungLDSE04071@fpt.edu.vn', 'SE04071', 1, 1, 'NotSet', 2, NULL),
(308, 'LanNHSE04186', 'Ngô Hoàng Lan', 'LanNHSE04186@fpt.edu.vn', 'SE04186', 1, 1, 'NotSet', 2, NULL),
(309, 'DungDCSE03691', 'Đào Công Dũng', 'DungDCSE03691@fpt.edu.vn', 'SE03691', 1, 1, 'NotSet', 2, NULL),
(310, 'KhueNDSE03287', 'Nguyễn Đoàn Khuê', 'KhueNDSE03287@fpt.edu.vn', 'SE03287', 1, 1, 'NotSet', 2, NULL),
(311, 'SonNVSE04038', 'Nguyễn Văn Sơn', 'SonNVSE04038@fpt.edu.vn', 'SE04038', 1, 1, 'NotSet', 2, NULL),
(312, 'DucDHVSE04075', 'Đào Hoàng Việt Đức', 'DucDHVSE04075@fpt.edu.vn', 'SE04075', 1, 1, 'NotSet', 2, NULL),
(313, 'ThachVNSE04094', 'Vũ Ngọc Thạch', 'ThachVNSE04094@fpt.edu.vn', 'SE04094', 1, 1, 'NotSet', 2, NULL),
(314, 'DatNTSE04096', 'Nguyễn Trọng Đạt', 'DatNTSE04096@fpt.edu.vn', 'SE04096', 1, 1, 'NotSet', 2, NULL),
(315, 'TrungNTSE04160', 'Nguyễn Tiến Trung', 'TrungNTSE04160@fpt.edu.vn', 'SE04160', 1, 1, 'NotSet', 2, NULL),
(316, 'NghiaTTSE04301', 'Trần Tuấn Nghĩa', 'NghiaTTSE04301@fpt.edu.vn', 'SE04301', 1, 1, 'NotSet', 2, NULL),
(317, 'ThinhDNSE04178', 'Đỗ Ngọc Thịnh', 'ThinhDNSE04178@fpt.edu.vn', 'SE04178', 1, 1, 'NotSet', 2, NULL),
(318, 'LongPTSE04304', 'Phạm Thanh Long', 'LongPTSE04304@fpt.edu.vn', 'SE04304', 1, 1, 'NotSet', 2, NULL),
(319, 'HanhLMSE04255', 'Lê Minh Hạnh', 'HanhLMSE04255@fpt.edu.vn', 'SE04255', 1, 1, 'NotSet', 2, NULL),
(320, 'ThuyPMSE03992', 'Phạm Minh Thúy', 'ThuyPMSE03992@fpt.edu.vn', 'SE03992', 1, 1, 'NotSet', 2, NULL),
(321, 'HoangNSE03997', 'Nguyễn Hoàng', 'HoangNSE03997@fpt.edu.vn', 'SE03997', 1, 1, 'NotSet', 2, NULL),
(322, 'DuongNVSE04356', 'Nguyễn Văn Dương', 'DuongNVSE04356@fpt.edu.vn', 'SE04356', 1, 1, 'NotSet', 2, NULL),
(323, 'QuanNMSE04141', 'Nguyễn Minh Quân', 'QuanNMSE04141@fpt.edu.vn', 'SE04141', 1, 1, 'NotSet', 2, NULL),
(324, 'AnhNNSE04387', 'Nguyễn Nam Anh', 'AnhNNSE04387@fpt.edu.vn', 'SE04387', 1, 1, 'NotSet', 2, NULL),
(325, 'TruongNXSE04362', 'Nguyễn Xuân Trường', 'TruongNXSE04362@fpt.edu.vn', 'SE04362', 1, 1, 'NotSet', 2, NULL),
(326, 'LinhLNSE04405', 'Lê Nguyên Linh', 'LinhLNSE04405@fpt.edu.vn', 'SE04405', 1, 1, 'NotSet', 2, NULL),
(327, 'TungNTSE04084', 'Nguyễn Tuấn Tùng', 'TungNTSE04084@fpt.edu.vn', 'SE04084', 1, 1, 'NotSet', 2, NULL),
(328, 'HungBNSE04268', 'Bùi Nguyên Hùng', 'HungBNSE04268@fpt.edu.vn', 'SE04268', 1, 1, 'NotSet', 2, NULL),
(329, 'DungNTTSE03851', 'Nguyễn Thị Thùy Dung', 'DungNTTSE03851@fpt.edu.vn', 'SE03851', 1, 1, 'NotSet', 2, NULL),
(330, 'BachNGSE04422', 'Nguyễn Gia Bách', 'BachNGSE04422@fpt.edu.vn', 'SE04422', 1, 1, 'NotSet', 3, NULL),
(331, 'NghiaDPSE04444', 'Đặng Phúc Nghĩa', 'NghiaDPSE04444@fpt.edu.vn', 'SE04444', 1, 1, 'NotSet', 2, NULL),
(332, 'KhanhTVSE02814', 'Trần Văn Khánh', 'KhanhTVSE02814@fpt.edu.vn', 'SE02814', 1, 1, 'NotSet', 4, NULL),
(333, 'ThangMPSE61502', 'Mai Phi Thắng', 'ThangMPSE61502@fpt.edu.vn', 'SE61502', 1, 1, 'NotSet', 3, NULL),
(334, 'LongHDSE61702', 'Hoàng Đức Long', 'LongHDSE61702@fpt.edu.vn', 'SE61702', 1, 1, 'NotSet', 5, NULL),
(335, 'Nhat HuyenTNSE61693', 'Trần Ngọc Nhật Huyền', 'Nhat HuyenTNSE61693@fpt.edu.vn', 'SE61693', 1, 1, 'NotSet', 4, NULL),
(336, 'NamLHSE61565', 'Lê Hoàng Nam', 'NamLHSE61565@fpt.edu.vn', 'SE61565', 1, 1, 'NotSet', 4, NULL),
(337, 'QuangLVSE03783', 'Lâm Việt Quang', 'QuangLVSE03783@fpt.edu.vn', 'SE03783', 1, 1, 'NotSet', 3, NULL),
(338, 'BinhNTSE03718', 'Nguyễn Thanh Bình', 'BinhNTSE03718@fpt.edu.vn', 'SE03718', 1, 1, 'NotSet', 3, NULL),
(339, 'LamPSE03555', 'Phan Lâm', 'LamPSE03555@fpt.edu.vn', 'SE03555', 1, 1, 'NotSet', 3, NULL),
(340, 'TaiNH01795', 'Nguyễn Hữu Tài', 'TaiNH01795@fpt.edu.vn', '01795', 1, 1, 'NotSet', 5, NULL),
(341, 'KhanhPDSE03812', 'Phạm Duy Khánh', 'KhanhPDSE03812@fpt.edu.vn', 'SE03812', 1, 8, 'NotSet', 1, NULL),
(342, 'HoangVMSE04349', 'Vũ Minh Hoàng', 'HoangVMSE04349@fpt.edu.vn', 'SE04349', 1, 8, 'NotSet', 1, NULL),
(343, 'TungDVSE04367', 'Đặng Văn Tùng', 'TungDVSE04367@fpt.edu.vn', 'SE04367', 1, 8, 'NotSet', 1, NULL),
(344, 'MinhDNNSE04062', 'Đỗ Nguyễn Nhật Minh', 'MinhDNNSE04062@fpt.edu.vn', 'SE04062', 1, 8, 'NotSet', 1, NULL),
(345, 'NghiaNCSE04270', 'Nguyễn Chí Nghĩa', 'NghiaNCSE04270@fpt.edu.vn', 'SE04270', 1, 8, 'NotSet', 1, NULL),
(346, 'LucTTSE04138', 'Tô Tiến Lực', 'LucTTSE04138@fpt.edu.vn', 'SE04138', 1, 8, 'NotSet', 1, NULL),
(347, 'HieuLTSE04326', 'Lê Trung Hiếu', 'HieuLTSE04326@fpt.edu.vn', 'SE04326', 1, 8, 'NotSet', 1, NULL),
(348, 'ChiNNSE62028', 'Nguyễn Ngọc Chí', 'ChiNNSE62028@fpt.edu.vn', 'SE62028', 1, 8, 'NotSet', 1, NULL),
(349, 'DucNTSE04072', 'Nguyễn Trọng Đức', 'DucNTSE04072@fpt.edu.vn', 'SE04072', 1, 8, 'NotSet', 1, NULL),
(350, 'LamHASE04171', 'Hoàng Anh Lâm', 'LamHASE04171@fpt.edu.vn', 'SE04171', 1, 8, 'NotSet', 1, NULL),
(351, 'AnhNDPSE04235', 'Ninh Đại Phước Anh', 'AnhNDPSE04235@fpt.edu.vn', 'SE04235', 1, 8, 'NotSet', 1, NULL),
(352, 'AnhHNSE61724', 'Huỳnh Nhật Anh', 'AnhHNSE61724@fpt.edu.vn', 'SE61724', 1, 7, 'NotSet', 1, NULL),
(353, 'AnhVQSE04377', 'Võ Quốc Anh', 'AnhVQSE04377@fpt.edu.vn', 'SE04377', 1, 7, 'NotSet', 1, NULL),
(354, 'LinhNXSE04363', 'Nguyễn Xuân Linh', 'LinhNXSE04363@fpt.edu.vn', 'SE04363', 1, 7, 'NotSet', 1, NULL),
(355, 'AnhDDSE04067', 'Đỗ Đức Anh', 'AnhDDSE04067@fpt.edu.vn', 'SE04067', 1, 7, 'NotSet', 1, NULL),
(356, 'DatNTSE04286', 'Nguyễn Tiến Đạt', 'DatNTSE04286@fpt.edu.vn', 'SE04286', 1, 7, 'NotSet', 1, NULL),
(357, 'VinhNTSE04274', 'Nguyễn Thành Vinh', 'VinhNTSE04274@fpt.edu.vn', 'SE04274', 1, 7, 'NotSet', 1, NULL),
(358, 'HoangNMSE03998', 'Nguyễn Minh Hoàng', 'HoangNMSE03998@fpt.edu.vn', 'SE03998', 1, 7, 'NotSet', 1, NULL),
(359, 'AnhNTSE04003', 'Nguyễn Tuấn Anh', 'AnhNTSE04003@fpt.edu.vn', 'SE04003', 1, 7, 'NotSet', 1, NULL),
(360, 'PhuNVSE04123', 'Ngô Văn Phú', 'PhuNVSE04123@fpt.edu.vn', 'SE04123', 1, 7, 'NotSet', 1, NULL),
(361, 'CongNTSE04104', 'Nguyễn Thành Công', 'CongNTSE04104@fpt.edu.vn', 'SE04104', 1, 7, 'NotSet', 1, NULL),
(362, 'AnhHKSE04034', 'Hồ Kim Anh', 'AnhHKSE04034@fpt.edu.vn', 'SE04034', 1, 7, 'NotSet', 1, NULL),
(363, 'QuyNCSE03957', 'Nguyễn Chí Quý', 'QuyNCSE03957@fpt.edu.vn', 'SE03957', 1, 7, 'NotSet', 1, NULL),
(364, 'ThangNDSE04090', 'Nguyễn Đức Thắng', 'ThangNDSE04090@fpt.edu.vn', 'SE04090', 1, 7, 'NotSet', 1, NULL),
(365, 'ThanhPDSE04309', 'Phan Duy Thành', 'ThanhPDSE04309@fpt.edu.vn', 'SE04309', 1, 7, 'NotSet', 1, NULL),
(366, 'AnhVVSE04447', 'Vũ Việt Anh', 'AnhVVSE04447@fpt.edu.vn', 'SE04447', 1, 7, 'NotSet', 1, NULL),
(367, 'HoangNTSE03960', 'Nguyễn Trọng Hoàng', 'HoangNTSE03960@fpt.edu.vn', 'SE03960', 1, 7, 'NotSet', 1, NULL),
(368, 'HaVMSE03868', 'Vũ Mạnh Hà', 'HaVMSE03868@fpt.edu.vn', 'SE03868', 1, 8, 'NotSet', 1, NULL),
(369, 'TungNXSE04275', 'Nguyễn Xuân Tùng', 'TungNXSE04275@fpt.edu.vn', 'SE04275', 1, 8, 'NotSet', 1, NULL),
(370, 'ThangNVSE04385', 'Ngụy Văn Thắng', 'ThangNVSE04385@fpt.edu.vn', 'SE04385', 1, 8, 'NotSet', 1, NULL),
(371, 'ThangTQSE04101', 'Tiêu Quang Thắng', 'ThangTQSE04101@fpt.edu.vn', 'SE04101', 1, 8, 'NotSet', 1, NULL),
(372, 'SonNHSE03996', 'Nguyễn Hồng Sơn', 'SonNHSE03996@fpt.edu.vn', 'SE03996', 1, 8, 'NotSet', 1, NULL),
(373, 'QuangCXSE04220', 'Cấn Xuân Quang', 'QuangCXSE04220@fpt.edu.vn', 'SE04220', 1, 8, 'NotSet', 1, NULL),
(374, 'LongNDSE04039', 'Nguyễn Đình Long', 'LongNDSE04039@fpt.edu.vn', 'SE04039', 1, 8, 'NotSet', 1, NULL),
(375, 'DuyNDSE04192', 'Nguyễn Đức Duy', 'DuyNDSE04192@fpt.edu.vn', 'SE04192', 1, 8, 'NotSet', 1, NULL),
(376, 'ThanhPTSE04200', 'Phùng Tuấn Thanh', 'ThanhPTSE04200@fpt.edu.vn', 'SE04200', 1, 8, 'NotSet', 1, NULL),
(377, 'QuanNASE04316', 'Nguyễn Anh Quân', 'QuanNASE04316@fpt.edu.vn', 'SE04316', 1, 8, 'NotSet', 1, NULL),
(378, 'QuangTLSE04109', 'Trần Long Quang', 'QuangTLSE04109@fpt.edu.vn', 'SE04109', 1, 8, 'NotSet', 1, NULL),
(379, 'CuongLVSE04314', 'Lê Văn Cường', 'CuongLVSE04314@fpt.edu.vn', 'SE04314', 1, 8, 'NotSet', 1, NULL),
(380, 'LongNCSE04205', 'Nguyễn Cao Long', 'LongNCSE04205@fpt.edu.vn', 'SE04205', 1, 8, 'NotSet', 1, NULL),
(381, 'CanhNDSE04042', 'Nguyễn Đức Cảnh', 'CanhNDSE04042@fpt.edu.vn', 'SE04042', 1, 8, 'NotSet', 1, NULL),
(382, 'HieuTMSE04225', 'Tăng Minh Hiếu', 'HieuTMSE04225@fpt.edu.vn', 'SE04225', 1, 8, 'NotSet', 1, NULL),
(383, 'HaHTSE04467', 'Hoàng Trọng Hà', 'HaHTSE04467@fpt.edu.vn', 'SE04467', 1, 8, 'NotSet', 1, NULL),
(384, 'VinhBDSE04008', 'Bùi Đức Vinh', 'VinhBDSE04008@fpt.edu.vn', 'SE04008', 1, 7, 'NotSet', 2, NULL),
(385, 'HuongLTSE03949', 'Lưu Thị Hưởng', 'HuongLTSE03949@fpt.edu.vn', 'SE03949', 1, 7, 'NotSet', 2, NULL),
(386, 'PhuLHDSE61898', 'Lưu Huỳnh Đức Phú', 'PhuLHDSE61898@fpt.edu.vn', 'SE61898', 1, 7, 'NotSet', 2, NULL),
(387, 'SonNVSE03984', 'Ngô Việt Sơn', 'SonNVSE03984@fpt.edu.vn', 'SE03984', 1, 7, 'NotSet', 2, NULL),
(388, 'DucLHSE04203', 'Lê Hồng Đức', 'DucLHSE04203@fpt.edu.vn', 'SE04203', 1, 7, 'NotSet', 2, NULL),
(389, 'BinhVQSE03979', 'Vũ Quang Bình', 'BinhVQSE03979@fpt.edu.vn', 'SE03979', 1, 7, 'NotSet', 2, NULL),
(390, 'AnhNTLSE04122', 'Nguyễn Thị Lan Anh', 'AnhNTLSE04122@fpt.edu.vn', 'SE04122', 1, 7, 'NotSet', 2, NULL),
(391, 'PhuNDSE04284', 'Nguyễn Danh Phú', 'PhuNDSE04284@fpt.edu.vn', 'SE04284', 1, 7, 'NotSet', 2, NULL),
(392, 'HungTXSE04334', 'Trịnh Xuân Hưng', 'HungTXSE04334@fpt.edu.vn', 'SE04334', 1, 7, 'NotSet', 2, NULL),
(393, 'NamNHSE04050', 'Nguyễn Hữu Nam', 'NamNHSE04050@fpt.edu.vn', 'SE04050', 1, 7, 'NotSet', 2, NULL),
(394, 'DatVVSE61792', 'Vòng Vĩnh Đạt', 'DatVVSE61792@fpt.edu.vn', 'SE61792', 1, 7, 'NotSet', 2, NULL),
(395, 'QuyTCSE04022', 'Tạ Chu Quý', 'QuyTCSE04022@fpt.edu.vn', 'SE04022', 1, 7, 'NotSet', 2, NULL),
(396, 'HungNHSE04359', 'Nguyễn Hoàng Hưng', 'HungNHSE04359@fpt.edu.vn', 'SE04359', 1, 7, 'NotSet', 2, NULL),
(397, 'DucNTSE04128', 'Nguyễn Tài Đức', 'DucNTSE04128@fpt.edu.vn', 'SE04128', 1, 7, 'NotSet', 2, NULL),
(398, 'AnhCHSE03808', 'Cao Hoàng Anh', 'AnhCHSE03808@fpt.edu.vn', 'SE03808', 1, 8, 'NotSet', 3, NULL),
(399, 'SonPNSE03916', 'Phạm Ngọc Sơn', 'SonPNSE03916@fpt.edu.vn', 'SE03916', 1, 8, 'NotSet', 3, NULL),
(400, 'AnhNVVSE04271', 'Ngô Vi Việt Anh', 'AnhNVVSE04271@fpt.edu.vn', 'SE04271', 1, 8, 'NotSet', 3, NULL),
(401, 'HanhNNSE03797', 'Nguyễn Ngọc Hạnh', 'HanhNNSE03797@fpt.edu.vn', 'SE03797', 1, 8, 'NotSet', 3, NULL),
(402, 'CongNTSE03805', 'Nguyễn Thành Công', 'CongNTSE03805@fpt.edu.vn', 'SE03805', 1, 7, 'NotSet', 4, NULL),
(403, 'ToanCGKSE03814', 'Chu Gia Khánh Toàn', 'ToanCGKSE03814@fpt.edu.vn', 'SE03814', 1, 7, 'NotSet', 4, NULL),
(404, 'QuangTHSE03486', 'Trần Hồng Quang', 'QuangTHSE03486@fpt.edu.vn', 'SE03486', 1, 7, 'NotSet', 4, NULL),
(405, 'HuyPQSE03487', 'Phạm Quang Huy', 'HuyPQSE03487@fpt.edu.vn', 'SE03487', 1, 7, 'NotSet', 4, NULL),
(406, 'DucLASE03549', 'Lê Anh Đức', 'DucLASE03549@fpt.edu.vn', 'SE03549', 1, 7, 'NotSet', 4, NULL),
(407, 'MinhNNSE03550', 'Nguyễn Nhật Minh', 'MinhNNSE03550@fpt.edu.vn', 'SE03550', 1, 7, 'NotSet', 4, NULL),
(408, 'LeNHSE03612', 'Nguyễn Hữu Lê', 'LeNHSE03612@fpt.edu.vn', 'SE03612', 1, 7, 'NotSet', 4, NULL),
(409, 'PhucNVSE03616', 'Nguyễn Văn Phúc', 'PhucNVSE03616@fpt.edu.vn', 'SE03616', 1, 7, 'NotSet', 4, NULL),
(410, 'LinhNQSE03645', 'Nguyễn Quang Linh', 'LinhNQSE03645@fpt.edu.vn', 'SE03645', 1, 7, 'NotSet', 4, NULL),
(411, 'HoanNTSE03654', 'Nguyễn Trọng Hoàn', 'HoanNTSE03654@fpt.edu.vn', 'SE03654', 1, 7, 'NotSet', 4, NULL),
(412, 'TuanTMSE03657', 'Trần Mạnh Tuấn', 'TuanTMSE03657@fpt.edu.vn', 'SE03657', 1, 7, 'NotSet', 4, NULL),
(413, 'HuyVDSE03665', 'Vũ Đức Huy', 'HuyVDSE03665@fpt.edu.vn', 'SE03665', 1, 7, 'NotSet', 4, NULL),
(414, 'DuyTTSE03672', 'Trần Trung Duy', 'DuyTTSE03672@fpt.edu.vn', 'SE03672', 1, 7, 'NotSet', 4, NULL),
(415, 'HangVKSE03676', 'Vũ Khánh Hằng', 'HangVKSE03676@fpt.edu.vn', 'SE03676', 1, 7, 'NotSet', 4, NULL),
(416, 'TungDXSE03682', 'Đinh Xuân Tùng', 'TungDXSE03682@fpt.edu.vn', 'SE03682', 1, 7, 'NotSet', 4, NULL),
(417, 'ThaoNDSE03717', 'Ngọ Đức Thảo', 'ThaoNDSE03717@fpt.edu.vn', 'SE03717', 1, 7, 'NotSet', 4, NULL),
(418, 'AnhNTSE03748', 'Nguyễn Thế Anh', 'AnhNTSE03748@fpt.edu.vn', 'SE03748', 1, 7, 'NotSet', 4, NULL),
(419, 'PhuHMSE03757', 'Hồ Minh Phú', 'PhuHMSE03757@fpt.edu.vn', 'SE03757', 1, 7, 'NotSet', 4, NULL),
(420, 'HieuNTSE03782', 'Nguyễn Trung Hiếu', 'HieuNTSE03782@fpt.edu.vn', 'SE03782', 1, 7, 'NotSet', 4, NULL),
(421, 'CuongCHSE03788', 'Chu Hoàng Cường', 'CuongCHSE03788@fpt.edu.vn', 'SE03788', 1, 7, 'NotSet', 4, NULL),
(422, 'ToanTVSE03872', 'Trần Văn Toản', 'ToanTVSE03872@fpt.edu.vn', 'SE03872', 1, 7, 'NotSet', 4, NULL),
(423, 'MyVTSE03878', 'Vũ Thị My', 'MyVTSE03878@fpt.edu.vn', 'SE03878', 1, 7, 'NotSet', 4, NULL),
(424, 'DuyDDSE03881', 'Đào Đức Duy', 'DuyDDSE03881@fpt.edu.vn', 'SE03881', 1, 7, 'NotSet', 4, NULL),
(425, 'NghiaNDSE03915', 'Nguyễn Đức Nghĩa', 'NghiaNDSE03915@fpt.edu.vn', 'SE03915', 1, 7, 'NotSet', 4, NULL),
(426, 'LuanNTSE03925', 'Nguyễn Thành Luân', 'LuanNTSE03925@fpt.edu.vn', 'SE03925', 1, 7, 'NotSet', 4, NULL),
(427, 'TruongNMSE03938', 'Nguyễn Mạnh Trường', 'TruongNMSE03938@fpt.edu.vn', 'SE03938', 1, 7, 'NotSet', 4, NULL),
(428, 'DucNASE03952', 'Ngọ Anh Đức', 'DucNASE03952@fpt.edu.vn', 'SE03952', 1, 7, 'NotSet', 4, NULL),
(429, 'AnhLDSE03953', 'Lê Duy Anh', 'AnhLDSE03953@fpt.edu.vn', 'SE03953', 1, 7, 'NotSet', 4, NULL),
(430, 'AnhDTSE03956', 'Đinh Tuấn Anh', 'AnhDTSE03956@fpt.edu.vn', 'SE03956', 1, 7, 'NotSet', 4, NULL),
(431, 'ThanhPTSE61729', 'Phạm Tiến Thành', 'ThanhPTSE61729@fpt.edu.vn', 'SE61729', 1, 7, 'NotSet', 4, NULL),
(432, 'HuyVTSE61529', 'Võ Thanh Huy', 'HuyVTSE61529@fpt.edu.vn', 'SE61529', 1, 7, 'NotSet', 4, NULL),
(433, 'TrungNMQSE61433', 'Nguyễn Minh Quốc Trung', 'TrungNMQSE61433@fpt.edu.vn', 'SE61433', 1, 7, 'NotSet', 4, NULL),
(434, 'KhoaPASE61403', 'Phạm Anh Khoa', 'KhoaPASE61403@fpt.edu.vn', 'SE61403', 1, 7, 'NotSet', 4, NULL),
(435, 'TrungHTSE61581', 'Huỳnh Thành Trung', 'TrungHTSE61581@fpt.edu.vn', 'SE61581', 1, 7, 'NotSet', 4, NULL),
(436, 'QuanNHSE61560', 'Nguyễn Hoàng Quân', 'QuanNHSE61560@fpt.edu.vn', 'SE61560', 1, 7, 'NotSet', 4, NULL),
(437, 'SinhDBTSE61674', 'Đinh Bá Thi Sinh', 'SinhDBTSE61674@fpt.edu.vn', 'SE61674', 1, 7, 'NotSet', 4, NULL),
(438, 'ThaoPTSE03601', 'Phạm Thu Thảo', 'ThaoPTSE03601@fpt.edu.vn', 'SE03601', 1, 7, 'NotSet', 4, NULL),
(439, 'HieuKNSE03605', 'Kiều Ngọc Hiệu', 'HieuKNSE03605@fpt.edu.vn', 'SE03605', 1, 7, 'NotSet', 4, NULL),
(440, 'NhatBVSE03740', 'Bùi Văn Nhật', 'NhatBVSE03740@fpt.edu.vn', 'SE03740', 1, 7, 'NotSet', 4, NULL),
(441, 'NguyenLSE03803', 'Lê Nguyên', 'NguyenLSE03803@fpt.edu.vn', 'SE03803', 1, 7, 'NotSet', 4, NULL),
(442, 'PhongDDSE03906', 'Đinh Duy Phong', 'PhongDDSE03906@fpt.edu.vn', 'SE03906', 1, 7, 'NotSet', 4, NULL),
(443, 'HungVNSE03969', 'Vũ Ngọc Hưng', 'HungVNSE03969@fpt.edu.vn', 'SE03969', 1, 7, 'NotSet', 5, NULL),
(444, 'KhanhPBSE03453', 'Phạm Bảo Khánh', 'KhanhPBSE03453@fpt.edu.vn', 'SE03453', 1, 7, 'NotSet', 5, NULL),
(445, 'TungTTSE03495', 'Trần Thanh Tùng', 'TungTTSE03495@fpt.edu.vn', 'SE03495', 1, 7, 'NotSet', 5, NULL),
(446, 'ThanhNMSE03534', 'Nguyễn Minh Thành', 'ThanhNMSE03534@fpt.edu.vn', 'SE03534', 1, 7, 'NotSet', 5, NULL),
(447, 'HieuTTSE03538', 'Trần Trung Hiếu', 'HieuTTSE03538@fpt.edu.vn', 'SE03538', 1, 7, 'NotSet', 5, NULL),
(448, 'TriLMSE03613', 'Lưu Minh Trí', 'TriLMSE03613@fpt.edu.vn', 'SE03613', 1, 7, 'NotSet', 5, NULL),
(449, 'LinhDBSE03640', 'Đoàn Bá Linh', 'LinhDBSE03640@fpt.edu.vn', 'SE03640', 1, 7, 'NotSet', 5, NULL),
(450, 'MinhHHSE03643', 'Hoàng Hải Minh', 'MinhHHSE03643@fpt.edu.vn', 'SE03643', 1, 7, 'NotSet', 5, NULL),
(451, 'AnhLDSE03662', 'Lê Đức Anh', 'AnhLDSE03662@fpt.edu.vn', 'SE03662', 1, 7, 'NotSet', 5, NULL),
(452, 'DuyDNSE03721', 'Đặng Ngọc Duy', 'DuyDNSE03721@fpt.edu.vn', 'SE03721', 1, 7, 'NotSet', 5, NULL),
(453, 'AnhHTSE03756', 'Hoàng Tuấn Anh', 'AnhHTSE03756@fpt.edu.vn', 'SE03756', 1, 7, 'NotSet', 5, NULL),
(454, 'AnhHDSE03777', 'Hoàng Đức Anh', 'AnhHDSE03777@fpt.edu.vn', 'SE03777', 1, 7, 'NotSet', 5, NULL),
(455, 'DungLTSE03784', 'Lê Trung Dũng', 'DungLTSE03784@fpt.edu.vn', 'SE03784', 1, 7, 'NotSet', 5, NULL),
(456, 'DucTQSE03819', 'Tạ Quang Đức', 'DucTQSE03819@fpt.edu.vn', 'SE03819', 1, 7, 'NotSet', 5, NULL),
(457, 'ToanPVNSE03821', 'Phạm Vũ Nhật Toàn', 'ToanPVNSE03821@fpt.edu.vn', 'SE03821', 1, 7, 'NotSet', 5, NULL),
(458, 'HoangVMSE03833', 'Vũ Minh  Hoàng', 'HoangVMSE03833@fpt.edu.vn', 'SE03833', 1, 7, 'NotSet', 5, NULL),
(459, 'VinhNQSE03886', 'Nguyễn Quang Vinh', 'VinhNQSE03886@fpt.edu.vn', 'SE03886', 1, 7, 'NotSet', 5, NULL),
(460, 'HaVHSE03912', 'Vũ Hồng Hà', 'HaVHSE03912@fpt.edu.vn', 'SE03912', 1, 7, 'NotSet', 5, NULL);
INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `detail_specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(461, 'HongNTSE03940', 'Ngô Thu Hồng', 'HongNTSE03940@fpt.edu.vn', 'SE03940', 1, 7, 'NotSet', 5, NULL),
(462, 'SuongPTSSE61497', 'Phạm Thị Sương Sương', 'SuongPTSSE61497@fpt.edu.vn', 'SE61497', 1, 7, 'NotSet', 5, NULL),
(463, 'MinhPASE61477', 'Phạm Anh Minh', 'MinhPASE61477@fpt.edu.vn', 'SE61477', 1, 7, 'NotSet', 5, NULL),
(464, 'DuyDTTSE61484', 'Đào Trần Tuấn Duy', 'DuyDTTSE61484@fpt.edu.vn', 'SE61484', 1, 7, 'NotSet', 5, NULL),
(465, 'TruongPMSE02605', 'Phan Mạnh Trường', 'TruongPMSE02605@fpt.edu.vn', 'SE02605', 1, 7, 'NotSet', 5, NULL),
(466, 'DucDVSE04420', 'Đỗ Văn Đức', 'DucDVSE04420@fpt.edu.vn', 'SE04420', 1, 1, 'NotSet', 1, NULL),
(467, 'HoangNKSE04300', 'Nguyễn Khắc Hoàng', 'HoangNKSE04300@fpt.edu.vn', 'SE04300', 1, 1, 'NotSet', 1, NULL),
(468, 'NamDVSE04360', 'Đàm Văn Nam', 'NamDVSE04360@fpt.edu.vn', 'SE04360', 1, 1, 'NotSet', 1, NULL),
(469, 'AnhNTSE03288', 'Ngô Tuấn Anh', 'AnhNTSE03288@fpt.edu.vn', 'SE03288', 1, 1, 'NotSet', 1, NULL),
(470, 'CongNNSE03402', 'Nguyễn Như Công', 'CongNNSE03402@fpt.edu.vn', 'SE03402', 1, 1, 'NotSet', 1, NULL),
(471, 'DungNVSE04017', 'Nguyễn Văn Dũng', 'DungNVSE04017@fpt.edu.vn', 'SE04017', 1, 1, 'NotSet', 1, NULL),
(472, 'NamNHSE04018', 'Nguyễn Hoài Nam', 'NamNHSE04018@fpt.edu.vn', 'SE04018', 1, 1, 'NotSet', 1, NULL),
(473, 'HauTTSE04044', 'Trần Thị Hậu', 'HauTTSE04044@fpt.edu.vn', 'SE04044', 1, 1, 'NotSet', 1, NULL),
(474, 'QuyetDVSE04265', 'Đỗ Văn Quyết', 'QuyetDVSE04265@fpt.edu.vn', 'SE04265', 1, 1, 'NotSet', 1, NULL),
(475, 'AnhLTSE04277', 'Lê Thế Anh', 'AnhLTSE04277@fpt.edu.vn', 'SE04277', 1, 1, 'NotSet', 1, NULL),
(476, 'PhuLQSE04278', 'Lê Quang Phú', 'PhuLQSE04278@fpt.edu.vn', 'SE04278', 1, 1, 'NotSet', 1, NULL),
(477, 'PhuNXSE04318', 'Nguyễn Xuân Phú', 'PhuNXSE04318@fpt.edu.vn', 'SE04318', 1, 1, 'NotSet', 1, NULL),
(478, 'TuPVSE03471', 'Phạm Văn Tú', 'TuPVSE03471@fpt.edu.vn', 'SE03471', 1, 1, 'NotSet', 1, NULL),
(479, 'NhatNDSE03653', 'Nguyễn Đức Nhật', 'NhatNDSE03653@fpt.edu.vn', 'SE03653', 1, 1, 'NotSet', 1, NULL),
(480, 'NgocTDSE03898', 'Từ Đức Ngọc', 'NgocTDSE03898@fpt.edu.vn', 'SE03898', 1, 1, 'NotSet', 1, NULL),
(481, 'CongNTSE03790', 'Nguyễn Tiến Công', 'CongNTSE03790@fpt.edu.vn', 'SE03790', 1, 1, 'NotSet', 1, NULL),
(482, 'TuPASE03994', 'Phan Anh Tú', 'TuPASE03994@fpt.edu.vn', 'SE03994', 1, 1, 'NotSet', 1, NULL),
(483, 'TienPNSE04024', 'Phan Ngọc Tiến', 'TienPNSE04024@fpt.edu.vn', 'SE04024', 1, 1, 'NotSet', 1, NULL),
(484, 'KhanhLNSE04056', 'Lâm Ngọc Khánh', 'KhanhLNSE04056@fpt.edu.vn', 'SE04056', 1, 1, 'NotSet', 1, NULL),
(485, 'ThiNTSE04059', 'Nguyễn Tiến Thi', 'ThiNTSE04059@fpt.edu.vn', 'SE04059', 1, 1, 'NotSet', 1, NULL),
(486, 'VuongNVSE04060', 'Nguyễn Văn Vượng', 'VuongNVSE04060@fpt.edu.vn', 'SE04060', 1, 1, 'NotSet', 1, NULL),
(487, 'DungNDSE04105', 'Nguyễn Duy Dũng', 'DungNDSE04105@fpt.edu.vn', 'SE04105', 1, 1, 'NotSet', 1, NULL),
(488, 'ThangTVSE04129', 'Trần Việt Thắng', 'ThangTVSE04129@fpt.edu.vn', 'SE04129', 1, 1, 'NotSet', 1, NULL),
(489, 'ChienTDSE04142', 'Tạ Đình Chiến', 'ChienTDSE04142@fpt.edu.vn', 'SE04142', 1, 1, 'NotSet', 1, NULL),
(490, 'TrungNHHSE04279', 'Nguyễn Hữu Hoàng Trung', 'TrungNHHSE04279@fpt.edu.vn', 'SE04279', 1, 1, 'NotSet', 1, NULL),
(491, 'GiangLTSE04307', 'Lương Trường Giang', 'GiangLTSE04307@fpt.edu.vn', 'SE04307', 1, 1, 'NotSet', 1, NULL),
(492, 'QuynhLTSE04240', 'Lê Thị Quỳnh', 'QuynhLTSE04240@fpt.edu.vn', 'SE04240', 1, 1, 'NotSet', 1, NULL),
(493, 'ThangNMSE04322', 'Nguyễn Minh Thắng', 'ThangNMSE04322@fpt.edu.vn', 'SE04322', 1, 1, 'NotSet', 1, NULL),
(494, 'TuyenNNSE04335', 'Ngô Ngọc Tuyên', 'TuyenNNSE04335@fpt.edu.vn', 'SE04335', 1, 1, 'NotSet', 1, NULL),
(495, 'BacNTSE04111', 'Nguyễn Tuấn Bắc', 'BacNTSE04111@fpt.edu.vn', 'SE04111', 1, 1, 'NotSet', 1, NULL),
(496, 'DuyAKSE04298', 'Ấu Khương Duy', 'DuyAKSE04298@fpt.edu.vn', 'SE04298', 1, 1, 'NotSet', 1, NULL),
(497, 'ThangLKDSE04320', 'Lê Khắc Đức Thắng', 'ThangLKDSE04320@fpt.edu.vn', 'SE04320', 1, 1, 'NotSet', 1, NULL),
(498, 'YenNTHSE04366', 'Nguyễn Thị Hải Yến', 'YenNTHSE04366@fpt.edu.vn', 'SE04366', 1, 1, 'NotSet', 1, NULL),
(499, 'NamLTSE04267', 'Lưu Thành Nam', 'NamLTSE04267@fpt.edu.vn', 'SE04267', 1, 1, 'NotSet', 1, NULL),
(500, 'ThanhNCSE04005', 'Nguyễn Chí Thành', 'ThanhNCSE04005@fpt.edu.vn', 'SE04005', 1, 1, 'NotSet', 1, NULL),
(501, 'HungBQSE04029', 'Bùi Quang Hưng', 'HungBQSE04029@fpt.edu.vn', 'SE04029', 1, 1, 'NotSet', 1, NULL),
(502, 'DungNASE04066', 'Nguyễn Anh Dũng', 'DungNASE04066@fpt.edu.vn', 'SE04066', 1, 1, 'NotSet', 1, NULL),
(503, 'SonTTSE04026', 'Trần Thái Sơn', 'SonTTSE04026@fpt.edu.vn', 'SE04026', 1, 1, 'NotSet', 1, NULL),
(504, 'TrungNHSE04291', 'Nguyễn Hữu Trung', 'TrungNHSE04291@fpt.edu.vn', 'SE04291', 1, 1, 'NotSet', 1, NULL),
(505, 'PhongNHSE04112', 'Nguyễn Hà Phong', 'PhongNHSE04112@fpt.edu.vn', 'SE04112', 1, 1, 'NotSet', 1, NULL),
(506, 'AnhBVSE04380', 'Bùi Việt Anh', 'AnhBVSE04380@fpt.edu.vn', 'SE04380', 1, 1, 'NotSet', 1, NULL),
(507, 'HungHDSE04010', 'Hoàng Đức Hùng', 'HungHDSE04010@fpt.edu.vn', 'SE04010', 1, 1, 'NotSet', 1, NULL),
(508, 'PhatPXSE04208', 'Phan Xuân Phát', 'PhatPXSE04208@fpt.edu.vn', 'SE04208', 1, 1, 'NotSet', 1, NULL),
(509, 'QuocTTSE04253', 'Trần Trọng Quốc', 'QuocTTSE04253@fpt.edu.vn', 'SE04253', 1, 1, 'NotSet', 1, NULL),
(510, 'DatNQSE04036', 'Nguyễn Quốc Đạt', 'DatNQSE04036@fpt.edu.vn', 'SE04036', 1, 1, 'NotSet', 1, NULL),
(511, 'HauLVSE04158', 'Len Văn Hậu', 'HauLVSE04158@fpt.edu.vn', 'SE04158', 1, 1, 'NotSet', 1, NULL),
(512, 'TuLASE04241', 'Lê Anh Tú', 'TuLASE04241@fpt.edu.vn', 'SE04241', 1, 1, 'NotSet', 1, NULL),
(513, 'ManhDDSE04244', 'Đinh Duy Mạnh', 'ManhDDSE04244@fpt.edu.vn', 'SE04244', 1, 1, 'NotSet', 1, NULL),
(514, 'ThangNTSE04263', 'Nguyễn Toàn Thắng', 'ThangNTSE04263@fpt.edu.vn', 'SE04263', 1, 1, 'NotSet', 1, NULL),
(515, 'VinhPTSE04106', 'Phạm Trọng Vinh', 'VinhPTSE04106@fpt.edu.vn', 'SE04106', 1, 1, 'NotSet', 1, NULL),
(516, 'ThanhVQSE04172', 'Vương Quốc Thành', 'ThanhVQSE04172@fpt.edu.vn', 'SE04172', 1, 1, 'NotSet', 1, NULL),
(517, 'DungPTSE04212', 'Phạm Tiến Dũng', 'DungPTSE04212@fpt.edu.vn', 'SE04212', 1, 1, 'NotSet', 1, NULL),
(518, 'NamBHSE04266', 'Bùi Hoàng Nam', 'NamBHSE04266@fpt.edu.vn', 'SE04266', 1, 1, 'NotSet', 1, NULL),
(519, 'TrungNNSE04407', 'Nguyễn Ngọc Trung', 'TrungNNSE04407@fpt.edu.vn', 'SE04407', 1, 1, 'NotSet', 1, NULL),
(520, 'DatNVSE04070', 'Nguyễn Văn Đạt', 'DatNVSE04070@fpt.edu.vn', 'SE04070', 1, 1, 'NotSet', 1, NULL),
(521, 'TuPTSE04117', 'Phan Tuấn Tú', 'TuPTSE04117@fpt.edu.vn', 'SE04117', 1, 1, 'NotSet', 1, NULL),
(522, 'BonVTSE04174', 'Vũ Thành Bôn', 'BonVTSE04174@fpt.edu.vn', 'SE04174', 1, 1, 'NotSet', 1, NULL),
(523, 'DuNVSE04312', 'Nguyễn Văn Du', 'DuNVSE04312@fpt.edu.vn', 'SE04312', 1, 1, 'NotSet', 1, NULL),
(524, 'PhuongBVSE04368', 'Bùi Việt Phương', 'PhuongBVSE04368@fpt.edu.vn', 'SE04368', 1, 1, 'NotSet', 1, NULL),
(525, 'PhuongVTSE04392', 'Vũ Thị Phương', 'PhuongVTSE04392@fpt.edu.vn', 'SE04392', 1, 1, 'NotSet', 1, NULL),
(526, 'HungNVSE04023', 'Nguyễn Văn Hưng', 'HungNVSE04023@fpt.edu.vn', 'SE04023', 1, 1, 'NotSet', 1, NULL),
(527, 'CongNVSE04236', 'Nguyễn Văn Công', 'CongNVSE04236@fpt.edu.vn', 'SE04236', 1, 1, 'NotSet', 1, NULL),
(528, 'MinhLQSE04336', 'Lê Quang Minh', 'MinhLQSE04336@fpt.edu.vn', 'SE04336', 1, 1, 'NotSet', 1, NULL),
(529, 'KienNTSE04344', 'Nguyễn Trung Kiên', 'KienNTSE04344@fpt.edu.vn', 'SE04344', 1, 1, 'NotSet', 1, NULL),
(530, 'ThuyNTNSE04351', 'Nguyễn Thị Ngọc Thủy', 'ThuyNTNSE04351@fpt.edu.vn', 'SE04351', 1, 1, 'NotSet', 1, NULL),
(531, 'YenNTHSE04411', 'Nguyễn Thị Hải Yến', 'YenNTHSE04411@fpt.edu.vn', 'SE04411', 1, 1, 'NotSet', 1, NULL),
(532, 'ThangTMSE04173', 'Trần Mạnh Thắng', 'ThangTMSE04173@fpt.edu.vn', 'SE04173', 1, 1, 'NotSet', 1, NULL),
(533, 'QuynhNHSE04030', 'Nguyễn Hữu Quỳnh', 'QuynhNHSE04030@fpt.edu.vn', 'SE04030', 1, 1, 'NotSet', 1, NULL),
(534, 'HoangDDSE04370', 'Đỗ Đình Hoàng', 'HoangDDSE04370@fpt.edu.vn', 'SE04370', 1, 1, 'NotSet', 1, NULL),
(535, 'NhungDHSE04065', 'Đỗ Hồng Nhung', 'NhungDHSE04065@fpt.edu.vn', 'SE04065', 1, 1, 'NotSet', 1, NULL),
(536, 'TuanDMSE04002', 'Đào Mạnh Tuấn', 'TuanDMSE04002@fpt.edu.vn', 'SE04002', 1, 1, 'NotSet', 1, NULL),
(537, 'TungDSSE04019', 'Đồng Sơn Tùng', 'TungDSSE04019@fpt.edu.vn', 'SE04019', 1, 1, 'NotSet', 1, NULL),
(538, 'ThaoDTPSE04341', 'Đào Thị Phương Thảo', 'ThaoDTPSE04341@fpt.edu.vn', 'SE04341', 1, 1, 'NotSet', 1, NULL),
(539, 'TungNXSE04170', 'Nguyễn Xuân Tùng', 'TungNXSE04170@fpt.edu.vn', 'SE04170', 1, 1, 'NotSet', 1, NULL),
(540, 'TuanNQSE04330', 'Nguyễn Quý Tuấn', 'TuanNQSE04330@fpt.edu.vn', 'SE04330', 1, 1, 'NotSet', 1, NULL),
(541, 'ThanhLHCSE04164', 'Lê Huy Công Thành', 'ThanhLHCSE04164@fpt.edu.vn', 'SE04164', 1, 1, 'NotSet', 1, NULL),
(542, 'TayLQSE04184', 'Lê Quang Tây', 'TayLQSE04184@fpt.edu.vn', 'SE04184', 1, 1, 'NotSet', 1, NULL),
(543, 'MinhHVSE04081', 'Hoàng Văn Minh', 'MinhHVSE04081@fpt.edu.vn', 'SE04081', 1, 1, 'NotSet', 1, NULL),
(544, 'HoanNNSE04151', 'Nguyễn Nhật Hoàn', 'HoanNNSE04151@fpt.edu.vn', 'SE04151', 1, 1, 'NotSet', 1, NULL),
(545, 'ThaoNTSE04311', 'Nguyễn Thị Thảo', 'ThaoNTSE04311@fpt.edu.vn', 'SE04311', 1, 1, 'NotSet', 1, NULL),
(546, 'ToaiNTSE04189', 'Nguyễn Thanh Toại', 'ToaiNTSE04189@fpt.edu.vn', 'SE04189', 1, 1, 'NotSet', 1, NULL),
(547, 'MinhPCSE04098', 'Phạm Công Minh', 'MinhPCSE04098@fpt.edu.vn', 'SE04098', 1, 1, 'NotSet', 1, NULL),
(548, 'GiangNTSE04376', 'Nguyễn Thị Giang', 'GiangNTSE04376@fpt.edu.vn', 'SE04376', 1, 1, 'NotSet', 1, NULL),
(549, 'HangHTHSE04246', 'Hoàng Thị Hồng Hằng', 'HangHTHSE04246@fpt.edu.vn', 'SE04246', 1, 1, 'NotSet', 1, NULL),
(550, 'DanDDSE04237', 'Dương Đức Dân', 'DanDDSE04237@fpt.edu.vn', 'SE04237', 1, 1, 'NotSet', 1, NULL),
(551, 'TrungHPSE04281', 'Hoàng Phan Trung', 'TrungHPSE04281@fpt.edu.vn', 'SE04281', 1, 1, 'NotSet', 1, NULL),
(552, 'TienTMSE03980', 'Trần Mạnh Tiến', 'TienTMSE03980@fpt.edu.vn', 'SE03980', 1, 1, 'NotSet', 1, NULL),
(553, 'LinhVTMSE04165', 'Vũ Thị Mỹ Linh', 'LinhVTMSE04165@fpt.edu.vn', 'SE04165', 1, 1, 'NotSet', 1, NULL),
(554, 'TienNHSE04167', 'Nguyễn Hữu Tiến', 'TienNHSE04167@fpt.edu.vn', 'SE04167', 1, 1, 'NotSet', 1, NULL),
(555, 'ThinhAVSE04295', 'Âu Văn Thịnh', 'ThinhAVSE04295@fpt.edu.vn', 'SE04295', 1, 1, 'NotSet', 1, NULL),
(556, 'HoangNXSE03995', 'Nguyễn Xuân Hoàng', 'HoangNXSE03995@fpt.edu.vn', 'SE03995', 1, 1, 'NotSet', 1, NULL),
(557, 'AnhPTSE04328', 'Phạm Tuấn Anh', 'AnhPTSE04328@fpt.edu.vn', 'SE04328', 1, 1, 'NotSet', 1, NULL),
(558, 'HieuNQSE04209', 'Nguyễn Quang Hiếu', 'HieuNQSE04209@fpt.edu.vn', 'SE04209', 1, 1, 'NotSet', 1, NULL),
(559, 'HieuNMSE04319', 'Nguyễn Minh Hiếu', 'HieuNMSE04319@fpt.edu.vn', 'SE04319', 1, 1, 'NotSet', 1, NULL),
(560, 'HuyNLQSE04182', 'Nguyễn Lê Quang Huy', 'HuyNLQSE04182@fpt.edu.vn', 'SE04182', 1, 1, 'NotSet', 1, NULL),
(561, 'LuatNTSE04140', 'Nguyễn Trường Luật', 'LuatNTSE04140@fpt.edu.vn', 'SE04140', 1, 1, 'NotSet', 1, NULL),
(562, 'TanNVSE03778', 'Nguyễn Văn Tân', 'TanNVSE03778@fpt.edu.vn', 'SE03778', 1, 1, 'NotSet', 1, NULL),
(563, 'TungVSSE04113', 'Vũ Sỹ Tùng', 'TungVSSE04113@fpt.edu.vn', 'SE04113', 1, 1, 'NotSet', 1, NULL),
(564, 'DucLBTSE04211', 'Lê Bá Trung Đức', 'DucLBTSE04211@fpt.edu.vn', 'SE04211', 1, 1, 'NotSet', 1, NULL),
(565, 'TuMGSE04012', 'Mai Gia Tú', 'TuMGSE04012@fpt.edu.vn', 'SE04012', 1, 1, 'NotSet', 1, NULL),
(566, 'DatBTSE04115', 'Bùi Tiến Đạt', 'DatBTSE04115@fpt.edu.vn', 'SE04115', 1, 1, 'NotSet', 1, NULL),
(567, 'DungVQSE04406', 'Vũ Quang Dũng', 'DungVQSE04406@fpt.edu.vn', 'SE04406', 1, 1, 'NotSet', 1, NULL),
(568, 'MinhNNSE04119', 'Nguyễn Ngọc Minh', 'MinhNNSE04119@fpt.edu.vn', 'SE04119', 1, 1, 'NotSet', 1, NULL),
(569, 'SonNKSE04290', 'Nguyễn Khánh Sơn', 'SonNKSE04290@fpt.edu.vn', 'SE04290', 1, 1, 'NotSet', 1, NULL),
(570, 'CuongDVSE03978', 'Đặng Văn Cương', 'CuongDVSE03978@fpt.edu.vn', 'SE03978', 1, 1, 'NotSet', 1, NULL),
(571, 'NhatDTSE03990', 'Dương Tuấn Nhật', 'NhatDTSE03990@fpt.edu.vn', 'SE03990', 1, 1, 'NotSet', 1, NULL),
(572, 'TrinhPTSE04213', 'Phí Thị Trinh', 'TrinhPTSE04213@fpt.edu.vn', 'SE04213', 1, 1, 'NotSet', 1, NULL),
(573, 'ThuanDBSE04389', 'Đỗ Bình Thuận', 'ThuanDBSE04389@fpt.edu.vn', 'SE04389', 1, 1, 'NotSet', 1, NULL),
(574, 'TuNVSE04009', 'Nguyễn Văn Tú', 'TuNVSE04009@fpt.edu.vn', 'SE04009', 1, 1, 'NotSet', 1, NULL),
(575, 'TuDVSE04020', 'Dương Văn Tú', 'TuDVSE04020@fpt.edu.vn', 'SE04020', 1, 1, 'NotSet', 1, NULL),
(576, 'HuyLVSE03982', 'Lê Văn Huy', 'HuyLVSE03982@fpt.edu.vn', 'SE03982', 1, 1, 'NotSet', 1, NULL),
(577, 'DoanCVSE04054', 'Cấn Việt Đoàn', 'DoanCVSE04054@fpt.edu.vn', 'SE04054', 1, 1, 'NotSet', 1, NULL),
(578, 'HungHPSE04137', 'Hoàng Phi Hùng', 'HungHPSE04137@fpt.edu.vn', 'SE04137', 1, 1, 'NotSet', 1, NULL),
(579, 'QuanTMSE04347', 'Tạ Minh Quân', 'QuanTMSE04347@fpt.edu.vn', 'SE04347', 1, 1, 'NotSet', 1, NULL),
(580, 'TungNTSE04120', 'Nguyễn Thanh Tùng', 'TungNTSE04120@fpt.edu.vn', 'SE04120', 1, 1, 'NotSet', 1, NULL),
(581, 'DuyNDSE04204', 'Nguyễn Đức Duy', 'DuyNDSE04204@fpt.edu.vn', 'SE04204', 1, 1, 'NotSet', 1, NULL),
(582, 'HieuVDSE04025', 'Vũ Đức Hiếu', 'HieuVDSE04025@fpt.edu.vn', 'SE04025', 1, 1, 'NotSet', 1, NULL),
(583, 'NgocNHSE04027', 'Nguyễn Hồng Ngọc', 'NgocNHSE04027@fpt.edu.vn', 'SE04027', 1, 1, 'NotSet', 1, NULL),
(584, 'AnhDVSE04154', 'Dương Việt Anh', 'AnhDVSE04154@fpt.edu.vn', 'SE04154', 1, 1, 'NotSet', 1, NULL),
(585, 'LamNTSE04379', 'Ngô Tú Lâm', 'LamNTSE04379@fpt.edu.vn', 'SE04379', 1, 1, 'NotSet', 1, NULL),
(586, 'DatPTSE04037', 'Phạm Tiến Đạt', 'DatPTSE04037@fpt.edu.vn', 'SE04037', 1, 1, 'NotSet', 1, NULL),
(587, 'HungNVSE04053', 'Nguyễn Việt Hùng', 'HungNVSE04053@fpt.edu.vn', 'SE04053', 1, 1, 'NotSet', 1, NULL),
(588, 'NamLTSE04085', 'Lã Trung Nam', 'NamLTSE04085@fpt.edu.vn', 'SE04085', 1, 1, 'NotSet', 1, NULL),
(589, 'DatLTSE04086', 'Lê Tuấn Đạt', 'DatLTSE04086@fpt.edu.vn', 'SE04086', 1, 1, 'NotSet', 1, NULL),
(590, 'ThanhPKSE04131', 'Phùng Khắc Thành', 'ThanhPKSE04131@fpt.edu.vn', 'SE04131', 1, 1, 'NotSet', 1, NULL),
(591, 'LongNHSE04143', 'Nguyễn Hoàng Long', 'LongNHSE04143@fpt.edu.vn', 'SE04143', 1, 1, 'NotSet', 1, NULL),
(592, 'KyBHSE04210', 'Bùi Hoàng Kỳ', 'KyBHSE04210@fpt.edu.vn', 'SE04210', 1, 1, 'NotSet', 1, NULL),
(593, 'AnhKHSE04323', 'Kiều Hoàng Anh', 'AnhKHSE04323@fpt.edu.vn', 'SE04323', 1, 1, 'NotSet', 1, NULL),
(594, 'HaiTNSE04386', 'Triệu Nam Hải', 'HaiTNSE04386@fpt.edu.vn', 'SE04386', 1, 1, 'NotSet', 1, NULL),
(595, 'KhoiNDSE04028', 'Nguyễn Đăng Khôi', 'KhoiNDSE04028@fpt.edu.vn', 'SE04028', 1, 1, 'NotSet', 1, NULL),
(596, 'BachNVSE04221', 'Nguyễn Viết Bách', 'BachNVSE04221@fpt.edu.vn', 'SE04221', 1, 1, 'NotSet', 1, NULL),
(597, 'BinhPTSE04222', 'Phùng Thanh Bình', 'BinhPTSE04222@fpt.edu.vn', 'SE04222', 1, 1, 'NotSet', 1, NULL),
(598, 'LamPTSE04354', 'Phạm Tùng Lâm', 'LamPTSE04354@fpt.edu.vn', 'SE04354', 1, 1, 'NotSet', 1, NULL),
(599, 'QuangTMSE04402', 'Trần Minh Quang', 'QuangTMSE04402@fpt.edu.vn', 'SE04402', 1, 1, 'NotSet', 1, NULL),
(600, 'HaoDHSE04064', 'Đặng Hòa Hảo', 'HaoDHSE04064@fpt.edu.vn', 'SE04064', 1, 1, 'NotSet', 1, NULL),
(601, 'TuongDVSE04317', 'Đỗ Vĩnh Tưởng', 'TuongDVSE04317@fpt.edu.vn', 'SE04317', 1, 1, 'NotSet', 1, NULL),
(602, 'QuynhDTTSE04133', 'Đỗ Thị Thu Quỳnh', 'QuynhDTTSE04133@fpt.edu.vn', 'SE04133', 1, 1, 'NotSet', 1, NULL),
(603, 'KhoaTNSE04180', 'Trần Nguyên Khoa', 'KhoaTNSE04180@fpt.edu.vn', 'SE04180', 1, 1, 'NotSet', 1, NULL),
(604, 'LamNPTSE04049', 'Nghiêm Phú Tùng Lâm', 'LamNPTSE04049@fpt.edu.vn', 'SE04049', 1, 1, 'NotSet', 1, NULL),
(605, 'DatNQSE04108', 'Nguyễn Quốc Đạt', 'DatNQSE04108@fpt.edu.vn', 'SE04108', 1, 1, 'NotSet', 1, NULL),
(606, 'BinhNVSE04381', 'Nguyễn Vũ Bình', 'BinhNVSE04381@fpt.edu.vn', 'SE04381', 1, 1, 'NotSet', 1, NULL),
(607, 'CongNDSE04261', 'Nguyễn Đình Công', 'CongNDSE04261@fpt.edu.vn', 'SE04261', 1, 1, 'NotSet', 1, NULL),
(608, 'DucNVSE04287', 'Nguyễn Văn Đức', 'DucNVSE04287@fpt.edu.vn', 'SE04287', 1, 1, 'NotSet', 1, NULL),
(609, 'HaHPSE04181', 'Huỳnh Phương Hà', 'HaHPSE04181@fpt.edu.vn', 'SE04181', 1, 1, 'NotSet', 1, NULL),
(610, 'DungNQSE04355', 'Nguyễn Quốc Dũng', 'DungNQSE04355@fpt.edu.vn', 'SE04355', 1, 1, 'NotSet', 1, NULL),
(611, 'TrungNSSE04051', 'Nguyễn Sỹ Trung', 'TrungNSSE04051@fpt.edu.vn', 'SE04051', 1, 1, 'NotSet', 1, NULL),
(612, 'KhanhNXSE04280', 'Nguyễn Xuân Khánh', 'KhanhNXSE04280@fpt.edu.vn', 'SE04280', 1, 1, 'NotSet', 1, NULL),
(613, 'HieuNTSE04238', 'Nguyễn Trung Hiếu', 'HieuNTSE04238@fpt.edu.vn', 'SE04238', 1, 1, 'NotSet', 1, NULL),
(614, 'KienNDSE04193', 'Nguyễn Đức Kiên', 'KienNDSE04193@fpt.edu.vn', 'SE04193', 1, 1, 'NotSet', 1, NULL),
(615, 'DuyLCSE04194', 'Lưu Cao Duy', 'DuyLCSE04194@fpt.edu.vn', 'SE04194', 1, 1, 'NotSet', 1, NULL),
(616, 'SonTNSE04223', 'Trần Nguyên Sơn', 'SonTNSE04223@fpt.edu.vn', 'SE04223', 1, 1, 'NotSet', 1, NULL),
(617, 'TuanLVSE04092', 'Lê Văn Tuấn', 'TuanLVSE04092@fpt.edu.vn', 'SE04092', 1, 1, 'NotSet', 1, NULL),
(618, 'DatPTSE04332', 'Phạm Tiến Đạt', 'DatPTSE04332@fpt.edu.vn', 'SE04332', 1, 1, 'NotSet', 1, NULL),
(619, 'LanNTMSE04069', 'Nguyễn Thị Mai Lan', 'LanNTMSE04069@fpt.edu.vn', 'SE04069', 1, 1, 'NotSet', 1, NULL),
(620, 'HuyNGSE03985', 'Nguyễn Gia Huy', 'HuyNGSE03985@fpt.edu.vn', 'SE03985', 1, 1, 'NotSet', 1, NULL),
(621, 'AnhDNSE04260', 'Dương Nhật Anh', 'AnhDNSE04260@fpt.edu.vn', 'SE04260', 1, 1, 'NotSet', 1, NULL),
(622, 'VuNHSE04013', 'Nguyễn Hải Vũ', 'VuNHSE04013@fpt.edu.vn', 'SE04013', 1, 1, 'NotSet', 1, NULL),
(623, 'MinhHVCSE04183', 'Hồ Viết Công Minh', 'MinhHVCSE04183@fpt.edu.vn', 'SE04183', 1, 1, 'NotSet', 1, NULL),
(624, 'TamNTSE04247', 'Nguyễn Thanh Tâm', 'TamNTSE04247@fpt.edu.vn', 'SE04247', 1, 1, 'NotSet', 1, NULL),
(625, 'KienNKSE04150', 'Nguyễn Khắc Kiên', 'KienNKSE04150@fpt.edu.vn', 'SE04150', 1, 1, 'NotSet', 1, NULL),
(626, 'DucPMSE04093', 'Phan Minh Đức', 'DucPMSE04093@fpt.edu.vn', 'SE04093', 1, 1, 'NotSet', 1, NULL),
(627, 'ThinhLCSE04248', 'Lê Công Thịnh', 'ThinhLCSE04248@fpt.edu.vn', 'SE04248', 1, 1, 'NotSet', 1, NULL),
(628, 'TungLTSE03911', 'Lê Trọng Tùng', 'TungLTSE03911@fpt.edu.vn', 'SE03911', 1, 1, 'NotSet', 1, NULL),
(629, 'TienPMSE03947', 'Phạm Minh Tiến', 'TienPMSE03947@fpt.edu.vn', 'SE03947', 1, 1, 'NotSet', 1, NULL),
(630, 'QuanNHSE04103', 'Nguyễn Hà Quân', 'QuanNHSE04103@fpt.edu.vn', 'SE04103', 1, 1, 'NotSet', 1, NULL),
(631, 'LamNNSE04185', 'Ngô Ngọc Lâm', 'LamNNSE04185@fpt.edu.vn', 'SE04185', 1, 1, 'NotSet', 1, NULL),
(632, 'HungUDSE04421', 'Uông Đông Hưng', 'HungUDSE04421@fpt.edu.vn', 'SE04421', 1, 1, 'NotSet', 1, NULL),
(633, 'HungVHSE04429', 'Vũ Hoàng Hưng', 'HungVHSE04429@fpt.edu.vn', 'SE04429', 1, 1, 'NotSet', 1, NULL),
(634, 'TanDDSE04433', 'Đỗ Duy Tân', 'TanDDSE04433@fpt.edu.vn', 'SE04433', 1, 1, 'NotSet', 1, NULL),
(635, 'SonTHSE04439', 'Trần Hữu Sơn', 'SonTHSE04439@fpt.edu.vn', 'SE04439', 1, 1, 'NotSet', 1, NULL),
(636, 'ThangNDSE04441', 'Nguyễn Đại Thắng', 'ThangNDSE04441@fpt.edu.vn', 'SE04441', 1, 1, 'NotSet', 1, NULL),
(637, 'LeTNSE04454', 'Trương Nhật Lệ', 'LeTNSE04454@fpt.edu.vn', 'SE04454', 1, 1, 'NotSet', 1, NULL),
(638, 'LongTTSE04476', 'Trần Thái Long', 'LongTTSE04476@fpt.edu.vn', 'SE04476', 1, 1, 'NotSet', 1, NULL),
(639, 'UyenPTTSE04479', 'Phan Thị Tú Uyên', 'UyenPTTSE04479@fpt.edu.vn', 'SE04479', 1, 1, 'NotSet', 1, NULL),
(640, 'ToanNVSE03701', 'Nguyễn Văn Toản', 'ToanNVSE03701@fpt.edu.vn', 'SE03701', 1, 1, 'NotSet', 1, NULL),
(641, 'ThuanHVSE03172', 'Hoàng Văn Thuận', 'ThuanHVSE03172@fpt.edu.vn', 'SE03172', 1, 1, 'NotSet', 1, NULL),
(642, 'MaiPDSE04409', 'Phạm Đình Mai', 'MaiPDSE04409@fpt.edu.vn', 'SE04409', 1, 1, 'NotSet', 1, NULL),
(643, 'ThaiTTSE04258', 'Trần Thế Thái', 'ThaiTTSE04258@fpt.edu.vn', 'SE04258', 1, 1, 'NotSet', 1, NULL),
(644, 'TungNDSE03988', 'Nguyễn Đông Tùng', 'TungNDSE03988@fpt.edu.vn', 'SE03988', 1, 1, 'NotSet', 1, NULL),
(645, 'TungLHSE02890', 'Lưu Huy Tùng', 'TungLHSE02890@fpt.edu.vn', 'SE02890', 1, 1, 'NotSet', 5, NULL),
(646, 'HaiLXSE03744', 'Lê Xuân Hải', 'HaiLXSE03744@fpt.edu.vn', 'SE03744', 1, 1, 'NotSet', 5, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `time_table`
--

INSERT INTO `time_table` (`time_table_id`, `class_course_semester_id`, `date`, `slot`, `room_id`, `teacher_semester_id`) VALUES
(33, 323, '2015-09-08', 2, NULL, NULL),
(34, 328, '2015-09-08', 2, NULL, NULL),
(35, 321, '2015-09-07', 1, 64, 41),
(36, 326, '2015-09-07', 1, 64, 41),
(37, 331, '2015-09-07', 1, NULL, NULL);

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
MODIFY `building_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=111;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=456;
--
-- AUTO_INCREMENT for table `class_course_semester_merge`
--
ALTER TABLE `class_course_semester_merge`
MODIFY `class_course_semester_merge_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
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
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=108;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=277;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=277;
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
MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=98;
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
MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=647;
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
MODIFY `student_course_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=73;
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
-- AUTO_INCREMENT for table `time_table`
--
ALTER TABLE `time_table`
MODIFY `time_table_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
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
