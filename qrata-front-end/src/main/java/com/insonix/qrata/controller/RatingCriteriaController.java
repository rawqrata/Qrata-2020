package com.insonix.qrata.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.SiteReviewRatingCriteriaVoting;
import com.insonix.qrata.service.RatingCriteriaService;
import com.insonix.qrata.service.SiteReviewService;

/**
 * @author Gurminder Singh
 * 
 * @date 19-Jun-2013
 */

@Controller("ratingCriteriaController")
@RequestMapping(value = "criterias")
public class RatingCriteriaController {

	@Autowired
	SiteReviewService siteReviewService;
	
	@Autowired
	RatingCriteriaService ratingCriteriaService;

//	@RequestMapping(value = { "ratingCriteria.htm" }, method = RequestMethod.GET)
//	public String getRatingCriteria(Model model,
//			@RequestParam("id") long id, HttpServletRequest request) {
//		SiteReviewForm siteReview = siteReviewService
//				.getSiteReviewRatingCriteria_id(id);
//		model.addAttribute("hideDiv", true);
//		model.addAttribute("siteReview", siteReview);
//		return "rating/ratingCriterias";
//	}
	@RequestMapping(value = {"ratingCriteria.htm"} , method = {RequestMethod.GET})
	public String getCriteriaCategory(HttpServletRequest request) {		
		List<RatingCriteria> criteria = ratingCriteriaService.findAllParentCriteria();
		request.setAttribute("criteria", criteria);
				return "rating/ratingCriterias";
	}

	@RequestMapping(value = "siteReviewRatingCriteria.htm", method = { RequestMethod.GET })
	public String findRatingCriteriaBySiteReview(Model model,
			@RequestParam("id") long id, HttpServletRequest request) {

		SiteReviewForm siteReview = siteReviewService.getSiteReviewRatingCriteria_id(id);
		SiteReviewForm siteReviewForm = siteReviewService.getSiteReviewRatingCriteriaVotingPercentage(siteReview);
		model.addAttribute("hideDiv", true);
		model.addAttribute("siteReview", siteReviewForm);
		return "rating/ratingCriteriaDetail";
	}

	@RequestMapping(value = "ratingCriteriaVote.htm", method = { RequestMethod.GET })
	public String findRatingCriteriaVote(Model model,
			@RequestParam("id") long id, HttpServletRequest request) {

		SiteReviewForm siteReview = siteReviewService
				.getSiteReviewRatingCriteria_id(id);
		model.addAttribute("hideDiv", true);
		model.addAttribute("siteReview", siteReview);
		return "rating/ratingCriteriaVote";
	}
	
	@RequestMapping(value="saveRatingCriteriaUserVote.htm" , method={RequestMethod.POST})
	public String saveUserVote(HttpServletRequest request , Model model, @RequestParam Map<String,String> allRequestParams, @RequestParam("srrcId")Set<Long> ssrcIds){
		String id= request.getParameter("id");
		List<SiteReviewRatingCriteriaVoting> criteriaVotings = new ArrayList<>();

		for(long ssrcId: ssrcIds){
			if(allRequestParams.get("radio"+ssrcId) != null){
				SiteReviewRatingCriteriaVoting criteriaVoting = new SiteReviewRatingCriteriaVoting();
				criteriaVoting.setSiteReviewRatingCriteria(siteReviewService.getSiteReviewRatingCriteriaById(ssrcId));
				criteriaVoting.setCriteriaVotingDecision(Short.parseShort(allRequestParams.get("radio"+ssrcId)));
				criteriaVotings.add(criteriaVoting);
				//System.out.println("SSRC ID : "+ssrcId+" ----- "+allRequestParams.get("radio"+ssrcId));
			}
		}
		siteReviewService.addSiteReviewRatingCriteriaVotings(criteriaVotings);
		model.addAttribute("id", id);
		return "redirect:siteReviewRatingCriteria.htm";
	}
}