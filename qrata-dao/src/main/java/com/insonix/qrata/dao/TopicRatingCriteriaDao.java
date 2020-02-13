package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicRatingCriteria;

public interface TopicRatingCriteriaDao extends IBaseDao<TopicRatingCriteria> {

	public List<TopicRatingCriteria> getTopicWeights(Topic topic);

	public List<Topic> chekTopicRatings(Topic topic);

	public int getTotalTopicRatingCriteria_TopicId(Status active, int topicId);

	public List<Object[]> getTopicRatings_RatingCriteria(Topic topic);

}
