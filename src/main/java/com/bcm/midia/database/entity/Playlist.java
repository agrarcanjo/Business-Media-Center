package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the tb_pla_playlist database table.
 * 
 */
@Entity
@Table(name="tb_pla_playlist") //uniqueConstraints = {@UniqueConstraint(columnNames = "id_playlist")}
@NamedQuery(name="Playlist.findAll", query="SELECT t FROM Playlist t")
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	@Column(name="id_playlist")
	private Long idPlaylist;

	@Column(name="dt_criacao")
	private Date dtCriacao;

	@Column(name="dt_modificacao")
	private Date dtModificacao;

	@Column(name="nm_playlist")
	private String nmPlaylist;

	@Column(name="qt_midia_transmissao")
	private int qtMidiaTransmissao;
	
	//@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
	//private Set<MidiaPlaylist> midiaPlaylist = new HashSet<MidiaPlaylist>();
	@Transient
	private List<MidiaPlaylist> midiaPlaylist  = new ArrayList<MidiaPlaylist>();
	
	@Transient
	private List<PlaylistProgramacao> listPlaylist;

	public List<PlaylistProgramacao> getListPlaylist() {
		return listPlaylist;
	}

	public void setListPlaylist(List<PlaylistProgramacao> listPlaylist) {
		this.listPlaylist = listPlaylist;
	}

	public Playlist() {
	}

	public List<MidiaPlaylist> getMidiaPlaylist() {
		return midiaPlaylist;
	}


	public void setMidiaPlaylist(List<MidiaPlaylist> midiaPlaylist) {
		this.midiaPlaylist = midiaPlaylist;
	}


	public Long getIdPlaylist() {
		return this.idPlaylist;
	}

	public void setIdPlaylist(Long idPlaylist) {
		this.idPlaylist = idPlaylist;
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

	public String getNmPlaylist() {
		return this.nmPlaylist;
	}

	public void setNmPlaylist(String nmPlaylist) {
		this.nmPlaylist = nmPlaylist;
	}

	public int getQtMidiaTransmissao() {
		return this.qtMidiaTransmissao;
	}

	public void setQtMidiaTransmissao(int qtMidiaTransmissao) {
		this.qtMidiaTransmissao = qtMidiaTransmissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtCriacao == null) ? 0 : dtCriacao.hashCode());
		result = prime * result + ((dtModificacao == null) ? 0 : dtModificacao.hashCode());
		result = prime * result + ((idPlaylist == null) ? 0 : idPlaylist.hashCode());
		result = prime * result + ((listPlaylist == null) ? 0 : listPlaylist.hashCode());
		result = prime * result + ((midiaPlaylist == null) ? 0 : midiaPlaylist.hashCode());
		result = prime * result + ((nmPlaylist == null) ? 0 : nmPlaylist.hashCode());
		result = prime * result + qtMidiaTransmissao;
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
		Playlist other = (Playlist) obj;
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
		if (idPlaylist == null) {
			if (other.idPlaylist != null)
				return false;
		} else if (!idPlaylist.equals(other.idPlaylist))
			return false;
		if (listPlaylist == null) {
			if (other.listPlaylist != null)
				return false;
		} else if (!listPlaylist.equals(other.listPlaylist))
			return false;
		if (midiaPlaylist == null) {
			if (other.midiaPlaylist != null)
				return false;
		} else if (!midiaPlaylist.equals(other.midiaPlaylist))
			return false;
		if (nmPlaylist == null) {
			if (other.nmPlaylist != null)
				return false;
		} else if (!nmPlaylist.equals(other.nmPlaylist))
			return false;
		if (qtMidiaTransmissao != other.qtMidiaTransmissao)
			return false;
		return true;
	}

	
}