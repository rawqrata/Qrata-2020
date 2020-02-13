/**
 * 
 */
package com.insonix.qrata.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author raman
 * @param <T> as generic
 *
 */
public interface IBaseDao<T> {
	/**
	 * @author raman
	 * @param domain
	 * @return void
	 */
	public String save(T domain);
	
	/**
	 * @author raman
	 * @param domain
	 */
	public void saveBulk(List<T> domain);
	
	/**
	 * @author raman
	 * @param domain
	 */
	public void update(T domain);
	
	/**
	 * @author raman
	 * @param domain
	 */
	public void updateBulk(List<T> domain);
	
	/**
	 * @author raman
	 * @param domain
	 */
	public void delete(T domain);
	
	/**
	 * @author raman
	 * @param id
	 * @return
	 */
	public T get(Serializable id);

}
