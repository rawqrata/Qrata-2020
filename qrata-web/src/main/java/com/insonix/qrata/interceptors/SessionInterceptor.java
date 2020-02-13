package com.insonix.qrata.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.insonix.qrata.controller.ForgetPasswordController;
import com.insonix.qrata.controller.HomeController;

/**
 * @author Gurminder Singh
 *
 */
public class SessionInterceptor implements HandlerInterceptor {
	private String redirectMapping;
	public void setRedirectMapping(String redirectMapping) {
		this.redirectMapping = redirectMapping;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(!(handler instanceof HomeController || handler instanceof ForgetPasswordController)) {
			if(session.getAttribute("user") == null) {
				response.sendRedirect(request.getContextPath()+"/"+redirectMapping+"?msg=e");
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub		
	}
	
}
