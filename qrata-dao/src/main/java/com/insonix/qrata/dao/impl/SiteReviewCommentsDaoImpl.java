package com.insonix.qrata.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.ReadStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteReviewCommentsDao;
import com.insonix.qrata.dao.SiteReviewsDao;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.SiteReviewComments;
import com.insonix.qrata.models.User;

/**
 * @author Gurminder Singh
 *
 */
@Repository("siteReviewCommentsDao")
public class SiteReviewCommentsDaoImpl extends BaseDao<SiteReviewComments> implements SiteReviewCommentsDao {
	@Autowired
	SiteReviewsDao siteReviewsDao;
	
	
	@Override
	public SiteReviewComments addComment(long siteReviewId, String comment, User user) {
		SiteReviewComments siteReviewComments = new SiteReviewComments();
		try {
			SiteReview siteReview = siteReviewsDao.get(siteReviewId);
			siteReviewComments.setCreatedBy(user.getId());
			siteReviewComments.setStatus(Status.ACTIVE.getValue());
			siteReviewComments.setComment(comment);
			siteReviewComments.setCommentedBy(user);
			siteReviewComments.setSiteReview(siteReview);
			siteReviewComments.setReadStatus(ReadStatus.UNREAD.getValue());
			save(siteReviewComments);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteReviewComments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SiteReviewComments> fetchAllComments(long reviewId, Status status) {
		Criteria ctr = getSession().createCriteria(SiteReviewComments.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("siteReview.id", reviewId));
		ctr.addOrder(Order.asc("dateCreated"));
		return ctr.list();
	}
	
	@Override
	public void updateReadStatus(long userId) {
		String queryString = "update SiteReviewComments sc set sc.readStatus = :readStatus where sc.commentedBy.id != :commentedBy";
		Query q = getSession().createQuery(queryString).setCacheable(true);
		q.setParameter("readStatus", ReadStatus.READ.getValue());
		q.setParameter("commentedBy", userId);
		q.executeUpdate();
	}
	
	@Override
	public int getUnreadCommentsCount(long reviewId, long userId, Status status) {
		int count = 0;
		Criteria ctr = getSession().createCriteria(SiteReviewComments.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("siteReview.id", reviewId));
		ctr.add(Restrictions.ne("commentedBy.id", userId));
		ctr.add(Restrictions.eq("readStatus", ReadStatus.UNREAD.getValue()));
		ctr.setProjection(Projections.rowCount());
		count = ctr.uniqueResult()!= null ? (Integer)ctr.uniqueResult() : 0;
		return count;
	}
	
}
