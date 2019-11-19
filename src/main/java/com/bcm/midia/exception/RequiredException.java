package com.bcm.midia.exception;

import java.util.ArrayList;
import java.util.List;

public class RequiredException extends Exception {

	private static final long serialVersionUID = -7467608452356269598L;

	private List<String> erroList;
	
    public RequiredException() {
    }

    public RequiredException(String message) {
        super(message);
        erroList = new ArrayList<String>();
        erroList.add(message);
    }
    
    /**
     * 
     * @param erroList
     */
    public RequiredException(List<String> erroList) {
    	this.erroList = erroList;
    }
    
    /**
     * Exception que carrega todas as mensagens de validação.
     * @param message
     * @param erroList
     */
    public RequiredException(String message, List<String> erroList) {
    	super(message);
    	this.erroList = erroList;
    }

	public List<String> getErroList() {
		return erroList;
	}

	public void setErroList(List<String> erroList) {
		this.erroList = erroList;
	}
}
