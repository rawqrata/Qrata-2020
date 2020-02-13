package com.insonix.qrata.dao;

import java.math.BigInteger;
import java.util.List;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;

public interface TopicDao extends IBaseDao<Topic> {
	
	@Deprecated
	public List<Topic> getTopics_SubCategoryId(int subCategoryId);
	
	public List<Topic> getSubTopics_TopicId(int topicsId);
	
	public List<Topic> getSubTopics(String uuid);

	public List<Topic> getTopicsOrSubtopics_Type(String name, Status status, Constants.Topics type,
			int start, int pageSize, String sortField, String sortOrder);
	
	public List<Topic> getTopicsOrSubtopics_Type_UserId(String name, Status status, Constants.Topics type,
			int start, int pageSize, long userId);
	
	public int getCount_Type(String name, Status status, Constants.Topics type);
	
	public int getCount_Type_UserId(String name, Status status, Constants.Topics type, long userId);
	
	public Topic getTopic(String uuid);
		
	public List<Topic> getTopics_Name(String name);
 
	public Topic getTopic_Name_Id(String name, int id); 

	public List<Topic> getTopics_Name_CategoryId(String name, int categoryId);

	public List<Topic> getTopics_Subtopics_Type_UserId(Status active,  long userId);

	public List<Site> getSites_Topic(Topic topic);

	public int getTotalTopics_EditorId(String replaceSpecialCharacters, Status active, Topics topic, long id);

	public List<Topic> getActiveTopics_EditorId(String name, Status active,Topics topic, int start, int pagesize, long id, 
			String sortOrder, String sortColumn);

	public List<Topic> getTopics_SubCategoryId_AssignedTopics(int subCategoryId);

	public List<Topic> getSubTopics_TopicId_AssignedTopics(int topicId);

	public BigInteger getSitesCount_TopicId(int topicId);

	public List<Topic> searchTopic_Name_SubCategoryId(String name,int subCatId, Status active, Topics topic);

	public List<Topic> searchSubTopic_Name_ParentTopicId(String name,int topicId, Topics subtopic, Status active);

	public int getTotalActiveTopic_Type(Status active, String name,Topics topic, Integer subCategoryId, Integer parentTopicId);

	public List<Topic> getActiveTopic_Type(Status active, String name,Topics topic, Integer subCategoryId, Integer parentTopicId,
			int pagesize, String sortField, String sortOrder, int start);
		
	public List<String> getTopicNamesForAutoSuggest_Name(String name, Integer parentId, Status status, Topics type);
	
	public List<String> getTopicNamesForAutoSuggestForMyPublishing_Name(String name, Topics type, Status status, long userId);

	public List<Object[]> getAllActiveTopics_Type_ChildCount_SiteCount_RatingCount( Topics topic, String name, int start, int pagesize,
			String sortField, String sortOrder);

	public List<Object[]> getAllActiveSubTopics_ChildCount_SiteCount_RatingCount( Topics subtopic, String name, int start, int pagesize,
			String sortField, String sortOrder);

	public List<Object[]> getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount( Topics topic, Integer subCategoryId, String name, int start,
			int pagesize, String sortField, String sortOrder);

	public List<Object[]> getActiveSubTopicsByCategoryId_ChildCount_SiteCount_RatingCount( Topics subtopic, String name, int parentTopicId, int start,
			int pagesize, String sortField, String sortOrder);

	public List<Topic> getTopics_SubCategoryIdOrParentTopicId_Type(Status active, Topics topicType, int subCatId, int parentTopicId);

//	public int getTotalRatings_TopicId(Status status,int topicId, String name, ReviewStatus reviewStatus);
//	
	
	
	

	
	
	
}
