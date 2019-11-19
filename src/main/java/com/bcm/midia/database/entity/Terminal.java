package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the tb_ter_terminal database table.
 * 
 */
@Entity
@Table(name="tb_ter_terminal")
@NamedQuery(name="Terminal.findAll", query="SELECT t FROM Terminal t")
public class Terminal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_terminal")
	private Long idTerminal;

	@Column(name="ds_terminal")
	private String dsTerminal;

	@Column(name="dt_criacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtCriacao;

	@Column(name="dt_modificacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtModificacao;

	@Column(name="nm_terminal")
	private String nmTerminal;
	
	//@OneToMany(mappedBy = "terminal", fetch = FetchType.LAZY)
	@Transient
	private List<PlaylistProgramacao> listTerminal;
	

	public Terminal() {
	}

	public Long getIdTerminal() {
		return this.idTerminal;
	}

	public void setIdTerminal(Long idTerminal) {
		this.idTerminal = idTerminal;
	}

	public String getDsTerminal() {
		return this.dsTerminal;
	}

	public void setDsTerminal(String dsTerminal) {
		this.dsTerminal = dsTerminal;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtModificacao() {
		return this.dtModificacao;
	}

	public void setDtModificacao(Date dtModificacao) {
		this.dtModificacao = dtModificacao;
	}

	public String getNmTerminal() {
		return this.nmTerminal;
	}

	public void setNmTerminal(String nmTerminal) {
		this.nmTerminal = nmTerminal;
	}

	public List<PlaylistProgramacao> getListTerminal() {
		return listTerminal;
	}
	
	public void setListTerminal(List<PlaylistProgramacao> terminal) {
		this.listTerminal = terminal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsTerminal == null) ? 0 : dsTerminal.hashCode());
		result = prime * result + ((dtCriacao == null) ? 0 : dtCriacao.hashCode());
		result = prime * result + ((dtModificacao == null) ? 0 : dtModificacao.hashCode());
		result = prime * result + ((idTerminal == null) ? 0 : idTerminal.hashCode());
		result = prime * result + ((nmTerminal == null) ? 0 : nmTerminal.hashCode());
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
		Terminal other = (Terminal) obj;
		if (dsTerminal == null) {
			if (other.dsTerminal != null)
				return false;
		} else if (!dsTerminal.equals(other.dsTerminal))
			return false;
		if (dtCriacao == null) {
			if (other.dtCriacao != null)
				return false;
		} else if (!dtCriacao.equals(other.dtCriacao))
			return false;
		if (dtModificacao == null) {
			if (other.dtModificacao != null)
				return false;
		} else if (!dtModificacao.equals(other.dtModificacao))
			return false;
		if (idTerminal == null) {
			if (other.idTerminal != null)
				return false;
		} else if (!idTerminal.equals(other.idTerminal))
			return false;
		if (nmTerminal == null) {
			if (other.nmTerminal != null)
				return false;
		} else if (!nmTerminal.equals(other.nmTerminal))
			return false;
		return true;
	}

	


}