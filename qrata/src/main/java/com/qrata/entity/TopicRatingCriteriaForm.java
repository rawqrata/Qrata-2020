package com.qrata.entity;

public class TopicRatingCriteriaForm {
	private int topicId;
	private int criteriaId;
	private int weight;
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	public int getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
