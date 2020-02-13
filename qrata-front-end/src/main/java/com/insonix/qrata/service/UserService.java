package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;

public interface UserService {

	
	public int getTotalUser_Role(String name, Roles expert);
	public List<User> getUser_Role_NameStartsWith(Role role, String searchVal);
	public List<User> getExperts(Role role, Status active, String sortField, String sortOrder, String name, int start, int pagesize);
	public User getUser_Id(long id);
}
