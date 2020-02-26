package com.insonix.qrata.entity;

public class RatingCriteriaForm {
	private int id;
	private String name;
	private String criteriaSearchVal;
	private String description;
	private int weight;
	private int priority;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the criteriaSearchVal
	 */
	public String getCriteriaSearchVal() {
		return criteriaSearchVal;
	}
	/**
	 * @param criteriaSearchVal the criteriaSearchVal to set
	 */
	public void setCriteriaSearchVal(String criteriaSearchVal) {
		this.criteriaSearchVal = criteriaSearchVal;
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
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
