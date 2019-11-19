package com.bcm.midia.database.dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.database.entity.PlaylistProgramacao;
import com.bcm.midia.database.entity.PlaylistProgramacaoPK;
import com.bcm.midia.database.entity.Programacao;
import com.bcm.midia.database.entity.Usuario;


public class ProgramacaoDAO extends AbstractDAO<Programacao> {

	private final static Logger LOGGER = Logger.getLogger(ProgramacaoDAO.class.getName());

	
	public List<Programacao> findAll() {		
		Criteria criteria = getSession().createCriteria(Programacao.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	
	/**
	 * Conta quantas programações existe no sistema
	 * @return
	 */
	public Long countProgramacao(){
		Criteria criteria = getSession().createCriteria(Programacao.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	@Override
	public void refresh(Programacao entity) {
		// TODO Auto-generated method stub
		
	}

}
