package com.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qrata.entity.RatingCriteriaForm;
import com.qrata.models.RatingCriteria;
import com.qrata.service.RatingCriteriaService;

@Controller("ratingCriteriaController")
@RequestMapping("admin")
public class RatingCriteriaController {
	
	
	@Autowired
	private RatingCriteriaService ratingCriteriaService;
	
	@RequestMapping(value = {"ratingCriteriaCategory.htm"} , method = {RequestMethod.GET})
	public String getCriteriaCategory(HttpServletRequest request, Model model) {		
		List<RatingCriteria> criteria = ratingCriteriaService.findAllParentCriteria();
		request.setAttribute("criteria", criteria);
		RatingCriteriaForm form = new RatingCriteriaForm();
		model.addAttribute("ratingCriteriaForm", form);
		return "ratingCriteria/ratingCriteriaCategory";
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
	

}
