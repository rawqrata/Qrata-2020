package com.insonix.qrata.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteReviewRatingCriteriaVotingDao;
import com.insonix.qrata.dao.SiteReviewsDao;
import com.insonix.qrata.dao.SiteReviewsRatingCriteriaDao;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.models.SiteReviewRatingCriteriaVoting;
import com.insonix.qrata.service.RatingCriteriaService;
import com.insonix.qrata.service.SiteReviewService;

/**
 * @author Gurminder Singh
 * 
 * @date 19-Jun-2013
 */

@Service("siteReviewService")
public class SiteReviewServiceImpl implements SiteReviewService {
	@Autowired
	SiteReviewsDao siteReviewsDao;
	@Autowired
	RatingCriteriaService ratingCriteriaService;
	@Autowired
	SiteReviewsRatingCriteriaDao siteReviewsRatingCriteriaDao;
	@Autowired
	SiteReviewRatingCriteriaVotingDao siteReviewRatingCriteriaVotingDao;
	
	@Override
	public SiteReviewForm getSiteReviewRatingCriteria_id(long id) {
		SiteReview siteReview = siteReviewsDao.get(id);
		SiteReviewForm form = new SiteReviewForm();
		form.setUrl(siteReview.getSite().getUrl());
		form.setId(siteReview.getId());
		form.setTopicName(siteReview.getTopics().getName());
		form.setScore(siteReview.getScore());
		form.setSiteName(siteReview.getSite().getName());
		form.setExpertFirstName(siteReview.getUser().getUserinfo()
				.getFirstname());
   		form.setExpertLastName(siteReview.getUser().getUserinfo().getLastname());
		form.setExpertId(siteReview.getUser().getId());
		List<RatingCriteria> list = ratingCriteriaService
				.findAllParentCriteria();
		List<Map<String, Set<SiteReviewRatingCriteria>>> parentList = new ArrayList<>();
		Map<String, Set<SiteReviewRatingCriteria>> map = new HashMap<>();
		Set<SiteReviewRatingCriteria> criterias = new HashSet<>();

		for (Iterator<RatingCriteria> itr = list.iterator(); itr.hasNext();) {
			RatingCriteria prc = itr.next();

			for (Iterator<SiteReviewRatingCriteria> itr2 = siteReview.getSiteReviewsRatingCriterias().iterator(); itr2.hasNext();) {
				SiteReviewRatingCriteria srrc = itr2.next();
				RatingCriteria rc = srrc.getRatingCriteria();
				if (rc.getParentRatingCriteria().getName()
						.equals(prc.getName())) {
					criterias.add(srrc);
				}
			}
			map.put(prc.getName(), criterias);
		}
		parentList.add(map);
		form.setParentCriteriaWithChildren(parentList);
		return form;
	}
	@Override
	public SiteReviewForm getSiteReview_id(long id) {
		SiteReview siteReview = siteReviewsDao.get(id);
		SiteReviewForm form = new SiteReviewForm();
		form.setUrl(siteReview.getSite().getUrl());
		form.setId(siteReview.getId());
		form.setDescription(siteReview.getDescription());
		form.setEvaluation(siteReview.getEvaluation());
		form.setTopicName(siteReview.getTopics().getName());
		form.setScore(siteReview.getScore());
		form.setStrength(siteReview.getStrength());
		form.setWeakness(siteReview.getWeakness());
		form.setSiteName(siteReview.getSite().getName());
		form.setSiteId(siteReview.getSite().getId());
		form.setImageName(siteReview.getSite().getImageName());
		form.setExpertFirstName(siteReview.getUser().getUserinfo().getFirstname());
		form.setExpertLastName(siteReview.getUser().getUserinfo().getLastname());
		form.setExpertId(siteReview.getUser().getId());
		
		return form;
	}

	@Override
	public String qrataSearchKeywords_SearchTerm(String searchTerm) {
		searchTerm = searchTerm.trim();
		List<String> keywordsList = siteReviewsDao
				.qrataSearchKeywords_SearchTerm(searchTerm);
		Set<String> compSet = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			if(searchTerm.contains(" ")) {
				String[] searchWords = searchTerm.split(" ");
				searchTerm = searchWords[searchWords.length - 1];
			}
			
			if(keywordsList != null) {
				for (String description : keywordsList) {
					if(StringUtils.isNotEmpty(description)) {
						String[] words = description.split(" ");
						int size = words.length>10 ? 10 : words.length;
						for(int i=0; i<size; i++) {
							if(words[i].toUpperCase().indexOf(searchTerm.toUpperCase()) == 0) {
								compSet.add(words[i].toLowerCase().replaceAll("[-+.^:,']", ""));
							}
						}
					}			
				}
			}
			
			for(String suggestion : compSet) {
				obj = new JSONObject();
				obj.put("name", suggestion);
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

	@Override
	public int getQrataSearchCount_Keyword(String keyword) {
		int count = 0;
		keyword = keyword.trim().replace(":", "").replace(" ", "|");
		
		int exactCount = siteReviewsDao.getQrataSearchCount_Keyword(keyword);
	System.out.println("exactCount  ===== "+exactCount);
	
		keyword = StringUtils.isNotEmpty(keyword) ? keyword+":*" : "";
		int wildCardCount = siteReviewsDao.getQrataSearchCount_Keyword(keyword);
	System.out.println("wildCardCount  >>>>>>  "+wildCardCount);
		
		count = exactCount + wildCardCount;
		return count;
	}
	
	@Override
	public List<SiteReviewForm> qrataSearch_Keyword(String keyword, int start, int pageSize) {
		keyword = keyword.trim().replace(":", "").replace(" ", "|");
		List<SiteReview> exactMatchReviewsList = siteReviewsDao
				.qrataSearch_Keyword(keyword, start, pageSize);
		
		int exactMatchReviewsListSize = exactMatchReviewsList.size();
		pageSize = pageSize - exactMatchReviewsListSize;
		if(pageSize != 0) {
			keyword = StringUtils.isNotEmpty(keyword) ? keyword+":*" : "";
			List<SiteReview> wildCardMatchReviewsList = siteReviewsDao.qrataSearch_Keyword(keyword, start, pageSize);
			exactMatchReviewsList.addAll(wildCardMatchReviewsList);
		}
		
		List<SiteReviewForm> reviewsFormList = new ArrayList<>();		
		SiteReviewForm form = null;
		for (SiteReview sr : exactMatchReviewsList) {
			form = new SiteReviewForm();
			try {
				BeanUtils.copyProperties(form, sr);
				form.setSiteName(sr.getSite().getName());
				form.setSiteId(sr.getSite().getId());
				form.setImageName(sr.getSite().getImageName());
				form.setUrl(sr.getSite().getUrl());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			reviewsFormList.add(form);
		}
		return reviewsFormList;
	}

	@Override
	public List<SiteReviewForm> findContentsByTopic(int topicId, int start,
			int pageSize) {
		String name = "%";
		List<SiteReview> reviews = siteReviewsDao.findContentsByTopicOrderByScore(name,
				Status.ACTIVE, topicId, start, pageSize, null, null);
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		for (SiteReview review : reviews) {
			SiteReviewForm form = new SiteReviewForm();
			form.setId(review.getId());
			form.setSiteId(review.getSite().getId());
			form.setSiteName(review.getSite().getName());
			form.setUrl(review.getSite().getUrl());
			form.setTopicId(review.getTopics().getId());
			form.setTopicName(review.getTopics().getName());   
			form.setScore(review.getScore());
			form.setUuid(review.getSite().getUuid());
			form.setUserId(review.getUser().getId());
			form.setDescription(review.getDescription());
			form.setEvaluation(review.getEvaluation());
			form.setStrength(review.getStrength());
			form.setWeakness(review.getWeakness());
			form.setExpertFirstName(review.getUser().getUserinfo()
					.getFirstname());
			form.setExpertLastName(review.getUser().getUserinfo().getLastname());
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(review
					.getReviewStatus()));
			form.setImageName(review.getSite().getImageName());
			siteReviews.add(form);
		}
		return siteReviews;
	}

	@Override
	public int getTotalRatings_TopicId(int topicId) {
		return siteReviewsDao.getTotalRatings_TopicId(Status.ACTIVE, topicId,
				null, ReviewStatus.ONLINE);
	}
	@Override
	public SiteReviewRatingCriteria getSiteReviewRatingCriteriaById(long id) {
		return siteReviewsRatingCriteriaDao.getSiteReviewRatingCriteriaById(Status.ACTIVE, id);
	}
	@Override
	public void addSiteReviewRatingCriteriaVotings(List<SiteReviewRatingCriteriaVoting> criteriaVotings) {
		siteReviewRatingCriteriaVotingDao.saveBulk(criteriaVotings);
	}
	
	@Override
	public SiteReviewForm getSiteReviewRatingCriteriaVotingPercentage(SiteReviewForm siteReview) {
		List<SiteReviewRatingCriteriaVoting> criterias = siteReviewRatingCriteriaVotingDao.getSiteReviewRatingCriteriaVotingPercentage(siteReview.getId());
		short totalSiteReviewRatingCriteria = 0;
		short totalAgreeVoting = 0;
		short votingPercentage = 0;
		
		//set voting percentage in SiteReviewRatingCriteria
		for(Map<String, Set<SiteReviewRatingCriteria>> map: siteReview.getParentCriteriaWithChildren()){
			for(Map.Entry<String, Set<SiteReviewRatingCriteria>> entry: map.entrySet()){
				Set<SiteReviewRatingCriteria> ratingCriterias = entry.getValue();
				for(SiteReviewRatingCriteria criteria: ratingCriterias){
					totalSiteReviewRatingCriteria = 0;
					totalAgreeVoting = 0;
					votingPercentage = 0;
					
					for(SiteReviewRatingCriteriaVoting critVoting: criterias){
						if(critVoting.getSiteReviewRatingCriteria().getId() == criteria.getId()){
							totalSiteReviewRatingCriteria++;
							if(critVoting.getCriteriaVotingDecision() == 1){
								totalAgreeVoting++;
							}
							votingPercentage = (short) ((totalAgreeVoting * 100) / totalSiteReviewRatingCriteria);
							//System.out.println("votingPercentage >>>>>>>>>>>>>>>>>>>> : "+votingPercentage);
							criteria.setVotingPercentage(votingPercentage);
						}
					}
				}
			}
		}
		return siteReview;
	}
}
