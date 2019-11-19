package com.bcm.midia.database.dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.database.entity.Midia;
import com.bcm.midia.database.entity.MidiaPlaylist;
import com.bcm.midia.database.entity.Playlist;


public class MidiaPlaylistDAO extends AbstractDAO<MidiaPlaylist> {

	private final static Logger LOGGER = Logger.getLogger(MidiaPlaylistDAO.class.getName());
	
	public List<MidiaPlaylist> findAll() {		
		Criteria criteria = getSession().createCriteria(MidiaPlaylist.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();	
	}
	
	public List<MidiaPlaylist> findById(Playlist p){
		Criteria criteria = getSession().createCriteria(MidiaPlaylist.class);
		criteria.createAlias("midia", "midia");
		criteria.add(Restrictions.eq("pk.idPlaylist", p.getIdPlaylist()));
		criteria.addOrder(Order.asc("nrOrdem"));
		return criteria.list();
	}
	
	@Override
	public void refresh(MidiaPlaylist entity) {
		// TODO Auto-generated method stub
		
	}




}
