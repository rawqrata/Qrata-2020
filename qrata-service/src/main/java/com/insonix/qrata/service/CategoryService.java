package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.models.Category;


/**
 * @author Raman
 *
 */
/**
 * @author kamal
 *
 */
public interface CategoryService {
	
	int getCategory_Name = 0;



	/**
	 * Method to get Category by Id
	 * 
	 * @param id
	 * @return
	 */
	public Category getCategory_Id(int id);
	/**
	 * @param category
	 */
	public boolean delete(Category category);
	/**
	 * @param category
	 * @return
	 */
	public String save(Category category);
	/**
	 * @param category
	 */
	public boolean update(Category category);
	/**
	 * @param categories
	 */
	public boolean updateBulk(List<Category> categories);
	
	/**
	 * @param domain
	 */
	public boolean addDomain(String domain);
	
	/**
	 * @return
	 */
	public List<Category> getActiveDomains();
	
	/**
	 * @param id
	 * @return
	 */
	public List<Category> getCategories_domainId(String id);
	
	/**
	 * @param id
	 */
	public boolean saveCategory(String id , String categoryName, long createdBy);
	
	/**
	 * @param categoryId
	 * @return
	 */
	public List<Category> getSubCategories_CategoryId(String id);
	
	/**
	 * @param id
	 * @param subCategoryName
	 */
	public boolean saveSubCategory(String id , String subCategoryName);
	
	public List<Category> getActiveCategories();
	
	public Category getCategory_uuid(String uuid);
	
	public List<Category> searchCategories_Name(String name);
	
	public List<Category> searchDomains_Name(String name);
	
	public List<Category> getCategory_Name(String name); 
	
	public Category getCategory_Name_Id(String name, int id);
	
	public List<Category> searchCategories_Name_ParentID(String domainSearchVal, String domainId, CategoryType category);
	
	public int getTotalActiveCategory_Type(String name,CategoryType domain,Integer parentId);
	
	public List<Category> getActiveCategory_Type(String name, CategoryType domain, Integer  object, int pagesize, String sortField, 
			String sortOrder,int start);
	
	public List<Category> getAllActiveCategories_Type(String name, CategoryType category,int pagesize, String sortField, 
			String sortOrder,int start);
	
	public int getTotalAllActiveCategories_Type(String name, CategoryType category);

	public String suggestDOrCOrSCByNameAndType(String name, String type, Integer parentId);
	
}
