package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.models.User;

/**
 * @author Raman
 *
 */
public interface EditorService {

	public List<User> getEditors();
	
	/**
	 * Method to Delete Editor
	 * @param uuid
	 * 
	 */
//	public void delete(String uuid);
	
	public List<User> searchEditors(String searchVal);

	public List<AddUserForm> getSortingEditor(String userSearchVal, String sortField, String sortOrder, int start, int pagesize);
}
