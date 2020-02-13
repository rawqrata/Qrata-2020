package com.insonix.qrata.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

/**
 * @author Gurminder
 *
 */
@Entity
@Table(name = "topic_expert_assignment", schema = "public")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TopicExpertAssignment extends CommonEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 472500435450739734L;
	
	private int id;
	private User expert;
	private User assignedBy;
	private Topic topic;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="topic_expert_assignment_id_seq")
	@SequenceGenerator(name="topic_expert_assignment_id_seq", initialValue=1, sequenceName="topic_expert_assignment_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="expert_id" , nullable = false)
	@Index(name="expert_id_idx")
	public User getExpert() {
		return expert;
	}

	public void setExpert(User expert) {
		this.expert = expert;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="assigned_by_userid" , nullable = false)
	@Index(name="assigned_by_userid_idx")
	public User getAssignedBy() {
		return assignedBy;
	}
	
	public void setAssignedBy(User assignedBy) {
		this.assignedBy = assignedBy;
	}
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="topic_id", nullable=false)
	@Index(name="topic_id_idx")
	public Topic getTopic() {
		return topic;
	}
	
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
