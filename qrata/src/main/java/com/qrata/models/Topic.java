package com.qrata.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "topics", schema = "public")
public class Topic extends CommonEntity implements Serializable {
	
	private static final long serialVersionUID = -3333410481175799150L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="topics_id_seq")
	@SequenceGenerator(name="topics_id_seq", initialValue=1, sequenceName="topics_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name", length = 100)
	//@Index(name="topics_name_idx")
	private String name;
	
	@Column(name = "topic_type")
	private Short topicType;
	
	
	@ManyToOne(fetch=FetchType.LAZY , cascade = CascadeType.PERSIST)
	private Topic parentTopic;
	
	@Column(name = "leaf_node" )
	private boolean leafNode = false;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="parentTopic")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Topic> childTopics;
	
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id")
	private Category category;
	

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="topics_sites", joinColumns={@JoinColumn(name="topic_id", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="site_id", nullable=false)})
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Site> sites = new ArrayList<Site>(0);
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="topic")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<TopicRatingCriteria> topicRatingCriterias = new HashSet<>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="topics")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<SiteReviewRatingCriteria> siteReviewRatingCriterias = new ArrayList<>();
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="topic")
	private TopicExpertAssignment topicExpertAssignment;
		
	public Topic() {
	}

	public Topic(int id) {
		this.id = id;
	}
	
	@Override
	public void setSortName(String sortName){
		super.setSortName(sortName);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getTopicType() {
		return topicType;
	}

	public void setTopicType(Short topicType) {
		this.topicType = topicType;
	}

	public Topic getParentTopic() {
		return parentTopic;
	}

	public void setParentTopic(Topic parentTopic) {
		this.parentTopic = parentTopic;
	}

	public boolean isLeafNode() {
		return leafNode;
	}

	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
	}

	public List<Topic> getChildTopics() {
		return childTopics;
	}

	public void setChildTopics(List<Topic> childTopics) {
		this.childTopics = childTopics;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public Set<TopicRatingCriteria> getTopicRatingCriterias() {
		return topicRatingCriterias;
	}

	public void setTopicRatingCriterias(Set<TopicRatingCriteria> topicRatingCriterias) {
		this.topicRatingCriterias = topicRatingCriterias;
	}

	public List<SiteReviewRatingCriteria> getSiteReviewRatingCriterias() {
		return siteReviewRatingCriterias;
	}

	public void setSiteReviewRatingCriterias(List<SiteReviewRatingCriteria> siteReviewRatingCriterias) {
		this.siteReviewRatingCriterias = siteReviewRatingCriterias;
	}

	public TopicExpertAssignment getTopicExpertAssignment() {
		return topicExpertAssignment;
	}

	public void setTopicExpertAssignment(TopicExpertAssignment topicExpertAssignment) {
		this.topicExpertAssignment = topicExpertAssignment;
	}


}
