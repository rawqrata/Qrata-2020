package com.insonix.qrata.entity;

import org.springframework.web.multipart.MultipartFile;

public class AddSiteForm {	
	private String name;	
	private String url;	
	private int domainId;	
	private String domainName;
	private int categoryId;	
	private String categoryName;
	private int subCategoryId;
	private String subCategoryName;
	private int topicId;	
	private String topicName;
	private int subTopicId;	
	private String subTopicName;
	private String siteUuid;	
	private String siteSearchVal;	
	private String id;	
	private long expertid;
	private String expertFirstName;	
	private String expertLastName;	
	private boolean reviewStatus;	
	private int siteScore;	
	private short statusNumber;	
	private String status;	
	private int score;
	private MultipartFile siteLogo;
	private String imageName;
	
	/**
	 * 
	 */
	public AddSiteForm() {
		super();
	}

	/**
	 * @param name
	 * @param url
	 * @param categoryId
	 * @param siteUuid
	 */
	public AddSiteForm(String name, String url, int domainId, String siteUuid) {
		super();
		this.name = name;
		this.url = url;
		this.domainId = domainId;
		this.siteUuid = siteUuid;		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the categoryId
	 */
	public int getDomainId() {
		return domainId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getSiteUuid() {
		return siteUuid;
	}

	public void setSiteUuid(String siteUuid) {
		this.siteUuid = siteUuid;
	}
	
	/**
	 * @return the siteSearchVal
	 */
	public String getSiteSearchVal() {
		return siteSearchVal;
	}

	/**
	 * @param siteSearchVal the siteSearchVal to set
	 */
	public void setSiteSearchVal(String siteSearchVal) {
		this.siteSearchVal = siteSearchVal;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the subCategoryId
	 */
	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}

	
	public int getSubTopicId() {
		return subTopicId;
	}

	public void setSubTopicId(int subTopicId) {
		this.subTopicId = subTopicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	public long getExpertid() {
		return expertid;
	}

	public void setExpertid(long expertid) {
		this.expertid = expertid;
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

	public boolean isReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(boolean reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Integer getSiteScore() {
		return siteScore;
	}

	public void setSiteScore(Integer siteScore) {
		this.siteScore = siteScore;
	}

	public short getStatusNumber() {
		return statusNumber;
	}

	public void setStatusNumber(short statusNumber) {
		this.statusNumber = statusNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setSiteScore(int siteScore) {
		this.siteScore = siteScore;
	}
	
	/**
	 * @return the siteLogo
	 */
	public MultipartFile getSiteLogo() {
		return siteLogo;
	}

	/**
	 * @param siteLogo the siteLogo to set
	 */
	public void setSiteLogo(MultipartFile siteLogo) {
		this.siteLogo = siteLogo;
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

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getSubTopicName() {
		return subTopicName;
	}

	public void setSubTopicName(String subTopicName) {
		this.subTopicName = subTopicName;
	}
	
	@Override
	public String toString() {
		return "AddSiteForm [name=" + name + ", url=" + url + ", topicId="
				+ topicId + ", siteUuid=" + siteUuid + ", id=" + id
				+ ", reviewStatus=" + reviewStatus + ", siteScore=" + siteScore
				+ ", status=" + status + "]";
	}
}
