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
@Table(name = "related_topics", schema = "public")
public class RelatedTopics extends CommonEntity implements Serializable {
	
private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relatedtopics_id_seq")
	@SequenceGenerator(name = "relatedtopics_id_seq", initialValue = 1, sequenceName = "relatedtopics_id_seq", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topics;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relatedtopic_id")
	private Topic relatedTopicId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Topic getTopics() {
		return topics;
	}

	public void setTopics(Topic topics) {
		this.topics = topics;
	}

	public Topic getRelatedTopicId() {
		return relatedTopicId;
	}

	public void setRelatedTopicId(Topic relatedTopicId) {
		this.relatedTopicId = relatedTopicId;
	}
	
	
	

}
