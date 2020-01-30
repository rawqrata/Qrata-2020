package com.qrata.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
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
@Table(name = "ratingcriteria", schema = "public")
public class RatingCriteria extends CommonEntity implements Serializable{
	
	private static final long serialVersionUID = 4352427783686766666L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingcriteria_id_seq")
	@SequenceGenerator(name = "ratingcriteria_id_seq", initialValue = 1, sequenceName = "ratingcriteria_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name="description", columnDefinition="text")
	private String description;
	
	@Column(name = "bookmark", length = 50)
	private String bookmark;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ratingCriteria", cascade=CascadeType.ALL)
	private Set<TopicRatingCriteria> topicsRatingCriterias = null;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ratingCriteria" , cascade=CascadeType.ALL)
	private Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias = null;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private RatingCriteria parentRatingCriteria;
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="parentRatingCriteria")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<RatingCriteria> childRatingCriterias = null;
	
	@Column(name="criteria_type")
	private Short criteriaType;
	
	@Basic
	@Column(name="priority", nullable=false, length=10)	
	private int priority;

	/**
	 * 
	 */
	public RatingCriteria() {
		super();
	}

	/**
	 * @param name
	 */
	public RatingCriteria(String name) {
		super();
		this.name = name;
	}
	
	
	@Override
	public void setSortName(String sortName){
		super.setSortName(sortName);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBookmark() {
		return bookmark;
	}

	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}

	public Set<TopicRatingCriteria> getTopicsRatingCriterias() {
		return topicsRatingCriterias;
	}

	public void setTopicsRatingCriterias(Set<TopicRatingCriteria> topicsRatingCriterias) {
		this.topicsRatingCriterias = topicsRatingCriterias;
	}

	public Set<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias() {
		return siteReviewsRatingCriterias;
	}

	public void setSiteReviewsRatingCriterias(Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias) {
		this.siteReviewsRatingCriterias = siteReviewsRatingCriterias;
	}

	public RatingCriteria getParentRatingCriteria() {
		return parentRatingCriteria;
	}

	public void setParentRatingCriteria(RatingCriteria parentRatingCriteria) {
		this.parentRatingCriteria = parentRatingCriteria;
	}

	public Set<RatingCriteria> getChildRatingCriterias() {
		return childRatingCriterias;
	}

	public void setChildRatingCriterias(Set<RatingCriteria> childRatingCriterias) {
		this.childRatingCriterias = childRatingCriterias;
	}

	public Short getCriteriaType() {
		return criteriaType;
	}

	public void setCriteriaType(Short criteriaType) {
		this.criteriaType = criteriaType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
