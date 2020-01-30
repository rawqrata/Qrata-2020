package com.qrata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qrata.service.FeedbackService;

@Controller
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(value={"feedback.htm"}, method={RequestMethod.GET})
	public String feedback(Model model,@RequestParam(value = "kind" ,defaultValue = "d")  String kind) {
		model.addAttribute("kind", kind);
		return "feedback/feedback";
	}
	
	@RequestMapping(value={"feedback.htm"}, method={RequestMethod.POST})
	public String feedbackMethod(Model model,@RequestParam(value = "kind" ,defaultValue = "d")  String kind) {
		model.addAttribute("kind", kind);
		return "home/home";
	}
	
	@RequestMapping(value={"cashContent.htm"}, method=RequestMethod.GET)
	public String cashContent(Model model) {
		
		return "feedback/cashContent";
	}
	

	@RequestMapping(value={"feedbackMailCashContent.htm"} , method = RequestMethod.POST)
	public String feedbackMailCashContent(HttpServletRequest request,Model model) {
		String topic = request.getParameter("topic");
		String site = request.getParameter("site_name");
		String url = request.getParameter("site_url");
		String from = request.getParameter("email");
		String zipcode=request.getParameter("zip_code");
	   String name = request.getParameter("name");
	 
		String success="1";
			try{
				feedbackService.sendFeedbackCashContent(name,zipcode, from, topic, site, url);
			}catch(RuntimeException ex){
				model.addAttribute("error", "Invalid email");
			}
		return "redirect:home.htm?success="+success;
	}

	@RequestMapping(value={"feedbackMail.htm"} , method = RequestMethod.POST)
	public String feedbackMail(HttpServletRequest request,Model model) {
		String content = request.getParameter("feedback_body");
		String from = request.getParameter("feedback_email");
		String subject = request.getParameter("kind");
	   String name = request.getParameter("feedback_name");
	   String zipcode=request.getParameter("feedback_zip_code");
	   String heading = request.getParameter("heading");
	 
		String success="1";
			try{
				feedbackService.sendFeedbackEmail(subject,heading, zipcode,name, from, content);
			}catch(RuntimeException ex){
				model.addAttribute("error", "Invalid email");
			}
		return "redirect:home.htm?success="+success;
	}
	

}
