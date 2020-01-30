package com.qrata.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qrata.entity.TopicForm;
import com.qrata.service.TopicService;
import com.qrata.utility.PaginationUtility;
import com.qrata.utility.Utility;
import com.qrata.enums.*;
import com.qrata.models.User;
@Controller
@RequestMapping("admin")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping(value = { "addTopic.htm" }, method = { RequestMethod.GET })
	public String addTopic(HttpServletRequest request , Model model,@RequestParam("id") String id,@RequestParam(value = "type" ,defaultValue = "d")  String type) {
		
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		
		return "topics/addTopic";

	}
	
	
	@RequestMapping(value= {"allTopics.htm"} , method = {RequestMethod.GET, RequestMethod.POST})
	public String allTopics(Model model, @ModelAttribute("topicForm") TopicForm topicForm, HttpServletRequest request) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
		String name = (Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal()));
		
		
		List<TopicForm> topics = topicService.getAllActiveTopics_ChildCount_SiteCount_RatingCount(Constants.Topics.TOPIC, name,start, PaginationUtility.pageSize,sortField,sortOrder);
		int totalTopics = topics.size();
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
	
	
	@RequestMapping(value = { "addSubTopic.htm" }, method = { RequestMethod.GET })
	public String addSubTopics(HttpServletRequest request ,Model model, @RequestParam String id,@RequestParam(value = "type" ,defaultValue = "d")  String type) {
		
		request.setAttribute("id", id);
		model.addAttribute("type", type);
		return "topics/addSubTopic";
	}
	
	
	@RequestMapping(value = { "listTopics.htm" }, method = { RequestMethod.GET , RequestMethod.POST})
	public String getTopics(HttpServletRequest request,Model model, @RequestParam("id") String id ,
			@ModelAttribute("topicForm") TopicForm form ) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
		String name = Utility.replaceSpecialCharacters(form.getTopicSearchVal());
		
		Integer subCategoryId = Integer.parseInt(id.trim());
	
		List<TopicForm> topicsForm = topicService.getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount(name,
				subCategoryId, PaginationUtility.pageSize, sortField, sortOrder,start);
		int totalActiveTopics = topicsForm.size();
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
	
	@RequestMapping(value = {"allSubTopics.htm"} , method = {RequestMethod.GET,RequestMethod.POST})
	public String allSubTopics(Model model, @ModelAttribute("topicForm") TopicForm topicForm, 
			HttpServletRequest request) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "subTopic");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "subTopic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subTopic");
		String name = Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal());
		
	
		List<TopicForm> subTopics = topicService.getAllActiveSubTopics_ChildCount_SiteCount_RatingCount(name,start, PaginationUtility.pageSize,sortField,sortOrder);
		int totalSubTopics = subTopics.size();
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
	
	
	@RequestMapping(value= {"rateTopics_Editor.htm"} , method = {RequestMethod.GET, RequestMethod.POST})
	public String rateTopics_Editor(Model model, @ModelAttribute("topicForm") TopicForm topicForm, 
			HttpServletRequest request) {
		int totalTopics = 0;
		List<TopicForm> topicList = new ArrayList<>();
		
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "topic");
		final String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "topic");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");		
		/* User loginUser = (User) request.getSession().getAttribute("user"); */
		
		String name = Utility.replaceSpecialCharacters(topicForm.getTopicSearchVal());
		
		 
		topicList = topicService.getActiveTopics_EditorId(name,	start, PaginationUtility.pageSize, 1, sortOrder, sortColumn);
		totalTopics=0;
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
	

}
