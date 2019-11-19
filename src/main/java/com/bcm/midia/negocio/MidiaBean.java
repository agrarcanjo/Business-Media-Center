package com.bcm.midia.negocio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.MidiaDAO;
import com.bcm.midia.database.entity.Midia;
import com.bcm.midia.database.entity.MidiaPlaylist;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;

@Stateless
@LocalBean
public class MidiaBean extends BussinessAbstract<Midia> implements Serializable {

	private static final long serialVersionUID = 6385902227601083543L;

	@Inject
	private MidiaDAO midiasDAO;

	public List<Midia> findAll() {
		return midiasDAO.findAll();
	}
	
	public List<Midia> findRss(){
		return midiasDAO.findRss();
	}
	
	public List<Midia> findByIds(List<MidiaPlaylist> midias){
		return midiasDAO.findByIds(midias);
	}
	
	public Long countMidias(){
		return midiasDAO.countMidia();
	}

	@Override
	protected void validaCamposObrigatorios(Midia entity) {
//		if (entity != null && (entity.getNmMidia() == null || entity.getNmMidia().trim().equals(""))) {
//			mensagens.add("midia.nome");
//		}
	}

	@Override
	protected void validaRegras(Midia entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validaRegrasExcluir(Midia entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected AbstractDAO<Midia> getDAO() {
		return midiasDAO;
	}

	public Midia salvaArquivo(Midia entity) {
		try{
	    	File file = new File(entity.getPath() + entity.getEvent().getFile().getFileName()+"_"+entity.getIdMidia());

	    	//Setar nome do arquivo na entidade midia    	
	    	entity.setNmMidia(entity.getEvent().getFile().getFileName()+"_"+entity.getIdMidia());
	    	entity.setDtCriacao(new Date(System.currentTimeMillis()));
	    	
	    	// criar diretório se não existe
	    	File caminho1 = new File(entity.getPath());
	        if (!caminho1.isDirectory()) {
	            caminho1.mkdirs();
	        }
			
	        
			InputStream is = entity.getEvent().getFile().getInputstream();
			OutputStream out = new FileOutputStream(file);
			byte buf[] = new byte[1024];
			int len;
			while ((len = is.read(buf)) > 0)
				out.write(buf, 0, len);
			is.close();
			out.close();
			
		}catch(Exception e){
			
		}
		
		return entity;
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Midia salvar(Midia entity){
		try {
			Midia old = entity;
			entity = super.save(entity);			
			entity.setPath(old.getPath());
			entity.setEvent(old.getEvent());			
			entity = salvaArquivo(entity);			
			super.update(entity);
			
		} catch (RequiredException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Midia salvarSemArquivo(Midia entity){
		try{
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
