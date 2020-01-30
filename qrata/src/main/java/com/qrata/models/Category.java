package com.qrata.models;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "categories", schema = "public")
public class Category extends CommonEntity implements Serializable{

		private static final long serialVersionUID = 2966907165051347041L;
		private static final long adsf =  333L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categories_id_seq")
		@SequenceGenerator(name="categories_id_seq", initialValue=1, sequenceName="categories_id_seq", allocationSize=1)
		@Column(name = "id", unique = true, nullable = false)
		private int id;
		
		@Column(name = "name", length = 200)
		private String name;
		
		@Column(name = "category_type")
		private Short categoryType;
		
		@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
		@JoinColumn(name = "parent_id")
		private Category parentCategory;
		
		@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="parentCategory")
		@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
		private List<Category> childCategories;
		
		
		@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="category")
		@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
		private List<Topic> topics = new ArrayList<Topic>(0);
		
		@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
		@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

		public Short getCategoryType() {
			return categoryType;
		}

		public void setCategoryType(Short categoryType) {
			this.categoryType = categoryType;
		}

		public Category getParentCategory() {
			return parentCategory;
		}

		public void setParentCategory(Category parentCategory) {
			this.parentCategory = parentCategory;
		}

		public List<Category> getChildCategories() {
			return childCategories;
		}

		public void setChildCategories(List<Category> childCategories) {
			this.childCategories = childCategories;
		}

		public List<Topic> getTopics() {
			return topics;
		}

		public void setTopics(List<Topic> topics) {
			this.topics = topics;
		}

		public List<Site> getSites() {
			return sites;
		}

		public void setSites(List<Site> sites) {
			this.sites = sites;
		}


}
