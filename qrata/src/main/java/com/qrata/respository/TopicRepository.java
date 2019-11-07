package com.qrata.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qrata.models.Category;
import com.qrata.models.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer>{
	/*
	 * SELECT t FROM Topic t LEFT JOIN t.topicExpertAssignment tea " +
	 * "WHERE t.createdBy = :created_by AND t.topicType = :topic_type AND tea.topic IS NULL AND t.childTopics IS EMPTY "
	 * + "AND LOWER(t.name) LIKE LOWER(:name) " +
	 * "ORDER BY "+sortColumn+" "+sortOrder;
	 */
	

	@Query("SELECT t FROM Topic t LEFT JOIN t.topicExpertAssignment tea WHERE t.createdBy = :created_by AND t.topicType = :topic_type AND tea.topic IS NULL AND t.childTopics IS EMPTY AND LOWER(t.name) LIKE LOWER(:name) ")
	public abstract List<Topic> getActiveTopics_EditorId(@Param("created_by") long id,@Param("topic_type") Short paramdstatus,@Param("name") String name);
	

}
