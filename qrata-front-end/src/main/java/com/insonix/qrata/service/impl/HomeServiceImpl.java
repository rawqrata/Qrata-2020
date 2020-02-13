package com.insonix.qrata.service.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.models.Category;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.service.HomeService;

/**
 * @author Gurminder Singh
 *
 * @date 18-Jun-2013
 */
@Service("homeService")
public class HomeServiceImpl implements HomeService {
	@Autowired CategoryService categoryService;

	@Override
	public String getDomains() {
		JSONObject responseObj = new JSONObject();
		try {
			List<Category> domainList = categoryService.getActiveDomains();
			JSONArray arr = new JSONArray();
			JSONObject obj = null;
			if(domainList != null) {
				for(Category c : domainList) {
					obj = new JSONObject();
					obj.put("id", c.getId());
					obj.put("name", c.getName());
					arr.put(obj);
				}
			}
			responseObj.put("obj", arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}
	
}
