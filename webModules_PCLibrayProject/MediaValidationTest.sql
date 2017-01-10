-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 22 Décembre 2016 à 15:03
-- Version du serveur :  5.7.16-0ubuntu0.16.04.1
-- Version de PHP :  7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `MediaValidationTest`
--

-- --------------------------------------------------------

--
-- Structure de la table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `kind` varchar(150) NOT NULL,
  `document_id` int(11) DEFAULT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `books`
--

INSERT INTO `books` (`id`, `kind`, `document_id`, `description`) VALUES
(1, 'Fantastique', 4, 'Hello je suis une nouveauté dans cette médiathèque, prenez-moi !!'),
(2, 'Science-fiction', 6, 'Un superbe livre à découvrir de 7 à 77 ans !!!');

-- --------------------------------------------------------

--
-- Structure de la table `borrowers`
--

CREATE TABLE `borrowers` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL,
  `borrow_date` date NOT NULL,
  `return_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `CDs`
--

CREATE TABLE `CDs` (
  `id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `document_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `CDs`
--

INSERT INTO `CDs` (`id`, `duration`, `document_id`) VALUES
(1, 3200, 3),
(2, 3600, 7);

-- --------------------------------------------------------

--
-- Structure de la table `comics`
--

CREATE TABLE `comics` (
  `id` int(11) NOT NULL,
  `kind` varchar(150) NOT NULL,
  `document_id` int(11) DEFAULT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `comics`
--

INSERT INTO `comics` (`id`, `kind`, `document_id`, `description`) VALUES
(1, 'Jeunesse', 5, 'BlaBlaBla je suis une description qui ne doit pas depasser 1500 lignes !!!'),
(2, 'Adulte', 9, 'Dans un univers sombre et intriguant, l\'inspecteur BlackSad doit résoudre le meurtre d\'un innocent récemment arriver en Grande-Bretagne.');

-- --------------------------------------------------------

--
-- Structure de la table `documents`
--

CREATE TABLE `documents` (
  `id` int(11) NOT NULL,
  `documentType_id` int(11) DEFAULT NULL,
  `title` varchar(250) NOT NULL,
  `author` varchar(150) NOT NULL,
  `year` int(11) NOT NULL,
  `arrival_date` datetime NOT NULL,
  `picture` varchar(255) NOT NULL,
  `booked` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `documents`
--

INSERT INTO `documents` (`id`, `documentType_id`, `title`, `author`, `year`, `arrival_date`, `picture`, `booked`) VALUES
(2, 2, 'Le seigneur des anneaux "les deux tours"', 'Peter jackson', 2008, '2016-12-13 09:11:54', 'img4.jpg', ''),
(3, 1, 'Into the wild', 'Eddie Wedder', 2009, '2016-12-13 09:21:07', 'img3.jpg\r\n', ''),
(4, 3, 'L\'épée de vérité', 'Terry Goodkind', 2009, '2016-12-13 09:24:18', 'img1.jpg', 'no'),
(5, 4, 'La soupe aux Schtroumpfs', 'Peyo', 1989, '2016-12-13 09:26:18', 'img5.jpg\r\n', ''),
(6, 3, 'La guerre eternelle', 'Raspbury', 1980, '2016-12-14 09:35:17', 'img2.jpg', 'no'),
(7, 1, 'The very BestOf', 'Ennio Morricone', 1965, '2016-12-22 00:00:00', 'img6.jpg\r\n', ''),
(9, 4, 'Quelque part entre les ombres', 'Juan Díaz Canales et Juanjo Guarnido', 2012, '2016-11-16 00:00:00', 'img7.jpg', ''),
(10, 2, 'The brokken circle breakdown', 'Felix Van Groeningen', 2013, '2016-12-01 00:00:00', 'img8.jpg', 'no');

-- --------------------------------------------------------

--
-- Structure de la table `document_type`
--

CREATE TABLE `document_type` (
  `id` int(11) NOT NULL,
  `code` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `document_type`
--

INSERT INTO `document_type` (`id`, `code`) VALUES
(1, 'CD'),
(2, 'DVD'),
(3, 'Book'),
(4, 'Comic');

-- --------------------------------------------------------

--
-- Structure de la table `DVDs`
--

CREATE TABLE `DVDs` (
  `id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `document_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `DVDs`
--

INSERT INTO `DVDs` (`id`, `duration`, `document_id`) VALUES
(1, 5000, 2),
(2, 5200, 10);

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `id` int(11) NOT NULL,
  `event_name` varchar(350) NOT NULL,
  `event_description` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `events`
--

INSERT INTO `events` (`id`, `event_name`, `event_description`) VALUES
(1, 'Foire aux livres de Morlaix', 'Dimanche 28 septembre, grande foire aux livre à Morlaix. \r\nLivres à bas prix et troc possible.\r\n\r\nVotre médiathèque sera présente.'),
(2, 'Dédicace de Bernard Webber à la mairie de Pleyber-Christ', 'Samedi 28 novembre, à l\'occasion de la fête des fruits d\'automne, Bernard Webber sera présent à la mairie de Plevber-Christ de 14h à 17h pour dédicacer son nouveau livre "la révolution des fourmis!!\r\n\r\nVenez nombreux.');

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

CREATE TABLE `fos_user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `reservation_date` datetime NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(4, 'manak', 'manak', 'lucas.fourteau@laposte.net', 'lucas.fourteau@laposte.net', 1, NULL, '$2y$13$ldg5EVCmm5kj5C0r3K9jwOnHYzDYt1GfhgjAnccWbiafI5vVVV6qS', '2016-12-22 09:08:21', NULL, NULL, 'a:1:{i:0;s:10:"ROLE_ADMIN";}'),
(5, 'userTest', 'usertest', 'usertest@usertest.fr', 'usertest@usertest.fr', 1, NULL, '$2y$13$mIBq/wshkmjzyEGtfdKpzOBuN8A7GrgUQ/4gqhfKsjZGKgYZ4R.7O', '2016-12-22 14:23:24', NULL, NULL, 'a:0:{}'),
(6, 'gestUser', 'gestuser', 'gestuser@gestUser.fr', 'gestuser@gestuser.fr', 1, NULL, '$2y$13$PB4GIFK3E./5HpXWp9DFvu6.F0sb.7ZI8AYGV74U5DFA91e9nyuay', '2016-12-22 14:25:34', NULL, NULL, 'a:1:{i:0;s:17:"ROLE_GESTIONNAIRE";}');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `document_id` (`document_id`);

--
-- Index pour la table `borrowers`
--
ALTER TABLE `borrowers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `publication_id` (`document_id`);

--
-- Index pour la table `CDs`
--
ALTER TABLE `CDs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `document_id` (`document_id`);

--
-- Index pour la table `comics`
--
ALTER TABLE `comics`
  ADD PRIMARY KEY (`id`),
  ADD KEY `document_id` (`document_id`);

--
-- Index pour la table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_id` (`documentType_id`);

--
-- Index pour la table `document_type`
--
ALTER TABLE `document_type`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `DVDs`
--
ALTER TABLE `DVDs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `document_id` (`document_id`);

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `fos_user`
--
ALTER TABLE `fos_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `document_id` (`document_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `borrowers`
--
ALTER TABLE `borrowers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT pour la table `CDs`
--
ALTER TABLE `CDs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `comics`
--
ALTER TABLE `comics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `documents`
--
ALTER TABLE `documents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `document_type`
--
ALTER TABLE `document_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `DVDs`
--
ALTER TABLE `DVDs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `fos_user`
--
ALTER TABLE `fos_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `FK_4A1B2A92C33F7837` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`);

--
-- Contraintes pour la table `borrowers`
--
ALTER TABLE `borrowers`
  ADD CONSTRAINT `FK_D7D928D3A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_D7D928D3C33F7837` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`);

--
-- Contraintes pour la table `CDs`
--
ALTER TABLE `CDs`
  ADD CONSTRAINT `FK_D084400CC33F7837` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`);

--
-- Contraintes pour la table `comics`
--
ALTER TABLE `comics`
  ADD CONSTRAINT `FK_2D56FB58C33F7837` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`);

--
-- Contraintes pour la table `documents`
--
ALTER TABLE `documents`
  ADD CONSTRAINT `FK_A2B072884DA0E3EA` FOREIGN KEY (`documentType_id`) REFERENCES `document_type` (`id`);

--
-- Contraintes pour la table `DVDs`
--
ALTER TABLE `DVDs`
  ADD CONSTRAINT `FK_1AE8F3C33F7837` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`);

--
-- Contraintes pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `FK_4DA239A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_4DA239C33F7837` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
