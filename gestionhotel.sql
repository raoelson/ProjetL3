-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 08 Août 2014 à 16:47
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `gestionhotel`
--
CREATE DATABASE IF NOT EXISTS `gestionhotel` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gestionhotel`;

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

CREATE TABLE IF NOT EXISTS `chambre` (
  `IDCHAMBRE` int(11) NOT NULL AUTO_INCREMENT,
  `SURFACECHAMBRE` char(255) DEFAULT NULL,
  `QUALITECHAMBRE` varchar(128) DEFAULT NULL,
  `LOYERCHAMBRE` decimal(10,2) DEFAULT NULL,
  `DISPOCHAMBRE` char(255) DEFAULT NULL,
  PRIMARY KEY (`IDCHAMBRE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `chambre`
--

INSERT INTO `chambre` (`IDCHAMBRE`, `SURFACECHAMBRE`, `QUALITECHAMBRE`, `LOYERCHAMBRE`, `DISPOCHAMBRE`) VALUES
(2, '3', '1', '3000.00', 'NON'),
(3, '2', '1', '2000.00', 'NON'),
(4, '3', '1', '2000.00', 'NON'),
(5, '4', '2', '50000.00', 'DISPONIBLE'),
(6, '3', '5', '2000.00', 'DISPONIBLE'),
(7, '5', '6', '10000.00', 'DISPONIBLE'),
(8, '5', '5', '30000.00', 'DISPONIBLE');

-- --------------------------------------------------------

--
-- Structure de la table `locataire`
--

CREATE TABLE IF NOT EXISTS `locataire` (
  `IDLOCATAIRE` bigint(4) NOT NULL AUTO_INCREMENT,
  `IDCHAMBRE` int(11) NOT NULL,
  `NOMLOCATAIRE` char(255) DEFAULT NULL,
  `PRENOMLOCATAIRE` char(255) DEFAULT NULL,
  `PROFESSIONLOCATAIRE` char(255) DEFAULT NULL,
  `DATEARRIVEE` char(32) DEFAULT NULL,
  `NBJRSLOCATAIRE` char(32) DEFAULT NULL,
  `ADRESSELOCATAIRE` char(255) DEFAULT NULL,
  `payement` varchar(32) DEFAULT NULL,
  `avance` varchar(32) DEFAULT NULL,
  `mois` varchar(32) DEFAULT NULL,
  `annee` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`IDLOCATAIRE`),
  KEY `I_FK_LOCATAIRE_CHAMBRE` (`IDCHAMBRE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Contenu de la table `locataire`
--

INSERT INTO `locataire` (`IDLOCATAIRE`, `IDCHAMBRE`, `NOMLOCATAIRE`, `PRENOMLOCATAIRE`, `PROFESSIONLOCATAIRE`, `DATEARRIVEE`, `NBJRSLOCATAIRE`, `ADRESSELOCATAIRE`, `payement`, `avance`, `mois`, `annee`) VALUES
(27, 2, 'raoelson', 'maoris', 'etudiant', '2014-08-06', '2', 'fianarantsoa', 'tous', '', '08', '2014'),
(28, 3, 'anjarasoa', 'mirana', 'etudiante', '2014-07-30', '3', 'fianarantsoa', 'tous', '', '07', '2014'),
(29, 4, 'julien', 'barina', 'etudiant', '2014-08-31', '2', 'fianarantsoa', 'tous', '', '08', '2014');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `password`, `type`) VALUES
(1, 'Mao', '3fe8ebd7f5996651fa46c4aefe24b6af', 'admin'),
(3, 'leris', '63206848b8d2bb660fd4020841e81251', 'simple'),
(6, 'fafa', '05d251ea28c5be9426611a121db0c92a', 'admin');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `locataire`
--
ALTER TABLE `locataire`
  ADD CONSTRAINT `locataire_ibfk_1` FOREIGN KEY (`IDCHAMBRE`) REFERENCES `chambre` (`IDCHAMBRE`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
