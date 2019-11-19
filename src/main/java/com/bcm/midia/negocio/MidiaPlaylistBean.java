package com.bcm.midia.negocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.MidiaPlaylistDAO;
import com.bcm.midia.database.entity.MidiaPlaylist;

@Stateless
@LocalBean
public class MidiaPlaylistBean extends BussinessAbstract<MidiaPlaylist> implements Serializable {
	
	private static final long serialVersionUID = -6212672805089749673L;
	
	@Inject
	private MidiaPlaylistDAO midiaPlaylistDAO;
	
	
	public List<MidiaPlaylist> findAll(){
		return midiaPlaylistDAO.findAll();
	}

	@Override
	protected void validaCamposObrigatorios(MidiaPlaylist entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validaRegras(MidiaPlaylist entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validaRegrasExcluir(MidiaPlaylist entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AbstractDAO<MidiaPlaylist> getDAO() {
		return midiaPlaylistDAO;
	}	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public MidiaPlaylist salvar(MidiaPlaylist entity){
		try{
			entity = super.save(entity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return entity;
	}


}
