package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicRatingCriteria;


/**
 * @author kamal
 *
 */
public interface TopicRatingCriteriaService {
	
	/**
	 * @param id
	 * @return
	 */
	public TopicRatingCriteria getTopicsRatingCriteria_Id(int id);
	/**
	 * @param topicsRatingCriteria
	 */
	public void delete(TopicRatingCriteria topicsRatingCriteria);
	/**
	 * @param topicsRatingCriteria
	 * @return
	 */
	public String save(TopicRatingCriteria topicsRatingCriteria);
	/**
	 * @param topicsRatingCriteria
	 * @return
	 */
	public boolean saveBulk(List<TopicRatingCriteria> topicsRatingCriteriaList);
	/**
	 * @param topicsRatingCriteria
	 */
	public void update(TopicRatingCriteria topicsRatingCriteria);
	/**
	 * @param topicsRatingCriteriaList
	 */
	public void updateBulk(List<TopicRatingCriteria> topicsRatingCriteriaList);
	/**
	 * @param topic
	 * @return
	 */
	public List<TopicRatingCriteria> getTopicWeights(Topic topic);
	/**
	 * @param topic
	 * @return
	 */
	public boolean chekTopicRatings(Topic topic);
	
	public int getTotalTopicRatingCriteria_TopicId(int topicId);
	
	public List<TopicRatingCriteria> getTopicRatingCriterias(Topic topic);
	
	public int[][] getTopicRatings_RatingCriteria(Topic topic);

}
