package com.qrata.service;

import java.util.List;

import com.qrata.entity.AddUserForm;
import com.qrata.models.User;

public interface EditorService {
	
public List<User> getEditors();
	
	
	
	public List<User> searchEditors(String searchVal);

	public List<AddUserForm> getSortingEditor(String userSearchVal, String sortField, String sortOrder, int start, int pageSize);

    public List<AddUserForm> getSortingAllEditors(String name, String sortColumn, String sortOrder, int start, int pageSize, Long editorId);

    public int getTotalEditorsForExpertByExpertId(String name, Long editorId);

}
