package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_ust_usuario_transacao database table.
 * 
 */
@Entity
@Table(name="tb_ust_usuario_transacao")
@NamedQuery(name="UsuarioTransacao.findAll", query="SELECT u FROM UsuarioTransacao u")
public class UsuarioTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioTransacaoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_criacao")
	private Date dtCriacao;

	@Column(name="id_usuario_alteracao")
	private int idUsuarioAlteracao;

	public UsuarioTransacao() {
	}

	public UsuarioTransacaoPK getId() {
		return this.id;
	}

	public void setId(UsuarioTransacaoPK id) {
		this.id = id;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public int getIdUsuarioAlteracao() {
		return this.idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(int idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}

}