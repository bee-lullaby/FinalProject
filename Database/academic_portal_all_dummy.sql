-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2015 at 09:50 PM
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
DROP DATABASE IF EXISTS academic_portal;
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
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `code`, `type`, `specialized_id`, `course_id`, `batch`, `number`) VALUES
(177, 'IS0901', 'Specialized', 2, NULL, 9, 1),
(178, 'IS0401', 'Specialized', 2, NULL, 4, 1),
(179, 'SE0402', 'Specialized', 1, NULL, 4, 2),
(180, 'CHN111.1', 'Course', NULL, 5, NULL, 1),
(182, 'SE0102', 'Specialized', 1, NULL, 1, 2),
(184, 'SE0201', 'Specialized', 1, NULL, 2, 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_semester`
--

INSERT INTO `class_semester` (`class_semester_id`, `class_id`, `semester_id`, `semester`, `students`) VALUES
(1, 177, 1, 3, 0),
(3, 178, 1, 6, 0),
(4, 180, 2, 1, 0),
(6, 177, 2, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
`course_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `code`, `name`, `department_id`) VALUES
(1, 'ACC101', 'Accounting Principles', NULL),
(2, 'ACC302', 'Managerial Accouting', NULL),
(3, 'ACC305', 'Financial Statement Analysis', NULL),
(4, 'BKG304', 'Bank Lending', NULL),
(5, 'CHN111', 'Chinese Elementary 1', NULL),
(6, 'CHN122', 'Chinees Elementary 2', NULL),
(7, 'CHN132', 'Chinese Elementary 3', NULL),
(8, 'ECO111', 'Basic Micro Economics', NULL),
(9, 'ECO121', 'Basic Macro Economics', NULL),
(10, 'ETR401', 'Entrepreneurship', NULL),
(11, 'FIM301', 'Valuation and Financial Modeling', NULL),
(12, 'FIN201', 'Monetary Economics and Global Economy', NULL),
(13, 'FIN202', 'Corporate Finance', NULL),
(14, 'FIN301', 'Finanical Market & Institutions', NULL),
(15, 'FIN303', 'Advanced Corporate Finance', NULL),
(16, 'FIN402', 'Derivatives', NULL),
(17, 'LAW101', 'Business Law Fundamentals', NULL),
(18, 'MGT101', 'Introduction to Management', NULL),
(19, 'MKT101', 'Marketing Principles', NULL),
(20, 'MKT201', 'Consumer Behavior', NULL),
(21, 'MKT202', 'Service Marketing Management', NULL),
(22, 'MKT301', 'Marketing Research', NULL),
(23, 'MKT304', 'Integrated Marketing Communications', NULL),
(24, 'OBE101', 'Organization Behavior', NULL),
(25, 'SSC101', 'Business Communication for BA', NULL),
(26, 'SSG101', 'Working in Group for BA', NULL),
(27, 'SSM201', 'Management Skills', NULL),
(28, 'CSD201', 'Data Structures and Algorithms', NULL),
(29, 'CSD202', 'Data structures and Algorithms (C++)', NULL),
(30, 'CSD301', 'Advanced Algorithms', NULL),
(31, 'CSI101', 'Introduction to Computing', NULL),
(32, 'CSI102', 'Introduction to Computing for Business', NULL),
(33, 'DBD301', 'Advanced Database', NULL),
(34, 'DBI202', 'Introduction to Database', NULL),
(35, 'NWC202', 'Networking', NULL),
(36, 'OSG202', 'Operating Systems', NULL),
(37, 'PRE201', 'Excel Programming', NULL),
(38, 'PRF192', 'Programming fundamentals using C', NULL),
(39, 'PRJ101', 'Core Java', NULL),
(40, 'PRJ201', 'Advanced Java', NULL),
(41, 'PRN292', 'C# and .NET', NULL),
(42, 'PRO001', 'Learning to Program with Alice', NULL),
(43, 'PRO191', 'Object Oriented Programming', NULL),
(44, 'ENI201', 'Business English - Pre-Intermediate', NULL),
(45, 'ENI301', 'Business English - Intermediate', NULL),
(46, 'ENI401', 'Business English - Upper-Intermediate', NULL),
(47, 'ENL111', 'Academic English 1', NULL),
(48, 'ENL112', 'Advanced Academic English', NULL),
(49, 'ENM201', 'Pre-Intermediate Business English', NULL),
(50, 'ENM301', 'Intermediate Business English', NULL),
(51, 'ENM401', 'Upper intermediate Business English', NULL),
(52, 'ENT102', 'Top Notch 1', NULL),
(53, 'ENT103', 'Top Notch 1 + Fundamentals', NULL),
(54, 'ENT202', 'Top Notch 2', NULL),
(55, 'ENT302', 'Top Notch 3', NULL),
(56, 'ENT401', 'English 5 - Summit 1 from 2012', NULL),
(57, 'ENT501', 'Summit 2 from Block 3-4 Fall 2011', NULL),
(58, 'AET101', 'Aesthetic', NULL),
(59, 'AFA201', 'Human Anatory for Artis', NULL),
(60, 'DRF201', 'Drawing - Figure drawing', NULL),
(61, 'DRP101', 'Drawing - Plaster Statue, Portrait', NULL),
(62, 'DRS101', 'Drawing - Form, Still-life', NULL),
(63, 'GDF101', 'Fundamental of Graphic Design', NULL),
(64, 'HOA101', 'Art History', NULL),
(65, 'HOD101', 'Design History', NULL),
(66, 'VCM201', 'Visual Communication', NULL),
(67, 'AIL301', 'Machine Learning', NULL),
(68, 'CEA201', 'Computer Organization and Architecture', NULL),
(69, 'CEC201', 'Circuits and Signals', NULL),
(70, 'CGG201', 'Computer Graphics', NULL),
(71, 'DGT201', 'Digital Fundamentals', NULL),
(72, 'DGT301', 'Digital Signal Processing', NULL),
(73, 'ECM391', 'Wireless Mobile Communications', NULL),
(74, 'ECS211', 'Communications Systems 1', NULL),
(75, 'EEA221', 'Electronics 2', NULL),
(76, 'ESH201', 'Embedded System Hardware', NULL),
(77, 'ESS301', 'Embedded Software Development', NULL),
(78, 'ISC301', 'E-Commerce', NULL),
(79, 'ISM301', 'ERP Fundamentals', NULL),
(80, 'OFC311', 'Optical Communications', NULL),
(81, 'PRM391', 'Mobile Programming', NULL),
(82, 'PRX301', 'Advanced XML', NULL),
(83, 'SCI201', 'Information Security', NULL),
(84, 'SYB301', 'Start your business', NULL),
(85, 'JIT301', 'Information Technology Japanese', NULL),
(86, 'JPS112', 'Japanese Elementery 1', NULL),
(87, 'JPS122', 'Japanese 2', NULL),
(88, 'JPS132', 'Japanese 3', NULL),
(89, 'JPS142', 'Japanese 4', NULL),
(90, 'JPS152', 'Japanese 5', NULL),
(91, 'JPS212', 'Japanese Intermediate 1', NULL),
(92, 'MAA101', 'Advanced Mathematics 2', NULL),
(93, 'MAC101', 'Advanced Mathematics 1', NULL),
(94, 'MAC102', 'Advanced Mathematics for Business', NULL),
(95, 'MAD111', 'Discrete Mathematics 1', NULL),
(96, 'MAD121', 'Discrete Mathematics 2', NULL),
(97, 'MAN201', 'Numerical Methods', NULL),
(98, 'MAS201', 'Statistics & data processing', NULL),
(99, 'MAS291', 'Probability and Applied Statistics', NULL),
(100, 'PHY101', 'Physics', NULL),
(101, 'COV111', 'Chess 1', NULL),
(102, 'COV121', 'Chess 2', NULL),
(103, 'COV131', 'Chess 3', NULL),
(104, 'VOV112', 'Vovinam 1', NULL),
(105, 'VOV113', 'Vovinam 2', NULL),
(106, 'VOV122', 'Vovinam 3', NULL),
(107, 'VOV123', 'Vovinam 4', NULL),
(108, 'VOV132', 'Vovinam 5', NULL),
(109, 'VOV133', 'Vovinam 6', NULL),
(110, 'AVC101', 'Asia Culture', NULL),
(111, 'HCM201', 'Ho Chi Minh Ideology', NULL),
(112, 'MLN101', 'Principles of Marxism - Leninism', NULL),
(113, 'SA', 'Self awareness', NULL),
(114, 'SSC102', 'Business Communication', NULL),
(115, 'VNR201', 'Revolutionary Lines of CPV', NULL),
(116, 'HCI201', 'Human Computer Interaction', NULL),
(117, 'ITE302', 'Ethics in Information Technology', NULL),
(118, 'SWD391', 'Software Architecture and Design', NULL),
(119, 'SWE102', 'Introduction to Software Engineering', NULL),
(120, 'SWM301', 'IT Project Management', NULL),
(121, 'SWQ391', 'Software Quality Assurance and Testing', NULL),
(122, 'SWR301', 'Software Requirements', NULL),
(123, 'ÐNG101', '?àn Nguy?t', NULL),
(124, 'ÐNH101', '?àn Nh?', NULL),
(125, 'ÐSA101', 'Sáo trúc', NULL),
(126, 'ÐTR101', '?àn Tranh', NULL),
(127, 'VNL111', 'Vietnamese - Fundamentals 1', NULL),
(128, 'VNL121', 'Vietnamese Language 2', NULL),
(129, 'VNL131', 'Vietnamese 3', NULL),
(130, 'AAAAAAA', 'Mon B', NULL),
(131, 'SE', 'Software Engineering 2', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_semester`
--

INSERT INTO `course_semester` (`course_semester_id`, `course_id`, `semester_id`, `slots`, `course_condition_id`) VALUES
(134, 1, 1, 30, 2),
(136, 2, 1, 40, NULL),
(137, 4, 2, 50, NULL),
(139, 1, 2, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
`department_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `code`, `name`) VALUES
(1, '1', '1'),
(2, '11', '1111');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `semesters`
--

INSERT INTO `semesters` (`semester_id`, `code`, `name`, `semester_year`, `start_date`, `end_date`) VALUES
(1, 'SM15', 'Summer2015', 2015, '2015-11-05', '2017-06-08'),
(2, 'SP2015', 'Spring 2015', 2015, '2015-01-02', '2015-04-29');

-- --------------------------------------------------------

--
-- Table structure for table `specialized`
--

DROP TABLE IF EXISTS `specialized`;
CREATE TABLE IF NOT EXISTS `specialized` (
`specialized_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialized`
--

INSERT INTO `specialized` (`specialized_id`, `code`, `name`) VALUES
(1, 'SE', 'Software Engineering'),
(2, 'IS', 'Information System');

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
(1, 'duchmse02551', 'Hoang Minh Duc', 'duchmse02551@fpt.edu.vn', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `account`, `name`, `email`, `student_code`, `specialized_id`, `batch`, `semester`, `class_semester_id`) VALUES
(1, 'DucHMSE02551', 'Hoang Minh Duc', 'duchmse02551@fpt.edu.vn', 'SE02551', 1, '7B', 1, 1),
(2, 'HaNSSE02618', 'Nguyen Son Ha', 'hansse02618@fpt.edu.vn', 'SE02618', 1, '7B', 1, NULL),
(4, 'ThanhVCSE02552', 'Vu Cong Thanh', 'ThanhVCSE02552@fpt.edu.vn', 'SE02552', 1, '7B', 1, NULL),
(7, 'CongLTSE02619', 'Luu Thanh Cong', 'CongLTSE02619@fpt.edu.vn', 'SE02619', 1, '7B', 1, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`teacher_id`, `account`, `name`, `email`, `account_type`) VALUES
(1, 'AnhBN', 'AnhBN', 'AnhBN@fpt.edu.vn', 'teacher'),
(2, 'AnhNN3', 'AnhNN3', 'AnhNN3@fpt.edu.vn', 'teacher'),
(3, 'AnhNTP', 'AnhNTP', 'AnhNTP@fpt.edu.vn', 'teacher'),
(4, 'AnhPN', 'AnhPN', 'AnhPN@fpt.edu.vn', 'teacher'),
(5, 'AnhPT', 'AnhPT', 'AnhPT@fpt.edu.vn', 'teacher'),
(6, 'BangBH', 'BangBH', 'BangBH@fpt.edu.vn', 'teacher'),
(7, 'BinhHN', 'BinhHN', 'BinhHN@fpt.edu.vn', 'teacher'),
(8, 'BinhNV2', 'BinhNV2', 'BinhNV2@fpt.edu.vn', 'teacher'),
(9, 'CamNV', 'CamNV', 'CamNV@fpt.edu.vn', 'teacher'),
(10, 'CauPD', 'CauPD', 'CauPD@fpt.edu.vn', 'teacher'),
(11, 'ChienBD', 'ChienBD', 'ChienBD@fpt.edu.vn', 'teacher'),
(12, 'ChiLP', 'ChiLP', 'ChiLP@fpt.edu.vn', 'teacher'),
(13, 'ChinhVV', 'ChinhVV', 'ChinhVV@fpt.edu.vn', 'teacher'),
(14, 'CongNM', 'CongNM', 'CongNM@fpt.edu.vn', 'teacher'),
(15, 'CuongN', 'CuongN', 'CuongN@fpt.edu.vn', 'teacher'),
(16, 'Darlenne', 'Darlenne', 'Darlenne@fpt.edu.vn', 'teacher'),
(17, 'DiepDN', 'DiepDN', 'DiepDN@fpt.edu.vn', 'teacher'),
(18, 'DiepPTB', 'DiepPTB', 'DiepPTB@fpt.edu.vn', 'teacher'),
(19, 'DoLQ', 'DoLQ', 'DoLQ@fpt.edu.vn', 'teacher'),
(20, 'DuanTC', 'DuanTC', 'DuanTC@fpt.edu.vn', 'teacher'),
(21, 'DungDV', 'DungDV', 'DungDV@fpt.edu.vn', 'teacher'),
(22, 'DungHA', 'DungHA', 'DungHA@fpt.edu.vn', 'teacher'),
(23, 'DungLM', 'DungLM', 'DungLM@fpt.edu.vn', 'teacher'),
(24, 'DungND', 'DungND', 'DungND@fpt.edu.vn', 'teacher'),
(25, 'DungNT', 'DungNT', 'DungNT@fpt.edu.vn', 'teacher'),
(26, 'DungNT3', 'DungNT3', 'DungNT3@fpt.edu.vn', 'teacher'),
(27, 'DungNT6', 'DungNT6', 'DungNT6@fpt.edu.vn', 'teacher'),
(28, 'DuongTB', 'DuongTB', 'DuongTB@fpt.edu.vn', 'teacher'),
(29, 'duydt', 'duydt', 'duydt@fpt.edu.vn', 'teacher'),
(30, 'Guuswink', 'Guuswink', 'Guuswink@fpt.edu.vn', 'teacher'),
(31, 'HaDT2', 'HaDT2', 'HaDT2@fpt.edu.vn', 'teacher'),
(32, 'HaDTT', 'HaDTT', 'HaDTT@fpt.edu.vn', 'teacher'),
(33, 'HaiNM', 'HaiNM', 'HaiNM@fpt.edu.vn', 'teacher'),
(34, 'HangNL', 'HangNL', 'HangNL@fpt.edu.vn', 'teacher'),
(35, 'HangNT', 'HangNT', 'HangNT@fpt.edu.vn', 'teacher'),
(36, 'HangTTT2', 'HangTTT2', 'HangTTT2@fpt.edu.vn', 'teacher'),
(37, 'HanhTH', 'HanhTH', 'HanhTH@fpt.edu.vn', 'teacher'),
(38, 'HaPTP', 'HaPTP', 'HaPTP@fpt.edu.vn', 'teacher'),
(39, 'HienDTT', 'HienDTT', 'HienDTT@fpt.edu.vn', 'teacher'),
(40, 'HienNTT', 'HienNTT', 'HienNTT@fpt.edu.vn', 'teacher'),
(41, 'HienVT2', 'HienVT2', 'HienVT2@fpt.edu.vn', 'teacher'),
(42, 'HiepVTB', 'HiepVTB', 'HiepVTB@fpt.edu.vn', 'teacher'),
(43, 'HieuLD2', 'HieuLD2', 'HieuLD2@fpt.edu.vn', 'teacher'),
(44, 'HoaHQ2', 'HoaHQ2', 'HoaHQ2@fpt.edu.vn', 'teacher'),
(45, 'HoaiNT3', 'HoaiNT3', 'HoaiNT3@fpt.edu.vn', 'teacher'),
(46, 'HoaNTQ', 'HoaNTQ', 'HoaNTQ@fpt.edu.vn', 'teacher'),
(47, 'HungLV', 'HungLV', 'HungLV@fpt.edu.vn', 'teacher'),
(48, 'HungPD', 'HungPD', 'HungPD@fpt.edu.vn', 'teacher'),
(49, 'HuongDT', 'HuongDT', 'HuongDT@fpt.edu.vn', 'teacher'),
(50, 'HuongKT', 'HuongKT', 'HuongKT@fpt.edu.vn', 'teacher'),
(51, 'HuongNT2', 'HuongNT2', 'HuongNT2@fpt.edu.vn', 'teacher'),
(52, 'HuongNT7', 'HuongNT7', 'HuongNT7@fpt.edu.vn', 'teacher'),
(53, 'HuongTTT3', 'HuongTTT3', 'HuongTTT3@fpt.edu.vn', 'teacher'),
(54, 'HuyenTT', 'HuyenTT', 'HuyenTT@fpt.edu.vn', 'teacher'),
(55, 'Jet', 'Jet', 'Jet@fpt.edu.vn', 'teacher'),
(56, 'KhaDK', 'KhaDK', 'KhaDK@fpt.edu.vn', 'teacher'),
(57, 'KhuongPD', 'KhuongPD', 'KhuongPD@fpt.edu.vn', 'teacher'),
(58, 'KienLT', 'KienLT', 'KienLT@fpt.edu.vn', 'teacher'),
(59, 'KienVC', 'KienVC', 'KienVC@fpt.edu.vn', 'teacher'),
(60, 'LamPT', 'LamPT', 'LamPT@fpt.edu.vn', 'teacher'),
(61, 'LanNTM', 'LanNTM', 'LanNTM@fpt.edu.vn', 'teacher'),
(62, 'LanNTT', 'LanNTT', 'LanNTT@fpt.edu.vn', 'teacher'),
(63, 'LanTV', 'LanTV', 'LanTV@fpt.edu.vn', 'teacher'),
(64, 'LapPT', 'LapPT', 'LapPT@fpt.edu.vn', 'teacher'),
(65, 'LienNP', 'LienNP', 'LienNP@fpt.edu.vn', 'teacher'),
(66, 'LienNQ', 'LienNQ', 'LienNQ@fpt.edu.vn', 'teacher'),
(67, 'LinhLHM', 'LinhLHM', 'LinhLHM@fpt.edu.vn', 'teacher'),
(68, 'LinhPT3', 'LinhPT3', 'LinhPT3@fpt.edu.vn', 'teacher'),
(69, 'LoanNTP', 'LoanNTP', 'LoanNTP@fpt.edu.vn', 'teacher'),
(70, 'LongDH', 'LongDH', 'LongDH@fpt.edu.vn', 'teacher'),
(71, 'LuDTH', 'LuDTH', 'LuDTH@fpt.edu.vn', 'teacher'),
(72, 'MaiTT', 'MaiTT', 'MaiTT@fpt.edu.vn', 'teacher'),
(73, 'MaiVTT', 'MaiVTT', 'MaiVTT@fpt.edu.vn', 'teacher'),
(74, 'MaoPV', 'MaoPV', 'MaoPV@fpt.edu.vn', 'teacher'),
(75, 'NaHA', 'NaHA', 'NaHA@fpt.edu.vn', 'teacher'),
(76, 'Nam', 'Nam', 'Nam@fpt.edu.vn', 'teacher'),
(77, 'NamKV', 'NamKV', 'NamKV@fpt.edu.vn', 'teacher'),
(78, 'NamTG', 'NamTG', 'NamTG@fpt.edu.vn', 'teacher'),
(79, 'NgaNTT', 'NgaNTT', 'NgaNTT@fpt.edu.vn', 'teacher'),
(80, 'NghiaHT', 'NghiaHT', 'NghiaHT@fpt.edu.vn', 'teacher'),
(81, 'NguyenLTT', 'NguyenLTT', 'NguyenLTT@fpt.edu.vn', 'teacher'),
(82, 'NguyetNN', 'NguyetNN', 'NguyetNN@fpt.edu.vn', 'teacher'),
(83, 'NguyetNT2', 'NguyetNT2', 'NguyetNT2@fpt.edu.vn', 'teacher'),
(84, 'NguyetNTM', 'NguyetNTM', 'NguyetNTM@fpt.edu.vn', 'teacher'),
(85, 'NhienNT', 'NhienNT', 'NhienNT@fpt.edu.vn', 'teacher'),
(86, 'NhungBH', 'NhungBH', 'NhungBH@fpt.edu.vn', 'teacher'),
(87, 'PhuDV', 'PhuDV', 'PhuDV@fpt.edu.vn', 'teacher'),
(88, 'PhuongLH2', 'PhuongLH2', 'PhuongLH2@fpt.edu.vn', 'teacher'),
(89, 'PhuongNT', 'PhuongNT', 'PhuongNT@fpt.edu.vn', 'teacher'),
(90, 'QuanNN', 'QuanNN', 'QuanNN@fpt.edu.vn', 'teacher'),
(91, 'QuyenTTL', 'QuyenTTL', 'QuyenTTL@fpt.edu.vn', 'teacher'),
(92, 'QuynhNTX', 'QuynhNTX', 'QuynhNTX@fpt.edu.vn', 'teacher'),
(93, 'QuyPH', 'QuyPH', 'QuyPH@fpt.edu.vn', 'teacher'),
(94, 'SangNV', 'SangNV', 'SangNV@fpt.edu.vn', 'teacher'),
(95, 'Setaita', 'Setaita', 'Setaita@fpt.edu.vn', 'teacher'),
(96, 'SonHN', 'SonHN', 'SonHN@fpt.edu.vn', 'teacher'),
(97, 'SonHX', 'SonHX', 'SonHX@fpt.edu.vn', 'teacher'),
(98, 'Susana', 'Susana', 'Susana@fpt.edu.vn', 'teacher'),
(99, 'TamNT', 'TamNT', 'TamNT@fpt.edu.vn', 'teacher'),
(100, 'TanNTT', 'TanNTT', 'TanNTT@fpt.edu.vn', 'teacher'),
(101, 'ThangNB', 'ThangNB', 'ThangNB@fpt.edu.vn', 'teacher'),
(102, 'ThanhNN', 'ThanhNN', 'ThanhNN@fpt.edu.vn', 'teacher'),
(103, 'ThienNV', 'ThienNV', 'ThienNV@fpt.edu.vn', 'teacher'),
(104, 'ThuLX', 'ThuLX', 'ThuLX@fpt.edu.vn', 'teacher'),
(105, 'ThuyDTP', 'ThuyDTP', 'ThuyDTP@fpt.edu.vn', 'teacher'),
(106, 'ThuyNT8', 'ThuyNT8', 'ThuyNT8@fpt.edu.vn', 'teacher'),
(107, 'TinhLT2', 'TinhLT2', 'TinhLT2@fpt.edu.vn', 'teacher'),
(108, 'TrangBT4', 'TrangBT4', 'TrangBT4@fpt.edu.vn', 'teacher'),
(109, 'TrangLQ', 'TrangLQ', 'TrangLQ@fpt.edu.vn', 'teacher'),
(110, 'TrangLTT', 'TrangLTT', 'TrangLTT@fpt.edu.vn', 'teacher'),
(111, 'TrangNTT3', 'TrangNTT3', 'TrangNTT3@fpt.edu.vn', 'teacher'),
(112, 'TrangTK', 'TrangTK', 'TrangTK@fpt.edu.vn', 'teacher'),
(113, 'TraTT', 'TraTT', 'TraTT@fpt.edu.vn', 'teacher'),
(114, 'TriTD', 'TriTD', 'TriTD@fpt.edu.vn', 'teacher'),
(115, 'TrungCV', 'TrungCV', 'TrungCV@fpt.edu.vn', 'teacher'),
(116, 'TrungDT', 'TrungDT', 'TrungDT@fpt.edu.vn', 'teacher'),
(117, 'TrungNN', 'TrungNN', 'TrungNN@fpt.edu.vn', 'teacher'),
(118, 'TrungNT', 'TrungNT', 'TrungNT@fpt.edu.vn', 'teacher'),
(119, 'TuanHM', 'TuanHM', 'TuanHM@fpt.edu.vn', 'teacher'),
(120, 'TuanTD', 'TuanTD', 'TuanTD@fpt.edu.vn', 'teacher'),
(121, 'TuanVM', 'TuanVM', 'TuanVM@fpt.edu.vn', 'teacher'),
(122, 'TungPT2', 'TungPT2', 'TungPT2@fpt.edu.vn', 'teacher'),
(123, 'TuVT', 'TuVT', 'TuVT@fpt.edu.vn', 'teacher'),
(124, 'TuyetLTA', 'TuyetLTA', 'TuyetLTA@fpt.edu.vn', 'teacher'),
(125, 'VanDTH', 'VanDTH', 'VanDTH@fpt.edu.vn', 'teacher'),
(126, 'VanDTT', 'VanDTT', 'VanDTT@fpt.edu.vn', 'teacher'),
(127, 'VietNK', 'VietNK', 'VietNK@fpt.edu.vn', 'teacher'),
(128, 'VinhPT', 'VinhPT', 'VinhPT@fpt.edu.vn', 'teacher'),
(129, 'VuDA', 'VuDA', 'VuDA@fpt.edu.vn', 'teacher'),
(130, 'VuNH', 'VuNH', 'VuNH@fpt.edu.vn', 'teacher'),
(131, 'XuanLT', 'XuanLT', 'XuanLT@fpt.edu.vn', 'teacher');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_course_semester`
--

DROP TABLE IF EXISTS `teacher_course_semester`;
CREATE TABLE IF NOT EXISTS `teacher_course_semester` (
`teacher_course_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1349 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teacher_semester`
--

DROP TABLE IF EXISTS `teacher_semester`;
CREATE TABLE IF NOT EXISTS `teacher_semester` (
`teacher_semester_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=latin1;

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
(130, 130, 1),
(131, 131, 1);

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
MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=185;
--
-- AUTO_INCREMENT for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT;
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
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=132;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=140;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
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
MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=55;
--
-- AUTO_INCREMENT for table `semesters`
--
ALTER TABLE `semesters`
MODIFY `semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `specialized`
--
ALTER TABLE `specialized`
MODIFY `specialized_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
MODIFY `student_course_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=132;
--
-- AUTO_INCREMENT for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
MODIFY `teacher_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1349;
--
-- AUTO_INCREMENT for table `teacher_semester`
--
ALTER TABLE `teacher_semester`
MODIFY `teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=132;
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
