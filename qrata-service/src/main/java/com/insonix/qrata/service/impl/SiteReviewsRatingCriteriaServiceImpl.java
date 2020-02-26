package com.insonix.qrata.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteReviewsDao;
import com.insonix.qrata.dao.SiteReviewsRatingCriteriaDao;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.TopicRatingCriteria;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.CopyContentService;
import com.insonix.qrata.service.RatingCriteriaService;
import com.insonix.qrata.service.SiteReviewsRatingCriteriaService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.SitesService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 *
 */
@Service("SiteReviewsRatingCriteriaService")
public class SiteReviewsRatingCriteriaServiceImpl implements SiteReviewsRatingCriteriaService {
	
	@Autowired
	SiteReviewsRatingCriteriaDao siteReviewsRatingCriteriaServiceDao;
	@Autowired
	SitesService sitesService;
	@Autowired
	TopicService topicService;
	@Autowired
	RatingCriteriaService ratingCriteriaService;
	@Autowired
	SiteReviewsService siteReviewsService;
	@Autowired 
	SiteReviewsDao siteReviewsDao;
	@Autowired
	CopyContentService copyContentService;

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsRatingCriteriaService#getSiteReviewsRatingCriteria_Id(int)
	 */
	@Override
	public SiteReviewRatingCriteria getSiteReviewsRatingCriteria_Id(long id) {
		
		return siteReviewsRatingCriteriaServiceDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsRatingCriteriaService#delete(com.insonix.qrata.models.SiteReviewsRatingCriteria)
	 */
	@Override
	public boolean delete(SiteReviewRatingCriteria siteReviewsRatingCriteria) {
		try{
			siteReviewsRatingCriteriaServiceDao.delete(siteReviewsRatingCriteria);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsRatingCriteriaService#save(com.insonix.qrata.models.SiteReviewsRatingCriteria)
	 */
	@Override
	public String save(SiteReviewRatingCriteria siteReviewsRatingCriteria) {
		String id = siteReviewsRatingCriteriaServiceDao.save(siteReviewsRatingCriteria);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsRatingCriteriaService#update(com.insonix.qrata.models.SiteReviewsRatingCriteria)
	 */
	@Override
	public boolean update(SiteReviewRatingCriteria siteReviewsRatingCriteria) {
		try{
			siteReviewsRatingCriteriaServiceDao.update(siteReviewsRatingCriteria);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsRatingCriteriaService#updateBulk(java.util.List)
	 */
	@Override
	public boolean updateBulk(
			List<SiteReviewRatingCriteria> siteReviewsRatingCriteriaList) {
		try{
			siteReviewsRatingCriteriaServiceDao.updateBulk(siteReviewsRatingCriteriaList);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	@Override
	public List<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias(Topic topic , Site site, User user) {
		return siteReviewsRatingCriteriaServiceDao.getSiteReviewsRatingCriterias(topic,site, user);
	}


	@Override
	public boolean saveBulk(List<SiteReviewRatingCriteria> siteReviewRatingCriterias) {
		try{
			siteReviewsRatingCriteriaServiceDao.saveBulk(siteReviewRatingCriterias);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
	}
	
	@Override
	public boolean saveSiteReviewsRatingCriteria(int[] ratings, int[] criteriaIds, int[] siteRatingIds, int topicId, int siteId, int siteReviewId,
			String description, String evaluation, User loginUser,String weakness ,String strength, short reviewStatus, long createdBy) {
		boolean saved = false;
		try {
			Site site = sitesService.getSites_Id(siteId);
			Topic topic = topicService.getTopics_Id(topicId);
			float totalTopicCriteriasWeight = topicService.getSumOfRatingCriteriasWeight_Topic(topic);
			float totalSiteScore = 0.0f;
			
			Set<TopicRatingCriteria> topicRatingCriterias = topic.getTopicRatingCriterias();
			Iterator<TopicRatingCriteria> topicRatingCriteriaItr = topicRatingCriterias.iterator();
			for(;topicRatingCriteriaItr.hasNext();) {
				TopicRatingCriteria topicRatingCriteria = topicRatingCriteriaItr.next();
				float topicCriteriaWeight = topicRatingCriteria.getWeight().intValue();
				int ratingCriteriaId = topicRatingCriteria.getRatingCriteria().getId();
				float singleTopicCriteriaPercentage = (topicCriteriaWeight / totalTopicCriteriasWeight) * 100;
				
				for(int i=0; i<ratings.length; i++) {
					float siteRating = ratings[i];
					int siteRatingCriteriaId = criteriaIds[i];					
					if (siteRatingCriteriaId == ratingCriteriaId) {
						float sitePercentage = singleTopicCriteriaPercentage * siteRating;
						float singleSiteCriteriaPercentage = (sitePercentage / 100) * 10;
						totalSiteScore = totalSiteScore + singleSiteCriteriaPercentage;
						break;
					}
				}
			}
			
			int siteScore = Math.round(totalSiteScore);
			
			
						/* Save Site Review N Rating Criteria */
			if(siteReviewId == 0) {
				SiteReviewForm siteReviewForm = new SiteReviewForm();
				siteReviewForm.setDescription(description);
				siteReviewForm.setEvaluation(evaluation);
				siteReviewForm.setUserId(loginUser.getId());
				siteReviewForm.setTopicId(topic.getId());
				siteReviewForm.setSiteId(site.getId());
				siteReviewForm.setStatus(Status.ACTIVE.getValue());
				siteReviewForm.setDateCreated(new Date());
				siteReviewForm.setDateUpdated(new Date());
				siteReviewForm.setUuid(Utility.getUUID());
				siteReviewForm.setScore(siteScore);
				siteReviewForm.setWeakness(weakness);
				siteReviewForm.setStrength(strength);
				siteReviewForm.setCreatedBy(createdBy);
				siteReviewForm.setReviewStatus(reviewStatus);
				SiteReview saveSiteReview = siteReviewsService.insertSiteReview(siteReviewForm);
//				siteReviewsService.save(siteReview);
				
				for(int i = 0; i < ratings.length ; i++) {
					SiteReviewRatingCriteria siteReviewRatingCriteria = new SiteReviewRatingCriteria();
					siteReviewRatingCriteria.setSites(site);
					siteReviewRatingCriteria.setTopics(topic);
					siteReviewRatingCriteria.setRatingCriteria(ratingCriteriaService.getRatingCriteria_Id(criteriaIds[i]));
					siteReviewRatingCriteria.setWeight(ratings[i]);
					siteReviewRatingCriteria.setSiteReviews(saveSiteReview);
					siteReviewRatingCriteria.setStatus(Status.ACTIVE.getValue());
					save(siteReviewRatingCriteria);
				}
			} else {
				SiteReviewForm siteReviewForm = new SiteReviewForm();
				siteReviewForm.setId(siteReviewId);
				siteReviewForm.setDescription(description);
				siteReviewForm.setEvaluation(evaluation);
				siteReviewForm.setWeakness(weakness);
				siteReviewForm.setStrength(strength);
				siteReviewForm.setDateUpdated(new Date());
				siteReviewForm.setScore(siteScore);
				siteReviewForm.setReviewStatus(reviewStatus);
			//	siteReviewForm.setUserId(loginUser.getId());
			//	siteReviewForm.setCreatedBy(createdBy);
				siteReviewsService.updateSearchVector(siteReviewForm);
				
				
				for(int i = 0; i < ratings.length ; i++) {
					SiteReviewRatingCriteria siteReviewRatingCriteria = getSiteReviewsRatingCriteria_Id(siteRatingIds[i]);
					siteReviewRatingCriteria.setWeight(ratings[i]);
					update(siteReviewRatingCriteria);
				}
				
				/* Update leaf contents if the site is root content */
				
				List<Site> sites = sitesService.getLeafSites(site);
				
				if(site.getRootSite() == null && ! sites.isEmpty() && reviewStatus == ReviewStatus.ONLINE.getValue()){
					copyContentService.updateSiteReviewsCopyContent(sites, siteReviewForm, criteriaIds, ratings);
				}else{
					site.setRootSite(null);
					sitesService.update(site);
				}
				
			}
			
			saved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saved;
	}

	@Override
	public void updateSitesScores(int topicId, int[] criteriaId, int[] topicWeightId, int[] weight) {
		List<SiteReview> siteReviews = siteReviewsService.getSiteReviews_TopicId(topicId);
		
		Topic topic = topicService.getTopics_Id(topicId);
	
		float totalTopicCriteriasWeight = topicService.getSumOfRatingCriteriasWeight_Topic(topic);
		
			
		for(SiteReview siteReview : siteReviews){
			Set<SiteReviewRatingCriteria> ratingCriterias = siteReview.getSiteReviewsRatingCriterias();
			float totalSiteScore = 0.0f;
		
			for(int i = 0; i < topicWeightId.length; i++){
				float topicCriteriaWeight = weight[i];
				int ratingCriteriaId = criteriaId[i];
				float singleTopicCriteriaPercentage = (topicCriteriaWeight / totalTopicCriteriasWeight) * 100;
			
			
				for(SiteReviewRatingCriteria criteria : ratingCriterias){
					if(criteria.getRatingCriteria().getId() == ratingCriteriaId){
						float sitePercentage = singleTopicCriteriaPercentage * criteria.getWeight();
						float singleSiteCriteriaPercentage = (sitePercentage / 100) * 10;
						totalSiteScore = totalSiteScore + singleSiteCriteriaPercentage;
						break;
					}
				}
			}
			siteReviewsDao.updateSiteScore(siteReview.getId(), Math.round(totalSiteScore));
		}
	}

	@Override
	public int[] getSiteRatingCriteria(long newSiteId, long reviewId, int topicId) {
		List<Object> objects = null;
		objects = siteReviewsRatingCriteriaServiceDao.getSiteRatingCriteria(newSiteId, reviewId, topicId);
		
		int[] siteRatingCriteria = new int[objects.size()];
		for(int i = 0; i < objects.size(); i++){
			siteRatingCriteria[i] = objects.get(i) != null? (int) objects.get(i): 1;
		}
		return siteRatingCriteria;
	}

	@Override
	public SiteReviewRatingCriteria saveBySQL(SiteReviewRatingCriteria criteria) {
		long id = siteReviewsRatingCriteriaServiceDao.saveBySQL(criteria);
		return getSiteReviewsRatingCriteria_Id(id);
	}

	@Override
	public void updateUsingSQL(SiteReviewRatingCriteria siteReviewRatingCriteria) {
		siteReviewsRatingCriteriaServiceDao.updateUsingSQL(siteReviewRatingCriteria);
	}
		
}
