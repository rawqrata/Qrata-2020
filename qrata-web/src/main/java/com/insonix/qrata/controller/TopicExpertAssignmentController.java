/**
 * 
 */
package com.insonix.qrata.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.TopicExpertAssignmentService;
import com.insonix.qrata.service.TopicRatingCriteriaService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.service.UserService;

/**
 * @author Harmeet Singh
 *
 */
@Controller
@RequestMapping("admin")
public class TopicExpertAssignmentController {

	@Autowired
	TopicExpertAssignmentService topicExpertAssignmentService;
	@Autowired
	UserService userService;
	@Autowired
	TopicService topicService;
	@Autowired
	ExpertService expertService;
	@Autowired
	TopicRatingCriteriaService topicRatingCriteriaService;
	
	
	@RequestMapping(value={"topicExpert.htm"},method=RequestMethod.GET)
	public String topicExpert(Model model, @RequestParam("id") int id) {
		Topic topic = topicService.getTopics_Id(id);
		model.addAttribute("topic", topic);
		
		boolean topicExists = topicExpertAssignmentService.getTopicExists(topic);
		if(!topicExists){
			//List<Site> sites = topicService.getSites_Topic(topic);
			BigInteger count = topicService.getSitesCount_TopicId(topic.getId());
			boolean result = topicRatingCriteriaService.chekTopicRatings(topic);
			if((!result && count.intValue() == 0)){
//				List<User> availableExperts = expertService.getExperts();
				User assignedExpert = null;
				if(topic.getTopicExpertAssignment() != null ){
					assignedExpert = topic.getTopicExpertAssignment().getExpert();
//					availableExperts.remove(assignedExpert);
				}
//				model.addAttribute("availableExperts", availableExperts);
				model.addAttribute("assignedExpert", assignedExpert);
				model.addAttribute("topicExpert", topic);
				return "topics/topicExpert";
			}else{
				if(topic.getParentTopic() == null){
					return "redirect:allTopics.htm?success="+4;
				}else{
					return "redirect:allSubTopics.htm?success="+4;
				}
			}	
		}else{
//			List<User> availableExperts = expertService.getExperts();
			User assignedExpert = null;
			if(topic.getTopicExpertAssignment() != null ){
				assignedExpert = topic.getTopicExpertAssignment().getExpert();
//				availableExperts.remove(assignedExpert);
			}
//			model.addAttribute("availableExperts", availableExperts);
			model.addAttribute("assignedExpert", assignedExpert);
			model.addAttribute("topicExpert", topic);
			return "topics/topicExpert";
		}
	}
	
	@RequestMapping(value={"assignedExpert.htm"} , method=RequestMethod.GET)
	public String assignedExpert(Model model,@RequestParam("topicId") int topicId, @RequestParam("expertsId") int expertsId, HttpServletRequest request){
		model.addAttribute("id" , topicId);
		Topic topic = topicService.getTopics_Id(topicId);
		
		if(!topic.getLeafNode()){
			topic.setLeafNode(true);
			topicService.update(topic);
		}
		
		User user = (User) request.getSession().getAttribute("user");
		String returnResult = "redirect:topicExpert.htm?success=3";
		boolean result = false;
		if(expertsId != 0){
			User expert = userService.getUser_Id(expertsId);
			result = topicExpertAssignmentService.assignedTopicToExpert(topic,user,expert);
			if(result){
				returnResult = "redirect:expertBio.htm?id="+expert.getId();
				model.addAttribute("success", 3);
			}
		}else{
			result = topicExpertAssignmentService.unAssignedTopicToExpert(topic);
		}
		return returnResult;
	}
	
	@RequestMapping(value="getAvailableExperts.htm", method=RequestMethod.GET)
	@ResponseBody
	public String getAvailableExperts(@RequestParam("term") String term) {
		String str = topicExpertAssignmentService.getAvailableExperts(term);
		return str;
	}
	
}
