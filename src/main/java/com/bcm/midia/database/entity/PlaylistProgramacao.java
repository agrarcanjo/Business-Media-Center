package com.bcm.midia.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tb_ppg_playlist_programacao database table.
 * 
 */
@Entity
@Table(name="tb_ppg_playlist_programacao")
@NamedQuery(name="PlaylistProgramacao.findAll", query="SELECT t FROM PlaylistProgramacao t")
public class PlaylistProgramacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlaylistProgramacaoPK pk = new PlaylistProgramacaoPK();
		
	//@MapsId("idProgramacao")
	@ManyToOne
	@JoinColumn(name="id_programacao", insertable = false, updatable = false)
	private Programacao programacao;
	
	//@MapsId("idTerminal")
	@ManyToOne
	@JoinColumn(name="id_terminal",  insertable = false, updatable = false)
	private Terminal terminal;
	
	//@MapsId("idPlaylist")
	@ManyToOne
	@JoinColumn(name="id_playlist", insertable = false, updatable = false)
	private Playlist playlist;
	
	@Column(name="nr_ordem", nullable=false)
	private int nrOrdem;

	public PlaylistProgramacao() {
	}
	
	public PlaylistProgramacao(Programacao programacao, Playlist playlist, Terminal terminal, int ordem){
		pk = new PlaylistProgramacaoPK(playlist.getIdPlaylist(), programacao.getIdProgramacao(), terminal.getIdTerminal());
		this.playlist = playlist;
		this.programacao = programacao;
		this.terminal = terminal;		
		this.nrOrdem = ordem;
	}
	
	public int getNrOrdem() {
		return nrOrdem;
	}

	public void setNrOrdem(int nrOrdem) {
		this.nrOrdem = nrOrdem;
	}

	public Playlist getPlaylist() {
		return playlist;
	} 

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	public Programacao getProgramacao() {
		return programacao;
	}

	public void setProgramacao(Programacao programacao) {
		this.programacao = programacao;
	}

	public PlaylistProgramacaoPK getPk() {
		return this.pk;
	}

	public void setPk(PlaylistProgramacaoPK id) {
		this.pk = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nrOrdem;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((playlist == null) ? 0 : playlist.hashCode());
		result = prime * result + ((programacao == null) ? 0 : programacao.hashCode());
		result = prime * result + ((terminal == null) ? 0 : terminal.hashCode());
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
		PlaylistProgramacao other = (PlaylistProgramacao) obj;
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
		if (programacao == null) {
			if (other.programacao != null)
				return false;
		} else if (!programacao.equals(other.programacao))
			return false;
		if (terminal == null) {
			if (other.terminal != null)
				return false;
		} else if (!terminal.equals(other.terminal))
			return false;
		return true;
	}
	
	

}