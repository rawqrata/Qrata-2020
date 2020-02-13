package com.insonix.qrata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.TopicRatingCriteriaDao;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicRatingCriteria;
import com.insonix.qrata.service.RatingCriteriaService;
import com.insonix.qrata.service.TopicRatingCriteriaService;

/**
 * @author kamal
 *
 */
@Service("TopicsRatingCriteriaService")
public class TopicRatingCriteriaServiceImpl implements TopicRatingCriteriaService {
	@Autowired
	TopicRatingCriteriaDao topicsRatingCriteriaDao;
	@Autowired
	RatingCriteriaService ratingCriteriaService;

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsRatingCriteriaService#getTopicsRatingCriteria_Id(int)
	 */
	@Override
	public TopicRatingCriteria getTopicsRatingCriteria_Id(int id) {
		return topicsRatingCriteriaDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsRatingCriteriaService#delete(com.insonix.qrata.models.TopicsRatingCriteria)
	 */
	@Override
	public void delete(TopicRatingCriteria topicsRatingCriteria) {
		topicsRatingCriteriaDao.delete(topicsRatingCriteria);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsRatingCriteriaService#save(com.insonix.qrata.models.TopicsRatingCriteria)
	 */
	@Override
	public String save(TopicRatingCriteria topicsRatingCriteria) {
		String id = topicsRatingCriteriaDao.save(topicsRatingCriteria);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsRatingCriteriaService#updateBulk(java.util.List)
	 */
	@Override
	public boolean saveBulk(List<TopicRatingCriteria> topicsRatingCriteriaList) {
		try{
			topicsRatingCriteriaDao.saveBulk(topicsRatingCriteriaList);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsRatingCriteriaService#update(com.insonix.qrata.models.TopicsRatingCriteria)
	 */
	@Override
	public void update(TopicRatingCriteria topicsRatingCriteria) {
		topicsRatingCriteriaDao.delete(topicsRatingCriteria);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.TopicsRatingCriteriaService#updateBulk(java.util.List)
	 */
	@Override
	public void updateBulk(List<TopicRatingCriteria> topicsRatingCriteriaList) {
		topicsRatingCriteriaDao.updateBulk(topicsRatingCriteriaList);
		
	}
	
	@Override
	public List<TopicRatingCriteria> getTopicWeights(Topic topic) {
		return topicsRatingCriteriaDao.getTopicWeights(topic);
		
	}

	@Override
	public boolean chekTopicRatings(Topic topic) {
		List<Topic> tempTopic = topicsRatingCriteriaDao.chekTopicRatings(topic);
		//System.out.println(tempTopic.isEmpty());
		if(!tempTopic.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int getTotalTopicRatingCriteria_TopicId(int topicId) {
		return topicsRatingCriteriaDao.getTotalTopicRatingCriteria_TopicId(Status.ACTIVE,topicId);
	}

	@Override
	public List<TopicRatingCriteria> getTopicRatingCriterias(Topic topic) {
		List<TopicRatingCriteria> topicRatingCriterias = getTopicWeights(topic);
		if(topicRatingCriterias.size() != 0){
			return topicRatingCriterias;
		}else{
			List<TopicRatingCriteria> topicRatingNewCriterias = new ArrayList<TopicRatingCriteria>();
			List<RatingCriteria> criterias = ratingCriteriaService.findAll();
			for(int i = 0 ; i < criterias.size() ; i++){
				TopicRatingCriteria topicRatingCriteria = new TopicRatingCriteria();
				topicRatingCriteria.setRatingCriteria(criterias.get(i));
				topicRatingCriteria.setWeight(0);
				topicRatingNewCriterias.add(topicRatingCriteria);
			}
			return topicRatingNewCriterias;
		}
	}

	@Override
	public int[][] getTopicRatings_RatingCriteria(Topic topic) {
		List<Object[]> objects = null;
		int[][] topicRatingAndCriteriaId = new int[2][];
		objects = topicsRatingCriteriaDao.getTopicRatings_RatingCriteria(topic);
		int[] topicWeight = new int[objects.size()];
		int[] crtieriaIds = new int[objects.size()];
		
		for(int i = 0; i < objects.size(); i++ ){
			Object[] obj = objects.get(i);
			topicWeight[i] = obj != null ? (int) obj[0] : 1;
			crtieriaIds[i] = obj != null ? (int) obj[1] : 0;
		}
		topicRatingAndCriteriaId[0] = topicWeight;
		topicRatingAndCriteriaId[1] = crtieriaIds;

		return topicRatingAndCriteriaId;
	}
}
