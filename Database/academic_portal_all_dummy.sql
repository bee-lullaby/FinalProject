-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2015 at 04:57 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_course_semester`
--

INSERT INTO `class_course_semester` (`class_course_semester_id`, `class_semester_id`, `course_semester_id`) VALUES
(615, 3, 1),
(616, 49, 1),
(617, 66, 1),
(618, 67, 1),
(619, 69, 1),
(620, 10, 2),
(621, 11, 2),
(622, 12, 3),
(623, 9, 4),
(624, 2, 5),
(625, 48, 5),
(626, 62, 5),
(627, 13, 6),
(628, 14, 6),
(629, 52, 6),
(630, 61, 6),
(631, 81, 6),
(632, 12, 7),
(633, 80, 7),
(634, 4, 8),
(635, 5, 8),
(636, 50, 8),
(637, 3, 9),
(638, 49, 9),
(639, 51, 10),
(640, 79, 10),
(641, 12, 11),
(642, 2, 12),
(643, 48, 12),
(644, 62, 12),
(645, 2, 13),
(646, 48, 13),
(647, 62, 13),
(648, 13, 14),
(649, 14, 14),
(650, 13, 15),
(651, 14, 15),
(652, 52, 15),
(653, 10, 16),
(654, 11, 16),
(655, 51, 16),
(656, 9, 17),
(657, 10, 17),
(658, 11, 17),
(659, 51, 17),
(660, 79, 17),
(661, 4, 18),
(662, 5, 18),
(663, 50, 18),
(664, 2, 19),
(665, 48, 19),
(666, 62, 19),
(667, 61, 20),
(668, 81, 20),
(669, 80, 21),
(670, 80, 22),
(671, 79, 23),
(672, 3, 24),
(673, 49, 24),
(674, 12, 25),
(675, 80, 25),
(676, 4, 26),
(677, 5, 26),
(678, 40, 26),
(679, 50, 26),
(680, 58, 26),
(681, 132, 26),
(682, 133, 26),
(683, 134, 26),
(684, 9, 27),
(685, 10, 27),
(686, 11, 27),
(687, 51, 27),
(688, 79, 27),
(689, 71, 28),
(690, 123, 28),
(691, 124, 28),
(692, 125, 28),
(693, 126, 28),
(694, 127, 28),
(695, 128, 28),
(696, 38, 29),
(697, 60, 29),
(698, 25, 30),
(699, 39, 31),
(700, 56, 31),
(701, 57, 31),
(702, 135, 31),
(703, 136, 31),
(704, 137, 31),
(705, 138, 31),
(706, 139, 31),
(707, 4, 32),
(708, 5, 32),
(709, 50, 32),
(710, 63, 32),
(711, 65, 33),
(712, 68, 33),
(713, 25, 34),
(714, 123, 34),
(715, 124, 34),
(716, 125, 34),
(717, 126, 34),
(718, 127, 34),
(719, 128, 34),
(720, 25, 35),
(721, 37, 35),
(722, 60, 35),
(723, 70, 35),
(724, 71, 35),
(725, 123, 35),
(726, 124, 35),
(727, 125, 35),
(728, 126, 35),
(729, 127, 35),
(730, 128, 35),
(731, 38, 36),
(732, 59, 36),
(733, 129, 36),
(734, 130, 36),
(735, 131, 36),
(736, 52, 37),
(737, 61, 37),
(738, 81, 37),
(739, 40, 38),
(740, 56, 38),
(741, 57, 38),
(742, 73, 38),
(743, 135, 38),
(744, 136, 38),
(745, 137, 38),
(746, 138, 38),
(747, 139, 38),
(748, 25, 39),
(749, 72, 39),
(750, 129, 39),
(751, 130, 39),
(752, 131, 39),
(753, 119, 40),
(754, 120, 40),
(755, 121, 40),
(756, 122, 40),
(757, 71, 41),
(758, 123, 41),
(759, 124, 41),
(760, 125, 41),
(761, 126, 41),
(762, 127, 41),
(763, 128, 41),
(764, 91, 42),
(765, 92, 42),
(766, 58, 43),
(767, 64, 43),
(768, 132, 43),
(769, 133, 43),
(770, 134, 43),
(771, 39, 44),
(772, 53, 44),
(773, 56, 44),
(774, 57, 44),
(775, 135, 44),
(776, 136, 44),
(777, 137, 44),
(778, 138, 44),
(779, 139, 44),
(780, 40, 45),
(781, 54, 45),
(782, 58, 45),
(783, 132, 45),
(784, 133, 45),
(785, 134, 45),
(786, 41, 46),
(787, 55, 46),
(788, 59, 46),
(789, 72, 46),
(790, 129, 46),
(791, 130, 46),
(792, 131, 46),
(793, 4, 47),
(794, 5, 47),
(795, 50, 47),
(796, 61, 47),
(797, 3, 48),
(798, 49, 48),
(799, 2, 49),
(800, 48, 49),
(801, 62, 49),
(802, 13, 50),
(803, 14, 50),
(804, 52, 50),
(805, 61, 50),
(806, 81, 50),
(807, 12, 51),
(808, 80, 51),
(809, 88, 55),
(810, 89, 56),
(811, 90, 56),
(812, 91, 57),
(813, 92, 57),
(814, 93, 57),
(815, 53, 58),
(816, 54, 59),
(817, 54, 60),
(818, 53, 61),
(819, 53, 62),
(820, 54, 63),
(821, 53, 64),
(822, 53, 65),
(823, 54, 66),
(824, 55, 66),
(825, 24, 67),
(826, 37, 68),
(827, 58, 68),
(828, 40, 69),
(829, 24, 70),
(830, 65, 33),
(831, 68, 33),
(832, 44, 71),
(833, 46, 71),
(834, 47, 71),
(835, 38, 72),
(836, 43, 72),
(837, 35, 73),
(838, 36, 73),
(839, 37, 74),
(840, 38, 75),
(841, 35, 76),
(842, 36, 76),
(843, 44, 76),
(844, 46, 76),
(845, 47, 76),
(846, 43, 77),
(847, 45, 77),
(848, 66, 78),
(849, 67, 78),
(850, 69, 79),
(851, 35, 80),
(852, 36, 80),
(853, 45, 81),
(854, 65, 82),
(855, 68, 82),
(856, 24, 83),
(857, 76, 85),
(858, 41, 86),
(859, 72, 86),
(860, 129, 86),
(861, 130, 86),
(862, 131, 86),
(863, 38, 87),
(864, 71, 87),
(865, 123, 87),
(866, 124, 87),
(867, 125, 87),
(868, 126, 87),
(869, 127, 87),
(870, 128, 87),
(871, 37, 88),
(872, 119, 88),
(873, 120, 88),
(874, 121, 88),
(875, 122, 88),
(876, 77, 89),
(877, 78, 89),
(878, 77, 90),
(879, 76, 91),
(880, 40, 92),
(881, 58, 92),
(882, 132, 92),
(883, 133, 92),
(884, 134, 92),
(885, 39, 93),
(886, 56, 93),
(887, 57, 93),
(888, 73, 93),
(889, 135, 93),
(890, 136, 93),
(891, 137, 93),
(892, 138, 93),
(893, 139, 93),
(894, 3, 94),
(895, 49, 94),
(896, 39, 95),
(897, 56, 95),
(898, 57, 95),
(899, 73, 95),
(900, 135, 95),
(901, 136, 95),
(902, 137, 95),
(903, 138, 95),
(904, 139, 95),
(905, 72, 96),
(906, 132, 96),
(907, 133, 96),
(908, 134, 96),
(909, 25, 97),
(910, 13, 98),
(911, 14, 98),
(912, 52, 98),
(913, 61, 98),
(914, 81, 98),
(915, 37, 99),
(916, 59, 99),
(917, 119, 99),
(918, 120, 99),
(919, 121, 99),
(920, 122, 99),
(921, 39, 100),
(922, 15, 101),
(923, 18, 102),
(924, 21, 103),
(925, 22, 103),
(926, 151, 106),
(927, 152, 106),
(928, 154, 107),
(929, 155, 107),
(930, 158, 108),
(931, 159, 108),
(932, 160, 108),
(933, 161, 108),
(934, 165, 109),
(935, 166, 109),
(936, 167, 109),
(937, 168, 109),
(938, 169, 109),
(939, 63, 110),
(940, 43, 111),
(941, 45, 111),
(942, 65, 111),
(943, 68, 111),
(944, 76, 111),
(945, 9, 112),
(946, 10, 112),
(947, 11, 112),
(948, 24, 112),
(949, 44, 112),
(950, 46, 112),
(951, 47, 112),
(952, 51, 112),
(953, 66, 112),
(954, 67, 112),
(955, 69, 112),
(956, 77, 112),
(957, 78, 112),
(958, 79, 112),
(959, 35, 114),
(960, 36, 114),
(961, 43, 114),
(962, 45, 114),
(963, 59, 114),
(964, 65, 114),
(965, 68, 114),
(966, 77, 114),
(967, 78, 114),
(968, 43, 115),
(969, 45, 115),
(970, 65, 115),
(971, 68, 115),
(972, 76, 115),
(973, 44, 116),
(974, 46, 116),
(975, 47, 116),
(976, 66, 116),
(977, 67, 116),
(978, 69, 116),
(979, 24, 117),
(980, 44, 118),
(981, 46, 118),
(982, 47, 118),
(983, 66, 118),
(984, 67, 118),
(985, 69, 118),
(986, 77, 118),
(987, 78, 118),
(988, 35, 119),
(989, 36, 119),
(990, 72, 119),
(991, 129, 119),
(992, 130, 119),
(993, 131, 119),
(994, 43, 120),
(995, 45, 120),
(996, 65, 120),
(997, 68, 120),
(998, 76, 120),
(999, 119, 121),
(1000, 120, 121),
(1001, 121, 121),
(1002, 122, 121),
(1003, 119, 122),
(1004, 120, 122),
(1005, 121, 122),
(1006, 122, 122),
(1007, 147, 127),
(1008, 73, 128),
(1009, 62, 129),
(1010, 72, 129);

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
) ENGINE=InnoDB AUTO_INCREMENT=398 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_course_teacher_semester`
--

INSERT INTO `class_course_teacher_semester` (`class_course_teacher_semester_id`, `class_semester_id`, `course_semester_id`, `teacher_semester_id`) VALUES
(2, 3, 1, 112),
(3, 49, 1, 112),
(4, 66, 1, 112),
(5, 67, 1, 112),
(6, 69, 1, 112),
(7, 10, 2, 117),
(8, 11, 2, 117),
(9, 12, 3, 112),
(10, 9, 4, 21),
(11, 2, 5, 26),
(12, 48, 5, 26),
(13, 62, 5, 26),
(14, 13, 6, 69),
(15, 14, 6, 69),
(16, 52, 6, 69),
(17, 61, 6, 69),
(18, 81, 6, 69),
(19, 12, 7, 69),
(20, 80, 7, 69),
(21, 4, 8, 110),
(22, 5, 8, 110),
(23, 50, 8, 110),
(24, 3, 9, 99),
(25, 49, 9, 99),
(26, 51, 10, 80),
(27, 79, 10, 80),
(28, 12, 11, 61),
(29, 2, 12, 110),
(30, 48, 12, 110),
(31, 62, 12, 110),
(32, 2, 13, 21),
(33, 48, 13, 21),
(34, 62, 13, 21),
(35, 13, 14, 61),
(36, 14, 14, 61),
(37, 13, 15, 21),
(38, 14, 15, 21),
(39, 52, 15, 21),
(40, 10, 16, 61),
(41, 11, 16, 61),
(42, 51, 16, 61),
(43, 9, 17, 120),
(44, 10, 17, 120),
(45, 11, 17, 120),
(46, 51, 17, 120),
(47, 79, 17, 120),
(48, 4, 18, 122),
(49, 5, 18, 122),
(50, 50, 18, 122),
(51, 2, 19, 65),
(52, 48, 19, 65),
(53, 62, 19, 65),
(54, 61, 20, 81),
(55, 81, 20, 81),
(56, 80, 21, 81),
(57, 80, 22, 66),
(58, 79, 23, 66),
(59, 3, 24, 33),
(60, 49, 24, 33),
(61, 12, 25, 80),
(62, 80, 25, 80),
(63, 4, 26, 125),
(64, 5, 26, 125),
(65, 40, 26, 125),
(66, 50, 26, 125),
(67, 58, 26, 125),
(68, 132, 26, 125),
(69, 133, 26, 125),
(70, 134, 26, 125),
(71, 9, 27, 122),
(72, 10, 27, 122),
(73, 11, 27, 122),
(74, 51, 27, 122),
(75, 79, 27, 122),
(76, 71, 28, 130),
(77, 123, 28, 130),
(78, 124, 28, 130),
(79, 125, 28, 130),
(80, 126, 28, 130),
(81, 127, 28, 130),
(82, 128, 28, 130),
(83, 38, 29, 10),
(84, 60, 29, 10),
(85, 25, 30, 100),
(86, 39, 31, 130),
(87, 56, 31, 130),
(88, 57, 31, 130),
(89, 135, 31, 130),
(90, 136, 31, 130),
(91, 137, 31, 130),
(92, 138, 31, 130),
(93, 139, 31, 130),
(94, 4, 32, 43),
(95, 5, 32, 43),
(96, 50, 32, 43),
(97, 63, 32, 43),
(98, 65, 33, 70),
(99, 68, 33, 70),
(100, 25, 34, 70),
(101, 123, 34, 70),
(102, 124, 34, 70),
(103, 125, 34, 70),
(104, 126, 34, 70),
(105, 127, 34, 70),
(106, 128, 34, 70),
(107, 25, 35, 130),
(108, 37, 35, 130),
(109, 60, 35, 130),
(110, 70, 35, 130),
(111, 71, 35, 130),
(112, 123, 35, 130),
(113, 124, 35, 130),
(114, 125, 35, 130),
(115, 126, 35, 130),
(116, 127, 35, 130),
(117, 128, 35, 130),
(118, 38, 36, 130),
(119, 59, 36, 130),
(120, 129, 36, 130),
(121, 130, 36, 130),
(122, 131, 36, 130),
(123, 52, 37, 70),
(124, 61, 37, 70),
(125, 81, 37, 70),
(126, 40, 38, 60),
(127, 56, 38, 60),
(128, 57, 38, 60),
(129, 73, 38, 60),
(130, 135, 38, 60),
(131, 136, 38, 60),
(132, 137, 38, 60),
(133, 138, 38, 60),
(134, 139, 38, 60),
(135, 25, 39, 29),
(136, 72, 39, 29),
(137, 129, 39, 29),
(138, 130, 39, 29),
(139, 131, 39, 29),
(140, 119, 40, 118),
(141, 120, 40, 118),
(142, 121, 40, 118),
(143, 122, 40, 118),
(144, 71, 41, 60),
(145, 123, 41, 60),
(146, 124, 41, 60),
(147, 125, 41, 60),
(148, 126, 41, 60),
(149, 127, 41, 60),
(150, 128, 41, 60),
(151, 91, 42, 70),
(152, 92, 42, 70),
(153, 58, 43, 29),
(154, 64, 43, 29),
(155, 132, 43, 29),
(156, 133, 43, 29),
(157, 134, 43, 29),
(158, 39, 44, 105),
(159, 53, 44, 105),
(160, 56, 44, 105),
(161, 57, 44, 105),
(162, 135, 44, 105),
(163, 136, 44, 105),
(164, 137, 44, 105),
(165, 138, 44, 105),
(166, 139, 44, 105),
(167, 40, 45, 126),
(168, 54, 45, 126),
(169, 58, 45, 126),
(170, 132, 45, 126),
(171, 133, 45, 126),
(172, 134, 45, 126),
(173, 41, 46, 129),
(174, 55, 46, 129),
(175, 59, 46, 129),
(176, 72, 46, 129),
(177, 129, 46, 129),
(178, 130, 46, 129),
(179, 131, 46, 129),
(180, 4, 47, 98),
(181, 5, 47, 98),
(182, 50, 47, 98),
(183, 61, 47, 98),
(184, 3, 48, 62),
(185, 49, 48, 62),
(186, 2, 49, 84),
(187, 48, 49, 84),
(188, 62, 49, 84),
(189, 13, 50, 67),
(190, 14, 50, 67),
(191, 52, 50, 67),
(192, 61, 50, 67),
(193, 81, 50, 67),
(194, 12, 51, 84),
(195, 80, 51, 84),
(196, 88, 55, 124),
(197, 89, 56, 124),
(198, 90, 56, 124),
(199, 91, 57, 129),
(200, 92, 57, 129),
(201, 93, 57, 129),
(202, 53, 58, 71),
(203, 54, 59, 113),
(204, 54, 60, 113),
(205, 53, 61, 90),
(206, 53, 62, 90),
(207, 54, 63, 91),
(208, 53, 64, 83),
(209, 53, 65, 91),
(210, 54, 66, 68),
(211, 55, 66, 68),
(212, 24, 67, 88),
(213, 37, 68, 97),
(214, 58, 68, 97),
(215, 40, 69, 128),
(216, 24, 70, 31),
(217, 65, 33, 70),
(218, 68, 33, 70),
(219, 44, 71, 63),
(220, 46, 71, 63),
(221, 47, 71, 63),
(222, 38, 72, 63),
(223, 43, 72, 63),
(224, 35, 73, 63),
(225, 36, 73, 63),
(226, 37, 74, 128),
(227, 38, 75, 63),
(228, 35, 76, 97),
(229, 36, 76, 97),
(230, 44, 76, 97),
(231, 46, 76, 97),
(232, 47, 76, 97),
(233, 43, 77, 48),
(234, 45, 77, 48),
(235, 66, 78, 58),
(236, 67, 78, 58),
(237, 69, 79, 1),
(238, 35, 80, 97),
(239, 36, 80, 97),
(240, 45, 81, 1),
(241, 65, 82, 58),
(242, 68, 82, 58),
(243, 24, 83, 5),
(244, 76, 85, 104),
(245, 41, 86, 86),
(246, 72, 86, 86),
(247, 129, 86, 86),
(248, 130, 86, 86),
(249, 131, 86, 86),
(250, 38, 87, 104),
(251, 71, 87, 104),
(252, 123, 87, 104),
(253, 124, 87, 104),
(254, 125, 87, 104),
(255, 126, 87, 104),
(256, 127, 87, 104),
(257, 128, 87, 104),
(258, 37, 88, 104),
(259, 119, 88, 104),
(260, 120, 88, 104),
(261, 121, 88, 104),
(262, 122, 88, 104),
(263, 77, 89, 104),
(264, 78, 89, 104),
(265, 77, 90, 86),
(266, 76, 91, 86),
(267, 40, 92, 78),
(268, 58, 92, 78),
(269, 132, 92, 78),
(270, 133, 92, 78),
(271, 134, 92, 78),
(272, 39, 93, 111),
(273, 56, 93, 111),
(274, 57, 93, 111),
(275, 73, 93, 111),
(276, 135, 93, 111),
(277, 136, 93, 111),
(278, 137, 93, 111),
(279, 138, 93, 111),
(280, 139, 93, 111),
(281, 3, 94, 119),
(282, 49, 94, 119),
(283, 39, 95, 127),
(284, 56, 95, 127),
(285, 57, 95, 127),
(286, 73, 95, 127),
(287, 135, 95, 127),
(288, 136, 95, 127),
(289, 137, 95, 127),
(290, 138, 95, 127),
(291, 139, 95, 127),
(292, 72, 96, 127),
(293, 132, 96, 127),
(294, 133, 96, 127),
(295, 134, 96, 127),
(296, 25, 97, 119),
(297, 13, 98, 116),
(298, 14, 98, 116),
(299, 52, 98, 116),
(300, 61, 98, 116),
(301, 81, 98, 116),
(302, 37, 99, 73),
(303, 59, 99, 73),
(304, 119, 99, 73),
(305, 120, 99, 73),
(306, 121, 99, 73),
(307, 122, 99, 73),
(308, 39, 100, 20),
(309, 15, 101, 45),
(310, 18, 102, 45),
(311, 21, 103, 45),
(312, 22, 103, 45),
(313, 151, 106, 3),
(314, 152, 106, 3),
(315, 154, 107, 131),
(316, 155, 107, 131),
(317, 158, 108, 131),
(318, 159, 108, 131),
(319, 160, 108, 131),
(320, 161, 108, 131),
(321, 165, 109, 131),
(322, 166, 109, 131),
(323, 167, 109, 131),
(324, 168, 109, 131),
(325, 169, 109, 131),
(326, 63, 110, 102),
(327, 43, 111, 115),
(328, 45, 111, 115),
(329, 65, 111, 115),
(330, 68, 111, 115),
(331, 76, 111, 115),
(332, 9, 112, 123),
(333, 10, 112, 123),
(334, 11, 112, 123),
(335, 24, 112, 123),
(336, 44, 112, 123),
(337, 46, 112, 123),
(338, 47, 112, 123),
(339, 51, 112, 123),
(340, 66, 112, 123),
(341, 67, 112, 123),
(342, 69, 112, 123),
(343, 77, 112, 123),
(344, 78, 112, 123),
(345, 79, 112, 123),
(346, 35, 114, 125),
(347, 36, 114, 125),
(348, 43, 114, 125),
(349, 45, 114, 125),
(350, 59, 114, 125),
(351, 65, 114, 125),
(352, 68, 114, 125),
(353, 77, 114, 125),
(354, 78, 114, 125),
(355, 43, 115, 115),
(356, 45, 115, 115),
(357, 65, 115, 115),
(358, 68, 115, 115),
(359, 76, 115, 115),
(360, 44, 116, 76),
(361, 46, 116, 76),
(362, 47, 116, 76),
(363, 66, 116, 76),
(364, 67, 116, 76),
(365, 69, 116, 76),
(366, 24, 117, 5),
(367, 44, 118, 114),
(368, 46, 118, 114),
(369, 47, 118, 114),
(370, 66, 118, 114),
(371, 67, 118, 114),
(372, 69, 118, 114),
(373, 77, 118, 114),
(374, 78, 118, 114),
(375, 35, 119, 118),
(376, 36, 119, 118),
(377, 72, 119, 118),
(378, 129, 119, 118),
(379, 130, 119, 118),
(380, 131, 119, 118),
(381, 43, 120, 118),
(382, 45, 120, 118),
(383, 65, 120, 118),
(384, 68, 120, 118),
(385, 76, 120, 118),
(386, 119, 121, 94),
(387, 120, 121, 94),
(388, 121, 121, 94),
(389, 122, 121, 94),
(390, 119, 122, 94),
(391, 120, 122, 94),
(392, 121, 122, 94),
(393, 122, 122, 94),
(394, 147, 127, 84),
(395, 73, 128, 105),
(396, 62, 129, 79),
(397, 72, 129, 79);

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
  `department_id` int(11) DEFAULT NULL,
  `course_condition` int(11) DEFAULT NULL,
  `block_condition` int(11) DEFAULT NULL,
  `semester_long` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `code`, `name`, `department_id`, `course_condition`, `block_condition`, `semester_long`) VALUES
(1, 'ACC101', 'Accounting Principles', NULL, NULL, NULL, 0),
(2, 'ACC302', 'Managerial Accouting', NULL, NULL, NULL, 0),
(3, 'ACC305', 'Financial Statement Analysis', NULL, NULL, NULL, 0),
(4, 'BKG304', 'Bank Lending', NULL, NULL, NULL, 0),
(5, 'CHN111', 'Chinese Elementary 1', NULL, NULL, NULL, 0),
(6, 'CHN122', 'Chinees Elementary 2', NULL, NULL, NULL, 0),
(7, 'CHN132', 'Chinese Elementary 3', NULL, NULL, NULL, 0),
(8, 'ECO111', 'Basic Micro Economics', NULL, NULL, NULL, 0),
(9, 'ECO121', 'Basic Macro Economics', NULL, NULL, NULL, 0),
(10, 'ETR401', 'Entrepreneurship', NULL, NULL, NULL, 0),
(11, 'FIM301', 'Valuation and Financial Modeling', NULL, NULL, NULL, 0),
(12, 'FIN201', 'Monetary Economics and Global Economy', NULL, NULL, NULL, 0),
(13, 'FIN202', 'Corporate Finance', NULL, NULL, NULL, 0),
(14, 'FIN301', 'Finanical Market & Institutions', NULL, NULL, NULL, 0),
(15, 'FIN303', 'Advanced Corporate Finance', NULL, NULL, NULL, 0),
(16, 'FIN402', 'Derivatives', NULL, NULL, NULL, 0),
(17, 'LAW101', 'Business Law Fundamentals', NULL, NULL, NULL, 0),
(18, 'MGT101', 'Introduction to Management', NULL, NULL, NULL, 0),
(19, 'MKT101', 'Marketing Principles', NULL, NULL, NULL, 0),
(20, 'MKT201', 'Consumer Behavior', NULL, NULL, NULL, 0),
(21, 'MKT202', 'Service Marketing Management', NULL, NULL, NULL, 0),
(22, 'MKT301', 'Marketing Research', NULL, NULL, NULL, 0),
(23, 'MKT304', 'Integrated Marketing Communications', NULL, NULL, NULL, 0),
(24, 'OBE101', 'Organization Behavior', NULL, NULL, NULL, 0),
(25, 'SSC101', 'Business Communication for BA', NULL, NULL, NULL, 0),
(26, 'SSG101', 'Working in Group for BA', NULL, NULL, NULL, 0),
(27, 'SSM201', 'Management Skills', NULL, NULL, NULL, 0),
(28, 'CSD201', 'Data Structures and Algorithms', NULL, NULL, NULL, 0),
(29, 'CSD202', 'Data structures and Algorithms (C++)', NULL, NULL, NULL, 0),
(30, 'CSD301', 'Advanced Algorithms', NULL, NULL, NULL, 0),
(31, 'CSI101', 'Introduction to Computing', NULL, NULL, NULL, 0),
(32, 'CSI102', 'Introduction to Computing for Business', NULL, NULL, NULL, 0),
(33, 'DBD301', 'Advanced Database', NULL, NULL, NULL, 0),
(34, 'DBI202', 'Introduction to Database', NULL, NULL, NULL, 0),
(35, 'NWC202', 'Networking', NULL, NULL, NULL, 0),
(36, 'OSG202', 'Operating Systems', NULL, NULL, NULL, 0),
(37, 'PRE201', 'Excel Programming', NULL, NULL, NULL, 0),
(38, 'PRF192', 'Programming fundamentals using C', NULL, NULL, NULL, 0),
(39, 'PRJ101', 'Core Java', NULL, NULL, NULL, 0),
(40, 'PRJ201', 'Advanced Java', NULL, NULL, NULL, 0),
(41, 'PRN292', 'C# and .NET', NULL, NULL, NULL, 0),
(42, 'PRO001', 'Learning to Program with Alice', NULL, NULL, NULL, 0),
(43, 'PRO191', 'Object Oriented Programming', NULL, NULL, NULL, 0),
(44, 'ENI201', 'Business English - Pre-Intermediate', NULL, NULL, NULL, 0),
(45, 'ENI301', 'Business English - Intermediate', NULL, NULL, NULL, 0),
(46, 'ENI401', 'Business English - Upper-Intermediate', NULL, NULL, NULL, 0),
(47, 'ENL111', 'Academic English 1', NULL, NULL, NULL, 0),
(48, 'ENL112', 'Advanced Academic English', NULL, NULL, NULL, 0),
(49, 'ENM201', 'Pre-Intermediate Business English', NULL, NULL, NULL, 0),
(50, 'ENM301', 'Intermediate Business English', NULL, NULL, NULL, 0),
(51, 'ENM401', 'Upper intermediate Business English', NULL, NULL, NULL, 0),
(52, 'ENT102', 'Top Notch 1', NULL, NULL, NULL, 0),
(53, 'ENT103', 'Top Notch 1 + Fundamentals', NULL, NULL, NULL, 0),
(54, 'ENT202', 'Top Notch 2', NULL, NULL, NULL, 0),
(55, 'ENT302', 'Top Notch 3', NULL, NULL, NULL, 0),
(56, 'ENT401', 'English 5 - Summit 1 from 2012', NULL, NULL, NULL, 0),
(57, 'ENT501', 'Summit 2 from Block 3-4 Fall 2011', NULL, NULL, NULL, 0),
(58, 'AET101', 'Aesthetic', NULL, NULL, NULL, 0),
(59, 'AFA201', 'Human Anatory for Artis', NULL, NULL, NULL, 0),
(60, 'DRF201', 'Drawing - Figure drawing', NULL, NULL, NULL, 0),
(61, 'DRP101', 'Drawing - Plaster Statue, Portrait', NULL, NULL, NULL, 0),
(62, 'DRS101', 'Drawing - Form, Still-life', NULL, NULL, NULL, 0),
(63, 'GDF101', 'Fundamental of Graphic Design', NULL, NULL, NULL, 0),
(64, 'HOA101', 'Art History', NULL, NULL, NULL, 0),
(65, 'HOD101', 'Design History', NULL, NULL, NULL, 0),
(66, 'VCM201', 'Visual Communication', NULL, NULL, NULL, 0),
(67, 'AIL301', 'Machine Learning', NULL, NULL, NULL, 0),
(68, 'CEA201', 'Computer Organization and Architecture', NULL, NULL, NULL, 0),
(69, 'CEC201', 'Circuits and Signals', NULL, NULL, NULL, 0),
(70, 'CGG201', 'Computer Graphics', NULL, NULL, NULL, 0),
(71, 'DGT201', 'Digital Fundamentals', NULL, NULL, NULL, 0),
(72, 'DGT301', 'Digital Signal Processing', NULL, NULL, NULL, 0),
(73, 'ECM391', 'Wireless Mobile Communications', NULL, NULL, NULL, 0),
(74, 'ECS211', 'Communications Systems 1', NULL, NULL, NULL, 0),
(75, 'EEA221', 'Electronics 2', NULL, NULL, NULL, 0),
(76, 'ESH201', 'Embedded System Hardware', NULL, NULL, NULL, 0),
(77, 'ESS301', 'Embedded Software Development', NULL, NULL, NULL, 0),
(78, 'ISC301', 'E-Commerce', NULL, NULL, NULL, 0),
(79, 'ISM301', 'ERP Fundamentals', NULL, NULL, NULL, 0),
(80, 'OFC311', 'Optical Communications', NULL, NULL, NULL, 0),
(81, 'PRM391', 'Mobile Programming', NULL, NULL, NULL, 0),
(82, 'PRX301', 'Advanced XML', NULL, NULL, NULL, 0),
(83, 'SCI201', 'Information Security', NULL, NULL, NULL, 0),
(84, 'SYB301', 'Start your business', NULL, NULL, NULL, 0),
(85, 'JIT301', 'Information Technology Japanese', NULL, NULL, NULL, 0),
(86, 'JPS112', 'Japanese Elementery 1', NULL, NULL, NULL, 0),
(87, 'JPS122', 'Japanese 2', NULL, NULL, NULL, 0),
(88, 'JPS132', 'Japanese 3', NULL, NULL, NULL, 0),
(89, 'JPS142', 'Japanese 4', NULL, NULL, NULL, 0),
(90, 'JPS152', 'Japanese 5', NULL, NULL, NULL, 0),
(91, 'JPS212', 'Japanese Intermediate 1', NULL, NULL, NULL, 0),
(92, 'MAA101', 'Advanced Mathematics 2', NULL, NULL, NULL, 0),
(93, 'MAC101', 'Advanced Mathematics 1', NULL, NULL, NULL, 0),
(94, 'MAC102', 'Advanced Mathematics for Business', NULL, NULL, NULL, 0),
(95, 'MAD111', 'Discrete Mathematics 1', NULL, NULL, NULL, 0),
(96, 'MAD121', 'Discrete Mathematics 2', NULL, NULL, NULL, 0),
(97, 'MAN201', 'Numerical Methods', NULL, NULL, NULL, 0),
(98, 'MAS201', 'Statistics & data processing', NULL, NULL, NULL, 0),
(99, 'MAS291', 'Probability and Applied Statistics', NULL, NULL, NULL, 0),
(100, 'PHY101', 'Physics', NULL, NULL, NULL, 0),
(101, 'COV111', 'Chess 1', NULL, NULL, NULL, 0),
(102, 'COV121', 'Chess 2', NULL, NULL, NULL, 0),
(103, 'COV131', 'Chess 3', NULL, NULL, NULL, 0),
(104, 'VOV112', 'Vovinam 1', NULL, NULL, NULL, 0),
(105, 'VOV113', 'Vovinam 2', NULL, NULL, NULL, 0),
(106, 'VOV122', 'Vovinam 3', NULL, NULL, NULL, 0),
(107, 'VOV123', 'Vovinam 4', NULL, NULL, NULL, 0),
(108, 'VOV132', 'Vovinam 5', NULL, NULL, NULL, 0),
(109, 'VOV133', 'Vovinam 6', NULL, NULL, NULL, 0),
(110, 'AVC101', 'Asia Culture', NULL, NULL, NULL, 0),
(111, 'HCM201', 'Ho Chi Minh Ideology', NULL, NULL, NULL, 0),
(112, 'MLN101', 'Principles of Marxism - Leninism', NULL, NULL, NULL, 0),
(113, 'SA', 'Self awareness', NULL, NULL, NULL, 0),
(114, 'SSC102', 'Business Communication', NULL, NULL, NULL, 0),
(115, 'VNR201', 'Revolutionary Lines of CPV', NULL, NULL, NULL, 0),
(116, 'HCI201', 'Human Computer Interaction', NULL, NULL, NULL, 0),
(117, 'ITE302', 'Ethics in Information Technology', NULL, NULL, NULL, 0),
(118, 'SWD391', 'Software Architecture and Design', NULL, NULL, NULL, 0),
(119, 'SWE102', 'Introduction to Software Engineering', NULL, NULL, NULL, 0),
(120, 'SWM301', 'IT Project Management', NULL, NULL, NULL, 0),
(121, 'SWQ391', 'Software Quality Assurance and Testing', NULL, NULL, NULL, 0),
(122, 'SWR301', 'Software Requirements', NULL, NULL, NULL, 0),
(123, 'ÐNG101', '?àn Nguy?t', NULL, NULL, NULL, 0),
(124, 'ÐNH101', '?àn Nh?', NULL, NULL, NULL, 0),
(125, 'ÐSA101', 'Sáo trúc', NULL, NULL, NULL, 0),
(126, 'ÐTR101', '?àn Tranh', NULL, NULL, NULL, 0),
(127, 'VNL111', 'Vietnamese - Fundamentals 1', NULL, NULL, NULL, 0),
(128, 'VNL121', 'Vietnamese Language 2', NULL, NULL, NULL, 0),
(129, 'VNL131', 'Vietnamese 3', NULL, NULL, NULL, 0),
(130, 'AAAAAAA', 'Mon B', NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `course_semester`
--

DROP TABLE IF EXISTS `course_semester`;
CREATE TABLE IF NOT EXISTS `course_semester` (
`course_semester_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `slots` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_semester`
--

INSERT INTO `course_semester` (`course_semester_id`, `course_id`, `semester_id`, `slots`) VALUES
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
(27, 27, 1, 30),
(28, 28, 1, 30),
(29, 29, 1, 30),
(30, 30, 1, 30),
(31, 31, 1, 30),
(32, 32, 1, 30),
(33, 33, 1, 30),
(34, 34, 1, 30),
(35, 35, 1, 30),
(36, 36, 1, 30),
(37, 37, 1, 30),
(38, 38, 1, 30),
(39, 39, 1, 30),
(40, 40, 1, 30),
(41, 41, 1, 30),
(42, 42, 1, 30),
(43, 43, 1, 30),
(44, 44, 1, 30),
(45, 45, 1, 30),
(46, 46, 1, 30),
(47, 47, 1, 30),
(48, 48, 1, 30),
(49, 49, 1, 30),
(50, 50, 1, 30),
(51, 51, 1, 30),
(52, 52, 1, 30),
(53, 53, 1, 30),
(54, 54, 1, 30),
(55, 55, 1, 30),
(56, 56, 1, 30),
(57, 57, 1, 30),
(58, 58, 1, 30),
(59, 59, 1, 30),
(60, 60, 1, 30),
(61, 61, 1, 30),
(62, 62, 1, 30),
(63, 63, 1, 30),
(64, 64, 1, 30),
(65, 65, 1, 30),
(66, 66, 1, 30),
(67, 67, 1, 30),
(68, 68, 1, 30),
(69, 69, 1, 30),
(70, 70, 1, 30),
(71, 71, 1, 30),
(72, 72, 1, 30),
(73, 73, 1, 30),
(74, 74, 1, 30),
(75, 75, 1, 30),
(76, 76, 1, 30),
(77, 77, 1, 30),
(78, 78, 1, 30),
(79, 79, 1, 30),
(80, 80, 1, 30),
(81, 81, 1, 30),
(82, 82, 1, 30),
(83, 83, 1, 30),
(84, 84, 1, 30),
(85, 85, 1, 30),
(86, 86, 1, 30),
(87, 87, 1, 30),
(88, 88, 1, 30),
(89, 89, 1, 30),
(90, 90, 1, 30),
(91, 91, 1, 30),
(92, 92, 1, 30),
(93, 93, 1, 30),
(94, 94, 1, 30),
(95, 95, 1, 30),
(96, 96, 1, 30),
(97, 97, 1, 30),
(98, 98, 1, 30),
(99, 99, 1, 30),
(100, 100, 1, 30),
(101, 101, 1, 30),
(102, 102, 1, 30),
(103, 103, 1, 30),
(104, 104, 1, 30),
(105, 105, 1, 30),
(106, 106, 1, 30),
(107, 107, 1, 30),
(108, 108, 1, 30),
(109, 109, 1, 30),
(110, 110, 1, 30),
(111, 111, 1, 30),
(112, 112, 1, 30),
(113, 113, 1, 30),
(114, 114, 1, 30),
(115, 115, 1, 30),
(116, 116, 1, 30),
(117, 117, 1, 30),
(118, 118, 1, 30),
(119, 119, 1, 30),
(120, 120, 1, 30),
(121, 121, 1, 30),
(122, 122, 1, 30),
(123, 123, 1, 30),
(124, 124, 1, 30),
(125, 125, 1, 30),
(126, 126, 1, 30),
(127, 127, 1, 30),
(128, 128, 1, 30),
(129, 129, 1, 30);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
`department_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `semesters`
--

INSERT INTO `semesters` (`semester_id`, `code`, `name`, `semester_year`, `start_date`, `end_date`) VALUES
(1, 'SM15', 'Summer2015', 2015, '2015-11-05', '2017-06-08');

-- --------------------------------------------------------

--
-- Table structure for table `specialized`
--

DROP TABLE IF EXISTS `specialized`;
CREATE TABLE IF NOT EXISTS `specialized` (
`specialized_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
`course_teacher_semester_id` int(11) NOT NULL,
  `teacher_semester_id` int(11) NOT NULL,
  `course_semester_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1349 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_course_semester`
--

INSERT INTO `teacher_course_semester` (`course_teacher_semester_id`, `teacher_semester_id`, `course_semester_id`) VALUES
(711, 53, 1),
(712, 107, 1),
(713, 112, 1),
(714, 117, 2),
(715, 112, 3),
(716, 21, 4),
(717, 26, 5),
(718, 26, 6),
(719, 69, 6),
(720, 69, 7),
(721, 99, 8),
(722, 110, 8),
(723, 99, 9),
(724, 80, 10),
(725, 61, 11),
(726, 99, 12),
(727, 110, 12),
(728, 21, 13),
(729, 61, 14),
(730, 21, 15),
(731, 61, 16),
(732, 120, 17),
(733, 33, 18),
(734, 122, 18),
(735, 49, 19),
(736, 65, 19),
(737, 65, 20),
(738, 81, 20),
(739, 81, 21),
(740, 66, 22),
(741, 66, 23),
(742, 33, 24),
(743, 80, 25),
(744, 39, 26),
(745, 49, 26),
(746, 72, 26),
(747, 96, 26),
(748, 125, 26),
(749, 33, 27),
(750, 34, 27),
(751, 122, 27),
(752, 10, 28),
(753, 12, 28),
(754, 130, 28),
(755, 10, 29),
(756, 100, 30),
(757, 1, 31),
(758, 8, 31),
(759, 12, 31),
(760, 22, 31),
(761, 29, 31),
(762, 70, 31),
(763, 130, 31),
(764, 1, 32),
(765, 5, 32),
(766, 23, 32),
(767, 43, 32),
(768, 11, 33),
(769, 70, 33),
(770, 12, 34),
(771, 70, 34),
(772, 2, 35),
(773, 6, 35),
(774, 43, 35),
(775, 130, 35),
(776, 6, 36),
(777, 22, 36),
(778, 130, 36),
(779, 70, 37),
(780, 2, 38),
(781, 10, 38),
(782, 22, 38),
(783, 23, 38),
(784, 58, 38),
(785, 60, 38),
(786, 2, 39),
(787, 8, 39),
(788, 10, 39),
(789, 29, 39),
(790, 8, 40),
(791, 94, 40),
(792, 118, 40),
(793, 29, 41),
(794, 52, 41),
(795, 60, 41),
(796, 12, 42),
(797, 29, 42),
(798, 70, 42),
(799, 12, 43),
(800, 22, 43),
(801, 28, 43),
(802, 29, 43),
(803, 40, 44),
(804, 42, 44),
(805, 51, 44),
(806, 62, 44),
(807, 67, 44),
(808, 79, 44),
(809, 105, 44),
(810, 13, 45),
(811, 38, 45),
(812, 46, 45),
(813, 67, 45),
(814, 126, 45),
(815, 129, 46),
(816, 16, 47),
(817, 40, 47),
(818, 98, 47),
(819, 62, 48),
(820, 13, 49),
(821, 84, 49),
(822, 62, 50),
(823, 67, 50),
(824, 79, 51),
(825, 84, 51),
(826, 9, 52),
(827, 13, 52),
(828, 16, 52),
(829, 17, 52),
(830, 32, 52),
(831, 40, 52),
(832, 51, 52),
(833, 55, 52),
(834, 62, 52),
(835, 67, 52),
(836, 84, 52),
(837, 105, 52),
(838, 124, 52),
(839, 126, 52),
(840, 129, 52),
(841, 13, 53),
(842, 79, 53),
(843, 124, 53),
(844, 9, 54),
(845, 18, 54),
(846, 42, 54),
(847, 67, 54),
(848, 95, 54),
(849, 109, 54),
(850, 124, 54),
(851, 126, 54),
(852, 129, 54),
(853, 30, 55),
(854, 42, 55),
(855, 62, 55),
(856, 67, 55),
(857, 79, 55),
(858, 84, 55),
(859, 85, 55),
(860, 98, 55),
(861, 109, 55),
(862, 124, 55),
(863, 16, 56),
(864, 30, 56),
(865, 35, 56),
(866, 42, 56),
(867, 51, 56),
(868, 62, 56),
(869, 85, 56),
(870, 98, 56),
(871, 105, 56),
(872, 124, 56),
(873, 9, 57),
(874, 13, 57),
(875, 18, 57),
(876, 32, 57),
(877, 38, 57),
(878, 42, 57),
(879, 55, 57),
(880, 67, 57),
(881, 79, 57),
(882, 84, 57),
(883, 98, 57),
(884, 126, 57),
(885, 129, 57),
(886, 71, 58),
(887, 113, 59),
(888, 113, 60),
(889, 90, 61),
(890, 90, 62),
(891, 91, 63),
(892, 83, 64),
(893, 91, 65),
(894, 68, 66),
(895, 27, 67),
(896, 88, 67),
(897, 97, 68),
(898, 128, 69),
(899, 31, 70),
(900, 48, 71),
(901, 63, 71),
(902, 63, 72),
(903, 63, 73),
(904, 128, 74),
(905, 63, 75),
(906, 48, 76),
(907, 97, 76),
(908, 48, 77),
(909, 58, 78),
(910, 1, 79),
(911, 97, 80),
(912, 1, 81),
(913, 58, 82),
(914, 5, 83),
(915, 1, 84),
(916, 92, 84),
(917, 108, 84),
(918, 118, 84),
(919, 104, 85),
(920, 54, 86),
(921, 86, 86),
(922, 15, 87),
(923, 54, 87),
(924, 104, 87),
(925, 15, 88),
(926, 54, 88),
(927, 104, 88),
(928, 86, 89),
(929, 104, 89),
(930, 86, 90),
(931, 86, 91),
(932, 57, 92),
(933, 78, 92),
(934, 7, 93),
(935, 14, 93),
(936, 37, 93),
(937, 57, 93),
(938, 73, 93),
(939, 111, 93),
(940, 119, 94),
(941, 93, 95),
(942, 103, 95),
(943, 116, 95),
(944, 127, 95),
(945, 93, 96),
(946, 116, 96),
(947, 127, 96),
(948, 119, 97),
(949, 25, 98),
(950, 73, 98),
(951, 116, 98),
(952, 25, 99),
(953, 73, 99),
(954, 20, 100),
(955, 45, 101),
(956, 45, 102),
(957, 45, 103),
(958, 44, 104),
(959, 56, 104),
(960, 3, 105),
(961, 44, 105),
(962, 47, 105),
(963, 50, 105),
(964, 56, 105),
(965, 59, 105),
(966, 64, 105),
(967, 87, 105),
(968, 101, 105),
(969, 121, 105),
(970, 131, 105),
(971, 3, 106),
(972, 50, 107),
(973, 101, 107),
(974, 131, 107),
(975, 24, 108),
(976, 44, 108),
(977, 47, 108),
(978, 50, 108),
(979, 56, 108),
(980, 59, 108),
(981, 131, 108),
(982, 3, 109),
(983, 56, 109),
(984, 64, 109),
(985, 87, 109),
(986, 101, 109),
(987, 131, 109),
(988, 102, 110),
(989, 19, 111),
(990, 115, 111),
(991, 4, 112),
(992, 77, 112),
(993, 102, 112),
(994, 123, 112),
(995, 39, 113),
(996, 72, 113),
(997, 89, 113),
(998, 96, 113),
(999, 125, 113),
(1000, 39, 114),
(1001, 89, 114),
(1002, 96, 114),
(1003, 125, 114),
(1004, 19, 115),
(1005, 115, 115),
(1006, 5, 116),
(1007, 76, 116),
(1008, 5, 117),
(1009, 28, 118),
(1010, 114, 118),
(1011, 94, 119),
(1012, 118, 119),
(1013, 94, 120),
(1014, 118, 120),
(1015, 5, 121),
(1016, 36, 121),
(1017, 75, 121),
(1018, 94, 121),
(1019, 94, 122),
(1020, 82, 123),
(1021, 41, 124),
(1022, 74, 125),
(1023, 106, 126),
(1024, 79, 127),
(1025, 84, 127),
(1026, 79, 128),
(1027, 84, 128),
(1028, 105, 128),
(1029, 79, 129),
(1030, 53, 1),
(1031, 107, 1),
(1032, 112, 1),
(1033, 117, 2),
(1034, 112, 3),
(1035, 21, 4),
(1036, 26, 5),
(1037, 26, 6),
(1038, 69, 6),
(1039, 69, 7),
(1040, 99, 8),
(1041, 110, 8),
(1042, 99, 9),
(1043, 80, 10),
(1044, 61, 11),
(1045, 99, 12),
(1046, 110, 12),
(1047, 21, 13),
(1048, 61, 14),
(1049, 21, 15),
(1050, 61, 16),
(1051, 120, 17),
(1052, 33, 18),
(1053, 122, 18),
(1054, 49, 19),
(1055, 65, 19),
(1056, 65, 20),
(1057, 81, 20),
(1058, 81, 21),
(1059, 66, 22),
(1060, 66, 23),
(1061, 33, 24),
(1062, 80, 25),
(1063, 39, 26),
(1064, 49, 26),
(1065, 72, 26),
(1066, 96, 26),
(1067, 125, 26),
(1068, 33, 27),
(1069, 34, 27),
(1070, 122, 27),
(1071, 10, 28),
(1072, 12, 28),
(1073, 130, 28),
(1074, 10, 29),
(1075, 100, 30),
(1076, 1, 31),
(1077, 8, 31),
(1078, 12, 31),
(1079, 22, 31),
(1080, 29, 31),
(1081, 70, 31),
(1082, 130, 31),
(1083, 1, 32),
(1084, 5, 32),
(1085, 23, 32),
(1086, 43, 32),
(1087, 11, 33),
(1088, 70, 33),
(1089, 12, 34),
(1090, 70, 34),
(1091, 2, 35),
(1092, 6, 35),
(1093, 43, 35),
(1094, 130, 35),
(1095, 6, 36),
(1096, 22, 36),
(1097, 130, 36),
(1098, 70, 37),
(1099, 2, 38),
(1100, 10, 38),
(1101, 22, 38),
(1102, 23, 38),
(1103, 58, 38),
(1104, 60, 38),
(1105, 2, 39),
(1106, 8, 39),
(1107, 10, 39),
(1108, 29, 39),
(1109, 8, 40),
(1110, 94, 40),
(1111, 118, 40),
(1112, 29, 41),
(1113, 52, 41),
(1114, 60, 41),
(1115, 12, 42),
(1116, 29, 42),
(1117, 70, 42),
(1118, 12, 43),
(1119, 22, 43),
(1120, 28, 43),
(1121, 29, 43),
(1122, 40, 44),
(1123, 42, 44),
(1124, 51, 44),
(1125, 62, 44),
(1126, 67, 44),
(1127, 79, 44),
(1128, 105, 44),
(1129, 13, 45),
(1130, 38, 45),
(1131, 46, 45),
(1132, 67, 45),
(1133, 126, 45),
(1134, 129, 46),
(1135, 16, 47),
(1136, 40, 47),
(1137, 98, 47),
(1138, 62, 48),
(1139, 13, 49),
(1140, 84, 49),
(1141, 62, 50),
(1142, 67, 50),
(1143, 79, 51),
(1144, 84, 51),
(1145, 9, 52),
(1146, 13, 52),
(1147, 16, 52),
(1148, 17, 52),
(1149, 32, 52),
(1150, 40, 52),
(1151, 51, 52),
(1152, 55, 52),
(1153, 62, 52),
(1154, 67, 52),
(1155, 84, 52),
(1156, 105, 52),
(1157, 124, 52),
(1158, 126, 52),
(1159, 129, 52),
(1160, 13, 53),
(1161, 79, 53),
(1162, 124, 53),
(1163, 9, 54),
(1164, 18, 54),
(1165, 42, 54),
(1166, 67, 54),
(1167, 95, 54),
(1168, 109, 54),
(1169, 124, 54),
(1170, 126, 54),
(1171, 129, 54),
(1172, 30, 55),
(1173, 42, 55),
(1174, 62, 55),
(1175, 67, 55),
(1176, 79, 55),
(1177, 84, 55),
(1178, 85, 55),
(1179, 98, 55),
(1180, 109, 55),
(1181, 124, 55),
(1182, 16, 56),
(1183, 30, 56),
(1184, 35, 56),
(1185, 42, 56),
(1186, 51, 56),
(1187, 62, 56),
(1188, 85, 56),
(1189, 98, 56),
(1190, 105, 56),
(1191, 124, 56),
(1192, 9, 57),
(1193, 13, 57),
(1194, 18, 57),
(1195, 32, 57),
(1196, 38, 57),
(1197, 42, 57),
(1198, 55, 57),
(1199, 67, 57),
(1200, 79, 57),
(1201, 84, 57),
(1202, 98, 57),
(1203, 126, 57),
(1204, 129, 57),
(1205, 71, 58),
(1206, 113, 59),
(1207, 113, 60),
(1208, 90, 61),
(1209, 90, 62),
(1210, 91, 63),
(1211, 83, 64),
(1212, 91, 65),
(1213, 68, 66),
(1214, 27, 67),
(1215, 88, 67),
(1216, 97, 68),
(1217, 128, 69),
(1218, 31, 70),
(1219, 48, 71),
(1220, 63, 71),
(1221, 63, 72),
(1222, 63, 73),
(1223, 128, 74),
(1224, 63, 75),
(1225, 48, 76),
(1226, 97, 76),
(1227, 48, 77),
(1228, 58, 78),
(1229, 1, 79),
(1230, 97, 80),
(1231, 1, 81),
(1232, 58, 82),
(1233, 5, 83),
(1234, 1, 84),
(1235, 92, 84),
(1236, 108, 84),
(1237, 118, 84),
(1238, 104, 85),
(1239, 54, 86),
(1240, 86, 86),
(1241, 15, 87),
(1242, 54, 87),
(1243, 104, 87),
(1244, 15, 88),
(1245, 54, 88),
(1246, 104, 88),
(1247, 86, 89),
(1248, 104, 89),
(1249, 86, 90),
(1250, 86, 91),
(1251, 57, 92),
(1252, 78, 92),
(1253, 7, 93),
(1254, 14, 93),
(1255, 37, 93),
(1256, 57, 93),
(1257, 73, 93),
(1258, 111, 93),
(1259, 119, 94),
(1260, 93, 95),
(1261, 103, 95),
(1262, 116, 95),
(1263, 127, 95),
(1264, 93, 96),
(1265, 116, 96),
(1266, 127, 96),
(1267, 119, 97),
(1268, 25, 98),
(1269, 73, 98),
(1270, 116, 98),
(1271, 25, 99),
(1272, 73, 99),
(1273, 20, 100),
(1274, 45, 101),
(1275, 45, 102),
(1276, 45, 103),
(1277, 44, 104),
(1278, 56, 104),
(1279, 3, 105),
(1280, 44, 105),
(1281, 47, 105),
(1282, 50, 105),
(1283, 56, 105),
(1284, 59, 105),
(1285, 64, 105),
(1286, 87, 105),
(1287, 101, 105),
(1288, 121, 105),
(1289, 131, 105),
(1290, 3, 106),
(1291, 50, 107),
(1292, 101, 107),
(1293, 131, 107),
(1294, 24, 108),
(1295, 44, 108),
(1296, 47, 108),
(1297, 50, 108),
(1298, 56, 108),
(1299, 59, 108),
(1300, 131, 108),
(1301, 3, 109),
(1302, 56, 109),
(1303, 64, 109),
(1304, 87, 109),
(1305, 101, 109),
(1306, 131, 109),
(1307, 102, 110),
(1308, 19, 111),
(1309, 115, 111),
(1310, 4, 112),
(1311, 77, 112),
(1312, 102, 112),
(1313, 123, 112),
(1314, 39, 113),
(1315, 72, 113),
(1316, 89, 113),
(1317, 96, 113),
(1318, 125, 113),
(1319, 39, 114),
(1320, 89, 114),
(1321, 96, 114),
(1322, 125, 114),
(1323, 19, 115),
(1324, 115, 115),
(1325, 5, 116),
(1326, 76, 116),
(1327, 5, 117),
(1328, 28, 118),
(1329, 114, 118),
(1330, 94, 119),
(1331, 118, 119),
(1332, 94, 120),
(1333, 118, 120),
(1334, 5, 121),
(1335, 36, 121),
(1336, 75, 121),
(1337, 94, 121),
(1338, 94, 122),
(1339, 82, 123),
(1340, 41, 124),
(1341, 74, 125),
(1342, 106, 126),
(1343, 79, 127),
(1344, 84, 127),
(1345, 79, 128),
(1346, 84, 128),
(1347, 105, 128),
(1348, 79, 129);

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
 ADD PRIMARY KEY (`class_id`), ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `class_course_semester`
--
ALTER TABLE `class_course_semester`
 ADD PRIMARY KEY (`class_course_semester_id`), ADD KEY `class_semester_id` (`class_semester_id`), ADD KEY `course_semester_id` (`course_semester_id`);

--
-- Indexes for table `class_course_student_semester`
--
ALTER TABLE `class_course_student_semester`
 ADD PRIMARY KEY (`class_course_student_semester_id`), ADD KEY `class_course_semester_id` (`class_course_semester_id`), ADD KEY `student_id` (`student_id`);

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
 ADD PRIMARY KEY (`course_id`), ADD UNIQUE KEY `code` (`code`), ADD KEY `course_condition` (`course_condition`), ADD KEY `department_id` (`department_id`);

--
-- Indexes for table `course_semester`
--
ALTER TABLE `course_semester`
 ADD PRIMARY KEY (`course_semester_id`), ADD UNIQUE KEY `course_id` (`course_id`,`semester_id`), ADD KEY `semester_id` (`semester_id`);

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
 ADD PRIMARY KEY (`time_table_id`), ADD UNIQUE KEY `date` (`date`,`slot`,`room_id`), ADD UNIQUE KEY `date_2` (`date`,`slot`,`class_course_semester_id`), ADD KEY `class_course_semester_id` (`class_course_semester_id`), ADD KEY `room_id` (`room_id`), ADD KEY `teacher_id` (`teacher_id`);

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
MODIFY `class_course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1011;
--
-- AUTO_INCREMENT for table `class_course_student_semester`
--
ALTER TABLE `class_course_student_semester`
MODIFY `class_course_student_semester_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `class_course_teacher_semester`
--
ALTER TABLE `class_course_teacher_semester`
MODIFY `class_course_teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=398;
--
-- AUTO_INCREMENT for table `class_semester`
--
ALTER TABLE `class_semester`
MODIFY `class_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=175;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=131;
--
-- AUTO_INCREMENT for table `course_semester`
--
ALTER TABLE `course_semester`
MODIFY `course_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=130;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT;
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
MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=132;
--
-- AUTO_INCREMENT for table `teacher_course_semester`
--
ALTER TABLE `teacher_course_semester`
MODIFY `course_teacher_semester_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1349;
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
ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`course_condition`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`);

--
-- Constraints for table `course_semester`
--
ALTER TABLE `course_semester`
ADD CONSTRAINT `course_semester_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
ADD CONSTRAINT `course_semester_ibfk_2` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`);

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
