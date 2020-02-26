package com.insonix.qrata.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.insonix.qrata.models.SiteReviewRatingCriteria;


/**
 * @author Gurminder
 *
 */
public class SiteReviewForm {
	private long id;
	private long userId;
	private long siteId;
	private String siteName;
	private long topicId;
	private String topicName;
	private int statusCode;
	private int score;
	private String description;
	private String evaluation;
	private String strength;
	private String weakness;
	private Short status;
	private Date dateCreated;
	private Date dateUpdated;
	private String uuid;	
	private String url;
	private short reviewStatus;
	private String reviewStatusName;
	private long createdBy;
	private String expertFirstName;
	private String expertLastName;	
	private long expertId;
	private String roleName;
	private String imageName;
	private int readStatus;
	private List<Map<String, Set<SiteReviewRatingCriteria>>> parentCriteriaWithChildren;
	private Set<SiteReviewRatingCriteria> siteReviewRatingCriterias = new HashSet<>();
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	public long getExpertId() {
		return expertId;
	}
	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the siteId
	 */
	public long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	/**
	 * @return the topicId
	 */
	public long getTopicId() {
		return topicId;
	}
	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the evaluation
	 */
	public String getEvaluation() {
		return evaluation;
	}
	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	/**
	 * @return the strength
	 */
	public String getStrength() {
		return strength;
	}
	/**
	 * @param strength the strength to set
	 */
	public void setStrength(String strength) {
		this.strength = strength;
	}
	/**
	 * @return the weakness
	 */
	public String getWeakness() {
		return weakness;
	}
	/**
	 * @param weakness the weakness to set
	 */
	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	/**
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
	}
	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	/**
	 * @return the dateUpdated
	 */
	public Date getDateUpdated() {
		return dateUpdated;
	}
	/**
	 * @param dateUpdated the dateUpdated to set
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public short getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(short reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getReviewStatusName() {
		return reviewStatusName;
	}
	public void setReviewStatusName(String reviewStatusName) {
		this.reviewStatusName = reviewStatusName;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public String getExpertFirstName() {
		return expertFirstName;
	}
	public void setExpertFirstName(String expertFirstName) {
		this.expertFirstName = expertFirstName;
	}
	public String getExpertLastName() {
		return expertLastName;
	}
	public void setExpertLastName(String expertLastName) {
		this.expertLastName = expertLastName;
	}
	/**
	 * @return the siteReviewRatingCriterias
	 */
	public Set<SiteReviewRatingCriteria> getSiteReviewRatingCriterias() {
		return siteReviewRatingCriterias;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}
	/**
	 * @param siteReviewRatingCriterias the siteReviewRatingCriterias to set
	 */
	public void setSiteReviewRatingCriterias(
			Set<SiteReviewRatingCriteria> siteReviewRatingCriterias) {
		this.siteReviewRatingCriterias = siteReviewRatingCriterias;
	}
	public List<Map<String, Set<SiteReviewRatingCriteria>>> getParentCriteriaWithChildren() {
		return parentCriteriaWithChildren;
	}
	public void setParentCriteriaWithChildren(
			List<Map<String, Set<SiteReviewRatingCriteria>>> parentCriteriaWithChildren) {
		this.parentCriteriaWithChildren = parentCriteriaWithChildren;
	}
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
