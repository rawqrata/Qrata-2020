/**
 * Author Gurminder
 * Date Created 06-Jun-2013 11:58:53 AM
 */
package com.qrata.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SiteRatingsInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5259849583969814492L;
	private String token;
	private List<Map<String, Object>> response = new ArrayList<>();
	
	/**
	 * Getter Method for token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * Setter Method for token
	 * Parameter to set token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * Getter Method for response
	 */
	public List<Map<String, Object>> getResponse() {
		return response;
	}
	/**
	 * Setter Method for response
	 * Parameter to set response
	 */
	public void setResponse(List<Map<String, Object>> response) {
		this.response = response;
	}
}
