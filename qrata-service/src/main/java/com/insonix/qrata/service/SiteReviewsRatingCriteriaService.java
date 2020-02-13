package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;


/**
 * @author kamal
 *
 */
public interface SiteReviewsRatingCriteriaService {
	/**
	 * @param id
	 * @return
	 */
	public SiteReviewRatingCriteria getSiteReviewsRatingCriteria_Id(long id);
	/**
	 * @param siteReviewsRatingCriteria
	 */
	public boolean delete(SiteReviewRatingCriteria siteReviewsRatingCriteria);
	/**
	 * @param siteReviewsRatingCriteria
	 * @return
	 */
	public String save(SiteReviewRatingCriteria siteReviewsRatingCriteria);
	/**
	 * @param siteReviewsRatingCriteria
	 */
	public boolean update(SiteReviewRatingCriteria siteReviewsRatingCriteria);
	/**
	 * @param siteReviewsRatingCriteriaList
	 */
	public boolean updateBulk(List<SiteReviewRatingCriteria> siteReviewsRatingCriteriaList);
	/**
	 * @param topic
	 * @param site 
	 */
	public List<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias(Topic topic, Site site, User user);
	/**
	 * @param createdBy 
	 * @param inprogress 
	 * @param siteReviewsRatingCriteriaList
	 */
	
	public boolean saveSiteReviewsRatingCriteria(int[] ratings, int[] criteriaIds, int[] siteRatingIds, int topicId, int siteId, int siteReviewId,
			String description, String evaluation, User loginUser, String weakness, String strength, short reviewStatus, long createdBy);

	public boolean saveBulk(List<SiteReviewRatingCriteria> siteReviewRatingCriterias);
	
	public void updateSitesScores(int topicId, int[] criteriaId, int[] topicWeightId, int[] weight);
	
	public int[] getSiteRatingCriteria(long newSiteId, long reviewId, int topicId);
	
	public SiteReviewRatingCriteria saveBySQL(SiteReviewRatingCriteria criteria);
	
	public void updateUsingSQL(SiteReviewRatingCriteria siteReviewRatingCriteria);
	
}
