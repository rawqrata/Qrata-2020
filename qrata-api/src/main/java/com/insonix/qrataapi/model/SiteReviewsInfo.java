package com.insonix.qrataapi.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Gurminder
 *
 */
@XmlRootElement
public class SiteReviewsInfo {
	private String url;
	private int score;
	
	/**
	 * Getter Method for url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Setter Method for url
	 * Parameter to set url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Getter Method for score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Setter Method for score
	 * Parameter to set score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}
