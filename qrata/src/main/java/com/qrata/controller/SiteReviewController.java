package com.qrata.controller;

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

import com.qrata.entity.ItemDetailForm;
import com.qrata.entity.SiteReviewForm;
import com.qrata.service.SiteReviewService;
import com.qrata.service.TopicService;

@Controller("siteReviewController")
@RequestMapping(value="reviews")
public class SiteReviewController {
	
	
	@Autowired 
	private SiteReviewService siteReviewService;

	@Autowired 
	private TopicService topicService;

	
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
//		List<SiteReviewForm> reviewsList = null;
		List<ItemDetailForm> reviewsList = null;
//		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");		
//		int totalReviews = 0;
		if(keyword != null ){
//			totalReviews = siteReviewService.getQrataSearchCount_Keyword(keyword);
			reviewsList = siteReviewService.qrataSearch_Keywordnew(keyword, 0, 100);
		}
		
		model.addAttribute("q", keyword);
//		model.addAttribute("totalReviews", totalReviews);
		model.addAttribute("reviewsList", reviewsList);
		model.addAttribute("hideDiv", true);
		return "siteReviews/qrataSearchByKeyword";
	}
	
	
	@RequestMapping(value="findSiteReviewByTopicDetail.htm", method={RequestMethod.GET})
	public String findSiteReviewDetail(Model model, @RequestParam("id") long id, HttpServletRequest request) {		

        SiteReviewForm siteReview = siteReviewService.getSiteReviewForm_Id(id);

        model.addAttribute("hideDiv", true)
                .addAttribute("siteReview", siteReview);

        return "sites/listSiteReviewDetail";
	}
	
}
