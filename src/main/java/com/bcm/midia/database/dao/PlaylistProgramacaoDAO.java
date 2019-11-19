package com.bcm.midia.database.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.database.entity.PlaylistProgramacao;

public class PlaylistProgramacaoDAO extends AbstractDAO<PlaylistProgramacao>{

private final static Logger LOGGER = Logger.getLogger(PlaylistProgramacaoDAO.class.getName());
	
	public List<PlaylistProgramacao> findAll() {		
		Criteria criteria = getSession().createCriteria(PlaylistProgramacao.class);
		criteria.createAlias("programacao", "programacao");
		criteria.setFetchMode("programacao", FetchMode.JOIN);
		criteria.createAlias("terminal", "terminal");
		criteria.setFetchMode("terminal", FetchMode.JOIN);
		//criteria.createAlias("playlist", "playlist");
		//criteria.setFetchMode("playlist", FetchMode.JOIN);
		criteria.addOrder(Order.asc("terminal.nmTerminal"));
		criteria.addOrder(Order.asc("nrOrdem"));
//		ProjectionList projectionList = Projections.projectionList();
//	    projectionList.add(Projections.groupProperty("terminal.nmTerminal"));
//	    projectionList.add(Projections.groupProperty("nrOrdem"));
//	    criteria.setProjection(projectionList);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();	
	}
	
	public List<PlaylistProgramacao> findPPById(Long id){
		Criteria criteria = getSession().createCriteria(PlaylistProgramacao.class);
		criteria.add(Restrictions.eq("id.idProgramacao", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	public List<PlaylistProgramacao> findByTerminal(Long id) {
		Criteria criteria = getSession().createCriteria(PlaylistProgramacao.class); 
		criteria.add(Restrictions.eq("id.idTerminal", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	/**
	 * Conta quantas programações existe no sistema
	 * @return
	 */
	public Long countProgramacao(){
		Criteria criteria = getSession().createCriteria(PlaylistProgramacao.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	
	@Override
	public void refresh(PlaylistProgramacao entity) {
		// TODO Auto-generated method stub
		
	}

	public List<PlaylistProgramacao> findByProgramacao(Long id) {
		Criteria criteria = getSession().createCriteria(PlaylistProgramacao.class);
		criteria.createAlias("programacao", "programacao");
		criteria.setFetchMode("programacao", FetchMode.JOIN);
		criteria.createAlias("terminal", "terminal");
		criteria.setFetchMode("terminal", FetchMode.JOIN);
		criteria.add(Restrictions.eq("programacao.idProgramacao", id));	
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	public List<PlaylistProgramacao> findProgramacaoByTerminal(Long id) {
		Criteria criteria = getSession().createCriteria(PlaylistProgramacao.class);
		criteria.createAlias("playlist", "playlist");
		criteria.setFetchMode("playlist", FetchMode.JOIN);
		criteria.createAlias("midia", "midia");
		criteria.setFetchMode("midia", FetchMode.JOIN);
		criteria.add(Restrictions.eq("terminal.idTerminal", id));	
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

}
