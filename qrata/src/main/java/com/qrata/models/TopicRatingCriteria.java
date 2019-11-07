package com.qrata.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "topics_ratingcriteria", schema = "public")
public class TopicRatingCriteria extends CommonEntity implements Serializable{
	
private static final long serialVersionUID = 2414488980318849585L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topicsratingcriteria_id_seq")
	@SequenceGenerator(name = "topicsratingcriteria_id_seq", initialValue = 1, sequenceName = "topicsratingcriteria_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "weight")
	private Integer weight;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ratingcriteria_id")
	private RatingCriteria ratingCriteria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topic;
	
	public TopicRatingCriteria() {
	}

	public TopicRatingCriteria(long id) {
		this.id = id;
	}
	

	/**
	 * @param weight
	 * @param ratingCriteria
	 * @param topic
	 */
	public TopicRatingCriteria(Integer weight, RatingCriteria ratingCriteria,
			Topic topic) {
		super();
		this.weight = weight;
		this.ratingCriteria = ratingCriteria;
		this.topic = topic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public RatingCriteria getRatingCriteria() {
		return ratingCriteria;
	}

	public void setRatingCriteria(RatingCriteria ratingCriteria) {
		this.ratingCriteria = ratingCriteria;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
