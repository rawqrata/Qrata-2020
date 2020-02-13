package com.insonix.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.CategoryForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.User;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.service.TopicExpertAssignmentService;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 * 
 */
@Controller("categoryController")
@RequestMapping("admin")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	TopicExpertAssignmentService topicExpertAssignmentService;
	

	/**
	 * @return
	 */
	
	@RequestMapping(value = { "listDomains.htm" }, method = { RequestMethod.GET ,RequestMethod.POST})
	public String getCategoryAndTopic(HttpServletRequest request,Model model,@ModelAttribute("categoryForm") CategoryForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "domain");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "domain");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "domain");
		
		int totalActiveDomains = categoryService.getTotalActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.DOMAIN, null);
		List<Category> domainList = categoryService.getActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.DOMAIN, null, PaginationUtility.pageSize, sortField, sortOrder, start);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "domain");
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
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);

		return "categories/listDomains";
	}
	
	@RequestMapping(value={"editDomain.htm"},method=RequestMethod.GET)
	public String editDomain (HttpServletRequest request,Model model,@RequestParam("id") int id,@RequestParam(value="prp")String prp,
			@RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, @RequestParam("sunrp")String sunrp){
		
		Category category = categoryService.getCategory_Id(id);
		CategoryForm form=new CategoryForm();
		form.setName(category.getName());
		form.setId(category.getId());
		
		model.addAttribute("domain",form);
		
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		
		return "categories/editDomain";
}
	@RequestMapping(value={"editDomain.htm"},method=RequestMethod.POST)
	public String updateDomain(HttpServletRequest request,	Model model,	@ModelAttribute("domain")CategoryForm form){
		
		String prp = request.getParameter("prp");
		String orp = request.getParameter("orp");
		String sfrp = request.getParameter("sfrp"); 
		String sunrp = request.getParameter("sunrp");
		
		Category category=categoryService.getCategory_Id(form.getId());
		category.setName(form.getName().trim());
		boolean result = categoryService.update(category);
		String success = null;
		if(result){
			success = "3";
		}else{
			success = "4";
		}
		return "redirect:listDomains.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "addDomain.htm" }, method = { RequestMethod.GET })
	public String addDomain(HttpServletRequest request) {

		return "categories/addDomain";
	}
	/**
	 * @return
	 */
	@RequestMapping(value = { "listCategories.htm" }, method = {RequestMethod.GET, RequestMethod.POST })
	public String getCategories(HttpServletRequest request,@RequestParam String id,@ModelAttribute("categoryForm") CategoryForm form,
			Model model) {
		int	start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "category");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "category");
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "category");
		
		Integer domainId = Integer.parseInt(id.trim());
		int totalActiveCategories = categoryService.getTotalActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.CATEGORY,domainId);
		List<Category> categoryList = categoryService.getActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal())
				,Constants.CategoryType.CATEGORY,domainId,PaginationUtility.pageSize, sortColumn, sortOrder,start);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "category");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "category");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "category");
		String sortFieldRequestParam = "";
		if(sortColumn == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "category", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "category", sortColumn);
		}
	
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
	
	
	
	@RequestMapping(value = { "editCategory.htm" }, method = RequestMethod.GET)
	public String editCategory(HttpServletRequest request, Model model,@RequestParam("id") String uuid,
			@RequestParam(value = "domainId") String domainId, @RequestParam(value="prp")String prp, @RequestParam("orp")String orp,
			@RequestParam("sfrp")String sfrp, @RequestParam("sunrp")String sunrp, @RequestParam(value = "ale", defaultValue = "") String ale) {

		Category category = categoryService.getCategory_uuid(uuid);
		model.addAttribute("category", category.getName().trim());
		model.addAttribute("categoryId", category.getUuid());
		model.addAttribute("domainId", domainId);
		
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		model.addAttribute("ale", ale);
		
		return "categories/editCategory";
	}

	@RequestMapping(value = { "editCategory.htm" }, method = RequestMethod.POST)
	public String updateCategory(HttpServletRequest request, Model model) {
		
		String name = request.getParameter("name");
		String uuid = request.getParameter("categoryId");
		String domainId = request.getParameter("domainId");
		
		String prp = request.getParameter("prp");
		String orp = request.getParameter("orp");
		String sfrp = request.getParameter("sfrp"); 
		String sunrp = request.getParameter("sunrp");
		String ale = request.getParameter("ale");
		
		Category category = categoryService.getCategory_uuid(uuid);
		category.setName(name.trim());
		
		boolean result = categoryService.update(category);
		String success = null;
		if(result){
			success = "3";
		}else{
			success = "4";
		}
		if (ale.equals("")) {
			model.addAttribute("id", domainId);
			return "redirect:listCategories.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		return "redirect:allCategories.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "listSubCategory.htm" }, method = { RequestMethod.GET , RequestMethod.POST})
	public String getSubCategories(HttpServletRequest request, Model model,@RequestParam String id,
			@ModelAttribute("categoryForm") CategoryForm form ) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "subCategory");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subCategory");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subCategory");
		
		Integer categoryId = Integer.parseInt(id.trim());
		int totalActiveSubCategories = categoryService.getTotalActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.SUBCATEGORY,categoryId);
		List<Category> sunCategoryList = categoryService.getActiveCategory_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal())
				,Constants.CategoryType.SUBCATEGORY,categoryId,PaginationUtility.pageSize, sortField, sortOrder,start);
		
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
	

	@RequestMapping(value = { "editSubCategory.htm" }, method = RequestMethod.GET)
	public String editSubCategory(Model model, @RequestParam("id") String uuid, @RequestParam(value = "catId") String categoryId,
			@RequestParam(value="prp")String prp, @RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, 
			@RequestParam("sunrp")String sunrp, @RequestParam(value = "ale", defaultValue = "") String ale) {

		Category category = categoryService.getCategory_uuid(uuid);
		model.addAttribute("category", category.getName());
		model.addAttribute("subCategoryId", category.getUuid());
		if (categoryId != null) {
			model.addAttribute("id", categoryId);
		}
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		model.addAttribute("ale", ale);
		
		return "categories/editSubCategory";
	}

	@RequestMapping(value = { "editSubCategory.htm" }, method = RequestMethod.POST)
	public String updateSubCategory(HttpServletRequest request, Model model) {

		String name = request.getParameter("name");
		String uuid = request.getParameter("subCategoryId");
		String categoryId = request.getParameter("categoryId");
		
		String prp = request.getParameter("prp");
		String orp = request.getParameter("orp");
		String sfrp = request.getParameter("sfrp"); 
		String sunrp = request.getParameter("sunrp");
		String ale = request.getParameter("ale");
		
		Category category = categoryService.getCategory_uuid(uuid);
		category.setName(name.trim());
		boolean result = categoryService.update(category);
		String success = null;
		if(result){
			success = "3";
		}else{
			success = "4";
		}
		model.addAttribute("id",categoryId);
		model.addAttribute("success", success);
		if(StringUtils.isEmpty(ale)){
			return "redirect:listSubCategory.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp; 
		}
		return "redirect:allSubCategories.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp; 

	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "categoriesByDomainId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String getCategoriesByDomainId(HttpServletRequest request,
			@RequestParam String id) {
		
		List<Category> categories = categoryService.getCategories_domainId(id);
		String options = "";
		if(! categories.isEmpty()){
			options = "<option value='select' >Select Category</option>";
			for(Category category : categories){
				options+="<option value="+category.getId()+">"+category.getName()+"</option>";
			}
		}
		JSONObject obj = new JSONObject();
		try{
			obj.put("options", options);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		//System.out.println(options);
		return obj.toString();
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "subCategoriesByCategoryId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String getSubCategoriesByCategoryId(HttpServletRequest request,@RequestParam String id) {
		//System.out.println("In Controller");
		List<Category> categories = categoryService.getSubCategories_CategoryId(id);
		String options = "";
		if(! categories.isEmpty()){
			options = "<option value='select' >Select Sub Category</option>";
			for(Category category : categories){
				options+="<option value="+category.getId()+">"+category.getName()+"</option>";
			}
		}
		JSONObject obj = new JSONObject();
		try{
			obj.put("options", options);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		//System.out.println(options);
		return obj.toString();
	}
	
	@RequestMapping(value="assignedCategoriesByDomainId_ExpertId.htm", method=RequestMethod.GET)
	@ResponseBody
	public String assignedCategoriesByDomainId_ExpertId(Model model ,@RequestParam("domainId") int domainId , 
			@RequestParam("userId") long userId) {
		List<Category> categories = topicExpertAssignmentService.getAssignedCategoriesByDomainId_ExpertId(domainId,userId);
		String options = "";
		
		if(! categories.isEmpty()){
			options = "<option value='select' >Select Category</option>";
			for(Category category : categories){
				options+="<option value="+category.getId()+">"+category.getName()+"</option>";
			}
		}
		
		JSONObject obj = new JSONObject();
		try{
			obj.put("options", options);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		//System.out.println(options);
		return obj.toString();
	}
	
	
	@RequestMapping(value="assignedSubCategoriesByCategoryId_ExpertId.htm", method=RequestMethod.GET)
	@ResponseBody
	public String assignedSubCategoriesByCategoryId_ExpertId(Model model ,@RequestParam("categoryId") int categoryId , 
			@RequestParam("userId") long userId) {
		List<Category> categories = topicExpertAssignmentService.getAssignedSubCategoriesByCategoryId_ExpertId(categoryId,userId);
		String options = "";
		
		if(! categories.isEmpty()){
			options = "<option value='select' >Select Sub Category</option>";
			for(Category category : categories){
				options+="<option value="+category.getId()+">"+category.getName()+"</option>";
			}
		}
		
		JSONObject obj = new JSONObject();
		try{
			obj.put("options", options);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		//System.out.println(options);
		return obj.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "addCategory.htm" }, method = { RequestMethod.GET })
	public String addCategory(HttpServletRequest request,Model model
			,@RequestParam String id, @RequestParam(value = "type" ,defaultValue = "d")  String type) {
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		//System.out.println(">>>"+type);
		return "categories/addCategory";
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "addSubCategory.htm" }, method = RequestMethod.GET)
	public String addSubCategory(HttpServletRequest request,Model model,
			@RequestParam String id,@RequestParam(value = "type" ,defaultValue = "c")  String type)  {
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		
		return "categories/addSubCategory";
	}
	

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "saveDomain.htm" }, method = RequestMethod.POST)
	public String saveDomain(HttpServletRequest request, Model model ) {

		String domain = request.getParameter("domain");
		boolean result = categoryService.addDomain(domain);
		String success = null;
		if(result){
			success = "1";
		}else{
			success = "4";
		}
		return "redirect:listDomains.htm?success="+success;

	}
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "saveCategory.htm" }, method = RequestMethod.POST)
	public String saveCategory(HttpServletRequest request, Model model) {
		String domainId = request.getParameter("id");
		String categoryName = request.getParameter("categoryName").trim();
		User user = (User) request.getSession().getAttribute("user");
		boolean result = categoryService.saveCategory(domainId, categoryName, user.getId());
		String success = null;
		if(result){
			success = "1";
		}else{
			success = "4";
		}
		model.addAttribute("id", domainId);
		return "redirect:listCategories.htm?success="+success;
	}
	

	@RequestMapping(value = { "saveSubCategory.htm" }, method = RequestMethod.POST)
	public String saveSubCategory(HttpServletRequest request, Model model) {

		String categoryId = request.getParameter("id");
		String subCategoryName = request.getParameter("subCategoryName").trim();
		boolean result = categoryService.saveSubCategory(categoryId, subCategoryName);
		String success = null;
		if(result){
			success = "1";
		}else{
			success = "4";
		}
		model.addAttribute("id", categoryId);
		return "redirect:listSubCategory.htm?success="+success;

	}
	@RequestMapping(value = { "deleteDomain.htm" }, method = RequestMethod.GET)
	public String deleteDomain(HttpServletRequest request, Model model, @RequestParam("id") int id,
			 @RequestParam(value="prp")String prp, @RequestParam("orp")String orp,
				@RequestParam("sfrp")String sfrp, @RequestParam("sunrp")String sunrp) {
		model.addAttribute("id", id);
		Category category = categoryService.getCategory_Id(id);
		category.setStatus(Status.INACTIVE.getValue());
		boolean result = categoryService.delete(category);
		String success = null;
		if(result){
			success = "2";
		}else{
			success = "4";
		}
		return "redirect:listDomains.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}

	@RequestMapping(value = { "deleteCategory.htm" }, method = RequestMethod.GET)
	public String deleteCategory(HttpServletRequest request, Model model, @RequestParam("id") int id, 
			@RequestParam(value="domainId",required=false) Integer domainId,  @RequestParam(value="prp")String prp, @RequestParam("orp")String orp,
			@RequestParam("sfrp")String sfrp, @RequestParam("sunrp")String sunrp) {
		
		Category category = categoryService.getCategory_Id(id);
		boolean result = categoryService.delete(category);
		String success = null;
		if(result){
			success = "2";
		}else{
			success = "4";
		}
		if(domainId!=null){
			model.addAttribute("id", domainId);
			return "redirect:listCategories.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		return "redirect:allCategories.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}

	@RequestMapping(value = { "deleteSubCategory.htm" }, method = RequestMethod.GET)
	public String deleteSubCategory(HttpServletRequest request, Model model, @RequestParam("id") int id,
			@RequestParam("CategoryId") int categoryId, @RequestParam(value="prp")String prp, @RequestParam("orp")String orp,
			@RequestParam("sfrp")String sfrp, @RequestParam("sunrp")String sunrp, @RequestParam(value = "ale", defaultValue = "") String ale) {
		
		model.addAttribute("id", categoryId);
		Category category = categoryService.getCategory_Id(id);
//		category.setStatus(Status.INACTIVE.getValue());
		boolean result = categoryService.delete(category);
		String success = null;
		if(result){
			success = "2";
		}else{
			success = "4";
		}
		if(ale.equals("")){
			return "redirect:listSubCategory.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		return "redirect:allSubCategories.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}

	@RequestMapping(value = { "allCategories.htm" }, method = {RequestMethod.GET , RequestMethod.POST})
	public String getAllCategories(HttpServletRequest request, Model model, @ModelAttribute("categoryForm") CategoryForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "category");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "category");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "category");
		
		int totalActiveCategories = categoryService.getTotalAllActiveCategories_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.CATEGORY);
		List<Category> categoriesList = categoryService.getAllActiveCategories_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal()),
				Constants.CategoryType.CATEGORY, PaginationUtility.pageSize, sortField, sortOrder,start);
		
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
	
	

	/*
	 * this method is used to search the category
	 */
	@RequestMapping(value = { "searchCategories.htm" }, method = { RequestMethod.POST })
	public String searchCategories(@ModelAttribute("categoryForm") CategoryForm form , Model model) {
		List<Category> categories = categoryService.searchCategories_Name(form.getCategorySearchVal());
		model.addAttribute("categories", categories);
		model.addAttribute("categoryForm", form);
		return "categories/allCategories";
	}
	/*
	 * this method is used to search the domain
	 */
	@RequestMapping(value = { "searchDomains.htm" }, method = { RequestMethod.POST })
	public String searchDomains(@ModelAttribute("categoryForm") CategoryForm form , Model model,
			@RequestParam(value = "status" , defaultValue = "0") int status) {
		List<Category> domains = categoryService.searchDomains_Name(form.getDomainSearchVal());
		model.addAttribute("domains", domains);
		model.addAttribute("categoryForm", form);
		return "categories/listDomains";
	}
	
	@RequestMapping(value="existsDomain.htm",method={RequestMethod.GET})
	public @ResponseBody String existsDomain(@RequestParam("name") String name) {		
		List<Category> domains = categoryService.getCategory_Name(name.trim());	
		boolean exists = false;
		if(domains!=null && domains.size() != 0) {
			exists = true;
		}	
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("exists", exists);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		return obj.toString();
	}
	
	@RequestMapping(value="existsCategory.htm",method={RequestMethod.GET})
	public @ResponseBody String existsCategory(@RequestParam("name")String name, @RequestParam("id") int id){
		Category category=categoryService.getCategory_Name_Id(name.trim(),id);
		
		boolean exists = false;
		if(category!=null ){
			exists=true;
		}
		//System.out.println("Exist Values : "+exists+"  name >>>   "+name);
		JSONObject obj = new JSONObject();
		try {
			obj.put("exists", exists);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return obj.toString();
	}
	
	@RequestMapping(value="existsSubCategory.htm",method={RequestMethod.GET})
	public @ResponseBody String existsSubCategory(@RequestParam("name")String name,@RequestParam("id") int id){
		Category subCategory = categoryService.getCategory_Name_Id(name.trim(),id);
		boolean exists=false;
		if(subCategory!=null ){
			exists=true;
		}
		//System.out.println("Exist Values : "+exists+"  name >>>   "+name);
		JSONObject obj = new JSONObject();
		try {
			obj.put("exists", exists);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();	
	}

			/*
			 * Names Auto Suggestion
			 */
	@RequestMapping(value="autoSuggestCategoryByNameAndType.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestCategoryByNameAndType(@RequestParam("name") String name, @RequestParam("type") String type,
			@RequestParam("parentId") Integer parentId) {
		String str = categoryService.suggestDOrCOrSCByNameAndType(name, type, parentId);
		return str;
	}
	
}	
