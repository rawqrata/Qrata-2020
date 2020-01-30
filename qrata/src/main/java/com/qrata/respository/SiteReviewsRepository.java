package com.qrata.respository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qrata.models.ItemDetails;
import com.qrata.models.SiteReview;

@Repository
public interface SiteReviewsRepository extends JpaRepository<SiteReview, Long>{

	@Query("SELECT sr from SiteReview sr where UPPER(sr.description) LIKE UPPER(concat('%', :description,'%')) or UPPER(sr.evaluation) LIKE UPPER(concat('%', :description,'%')) ")
	public abstract List<SiteReview> qrataSearchKeywords_SearchTerm(@Param("description") String searchTerm);
		
	
	@Query("select COUNT(s.id) from SiteReview s where s.status = :status and s.reviewStatus = :review_status and s.user.id = :user_id and s.site.backupRootSite IS NULL")
	public abstract int getExpertCoreRatings(@Param("status")Short status,@Param("review_status")Short review_status,@Param("user_id") long user_id);

	@Query("SELECT sr FROM SiteReview sr where sr.createdBy = :user_id")
    public abstract Set<SiteReview> getSiteReviewByUser(@Param("user_id") long user_id);

	
} 
