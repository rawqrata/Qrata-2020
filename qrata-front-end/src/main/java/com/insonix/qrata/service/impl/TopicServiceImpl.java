package com.insonix.qrata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.Constants.Topics;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.TopicDao;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.utility.Utility;

/**
 * @author Gurminder Singh
 *
 * @date 19-Jun-2013
 */

@Service("topicService")
public class TopicServiceImpl implements TopicService {
	@Autowired TopicDao topicDao;
	
	@Override
	public List<Topic> listTopics(int subCategoryId){
		return topicDao.getTopics_SubCategoryId(subCategoryId);
	}
	
	@Override
	public List<Topic> listSubTopics(int topicId){
		return topicDao.getSubTopics_TopicId(topicId);
	}
	@Override
	public Topic getTopics_Id(int id) {		
		return topicDao.get(id);
	}

	@Override
	public int getTotalActiveTopic_Type(String name, Topics topic,Integer subCategoryId, Integer parentTopicId) {
		name = Utility.searchValueCheck(name);
		return topicDao.getTotalActiveTopic_Type(Status.ACTIVE,name,topic,subCategoryId,parentTopicId);
	}
	@Override
	public List<Topic> getActiveTopic_Type(String name, Topics topic,Integer subCategoryId, Integer parentTopicId, int pagesize,
			String sortField, String sortOrder, int start) {
		name = Utility.searchValueCheck(name);
		return topicDao.getActiveTopic_Type(Status.ACTIVE,name,topic,subCategoryId,parentTopicId,pagesize,sortField,sortOrder,start);
	}	
}
