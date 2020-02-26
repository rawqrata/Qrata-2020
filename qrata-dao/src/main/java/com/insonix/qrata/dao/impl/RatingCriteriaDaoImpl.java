package com.insonix.qrata.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.RatingCriteriaType;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.RatingCriteriaDao;
import com.insonix.qrata.models.RatingCriteria;



@Repository("RatingCriteriaDao")
public class RatingCriteriaDaoImpl extends BaseDao<RatingCriteria> implements RatingCriteriaDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> findAll(RatingCriteriaType ratingCriteriaType) {		
		List<RatingCriteria> criteriaList = getSession().createCriteria(RatingCriteria.class)
				.add(Restrictions.eq("status", Status.ACTIVE.getValue())).add(Restrictions.eq("criteriaType", ratingCriteriaType.getValue()))
				.addOrder(Order.asc("name")).list();		
		return criteriaList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> searchCriteria_Name(String name, Status status) {
		Criteria criteria = getSession().createCriteria(RatingCriteria.class);
		criteria.add(Restrictions.eq("status", status.getValue()));
		criteria.add(Restrictions.ilike("name", name+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> getRatingCriteria_Name(String name, int id) {
		Criteria criteria =getSession().createCriteria(RatingCriteria.class);
		criteria.add(Restrictions.eq("name",name).ignoreCase());
		if(id != 0) {
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		}
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> getRatingCriteriaCategory(int id) {		
			Criteria criteria = getSession().createCriteria(RatingCriteria.class);
			criteria.createAlias("parentRatingCriteria", "parentRatingCriteria");
			criteria.add(Restrictions.eq("parentRatingCriteria.id", id))
					.add(Restrictions.eq("status", Status.ACTIVE.getValue()))
					.add(Restrictions.eq(" criteriaType",
							Constants.RatingCriteriaType. RATINGCRITERIA.getValue()))
			.addOrder(Order.asc("name"));
			return criteria.list();
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> getRatingCriteria_ratingCriteriaCategoryId(int ratingCriteriaCategoryId) {
		Criteria crit = getSession().createCriteria(RatingCriteria.class);
		crit.add(Restrictions.eq("parentRatingCriteria.id", ratingCriteriaCategoryId))
			.add(Restrictions.eq("status", Status.ACTIVE.getValue()));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> findAllParentCriteria() {		
		List<RatingCriteria> criterias = getSession().createCriteria(RatingCriteria.class)
				.add(Restrictions.isNull("parentRatingCriteria")).list();
		return criterias;		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> getRatingCriteria_Priority(int priority, int parentId, int id) {
		Criteria criteria = getSession().createCriteria(RatingCriteria.class);
		criteria.add(Restrictions.eq("priority", priority));
		criteria.createAlias("parentRatingCriteria", "parent");
		criteria.add(Restrictions.eq("parent.id", parentId));
		if(id != 0) {
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RatingCriteria> getRatingCriteriaCategories_Name(String name) {
		Criteria criteria = getSession().createCriteria(RatingCriteria.class);
		criteria.add(Restrictions.eq("name", name).ignoreCase()).addOrder(Order.asc("name"));
		return criteria.list();
	}

	
	@Override
	public RatingCriteria getRatingCriteria_Name_Id(String name, int id) {
		Criteria criteria = getSession().createCriteria(RatingCriteria.class);
		criteria.add(Restrictions.eq("name", name).ignoreCase());
		criteria.add(Restrictions.eq("parentRatingCriteria.id", id));
		return (RatingCriteria)criteria.uniqueResult();
	}

	@Override
	public String getDescription_CriteriaId(int criteriaId, Status active) {
		Query query = getSession().createQuery("SELECT rc.description FROM RatingCriteria rc WHERE rc.id = :criteria_id AND status = :status");
		query.setCacheable(true);
		query.setParameter("criteria_id", criteriaId);
		query.setParameter("status", active.getValue());
		return (String) query.uniqueResult();
	}

	@Override
	public List<Integer> getRatingsCriteriaIDs() {
		Criteria ctr = getSession().createCriteria(RatingCriteria.class);
		ctr.setProjection(Projections.property("id"));
		ctr.setResultTransformer(Transformers.aliasToBean(Integer.class));
		return ctr.list();
	}
	
}
