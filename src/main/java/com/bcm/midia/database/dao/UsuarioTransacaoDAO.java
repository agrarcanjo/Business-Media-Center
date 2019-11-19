package com.bcm.midia.database.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.database.entity.Usuario;
import com.bcm.midia.database.entity.UsuarioTransacao;


public class UsuarioTransacaoDAO extends AbstractDAO<UsuarioTransacao> {

	private final static Logger LOGGER = Logger.getLogger(UsuarioTransacaoDAO.class.getName());

	@Override
	public void refresh(UsuarioTransacao entity) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Buscar todas as transacoes permitidas para aquele usuário
	 * @param usuario
	 * @return
	 */
	public List<UsuarioTransacao> buscarTransacoesByUsuario(Usuario usuario) {		
		Criteria criteria = getSession().createCriteria(UsuarioTransacao.class);
		criteria.add(Restrictions.eq("id.idUsuario", usuario.getIdUsuario()));
		return criteria.list();	
	}

	/**
	 * Conta todas as permissoes registradas no sistema.
	 * @return
	 */
	public Long countTransacoes(){
		Criteria criteria = getSession().createCriteria(UsuarioTransacao.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	

}
