package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Constants.RatingCriteriaType;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.RatingCriteria;


public interface RatingCriteriaDao extends IBaseDao<RatingCriteria> {

	public List<RatingCriteria> findAll(RatingCriteriaType ratingCriteriaType);	
	
	public List<RatingCriteria> searchCriteria_Name(String name, Status status);
	
	public List<RatingCriteria> getRatingCriteria_Name(String name, int id);

	public List<RatingCriteria> getRatingCriteriaCategory(int id);

	public List<RatingCriteria> getRatingCriteria_ratingCriteriaCategoryId(
			int ratingCriteriaCategoryId);

	public List<RatingCriteria> findAllParentCriteria(); 

	public List<RatingCriteria> getRatingCriteria_Priority(int priority, int parentId, int id);

	public List<RatingCriteria> getRatingCriteriaCategories_Name(String name);

	public RatingCriteria getRatingCriteria_Name_Id(String name, int id);

	public String getDescription_CriteriaId(int criteriaId, Status active);

	public List<Integer> getRatingsCriteriaIDs();	 
	 

}
