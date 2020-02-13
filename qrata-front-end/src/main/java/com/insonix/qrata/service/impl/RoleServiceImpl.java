package com.insonix.qrata.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.dao.RoleDao;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.service.RoleService;

@Service("RolesService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDao rolesDao;
	
	@Override
	public Role getRoles_Id(Serializable id) {
		
		return rolesDao.get(id);
	}
}
