package com.bcm.midia.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_mip_midia_playlist database table.
 * 
 */
@Entity
@Table(name="tb_mip_midia_playlist")
@NamedQuery(name="MidiaPlaylist.findAll", query="SELECT t FROM MidiaPlaylist t")
public class MidiaPlaylist implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId	
	private MidiaPlaylistPK pk;
	
	@ManyToOne
	@JoinColumn(name="id_midia", updatable = false, insertable = false)
	private Midia midia;
	
	@ManyToOne
	@JoinColumn(name="id_playlist", updatable = false, insertable = false)
	private Playlist playlist;	
	
	@Column(name="nr_ordem", nullable=false)
	private int nrOrdem;
	
	public MidiaPlaylist(){
	}
	
	public MidiaPlaylist(Midia midia, Playlist playlist, int nrOrdem){
		this.pk = new MidiaPlaylistPK(midia.getIdMidia(), playlist.getIdPlaylist());
		
		this.midia = midia;
		this.playlist = playlist;
		this.nrOrdem = nrOrdem;		
	}
	
	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public void setPk(MidiaPlaylistPK id) {
		this.pk = id;
	}
	
	public MidiaPlaylistPK getPk() {
		return this.pk;
	}
	
	public void setNrOrdem(int nrOrdem){
		this.nrOrdem = nrOrdem;
	}

	public int getNrOrdem(){
		return this.nrOrdem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((midia == null) ? 0 : midia.hashCode());
		result = prime * result + nrOrdem;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((playlist == null) ? 0 : playlist.hashCode());
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
		MidiaPlaylist other = (MidiaPlaylist) obj;
		if (midia == null) {
			if (other.midia != null)
				return false;
		} else if (!midia.equals(other.midia))
			return false;
		if (nrOrdem != other.nrOrdem)
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (playlist == null) {
			if (other.playlist != null)
				return false;
		} else if (!playlist.equals(other.playlist))
			return false;
		return true;
	}
	
	
	
}