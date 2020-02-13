/**
 * 
 */
package com.insonix.qrata.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.dao.SiteReviewRatingCriteriaVotingDao;
import com.insonix.qrata.models.SiteReviewRatingCriteriaVoting;

/**
 * @author Harmeet Singh
 *
 */

@SuppressWarnings("unchecked")
@Repository("siteReviewRatingCriteriaVotingDao")
public class SiteReviewRatingCriteriaVotingDaoImpl extends BaseDao<SiteReviewRatingCriteriaVoting> implements SiteReviewRatingCriteriaVotingDao {

	@Override
	public List<SiteReviewRatingCriteriaVoting> getSiteReviewRatingCriteriaVotingPercentage(long siteReviewId) {
		String hql = "SELECT srrcv FROM SiteReviewRatingCriteriaVoting srrcv INNER JOIN srrcv.siteReviewRatingCriteria srrc "
				+ "WHERE srrc.siteReviews.id = :sitereview_id ORDER BY srrc.id";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("sitereview_id", siteReviewId);
		return query.list();
	}
}
