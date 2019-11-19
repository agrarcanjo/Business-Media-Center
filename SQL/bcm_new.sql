-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 30-Ago-2017 às 18:01
-- Versão do servidor: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bcm`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(72);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_cat_categoria`
--

CREATE TABLE `tb_cat_categoria` (
  `id_categoria` bigint(20) NOT NULL,
  `id_categoria_pai` bigint(20) DEFAULT NULL,
  `nm_categoria` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_cfg_configuracao`
--

CREATE TABLE `tb_cfg_configuracao` (
  `id_configuracao` bigint(20) NOT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `dt_modificacao` datetime DEFAULT NULL,
  `id_terminal` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_cfg_configuracao_resolucao`
--

CREATE TABLE `tb_cfg_configuracao_resolucao` (
  `id_Configuracao` bigint(20) NOT NULL,
  `id_Resolucao` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_cfg_resolucao`
--

CREATE TABLE `tb_cfg_resolucao` (
  `id_resolucao` bigint(20) NOT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `dt_modificacao` datetime DEFAULT NULL,
  `ps_Height` int(11) DEFAULT NULL,
  `ps_Width` int(11) DEFAULT NULL,
  `ps_x` int(11) DEFAULT NULL,
  `ps_y` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `tb_cfg_resolucao`
--

INSERT INTO `tb_cfg_resolucao` (`id_resolucao`, `dt_criacao`, `dt_modificacao`, `ps_Height`, `ps_Width`, `ps_x`, `ps_y`) VALUES
(27, '2017-03-24 14:31:04', NULL, 698, 1024, 0, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_mid_midia`
--

CREATE TABLE `tb_mid_midia` (
  `id_midia` bigint(20) NOT NULL,
  `ds_caminho` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ds_midia` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `dt_modificacao` datetime DEFAULT NULL,
  `nm_extensao` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nm_midia` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_categoria` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `tb_mid_midia`
--

INSERT INTO `tb_mid_midia` (`id_midia`, `ds_caminho`, `ds_midia`, `dt_criacao`, `dt_modificacao`, `nm_extensao`, `nm_midia`, `id_categoria`) VALUES
(46, NULL, NULL, '2017-08-11 19:28:12', NULL, NULL, 'Entretenimento 1 - Copia (8).mp4_46', NULL),
(47, NULL, NULL, '2017-08-11 19:28:12', NULL, NULL, 'Entretenimento 1 - Copia (7).mp4_47', NULL),
(48, NULL, NULL, '2017-08-11 19:28:12', NULL, NULL, 'Entretenimento 1 - Copia (6).mp4_48', NULL),
(49, NULL, NULL, '2017-08-11 19:28:12', NULL, NULL, 'Entretenimento 1 - Copia (5).mp4_49', NULL),
(50, NULL, NULL, '2017-08-11 19:28:12', NULL, NULL, 'Entretenimento 1 - Copia (9).mp4_50', NULL),
(51, NULL, NULL, '2017-08-11 19:28:12', NULL, NULL, 'Entretenimento 1 - Copia (10).mp4_51', NULL),
(52, NULL, NULL, '2017-08-11 19:28:14', NULL, NULL, 'Entretenimento 1 - Copia (12).mp4_52', NULL),
(53, NULL, NULL, '2017-08-11 19:28:14', NULL, NULL, 'Entretenimento 1 - Copia (11).mp4_53', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_mip_midia_playlist`
--

CREATE TABLE `tb_mip_midia_playlist` (
  `id_midia` bigint(20) NOT NULL,
  `id_playlist` bigint(20) NOT NULL,
  `nr_ordem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_pla_playlist`
--

CREATE TABLE `tb_pla_playlist` (
  `id_playlist` bigint(20) NOT NULL,
  `dt_criacao` date DEFAULT NULL,
  `dt_modificacao` date DEFAULT NULL,
  `nm_playlist` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `qt_midia_transmissao` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `tb_pla_playlist`
--

INSERT INTO `tb_pla_playlist` (`id_playlist`, `dt_criacao`, `dt_modificacao`, `nm_playlist`, `qt_midia_transmissao`) VALUES
(54, '2017-08-11', NULL, 'teste', 4),
(62, '2017-08-22', NULL, 'play neses', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_ppg_playlist_programacao`
--

CREATE TABLE `tb_ppg_playlist_programacao` (
  `id_playlist` bigint(20) NOT NULL,
  `id_programacao` bigint(20) NOT NULL,
  `id_terminal` bigint(20) NOT NULL,
  `nr_ordem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_pro_programacao`
--

CREATE TABLE `tb_pro_programacao` (
  `id_programacao` bigint(20) NOT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `dt_dia_semana` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dt_fim` datetime DEFAULT NULL,
  `dt_inicio` datetime DEFAULT NULL,
  `dt_modificacao` datetime DEFAULT NULL,
  `hr_fim` datetime DEFAULT NULL,
  `hr_inicio` datetime DEFAULT NULL,
  `st_intercalar` char(1) COLLATE utf8_bin DEFAULT NULL,
  `st_semDtFim` char(1) COLLATE utf8_bin DEFAULT NULL,
  `st_situacao` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `st_todosDias` char(1) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_ter_terminal`
--

CREATE TABLE `tb_ter_terminal` (
  `id_terminal` bigint(20) NOT NULL,
  `ds_terminal` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `dt_modificacao` datetime DEFAULT NULL,
  `nm_terminal` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `tb_ter_terminal`
--

INSERT INTO `tb_ter_terminal` (`id_terminal`, `ds_terminal`, `dt_criacao`, `dt_modificacao`, `nm_terminal`) VALUES
(58, 'maternidade', '2017-08-17 10:11:15', NULL, 'Amparo'),
(59, 'aeroporto', '2017-08-17 10:20:37', NULL, 'Santa genoveva'),
(67, 'Goiania', '2017-08-29 16:42:13', NULL, 'Santa Luzia');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_trn_transacao`
--

CREATE TABLE `tb_trn_transacao` (
  `id_transacao` int(11) NOT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `nm_transacao` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_usr_usuario`
--

CREATE TABLE `tb_usr_usuario` (
  `id_usuario` int(11) NOT NULL,
  `ds_login` varchar(20) COLLATE utf8_bin NOT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `dt_ult_alteracao` datetime DEFAULT NULL,
  `dt_ult_login` datetime DEFAULT NULL,
  `im_foto_perfil` longblob,
  `id_usuario_alteracao` int(11) DEFAULT NULL,
  `nm_sobrenome` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nm_usuario` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nr_celular` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nr_ddd_celular` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nr_ddd_telefone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nr_senha` longtext COLLATE utf8_bin,
  `nr_telefone` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `tb_usr_usuario`
--

INSERT INTO `tb_usr_usuario` (`id_usuario`, `ds_login`, `dt_criacao`, `dt_ult_alteracao`, `dt_ult_login`, `im_foto_perfil`, `id_usuario_alteracao`, `nm_sobrenome`, `nm_usuario`, `nr_celular`, `nr_ddd_celular`, `nr_ddd_telefone`, `nr_senha`, `nr_telefone`) VALUES
(1, 'tvsaude', '2017-01-31 00:00:00', NULL, NULL, NULL, NULL, 'Arcanjo', 'Gabriel', NULL, NULL, NULL, '37983E9AC6C13AE3AFDF708603FF7BD3A4A038271E4BF79ABE104D0012C52EDF', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_ust_usuario_transacao`
--

CREATE TABLE `tb_ust_usuario_transacao` (
  `id_transacao` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `id_usuario_alteracao` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_cat_categoria`
--
ALTER TABLE `tb_cat_categoria`
  ADD PRIMARY KEY (`id_categoria`),
  ADD KEY `FK_g0jerdc0lc2326rq87jnbyrui` (`id_categoria_pai`);

--
-- Indexes for table `tb_cfg_configuracao`
--
ALTER TABLE `tb_cfg_configuracao`
  ADD PRIMARY KEY (`id_configuracao`);

--
-- Indexes for table `tb_cfg_configuracao_resolucao`
--
ALTER TABLE `tb_cfg_configuracao_resolucao`
  ADD PRIMARY KEY (`id_Configuracao`,`id_Resolucao`);

--
-- Indexes for table `tb_cfg_resolucao`
--
ALTER TABLE `tb_cfg_resolucao`
  ADD PRIMARY KEY (`id_resolucao`);

--
-- Indexes for table `tb_mid_midia`
--
ALTER TABLE `tb_mid_midia`
  ADD PRIMARY KEY (`id_midia`),
  ADD KEY `FK_fbc6m3dn74x59u67c2gkvj5l8` (`id_categoria`);

--
-- Indexes for table `tb_mip_midia_playlist`
--
ALTER TABLE `tb_mip_midia_playlist`
  ADD PRIMARY KEY (`id_midia`,`id_playlist`),
  ADD KEY `FK_3kfxulove6mwmyjmncbjvj7r9` (`id_midia`),
  ADD KEY `FK_oly8nxjn31tv8bcbaarrkeges` (`id_playlist`);

--
-- Indexes for table `tb_pla_playlist`
--
ALTER TABLE `tb_pla_playlist`
  ADD PRIMARY KEY (`id_playlist`);

--
-- Indexes for table `tb_ppg_playlist_programacao`
--
ALTER TABLE `tb_ppg_playlist_programacao`
  ADD PRIMARY KEY (`id_playlist`,`id_programacao`,`id_terminal`),
  ADD KEY `FK_jg6arlgp8dguxqd178o3h89go` (`id_playlist`),
  ADD KEY `FK_9huu5wa628jssiri4q8lj2ev1` (`id_programacao`),
  ADD KEY `FK_arodg14kefa9jimeqslphrjqg` (`id_terminal`);

--
-- Indexes for table `tb_pro_programacao`
--
ALTER TABLE `tb_pro_programacao`
  ADD PRIMARY KEY (`id_programacao`);

--
-- Indexes for table `tb_ter_terminal`
--
ALTER TABLE `tb_ter_terminal`
  ADD PRIMARY KEY (`id_terminal`);

--
-- Indexes for table `tb_trn_transacao`
--
ALTER TABLE `tb_trn_transacao`
  ADD PRIMARY KEY (`id_transacao`);

--
-- Indexes for table `tb_usr_usuario`
--
ALTER TABLE `tb_usr_usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indexes for table `tb_ust_usuario_transacao`
--
ALTER TABLE `tb_ust_usuario_transacao`
  ADD PRIMARY KEY (`id_transacao`,`id_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_pla_playlist`
--
ALTER TABLE `tb_pla_playlist`
  MODIFY `id_playlist` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tb_cat_categoria`
--
ALTER TABLE `tb_cat_categoria`
  ADD CONSTRAINT `FK_g0jerdc0lc2326rq87jnbyrui` FOREIGN KEY (`id_categoria_pai`) REFERENCES `tb_cat_categoria` (`id_categoria`);

--
-- Limitadores para a tabela `tb_mid_midia`
--
ALTER TABLE `tb_mid_midia`
  ADD CONSTRAINT `FK_fbc6m3dn74x59u67c2gkvj5l8` FOREIGN KEY (`id_categoria`) REFERENCES `tb_cat_categoria` (`id_categoria`);

--
-- Limitadores para a tabela `tb_mip_midia_playlist`
--
ALTER TABLE `tb_mip_midia_playlist`
  ADD CONSTRAINT `FK_3kfxulove6mwmyjmncbjvj7r9` FOREIGN KEY (`id_midia`) REFERENCES `tb_mid_midia` (`id_midia`),
  ADD CONSTRAINT `FK_oly8nxjn31tv8bcbaarrkeges` FOREIGN KEY (`id_playlist`) REFERENCES `tb_pla_playlist` (`id_playlist`);

--
-- Limitadores para a tabela `tb_ppg_playlist_programacao`
--
ALTER TABLE `tb_ppg_playlist_programacao`
  ADD CONSTRAINT `FK_9huu5wa628jssiri4q8lj2ev1` FOREIGN KEY (`id_programacao`) REFERENCES `tb_pro_programacao` (`id_programacao`),
  ADD CONSTRAINT `FK_arodg14kefa9jimeqslphrjqg` FOREIGN KEY (`id_terminal`) REFERENCES `tb_ter_terminal` (`id_terminal`),
  ADD CONSTRAINT `FK_jg6arlgp8dguxqd178o3h89go` FOREIGN KEY (`id_playlist`) REFERENCES `tb_pla_playlist` (`id_playlist`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
