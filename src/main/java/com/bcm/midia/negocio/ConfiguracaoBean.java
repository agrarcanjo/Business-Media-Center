package com.bcm.midia.negocio;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bcm.midia.database.dao.AbstractDAO;
import com.bcm.midia.database.dao.ConfiguracaoDAO;
import com.bcm.midia.database.entity.Configuracao;

@Stateless
@LocalBean
public class ConfiguracaoBean extends BussinessAbstract<Configuracao> implements Serializable{

	private static final long serialVersionUID = -4060121810055502474L;

	@Inject
	private ConfiguracaoDAO configuracaoDAO;
	
	@Override
	protected void validaCamposObrigatorios(Configuracao entity) {
//		if(entity != null && ( (Integer) entity.getPsHeigth() == 0 || entity.getPsWidth() == 0)){
//			mensagens.add("categoria.nome");
//		}
	}

	@Override
	protected void validaRegras(Configuracao entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validaRegrasExcluir(Configuracao entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AbstractDAO<Configuracao> getDAO() {
		return configuracaoDAO;
	}
	
	
	
}
