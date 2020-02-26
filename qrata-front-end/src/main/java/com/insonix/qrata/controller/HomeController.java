package com.insonix.qrata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insonix.qrata.service.HomeService;

/**
 * @author Gurminder Singh
 *
 * @date 17-Jun-2013
 */
@Controller("homeController")
public class HomeController {
	@Autowired HomeService homeService;
	
	@RequestMapping(value={"home.htm"}, method=RequestMethod.GET)
	public String home() {
		return "home/home";
	}
	
	@RequestMapping(value={"getDomains.htm"}, method=RequestMethod.GET)
	public @ResponseBody String getDomains() {
		String str = homeService.getDomains();
		return str;
	}
	@RequestMapping(value={"information.htm"}, method=RequestMethod.GET)
	public String info() {
		return "home/information";
	}
	@RequestMapping(value={"contact.htm"}, method=RequestMethod.GET)
	public String contact() {
		return "home/contact";
	}
	
	@RequestMapping(value={"feedbackOption.htm"}, method=RequestMethod.GET)
	public String feedbackOption() {
		return "home/feedbackOption";
	}
	
	@RequestMapping(value={"faq.htm"}, method=RequestMethod.GET)
	public String faq() {
		return "qInfo/faq";
	}
	
	@RequestMapping(value={"legal.htm"}, method=RequestMethod.GET)
	public String legal() {
		return "qInfo/legal";
	}
	
}
