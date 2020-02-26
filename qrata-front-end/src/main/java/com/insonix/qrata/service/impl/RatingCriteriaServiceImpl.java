package com.insonix.qrata.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.dao.RatingCriteriaDao;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.service.RatingCriteriaService;

/**
 * @author Gurminder Singh
 * 
 * @date 19-Jun-2013
 */

@Service("criteriaService")
public class RatingCriteriaServiceImpl implements RatingCriteriaService {

	@Autowired
	RatingCriteriaDao ratingCriteriaDao;
	CustomSortComparator<RatingCriteria> customSort = new CustomSortComparator<RatingCriteria>();

	
	@Override
	public List<RatingCriteria> findAllParentCriteria() {
		List<RatingCriteria> ratingCriteriaList = ratingCriteriaDao
				.findAllParentCriteria();
		Collections.sort(ratingCriteriaList, customSort);
		return ratingCriteriaList;
	}
}
