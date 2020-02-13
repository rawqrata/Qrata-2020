package com.insonix.qrata.dao.impl;

import java.math.BigInteger;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.insonix.qrata.dao.CommonDao;

/**
 * @author Gurminder Singh
 *
 * @date 01-Aug-2013
 */
@Service("commonDaoService")
public class CommonDaoImpl extends HibernateDaoSupport implements CommonDao {

	@Override
	public Long getNextValueOfSequence(String sequenceName) {
		SQLQuery query = getSession().createSQLQuery("select nextval(?)");
		query.setParameter(0, sequenceName);
	    Long key = query.uniqueResult()!=null ? ((BigInteger) query.uniqueResult()).longValue() : null;
	    return key;
	}

}
