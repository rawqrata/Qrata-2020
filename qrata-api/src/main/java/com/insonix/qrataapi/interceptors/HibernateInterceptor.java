package com.insonix.qrataapi.interceptors;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.insonix.qrata.models.CommonEntity;
import com.insonix.qrata.utility.Utility;

/**
 * @author Gurminder
 * @since March,2013
 */
public class HibernateInterceptor extends EmptyInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3511086924142655580L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object,
	 * java.io.Serializable, java.lang.Object[], java.lang.String[],
	 * org.hibernate.type.Type[])
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		if (entity != null && entity instanceof CommonEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (propertyNames[i].equals("uuid")) {
					state[i] = Utility.getUUID();
				}
				if (propertyNames[i].equals("dateCreated")) {
					state[i] = new Date();
				}
				if (propertyNames[i].equals("lastUpdated")) {
					state[i] = new Date();
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object,
	 * java.io.Serializable, java.lang.Object[], java.lang.Object[],
	 * java.lang.String[], org.hibernate.type.Type[])
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		if (entity != null && entity instanceof CommonEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (propertyNames[i].equals("lastUpdated")) {
					currentState[i] = new Date();
				}
			}
		}
		return true;
	}

}
