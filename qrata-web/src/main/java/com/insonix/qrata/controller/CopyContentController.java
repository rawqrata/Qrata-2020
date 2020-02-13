package com.insonix.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.entity.CopyContentForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.service.CopyContentService;
import com.insonix.qrata.service.TopicService;


/**
 * This is the class which is used to copy the contents from 
 * source breadcrumb to destination breadcrumb
 * 
 * @author Harmeet Singh
 */

@Controller("copyContentController")
@RequestMapping("admin")
public class CopyContentController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	CopyContentService copyContentService;
	@Autowired
	TopicService topicService;
	
	@RequestMapping(value="copyContents.htm", method=RequestMethod.GET)
	public String copyContent(HttpServletRequest request, Model model) {
		List<Category> domains = categoryService.getActiveDomains();		
		CopyContentForm copyContentForm = new CopyContentForm();
		model.addAttribute("domains", domains);
		model.addAttribute("copyContentForm", copyContentForm);
		return "copying/copyContents";
	}	
	
	@RequestMapping(value="copyContents.htm", method=RequestMethod.POST)
	public String copyContents(HttpServletRequest request, @ModelAttribute("copyContentForm") CopyContentForm contentForm, Model model,
			@RequestParam("copyContentIds")String copyContentIds){
		String[] contentsIds = copyContentIds.split(",");
		Category category = categoryService.getCategory_Id(contentForm.getCategoryIdDest());
		Topic topic = null;
		if(contentForm.getSubTopicDest() == 0){
			topic = topicService.getTopics_Id(contentForm.getTopicIdDest());
		}else{
			topic = topicService.getTopics_Id(contentForm.getSubTopicDest());
		}
		
		copyContentService.saveCopyContents(contentsIds, category, topic);
		return "redirect:findContentsByTopic.htm?id="+topic.getId()+"&m=s";
	}
}
