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
@Table(name = "sitereviews_ratingcriteria_voting", schema = "public")
public class SiteReviewRatingCriteriaVoting extends CommonEntity implements Serializable {
	
	private static final long serialVersionUID = 6971958407143190732L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sitereviewratingcriteriavoting_id_seq")
	@SequenceGenerator(name="sitereviewratingcriteriavoting_id_seq", initialValue=1, sequenceName="sitereviewratingcriteriavoting_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sitereviewrating_id")
	private SiteReviewRatingCriteria siteReviewRatingCriteria;
	

	private short criteriaVotingDecision;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public SiteReviewRatingCriteria getSiteReviewRatingCriteria() {
		return siteReviewRatingCriteria;
	}


	public void setSiteReviewRatingCriteria(SiteReviewRatingCriteria siteReviewRatingCriteria) {
		this.siteReviewRatingCriteria = siteReviewRatingCriteria;
	}


	public short getCriteriaVotingDecision() {
		return criteriaVotingDecision;
	}


	public void setCriteriaVotingDecision(short criteriaVotingDecision) {
		this.criteriaVotingDecision = criteriaVotingDecision;
	}



}
