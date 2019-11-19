package com.bcm.midia.negocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.TerminalDAO;
import com.bcm.midia.database.entity.Terminal;

@Stateless
@LocalBean
public class TerminalBean extends BussinessAbstract<Terminal> implements Serializable {
	
	private static final long serialVersionUID = -6212672805089749673L;
	
	@Inject
	private TerminalDAO terminalDAO;
	
	
	public List<Terminal> findAll(){
		return terminalDAO.findAllTerminais();
	}
	
	public Long countTerminal(){
		return terminalDAO.countTerminais();
	}
	
	public Terminal findById(int id){
		return terminalDAO.findById(id);
	}

	@Override
	protected void validaCamposObrigatorios(Terminal entity) {
		if(entity != null && (entity.getNmTerminal().trim().equals("") || entity.getNmTerminal() == null)){
			mensagens.add("categoria.nome");
		}		
	}

	@Override
	protected void validaRegras(Terminal entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validaRegrasExcluir(Terminal entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AbstractDAO<Terminal> getDAO() {
		return terminalDAO;
	}


}
