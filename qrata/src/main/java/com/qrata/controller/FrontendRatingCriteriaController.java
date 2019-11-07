package com.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qrata.entity.SiteReviewForm;
import com.qrata.models.RatingCriteria;
import com.qrata.service.RatingCriteriaService;
import com.qrata.service.SiteReviewService;
import com.qrata.service.SiteReviewsRatingCriteriaService;

@Controller
@RequestMapping(value = "criterias")
public class FrontendRatingCriteriaController {
	

	@Autowired
    private SiteReviewService siteReviewService;
	
	@Autowired
	private RatingCriteriaService ratingCriteriaService;
	
    @Autowired
    private SiteReviewsRatingCriteriaService siteReviewsRatingCriteriaService;
	
	@RequestMapping(value = {"ratingCriteria.htm"} , method = {RequestMethod.GET})
	public String getCriteriaCategory(HttpServletRequest request) {		
		List<RatingCriteria> criteria = ratingCriteriaService.findAllParentCriteria();
		request.setAttribute("criteria", criteria);
				return "rating/ratingCriterias";
	}
	

	@RequestMapping(value = "siteReviewRatingCriteria.htm", method = { RequestMethod.GET })
	public String findRatingCriteriaBySiteReview(Model model,
			@RequestParam("id") long id, HttpServletRequest request) {

		SiteReviewForm siteReview = siteReviewsRatingCriteriaService.getSiteReviewsRatingCriteriaForm_Id(id);
		SiteReviewForm siteReviewForm = siteReviewService.getSiteReviewRatingCriteriaVotingPercentage(siteReview);
		model.addAttribute("hideDiv", true);
		model.addAttribute("siteReview", siteReviewForm);
		return "rating/ratingCriteriaDetail";
	}
	
	
	@RequestMapping(value = "ratingCriteriaVote.htm", method = { RequestMethod.GET })
	public String findRatingCriteriaVote(Model model,
			@RequestParam("id") long id, HttpServletRequest request) {

		SiteReviewForm siteReview = siteReviewService.getSiteReviewRatingCriteria_id(id);
		model.addAttribute("hideDiv", true);
		model.addAttribute("siteReview", siteReview);
		return "rating/ratingCriteriaVote";
	}

}
