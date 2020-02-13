package com.insonix.qrata.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.entity.TopicForm;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicRatingCriteria;
import com.insonix.qrata.models.User;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.RatingCriteriaService;
import com.insonix.qrata.service.SiteReviewsRatingCriteriaService;
import com.insonix.qrata.service.SitesService;
import com.insonix.qrata.service.TopicExpertAssignmentService;
import com.insonix.qrata.service.TopicRatingCriteriaService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 * 
 */
@Controller
@RequestMapping("admin")
public class TopicController {

	@Autowired
	TopicService topicService;
	@Autowired
	ExpertService expertService;
	@Autowired
	SitesService sitesService;
	@Autowired
	RatingCriteriaService ratingCriteriaService; 
	@Autowired
	TopicRatingCriteriaService topicRatingCriteriaService;
	@Autowired
	UserService userService;
	@Autowired
	TopicExpertAssignmentService topicExpertAssignmentService;
	@Autowired
	SiteReviewsRatingCriteriaService siteReviewRatingCriteriaService;
	
	/**
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "listTopics.htm" }, method = { RequestMethod.GET , RequestMethod.POST})
	public String getTopics(HttpServletRequest request,Model model, @RequestParam("id") String id ,
			@ModelAttribute("topicForm") TopicForm form ) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
		String name = Utility.replaceSpecialCharacters(form.getTopicSearchVal());
		
		Integer subCategoryId = Integer.parseInt(id.trim());
		int totalActiveTopics = topicService.getTotalActiveTopic_Type(name, Constants.Topics.TOPIC,subCategoryId,null);
		List<TopicForm> topicsForm = topicService.getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount(name,
				subCategoryId, PaginationUtility.pageSize, sortField, sortOrder,start);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "topic");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "topic");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "topic");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "topic", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "topic", sortField);
		}
				
		model.addAttribute("topicList", topicsForm);
		model.addAttribute("totalActiveTopics", totalActiveTopics);
		model.addAttribute("topicForm", form);
		model.addAttribute("id", id);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		
		return "topics/listTopics";

	}

	@RequestMapping(value={"editTopic.htm","editSubTopic.htm"},method=RequestMethod.GET)
	public String updateTopic(HttpServletRequest request,Model model,@RequestParam("id") String uuid,@RequestParam(value="subCatId")Integer subCatId,
			@RequestParam(value="prp")String prp, @RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, 
			@RequestParam("sunrp")String sunrp, @RequestParam(value = "ale", defaultValue = "") String ale){
	
		Topic topic=topicService.getTopic(uuid);
		
		TopicForm form=new TopicForm();
		form.setName(topic.getName());
		form.setUuid(topic.getUuid());
		form.setType(topic.getTopicType());

		if(subCatId!=null){
			form.setSubCatId(subCatId);
		}
		model.addAttribute("topicRef", topic);
		model.addAttribute("topic",form);
		model.addAttribute("id", subCatId);
		
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		model.addAttribute("ale", ale);
		
		return "topics/editTopic";
	}
	
	@RequestMapping(value={"editTopic.htm"},method=RequestMethod.POST)
	public String updateTopic(HttpServletRequest request,Model model,@ModelAttribute("topic")TopicForm topic){
		
		String prp = request.getParameter("prp");
		String orp = request.getParameter("orp");
		String sfrp = request.getParameter("sfrp"); 
		String sunrp = request.getParameter("sunrp");
		String ale = request.getParameter("ale");
		
		boolean result = topicService.updateTopic(topic);
		String success = null;
		if(result){
			success = "3";
		}else{
			success = "4";
		}
		
		/*
		 * If type of topic is TOPIC then redirecting it to ListTopics else ListSubTOpics
		 */
		if(topic.getSubCatId()!=null && topic.getType()==Constants.Topics.TOPIC.getValue() && StringUtils.isEmpty(ale)){
			model.addAttribute("id", topic.getSubCatId());
			return "redirect:listTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		else if (topic.getSubCatId()!=null && topic.getType()==Constants.Topics.SUBTOPIC.getValue() && StringUtils.isEmpty(ale)) {
			model.addAttribute("id", topic.getSubCatId());
			return "redirect:listSubTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		else{
			if(ale.equals("1")){
				return "redirect:allTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
			}else{
				return "redirect:allSubTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
			}
		}
	}
	/**
	 * @return
	 */
	@RequestMapping(value = { "addTopic.htm" }, method = { RequestMethod.GET })
	public String addTopic(HttpServletRequest request , Model model,@RequestParam("id") String id,@RequestParam(value = "type" ,defaultValue = "d")  String type) {
		
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		
		return "topics/addTopic";

	}


	@RequestMapping(value = { "listSubTopics.htm" }, method = { RequestMethod.GET ,RequestMethod.POST})
	public String getSubTopics(HttpServletRequest request,Model model , @RequestParam int id,
			@ModelAttribute("topicForm") TopicForm form ) {
		Topic topic = topicService.getTopics_Id(id);
		if(topic.getLeafNode()){
			model.addAttribute("success", "error");
			model.addAttribute("id", topic.getCategory().getId());
			return "redirect:listTopics.htm";
		}else{
			String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
			String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
			int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
			String name = Utility.replaceSpecialCharacters(form.getTopicSearchVal());
			
			int totalSubActiveTopics = topicService.getTotalActiveTopic_Type(name, Constants.Topics.SUBTOPIC,null,id);
			List<TopicForm> subTopicsForm = topicService.getActiveSubTopicsByCategoryId_ChildCount_SiteCount_RatingCount(name,
					id, PaginationUtility.pageSize, sortField, sortOrder,start);
			
			String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "topic");
			String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "topic");
			String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "topic");
			String sortFieldRequestParam = "";
			if(sortField == null){
				sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "topic", "name");
			}else{
				sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "topic", sortField);
			}
			
			model.addAttribute("topicList",subTopicsForm);
			model.addAttribute("totalSubActiveTopics", totalSubActiveTopics);
			model.addAttribute("topicForm", form);
			model.addAttribute("id", id);
			
			model.addAttribute("prp", pageRequestparam);
			model.addAttribute("orp",orderRequestParam);
			model.addAttribute("sfrp",sortFieldRequestParam);
			model.addAttribute("sunrp",sortUsingNameRequestParam);
			
			return "topics/listSubTopics";
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "addSubTopic.htm" }, method = { RequestMethod.GET })
	public String addSubTopics(HttpServletRequest request ,Model model, @RequestParam String id,@RequestParam(value = "type" ,defaultValue = "d")  String type) {
		
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		return "topics/addSubTopic";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "saveTopic.htm" }, method = { RequestMethod.POST })
	public String saveTopic(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String topicName = request.getParameter("topicName");
		User user = (User) request.getSession().getAttribute("user");
		boolean result = topicService.saveTopic(id, topicName,user.getId());
		String success = null;
		if(result){
			success = "1";
		}else{
			success = "4";
		}
		model.addAttribute("id",id);
		
		return "redirect:listTopics.htm?success="+success;
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value= {"saveSubTopic.htm"} , method = {RequestMethod.POST})
	public String saveSubTopic(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		String subTopicName = request.getParameter("subTopicName");
		User user = (User) request.getSession().getAttribute("user");
		int topicID = Integer.parseInt(id.trim());
		Topic topic = topicService.getTopics_Id(topicID);
		if(topic.getLeafNode()){
			topic.setLeafNode(false);
			topicService.update(topic);
		}
		boolean result = topicService.saveSubTopic(id, subTopicName,user.getId());
		String success = null;
		if(result){
			success = "1";
		}else{
			success = "4";
		}
		model.addAttribute("id",id);
		return "redirect:listSubTopics.htm?success="+success;
	}
	
	@RequestMapping(value = { "topicsBySubcategoryId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String getTopicsBySubcategoryId(HttpServletRequest request,@RequestParam String id) {
		List<Topic> topics = topicService.getTopics_SubCategoryId_AssignedTopics(id);
		String options = "";
	
		if(! topics.isEmpty()){
			options = "<option value='select' >Select Topic</option>";
			for(Topic topic : topics){
				options+="<option value="+topic.getId()+">"+topic.getName()+"</option>";
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
	
	@RequestMapping(value = { "subTopicByTopicId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String getSubTopicsByTopicId(HttpServletRequest request,@RequestParam String id) {
		List<Topic> topics = topicService.getSubTopics_TopicId_AssignedTopics(Integer.parseInt(id));
		
		String options = "";
		if(!topics.isEmpty()){
			options = "<option value='select' >Select Sub Topic</option>";
			for(Topic topic : topics){
				options+="<option value="+topic.getId()+">"+topic.getName()+"</option>";
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
	
	@RequestMapping(value = { "allTopicsBySubCategoryId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String getAllTopicsBySubcategoryId(HttpServletRequest request,@RequestParam("subCatId") int subCatId) {
		
		List<Topic> topics = topicService.getTopics_SubCategoryIdOrParentTopicId_Type(subCatId, 0, Constants.Topics.TOPIC);
		String options = "";
	
		Iterator<Topic> iterator = topics.iterator();
		while(iterator.hasNext()){
			Topic topic = iterator.next();
			if(! topicRatingCriteriaService.chekTopicRatings(topic) && topic.getChildTopics().isEmpty()){
				iterator.remove();
			}
		}
		
		if(! topics.isEmpty()){
			options = "<option value='select' >Select Topic</option>";
			for(Topic topic : topics){
				options+="<option value="+topic.getId()+">"+topic.getName()+"</option>";
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
	
	@RequestMapping(value = { "subTopicsOrContentsByTopicId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String getSubTopicsOrContentsByTopicId(HttpServletRequest request,@RequestParam int topicId) {
		String options = "";
		Topic topic = topicService.getTopics_Id(topicId);
		JSONObject obj = new JSONObject();
		try{
			if(topic.getLeafNode()){
				List<SiteReviewForm> forms = sitesService.getOnlineSitesByTopic(topic, ReviewStatus.ONLINE);
				
				for(SiteReviewForm form : forms){
					options += "<option value='"+form.getSiteId()+"'>"+form.getSiteName()+"("+form.getUrl()+")</option>";
				}
			obj.put("result", "contents");
			}else{
				List<Topic> topics = topicService.getTopics_SubCategoryIdOrParentTopicId_Type(0, topicId, Constants.Topics.SUBTOPIC);
				Iterator<Topic> iterator = topics.iterator();
				while(iterator.hasNext()){
					Topic topic2 = iterator.next();
					if(! topicRatingCriteriaService.chekTopicRatings(topic2) && topic2.getChildTopics().isEmpty()){
						iterator.remove();
					}
				}
				
				if(! topics.isEmpty()){
					options = "<option value='select' >Select Sub Topic</option>";
					for(Topic tempTopic : topics){
						options +="<option value="+tempTopic.getId()+">"+tempTopic.getName()+"</option>";
					}
				}
				obj.put("result", "subTopics");
			}
			obj.put("options", options);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		return obj.toString();
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "assignedTopicsBySubCategoryId_ExpertId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String assignedTopicsBySubCategoryId_ExpertId(HttpServletRequest request,@RequestParam("subcategoryId") int  subcategoryId ,
			@RequestParam("userId") long userId ) {
		//System.out.println("In Controller");
		Set<Topic> topics = topicExpertAssignmentService.getAssignedTopicsBySubCategoryId_ExpertId(subcategoryId, userId);
		
		String options = "";
		if(! topics.isEmpty()){
			options = "<option value='select' >Select Topic</option>";
			for(Topic topic : topics){
				options+="<option value="+topic.getId()+">"+topic.getName()+"</option>";
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
	@RequestMapping(value = { "assignedSubTopicByTopicId_expertId.htm" }, method = {RequestMethod.GET})
	public @ResponseBody String assignedSubTopicByTopicId_expertId(HttpServletRequest request,@RequestParam("topicId") int  topicId ,
			@RequestParam("userId") long userId ) {
		//System.out.println("In Controller");
		List<Topic> topics = topicExpertAssignmentService.getAssignedSubTopicByTopicId_expertId(topicId, userId);
		
		String options = "";
		if(! topics.isEmpty()){
			options = "<option value='select' >Select Topic</option>";
			for(Topic topic : topics){
				options+="<option value="+topic.getId()+">"+topic.getName()+"</option>";
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
	
	@RequestMapping(value= {"allTopics.htm"} , method = {RequestMethod.GET, RequestMethod.POST})
	public String allTopics(Model model, @ModelAttribute("topicForm") TopicForm topicForm, HttpServletRequest request) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
		String name = (Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()));
		
		int totalTopics = topicService.getTotalTopics(name);
		List<TopicForm> topics = topicService.getAllActiveTopics_ChildCount_SiteCount_RatingCount(Constants.Topics.TOPIC, name,start, PaginationUtility.pageSize,sortField,sortOrder);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "topic");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "topic");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "topic");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "topic", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "topic", sortField);
		}
				
		
		model.addAttribute("topicList", topics);
		model.addAttribute("topicForm", topicForm);
		model.addAttribute("totalTopics", totalTopics);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "topics/allTopics";
	}
	
	@RequestMapping(value = {"allSubTopics.htm"} , method = {RequestMethod.GET,RequestMethod.POST})
	public String allSubTopics(Model model, @ModelAttribute("topicForm") TopicForm topicForm, 
			HttpServletRequest request) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "subTopic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subTopic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subTopic");
		String name = Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal());
		
		int totalSubTopics = topicService.getTotalSubTopics(name);
		List<TopicForm> subTopics = topicService.getAllActiveSubTopics_ChildCount_SiteCount_RatingCount(name,start, PaginationUtility.pageSize,sortField,sortOrder);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "subTopic");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "subTopic");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "subTopic");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "subTopic", "name");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "subTopic", sortField);
		}
		
		model.addAttribute("subTopicList", subTopics);
		model.addAttribute("topicForm", topicForm);
		model.addAttribute("totalSubTopics", totalSubTopics);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "topics/allSubTopics";
	}
	
	@RequestMapping(value={"deleteTopics.htm"},method=RequestMethod.GET)
	public String deleteTopic(HttpServletRequest request,Model model,@RequestParam("id") int id,@RequestParam("SubCategoryId") int SubCategoryId, 
			@RequestParam(value="prp")String prp, @RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, 
			@RequestParam("sunrp")String sunrp, @RequestParam(value = "ale", defaultValue = "") String ale){
		model.addAttribute("id",SubCategoryId);
		Topic topic = topicService.getTopics_Id(id);
		boolean result = topicService.delete(topic);	
		String success = null;
		if(result){
			success = "2";
		}else{
			success = "4";
		}
		if(ale.equals("")){
			return "redirect:listTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		return "redirect:allTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}
	
	@RequestMapping(value={"deleteSubTopics.htm"},method=RequestMethod.GET)
	public String deleteSubTopics(HttpServletRequest request,Model model,@RequestParam("id") int id , @RequestParam("topicId") int topicId,
			@RequestParam(value="prp")String prp, @RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, 
			@RequestParam("sunrp")String sunrp, @RequestParam(value = "ale", defaultValue = "") String ale){
		model.addAttribute("id",topicId);
		
		Topic topic = topicService.getTopics_Id(id);
		boolean result = topicService.delete(topic);
		String success = null;
		if(result){
			success = "2";
		}else{
			success = "4";
		}
		if(ale.equals("")){
			return "redirect:listSubTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		return "redirect:allSubTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}
	
	@RequestMapping(value={"deleteallTopics.htm"},method=RequestMethod.GET)
	public String deleteallTopics(HttpServletRequest request,Model model,@RequestParam("id") int id, 
			@RequestParam(value="prp")String prp, @RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, 
			@RequestParam("sunrp")String sunrp, @RequestParam(value = "ale", defaultValue = "") String ale){
		
		model.addAttribute("id",id);
		Topic topic = topicService.getTopics_Id(id);
		boolean result = topicService.delete(topic);	
		String success = null;
		if(result){
			success = "2";
		}else{
			success = "4";
		}
		if(ale.equals("")){
			return "redirect:listTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
		}
		return "redirect:allTopics.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}
	
	
	@RequestMapping(value={"topicSites.htm"}, method=RequestMethod.GET)
	public String topicSites(Model model, @RequestParam("id") int id) {
		Topic topic = topicService.getTopics_Id(id);
		model.addAttribute("topic", topic);
		List<Site> availableSites = sitesService.getActiveSites_CategoryId(topic.getCategory().getParentCategory().getId());
		List<Site> assignedSites = topic.getSites();
		
		for(Iterator<Site> itr=availableSites.iterator();itr.hasNext();) {
			Site site = itr.next();
			if(assignedSites != null && assignedSites.contains(site)) {
				itr.remove();
			}
		}
		//System.out.println("Hello COntroller");
		model.addAttribute("availableSites", availableSites);
		model.addAttribute("assignedSites", assignedSites);
		//model.addAttribute("topicSite", topic);
		return "topics/topicSites";
	}
	
	@RequestMapping(value="assignSites.htm", method=RequestMethod.POST)	
	public @ResponseBody String assignSites(@RequestBody Object object) {
		String str = topicService.assignSites(object.toString());
		return str;
	}
	
	@RequestMapping(value="unAssignSites.htm", method=RequestMethod.POST)	
	public @ResponseBody String unAssignSites(@RequestBody Object object) {
		String str = topicService.unAssignSites(object.toString());
		return str;
	}
	
	/*
	 * Methods For Grid
	 */
	@RequestMapping(value="assignTopics.htm", method=RequestMethod.GET)
	public String assignTopics() {
		return "topics/assignTopics";
	}	
	
	@RequestMapping(value="openPopup.htm", method=RequestMethod.GET)
	public @ResponseBody String openPopup(@RequestParam("subTopicId") int subTopicId, @RequestParam("subTopic") String subTopic) {
		List<User> experts = expertService.getExperts();
		String str = "";
		str += "<table style='width:100%'>"+
		"<tr><th>Sub Topic: </th><td>"+subTopic+"</td></tr>"+
		"<tr><td colspan='2'><input type='hidden' name='subTopicId' id='subTopicId' value='"+subTopicId+"' />&nbsp;</td></tr>"+
		"<tr><th>Expert: </th><td><select name='expert' id='expert'> <option value='-1'>Select Expert</option>";
		for(User user : experts) {
			str += "<option value='"+user.getId()+"'>"+user.getUserinfo().getFirstname()+ " " + user.getUserinfo().getLastname() +"</option>";
		}
		str += "</select></td></tr>"+
		"<tr><td><input type='button' name='btn' id='btn' value='Save' onclick='javascript: saveAssignment();' /></td><td><input type='button' name='btn1' id='btn1' value='Cancel' /></td></tr>"+
		"</table>";
		JSONObject obj = new JSONObject();
		try {
			obj.put("htm", str);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return obj.toString();
	}
	
	@RequestMapping(value="existsTopic.htm",method=RequestMethod.GET)
	public @ResponseBody String existsTopic(@RequestParam("name")String name,@RequestParam("id") int id){
		List<Topic> topics = topicService.getTopics_Name_CategoryId(name,id);
		boolean exists=false;
		if(topics!=null && topics.size()!=0){
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
	
	@RequestMapping(value="existsSubTopic.htm",method=RequestMethod.GET)
	public @ResponseBody String existsSubTopic(@RequestParam("name")String name,@RequestParam("id") int id){
		//System.out.println("in subtopic");
		Topic subTopic = topicService.getTopic_Name_Id(name,id);
	
		boolean exists=false;
		if(subTopic!=null ){
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
	
	@RequestMapping(value={"topicCriteriaRatings.htm"}, method=RequestMethod.GET)
	public String getTopicRatingCriteria(Model model , @RequestParam("id") int id , HttpServletRequest request,
			@RequestParam(value = "reviewStatus" , defaultValue = "0") int reviewStatus, @RequestParam(value = "status" , defaultValue = "1") int status,
			@RequestParam(value = "rdv" , defaultValue = "")String rdv){
		Topic topic = null;
		topic = topicService.getTopics_Id(id);
		model.addAttribute("topic", topic);
		
		
		List<TopicRatingCriteria> topicRatingCriterias = topicRatingCriteriaService.getTopicRatingCriterias(topic);
				topicRatingCriteriaService.getTopicWeights(topic);
		
		List<RatingCriteria> parentCriteriaList = ratingCriteriaService.findAllParentCriteria();
		model.addAttribute("parentCriteriaList", parentCriteriaList);
		model.addAttribute("criteriaList", topicRatingCriterias);
		model.addAttribute("reviewStatus", reviewStatus);
		model.addAttribute("status", status);
		model.addAttribute("rdv", rdv);
		
		return "topics/topicCriteriaRatings";
	}
	
	@RequestMapping(value={"saveTopicCriteriaRatings.htm"} , method=RequestMethod.POST)
	public String saveTopicCriteriaRatings(Model model , @RequestParam("weight") int[] weight ,@RequestParam("topicWeightId")int[] topicWeightId ,
			@RequestParam("criteriaId") int[] criteriaId , @RequestParam("topicId") int topicId,HttpServletRequest request,@RequestParam(value = "redirect", defaultValue = "1") int redirect){
		
		List<TopicRatingCriteria> topicRatingCriteriaList = new ArrayList<TopicRatingCriteria>();
		User loginUser = (User) request.getSession().getAttribute("user");
		Topic topic = topicService.getTopics_Id(topicId);
		String rdv = request.getParameter("rdv");

		if(!topic.getLeafNode()){
			topic.setLeafNode(true);
			topicService.update(topic);
		}
		
		for(int i=0 ; i < weight.length ; i++){
			TopicRatingCriteria topicRatingCriteria = new TopicRatingCriteria();
			topicRatingCriteria.setId(topicWeightId[i]);
			topicRatingCriteria.setRatingCriteria(ratingCriteriaService.getRatingCriteria_Id(criteriaId[i]));
			topicRatingCriteria.setTopic(topicService.getTopics_Id(topicId));
			topicRatingCriteria.setWeight(weight[i]);
			topicRatingCriteria.setCreatedBy(loginUser.getId());
			topicRatingCriteriaList.add(topicRatingCriteria);
		}
		boolean result = topicRatingCriteriaService.saveBulk(topicRatingCriteriaList);
		String success = null;
		if(! result){
			success = "4";
		}else{
			success = "1";
			siteReviewRatingCriteriaService.updateSitesScores(topicId, criteriaId, topicWeightId, weight);
		}
		
		String returnView = "redirect:rateTopics_Editor.htm?success="+success;
		if(loginUser.getRole().getId() == Roles.EXPERT.getValue() && redirect == 1 && rdv.equals("")){
			returnView = "redirect:rateTopics_Expert.htm?success="+success;
		}else if(rdv.equals("et")){
			returnView = "redirect:getAssignedTopicsToExperts.htm";
		}
		return returnView;
		//return "redirect:rateTopics_Editor.htm?success="+success;
	}
	
	@RequestMapping(value={"subTopicCriteriaRatings.htm"}, method=RequestMethod.GET)
	public String getSubTopicRatingCriteria(Model model , @RequestParam("id") int id,
			@RequestParam(value = "reviewStatus" , defaultValue = "0") int reviewStatus,@RequestParam(value = "status" , defaultValue = "1") int status,
			@RequestParam(value = "rdv" , defaultValue = "")String rdv){
		
		Topic topic = topicService.getTopics_Id(id);
		model.addAttribute("topic", topic);
		
		List<TopicRatingCriteria> topicRatingCriterias = topicRatingCriteriaService.getTopicRatingCriterias(topic);
	
		List<RatingCriteria> parentCriteriaList = ratingCriteriaService.findAllParentCriteria();
		model.addAttribute("parentCriteriaList", parentCriteriaList);
		model.addAttribute("criteriaList", topicRatingCriterias);
		model.addAttribute("reviewStatus", reviewStatus);
		model.addAttribute("status", status);
		model.addAttribute("rdv", rdv);
		
		return "topics/subTopicCriteriaRatings";
	}
	
	@RequestMapping(value={"saveSubTopicCriteriaRatings.htm"} , method=RequestMethod.POST)
	public String saveSubTopicCriteriaRatings(Model model , @RequestParam("weight") int[] weight ,@RequestParam("topicWeightId")int[] topicWeightId ,
			@RequestParam("criteriaId") int[] criteriaId , @RequestParam("topicId") int topicId , HttpServletRequest request,@RequestParam(value = "redirect", defaultValue = "1") int redirect){
		model.addAttribute("id", topicId);
		
		List<TopicRatingCriteria> topicRatingCriteriaList = new ArrayList<TopicRatingCriteria>();
		User loginUser = (User) request.getSession().getAttribute("user");
		String rdv = request.getParameter("rdv");
		
		for(int i=0 ; i < weight.length ; i++){
			TopicRatingCriteria topicRatingCriteria = new TopicRatingCriteria();
			topicRatingCriteria.setId(topicWeightId[i]);
			topicRatingCriteria.setRatingCriteria(ratingCriteriaService.getRatingCriteria_Id(criteriaId[i]));
			topicRatingCriteria.setTopic(topicService.getTopics_Id(topicId));
			topicRatingCriteria.setWeight(weight[i]);
			topicRatingCriteria.setStatus(Status.ACTIVE.getValue());
			topicRatingCriteria.setCreatedBy(loginUser.getId());
			topicRatingCriteriaList.add(topicRatingCriteria);
		}
		boolean result = topicRatingCriteriaService.saveBulk(topicRatingCriteriaList);
		String success = null;
		if(! result){
			success = "4";
		}else{
			success = "5";
			siteReviewRatingCriteriaService.updateSitesScores(topicId, criteriaId, topicWeightId, weight);
		}
		
		String returnView = "redirect:rateSubTopics_Editor.htm?success="+success;
		if(loginUser.getRole().getId() == Roles.EXPERT.getValue() && redirect == 1 && rdv.equals("")){
			returnView = "redirect:rateSubTopics_Expert.htm?success="+success;
		}else if(rdv.equals("est")){
			returnView = "redirect:getAssignedSubTopicsToExperts.htm";
		}
		return returnView;
	}
	
	@RequestMapping(value= {"rateTopics_Editor.htm"} , method = {RequestMethod.GET, RequestMethod.POST})
	public String rateTopics_Editor(Model model, @ModelAttribute("topicForm") TopicForm topicForm, 
			HttpServletRequest request) {
		int totalTopics = 0;
		List<TopicForm> topicList = new ArrayList<>();
		
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");		
		User loginUser = (User) request.getSession().getAttribute("user");
		
		String name = Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal());
		
		totalTopics = topicService.getTotalTopics_EditorId(name, loginUser.getId());
		topicList = topicService.getActiveTopics_EditorId(name,	start, PaginationUtility.pageSize, loginUser.getId(), sortOrder, sortColumn);
		
		if(sortColumn != null && sortColumn.equals("ratings")){
			Collections.sort(topicList,new Comparator<TopicForm>() {
				@Override
				public int compare(TopicForm arg0, TopicForm arg1) {
					if(sortOrder.equals("asc")){
						return arg0.getRatingStatus().compareTo(arg1.getRatingStatus());
					}else{
						return arg1.getRatingStatus().compareTo(arg0.getRatingStatus());
					}
					
				}
			});
		}
		model.addAttribute("topicList", topicList);
		model.addAttribute("topicForm", topicForm);
		model.addAttribute("totalTopics", totalTopics);
		return "topics/rateTopics";		
	}
	
	@RequestMapping(value= {"rateSubTopics_Editor.htm"} , method = {RequestMethod.GET, RequestMethod.POST})
	public String rateSubTopics_Editor(Model model, @ModelAttribute("topicForm") TopicForm topicForm, 
			HttpServletRequest request) {
		int totalSubTopics = 0;
		List<TopicForm> subTopicList = new ArrayList<>();
		
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "subTopic");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subTopic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subTopic");
		User loginUser = (User) request.getSession().getAttribute("user");
		
		String name = Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal());
		
		totalSubTopics = topicService.getTotalSubTopics_Editor(name, loginUser.getId());
		subTopicList = topicService.getActiveSubTopics_Editor(name, start, PaginationUtility.pageSize, loginUser.getId(),sortOrder,sortColumn);
		
		if(sortColumn != null && sortColumn.equals("ratings")){
			Collections.sort(subTopicList,new Comparator<TopicForm>() {
				@Override
				public int compare(TopicForm arg0, TopicForm arg1) {
					if(sortOrder.equals("asc")){
						return arg0.getRatingStatus().compareTo(arg1.getRatingStatus());
					}else{
						return arg1.getRatingStatus().compareTo(arg0.getRatingStatus());
					}
				}
			});
		}
		model.addAttribute("subTopicList", subTopicList);
		model.addAttribute("topicForm", topicForm);
		model.addAttribute("totalSubTopics", totalSubTopics);		
		return "topics/rateSubTopics";		
	}

	@RequestMapping(value= {"rateTopics_Expert.htm"} , method = {RequestMethod.GET, RequestMethod.POST})
	public String rateTopics_Expert(Model model, @ModelAttribute("topicForm") TopicForm topicForm, 
			HttpServletRequest request) {
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");		
		User loginUser = (User) request.getSession().getAttribute("user");
		
		int totalTopics = topicExpertAssignmentService.getTotalTopics_ExpertId(
				Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()), loginUser.getId(),Constants.Topics.TOPIC.getValue());
		
		List<TopicForm> topicList = topicExpertAssignmentService.getActiveTopics_ExpertId(Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()),
				start, PaginationUtility.pageSize, loginUser.getId(),Constants.Topics.TOPIC.getValue(),sortOrder,sortColumn);
		
		if(sortColumn != null && sortColumn.equals("ratings")){
			Collections.sort(topicList,new Comparator<TopicForm>() {
				@Override
				public int compare(TopicForm arg0, TopicForm arg1) {
					if(sortOrder.equals("asc")){
						return arg0.getRatingStatus().compareTo(arg1.getRatingStatus());
					}else{
						return arg1.getRatingStatus().compareTo(arg0.getRatingStatus());
					}
					
				}
			});
		}
		
		model.addAttribute("topicList", topicList);
		model.addAttribute("topicForm", topicForm);
		model.addAttribute("totalTopics", totalTopics);
		return "publishing/rateTopics";		
	}
	
	@RequestMapping(value= {"rateSubTopics_Expert.htm"} , method = {RequestMethod.GET, RequestMethod.POST})
	public String rateSubTopics_Expert(Model model, @ModelAttribute("topicForm") TopicForm topicForm, 
			HttpServletRequest request) {
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "subTopic");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subTopic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subTopic");
		User loginUser = (User) request.getSession().getAttribute("user");
		
		int totalSubTopics = topicExpertAssignmentService.getTotalTopics_ExpertId(
				Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()), loginUser.getId(),Constants.Topics.SUBTOPIC.getValue());
		
		List<TopicForm> subTopicList = topicExpertAssignmentService.getActiveTopics_ExpertId(Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()),
				start, PaginationUtility.pageSize, loginUser.getId(),Constants.Topics.SUBTOPIC.getValue(),sortOrder,sortColumn);
	
		
		if(sortColumn != null && sortColumn.equals("ratings")){
			Collections.sort(subTopicList,new Comparator<TopicForm>() {
				@Override
				public int compare(TopicForm arg0, TopicForm arg1) {
					if(sortOrder.equals("asc")){
						return arg0.getRatingStatus().compareTo(arg1.getRatingStatus());
					}else{
						return arg1.getRatingStatus().compareTo(arg0.getRatingStatus());
					}
				}
			});
		}
		model.addAttribute("subTopicList", subTopicList);
		model.addAttribute("topicForm", topicForm);
		model.addAttribute("totalSubTopics", totalSubTopics);		
		return "publishing/rateSubTopics";		
	}
	
	@RequestMapping(value = "getAssignedTopicsToExperts.htm" , method = {RequestMethod.GET , RequestMethod.POST})
	public String getAssignedTopicToExpert(HttpServletRequest request , Model model,@ModelAttribute("topicForm") TopicForm topicForm){
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "topicExpertAssignment");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topicExpertAssignment");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topicExpertAssignment");
		User user = (User) request.getSession().getAttribute("user");
		
		int totalTopicExpertAssignment = topicExpertAssignmentService.getTotalTopicExpertAssignment_AssignedBy(Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()),
				user,Constants.Topics.TOPIC.getValue());
		List<TopicForm> topicExpertAssignments = topicExpertAssignmentService.getTopicExpertAssignment_AssignedBy(
				Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()),user,Constants.Topics.TOPIC.getValue(),
				start, PaginationUtility.pageSize,sortColumn,sortOrder);
		
		model.addAttribute("topicExpertAssignments" , topicExpertAssignments);
		model.addAttribute("totalTopicExpertAssignment" , totalTopicExpertAssignment);
		return "publishing/assignedTopicsExperts";
	}
	
	@RequestMapping(value = "getAssignedSubTopicsToExperts.htm" , method = {RequestMethod.GET , RequestMethod.POST})
	public String getAssignedSubTopicToExpert(HttpServletRequest request , Model model,@ModelAttribute("topicForm") TopicForm topicForm){
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "subTopicExpertAssignment");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subTopicExpertAssignment");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subTopicExpertAssignment");
		User user = (User) request.getSession().getAttribute("user");
	
		int totalSubTopicExpertAssignment = topicExpertAssignmentService.getTotalTopicExpertAssignment_AssignedBy(Utility.replaceSpecialCharacters(
				topicForm.getTopicSearchVal()),user,Constants.Topics.SUBTOPIC.getValue());
		List<TopicForm> subTopicExpertAssignments = topicExpertAssignmentService.getTopicExpertAssignment_AssignedBy(
				Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()),user,Constants.Topics.SUBTOPIC.getValue(),
				start, PaginationUtility.pageSize,sortColumn,sortOrder);
		
		model.addAttribute("subTopicExpertAssignments" , subTopicExpertAssignments);
		model.addAttribute("totalSubTopicExpertAssignment" , totalSubTopicExpertAssignment);
		return "publishing/assignedSubTopicsExperts";
	}
	
	@RequestMapping(value="checkTopicOrSubTopicRatings.htm", method=RequestMethod.GET)
	public @ResponseBody String checkTopicOrSubTopicRatings(@RequestParam("id") int topicId) {
		Topic topic = topicService.getTopics_Id(topicId);
		boolean result = topicRatingCriteriaService.chekTopicRatings(topic);
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("result", result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
		/*
		 * Names Auto Suggestion
		 */
	@RequestMapping(value="autoSuggestTopicByNameAndType.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestTopicByNameAndType(@RequestParam("name") String name, @RequestParam("type") String type,
		@RequestParam("parentId") Integer parentId) {
		String str = topicService.suggestTopicOrSubTopicByNameAndType(name, parentId, type);
		return str;
	}
	
	
	@RequestMapping(value="autoSuggestTopicByNameAndTypeForMyPublishing.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestTopicByNameAndTypeForMyPublishing(@RequestParam("name") String name,
			@RequestParam("type") String type, HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		
		String str = topicService.suggestTOrSTByNameAndTypeForMyPublishing(name, type, loginUser.getId());
		return str;
	}
	
	@RequestMapping(value="autoSuggestTopicByNameAndTypeForExpertPublishing.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestTopicByNameAndTypeForExpertPublishing(@RequestParam("name") String name,
			@RequestParam("type") String type, HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = topicExpertAssignmentService.suggestTOrSTByNameAndTypeForExpertPublishing(name, type, 
				loginUser.getId(), Roles.EDITOR);
		return str;
	}
	
	@RequestMapping(value="autoSuggestTopicByNameAndTypeForExpert.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestTopicByNameAndTypeForExpert(@RequestParam("name") String name,
			@RequestParam("type") String type, HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		String str = topicExpertAssignmentService.suggestTOrSTByNameAndTypeForExpertPublishing(name, type, 
				loginUser.getId(), Roles.EXPERT);
		return str;
	}
	
}
