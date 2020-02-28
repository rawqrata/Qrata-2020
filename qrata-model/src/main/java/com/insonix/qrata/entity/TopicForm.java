package com.insonix.qrata.entity;

public class TopicForm {
	private int topicId;
	private String uuid;
	private String name;
	private Integer categoryId;
	private Integer subCatId;	
	private Integer parentTopicId;
	private Short type;
	private String topicSearchVal;
	private Boolean ratingStatus;
	private Boolean childStatus;
	private Boolean assignStatus;
	private long expertId;
	private Boolean applicableAssignment;
	private boolean existContent;
	private String editorName;
	private boolean leafNode;
	private String expertFirstName;
	private String expertLastName;
	private String status;
	private boolean reviewStatus;
	private int count;
	private boolean sites;
	

	
	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getTopicId() {
		return topicId;
	}


	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getSubCatId() {
		return subCatId;
	}


	public void setSubCatId(Integer subCatId) {
		this.subCatId = subCatId;
	}


	public Integer getParentTopicId() {
		return parentTopicId;
	}


	public void setParentTopicId(Integer parentTopicId) {
		this.parentTopicId = parentTopicId;
	}


	public Short getType() {
		return type;
	}


	public void setType(Short type) {
		this.type = type;
	}


	public String getTopicSearchVal() {
		return topicSearchVal;
	}


	public void setTopicSearchVal(String topicSearchVal) {
		this.topicSearchVal = topicSearchVal;
	}


	public Boolean getRatingStatus() {
		return ratingStatus;
	}


	public void setRatingStatus(Boolean ratingStatus) {
		this.ratingStatus = ratingStatus;
	}


	public Boolean getChildStatus() {
		return childStatus;
	}


	public void setChildStatus(Boolean childStatus) {
		this.childStatus = childStatus;
	}


	public Boolean getAssignStatus() {
		return assignStatus;
	}


	public void setAssignStatus(Boolean assignStatus) {
		this.assignStatus = assignStatus;
	}


	public long getExpertId() {
		return expertId;
	}


	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}


	public Boolean getApplicableAssignment() {
		return applicableAssignment;
	}


	public void setApplicableAssignment(Boolean applicableAssignment) {
		this.applicableAssignment = applicableAssignment;
	}


	public boolean isExistContent() {
		return existContent;
	}


	public void setExistContent(boolean existContent) {
		this.existContent = existContent;
	}


	public String getEditorName() {
		return editorName;
	}


	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}


	public boolean isLeafNode() {
		return leafNode;
	}


	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public boolean isReviewStatus() {
		return reviewStatus;
	}


	public void setReviewStatus(boolean reviewStatus) {
		this.reviewStatus = reviewStatus;
	}


	public boolean isSites() {
		return sites;
	}


	public void setSites(boolean sites) {
		this.sites = sites;
	}


	@Override
	public String toString() {
		return "TopicForm [topicId=" + topicId + ", uuid=" + uuid + ", name=" + name + ", categoryId=" + categoryId
				+ ", subCatId=" + subCatId + ", parentTopicId=" + parentTopicId + ", type=" + type + ", topicSearchVal="
				+ topicSearchVal + ", ratingStatus=" + ratingStatus + ", childStatus=" + childStatus + ", assignStatus="
				+ assignStatus + ", expertId=" + expertId + ", applicableAssignment=" + applicableAssignment
				+ ", existContent=" + existContent + ", editorName=" + editorName + ", leafNode=" + leafNode
				+ ", expertFirstName=" + expertFirstName + ", expertLastName=" + expertLastName + ", status=" + status
				+ ", reviewStatus=" + reviewStatus + ", count=" + count + ", sites=" + sites + "]";
	}


	

}
