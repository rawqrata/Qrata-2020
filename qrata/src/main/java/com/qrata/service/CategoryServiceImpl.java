package com.qrata.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.qrata.entity.CategoryForm;
import com.qrata.enums.Constants;
import com.qrata.enums.Constants.CategoryType;
import com.qrata.enums.Status;
import com.qrata.models.Category;
import com.qrata.respository.CategoryRepository;
import com.qrata.utility.SortingUtility;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	private SortingUtility<Category> customSort  = new SortingUtility<Category>();
	 

    @PersistenceContext
    private EntityManager em;
    
	@Override
	public Category getCategory_Id(int id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public boolean delete(Category category) {
		try{
			categoryRepository.delete(category);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	@Override
	public int saveOrUpdate(Category category) {
		return categoryRepository.save(category).getId();
	}

	@Override
	public boolean updateBulk(List<Category> categories) {
		try{
			categoryRepository.saveAll(categories);
			return true;
		}catch(Exception ex){
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
			categoryRepository.save(category);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	@Override
	public List<Category> getActiveDomains() {
		List<Category> list= categoryRepository.getCategoryByCondition(Constants.CategoryType.DOMAIN.getValue(),Status.ACTIVE.getValue());
	    return list;
	}

	@Override
	public List<Category> getCategories_domainId(String id) {
		return null;
	}

	@Override
	public boolean saveCategory(String id, String categoryName) {
		int domainId = Integer.parseInt(id);
		Category category = new Category();
		Category parentCategory = categoryRepository.findById(domainId).get();
		category.setCategoryType(Constants.CategoryType.CATEGORY.getValue());
		category.setStatus(Status.ACTIVE.getValue());
		category.setName(categoryName);
		category.setParentCategory(parentCategory);
		try{
			categoryRepository.save(category);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	@Override
	public List<Category> getSubCategories_CategoryId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveSubCategory(String id, String subCategoryName) {
		int subCategoryId = Integer.parseInt(id);
		Category category = new Category();
		Category parentCategory = categoryRepository.findById(subCategoryId).get();
		category.setCategoryType(Constants.CategoryType.SUBCATEGORY.getValue());
		category.setStatus(Status.ACTIVE.getValue());
		category.setName(subCategoryName);
		category.setParentCategory(parentCategory);
		try{
			categoryRepository.save(category);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	@Override
	public List<Category> getActiveCategories() {
		return null;
	}

	@Override
	public Category getCategory_uuid(String uuid) {
		return categoryRepository.getCategory(uuid);
	}

	@Override
	public List<Category> searchCategories_Name(String name) {
		List<Category> categories = categoryRepository.searchByCondition(Status.ACTIVE.getValue(), CategoryType.CATEGORY.getValue(),name);
		Collections.sort(categories,customSort);
		return categories;
	}

	@Override
	public List<Category> searchDomains_Name(String name) {
		 List<Category> categories = categoryRepository.searchByCondition(Status.ACTIVE.getValue(), CategoryType.DOMAIN.getValue(),name);
		 Collections.sort(categories,customSort);
		 return categories;
	}

	@Override
	public List<Category> getCategory_Name(String name) {
		 List<Category> categories = categoryRepository.getCategory_Name(Status.ACTIVE.getValue(), CategoryType.DOMAIN.getValue(),name);
		 Collections.sort(categories,customSort);
		 return categories;
	}

	@Override
	public Category getCategory_Name_Id(String name, int id) {
		return categoryRepository.getCategoryByNameId(name, id);
	}

	@Override
	public List<Category> searchCategories_Name_ParentID(String domainSearchVal, String domainId,
			CategoryType category) {
		int parentId = Integer.parseInt(domainId);
		return categoryRepository.getCategoryByNameAndParentId(parentId, Status.ACTIVE.getValue(), category.getValue(), domainSearchVal);
	}

	@Override
	public int getTotalActiveCategory_Type(String name, CategoryType domain, Integer parentId) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		Root<Category> itemRoot = criteriaQuery.from(Category.class);
		return 0;
	}

	@Override
	public List<Category> getActiveCategory_Type(String name, CategoryType domain, Integer object, int pagesize,
			String sortField, String sortOrder, int start) {
		if (object == null) {
			return categoryRepository.getAllActiveCategories_Type(Status.ACTIVE.getValue(),
					Constants.CategoryType.DOMAIN.getValue());
		} else {
			return categoryRepository.getAllActiveSubCategories_Type(object, Status.ACTIVE.getValue(),
					Constants.CategoryType.SUBCATEGORY.getValue());
		}
	}

	@Override
	public List<Category> getAllActiveCategories_Type(String name, CategoryType category, int pagesize,
			String sortField, String sortOrder, int start) {
		/* This is for final business logic
		 * String order=""; if(name == null){ name = "%"; }else{ name = name + "%"; }
		 * 
		 * if(!StringUtils.isEmpty(sortField)) { if(sortOrder.equals("asc"))
		 * order="order by name asc"; else order="order by name desc"; } else {
		 * order="order by name asc"; }
		 */
		return categoryRepository.getAllActiveCategories_Type(Status.ACTIVE.getValue(), category.getValue());
	}

	@Override
	public int getTotalAllActiveCategories_Type(String name, CategoryType category) {
		// TODO Auto-generated method stub
		return 0;
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
			
			List<String> namesList = new ArrayList<String>();/*categoryDao.getCategoryNamesForAutoSuggest_Name(name, Status.ACTIVE, cType, parentId);*/
			namesList.add("sample1");
			namesList.add("sample2");
			namesList.add("sample3");
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

	@Override
	public List<CategoryForm> getActiveCategoryForm_Type(String name, CategoryType domain, Integer object, int pagesize,
			String sortField, String sortOrder, int start) {
		List<Category>  categoryList=categoryRepository.getAllActiveCategories_Type(Status.ACTIVE.getValue(),Constants.CategoryType.DOMAIN.getValue());
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
