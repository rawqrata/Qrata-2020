package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;

/**
 * @author kamal
 *
 */
public interface UserDao extends IBaseDao<User> {
	

	public User getUser(String userName);


	public User getUser(String userName, Status status);

	
	/**
	 * @return
	 */
	public List<User> findAll(String name, int start, int limit, String sortField, String sortOrder, Status status);	

	public List<User> getUsers(Role role);

	
	/**
	 * Method to search user 
	 * @param role
	 * @param firstName
	 * @return
	 */
	public List<User> getUser_Role_NameStartsWith(Role role, String searchVal);
	
	/**
	 * @param email
	 * @return
	 */
	public User getUser_Email(String email);
	
	public User getUser_uuid(String uuid);
	
	/**
	 * @param role
	 * @param status
	 * @return
	 */
	public List<User> getUsers(Role role, Status status);
	
	public List<User> getUser_FirstName(String name);

	public int getTotalActiveUsersCount(String name, Status status);

	public List<User> getExperts(Role role, Status active, String sortField, String sortOrder, String name, int start, int pagesize);

	public List<User> getExpertsByLastName(Role role, Status active, String sortField, String sortOrder, String name, int start, int pagesize);
	
	public int getTotalUser_Role(String name, Roles expert, Status active);
	
	public int getTotalUser_RoleByLastName(String name, Roles role, Status active) ;
	
	public List<String> getUserNamesForAutoSuggest_Name(String name, Roles role, Status status);

	public List<Object[]> getExperts_Editors(Status active, String sortColumn, String sortOrder, String name, int start, 
			int pagesize, Long editorId);

	public int getTotalExperts_Editor_Id(String name, long editorId, Status status);
	
	public List<User> getUsers_TopicId_Name(Role role, Status status, String name);
	
	public List<String> autoSuggestExpertByNameAndEditor(String name, Long editorId, Status status);

}
