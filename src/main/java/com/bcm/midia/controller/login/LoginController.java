package com.bcm.midia.controller.login;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.bcm.midia.database.entity.Usuario;
import com.bcm.midia.negocio.LoginBean;


@Named("login")
@SessionScoped
public class LoginController  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5500204719129662105L;

	private Usuario usuario;
	
	@Inject 
	private LoginBean loginBean;

	public String login() throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = loginBean.login(usuario);
		if(usuario != null){
			 HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			session.setAttribute("usuarioLogado", usuario);
			
			return "/pages/dashboard/index.xhtml?faces-redirect=true";
		}else{
	       FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
	       mensagem.setSeverity(FacesMessage.SEVERITY_WARN);
	       context.addMessage(null, mensagem);
		}
		
		return null;
	}
    
	
	 public String logout() {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
	        session.invalidate();
	        facesContext.getAttributes().remove("usuarioLogado");
	        return "/login.xhtml?faces-redirect=true";
	 }


	public Usuario getUsuario() {
		if(this.usuario == null){
			this.usuario = new Usuario();
		}
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
