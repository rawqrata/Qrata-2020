package com.insonix.qrata.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.dao.UserDao;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired UserDao userDao;
	CustomSortComparator<User> customSort = new CustomSortComparator<User>();
	
	@Override
	public int getTotalUser_Role(String name, Roles expert) {
		if(name == null){
			name = "%";
		}else{
			name = name.trim() + "%";
		}
		return userDao.getTotalUser_RoleByLastName(name, expert , Status.ACTIVE);
	}
	@Override
	public List<User> getUser_Role_NameStartsWith(Role role, String searchVal) {
		List<User> userList =  userDao.getUser_Role_NameStartsWith(role, searchVal);
		Collections.sort(userList, customSort);
		return userList;
	}
	@Override
	public List<User> getExperts(Role role, Status active, String sortField, String sortOrder, String name, int start, int pagesize) {
				
		return userDao.getExpertsByLastName(role, active, sortField, sortOrder, name, start, pagesize);
	}
	@Override
	public User getUser_Id(long id) {
		return userDao.get(id);
	}

}
