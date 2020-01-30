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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sites", schema = "public")
public class Site extends CommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2013134351059546201L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sites_id_seq")
	@SequenceGenerator(name="sites_id_seq", initialValue=1, sequenceName="sites_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "name")
	//@Index(name="sites_name_idx")
	private String name;
	
	@Column(name="image_path", nullable=true)
	private String imagePath;
	
	@Column(name="image_name", nullable=true)
	private String imageName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "site" ,cascade = CascadeType.ALL)
	private Set<SiteReview> sitereviews = new HashSet<SiteReview>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sites", cascade = CascadeType.ALL)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias = new HashSet<SiteReviewRatingCriteria>(0);
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="topics_sites", joinColumns={@JoinColumn(name="site_id", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="topic_id", nullable=false)})
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Topic> topics = new ArrayList<>(0);
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rootsite_id")
	private Site rootSite;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="backup_rootsite_id")
	private Site backupRootSite;
	
	@OneToMany(mappedBy="rootSite", fetch = FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Site> leafSites;

	public Site() {
	}

	public Site(long id) {
		this.id = id;
	}

	
	/**
	 * @param url
	 * @param name
	 * @param category
	 */
	public Site(String url, String name, Category category) {
		super();
		this.url = url;
		this.name = name;
		this.category = category;
	}

	/**
	 * @param url
	 * @param name
	 * @param image
	 * @param searchvector
	 * @param topic
	 * @param category
	 * @param sitereviews
	 * @param sitereviewsRatingcriterias
	 */
	public Site(String url, String name, List<Topic> topics, Category category,
			Set<SiteReview> sitereviews,
			Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias) {
		super();
		this.url = url;
		this.name = name;
		this.topics = topics;
		this.category = category;
		this.sitereviews = sitereviews;
		this.siteReviewsRatingCriterias = siteReviewsRatingCriterias;
	}
	

	@Override
	public void setSortName(String sortName){
		super.setSortName(sortName);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<SiteReview> getSitereviews() {
		return sitereviews;
	}

	public void setSitereviews(Set<SiteReview> sitereviews) {
		this.sitereviews = sitereviews;
	}

	public Set<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias() {
		return siteReviewsRatingCriterias;
	}

	public void setSiteReviewsRatingCriterias(Set<SiteReviewRatingCriteria> siteReviewsRatingCriterias) {
		this.siteReviewsRatingCriterias = siteReviewsRatingCriterias;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Site getRootSite() {
		return rootSite;
	}

	public void setRootSite(Site rootSite) {
		this.rootSite = rootSite;
	}

	public Site getBackupRootSite() {
		return backupRootSite;
	}

	public void setBackupRootSite(Site backupRootSite) {
		this.backupRootSite = backupRootSite;
	}

	public List<Site> getLeafSites() {
		return leafSites;
	}

	public void setLeafSites(List<Site> leafSites) {
		this.leafSites = leafSites;
	}

}
