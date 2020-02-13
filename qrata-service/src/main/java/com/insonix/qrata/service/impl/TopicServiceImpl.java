package com.insonix.qrata.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
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

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.CategoryDao;
import com.insonix.qrata.dao.TopicDao;
import com.insonix.qrata.entity.TopicForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicRatingCriteria;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.SitesService;
import com.insonix.qrata.service.TopicRatingCriteriaService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 *
 */
@Service("TopicsService")
public class TopicServiceImpl implements TopicService {
	@Autowired
	TopicDao topicsDao;	
	@Autowired
	CategoryDao categoryDao;	
	@Autowired
	UserService userService;
	@Autowired
	ExpertService expertService;
	@Autowired
	SitesService sitesService;
	@Autowired SiteReviewsService siteReviewsService;
	CustomSortComparator<Topic> customSort = new CustomSortComparator<Topic>();
	@Autowired
	TopicRatingCriteriaService topicRatingCriteriaService;

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#getTopics_Id(int)
	 */
	@Override
	public Topic getTopics_Id(int id) {		
		return topicsDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#delete(com.insonix.qrata.models.Topics)
	 */
	@Override
	public boolean delete(Topic topics) {
		try{
			topicsDao.delete(topics);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#save(com.insonix.qrata.models.Topics)
	 */
	@Override
	public String save(Topic topics) {
		String id = topicsDao.save(topics);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#update(com.insonix.qrata.models.Topics)
	 */
	@Override
	public boolean update(Topic topics) {
		try{
			topicsDao.update(topics);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#updateBulk(java.util.List)
	 */
	@Override
	public boolean updateBulk(List<Topic> topicsList) {
		try{
			topicsDao.updateBulk(topicsList);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#getTopics_SubCategoryId(java.lang.String)
	 */
	@Override
	public List<Topic> getTopics_SubCategoryId(String subCategoryId) {
		int id = Integer.parseInt(subCategoryId);
		List<Topic> topicList = topicsDao.getTopics_SubCategoryId(id);
		Collections.sort(topicList, customSort);
		return topicList;
	}

	@Override
	public boolean saveTopic(String id, String topicName , long createdBy) {
		
		int subCategoryId = Integer.parseInt(id);
		Topic topic = new Topic();
		Category parentCategory = categoryDao.get(subCategoryId);
		topic.setTopicType(Constants.Topics.TOPIC.getValue());
		topic.setStatus(Status.ACTIVE.getValue());
		topic.setName(topicName);
		topic.setCategory(parentCategory);
		topic.setCreatedBy(createdBy);
		try{
			topicsDao.save(topic);
			return true;
		}catch(DataAccessException ex){
			System.out.println("Stack Trace : ");
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean saveSubTopic(String id, String subTopicName,long createdBy) {
		
		int topicId = Integer.parseInt(id);
		Topic topic = topicsDao.get(topicId);
		Topic subTopic = new Topic();
		subTopic.setName(subTopicName);
		subTopic.setTopicType(Constants.Topics.SUBTOPIC.getValue());
		subTopic.setCategory(topic.getCategory());
		subTopic.setStatus(Status.ACTIVE.getValue());
		subTopic.setParentTopic(topic);
		subTopic.setLeafNode(true);
		subTopic.setCreatedBy(createdBy);
		try{
			topicsDao.save(subTopic);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#getSubTopics(java.lang.String)
	 */
	@Override
	public List<Topic> getSubTopics(String uuid) {
		List<Topic> topicList = topicsDao.getSubTopics(uuid);
		Collections.sort(topicList, customSort);
		return topicList;
	}
	
	@Override
	public List<TopicForm> getActiveTopics(String name,int start,int pageSize,String sortField, String sortOrder) {
		List<Topic> topicList = topicsDao.getTopicsOrSubtopics_Type(name, Status.ACTIVE, 
				Topics.TOPIC, start, pageSize,sortField,sortOrder);
		List<TopicForm> topicFormList = new ArrayList<>();
		for(Topic topic : topicList){
			TopicForm form=new TopicForm();
			form.setTopicId(topic.getId());
			form.setName(topic.getName());
			form.setCategoryId(topic.getCategory().getId());
			int totalRatings = siteReviewsService.getTotalRatings_TopicId(topic.getId(), null);
			form.setCount(totalRatings);
			topicFormList.add(form);
		}
		//Collections.sort(topicList, customSort);
		return topicFormList;
	}

	@Override
	public List<TopicForm> getActiveSubTopics(String name, int start, int pageSize, String sortField, String sortOrder) {
		List<Topic> subTopicList = topicsDao.getTopicsOrSubtopics_Type(name, Status.ACTIVE, 
				Topics.SUBTOPIC, start, pageSize,sortField,sortOrder);
		List<TopicForm> SubTopicFormList = new ArrayList<>();
		for(Topic topic : subTopicList){
			TopicForm form = new TopicForm();
			form.setParentTopicId(topic.getParentTopic().getId());
			form.setName(topic.getName());
			form.setTopicId(topic.getId());
			int totalRatings = siteReviewsService.getTotalRatings_TopicId(topic.getId(), null);
			form.setCount(totalRatings);
			SubTopicFormList.add(form);
			
		}
		//Collections.sort(subTopicList, customSort);
		return SubTopicFormList;
	}
	
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsService#getSubTopics(int)
	 */
	@Override
	public List<Topic> getSubTopics(int topicId) {
		List<Topic> topicList = topicsDao.getSubTopics_TopicId(topicId);
		Collections.sort(topicList, customSort);
		return topicList;
	}

	@Override
	public Topic getTopic(String uuid) {
		return topicsDao.getTopic(uuid);
	}

	@Override
	public boolean updateTopic(TopicForm topicForm) {
		Topic topic=topicsDao.getTopic(topicForm.getUuid());
		topic.setName(topicForm.getName());
		try{
			topicsDao.update(topic);
			return true;
		}catch(DataAccessException ex){
			return false;
		}		
	}
	
	@Override
	public List<Topic> getTopics_Name(String name) {
		List<Topic> topics = topicsDao.getTopics_Name(name);
		Collections.sort(topics, customSort);
		return topics;
	}
	
	private boolean saveSite(String topicId, String siteId) {
		Topic topic = getTopics_Id(Integer.parseInt(topicId));
		Site site = sitesService.getSites_Id(Long.parseLong(siteId));
		List<Site> sites = topic.getSites();
		if(sites != null) {
			if(!sites.contains(site))
				sites.add(site);
		} else {
			sites = new ArrayList<>();
			sites.add(site);
		}
		topic.setSites(sites);
		try{
			topicsDao.update(topic);
			return true;
		}catch(DataAccessException ex){
			return false;
		}		
	}		
	private boolean removeSite(String topicId, String siteId) {
		Topic topic = getTopics_Id(Integer.parseInt(topicId));
		Site site = sitesService.getSites_Id(Long.parseLong(siteId));
		List<Site> sites = topic.getSites();
		if(sites != null) {
			sites.remove(site);
		}
		topic.setSites(sites);
		try{
			topicsDao.update(topic);	
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}
	
	@Override
	public String assignSites(String jsonObject) {
		String topicId = "";
		try {
			JSONObject jobj = new JSONObject(jsonObject.toString());
			JSONObject obj = new JSONObject(jobj.get("obj").toString());
			topicId = obj.get("topicId").toString();
			JSONArray sitesJSONArray = (JSONArray) obj.get("sitesArray");
			for(int i=0; i<sitesJSONArray.length(); i++) {
				String sitetId = sitesJSONArray.get(i).toString();
				saveSite(topicId, sitetId);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Topic topic = getTopics_Id(Integer.parseInt(topicId));
		List<Site> availableSites = sitesService.getActiveSites_CategoryId(topic.getCategory().getParentCategory().getId());
		List<Site> assignedSites = topic.getSites();
		
		for(Iterator<Site> itr=availableSites.iterator();itr.hasNext();) {
			Site site = itr.next();
			if(assignedSites != null && assignedSites.contains(site)) {
				itr.remove();
			}
		}
		
		String str = "";
		str += "<select name='availableSites' id='availableSites' multiple='multiple' style='height: 200px;'>";
		for(Site site : availableSites) {
			str += "<option value='"+site.getId()+"'>"+site.getName()+" ("+site.getUrl()+")</option>";
		}
		str += "</select>";
		
		String str2 = "";
		str2 += "<select name='assignedSites' id='assignedSites' multiple='multiple' style='height: 200px;'>";
		if(assignedSites != null) {
			for(Site site : assignedSites) {
				str2 += "<option value='"+site.getId()+"'>"+site.getName()+" ("+site.getUrl()+")</option>";
			}
		}
		str2 += "</select>";
				
		JSONObject responseObj = new JSONObject();
		try {
			responseObj.put("availableSites", str);
			responseObj.put("assignedSites", str2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return responseObj.toString();
	}
	
	@Override
	public String unAssignSites(String jsonObject) {
		String topicId = "";
		try {
			JSONObject jobj = new JSONObject(jsonObject.toString());
			JSONObject obj = new JSONObject(jobj.get("obj").toString());
			topicId = obj.get("topicId").toString();
			JSONArray sitesJSONArray = (JSONArray) obj.get("sitesArray");
			for(int i=0; i<sitesJSONArray.length(); i++) {
				String siteId = sitesJSONArray.get(i).toString();
				removeSite(topicId, siteId);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Topic topic = getTopics_Id(Integer.parseInt(topicId));
		List<Site> assignedSites = topic.getSites();
		
		String str = "";
		str += "<select name='assignedSites' id='assignedSites' multiple='multiple' style='height: 200px;'>";
		if(assignedSites != null) {
			for(Site site : assignedSites) {
				str += "<option value='"+site.getId()+"'>"+site.getName()+" ("+site.getUrl()+")</option>";
			}
		}
		str += "</select>";
		
		
		List<Site> availableSites = sitesService.getActiveSites_CategoryId(topic.getCategory().getParentCategory().getId());
		for(Iterator<Site> itr=availableSites.iterator();itr.hasNext();) {
			Site site = itr.next();
			if(assignedSites != null && assignedSites.contains(site)) {
				itr.remove();
			}
		}
		
		String str2 = "";
		str2 += "<select name='availableSites' id='availableSites' multiple='multiple' style='height: 200px;'>";
		for(Site site : availableSites) {
			str2 += "<option value='"+site.getId()+"'>"+site.getName()+" ("+site.getUrl()+")</option>";
		}
		str2 += "</select>";
		
		JSONObject responseObj = new JSONObject();
		try {
			responseObj.put("availableSites", str2);
			responseObj.put("assignedSites", str);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return responseObj.toString();
	}
	
	@Override
	public int getSumOfRatingCriteriasWeight_Topic(Topic topic) {
		int totalWeight = 0;
		try {
			Set<TopicRatingCriteria> topicRatingCriterias = topic.getTopicRatingCriterias();
			Iterator<TopicRatingCriteria> topicRatingCriteriaItr = topicRatingCriterias.iterator();
			for(;topicRatingCriteriaItr.hasNext();) {
				TopicRatingCriteria topicRatingCriteria = topicRatingCriteriaItr.next();
				int topicCriteriaWeight = topicRatingCriteria.getWeight().intValue();
				totalWeight = totalWeight + topicCriteriaWeight;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalWeight;
	}

	@Override
	public Topic getTopic_Name_Id(String name, int id) {
		Topic topic = topicsDao.getTopic_Name_Id(name,id);
		return topic;
	}

	@Override
	public List<Topic> getTopics_Name_CategoryId(String name, int id) {
		List<Topic> topics = topicsDao.getTopics_Name_CategoryId(name,id);
		return topics;
	}
	
	@Override
	public int getTotalTopics(String name) {
		return topicsDao.getCount_Type(name, Status.ACTIVE, Topics.TOPIC);
	}
	
	@Override
	public int getTotalSubTopics(String name) {
		return topicsDao.getCount_Type(name, Status.ACTIVE, Topics.SUBTOPIC);
	}
	
	@Override
	public List<Topic> getActiveTopics_UserId(String name, int start,
			int pageSize, long userId) {
		List<Topic> topicList = topicsDao.getTopicsOrSubtopics_Type_UserId(name, Status.ACTIVE, Topics.TOPIC, start, pageSize, userId);
		Collections.sort(topicList, customSort);
		return topicList;
	}
	
	@Override
	public List<Topic> getActiveTopics_SubTopics_UserId(long userId) {
		List<Topic> topicList = topicsDao.getTopics_Subtopics_Type_UserId(Status.ACTIVE, userId);
		Collections.sort(topicList, customSort);
		return topicList;
	}
	
	@Override
	public List<Topic> getActiveSubTopics_UserId(String name, int start,
			int pageSize, long userId) {
		List<Topic> subTopicList = topicsDao.getTopicsOrSubtopics_Type_UserId(name, Status.ACTIVE, 
				Topics.SUBTOPIC, start, pageSize, userId);
		Collections.sort(subTopicList, customSort);
		return subTopicList;
	}

	@Override
	public int getTotalTopics_UserId(String name, long userId) {
		return topicsDao.getCount_Type_UserId(name, Status.ACTIVE, Topics.TOPIC, userId);
	}
	
	@Override
	public int getTotalSubTopics_UserId(String name, long userId) {
		return topicsDao.getCount_Type_UserId(name, Status.ACTIVE, Topics.SUBTOPIC, userId);
	}

	@Override
	public List<Site> getSites_Topic(Topic topic) {
		return topicsDao.getSites_Topic(topic);
	}

	@Override
	public int getTotalTopics_EditorId(String name, long id) {
		name = Utility.searchValueCheck(name);
		return topicsDao.getTotalTopics_EditorId(name, Status.ACTIVE, Topics.TOPIC, id);
	}

	@Override
	public List<TopicForm> getActiveTopics_EditorId(String name, int start,int pagesize, long id,
			String sortOrder,String sortColumn) {
		name = Utility.searchValueCheck(name);
		List<Topic> topics =  topicsDao.getActiveTopics_EditorId(name, Status.ACTIVE, Topics.TOPIC, start, pagesize, id, 
				sortOrder, sortColumn);
		
		List<TopicForm> topicList = new ArrayList<>();
		Iterator<Topic> iterator = topics.iterator();
		while(iterator.hasNext()){
			Topic topic = iterator.next();
			TopicForm form = new TopicForm();
			form.setTopicId(topic.getId());
			form.setName(topic.getName());
			form.setUuid(topic.getUuid());
			form.setSubCatId(topic.getCategory().getId());
			int ratingCriteriasCount = topicRatingCriteriaService.getTotalTopicRatingCriteria_TopicId(topic.getId());
			if(ratingCriteriasCount != 0){
				form.setRatingStatus(true);
			}else{
				form.setRatingStatus(false);
			}if(topic.getSites().isEmpty()){
				form.setSites(false);
			}else{
				form.setSites(true);
			}
			topicList.add(form);
		}
		
		return topicList;
	}

	@Override
	public int getTotalSubTopics_Editor(String name, long id) {
		name = Utility.searchValueCheck(name);
		return topicsDao.getTotalTopics_EditorId(name, Status.ACTIVE, Topics.SUBTOPIC, id);
	}

	@Override
	public List<TopicForm> getActiveSubTopics_Editor(String name, int start, int pagesize, long id,
			String sortOrder,String sortColumn) {
		name = Utility.searchValueCheck(name);
		List<Topic> subTopics =  topicsDao.getActiveTopics_EditorId(name,Status.ACTIVE,Topics.SUBTOPIC,start,pagesize,id,sortOrder,sortColumn);
		
		List<TopicForm> subTopicList = new ArrayList<>();
		Iterator<Topic> iterator = subTopics.iterator();
		while(iterator.hasNext()){
			Topic topic = iterator.next();
			TopicForm form = new TopicForm();
			form.setTopicId(topic.getId());
			form.setName(topic.getName());
			form.setUuid(topic.getUuid());
			form.setSubCatId(topic.getCategory().getId());
			form.setParentTopicId(topic.getParentTopic().getId());
			int ratingCriteriasCount = topicRatingCriteriaService.getTotalTopicRatingCriteria_TopicId(topic.getId());
			
			if(ratingCriteriasCount != 0){
				form.setRatingStatus(true);
			}else{
				form.setRatingStatus(false);
			}
			
			if(topic.getSites().isEmpty()){
				form.setSites(false);
			}else{
				form.setSites(true);
			}
			form.setChildStatus(false);
			subTopicList.add(form);
		}
		
		return subTopicList;
	}

	@Override
	public List<Topic> getTopics_SubCategoryId_AssignedTopics(String id) {
		int subCategoryId = Integer.parseInt(id);
		return topicsDao.getTopics_SubCategoryId_AssignedTopics(subCategoryId);
	}

	@Override
	public List<Topic> getSubTopics_TopicId_AssignedTopics(int topicId) {
		return topicsDao.getSubTopics_TopicId_AssignedTopics(topicId);
	}

	@Override
	public BigInteger getSitesCount_TopicId(int topicId) {
		return topicsDao.getSitesCount_TopicId(topicId);
	}

	@Override
	public List<Topic> searchTopic_Name_SubCategoryId(String name, String subCategoryId, Topics topic) {
		int subCatId = Integer.parseInt(subCategoryId);
		List<Topic> topics = topicsDao.searchTopic_Name_SubCategoryId(name,subCatId,Status.ACTIVE,topic);
		return topics;
	}

	@Override
	public List<Topic> searchSubTopic_Name_ParentTopicId(String name , int topicId,Topics subtopic) {
		List<Topic> topics = topicsDao.searchSubTopic_Name_ParentTopicId(name,topicId,subtopic,Status.ACTIVE);
		return topics;
	}

	@Override
	public int getTotalActiveTopic_Type(String name, Topics topic,Integer subCategoryId, Integer parentTopicId) {
		name = Utility.searchValueCheck(name);
		return topicsDao.getTotalActiveTopic_Type(Status.ACTIVE,name,topic,subCategoryId,parentTopicId);
	}

	@Override
	public List<Topic> getActiveTopic_Type(String name, Topics topic,Integer subCategoryId, Integer parentTopicId, int pagesize,
			String sortField, String sortOrder, int start) {
		name = Utility.searchValueCheck(name);
		return topicsDao.getActiveTopic_Type(Status.ACTIVE,name,topic,subCategoryId,parentTopicId,pagesize,sortField,sortOrder,start);
	}		
	
	
	@Override
	public String suggestTopicOrSubTopicByNameAndType(String name, Integer parentId, String type) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			Topics tType = null;
			if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("TOPIC")) {
				tType = Topics.TOPIC;
			} else if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("SUBTOPIC")) {
				tType = Topics.SUBTOPIC;
			}
			
			List<String> namesList = topicsDao.getTopicNamesForAutoSuggest_Name(name, parentId, Status.ACTIVE, tType);
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
	public String suggestTOrSTByNameAndTypeForMyPublishing(String name, String type, long userId) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			Topics tType = null;
			if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("TOPIC")) {
				tType = Topics.TOPIC;
			} else if(!StringUtils.isEmpty(type) && type.equalsIgnoreCase("SUBTOPIC")) {
				tType = Topics.SUBTOPIC;
			}
			name = Utility.searchValueCheck(name);
			List<String> namesList = topicsDao.getTopicNamesForAutoSuggestForMyPublishing_Name(name, tType, 
					Status.ACTIVE, userId);
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
	public List<TopicForm> getAllActiveTopics_ChildCount_SiteCount_RatingCount( Topics topic, String name, int start, int pagesize, String sortField, String sortOrder) {
		List<Object[]> objects = null;
		name = Utility.searchValueCheck(name);
		objects = topicsDao.getAllActiveTopics_Type_ChildCount_SiteCount_RatingCount(topic, name, start, pagesize, sortField, sortOrder);
		return assignTopicObjectListValuesToTopicFormList(objects);
	}
	
	@Override
	public List<TopicForm> getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount( String name, Integer subCategoryId, int pagesize, String sortField,
			String sortOrder, int start) {
		List<Object[]> objects = null;
		name = Utility.searchValueCheck(name);
		
		objects = topicsDao. getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount(Constants.Topics.TOPIC, subCategoryId, name, start, pagesize, 
				sortField, sortOrder);
		
		return assignTopicObjectListValuesToTopicFormList(objects);
	}

	private List<TopicForm> assignTopicObjectListValuesToTopicFormList(List<Object[]> objects){
		List<TopicForm> topicsForm = new ArrayList<>();
		
		for(Object[] obj : objects){
			int  topicId 					= obj[0] != null ? (int) obj[0] : 0;
			String topicName 				= obj[1] != null ? obj[1].toString() : "";
			String topicUuid 				= obj[2] != null ? obj[2].toString() : "";
			int categoryId 					= obj[3] != null ? (int) obj[3] : 0;
			BigInteger expertId 			= obj[4] != null ? (BigInteger) obj[4] : new BigInteger("0");
			BigInteger childTopicCount 		= obj[5] != null ?(BigInteger) obj[5]: new BigInteger("0");
			BigInteger topicRatingsCount 	= obj[6] != null ? (BigInteger) obj[6] : new BigInteger("0");
			BigInteger topicSiteCount 		= obj[7] != null ? (BigInteger) obj[7] : new BigInteger("0");
			boolean leafNode				= obj[8] != null ? (boolean) obj[8] : false;
			
			TopicForm form = new TopicForm();
			form.setTopicId(topicId);
			form.setName(topicName);
			form.setUuid(topicUuid);
			form.setCategoryId(categoryId);
			
			if(childTopicCount.longValue() == 0){
				form.setChildStatus(false);
			}else{
				form.setChildStatus(true);
			}
			
			if(expertId.longValue() != 0){
				form.setExpertId(expertId.longValue());
				form.setAssignStatus(true);
			}else{
				form.setAssignStatus(false);
			}
			
			if(topicSiteCount.longValue() == 0 && topicRatingsCount.longValue() == 0){
				form.setApplicableAssignment(true);
			}else{
				form.setApplicableAssignment(false);
			}
			
			form.setLeafNode(leafNode);
			topicsForm.add(form);
		}return topicsForm;
	}
	
	@Override
	public List<TopicForm> getAllActiveSubTopics_ChildCount_SiteCount_RatingCount( String name, int start, int pagesize, String sortField, String sortOrder) {
		List<Object[]> objects = null;
		name = Utility.searchValueCheck(name);
		
		objects = topicsDao.getAllActiveSubTopics_ChildCount_SiteCount_RatingCount(Constants.Topics.SUBTOPIC, name,start, pagesize,sortField,sortOrder);
		return assignSubTopicObjectListValuesToTopicFormList(objects);
	}
	
	@Override
	public List<TopicForm> getActiveSubTopicsByCategoryId_ChildCount_SiteCount_RatingCount( String name, int parentTopicId, int pagesize, String sortField,
			String sortOrder, int start) {
		List<Object[]> objects = null;
		name = Utility.searchValueCheck(name);
		
		objects = topicsDao.getActiveSubTopicsByCategoryId_ChildCount_SiteCount_RatingCount(Constants.Topics.SUBTOPIC, name,
				parentTopicId, start, pagesize,sortField,sortOrder);
		return assignSubTopicObjectListValuesToTopicFormList(objects);
	}
	
	private List<TopicForm> assignSubTopicObjectListValuesToTopicFormList(List<Object[]> objects){
		List<TopicForm> subTopicsForm = new ArrayList<>();
		for(Object[] obj : objects){
			int  topicId 							= obj[0] != null ? (int) obj[0] : 0;
			String topicName 					= obj[1] != null ? obj[1].toString() : "";
			String topicUuid 					= obj[2] != null ? obj[2].toString() : "";
			int categoryId 						= obj[3] != null ? (int) obj[3] : 0;
			int parentTopicId					= obj[4] != null ? (int) obj[4] : 0;
			BigInteger expertId 				= obj[5] != null ? (BigInteger) obj[5] : new BigInteger("0");
			BigInteger topicRatingsCount 	= obj[6] != null ? (BigInteger) obj[6] : new BigInteger("0");
			BigInteger topicSiteCount 		= obj[7] != null ? (BigInteger) obj[7] : new BigInteger("0");
			
			TopicForm form = new TopicForm();
			form.setTopicId(topicId);
			form.setName(topicName);
			form.setUuid(topicUuid);
			form.setCategoryId(categoryId);
			form.setParentTopicId(parentTopicId);
			form.setChildStatus(false);
			
			if(expertId.longValue() != 0){
				form.setExpertId(expertId.longValue());
				form.setAssignStatus(true);
			}else{
				form.setAssignStatus(false);
			}
			
			if(topicSiteCount.longValue() == 0 && topicRatingsCount.longValue() == 0){
				form.setApplicableAssignment(true);
			}else{
				form.setApplicableAssignment(false);
			}
			
			subTopicsForm.add(form);
		}
		return subTopicsForm;
	}

	@Override
	public List<Topic> getTopics_SubCategoryIdOrParentTopicId_Type(int subCatId, int parentTopicId, Topics topicType) {
		return topicsDao.getTopics_SubCategoryIdOrParentTopicId_Type(Status.ACTIVE, topicType, subCatId, parentTopicId);
	}
	
	
}
