package com.bcm.midia.database.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.database.entity.Midia;
import com.bcm.midia.database.entity.MidiaPlaylist;
import com.bcm.midia.database.entity.Playlist;

public class MidiaDAO extends AbstractDAO<Midia> {

	private final static Logger LOGGER = Logger.getLogger(MidiaDAO.class.getName());


	public List<Midia> findAll() {		
		Criteria criteria = getSession().createCriteria(Midia.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	
//		final List<Midia> list = new LinkedList<Midia>();
//		
//		for(final Object o : criteria.list()) {
//		    list.add((Midia)o);
//		}
//		
//		return list; 	
		return criteria.list();
		}
	
	public List<Midia> findRss(){
		Criteria criteria = getSession().createCriteria(Midia.class);
		criteria.add(Restrictions.eq("nmExtensao", "xml"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	public List<Midia> findByIds(List<MidiaPlaylist> midias){
		List<Object> parametro = new ArrayList<Object>();
		Long idPlaylist = 0L;
		if(midias != null && midias.size() >0){
			for (MidiaPlaylist m : midias) {
				if(m.getPk() != null && m.getPk().getIdMidia() > 0){
					parametro.add(m.getPk().getIdMidia());
					idPlaylist = m.getPk().getIdPlaylist();
				}				
			}
		}
		Criteria criteria = getSession().createCriteria(Midia.class);
		criteria.createAlias("midiaPlaylist", "listMidias" );
		criteria.add(Restrictions.eq("listMidias.pk.idPlaylist", idPlaylist));
		criteria.add(Restrictions.in("idMidia", parametro));
		criteria.addOrder(Order.asc("listMidias.nrOrdem"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
		
	}

	
	/**
	 * Conta a quantidade de midia existente no sistema
	 * @return
	 */
	public Long countMidia(){
		Criteria criteria = getSession().createCriteria(Midia.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public void refresh(Midia entity) {
		// TODO Auto-generated method stub
		
	}






}
