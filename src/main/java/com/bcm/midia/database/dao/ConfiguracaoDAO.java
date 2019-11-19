package com.bcm.midia.database.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;

import com.bcm.midia.database.entity.Configuracao;

public class ConfiguracaoDAO extends AbstractDAO<Configuracao>{
	
	private final static Logger LOGGER = Logger.getLogger(CategoriaDAO.class.getName());
	
	
	public List<Configuracao> findAll() {		
		Criteria criteria = getSession().createCriteria(Configuracao.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();		
	}
	@Override
	public void refresh(Configuracao entity) {
		// TODO Auto-generated method stub
		
	}

}
