package com.insonix.qrata.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.TopicDao;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;

@SuppressWarnings("unchecked")
@Repository("TopicsDao")
public class TopicDaoImpl extends BaseDao<Topic> implements TopicDao {

	@Override
	public List<Topic> getTopics_SubCategoryId(int subCategoryId) {
		Criteria crit = getSession().createCriteria(Topic.class);
		crit.createAlias("category", "category");
		crit.add(Restrictions.eq("category.id", subCategoryId))
				.add(Restrictions.eq("status", Status.ACTIVE.getValue()))
				.add(Restrictions.eq("topicType",
						Constants.Topics.TOPIC.getValue()))
				//.add(Restrictions.not(Restrictions.in("id", topicsId)))
				.addOrder(Order.asc("name"));
		return crit.list();
	}

	@Override
	public List<Topic> getSubTopics_TopicId(int topicsId) {
		Criteria crit = getSession().createCriteria(Topic.class);
		crit.createAlias("parentTopic", "parentTopic");
		crit.add(Restrictions.eq("parentTopic.id", topicsId))
				.add(Restrictions.eq("status", Status.ACTIVE.getValue()))
				.add(Restrictions.eq("topicType",
						Constants.Topics.SUBTOPIC.getValue()))
		.addOrder(Order.asc("name"));
		return crit.list();
	}

	@Override
	public List<Topic> getSubTopics(String uuid) {
		Criteria crit = getSession().createCriteria(Topic.class);
		crit.createAlias("parentTopic", "parentTopic");
		crit.add(Restrictions.eq("parentTopic.uuid", uuid))
				.add(Restrictions.eq("status", Status.ACTIVE.getValue()))
				.add(Restrictions.eq("topicType",
						Constants.Topics.SUBTOPIC.getValue()))
		.addOrder(Order.asc("name"));
		return crit.list();
	}
	/* (non-Javadoc)
	 * @see com.insonix.qrata.dao.TopicsDao#getTopics(com.insonix.qrata.constants.Status)
	 */
	@Override
	public List<Topic> getTopicsOrSubtopics_Type(String name, Status status, Constants.Topics type,
			int start, int pageSize,String sortField, String sortOrder) {
		Criteria ctr = getSession().createCriteria(Topic.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("topicType", type.getValue()));
		if(name != null) {
			ctr.add(Restrictions.ilike("name", name+"%"));
		}
		if(!StringUtils.isEmpty(sortField)) {
			if(sortOrder.equals("asc"))
				ctr.addOrder(Order.asc(sortField));
			else
				ctr.addOrder(Order.desc(sortField));
		} else {
			ctr.addOrder(Order.asc("name"));
		}
		
		ctr.setFirstResult(start);
		ctr.setMaxResults(pageSize);
		return ctr.list();
	}
	
	@Override
	public Topic getTopic(String uuid) {
		Criteria criteria=getSession().createCriteria(Topic.class);
		criteria.add(Restrictions.eq("uuid",uuid));
		return (Topic)criteria.uniqueResult();
	}
		
	@Override
	public List<Topic> getTopics_Name(String name) {
		Criteria criteria = getSession().createCriteria(Topic.class);
				criteria.add(Restrictions.eq("name",name))
				.addOrder(Order.asc("name"));
		return criteria.list();
	}
	
	@Override
	public Topic getTopic_Name_Id(String name, int id) {
		Criteria criteria = getSession().createCriteria(Topic.class);
		criteria.add(Restrictions.eq("name", name).ignoreCase());
		criteria.add(Restrictions.eq("parentTopic.id", id));
		return (Topic)criteria.uniqueResult();
	}

	@Override
	public List<Topic> getTopics_Name_CategoryId(String name, int categoryId) {
		Criteria criteria = getSession().createCriteria(Topic.class);
		criteria.add(Restrictions.eq("name",name).ignoreCase());
		criteria.add(Restrictions.eq("category.id", categoryId));
		return criteria.list();
	}

	@Override
	public int getCount_Type(String name, Status status, Topics type) {
		Criteria ctr = getSession().createCriteria(Topic.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("topicType", type.getValue()));
		if(name != null)
			ctr.add(Restrictions.ilike("name", name+"%"));
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult()!=null ? (Integer) ctr.uniqueResult() : 0;
		return count;
	}
	
	
	@Override
	public List<Topic> getTopicsOrSubtopics_Type_UserId(String name,
			Status status, Topics type, int start, int pageSize, long userId) {
		Criteria ctr = getSession().createCriteria(Topic.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("topicType", type.getValue()));
		if(name != null) {
			ctr.add(Restrictions.ilike("name", name+"%"));
		}
		if(userId != 0) {
			ctr.createAlias("expert", "expert");
			ctr.add(Restrictions.eq("expert.id", userId));
		}
		
		ctr.addOrder(Order.asc("name"));
		ctr.setFirstResult(start);
		ctr.setMaxResults(pageSize);
		return ctr.list();
	}
	
	@Override
	public List<Topic> getTopics_Subtopics_Type_UserId(Status status, long userId) {
		Criteria ctr = getSession().createCriteria(Topic.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		if(userId != 0) {
			ctr.createAlias("expert", "expert");
			ctr.add(Restrictions.eq("expert.id", userId));
		}
		
		ctr.addOrder(Order.asc("name"));
		return ctr.list();
	}
	
	@Override
	public int getCount_Type_UserId(String name, Status status, Topics type,
			long userId) {
		Criteria ctr = getSession().createCriteria(Topic.class);
		ctr.add(Restrictions.eq("status", status.getValue()));
		ctr.add(Restrictions.eq("topicType", type.getValue()));
		if(name != null) {
			ctr.add(Restrictions.ilike("name", name+"%"));
		}
		
		ctr.createAlias("expert", "expert");
		ctr.add(Restrictions.eq("expert.id", userId));
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult()!=null ? (Integer) ctr.uniqueResult() : 0;
		return count;
	}

	@Override
	public List<Site> getSites_Topic(Topic topic) {
		Query query = getSession().createQuery("SELECT t.sites FROM Topic t WHERE t.id = :id ");
		query.setParameter("id", topic.getId());
		return query.list();
	}

	@Override
	public int getTotalTopics_EditorId(String name, Status active, Topics topic, long id) {
		String hql = "SELECT COUNT(t.id) FROM Topic t LEFT JOIN  t.topicExpertAssignment tea "
				+ "WHERE t.createdBy = :created_by AND t.topicType = :topic_type AND tea.topic IS NULL AND t.childTopics IS EMPTY "
				+ "AND LOWER(t.name) LIKE LOWER(:name) ";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("created_by", id);
		query.setParameter("topic_type", topic.getValue());
		query.setParameter("name", name);
		
		int count = query.uniqueResult() != null ?((Long) query.uniqueResult()).intValue() : 0;
		return count;
	}

	@Override
	public List<Topic> getActiveTopics_EditorId(String name, Status active, Topics topic, int start, int pagesize, long id,
			String sortOrder,String sortColumn) {
		if(sortColumn == null && sortOrder == null){
			sortColumn = "name";
			sortOrder = "ASC";
		}
		
		String hql = "SELECT t FROM Topic t LEFT JOIN  t.topicExpertAssignment tea "
				+ "WHERE t.createdBy = :created_by AND t.topicType = :topic_type AND tea.topic IS NULL AND t.childTopics IS EMPTY "
				+ "AND LOWER(t.name) LIKE LOWER(:name) "
				+ "ORDER BY "+sortColumn+" "+sortOrder;
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("created_by", id);
		query.setParameter("topic_type", topic.getValue());
		query.setParameter("name", name);
		query.setFirstResult(start);
		query.setMaxResults(pagesize);
		
		return query.list();
	}

	@Override
	public List<Topic> getTopics_SubCategoryId_AssignedTopics(int subCategoryId) {
		String hql = "SELECT t FROM Topic t LEFT JOIN  t.topicExpertAssignment tea "
				+ "WHERE t.topicType = :topic_type AND tea.topic IS NULL "
				+ "AND t.parentTopic IS NULL AND t.category.id = :category_id "
				+ "ORDER BY t.name ASC";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("topic_type", Constants.Topics.TOPIC.getValue());
		query.setParameter("category_id", subCategoryId);
		
		return query.list();
	}

	@Override
	public List<Topic> getSubTopics_TopicId_AssignedTopics(int topicId) {
		String hql = "SELECT t FROM Topic t LEFT JOIN  t.topicExpertAssignment tea "
				+ "WHERE t.topicType = :topic_type AND tea.topic IS NULL "
				+ "AND t.parentTopic.id = :parenttopic_id AND t.parentTopic IS NOT NULL "
				+ "ORDER BY t.name ASC ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("topic_type", Constants.Topics.SUBTOPIC.getValue());
		query.setParameter("parenttopic_id", topicId);
		
		return query.list();
	}

	@Override
	public BigInteger getSitesCount_TopicId(int topicId) {
		String sql = "SELECT COUNT(site_id) FROM topics_sites WHERE topic_id = :topic_id";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("topic_id", topicId);
		//System.out.println(query.uniqueResult());
		return query.uniqueResult() != null ? (BigInteger) query.uniqueResult() : new BigInteger("0");
	}

	@Override
	public List<Topic> searchTopic_Name_SubCategoryId(String name,int subCatId, Status active, Topics topic) {
		Criteria crt = getSession().createCriteria(Topic.class);
		crt.add(Restrictions.eq("category.id", subCatId));
		crt.add(Restrictions.ilike("name", name+"%"));
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.eq("topicType", topic.getValue()));
		crt.addOrder(Order.asc("name"));
		return crt.list();
	}

	@Override
	public List<Topic> searchSubTopic_Name_ParentTopicId(String name,int topicId, Topics subtopic, Status active) {
		Criteria crt = getSession().createCriteria(Topic.class);
		crt.add(Restrictions.eq("parentTopic.id", topicId));
		crt.add(Restrictions.ilike("name", name+"%"));
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.eq("topicType", subtopic.getValue()));
		crt.addOrder(Order.asc("name"));
		return crt.list();
	}

	@Override
	public int getTotalActiveTopic_Type(Status active, String name,Topics topic, Integer subCategoryId, Integer parentTopicId) {
		int count = 0;
		Criteria crt = getSession().createCriteria(Topic.class);
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.ilike("name", name));
		crt.add(Restrictions.eq("topicType", topic.getValue()));
		if(parentTopicId == null){
			crt.add(Restrictions.eq("category.id", subCategoryId));
		}else{
			crt.add(Restrictions.eq("parentTopic.id", parentTopicId));
		}
		crt.setProjection(Projections.rowCount());
		count = crt.uniqueResult() != null ? (Integer) crt.uniqueResult() : 0;
		return count;
	}

	@Override
	public List<Topic> getActiveTopic_Type(Status active, String name,Topics topic, Integer subCategoryId, Integer parentTopicId,
			int pagesize, String sortField, String sortOrder, int start) {
		Criteria crt = getSession().createCriteria(Topic.class);
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.ilike("name", name));
		crt.add(Restrictions.eq("topicType", topic.getValue()));
		if(parentTopicId == null){
			crt.add(Restrictions.eq("category.id", subCategoryId));
		}else{
			crt.add(Restrictions.eq("parentTopic.id", parentTopicId));
		}
		crt.setMaxResults(pagesize);
		crt.setFirstResult(start);
		
		if(!StringUtils.isEmpty(sortField)) {
			if(sortOrder.equals("asc"))
				crt.addOrder(Order.asc(sortField));
			else
				crt.addOrder(Order.desc(sortField));
		} else {
			crt.addOrder(Order.asc("name"));
		}
		
		crt.addOrder(Order.asc("name"));
		return crt.list();
	}
	
	@Override
	public List<String> getTopicNamesForAutoSuggest_Name(String name, Integer parentId, 
			Status status, Topics type) {
		Criteria crt = getSession().createCriteria(Topic.class);
		crt.setProjection(Projections.property("name"));
		crt.add(Restrictions.ilike("name", name+"%"));
		crt.add(Restrictions.eq("status", status.getValue()));
		crt.add(Restrictions.eq("topicType", type.getValue()));
		crt.addOrder(Order.asc("name"));
		if(type.getValue() == Topics.TOPIC.getValue()) {
			if(parentId != 0) {
				crt.add(Restrictions.eq("category.id", parentId));
			}
		} else {
			if(parentId != 0) {
				crt.add(Restrictions.eq("parentTopic.id", parentId));
			}
		}
		crt.setMaxResults(10);
		return crt.list();
	}
	
	
	@Override
	public List<String> getTopicNamesForAutoSuggestForMyPublishing_Name(String name, Topics type, 
			Status status, long userId) {
		String hql = "SELECT t.name FROM Topic t LEFT JOIN  t.topicExpertAssignment tea "
				+ "WHERE t.createdBy = :created_by AND t.topicType = :topic_type AND tea.topic IS NULL "
				+ "AND LOWER(t.name) LIKE LOWER(:name) "
				+ "ORDER BY t.name ASC ";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("created_by", userId);
		query.setParameter("topic_type", type.getValue());
		query.setParameter("name", name);
		query.setMaxResults(10);
	
		return query.list();
	}

	@Override
	public List<Object[]> getAllActiveTopics_Type_ChildCount_SiteCount_RatingCount( Topics topic, String name, int start, int pagesize,
			String sortField, String sortOrder) {
		String sql = "SELECT t.id, t.name, t.uuid, t.category_id, tea.expert_id, ct.ct_count, tcr.tcr_count, s.s_count, t.leaf_node "
					+ "FROM topics t "
					+ "LEFT JOIN (SELECT parenttopic_id , COUNT(id) ct_count FROM topics GROUP BY parenttopic_id) ct ON ct.parenttopic_id = t.id "
					+ "LEFT JOIN (SELECT topic_id, COUNT(id) tcr_count FROM topics_ratingcriteria GROUP BY topic_id) tcr ON tcr.topic_id = t.id "
					+ "LEFT JOIN (SELECT topic_id, COUNT(site_id) s_count FROM topics_sites GROUP BY topic_id) s ON s.topic_id = t.id "
					+ "LEFT JOIN topic_expert_assignment tea ON tea.topic_id = t.id ";
		
		if(sortField == null || sortOrder == null){
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "ORDER BY t.name ASC LIMIT :pagesize OFFSET :start";
		}else{
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "ORDER BY "+sortField+" "+sortOrder+" LIMIT :pagesize OFFSET :start";
		}
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("topic_type", topic.getValue());
		query.setParameter("search_name", name);
		query.setParameter("pagesize", pagesize);
		query.setParameter("start", start);
		
		return query.list();
	}

	@Override
	public List<Object[]> getAllActiveSubTopics_ChildCount_SiteCount_RatingCount(Topics subtopic, String name, int start, int pagesize,
			String sortField, String sortOrder) {
		String sql = "SELECT t.id, t.name, t.uuid, t.category_id, t.parenttopic_id, tea.expert_id, tcr.tcr_count, s.s_count "
					+ "FROM topics t "
					+ "LEFT JOIN (SELECT topic_id, COUNT(id) tcr_count FROM topics_ratingcriteria GROUP BY topic_id) tcr ON tcr.topic_id = t.id "
					+ "LEFT JOIN (SELECT topic_id, COUNT(site_id) s_count FROM topics_sites GROUP BY topic_id) s ON s.topic_id = t.id "
					+ "LEFT JOIN topic_expert_assignment tea ON tea.topic_id = t.id ";
		
		if(sortField == null || sortOrder == null){
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "ORDER BY t.name ASC LIMIT :pagesize OFFSET :start";
		}else{
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "ORDER BY "+sortField+" "+sortOrder+" LIMIT :pagesize OFFSET :start";
		}
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("topic_type", subtopic.getValue());
		query.setParameter("search_name", name);
		query.setParameter("pagesize", pagesize);
		query.setParameter("start", start);
		
		return query.list();
	}

	@Override
	public List<Object[]> getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount(Topics topic, Integer subCategoryId, String name, int start,
			int pagesize, String sortField, String sortOrder) {
		String sql = "SELECT t.id, t.name, t.uuid, t.category_id, tea.expert_id, ct.ct_count, tcr.tcr_count, s.s_count, t.leaf_node "
				+ "FROM topics t "
				+ "LEFT JOIN (SELECT parenttopic_id , COUNT(id) ct_count FROM topics GROUP BY parenttopic_id) ct ON ct.parenttopic_id = t.id "
				+ "LEFT JOIN (SELECT topic_id, COUNT(id) tcr_count FROM topics_ratingcriteria GROUP BY topic_id) tcr ON tcr.topic_id = t.id "
				+ "LEFT JOIN (SELECT topic_id, COUNT(site_id) s_count FROM topics_sites GROUP BY topic_id) s ON s.topic_id = t.id "
				+ "LEFT JOIN topic_expert_assignment tea ON tea.topic_id = t.id ";
		
		if(sortField == null || sortOrder == null){
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "AND t.category_id = :category_id "
					+ "ORDER BY t.name ASC LIMIT :pagesize OFFSET :start";
		}else{
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "AND t.category_id = :category_id "
					+ "ORDER BY "+sortField+" "+sortOrder+" LIMIT :pagesize OFFSET :start";
		}
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("topic_type", topic.getValue());
		query.setParameter("search_name", name);
		query.setParameter("category_id", subCategoryId);
		query.setParameter("pagesize", pagesize);
		query.setParameter("start", start);
		
		return query.list();
	}

	@Override
	public List<Object[]> getActiveSubTopicsByCategoryId_ChildCount_SiteCount_RatingCount( Topics subtopic, String name, int parentTopicId, int start,
			int pagesize, String sortField, String sortOrder) {
		String sql = "SELECT t.id, t.name, t.uuid, t.category_id, t.parenttopic_id, tea.expert_id, tcr.tcr_count, s.s_count "
				+ "FROM topics t "
				+ "LEFT JOIN (SELECT topic_id, COUNT(id) tcr_count FROM topics_ratingcriteria GROUP BY topic_id) tcr ON tcr.topic_id = t.id "
				+ "LEFT JOIN (SELECT topic_id, COUNT(site_id) s_count FROM topics_sites GROUP BY topic_id) s ON s.topic_id = t.id "
				+ "LEFT JOIN topic_expert_assignment tea ON tea.topic_id = t.id ";
		
		if(sortField == null || sortOrder == null){
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "AND t.parenttopic_id = :parent_topic_id "
					+ "ORDER BY t.name ASC LIMIT :pagesize OFFSET :start";
		}else{
			sql = sql + "WHERE t.topic_type = :topic_type AND t.name ILIKE :search_name "
					+ "AND t.parenttopic_id = :parent_topic_id "
					+ "ORDER BY "+sortField+" "+sortOrder+" LIMIT :pagesize OFFSET :start";
		}
		
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("topic_type", subtopic.getValue());
		query.setParameter("search_name", name);
		query.setParameter("parent_topic_id", parentTopicId);
		query.setParameter("pagesize", pagesize);
		query.setParameter("start", start);
		
		return query.list();
	}

	@Override
	public List<Topic> getTopics_SubCategoryIdOrParentTopicId_Type(Status active, Topics topicType, int subCatId, int parentTopicId) {
		Criteria ctr = getSession().createCriteria(Topic.class);
		if(parentTopicId == 0){
			ctr.add(Restrictions.eq("category.id", subCatId));
		}else{
			ctr.add(Restrictions.eq("parentTopic.id", parentTopicId));
		}
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.add(Restrictions.eq("topicType", topicType.getValue()));
		return ctr.list();
	}

	
//	@Override
//	public int getTotalRatings_TopicId(Status status,int topicId,String name, ReviewStatus reviewStatus) {
//		Criteria crt = getSession().createCriteria(Topic.class);
//		crt.add(Restrictions.eq("status", status.getValue()));
//		crt.add(Restrictions.eq("reviewStatus", reviewStatus.getValue()));
//		crt.createAlias("topics", "topic");
//    	crt.add(Restrictions.eq("topic.id", topicId));
//		if(StringUtils.isNotEmpty(name)) {
//			crt.createAlias("topic", "topic");
//			crt.add(Restrictions.ilike("topic.name", name+"%"));
//		}
//		crt.setProjection(Projections.rowCount());
//		int count = crt.uniqueResult() != null ? (Integer) crt.uniqueResult()
//				: 0;
//		return count;
//	}
//	
//
//
//	
}
