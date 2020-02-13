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
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.EditorService;
import com.insonix.qrata.service.RoleService;
import com.insonix.qrata.service.UserService;


/**
 * @author Raman
 *
 */

@Service("editorService")
public class EditorServiceImpl implements EditorService {

	@Autowired
	RoleService rolesService;
	
	@Autowired
	UserService userService;
		
	CustomSortComparator<User> customSort = new CustomSortComparator<User>();
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.EditorService#getEditors()
	 */
	@Override
	public List<User> getEditors() {
		Role role=rolesService.getRoles_Id(Constants.Roles.EDITOR.getValue());
		List<User> userList = userService.getUsers(role, Status.ACTIVE);
		Collections.sort(userList, customSort);
		return userList;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.EditorService#delete(java.lang.String)
	 */
//	@Override
//	public void delete(String uuid) {
//		User user = userService.getUser_uuid(uuid);
//		List<UserRole> userRoles=user.getUserRoles();
//		Iterator<UserRole> itr=userRoles.iterator();
//		while(itr.hasNext()){
//			UserRole userRole=itr.next();
//			if(com.insonix.qrata.constants.Constants.Roles.EDITOR.getValue()==userRole.getRole().getId()){
//				itr.remove();
//				user.getUserRoles().remove(userRole);
//				userRolesService.delete(userRole);
//			}
//		}
//		
//	}
	
	@Override
	public List<User> searchEditors(String searchVal) {
		Role role = rolesService.getRoles_Id(Constants.Roles.EDITOR.getValue());
		List<User> userList = userService.getUser_Role_NameStartsWith(role, searchVal);
		Collections.sort(userList, customSort);
		return userList;
	}

	@Override
	public List<AddUserForm> getSortingEditor(String name, String sortField, String sortOrder, int start, int pagesize) {
		List<AddUserForm > addUserForms = new ArrayList<>();
		
		Role role = rolesService.getRoles_Id(Constants.Roles.EDITOR.getValue());
		List<User> editors  = userService.getExperts(role,Status.ACTIVE,sortField,sortOrder,name,start,pagesize);
		
		for(User user : editors){
			int counter = 0;
			int totalCounter = 0;
			
			Set<SiteReview> siteReviews = user.getSiteReviews();
			for(SiteReview siteReview : siteReviews){
				if(siteReview.getReviewStatus() == ReviewStatus.ONLINE.getValue()){
					if(user.getId() == siteReview.getSite().getCreatedBy()){
						counter++;
					}
					totalCounter++;
				}
			}
			
			AddUserForm addUserForm = new AddUserForm();
			addUserForm.setId(user.getId());
			addUserForm.setUuid(user.getUuid());
			addUserForm.setFirstName(user.getUserinfo().getFirstname());
			addUserForm.setLastName(user.getUserinfo().getLastname());
			addUserForm.setUserName(user.getUserName());
			addUserForm.setEmail(user.getUserinfo().getEmail());
			addUserForm.setNoOfCoreRatings(counter);
			addUserForm.setNoOfTotalRatings(totalCounter);
			addUserForm.setBio(user.getUserinfo().getBio());
//			addUserForm.setImage(user.getUserinfo().getImage());
			
			addUserForms.add(addUserForm);
		}
		return addUserForms;
	}

}
