package com.qrata.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sitereviews_ratingcriteria", schema = "public")
public class SiteReviewRatingCriteria {

	
	private static final long serialVersionUID = -6518413094945787753L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sitereviewratingcriteria_id_seq")
	@SequenceGenerator(name="sitereviewratingcriteria_id_seq", initialValue=1, sequenceName="sitereviewratingcriteria_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	private Site sites;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ratingcriteria_id")
	private RatingCriteria ratingCriteria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topics;
	
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name = "sitereview_id")
	private SiteReview siteReviews;
	
	@Column(name = "weight")
	private Integer weight;
	
	@Transient
	private short votingPercentage;
	
	@OneToMany(mappedBy = "siteReviewRatingCriteria", cascade = CascadeType.ALL)
	private List<SiteReviewRatingCriteriaVoting> criteriaVotings;

	public SiteReviewRatingCriteria() {
	}

	public SiteReviewRatingCriteria(long id) {
		this.id = id;
	}

	
	
	/**
	 * @param userByCreatedBy
	 * @param userByUserId
	 * @param sites
	 * @param ratingCriteria
	 * @param topics
	 * @param sitereviews
	 * @param weight
	 * @param status
	 * @param lastUpdated
	 * @param dateCreated
	 * @param uuid
	 */
	public SiteReviewRatingCriteria(
			Site sites, RatingCriteria ratingCriteria, Topic topics,
			SiteReview siteReviews, Integer weight, Short status,
			Date lastUpdated, Date dateCreated, Serializable uuid) {
		super();
		this.sites = sites;
		this.ratingCriteria = ratingCriteria;
		this.topics = topics;
		this.siteReviews = siteReviews;
		this.weight = weight;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Site getSites() {
		return sites;
	}

	public void setSites(Site sites) {
		this.sites = sites;
	}

	public RatingCriteria getRatingCriteria() {
		return ratingCriteria;
	}

	public void setRatingCriteria(RatingCriteria ratingCriteria) {
		this.ratingCriteria = ratingCriteria;
	}

	public Topic getTopics() {
		return topics;
	}

	public void setTopics(Topic topics) {
		this.topics = topics;
	}

	public SiteReview getSiteReviews() {
		return siteReviews;
	}

	public void setSiteReviews(SiteReview siteReviews) {
		this.siteReviews = siteReviews;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public short getVotingPercentage() {
		return votingPercentage;
	}

	public void setVotingPercentage(short votingPercentage) {
		this.votingPercentage = votingPercentage;
	}

	public List<SiteReviewRatingCriteriaVoting> getCriteriaVotings() {
		return criteriaVotings;
	}

	public void setCriteriaVotings(List<SiteReviewRatingCriteriaVoting> criteriaVotings) {
		this.criteriaVotings = criteriaVotings;
	}

}
