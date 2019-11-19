package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_cfg_configuracao")
@NamedQuery(name="Configuracao.findAll", query="SELECT t FROM Configuracao t")
public class Configuracao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_configuracao")
	private Long idCfg;
	
	@Column(name="id_terminal")
	private Long idTerminal;	
	
	@Column(name="dt_criacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtCriacao;

	@Column(name="dt_modificacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtModificacao;

	public Long getIdCfg() {
		return idCfg;
	}

	public void setIdCfg(Long idCfg) {
		this.idCfg = idCfg;
	}

	public Long getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(Long idTerminal) {
		this.idTerminal = idTerminal;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtModificacao() {
		return dtModificacao;
	}

	public void setDtModificacao(Date dtModificacao) {
		this.dtModificacao = dtModificacao;
	}
	
	
}
