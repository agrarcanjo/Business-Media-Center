package com.bcm.midia.database.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.database.entity.Configuracao;
import com.bcm.midia.database.entity.Usuario;


public class UsuarioDAO extends AbstractDAO<Usuario> {

	private final static Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());

	@Override
	public void refresh(Usuario entity) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Buscar usuario com base no login e senha
	 * @param usuario
	 * @return
	 */
	public Usuario efetuarLogin(Usuario usuario) {		
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("dsLogin", usuario.getDsLogin()));
		criteria.add(Restrictions.eq("nrSenha", usuario.getNrSenha()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Usuario) criteria.uniqueResult();	
	}

	
	/**
	 * contador de quantos usuários existe no sistema
	 * 
	 * @return
	 */
	public Long countUsuarios(){
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	

}
