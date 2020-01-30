package com.qrata.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrata.entity.AddUserForm;
import com.qrata.entity.ExpertBioForm;
import com.qrata.enums.Constants;
import com.qrata.enums.Status;
import com.qrata.models.Role;
import com.qrata.models.User;
import com.qrata.respository.RoleRepository;
import com.qrata.respository.UserRespository;

@Service
public class ExpertServiceImpl implements ExpertService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRespository userRespository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	SiteReviewService siteReviewService;
	
	
	@Override
	public List<User> getExperts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpertBioForm getUser_Id(long id) {
		 User user = userRespository.findById(id).get();
	        ExpertBioForm form= new ExpertBioForm();
	        form.setId(user.getId());
	        form.setFirstName(user.getUserinfo().getFirstname());
	        form.setLastName(user.getUserinfo().getLastname());
	        form.setBio(user.getUserinfo().getBio());
	        form.setImageName(user.getUserinfo().getImageName());
	        return form;
	}

	@Override
	public List<AddUserForm> getSortingExpert(String name,String sortField, String sortOrder, int start,
			int pageSize) {
		 List<AddUserForm > addUserForms = new ArrayList<>();
		/* To do
		 * Role role =roleRepository.findById(Constants.Roles.EXPERT.getValue()).get();
		 */
		 Role role = new Role();
		 role.setId(new Short("1"));
		 
		
		@SuppressWarnings("unchecked")
		List<Object[]> all = entityManager.createNativeQuery("SELECT u.id as uid,u.username,uf.firstname,uf.lastname,uf.email,uf.bio,uf.image_name FROM user u INNER JOIN userinfo uf ON u.id = uf.user_id INNER JOIN  user_roles r ON u.id = r.users_id "
				              + " WHERE u.status ="+Status.ACTIVE.getValue()
				              +" AND r.roles_id ="+role.getId() 
				              +" AND (uf.firstname like '%"+name+"%' or uf.lastname like '%"+name+"%' or uf.email like '%"+name+"%' or u.username like '%"+name+"%')").getResultList();
		
		
		User user = new User();
		for(Object[] obj : all){
			
			BigInteger userid=obj[0] != null ?  (BigInteger) obj[0] : new BigInteger("0");
			user.setId(userid.longValue());
			int counter = siteReviewService.getExpertCoreRatings(user);
			int totalCounter = siteReviewService.getExpertTotalRatings(user);
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
	public boolean saveExpertBio(ExpertBioForm expertBioForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AddUserForm> getSortingAllExpert(String name, String sortColumn, String sortOrder, int start,
			int pageSize, Long editorId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalExpertsForEditorByEditorId(String name, long editorId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String removeExpertPic(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
