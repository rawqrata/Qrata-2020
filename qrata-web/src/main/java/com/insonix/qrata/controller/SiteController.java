package com.insonix.qrata.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.entity.SiteReviewCommentsForm;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.entity.TopicForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.RatingCriteriaService;
import com.insonix.qrata.service.SiteReviewCommentsService;
import com.insonix.qrata.service.SiteReviewsRatingCriteriaService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.SitesService;
import com.insonix.qrata.service.TopicExpertAssignmentService;
import com.insonix.qrata.service.TopicRatingCriteriaService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.QrataImagesUtil;
import com.insonix.qrata.utility.Utility;

@Controller("siteController")
@RequestMapping("admin")
public class SiteController extends BaseController {	

	@Autowired
	ServletContext servletContext;
	@Autowired
	SitesService sitesService;	
	@Autowired
	CategoryService categoryService;
	@Autowired
	TopicService topicService;
	@Autowired
	SiteReviewsRatingCriteriaService siteReviewsRatingCriteriaService;
	@Autowired
	RatingCriteriaService ratingCriteriaService;
	@Autowired
	SiteReviewsService siteReviewsService;
	@Autowired
	TopicRatingCriteriaService topicRatingCriteriaService;
	@Autowired
	UserService userService;
	@Autowired
	TopicExpertAssignmentService topicExpertAssignmentService;
	@Autowired
	SiteReviewCommentsService siteReviewCommentsService;
	@Autowired
	ExpertService expertService;
		
	/**
	 * 
	 * @param request
	 * @param model
	 * @param form
	 * @return
	 */
	@RequestMapping(value="listSites.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String listSites(HttpServletRequest request, Model model, @ModelAttribute("siteForm") AddSiteForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "site");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "site");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "site");
		
		int totalSites = sitesService.getTotalSites(Utility.replaceSpecialCharacters(form.getSiteSearchVal()));	
		List<AddSiteForm> sites = sitesService.getAllActiveSites(start, PaginationUtility.pageSize, 
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
	
	
	@RequestMapping(value="editSite.htm",method={ RequestMethod.GET })
	public String editSite(HttpServletRequest request,Model model,@RequestParam("id")String uuid, @RequestParam(value="prp")String prp,
			@RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, @RequestParam("sunrp")String sunrp) {		
		Site site = sitesService.getSite(uuid);
		Category category = site.getCategory();
		AddSiteForm form = new AddSiteForm(site.getName(),site.getUrl(),category.getParentCategory().getId(),site.getUuid());
		
		form.setId(String.valueOf(site.getId()));
		form.setImageName(site.getImageName());
		Topic topic = site.getTopics().get(0);
		
		form.setDomainId(category.getParentCategory().getId());
		form.setDomainName(category.getParentCategory().getName());
		form.setCategoryId(site.getCategory().getId());
		form.setCategoryName(site.getCategory().getName());
		form.setSubCategoryId(topic.getCategory().getId());
		form.setSubCategoryName(topic.getCategory().getName());
		if(topic.getParentTopic() == null){
			form.setTopicId(topic.getId());
			form.setTopicName(topic.getName());
		}else{
			form.setTopicId(topic.getParentTopic().getId());
			form.setTopicName(topic.getParentTopic().getName());
			form.setSubTopicId(topic.getId());
			form.setSubTopicName(topic.getName());
		}
		
		if(topic.getParentTopic() == null){
			form.setTopicId(topic.getId());
			model.addAttribute("topicId", topic.getId());
		}else{
			form.setTopicId(topic.getParentTopic().getId());
			form.setSubTopicId(topic.getId());
			model.addAttribute("topicId", topic.getParentTopic().getId());
			model.addAttribute("subtopicId", topic.getId());
		}
		
		List<Category> domains = categoryService.getActiveCategories();
		model.addAttribute("domains", domains);
		model.addAttribute("topic", topic);
		model.addAttribute("siteForm", form);
		
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		
		User user = (User) request.getSession().getAttribute("user");
		if(user.getRole().getId() == Roles.EXPERT.getValue()){
			return "sites/editSite_Expert";
		} else {
			return "sites/editSite";
		}
	}
	
	@RequestMapping(value="editSite.htm",method={ RequestMethod.POST })
	public String editSite(HttpServletRequest request,@ModelAttribute("siteForm") AddSiteForm form) {		
		String prp = request.getParameter("prp");
		String orp = request.getParameter("orp");
		String sfrp = request.getParameter("sfrp"); 
		String sunrp = request.getParameter("sunrp");
		
		boolean result = sitesService.update(form);
		String success = null;
		if(result) {
			success = "3";
		} else {
			success = "4";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		if(user.getRole().getId() == Roles.EXPERT.getValue()){
			return "redirect:getContents.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}else{
			return "redirect:listSites.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
	}
		
	@RequestMapping(value = {"checkSiteURL.htm"} , method = RequestMethod.GET)
	public @ResponseBody String  checkSiteURL(@RequestParam("id") int topicId ,@RequestParam("url") String url) {
		boolean check = sitesService.checkSite_URL_Topic(url,topicId);
		
		String result = "NotExist";
		JSONObject obj = new JSONObject();
		try{
			if(! check){
					obj.put("result", result);
			}else{
				result = "Exist";
				obj.put("result", result);
			}
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		return obj.toString();
	}
	
	@RequestMapping(value = {"checkSiteName.htm"} , method = RequestMethod.GET)
	public @ResponseBody String checkSiteName(@RequestParam("id") int topicId , @RequestParam("name") String name) {
		boolean check = sitesService.checkSite_Name_Topic(topicId, name);
		
		String result = "NotExist";
		JSONObject obj = new JSONObject();
		try{
			if(! check){
					obj.put("result", result);
			}else{
				result = "Exist";
				obj.put("result", result);
			}
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		return obj.toString();
	}
	
	@RequestMapping(value="addSite.htm", method = { RequestMethod.GET })
	public String addSite (HttpServletRequest request,Model model) {		
		List<Category> domains = categoryService.getActiveDomains();
		AddSiteForm siteForm = new AddSiteForm();		
		model.addAttribute("domains", domains);
		model.addAttribute("siteForm", siteForm);
		return "sites/addSite";
	}
	
	@RequestMapping(value = "addSite_Expert.htm" , method = RequestMethod.GET)
	public String addSiteExpert(Model model,@RequestParam("topicId") int topicId ,@RequestParam("categoryId") int categoryId,
			@RequestParam(value = "flag" , defaultValue = "0") int flag, HttpServletRequest request) {		
		AddSiteForm siteForm=new AddSiteForm();
		siteForm.setCategoryId(categoryId);
		siteForm.setTopicId(topicId);
		Topic topic = topicService.getTopics_Id(topicId);
		
		model.addAttribute("topic", topic);
		model.addAttribute("siteForm", siteForm);
		model.addAttribute("flag", flag);
		return "sites/addSite_Expert";		
	}
	
	@RequestMapping(value = "addContent_Expert.htm" , method = RequestMethod.GET)
	public String addContentExpert(Model model, HttpServletRequest request) {
		AddSiteForm siteForm = new AddSiteForm();
		User user = (User) request.getSession().getAttribute("user");
		List<Category> assignedDomainToExpert = sitesService.getAssignedDomainToExpert(user.getId());
		model.addAttribute("domains", assignedDomainToExpert);
		model.addAttribute("siteForm", siteForm);
		return "sites/addContent_Expert";
	}
	
	@RequestMapping(value="addSite.htm",method = RequestMethod.POST)
	public String saveSite(HttpServletRequest request,@ModelAttribute("siteForm") AddSiteForm form) {	
		User user = (User) request.getSession().getAttribute("user");
		boolean result = sitesService.saveSite(form , user.getId());
		String success = null;
		if(result){
			success = "1";
		}else{
			success = "4";
		}
		return "redirect:/admin/getContents.htm?success="+success;
	}
	
	
	@RequestMapping(value = { "deleteSite.htm" }, method = RequestMethod.GET)
	public String deleteSites(HttpServletRequest request, Model model, @RequestParam("id") String uuid, @RequestParam(value="prp")String prp,
			@RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, @RequestParam("sunrp")String sunrp, @RequestParam(value="gcd", defaultValue="")String gcd) {		
		boolean result = sitesService.delete(uuid);
		String success = null;
		if(result){
			success = "2";
		}else{
			success = "4";
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user.getRole().getId() == Roles.EXPERT.getValue() && gcd.equals("y")){
			return "redirect:getContents.htm?role=3&"+prp+"&"+orp+"&"+sfrp+"&"+sunrp;
		}else if(user.getRole().getId() != Roles.EXPERT.getValue() && gcd.equals("y")){
			return "redirect:getContents.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp;
		}else{
			return "redirect:listSites.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
	}
	
	@RequestMapping(value="unreadSiteRatings.htm", method=RequestMethod.GET)
	public String unreadSiteRatings(Model model, HttpServletRequest request, @RequestParam("topicId") int topicId , @RequestParam("siteId") int siteId, @RequestParam(value = "type" ,defaultValue = "d")  String type)  {
		int checked = Integer.parseInt(request.getParameter("checked"));
		if(checked == 2){
			long sitereviewId = Long.parseLong(request.getParameter("sitereviewId"));
			siteReviewsService.setCheckedStatus(sitereviewId);
		}
		return "redirect:siteCriteriaRatings.htm?topicId="+topicId+"&siteId="+siteId+"&preview=1&view=11&type=c";
	}
	
	@RequestMapping(value="getContents.htm" ,  method= {RequestMethod.GET,RequestMethod.POST})
	public String getSites_UserId(HttpServletRequest request, Model model, @ModelAttribute("siteForm") AddSiteForm form,
			@RequestParam(value = "role" , defaultValue = "0") int role) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "site");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "site");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "site");		
		long userId = 0;
	
		User loginUser = (User) request.getSession().getAttribute("user");
		List<AddSiteForm> sites = null;
		userId = loginUser.getId();
		
		int totalSites = sitesService.getTotalSites_UserId(Utility.replaceSpecialCharacters(form.getSiteSearchVal()),userId);
		sites = sitesService.getActiveSites_UserId(Utility.replaceSpecialCharacters(form.getSiteSearchVal()), 
				start, PaginationUtility.pageSize, userId,sortOrder,sortField);
	
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "site");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "site");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "site");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "site", "s.name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "site", sortField);
		}
		
		 if(sortField != null && sortField.equals("reviewStatus")) {
			Collections.sort(sites, new Comparator<AddSiteForm>() {
				@Override
				public int compare(AddSiteForm arg0, AddSiteForm arg1) {
					if(sortOrder.equals("asc")){
						return arg0.getStatus().compareTo(arg1.getStatus());
					}else{
						return arg1.getStatus().compareTo(arg0.getStatus());
					}
				}				
			});
		}
		
		model.addAttribute("role", role);
		model.addAttribute("siteForm", form);
		model.addAttribute("sites", sites);
		model.addAttribute("totalSites", totalSites);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "sites/rateSites";
	}
	
	@RequestMapping(value="siteCriteriaRatings.htm", method=RequestMethod.GET)
	public String getSiteRatingCriteria(Model model, HttpServletRequest request, @RequestParam("topicId") int topicId , @RequestParam("siteId") int siteId
			, @RequestParam("preview") int preview ,@RequestParam(value = "type" ,defaultValue = "d")  String type,@RequestParam( value = "view" , defaultValue = "0") int view)  {
		Topic topic = topicService.getTopics_Id(topicId);
		Site site = sitesService.getSites_Id(siteId);
		User loginUser = (User) request.getSession().getAttribute("user");
		
		String returnView = "";
		if(loginUser.getRole().getId() == Roles.EXPERT.getValue() && preview == 1) {
			returnView = "sites/siteCriteriaRatings";
			if(view == 1 || view == 2) {
				model.addAttribute("view",view);
			}
		} else if( !(loginUser.getRole().getId() == Roles.EXPERT.getValue()) && preview == 0 ) {
			if(view == 1 ) {
				model.addAttribute("view",view);
			}
			returnView = "sites/siteCriteriaRatings";
			
		} else if(view == 11) {
			returnView = "sites/viewPublishedReviews";
			
		} else {
			returnView = "sites/adminAndEditorSiteCriteriaRatings";
		}
				
		model.addAttribute("site", site);
		List<SiteReviewRatingCriteria> siteReviewRatingCriterias = siteReviewsRatingCriteriaService.getSiteReviewsRatingCriterias(topic, site, loginUser);
		if(siteReviewRatingCriterias.size() != 0) {
			SiteReviewRatingCriteria siteReviewRatingCriteria = siteReviewRatingCriterias.get(0);
			SiteReview siteReview = siteReviewRatingCriteria.getSiteReviews();
			model.addAttribute("siteReview", siteReview);
			model.addAttribute("criteriaList", siteReviewRatingCriterias);
		} else {
			List<SiteReviewRatingCriteria> siteRatingNewCriterias = new ArrayList<SiteReviewRatingCriteria>();
			List<RatingCriteria> criterias = ratingCriteriaService.findAll();
			for(int i = 0 ; i < criterias.size() ; i++) {
				SiteReviewRatingCriteria siteReviewRatingCriteria = new SiteReviewRatingCriteria();
				siteReviewRatingCriteria.setRatingCriteria(criterias.get(i));
				siteReviewRatingCriteria.setWeight(0);
				siteRatingNewCriterias.add(siteReviewRatingCriteria);
			}
			model.addAttribute("siteReview", new SiteReview());
			model.addAttribute("criteriaList",siteRatingNewCriterias);
		}
		List<RatingCriteria> parentCriteriaList = ratingCriteriaService.findAllParentCriteria();
		model.addAttribute("parentCriteriaList", parentCriteriaList);
		model.addAttribute("type", type);
		
		return returnView;
	}
	
	@RequestMapping(value="saveSiteCriteriaRatings.htm" , method=RequestMethod.POST)
	public String saveSiteRaingCriteria(HttpServletRequest request, Model model, @RequestParam("ratings") int[] ratings ,@RequestParam("criteriaId") int[] criteriaId,
			@RequestParam("siteRatingId") int[] siteRatingId , @RequestParam("topicId") int topicId ,@RequestParam("siteId") int siteId, @RequestParam("siteReviewId") int siteReviewId,
			@RequestParam("description") String description,@RequestParam("evaluation") String evaluation ,
			@RequestParam("strength") String strength, @RequestParam("weakness") String weakness , @RequestParam(value = "redirect", defaultValue = "1") int redirect ) {
		
		User loginUser = (User) request.getSession().getAttribute("user");
		String status = request.getParameter("submitRatings");
		boolean result = false;
		String returnView = "redirect:/admin/getContents.htm?success=";
		if(loginUser.getRole().getId() == Roles.EXPERT.getValue() && redirect == 1){
			returnView = "redirect:/admin/getReviewsByStatus.htm?reviewByStatus=";
		}else if (loginUser.getRole().getId() != Roles.EXPERT.getValue() && redirect == 1){
			returnView = "redirect:/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=";
		}else if(loginUser.getRole().getId() != Roles.EXPERT.getValue() && redirect == 0){
			returnView = "redirect:/admin/getReviewsByStatus.htm?reviewByStatus=";
		}
		if(status.trim().equals("Save & Continue")){
			result = siteReviewsRatingCriteriaService.saveSiteReviewsRatingCriteria(ratings, criteriaId, siteRatingId, topicId, 
					siteId, siteReviewId, description, evaluation, loginUser,weakness,strength,ReviewStatus.INPROGRESS.getValue(),loginUser.getId());
			returnView =  "redirect:/admin/siteCriteriaRatings.htm?siteId="+siteId+"&topicId="+topicId+"&preview=1&success=1";
		}
		else if(status.trim().equals("Submit to Editor")){
			result = siteReviewsRatingCriteriaService.saveSiteReviewsRatingCriteria(ratings, criteriaId, siteRatingId, topicId, 
					siteId, siteReviewId, description, evaluation, loginUser,weakness,strength,ReviewStatus.APPROVEL.getValue(),loginUser.getId());
			returnView += ReviewStatus.APPROVEL.getValue();
		}
		else if(status.trim().equals("Rework")){
			result = siteReviewsRatingCriteriaService.saveSiteReviewsRatingCriteria(ratings, criteriaId, siteRatingId, topicId, 
					siteId, siteReviewId, description, evaluation, loginUser,weakness,strength,ReviewStatus.REVISE.getValue(),loginUser.getId());
			returnView += ReviewStatus.REVISE.getValue();
		}
		else if(status.trim().equals("Save & Edit Later")){
			result = siteReviewsRatingCriteriaService.saveSiteReviewsRatingCriteria(ratings, criteriaId, siteRatingId, topicId, 
					siteId, siteReviewId, description, evaluation, loginUser,weakness,strength,ReviewStatus.INPROGRESS.getValue(),loginUser.getId());
			returnView += ReviewStatus.INPROGRESS.getValue();
		}
		else{
			result = siteReviewsRatingCriteriaService.saveSiteReviewsRatingCriteria(ratings, criteriaId, siteRatingId, topicId, 
					siteId, siteReviewId, description, evaluation, loginUser,weakness,strength,ReviewStatus.ONLINE.getValue(),loginUser.getId());
			returnView += ReviewStatus.ONLINE.getValue();
		}
		
		@SuppressWarnings("unused")
		String success = null;
		if(result){
			success = "5";
		}else{
			success = "4";
		}
 		return returnView;
	}

	@RequestMapping(value="contentOffline.htm" , method=RequestMethod.GET)
	public String contentOffline(HttpServletRequest request, Model model,  @RequestParam("siteReviewId") long siteReviewId){
		boolean result = siteReviewsService.setContentStatusOffline(ReviewStatus.OFFLINE, siteReviewId);
		return "redirect:/admin/findOfflineContents.htm";
	}
	
				/* Methods To Find Ratings */
	
	@RequestMapping(value="findNewSiteRatings.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String findNewSiteRatings(Model model, HttpServletRequest request, @ModelAttribute("siteForm") AddSiteForm siteForm) {
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(siteForm.getSiteSearchVal());
	
		int totalRatings = siteReviewsService.getTotalNewRatings(name);
		List<SiteReviewForm> siteReviewList = siteReviewsService.findNewSiteRatings(start, PaginationUtility.pageSize, 
				sortColumn , sortOrder, name);
		
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
		
		int totalRatings = siteReviewsService.getTotalRatings_Name(name);
		List<SiteReviewForm> siteReviewList = siteReviewsService.findContentsByName(name, start, PaginationUtility.pageSize, sortColumn , sortOrder);
		
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("siteForm", siteForm);
		model.addAttribute("totalRatings", totalRatings);
		return "sites/findContentsByName";
	}
	
	@RequestMapping(value="findContentsByTopic.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String findContentsByTopic(Model model, @RequestParam("id") int topicId, HttpServletRequest request,
			@ModelAttribute("siteForm") AddSiteForm form, HttpServletResponse response) {
		Topic topic = topicService.getTopics_Id(topicId);
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(form.getSiteSearchVal());
		
		int totalRatings = siteReviewsService.getTotalRatings_TopicId(topicId, name);
		List<SiteReviewForm> siteReviewList = siteReviewsService.findContentsByTopic(name, topicId, start, PaginationUtility.pageSize, 
				sortColumn, sortOrder);
		
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("topic", topic);
		model.addAttribute("totalRatings", totalRatings);
		
		String msg = request.getParameter("m");
		if(StringUtils.isNotEmpty(msg)) {
			Cookie activeTab = new Cookie("activeTab", "1");
			activeTab.setPath("/");
			
			Cookie activeLink = null;
			if(topic.getTopicType().equals(Topics.TOPIC.getValue())) {
				activeLink = new Cookie("activeLink", "findByTopicLink");
			} else {
				activeLink = new Cookie("activeLink", "findBySubTopicLink");
			}
			activeLink.setPath("/");
			
			response.addCookie(activeTab);
			response.addCookie(activeLink);
			request.setAttribute("msg", msg);
		}
		return "sites/findContentsByTopic";
	}
	
	@RequestMapping(value="findOfflineContents.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String findOfflineContents(Model model, HttpServletRequest request, @ModelAttribute("siteForm") AddSiteForm form) {
		
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(form.getSiteSearchVal());
		
		int totalRatings = siteReviewsService.getTotal_AllContentByReviewStatus(name, ReviewStatus.OFFLINE);
		List<SiteReviewForm> siteReviewList = siteReviewsService.getAllContentsByReviewStatus(name,ReviewStatus.OFFLINE, start, PaginationUtility.pageSize, 
				sortColumn, sortOrder);
		
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("totalRatings", totalRatings);
		return "sites/findOfflineContents";
	}
	
	@RequestMapping(value="findAllContentsByTopic.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String findAllContentsByTopic(Model model, @RequestParam("id") int topicId, HttpServletRequest request, @RequestParam(value = "type1" ,defaultValue = "d")  String type1 
			,@ModelAttribute("siteForm") AddSiteForm form, @RequestParam(value = "roleId" , defaultValue = "0") short roleId) {
		Topic topic = topicService.getTopics_Id(topicId);
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(form.getSiteSearchVal());
		
		int totalRatings = sitesService.getTotalAllRatings_TopicId(topicId, name);
		List<SiteReviewForm> siteReviewList = sitesService.findAllContentsByTopic(name, topicId, start, PaginationUtility.pageSize, 
				sortColumn, sortOrder, roleId);
	
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("topic", topic);
		model.addAttribute("totalRatings", totalRatings);
		model.addAttribute("type1", type1);		
			/*
			 * Set Return Parameters
			 */
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "site");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "site");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "site");
		String sortFieldRequestParam = "";
		if(sortColumn == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "site", "s.name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "site", sortColumn);
		}
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "sites/findAllContentsByTopic";
	}
	
	@RequestMapping(value="allContentsByTopic.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String allContentsByTopic(Model model, @RequestParam("id") int topicId, HttpServletRequest request,
			@ModelAttribute("siteForm") AddSiteForm form, @RequestParam(value = "roleId" , defaultValue = "0") short roleId) {
		Topic topic = topicService.getTopics_Id(topicId);
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(form.getSiteSearchVal());
		
		int totalRatings = sitesService.getAllContentRatings_TopicId(topicId, name);
		List<SiteReviewForm> siteReviewList = sitesService.allContentsByTopic(name, topicId, start, PaginationUtility.pageSize, 
				sortColumn, sortOrder, roleId);
		
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("topic", topic);
		model.addAttribute("totalRatings", totalRatings);
		return "sites/allContentsByTopic";
	}
	
	@RequestMapping(value="findContentsByExpert.htm", method={RequestMethod.GET, RequestMethod.POST})
	public String findContentsByExpert(Model model, @RequestParam("id") long expertId, HttpServletRequest request,
			@ModelAttribute("siteForm") AddSiteForm form) {
		User user = userService.getUser_Id(expertId);
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReview");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		String name = Utility.replaceSpecialCharacters(form.getSiteSearchVal());
		
		int totalRatings = siteReviewsService.getTotalRatings_ExpertId(expertId, name);
		List<SiteReviewForm> siteReviewList = siteReviewsService.findContentsByExpert(expertId, start, PaginationUtility.pageSize,
				sortColumn , sortOrder, name);
		
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("user", user);
		model.addAttribute("totalRatings", totalRatings);
		return "sites/findContentsByExpert";
	}
	
	@RequestMapping(value="getExperts.htm", method={RequestMethod.GET,RequestMethod.POST})
	public String getExperts(Model model,HttpServletRequest request, @ModelAttribute("userForm") AddUserForm userForm) {
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "expert");
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "expert");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "expert");
		String name = userForm.getUserSearchVal();
		
		int totalExpert = userService.getTotalUser_Role(name,Roles.EXPERT);
		List<AddUserForm> experts = expertService.getSortingExpert(name,sortField,sortOrder,start,PaginationUtility.pageSize);
		
		model.addAttribute("experts", experts);
		model.addAttribute("userForm", userForm);
		model.addAttribute("totalExpertSize",totalExpert);
		
		return "sites/getExperts";
	}	
	
	
	@RequestMapping(value="getTopics.htm", method={RequestMethod.GET,RequestMethod.POST})
	public String getTopics(Model model, HttpServletRequest request, @ModelAttribute("topicForm") TopicForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
		String name = Utility.replaceSpecialCharacters(form.getTopicSearchVal());
		
		int totalTopics = topicService.getTotalTopics(name);
		List<TopicForm> topicsList = topicService.getActiveTopics(name,start, PaginationUtility.pageSize, sortField, sortOrder);
		
		model.addAttribute("topicList", topicsList);
//		model.addAttribute("totalRatings", totalRatings);
		model.addAttribute("totalTopics", totalTopics);
		model.addAttribute("topicForm", form);
		return "sites/getTopics";
	}
	
	@RequestMapping(value="getSubTopics.htm", method={RequestMethod.GET,RequestMethod.POST})
	public String getSubTopics(Model model,HttpServletRequest request, @ModelAttribute("topicForm") TopicForm form) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "subTopic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subTopic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subTopic");
		String name = Utility.replaceSpecialCharacters(form.getTopicSearchVal());
		int totalSubActiveTopics = topicService.getTotalSubTopics(name);
		List<TopicForm> subTopics = topicService.getActiveSubTopics(name, start, PaginationUtility.pageSize, sortField, sortOrder);
		model.addAttribute("topicList",subTopics);
		model.addAttribute("totalSubActiveTopics", totalSubActiveTopics);
		model.addAttribute("topicForm", form);
		return "sites/getSubTopics";
	}
	
				/* Methods For Finding Ratings */
	
	@RequestMapping(value={"assignedSiteTopic.htm"} , method=RequestMethod.GET)
	public String assignedSiteTopic(Model model,@RequestParam("topicId") int topicId, @RequestParam("sitesId") String sitesId){
		String[] sitesIds = sitesId.split(",");
		model.addAttribute("id" , topicId);
		Topic topic = topicService.getTopics_Id(topicId);
		if(! sitesIds[0].equals("empty")){
			List<Site> sitesList = new ArrayList<Site>();
			for(int i = 0 ; i < sitesIds.length ; i++ ){
				sitesList.add(sitesService.getSites_Id(Integer.parseInt(sitesIds[i])));
			}
			
			topic.getSites().removeAll(topic.getSites());
			for(Site site : sitesList){
				topic.getSites().add(site);
			}
			boolean result = topicService.update(topic);
			String success = null;
			
			if(result){
				success = "3";
			}else{
				success = "4";
			}
			return "redirect:topicSites.htm?success="+success;
		}else{
			topic.getSites().removeAll(topic.getSites());
			boolean result = topicService.update(topic);
			String success = null;
			if(result){
				success = "5";
			}else{
				success = "4";
			}
			return "redirect:topicSites.htm?success="+success;
		}
	}
	
	@RequestMapping(value = "getContentAssignedToExpert.htm" , method = {RequestMethod.GET , RequestMethod.POST})
	public String getContentAssignedToExpert(HttpServletRequest request , Model model ,@ModelAttribute("siteForm") AddSiteForm form) {
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "site");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "site");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "site");
		User user = (User) request.getSession().getAttribute("user");
		
		List<AddSiteForm> sites = null;
		int totalSites = sitesService.getTotalSites_Experts(Utility.replaceSpecialCharacters(form.getSiteSearchVal()), user.getId());
		
		sites = sitesService.getActiveSites_Experts(Utility.replaceSpecialCharacters(form.getSiteSearchVal()), 
				start, PaginationUtility.pageSize, user.getId(), sortColumn, sortOrder);
		
		model.addAttribute("siteForm", form);
		model.addAttribute("sites", sites);
		model.addAttribute("totalSites", totalSites);
		
		return "publishing/contentAssignedToExpert";
	}
	
	@RequestMapping(value = "getExpertsContentsByReviewStatus.htm" , method = {RequestMethod.GET , RequestMethod.POST})
	public String getExpertsContentsByReviewStatus(HttpServletRequest request , Model model , @RequestParam("reviewByStatus") short reviewByStatus
			,@ModelAttribute("siteForm") AddSiteForm form) {
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "siteReviewForm");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReviewForm");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReviewForm");
		String name = Utility.replaceSpecialCharacters(form.getSiteSearchVal());
		int totalRatings = 0;
		
		User user = (User) request.getSession().getAttribute("user");
		List<SiteReviewForm> siteReviewForms = null;
		
		short status = 0;
		if(reviewByStatus == 1) {
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus(name, user.getId(), ReviewStatus.ONLINE);
			siteReviewForms = siteReviewsService.getSiteReviews_ReviewStatus(name, start,  PaginationUtility.pageSize , ReviewStatus.ONLINE , 
					user.getId(), sortColumn,sortOrder);
			status = ReviewStatus.ONLINE.getValue();
		} 
		else if(reviewByStatus == 2) {
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus(name, user.getId(), ReviewStatus.APPROVEL);
			siteReviewForms = siteReviewsService.getSiteReviews_ReviewStatus(name, start, PaginationUtility.pageSize ,ReviewStatus.APPROVEL , 
					user.getId(), sortColumn,sortOrder);
			status = ReviewStatus.APPROVEL.getValue();
		}
		else if(reviewByStatus == 3){
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus(name, user.getId(), ReviewStatus.INPROGRESS);
			siteReviewForms = siteReviewsService.getSiteReviews_ReviewStatus(name, start, PaginationUtility.pageSize , ReviewStatus.INPROGRESS ,  
					user.getId(), sortColumn,sortOrder);
			status = ReviewStatus.INPROGRESS.getValue();
		}
		else if(reviewByStatus == 4){
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus(name, user.getId(), ReviewStatus.REVISE);
			siteReviewForms = siteReviewsService.getSiteReviews_ReviewStatus(name, start, PaginationUtility.pageSize , ReviewStatus.REVISE ,  
					user.getId(), sortColumn,sortOrder);
			status = ReviewStatus.REVISE.getValue();
		}
		
		model.addAttribute("siteReviewList", siteReviewForms);
		model.addAttribute("totalRatings", totalRatings);
		model.addAttribute("status",status);
		model.addAttribute("siteForm", form);
		return "publishing/expertsContentsByReviewStatus";
	}
	
	@RequestMapping(value = "getReviewsByStatus.htm" , method = {RequestMethod.GET , RequestMethod.POST})
	public String getReviewsByStatus(HttpServletRequest request , Model model,@RequestParam("reviewByStatus") short reviewByStatus,
			@RequestParam(value = "success" , defaultValue = "1" ) String success ,@ModelAttribute("siteForm") AddSiteForm form) {
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "siteReview");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "siteReview");
		int totalRatings = 0;
		short status = 0;
		String name = Utility.replaceSpecialCharacters(form.getSiteSearchVal());
		
		User user = (User) request.getSession().getAttribute("user");
		List<SiteReviewForm> siteReviewList = null;
		
		if(reviewByStatus == 1) {
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(name,user.getId() , ReviewStatus.ONLINE);
			siteReviewList = siteReviewsService.getSiteReviews_ReviewStatus_UserId(name,start, PaginationUtility.pageSize , ReviewStatus.ONLINE , user.getId(), sortOrder);
			status = ReviewStatus.ONLINE.getValue();
		}else if(reviewByStatus == 2) {
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(name,user.getId() , ReviewStatus.APPROVEL);
			siteReviewList = siteReviewsService.getSiteReviews_ReviewStatus_UserId(name,start, PaginationUtility.pageSize , ReviewStatus.APPROVEL , user.getId(), sortOrder);
			status = ReviewStatus.APPROVEL.getValue();
		}else if(reviewByStatus == 3) {
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(name,user.getId() , ReviewStatus.INPROGRESS);
			siteReviewList = siteReviewsService.getSiteReviews_ReviewStatus_UserId(name,start, PaginationUtility.pageSize , ReviewStatus.INPROGRESS , user.getId(), sortOrder);
			status = ReviewStatus.INPROGRESS.getValue();
		}else if(reviewByStatus == 4) {
			totalRatings = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(name,user.getId() , ReviewStatus.REVISE);
			siteReviewList = siteReviewsService.getSiteReviews_ReviewStatus_UserId(name,start, PaginationUtility.pageSize , ReviewStatus.REVISE , user.getId(), sortOrder);
			status = ReviewStatus.REVISE.getValue();
		}
		
		model.addAttribute("siteReviewList", siteReviewList);
		model.addAttribute("totalRatings", totalRatings);
		model.addAttribute("status", status);
		model.addAttribute("success", success);
		model.addAttribute("siteForm", form);
		return "publishing/reviewsByStatus";
	}

	
		/*
		 * Comment Methods Start
		 */	
	@RequestMapping(value="addComment.htm", method=RequestMethod.POST)
	@ResponseBody	
	public String addComment(HttpServletRequest request, @RequestBody SiteReviewCommentsForm commentsForm) {
		User user = (User) request.getSession().getAttribute("user");
		String str = siteReviewCommentsService.addComment(commentsForm, user);
		return str;
	}
	
	@RequestMapping(value="fetchAllComments.htm", method=RequestMethod.GET)
	@ResponseBody
	public String fetchAllComments(@RequestParam("siteReviewId") long reviewId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String str = siteReviewCommentsService.fetchAllComments(reviewId, user.getId());
		return str;
	}
	
	@RequestMapping(value="getUnreadComments.htm", method=RequestMethod.GET)
	@ResponseBody
	public String getUnreadComments(@RequestParam("siteReviewId") long reviewId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String str = siteReviewCommentsService.getUnreadCommentsCount(reviewId, user.getId());
		return str;
	}
	
	
			/*
			 * Count Methods Start
			 */	
	@RequestMapping(value="fetchCountMyData.htm", method=RequestMethod.GET)
	@ResponseBody
	public String fetchCountMyData(HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = sitesService.fetchCountMyData(loginUser);
		return str;
	}
	
	@RequestMapping(value="fetchCountExpertData.htm", method=RequestMethod.GET)
	@ResponseBody
	public String fetchCount(HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = sitesService.fetchCountExpertData(loginUser);
		return str;
	}
	
	@RequestMapping(value="fetchCountExpert.htm", method=RequestMethod.GET)
	@ResponseBody
	public String fetchCountExpert(HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = sitesService.fetchCountExpert(loginUser);
		return str;
	}
	
		/*
		 * Names Auto Suggestion
		 */
	@RequestMapping(value="autoSuggestSiteByName.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestSiteByName(@RequestParam("name") String name) {
		String str = sitesService.suggestSiteByName(name, 0);
		return str;
	}
	
	@RequestMapping(value="autoSuggestReviewsBySiteName.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestReviewsBySiteName(@RequestParam("name") String siteName, @RequestParam("parentId") long entityId, 
			@RequestParam("type") String type) {
		String str = siteReviewsService.suggestReviewsBySiteName(siteName, type, entityId);
		return str;
	}
	
	@RequestMapping(value="autoSuggestSiteByNameForMyPublishing.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestSiteByNameForMyPublishing(@RequestParam("name") String name,
			HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = sitesService.suggestSiteByName(name, loginUser.getId());
		return str;
	}
	
	@RequestMapping(value="autoSuggestSiteByNameNReviewStatusForMyPublishing.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestSiteByNameNReviewStatusForMyPublishing(@RequestParam("name") String name,
			@RequestParam("type") String type, HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = siteReviewsService.suggestSiteByNameNReviewStatusForMyPublishing(name, loginUser.getId(), 
				type);
		return str;
	}
	
	@RequestMapping(value="autoSuggestSiteByNameForExpertPublishing.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestSiteByNameForExpertPublishing(@RequestParam("name") String name,
			HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = sitesService.suggestSiteByNameForExpertPublishing(name, loginUser.getId());
		return str;
	}
	
	@RequestMapping(value="autoSuggestSiteByNameNReviewStatusForExpertPublishing.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestSiteByNameNReviewStatusForExpertPublishing(@RequestParam("name") String name,
			@RequestParam("type") String type, HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = siteReviewsService.suggestSiteByNameNReviewStatusForExpertPublishing(name, loginUser.getId(), 
				type);
		return str;
	}
	
	@RequestMapping(value="faq.htm",method=RequestMethod.GET)
	public String faq() {
		return "publishing/faq";
	}
	
	@RequestMapping(value = {"checkLeafNode.htm"} , method = RequestMethod.GET)
	public @ResponseBody String  checkLeafNode(@RequestParam("topicId") int topicId ) {
		Topic topic = topicService.getTopics_Id(topicId);
		boolean leafNode = topic.getLeafNode();
		
		JSONObject obj = new JSONObject();
		try{
			obj.put("leafNode", leafNode);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		return obj.toString();
	}
	
		/*
		 * Display Content's Image
		 */
	@RequestMapping(value="getContentImage.htm", method=RequestMethod.GET)
	public void getContentImage(@RequestParam("siteId") long siteId, HttpServletResponse response) {
		OutputStream out  = null;
		try {		
			Site site = sitesService.getSites_Id(siteId);
			if(StringUtils.isNotEmpty(site.getImagePath())) {
				File qrataData = QrataImagesUtil.getQrataDataFolder();
				File contentPicFile = new File(qrataData, site.getImagePath());
				
				out = response.getOutputStream();
				BufferedImage img = ImageIO.read(contentPicFile);
				ImageIO.write(img, "png", out);
				out.flush();
				img.flush();
			}
		} catch (Exception e) {
			try {
				String filePath = servletContext.getRealPath("/resources/images/column-image.gif");
				File contentPicFile = new File(filePath);
				BufferedImage img = ImageIO.read(contentPicFile);
				out = response.getOutputStream();
				
				ImageIO.write(img, "png", out);
				out.flush();
				img.flush();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(out != null)
					out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="removeSiteLogo.htm", method=RequestMethod.GET)
	public @ResponseBody String removeSiteLogo(@RequestParam("id") Long id) {
		String str = sitesService.removeSiteLogo(id);
		return str;
	}
	
	@RequestMapping(value="checkExistContentsInTopic", method=RequestMethod.GET)
	public @ResponseBody String checkExistContentsInTopic(@RequestParam("topicId")int topicId, @RequestParam("contentsId")String contentsIds) {
		Topic topic = topicService.getTopics_Id(topicId);
		List<Site> sites = topic.getSites();
		String result = "";
		String error = "";
		String[] contentsId = contentsIds.split(",");
		
		for(int i=0; i<contentsId.length; i++){
			long siteId = Long.parseLong(contentsId[i]);
			boolean flag = false;
			Site site = null;
			for(Site tempSite : sites){
				site = sitesService.getSites_Id(siteId);
				if(tempSite.getName().trim().equalsIgnoreCase(site.getName().trim())){
					flag = true;
				}
			}
			if(!flag){
				site = sitesService.getSites_Id(siteId);
				result += "<option value="+site.getId()+">"+site.getName().trim()+"("+site.getUrl().trim()+")</option>";
			}else{
				site = sitesService.getSites_Id(siteId);
				error += site.getName().trim()+"("+site.getUrl().trim()+")\n";
			}
		}
		
		JSONObject obj = new JSONObject();
		try{
			obj.put("result", result);
			obj.put("error", error);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		return obj.toString();
	}
	
}
