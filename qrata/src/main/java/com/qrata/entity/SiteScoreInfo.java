/**
 * Author Gurminder
 * Date Created 09-May-2013 6:53:34 PM
 */
package com.qrata.entity;

import java.util.ArrayList;
import java.util.List;

public class SiteScoreInfo {
	private String token;
	private List<SiteReviewsInfo> response = new ArrayList<>();
	
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
	public List<SiteReviewsInfo> getResponse() {
		return response;
	}
	/**
	 * Setter Method for response
	 * Parameter to set response
	 */
	public void setResponse(List<SiteReviewsInfo> response) {
		this.response = response;
	}
	
}
