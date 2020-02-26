package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.models.RatingCriteria;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */
public interface RatingCriteriaService {
	
	public List<RatingCriteria> findAllParentCriteria();
}
