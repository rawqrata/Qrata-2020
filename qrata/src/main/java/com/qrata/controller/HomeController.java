package com.qrata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gurminder Singh
 *
 * @date 17-Jun-2013
 */
@Controller
public class HomeController {
	//@Autowired HomeService homeService;
	
	@RequestMapping(value={"home.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String home() {
		return "home/home";
	}
	
	@RequestMapping(value={"getDomains.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public @ResponseBody String getDomains() {
		String str = "";
		return str;
	}
	@RequestMapping(value={"information.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String info() {
		return "home/information";
	}
	@RequestMapping(value={"contact.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String contact() {
		return "home/contact";
	}
	
	@RequestMapping(value={"feedbackOption.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String feedbackOption() {
		return "home/feedbackOption";
	}
	
	@RequestMapping(value={"faq.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String faq() {
		return "qInfo/faq";
	}
	
	@RequestMapping(value={"legal.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String legal() {
		return "qInfo/legal";
	}
	
	
	@RequestMapping(value={"homeLayout.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String homeLayOut() {
		return "layouts/homeLayout";
	}
	
	@RequestMapping(value={"loginLayout.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String loginLayOut() {
		return "/login";
	}
	
	@RequestMapping(value={"adminLayout.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String adminLayOut() {
		return "layouts/adminLayout";
	}
	

	@RequestMapping(value={""}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String welcome() {
		return "/home";
	}
	
	@RequestMapping(value={"welcome.htm"}, method={RequestMethod.GET, RequestMethod.HEAD})
	public String adminPage() {
		return "/welcome";
	}

	
}
