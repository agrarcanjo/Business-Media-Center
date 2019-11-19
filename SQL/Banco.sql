CREATE TABLE `tb_ust_usuario_transacao` (
 `id_usuario` int(11) NOT NULL,
 `id_transacao` int(11) NOT NULL,
 `dt_criacao` datetime NOT NULL,
 `id_usuario_alteracao` int(11) DEFAULT NULL,
 PRIMARY KEY (`id_transacao`,`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_trn_transacao` (
  id_transacao INT(11) AUTO_INCREMENT NOT NULL,
  nm_transacao VARCHAR(255) NOT NULL,
  dt_criacao DATETIME NOT NULL,
 PRIMARY KEY (id_transacao)
) ENGINE = InnoDB AUTO_INCREMENT = 1 ROW_FORMAT = DEFAULT CHARACTER SET utf8;

CREATE TABLE `tb_usr_usuario` (
  id_usuario INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
  nm_usuario VARCHAR(255) NOT NULL,
  nm_sobrenome VARCHAR(255) NOT NULL,
  nr_ddd_telefone VARCHAR(2),
  nr_telefone VARCHAR(10),
  nr_ddd_celular VARCHAR(2),
  nr_celular VARCHAR(10),
  nr_senha TEXT ASCII NOT NULL,
  im_foto_perfil LONGBLOB,
  dt_criacao DATETIME NOT NULL,
  dt_ult_alteracao DATETIME,
  dt_ult_login DATETIME,
  id_usuario_alteracao INT(11),
 PRIMARY KEY (id_usuario)
) ENGINE = InnoDB AUTO_INCREMENT = 1 ROW_FORMAT = DEFAULT CHARACTER SET utf8;

--
-- Estrutura da tabela `tb_mip_midia_playlist`
--

CREATE TABLE `tb_mip_midia_playlist` (
  `id_midia` int(11) NOT NULL,
  `id_playlist` int(11) NOT NULL,
  `nr_ordem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for table `tb_mip_midia_playlist`
--
ALTER TABLE `tb_mip_midia_playlist`
  ADD PRIMARY KEY (`id_midia`,`id_playlist`,`nr_ordem`);
  
 
  