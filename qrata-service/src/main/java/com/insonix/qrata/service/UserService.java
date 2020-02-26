package com.insonix.qrata.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;


/**
 * @author kamal
 *
 */
public interface UserService extends UserDetailsService  {
	
	
	/**
     * Method to get user by username
     * @param userName
     * @return
     */
    public User getUser_UserName(String userName);
    /**
     * @param id
     * @return
     */
    public User getUser_Id(long id);
	/**
	 * @param user
	 */
	public boolean delete(User user);
	/**
	 * @param addUser
	 * @return
	 */
	public String save(AddUserForm addUser);
	/**
	 * @param user
	 */
	public boolean update(User user);
	/**
	 * @param usersList
	 */
	public boolean updateBulk(List<User> usersList);
	/**
	 * @return
	 */
	public List<User> findAll(String name, int start, int limit, String sortField, String sortOrder);
	public int getTotalActiveUsersCount(String name);
	
	/**
	 * @param user
	 * @param addUserForm
	 */
	public boolean editUser(User user , AddUserForm addUserForm);
	/**
	 * @param role
	 * @return
	 */
	public List<User> getUsers_Role(Role role);
	/**
	 * @param user
	 * @param model
	 */
	public void getEditForm(User user, Model model);

	
	/**
	 * @param role
	 * @param firstName
	 * @return
	 */
	public List<User> getUser_Role_NameStartsWith(Role role, String searchVal);
	
	/**
	 * @param email
	 * @return
	 */
	public boolean getUser_Email(String email);
	
	/**
	 * @param firstName
	 * @return
	 */
	public String getUsers_Category(String firstName);
	
	public User getUser_uuid(String uuid);
	
	/**
	 * @param role
	 * @param status
	 * @param sortOrder 
	 * @param sortField 
	 * @return
	 */
	public List<User> getUsers(Role role, Status status);
	
	public List<User> getUser_FirstName(String name);
	
	public List<User> getExperts(Role role, Status active, String sortField, String sortOrder, String name, int start, int pagesize);
	
	public int getTotalUser_Role(String name, Roles expert);
		
	public String suggestUserByNameOrRole(String name, String type);

	public List<AddUserForm> getExperts_Editors(Status active, String sortColumn, String sortOrder, String name, int start,
			int pagesize, Long editorId);
	
	public int getTotalExperts_Editor_Id(String name, long id, Status status);
	
	public String autoSuggestExpertByNameAndEditor(String name, Long editorId);
	
	public List<User> getUsers_TopicId_Name(Role role, Status status, String name);

} 
