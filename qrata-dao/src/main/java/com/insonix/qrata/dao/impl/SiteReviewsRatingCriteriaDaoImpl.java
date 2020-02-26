package com.insonix.qrata.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteReviewsRatingCriteriaDao;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;

@SuppressWarnings("unchecked")
@Repository("SiteReviewsRatingCriteriaDao")
public class SiteReviewsRatingCriteriaDaoImpl extends BaseDao<SiteReviewRatingCriteria> implements SiteReviewsRatingCriteriaDao {

	
	@Override
	public List<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias(
			Topic topic, Site site, User user) {
		Criteria crit = getSession().createCriteria(SiteReviewRatingCriteria.class);
		crit.createAlias("topics", "topic");
		crit.createAlias("sites", "site");
		crit.createAlias("siteReviews", "siteReview");
		crit.add(Restrictions.eq("topic.id", topic.getId()));
		crit.add(Restrictions.eq("site.id", site.getId()));
		//crit.add(Restrictions.eq("siteReview.user.id", user.getId()));
		return crit.list();
	}

	@Override
	public List<Object> getSiteRatingCriteria(long newSiteId, long reviewId, int topicId) {
		String sql = "select weight from sitereviews_ratingcriteria "
				+ "where topic_id = :topic_id and sitereview_id = :sitereview_id and site_id = :site_id "
				+ "ORDER BY ratingcriteria_id ";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("topic_id", topicId);
		query.setParameter("sitereview_id", reviewId);
		query.setParameter("site_id", newSiteId);
		
		return query.list();
	}

	@Override
	public long saveBySQL(SiteReviewRatingCriteria criteria) {
		try {
			String idQueryString = "SELECT nextval('sitereviewratingcriteria_id_seq')";
			SQLQuery idQuery = getSession().createSQLQuery(idQueryString);
			BigInteger bigId = (BigInteger) idQuery.uniqueResult();
			long siteReviewRatingCriteriaId = bigId.longValue();
			
			String sql = "INSERT INTO sitereviews_ratingcriteria ("
					+ "id, date_created, last_updated, status, uuid, "
					+ "weight, ratingcriteria_id, sitereview_id, site_id, topic_id) "
					+ "VALUES "
					+ "(:id, :date_created, :last_updated, :status, :uuid, "
					+ ":weight, :ratingcriteria_id, :sitereview_id, :site_id, :topic_id) ";
			
			SQLQuery query = getSession().createSQLQuery(sql);
			query.setParameter("id", siteReviewRatingCriteriaId);
			query.setParameter("date_created", criteria.getDateCreated());
			query.setParameter("last_updated", criteria.getLastUpdated());
			query.setParameter("status", criteria.getStatus());
			query.setParameter("uuid", criteria.getUuid());
			query.setParameter("weight", criteria.getWeight());
			query.setParameter("ratingcriteria_id", criteria.getRatingCriteria().getId());
			query.setParameter("sitereview_id", criteria.getSiteReviews().getId());
			query.setParameter("site_id", criteria.getSites().getId());
			query.setParameter("topic_id", criteria.getTopics().getId());
		
			query.executeUpdate();
			return siteReviewRatingCriteriaId;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateUsingSQL(SiteReviewRatingCriteria siteReviewRatingCriteria) {
		String sql = "UPDATE sitereviews_ratingcriteria SET weight = :weight "
				+ "WHERE id = :id AND ratingcriteria_id = :ratingcriteria_id";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("weight", siteReviewRatingCriteria.getWeight());
		query.setParameter("id", siteReviewRatingCriteria.getId());
		query.setParameter("ratingcriteria_id", siteReviewRatingCriteria.getRatingCriteria().getId());
		query.executeUpdate();	
	}

	@Override
	public SiteReviewRatingCriteria getSiteReviewRatingCriteriaById(Status active, long id) {
		Criteria criteria = getSession().createCriteria(SiteReviewRatingCriteria.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("status", active.getValue()));
		return (SiteReviewRatingCriteria)criteria.uniqueResult();
	}
	
}
