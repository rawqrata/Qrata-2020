package com.insonix.qrata.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.CategoryDao;
import com.insonix.qrata.models.Category;

/**
 * @author Ramandeep Singh
 * @Since Feb 6, 2013
 */
@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getActiveDomains() {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.add(
				Restrictions.eq("categoryType",
						Constants.CategoryType.DOMAIN.getValue()))
				.add(Restrictions.eq("status", Status.ACTIVE.getValue()))
				.addOrder(Order.asc("name"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories_domainId(int domainId) {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.createAlias("parentCategory", "parentCategory");
		crit.add(Restrictions.eq("parentCategory.id", domainId)).add(Restrictions.eq("status", Status.ACTIVE.getValue()));
		crit.addOrder(Order.asc("name"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getSubCategories_CategoryId(int categoryId) {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.createAlias("parentCategory", "parentCategory");
		crit.add(Restrictions.eq("parentCategory.id", categoryId)).add(Restrictions.eq("status", Status.ACTIVE.getValue()));
		crit.addOrder(Order.asc("name"));
		return crit.list();
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.dao.CategoryDao#getCategories(com.insonix.qrata.constants.Status)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(Status status) {
		Criteria criteria=getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("status",status.getValue()));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}
	
	/* (non-Javadoc)
	 * @see com.insonix.qrata.dao.CategoryDao#getCategories(com.insonix.qrata.constants.Status)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(Status status, Constants.CategoryType categoryType) {
		Criteria criteria=getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("status",status.getValue()));
		criteria.add(Restrictions.eq("categoryType", categoryType.getValue()));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}
	

	@Override
	public Category getCategory(String uuid) {
		Criteria criteria=getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (Category) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> searchCategories_Name(String name, Status status,
			CategoryType type) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("status", status.getValue()));
		criteria.add(Restrictions.eq("categoryType", type.getValue()));
		criteria.add(Restrictions.ilike("name", name+"%"));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> searchDomains_Name(String name, Status status,
			CategoryType type) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("status", status.getValue()));
		criteria.add(Restrictions.eq("categoryType", type.getValue()));
		criteria.add(Restrictions.ilike("name", name+"%"));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategory_Name(String name,Status status,CategoryType type){
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("status",status.getValue()));
		criteria.add(Restrictions.eq("categoryType",type.getValue()));
		criteria.add(Restrictions.eq("name",name).ignoreCase());
		return criteria.list();
	}

	@Override
	public Category getCategory_Name_Id(String name, int id) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("name", name).ignoreCase());
		criteria.add(Restrictions.eq("parentCategory.id",id));
		return (Category)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> searchCategories_Name_ParentID(String name,int parentId, Status active, CategoryType category) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("parentCategory.id", parentId));
		criteria.add(Restrictions.eq("status", active.getValue()));
		criteria.add(Restrictions.eq("categoryType", category.getValue()));
		criteria.add(Restrictions.ilike("name", name+"%"));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}

	@Override
	public int getTotalActiveCategory_Type(Status active, String name,CategoryType categoryType, Integer parentId) {
		int count = 0;
		Criteria crt = getSession().createCriteria(Category.class);
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.eq("categoryType", categoryType.getValue()));
		crt.add(Restrictions.ilike("name", name));
		
		if(parentId == null){
			crt.add(Restrictions.isNull("parentCategory"));
		}else{
			crt.add(Restrictions.eq("parentCategory.id", parentId));
		}
		
		crt.setProjection(Projections.rowCount());
		count = crt.uniqueResult()!=null ? (Integer)crt.uniqueResult() : 0;
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getActiveCategory_Type(Status active, String name, CategoryType categoryType, 
			Integer parentId, int pagesize, String sortField, String sortOrder, int start) {
		Criteria crt = getSession().createCriteria(Category.class);
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.eq("categoryType", categoryType.getValue()));
		crt.add(Restrictions.ilike("name", name));
		crt.setMaxResults(pagesize);
		crt.setFirstResult(start);
		
		if(parentId == null){
			crt.add(Restrictions.isNull("parentCategory"));
		}else{
			crt.add(Restrictions.eq("parentCategory.id", parentId));
		}
		
		if(!StringUtils.isEmpty(sortField)) {
			if(sortOrder.equals("asc"))
				crt.addOrder(Order.asc(sortField));
			else
				crt.addOrder(Order.desc(sortField));
		} else {
			crt.addOrder(Order.asc("name"));
		}
		
		return crt.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllActiveCategories_Type(Status active,String name, CategoryType category, int pagesize, String sortField,
			String sortOrder, int start) {
		Criteria crt = getSession().createCriteria(Category.class);
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.eq("categoryType", category.getValue()));
		crt.add(Restrictions.ilike("name", name));
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
	public int getTotalAllActiveCategories_Type(Status active,String name, CategoryType category) {
		int count = 0;
		Criteria crt = getSession().createCriteria(Category.class);
		crt.add(Restrictions.eq("status", active.getValue()));
		crt.add(Restrictions.eq("categoryType", category.getValue()));
		crt.add(Restrictions.ilike("name", name));
		
		crt.setProjection(Projections.rowCount());
		count = crt.uniqueResult()!=null ? (Integer)crt.uniqueResult() : 0;
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCategoryNamesForAutoSuggest_Name(String name, Status status, 
			CategoryType type, Integer parentId) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.setProjection(Projections.property("name"));
		criteria.add(Restrictions.eq("status", status.getValue()));
		criteria.add(Restrictions.eq("categoryType", type.getValue()));
		criteria.add(Restrictions.ilike("name", name+"%"));
		criteria.addOrder(Order.asc("name"));
		if(parentId != 0) {
			criteria.add(Restrictions.eq("parentCategory.id", parentId));
		}
		criteria.setMaxResults(10);
		return criteria.list();
	}
	
}
