package com.insonix.qrata.models;

// Generated Feb 6, 2013 11:51:22 AM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Categories generated by hbm2java
 */
@Entity
@Table(name = "categories", schema = "public")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category extends CommonEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2966907165051347041L;
	
	private int id;
	private String name;
	private Short categoryType;
	private Category parentCategory;
	private List<Category> childCategories;
	private List<Topic> topics = new ArrayList<Topic>(0);
	private List<Site> sites = new ArrayList<Site>(0);

	public Category() {
	}	
	
	/**
	 * @param name
	 * @param description
	 * @param categoryType
	 * @param parentCategory
	 * @param childCategories
	 * @param topics
	 * @param sites
	 */
	public Category(String name, Short categoryType,
			Category parentCategory, List<Category> childCategories,
			List<Topic> topics, List<Site> sites) {
		super();
		this.name = name;
		this.categoryType = categoryType;
		this.parentCategory = parentCategory;
		this.childCategories = childCategories;
		this.topics = topics;
		this.sites = sites;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categories_id_seq")
	@SequenceGenerator(name="categories_id_seq", initialValue=1, sequenceName="categories_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		this.setSortName(this.name);
	}

	@Override
	public void setSortName(String sortName){
		super.setSortName(sortName);
	}
	
	/**
	 * @return the parentCategory
	 */
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
	@JoinColumn(name = "parent_id")
	public Category getParentCategory() {
		return parentCategory;
	}

	/**
	 * @param parentCategory the parentCategory to set
	 */
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	
	/**
	 * @return the childCategories
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="parentCategory")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Category> getChildCategories() {
		return childCategories;
	}

	/**
	 * @param childCategories the childCategories to set
	 */
	public void setChildCategories(List<Category> childCategories) {
		this.childCategories = childCategories;
	}
	
	@Column(name = "category_type")
	public Short getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(Short categoryType) {
		this.categoryType = categoryType;
	}

	
	/**
	 * @return the topics
	 */
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="category")
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Topic> getTopics() {
		return topics;
	}

	/**
	 * @param topics the topics to set
	 */
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	/**
	 * @return the sites
	 */
	public List<Site> getSites() {
		return sites;
	}

	/**
	 * @param sites the sites to set
	 */
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + ", categoryType=" + categoryType + "]";
	}
	
}
