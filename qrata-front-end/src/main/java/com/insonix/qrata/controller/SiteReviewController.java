package com.insonix.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.SiteReviewService;
import com.insonix.qrata.service.TopicService;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */

@Controller("siteReviewController")
@RequestMapping(value="reviews")
public class SiteReviewController {
	@Autowired SiteReviewService siteReviewService;
	@Autowired TopicService topicService;

	
	@RequestMapping(value="findSiteReviewByTopic.htm", method={RequestMethod.GET})
	public String findSiteReview(Model model, @RequestParam("id") int topicId, HttpServletRequest request) {
		Topic topic = topicService.getTopics_Id(topicId);
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		int totalRatings = siteReviewService.getTotalRatings_TopicId(topicId);
		List<SiteReviewForm> siteReviewList = siteReviewService.findContentsByTopic(topicId, start, PaginationUtility.pageSize);
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("totalRatings", totalRatings);
		model.addAttribute("topic", topic);
		model.addAttribute("hideDiv", true);
		return "sites/listSiteReview";
	}
	
	@RequestMapping(value="findSiteReviewByTopicDetail.htm", method={RequestMethod.GET})
	public String findSiteReviewDetail(Model model, @RequestParam("id") long id, HttpServletRequest request) {		
		SiteReviewForm siteReview = siteReviewService.getSiteReview_id(id);
		model.addAttribute("hideDiv", true);
		model.addAttribute("siteReview", siteReview);
		return "sites/listSiteReviewDetail";
	}
	
			/*
			 * Qrata Search Methods
			 */
	@RequestMapping(value="qrataSearchKeywords.htm", method=RequestMethod.GET)
	@ResponseBody
	public String qrataSearchKeywords(@RequestParam("q") String searchTerm) {
		JSONArray arr = null;
		String str = "";
		if(searchTerm.matches("^[a-zA-Z]+$")) {
			str = siteReviewService.qrataSearchKeywords_SearchTerm(searchTerm);
		} else {
			JSONObject obj = new JSONObject();
			arr = new JSONArray();
			try {
				obj.put("name", "No results available");
				arr.put(obj);
				str = arr.toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	@RequestMapping(value="search.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String qrataSearchByKeyword(HttpServletRequest request, Model model, 
			@RequestParam(value="q", required=false) String keyword) {
		List<SiteReviewForm> reviewsList = null;
//		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");		
//		int totalReviews = 0;
		if(keyword != null && keyword.matches("^[a-zA-Z]+$")){
//			totalReviews = siteReviewService.getQrataSearchCount_Keyword(keyword);
			reviewsList = siteReviewService.qrataSearch_Keyword(keyword, 0, 100);
		}
		
		model.addAttribute("q", keyword);
//		model.addAttribute("totalReviews", totalReviews);
		model.addAttribute("reviewsList", reviewsList);
		model.addAttribute("hideDiv", true);
		return "siteReviews/qrataSearchByKeyword";
	}
			/*
			 * Qrata Search Methods End
			 */
	
			
}
