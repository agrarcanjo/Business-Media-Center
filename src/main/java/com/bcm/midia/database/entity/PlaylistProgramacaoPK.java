package com.bcm.midia.database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_ppg_playlist_programacao database table.
 * 
 */
@Embeddable
public class PlaylistProgramacaoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_playlist")
	private Long idPlaylist;
	
	@Column(name = "id_programacao")
	private Long idProgramacao;	
	
	@Column(name = "id_terminal")
	private Long idTerminal;
	
	public PlaylistProgramacaoPK() {
	}
	
	public PlaylistProgramacaoPK(Long playlist, Long programacao, Long terminal){
		this.idPlaylist = playlist;
		this.idProgramacao = programacao;
		this.idTerminal = terminal;
	}

	public Long getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(Long idPlaylist) {
		this.idPlaylist = idPlaylist;
	}

	public Long getIdProgramacao() {
		return idProgramacao;
	}

	public void setIdProgramacao(Long idProgramacao) {
		this.idProgramacao = idProgramacao;
	}

	public Long getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(Long idTerminal) {
		this.idTerminal = idTerminal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPlaylist == null) ? 0 : idPlaylist.hashCode());
		result = prime * result + ((idProgramacao == null) ? 0 : idProgramacao.hashCode());
		result = prime * result + ((idTerminal == null) ? 0 : idTerminal.hashCode());
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
		PlaylistProgramacaoPK other = (PlaylistProgramacaoPK) obj;
		if (idPlaylist == null) {
			if (other.idPlaylist != null)
				return false;
		} else if (!idPlaylist.equals(other.idPlaylist))
			return false;
		if (idProgramacao == null) {
			if (other.idProgramacao != null)
				return false;
		} else if (!idProgramacao.equals(other.idProgramacao))
			return false;
		if (idTerminal == null) {
			if (other.idTerminal != null)
				return false;
		} else if (!idTerminal.equals(other.idTerminal))
			return false;
		return true;
	}

	
	
}