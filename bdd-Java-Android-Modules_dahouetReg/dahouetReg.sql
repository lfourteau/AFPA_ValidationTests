-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 03, 2017 at 12:04 PM
-- Server version: 5.7.17-0ubuntu0.16.04.1
-- PHP Version: 7.0.15-0ubuntu0.16.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dahouetReg`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCommissaires` (IN `startDate` DATE, IN `endDate` DATE)  BEGIN
Select p.per_nom, p.per_prenom, com_com.cmt_com_nom
from personne p
inner join commissaire c on p.per_id = c.per_id
inner join comite_commissaire com_com on c.cmt_com_id = com_com.cmt_com_id
inner join comite_course com_cou on c.com_id = com_cou.com_id
inner join regate r on com_cou.reg_id = r.reg_id
inner join challenge cha on r.cha_id = cha.cha_id
where cha.cha_date_debut = startDate and cha.cha_date_fin = endDate;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getEquipageVoilier` (IN `voi_id` INT(3), IN `reg_id` INT(3))  BEGIN

select per.per_nom, per.per_prenom, p.voi_skipper_id
from participation_voilier p
inner join equipier e on p.par_voi_id = e.par_voi_id
inner join  participant par on e.par_id = par.par_id
inner join personne per on par.per_id = per.per_id
where p.voi_id = voi_id
and p.reg_id = reg_id;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getRegDistMoy` (IN `cha_id` INT(1))  BEGIN
SELECT c.cha_nom, AVG(r.reg_distance) as moy_dist_reg
from challenge c
INNER JOIN regate r ON c.cha_id = r.cha_id
where c.cha_id = cha_id
group BY c.cha_nom;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `getRegCode` (`reg_id` INT(4)) RETURNS VARCHAR(10) CHARSET latin1 BEGIN
DECLARE regCode varchar(10);
DECLARE chaCode int(1);
DECLARE regMois int(2);
DECLARE numSeq int(2);

SELECT r.cha_id into chaCode from regate r where r.reg_id = reg_id;
SELECT MONTH(r.reg_date) into regMois from regate r where r.reg_id = reg_id;
SELECT COUNT(DISTINCT(r.reg_id)) into numSeq FROM regate r WHERE r.cha_id = chaCode;

SET regCode = CONCAT(chaCode, regMois, numSeq);

RETURN regCode;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `challenge`
--

CREATE TABLE `challenge` (
  `cha_id` int(11) NOT NULL,
  `cha_nom` varchar(25) DEFAULT NULL,
  `cha_date_debut` date DEFAULT NULL,
  `cha_date_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `challenge`
--

INSERT INTO `challenge` (`cha_id`, `cha_nom`, `cha_date_debut`, `cha_date_fin`) VALUES
(1, 'été', '2017-05-01', '2017-09-30'),
(2, 'hiver', '2016-11-01', '2017-03-31');

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

CREATE TABLE `classe` (
  `cla_id` int(11) NOT NULL,
  `cla_coefficient` decimal(6,2) DEFAULT NULL,
  `cla_nom` varchar(25) DEFAULT NULL,
  `ser_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classe`
--

INSERT INTO `classe` (`cla_id`, `cla_coefficient`, `cla_nom`, `ser_id`) VALUES
(6, NULL, 'Corsaire', 1),
(7, NULL, 'Surprise', 1),
(8, NULL, '8 metres', 1),
(9, NULL, 'Maraudeur', 1),
(10, NULL, 'Figaro', 1),
(11, NULL, 'Flying Fifteen', 2),
(12, NULL, 'Soling', 2),
(13, NULL, 'Star', 2),
(14, NULL, 'Tempest', 2),
(15, NULL, 'Yngling', 2),
(16, NULL, '5.5', 2);

-- --------------------------------------------------------

--
-- Table structure for table `club_nautique`
--

CREATE TABLE `club_nautique` (
  `clu_id` int(11) NOT NULL,
  `clu_nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `club_nautique`
--

INSERT INTO `club_nautique` (`clu_id`, `clu_nom`) VALUES
(1, 'Club Brestois'),
(2, 'Club morlaisien'),
(3, 'Club lorientais'),
(4, 'Club de la Baie '),
(5, 'Club Rennais');

-- --------------------------------------------------------

--
-- Table structure for table `comite_commissaire`
--

CREATE TABLE `comite_commissaire` (
  `cmt_com_id` int(11) NOT NULL,
  `cmt_com_nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comite_commissaire`
--

INSERT INTO `comite_commissaire` (`cmt_com_id`, `cmt_com_nom`) VALUES
(1, 'Comité de Bretagne'),
(2, 'Comité d\'Ile de France'),
(3, 'Comité des Pays de Loire'),
(4, 'Comité du Poitou-Charente'),
(5, 'Comité d\'Aquitaine'),
(6, 'Comité de ');

-- --------------------------------------------------------

--
-- Table structure for table `comite_course`
--

CREATE TABLE `comite_course` (
  `com_id` int(11) NOT NULL,
  `reg_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comite_course`
--

INSERT INTO `comite_course` (`com_id`, `reg_id`) VALUES
(2, 3),
(3, 3),
(1, 2),
(3, 1),
(2, 13),
(2, 20),
(3, 13),
(1, 15),
(3, 16);

-- --------------------------------------------------------

--
-- Table structure for table `commissaire`
--

CREATE TABLE `commissaire` (
  `com_id` int(11) NOT NULL,
  `cmt_com_id` int(11) NOT NULL,
  `per_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commissaire`
--

INSERT INTO `commissaire` (`com_id`, `cmt_com_id`, `per_id`) VALUES
(1, 1, 10),
(2, 2, 9),
(3, 4, 8);

-- --------------------------------------------------------

--
-- Table structure for table `equipier`
--

CREATE TABLE `equipier` (
  `equ_id` int(11) NOT NULL,
  `par_id` int(11) NOT NULL,
  `par_voi_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipier`
--

INSERT INTO `equipier` (`equ_id`, `par_id`, `par_voi_id`) VALUES
(1, 4, 12),
(2, 5, 16),
(3, 6, 11);

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

CREATE TABLE `participant` (
  `par_id` int(11) NOT NULL,
  `per_id` int(11) NOT NULL,
  `par_numero_ffv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participant`
--

INSERT INTO `participant` (`par_id`, `per_id`, `par_numero_ffv`) VALUES
(1, 1, 54565),
(2, 2, 65654),
(3, 3, 56645),
(4, 4, 35465),
(5, 5, 64665),
(6, 6, 68463);

-- --------------------------------------------------------

--
-- Table structure for table `participation_voilier`
--

CREATE TABLE `participation_voilier` (
  `par_voi_id` int(11) NOT NULL,
  `voi_id` int(11) NOT NULL,
  `reg_id` int(11) NOT NULL,
  `voi_skipper_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participation_voilier`
--

INSERT INTO `participation_voilier` (`par_voi_id`, `voi_id`, `reg_id`, `voi_skipper_id`) VALUES
(11, 11, 3, 1),
(12, 12, 3, 3),
(13, 13, 3, 2),
(14, 14, 3, 2),
(15, 15, 13, 2),
(16, 13, 17, 1),
(17, 11, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `per_id` int(11) NOT NULL,
  `per_nom` varchar(50) DEFAULT NULL,
  `per_prenom` varchar(50) DEFAULT NULL,
  `per_date_naissance` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`per_id`, `per_nom`, `per_prenom`, `per_date_naissance`) VALUES
(1, 'Fourteau', 'Lucas', '2017-12-21'),
(2, 'Balcon', 'Yoann', '2017-02-10'),
(3, 'Le Henaff', 'Gwenolé', '2017-02-15'),
(4, 'Nicolas ', 'Rodriguez', '2017-02-17'),
(5, 'Trompette', 'Tanguy', '2017-02-03'),
(6, 'Sonia', 'Hervochon', '2017-02-15'),
(7, 'Carré', 'Lydie', '2017-02-10'),
(8, 'Ursache', 'Ovidiu', '2017-02-07'),
(9, 'Lelu', 'Florent', '2017-02-16'),
(10, 'Jezecquel', 'Margaux', '2017-02-02'),
(11, 'Goyot', 'Gaetane', '2017-02-08'),
(12, 'Bur', 'Nathalie', '2017-02-02'),
(19, 'qzdzqdqz', 'dqzdzqdzqd', '1221-12-21');

-- --------------------------------------------------------

--
-- Table structure for table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `pro_id` int(11) NOT NULL,
  `per_id` int(11) DEFAULT NULL,
  `clu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proprietaire`
--

INSERT INTO `proprietaire` (`pro_id`, `per_id`, `clu_id`) VALUES
(4, 7, 3),
(5, 11, 4),
(6, 12, 5),
(10, 8, 2),
(13, 19, 1);

-- --------------------------------------------------------

--
-- Table structure for table `regate`
--

CREATE TABLE `regate` (
  `reg_id` int(11) NOT NULL,
  `reg_libelle` varchar(150) NOT NULL,
  `reg_date` date DEFAULT NULL,
  `reg_distance` decimal(6,2) NOT NULL,
  `cha_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `regate`
--

INSERT INTO `regate` (`reg_id`, `reg_libelle`, `reg_date`, `reg_distance`, `cha_id`) VALUES
(1, 'Regate d\'hiver de la Baie 1', '2016-11-05', '5.65', 2),
(2, 'Regate d\'hiver de la Baie 2', '2016-11-12', '6.26', 2),
(3, 'Regate d\'hiver de la Baie 3', '2016-11-19', '5.25', 2),
(4, 'Regate d\'hiver de la Baie 4', '2016-11-26', '3.36', 2),
(5, 'Regate d\'hiver de la Baie 5', '2016-12-03', '5.58', 2),
(6, 'Regate d\'hiver de la Baie 6', '2016-12-10', '9.20', 2),
(7, 'Regate d\'hiver de la Baie 7', '2016-12-17', '7.50', 2),
(8, 'Regate d\'hiver de la Baie 8', '2017-01-14', '6.84', 2),
(9, 'Regate d\'hiver de la Baie 9', '2017-01-28', '6.20', 2),
(10, 'Regate d\'hiver de la Baie 10', '2017-02-18', '9.69', 2),
(11, 'Regate d\'hiver de la Baie 11', '2017-03-11', '7.58', 2),
(13, 'Regate d\'été de la Baie 1', '2017-05-14', '5.69', 1),
(14, 'Regate d\'été de la Baie 2', '2017-05-28', '6.69', 1),
(15, 'Regate d\'été de la Baie 3', '2017-06-11', '8.25', 1),
(16, 'Regate d\'été de la Baie 4', '2017-06-18', '4.36', 1),
(17, 'Regate d\'été de la Baie 5', '2017-07-02', '5.45', 1),
(18, 'Regate d\'été de la Baie 6', '2017-07-16', '5.36', 1),
(19, 'Regate d\'été de la Baie 7', '2017-07-30', '5.69', 1),
(20, 'Regate d\'été de la Baie 8', '2017-08-06', '7.56', 1),
(21, 'Regate d\'été de la Baie 9', '2017-08-20', '9.35', 1),
(22, 'Regate d\'été de la Baie 10', '2017-09-03', '9.23', 1),
(23, 'Regate d\'été de la Baie 11', '2017-09-17', '4.65', 1),
(24, 'Regate d\'été de la Baie 12', '2017-09-24', '8.88', 1);

--
-- Triggers `regate`
--
DELIMITER $$
CREATE TRIGGER `regate_BEFORE_DELETE` BEFORE DELETE ON `regate` FOR EACH ROW BEGIN
declare currentDate DATE;
declare endingDateCha Date;
declare msg varchar(120);

SET currentDate = CURDATE();
SELECT cha_date_fin into endingDateCha from challenge where cha_id = old.cha_id;

IF currentDate < endingDateCha THEN 
set msg = 'Impossible de supprimer cette régate, le challenge est toujours en cours ou n'est pas commencé';
		signal sqlstate '45001' set message_text = msg;    
    END IF;    
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `regate_BEFORE_INSERT` BEFORE INSERT ON `regate` FOR EACH ROW BEGIN
    declare testingDate DATE;
    declare startingDate DATE;
    declare endingDate DATE;
    declare msg varchar(100);
	
    select cha_date_debut into startingDate from challenge where cha_id = new.cha_id;
    select cha_date_fin into endingDate from challenge where cha_id = new.cha_id;    
    
    IF startingDate > new.reg_date OR endingDate < new.reg_date THEN
    set msg = 'La date ne correspond pas';
	signal sqlstate '45000' set message_text = msg, MYSQL_ERRNO = 45000;
    END IF;
    
       
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `resultat`
--

CREATE TABLE `resultat` (
  `res_id` int(11) NOT NULL,
  `res_temps` datetime DEFAULT NULL,
  `res_points` int(11) DEFAULT NULL,
  `par_voi_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resultat`
--

INSERT INTO `resultat` (`res_id`, `res_temps`, `res_points`, `par_voi_id`) VALUES
(23, '2017-02-22 03:14:22', 1, 11),
(24, '2017-02-22 03:29:32', 2, 12),
(25, '2017-02-22 03:50:47', 3, 13),
(26, '2017-02-22 04:18:11', 4, 14),
(27, '2017-02-22 03:18:12', 1, 15),
(28, '2017-02-22 04:17:08', 2, 16),
(29, '2017-02-15 07:14:18', 5, 17);

--
-- Triggers `resultat`
--
DELIMITER $$
CREATE TRIGGER `resultat_BEFORE_INSERT` BEFORE INSERT ON `resultat` FOR EACH ROW BEGIN
	declare nbPartVoi int(3);	
	declare tempRegId int(3);
	declare msg varchar(120);

	select p.reg_id into tempRegId from resultat r inner join participation_voilier p on p.par_voi_id = r.par_voi_id where r.par_voi_id = new.par_voi_id group by p.reg_id;
	select count(distinct(voi_id)) into nbPartVoi from participation_voilier where reg_id = tempRegId;
	

	IF new.res_points > nbPartVoi THEN
		set msg = 'Il y a trop de resultats par rapport au nombre de participants';
		signal sqlstate '45001' set message_text = msg;    
    END IF;    

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `resultat_BEFORE_UPDATE` BEFORE UPDATE ON `resultat` FOR EACH ROW BEGIN
	declare nbPartVoi int(3);	
	declare tempRegId int(3);
	declare msg varchar(120);

	select p.reg_id into tempRegId from resultat r inner join participation_voilier p on p.par_voi_id = r.par_voi_id where r.par_voi_id = new.par_voi_id group by p.reg_id;
	select count(distinct(voi_id)) into nbPartVoi from participation_voilier where reg_id = tempRegId;
	

	IF new.res_points > nbPartVoi THEN
		set msg = 'Il y a trop de resultats par rapport au nombre de participants';
		signal sqlstate '45001' set message_text = msg;    
    END IF;    
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `serie`
--

CREATE TABLE `serie` (
  `ser_id` int(11) NOT NULL,
  `ser_nom` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `serie`
--

INSERT INTO `serie` (`ser_id`, `ser_nom`) VALUES
(1, 'Habitables'),
(2, 'Quillard');

-- --------------------------------------------------------

--
-- Table structure for table `voilier`
--

CREATE TABLE `voilier` (
  `voi_id` int(11) NOT NULL,
  `voi_num_voile` int(11) DEFAULT NULL,
  `voi_nom` varchar(70) NOT NULL,
  `cla_id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `voilier`
--

INSERT INTO `voilier` (`voi_id`, `voi_num_voile`, `voi_nom`, `cla_id`, `pro_id`) VALUES
(11, 656466, 'L\'écume des jours', 7, 4),
(12, 365465, 'Annytia', 12, 5),
(13, 564665, 'Carmalia', 13, 6),
(14, 646866, 'Mor-Braz', 13, 5),
(15, 546466, 'L\'héliotrope', 12, 5),
(16, 65156, 'qzdqzd', 6, 4),
(17, 556465, 'dqsdsq', 6, 4),
(18, 65464, 'sqcqssc', 8, 4),
(19, 5616516, 'qzdqzdqzd', 14, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `challenge`
--
ALTER TABLE `challenge`
  ADD PRIMARY KEY (`cha_id`);

--
-- Indexes for table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`cla_id`),
  ADD KEY `FK_classe_ser_id` (`ser_id`);

--
-- Indexes for table `club_nautique`
--
ALTER TABLE `club_nautique`
  ADD PRIMARY KEY (`clu_id`);

--
-- Indexes for table `comite_commissaire`
--
ALTER TABLE `comite_commissaire`
  ADD PRIMARY KEY (`cmt_com_id`);

--
-- Indexes for table `comite_course`
--
ALTER TABLE `comite_course`
  ADD KEY `reg_id` (`reg_id`),
  ADD KEY `com_id` (`com_id`);

--
-- Indexes for table `commissaire`
--
ALTER TABLE `commissaire`
  ADD PRIMARY KEY (`com_id`),
  ADD KEY `FK_commissaire_per_id` (`per_id`),
  ADD KEY `cmt_id` (`cmt_com_id`),
  ADD KEY `com_id` (`com_id`);

--
-- Indexes for table `equipier`
--
ALTER TABLE `equipier`
  ADD PRIMARY KEY (`equ_id`),
  ADD KEY `par_id` (`par_id`),
  ADD KEY `par_voi_id` (`par_voi_id`);

--
-- Indexes for table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`par_id`),
  ADD KEY `per_id` (`per_id`);

--
-- Indexes for table `participation_voilier`
--
ALTER TABLE `participation_voilier`
  ADD PRIMARY KEY (`par_voi_id`),
  ADD KEY `voi_id` (`voi_id`),
  ADD KEY `reg_id` (`reg_id`),
  ADD KEY `voi_skipper_id` (`voi_skipper_id`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`per_id`);

--
-- Indexes for table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`pro_id`),
  ADD KEY `clu_id` (`clu_id`),
  ADD KEY `per_id` (`per_id`);

--
-- Indexes for table `regate`
--
ALTER TABLE `regate`
  ADD PRIMARY KEY (`reg_id`),
  ADD KEY `FK_regate_cha_id` (`cha_id`);

--
-- Indexes for table `resultat`
--
ALTER TABLE `resultat`
  ADD PRIMARY KEY (`res_id`),
  ADD KEY `voi_id` (`par_voi_id`);

--
-- Indexes for table `serie`
--
ALTER TABLE `serie`
  ADD PRIMARY KEY (`ser_id`);

--
-- Indexes for table `voilier`
--
ALTER TABLE `voilier`
  ADD PRIMARY KEY (`voi_id`),
  ADD KEY `pro_id` (`pro_id`),
  ADD KEY `pro_id_2` (`pro_id`),
  ADD KEY `cla_id` (`cla_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `challenge`
--
ALTER TABLE `challenge`
  MODIFY `cha_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `classe`
--
ALTER TABLE `classe`
  MODIFY `cla_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `club_nautique`
--
ALTER TABLE `club_nautique`
  MODIFY `clu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `comite_commissaire`
--
ALTER TABLE `comite_commissaire`
  MODIFY `cmt_com_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `commissaire`
--
ALTER TABLE `commissaire`
  MODIFY `com_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `equipier`
--
ALTER TABLE `equipier`
  MODIFY `equ_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `participant`
--
ALTER TABLE `participant`
  MODIFY `par_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `participation_voilier`
--
ALTER TABLE `participation_voilier`
  MODIFY `par_voi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `per_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `proprietaire`
--
ALTER TABLE `proprietaire`
  MODIFY `pro_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `regate`
--
ALTER TABLE `regate`
  MODIFY `reg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `resultat`
--
ALTER TABLE `resultat`
  MODIFY `res_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `serie`
--
ALTER TABLE `serie`
  MODIFY `ser_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `voilier`
--
ALTER TABLE `voilier`
  MODIFY `voi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `FK_classe_ser_id` FOREIGN KEY (`ser_id`) REFERENCES `serie` (`ser_id`);

--
-- Constraints for table `comite_course`
--
ALTER TABLE `comite_course`
  ADD CONSTRAINT `comite_course_ibfk_1` FOREIGN KEY (`com_id`) REFERENCES `commissaire` (`com_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comite_course_ibfk_2` FOREIGN KEY (`reg_id`) REFERENCES `regate` (`reg_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `commissaire`
--
ALTER TABLE `commissaire`
  ADD CONSTRAINT `FK_commissaire_per_id` FOREIGN KEY (`per_id`) REFERENCES `personne` (`per_id`),
  ADD CONSTRAINT `commissaire_ibfk_1` FOREIGN KEY (`cmt_com_id`) REFERENCES `comite_commissaire` (`cmt_com_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `equipier`
--
ALTER TABLE `equipier`
  ADD CONSTRAINT `equipier_ibfk_1` FOREIGN KEY (`par_id`) REFERENCES `participant` (`par_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `equipier_ibfk_2` FOREIGN KEY (`par_voi_id`) REFERENCES `participation_voilier` (`par_voi_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`per_id`) REFERENCES `personne` (`per_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `participation_voilier`
--
ALTER TABLE `participation_voilier`
  ADD CONSTRAINT `participation_voilier_ibfk_2` FOREIGN KEY (`reg_id`) REFERENCES `regate` (`reg_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `participation_voilier_ibfk_3` FOREIGN KEY (`voi_skipper_id`) REFERENCES `participant` (`par_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `participation_voilier_ibfk_4` FOREIGN KEY (`voi_id`) REFERENCES `voilier` (`voi_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD CONSTRAINT `proprietaire_ibfk_1` FOREIGN KEY (`clu_id`) REFERENCES `club_nautique` (`clu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proprietaire_ibfk_2` FOREIGN KEY (`per_id`) REFERENCES `personne` (`per_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `regate`
--
ALTER TABLE `regate`
  ADD CONSTRAINT `FK_regate_cha_id` FOREIGN KEY (`cha_id`) REFERENCES `challenge` (`cha_id`);

--
-- Constraints for table `resultat`
--
ALTER TABLE `resultat`
  ADD CONSTRAINT `resultat_ibfk_1` FOREIGN KEY (`par_voi_id`) REFERENCES `participation_voilier` (`par_voi_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `voilier`
--
ALTER TABLE `voilier`
  ADD CONSTRAINT `voilier_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `proprietaire` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `voilier_ibfk_2` FOREIGN KEY (`cla_id`) REFERENCES `classe` (`cla_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
