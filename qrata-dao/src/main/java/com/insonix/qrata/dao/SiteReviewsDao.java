package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.ReadStatus;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.entity.SiteStatusAndScoreForm;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.User;

public interface SiteReviewsDao extends IBaseDao<SiteReview> {

	public List<SiteReview> findNewSiteRatings(Status status, int start, int pageSize, ReadStatus unread, String sortColumn, 
			String sortOrder, String siteName);
	public int getTotalNewRatings(String siteName, Status status, ReadStatus unread);
	
	public List<SiteReview> findContentsByName(Status status, String siteName, int start, int pageSize, String sortColumn, String sortOrder);
	public int getTotalRatings_Name(Status status, String siteName, ReviewStatus reviewStatus);
	
	public List<SiteReview> findContentsByTopic(String name, Status status, int topicId, int start, int pageSize, String sortColumn, String sortOrder);
	public int getTotalRatings_TopicId(Status status, int topicId, String name, ReviewStatus reviewStatus);
	
	public List<SiteReview> findContentsByTopicOrderByScore(String name, Status status, int topicId,
			int start, int pageSize, String sortColumn, String sortOrder);
	
	public List<SiteReview> findContentsByExpert(Status status, long expertId, int start, int pageSize, String sortColumn,
			String sortOrder, String siteName);
	public int getTotalRatings_ExpertId(Status status, long expertId, String siteName);
	
	public void updateSearchVector(SiteReviewForm siteReviewForm);
	
	public SiteReview insertSiteReview(SiteReviewForm siteReviewForm);
	
	public List<SiteReviewForm> getSitesByKeyword(String keyword);
	public SiteReviewForm getSiteReviewRatings_URL(String url);
	
	public void setCheckedStatus(long sitereviewId, ReadStatus unread);
	
	public List<Object[]> getSiteReviews_ReviewStatus(String name,ReviewStatus status,long userId, 
			Status active, int start, int pagesize, String sortColumn, String sortOrder);
	
	public int getTotalSiteReviews_ReviewStatus(String name, ReviewStatus status, long userId, Status active);
	
	public int getTotalSiteReviews_ReviewStatus_UserId(String name, long userId,Status active, ReviewStatus status);
	
	public List<SiteReview> getSiteReviews_ReviewStatus_UserId(String name, Status active, int start, int pagesize, ReviewStatus status, long userId, String sortOrder);
	
	public SiteReview getSiteReviews_SiteId_TopicId(Status active, long siteId,int topicId);
	
	public SiteStatusAndScoreForm getReviewStatus_TopicId_SiteId(Status active, int topicId,long siteId);
	
	public List<Site> getSiteNamesForReviewsAutoSuggest_Name(String siteName, String type, long entityId, Status status);	
	public List<Site> getSiteNamesForReviewsAutoSuggest_Name_ReviewStatus(String siteName, long userId, ReviewStatus reviewStatus, Status status);
	public List<String> getSiteNamesForReviewsAutoSuggestForExpertPublishing_Name_ReviewStatus(String siteName, long userId, ReviewStatus reviewStatus, Status status);
	
	public List<String> qrataSearchKeywords_SearchTerm(String searchTerm);
	public int getQrataSearchCount_Keyword(String keyword);
	public List<SiteReview> qrataSearch_Keyword(String keyword, int start, int pageSize);
	
	public List<SiteReview> getSiteReviews_TopicId(int topicId, Status active);

	public void updateSiteScore(long siteReviewId, int totalSiteScore);
	
	public int setContentStatusOffline(Status active, ReviewStatus offline, long siteReviewId, ReviewStatus online);
	
	public int getTotal_AllContentByReviewStatus(String name, ReviewStatus reviewStatus, Status active);
	public List<Object[]> getAllContentsByReviewStatus(String name, ReviewStatus reviewStatus, int start, int pagesize,
			String sortColumn, String sortOrder, Status active);
	
	public List<SiteReview> getSiteReviews_UserId_ReviewStatus(Status active, long userId, ReviewStatus reviewStatus);
	
	public int getExpertCoreRatings(User user, Status status, ReviewStatus reviewStatus);
	
	public int getExpertTotalRatings(User user, Status status, ReviewStatus reviewStatus);
}
