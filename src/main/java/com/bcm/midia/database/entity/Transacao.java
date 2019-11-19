package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_trn_transacao database table.
 * 
 */
@Entity
@Table(name="tb_trn_transacao")
@NamedQuery(name="Transacao.findAll", query="SELECT t FROM Transacao t")
public class Transacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_transacao")
	private int idTransacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_criacao")
	private Date dtCriacao;

	@Column(name="nm_transacao")
	private String nmTransacao;

	public Transacao() {
	}

	public int getIdTransacao() {
		return this.idTransacao;
	}

	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNmTransacao() {
		return this.nmTransacao;
	}

	public void setNmTransacao(String nmTransacao) {
		this.nmTransacao = nmTransacao;
	}

	

}