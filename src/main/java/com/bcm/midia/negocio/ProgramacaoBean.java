package com.bcm.midia.negocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.PlaylistProgramacaoDAO;
import com.bcm.midia.database.dao.ProgramacaoDAO;
import com.bcm.midia.database.entity.PlaylistProgramacao;
import com.bcm.midia.database.entity.Programacao;

@Stateless
@LocalBean
public class ProgramacaoBean extends BussinessAbstract<Programacao> implements Serializable {

	private static final long serialVersionUID = 8654767882044737959L;

	@Inject
	private ProgramacaoDAO programacaoDAO;
	
	@Inject
	private PlaylistProgramacaoDAO playlistProgramacaoDAO;

	public List<Programacao> findAll() {
		return programacaoDAO.findAll();
	}
	
	public List<PlaylistProgramacao> findAllProgramacaoTerminal(){
		return playlistProgramacaoDAO.findAll();
	}

	public List<PlaylistProgramacao> findPPById(Long id) {
		return playlistProgramacaoDAO.findPPById(id);
	}
	
	public List<PlaylistProgramacao> findByTerminal(Long id){
		return playlistProgramacaoDAO.findByTerminal(id);
	}

	public Long countProgramacao() {
		return playlistProgramacaoDAO.countProgramacao();
	}
	
	public List<PlaylistProgramacao> findByProgramacao(Long id){
		return playlistProgramacaoDAO.findByProgramacao(id);
	}
	
	public List<PlaylistProgramacao> findProgramacaoByTerminal(Long id){
		return playlistProgramacaoDAO.findProgramacaoByTerminal(id);
	}

	@Override
	protected void validaCamposObrigatorios(Programacao entity) {

		if (entity != null && entity.getDtInicio() == null) {
			mensagens.add("programacao.dtInicio");
		}
		if (entity.getDtFim() == null) {
			if (entity.getStSemDtFim() == null || entity.getStSemDtFim().equals("")) {
				mensagens.add("programacao.dtFim");
			}
		}
		if (entity.getDtDiaSemana() == null || (entity.getStTodosDias().equals('N') && entity.getDtDiaSemana().equals(""))) {
			mensagens.add("programacao.diaSemana");
		}
		
//		if(entity.getHrFim()==null)
//			mensagens.add("programacao.horaFim");
//
//		if(entity.getHrInicio()==null)
//			mensagens.add("programacao.horaInicio");
	}

	@Override
	protected void validaRegras(Programacao entity) {
		
		if(entity == null)
			mensagens.add("entity.null");
		
		if(entity.getHrInicio() != null){
			if(entity.getHrInicio().after(entity.getHrFim()))
				mensagens.add("programacao.horaInicio.maior.final");
		}
		
		if(entity.getDtInicio() != null){
			if(entity.getDtFim()!=null){
				if(entity.getDtInicio().after(entity.getDtFim()))
					mensagens.add("programacao.dateInicio.maior.fim");
			}
		}
	}

	@Override
	protected void validaRegrasExcluir(Programacao entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected AbstractDAO<Programacao> getDAO() {
		return programacaoDAO;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(PlaylistProgramacao pp) {
		playlistProgramacaoDAO.save(pp);
	}

}
