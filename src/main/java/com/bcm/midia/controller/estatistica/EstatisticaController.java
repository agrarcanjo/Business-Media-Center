package com.bcm.midia.controller.estatistica;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bcm.midia.negocio.CategoriaBean;
import com.bcm.midia.negocio.LoginBean;
import com.bcm.midia.negocio.MidiaBean;
import com.bcm.midia.negocio.PlaylistBean;
import com.bcm.midia.negocio.ProgramacaoBean;
import com.bcm.midia.negocio.TerminalBean;

@Named
@ConversationScoped
public class EstatisticaController  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2875345839133558809L;
	
	
	@Inject
	private CategoriaBean categoriaBean;
	
	@Inject
	private MidiaBean midiaBean;
	
	@Inject
	private PlaylistBean playlistBean;
	
	@Inject
	private ProgramacaoBean programacaoBean;
	
	@Inject
	private TerminalBean terminalBean;
	
	@Inject
	private LoginBean usuarioBean;
	
	
	public Long getTotalCategoria(){
		return categoriaBean.countCategorias();
	}
	
	public Long getTotalMidia(){
		return midiaBean.countMidias();
	}
	
	public Long getTotalPlaylist(){
		return playlistBean.countPlaylist();
	}
	
	public Long getTotalProgramacao(){
		return programacaoBean.countProgramacao();
	}
	
	public Long getTotalTerminal(){
		return terminalBean.countTerminal();
	}
	
	public Long getTotalUsuario(){
		return usuarioBean.countUsuario();
	}
	
	public Long getTotalUsuarioTransacao(){
		return usuarioBean.countTransacoes();
	}
	
	
}
