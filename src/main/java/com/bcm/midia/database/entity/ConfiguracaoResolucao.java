package com.bcm.midia.database.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_ppg_playlist_programacao database table.
 * 
 */
@Entity
@Table(name="tb_cfg_configuracao_resolucao")
@NamedQuery(name="configuracaoResolucao.findAll", query="SELECT t FROM ConfiguracaoResolucao t")
public class ConfiguracaoResolucao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConfiguracaoResolucaoPK id;

	
	public ConfiguracaoResolucao() {
	}

	public ConfiguracaoResolucaoPK getId() {
		return this.id;
	}

	public void setId(ConfiguracaoResolucaoPK id) {
		this.id = id;
	}

}