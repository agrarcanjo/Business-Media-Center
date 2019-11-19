package com.bcm.midia.database.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the tb_pro_programacao database table.
 * 
 */
@Entity
@Table(name="tb_pro_programacao")
@NamedQuery(name="Programacao.findAll", query="SELECT t FROM Programacao t")
public class Programacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_programacao")
	private Long idProgramacao;
	
	@Column(name="dt_criacao")
	private Date dtCriacao;

	@Column(name="dt_dia_semana")
	private String dtDiaSemana;

	@Column(name="dt_modificacao")
	private Date dtModificacao;

	@Column(name="dt_inicio") 
	private Date dtInicio;

	@Column(name="dt_fim")
	private Date dtFim;
	
	@Column(name="st_todosDias")
	private Character stTodosDias;
	
	@Column(name="st_semDtFim")
	private Character stSemDtFim;

	@Column(name="st_situacao")
	private String stSituacao;
	
	@Column(name="st_intercalar")
	private Character stIntercalar;
	
	@Column(name="hr_inicio")
	private Date hrInicio;
	
	@Column(name="hr_fim")
	private Date hrFim;
	
	//Uma programação pode estar em várias playlist de programações
	//@OneToMany(mappedBy = "programacao", fetch = FetchType.LAZY) //, cascade=CascadeType.ALL
	@Transient
	private List<PlaylistProgramacao> listProgramacao;

	public Programacao() {
	}

	public Long getIdProgramacao() {
		return this.idProgramacao;
	}

	public void setIdProgramacao(Long idProgramacao) {
		this.idProgramacao = idProgramacao;
	}

	public List<PlaylistProgramacao> getListProgramacao() {
		return listProgramacao;
	}

	public void setListProgramacao(List<PlaylistProgramacao> listProgramacao) {
		this.listProgramacao = listProgramacao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date date) {
		this.dtCriacao = date;
	}

	public String getDtDiaSemana() {
		return this.dtDiaSemana;
	}
	
	public String getDtDiaSemanaFormat(){
		String dtDiaSemanaFormat="";
		if(this.dtDiaSemana.length() < 1){
			return "-";
		}
		if(this.dtDiaSemana.indexOf("1")>=0){
			dtDiaSemanaFormat += "D ";
		}
		if(this.dtDiaSemana.indexOf("2")>=0){
			dtDiaSemanaFormat += "S ";
		}
		if(this.dtDiaSemana.indexOf("3")>=0){
			dtDiaSemanaFormat += "T ";
		}
		if(this.dtDiaSemana.indexOf("4")>=0){
			dtDiaSemanaFormat += "Q ";
		}
		if(this.dtDiaSemana.indexOf("5")>=0){
			dtDiaSemanaFormat += "QI ";
		}
		if(this.dtDiaSemana.indexOf("6")>=0){
			dtDiaSemanaFormat += "SE ";
		}
		if(this.dtDiaSemana.indexOf("7")>=0){
			dtDiaSemanaFormat += "SA ";
		}
		return dtDiaSemanaFormat;
	}

	public void setDtDiaSemana(String dtDiaSemana) {
		this.dtDiaSemana = dtDiaSemana;
	}

	public Date getDtModificacao() {
		return this.dtModificacao;
	}

	public void setDtModificacao(Date dtModificacao) {
		this.dtModificacao = dtModificacao;
	}

	public String getStSituacao() {
		return this.stSituacao;
	}

	public void setStSituacao(String stSituacao) {
		this.stSituacao = stSituacao;
	}
	
	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Character getStTodosDias() {
		return stTodosDias;
	}

	public void setStTodosDias(Character stTodosDias) {
		this.stTodosDias = stTodosDias;
	}

	public Character getStSemDtFim() {
		return stSemDtFim;
	}

	public void setStSemDtFim(Character stSemDtFim) {
		this.stSemDtFim = stSemDtFim;
	}

	public Character getStIntercalar() {
		return stIntercalar;
	}

	public void setStIntercalar(Character stIntercalar) {
		this.stIntercalar = stIntercalar;
	}

	public Date getHrInicio() {
		return hrInicio;
	}

	public void setHrInicio(Date hrInicio) {
		this.hrInicio = hrInicio;
	}

	public Date getHrFim() {
		return hrFim;
	}

	public void setHrFim(Date hrfim) {
		this.hrFim = hrfim;
	}

}