package com.bcm.midia.negocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.ResolucaoDAO;
import com.bcm.midia.database.entity.Resolucao;

@Stateless
@LocalBean
public class ResolucaoBean extends BussinessAbstract<Resolucao> implements Serializable {
	
	private static final long serialVersionUID = -6212672805089749673L;
	
	@Inject
	private ResolucaoDAO resolucaoDAO;
	
	
	public List<Resolucao> findAll(){
		return resolucaoDAO.findAll();
	}
	
	public Resolucao findById(int id){
		return resolucaoDAO.findById(id);
	}

	@Override
	protected void validaCamposObrigatorios(Resolucao entity) {
		if(entity != null && (entity.getPsHeight() == 0 || 
				entity.getPsHeight() == null)){
			mensagens.add("resolucao.height");
		}		
		if(entity != null && (entity.getPsWidth() == 0 || 
				entity.getPsWidth() == null)){
			mensagens.add("resolucao.width");
		}		
		if(entity != null && (entity.getPsX() < 0|| 
				entity.getPsX() == null)){
			mensagens.add("resolucao.x");
		}		
		if(entity != null && (entity.getPsY() < 0|| 
				entity.getPsY() == null)){
			mensagens.add("resolucao.y");
		}		
	}


	@Override
	protected void validaRegras(Resolucao entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void validaRegrasExcluir(Resolucao entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected AbstractDAO<Resolucao> getDAO() {
		return resolucaoDAO;
	}


}
