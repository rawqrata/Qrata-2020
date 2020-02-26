package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.User;


/**
 * @author kamal
 *
 */
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
	public String save(RatingCriteria ratingCriteria);
	/**
	 * @param ratingCriteria
	 * @return 
	 */
	public boolean update(RatingCriteria ratingCriteria);
	/**
	 * @param ratingCriteriaList
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
	
	public boolean addCriteriaCategory(String name , User user);
	
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
