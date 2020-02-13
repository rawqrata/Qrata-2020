package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.entity.ExpertBioForm;
import com.insonix.qrata.models.User;

/**
 * @author kamal
 *
 */
public interface ExpertService {
	
	public List<User> getExperts();
	
	public List<User> searchExperts(String searchVal);

	public List<AddUserForm> getSortingExpert(String sortField, String sortOrder, String name, int start, int pagesize);
		
	public boolean saveExpertBio(ExpertBioForm expertBioForm);

	public List<AddUserForm> getSortingALLExpert(String name, String sortColumn, String sortOrder, int start, 
			int pagesize, Long editorId, User user);
	
	public int getTotalExperts_Editor_Id(String name, long editorId);
	
	public String removeExpertPic(Long id);
	
}
