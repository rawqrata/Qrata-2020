package com.qrata.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "sitereviews", schema = "public")
public class SiteReview extends CommonEntity implements Serializable {
	
private static final long serialVersionUID = -3122358534861557426L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sitereviews_id_seq")
	@SequenceGenerator(name = "sitereviews_id_seq", initialValue = 1, sequenceName = "sitereviews_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	private Site site;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topics;
	
	@Column(name = "status_code")
	private Integer statusCode;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "description" ,  columnDefinition="text")
	private String description;
	
	@Column(name = "evaluation" ,  columnDefinition="text")
	private String evaluation;
	
	@Column(name="strength" ,  columnDefinition="text")
	private String strength;
	
	@Column(name="weakness" ,  columnDefinition="text")
	private String weakness;
	
	@Column(name="searchvector")
	private String searchvector;
	
	@Column(name = "read_status")
	private int readStatus;
	
	@Column(name="review_status")
	private Short reviewStatus;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "siteReviews" , cascade = CascadeType.REMOVE)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias = 
			new HashSet<SiteReviewRatingCriteria>(0);
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, mappedBy="siteReview")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<SiteReviewComments> siteReviewComments = new HashSet<>();

	public SiteReview() {
	}

	public SiteReview(long id) {
		this.id = id;
	}

	public SiteReview(long id, User user, Site site, Topic topics,
			Integer statusCode, Integer score, String description,
			String evaluation, Long createdBy, Date dateCreated,
			Date lastUpdated, Serializable uuid,
			Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias) {
		this.id = id;
		this.user = user;
		this.site = site;
		this.topics = topics;
		this.statusCode = statusCode;
		this.score = score;
		this.description = description;
		this.evaluation = evaluation;
		this.siteReviewsRatingCriterias = siteReviewsRatingCriterias;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Topic getTopics() {
		return topics;
	}

	public void setTopics(Topic topics) {
		this.topics = topics;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	public String getSearchvector() {
		return searchvector;
	}

	public void setSearchvector(String searchvector) {
		this.searchvector = searchvector;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public Short getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Short reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Set<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias() {
		return siteReviewsRatingCriterias;
	}

	public void setSiteReviewsRatingCriterias(Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias) {
		this.siteReviewsRatingCriterias = siteReviewsRatingCriterias;
	}

	public Set<SiteReviewComments> getSiteReviewComments() {
		return siteReviewComments;
	}

	public void setSiteReviewComments(Set<SiteReviewComments> siteReviewComments) {
		this.siteReviewComments = siteReviewComments;
	}

	
}
