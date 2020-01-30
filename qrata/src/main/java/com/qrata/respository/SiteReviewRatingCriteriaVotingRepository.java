package com.qrata.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qrata.models.SiteReviewRatingCriteriaVoting;

@Repository
public interface SiteReviewRatingCriteriaVotingRepository extends JpaRepository<SiteReviewRatingCriteriaVoting, Long>{
	
	@Query("SELECT srrcv FROM SiteReviewRatingCriteriaVoting srrcv INNER JOIN srrcv.siteReviewRatingCriteria srrc WHERE srrc.siteReviews.id = :sitereview_id ORDER BY srrc.id")
	public abstract List<SiteReviewRatingCriteriaVoting> getSiteReviewRatingCriteriaVotingPercentage(@Param("sitereview_id")long siteReviewId); 

}
