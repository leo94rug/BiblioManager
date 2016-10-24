-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Ott 24, 2016 alle 23:00
-- Versione del server: 5.6.25
-- Versione PHP: 5.6.11

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
-- Struttura della tabella `capitoli`
--

CREATE TABLE IF NOT EXISTS `capitoli` (
  `testo` varchar(255) NOT NULL,
  `book_fk` varchar(255) CHARACTER SET utf8 NOT NULL,
  `num_cap` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `capitoli`
--

INSERT INTO `capitoli` (`testo`, `book_fk`, `num_cap`, `id`) VALUES
('Introduzione ai Malavoglia', '9788854195738', 1, 9),
('Prefazione di Giovanni Verga', '9788854195738', 2, 10),
('capitolo primo', '9788854195738', 3, 11),
('capitolo secondo', '9788854195738', 4, 12),
('capitolo terzo', '9788854195738', 5, 13),
('capitolo quarto', '9788854195738', 6, 14),
('capitolo quinto', '9788854195738', 7, 15),
('capitolo sesto', '9788854195738', 8, 16),
('capitolo settimo', '9788854195738', 9, 17),
('capitolo ottavo', '9788854195738', 10, 18),
('capitolo nono', '9788854195738', 11, 19),
('capitolo decimo', '9788854195738', 12, 20),
('capitolo undicesimo', '9788854195738', 13, 21),
('capitolo dodicesimo', '9788854195738', 14, 22),
('capitolo tredicesimo', '9788854195738', 15, 23),
('capitolo quattordicesimo', '9788854195738', 16, 24),
('capitolo quindicesimo', '9788854195738', 17, 25),
('Capitolo uno', '0385504209', 1, 26),
('Capitolo due', '0385504209', 2, 27),
('Capitolo tre', '0385504209', 3, 28),
('Capitolo quattro', '0385504209', 4, 29),
('Capitolo cinque', '0385504209', 5, 30),
('Capitolo sei', '0385504209', 6, 31),
('Goloso Cioccolato', '9788844034559', 1, 32),
('Torte', '9788844034559', 2, 33),
('Crostate', '9788844034559', 3, 34),
('Torte Farcite', '9788844034559', 4, 35),
('Frutta e cioccolato', '9788844034559', 5, 36),
('Dolci al cucchiaio', '9788844034559', 6, 37),
('Freddi e semifreddi', '9788844034559', 7, 38),
('Il cioccolato delle feste', '9788844034559', 8, 39),
('Cioccolato nel mondo', '9788844034559', 9, 40),
('Docietti e mini dessert', '9788844034559', 10, 41),
('Cioccolatini e praline', '9788844034559', 11, 42),
('Biscotti', '9788844034559', 12, 43),
('Bevande', '9788844034559', 13, 44),
('Piatti dolceforte', '9788844034559', 14, 45),
('Indice', '9788844034559', 15, 46);

-- --------------------------------------------------------

--
-- Struttura della tabella `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `user_fk` varchar(100) NOT NULL,
  `libro_fk` varchar(100) NOT NULL,
  `id` int(10) NOT NULL,
  `data_ins_feed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valutazione` int(10) DEFAULT NULL,
  `commento` text NOT NULL,
  `approvato` int(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1 COMMENT='Feedback sui libri';

--
-- Dump dei dati per la tabella `feedback`
--

INSERT INTO `feedback` (`user_fk`, `libro_fk`, `id`, `data_ins_feed`, `valutazione`, `commento`, `approvato`) VALUES
('master_94@virgilio.it', '0385504209', 20, '2016-10-23 13:47:36', 5, 'dunque, se prendete questo libro per libro storico-religioso (date le polemiche alla sua uscita) fatevene una ragione, ci sono vari errori ma potete prendere questo libro come spunto x iniziare ricerche più approfondite sulla Chiesa (se proprio vi interessa); oppure potete prenderlo per quello che è: un romanzo! ben scritto, scorrevole e con un''ottima trama!avvincente! a parte il finale esagerato ma vabbè!', 1),
('matteoricci229@gmail.com', '0385504209', 21, '2016-10-23 13:48:36', 5, 'Anch''io ho letto questo libro anni fa e desidero esprimere il mio parere più che positivo. L''autore riesce a fondere in maniera perfetta esoterismo, arte, mistero e storia, ed ha praticamente inaugurato un nuovo filone a cui molti si rifanno ma nessuno con altrettanta bravura. Grande Dan Brown', 1),
('ennio@ennio.it', '9788846738776', 22, '2016-10-23 13:54:40', 5, 'Il libro e'' scritto in un modo molto piacevole e scorrevole. I dettagli tecnici sono abbastanza limitati e non impediscono la comprensione del senso complessivo. Io l''ho letto due volte di fila per poterne comprendere tutti gli aspetti. Colpisce positivamente anche l''emotività di chi legge e di chi poi deve studiare.', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `libro`
--

CREATE TABLE IF NOT EXISTS `libro` (
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
  `type` varchar(100) NOT NULL DEFAULT 'jpg',
  `sottotitolo` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Pubblicazioni';

--
-- Dump dei dati per la tabella `libro`
--

INSERT INTO `libro` (`isbn`, `titolo`, `autore`, `editore`, `descrizione`, `url_img`, `utente_fk`, `data_ins`, `anno_pub`, `lingua`, `download`, `ultima_mod`, `buy`, `link_buy`, `link_download`, `size`, `type`, `sottotitolo`) VALUES
('0385504209', 'The Da Vinci Code', 'Dan Brown', 'Mondadori', 'Storico dell''arte, Dan Brown struttura il suo ultimo romanzo intorno a un''idea di base: per il severo controllo esercitato finora dalla Chiesa, la chiave del segreto del Graal andrebbe cercata nel linguaggio, spesso allusivo, della pittura. A questo porta l''avventurosa caccia al tesoro che coinvolge un esperto di simbologia, Robert Langdon, dopo l''assassinio del curatore del Louvre, Jacques Saunière (stesso cognome del parroco che a fine Ottocento rese celebre la chiesa di Rennes-le-Château con alcune rivelazioni sul Graal). È così che poco a poco, sullo sfondo d''un impianto narrativo tanto schematico quanto efficace, si staglierà la figura di una "regina cancellata" contro cui la Chiesa avrebbe condotto "la più grande opera di insabbiamento della storia". Alcuni elementi destano però scetticismo: la lettura della Vergine delle Rocce e della Gioconda è forzata; il dossier sui maestri del Priorato di Sion, perno della vicenda, citato da Brown in apertura, pare sia il falso di un esoterista, Pierre Plantard; molte teorie sul Graal contrastano con le più autorevoli interpretazioni tradizionali, per accogliere invece quelle esposte anni fa in un vendutissimo e discutibile saggio da Baigent, Leigh e Lincoln (Brown a uno studioso del Graal dà nome Leigh Teabing, con riferimento, anche tramite anagramma, ai primi due); la cifra qui avanzata di cinque milioni di "streghe" uccise dall''Inquisizione è cinquanta volte maggiore di quella reale. Ma forse la novità, per così dire, più esplosiva del volume - si spera non dovuta all''autore - è che la Torre dell''orologio a Londra non si chiamerebbe Big Ben, bensì "Big Bang".', '0385504209_59.dat', 'matteoricci229@gmail.com', '2016-10-23 13:29:53', 2003, 'italiano', 1, NULL, 1, 'www.lafeltrinelli.it', 'danbrown.com', 335861, 'jpg', NULL),
('1234567', 'dddd', 'ssss', 'ddd', 'aaaaaaaaaaaaaaaaaa', 'cover.dat', 'matteoricci229@gmail.com', '2016-10-24 16:53:29', 12334, 'italiano', 1, NULL, 1, 'aaaaaa', 'www.google.com', NULL, 'jpg', NULL),
('9788844034559', 'Cioccolato', 'Edimedia,sas', 'Giunti editore', 'Il "cibo degli dei" è l''ingrediente principe di tante prelibate ricette, dolci e salate, che deliziano l''olfatto e il palato. Oltre a insoliti primi e secondi piatti, una grandissima varietà di dolci e dessert: cioccolata in tazza, mousse ai vari sapori, torte farcite, meringhe, profiteroles, semifreddi, gelati, cioccolatini, tartufi.', '9788844034559_45.dat', 'matteoricci229@gmail.com', '2016-10-23 13:45:30', 2007, 'italiano', 0, NULL, 1, 'www.ibs.it', NULL, 9506, 'jpg', NULL),
('9788846738776', 'Dal silenzio la musica. Il pianoforte e la costruzione interiore del pezzo musicale', 'Giovanni Carmassi', 'ETS', 'Dialogando con Piero Ferrucci, il maestro Giovanni Carmassi illustra il nucleo essenziale della sua impostazione pianistica: prima di essere eseguito, il pezzo musicale deve essere ideato e costruito nella mente del musicista. Nei vari capitoli sono esaminati i capisaldi su cui si basa l''arte del pianoforte: il peso del corpo nell''espressione del colore, il respiro, la pausa, il silenzio, il ritmo e il tempo, il ruolo della memoria, il rapporto col pubblico, il contesto storico, l''orchestrazione del pianoforte, i pericoli dello stereotipo, del meccanicismo e dell''esibizione narcisistica. Questo libro è una guida per ogni musicista o aspirante tale per avere indicazioni di immediata utilità pratica. Grazie alla sua natura dialogica, rende espliciti concetti e accorgimenti che, magari scontati per un maestro, possono sfuggire all''allievo. Lo scopo ultimo è di mostrare quanto nell''esecuzione pianistica partecipi tutto l''essere: la mente e il cuore, la memoria e l''attenzione, la cultura e l''istinto, il corpo intero, il respiro. Attingendo alla sua grande esperienza didattica, il maestro Carmassi insegna passo passo la via per raggiungere l''interpretazione musicale più autentica e viva.', '9788846738776_832.dat', 'master_94@virgilio.it', '2016-10-23 13:54:08', 2014, 'italiano', 0, NULL, 1, 'www.lafeltrinelli.it', NULL, 30589, 'jpg', NULL),
('9788854195738', 'I Malavoglia', 'Giovanni Verga', 'Garzanti', 'Nel suo capolavoro, I Malavoglia (1881), Verga racconta la sventurata storia di un gruppo di poveri pescatori siciliani, ponendosi ai vertici della letteratura italiana, senza incorrere nel folklore populista che caratterizzava, e caratterizzerà, molte opere ambientate tra le classi più umili. La vicenda dei Toscano, detti "Malavoglia", di Acitrezza è emblematica della dissoluzione di un mondo che sta per essere cancellato dall''epoca moderna. Lo scrittore siciliano guarda con un velo di nostalgia e compassione all''affannoso dibattersi degli abitanti della "casa del nespolo", ma descrive con estrema crudezza la violenza dei loro rapporti e la loro vulnerabilità di fronte alla Natura e alla Storia.', '9788854195738_530.dat', 'master_94@virgilio.it', '2016-10-23 09:49:29', 1993, 'italiano', 0, NULL, 1, 'www.lafeltrinelli.it', NULL, 11695, 'jpg', NULL),
('9788860440402', 'La ricerca della felicità', 'Chris Gardner', 'Fandango', 'La prima parte della vita di Chris Gardner assomiglia a quella di tanti ragazzi americani di colore: nasce negli anni Cinquanta nello stato del Milwaukee, dopo la scuola entra in Marina anche per sottrarsi a un ambiente familiare ristretto e violento, ventenne arriva a San Francisco e comincia a lavorare in un ospedale coltivando parallelamente la sua passione - un talento naturale - per l''alta finanza. Una serie di circostanze negative lo portano per un breve periodo in prigione. Ma Chris non si rassegna, reagisce, cerca lavoro - e lo trova - in una società finanziaria. Nel frattempo la sua compagna lo lascia e gli affida il figlio piccolo. Chris rimane per un anno senza casa, è uno dei tanti "homeless" ma con un bambino di due anni con sé. La parte centrale della sua autobiografia - e del film diretto da Muccino - è dedicata a questa esistenza errabonda nelle strade e nei quartieri di San Francisco, un mondo sconosciuto alla luce del giorno dove vince la solidarietà tra diversi e dove Chris sperimenta un modo di essere padre che nulla toglie alla cura e alla tenerezza per suo figlio. Da qui ha inizio la seconda parte della vita di Chris: nella migliore tradizione del Sogno Americano, l''uomo riesce a farsi valere sul lavoro, assicura un''abitazione a sé e al figlio e sfonda nel mondo dell''alta finanza diventando un guru di Wall Street.', '9788860440402_66.dat', 'master_94@virgilio.it', '2016-10-23 09:32:09', 2008, 'italiano', 0, NULL, 1, 'www.lafeltrinelli.it', NULL, 14757, 'jpg', NULL),
('9788861070417', 'Peccati al cioccolato', 'Luca Montersino', 'LT Editore', 'Luca Montersino propone una serie di ricette inedite che hanno come protagonista l''elemento principe della pasticceria: il cioccolato. Tantissime ricette golose divise per tipologia di dolci e con tutti gli step fotografici per realizzare torte da maestro. Le tecniche base di lavorazione del cioccolato e i preziosi consigli del maestro Luca Montersino. Oltre 60 ricette. Tantissime foto a colori. Step fotografici di tutte le preparazioni. 6 capitoli dedicati a varie tipologie di dolci.', '9788861070417_957.dat', 'matteoricci229@gmail.com', '2016-10-23 13:36:32', 2011, 'italiano', 0, NULL, 1, 'www.lafeltrinelli.it', NULL, 14001, 'jpg', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `storico`
--

CREATE TABLE IF NOT EXISTS `storico` (
  `id` int(11) NOT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `utente_fk` varchar(100) NOT NULL,
  `descrizione` text NOT NULL,
  `book_fk` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COMMENT='Storico';

--
-- Dump dei dati per la tabella `storico`
--

INSERT INTO `storico` (`id`, `time_stamp`, `utente_fk`, `descrizione`, `book_fk`) VALUES
(17, '2016-10-23 09:32:09', 'master_94@virgilio.it', 'ha inserito il libro:', '9788860440402'),
(18, '2016-10-23 09:33:02', 'master_94@virgilio.it', 'ha modificato il libro:', '9788860440402'),
(19, '2016-10-23 09:33:19', 'master_94@virgilio.it', 'ha modificato il libro:', '9788860440402'),
(20, '2016-10-23 09:49:29', 'master_94@virgilio.it', 'ha inserito il libro:', '9788854195738'),
(21, '2016-10-23 13:29:53', 'matteoricci229@gmail.com', 'ha inserito il libro:', '0385504209'),
(22, '2016-10-23 13:30:58', 'matteoricci229@gmail.com', 'ha modificato il libro:', '0385504209'),
(23, '2016-10-23 13:31:15', 'matteoricci229@gmail.com', 'ha modificato il libro:', '0385504209'),
(24, '2016-10-23 13:36:32', 'matteoricci229@gmail.com', 'ha inserito il libro:', '9788861070417'),
(25, '2016-10-23 13:45:30', 'matteoricci229@gmail.com', 'ha inserito il libro:', '9788844034559'),
(26, '2016-10-23 13:48:01', 'master_94@virgilio.it', 'ha commentato il libro:', '0385504209'),
(27, '2016-10-23 13:48:42', 'matteoricci229@gmail.com', 'ha commentato il libro:', '0385504209'),
(28, '2016-10-23 13:54:08', 'master_94@virgilio.it', 'ha inserito il libro:', '9788846738776'),
(29, '2016-10-24 16:53:29', 'matteoricci229@gmail.com', 'ha inserito il libro:', '1234567');

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE IF NOT EXISTS `utente` (
  `email` varchar(50) NOT NULL,
  `n_pubblicazioni` int(10) NOT NULL DEFAULT '0',
  `pwd` varchar(255) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `tipo` int(10) NOT NULL DEFAULT '0',
  `indirizzo` varchar(100) DEFAULT NULL,
  `professione` varchar(100) DEFAULT NULL,
  `iscrizione` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `img_user` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabella degli utenti';

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`email`, `n_pubblicazioni`, `pwd`, `nome`, `cognome`, `tipo`, `indirizzo`, `professione`, `iscrizione`, `img_user`) VALUES
('ennio@ennio.it', 0, '47bfc658557d384821eb5de8981e6299378acd90899a04e8b1b561757974c', 'Ennio', 'Morricone', 1, 'via Roma', 'Compositore', '2016-10-23 13:49:36', 'ennio@ennio.it_437.dat'),
('master_94@virgilio.it', 3, '24be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'Leonardo', 'Ruggeri', 2, 'Sant''Elpidio a Mare', 'Studente', '2016-10-23 09:16:45', 'master_94@virgilio.it_669.dat'),
('matteoricci229@gmail.com', 4, '24be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'Matteo', 'Ricci', 2, 'Decime 23/a', 'Studente', '2016-10-23 09:18:25', 'matteoricci229@gmail.com_771.dat');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `capitoli`
--
ALTER TABLE `capitoli`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`isbn`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indici per le tabelle `storico`
--
ALTER TABLE `storico`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `capitoli`
--
ALTER TABLE `capitoli`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT per la tabella `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT per la tabella `storico`
--
ALTER TABLE `storico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
