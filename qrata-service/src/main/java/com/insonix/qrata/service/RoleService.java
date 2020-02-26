package com.insonix.qrata.service;

import java.io.Serializable;
import java.util.List;

import com.insonix.qrata.models.Role;


/**
 * @author kamal
 *
 */
public interface RoleService {
	
	/**
	 * @param id
	 * @return
	 */
	public Role getRoles_Id(Serializable id);
	/**
	 * @param roles
	 */
	public void delete(Role roles);
	/**
	 * @param roles
	 * @return
	 */
	public String save(Role roles);
	/**
	 * @param roles
	 */
	public void update(Role roles);
	/**
	 * @param rolesList
	 */
	public void updateBulk(List<Role> rolesList);
	/**
	 * @return
	 */
	public List<Role> findAll();
	
	/**
	 * Method to get Active Roles
	 * 
	 * @author Ramandeep Singh
	 * @return
	 */
	public List<Role>  getActiveRoles();
	

}
