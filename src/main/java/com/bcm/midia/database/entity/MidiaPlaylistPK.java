package com.bcm.midia.database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_mip_midia_playlist database table.
 * 
 */
@Embeddable
public class MidiaPlaylistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_midia")
	private Long idMidia;
	
	@Column(name = "id_playlist")
	private Long idPlaylist;
	
	public MidiaPlaylistPK(){
	}
	
	public MidiaPlaylistPK(Long idMidia, Long idPlaylist){
		this.idMidia = idMidia;
		this.idPlaylist = idPlaylist;
	}
	
	public Long getIdMidia() {
		return idMidia;
	}
	public void setIdMidia(Long idMidia) {
		this.idMidia = idMidia;
	}
	public Long getIdPlaylist() {
		return idPlaylist;
	}
	
	public void setIdPlaylist(Long idPlaylist) {
		this.idPlaylist = idPlaylist;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMidia == null) ? 0 : idMidia.hashCode());
		result = prime * result + ((idPlaylist == null) ? 0 : idPlaylist.hashCode());
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
		MidiaPlaylistPK other = (MidiaPlaylistPK) obj;
		if (idMidia == null) {
			if (other.idMidia != null)
				return false;
		} else if (!idMidia.equals(other.idMidia))
			return false;
		if (idPlaylist == null) {
			if (other.idPlaylist != null)
				return false;
		} else if (!idPlaylist.equals(other.idPlaylist))
			return false;
		return true;
	}
	
}