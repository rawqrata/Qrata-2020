package com.qrata.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.qrata.entity.AddUserForm;
import com.qrata.enums.ReviewStatus;
import com.qrata.enums.Status;
import com.qrata.models.Role;
import com.qrata.models.SiteReview;
import com.qrata.models.User;
import com.qrata.respository.SiteReviewsRepository;

@Service
public class EditorServiceImpl implements EditorService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private SiteReviewsRepository siteReviewsRepository;

	@Override
	public List<User> getEditors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchEditors(String searchVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddUserForm> getSortingEditor(String name, String sortField, String sortOrder, int start,
			int pageSize) {
		List<AddUserForm > addUserForms = new ArrayList<>();
		 Role role = new Role();
		 role.setId(new Short("1"));
		 
			/* Role role = rolesService.getRoles_Id(Constants.Roles.EDITOR.getValue()); */

			@SuppressWarnings("unchecked")
			List<Object[]> all = entityManager.createNativeQuery("SELECT u.id as uid,u.username,uf.firstname,uf.lastname,uf.email,uf.bio,uf.image_name FROM user u INNER JOIN userinfo uf ON u.id = uf.user_id INNER JOIN  user_roles r ON u.id = r.users_id "
					              + " WHERE u.status ="+Status.ACTIVE.getValue()
					              +" AND r.roles_id ="+role.getId() 
					              +" AND (uf.firstname like '%"+name+"%' or uf.lastname like '%"+name+"%' or uf.email like '%"+name+"%' or u.username like '%"+name+"%')").getResultList();
			
			
			User user = new User();
			for(Object[] obj : all){
				int counter = 0;
				int totalCounter = 0;
				BigInteger userid=obj[0] != null ?  (BigInteger) obj[0] : new BigInteger("0");
				user.setId(userid.longValue());
				
				Set<SiteReview> siteReviews = siteReviewsRepository.getSiteReviewByUser(userid.longValue());
				for(SiteReview siteReview : siteReviews){
					if(siteReview.getReviewStatus() == ReviewStatus.ONLINE.getValue()){
						if(user.getId() == siteReview.getSite().getCreatedBy()){
							counter++;
						}
						totalCounter++;
					}
				}
				
				AddUserForm addUserForm = new AddUserForm();
				addUserForm.setId(userid.longValue());
				addUserForm.setUserName(obj[1] != null ? obj[1].toString() : "");
				addUserForm.setFirstName(obj[2] != null ? obj[2].toString() : "");
				addUserForm.setLastName(obj[3] != null ? obj[3].toString() : "");
				
				addUserForm.setEmail(obj[4] != null ? obj[4].toString() : "");
				addUserForm.setBio(obj[5] != null ? obj[5].toString() : "");
				addUserForm.setImageName(obj[6] != null ? obj[6].toString() : "");
				addUserForm.setNoOfCoreRatings(counter);
				addUserForm.setNoOfTotalRatings(totalCounter);
				addUserForms .add(addUserForm);
			}
			return addUserForms;
	}

	@Override
	public List<AddUserForm> getSortingAllEditors(String name, String sortColumn, String sortOrder, int start,
			int pageSize, Long editorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalEditorsForExpertByExpertId(String name, Long editorId) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
}
