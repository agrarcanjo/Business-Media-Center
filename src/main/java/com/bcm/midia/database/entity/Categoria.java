package com.bcm.midia.database.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_cat_categoria database table.
 * 
 */
@Entity
@Table(name="tb_cat_categoria")
@NamedQuery(name="Categoria.findAll", query="SELECT t FROM Categoria t")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_categoria")
	private Long idCategoria;

	@Column(name="id_categoria_pai", nullable=true)
	private Long idCategoriaPai;

	@OneToOne
	@JoinColumn(name="id_categoria_pai", insertable=false, updatable=false)
	private Categoria categoriaPai;

	@Column(name="nm_categoria")
	private String nmCategoria;

	@OneToMany(mappedBy="categoria", fetch = FetchType.LAZY)
	private List<Midia> midias;

	public Categoria() {
	}

	public Long getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdCategoriaPai() {
		return this.idCategoriaPai;
	}

	public void setIdCategoriaPai(Long idCategoriaPai) {
		this.idCategoriaPai = idCategoriaPai;
	}

	public String getNmCategoria() {
		if(nmCategoria==null){
			return "-";
		}
		return this.nmCategoria;
	}

	public void setNmCategoria(String nmCategoria) {
		this.nmCategoria = nmCategoria;
	}

	public List<Midia> getMidias() {
		return this.midias;
	}

	public void setMidias(List<Midia> tbMidMidias) {
		this.midias = tbMidMidias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriaPai == null) ? 0 : categoriaPai.hashCode());
		result = prime * result +((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((idCategoriaPai == null) ? 0 : idCategoriaPai.hashCode());
		result = prime * result + ((midias == null) ? 0 : midias.hashCode());
		result = prime * result + ((nmCategoria == null) ? 0 : nmCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (categoriaPai == null) {
			if (other.categoriaPai != null)
				return false;
		} else if (!categoriaPai.equals(other.categoriaPai))
			return false;
		if (idCategoria != other.idCategoria)
			return false;
		if (idCategoriaPai == null) {
			if (other.idCategoriaPai != null)
				return false;
		} else if (!idCategoriaPai.equals(other.idCategoriaPai))
			return false;
		if (midias == null) {
			if (other.midias != null)
				return false;
		} else if (!midias.equals(other.midias))
			return false;
		if (nmCategoria == null) {
			if (other.nmCategoria != null)
				return false;
		} else if (!nmCategoria.equals(other.nmCategoria))
			return false;
		return true;
	}

}