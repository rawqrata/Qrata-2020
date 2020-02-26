package com.insonix.qrata.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.entity.ExpertBioForm;
import com.insonix.qrata.models.User;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.QrataImagesUtil;

@Controller("expertController")
@RequestMapping(value="expert")
public class ExpertController {
	@Autowired
	ExpertService expertService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "expertBio.htm", method = { RequestMethod.GET })
	public String expertBioInfo(HttpServletRequest request, Model model,
			@RequestParam("id") long id) {
		ExpertBioForm form = expertService.getUser_Id(id);
		model.addAttribute("form", form);
		return "users/expertBio";
	}

	@RequestMapping(value = "listExperts.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getAllExperts(
			@ModelAttribute("userForm") AddUserForm userForm, Model model,
			HttpServletRequest request) {
		String name="A";
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "expert");
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "expert");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "expert");
		if(userForm.getUserSearchVal()!=null){
		     name = userForm.getUserSearchVal();
		}
		
		int totalExpert = userService.getTotalUser_Role(name, Roles.EXPERT);
		List<AddUserForm> experts = expertService.getSortingExpert(name, sortField,sortOrder, start, PaginationUtility.pageSize);
		//List<AddUserForm> experts = expertService.searchExperts(userForm.getUserSearchVal());
		model.addAttribute("experts", experts);
		model.addAttribute("userForm", userForm);
		model.addAttribute("totalExpertSize", totalExpert);
		return "experts/listExperts";
	}
	@RequestMapping(value="getUserImage.htm", method=RequestMethod.GET)
	public void getUserImage(@RequestParam("userId") long userId, HttpServletResponse response) {
		OutputStream out  = null;
		try {		
			User user = userService.getUser_Id(userId);
			if(StringUtils.isNotEmpty(user.getUserinfo().getImagePath())) {
				File qrataData = QrataImagesUtil.getQrataDataFolder();
				File expertPicFile = new File(qrataData, user.getUserinfo().getImagePath());
				
				out = response.getOutputStream();
				BufferedImage img = ImageIO.read(expertPicFile);
				ImageIO.write(img, "png", out);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null)
					out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}