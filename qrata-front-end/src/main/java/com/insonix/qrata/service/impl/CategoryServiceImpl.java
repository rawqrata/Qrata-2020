package com.insonix.qrata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.dao.CategoryDao;
import com.insonix.qrata.entity.CategoryForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.service.CategoryService;

/**
 * @author Gurminder Singh
 *
 * @date 18-Jun-2013
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired CategoryDao categoryDao;

	@Override
	public List<Category> getActiveDomains() {
		return categoryDao.getActiveDomains();
	}
	
	@Override
	public List<Category> listCategories(int domainId) {
		return categoryDao.getCategories_domainId(domainId);
	}
	
	@Override
	public List<Category> listSubCategories(int categoryId){
		return categoryDao.getSubCategories_CategoryId(categoryId);
	}
	
	@Override
	public int getTotalActiveCategory_Type(String name, CategoryType categoryType,Integer parentId) {
		if(name == null){
			name = "%";
		}else{
			name = name + "%";
		}
		return categoryDao.getTotalActiveCategory_Type(Status.ACTIVE,name,categoryType,parentId);
	}
	
	@Override
	public List<CategoryForm> getActiveCategory_Type(String name,CategoryType categoryType, Integer parentId, int pagesize, String sortField, 
			String sortOrder,int start) {
		if(name == null){
			name = "%";
		}else{
			name = name + "%";
		}
		List<Category> categoryList = categoryDao.getActiveCategory_Type(Status.ACTIVE,name,categoryType,parentId,pagesize,sortField,sortOrder,start);
		List<CategoryForm> categoryFormList=new ArrayList<>();
		for(Category category : categoryList){
			CategoryForm form=new CategoryForm();
			form.setId(category.getId());
			form.setName(category.getName());
			form.setUuid(category.getUuid());
			categoryFormList.add(form);
		}
		return categoryFormList;
	}
	
	
}
