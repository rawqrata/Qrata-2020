package com.insonix.qrata.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.entity.ExpertBioForm;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.RoleService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.QrataImagesUtil;
import com.insonix.qrata.utility.Utility;

/**
 * @author Raman
 *
 */
@Service("expertService")
public class ExpertServiceImpl implements ExpertService {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService rolesService;
	@Autowired
	SiteReviewsService siteReviewsService;
	
	CustomSortComparator<User> customSort = new CustomSortComparator<User>();
	
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.ExpertService#getExperts()
	 */
	@Override
	public List<User> getExperts() {
		Role role = rolesService.getRoles_Id(Constants.Roles.EXPERT.getValue());
		List<User> usersList = userService.getUsers(role, Status.ACTIVE);
		return usersList;
	}

	@Override
	public List<AddUserForm> getSortingExpert(String name, String sortField, String sortOrder, int start, int pagesize) {
		List<AddUserForm > addUserForms = new ArrayList<>();
		
		Role role = rolesService.getRoles_Id(Constants.Roles.EXPERT.getValue());
		List<User> userList = userService.getExperts(role, Status.ACTIVE, sortField, sortOrder, name, start, pagesize);
		
		for(User user : userList) {
			int counter = siteReviewsService.getExpertCoreRatings(user);
			int totalCounter = siteReviewsService.getExpertTotalRatings(user);
			
			AddUserForm addUserForm = new AddUserForm();
			addUserForm.setId(user.getId());
			addUserForm.setFirstName(user.getUserinfo().getFirstname());
			addUserForm.setLastName(user.getUserinfo().getLastname());
			addUserForm.setUserName(user.getUserName());
			addUserForm.setEmail(user.getUserinfo().getEmail());
			addUserForm.setNoOfCoreRatings(counter);
			addUserForm.setNoOfTotalRatings(totalCounter);
			addUserForm.setBio(user.getUserinfo().getBio());
			addUserForm.setImageName(user.getUserinfo().getImageName());
			
			addUserForms .add(addUserForm);
		}
		return addUserForms;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.ExpertService#searchExperts(java.lang.String)
	 */
	@Override
	public List<User> searchExperts(String searchVal) {
		Role role = rolesService.getRoles_Id(Constants.Roles.EXPERT.getValue());
		List<User> usersList = userService.getUser_Role_NameStartsWith(role, searchVal);
		Collections.sort(usersList, customSort);
		return usersList;
	}	
	
	
	@Override
	public boolean saveExpertBio(ExpertBioForm expertBioForm) {
		MultipartFile file = expertBioForm.getFile();
		User user = userService.getUser_Id(expertBioForm.getId());		
		if (!file.isEmpty()) {
			try {
				if(user.getUserinfo().getImagePath() != null) {
					QrataImagesUtil.deleteOldExpertPicFile(user.getUserinfo().getImageName(), user.getId());
				}
				
				BufferedImage bImg = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
				bImg = Utility.resizeImage(bImg, 102, 102);
				
				String imgName = file.getOriginalFilename();
				String imgPath = "/Expert-Pics/"+user.getId()+"/"+user.getId()+"_"+imgName;
				File expertPic = QrataImagesUtil.createExpertPicFile(imgName, user.getId());
				ImageIO.write(bImg, "png", expertPic);
				
				user.getUserinfo().setImagePath(imgPath);
				user.getUserinfo().setImageName(imgName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(!StringUtils.isEmpty(expertBioForm.getBio())){
			user.getUserinfo().setBio(expertBioForm.getBio());
		}
		
		boolean result = userService.update(user);
		return result;
	}

	@Override
	public List<AddUserForm> getSortingALLExpert(String name, String sortColumn, String sortOrder, int start, 
			int pagesize, Long editorId, User user) {        
		List<AddUserForm> formList = userService.getExperts_Editors(Status.ACTIVE, sortColumn, sortOrder, 
				name, start, pagesize, editorId);
		return formList;
	}
	
	@Override
	public int getTotalExperts_Editor_Id(String name, long editorId) {
		return userService.getTotalExperts_Editor_Id(name, editorId, Status.ACTIVE);
	}
	
	@Override
	public String removeExpertPic(Long id) {
		JSONObject obj = new JSONObject();
		try {
			User user = userService.getUser_Id(id);
			if(user.getUserinfo().getImagePath() != null) {
				QrataImagesUtil.deleteExpertPicFolder(user.getUserinfo().getImageName(), id);
			}
			user.getUserinfo().setImageName(null);
			user.getUserinfo().setImagePath(null);
			userService.update(user);
			obj.put("msg", "Expert's pic has been removed successfully.");
		} catch (JSONException e) {
			try {
				obj.put("msg", "There is some problem removing the expert's pic. Please try again later.");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
}
