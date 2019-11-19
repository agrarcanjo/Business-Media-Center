package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the tb_usr_usuario database table.
 * 
 */
@Entity
@Table(name="tb_usr_usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT t FROM Usuario t")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario")
	private int idUsuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_criacao")
	private Date dtCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_ult_alteracao")
	private Date dtUltimaAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_ult_login")
	private Date dtUltimoLogin;

	@Column(name="id_usuario_alteracao")
	private Integer idUsuarioAlteracao;

	@Lob
	@Column(name="im_foto_perfil")
	private byte[] fotoPerfil;

	@Column(name="nm_sobrenome")
	private String nmSobrenome;

	@Column(name="nm_usuario")
	private String nmUsuario;

	@Column(name="nr_celular")
	private String nrCelular;

	@Column(name="nr_ddd_celular")
	private String nrDddCelular;

	@Column(name="nr_ddd_telefone")
	private String nrDddTelefone;

	@Lob
	@Column(name="nr_senha")
	private String nrSenha;

	@Column(name="nr_telefone")
	private String nrTelefone;

	@Column(name="ds_login", nullable=false, length=20)
	private String dsLogin;
	
	
	@Transient
	private List<UsuarioTransacao> permissoes;
	
	
	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return this.dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Date getDtUltimoLogin() {
		return this.dtUltimoLogin;
	}

	public void setDtUltimoLogin(Date dtUltimoLogin) {
		this.dtUltimoLogin = dtUltimoLogin;
	}

	public Integer getIdUsuarioAlteracao() {
		return this.idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(Integer idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}

	public byte[] getFotoPerfil() {
		return this.fotoPerfil;
	}

	public void setFotoPerfil(byte[] fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getNmSobrenome() {
		return this.nmSobrenome;
	}

	public void setNmSobrenome(String nmSobrenome) {
		this.nmSobrenome = nmSobrenome;
	}

	public String getNmUsuario() {
		return this.nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getNrCelular() {
		return this.nrCelular;
	}

	public void setNrCelular(String nrCelular) {
		this.nrCelular = nrCelular;
	}

	public String getNrDddCelular() {
		return this.nrDddCelular;
	}

	public void setNrDddCelular(String nrDddCelular) {
		this.nrDddCelular = nrDddCelular;
	}

	public String getNrDddTelefone() {
		return this.nrDddTelefone;
	}

	public void setNrDddTelefone(String nrDddTelefone) {
		this.nrDddTelefone = nrDddTelefone;
	}

	public String getNrSenha() {
		return this.nrSenha;
	}

	public void setNrSenha(String nrSenha) {
		this.nrSenha = nrSenha;
	}

	public String getNrTelefone() {
		return this.nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getDsLogin() {
		return dsLogin;
	}

	public void setDsLogin(String dsLogin) {
		this.dsLogin = dsLogin;
	}

	public List<UsuarioTransacao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<UsuarioTransacao> permissoes) {
		this.permissoes = permissoes;
	}

}