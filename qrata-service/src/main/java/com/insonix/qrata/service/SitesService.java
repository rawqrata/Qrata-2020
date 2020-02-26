package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;

/**
 * @author kamal
 *
 */
public interface SitesService {
	/**
	 * @param id
	 * @return
	 */
	public Site getSites_Id(long id);
	/**
	 * @param sites
	 */
	public boolean delete(Site sites);
	/**
	 * @param sites
	 * @return
	 */
	public String save(Site sites);
	/**
	 * @param sites
	 */
	public boolean update(Site sites);
	/**
	 * @param sites
	 */
	public void add(Site sites);
	/**
	 * @param sitesList
	 */
	public boolean updateBulk(List<Site> sitesList);
		
	public boolean saveSite(AddSiteForm form, long createdBy);
	
	public List<Site> getActiveSites();

	public boolean delete(String uuid);
	public boolean update(AddSiteForm form);
	public Site getSite(String uuid);
	public List<Site> searchSites_Name(String name);
	
	public List<Site> getActiveSites_CategoryId(int categoryId);

	public List<AddSiteForm> getAllActiveSites(int start, int pageSize, String name, String sortField, String sortOrder);
	
	public int getTotalSites(String name);
	
	public List<AddSiteForm> getActiveSites_Topics(String name, int start, int pagesize, List<Topic> topics);
	
	public int getTotalSites_Topics(String name, List<Topic> topics);
	
	public boolean checkSite_URL_Topic(String url , int topicId);
	
	public boolean checkSite_Name_Topic(int topicId, String name);
	
	public List<AddSiteForm> getActiveSites_UserId(String name,	 int start, int pagesize,long userId, String sortOrder, String sortField);
	
	public int getTotalSites_UserId(String name, long userId);
	
	public int getTotalSites_Experts(String name,long userId);
	
	public List<AddSiteForm> getActiveSites_Experts(String name, int start, int pagesize,long userId, String sortColumn, String sortOrder);
	
	/*
	 * Count Methods Start
	 */
	public String fetchCountMyData(User loginUser);
	public String fetchCountExpertData(User loginUser);
	public String fetchCountExpert(User loginUser);
	
	public int getTotalRatings_TopicId(String name , int topicId);
	
	public List<AddSiteForm> getActiveSites_Topic(String name, Topic topic, int start,int pagesize);
	
	public List<Category> getAssignedDomainToExpert(long expertId);
	
	public String suggestSiteByName(String name, long userId);	
	public String suggestSiteByNameForExpertPublishing(String name, long userId);
	
	public int getTotalAllRatings_TopicId(int topicId, String name);
	
	public List<SiteReviewForm> findAllContentsByTopic(String name, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId);
	
	public List<SiteReviewForm> allContentsByTopic(String name, int topicId, int start, int pagesize, String sortColumn, 
			String sortOrder, short roleId);
	
	public int getAllContentRatings_TopicId(int topicId, String name);
	
	public long saveSiteUsingSQL(Site site);
	
	public List<SiteReviewForm> getOnlineSitesByTopic(Topic topic, ReviewStatus reviewStatus);
	
	public List<Site> getLeafSites(Site site);
	
	public List<SiteReview> getSiteReviews(Site site);
	
	public String removeSiteLogo(Long id);
}
