package com.insonix.qrata.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.RoleDao;
import com.insonix.qrata.models.Role;

@Repository("RolesDao")
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {

	@Override
	public List<Role> findAll() {
		
		Status status = Status.ACTIVE;
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) getSession().createCriteria(Role.class).add(Restrictions.eq("status", status.getValue())).list();
		return roles;
	
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.dao.RolesDao#getRoles(com.insonix.qrata.constants.Status)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles(Status status) {
		Criteria criteria=getSession().createCriteria(Role.class);
				criteria.add(Restrictions.eq("status", status.getValue()));
		return criteria.list();
	}

	
}
