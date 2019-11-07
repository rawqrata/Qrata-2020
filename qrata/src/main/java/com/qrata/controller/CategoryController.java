package com.qrata.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qrata.enums.Constants;
import com.qrata.models.Category;
import com.qrata.models.CategoryForm;
import com.qrata.service.CategoryService;
import com.qrata.utility.PaginationUtility;
import com.qrata.utility.Utility;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "welcome.htm" , method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value = { "listCategories.htm" }, method = {RequestMethod.GET, RequestMethod.POST })
	public String getCategories(HttpServletRequest request,@RequestParam String id,@ModelAttribute("categoryForm") CategoryForm form,
			Model model) {
		
		Integer domainId = Integer.parseInt(id.trim());
		int totalActiveCategories = 1;
		List<Category> categoryList = new ArrayList();
		
		String pageRequestparam = "1";
		String orderRequestParam = "1";
		String sortUsingNameRequestParam = "1";
		String sortFieldRequestParam = "";
	
	
		request.setAttribute("categories", categoryList);
		model.addAttribute("categoryForm", form);
		model.addAttribute("totalActiveCategories", totalActiveCategories);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		model.addAttribute("id", id);
		
		return "categories/listCategories";
	}
	
	
	@RequestMapping(value = { "allCategories.htm" }, method = {RequestMethod.GET , RequestMethod.POST})
	public String getAllCategories(HttpServletRequest request, Model model, @ModelAttribute("categoryForm") CategoryForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "category");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "category");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "category");
		
		
		List<Category> categoriesList = categoryService.getAllActiveCategories_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.CATEGORY, PaginationUtility.pageSize, sortField, sortOrder,start);
		
		int totalActiveCategories =categoriesList.size();
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "category");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "category");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "category");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "category", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "category", sortField);
		}
		
		model.addAttribute("categories", categoriesList);
		model.addAttribute("totalActiveCategories", totalActiveCategories);
		model.addAttribute("categoryForm", form);
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "categories/allCategories";
	}
	
	@RequestMapping(value = { "listDomains.htm" }, method = { RequestMethod.GET ,RequestMethod.POST})
	public String getCategoryAndTopic(HttpServletRequest request,Model model,@ModelAttribute("categoryForm") CategoryForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "domain");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "domain");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "domain");
		
		
		List<Category> domainList = categoryService.getActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.DOMAIN, null, PaginationUtility.pageSize, sortField, sortOrder, start);
		System.out.println(domainList.size());
		int totalActiveDomains =domainList.size();
		String pageRequestParam = PaginationUtility.getCurrentPageRequestParam(request, "domain");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "domain");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "domain");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "domain", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "domain", sortField);
		}
		
		request.setAttribute("domains", domainList);
		model.addAttribute("categoryForm", form);
		model.addAttribute("totalActiveDomains", totalActiveDomains);
		
		model.addAttribute("prp", pageRequestParam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);

		return "categories/listDomains";
	}
	
	@RequestMapping(value = { "listSubCategory.htm" }, method = { RequestMethod.GET , RequestMethod.POST})
	public String getSubCategories(HttpServletRequest request, Model model,@RequestParam String id,
			@ModelAttribute("categoryForm") CategoryForm form ) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "subCategory");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subCategory");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subCategory");
		
		Integer categoryId = Integer.parseInt(id.trim());
		
		List<Category> sunCategoryList = categoryService.getActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal())
				,Constants.CategoryType.SUBCATEGORY,categoryId,PaginationUtility.pageSize, sortField, sortOrder,start);
		
		int totalActiveSubCategories =sunCategoryList.size();
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "subCategory");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "subCategory");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "subCategory");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "subCategory", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "subCategory", sortField);
		}
		
		model.addAttribute("subCategoryList", sunCategoryList);
		model.addAttribute("categoryForm", form);
		model.addAttribute("totalActiveSubCategories", totalActiveSubCategories);
		model.addAttribute("id", id);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		
		return "categories/listSubCategories";
	}
	
	
	@RequestMapping(value = { "allSubCategories.htm" }, method = {RequestMethod.GET , RequestMethod.POST})
	public String allSubCategories(HttpServletRequest request, Model model, @ModelAttribute("categoryForm") CategoryForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "subCategory");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subCategory");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subCategory");
		
		int totalActiveSubCategories = categoryService.getTotalAllActiveCategories_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.SUBCATEGORY);
		List<Category> subCategoryList = categoryService.getAllActiveCategories_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.SUBCATEGORY, PaginationUtility.pageSize, sortField, sortOrder, start);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "subCategory");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "subCategory");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "subCategory");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "subCategory", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "subCategory", sortField);
		}
		
		model.addAttribute("subCategoryList", subCategoryList);
		model.addAttribute("totalActiveSubCategories", totalActiveSubCategories);
		model.addAttribute("categoryForm", form);
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		
		return "categories/allSubCategories";
	}
	
	
	@RequestMapping(value="autoSuggestCategoryByNameAndType.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestCategoryByNameAndType(@RequestParam("name") String name, @RequestParam("type") String type,
			@RequestParam("parentId") Integer parentId) {
		return categoryService.suggestDOrCOrSCByNameAndType(name, type, parentId);
	}

	
	@RequestMapping(value = { "saveDomain.htm" }, method = RequestMethod.POST)
	public String saveDomain(HttpServletRequest request, Model model ) {

		String domain = request.getParameter("domain");
		boolean result = categoryService.addDomain("Domain test");
		String success = null;
		if(result){
			success = "1";
		}else{
			success = "4";
		}
		return "redirect:listDomains.htm?success="+success;

	}
	
	@RequestMapping(value = { "addCategory.htm" }, method = { RequestMethod.GET })
	public String addCategory(HttpServletRequest request,Model model
			,@RequestParam String id, @RequestParam(value = "type" ,defaultValue = "d")  String type) {
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		//System.out.println(">>>"+type);
		return "categories/addCategory";
	}
	
	
	@RequestMapping(value = { "addDomain.htm" }, method = { RequestMethod.GET })
	public String addDomain(HttpServletRequest request) {

		return "categories/addDomain";
	}
	
	@RequestMapping(value = { "addSubCategory.htm" }, method = RequestMethod.GET)
	public String addSubCategory(HttpServletRequest request,Model model,
			@RequestParam String id,@RequestParam(value = "type" ,defaultValue = "c")  String type)  {
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		
		return "categories/addSubCategory";
	}

	@PostConstruct
	public void saveCategory() {
		
		/*
		 * Category category = new Category();
		 * category.setCategoryType(Short.parseShort("1")); category.setName("sample");
		 * category.setStatus(Short.parseShort("1")); category.setUuid("1234564");
		 * categoryService.saveOrUpdate(category);
		 * 
		 * List<Category> list = categoryService.getActiveDomains(); for(Category ct :
		 * list) { System.out.println(ct); }
		 */
	}


}
