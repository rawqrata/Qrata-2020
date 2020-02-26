package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.SiteReviewRatingCriteriaVoting;


/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */
public interface SiteReviewService {
	
	public String qrataSearchKeywords_SearchTerm(String searchTerm);
	public int getQrataSearchCount_Keyword(String keyword);
	public List<SiteReviewForm> qrataSearch_Keyword(String keyword, int start, int pageSize);
    public SiteReviewForm getSiteReview_id(long id);
	public List<SiteReviewForm> findContentsByTopic(int topicId, int start, int pageSize);
	public int getTotalRatings_TopicId(int topicId);
	public SiteReviewForm getSiteReviewRatingCriteria_id(long id);
	public SiteReviewRatingCriteria getSiteReviewRatingCriteriaById(long id);
	public void addSiteReviewRatingCriteriaVotings(List<SiteReviewRatingCriteriaVoting> criteriaVotings);
	public SiteReviewForm getSiteReviewRatingCriteriaVotingPercentage(SiteReviewForm siteReview);
}
