package com.bcm.midia.database.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.database.entity.Resolucao;


public class ResolucaoDAO extends AbstractDAO<Resolucao> {

	private final static Logger LOGGER = Logger.getLogger(ResolucaoDAO.class.getName());


	public List<Resolucao> findAll() {		
		Criteria criteria = getSession().createCriteria(Resolucao.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);	
		return criteria.list();
	}
	
	public Resolucao findById(int id){
		Criteria criteria = getSession().createCriteria(Resolucao.class);
		criteria.add(Restrictions.eq("idResolucao",id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(1);		
		return (Resolucao) criteria.uniqueResult();
	}


	@Override
	public void refresh(Resolucao entity) {
		// TODO Auto-generated method stub
		
	}



}
