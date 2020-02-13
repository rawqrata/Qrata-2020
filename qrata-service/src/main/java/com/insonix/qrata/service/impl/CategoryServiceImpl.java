package com.insonix.qrata.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.CategoryDao;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.service.CategoryService;


/**
 * @author kamal
 *
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	CustomSortComparator<Category> customSort  = new CustomSortComparator<Category>();
	
	
	/*
	 * this method is used to get category by id
	 * */
	@Override
	public Category getCategory_Id(int id) {
	return categoryDao.get(id);
	}

	/*
	 * this method is used to delete category 
	 * */
	@Override
	public boolean delete(Category category) {
		//category.setStatus(Status.DELETED.getValue());
		try{
			categoryDao.delete(category);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}
		/*
	 * this method is used to save category 
	 * */
	@Override
	public String save(Category category) {
		String id = categoryDao.save(category);
		return id;
	}

	/*
	 * this method is used to update category 
	 * */
	@Override
	public boolean update(Category category) {
		try{
			categoryDao.update(category); 
			return true;	
		}catch(DataAccessException ex){
			return false;
		}
		 
	}

	/*
	 * this method is used to update list of categories 
	 * */
	@Override
	public boolean updateBulk(List<Category> categories) {
		try{
			categoryDao.updateBulk(categories);
			return true;	
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	@Override
	public boolean addDomain(String domain) {
		Category category = new Category();
		category.setName(domain.trim());
		category.setCategoryType(Constants.CategoryType.DOMAIN.getValue());
		category.setStatus(Status.ACTIVE.getValue());
		try{
			categoryDao.save(category);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.CategoryService#findAll_Id_Status()
	 */
	@Override
	public List<Category> getActiveDomains() {
		List<Category> usersList = categoryDao.getActiveDomains();
		Collections.sort(usersList,customSort);
		return usersList;
	}

	@Override
	public List<Category> getCategories_domainId(String id) {
		int domainId = Integer.parseInt(id);
		List<Category> usersList = categoryDao.getCategories_domainId(domainId);
		Collections.sort(usersList,customSort);
		return usersList;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.CategoryService#saveCategory(java.lang.String)
	 */
	@Override
	public boolean saveCategory(String id , String categoryName, long createdBy) {
		
		int domainId = Integer.parseInt(id);
		Category category = new Category();
		//Categories parentCategory = new Categories();
		Category parentCategory = categoryDao.get(domainId);
		category.setCategoryType(Constants.CategoryType.CATEGORY.getValue());
		category.setStatus(Status.ACTIVE.getValue());
		category.setName(categoryName);
		category.setParentCategory(parentCategory);
		category.setCreatedBy(createdBy);
		try{
			categoryDao.save(category);
			return true;
		}catch(DataAccessException ex){
			ex.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.CategoryService#getSubCategories_CategoryId(java.lang.String)
	 */
	@Override
	public List<Category> getSubCategories_CategoryId(String id) {
		int category_Id = Integer.parseInt(id);
		List<Category> subCategoryList = categoryDao.getSubCategories_CategoryId(category_Id);
		Collections.sort(subCategoryList,customSort);
		return subCategoryList;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.CategoryService#saveSubCategory(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean saveSubCategory(String id, String subCategoryName) {
		
		int subCategoryId = Integer.parseInt(id);
		Category category = new Category();

		Category parentCategory = categoryDao.get(subCategoryId);
		category.setCategoryType(Constants.CategoryType.SUBCATEGORY.getValue());
		category.setStatus(Status.ACTIVE.getValue());
		category.setName(subCategoryName);
		category.setParentCategory(parentCategory);
		try{
			categoryDao.save(category);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.CategoryService#getActiveCategories()
	 */
	@Override
	public List<Category> getActiveCategories() {
		List<Category> domains = categoryDao.getCategories(Status.ACTIVE, CategoryType.DOMAIN);
		Collections.sort(domains,customSort);
		return domains;
	}

	@Override
	public Category getCategory_uuid(String uuid) {
	Category category=categoryDao.getCategory(uuid);
	return category;
		
	}
	
	@Override
	public List<Category> searchCategories_Name(String name) {
		List<Category> categories = categoryDao.searchCategories_Name(name, Status.ACTIVE, CategoryType.CATEGORY);
		Collections.sort(categories,customSort);
		return categories;
	}
	@Override
	public List<Category> searchDomains_Name(String name) {
		 List<Category> categories = categoryDao.searchDomains_Name(name, Status.ACTIVE, CategoryType.DOMAIN);
		 Collections.sort(categories,customSort);
		 return categories;
	}

	
	@Override
	public List<Category> getCategory_Name(String name) {
		List<Category> categories = categoryDao.getCategory_Name(name,Status.ACTIVE,CategoryType.DOMAIN);
		Collections.sort(categories,customSort);
		return categories;
	}

	@Override
	public Category getCategory_Name_Id(String name, int id) {
		Category category = categoryDao.getCategory_Name_Id(name,id);
		return category;
	}

	@Override
	public List<Category> searchCategories_Name_ParentID(String name, String domainId , CategoryType category) {
		int parentId = Integer.parseInt(domainId);
		List<Category> categoriesList = categoryDao.searchCategories_Name_ParentID(name,parentId,Status.ACTIVE, category);
		Collections.sort(categoriesList,customSort);
		return categoriesList;
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
	public List<Category> getActiveCategory_Type(String name,CategoryType categoryType, Integer parentId, int pagesize, String sortField, 
			String sortOrder, int start) {
		if(name == null){
			name = "%";
		}else{
			name = name + "%";
		}
		return categoryDao.getActiveCategory_Type(Status.ACTIVE,name,categoryType,parentId,pagesize,sortField,sortOrder,start);
	}

	@Override
	public List<Category> getAllActiveCategories_Type(String name,CategoryType category,int pagesize, String sortField, 
			String sortOrder,int start) {
		if(name == null){
			name = "%";
		}else{
			name = name + "%";
		}
		return categoryDao.getAllActiveCategories_Type(Status.ACTIVE,name,category,pagesize,sortField,sortOrder,start);
	}

	@Override
	public int getTotalAllActiveCategories_Type(String name,CategoryType category) {
		if(name == null){
			name = "%";
		}else{
			name = name + "%";
		}
		return categoryDao.getTotalAllActiveCategories_Type(Status.ACTIVE,name,category);
	}
	
	@Override
	public String suggestDOrCOrSCByNameAndType(String name, String type, Integer parentId) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			CategoryType cType = null;
			if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("DOMAIN")) {
				cType = CategoryType.DOMAIN;
			} else if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("CATEGORY")) {
				cType = CategoryType.CATEGORY;
			} else if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("SUBCATEGORY")) {
				cType = CategoryType.SUBCATEGORY;
			}
			
			List<String> namesList = categoryDao.getCategoryNamesForAutoSuggest_Name(name, Status.ACTIVE, cType, parentId);
			for(String cName: namesList) {
				obj = new JSONObject();
				obj.put("name", cName);
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

}
