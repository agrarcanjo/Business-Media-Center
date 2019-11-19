package com.bcm.midia.util;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.xml.soap.SOAPException;


/**
 * Classe base para qualquer outro controller
 */
public abstract class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -352916202186969370L;

	@Inject
	protected FacesMessager facesMessager;

	@Inject
	protected transient Logger logger;

	@Inject
	private PrimeFacesUtil primeFacesUtil;

	protected String htmlApplet;

	protected String facesRedirect = "?faces-redirect=true";

	private boolean visualizando = false;
	private boolean editando = false;
	private boolean fromResults = false;
	private boolean fromDetalhes = false;
	private boolean fromLista = false;

	/**
	 * Construtor default
	 */
	public BaseController() {
		super();
	}

	/**
	 * Faz update nos componentes dos respectivos ids passados como parâmetro
	 * 
	 * @param idComponente
	 */
	protected void updateComponentes(String... idComponente) {
		for (String id : idComponente) {
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(id);
		}
	}

	protected void updateComponente(String idComponente) {
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(idComponente);
	}

	protected String includeRedirect(String url) {
		String formatedUrl = url.concat(facesRedirect);
		return formatedUrl;
	}

	protected String getRootErrorMessage(Exception e) {

		if (e instanceof SOAPException) {
			facesMessager.warn(MensagemUtil.obterMensagem(e.getMessage()));
			return e.getMessage();
		}

		String errorMessage = MensagemUtil.obterMensagem("general.crud.rootErrorMessage");
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		e.printStackTrace();
		// This is the root cause message
		facesMessager.error(errorMessage);
		return errorMessage;
	}

	protected String createViolationResponse(Set<ConstraintViolation<?>> violations) {
		logger.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return responseObj.toString();
	}

	protected void logErroMessage() {
		logger.severe("--------------------------------------------");
		logger.severe("Ocorreu um erro ao Persistir o registro, verifique o log do servidor.");
		logger.severe("--------------------------------------------");
	}

		/**
	 * Ajusta o nome do arquivo quando for feito um upload pelo IE.
	 * 
	 * @param fileName
	 * @return
	 */
	private String ajustarNomeArquivo(String fileName) {
		if (fileName.contains("\\")) {
			int lastBarra = fileName.lastIndexOf("\\");
			fileName = fileName.substring(lastBarra + 1, fileName.length());
		}
		return fileName;
	}


	public String completaURL(String url) {
		String retorno = null;
		try {
			FacesContext ctxt = FacesContext.getCurrentInstance();
			ExternalContext ext = ctxt.getExternalContext();
			URI uri = new URI(ext.getRequestScheme(), null, ext.getRequestServerName(), ext.getRequestServerPort(), ext.getRequestContextPath(), null, null);
			retorno = uri.toASCIIString();
		} catch (URISyntaxException e) {
			throw new FacesException(e);
		}
		return retorno != null ? retorno + url : retorno;
	}




	public boolean isVisualizando() {
		return visualizando;
	}

	public void setVisualizando(boolean visualizando) {
		this.visualizando = visualizando;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public boolean isFromResults() {
		return fromResults;
	}

	public void setFromResults(boolean fromResults) {
		this.fromResults = fromResults;
	}

	public boolean isFromDetalhes() {
		return fromDetalhes;
	}

	public void setFromDetalhes(boolean fromDetalhes) {
		this.fromDetalhes = fromDetalhes;
	}

	public boolean isFromLista() {
		return fromLista;
	}

	public void setFromLista(boolean fromLista) {
		this.fromLista = fromLista;
	}
}