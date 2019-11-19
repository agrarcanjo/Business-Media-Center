package com.bcm.midia.controller.terminal;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.bcm.midia.database.entity.PlaylistProgramacao;
import com.bcm.midia.database.entity.Terminal;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;
import com.bcm.midia.negocio.ProgramacaoBean;
import com.bcm.midia.negocio.TerminalBean;
import com.bcm.midia.util.AbstractCRUD;
import com.bcm.midia.util.Util;


@Named
@ConversationScoped
public class TerminalController extends AbstractCRUD<Terminal> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2693989382095359172L;

//	@Inject
//    private TerminalInterface senhaBean;

    @Inject
    private TerminalBean terminalBean;
    @Inject
	private ProgramacaoBean programacaoBean;
    
    private Terminal terminal;
    private List<Terminal> terminais;
    private List<Terminal> filteredTerminais;
    private List<PlaylistProgramacao> listTerminalProgramacao;
    
    @PostConstruct
    public void init() {
    	if(instance==null)
    		instance = new Terminal();	
    	if(!Util.isNotNullOrEmpty(filteredTerminais))
    		filteredTerminais = new ArrayList<Terminal>();
    }
      
	@Override
	protected Long getEntityId(Terminal referenceValue) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected Terminal load(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void deleteImpl(Terminal referenceValue) throws BusinessException, Exception {
		//terminalBean.remove(referenceValue);
	}


	@Override
	protected void saveImpl(Terminal referenceValue) throws RequiredException, BusinessException, Exception {
		if(referenceValue != null){
			instance.setDtCriacao(new Date(System.currentTimeMillis()));
			instance = terminalBean.save(referenceValue);
			mensagemInfo("registro.salvo.sucesso");
			limpar();
			init();
		}
	}

	public void limpar(){
		instance = new Terminal();
	}

	@Override
	protected void updateImpl(Terminal referenceValue) throws RequiredException, BusinessException, Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void consultarImpl() throws BusinessException, Exception {
		// TODO Auto-generated method stub
		
	}
	
	public String redirectProgramacao(Terminal t){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idTerminal", t.getIdTerminal());
//		super.setId(t.getIdTerminal());
		return includeRedirect("/pages/programacao/cadastro.xhtml");
	}


	@Override
	protected Terminal newInstance() {
		return new Terminal();
	}


	@Override
	protected List<Terminal> loadAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Terminal> getTerminais() {
		if(terminais==null){
			//terminais = new ArrayList<Terminal>();
			terminais=terminalBean.findAll();
		}
		return terminais;
	}

	public void setTerminais(List<Terminal> terminais) {
		this.terminais = terminais;
	}
	
	public Terminal getTerminal(){
		return terminal;
	}
	
	public void setTerminal(Terminal terminal){
		this.terminal = terminal;
	}
	
    public void onRowSelect(SelectEvent event) {
    	System.out.println("selecionou " + ((Terminal) event.getObject()).getNmTerminal());
//        FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
    	System.out.println("Deselecionou " + ( (Terminal) event.getObject()).getNmTerminal());
//        FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


	public List<Terminal> getFilteredTerminais() {
		return filteredTerminais;
	}


	public void setFilteredTerminais(List<Terminal> filteredTerminais) {
		this.filteredTerminais = filteredTerminais;
	}

	public void pegaProgramacaoDoTerminal(Terminal terminal) {
		if(!Util.isNotNullOrEmpty(terminal.getIdTerminal())){
			listTerminalProgramacao = new ArrayList<PlaylistProgramacao>();
			setListTerminalProgramacao(programacaoBean.findByTerminal(terminal.getIdTerminal()));
		}
	}

	public List<PlaylistProgramacao> getListTerminalProgramacao() {
		return listTerminalProgramacao;
	}

	public void setListTerminalProgramacao(List<PlaylistProgramacao> listTerminalProgramacao) {
		this.listTerminalProgramacao = listTerminalProgramacao;
	}
   
        
}
