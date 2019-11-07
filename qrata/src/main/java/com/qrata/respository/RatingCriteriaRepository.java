package com.qrata.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qrata.models.Category;
import com.qrata.models.RatingCriteria;

public interface RatingCriteriaRepository extends JpaRepository<RatingCriteria,Integer> {
	
	/*
	 * 	Criteria crit = getSession().createCriteria(RatingCriteria.class);
		crit.add(Restrictions.eq("parentRatingCriteria.id", ratingCriteriaCategoryId))
			.add(Restrictions.eq("status", Status.ACTIVE.getValue()));
	}
	
	}
	 */		
	
	@Query("select rc from RatingCriteria rc where rc.status = :status and rc.criteriaType = :type order by rc.name asc")
	public abstract List<RatingCriteria>  findAllByCondition(@Param("status") Short paramdstatus,@Param("type") Short paramtype);
	
	
	@Query("select rc from RatingCriteria rc where rc.status = :status and rc.name like :name%")
	public abstract List<RatingCriteria> searchCriteria_Name(@Param("status") Short paramdstatus,@Param("name") String name);
	
	@Query("select rc from RatingCriteria rc where rc.status = :status and rc.parentRatingCriteria.id = :id")
	public abstract List<RatingCriteria> getRatingCriteria_ratingCriteriaCategoryId(@Param("status") Short paramdstatus,@Param("id") Integer id);

}
