package com.insonix.qrata.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.UserDao;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;
import com.insonix.qrata.models.UserInfo;
import com.insonix.qrata.service.RoleService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao	usersDao;	
	@Autowired
	RoleService rolesService;
	@Autowired
	SiteReviewsService siteReviewsService;
		
	CustomSortComparator<User> customSort = new CustomSortComparator<User>();

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException
		{
			User user = usersDao.getUser(userName, Status.ACTIVE);
			if (user == null)
				throw new UsernameNotFoundException("User not found: "
						+ userName);
			else
				{
					return makeUser(user);
				}
		}

	/**
	 * @param user
	 * @return
	 */
	private org.springframework.security.core.userdetails.User makeUser(
			User user)
		{
			return new org.springframework.security.core.userdetails.User(
					user.getUserName(), user.getPassword(), true, true, true,
					true, makeGrantedAuthorities(user));
		}

	/**
	 * @param user
	 * @return
	 */
	private List<GrantedAuthority> makeGrantedAuthorities(User user){
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		Role role = user.getRole();
		result.add(new GrantedAuthorityImpl(role.getName().trim()));
		return result;
	}

	

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#getUser_UserName(java.lang.String)
	 */
	/*this method will return user by 
	 * its username and whose status will be active
	 * */
	@Override
	public User getUser_UserName(String userName) {
	   return usersDao.getUser(userName, Status.ACTIVE);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#getUser_Id(int)
	 */
	/*this method is used to get user by its id*/
	@Override
	public User getUser_Id(long id) {
		return usersDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#delete(com.insonix.qrata.models.User)
	 */
	
	/*this method is used to delete the user*/
	@Override
	public boolean delete(User user) {
		user.setStatus(Status.DELETED.getValue());
		try{
			usersDao.delete(user);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#save(com.insonix.qrata.entity.AddUserForm)
	 */
	/*
	 * this method is getting model (AddUserForm) used to save a new user.
	 * */ 
	@Override
	public String save(AddUserForm addUser) {
		UserInfo userInfo = new UserInfo(addUser.getFirstName().trim(), addUser.getLastName().trim(), addUser.getEmail());		
		User user = new User(addUser.getUserName(), addUser.getPassword(), userInfo);
		userInfo.setUser(user);
		user.setStatus(Status.ACTIVE.getValue());
		userInfo.setStatus(Status.ACTIVE.getValue());
		Role role = rolesService.getRoles_Id(addUser.getRoleId());
		user.setRole(role);
		String id = usersDao.save(user);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#update(com.insonix.qrata.models.User)
	 */
	/*
	 * this method is used to update the user's information
	 */	
	@Override
	public boolean update(User user) {
		try{
			usersDao.update(user);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#updateBulk(java.util.List)
	 */
	/*
	 * this method is used to update user's information when users are more than one 
	 */	
	@Override
	public boolean updateBulk(List<User> usersList) {
		try{
			usersDao.updateBulk(usersList);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#findAll()
	 */
	/*
	 * this method is used to show all users on home page
	 * */
	@Override
	public List<User> findAll(String name, int start, int limit, String sortField, String sortOrder) {
		List<User> userList = usersDao.findAll(name, start, limit, sortField, sortOrder, Status.ACTIVE);
		return userList;
	}

	@Override
	public int getTotalActiveUsersCount(String name) {
		return usersDao.getTotalActiveUsersCount(name, Status.ACTIVE);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#editUser(com.insonix.qrata.models.User, com.insonix.qrata.entity.AddUserForm)
	 */
	/*
	 * this method is used to edit information of any user
	 * */
	@Override
	public boolean editUser(User user, AddUserForm addUserForm) {
		Role role = rolesService.getRoles_Id(addUserForm.getRoleId());
		user.setRole(role);		
		user.setUserName(addUserForm.getUserName());
		UserInfo userInfo = user.getUserinfo();
		userInfo.setFirstname(addUserForm.getFirstName().trim());
		userInfo.setLastname(addUserForm.getLastName().trim());
		userInfo.setEmail(addUserForm.getEmail());
		try {
			usersDao.update(user);			
			return true;
		} catch(DataAccessException ex) {
			return false;
		}	
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#getUsers_Role(com.insonix.qrata.constants.Constants.Roles)
	 */
	/*
	 * this method is used to get list of user's according to their roles
	 */	
	@Override
	public List<User> getUsers_Role(Role role) {
		List<User> userList = usersDao.getUsers(role);
		Collections.sort(userList, customSort);
		return userList;
	}
	
	@Override
	public List<User> getUsers(Role role,Status status) {
		List<User> userList = usersDao.getUsers(role, status);
		Collections.sort(userList, customSort);
		return userList;
	}

	@Override
	public void getEditForm(User user , Model model) {
		AddUserForm userForm = new AddUserForm(user);
		try {
			BeanUtils.copyProperties(userForm, user);
			model.addAttribute("userData", userForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#getUser_Role_NameStartsWith(java.lang.String)
	 */
	@Override
	public List<User> getUser_Role_NameStartsWith(Role role, String searchVal) {
		List<User> userList =  usersDao.getUser_Role_NameStartsWith(role, searchVal);
		Collections.sort(userList, customSort);
		return userList;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#getUser_Email(java.lang.String)
	 */
	@Override
	public boolean getUser_Email(String email) {
		User user = usersDao.getUser_Email(email);
		if(user!=null) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#getUsers_Category(java.lang.String)
	 */
	@Override
	public String getUsers_Category(String firstName) {
		StringBuffer categorizesUsers = new StringBuffer();

		Role role = rolesService
				.getRoles_Id(Constants.Roles.EDITOR.getValue());
		
		List<User> editorUsers = getUser_Role_NameStartsWith(role, firstName);
		
		Iterator<User> editorsItr = editorUsers.iterator();
		
		categorizesUsers.append("[");
		
		while (editorsItr.hasNext()) {
			
			User editorUser = editorsItr.next();
			UserInfo editorUserInfo = editorUser.getUserinfo();
			
			categorizesUsers.append("{\"label\":\"").append(editorUserInfo.getFirstname()).append(" ")
			.append(editorUserInfo.getLastname()).append("\",");
			categorizesUsers.append("\"category\":\"").append(role.getName()+"\"").append("}").append(",");
		}
		
		Role role1 = rolesService.getRoles_Id(Constants.Roles.EXPERT.getValue());
		
		List<User> expertUsers = getUser_Role_NameStartsWith(role1, firstName);
		
		Iterator<User> expertItr = expertUsers.iterator();
		
		while(expertItr.hasNext()){
			
			User user = expertItr.next();
			UserInfo expertUserInfo = user.getUserinfo();
			
			categorizesUsers.append("{\"label\":\"").append(expertUserInfo.getFirstname()).append(" ")
			.append(expertUserInfo.getLastname()).append("\",");
			categorizesUsers.append("\"category\":\"").append(role1.getName()+"\"").append("}").append(",");
			
		}
		
		Role role2 = rolesService.getRoles_Id(Constants.Roles.ADMIN.getValue());
		
		List<User> adminUsers = getUser_Role_NameStartsWith(role2, firstName);
		
		Iterator<User> adminItr = adminUsers.iterator();
		
		while(adminItr.hasNext()){
			
			User adminUser = adminItr.next();
			UserInfo adminUserInfo = adminUser.getUserinfo();
			
			categorizesUsers.append("{\"label\":\"").append(adminUserInfo.getFirstname()).append(" ")
			.append(adminUserInfo.getLastname()).append("\",");
			categorizesUsers.append("\"category\":\"").append(role2.getName()+"\"").append("}").append(",");
		}
		if (categorizesUsers.toString().endsWith(","))
			categorizesUsers.replace(categorizesUsers.length() - 1, categorizesUsers.length(), "");
		
		categorizesUsers.append("]");
		System.out.println(categorizesUsers.toString());
		 
		return categorizesUsers.toString();
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.UserService#getUser_uuid(java.lang.String)
	 */
	@Override
	public User getUser_uuid(String uuid) {
		return usersDao.getUser_uuid(uuid);
	}

	@Override
	public List<User> getUser_FirstName(String name) {
		List<User> userList = usersDao.getUser_FirstName(name);
		Collections.sort(userList, customSort);
		return userList;
	}

	@Override
	public List<User> getExperts(Role role, Status active, String sortField, String sortOrder, String name, int start, int pagesize) {
		if(name == null){
			name = "%";
		}else{
			name = name.trim() + "%";
		}
				
		return usersDao.getExperts(role, active, sortField, sortOrder,name, start,pagesize);
	}

	@Override
	public int getTotalUser_Role(String name, Roles expert) {
		if(name == null){
			name = "%";
		}else{
			name = name.trim() + "%";
		}
		return usersDao.getTotalUser_Role(name, expert , Status.ACTIVE);
	}
		
	@Override
	public String suggestUserByNameOrRole(String name, String type) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			Roles role = null;
			if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("EXPERT")) {
				role = Roles.EXPERT;
			} else if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("EDITOR")) {
				role = Roles.EDITOR;
			}
			
			List<String> infoList = usersDao.getUserNamesForAutoSuggest_Name(name, role, Status.ACTIVE);
			for(String value : infoList) {
				obj = new JSONObject();
				obj.put("name", value);
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}


	@Override
	public List<AddUserForm> getExperts_Editors(Status active, String sortColumn, String sortOrder, String name, int start,
			int pagesize, Long editorId) {
		List<AddUserForm> formList = new ArrayList<>();
		List<Object[]> objects = null;
		
		name = Utility.searchValueCheck(name);
		objects =  usersDao.getExperts_Editors(active, sortColumn, sortOrder,name, start, pagesize, editorId);
		
		for(Object[] obj : objects) {
			long userId 		= obj[0] != null ? (long) obj[0] : 0;
			String firstName 	= obj[1] != null ? obj[1].toString() : "";
			String lastName		= obj[2] != null ? obj[2].toString() : "";
			String userName		= obj[3] != null ? obj[3].toString() : "";
			String email		= obj[4] != null ? obj[4].toString() : "";
			
			User user = usersDao.get(userId);
			int counter = siteReviewsService.getExpertCoreRatings(user);
			int totalCounter = siteReviewsService.getExpertTotalRatings(user);
			
			AddUserForm form = new AddUserForm();
			form.setId(userId);
			form.setFirstName(firstName);
			form.setLastName(lastName);
			form.setUserName(userName);
			form.setEmail(email);
			form.setNoOfCoreRatings(counter);
			form.setNoOfTotalRatings(totalCounter);
			
			formList.add(form);
		}
		
		return formList;
	}

	@Override
	public int getTotalExperts_Editor_Id(String name, long editorId, Status status) {
		name = Utility.searchValueCheck(name);
		return usersDao.getTotalExperts_Editor_Id(name, editorId, status);	
	}

	
	@Override
	public List<User> getUsers_TopicId_Name(Role role, Status status, String name) {
		List<User> userList = usersDao.getUsers_TopicId_Name(role, status, name);
		return userList;
	}
	
	@Override
	public String autoSuggestExpertByNameAndEditor(String name, Long editorId) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			name = Utility.searchValueCheck(name);
			List<String> infoList = usersDao.autoSuggestExpertByNameAndEditor(name, editorId, Status.ACTIVE);
			for(String value : infoList) {
				obj = new JSONObject();
				obj.put("name", value);
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

}
