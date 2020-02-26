/**
 * 
 */
package com.insonix.qrata.service;

import java.util.List;
import java.util.Set;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.entity.TopicForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;

/**
 * @author Harmeet Singh
 *
 */
public interface TopicExpertAssignmentService {
	public boolean assignedTopicToExpert(Topic topic, User user, User expert);
	
	public boolean unAssignedTopicToExpert(Topic topic);

	public List<TopicForm> getTopicExpertAssignment_AssignedBy(String name ,User user, short topicType, 
			int start, int pagesize, String sortColumn, String sortOrder);

	public int getTotalTopics_ExpertId(String replaceSpecialCharacters, long id, short topicType);

	public List<TopicForm> getActiveTopics_ExpertId(String name, int start, int pagesize, long id, short topicType, 
			String sortOrder, String sortColumn);

	public List<User> getExpert_AssignedByUser(long assignedById);

	public int getTotalTopicExpertAssignment_AssignedBy(String name,User user, short value);

	public List<Topic> getTopics_ExpertId(User user);
	
	public List<Topic> getTopics_AssignedId(User user);
	
	@Deprecated
	public List<Integer> getTopicIds();

	@Deprecated
	public boolean getTopicExists(Topic topic);

	public long getExpertId_TopicId(int topicId);

	public List<Long> getExpertIds_AssignedByUser(long assignedById);

	public List<Category> getAssignedCategoriesByDomainId_ExpertId(int domainId, long userId);

	public List<Category> getAssignedSubCategoriesByCategoryId_ExpertId(int categoryId, long userId);

	public Set<Topic> getAssignedTopicsBySubCategoryId_ExpertId(int subcategoryId, long userId);

	public List<Topic> getAssignedSubTopicByTopicId_expertId(int topicId, long userId);
	
	public String suggestTOrSTByNameAndTypeForExpertPublishing(String name, String type, long userId, Roles role);

	public String getAvailableExperts(String term);
}
