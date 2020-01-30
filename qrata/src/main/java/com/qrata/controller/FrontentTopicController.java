package com.qrata.controller;

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

import com.qrata.entity.TopicForm;
import com.qrata.models.Topic;
import com.qrata.service.TopicService;
import com.qrata.utility.PaginationUtility;
import com.qrata.utility.Utility;
import com.qrata.enums.*;

@Controller("FrontenttopicController")
@RequestMapping(value="topics")
public class FrontentTopicController {
	
	@Autowired 
	TopicService topicService;
	
	@RequestMapping(value=("listTopics.htm") , method=RequestMethod.GET)
	public String listTopics(HttpServletRequest request, Model model,@RequestParam("id") int subCategoryId ,@ModelAttribute("topicForm") TopicForm form){
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "topic");
		int totalActiveTopics =0;
		List<Topic> topicList = topicService.getActiveTopic_Type(Utility.replaceSpecialCharacters(form.getTopicSearchVal())
				,Constants.Topics.TOPIC,subCategoryId,null,PaginationUtility.pageSize, null, null,start);
		
		List<TopicForm> topicFormList = new ArrayList<TopicForm>();
		for(Topic topic : topicList){
			TopicForm topicForm = new TopicForm();
			topicForm.setTopicId(topic.getId());
			topicForm.setName(topic.getName());
			topicForm.setUuid(topic.getUuid());
			topicForm.setCategoryId(topic.getCategory().getId());
			/* topicForm.setLeafNode(topic.getLeafNode()); */
			List<Topic> childTopics = topic.getChildTopics();
			if(!childTopics.isEmpty()){
				topicForm.setChildStatus(true);
			}else{
				topicForm.setChildStatus(false);
			}
			topicFormList.add(topicForm);
		}
		//List<Topic> topicList=topicService.listTopics(subCategoryId);
        model.addAttribute("topicList", topicFormList)
                .addAttribute("totalActiveTopics", totalActiveTopics);
        model.addAttribute("id", subCategoryId);

        return "topics/listTopics";
	}
	

}
