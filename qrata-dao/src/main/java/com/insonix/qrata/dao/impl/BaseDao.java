package com.insonix.qrata.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.IBaseDao;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.RatingCriteria;

/**
 * @author raman
 *
 */
public abstract class BaseDao<T>  extends HibernateDaoSupport implements IBaseDao<T> {
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
	
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T get(Serializable id) {
		T o = (T) getHibernateTemplate().get(entityClass, id);
		return o;
	}

		
	public void delete(T domain) {
		
		getHibernateTemplate().delete(domain);
		getSession().flush();
	
	}

	public String save(T domain) {
		
		Serializable id = getHibernateTemplate().save(domain);
		return id.toString();
	}

	
	public void update(T domain) {
		if (getSession().isOpen()) {
			getSession().clear();
		}
		getHibernateTemplate().update(domain);
		getHibernateTemplate().flush();
	
	}
	
	public void saveBulk(List<T> domain) {
		getHibernateTemplate().saveOrUpdateAll(domain);
	}
	
	public void updateBulk(List<T> domain){
	    getHibernateTemplate().saveOrUpdateAll(domain);
	}

	public List<Category> getCategories(String name, Status status,
			CategoryType categoryType) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RatingCriteria> searchCriteria_Name(String name, Status status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> getCategory_Name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RatingCriteria> getRatingCriteria_ratingCriteriaCategoryId(
			int ratingCriteriaCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
