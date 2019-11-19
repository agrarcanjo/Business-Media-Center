package com.bcm.midia.controller.midia;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import com.bcm.midia.database.entity.Midia;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;
import com.bcm.midia.negocio.MidiaBean;
import com.bcm.midia.util.AbstractCRUD;
import com.bcm.midia.util.Util;

@Named
@ConversationScoped
public class MidiaController extends AbstractCRUD<Midia> implements Serializable {

	private static final long serialVersionUID = -2693989382095359172L;

	@Inject
	private MidiaBean midiaBean;

	private List<Midia> midias;
	private List<Midia> selectMidias;
	private List<Midia> MidiasRss;
	private Midia midiaSelect;

	final String path = // FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")
			"C:\\videos\\";

	@PostConstruct
	public void init() {
		if (!Util.isNotNullOrEmpty(instance)) {
			instance = new Midia();
		}
	}

	public void doUpload(FileUploadEvent event) {
		instance.setPath(path);
		instance.setEvent(event);
		// salvar no banco
		instance = midiaBean.salvar(instance);
		if(instance!=null){
			mensagemInfo("registro.salvo.sucesso");
		}
	}
	
	public void atualizaRSS(){
		if(!Util.isNotNullOrEmpty(instance) && !Util.isNotNullOrEmpty(instance.getDsCaminho())){
			
		}
	}

	@Override
	protected Long getEntityId(Midia referenceValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Midia load(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String delete(Long referenceValue){
		try{
			Long x = referenceValue;
			deleteImpl(midiaSelect);
		} catch (BusinessException be) {
			facesMessager.warn("Não possível deletar: "+be);
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}
		return "";
	}

	@Override
	protected void deleteImpl(Midia referenceValue) throws BusinessException, Exception {
		if (referenceValue != null) {
			midiaBean.remove(referenceValue);
			mensagemInfo("registro.removido.sucesso");
		} else {
			if (!selectMidias.isEmpty()) {
				for (Midia select : getSelectMidias()) {
					if (select != null) {
						midiaBean.remove(select);
						mensagemInfo("registro.removido.sucesso");
					}
				}
			}
		}
	}

	public void salvarRSS() throws RequiredException, BusinessException, Exception {
		instance.setDtCriacao((new Date(System.currentTimeMillis())));
		instance.setNmExtensao("xml");
		instance = midiaBean.save(instance);
		mensagemInfo("registro.salvo.sucesso");
		limparForm();
		redirectTelaResultado();
	}

	@Override
	protected void saveImpl(Midia referenceValue) throws RequiredException, BusinessException, Exception {
		if (!Util.isNotNullOrEmpty(referenceValue)) {
			instance = midiaBean.save(referenceValue);
			mensagemInfo("registro.salvo.sucesso");
			redirectTelaResultado();
		}
	}

	@Override
	protected void updateImpl(Midia referenceValue) throws RequiredException, BusinessException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void consultarImpl() throws BusinessException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Midia newInstance() {
		return new Midia();
	}

	@Override
	protected List<Midia> loadAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Midia> getMidias() {
		if (midias == null)
			setMidias(midiaBean.findAll());
		return midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

	public List<Midia> getSelectMidias() {
		return selectMidias;
	}

	public void setSelectMidias(List<Midia> selectMidias) {
		this.selectMidias = selectMidias;
	}

	public void selecionarMidia(Midia midia) {
		if (midia != null)
			selectMidias.add(midia);
	}

	public List<Midia> getMidiasRss() {
		if (MidiasRss == null)
			MidiasRss = midiaBean.findRss();
		return MidiasRss;
	}

	public void setMidiasRss(List<Midia> midiasRss) {
		MidiasRss = midiasRss;
	}

	public Midia getMidiaSelect() {
		if(!Util.isNotNullOrEmpty(midiaSelect)){
			midiaSelect = new Midia();
		}
		return midiaSelect;
	}

	public void setMidiaSelect(Midia midiaSelect) {
		this.midiaSelect = midiaSelect;
	}

}
