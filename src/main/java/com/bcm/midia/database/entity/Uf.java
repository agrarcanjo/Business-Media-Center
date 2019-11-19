package com.bcm.midia.database.entity;

/*
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "uf", catalog = "corporativo")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Uf implements Comparable<Uf> {

	private static final long serialVersionUID = 1L;
	private Regiao regiao;
	private String sigla;
	private String codigoIBGE;
	private Set<Municipio> listaMunicipio = new HashSet<Municipio>(0);

	private Pais pais;

	@Override
	@Id
	@Column(name = "id_uf", nullable = false, precision = 4, scale = 0)
	public Long getId() {
		return (Long) super.getId();
	}

	@ManyToOne
	@JoinColumn(name = "id_regiao", nullable = false)
	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	@Column(name = "Sg_UF", nullable = false, length = 2)
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	@Column(name = "nm_uf", nullable = false, length = 30)
	public String getNome() {
		return super.getNome();
	}

	@Column(name = "cd_uf_ibge", length = 8)
	public String getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(String codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uf")
	public Set<Municipio> getListaMunicipio() {
		return listaMunicipio;
	}

	public void setListaMunicipio(Set<Municipio> listaMunicipio) {
		this.listaMunicipio = listaMunicipio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pais", nullable = false)
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int compareTo(Uf o) {
		if (!UtilEJB.isNullOuVazio(this.sigla) && !UtilEJB.isNullOuVazio(o.sigla)) {
			int valor = this.sigla.compareTo(o.sigla);
			return (valor != 0 ? valor : 1);
		} else {
			return 0;
		}
	}
}
*/


