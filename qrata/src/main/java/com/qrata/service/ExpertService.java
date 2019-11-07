package com.qrata.service;

import java.util.List;

import com.qrata.entity.AddUserForm;
import com.qrata.entity.ExpertBioForm;
import com.qrata.models.User;

public interface ExpertService {
	
	public List<User> getExperts();

    public ExpertBioForm getUser_Id(long id);

	//public List<User> searchExperts(String searchVal);

	public List<AddUserForm> getSortingExpert(String sortField, String sortOrder, String name, int start, int pageSize);
		
	public boolean saveExpertBio(ExpertBioForm expertBioForm);

	public List<AddUserForm> getSortingAllExpert(String name, String sortColumn, String sortOrder, int start,
                                                 int pageSize, Long editorId, User user);
	
	public int getTotalExpertsForEditorByEditorId(String name, long editorId);
	
	public String removeExpertPic(Long id);

}
