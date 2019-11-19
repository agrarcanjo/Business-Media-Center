package com.bcm.midia.database.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.bcm.midia.database.entity.MidiaPlaylist;
import com.bcm.midia.database.entity.Terminal;


public class TerminalDAO extends AbstractDAO<Terminal> {

	private final static Logger LOGGER = Logger.getLogger(TerminalDAO.class.getName());


	public List<Terminal> findAll(Terminal senha) {		
		Criteria criteria = getSession().createCriteria(Terminal.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//criteria.setMaxResults(5);	
		return criteria.list();
	}
	
	public List<Terminal> findAllTerminais(){
		Criteria criteria = getSession().createCriteria(Terminal.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	public Terminal findById(int id){
		Criteria criteria = getSession().createCriteria(Terminal.class);
		criteria.add(Restrictions.eq("idTerminal",id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(1);		
		return (Terminal) criteria.uniqueResult();
	}
	
	/**
	 * Realiza a contagem de quantos terminais existe no sistema
	 * @return
	 */
	public Long countTerminais(){
		Criteria criteria = getSession().createCriteria(Terminal.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	@Override
	public void refresh(Terminal entity) {
		// TODO Auto-generated method stub
		
	}



}
