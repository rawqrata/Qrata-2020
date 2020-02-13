package com.insonix.qrata.service.impl;

import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.RatingCriteriaDao;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.RatingCriteriaService;

/**
 * @author kamal
 *
 */
@Service("RatingCriteriaService")
public class RatingCriteriaServiceImpl implements RatingCriteriaService {
	@Autowired
	RatingCriteriaDao ratingCriteriaDao;

	CustomSortComparator<RatingCriteria> customSort = new CustomSortComparator<RatingCriteria>();
	
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RatingCriteriaService#getRatingCriteria_Id(int)
	 */
	/*
	 * this method is used to get RatingCrieteria by its id 
	 * */
	@Override
	public RatingCriteria getRatingCriteria_Id(int id) {		
		return ratingCriteriaDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RatingCriteriaService#delete(com.insonix.qrata.models.RatingCriteria)
	 */
	@Override
	public boolean delete(RatingCriteria ratingCriteria) {
		try{
			System.out.println("In Service " +ratingCriteria);
			ratingCriteriaDao.delete(ratingCriteria);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RatingCriteriaService#save(com.insonix.qrata.models.RatingCriteria)
	 */
	@Override
	public String save(RatingCriteria ratingCriteria) {
		String id = ratingCriteriaDao.save(ratingCriteria);
		return id;
	}
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RatingCriteriaService#update(com.insonix.qrata.models.RatingCriteria)
	 */

	@Override
	public boolean update(RatingCriteria ratingCriteria) {
		try{
			ratingCriteriaDao.update(ratingCriteria);
			return true;

		}catch(DataAccessException ex){
			return false;
		}		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RatingCriteriaService#updateBulk(java.util.List)
	 */
	@Override
	public boolean updateBulk(List<RatingCriteria> ratingCriteriaList) {
		try{
			ratingCriteriaDao.updateBulk(ratingCriteriaList);
			return true;
		}
		catch(DataAccessException ex){
			return false;
		}		
	}
	
	@Override
	public List<RatingCriteria> findAll() {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.findAll(Constants.RatingCriteriaType.RATINGCRITERIA);
//		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}

	@Override
	public boolean addCriteriaCategory(String criteriaCategory , User user) {
		RatingCriteria criteria = new RatingCriteria();
		criteria.setName(criteriaCategory.trim());
		criteria.setCreatedBy(user.getId());
		criteria.setCriteriaType(Constants.RatingCriteriaType.RATINGCRITERIACATEGORY.getValue());
		criteria.setStatus(Status.ACTIVE.getValue());
		try{
			ratingCriteriaDao.save(criteria);
			return true;
		} catch(DataAccessException ex){
			ex.printStackTrace();
			return false;
		}
	}

	

	@Override
	public List<RatingCriteria> searchCriteria_Name(String name) {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.searchCriteria_Name(name, Status.ACTIVE);
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}

	@Override
	public boolean add(RatingCriteria criteria) {
		return false;
	}

	@Override
	public List<RatingCriteria> getRatingCriteria_Name(String name, int id) {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.getRatingCriteria_Name(name, id);
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}

	
	@Override
	public List<RatingCriteria> getRatingCriteriaCategory(int id) {
		
			List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.getRatingCriteriaCategory(id);
			Collections.sort(ratingCriteriaList , customSort);
			return ratingCriteriaList;
		}
	@Override
	public boolean saveCriteriaCategory(String id , String criteriaCategoryName) {
		
		int ratingCriteriaId = Integer.parseInt(id);
		RatingCriteria criteria = new RatingCriteria();
		//Categories parentCategory = new Categories();
		RatingCriteria parentRatingCriteria = ratingCriteriaDao.get(ratingCriteriaId);
		criteria.setCriteriaType(Constants.RatingCriteriaType. RATINGCRITERIA.getValue());
		criteria.setStatus(Status.ACTIVE.getValue());
		criteria.setName(criteriaCategoryName);
		criteria.setParentRatingCriteria(parentRatingCriteria);
		try{
			ratingCriteriaDao.save(criteria);
			return true;
		}
		catch(DataAccessException ex){
			return false;
		}
	}


	
	public List<RatingCriteria> getRatingCriteriaCategory_ratingCriteriaId(
			String id) {
		
			/*int ratingCriteriaId = Integer.parseInt(id);
			List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao. getRatingCriteriaCategory_ratingCriteriaId(ratingCriteriaId);
			Collections.sort(ratingCriteriaList,customSort);*/
			return null;
	
	}

	

	@Override
	public List<RatingCriteria> getRatingCriteria_ratingCriteriaCategoryId(String id) {
		int ratingCriteriaCategoryId = Integer.parseInt(id);
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.getRatingCriteria_ratingCriteriaCategoryId(ratingCriteriaCategoryId);
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}

	@Override
	public List<RatingCriteria> findAllParentCriteria() {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.findAllParentCriteria();
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;	
	}
	
	@Override
	public boolean saveRatingCriteria(String id, String ratingCriteriaName,String description,int priority,User user) {
		int ratingCriteriaCategoryId =Integer.parseInt(id);
		RatingCriteria criteria = new RatingCriteria();
		
		RatingCriteria parentRatingCriteria = ratingCriteriaDao.get(ratingCriteriaCategoryId);
		criteria.setCriteriaType(Constants.RatingCriteriaType.RATINGCRITERIA.getValue());
		criteria.setStatus(Status.ACTIVE.getValue());
		criteria.setName(ratingCriteriaName.trim());
		criteria.setDescription(description);
		criteria.setPriority(priority);
		//criteria.setCreatedBy(user.getId());
		criteria.setParentRatingCriteria(parentRatingCriteria);
		
		try{
			ratingCriteriaDao.save(criteria);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}


	@Override
	public List<RatingCriteria> getRatingCriteria_Priority(int priority, int parentId, int id) {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.getRatingCriteria_Priority(priority, parentId, id);
		return ratingCriteriaList;		
	}

	@Override
	public boolean addCriteria(String name) {
		return false;
	}

	@Override
	public List<RatingCriteria> getRatingCriteriaCategories_Name(String name) {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao.getRatingCriteriaCategories_Name(name);
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}

	@Override
	public RatingCriteria getRatingCriteria_Name_Id(String name, int id) {
		RatingCriteria ratingCriteria = ratingCriteriaDao.getRatingCriteria_Name_Id(name,id);
		return ratingCriteria;
	}

	@Override
	public String getCriteriaDetails_Id(int criteriaId) {
		JSONObject responseObj = new JSONObject();
		try {
			JSONObject obj = new JSONObject();
			RatingCriteria ratingCriteria = getRatingCriteria_Id(criteriaId);
			obj.put("name", ratingCriteria.getName());
			obj.put("description", ratingCriteria.getDescription());
			responseObj.put("obj", obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}

	@Override
	public List<Integer> getRatingsCriteriaIDs() {
		return ratingCriteriaDao.getRatingsCriteriaIDs();
	}

}
