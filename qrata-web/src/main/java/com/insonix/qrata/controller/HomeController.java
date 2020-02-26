package com.insonix.qrata.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insonix.qrata.models.User;
import com.insonix.qrata.service.UserService;

/**
 * @author Ramandeep Singh
 * @Since Feb,2013
 */
@Controller("HomeController")
public class HomeController extends BaseController {

	@Autowired
	UserService userService;

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "loginForm.htm" }, method = { RequestMethod.GET })
	public String loginForm(HttpServletRequest request, Model model) {
		model.addAttribute("msg", request.getParameter("msg"));
		return "login";
	}
	
	@RequestMapping(value="/fail.htm")
	public String failForm(ModelMap model) {
		model.addAttribute("error", true);
		return "login";
	}
		
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "login.htm" }, method = { RequestMethod.GET })
	public String login(HttpServletRequest request, Principal principal) {
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();

		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		User user = userService.getUser_UserName(userName);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if (authorities != null && authorities.size() > 0) {
			GrantedAuthority authority = authorities.get(0);
			if (authority.getAuthority().equals("ADMIN")) {
 
				return "redirect:/welcome.htm";
			} else if (authority.getAuthority().equals("EXPERT")) {

				return "redirect:/welcome.htm";
			} else if (authority.getAuthority().equals("EDITOR")) {

				return "redirect:/welcome.htm";
			}
		}
		return "login";
	}
	
	@RequestMapping(value = "welcome.htm" , method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}
}
