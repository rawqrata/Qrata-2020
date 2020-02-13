package com.insonix.qrata.entity;

/**
 * @author Gurminder Singh
 *
 */
public class SiteReviewCommentsForm {
	private int id;
	private long siteReviewId;
	private String userName;
	private String comment;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the siteReviewId
	 */
	public long getSiteReviewId() {
		return siteReviewId;
	}
	/**
	 * @param siteReviewId the siteReviewId to set
	 */
	public void setSiteReviewId(long siteReviewId) {
		this.siteReviewId = siteReviewId;
	}
	
}
