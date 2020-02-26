package com.insonix.qrata.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.service.CopyContentService;
import com.insonix.qrata.service.RatingCriteriaService;
import com.insonix.qrata.service.SiteReviewsRatingCriteriaService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.SitesService;
import com.insonix.qrata.service.TopicRatingCriteriaService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.utility.Utility;


/**
 * @author Harmeet Singh
 *
 */
@Service("copyContentService")
public class CopyContentServiceImpl implements CopyContentService {

	@Autowired
	SitesService sitesService;
	@Autowired
	SiteReviewsService siteReviewsService;
	@Autowired
	SiteReviewsRatingCriteriaService siteReviewsRatingCriteriaService;
	@Autowired
	RatingCriteriaService ratingCriteriaService;
	@Autowired
	TopicService topicService;
	@Autowired
	TopicRatingCriteriaService topicRatingCriteriaService;
	
	@Override
	public void saveCopyContents(String[] contentsIds, Category category, Topic topic) {
		Date date = new Date();
		long newSiteId = 0;
		SiteReview review = null; 
		
		for(String contentId : contentsIds){
			if(! contentId.trim().equals("0") && ! contentId.trim().equals("")){
				long id = Long.parseLong(contentId.trim());
				Site site = sitesService.getSites_Id(id);
				
				Site copySite = new Site();
				copySite.setName(site.getName());
				copySite.setUrl(site.getUrl());
				copySite.setCategory(category);
				copySite.setCreatedBy(site.getCreatedBy());
				copySite.setStatus(site.getStatus());
				copySite.getTopics().add(topic);
				if(site.getRootSite() == null){
					copySite.setRootSite(site);
				}else{
					copySite.setRootSite(site.getRootSite());
				}
				copySite.setDateCreated(date);
				copySite.setLastUpdated(date);
				copySite.setUuid(Utility.getUUID());
				copySite.setImageName(site.getImageName());
				copySite.setImagePath(site.getImagePath());
				copySite.setStatus(Status.ACTIVE.getValue());
				
				newSiteId = sitesService.saveSiteUsingSQL(copySite);
				
				if(newSiteId != 0) {
					Set<SiteReview> siteReviews = site.getSitereviews();
					for(SiteReview siteReview : siteReviews){
						SiteReviewForm form = new SiteReviewForm();
						
						form.setDescription(siteReview.getDescription());
						form.setEvaluation(siteReview.getEvaluation());
						form.setReviewStatus(siteReview.getReviewStatus());
						form.setScore(0);
						form.setSiteId(newSiteId);
						form.setTopicId(topic.getId());
						form.setDateCreated(new Date());
						form.setDateUpdated(new Date());
						form.setUuid(Utility.getUUID());
						form.setCreatedBy(siteReview.getCreatedBy());
						form.setUserId(siteReview.getUser().getId());
						form.setReadStatus(siteReview.getReadStatus());
						form.setWeakness(siteReview.getWeakness());
						form.setStrength(siteReview.getStrength());
						form.setStatusCode(siteReview.getStatusCode());
						form.setStatus(siteReview.getStatus());
						
						review = siteReviewsService.insertSiteReview(form);
						
						Set<SiteReviewRatingCriteria> siteReviewRatingCriterias = siteReview.getSiteReviewsRatingCriterias();
						for(SiteReviewRatingCriteria siteReviewRatingCriteria : siteReviewRatingCriterias){
							SiteReviewRatingCriteria criteria = new SiteReviewRatingCriteria();
							
							criteria.setRatingCriteria(siteReviewRatingCriteria.getRatingCriteria());
							criteria.setTopics(topic);
							criteria.setSiteReviews(review);
							criteria.setWeight(siteReviewRatingCriteria.getWeight());
							criteria.setSites(sitesService.getSites_Id(newSiteId));
							criteria.setCreatedBy(siteReviewRatingCriteria.getCreatedBy());
							criteria.setDateCreated(date);
							criteria.setLastUpdated(date);
							criteria.setUuid(Utility.getUUID());
							criteria.setStatus(siteReviewRatingCriteria.getStatus());
							
							SiteReviewRatingCriteria ratingCriteria = siteReviewsRatingCriteriaService.saveBySQL(criteria);
							review.getSiteReviewsRatingCriterias().add(ratingCriteria);
						}
					}
				}
			}
		}
		
		if(newSiteId != 0){
			int[][] topicRatingAndCriteriaId  = topicRatingCriteriaService.getTopicRatings_RatingCriteria(topic);
			siteReviewsRatingCriteriaService.updateSitesScores(topic.getId(), topicRatingAndCriteriaId[1], topicRatingAndCriteriaId[0], topicRatingAndCriteriaId[0]);
		}
		
	}

	@Override
	public void updateSiteReviewsCopyContent(List<Site> sites, SiteReviewForm form, int[] criteriaIds, int[] ratings) {
		for(Site site : sites){
			List<SiteReview> reviews =  sitesService.getSiteReviews(site);
			for(SiteReview review : reviews){
				form.setId(review.getId());
				siteReviewsService.updateSearchVector(form);
				
				Set<SiteReviewRatingCriteria> criterias =  review.getSiteReviewsRatingCriterias();
				int counter = 0;
				for(SiteReviewRatingCriteria criteria : criterias){
					criteria.setWeight(ratings[counter]);
					criteria.setRatingCriteria(ratingCriteriaService.getRatingCriteria_Id(criteriaIds[counter]));
					siteReviewsRatingCriteriaService.updateUsingSQL(criteria);
					counter++;
				}
			}
			
			Topic topic = site.getTopics().get(0);
			int[][] topicRatingAndCriteriaId  = topicRatingCriteriaService.getTopicRatings_RatingCriteria(topic);
			
			siteReviewsRatingCriteriaService.updateSitesScores(topic.getId(), topicRatingAndCriteriaId[1], topicRatingAndCriteriaId[0], topicRatingAndCriteriaId[0]);
			
		}
	}

	@Override
	public void updateSitesNameAndURL(List<Site> sites, Site site) {
		for(Site tempSite : sites){
			tempSite.setName(site.getName());
			tempSite.setUrl(site.getUrl());
			tempSite.setImageName(site.getImageName());
			tempSite.setImagePath(site.getImagePath());
			sitesService.update(tempSite);
		}		
	}

}
