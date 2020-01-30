package com.qrata.entity;

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
	 * @param domainId
	 * @param siteUuid
	 */
	public AddSiteForm(String name, String url, int domainId, String siteUuid) {
		super();
		this.name = name;
		this.url = url;
		this.domainId = domainId;
		this.siteUuid = siteUuid;		
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getSubTopicId() {
		return subTopicId;
	}

	public void setSubTopicId(int subTopicId) {
		this.subTopicId = subTopicId;
	}

	public String getSubTopicName() {
		return subTopicName;
	}

	public void setSubTopicName(String subTopicName) {
		this.subTopicName = subTopicName;
	}

	public String getSiteUuid() {
		return siteUuid;
	}

	public void setSiteUuid(String siteUuid) {
		this.siteUuid = siteUuid;
	}

	public String getSiteSearchVal() {
		return siteSearchVal;
	}

	public void setSiteSearchVal(String siteSearchVal) {
		this.siteSearchVal = siteSearchVal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getSiteScore() {
		return siteScore;
	}

	public void setSiteScore(int siteScore) {
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

	public MultipartFile getSiteLogo() {
		return siteLogo;
	}

	public void setSiteLogo(MultipartFile siteLogo) {
		this.siteLogo = siteLogo;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
