package com.insonix.qrata.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.RoleDao;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.service.RoleService;

/**
 * @author kamal
 *
 */
@Service("RolesService")
public class RolesServiceImpl implements RoleService {
	@Autowired
	RoleDao rolesDao;

	CustomSortComparator<Role> customSort = new CustomSortComparator<Role>();
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RolesService#getRoles_Id(java.io.Serializable)
	 */
	@Override
	public Role getRoles_Id(Serializable id) {
		
		return rolesDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RolesService#delete(com.insonix.qrata.models.Roles)
	 */
	@Override
	public void delete(Role roles) {
		rolesDao.delete(roles);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RolesService#save(com.insonix.qrata.models.Roles)
	 */
	@Override
	public String save(Role roles) {
		String id = rolesDao.save(roles);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RolesService#update(com.insonix.qrata.models.Roles)
	 */
	@Override
	public void update(Role roles) {
		rolesDao.update(roles);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RolesService#updateBulk(java.util.List)
	 */
	@Override
	public void updateBulk(List<Role> rolesList) {
		rolesDao.updateBulk(rolesList);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.RolesService#findAll()
	 */
	@Override
	public List<Role> findAll() {
		List<Role> roles = rolesDao.findAll();
		Collections.sort(roles, customSort);
		return roles;
	}
	
	@Override
	public List<Role> getActiveRoles(){
		List<Role> roles=rolesDao.getRoles(Status.ACTIVE);
		Collections.sort(roles, customSort);
		return roles;
	}

}
