package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.Role;

public interface RoleDao extends IBaseDao<Role> {
	public List<Role> findAll();
	
	/**
	 * Method to get Roles by Status
	 * 
	 * @param status
	 * @return
	 */
	public List<Role> getRoles(Status status);

}
