package com.insonix.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.entity.CategoryForm;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.utility.Utility;

/**
 * @author Gurminder Singh
 *
 * @date 18-Jun-2013
 */

@Controller("categoryController")
@RequestMapping(value="categories")
public class CategoryController {
	@Autowired CategoryService categoryService;

	@RequestMapping(value={"listCategories.htm"}, method=RequestMethod.GET)
	public String listCategories(HttpServletRequest request , Model model, @RequestParam("id") int domainId,@ModelAttribute("categoryForm") CategoryForm form) {
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "category");
		int totalActiveCategories = categoryService.getTotalActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.CATEGORY,domainId);
		//List<Category> categoryList = categoryService.listCategories(domainId);
		List<CategoryForm> categoryList = categoryService.getActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal())
				,Constants.CategoryType.CATEGORY,domainId,PaginationUtility.pageSize, null, null,start);
		request.setAttribute("categoryList", categoryList);
		model.addAttribute("totalActiveCategories" ,totalActiveCategories);
		return "categories/listCategories";
	}
	
	@RequestMapping(value={"listSubCategories.htm"} , method=RequestMethod.GET)
	public String listSubCategories(HttpServletRequest request , Model model , @RequestParam("id") int categoryId,@ModelAttribute("categoryForm") CategoryForm form){
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subCategory");
		int totalActiveSubCategories = categoryService.getTotalActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.SUBCATEGORY,categoryId);
	//	List<Category> subCategoryList=categoryService.listSubCategories(categoryId);
		List<CategoryForm> subCategoryList = categoryService.getActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal())
				,Constants.CategoryType.SUBCATEGORY,categoryId,PaginationUtility.pageSize, null, null,start);
		model.addAttribute("subCategoryList", subCategoryList);
		model.addAttribute("totalActiveSubCategories", totalActiveSubCategories);
		return "categories/listSubCategories";
	}
	
	
}
