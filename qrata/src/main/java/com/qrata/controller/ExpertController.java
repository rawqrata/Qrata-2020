package com.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qrata.entity.AddUserForm;
import com.qrata.entity.ExpertBioForm;
import com.qrata.enums.Constants.Roles;
import com.qrata.service.ExpertService;
import com.qrata.utility.PaginationUtility;

@Controller("expertController")
@RequestMapping({ "admin", "expert" })
public class ExpertController {
	
	@Autowired
	private ExpertService expertService;
	

	@RequestMapping(value = "expertBio.htm", method = { RequestMethod.GET })
	public String expertBioInfo(HttpServletRequest request, Model model,
			@RequestParam("id") long id) {
		ExpertBioForm form = expertService.getUser_Id(id);
		model.addAttribute("form", form);
		return "users/expertBio";
	}
	
	@RequestMapping(value = "listExperts.htm", method = { RequestMethod.GET , RequestMethod.POST})
	public String getAllExperts(@ModelAttribute("userForm") AddUserForm userForm , Model model,HttpServletRequest request) {
		
	   int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "expert");
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "expert");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "expert");
		String name="A";
		if(userForm.getUserSearchVal()!=null){
		     name = userForm.getUserSearchVal();
		}
		List<AddUserForm> experts = expertService.getSortingExpert(name,sortField,sortOrder,start,PaginationUtility.pageSize);

		int totalExpert =experts.size();
		
		String pageRequestParam = PaginationUtility.getCurrentPageRequestParam(request, "expert");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "expert");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "expert");
		String sortFieldRequestParam;
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "expert", "userInfo.firstname");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "expert", sortField);
		}

        model.addAttribute("experts", experts)
                .addAttribute("userForm", userForm)
                .addAttribute("totalExpertSize", totalExpert)
                .addAttribute("prp", pageRequestParam)
                .addAttribute("orp", orderRequestParam)
                .addAttribute("sfrp", sortFieldRequestParam)
                .addAttribute("sunrp", sortUsingNameRequestParam);
        return "experts/listExperts";
	}


}
	
