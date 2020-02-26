package com.insonix.qrata.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.ReadStatus;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteDao;
import com.insonix.qrata.dao.SiteReviewsDao;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.entity.SiteStatusAndScoreForm;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.User;

@SuppressWarnings("unchecked")
@Repository("SiteReviewsDao")
public class SiteReviewsDaoImpl extends BaseDao<SiteReview> implements SiteReviewsDao {

	@Autowired
	SiteDao siteDao;

	@Override
	public List<SiteReview> findNewSiteRatings(Status status, int start, int pageSize, ReadStatus unread, String sortColumn, 
			String sortOrder, String siteName) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("readStatus", unread.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));

		ctr.createAlias("site", "site");
		if (siteName != null) {
			ctr.add(Restrictions.ilike("site.name", siteName+"%"));
		}
		
		if(sortOrder == null) {
			ctr.addOrder(Order.asc("site.name"));
		} else {
			ctr.createAlias("user", "user");
			ctr.createAlias("user.userinfo", "userinfo");
			if(sortOrder.equals("asc")){
				ctr.addOrder(Order.asc(sortColumn));
			}else{
				ctr.addOrder(Order.desc(sortColumn));
			}
		}
		ctr.setFirstResult(start);
		ctr.setMaxResults(pageSize);
		return ctr.list();
	}

	@Override
	public int getTotalNewRatings(String siteName, Status status, ReadStatus unread) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("readStatus", unread.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));
		if (siteName != null) {
			ctr.createAlias("site", "site");
			ctr.add(Restrictions.ilike("site.name", siteName+"%"));
		}
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult() != null ? (Integer) ctr.uniqueResult()
				: 0;
		return count;
	}

	@Override
	public List<SiteReview> findContentsByName(Status status, String siteName,
			int start, int pageSize, String sortColumn, String sortOrder) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));
		ctr.setFirstResult(start);
		ctr.setMaxResults(pageSize);
		ctr.createAlias("site", "site");
		ctr.createAlias("user", "user");
		ctr.createAlias("user.userinfo", "userinfo");
		
		if (siteName != null) {
			ctr.add(Restrictions.ilike("site.name", siteName + "%"));
		}
				
		if(sortOrder == null) {
			ctr.addOrder(Order.asc("site.name"));
		} else {
			if(sortOrder.equals("asc")) {
				ctr.addOrder(Order.asc(sortColumn));
			} else {
				ctr.addOrder(Order.desc(sortColumn));
			}
		}
		return ctr.list();
	}

	@Override
	public int getTotalRatings_Name(Status status, String siteName, ReviewStatus reviewStatus) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", reviewStatus.getValue()));
		if (siteName != null) {
			ctr.createAlias("site", "site");
			ctr.add(Restrictions.ilike("site.name", siteName + "%"));
		}
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult() != null ? (Integer) ctr.uniqueResult()
				: 0;
		return count;
	}

	@Override
	public List<SiteReview> findContentsByTopic(String name, Status status, int topicId,
			int start, int pageSize, String sortColumn, String sortOrder) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));
		ctr.createAlias("topics", "topic");
		ctr.add(Restrictions.eq("topic.id", topicId));
		ctr.setFirstResult(start);
		ctr.setMaxResults(pageSize);
		ctr.createAlias("site", "site");	
		ctr.add(Restrictions.ilike("site.name", name));
		
		if(sortOrder == null){
			ctr.addOrder(Order.asc("site.name"));
		}else{
			ctr.createAlias("user", "user");
			ctr.createAlias("user.userinfo", "userinfo");
			
			if(sortOrder.equals("asc")){
				ctr.addOrder(Order.asc(sortColumn));
			}else{
				ctr.addOrder(Order.desc(sortColumn));
			}
		}
		return ctr.list();
	}
	
	@Override
	public List<SiteReview> findContentsByTopicOrderByScore(String name, Status status, int topicId,
			int start, int pageSize, String sortColumn, String sortOrder) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));
		ctr.createAlias("topics", "topic");
		ctr.add(Restrictions.eq("topic.id", topicId));
		ctr.setFirstResult(start);
		ctr.setMaxResults(pageSize);
		ctr.createAlias("site", "site");	
		ctr.add(Restrictions.ilike("site.name", name));
		
		ctr.addOrder(Order.desc("score"));
		ctr.addOrder(Order.asc("site.name"));
		/*if(sortOrder == null){
				ctr.addOrder(Order.desc("score"));
				ctr.addOrder(Order.asc("site.name"));
		}else{
			ctr.addOrder(Order.asc("site.name"));
//			ctr.createAlias("user", "user");
//			ctr.createAlias("user.userinfo", "userinfo");
//			
//			if(sortOrder.equals("desc")){
//				ctr.addOrder(Order.asc(sortColumn));
//			}else{
//				ctr.addOrder(Order.desc(sortColumn));
//			}
		}*/
		return ctr.list();
		
	}

	@Override
	public int getTotalRatings_TopicId(Status status, int topicId, String name, ReviewStatus reviewStatus) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", reviewStatus.getValue()));
		ctr.createAlias("topics", "topic");
		ctr.add(Restrictions.eq("topic.id", topicId));
		if(StringUtils.isNotEmpty(name)) {
			ctr.createAlias("site", "site");
			ctr.add(Restrictions.ilike("site.name", name+"%"));
		}
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult() != null ? (Integer) ctr.uniqueResult()
				: 0;
		return count;
	}

	
	@Override
	public List<SiteReview> findContentsByExpert(Status status, long expertId, int start, int pageSize, 
			String sortColumn, String sortOrder, String siteName) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));
		ctr.createAlias("user", "user");
		ctr.add(Restrictions.eq("user.id", expertId));
		ctr.setFirstResult(start);
		ctr.setMaxResults(pageSize);
		ctr.createAlias("site", "site");
		ctr.createAlias("user.userinfo", "userinfo");
		
		if(StringUtils.isNotEmpty(siteName)) {
			ctr.add(Restrictions.ilike("site.name", siteName+"%"));
		}
		
		if(sortOrder == null){
			ctr.addOrder(Order.asc("site.name"));
		}else{
			if(sortOrder.equals("asc")){
				ctr.addOrder(Order.asc(sortColumn));
			}else{
				ctr.addOrder(Order.desc(sortColumn));
			}
		}
		
		return ctr.list();
	}

	@Override
	public int getTotalRatings_ExpertId(Status status, long expertId, String siteName) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));
		ctr.createAlias("user", "user");
		ctr.add(Restrictions.eq("user.id", expertId));
		if(StringUtils.isNotEmpty(siteName)) {
			ctr.createAlias("site", "site");
			ctr.add(Restrictions.ilike("site.name", siteName+"%"));
		}
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult() != null ? (Integer) ctr.uniqueResult()
				: 0;
		return count;
	}

	@Override
	public void updateSearchVector(SiteReviewForm siteReviewForm) {
		try {
			String queryString = "update sitereviews set searchvector= to_tsvector(?), description= ?,  evaluation= ?, last_updated= ?, "
					+ "score= ? , strength = ? , weakness = ? , review_status = ? where id= ?";

			SQLQuery query = getSession().createSQLQuery(queryString);
			query.setParameter(0, siteReviewForm.getDescription() + siteReviewForm.getStrength());
			query.setParameter(1, siteReviewForm.getDescription());
			query.setParameter(2, siteReviewForm.getEvaluation());
			query.setParameter(3, siteReviewForm.getDateUpdated());
			query.setParameter(4, siteReviewForm.getScore());
			query.setParameter(5, siteReviewForm.getStrength());
			query.setParameter(6, siteReviewForm.getWeakness());
			query.setParameter(7, siteReviewForm.getReviewStatus());
			query.setParameter(8, siteReviewForm.getId());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public SiteReview insertSiteReview(SiteReviewForm siteReviewForm) {
		SiteReview returnSiteReview = null;
		try {
			String idQueryString = "SELECT nextval('sitereviews_id_seq')";
			SQLQuery idQuery = getSession().createSQLQuery(idQueryString);
			BigInteger bigId = (BigInteger) idQuery.uniqueResult();
			long id = bigId.longValue();

			String queryString = "INSERT INTO sitereviews( "
					+ "id, status, date_created, last_updated, uuid, description, "
					+ "evaluation, score, searchvector, status_code, strength, weakness, "
					+ "site_id, topic_id, user_id,read_status,review_status,created_by) "
					+ "VALUES (:id, :status,:dateCreated,:dateUpdated, :uuid, :description,"
					+ " :evaluation, :score, to_tsvector(:searchvector),:status_code , :strength, "
					+ " :weakness, :site_id, :topic_id, :user_id ,:read_status, :review_status,:created_by ) ";

			SQLQuery query = getSession().createSQLQuery(queryString);
			query.setParameter("id", id);
			query.setParameter("status", siteReviewForm.getStatus());
			query.setParameter("dateCreated", siteReviewForm.getDateCreated());
			query.setParameter("dateUpdated", siteReviewForm.getDateUpdated());
			query.setParameter("uuid", siteReviewForm.getUuid());
			query.setParameter("description", siteReviewForm.getDescription());
			query.setParameter("evaluation", siteReviewForm.getEvaluation());
			query.setParameter("score", siteReviewForm.getScore());
			query.setParameter("searchvector", siteReviewForm.getDescription() + siteReviewForm.getStrength());
			query.setParameter("status_code", 1);
			query.setParameter("strength", siteReviewForm.getStrength());
			query.setParameter("weakness", siteReviewForm.getWeakness());
			query.setParameter("site_id", siteReviewForm.getSiteId());
			query.setParameter("topic_id", siteReviewForm.getTopicId());
			query.setParameter("user_id", siteReviewForm.getUserId());
			query.setParameter("read_status", siteReviewForm.getReadStatus());
			query.setParameter("review_status",
					siteReviewForm.getReviewStatus());
			query.setParameter("created_by", siteReviewForm.getCreatedBy());
			query.executeUpdate();

			returnSiteReview = get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnSiteReview;
	}

	@Override
	public List<SiteReviewForm> getSitesByKeyword(String keyword) {
		keyword = keyword.replaceAll(" ", "|");
		List<SiteReviewForm> list = new ArrayList<>();
		SiteReviewForm siteReviewForm = null;
		try {
			String queryString = "SELECT DISTINCT site_id,score FROM sitereviews, to_tsquery('pg_catalog.english', ?) as q WHERE (searchvector @@ q) "
					+ "AND review_status = ? order by score desc LIMIT 10";
			SQLQuery query = getSession().createSQLQuery(queryString);
			query.setParameter(0, (StringUtils.isNotEmpty(keyword) ? keyword+":*" : ""));
			query.setParameter(1, ReviewStatus.ONLINE.getValue());
			List<Object[]> objList = query.list();
			for (Object[] objs : objList) {
				siteReviewForm = new SiteReviewForm();
				int score = objs[1] != null ? Integer.parseInt(objs[1]
						.toString()) : 0;
				long siteId = objs[0] != null ? Long.parseLong(objs[0]
						.toString()) : 0;
				Site site = siteDao.get(siteId);
				siteReviewForm.setUrl(site.getUrl());
				siteReviewForm.setScore(score);
				list.add(siteReviewForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/*public List<SiteReviewForm> getSitesByKeyword(String keyword) {
		List<SiteReviewForm> list = new ArrayList<>();
		SiteReviewForm siteReviewForm = null;
		try {
			String queryString = "SELECT DISTINCT s.site_id,s.score FROM sitereviews s where s.description ILIKE ?"
					+ "AND review_status = ? order by score desc LIMIT 10";
			SQLQuery query = getSession().createSQLQuery(queryString);
			query.setParameter(0, "%"+keyword+"%");
			query.setParameter(1, ReviewStatus.ONLINE.getValue());
			List<Object[]> objList = query.list();
			for (Object[] objs : objList) {
				siteReviewForm = new SiteReviewForm();
				int score = objs[1] != null ? Integer.parseInt(objs[1]
						.toString()) : 0;
				long siteId = objs[0] != null ? Long.parseLong(objs[0]
						.toString()) : 0;
				Site site = siteDao.get(siteId);
				siteReviewForm.setUrl(site.getUrl());
				siteReviewForm.setScore(score);
				list.add(siteReviewForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/

	@Override
	public SiteReviewForm getSiteReviewRatings_URL(String url) {
		SiteReviewForm siteReviewForm = null;
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", Status.ACTIVE.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", ReviewStatus.ONLINE.getValue()));
		ctr.createAlias("site", "site");
		ctr.add(Restrictions.ilike("site.url", url+"%"));
		ctr.addOrder(Order.desc("score"));
		ctr.setMaxResults(1);
		SiteReview siteReview = (SiteReview) ctr.uniqueResult();
		if (siteReview != null) {
			siteReviewForm = new SiteReviewForm();
			siteReviewForm.setId(siteReview.getId());
			siteReviewForm.setScore(siteReview.getScore());
			siteReviewForm.setDescription(siteReview.getDescription());
			siteReviewForm.setEvaluation(siteReview.getEvaluation());
			siteReviewForm.setStrength(siteReview.getStrength());
			siteReviewForm.setWeakness(siteReview.getWeakness());
			siteReviewForm.setDateCreated(siteReview.getDateCreated());
			siteReviewForm.setDateUpdated(siteReview.getLastUpdated());
			siteReviewForm.setUrl(siteReview.getSite().getUrl());
			siteReviewForm.setExpertId(siteReview.getUser().getId());
			siteReviewForm.setSiteReviewRatingCriterias(siteReview
					.getSiteReviewsRatingCriterias());
		}
		return siteReviewForm;
	}

	@Override
	public void setCheckedStatus(long sitereviewId, ReadStatus read) {
		Query query = getSession().createQuery("UPDATE SiteReview SET readStatus = :status WHERE id = :id ");
		query.setParameter("status", read.getValue());
		query.setParameter("id", sitereviewId);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getSiteReviews_ReviewStatus(String name ,ReviewStatus status,
			long userId, Status active, int start, int pageSize,String sortColumn, String sortOrder) {		
		String sortName = "s.name";
		if(sortColumn != null && sortColumn.trim().equals("experts")){
			sortName = "info.firstname";
		}
		if(sortOrder == null ){
			sortOrder = "ASC";
		}
		
		String hql = "SELECT s.id , s.name , s.url, t.id , info.firstname , info.lastname, sr.score, sr.reviewStatus, u.id  "
				+ "FROM Site s INNER JOIN s.sitereviews sr "
				+ "INNER JOIN s.topics t "
				+ "INNER JOIN t.topicExpertAssignment tea "
				+ "INNER JOIN tea.expert u  "
				+ "INNER JOIN u.userinfo info "
				+ "WHERE tea.assignedBy.id = :user_id AND s.createdBy = tea.expert.id  "
				+ "AND sr.user.id = tea.expert.id AND sr.topics.id = t.id " 
				+ "AND LOWER(s.name) LIKE LOWER(:name) "
				+ "AND sr.reviewStatus = :review_status "
				+ "ORDER BY "+sortName+" "+sortOrder;
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("user_id", userId);
		query.setParameter("review_status", status.getValue());
		query.setMaxResults(pageSize);
		query.setFirstResult(start);
		
		return query.list();
	}

	@Override
	public int getTotalSiteReviews_ReviewStatus(String name ,ReviewStatus status, long userId, Status active) {
		int count = 0;
		String hql = "SELECT COUNT(s.id) "
				+ "FROM Site s INNER JOIN s.sitereviews sr "
				+ "INNER JOIN s.topics t "
				+ "INNER JOIN t.topicExpertAssignment tea "
				+ "INNER JOIN tea.expert u "
				+ "WHERE tea.assignedBy.id = :user_id AND s.createdBy = tea.expert.id  "
				+ "AND sr.user.id = tea.expert.id AND sr.topics.id = t.id " 
				+ "AND LOWER(s.name) LIKE LOWER(:name) "
				+ "AND sr.reviewStatus = :review_status";
				
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("user_id", userId);
		query.setParameter("review_status", status.getValue());
		
		count = query.uniqueResult()!=null ? ((Long) query.uniqueResult()).intValue() : 0;
		return count;
	}

	@Override
	public int getTotalSiteReviews_ReviewStatus_UserId(String name ,long userId,Status active, ReviewStatus status) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.add(Restrictions.eq("createdBy", userId));
		ctr.add(Restrictions.eq("reviewStatus", status.getValue()));
		ctr.createAlias("site", "site");
		ctr.add(Restrictions.ilike("site.name", name));
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult() != null ?(Integer) ctr.uniqueResult() : 0;
		return count;
	}

	@Override
	public List<SiteReview> getSiteReviews_ReviewStatus_UserId(String name ,Status active,
			int start, int pagesize, ReviewStatus status, long userId,String sortOrder) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.add(Restrictions.eq("createdBy", userId));
		ctr.add(Restrictions.eq("reviewStatus", status.getValue()));
		ctr.createAlias("site", "site");
		ctr.add(Restrictions.ilike("site.name", name));
		if(sortOrder == null){
			ctr.addOrder(Order.asc("site.name"));
		}else{
			if(sortOrder.equals("asc")){
				ctr.addOrder(Order.asc("site.name"));
			}else{
				ctr.addOrder(Order.desc("site.name"));
			}
		}
		ctr.setFirstResult(start);
		ctr.setMaxResults(pagesize);
		return ctr.list();
	}

	@Override
	public SiteReview getSiteReviews_SiteId_TopicId(Status active, long siteId,
			int topicId) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("site.id", siteId));
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.add(Restrictions.eq("topics.id", topicId));
		return (SiteReview) ctr.uniqueResult();
	}

	@Deprecated
	@Override
	public SiteStatusAndScoreForm getReviewStatus_TopicId_SiteId(Status active, int topicId,
			long siteId) {
		Query query = getSession()
				.createQuery(
						"SELECT NEW com.insonix.qrata.entity.SiteStatusAndScoreForm(sr.reviewStatus ,sr.score) FROM SiteReview sr WHERE status = :status and topics.id = :topicId "
								+ "and site.id = :siteId");
		query.setParameter("status", active.getValue());
		query.setParameter("topicId", topicId);
		query.setParameter("siteId", siteId);
		query.setCacheable(true);
		return (SiteStatusAndScoreForm) query.uniqueResult();
	}

	
	@Override
	public List<Site> getSiteNamesForReviewsAutoSuggest_Name(String siteName, String type, long entityId, Status status) {
		Criteria ctr = getSession().createCriteria(SiteReview.class, "sr");
		ctr.add(Restrictions.eq("sr.status", status.getValue()));
		ctr.add(Restrictions.eq("sr.reviewStatus", ReviewStatus.ONLINE.getValue()));
		ctr.createAlias("sr.site", "site");
		ctr.setProjection(Projections.projectionList()
				.add(Projections.property("site.name"), "name"));
		ctr.setMaxResults(10);
		ctr.addOrder(Order.asc("site.name"));		
		if (siteName != null) {
			ctr.add(Restrictions.ilike("site.name", siteName+"%"));
		}
		
		if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("NEWRATINGS")) {
			ctr.add(Restrictions.eq("sr.readStatus", ReadStatus.UNREAD.getValue()));
		} else if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("RATINGSBYTOPIC")) {
			ctr.add(Restrictions.eq("sr.topics.id", Integer.parseInt(entityId+"")));
		} else if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("RATINGSBYEXPERT")) {
			ctr.add(Restrictions.eq("sr.user.id", entityId));
		}
		
		ctr.setResultTransformer(Transformers.aliasToBean(Site.class));
		return ctr.list();
	}
	
	@Override
	public List<Site> getSiteNamesForReviewsAutoSuggest_Name_ReviewStatus(String siteName, long userId, 
			ReviewStatus reviewStatus, Status status) {
		Criteria ctr = getSession().createCriteria(SiteReview.class, "sr");
		ctr.add(Restrictions.eq("sr.status", status.getValue()));
		ctr.setMaxResults(10);
		ctr.createAlias("sr.site", "site");
		ctr.setProjection(Projections.projectionList()
				.add(Projections.property("site.name"), "name"));
		ctr.add(Restrictions.eq("sr.reviewStatus", reviewStatus.getValue()));
		ctr.add(Restrictions.eq("sr.createdBy", userId));
		
		ctr.addOrder(Order.asc("site.name"));		
		if (siteName != null) {
			ctr.add(Restrictions.ilike("site.name", siteName+"%"));
		}
		ctr.setResultTransformer(Transformers.aliasToBean(Site.class));		
		return ctr.list();
	}

	@Override
	public List<String> getSiteNamesForReviewsAutoSuggestForExpertPublishing_Name_ReviewStatus(String siteName, 
			long userId, ReviewStatus reviewStatus, Status status) {
		String hql = "SELECT s.name FROM Site s INNER JOIN s.sitereviews sr "
				+ "INNER JOIN s.topics t "
				+ "INNER JOIN t.topicExpertAssignment tea "
				+ "INNER JOIN tea.expert u "
				+ "WHERE tea.assignedBy.id = :user_id AND s.createdBy = tea.expert.id  "
				+ "AND sr.user.id = tea.expert.id AND sr.topics.id = t.id " 
				+ "AND LOWER(s.name) LIKE LOWER(:name) "
				+ "AND sr.reviewStatus = :review_status AND sr.status = :status ORDER BY s.name";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", siteName+"%");
		query.setParameter("user_id", userId);
		query.setParameter("status", status.getValue());
		query.setParameter("review_status", reviewStatus.getValue());
		query.setMaxResults(10);		
		return query.list();
	}
	
			/*
			 * (non-Javadoc)
			 * @see com.insonix.qrata.dao.SiteReviewsDao#qrataSearchKeywords_SearchTerm(java.lang.String)
			 * 
			 * qrata-front-end Methods
			 */
	@Override
	public List<String> qrataSearchKeywords_SearchTerm(String searchTerm) {
		List<String> keywordsList = null;
		try {
			if(StringUtils.isNotEmpty(searchTerm)) {
				String queryString = "select sr.description from SiteReview sr where UPPER(sr.description) LIKE UPPER(:desc)";
				Query query = getSession().createQuery(queryString).setCacheable(true);			
				query.setParameter("desc", "%"+searchTerm+"%");
				query.setMaxResults(10);
				keywordsList = query.list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keywordsList;
	}
		
	@Override
	public int getQrataSearchCount_Keyword(String keyword) {
		int count = 0;
		String queryString = "select COUNT(sr.id) from SiteReview sr where fts('pg_catalog.english', sr.searchvector, :vector) = true " +
				"AND sr.reviewStatus = :reviewStatus";
		Query query = getSession().createQuery(queryString).setCacheable(true);			
		query.setParameter("vector", keyword);
		query.setParameter("reviewStatus", ReviewStatus.ONLINE.getValue());
		count = query.uniqueResult()!=null ? ((Long)query.uniqueResult()).intValue() : 0;
		return count;
	}
	
	@Override
	public List<SiteReview> qrataSearch_Keyword(String searchTerm, int start, int pageSize) {
		List<SiteReview> reviewsList = null;
		try {
			String queryString = "select sr from SiteReview sr where fts('pg_catalog.english', sr.searchvector, :vector) = true " +
					"AND sr.reviewStatus = :reviewStatus ORDER BY sr.score DESC,sr.site.name";
			Query query = getSession().createQuery(queryString).setCacheable(true);			
			query.setParameter("vector", searchTerm);
			query.setParameter("reviewStatus", ReviewStatus.ONLINE.getValue());
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
			reviewsList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewsList;
	}

	@Override
	public List<SiteReview> getSiteReviews_TopicId(int topicId, Status active) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.add(Restrictions.eq("topics.id", topicId));
		return ctr.list();
	}

	@Override
	public void updateSiteScore(long siteReviewId, int totalSiteScore) {
		Query query = getSession().createQuery("UPDATE SiteReview sr SET sr.score = :score WHERE sr.id = :id");
		query.setParameter("score", totalSiteScore);
		query.setParameter("id", siteReviewId);		
		query.executeUpdate();		
	}
	
	@Override
	public int setContentStatusOffline(Status active, ReviewStatus offline, long siteReviewId, ReviewStatus online) {
		Query query = getSession().createQuery("UPDATE SiteReview sr SET sr.reviewStatus = :reviewStatus "
				+ "WHERE sr.id = :id AND sr.reviewStatus = :online AND sr.status = :active");
		query.setParameter("reviewStatus", offline.getValue());
		query.setParameter("id", siteReviewId);
		query.setParameter("online", online.getValue());
		query.setParameter("active", active.getValue());
		
		return query.executeUpdate();
	}

	@Override
	public int getTotal_AllContentByReviewStatus(String name, ReviewStatus reviewStatus, Status active) {
		int count = 0;
		String hql = "SELECT COUNT(s.id) "
				+ "FROM Site s INNER JOIN s.topics t "
				+ "INNER JOIN s.sitereviews sr "
				+ "INNER JOIN sr.user u "
				+ "INNER JOIN u.userinfo info  "
				+ "INNER JOIN u.role r "
				+ "WHERE sr.reviewStatus = :review_status AND sr.status = :status "
				+ "AND LOWER(s.name) LIKE LOWER(:name) ";
				
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("review_status", reviewStatus.getValue());
		query.setParameter("status", active.getValue());
		
		count = query.uniqueResult()!=null ? ((Long) query.uniqueResult()).intValue() : 0;
		return count;
	}

	@Override
	public List<Object[]> getAllContentsByReviewStatus(String name, ReviewStatus reviewStatus, int start, int pagesize,
			String sortColumn, String sortOrder, Status active) {
		String sortName = "s.name";
		if(sortColumn != null && sortColumn.trim().equals("experts")){
			sortName = "info.firstname";
		}
		if(sortOrder == null ){
			sortOrder = "ASC";
		}
		
		String hql = "SELECT s.id , s.name , s.url, t.id , info.firstname , info.lastname, sr.score, sr.reviewStatus, u.id , r.name  "
				+ "FROM Site s INNER JOIN s.topics t "
				+ "INNER JOIN s.sitereviews sr "
				+ "INNER JOIN sr.user u "
				+ "INNER JOIN u.userinfo info  "
				+ "INNER JOIN u.role r "
				+ "WHERE sr.reviewStatus = :review_status AND sr.status = :status "
				+ "AND LOWER(s.name) LIKE LOWER(:name) "
				+ "ORDER BY "+sortName+" "+sortOrder;
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("review_status", reviewStatus.getValue());
		query.setParameter("status", active.getValue());
		query.setMaxResults(pagesize);
		query.setFirstResult(start);
		
		return query.list();
	}

	@Override
	public List<SiteReview> getSiteReviews_UserId_ReviewStatus(Status active, long userId,ReviewStatus reviewStatus) {
		Criteria ctr = getSession().createCriteria(SiteReview.class);
		ctr.createAlias("user", "user");
		ctr.add(Restrictions.eq("user.id", userId));
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.add(Restrictions.eq("reviewStatus", reviewStatus.getValue()));
		return ctr.list();
	}

	@Override
	public int getExpertCoreRatings(User user, Status status, ReviewStatus reviewStatus) {
		int count = 0;
		Query query = getSession().createQuery("select COUNT(s.id) from SiteReview s where s.status = :status and " +
				"s.reviewStatus = :review_status and s.user.id = :user_id and s.site.backupRootSite IS NULL").setCacheable(true);
		query.setParameter("status", status.getValue());
		query.setParameter("review_status", reviewStatus.getValue());
		query.setParameter("user_id", user.getId());
		count = query.uniqueResult()!=null ? ((Long)query.uniqueResult()).intValue() : 0;
		return count;
	}
	
	@Override
	public int getExpertTotalRatings(User user, Status status, ReviewStatus reviewStatus) {
		int count = 0;
		Query query = getSession().createQuery("select COUNT(s.id) from SiteReview s where s.status = :status and " +
				"s.reviewStatus = :review_status and s.user.id = :user_id").setCacheable(true);
		query.setParameter("status", status.getValue());
		query.setParameter("review_status", reviewStatus.getValue());
		query.setParameter("user_id", user.getId());
		count = query.uniqueResult()!=null ? ((Long)query.uniqueResult()).intValue() : 0;
		return count;
	}
	
}
