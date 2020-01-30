package com.qrata.entity;

public class SiteReviewRatingCriteriaVoteForm {

	private long siteReviewRaingCriteriaId;
	private int totalSiteReviewId;
	private int totalCriteriaVotingDecision;
	private short criteriaVotingDecision;
	
	public long getSiteReviewRaingCriteriaId() {
		return siteReviewRaingCriteriaId;
	}
	public void setSiteReviewRaingCriteriaId(long siteReviewRaingCriteriaId) {
		this.siteReviewRaingCriteriaId = siteReviewRaingCriteriaId;
	}
	public int getTotalSiteReviewId() {
		return totalSiteReviewId;
	}
	public void setTotalSiteReviewId(int totalSiteReviewId) {
		this.totalSiteReviewId = totalSiteReviewId;
	}
	public int getTotalCriteriaVotingDecision() {
		return totalCriteriaVotingDecision;
	}
	public void setTotalCriteriaVotingDecision(int totalCriteriaVotingDecision) {
		this.totalCriteriaVotingDecision = totalCriteriaVotingDecision;
	}
	public short getCriteriaVotingDecision() {
		return criteriaVotingDecision;
	}
	public void setCriteriaVotingDecision(short criteriaVotingDecision) {
		this.criteriaVotingDecision = criteriaVotingDecision;
	}
	
	@Override
	public String toString() {
		return "SiteReviewRatingCriteriaVoteForm [siteReviewRaingCriteriaId="
				+ siteReviewRaingCriteriaId + ", totalSiteReviewId="
				+ totalSiteReviewId + ", totalCriteriaVotingDecision="
				+ totalCriteriaVotingDecision + ", criteriaVotingDecision="
				+ criteriaVotingDecision + "]";
	}
	
	
}
