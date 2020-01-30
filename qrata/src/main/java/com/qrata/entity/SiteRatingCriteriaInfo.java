/**
 * Author Gurminder
 * Date Created 06-Jun-2013 12:09:53 PM
 */
package com.qrata.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SiteRatingCriteriaInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 131081283558155383L;
	private long id;
	private int score;
	private String description;
	private String evaluation;
	private String strengths;
	private String weaknesses;
	private Date created_at;
	private Date updated_at;
	private String url;
	private List<Map<String, Integer>> ratingCriterias = new ArrayList<>();
	
	/**
	 * Getter Method for score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Setter Method for score
	 * Parameter to set score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Getter Method for description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Setter Method for description
	 * Parameter to set description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter Method for evaluation
	 */
	public String getEvaluation() {
		return evaluation;
	}
	/**
	 * Setter Method for evaluation
	 * Parameter to set evaluation
	 */
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
		
	/**
	 * Getter Method for created_at
	 */
	public Date getCreated_at() {
		return created_at;
	}
	/**
	 * Setter Method for created_at
	 * Parameter to set created_at
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Getter Method for url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Setter Method for url
	 * Parameter to set url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Getter Method for updated_at
	 */
	public Date getUpdated_at() {
		return updated_at;
	}
	/**
	 * Setter Method for updated_at
	 * Parameter to set updated_at
	 */
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	/**
	 * Getter Method for id
	 */
	public long getId() {
		return id;
	}
	/**
	 * Setter Method for id
	 * Parameter to set id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Getter Method for strengths
	 */
	public String getStrengths() {
		return strengths;
	}
	/**
	 * Setter Method for strengths
	 * Parameter to set strengths
	 */
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}
	
	/**
	 * Getter Method for weaknesses
	 */
	public String getWeaknesses() {
		return weaknesses;
	}
	/**
	 * Setter Method for weaknesses
	 * Parameter to set weaknesses
	 */
	public void setWeaknesses(String weaknesses) {
		this.weaknesses = weaknesses;
	}
	
	
	/**
	 * Getter Method for ratingCriterias
	 */
	public List<Map<String, Integer>> getRatingCriterias() {
		return ratingCriterias;
	}
	/**
	 * Setter Method for ratingCriterias
	 * Parameter to set ratingCriterias
	 */
	public void setRatingCriterias(List<Map<String, Integer>> ratingCriterias) {
		this.ratingCriterias = ratingCriterias;
	}
	
}
