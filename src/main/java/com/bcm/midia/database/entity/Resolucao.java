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
@Table(name="tb_cfg_resolucao")
@NamedQuery(name="Resolucao.findAll", query="SELECT t FROM Resolucao t")
public class Resolucao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_resolucao")
	private Long idResolucao;
	
	@Column(name="ps_x")
	private Integer psX;

	@Column(name="ps_y")
	private Integer psY;
	
	@Column(name="ps_Height")
	private Integer psHeight;
	
	@Column(name="ps_Width")
	private Integer psWidth;

	@Column(name="dt_criacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtCriacao;

	@Column(name="dt_modificacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtModificacao;

	public Long getIdResolucao() {
		return idResolucao;
	}

	public void setIdResolucao(Long idResolucao) {
		this.idResolucao = idResolucao;
	}

	public Integer getPsX() {
		return psX;
	}

	public void setPsX(Integer psX) {
		this.psX = psX;
	}

	public Integer getPsY() {
		return psY;
	}

	public void setPsY(Integer psY) {
		this.psY = psY;
	}

	public Integer getPsHeight() {
		return psHeight;
	}

	public void setPsHeight(Integer psHeight) {
		this.psHeight = psHeight;
	}

	public Integer getPsWidth() {
		return psWidth;
	}

	public void setPsWidth(Integer psWidth) {
		this.psWidth = psWidth;
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
