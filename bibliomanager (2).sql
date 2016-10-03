-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2016 at 04:26 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bibliomanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `utente_fk` varchar(100) NOT NULL,
  `libro_fk` varchar(100) NOT NULL,
  `id` int(10) NOT NULL,
  `data_ins` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valutazione` int(10) NOT NULL,
  `commento` text NOT NULL,
  `approvato` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Feedback sui libri';

-- --------------------------------------------------------

--
-- Table structure for table `libro`
--

CREATE TABLE `libro` (
  `isbn` varchar(100) NOT NULL,
  `titolo` varchar(100) NOT NULL,
  `autore` varchar(100) NOT NULL,
  `editore` varchar(100) NOT NULL,
  `descrizione` text NOT NULL,
  `url_img` varchar(1000) DEFAULT NULL,
  `utente_fk` varchar(100) NOT NULL,
  `data_ins` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `anno_pub` int(10) NOT NULL,
  `lingua` varchar(10) NOT NULL DEFAULT 'it',
  `download` int(10) NOT NULL DEFAULT '0',
  `ultima_mod` date DEFAULT NULL,
  `buy` int(100) NOT NULL,
  `link_buy` varchar(1000) DEFAULT NULL,
  `link_download` varchar(1000) DEFAULT NULL,
  `size` double DEFAULT NULL,
  `type` varchar(100) NOT NULL DEFAULT 'jpg'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Pubblicazioni';

--
-- Dumping data for table `libro`
--

INSERT INTO `libro` (`isbn`, `titolo`, `autore`, `editore`, `descrizione`, `url_img`, `utente_fk`, `data_ins`, `anno_pub`, `lingua`, `download`, `ultima_mod`, `buy`, `link_buy`, `link_download`, `size`, `type`) VALUES
('ksdnvpjfn', 'le egadi', 'massimo ranieri', 'fdsfsd', 'dss gfgdf gfdddd  ddddddd ddddd ddddd dddd ddd dd d ddd ddd d ddd ddddddd dddddddddd dddddd ddddd dddddd dddddd ddddddd dddddd dddddd dddddd dddddd ddddddd dddddd ddddddddd dddddd ddddd  dddddd ddddddd', 'ksdnvpjfn_34.jpg', 'master_94@virgilio.it', '2016-10-03 13:53:31', 5453, 'italiano', 0, NULL, 0, NULL, NULL, 2167760, 'jpg'),
('lbblfqbfqrelfkkler', 'pippo', 'e', 'fvregtrgrtg', 'ldslr bvrebvrj brejrj jernvjern lvjrblvbd lvbdfvbs jkvbjbbffb jbffsbbl blgdfbgjd flglsdfj', 'lbblfqbfqrelfkkler_698.jpg', 'master_94@virgilio.it', '2016-10-03 12:58:15', 4533, 'italiano', 0, NULL, 0, NULL, NULL, 31326, 'jpg');

-- --------------------------------------------------------

--
-- Table structure for table `storico`
--

CREATE TABLE `storico` (
  `id` int(11) NOT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `utente_fk` varchar(100) NOT NULL,
  `descrizione` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Storico';

-- --------------------------------------------------------

--
-- Table structure for table `utente`
--

CREATE TABLE `utente` (
  `id` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `n_pubblicazioni` int(10) NOT NULL DEFAULT '0',
  `pwd` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `tipo` int(10) NOT NULL DEFAULT '0',
  `indirizzo` varchar(100) DEFAULT NULL,
  `professione` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabella degli utenti';

--
-- Dumping data for table `utente`
--

INSERT INTO `utente` (`id`, `email`, `n_pubblicazioni`, `pwd`, `nome`, `cognome`, `tipo`, `indirizzo`, `professione`) VALUES
(1, 'master_94@virgilio.it', 0, '000000', 'Leonardo', 'Ruggeri', 1, 'civitanova', 'studente'),
(8, 'a@a.it', 0, 'dddd', 'matte', 'ricci', 1, 'roma', 'studente');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`isbn`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indexes for table `storico`
--
ALTER TABLE `storico`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `storico`
--
ALTER TABLE `storico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `utente`
--
ALTER TABLE `utente`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
