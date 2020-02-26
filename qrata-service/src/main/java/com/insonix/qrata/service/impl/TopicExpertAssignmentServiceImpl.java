/**
 * 
 */
package com.insonix.qrata.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.TopicDao;
import com.insonix.qrata.dao.TopicExpertAssignmentDao;
import com.insonix.qrata.entity.TopicForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicExpertAssignment;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.RoleService;
import com.insonix.qrata.service.TopicExpertAssignmentService;
import com.insonix.qrata.service.TopicRatingCriteriaService;
import com.insonix.qrata.service.UserService;

/**
 * @author Harmeet Singh
 *
 */
@Service("TopicExpertAssignmentService")
public class TopicExpertAssignmentServiceImpl implements TopicExpertAssignmentService{
	@Autowired
	TopicExpertAssignmentDao topicExpertAssignmentDao;
	@Autowired
	TopicDao topicDao;
	@Autowired
	TopicRatingCriteriaService topicRatingCriteriaService;
	@Autowired
	UserService userService;
	@Autowired
	RoleService rolesService;
	
	@Override
	public boolean assignedTopicToExpert(Topic topic, User user, User expert) {
		TopicExpertAssignment topicExpertAssignment = new TopicExpertAssignment();
		topicExpertAssignment.setCreatedBy(user.getId());
		topicExpertAssignment.setAssignedBy(user);
		topicExpertAssignment.setExpert(expert);
		topicExpertAssignment.setTopic(topic);
		topicExpertAssignment.setStatus(Status.ACTIVE.getValue());
		
		try{
			//topicExpertAssignmentDao.save(topicExpertAssignment);
			if(topic.getTopicExpertAssignment() != null){
				unAssignedTopicToExpert(topic);
			}
			topic.setTopicExpertAssignment(topicExpertAssignment);
			topicDao.update(topic);
			return true;
		}catch(DataAccessException ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean unAssignedTopicToExpert(Topic topic) {
		try{
			TopicExpertAssignment topicExpertAssignment = topic.getTopicExpertAssignment();
			topic.setTopicExpertAssignment(null);
			topicDao.update(topic);
			topicExpertAssignmentDao.delete(topicExpertAssignment);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	@Override
	public List<TopicForm> getTopicExpertAssignment_AssignedBy(String name,User user,short topicType,
			int start, int pagesize,String sortColumn, String sortOrder) {
		if(name != null){
			name = name.trim() + "%";
		}else{
			name = "%";
		}
		
		List<TopicExpertAssignment> topicExpertAssignments = topicExpertAssignmentDao.getTopicExpertAssignment_AssignedBy(name,user,topicType,start, pagesize,sortColumn,sortOrder);
		List<TopicForm> topicForms = new ArrayList<>();
		
		for(TopicExpertAssignment topicExpertAssignment : topicExpertAssignments){
			TopicForm form = new TopicForm();
			form.setTopicId(topicExpertAssignment.getTopic().getId());
			form.setName(topicExpertAssignment.getTopic().getName());
			form.setSubCatId(topicExpertAssignment.getTopic().getCategory().getId());
			form.setExpertId(topicExpertAssignment.getExpert().getId());
			form.setExpertFirstName(topicExpertAssignment.getExpert().getUserinfo().getFirstname());
			form.setExpertLastName(topicExpertAssignment.getExpert().getUserinfo().getLastname());
			boolean result = topicRatingCriteriaService.chekTopicRatings(topicExpertAssignment.getTopic());
			form.setRatingStatus(result);
			
			if(topicExpertAssignment.getTopic().getParentTopic() != null){
				form.setParentTopicId(topicExpertAssignment.getTopic().getParentTopic().getId());
			}
			
			topicForms.add(form);
		}
		return topicForms;
	}

	@Override
	public int getTotalTopics_ExpertId(String name, long id,short topicType) {
		if(name != null){
			name = name.trim() + "%";
		}else{
			name = "%";
		}
		return topicExpertAssignmentDao.getTotalTopics_ExpertId(name,Status.ACTIVE,id,topicType);
	}

	@Override
	public List<TopicForm> getActiveTopics_ExpertId(String name,int start, int pagesize, long id,short topicType,
			String sortOrder,String sortColumn) {
		if(name != null){
			name = name.trim() + "%";
		}else{
			name = "%";
		}
		
		List<TopicExpertAssignment> topicExpertAssignments = topicExpertAssignmentDao.getActiveTopics_ExpertId(name,Status.ACTIVE,start,
				pagesize,id,topicType,sortOrder,sortColumn);
		
		List<TopicForm> topicList = new ArrayList<>();
		Iterator<TopicExpertAssignment> iterator = topicExpertAssignments.iterator();
		while(iterator.hasNext()){
			TopicExpertAssignment topicExpertAssignment = iterator.next();
			TopicForm form = new TopicForm();
			form.setTopicId(topicExpertAssignment.getTopic().getId());
			form.setName(topicExpertAssignment.getTopic().getName());
			form.setUuid(topicExpertAssignment.getTopic().getUuid());
			form.setCategoryId(topicExpertAssignment.getTopic().getCategory().getParentCategory().getId());
			form.setSubCatId(topicExpertAssignment.getTopic().getCategory().getId());
			
			if(topicExpertAssignment.getTopic().getParentTopic() != null){
				form.setParentTopicId(topicExpertAssignment.getTopic().getParentTopic().getId());
				
			}
			
			String editorName = topicExpertAssignment.getAssignedBy().getUserinfo().getFirstname();
			editorName = editorName +" "+topicExpertAssignment.getAssignedBy().getUserinfo().getLastname();
			
			form.setEditorName(editorName);
			int ratingCriteriasCount = topicRatingCriteriaService.getTotalTopicRatingCriteria_TopicId(topicExpertAssignment.getTopic().getId());
			if(ratingCriteriasCount != 0){
				form.setRatingStatus(true);
			}else{
				form.setRatingStatus(false);
			}
			topicList.add(form);
		}
		
		return topicList;
	}

	@Override
	public List<User> getExpert_AssignedByUser(long assignedById) {
		return topicExpertAssignmentDao.getExpert_AssignedByUser(assignedById, Status.ACTIVE);
	}
	
	@Override
	public List<Long> getExpertIds_AssignedByUser(long assignedById) {
		return topicExpertAssignmentDao.getExpertIds_AssignedByUser(assignedById,Status.ACTIVE);
	}

	@Override
	public int getTotalTopicExpertAssignment_AssignedBy(String name,User user, short topicType) {
		if(name != null){
			name = name.trim() + "%";
		}else{
			name = "%";
		}
		return topicExpertAssignmentDao.getTotalTopicExpertAssignment_AssignedBy(name,user,topicType);
	}

	@Override
	public List<Topic> getTopics_ExpertId(User user) {
		return topicExpertAssignmentDao.getTopics_ExpertId(Status.ACTIVE, user);
	}
	
	@Override
	public List<Topic> getTopics_AssignedId(User user) {
		return topicExpertAssignmentDao.getTopics_AssignedId(Status.ACTIVE,user);
	}
	
	@Override
	public List<Integer> getTopicIds() {
		return topicExpertAssignmentDao.getTopicIds(Status.ACTIVE);
	}

	@Override
	public boolean getTopicExists(Topic topic) {
		List<Integer> topicIds = topicExpertAssignmentDao.getTopics_TopicId(Status.ACTIVE,topic);
		if(topicIds.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public long getExpertId_TopicId(int topicId) {
		return topicExpertAssignmentDao.getExpertId_TopicId(Status.ACTIVE,topicId);
	}

	@Override
	public List<Category> getAssignedCategoriesByDomainId_ExpertId(int domainId, long userId) {
		return topicExpertAssignmentDao.getAssignedCategoriesByDomainId_ExpertId(domainId,userId);
	}

	@Override
	public List<Category> getAssignedSubCategoriesByCategoryId_ExpertId(int categoryId, long userId) {
		return topicExpertAssignmentDao.getAssignedSubCategoriesByCategoryId_ExpertId(categoryId,userId);
	}

	@Override
	public Set<Topic> getAssignedTopicsBySubCategoryId_ExpertId(int subcategoryId, long userId) {
		List<Topic> topics = null;
		Set<Topic> topicsSet = new HashSet<>();
		topics =  topicExpertAssignmentDao.getAssignedTopicsBySubCategoryId_ExpertId(subcategoryId, userId);
		if(! topics.isEmpty()){
			for(Topic topic : topics){
				if(topic.getParentTopic() == null){
					topicsSet.add(topic);
				}else{
					Topic tempTopic = topic.getParentTopic();
					topicsSet.add(tempTopic);
				}
			}
		}return topicsSet;
	}

	@Override
	public List<Topic> getAssignedSubTopicByTopicId_expertId(int topicId, long userId) {
		return topicExpertAssignmentDao.getAssignedSubTopicByTopicId_expertId(topicId,userId);
	}
	
	@Override
	public String suggestTOrSTByNameAndTypeForExpertPublishing(String name, String type, long userId, Roles role) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			Topics tType = null;
			if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("TOPIC")) {
				tType = Topics.TOPIC;
			} else if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("SUBTOPIC")) {
				tType = Topics.SUBTOPIC;
			}
			
			List<String> namesList = topicExpertAssignmentDao.getTopicNamesForAutoSuggestForExpertPublishing_Name(name, 
					tType, Status.ACTIVE, userId, role);
			for(String cName: namesList) {
				obj = new JSONObject();
				obj.put("name", cName);
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

	
	@Override
	public String getAvailableExperts(String term) {
		JSONObject responseObj = new JSONObject();
		try {
			JSONObject userObj = null;
			List<JSONObject> usersObjList = new ArrayList<>();
			Role role = rolesService.getRoles_Id(Constants.Roles.EXPERT.getValue());
			List<User> usersList = userService.getUsers_TopicId_Name(role, Status.ACTIVE, term);
			for(User user : usersList) {
				userObj = new JSONObject();
				String fullName = user.getUserinfo().getLastname() 
						+ " " + user.getUserinfo().getFirstname();
				userObj.put("id", user.getId());
				userObj.put("fullName", fullName);
				usersObjList.add(userObj);
			}
			
			responseObj.put("obj", usersObjList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}
	
}
