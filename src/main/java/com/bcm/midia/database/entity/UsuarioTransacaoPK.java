package com.bcm.midia.database.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_ust_usuario_transacao database table.
 * 
 */
@Embeddable
public class UsuarioTransacaoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_transacao")
	private int idTransacao;

	@Column(name="id_usuario")
	private int idUsuario;

	public UsuarioTransacaoPK() {
	}
	public int getIdTransacao() {
		return this.idTransacao;
	}
	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}
	public int getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuarioTransacaoPK)) {
			return false;
		}
		UsuarioTransacaoPK castOther = (UsuarioTransacaoPK)other;
		return 
			(this.idTransacao == castOther.idTransacao)
			&& (this.idUsuario == castOther.idUsuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTransacao;
		hash = hash * prime + this.idUsuario;
		
		return hash;
	}
}