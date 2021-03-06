package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.entity.SiteStatusAndScoreForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;


/**
 * @author kamal
 *
 */
public interface SiteReviewsService {
	/**
	 * @param id
	 * @return
	 */
	public SiteReview getSiteReviews_Id(long id);
	/**
	 * @param siteReviews
	 */
	public void delete(SiteReview siteReviews);
	/**
	 * @param siteReviews
	 * @return
	 */
	public String save(SiteReview siteReviews);
	/**
	 * @param siteReviews
	 */
	public void update(SiteReview siteReviews);
	/**
	 * @param siteReviewsList
	 */
	public void updateBulk(List<SiteReview> siteReviewsList);
	
	public void updateSearchVector(SiteReviewForm siteReviewForm);
	
	public SiteReview insertSiteReview(SiteReviewForm siteReviewForm);
	
					/* Methods To Find Ratings */
	public List<SiteReviewForm> findNewSiteRatings(int start, int pageSize, String sortColumn, String sortOrder, String siteName);
	public int getTotalNewRatings(String siteName);
	
	public List<SiteReviewForm> findContentsByName(String siteName, int start, int pageSize, String sortColumn, String sortOrder);
	public int getTotalRatings_Name(String siteName);
	
	public List<SiteReviewForm> findContentsByTopic(String name, int topicId, int start, int pageSize, String sortColumn, String sortOrder);
	public int getTotalRatings_TopicId(int topicId, String name);
	
	public List<SiteReviewForm> findContentsByExpert(long expertId, int start, int pageSize, String sortColumn, 
			String sortOrder, String siteName);
	public int getTotalRatings_ExpertId(long expertId, String siteName);
	
	public List<Category> getDomains();
	public List<Category> getCategories_DomainId(String domainId);
	public List<Category> getSubCategories_CategoryId(String categoryId);
	public List<Topic> getTopics_SubCategoryId(String subCategoryId);
	public List<Topic> getSubTopics_TopicId(int topicId);
	public List<User> getExperts();
					/* Methods To Find Ratings */
	
	public List<SiteReviewForm> getSitesByKeyword(String keyword);
	public SiteReviewForm getSiteReviewRatings_URL(String url);
	
	public void setCheckedStatus(long sitereviewId);
	
	public List<SiteReviewForm> getSiteReviews_ReviewStatus(String name , int start, int pagesize, ReviewStatus online, 
			long userId, String sortColumn, String sortOrder);
	
	public int getTotalSiteReviews_ReviewStatus(String name, long userId, ReviewStatus online);
	
	public int getTotalSiteReviews_ReviewStatus_UserId(String name, long userId, ReviewStatus online);
	
	public List<SiteReviewForm> getSiteReviews_ReviewStatus_UserId(String name , int start,int pagesize, ReviewStatus online, long userId, String sortOrder);
	
	@Deprecated
	public SiteReview getSiteReviews_SiteId_TopicId(long id, int topicId);
	
	public SiteStatusAndScoreForm getReviewStatus_TopicId_SiteId(int topicId, long siteId);
	
	public String suggestReviewsBySiteName(String siteName, String type, long entityId);	
	public String suggestSiteByNameNReviewStatusForMyPublishing(String siteName, long userId, String type);
	public String suggestSiteByNameNReviewStatusForExpertPublishing(String siteName, long userId, String type);
	
	public List<SiteReview> getSiteReviews_TopicId(int topicId);
	
	public boolean setContentStatusOffline(ReviewStatus offline, long siteReviewId);
	
	public int getTotal_AllContentByReviewStatus(String name, ReviewStatus reviewStatus);
	public List<SiteReviewForm> getAllContentsByReviewStatus(String name, ReviewStatus reviewStatus, int start, int pagesize, 
			String sortColumn, String sortOrder);
	
	public List<SiteReview> getSiteReviews_UserId_ReviewStatus(long id, ReviewStatus reviewStatus);
	
	public int getExpertCoreRatings(User user);
	
	public int getExpertTotalRatings(User user);
}
