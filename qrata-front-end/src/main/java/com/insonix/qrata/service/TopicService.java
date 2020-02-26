package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.models.Topic;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */
public interface TopicService {
	public List<Topic> listTopics(int subCategoryId);
	
	public List<Topic> listSubTopics(int topicId);
	
	public Topic getTopics_Id(int id);
	public int getTotalActiveTopic_Type(String name,Topics topic, Integer subCategoryId, Integer parentTopicId);
	public List<Topic> getActiveTopic_Type(String name, Topics topic,Integer subCategoryId, Integer parentTopicId, int pagesize,
			String sortField, String sortOrder, int start);
}
