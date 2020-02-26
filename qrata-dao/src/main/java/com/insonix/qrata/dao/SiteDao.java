package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.Topic;

public interface SiteDao extends IBaseDao<Site> {

	public List<Site> getSites(Status status);
	
	public Site getSite(String uuid);
	
	public List<Site> searchSites_Name(String name, Status status);
	
	public List<Site> getSites_CategoryId(int categoryId, Status status);
	
	public List<AddSiteForm> getAllSites(Status status, int start, int pageSize, String name, String sortField, String sortOrder);
	
	public int getTotalSites(String name);

	public List<AddSiteForm> getActiveSites_Topics(Status active, String name, int start, int pagesize, String topics);

	public int getTotalSites_Topics(String name, String topicsString);

	public boolean checkSite_URL_Topic(String url,int topicId);
	
	public boolean checkSite_Name_Topic(int topicId, String name);

	public List<Object[]> getActiveSites_UserId(Status active, String name,int start, int pagesize, long userId, String sortOrder, String sortField);

	public int getTotalSites_UserId(String name, long userId, Status active);

	public List<Object[]> getActiveSites_Experts(Status active, String name, int start, int pagesize, 
			long userId, String sortColumn, String sortOrder);

	public int getTotalSites_Experts(String name, long userId);

	public int getTotalRatings_TopicId(Status active, String name, int topicId);

	public List<AddSiteForm> getActiveSites_Topic(Status active, String name,Topic topic, int start, int pagesize);

	public List<Category> getAssignedDomainToExpert(long expertId, Status active, CategoryType domain);
	
	public List<Site> getSiteNamesForAutoSuggest_Name(String name, long userId, Status status);
	public List<String> getSiteNamesForAutoSuggestForExpertPublishing_Name(String name, long userId, Status status);

	public int getTotalAllRatings_TopicId(Status active, int topicId, String name);

	public List<Object[]> findAllContentsByTopic(String name, Status active, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId);

	public List<Object[]> allContentsByTopic(String name, Status active, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId);

	public int getAllContentRatings_TopicId(Status active, int topicId,
			String name);
	
	public void updateContentLogoDetails(String imageName, String imagePath, long siteId);

	public long saveSiteUsingSQL(Site site);

	public List<Object[]> getOnlineSitesByTopic(Topic topic, ReviewStatus reviewStatus);

	public List<Site> getLeafSites(long siteId);

	public List<SiteReview> getSiteReviews(long siteId);
}
