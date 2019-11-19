package com.bcm.midia.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -4806939480794045985L;

	private List<String> erroList;
	
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
        erroList = new ArrayList<String>();
        erroList.add(message);
    }
    
    /**
     * 
     * @param erroList
     */
    public BusinessException(List<String> erroList) {
    	this.erroList = erroList;
    }
    
    /**
     * Exception que carrega todas as mensagens de validação.
     * @param message
     * @param erroList
     */
    public BusinessException(String message, List<String> erroList) {
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
