package com.bcm.midia.database.dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringNVarcharType;

import com.bcm.midia.database.entity.Categoria;
import com.bcm.midia.database.entity.Usuario;


public class CategoriaDAO extends AbstractDAO<Categoria> {

	private final static Logger LOGGER = Logger.getLogger(CategoriaDAO.class.getName());


	public List<Categoria> findAll(Categoria senha) {		
		Criteria criteria = getSession().createCriteria(Categoria.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();		
	}
	
	public List<Categoria> findAllPai(){
		Criteria criteria = getSession().createCriteria(Categoria.class);
		criteria.addOrder(Order.asc("idCategoriaPai"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();		
	}
	
	public List<Categoria> findbyPaiFilho(Categoria cat){
		Criteria criteria = getSession().createCriteria(Categoria.class);
		
		if(cat.getNmCategoria() == null)
			criteria.add(Restrictions.isNull("idCategoriaPai"));
		else{
			criteria.add(Restrictions.eq("idCategoriaPai",cat.getIdCategoria()));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();		
	}

	
	/**
	 * Conta a quantidade de categoria existente no sistema
	 * @return
	 */
	public Long countCategoria(){
		Criteria criteria = getSession().createCriteria(Categoria.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public void refresh(Categoria entity) {
		// TODO Auto-generated method stub
		
	}




}
