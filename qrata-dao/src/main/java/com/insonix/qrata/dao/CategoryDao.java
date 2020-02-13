/**
 * 
 */
package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.Category;

/**
 * @author Ramandeep Singh
 * @Since  Feb 6, 2013
 */
public interface CategoryDao extends IBaseDao<Category> {

	/**
	 * @return
	 */
	public List<Category> getActiveDomains();
	
	/**
	 * @param domainId
	 * @return
	 */
	public List<Category> getCategories_domainId(int domainId);
	
	/**
	 * @param categoryId
	 * @return
	 */
	public List<Category> getSubCategories_CategoryId(int categoryId);
	
	/**
	 * Method to get Categories by Status
	 * 
	 * @param status
	 * @return
	 */
	public List<Category> getCategories(Status status);
	
	/**
	 * Method Get Category by UUId
	 * @author Ramandeep Singh
	 * @param uuid
	 * @return
	 */
	public Category getCategory(String uuid);

	public List<Category> getCategories(Status status, com.insonix.qrata.constants.Constants.CategoryType type);
	
	public List<Category> searchCategories_Name(String name, Status status, com.insonix.qrata.constants.Constants.CategoryType type);
	public List<Category> searchDomains_Name(String name, Status status, com.insonix.qrata.constants.Constants.CategoryType type);
	public List<Category> getCategory_Name(String name,Status status,com.insonix.qrata.constants.Constants.CategoryType type);
 
	public Category getCategory_Name_Id(String name, int id);

	public List<Category> searchCategories_Name_ParentID(String name,int parentId, Status active, CategoryType category);

	public int getTotalActiveCategory_Type(Status active, String name, CategoryType categoryType, Integer parentId);

	public List<Category> getActiveCategory_Type(Status active, String name, CategoryType categoryType, Integer parentId, int pagesize,
			String sortField, String sortOrder,int start);

	public List<Category> getAllActiveCategories_Type(Status active,String name, CategoryType category, int pagesize, String sortField,
			String sortOrder, int start);

	public int getTotalAllActiveCategories_Type(Status active,String name, CategoryType category);
	
	public List<String> getCategoryNamesForAutoSuggest_Name(String name, Status status, CategoryType type, Integer parentId);
}
