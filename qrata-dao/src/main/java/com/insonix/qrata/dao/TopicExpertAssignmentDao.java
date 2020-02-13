/**
 * 
 */
package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicExpertAssignment;
import com.insonix.qrata.models.User;

/**
 * @author Harmeet Singh
 *
 */
public interface TopicExpertAssignmentDao extends IBaseDao<TopicExpertAssignment>{

	public List<TopicExpertAssignment> getTopicExpertAssignment_AssignedBy(String name,User user, short topicType,
			int start, int pagesize, String sortColumn, String sortOrder);

	public int getTotalTopics_ExpertId(String name, Status active, long id, short topicType);

	public List<TopicExpertAssignment> getActiveTopics_ExpertId(String name,Status active, int start, int pagesize, long id, short topicType, 
			String sortOrder, String sortColumn);

	public List<User> getExpert_AssignedByUser(long assignedById, Status active);

	public int getTotalTopicExpertAssignment_AssignedBy(String name,User user,short topicType);

	public List<Topic> getTopics_ExpertId(Status active, User user);

	public List<Topic> getTopics_AssignedId(Status active, User user);

	public List<Integer> getTopicIds(Status active);

	public List<Integer> getTopics_TopicId(Status active, Topic topic);

	public long getExpertId_TopicId(Status active, int topicId);

	public List<Long> getExpertIds_AssignedByUser(long assignedById,Status active);

	public List<Category> getAssignedCategoriesByDomainId_ExpertId(int domainId, long userId);

	public List<Category> getAssignedSubCategoriesByCategoryId_ExpertId(int categoryId, long userId);

	public List<Topic> getAssignedTopicsBySubCategoryId_ExpertId(int subcategoryId, long userId);

	public List<Topic> getAssignedSubTopicByTopicId_expertId(int topicId, long userId);
	
	public List<String> getTopicNamesForAutoSuggestForExpertPublishing_Name(String name, Topics type, 
			Status status, long userId, Roles role);
}
