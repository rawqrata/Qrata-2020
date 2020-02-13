package com.insonix.qrata.models;

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

@Entity
@Table(name = "sitereviews_ratingcriteria_voting", schema = "public")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SiteReviewRatingCriteriaVoting extends CommonEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6971958407143190732L;
	private Long id;
	private SiteReviewRatingCriteria siteReviewRatingCriteria;
	private short criteriaVotingDecision;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sitereviewratingcriteriavoting_id_seq")
	@SequenceGenerator(name="sitereviewratingcriteriavoting_id_seq", initialValue=1, sequenceName="sitereviewratingcriteriavoting_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sitereviewrating_id")
	public SiteReviewRatingCriteria getSiteReviewRatingCriteria() {
		return siteReviewRatingCriteria;
	}
	public void setSiteReviewRatingCriteria(
			SiteReviewRatingCriteria siteReviewRatingCriteria) {
		this.siteReviewRatingCriteria = siteReviewRatingCriteria;
	}
	
	public short getCriteriaVotingDecision() {
		return criteriaVotingDecision;
	}
	public void setCriteriaVotingDecision(short criteriaVotingDecision) {
		this.criteriaVotingDecision = criteriaVotingDecision;
	}
	
	
}
