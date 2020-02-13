package com.insonix.qrata.service;

import java.math.BigInteger;
import java.util.List;

import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.entity.TopicForm;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;


/**
 * @author kamal
 *
 */
public interface TopicService {
	/**
	 * @param id
	 * @return
	 */
	public Topic getTopics_Id(int id);
	/**
	 * @param topics
	 */
	public boolean delete(Topic topics);
	/**
	 * @param topics
	 * @return
	 */
	public String save(Topic topics);
	/**
	 * @param topics
	 */
	public boolean update(Topic topics);
	/**
	 * @param topicsList
	 */
	public boolean updateBulk(List<Topic> topicsList);
	
	/**
	 * @param subCategoryId
	 * @return
	 */
	@Deprecated
	public List<Topic> getTopics_SubCategoryId(String subCategoryId);
	
	/**
	 * @param id
	 * @param topicName
	 * @param createdBy 
	 */
	public boolean saveTopic(String id , String topicName, long createdBy);
	
	/**
	 * @param id
	 * @param subTopicName
	 * @param createdBy 
	 */
	public boolean saveSubTopic(String id , String subTopicName, long createdBy);
	
	/**
	 * This method will fetch the subtopics by the topic UUID passed as argument
	 * @author Shabina
	 * @param uuid
	 * @return
	 */
	public List<Topic> getSubTopics(String uuid);

	/**
	 * @param sortOrder 
	 * @param sortField 
	 * @return
	 */
	public List<TopicForm> getActiveTopics(String name, int start,int pageSize,String sortField, String sortOrder);
	
	public List<TopicForm> getActiveSubTopics(String name, int start, int pageSize,String sortField, String sortOrder);
	
	public List<Topic> getActiveTopics_UserId(String name, int start, int pageSize, long userId);
	
	public List<Topic> getActiveSubTopics_UserId(String name, int start, int pageSize, long userId);
		
	/**
	 * Method to get Subtopics of the Topic
	 * 
	 * @param id
	 * @return
	 */
	public List<Topic> getSubTopics(int topicId);
	
	public Topic getTopic(String uuid);
	
	public boolean updateTopic(TopicForm topic);
	/*
	
	public String assignExpert(String jsonObject);
	
	public String unAssignExpert(String jsonObject);
	*/
	public String assignSites(String jsonObject);
	
	public String unAssignSites(String jsonObject);
	
	public List<Topic> getTopics_Name(String name);
	
	public int getSumOfRatingCriteriasWeight_Topic(Topic topic);
	 
	public Topic getTopic_Name_Id(String name, int id); 
	public List<Topic> getTopics_Name_CategoryId(String name, int id); 
	
	public int getTotalTopics(String name);
	public int getTotalSubTopics(String name);
	public int getTotalTopics_UserId(String name, long userId);
	public int getTotalSubTopics_UserId(String name, long userId);
	
	public List<Topic> getActiveTopics_SubTopics_UserId(long userId);
	
	public List<Site> getSites_Topic(Topic topic);
	
	public int getTotalTopics_EditorId(String replaceSpecialCharacters, long id);
	
	public List<TopicForm> getActiveTopics_EditorId(String name, int start, int pagesize, long id, String sortOrder, String sortColumn);
	
	public int getTotalSubTopics_Editor(String name, long id);
	
	public List<TopicForm> getActiveSubTopics_Editor(String name, int start, int pagesize, long id, String sortOrder, String sortColumn);
	
	public List<Topic> getTopics_SubCategoryId_AssignedTopics(String id);
	
	public List<Topic> getSubTopics_TopicId_AssignedTopics(int topicId);
	
	public BigInteger getSitesCount_TopicId(int topicId);
	
	public List<Topic> searchTopic_Name_SubCategoryId(String name , String subCategoryId, Topics topic);
	
	public List<Topic> searchSubTopic_Name_ParentTopicId(String name , int topicId,Topics subtopic);
	
	public int getTotalActiveTopic_Type(String name,Topics topic, Integer subCategoryId, Integer parentTopicId);
	
	public List<Topic> getActiveTopic_Type(String name, Topics topic, Integer subCategoryId, Integer parentTopicId,
			int pagesize, String sortField, String sortOrder, int start);
	
	public String suggestTopicOrSubTopicByNameAndType(String name, Integer parentId, String type);
	
	public String suggestTOrSTByNameAndTypeForMyPublishing(String name, String type, long userId);
	
	public List<TopicForm> getAllActiveTopics_ChildCount_SiteCount_RatingCount(Topics topic, String name, int start, int pagesize, String sortField, String sortOrder);
	
	public List<TopicForm> getAllActiveSubTopics_ChildCount_SiteCount_RatingCount( String name, int start, int pagesize, String sortField, String sortOrder);
	
	public List<TopicForm> getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount( String name, Integer subCategoryId, int pagesize, String sortField,
			String sortOrder, int start);
	
	public List<TopicForm> getActiveSubTopicsByCategoryId_ChildCount_SiteCount_RatingCount( String name, int id, int pagesize, String sortField,
			String sortOrder, int start);
	
	public List<Topic> getTopics_SubCategoryIdOrParentTopicId_Type(int subCatId, int parentTopicId, Topics topicType);
	
//	public int getTotalRatings_TopicId(int topicId,String name);
	
	

	
	
	
}
