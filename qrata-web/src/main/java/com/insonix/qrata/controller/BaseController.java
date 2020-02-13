package com.insonix.qrata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

/**
 * This is base class which provides functionality to read messages from
 * resource bundle.
 * 
 * @author Sunny
 * @timeStamp Oct 04, 2010 2:38:26 PM
 */

public abstract class BaseController {

	@Autowired
	private MessageSource messageSource;

	@Autowired(required = true)
	private LocaleResolver localeResolver;

	@Autowired(required=false)
	private HttpServletRequest request;
	/**
	 * The method returns value of specified key.
	 * @param key
	 * @return
	 */
	public String getText(String key) {
		return messageSource.getMessage(key, null, localeResolver
				.resolveLocale(getRequest()));
	}
	/**
	 * The method returns value of specified key and set arguments.
	 * e.g. error.required={0} is required.
	 * if we call getText("error.required", new String[]{"Name"});
	 * Result will be : Name is required
	 * @param key
	 * @param args
	 * @return
	 */
	public String getText(String key, String args) {
		return messageSource.getMessage(key, new String[] { args },
				localeResolver.resolveLocale(getRequest()));
	}
	/**
	 * @return the request
	 */
	@Autowired
	public HttpServletRequest getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
