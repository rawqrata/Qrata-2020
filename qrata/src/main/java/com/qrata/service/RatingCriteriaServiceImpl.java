package com.qrata.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.qrata.enums.Constants;
import com.qrata.enums.Status;
import com.qrata.models.Category;
import com.qrata.models.RatingCriteria;
import com.qrata.models.User;
import com.qrata.respository.RatingCriteriaRepository;
import com.qrata.utility.SortingUtility;

@Service
public class RatingCriteriaServiceImpl implements RatingCriteriaService{

	@Autowired
	private RatingCriteriaRepository ratingCriteriaRepository;

    @PersistenceContext
    private EntityManager entityManager;
	
	private SortingUtility<RatingCriteria> customSort  = new SortingUtility<RatingCriteria>();
	
	
	
	@Override
	public RatingCriteria getRatingCriteria_Id(int id) {
		return ratingCriteriaRepository.findById(id).get();
	}

	@Override
	public boolean delete(RatingCriteria ratingCriteria) {
		try{
			ratingCriteriaRepository.delete(ratingCriteria);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	@Override
	public int saveOrUpdate(RatingCriteria ratingCriteria) {
		return ratingCriteriaRepository.save(ratingCriteria).getId();
	}

	@Override
	public boolean updateBulk(List<RatingCriteria> ratingCriteriaList) {
		try{
			ratingCriteriaRepository.saveAll(ratingCriteriaList);
			return true;
		}catch(Exception ex){
		   ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RatingCriteria> findAll() {
		return ratingCriteriaRepository.findAllByCondition(Status.ACTIVE.getValue(),Constants.RatingCriteriaType.RATINGCRITERIA.getValue());
	}

	@Override
	public boolean addCriteria(String name) {
		return false;
	}

	@Override
	public List<RatingCriteria> searchCriteria_Name(String name) {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaRepository.searchCriteria_Name(Status.ACTIVE.getValue(),name);
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}

	@Override
	public boolean add(RatingCriteria criteria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RatingCriteria> getRatingCriteria_Name(String name, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RatingCriteria> getRatingCriteriaCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveCriteriaCategory(String id, String criteriaCategoryName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCriteriaCategory(String criteriaCategory, User user) {
		RatingCriteria criteria = new RatingCriteria();
		criteria.setName(criteriaCategory.trim());
		criteria.setCreatedBy(user.getId());
		criteria.setCriteriaType(Constants.RatingCriteriaType.RATINGCRITERIACATEGORY.getValue());
		criteria.setStatus(Status.ACTIVE.getValue());
		try{
			ratingCriteriaRepository.save(criteria);
			return true;
		} catch(DataAccessException ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<RatingCriteria> getRatingCriteria_ratingCriteriaCategoryId(String id) {
		int ratingCriteriaCategoryId = Integer.parseInt(id);
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaRepository.getRatingCriteria_ratingCriteriaCategoryId(Status.ACTIVE.getValue(), ratingCriteriaCategoryId);
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}

	@Override
	public List<RatingCriteria> findAllParentCriteria() {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaRepository.findAll();
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;	
	}

	@Override
	public boolean saveRatingCriteria(String id, String ratingCriteriaName, String description, int priority,
			User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RatingCriteria> getRatingCriteria_Priority(int priority, int parentId, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RatingCriteria> getRatingCriteriaCategories_Name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RatingCriteria getRatingCriteria_Name_Id(String name, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCriteriaDetails_Id(int criteriaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getRatingsCriteriaIDs() {
		// TODO Auto-generated method stub
		return null;
	}

}
