package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.models.SiteReviewRatingCriteriaVoting;

public interface SiteReviewRatingCriteriaVotingDao extends IBaseDao<SiteReviewRatingCriteriaVoting> {

	public List<SiteReviewRatingCriteriaVoting> getSiteReviewRatingCriteriaVotingPercentage(long siteReviewId);

}
