package com.insonix.qrata.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.entity.TopicForm;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.utility.Utility;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */

@Controller("topicController")
@RequestMapping(value="topics")
public class TopicController {
	@Autowired TopicService topicService;
	
	@RequestMapping(value=("listTopics.htm") , method=RequestMethod.GET)
	public String listTopics(HttpServletRequest request, Model model,@RequestParam("id") int subCategoryId ,@ModelAttribute("topicForm") TopicForm form){
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
		int totalActiveTopics = topicService.getTotalActiveTopic_Type(Utility.replaceSpecialCharacters(form.getTopicSearchVal()),
				Constants.Topics.TOPIC,subCategoryId,null);
		List<Topic> topicList = topicService.getActiveTopic_Type(Utility.replaceSpecialCharacters(form.getTopicSearchVal())
				,Constants.Topics.TOPIC,subCategoryId,null,PaginationUtility.pageSize, null, null,start);
		
		List<TopicForm> topicFormList = new ArrayList<TopicForm>();
		for(Topic topic : topicList){
			TopicForm topicForm = new TopicForm();
			topicForm.setTopicId(topic.getId());
			topicForm.setName(topic.getName());
			topicForm.setUuid(topic.getUuid());
			topicForm.setCategoryId(topic.getCategory().getId());
			topicForm.setLeafNode(topic.getLeafNode());
			List<Topic> childTopics = topic.getChildTopics();
			if(!childTopics.isEmpty()){
				topicForm.setChildStatus(true);
			}else{
				topicForm.setChildStatus(false);
			}
			topicFormList.add(topicForm);
		}
		//List<Topic> topicList=topicService.listTopics(subCategoryId);
		model.addAttribute("topicList", topicFormList);
		model.addAttribute("totalActiveTopics", totalActiveTopics);
		return "topics/listTopics";
	}
	
	@RequestMapping(value=("listSubTopics.htm") ,method=RequestMethod.GET)
	public String listSubTopics(HttpServletRequest request , Model model, @RequestParam("id") int topicId ,@ModelAttribute("topicForm") TopicForm form){
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "subTopic");
		int totalSubActiveTopics = topicService.getTotalActiveTopic_Type(Utility.replaceSpecialCharacters(form.getTopicSearchVal()),
				Constants.Topics.SUBTOPIC,null,topicId);
		List<Topic> subTopicList = topicService.getActiveTopic_Type(Utility.replaceSpecialCharacters(form.getTopicSearchVal())
				,Constants.Topics.SUBTOPIC,null,topicId,PaginationUtility.pageSize, null, null,start);
		
		List<TopicForm> topicFormList = new ArrayList<TopicForm>();
		for(Topic subTopic : subTopicList){
			TopicForm topicForm = new TopicForm();
			topicForm.setTopicId(subTopic.getId());
			topicForm.setName(subTopic.getName());
			topicForm.setUuid(subTopic.getUuid());
			topicForm.setCategoryId(subTopic.getCategory().getId());
			topicForm.setParentTopicId(subTopic.getParentTopic().getId());
			topicFormList.add(topicForm);
		}
		//List<Topic> subTopicList=topicService.listSubTopics(topicId);
		model.addAttribute("subTopicList", topicFormList);
		model.addAttribute("totalSubActiveTopics", totalSubActiveTopics);
		return "topics/listSubTopics";
	}

}
