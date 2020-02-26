package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;

public interface SiteReviewsRatingCriteriaDao extends IBaseDao<SiteReviewRatingCriteria>{

	public List<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias(Topic topic, Site site, User user);

	public List<Object> getSiteRatingCriteria(long newSiteId, long reviewId, int topicId);

	public long saveBySQL(SiteReviewRatingCriteria criteria);

	public void updateUsingSQL(SiteReviewRatingCriteria siteReviewRatingCriteria);

	public SiteReviewRatingCriteria getSiteReviewRatingCriteriaById(Status active, long id);
	
}
