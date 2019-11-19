package com.bcm.midia.database.dao;

import java.util.List;
import java.util.logging.Logger;


import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import com.bcm.midia.database.entity.Playlist;


public class PlaylistDAO extends AbstractDAO<Playlist> {

	private final static Logger LOGGER = Logger.getLogger(PlaylistDAO.class.getName());


	public List<Playlist> findAll() {		
		Criteria criteria = getSession().createCriteria(Playlist.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Playlist> lista = criteria.list(); 
		return lista;	
	}


	/**
	 * Conta quantos playslists existe no sistema
	 * @return
	 */
	public Long countPlaylist(){
		Criteria criteria = getSession().createCriteria(Playlist.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	@Override
	public void refresh(Playlist entity) {
		// TODO Auto-generated method stub
		
	}




}
