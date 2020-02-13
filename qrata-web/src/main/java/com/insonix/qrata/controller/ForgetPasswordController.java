package com.insonix.qrata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.models.User;
import com.insonix.qrata.service.ForgetPasswordService;

/**
 * @author Ramandeep Singh
 * @Since Feb,2013
 */
@Controller("ForgetPasswordController")
public class ForgetPasswordController extends BaseController {

	@Autowired
	ForgetPasswordService forgetPasswordService;

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "forgetForm.htm" }, method = { RequestMethod.GET })
	public String forgetForm(HttpServletRequest request) {
		return "forgetPassword";
	}
	
	@RequestMapping(value={"recoverPassword.htm"} , method = RequestMethod.POST)
	public String recoverPassword(HttpServletRequest request,Model model, @RequestParam("emailId") String emailId) {
		String context =  request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		System.out.println("Forget Context : "+context);
		User user = forgetPasswordService.checkEmailIdExist(emailId);
		if(user != null){
			try{
				forgetPasswordService.sendEmail(user,context);
			}catch(RuntimeException ex){
				model.addAttribute("error", "There was an error in executing the process! Please try again. If the problem persist, please contact administrator.");
			}
			model.addAttribute("success", "Your password has been sent successfully. Please check your email.");
		}else{
			model.addAttribute("error", "Your email address is not found in our records! Try another.");
		}
		
		return "forgetPassword";
	}
}
