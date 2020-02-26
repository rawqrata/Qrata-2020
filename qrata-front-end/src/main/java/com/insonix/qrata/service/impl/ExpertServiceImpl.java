package com.insonix.qrata.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.UserDao;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.entity.ExpertBioForm;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.RoleService;
import com.insonix.qrata.service.SiteReviewService;
import com.insonix.qrata.service.UserService;

@Service("expertService")
public class ExpertServiceImpl implements ExpertService {
	@Autowired
	UserDao	usersDao;
	@Autowired
	UserService userService;
	@Autowired
	RoleService rolesService;
	@Autowired SiteReviewService siteReviewService;
	
	CustomSortComparator<User> customSort = new CustomSortComparator<User>();
	
	@Override
	public ExpertBioForm getUser_Id(long id) {
		User user = usersDao.get(id);
		ExpertBioForm form= new ExpertBioForm();
		form.setId(user.getId());
		form.setFirstName(user.getUserinfo().getFirstname());
		form.setLastName(user.getUserinfo().getLastname());
		form.setBio(user.getUserinfo().getBio());
		form.setImageName(user.getUserinfo().getImageName());
		return form;
	}

	@Override
	public List<AddUserForm> getSortingExpert(String name, String sortField, String sortOrder, int start, int pagesize) {
		List<AddUserForm > addUserForms = new ArrayList<>();
		if(name!=null){
		Role role = rolesService.getRoles_Id(Constants.Roles.EXPERT.getValue());
		List<User> userList = userService.getExperts(role,Status.ACTIVE,sortField,sortOrder,name,start,pagesize);
		
		for(User user : userList){
						
			AddUserForm addUserForm = new AddUserForm();
			addUserForm.setId(user.getId());
			addUserForm.setFirstName(user.getUserinfo().getFirstname());
			addUserForm.setLastName(user.getUserinfo().getLastname());
			addUserForm.setBio(user.getUserinfo().getBio());
			addUserForm.setImageName(user.getUserinfo().getImageName());
			//int totalRatings = siteReviewsService.getTotalRatings_ExpertId(user.getId(), null);
			//addUserForm.setCount(totalRatings);
			
			addUserForms .add(addUserForm);
		}
		}
		return addUserForms;
	}
	
	@Override
	public List<AddUserForm> searchExperts(String searchVal) {
		Role role = rolesService.getRoles_Id(Constants.Roles.EXPERT.getValue());
		List<User> usersList = userService.getUser_Role_NameStartsWith(role, searchVal);
		List<AddUserForm> formList = new ArrayList<>();
		for(User user : usersList){
			AddUserForm form = new AddUserForm();
			form.setId(user.getId());
			form.setFirstName(user.getUserinfo().getFirstname());
			form.setLastName(user.getUserinfo().getLastname());
			form.setUserName(user.getUserName());
			form.setBio(user.getUserinfo().getBio());
			form.setImageName(user.getUserinfo().getImageName());
			formList .add(form);
		}
		Collections.sort(usersList, customSort);
		return formList;
	}	
}
