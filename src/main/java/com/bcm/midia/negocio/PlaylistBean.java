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
import com.bcm.midia.database.dao.PlaylistDAO;
import com.bcm.midia.database.entity.MidiaPlaylist;
import com.bcm.midia.database.entity.Playlist;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;

@Stateless
@LocalBean
public class PlaylistBean extends BussinessAbstract<Playlist> implements Serializable{

	
	private static final long serialVersionUID = 5960649558753217291L;

	@Inject
	private PlaylistDAO playlistDAO;

    @Inject
    private MidiaPlaylistDAO midiaPlaylistDAO;
    
	public Long countPlaylist(){
		return playlistDAO.countPlaylist();
	}
    
	public List<Playlist> findAll(){
		return playlistDAO.findAll();
	}
	
	public List<MidiaPlaylist> findMidiasbyIdPlaylist(Playlist p){
		return midiaPlaylistDAO.findById(p);
	}
	
	@Override
	protected void validaCamposObrigatorios(Playlist entity) {
		if(entity != null && (entity.getNmPlaylist().trim().equals("") || entity.getNmPlaylist() == null)){
			mensagens.add("playlist.nome");
		}
		
		if(entity.getQtMidiaTransmissao() == 0){
			mensagens.add("playlist.midias");
		}
		
	}

	@Override
	protected void validaRegras(Playlist entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validaRegrasExcluir(Playlist entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AbstractDAO<Playlist> getDAO() {
		return playlistDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Playlist salvarPlaylist(Playlist entity){
		try {
			entity = super.save(entity);
		} catch (RequiredException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	

}
