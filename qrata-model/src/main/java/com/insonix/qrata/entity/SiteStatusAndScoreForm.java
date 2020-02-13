package com.insonix.qrata.entity;

public class SiteStatusAndScoreForm {
	private Short reviewStatus;
	private Integer score;
	
	public SiteStatusAndScoreForm(Short reviewStatus,Integer score) {
		this.setReviewStatus(reviewStatus);
		this.score = score;
	}
	
	public Short getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Short reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
