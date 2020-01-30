package com.qrata.entity;

/**
 * @author Gurminder
 *
 */
public class SiteReviewsInfo {
	private String url;
	private int score;
	private long id;

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

	/**
	 * Setter for id
	 * @param id - parameter to set id
	 */
	public void setId(long id) { this.id = id; }

	/**
	 * Getter for id
	 * @return - value of id
	 */
	public long getId(){ return id; }
}
