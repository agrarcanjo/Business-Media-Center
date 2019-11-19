package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.primefaces.event.FileUploadEvent;




/**
 * The persistent class for the tb_mid_midia database table.
 * 
 */
@Entity
@Table(name="tb_mid_midia")
@NamedQuery(name="Midia.findAll", query="SELECT t FROM Midia t")
public class Midia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
		
	@Id
	@GeneratedValue
	@Column(name="id_midia")
	private Long idMidia;

	@Column(name="ds_caminho")
	private String dsCaminho;

	@Column(name="ds_midia")
	private String dsMidia;

	@Column(name="dt_criacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtCriacao;

	@Column(name="dt_modificacao")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dtModificacao;

	@Column(name="nm_extensao")
	private String nmExtensao;

	@Column(name="nm_midia")
	private String nmMidia;
	
	@Transient
	private FileUploadEvent event;
	
	@Transient
	private String path;
	
	//bi-directional many-to-one association to TbCatCategoria
	//muitas mídias para uma categoria
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	//uma mídia para várias playlist
	//@OneToMany(mappedBy = "midia",cascade = CascadeType.ALL, fetch= FetchType.LAZY)	
	//private Set<MidiaPlaylist> midiaPlaylist = new HashSet<MidiaPlaylist>();
	@Transient
	private List<MidiaPlaylist> midiaPlaylist = new ArrayList<MidiaPlaylist>();
	
	public Midia() {
	}

	public List<MidiaPlaylist> getMidiaPlaylist() {
		return midiaPlaylist;
	}
	
	public void setMidiaPlaylist(List<MidiaPlaylist> midiaPlaylist) {
		this.midiaPlaylist = midiaPlaylist;
	}

	public Long getIdMidia() {
		return this.idMidia;
	}

	public void setIdMidia(Long idMidia) {
		this.idMidia = idMidia;
	}

	public String getDsCaminho() {
		return this.dsCaminho;
	}

	public void setDsCaminho(String dsCaminho) {
		this.dsCaminho = dsCaminho;
	}

	public String getDsMidia() {
		if(dsMidia==null){
			return "-";
		}
		return this.dsMidia;
	}

	public void setDsMidia(String dsMidia) {
		this.dsMidia = dsMidia;
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

	public String getNmExtensao() {
		if(nmExtensao==null){
			return "-";
		}
		return this.nmExtensao;
	}

	public void setNmExtensao(String nmExtensao) {
		this.nmExtensao = nmExtensao;
	}

	public String getNmMidia() {
		return this.nmMidia;
	}

	public void setNmMidia(String nmMidia) {
		this.nmMidia = nmMidia;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria tbCatCategoria) {
		this.categoria = tbCatCategoria;
	}


	public FileUploadEvent getEvent() {
		return event;
	}

	public void setEvent(FileUploadEvent event) {
		this.event = event;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((dsCaminho == null) ? 0 : dsCaminho.hashCode());
		result = prime * result + ((dsMidia == null) ? 0 : dsMidia.hashCode());
		result = prime * result + ((dtCriacao == null) ? 0 : dtCriacao.hashCode());
		result = prime * result + ((dtModificacao == null) ? 0 : dtModificacao.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((idMidia == null) ? 0 : idMidia.hashCode());
		result = prime * result + ((midiaPlaylist == null) ? 0 : midiaPlaylist.hashCode());
		result = prime * result + ((nmExtensao == null) ? 0 : nmExtensao.hashCode());
		result = prime * result + ((nmMidia == null) ? 0 : nmMidia.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		Midia other = (Midia) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (dsCaminho == null) {
			if (other.dsCaminho != null)
				return false;
		} else if (!dsCaminho.equals(other.dsCaminho))
			return false;
		if (dsMidia == null) {
			if (other.dsMidia != null)
				return false;
		} else if (!dsMidia.equals(other.dsMidia))
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
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (idMidia == null) {
			if (other.idMidia != null)
				return false;
		} else if (!idMidia.equals(other.idMidia))
			return false;
		if (midiaPlaylist == null) {
			if (other.midiaPlaylist != null)
				return false;
		} else if (!midiaPlaylist.equals(other.midiaPlaylist))
			return false;
		if (nmExtensao == null) {
			if (other.nmExtensao != null)
				return false;
		} else if (!nmExtensao.equals(other.nmExtensao))
			return false;
		if (nmMidia == null) {
			if (other.nmMidia != null)
				return false;
		} else if (!nmMidia.equals(other.nmMidia))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
	
}