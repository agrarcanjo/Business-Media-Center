package com.bcm.midia.util;

import java.io.Serializable;

import org.primefaces.context.RequestContext;

public class PrimeFacesUtil implements Serializable {
	
	private static final long serialVersionUID = 1509300678409483683L;

	public void executarJavaScript(String script) {
		RequestContext.getCurrentInstance().execute(script);
	}
	
	/**
	 * Esconde o componente do widgetvar especificado
	 * @param widgetvar
	 */
	public void hideDialog(String widgetvar) {
		RequestContext.getCurrentInstance().execute(String.format("PF('%s').hide();", widgetvar));
	}
	
	/**
	 * Mostra o componente do widgetvar especificado
	 * @param widgetvar
	 */
	public void showDialog(String widgetvar) {
		RequestContext.getCurrentInstance().execute(String.format("PF('%s').show();", widgetvar));
	}

	/**
	 * Mostra o componente do widgetvar especificado
	 * @param widgetvar
	 */
	public void updateMessage(String widgetvar) {
		RequestContext.getCurrentInstance().update(widgetvar); 
	}
	

}
