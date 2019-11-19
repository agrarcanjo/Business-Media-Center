package com.bcm.midia.controller.playlist;

import java.beans.Transient;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.bcm.midia.database.entity.Midia;
import com.bcm.midia.database.entity.MidiaPlaylist;
import com.bcm.midia.database.entity.MidiaPlaylistPK;
import com.bcm.midia.database.entity.Playlist;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;
import com.bcm.midia.negocio.MidiaBean;
import com.bcm.midia.negocio.MidiaPlaylistBean;
import com.bcm.midia.negocio.PlaylistBean;
import com.bcm.midia.util.AbstractCRUD;
import com.bcm.midia.util.Util;


@Named
@ConversationScoped
public class PlaylistController extends AbstractCRUD<Playlist> implements Serializable {

	private static final long serialVersionUID = -2693989382095359172L;

    @Inject
    private PlaylistBean playlistBean;
    @Inject
    private MidiaPlaylistBean midiaPlaylistBean; 
    @Inject 
    private MidiaBean midiaBean;
    
    private DualListModel<Midia> dualListMidias;
    private List<Midia> midiasTarget;
    private List<Midia> midiasSource;
    private List<Playlist> filteredMidias;
    private List<Playlist> playlist;
    private List<MidiaPlaylist> midiasPlaylist;
    private Playlist selectPlaylist;
    private List<Midia> midiasReOrder;
    
    @PostConstruct
    public void init() {
    	instance = new Playlist();
    }
    
    
    public void limpar(){
    	dualListMidias = null;
    }
    
    public void onTransfer(TransferEvent event) {
    	//insere todas novamente ou só removida
    	//midias.setSource(midiaBean.findAll());
        for(Object item : event.getItems()) {
        	dualListMidias.getSource().add((Midia) item);
        }
    } 
    
	@Override
	protected Long getEntityId(Playlist referenceValue) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected Playlist load(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void deletar() throws BusinessException, Exception{
		if(getInstance().getIdPlaylist()!=null){
			deleteImpl(getInstance());
		}
	}

	@Override
	protected void deleteImpl(Playlist referenceValue) throws BusinessException, Exception {
		if(getInstance()!=null)
			playlistBean.remove(getInstance());
	}


	@Override
	protected void saveImpl(Playlist referenceValue) throws RequiredException, BusinessException, Exception {
		if(referenceValue != null){
			instance.setDtCriacao(new Date(System.currentTimeMillis()));
			instance.setQtMidiaTransmissao(dualListMidias.getTarget().size());
			
			try{				
				instance = playlistBean.salvarPlaylist(referenceValue);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			int i = 1;
			for(Midia midias : dualListMidias.getTarget()){	
				MidiaPlaylist midiaPlaylist1 = new MidiaPlaylist(midias, instance, i++);				
				midiaPlaylistBean.salvar(midiaPlaylist1);
			}			
			mensagemInfo("registro.salvo.sucesso");
		}	
		
		//limpar();
		//init();
		
	}

	@Override
	protected void updateImpl(Playlist referenceValue) throws RequiredException, BusinessException, Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void consultarImpl() throws BusinessException, Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected Playlist newInstance() {
		return new Playlist();
	}


	@Override
	protected List<Playlist> loadAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public PlaylistBean getPlaylistBean() {
		return playlistBean;
	}


	public void setPlaylistBean(PlaylistBean playlistBean) {
		this.playlistBean = playlistBean;
	}


	public DualListModel<Midia> getDualListMidias() {
		if(dualListMidias == null){
			midiasSource = new ArrayList<Midia>(midiaBean.findAll());
	    	midiasTarget = new ArrayList<Midia>();
			dualListMidias = new DualListModel<Midia>(midiasSource, midiasTarget);
		}
		return dualListMidias;
	}


	public void setDualListMidias(DualListModel<Midia> midias) {
		this.dualListMidias = midias;
	}

	public List<Midia> getMidiasSource() {
		return midiasSource;
	}

	public void setMidiasSource(List<Midia> midiasSource) {
		this.midiasSource = midiasSource;
	}

	public List<Playlist> getFilteredMidias() {
		return filteredMidias;
	}

	public void setFilteredMidias(List<Playlist> filteredMidias) {
		this.filteredMidias = filteredMidias;
	}

	public List<Playlist> getPlaylist() {
		if(playlist == null){
			playlist = playlistBean.findAll();
		}
		return playlist;
	}

	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}
	
	public void findMidiasDaPlay(Playlist play){
		if(Util.isNotNullOrEmpty(play)){
			midiasPlaylist = playlistBean.findMidiasbyIdPlaylist(play);
		}
	}

	public List<MidiaPlaylist> getMidiasPlaylist() {
//		if(selectPlaylist != null){
//			midiasPlaylist = playlistBean.findMidiasbyIdPlaylist(selectPlaylist);
//		}
		return midiasPlaylist;
	}
	
	public void setMidiasPlaylist(List<MidiaPlaylist> midiasPlaylist) {
		this.midiasPlaylist = midiasPlaylist;
	}

	public Playlist getSelectPlaylist() {
		return selectPlaylist;
	}

	public void setSelectPlaylist(Playlist selectPlaylist) {
		this.selectPlaylist = selectPlaylist;
	}

	public List<Midia> getMidiasReOrder() {
		if(selectPlaylist != null){
			midiasReOrder = new ArrayList<Midia>();
		}
		return midiasReOrder;
	}

	public void setMidiasReOrder(List<Midia> midiasReOrder) {
		this.midiasReOrder = midiasReOrder;
	}
	
}
