package com.insonix.qrata.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Gurminder
 *
 */
@Entity
@Table(name="sitereview_comments", schema="public")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SiteReviewComments extends CommonEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1751256459647939235L;

	private int id;
	private String comment;
	private int readStatus;
	private User commentedBy;
	private SiteReview siteReview;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sitereviews_comments_id_seq")
	@SequenceGenerator(name="sitereviews_comments_id_seq", sequenceName="sitereviews_comments_id_seq", allocationSize=1, initialValue=1)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="comment", nullable=false, columnDefinition="text")
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="commented_by", nullable=false)
	public User getCommentedBy() {
		return commentedBy;
	}
	
	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sitereview_id", nullable=false)
	public SiteReview getSiteReview() {
		return siteReview;
	}
	
	public void setSiteReview(SiteReview siteReview) {
		this.siteReview = siteReview;
	}
	
	
	@Column(name="read_status", nullable=false)	
	/**
	 * @return the readStatus
	 */
	public int getReadStatus() {
		return readStatus;
	}

	/**
	 * @param readStatus the readStatus to set
	 */
	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	/**
	 * 
	 */
	public SiteReviewComments() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SiteReviewComments [id=" + id + ", comment=" + comment
				+ ", commentedBy=" + commentedBy + "]";
	}
	
}
