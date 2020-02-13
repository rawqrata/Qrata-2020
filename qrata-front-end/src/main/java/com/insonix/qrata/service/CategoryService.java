package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.entity.CategoryForm;
import com.insonix.qrata.models.Category;

/**
 * @author Gurminder Singh
 *
 * @date 18-Jun-2013
 */
public interface CategoryService {
	public List<Category> getActiveDomains();
	public List<Category> listCategories(int domainId);
	public List<Category> listSubCategories(int categoryId);
	public int getTotalActiveCategory_Type(String name,CategoryType domain,Integer parentId);
	public List<CategoryForm> getActiveCategory_Type(String name,CategoryType categoryType, Integer parentId, int pagesize, String sortField, 
			String sortOrder,int start);
}
