/**
 * 
 */
package com.insonix.qrata.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.TopicExpertAssignmentDao;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicExpertAssignment;
import com.insonix.qrata.models.User;

/**
 * @author Harmeet Singh
 * 
 */
@SuppressWarnings("unchecked")
@Repository("TopicExpertAssignmentDao")
public class TopicExpertAssignmentDaoImpl extends
		BaseDao<TopicExpertAssignment> implements TopicExpertAssignmentDao {

	@Override
	public List<TopicExpertAssignment> getTopicExpertAssignment_AssignedBy(
			String name, User user, short topicType, int start, int pagesize,
			String sortColumn, String sortOrder) {
		Criteria crt = getSession().createCriteria(TopicExpertAssignment.class);
		crt.add(Restrictions.eq("assignedBy.id", user.getId()));
		crt.createAlias("topic", "topic");
		crt.add(Restrictions.eq("topic.topicType", topicType));
		crt.add(Restrictions.ilike("topic.name", name));
		crt.createAlias("expert", "expert");
		crt.createAlias("expert.userinfo", "userinfo");
		if (sortColumn == null) {
			crt.addOrder(Order.asc("topic.name"));
		} else {
			if (sortOrder.equals("asc")) {
				crt.addOrder(Order.asc(sortColumn));
			} else {
				crt.addOrder(Order.desc(sortColumn));
			}
		}
		crt.setFirstResult(start);
		crt.setMaxResults(pagesize);
		return crt.list();
	}

	@Override
	public int getTotalTopics_ExpertId(String name, Status active, long id,
			short topicType) {
		Criteria ctr = getSession().createCriteria(TopicExpertAssignment.class);
		ctr.add(Restrictions.eq("expert.id", id));
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.createAlias("topic", "topic");
		ctr.add(Restrictions.eq("topic.topicType", topicType));
		ctr.add(Restrictions.ilike("topic.name", name));
		ctr.setProjection(Projections.rowCount());
		int count = ctr.uniqueResult() != null ? (Integer) ctr.uniqueResult()
				: 0;
		return count;
	}

	@Override
	public List<TopicExpertAssignment> getActiveTopics_ExpertId(String name,
			Status active, int start, int pagesize, long id, short topicType,
			String sortOrder, String sortColumn) {
		Criteria ctr = getSession().createCriteria(TopicExpertAssignment.class);
		ctr.add(Restrictions.eq("expert.id", id));
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.createAlias("topic", "topic");
		ctr.add(Restrictions.eq("topic.topicType", topicType));
		ctr.add(Restrictions.ilike("topic.name", name));
		if (sortColumn != null && sortColumn.equals("name")) {
			if (sortOrder == null) {
				ctr.addOrder(Order.asc("topic.name"));
			} else {
				if (sortOrder.equals("asc")) {
					ctr.addOrder(Order.asc("topic.name"));
				} else {
					ctr.addOrder(Order.desc("topic.name"));
				}
			}
		} else {
			ctr.addOrder(Order.asc("topic.name"));
		}

		ctr.setFirstResult(start);
		ctr.setMaxResults(pagesize);
		return ctr.list();
	}

	@Override
	public List<User> getExpert_AssignedByUser(long assignedById, Status active) {
		Query query = getSession()
				.createQuery("SELECT tea.expert FROM TopicExpertAssignment tea WHERE tea.assignedBy.id = :id");
		query.setParameter("id", assignedById).setCacheable(true);
		return query.list();
	}

	@Override
	public List<Long> getExpertIds_AssignedByUser(long assignedById,
			Status active) {
		Query query = getSession()
				.createQuery("SELECT DISTINCT tea.expert.id FROM TopicExpertAssignment tea WHERE tea.assignedBy.id = :id");
		query.setParameter("id", assignedById).setCacheable(true);
		return query.list();
	}

	@Override
	public int getTotalTopicExpertAssignment_AssignedBy(String name, User user,
			short topicType) {
		Criteria crt = getSession().createCriteria(TopicExpertAssignment.class);
		crt.add(Restrictions.eq("assignedBy.id", user.getId()));
		crt.createAlias("topic", "topic");
		crt.add(Restrictions.eq("topic.topicType", topicType));
		crt.add(Restrictions.ilike("topic.name", name));
		crt.setProjection(Projections.rowCount());
		int count = crt.uniqueResult() != null ? (Integer) crt.uniqueResult()
				: 0;
		return count;
	}

	@Override
	public List<Topic> getTopics_ExpertId(Status active, User user) {
		Query query = getSession()
				.createQuery(
						"SELECT tea.topic FROM TopicExpertAssignment tea WHERE tea.expert.id = :id")
				.setCacheable(true);
		query.setParameter("id", user.getId());
		return query.list();
	}

	@Override
	public List<Topic> getTopics_AssignedId(Status active, User user) {
		Query query = getSession()
				.createQuery(
						"SELECT tea.topic FROM TopicExpertAssignment tea WHERE tea.assignedBy.id = :id");
		query.setParameter("id", user.getId());
		return query.list();
	}

	@Override
	public List<Integer> getTopicIds(Status active) {
		Query query = getSession().createQuery(
				"SELECT tea.topic.id FROM TopicExpertAssignment tea")
				.setCacheable(true);
		return query.list();
	}

	@Override
	public List<Integer> getTopics_TopicId(Status active, Topic topic) {
		Query query = getSession()
				.createQuery(
						"SELECT tea.topic.id FROM TopicExpertAssignment tea WHERE tea.topic.id = :topic_id")
				.setCacheable(true);
		query.setParameter("topic_id", topic.getId());
		return query.list();
	}

	@Override
	public long getExpertId_TopicId(Status active, int topicId) {
		Query query = getSession()
				.createQuery(
						"SELECT tea.expert.id FROM TopicExpertAssignment tea WHERE tea.topic.id = :topic_id AND "
								+ "tea.status = :status").setCacheable(true);
		query.setParameter("topic_id", topicId);
		query.setParameter("status", active.getValue());
		return query.uniqueResult() != null ? (Long) query.uniqueResult() : 0;
	}

	@Override
	public List<Category> getAssignedCategoriesByDomainId_ExpertId(int domainId, long userId) {
		String hql = "SELECT DISTINCT(c) FROM TopicExpertAssignment tea " +
				"INNER JOIN tea.topic t INNER JOIN t.category sc INNER JOIN sc.parentCategory c INNER JOIN c.parentCategory d " +
				"WHERE tea.expert.id = :expert_id AND d.id = :domain_id " +
				"AND sc.categoryType = :subcategory_type AND sc.parentCategory IS NOT NULL " +
				"AND c.categoryType = :category_type AND c.parentCategory IS NOT NULL";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("expert_id", userId);
		query.setParameter("domain_id", domainId);
		query.setParameter("subcategory_type", Constants.CategoryType.SUBCATEGORY.getValue());
		query.setParameter("category_type", Constants.CategoryType.CATEGORY.getValue());
		return query.list();
	}

	@Override
	public List<Category> getAssignedSubCategoriesByCategoryId_ExpertId(int categoryId, long userId) {
		String hql = "SELECT DISTINCT(sc) FROM TopicExpertAssignment tea " +
				"INNER JOIN tea.topic t INNER JOIN t.category sc INNER JOIN sc.parentCategory c INNER JOIN c.parentCategory d " +
				"WHERE tea.expert.id = :expert_id AND c.id = :category_id " +
				"AND sc.categoryType = :subcategory_type AND sc.parentCategory IS NOT NULL ";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("expert_id", userId);
		query.setParameter("category_id", categoryId);
		query.setParameter("subcategory_type", Constants.CategoryType.SUBCATEGORY.getValue());
		return query.list();
	}

	@Override
	public List<Topic> getAssignedTopicsBySubCategoryId_ExpertId(int subcategoryId, long userId) {
		String hql = "SELECT DISTINCT(t) FROM TopicExpertAssignment tea " +
				"INNER JOIN tea.topic t INNER JOIN t.category sc " +
				"WHERE tea.expert.id = :expert_id AND sc.id = :subcategory_id  ";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("expert_id", userId);
		query.setParameter("subcategory_id", subcategoryId);
		return query.list();
	}

	@Override
	public List<Topic> getAssignedSubTopicByTopicId_expertId(int topicId, long userId) {
		String hql = "SELECT DISTINCT(t) FROM TopicExpertAssignment tea " +
				"INNER JOIN tea.topic t " +
				"WHERE tea.expert.id = :expert_id AND t.parentTopic.id = :topic_id  "
				+ "AND t.topicType = :topic_type";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("expert_id", userId);
		query.setParameter("topic_id", topicId);
		query.setParameter("topic_type", Constants.Topics.SUBTOPIC.getValue());
		return query.list();
	}

	@Override
	public List<String> getTopicNamesForAutoSuggestForExpertPublishing_Name(String name, Topics type, 
			Status status, long userId, Roles role) {
		Criteria crt = getSession().createCriteria(TopicExpertAssignment.class, "te");
		crt.createAlias("te.topic", "topic");
		crt.setProjection(Projections.projectionList()
				.add(Projections.property("topic.name"), "name"));
		if(role != null && role.getValue() == Roles.EDITOR.getValue()) {
			crt.add(Restrictions.eq("te.assignedBy.id", userId));
		} else {
			crt.add(Restrictions.eq("expert.id", userId));
		}
		crt.add(Restrictions.eq("topic.topicType", type.getValue()));
		crt.add(Restrictions.ilike("topic.name", name+"%"));
		crt.addOrder(Order.asc("topic.name"));
		crt.setMaxResults(10);
		return crt.list();
	}
	
}
