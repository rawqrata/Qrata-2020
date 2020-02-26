package com.insonix.qrata.interceptors;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonLogger extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		Logger log = Logger.getLogger("com.qrata.user");
		String remoteAddress = request.getRemoteHost();
		MDC.put("remoteAddress", remoteAddress);
		log.log(Level.INFO, request.getRequestURI());
		return true;

	}

}
