package com.bcm.midia.database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_mip_midia_playlist database table.
 * 
 */
@Embeddable
public class ConfiguracaoResolucaoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="id_Resolucao")
	private Long idResolucao;
	
	@Column(name="id_Configuracao")
	private Long idConfiguracao;
	
	public ConfiguracaoResolucaoPK() {
	}

	public Long getIdConfiguracao() {
		return idConfiguracao;
	}

	public void setIdConfiguracao(Long idConfiguracao) {
		this.idConfiguracao = idConfiguracao;
	}

	public Long getIdResolucao() {
		return idResolucao;
	}

	public void setIdResolucao(Long idResolucao) {
		this.idResolucao = idResolucao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConfiguracao == null) ? 0 : idConfiguracao.hashCode());
		result = prime * result + ((idResolucao == null) ? 0 : idResolucao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfiguracaoResolucaoPK other = (ConfiguracaoResolucaoPK) obj;
		if (idConfiguracao == null) {
			if (other.idConfiguracao != null)
				return false;
		} else if (!idConfiguracao.equals(other.idConfiguracao))
			return false;
		if (idResolucao == null) {
			if (other.idResolucao != null)
				return false;
		} else if (!idResolucao.equals(other.idResolucao))
			return false;
		return true;
	}
	
	
}