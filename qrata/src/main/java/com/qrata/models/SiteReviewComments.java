package com.qrata.models;

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

@Entity
@Table(name="sitereview_comments", schema="public")
public class SiteReviewComments extends CommonEntity implements Serializable{

	private static final long serialVersionUID = -1751256459647939235L;
	
	public SiteReviewComments() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sitereviews_comments_id_seq")
	@SequenceGenerator(name="sitereviews_comments_id_seq", sequenceName="sitereviews_comments_id_seq", allocationSize=1, initialValue=1)
	private int id;
	
	@Column(name="comment", nullable=false, columnDefinition="text")
	private String comment;
	
	@Column(name="read_status", nullable=false)	
	private int readStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="commented_by", nullable=false)
	private User commentedBy;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sitereview_id", nullable=false)
	private SiteReview siteReview;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public User getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}

	public SiteReview getSiteReview() {
		return siteReview;
	}

	public void setSiteReview(SiteReview siteReview) {
		this.siteReview = siteReview;
	}
	
	
	
	
	
}
