package com.qrata.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.qrata.entity.TopicForm;
import com.qrata.enums.Constants;
import com.qrata.enums.Constants.Topics;
import com.qrata.models.Site;
import com.qrata.models.Topic;

@Service
public class TopicServiceImpl implements TopicService {

	  @PersistenceContext
	    private EntityManager entityManager;
	
	@Override
	public Topic getTopics_Id(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Topic topics) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String save(Topic topics) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Topic topics) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBulk(List<Topic> topicsList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Topic> getTopics_SubCategoryId(String subCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveTopic(String id, String topicName, long createdBy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveSubTopic(String id, String subTopicName, long createdBy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Topic> getSubTopics(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TopicForm> getActiveTopics(String name, int start, int pageSize, String sortField, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TopicForm> getActiveSubTopics(String name, int start, int pageSize, String sortField,
			String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getActiveTopics_UserId(String name, int start, int pageSize, long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getActiveSubTopics_UserId(String name, int start, int pageSize, long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getSubTopics(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic getTopic(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTopic(TopicForm topic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String assignSites(String jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unAssignSites(String jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopics_Name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSumOfRatingCriteriasWeight_Topic(Topic topic) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Topic getTopic_Name_Id(String name, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopics_Name_CategoryId(String name, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalTopics(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSubTopics(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalTopics_UserId(String name, long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSubTopics_UserId(String name, long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Topic> getActiveTopics_SubTopics_UserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> getSites_Topic(Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalTopics_EditorId(String replaceSpecialCharacters, long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TopicForm> getActiveTopics_EditorId(String name, int start, int pagesize, long id, String sortOrder,
			String sortColumn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSubTopics_Editor(String name, long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TopicForm> getActiveSubTopics_Editor(String name, int start, int pagesize, long id, String sortOrder,
			String sortColumn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopics_SubCategoryId_AssignedTopics(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getSubTopics_TopicId_AssignedTopics(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger getSitesCount_TopicId(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> searchTopic_Name_SubCategoryId(String name, String subCategoryId, Topics topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> searchSubTopic_Name_ParentTopicId(String name, int topicId, Topics subtopic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalActiveTopic_Type(String name, Topics topic, Integer subCategoryId, Integer parentTopicId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Topic> getActiveTopic_Type(String name, Topics topic, Integer subCategoryId, Integer parentTopicId,
			int pagesize, String sortField, String sortOrder, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String suggestTopicOrSubTopicByNameAndType(String name, Integer parentId, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String suggestTOrSTByNameAndTypeForMyPublishing(String name, String type, long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TopicForm> getAllActiveTopics_ChildCount_SiteCount_RatingCount(Topics topic, String name, int start,
			int pagesize, String sortField, String sortOrder) {
		List<Object[]> all = entityManager
				.createNativeQuery(
						" SELECT t.id, t.name, t.uuid, t.category_id, tea.expert_id, ct.ct_count, tcr.tcr_count, s.s_count, t.leaf_node " + 
						" FROM topics t LEFT JOIN (SELECT parent_topic_id , COUNT(id) ct_count FROM topics GROUP BY parent_topic_id) ct ON ct.parent_topic_id = t.id " + 
						"			LEFT JOIN (SELECT topic_id, COUNT(id) tcr_count FROM topics_ratingcriteria GROUP BY topic_id) tcr ON tcr.topic_id = t.id " + 
						"				LEFT JOIN (SELECT topic_id, COUNT(site_id) s_count FROM topics_sites GROUP BY topic_id) s ON s.topic_id = t.id " + 
						"				LEFT JOIN topic_expert_assignment tea ON tea.topic_id = t.id").getResultList();
		return assignTopicObjectListValuesToTopicFormList(all);
	}

	@Override
	public List<TopicForm> getAllActiveSubTopics_ChildCount_SiteCount_RatingCount(String name, int start, int pagesize,
			String sortField, String sortOrder) {
		List<Object[]> all = entityManager
				.createNativeQuery("SELECT t.id, t.name, t.uuid, t.category_id, tea.expert_id, ct.ct_count, tcr.tcr_count, s.s_count, t.leaf_node " + 
						" FROM topics t LEFT JOIN (SELECT parent_topic_id , COUNT(id) ct_count FROM topics GROUP BY parent_topic_id) ct ON ct.parent_topic_id = t.id " + 
						" LEFT JOIN (SELECT topic_id, COUNT(id) tcr_count FROM topics_ratingcriteria GROUP BY topic_id) tcr ON tcr.topic_id = t.id " + 
						" LEFT JOIN (SELECT topic_id, COUNT(site_id) s_count FROM topics_sites GROUP BY topic_id) s ON s.topic_id = t.id " + 
						" LEFT JOIN topic_expert_assignment tea ON tea.topic_id = t.id  where topic_type="+Constants.Topics.SUBTOPIC.getValue()).getResultList();
	
		return assignTopicObjectListValuesToTopicFormList(all);
	}

	@Override
	public List<TopicForm> getActiveTopicsByCategoryId_ChildCount_SiteCount_RatingCount(String name,
			Integer subCategoryId, int pagesize, String sortField, String sortOrder, int start) {
		List<Object[]> all = entityManager
				.createNativeQuery("SELECT t.id, t.name, t.uuid, t.category_id, tea.expert_id, ct.ct_count, tcr.tcr_count, s.s_count, t.leaf_node " + 
						" FROM topics t LEFT JOIN (SELECT parent_topic_id , COUNT(id) ct_count FROM topics GROUP BY parent_topic_id) ct ON ct.parent_topic_id = t.id " + 
						" LEFT JOIN (SELECT topic_id, COUNT(id) tcr_count FROM topics_ratingcriteria GROUP BY topic_id) tcr ON tcr.topic_id = t.id " + 
						" LEFT JOIN (SELECT topic_id, COUNT(site_id) s_count FROM topics_sites GROUP BY topic_id) s ON s.topic_id = t.id " + 
						" LEFT JOIN topic_expert_assignment tea ON tea.topic_id = t.id  where topic_type="+Constants.Topics.TOPIC.getValue() +" and category_id="+subCategoryId).getResultList();
		return assignTopicObjectListValuesToTopicFormList(all);
	}

	@Override
	public List<TopicForm> getActiveSubTopicsByCategoryId_ChildCount_SiteCount_RatingCount(String name, int id,
			int pagesize, String sortField, String sortOrder, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopics_SubCategoryIdOrParentTopicId_Type(int subCatId, int parentTopicId, Topics topicType) {
		// TODO Auto-generated method stub
		return null;
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

}
