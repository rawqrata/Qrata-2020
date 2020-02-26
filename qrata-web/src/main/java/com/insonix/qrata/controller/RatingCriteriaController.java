package com.insonix.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.RatingCriteriaForm;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.RatingCriteriaService;

@Controller("ratingCriteriaController")
@RequestMapping("admin")
public class RatingCriteriaController {
	@Autowired
	RatingCriteriaService ratingCriteriaService;
	
	/**
	 * @return
	 */
	@RequestMapping(value = {"ratingCriteriaCategory.htm"} , method = {RequestMethod.GET})
	public String getCriteriaCategory(HttpServletRequest request, Model model) {		
		List<RatingCriteria> criteria = ratingCriteriaService.findAllParentCriteria();
		request.setAttribute("criteria", criteria);
		RatingCriteriaForm form = new RatingCriteriaForm();
		model.addAttribute("ratingCriteriaForm", form);
		return "ratingCriteria/ratingCriteriaCategory";
	}
	
			/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "addCriteriaCategory.htm" }, method = { RequestMethod.GET })
	public String addCriteriaCategory(HttpServletRequest request) {
		
		return "ratingCriteria/addCriteriaCategories";
	}
	/**
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = { "saveCriteriaCategory.htm" }, method = RequestMethod.POST)
	public String saveCriteriaCategory(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String criteriaCategory = request.getParameter("criteriaCategory");
		boolean result = ratingCriteriaService.addCriteriaCategory(criteriaCategory,user);
		String success = null;
		if(result){
			success = "1";
		}
		
		return "redirect:ratingCriteriaCategory.htm?success="+success;

	}
	
	@RequestMapping(value={"ratingCriteria.htm"},method={RequestMethod.GET,RequestMethod.POST})
	public String getRatingCriteria(HttpServletRequest request,Model model , @RequestParam String id) {
		List<RatingCriteria> ratingCriteria = ratingCriteriaService.getRatingCriteria_ratingCriteriaCategoryId(id);
		RatingCriteriaForm ratingCriteriaForm = new RatingCriteriaForm();
		request.setAttribute("ratingCriteria", ratingCriteria);
		model.addAttribute("id", id);
		model.addAttribute("ratingCriteriaForm", ratingCriteriaForm);
		return "ratingCriteria/ratingCriteria";
	}
	
	
	@RequestMapping(value = { "addCriteria.htm" }, method = { RequestMethod.GET })
	public String addRatingCriteria(HttpServletRequest request, @RequestParam String id){
		request.setAttribute("id", id);
		return "ratingCriteria/addCriteria";
	}

	
	@RequestMapping(value = { "saveCriteria.htm" }, method = RequestMethod.POST)
	public String saveRatingCriteria(HttpServletRequest request, Model model) {
		String ratingCriteriaCategoryId = request.getParameter("id");
		String ratingCriteriaName = request.getParameter("ratingCriteriaName");
		String description = request.getParameter("description");
		User user = (User) request.getSession().getAttribute("user");
		int priority = Integer.parseInt( request.getParameter("priority"));
		boolean result = ratingCriteriaService.saveRatingCriteria(ratingCriteriaCategoryId, ratingCriteriaName, description, priority,user);
		String success = null;
		if(result){
			success = "1";
		}
		model.addAttribute("id", ratingCriteriaCategoryId);
		return "redirect:ratingCriteria.htm?success="+success;
	}
	
	@RequestMapping(value = { "deleteCriteria.htm" }, method = RequestMethod.GET)
	public String deleteCriteria(HttpServletRequest request, Model model,
			@RequestParam("id") int id, @RequestParam(value="categoryId") int categoryId) {
		
		RatingCriteria criteria =ratingCriteriaService.getRatingCriteria_Id(id);
		boolean result = ratingCriteriaService.delete(criteria);
		String success = null;
		if(result){
			success = "2";
		}
			model.addAttribute("id", categoryId);
			return "redirect:ratingCriteria.htm?success="+success;
	}

	@RequestMapping(value={"editCriteria.htm"},method=RequestMethod.GET)
	public String editCriteria1(HttpServletRequest request,Model model,@RequestParam("id") int id){
		RatingCriteria criteria = ratingCriteriaService.getRatingCriteria_Id(id);
		RatingCriteriaForm form = new RatingCriteriaForm();
		form.setName(criteria.getName());
		form.setId(criteria.getId());
		form.setDescription(criteria.getDescription());
		form.setPriority(criteria.getPriority());
		model.addAttribute("id",id);
		model.addAttribute("criteriaForm", form);
		model.addAttribute("criteria", criteria);
		
		return "ratingCriteria/editCriteria";
}
	
	@RequestMapping(value={"editCriteria.htm"}, method=RequestMethod.POST)
	public String updateCriteria1(Model model, @ModelAttribute("criteria") RatingCriteriaForm form, 
			@RequestParam("parentId") int parentId){
		
		RatingCriteria criteria=ratingCriteriaService.getRatingCriteria_Id(form.getId());
		criteria.setName(form.getName().trim());
		criteria.setId(form.getId());
		criteria.setDescription(form.getDescription());
		criteria.setPriority(form.getPriority());
		
		boolean result = ratingCriteriaService.update(criteria);
		String success = null;
		if(result){
			success = "3";
		}
		model.addAttribute("id", parentId);

		return "redirect:ratingCriteria.htm?success="+success;
		
	}
	@RequestMapping(value = { "deleteCriteriaCategory.htm" }, method = RequestMethod.GET)
	public String deleteCriteriaCategory(HttpServletRequest request, Model model,
			@RequestParam("id") int id) {
		model.addAttribute("id", id);
		RatingCriteria criteria = ratingCriteriaService.getRatingCriteria_Id(id);
		criteria.setStatus(Status.INACTIVE.getValue());
		boolean result = ratingCriteriaService.delete(criteria);
		String success = null;
		if(result){
			success = "2";
		}
		return "redirect:ratingCriteriaCategory.htm?success="+success;
	}

	@RequestMapping(value={"editCriteriaCategory.htm"},method=RequestMethod.GET)
	public String editCriteria(HttpServletRequest request,Model model,@RequestParam("id") int id){
		RatingCriteria criteria = ratingCriteriaService.getRatingCriteria_Id(id);
		RatingCriteriaForm form=new RatingCriteriaForm();
		form.setName(criteria.getName());
		form.setId(criteria.getId());
		form.setDescription(criteria.getDescription());
		
		model.addAttribute("criteria",form);
		
		return "ratingCriteria/editCriteriaCategory";
}
	
	@RequestMapping(value={"editCriteriaCategory.htm"},method=RequestMethod.POST)
	public String updateCriteria(HttpServletRequest request,Model model,@ModelAttribute("criteria")RatingCriteriaForm form){
		RatingCriteria criteria=ratingCriteriaService.getRatingCriteria_Id(form.getId());
		criteria.setName(form.getName().trim());
		criteria.setId(form.getId());
		criteria.setDescription(form.getDescription());
		boolean result = ratingCriteriaService.update(criteria);
		String success = null;
		if(result){

			success = "3";
		}
		return "redirect:ratingCriteriaCategory.htm?success="+success;
		
	}
	/*
	 * this method is used to search the criteria
	 */
	@RequestMapping(value = { "searchCriteriaCategory.htm" }, method = { RequestMethod.POST })
	public String searchCriteriaCategory(@ModelAttribute("ratingCriteriaForm") RatingCriteriaForm form, Model model) {
		List<RatingCriteria> criteria = ratingCriteriaService.searchCriteria_Name(form.getCriteriaSearchVal());
		model.addAttribute("criteria", criteria);
		model.addAttribute("ratingCriteriaForm", form);
		return "ratingCriteria/ratingCriteriaCategory";
	}
	
	@RequestMapping(value = "previewCriteriaCategory.htm", method = { RequestMethod.GET })
	public String previewCriteriaCategory(HttpServletRequest request, Model model,@RequestParam("id") int id) {
		RatingCriteriaForm form = new RatingCriteriaForm();
		RatingCriteria criteria=ratingCriteriaService.getRatingCriteria_Id(id);
		model.addAttribute("priority", criteria.getPriority());
		model.addAttribute ("name", criteria.getName());
		model.addAttribute("description", criteria.getDescription());
		model.addAttribute("ratingCriteriaForm", form);
		model.addAttribute("id", id);
		return "ratingCriteria/previewCriteria";
	}
	
	@RequestMapping(value="existsPriority.htm",method={RequestMethod.GET})
	public @ResponseBody String existsPriority(@RequestParam("priority") int priority, @RequestParam("parentId") int parentId, @RequestParam("id") int id){
		List<RatingCriteria> criteria = ratingCriteriaService.getRatingCriteria_Priority(priority, parentId, id);
		//System.out.println("in criteria"+criteria);
		boolean exists = false;
		if(criteria!=null && criteria.size() != 0) {
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
	
	@RequestMapping(value="existsCriteria.htm", method={RequestMethod.GET})
	public @ResponseBody String existsCriteria(@RequestParam("name") String name, @RequestParam("id") int id){
		RatingCriteria criteria = ratingCriteriaService.getRatingCriteria_Name_Id(name, id);
		boolean exists=false;
		if(criteria!=null ){
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
	
	@RequestMapping(value="existsCriteriaCategories.htm",method={RequestMethod.GET})
	public @ResponseBody String existsCriteriaCategories(@RequestParam("name") String name){
		List<RatingCriteria> categories=ratingCriteriaService.getRatingCriteriaCategories_Name(name);
		boolean exists=false;
		if(categories!=null && categories.size()!=0){
			exists=true;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("exists", exists);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();	
	}
	
	@RequestMapping(value = "getDescription.htm" , method = RequestMethod.GET)
	public @ResponseBody String getDescription_CriteriaId(Model model, @RequestParam("id") int criteriaId) {
		String response = ratingCriteriaService.getCriteriaDetails_Id(criteriaId);
		return response;
	}
	
}
