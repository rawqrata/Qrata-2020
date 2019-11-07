package com.qrata.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qrata.models.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	/*
	select * from categories where category_type=1 and status=1 and name like "%%" order by name asc;
	}
	 */

	@Query("select c from Category c where c.categoryType  = :type  and c.status = :status order by c.name asc")
	public abstract List<Category> getCategoryByCondition(@Param("type") Short paramtype, @Param("status") Short paramdstatus);
	
	@Query("select c from Category c where c.uuid = :uuid")
	public abstract Category getCategory(@Param("uuid") String paramString);
	
	@Query("select c from Category c where c.status = :status and c.categoryType = :type and c.name like :name% order by c.name asc")
	public abstract List<Category> searchByCondition(@Param("status") Short paramdstatus,@Param("type") Short paramtype,@Param("name") String name);
	
	@Query("select c from Category c where c.status = :status and c.categoryType = :type and lower(c.name) = :name")
	public abstract List<Category> getCategory_Name(@Param("status") Short paramdstatus,@Param("type") Short paramtype,@Param("name") String name);
	
	@Query("select c from Category c where lower(c.name) = :name and parentCategory.id = :id")
	public abstract Category getCategoryByNameId(@Param("name") String name, @Param("id") int id);
	
	@Query("select c from Category c where parentCategory.id = :id and c.status = :status and c.categoryType = :type and c.name like :name% order by c.name asc")
	public abstract List<Category>  getCategoryByNameAndParentId(@Param("id") int id,@Param("status") Short paramdstatus,@Param("type") Short paramtype,@Param("name") String name);
	
	@Query("select c from Category c where c.status = :status and c.categoryType = :type order by c.name asc")
	public abstract List<Category> getAllActiveCategories_Type(@Param("status") Short paramdstatus,@Param("type") Short paramtype);
	
	@Query("select c from Category c where  parentCategory.id = :id  and c.status = :status and c.categoryType = :type order by c.name asc")
	public abstract List<Category> getAllActiveSubCategories_Type(@Param("id") int id,@Param("status") Short paramdstatus,@Param("type") Short paramtype);
}
