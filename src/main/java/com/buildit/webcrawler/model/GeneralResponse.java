package com.buildit.webcrawler.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Response class used for all kind of responses,
 * @author DELL
 *
 */
public class GeneralResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8496224792752033894L;
	
	private String message;
	private Integer errorCode;
	private Map<String, Object> result;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

}
