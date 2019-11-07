package com.qrata.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qrata.entity.AddSiteForm;
import com.qrata.entity.SiteReviewForm;
import com.qrata.models.Category;
import com.qrata.models.Topic;
import com.qrata.models.User;
import com.qrata.service.CategoryService;
import com.qrata.service.SiteReviewService;
import com.qrata.service.SiteService;
import com.qrata.utility.PaginationUtility;
import com.qrata.utility.Utility;

@Controller
@RequestMapping("admin")
public class SiteController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private SiteReviewService siteReviewService;
	
	
	@RequestMapping(value="addSite.htm", method = { RequestMethod.GET })
	public String addSite (HttpServletRequest request,Model model) {		
		List<Category> domains = categoryService.getActiveDomains();
		AddSiteForm siteForm = new AddSiteForm();		
		model.addAttribute("domains", domains);
		model.addAttribute("siteForm", siteForm);
		return "sites/addSite";
	}
	
	@RequestMapping(value="listSites.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String listSites(HttpServletRequest request, Model model, @ModelAttribute("siteForm") AddSiteForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "site");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "site");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "site");
		
		int totalSites =1; /*siteService.getTotalSites(Utility.replaceSpecialCharacters(form.getSiteSearchVal()));*/
		List<AddSiteForm> sites = siteService.getAllActiveSites(start, PaginationUtility.pageSize,
				Utility.replaceSpecialCharacters(form.getSiteSearchVal()),sortField,sortOrder);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "site");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "site");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "site");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "site", "s.name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "site", sortField);
		}
		
		model.addAttribute("siteForm", form);
		model.addAttribute("sites", sites);
		model.addAttribute("totalSites", totalSites);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "sites/listSites";
	}
	
	@RequestMapping(value="findNewSiteRatings.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String findNewSiteRatings(Model model, HttpServletRequest request, @ModelAttribute("siteForm") AddSiteForm siteForm) {
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(siteForm.getSiteSearchVal());
	
		List<SiteReviewForm> siteReviewList = siteReviewService.findNewSiteRatings(start, PaginationUtility.pageSize,
				sortColumn , sortOrder, name);
		int totalRatings=siteReviewList.size();
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("totalRatings", totalRatings);
		model.addAttribute("siteForm", siteForm);
		return "sites/findNewSiteRatings";
	}
	
	@RequestMapping(value="findContentsByName.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String findContentsByName(Model model, @ModelAttribute("siteForm") AddSiteForm siteForm, 
			HttpServletRequest request) {
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(siteForm.getSiteSearchVal());
		
		int totalRatings = siteReviewService.getTotalRatings_Name(name);
		List<SiteReviewForm> siteReviewList = siteReviewService.findContentsByName(name, start, PaginationUtility.pageSize, sortColumn , sortOrder);
		
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("siteForm", siteForm);
		model.addAttribute("totalRatings", totalRatings);
		return "sites/findContentsByName";
	}
	
	@RequestMapping(value="unreadSiteRatings.htm", method=RequestMethod.GET)
	public String unreadSiteRatings(Model model, HttpServletRequest request, @RequestParam("topicId") int topicId , @RequestParam("siteId") int siteId, @RequestParam(value = "type" ,defaultValue = "d")  String type)  {
		int checked = Integer.parseInt(request.getParameter("checked"));
		if(checked == 2){
			long sitereviewId = Long.parseLong(request.getParameter("sitereviewId"));
			siteReviewService.setCheckedStatus(sitereviewId);
		}
		return "redirect:siteCriteriaRatings.htm?topicId="+topicId+"&siteId="+siteId+"&preview=1&view=11&type=c";
	}
	
	/*
	 * @RequestMapping(value="siteCriteriaRatings.htm", method=RequestMethod.GET)
	 * public String getSiteRatingCriteria(Model model, HttpServletRequest
	 * request, @RequestParam("topicId") int topicId , @RequestParam("siteId") int
	 * siteId , @RequestParam("preview") int preview ,@RequestParam(value = "type"
	 * ,defaultValue = "d") String type,@RequestParam( value = "view" , defaultValue
	 * = "0") int view) { Topic topic = topicService.getTopics_Id(topicId); Site
	 * site = siteService.getSite_Id(siteId); User loginUser = getUser();
	 * 
	 * String returnView = ""; if(loginUser.isExpert() && preview == 1) { returnView
	 * = "sites/siteCriteriaRatings"; if(view == 1 || view == 2) {
	 * model.addAttribute("view",view); } } else if( !(loginUser.isExpert()) &&
	 * preview == 0 ) { if(view == 1 ) { model.addAttribute("view",view); }
	 * returnView = "sites/siteCriteriaRatings";
	 * 
	 * } else if(view == 11) { returnView = "sites/viewPublishedReviews";
	 * 
	 * } else { returnView = "sites/adminAndEditorSiteCriteriaRatings"; }
	 * 
	 * model.addAttribute("site", site); List<SiteReviewRatingCriteria>
	 * siteReviewRatingCriterias =
	 * siteReviewsRatingCriteriaService.getSiteReviewsRatingCriterias(topic, site,
	 * loginUser); if(siteReviewRatingCriterias.size() != 0) {
	 * SiteReviewRatingCriteria siteReviewRatingCriteria =
	 * siteReviewRatingCriterias.get(0); SiteReview siteReview =
	 * siteReviewRatingCriteria.getSiteReviews(); model.addAttribute("siteReview",
	 * siteReview); model.addAttribute("criteriaList", siteReviewRatingCriterias); }
	 * else { List<SiteReviewRatingCriteria> siteRatingNewCriterias = new
	 * ArrayList<SiteReviewRatingCriteria>(); List<RatingCriteria> criterias =
	 * ratingCriteriaService.findAll(); for(int i = 0 ; i < criterias.size() ; i++)
	 * { SiteReviewRatingCriteria siteReviewRatingCriteria = new
	 * SiteReviewRatingCriteria();
	 * siteReviewRatingCriteria.setRatingCriteria(criterias.get(i));
	 * siteReviewRatingCriteria.setWeight(0);
	 * siteRatingNewCriterias.add(siteReviewRatingCriteria); }
	 * model.addAttribute("siteReview", new SiteReview());
	 * model.addAttribute("criteriaList",siteRatingNewCriterias); }
	 * List<RatingCriteria> parentCriteriaList =
	 * ratingCriteriaService.findAllParentCriteria();
	 * model.addAttribute("parentCriteriaList", parentCriteriaList);
	 * model.addAttribute("type", type);
	 * 
	 * return returnView; }
	 */
	

	

}
