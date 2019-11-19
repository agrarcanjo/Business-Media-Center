package com.bcm.midia.controller.configuracoes;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bcm.midia.database.entity.Configuracao;
import com.bcm.midia.database.entity.Resolucao;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;
import com.bcm.midia.negocio.ConfiguracaoBean;
import com.bcm.midia.negocio.ResolucaoBean;
import com.bcm.midia.util.AbstractCRUD;


@Named
@ConversationScoped
public class ConfiguracaoController extends AbstractCRUD<Configuracao> implements Serializable {


	private static final long serialVersionUID = -2693989382095359172L;

	@Inject
    private ConfiguracaoBean configuracaoBean;
	
	@Inject
	private ResolucaoBean ResolucaoBean;
	
	private Resolucao resolucao;
	private List<Resolucao> resolucoes;
	
	    
    @PostConstruct
    public void init() { 
    	instance = new Configuracao();
    	configuracaoBean.findAll();
    }


	@Override
	protected Long getEntityId(Configuracao referenceValue) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected Configuracao load(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void deleteImpl(Configuracao referenceValue) throws BusinessException, Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void saveImpl(Configuracao referenceValue) throws RequiredException, BusinessException, Exception {
		if(referenceValue!=null){
			instance.setDtCriacao(new Date(System.currentTimeMillis()));
			instance = configuracaoBean.save(referenceValue);
			
		}		
	}
	
	public void salvarResolucao() throws RequiredException, BusinessException, Exception{
		if(resolucao!=null){
			resolucao.setDtCriacao(new Date(System.currentTimeMillis()));			
			resolucao = ResolucaoBean.save(resolucao);
			mensagemInfo("registro.salvo.sucesso");
			redirectTelaResultado();
		}
	}


	@Override
	protected void updateImpl(Configuracao referenceValue) throws RequiredException, BusinessException, Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void consultarImpl() throws BusinessException, Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected Configuracao newInstance() {
		return new Configuracao();
	}


	@Override
	protected List<Configuracao> loadAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public Resolucao getResolucao() {
		if(resolucao == null)
	    	resolucao = new Resolucao();
		return resolucao;
	}


	public void setResolucao(Resolucao resolucao) {
		this.resolucao = resolucao;
	}


	public List<Resolucao> getResolucoes() {
		if(resolucoes==null){
			resolucoes = ResolucaoBean.findAll();
		}
		return resolucoes;
	}


	public void setResolucoes(List<Resolucao> resolucoes) {
		this.resolucoes = resolucoes;
	}
    
    
}
