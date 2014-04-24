-- phpMyAdmin SQL Dump
-- version 4.1.8
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 25 Mars 2014 à 10:26
-- Version du serveur :  5.5.36-MariaDB-log
-- Version de PHP :  5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `JavaProject`
--

-- --------------------------------------------------------

--
-- Structure de la table `AnneeEtude`
--

CREATE TABLE IF NOT EXISTS `AnneeEtude` (
  `IdAnneeEtude` int(11) NOT NULL,
  `Annee` int(11) NOT NULL,
  `CodeSection` varchar(2) NOT NULL,
  PRIMARY KEY (`IdAnneeEtude`),
  KEY `REF_Annee_Secti_FK` (`CodeSection`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `AnneeEtude`
--

INSERT INTO `AnneeEtude` (`IdAnneeEtude`, `Annee`, `CodeSection`) VALUES
(1, 1, 'TI'),
(2, 2, 'TI'),
(3, 3, 'TI'),
(4, 1, 'IG'),
(5, 2, 'IG'),
(6, 3, 'IG'),
(7, 1, 'CP'),
(8, 2, 'CP'),
(9, 3, 'CP'),
(10, 1, 'MK'),
(11, 2, 'MK'),
(12, 3, 'MK');

-- --------------------------------------------------------

--
-- Structure de la table `Editeur`
--

CREATE TABLE IF NOT EXISTS `Editeur` (
  `CodeEdit` varchar(5) NOT NULL,
  `Designation` varchar(100) NOT NULL,
  `RueNumero` varchar(100) NOT NULL,
  `CodePostal` int(11) NOT NULL,
  `Localite` varchar(100) NOT NULL,
  `URL` varchar(100) NOT NULL,
  `NomContactComm` varchar(100) DEFAULT NULL,
  `EmailComm` varchar(100) DEFAULT NULL,
  `TelComm` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CodeEdit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Editeur`
--

INSERT INTO `Editeur` (`CodeEdit`, `Designation`, `RueNumero`, `CodePostal`, `Localite`, `URL`, `NomContactComm`, `EmailComm`, `TelComm`) VALUES
('Ed01', 'Microsoft', 'rue haute, 32', 1000, 'Bruxelles', 'Microsoft.be', 'Bill', 'emailBill', '+322459876'),
('Ed02', 'Oracle', 'rue basse, 4569', 4000, 'Liège', 'Oracle.be', 'James', NULL, NULL),
('Ed03', 'Bob Software', 'rue plane, 2332', 1000, 'Bruxelles', 'Bob.be', 'Laurent', NULL, NULL),
('Ed04', 'Real Software', 'Kenedy street, 332', 1000, 'New York', 'RealSoftware.us', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `FamilleSoftware`
--

CREATE TABLE IF NOT EXISTS `FamilleSoftware` (
  `IdFamSoft` int(11) NOT NULL,
  `Libelle` varchar(100) NOT NULL,
  PRIMARY KEY (`IdFamSoft`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `FamilleSoftware`
--

INSERT INTO `FamilleSoftware` (`IdFamSoft`, `Libelle`) VALUES
(1, 'Logiciel Comptable'),
(2, 'Atelier génie logiciel'),
(3, 'Atelier Dévelop logiciel'),
(4, 'Gestion BD end user'),
(5, 'Gestion BD professionnel'),
(6, 'Compilateur'),
(7, 'Envir. dévelop. Java'),
(8, 'Monitoring réseaux'),
(9, 'Design électronique'),
(10, 'Bureautique');

-- --------------------------------------------------------

--
-- Structure de la table `Fournisseur`
--

CREATE TABLE IF NOT EXISTS `Fournisseur` (
  `CodeFourn` varchar(5) NOT NULL,
  `Designation` varchar(100) NOT NULL,
  `URL` varchar(100) NOT NULL,
  `NomContactTech` varchar(100) DEFAULT NULL,
  `EmailTech` varchar(100) DEFAULT NULL,
  `TelTech` varchar(20) DEFAULT NULL,
  `NomContactComm` varchar(100) DEFAULT NULL,
  `EmailComm` varchar(100) DEFAULT NULL,
  `TelComm` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CodeFourn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Fournisseur`
--

INSERT INTO `Fournisseur` (`CodeFourn`, `Designation`, `URL`, `NomContactTech`, `EmailTech`, `TelTech`, `NomContactComm`, `EmailComm`, `TelComm`) VALUES
('Fou1', 'Priminfo', 'Priminfo.be', 'Dupond', 'emailDupond', '081458967', NULL, NULL, NULL),
('Fou2', 'Flag2000', 'Flag2000.be', 'Durand', 'emailDurand', '042569875', 'Albert', 'emailAlbert', '0479698547'),
('Fou3', 'Adam''s Computer', 'AdamComputer.be', NULL, NULL, NULL, 'Jules', 'emailJules', '02548796');

-- --------------------------------------------------------

--
-- Structure de la table `Installation`
--

CREATE TABLE IF NOT EXISTS `Installation` (
  `IdInstallation` int(11) NOT NULL,
  `DateInstallation` date NOT NULL,
  `Commentaires` varchar(100) DEFAULT NULL,
  `DureeInstallation` int(11) NOT NULL,
  `RefProcedureInstallation` varchar(50) DEFAULT NULL,
  `CodeSoftware` varchar(10) NOT NULL,
  `Matricule` varchar(10) NOT NULL,
  `CodeOS` varchar(10) NOT NULL,
  PRIMARY KEY (`IdInstallation`),
  KEY `REF_Insta_Softw_FK` (`CodeSoftware`),
  KEY `REF_Insta_Respo_FK` (`Matricule`),
  KEY `REF_Insta_OS_FK` (`CodeOS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Installation`
--

INSERT INTO `Installation` (`IdInstallation`, `DateInstallation`, `Commentaires`, `DureeInstallation`, `RefProcedureInstallation`, `CodeSoftware`, `Matricule`, `CodeOS`) VALUES
(1, '0000-00-00', 'Sans problème', 20, NULL, 'NB02', 'AVK', 'W7ProfEn'),
(2, '0000-00-00', 'Trois essais', 120, 'Procedure num 125', 'Or11', 'AlBa', 'W8ProfFr'),
(3, '0000-00-00', NULL, 100, NULL, 'Vs12', 'MarGob', 'W8ProfEn');

-- --------------------------------------------------------

--
-- Structure de la table `OS`
--

CREATE TABLE IF NOT EXISTS `OS` (
  `CodeOS` varchar(10) NOT NULL,
  `Libelle` varchar(30) NOT NULL,
  `Version` varchar(20) NOT NULL,
  PRIMARY KEY (`CodeOS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `OS`
--

INSERT INTO `OS` (`CodeOS`, `Libelle`, `Version`) VALUES
('Fedora', 'Fedora 2012', '2012'),
('Mint', 'Linux Mint', '2011'),
('RedHat8', 'Red Hat 8 Linux EN', '2011'),
('Ubuntu', 'Ubuntu 2012', '12,04'),
('W7ProfEn', 'Windows 7 Professional English', 'v7'),
('W8ProfEn', 'Windows 8 Professional English', 'v8.1'),
('W8ProfFr', 'Windows 8 Prof Français', 'v8');

-- --------------------------------------------------------

--
-- Structure de la table `Professeur`
--

CREATE TABLE IF NOT EXISTS `Professeur` (
  `CodeProf` varchar(5) NOT NULL,
  `NomPrenom` varchar(100) NOT NULL,
  `CodeSection` varchar(2) NOT NULL,
  PRIMARY KEY (`CodeProf`),
  KEY `REF_Profe_Secti_FK` (`CodeSection`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Professeur`
--

INSERT INTO `Professeur` (`CodeProf`, `NomPrenom`, `CodeSection`) VALUES
('a4g', 'Anthony Grévisse', 'MK'),
('ch2', 'Isabelle Charlier', 'IG'),
('fb1', 'Fabian Restiaux', 'TI'),
('g5c', 'Christiane Glime', 'IG'),
('gw6', 'Geneviève Wiame', 'CP'),
('tf3', 'Fabrice Triquet', 'TI');

-- --------------------------------------------------------

--
-- Structure de la table `ResponsableReseaux`
--

CREATE TABLE IF NOT EXISTS `ResponsableReseaux` (
  `Matricule` varchar(10) NOT NULL,
  `NomPrenom` varchar(100) NOT NULL,
  PRIMARY KEY (`Matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `ResponsableReseaux`
--

INSERT INTO `ResponsableReseaux` (`Matricule`, `NomPrenom`) VALUES
('AlBa', 'Alexandre Baligant'),
('AVK', 'André Van Kerrebroeck'),
('MarGob', 'Marvin Gobin');

-- --------------------------------------------------------

--
-- Structure de la table `Section`
--

CREATE TABLE IF NOT EXISTS `Section` (
  `CodeSection` varchar(2) NOT NULL,
  `Libelle` varchar(100) NOT NULL,
  PRIMARY KEY (`CodeSection`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Section`
--

INSERT INTO `Section` (`CodeSection`, `Libelle`) VALUES
('AU', 'Automatique'),
('CP', 'Comptabilité'),
('DR', 'Droit'),
('IG', 'Informatique de gestion'),
('MK', 'Marketing'),
('TI', 'Technologie informatique');

-- --------------------------------------------------------

--
-- Structure de la table `Software`
--

CREATE TABLE IF NOT EXISTS `Software` (
  `CodeSoftware` varchar(10) NOT NULL,
  `Nom` varchar(30) NOT NULL,
  `Version` varchar(20) NOT NULL,
  `DateAcquisition` date NOT NULL,
  `CoutAcquisition` double NOT NULL,
  `CodeInstallation` varchar(200) DEFAULT NULL,
  `CleInstallation` varchar(100) DEFAULT NULL,
  `CapaciteDisqueMin` int(11) DEFAULT NULL,
  `MemoireViveMin` int(11) DEFAULT NULL,
  `ProcesseurMin` varchar(20) DEFAULT NULL,
  `MemCarteVideoMin` int(11) DEFAULT NULL,
  `CarteSonExigee` bit(1) NOT NULL,
  `ReseauLocal` bit(1) NOT NULL,
  `CodeEdit` varchar(5) NOT NULL,
  `IdFamSoft` int(11) NOT NULL,
  `CodeFourn` varchar(5) NOT NULL,
  PRIMARY KEY (`CodeSoftware`),
  KEY `REF_Softw_Edite_FK` (`CodeEdit`),
  KEY `REF_Softw_Famil_FK` (`IdFamSoft`),
  KEY `REF_Softw_Fourn_FK` (`CodeFourn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Software`
--

INSERT INTO `Software` (`CodeSoftware`, `Nom`, `Version`, `DateAcquisition`, `CoutAcquisition`, `CodeInstallation`, `CleInstallation`, `CapaciteDisqueMin`, `MemoireViveMin`, `ProcesseurMin`, `MemCarteVideoMin`, `CarteSonExigee`, `ReseauLocal`, `CodeEdit`, `IdFamSoft`, `CodeFourn`) VALUES
('Bob001', 'Bob50', 'v1.1', '0000-00-00', 1000, NULL, 'XZE4-O569-YT65', 1000, 128, NULL, 4, b'0', b'1', 'Ed03', 1, 'Fou3'),
('NB02', 'NetBeans', 'v7.4', '0000-00-00', 0, NULL, NULL, 400, 500, 'core I3', 4, b'0', b'1', 'Ed02', 7, 'Fou2'),
('Of13', 'Office 2013', 'v1.0', '0000-00-00', 2000, NULL, 'BUI89-LOI6-KOP3-FR58', 800, 2000, 'dualcore', 4, b'0', b'1', 'Ed01', 10, 'Fou1'),
('Or11', 'Oracle 11g', '11g', '0000-00-00', 500, NULL, NULL, 100, 500, 'core I3', 4, b'0', b'0', 'Ed02', 5, 'Fou1'),
('Vs12', 'Visual Studio', 'v1.0', '0000-00-00', 600, NULL, NULL, 500, 500, 'core I3', 4, b'0', b'1', 'Ed01', 3, 'Fou2');

-- --------------------------------------------------------

--
-- Structure de la table `SoftwarePreinstalle`
--

CREATE TABLE IF NOT EXISTS `SoftwarePreinstalle` (
  `CodeSoftware` varchar(10) NOT NULL,
  `IdTypePC` int(11) NOT NULL,
  PRIMARY KEY (`IdTypePC`,`CodeSoftware`),
  KEY `REF_Softw_Softw_FK` (`CodeSoftware`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `SoftwarePreinstalle`
--

INSERT INTO `SoftwarePreinstalle` (`CodeSoftware`, `IdTypePC`) VALUES
('Bob001', 1),
('Of13', 1),
('Of13', 2),
('Or11', 2),
('Vs12', 2),
('Bob001', 3),
('NB02', 3),
('Vs12', 3),
('NB02', 4),
('Of13', 4),
('Vs12', 4),
('NB02', 5),
('Or11', 5);

-- --------------------------------------------------------

--
-- Structure de la table `TypePC`
--

CREATE TABLE IF NOT EXISTS `TypePC` (
  `IdTypePC` int(11) NOT NULL,
  `Description` varchar(200) NOT NULL,
  PRIMARY KEY (`IdTypePC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `TypePC`
--

INSERT INTO `TypePC` (`IdTypePC`, `Description`) VALUES
(1, 'Athlon II X3'),
(2, 'Core I7 2600'),
(3, 'Celeron 530'),
(4, 'Pentium 4 3GB'),
(5, 'Dual core 2x 1,8GB');

-- --------------------------------------------------------

--
-- Structure de la table `UtilisationSoftware`
--

CREATE TABLE IF NOT EXISTS `UtilisationSoftware` (
  `IdAnneeEtude` int(11) NOT NULL,
  `CodeSoftware` varchar(10) NOT NULL,
  PRIMARY KEY (`IdAnneeEtude`,`CodeSoftware`),
  KEY `REF_Utili_Softw_FK` (`CodeSoftware`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `UtilisationSoftware`
--

INSERT INTO `UtilisationSoftware` (`IdAnneeEtude`, `CodeSoftware`) VALUES
(1, 'Of13'),
(2, 'Of13'),
(3, 'Of13'),
(4, 'Of13'),
(5, 'NB02'),
(5, 'Or11'),
(6, 'Vs12'),
(7, 'Bob001'),
(8, 'Bob001');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `AnneeEtude`
--
ALTER TABLE `AnneeEtude`
  ADD CONSTRAINT `REF_Annee_Secti_FK` FOREIGN KEY (`CodeSection`) REFERENCES `Section` (`CodeSection`);

--
-- Contraintes pour la table `Installation`
--
ALTER TABLE `Installation`
  ADD CONSTRAINT `REF_Insta_OS_FK` FOREIGN KEY (`CodeOS`) REFERENCES `OS` (`CodeOS`),
  ADD CONSTRAINT `REF_Insta_Respo_FK` FOREIGN KEY (`Matricule`) REFERENCES `ResponsableReseaux` (`Matricule`),
  ADD CONSTRAINT `REF_Insta_Softw_FK` FOREIGN KEY (`CodeSoftware`) REFERENCES `Software` (`CodeSoftware`);

--
-- Contraintes pour la table `Professeur`
--
ALTER TABLE `Professeur`
  ADD CONSTRAINT `REF_Profe_Secti_FK` FOREIGN KEY (`CodeSection`) REFERENCES `Section` (`CodeSection`);

--
-- Contraintes pour la table `Software`
--
ALTER TABLE `Software`
  ADD CONSTRAINT `REF_Softw_Fourn_FK` FOREIGN KEY (`CodeFourn`) REFERENCES `Fournisseur` (`CodeFourn`),
  ADD CONSTRAINT `REF_Softw_Edite_FK` FOREIGN KEY (`CodeEdit`) REFERENCES `Editeur` (`CodeEdit`),
  ADD CONSTRAINT `REF_Softw_Famil_FK` FOREIGN KEY (`IdFamSoft`) REFERENCES `FamilleSoftware` (`IdFamSoft`);

--
-- Contraintes pour la table `SoftwarePreinstalle`
--
ALTER TABLE `SoftwarePreinstalle`
  ADD CONSTRAINT `REF_Softw_Softw_FK` FOREIGN KEY (`CodeSoftware`) REFERENCES `Software` (`CodeSoftware`),
  ADD CONSTRAINT `REF_Softw_TypeP` FOREIGN KEY (`IdTypePC`) REFERENCES `TypePC` (`IdTypePC`);

--
-- Contraintes pour la table `UtilisationSoftware`
--
ALTER TABLE `UtilisationSoftware`
  ADD CONSTRAINT `REF_Utili_Annee` FOREIGN KEY (`IdAnneeEtude`) REFERENCES `AnneeEtude` (`IdAnneeEtude`),
  ADD CONSTRAINT `REF_Utili_Softw_FK` FOREIGN KEY (`CodeSoftware`) REFERENCES `Software` (`CodeSoftware`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
