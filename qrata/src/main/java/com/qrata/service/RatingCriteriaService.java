package com.qrata.service;

import java.util.List;

import com.qrata.models.RatingCriteria;
import com.qrata.models.User;

public interface RatingCriteriaService {
	
	/**
	 * @param id
	 * @return
	 */
	public RatingCriteria getRatingCriteria_Id(int id);
	/**
	 * @param ratingCriteria
	 * @return 
	 */
	public boolean delete(RatingCriteria ratingCriteria);
	/**
	 * @param ratingCriteria
	 * @return
	 */
	public int saveOrUpdate(RatingCriteria ratingCriteria);
	/**
	 * @param ratingCriteria
	 * @return 
	 */
	public boolean updateBulk(List<RatingCriteria> ratingCriteriaList);
	
	public List<RatingCriteria> findAll();

	/**
	 * @param domain
	 */
	public boolean addCriteria(String name);
	public List<RatingCriteria> searchCriteria_Name(String name);
	
	public boolean add(RatingCriteria criteria);
	
	
	public List<RatingCriteria> getRatingCriteria_Name(String name, int id);
	public List<RatingCriteria> getRatingCriteriaCategory(int id);
	
	/**
	 * @param id
	 */
	public boolean saveCriteriaCategory(String id , String criteriaCategoryName);
	
	public boolean addCriteriaCategory(String criteriaCategory , User user);
	
	public List<RatingCriteria> getRatingCriteria_ratingCriteriaCategoryId(
			String id);
	
	public List<RatingCriteria> findAllParentCriteria();
	
	public boolean saveRatingCriteria(String id,
			String ratingCriteriaName,String description,int  priority,User user);
	
	public List<RatingCriteria> getRatingCriteria_Priority(int priority, int parentId, int id);
	public List<RatingCriteria> getRatingCriteriaCategories_Name(String name); 

	public RatingCriteria getRatingCriteria_Name_Id(String name, int id);
	
	public String getCriteriaDetails_Id(int criteriaId);
	
	public List<Integer> getRatingsCriteriaIDs(); 

}
