-- --------------------------------------------------------
-- Anfitrião:                    localhost
-- Versão do servidor:           5.7.24 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ednicare
CREATE DATABASE IF NOT EXISTS `ednicare` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ednicare`;

-- Dumping structure for table ednicare.examesporfazer
CREATE TABLE IF NOT EXISTS `examesporfazer` (
  `idexamesPorFazer` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `codigoHistorico` int(10) unsigned DEFAULT '0',
  `codigoUtilizador` int(10) unsigned DEFAULT NULL,
  `codigoStatus` int(10) unsigned DEFAULT '1',
  `dataPedido` date DEFAULT NULL,
  `pacienteInterno` varchar(45) DEFAULT 'NAO',
  `codigoMedico` int(10) unsigned DEFAULT '0',
  `codigoPaciente` int(10) unsigned DEFAULT '0',
  `hora` varchar(45) DEFAULT NULL,
  `quantidade` int(10) unsigned DEFAULT NULL,
  `prescricao` varchar(500) DEFAULT NULL,
  `tecnico` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idexamesPorFazer`),
  KEY `FK_examesporfazer_3` (`codigoStatus`),
  KEY `FK_examesporfazer_1` (`codigoHistorico`),
  KEY `FK_examesporfazer_4` (`codigoMedico`),
  KEY `FK_examesporfazer_5` (`codigoPaciente`),
  KEY `codigoUtilizador` (`codigoUtilizador`) USING BTREE,
  CONSTRAINT `FK_examesporfazer_3` FOREIGN KEY (`codigoStatus`) REFERENCES `status_exames` (`idstatus_exames`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=latin1;

-- Dumping data for table ednicare.examesporfazer: ~154 rows (approximately)
/*!40000 ALTER TABLE `examesporfazer` DISABLE KEYS */;
INSERT INTO `examesporfazer` (`idexamesPorFazer`, `codigoHistorico`, `codigoUtilizador`, `codigoStatus`, `dataPedido`, `pacienteInterno`, `codigoMedico`, `codigoPaciente`, `hora`, `quantidade`, `prescricao`, `tecnico`) VALUES
	(1, 0, 4, 4, '2021-04-11', 'NAO', 11, 26765, '2021-04-11 19:34:43', 1, NULL, NULL),
	(2, 0, 4, 4, '2021-04-11', 'NAO', 3, 26908, '2021-04-11 19:42:31', 1, NULL, NULL),
	(3, 0, 4, 4, '2021-04-11', 'NAO', 3, 26908, '2021-04-11 19:42:51', 1, NULL, NULL),
	(4, 0, 4, 4, '2021-04-11', 'NAO', 13, 26765, '2021-04-11 20:04:44', 1, NULL, NULL),
	(5, 0, 4, 4, '2021-04-11', 'NAO', 3, 26908, '2021-04-11 20:10:09', 1, NULL, NULL),
	(6, 0, 4, 4, '2021-04-11', 'NAO', 3, 26907, '2021-04-11 20:10:25', 1, NULL, NULL),
	(7, 0, 4, 4, '2021-04-11', 'NAO', 4, 26765, '2021-04-11 20:20:56', 1, NULL, NULL),
	(8, 0, 4, 4, '2021-04-11', 'NAO', 9, 26765, '2021-04-11 20:24:57', 1, NULL, NULL),
	(9, 0, 4, 4, '2021-04-11', 'NAO', 11, 26765, '2021-04-11 20:26:50', 1, NULL, NULL),
	(10, 0, 4, 4, '2021-04-11', 'NAO', 11, 26765, '2021-04-11 20:31:05', 1, NULL, NULL),
	(11, 0, 4, 4, '2021-04-11', 'NAO', 5, 26765, '2021-04-11 20:34:39', 1, NULL, NULL),
	(12, 0, 4, 4, '2021-04-11', 'NAO', 5, 26765, '2021-04-11 20:42:52', 1, NULL, NULL),
	(13, 0, 4, 4, '2021-04-11', 'NAO', 11, 22649, '2021-04-11 20:53:27', 1, NULL, NULL),
	(14, 0, 4, 4, '2021-04-11', 'NAO', 3, 26908, '2021-04-11 23:27:44', 1, NULL, NULL),
	(15, 0, 4, 4, '2021-04-11', 'NAO', 3, 26908, '2021-04-11 23:28:21', 1, NULL, NULL),
	(16, 0, 4, 4, '2021-04-13', 'NAO', 4, 26908, '2021-04-13 12:43:08', 1, NULL, NULL),
	(17, 0, 4, 4, '2021-04-13', 'NAO', 4, 2047, '2021-04-13 12:46:34', 1, NULL, NULL),
	(18, 0, 4, 4, '2021-04-13', 'NAO', 5, 26908, '2021-04-13 12:50:28', 1, NULL, NULL),
	(19, 0, 4, 4, '2021-04-13', 'NAO', 5, 26908, '2021-04-13 12:51:32', 1, NULL, NULL),
	(20, 0, 4, 4, '2021-04-15', 'NAO', 1, 26909, '2021-04-15 10:03:32', 1, NULL, NULL),
	(21, 0, 4, 4, '2021-04-15', 'NAO', 4, 26910, '2021-04-15 10:08:46', 1, NULL, NULL),
	(22, 0, 4, 4, '2021-04-15', 'NAO', 4, 26907, '2021-04-15 10:34:44', 1, NULL, NULL),
	(23, 0, 4, 4, '2021-04-15', 'NAO', 4, 26909, '2021-04-15 10:46:03', 1, NULL, NULL),
	(24, 0, 4, 4, '2021-04-15', 'NAO', 4, 26910, '2021-04-15 10:54:43', 1, NULL, NULL),
	(25, 0, 4, 4, '2021-04-15', 'NAO', 4, 26910, '2021-04-15 11:16:30', 1, NULL, NULL),
	(26, 0, 4, 4, '2021-04-15', 'NAO', 3, 26909, '2021-04-15 11:32:57', 1, NULL, NULL),
	(27, 0, 4, 4, '2021-01-02', 'NAO', 4, 26906, '2021-01-02 11:36:54', 1, NULL, NULL),
	(28, 0, 3, 4, '2021-04-15', 'NAO', 3, 26910, '2021-04-15 23:28:01', 1, NULL, NULL),
	(29, 0, 3, 4, '2021-04-15', 'NAO', 3, 26910, '2021-04-15 23:28:22', 1, NULL, NULL),
	(30, 0, 3, 4, '2021-04-15', 'NAO', 3, 26910, '2021-04-15 23:29:36', 1, NULL, NULL),
	(31, 0, 3, 4, '2021-04-15', 'NAO', 3, 26910, '2021-04-15 23:30:28', 1, NULL, NULL),
	(32, 0, 3, 4, '2021-04-15', 'NAO', 3, 26910, '2021-04-15 23:31:44', 1, NULL, NULL),
	(33, 0, 3, 4, '2021-04-15', 'NAO', 3, 26910, '2021-04-15 23:34:39', 1, NULL, NULL),
	(34, 0, 3, 4, '2021-04-24', 'NAO', 1, 26910, '2021-04-24 19:03:56', 1, NULL, NULL),
	(35, 0, 4, 4, '2021-04-24', 'NAO', 3, 26910, '2021-04-24 19:18:48', 1, NULL, NULL),
	(36, 0, 3, 4, '2021-04-24', 'NAO', 4, 26910, '2021-04-24 19:36:48', 1, NULL, NULL),
	(37, 0, 4, 4, '2021-04-24', 'NAO', 1, 26908, '2021-04-24 19:44:32', 1, NULL, NULL),
	(38, 0, 3, 4, '2021-04-25', 'NAO', 4, 26910, '2021-04-25 14:57:39', 1, NULL, NULL),
	(39, 0, 3, 4, '2021-04-25', 'NAO', 3, 26910, '2021-04-25 15:07:09', 1, NULL, NULL),
	(40, 0, 3, 4, '2021-04-25', 'NAO', 4, 26910, '2021-04-25 17:40:14', 1, NULL, NULL),
	(41, 0, 3, 4, '2021-04-25', 'NAO', 4, 349, '2021-04-25 18:13:45', 1, NULL, NULL),
	(42, 0, 3, 4, '2021-04-25', 'NAO', 4, 26904, '2021-04-25 19:48:10', 1, NULL, NULL),
	(43, 0, 3, 4, '2021-04-25', 'NAO', 4, 26903, '2021-04-25 20:54:45', 1, NULL, NULL),
	(44, 0, 3, 4, '2021-04-25', 'NAO', 4, 26910, '2021-04-25 22:36:28', 1, NULL, NULL),
	(45, 0, 3, 4, '2021-04-25', 'NAO', 4, 26910, '2021-04-25 22:36:37', 1, NULL, NULL),
	(46, 0, 4, 4, '2021-04-28', 'NAO', 4, 26910, '2021-04-28 21:35:25', 1, NULL, NULL),
	(47, 0, 4, 4, '2021-04-28', 'NAO', 4, 26909, '2021-04-28 21:36:05', 1, NULL, NULL),
	(48, 0, 4, 4, '2021-04-28', 'NAO', 4, 26903, '2021-04-28 21:36:25', 1, NULL, NULL),
	(49, 0, 4, 4, '2021-04-28', 'NAO', 4, 26905, '2021-04-28 21:36:42', 1, NULL, NULL),
	(50, 0, 3, 4, '2021-05-01', 'NAO', 4, 26910, '2021-05-01 12:58:17', 1, NULL, NULL),
	(51, 0, 3, 4, '2021-05-01', 'NAO', 4, 26907, '2021-05-01 12:58:39', 1, NULL, NULL),
	(52, 0, 3, 4, '2021-05-01', 'NAO', 3, 26910, '2021-05-01 12:59:49', 1, NULL, NULL),
	(53, 0, 3, 4, '2021-05-01', 'NAO', 4, 26910, '2021-05-01 13:46:14', 1, NULL, NULL),
	(54, 0, 3, 4, '2021-05-01', 'NAO', 3, 26904, '2021-05-01 14:02:51', 1, NULL, NULL),
	(55, 0, 4, 4, '2021-05-02', 'NAO', 4, 26910, '2021-05-02 12:16:28', 1, NULL, NULL),
	(56, 0, 4, 4, '2021-05-02', 'NAO', 4, 26909, '2021-05-02 12:17:22', 1, NULL, NULL),
	(57, 0, 4, 4, '2021-05-02', 'NAO', 11, 26765, '2021-05-02 12:18:04', 1, NULL, NULL),
	(58, 0, 4, 4, '2021-05-02', 'NAO', 4, 26904, '2021-05-02 12:23:58', 1, NULL, NULL),
	(59, 0, 3, 4, '2021-05-05', 'NAO', 3, 26911, '2021-05-05 11:41:27', 1, NULL, 'laboratorio'),
	(60, 0, 3, 4, '2021-05-05', 'NAO', 1, 26765, '2021-05-05 15:00:36', 1, NULL, NULL),
	(61, 0, 3, 4, '2021-05-05', 'NAO', 1, 26765, '2021-05-05 15:00:52', 1, NULL, NULL),
	(62, 0, 3, 4, '2021-05-05', 'NAO', 1, 22649, '2021-05-05 15:02:49', 1, NULL, NULL),
	(63, 0, 4, 4, '2021-05-05', 'NAO', 11, 22649, '2021-05-05 15:20:00', 1, NULL, NULL),
	(64, 0, 4, 4, '2021-05-05', 'NAO', 13, 26765, '2021-05-05 15:21:05', 1, NULL, NULL),
	(65, 0, 4, 4, '2021-02-05', 'NAO', 13, 21918, '2021-05-05 18:01:26', 1, NULL, NULL),
	(66, 0, 4, 4, '2021-02-01', 'NAO', 13, 26765, '2021-05-05 18:05:03', 1, NULL, NULL),
	(67, 0, 4, 4, '2021-01-01', 'NAO', 11, 26765, '2021-05-05 18:08:12', 1, NULL, NULL),
	(68, 0, 4, 4, '2021-02-01', 'NAO', 1, 26765, '2021-05-05 18:10:29', 1, NULL, NULL),
	(69, 0, 4, 4, '2021-02-01', 'NAO', 9, 26765, '2021-05-05 18:11:32', 1, NULL, NULL),
	(70, 0, 4, 4, '2021-01-01', 'NAO', 1, 26765, '2021-05-05 18:12:44', 1, NULL, NULL),
	(71, 0, 4, 4, '2021-05-05', 'NAO', 5, 26765, '2021-05-05 18:15:04', 1, NULL, NULL),
	(72, 0, 4, 4, '2021-02-01', 'NAO', 1, 26765, '2021-05-05 18:17:03', 1, NULL, NULL),
	(73, 0, 4, 4, '2021-01-01', 'NAO', 1, 26765, '2021-05-05 18:18:46', 1, NULL, NULL),
	(74, 0, 4, 4, '2021-02-01', 'NAO', 1, 26765, '2021-05-05 18:22:42', 1, NULL, NULL),
	(75, 0, 4, 4, '2021-01-01', 'NAO', 11, 26765, '2021-05-05 18:26:32', 1, NULL, NULL),
	(76, 0, 4, 4, '2021-02-01', 'NAO', 1, 26765, '2021-05-05 18:32:50', 1, NULL, NULL),
	(77, 0, 4, 4, '2021-01-05', 'NAO', 1, 22649, '2021-05-05 18:35:23', 1, NULL, NULL),
	(78, 0, 4, 4, '2021-02-01', 'NAO', 1, 26765, '2021-05-05 18:38:16', 1, NULL, NULL),
	(79, 0, 4, 4, '2021-05-05', 'NAO', 1, 26765, '2021-05-05 18:40:29', 1, NULL, NULL),
	(80, 0, 4, 4, '2021-05-05', 'NAO', 1, 26765, '2021-05-05 18:42:06', 1, NULL, NULL),
	(81, 0, 4, 4, '2021-05-05', 'NAO', 1, 26765, '2021-05-05 18:44:30', 1, NULL, NULL),
	(82, 0, 4, 4, '2021-05-05', 'NAO', 1, 26765, '2021-05-05 18:47:24', 1, NULL, NULL),
	(83, 0, 4, 4, '2021-05-05', 'NAO', 1, 26765, '2021-05-05 18:49:21', 1, NULL, NULL),
	(84, 0, 4, 4, '2021-05-05', 'NAO', 4, 26765, '2021-05-05 18:50:35', 1, NULL, NULL),
	(85, 0, 4, 4, '2021-05-05', 'NAO', 13, 26765, '2021-05-05 20:35:20', 1, NULL, NULL),
	(86, 0, 4, 4, '2021-05-04', 'NAO', 9, 26765, '2021-05-05 20:47:15', 1, NULL, NULL),
	(87, 0, 4, 4, '2021-01-04', 'NAO', 9, 26765, '2021-05-05 20:47:52', 1, NULL, NULL),
	(88, 0, 4, 4, '2021-05-05', 'NAO', 9, 26453, '2021-05-05 20:48:30', 1, NULL, NULL),
	(89, 0, 4, 4, '2021-05-05', 'NAO', 4, 26911, '2021-05-05 21:57:35', 1, NULL, NULL),
	(90, 0, 4, 4, '2021-05-05', 'NAO', 9, 26454, '2021-05-05 21:58:28', 1, NULL, NULL),
	(91, 0, 3, 4, '2021-05-06', 'NAO', 4, 26911, '2021-05-06 07:08:12', 1, NULL, NULL),
	(92, 0, 3, 4, '2021-05-06', 'NAO', 4, 26911, '2021-05-06 07:09:09', 1, NULL, NULL),
	(93, 0, 3, 4, '2021-05-06', 'NAO', 4, 26910, '2021-05-06 07:09:58', 1, NULL, NULL),
	(94, 0, 3, 4, '2021-05-06', 'NAO', 4, 26909, '2021-05-06 07:12:51', 1, NULL, NULL),
	(95, 0, 3, 4, '2021-05-06', 'NAO', 11, 26456, '2021-05-06 07:14:07', 1, NULL, NULL),
	(96, 0, 3, 4, '2021-05-06', 'NAO', 11, 22649, '2021-05-06 07:17:12', 1, NULL, NULL),
	(97, 0, 3, 4, '2021-05-08', 'NAO', 4, 26911, '2021-05-08 13:21:00', 1, NULL, NULL),
	(98, 0, 3, 4, '2021-05-10', 'NAO', 4, 26910, '2021-05-10 07:56:58', 1, NULL, NULL),
	(99, 0, 3, 4, '2021-05-10', 'NAO', 4, 26907, '2021-05-10 07:58:09', 1, NULL, NULL),
	(100, 0, 3, 4, '2021-05-10', 'NAO', 4, 26765, '2021-05-10 07:58:40', 1, NULL, NULL),
	(101, 0, 3, 4, '2021-05-10', 'NAO', 4, 26456, '2021-05-10 08:00:13', 1, NULL, NULL),
	(102, 0, 4, 4, '2021-05-10', 'NAO', 4, 26911, '2021-05-10 08:12:57', 1, NULL, NULL),
	(103, 0, 3, 4, '2021-05-11', 'NAO', 4, 26911, '2021-05-11 20:50:47', 1, NULL, 'laboratorio'),
	(104, 0, 3, 4, '2021-05-11', 'NAO', 5, 26910, '2021-05-11 20:58:59', 1, NULL, 'laboratorio'),
	(105, 0, 3, 4, '2021-05-11', 'NAO', 4, 26911, '2021-05-11 21:10:37', 1, NULL, NULL),
	(106, 0, 3, 4, '2021-05-12', 'NAO', 9, 26911, '2021-05-12 17:25:01', 1, NULL, NULL),
	(107, 0, 3, 4, '2021-05-12', 'NAO', 5, 26911, '2021-05-12 18:07:00', 1, NULL, NULL),
	(108, 0, 4, 4, '2021-05-13', 'NAO', 4, 26911, '2021-05-13 10:53:02', 1, NULL, NULL),
	(109, 0, 4, 4, '2021-05-13', 'NAO', 4, 26765, '2021-05-13 11:01:25', 1, NULL, NULL),
	(110, 0, 3, 4, '2021-05-15', 'NAO', 4, 26765, '2021-05-15 08:27:25', 1, NULL, 'laboratorio'),
	(111, 0, 3, 4, '2021-05-15', 'NAO', 1, 26912, '2021-05-15 09:04:18', 1, NULL, NULL),
	(112, 0, 3, 4, '2021-05-15', 'NAO', 4, 45, '2021-05-15 09:06:56', 1, NULL, NULL),
	(113, 0, 3, 4, '2021-05-15', 'NAO', 1, 26912, '2021-05-15 10:50:04', 1, NULL, NULL),
	(114, 0, 3, 4, '2021-05-15', 'NAO', 4, 67, '2021-05-15 10:55:38', 1, NULL, NULL),
	(115, 0, 3, 4, '2021-05-15', 'NAO', 1, 26912, '2021-05-15 11:20:10', 1, NULL, 'laboratorio'),
	(116, 0, 4, 4, '2021-05-15', 'NAO', 4, 26912, '2021-05-15 14:02:42', 1, NULL, NULL),
	(117, 0, 3, 4, '2021-05-15', 'NAO', 3, 26912, '2021-05-15 14:07:32', 1, NULL, NULL),
	(118, 0, 3, 4, '2021-05-15', 'NAO', 4, 26912, '2021-05-15 14:10:00', 1, NULL, NULL),
	(119, 0, 3, 4, '2021-05-15', 'NAO', 1, 26912, '2021-05-15 14:12:37', 1, NULL, NULL),
	(120, 0, 3, 4, '2021-05-15', 'NAO', 3, 26912, '2021-05-15 14:15:41', 1, NULL, NULL),
	(121, 0, 3, 4, '2021-05-15', 'NAO', 4, 26912, '2021-05-15 14:24:44', 1, NULL, NULL),
	(122, 0, 3, 4, '2021-05-15', 'NAO', 4, 26912, '2021-05-15 14:31:44', 1, NULL, NULL),
	(123, 690, 22, 1, '2021-05-15', 'SIM', 4, 67, '2021-05-15 15:25:33', 0, 'Teste', NULL),
	(124, 0, 3, 4, '2021-05-15', 'NAO', 4, 26912, '2021-05-15 20:26:51', 1, NULL, NULL),
	(125, 0, 3, 4, '2021-05-15', 'NAO', 4, 26912, '2021-05-15 20:53:30', 1, NULL, NULL),
	(126, 0, 3, 4, '2021-05-15', 'NAO', 4, 26911, '2021-05-15 20:56:27', 1, NULL, NULL),
	(127, 0, 3, 4, '2021-05-17', 'NAO', 4, 26912, '2021-05-17 18:49:48', 1, NULL, NULL),
	(128, 0, 3, 4, '2021-05-17', 'NAO', 4, 26911, '2021-05-17 18:50:15', 1, NULL, NULL),
	(129, 0, 3, 4, '2021-05-17', 'NAO', 4, 26765, '2021-05-17 18:50:57', 1, NULL, NULL),
	(130, 0, 3, 4, '2021-05-17', 'NAO', 4, 26456, '2021-05-17 18:51:13', 1, NULL, NULL),
	(131, 0, 4, 4, '2021-05-17', 'NAO', 1, 26912, '2021-05-17 21:28:01', 1, NULL, NULL),
	(132, 0, 4, 4, '2021-05-17', 'NAO', 4, 26912, '2021-05-17 21:41:18', 1, NULL, NULL),
	(133, 0, 4, 4, '2021-05-17', 'NAO', 4, 26911, '2021-05-17 21:41:55', 1, NULL, NULL),
	(134, 0, 4, 4, '2021-05-19', 'NAO', 4, 26912, '2021-05-19 20:08:37', 1, NULL, NULL),
	(135, 0, 4, 4, '2021-05-19', 'NAO', 3, 26911, '2021-05-19 20:11:13', 1, NULL, NULL),
	(136, 0, 3, 4, '2021-05-21', 'NAO', 4, 26912, '2021-05-21 20:07:59', 1, NULL, NULL),
	(137, 0, 4, 4, '2021-05-21', 'NAO', 3, 26912, '2021-05-21 20:10:51', 1, NULL, NULL),
	(138, 0, 4, 4, '2021-05-21', 'NAO', 3, 26912, '2021-05-21 20:14:10', 1, NULL, NULL),
	(139, 0, 3, 4, '2021-05-21', 'NAO', 4, 26912, '2021-05-21 20:21:23', 1, NULL, NULL),
	(140, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 22:57:42', 1, NULL, NULL),
	(141, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:00:22', 1, NULL, NULL),
	(142, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:02:42', 1, NULL, NULL),
	(143, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:06:06', 1, NULL, NULL),
	(144, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:08:42', 1, NULL, NULL),
	(145, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:09:25', 1, NULL, NULL),
	(146, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:09:46', 1, NULL, NULL),
	(147, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:11:32', 1, NULL, NULL),
	(148, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 23:12:45', 1, NULL, NULL),
	(149, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 23:16:58', 1, NULL, NULL),
	(150, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 23:19:48', 1, NULL, NULL),
	(151, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 23:20:31', 1, NULL, NULL),
	(152, 0, 4, 4, '2021-05-21', 'NAO', 4, 26912, '2021-05-21 23:21:19', 1, NULL, NULL),
	(153, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 23:23:29', 1, NULL, NULL),
	(154, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 23:24:07', 1, NULL, NULL),
	(155, 0, 4, 4, '2021-05-21', 'NAO', 4, 26765, '2021-05-21 23:27:08', 1, NULL, NULL),
	(156, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:28:46', 1, NULL, NULL),
	(157, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:33:47', 1, NULL, NULL),
	(158, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:35:27', 1, NULL, NULL),
	(159, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:36:45', 1, NULL, NULL),
	(160, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:38:48', 1, NULL, NULL),
	(161, 0, 4, 4, '2021-05-21', 'NAO', 1, 26912, '2021-05-21 23:39:43', 1, NULL, NULL),
	(162, 0, 4, 4, '2021-05-21', 'NAO', 1, 26912, '2021-05-21 23:39:46', 1, NULL, NULL),
	(163, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:41:16', 1, NULL, NULL),
	(164, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:43:22', 1, NULL, NULL),
	(165, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:49:22', 1, NULL, NULL),
	(166, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:50:24', 1, NULL, NULL),
	(167, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:55:18', 1, NULL, NULL),
	(168, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:56:45', 1, NULL, NULL),
	(169, 0, 4, 4, '2021-05-21', 'NAO', 1, 26765, '2021-05-21 23:57:55', 1, NULL, NULL),
	(170, 0, 4, 4, '2021-05-22', 'NAO', 1, 26765, '2021-05-22 00:00:44', 1, NULL, NULL),
	(171, 0, 4, 4, '2021-05-22', 'NAO', 1, 26765, '2021-05-22 00:39:00', 1, NULL, 'laboratorio'),
	(172, 0, 4, 4, '2021-05-22', 'NAO', 4, 26912, '2021-05-22 10:36:34', 1, NULL, 'laboratorio'),
	(173, 0, 3, 4, '2021-05-22', 'NAO', 1, 26911, '2021-05-22 20:56:20', 1, NULL, 'laboratorio'),
	(174, 0, 4, 4, '2021-05-25', 'NAO', 1, 26912, '2021-05-25 12:50:14', 1, NULL, NULL),
	(175, 0, 4, 4, '2021-05-25', 'NAO', 1, 26910, '2021-05-25 12:50:54', 1, NULL, NULL),
	(176, 0, 4, 4, '2021-05-25', 'NAO', 1, 26765, '2021-05-25 12:51:57', 1, NULL, NULL),
	(177, 0, 4, 4, '2021-05-25', 'NAO', 1, 26456, '2021-05-25 12:52:25', 1, NULL, NULL);
/*!40000 ALTER TABLE `examesporfazer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;