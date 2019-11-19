package com.bcm.midia.controller.programacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.bcm.midia.database.entity.Playlist;
import com.bcm.midia.database.entity.PlaylistProgramacao;
import com.bcm.midia.database.entity.PlaylistProgramacaoPK;
import com.bcm.midia.database.entity.Programacao;
import com.bcm.midia.database.entity.Terminal;
import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;
import com.bcm.midia.negocio.PlaylistBean;
import com.bcm.midia.negocio.ProgramacaoBean;
import com.bcm.midia.negocio.TerminalBean;
import com.bcm.midia.util.AbstractCRUD;
import com.bcm.midia.util.Util;

@Named
@ConversationScoped
public class ProgramacaoController extends AbstractCRUD<Programacao> implements Serializable {

	private static final long serialVersionUID = -2693989382095359172L;

	@Inject
	private ProgramacaoBean programacaoBean;
	@Inject
	private TerminalBean terminalBean;
	@Inject
	private PlaylistBean playlistBean;

	private List<Programacao> programacoes;
	private List<Terminal> terminaisSelect;
	private List<Terminal> terminais;
	private List<Playlist> playlist;
	private List<String> selectedDiaSemana;
	private List<PlaylistProgramacao> playProgramacao;

	private boolean seTerminalPreenchido = false;
	private boolean seSemDataFim = false;
	private boolean seTodosDiasSemana = false;
	private boolean seIntercalar = false;
	private boolean sePeriodoHora = false;

	private Terminal terminal;
	private Playlist selectedPlaylist;

	@PostConstruct
	public void init() {
		if(instance == null)
			instance = new Programacao();
		
		

		// Long idTerminal = (Long)
		// FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
		// .get("idTerminal");
		//
		// if (idTerminal != null) {
		// FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("idTerminal");
		// seTerminalPreenchido = true;
		// terminaisSelect.add(terminalBean.findById(idTerminal));
		// }
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}
	
	public List<PlaylistProgramacao> findByProgramacao(Long id){
		if(Util.isNotNullOrEmpty(id)){
			
		}
		return null;
	}

	@Override
	protected Long getEntityId(Programacao referenceValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Programacao load(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deleteImpl(Programacao referenceValue) throws BusinessException, Exception {
		// terminalBean.remove(referenceValue);
	}

	@Override
	protected void consultarImpl() throws BusinessException, Exception {
		// TODO Auto-generated method stub

	}

	public Date getDiaHoje() {
		return new Date();
	}

	public String getPlaceHolder() {
		if (seSemDataFim) {
			return "Sem data fim";
		} else {
			return "";
		}
	}
	
	public String pegaDias(List<String> lista){
		String dias="";
		for(String x : lista){
			dias+=x;
		}
		return dias;		
	}

	@Override
	protected void saveImpl(Programacao referenceValue) throws RequiredException, BusinessException, Exception {
		if (!terminaisSelect.isEmpty() && selectedPlaylist != null) {
			
			instance.setDtDiaSemana(pegaDias(selectedDiaSemana));
			instance.setDtCriacao((new Date(System.currentTimeMillis())));
			
			if (seSemDataFim)
				instance.setStSemDtFim('S');
			else
				instance.setStSemDtFim('N');
			
			if (seTodosDiasSemana)
				instance.setStTodosDias('S');
			else
				instance.setStTodosDias('N');
			
			if(seIntercalar)
				instance.setStIntercalar('S');
			else
				instance.setStIntercalar('N');				
						
			for (Terminal terminal : terminaisSelect) {
				//instance.setIdProgramacao(null);
				
				//salvar programação
				instance = programacaoBean.save(referenceValue);

				PlaylistProgramacao pp = new PlaylistProgramacao(instance, selectedPlaylist, terminal, 0);
				
				List<PlaylistProgramacao> pps = programacaoBean.findByTerminal(terminal.getIdTerminal());				
				
				//conta ordem da programação
				if (pps.isEmpty())
					pp.setNrOrdem(1);
				else
					pp.setNrOrdem(pps.size() + 1);

				programacaoBean.salvar(pp);
			}

			mensagemInfo("registro.salvo.sucesso");
			limparForm();
			redirectTelaResultado();
			
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Terminal ou Playlist não preenchidos", ""));
		}

	}

	@Override
	protected void updateImpl(Programacao referenceValue) throws RequiredException, BusinessException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Programacao newInstance() {
		return new Programacao();
	}

	@Override
	protected List<Programacao> loadAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Programacao> getProgramacoes() {
		if (programacoes == null)
			programacoes = programacaoBean.findAll();
		return programacoes;
	}

	public void setProgramacoes(List<Programacao> programacoes) {
		this.programacoes = programacoes;
	}

	public List<Terminal> getTerminais() {
		if (terminais == null)
			terminais = terminalBean.findAll();
		return terminais;
	}

	public void setTerminais(List<Terminal> terminais) {
		this.terminais = terminais;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public List<Terminal> getTerminaisSelect() {
		return terminaisSelect;
	}

	public void setTerminaisSelect(List<Terminal> terminaisSelect) {
		this.terminaisSelect = terminaisSelect;
	}

	public boolean isSeTerminalPreenchido() {
		return seTerminalPreenchido;
	}

	public void setSeTerminalPreenchido(boolean seTerminalPreenchido) {
		this.seTerminalPreenchido = seTerminalPreenchido;
	}

	public boolean isSeSemDataFim() {
		return seSemDataFim;
	}

	public void setSeSemDataFim(boolean seSemDataFim) {
		this.seSemDataFim = seSemDataFim;
	}

	public List<String> getSelectedDiaSemana() {
		return selectedDiaSemana;
	}

	public void setSelectedDiaSemana(List<String> selectedDiaSemana) {
		this.selectedDiaSemana = selectedDiaSemana;
	}

	public boolean isSeTodosDiasSemana() {
		return seTodosDiasSemana;
	}

	public void setSeTodosDiasSemana(boolean seTodosDiasSemana) {
		this.seTodosDiasSemana = seTodosDiasSemana;
	}

	public List<Playlist> getPlaylist() {
		if (playlist == null) {
			playlist = playlistBean.findAll();
		}
		return playlist;
	}

	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}

	public Playlist getSelectedPlaylist() {
		return selectedPlaylist;
	}

	public void setSelectedPlaylist(Playlist selectedPlaylist) {
		this.selectedPlaylist = selectedPlaylist;
	}

	public boolean isSeIntercalar() {
		return seIntercalar;
	}

	public void setSeIntercalar(boolean seIntercalar) {
		this.seIntercalar = seIntercalar;
	}

	public boolean isSePeriodoHora() {
		return sePeriodoHora;
	}

	public void setSePeriodoHora(boolean sePeriodoHora) {
		this.sePeriodoHora = sePeriodoHora;
	}

	public List<PlaylistProgramacao> getPlayProgramacao() {
		if(!Util.isNotNullOrEmpty(playProgramacao)){
			playProgramacao = new ArrayList<PlaylistProgramacao>();
			playProgramacao = programacaoBean.findAllProgramacaoTerminal();
		}
		return playProgramacao;
	}

	public void setPlayProgramacao(List<PlaylistProgramacao> playProgramacao) {
		this.playProgramacao = playProgramacao;
	}
	
	
}
