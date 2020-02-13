package com.insonix.qrata.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.UserDao;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;
import com.insonix.qrata.models.UserInfo;

/**
 * @author kamal
 * 
 */
@Repository("usersDao")
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insonix.qrata.dao.UsersDao#getUser_UserName(java.lang.String)
	 */
	/*
	 * this method is used to get user by its username we define _ in place of
	 * by
	 */
	@Override
	public User getUser(String userName) {
		return getUser(userName, Status.ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.insonix.qrata.dao.UsersDao#getUser_UserName_status(java.lang.String,
	 * com.insonix.qrata.constants.Status)
	 */
	/*
	 * this method is used to get user by its username and its status
	 */
	@Override
	public User getUser(String userName, Status status) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("userName", userName).ignoreCase());
		crit.add(Restrictions.eq("status", status.getValue()));
		return (User) crit.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insonix.qrata.dao.UsersDao#findAll()
	 */
	/*
	 * this mehtod is used get list of all users whose status is active
	 */
	@Override
	public List<User> findAll(String name, int start, int limit, String sortField, String sortOrder, Status status) {
		Criteria criteria = getSession().createCriteria(User.class);		
		criteria.add(Restrictions.eq("status", status.getValue()));
		criteria.createAlias("userinfo", "userInfo");
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		
		if(!StringUtils.isEmpty(name)) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.ilike("userInfo.firstname", name+"%"));
			or.add(Restrictions.ilike("userInfo.lastname", name+"%"));
			or.add(Restrictions.ilike("userInfo.email", name+"%"));
			or.add(Restrictions.ilike("userName", name+"%"));
			criteria.add(or);
		}
		
		if(!StringUtils.isEmpty(sortField)) {
			if(sortOrder.equals("asc"))
				criteria.addOrder(Order.asc(sortField));
			else
				criteria.addOrder(Order.desc(sortField));
		} else {
			criteria.addOrder(Order.asc("userInfo.firstname"));
		}
		
		return criteria.list();
	}
	
	@Override
	public int getTotalActiveUsersCount(String name, Status status) {
		int count = 0;
		Criteria criteria = getSession().createCriteria(User.class);		
		criteria.add(Restrictions.eq("status", status.getValue()));
		
		if(!StringUtils.isEmpty(name)) {
			Disjunction or = Restrictions.disjunction();
			criteria.createAlias("userinfo", "userInfo");
			or.add(Restrictions.ilike("userInfo.firstname", name+"%"));
			or.add(Restrictions.ilike("userInfo.lastname", name+"%"));
			or.add(Restrictions.ilike("userInfo.email", name+"%"));
			or.add(Restrictions.ilike("userName", name+"%"));
			criteria.add(or);
		}
		
		criteria.setProjection(Projections.rowCount());
		count = criteria.uniqueResult()!=null ? (Integer)criteria.uniqueResult() : 0;
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.insonix.qrata.dao.UsersDao#getUser_Role(com.insonix.qrata.models.
	 * Roles)
	 */
	@Override
	public List<User> getUsers(Role role) {
		return getUser(role, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.insonix.qrata.dao.UsersDao#getUser_role_firstnameStartsWith(com.insonix
	 * .qrata.models.Roles, java.lang.String)
	 */
	@Override
	public List<User> getUser_Role_NameStartsWith(Role role, String searchVal) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("role", "role");
		criteria.add(Restrictions.eq("role.id", role.getId())).add(
				Restrictions.eq("status", Status.ACTIVE.getValue()));
		criteria.addOrder(Order.asc("userName"));
		criteria.createAlias("userinfo", "userinfo");
		
		Disjunction or = Restrictions.disjunction();
	//	or.add(Restrictions.ilike("userinfo.firstname", searchVal+"%"));
		or.add(Restrictions.ilike("userinfo.lastname", searchVal+"%"));
		criteria.add(or);
		
		return criteria.list();
	}

	private List<User> getUser(Role role, String userName, String firstName,
			Status status) {
		Criteria criteria = getSession().createCriteria(User.class);
		if (role != null) {
			criteria.createAlias("role", "role");
			criteria.add(Restrictions.eq("role.id", role.getId()));
			criteria.addOrder(Order.asc("userName"));
		}
		if (!StringUtils.isEmpty(firstName)) {
			criteria.createAlias("userinfo", "userinfo");
			criteria.add(Restrictions.eq("userinfo.firstname", firstName));
			criteria.addOrder(Order.asc("userinfo.firstname"));
		}
		if (status != null) {
			criteria.add(Restrictions.eq("status", status.getValue()));
			criteria.addOrder(Order.asc("userName"));
		}

		return criteria.list();
	}

	@Override
	public User getUser_Email(String email) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("userinfo", "userInfo");
		User user = (User) criteria.add(
				Restrictions.eq("userInfo.email", email).ignoreCase()).uniqueResult();
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insonix.qrata.dao.UsersDao#getUser_uuid(java.lang.String)
	 */
	@Override
	public User getUser_uuid(String uuid) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (User) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.insonix.qrata.dao.UsersDao#getUsers(com.insonix.qrata.models.Roles,
	 * com.insonix.qrata.constants.Status)
	 */
	@Override
	public List<User> getUsers(Role role, Status status) {
		return getUser(role, null, null, status);
	}
	
	@Override
	public List<User> getUser_FirstName(String name) {
		Criteria criteria =getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("name",name));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}

	@Override
	public List<User> getExperts(Role role, Status active, String sortField,  String sortOrder, String name, int start, int pagesize) {
		Criteria ctr = getSession().createCriteria(User.class);
		ctr.createAlias("role", "role");
		ctr.add(Restrictions.eq("role.id", role.getId()));
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.createAlias("userinfo", "userinfo");
		
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("userinfo.firstname", name));
		or.add(Restrictions.ilike("userinfo.lastname", name));
		or.add(Restrictions.ilike("userinfo.email", name));
		or.add(Restrictions.ilike("userName", name));
		ctr.add(or);
		ctr.setMaxResults(pagesize);
		ctr.setFirstResult(start);
		
		if(sortField == null){
			ctr.addOrder(Order.asc("userinfo.firstname"));	
		}else{
		
			if(sortOrder.equals("asc")){
				ctr.addOrder(Order.asc(sortField));
			}else{
				ctr.addOrder(Order.desc(sortField));
			}
		}
		
		return ctr.list();
	}
	
	@Override
	public List<User> getExpertsByLastName(Role role, Status active, String sortField,  String sortOrder, String name, int start, int pagesize) {
		Criteria ctr = getSession().createCriteria(User.class);
		ctr.createAlias("role", "role");
		ctr.add(Restrictions.eq("role.id", role.getId()));
		ctr.add(Restrictions.eq("status", active.getValue()));
		ctr.createAlias("userinfo", "userinfo");
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("userinfo.lastname", name+"%"));
		ctr.add(or);
		ctr.setMaxResults(pagesize);
		ctr.setFirstResult(start);
		if(sortField == null){
			ctr.addOrder(Order.asc("userinfo.lastname"));	
		}else{
		
			if(sortOrder.equals("asc")){
				ctr.addOrder(Order.asc(sortField));
			}else{
				ctr.addOrder(Order.desc(sortField));
			}
		}
		
		return ctr.list();
	}

	@Override
	public int getTotalUser_Role(String name, Roles role, Status active) {
		int count = 0;
		Criteria ctr = getSession().createCriteria(User.class);
		ctr.createAlias("role", "role");
		ctr.createAlias("userinfo", "userinfo");
		ctr.add(Restrictions.eq("role.id", role.getValue()));
		ctr.add(Restrictions.eq("status", active.getValue()));
		
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("userinfo.firstname", name));
		or.add(Restrictions.ilike("userinfo.lastname", name));
		or.add(Restrictions.ilike("userinfo.email", name));
		or.add(Restrictions.ilike("userName", name));
		ctr.add(or);
		
		ctr.setProjection(Projections.rowCount());
		count = ctr.uniqueResult()!=null ? (Integer)ctr.uniqueResult() : 0;
		return count;
	}
	
	@Override
	public int getTotalUser_RoleByLastName(String name, Roles role, Status active) {
		int count = 0;
		Criteria ctr = getSession().createCriteria(User.class);
		ctr.createAlias("role", "role");
		ctr.createAlias("userinfo", "userinfo");
		ctr.add(Restrictions.eq("role.id", role.getValue()));
		ctr.add(Restrictions.eq("status", active.getValue()));
		
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("userinfo.lastname", name));
		ctr.add(or);
		
		ctr.setProjection(Projections.rowCount());
		count = ctr.uniqueResult()!=null ? (Integer)ctr.uniqueResult() : 0;
		return count;
	}
	
	@Override
	public List<String> getUserNamesForAutoSuggest_Name(String name, Roles role, Status status) {
		List<String> infoList = null;
		List<String> returnList = new ArrayList<>();;
			/*
			 * First Name Records
			 */
		Criteria firstNameCrt = getSession().createCriteria(UserInfo.class, "ui");
		firstNameCrt.add(Restrictions.eq("ui.status", status.getValue()));
		firstNameCrt.setProjection(Projections.property("ui.firstname"));
		firstNameCrt.addOrder(Order.asc("ui.firstname"));		
		firstNameCrt.add(Restrictions.ilike("ui.firstname", name+"%"));		
		firstNameCrt.setMaxResults(4);
		if(role != null) {
			firstNameCrt.createAlias("ui.user", "user");
			firstNameCrt.add(Restrictions.eq("user.role.id", role.getValue()));
		}		
		infoList = firstNameCrt.list();
		
			/*
			 * Last Name Records
			 */
		Criteria lastNameCrt = getSession().createCriteria(UserInfo.class, "ui");
		lastNameCrt.add(Restrictions.eq("ui.status", status.getValue()));
		lastNameCrt.setProjection(Projections.property("ui.lastname"));
		lastNameCrt.addOrder(Order.asc("ui.lastname"));		
		lastNameCrt.add(Restrictions.ilike("ui.lastname", name+"%"));		
		lastNameCrt.setMaxResults(4);
		if(role != null) {
			lastNameCrt.createAlias("ui.user", "user");
			lastNameCrt.add(Restrictions.eq("user.role.id", role.getValue()));
		}
		infoList.addAll(lastNameCrt.list());
		
			/*
			 * Email Records
			 */
		Criteria emailCrt = getSession().createCriteria(UserInfo.class, "ui");
		emailCrt.add(Restrictions.eq("ui.status", status.getValue()));
		emailCrt.setProjection(Projections.property("ui.email"));
		emailCrt.addOrder(Order.asc("ui.email"));		
		emailCrt.add(Restrictions.ilike("ui.email", name+"%"));		
		emailCrt.setMaxResults(4);
		if(role != null) {
			emailCrt.createAlias("ui.user", "user");
			emailCrt.add(Restrictions.eq("user.role.id", role.getValue()));
		}
		infoList.addAll(emailCrt.list());
		
			/*
			 * User Name Records
			 */
		Criteria loginCrt = getSession().createCriteria(User.class, "u");
		loginCrt.add(Restrictions.eq("u.status", status.getValue()));
		loginCrt.setProjection(Projections.property("u.userName"));
		loginCrt.addOrder(Order.asc("u.userName"));		
		loginCrt.add(Restrictions.ilike("u.userName", name+"%"));		
		loginCrt.setMaxResults(4);
		if(role != null) {			
			loginCrt.add(Restrictions.eq("u.role.id", role.getValue()));
		}
		infoList.addAll(loginCrt.list());
		
		Set<String> set = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		set.addAll(infoList);
		
		returnList.addAll(set);
		return returnList;
	}
	
	@Override
	public List<User> getUsers_TopicId_Name(Role role, Status status, String name) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createAlias("role", "role");
		criteria.createAlias("userinfo", "userinfo");
		criteria.add(Restrictions.eq("role.id", role.getId()));
		criteria.add(Restrictions.eq("status", status.getValue()));
		if (!StringUtils.isEmpty(name)) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.ilike("userinfo.firstname", name+"%"));
			or.add(Restrictions.ilike("userinfo.lastname", name+"%"));
			criteria.add(or);
		}
		criteria.addOrder(Order.asc("userinfo.lastname"));
		criteria.addOrder(Order.asc("userinfo.firstname"));
		return criteria.list();
	}

	@Override
	public List<Object[]> getExperts_Editors(Status status, String sortColumn, String sortOrder, String name,
			int start, int pagesize, Long editorId) {
		String clause = "";
		if(sortColumn == null) {
			sortColumn = "tea.expert.userinfo.firstname";
			sortOrder = "ASC";
		}
		
		if(StringUtils.isNotEmpty(name)) {
			clause = "AND ( LOWER(tea.expert.userinfo.firstname) LIKE LOWER(:name) OR LOWER(tea.expert.userinfo.lastname) LIKE LOWER(:name)" +
					" OR LOWER(tea.expert.userName) LIKE LOWER(:name) OR LOWER(tea.expert.userinfo.email) LIKE LOWER(:name) ) ";
		}
			
		String hql = "SELECT DISTINCT tea.expert.id, tea.expert.userinfo.firstname, tea.expert.userinfo.lastname, tea.expert.userName, tea.expert.userinfo.email "
				+ "FROM TopicExpertAssignment tea WHERE tea.status = :status AND tea.assignedBy.id = :editor_id "
				+ clause
				+ "ORDER BY "+sortColumn+" "+sortOrder;
		
		Query query = getSession().createQuery(hql);
		query.setParameter("status", status.getValue());
		query.setParameter("editor_id", editorId);
		if(StringUtils.isNotEmpty(name)) {
			query.setParameter("name", name);
		}
		query.setFirstResult(start);
		query.setMaxResults(pagesize);
		
		return query.list();
	}

	@Override
	public int getTotalExperts_Editor_Id(String name, long editorId, Status status) {
		int count = 0;
		String clause = "";
		if(StringUtils.isNotEmpty(name)) {
			clause = "AND ( LOWER(tea.expert.userinfo.firstname) LIKE LOWER(:name) OR LOWER(tea.expert.userinfo.lastname) LIKE LOWER(:name)" +
					" OR LOWER(tea.expert.userName) LIKE LOWER(:name) OR LOWER(tea.expert.userinfo.email) LIKE LOWER(:name) ) ";
		}
		String hql = "SELECT COUNT(DISTINCT tea.expert.id) FROM TopicExpertAssignment tea "
				+ "WHERE tea.status = :status AND tea.assignedBy.id = :editor_id "+clause;
		
		Query query = getSession().createQuery(hql);
		query.setParameter("status", status.getValue());
		query.setParameter("editor_id", editorId);
		if(StringUtils.isNotEmpty(name)) {
			query.setParameter("name", name);
		}
		count = query.uniqueResult()!=null ? ((Long)query.uniqueResult()).intValue() : 0;
		return count;
	}
	
	@Override
	public List<String> autoSuggestExpertByNameAndEditor(String name, Long editorId, Status status) {
		List<String> infoList = null;
		List<String> returnList = null;
		String hql = null;
		Query query = null;
			/*
			 * First Name Records
			 */
		hql = "SELECT DISTINCT tea.expert.userinfo.firstname FROM TopicExpertAssignment tea WHERE tea.status = :status AND tea.assignedBy.id = :editor_id " +
				" AND LOWER(tea.expert.userinfo.firstname) LIKE LOWER(:name) ORDER BY tea.expert.userinfo.firstname";		
		query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("status", status.getValue());
		query.setParameter("editor_id", editorId);
		query.setParameter("name", name);
		query.setMaxResults(4);
		infoList = query.list();
		
			/*
			 * Last Name Records
			 */
		query = null;
		hql = "SELECT DISTINCT tea.expert.userinfo.lastname FROM TopicExpertAssignment tea WHERE tea.status = :status AND tea.assignedBy.id = :editor_id " +
				" AND LOWER(tea.expert.userinfo.lastname) LIKE LOWER(:name) ORDER BY tea.expert.userinfo.lastname";		
		query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("status", status.getValue());
		query.setParameter("editor_id", editorId);
		query.setParameter("name", name);
		query.setMaxResults(4);
		infoList.addAll(query.list());
		
			/*
			 * Email Records
			 */
		query = null;
		hql = "SELECT DISTINCT tea.expert.userinfo.email FROM TopicExpertAssignment tea WHERE tea.status = :status AND tea.assignedBy.id = :editor_id " +
				" AND LOWER(tea.expert.userinfo.email) LIKE LOWER(:name) ORDER BY tea.expert.userinfo.email";		
		query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("status", status.getValue());
		query.setParameter("editor_id", editorId);
		query.setParameter("name", name);
		query.setMaxResults(4);
		infoList.addAll(query.list());
		
			/*
			 * User Name Records
			 */
		query = null;
		hql = "SELECT DISTINCT tea.expert.userName FROM TopicExpertAssignment tea WHERE tea.status = :status AND tea.assignedBy.id = :editor_id " +
				" AND LOWER(tea.expert.userName) LIKE LOWER(:name) ORDER BY tea.expert.userName";		
		query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("status", status.getValue());
		query.setParameter("editor_id", editorId);
		query.setParameter("name", name);
		query.setMaxResults(4);
		infoList.addAll(query.list());
		
		Set<String> set = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		set.addAll(infoList);
		
		returnList = new ArrayList<>();
		returnList.addAll(set);
		return returnList;
	}

}
