package com.insonix.qrata.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.TopicRatingCriteriaDao;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicRatingCriteria;

@SuppressWarnings("unchecked")
@Repository("TopicsRatingCriteriaDao")
public class TopicRatingCriteriaDaoImpl extends BaseDao<TopicRatingCriteria> implements TopicRatingCriteriaDao {

	@Override
	public List<TopicRatingCriteria> getTopicWeights(Topic topic) {
		Criteria crit = getSession().createCriteria(TopicRatingCriteria.class);
		crit.createAlias("ratingCriteria", "ratingCriteria");
		crit.add(Restrictions.eq("topic.id", topic.getId()));
		crit.addOrder(Order.asc("ratingCriteria.name"));
		return crit.list();
	}

	@Override
	public List<Topic> chekTopicRatings(Topic topic) {
		Criteria crit = getSession().createCriteria(TopicRatingCriteria.class);
		crit.createAlias("topic", "topic");
		crit.add(Restrictions.eq("topic.id", topic.getId()));
		return crit.list();
	}

	@Override
	public int getTotalTopicRatingCriteria_TopicId(Status active, int topicId) {
		Criteria ctr = getSession().createCriteria(TopicRatingCriteria.class);
		ctr.createAlias("topic", "topic");
		ctr.add(Restrictions.eq("topic.id", topicId));
		ctr.setProjection(Projections.rowCount());
		int count =  ctr.uniqueResult() !=null  ? (Integer) ctr.uniqueResult() : 0;
		return count;
	}

	@Override
	public List<Object[]> getTopicRatings_RatingCriteria(Topic topic) {
		String sql = "select weight, ratingcriteria_id from topics_ratingcriteria "
				+ "where topic_id = :topic_id "
				+ "ORDER BY ratingcriteria_id";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("topic_id", topic.getId());
		return query.list();
	}

}
