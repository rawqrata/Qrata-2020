package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.entity.ExpertBioForm;

public interface ExpertService {
	public ExpertBioForm getUser_Id(long id);
	public List<AddUserForm> searchExperts(String searchVal);
	public List<AddUserForm> getSortingExpert(String name, String sortField, String sortOrder, int start, int pagesize);
	
}
