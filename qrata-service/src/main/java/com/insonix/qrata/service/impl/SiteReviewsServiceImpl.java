package com.insonix.qrata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.ReadStatus;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteReviewsDao;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.entity.SiteStatusAndScoreForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 *
 */
@Service("SiteReviewsService")
public class SiteReviewsServiceImpl implements SiteReviewsService {
	@Autowired
	SiteReviewsDao siteReviewsDao;
	@Autowired
	CategoryService categoryService;
	@Autowired
	TopicService topicService;
	@Autowired
	ExpertService expertService;
	
	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsService#getSiteReviews_Id(int)
	 */
	@Override
	public SiteReview getSiteReviews_Id(long id) {
		
		return siteReviewsDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsService#delete(com.insonix.qrata.models.SiteReviews)
	 */
	@Override
	public void delete(SiteReview siteReviews) {
		siteReviewsDao.delete(siteReviews);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsService#save(com.insonix.qrata.models.SiteReviews)
	 */
	@Override
	public String save(SiteReview siteReviews) {
		String id = siteReviewsDao.save(siteReviews);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsService#update(com.insonix.qrata.models.SiteReviews)
	 */
	@Override
	public void update(SiteReview siteReviews) {
		siteReviewsDao.update(siteReviews);
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SiteReviewsService#updateBulk(java.util.List)
	 */
	@Override
	public void updateBulk(List<SiteReview> siteReviewsList) {
		siteReviewsDao.updateBulk(siteReviewsList);		
	}

	
	@Override
	public List<SiteReviewForm> findNewSiteRatings(int start, int pageSize,String sortColumn, String sortOrder, String siteName) {
		List<SiteReview> reviews = siteReviewsDao.findNewSiteRatings(Status.ACTIVE, start, pageSize, ReadStatus.UNREAD, 
				sortColumn, sortOrder, siteName);
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		for(SiteReview review : reviews){
			SiteReviewForm form = new SiteReviewForm();
			form.setId(review.getId());
			form.setSiteId(review.getSite().getId());
			form.setSiteName(review.getSite().getName());
			form.setUrl(review.getSite().getUrl());
			form.setTopicId(review.getTopics().getId());
			form.setScore(review.getScore());
			form.setUuid(review.getSite().getUuid());
			form.setUserId(review.getUser().getId());
			form.setExpertFirstName(review.getUser().getUserinfo().getFirstname());
			form.setExpertLastName(review.getUser().getUserinfo().getLastname());
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(review.getReviewStatus()));
			
			siteReviews.add(form);
		}
		return siteReviews;
	}
	@Override
	public int getTotalNewRatings(String siteName) {
		return siteReviewsDao.getTotalNewRatings(siteName, Status.ACTIVE,ReadStatus.UNREAD);
	}
	
	@Override
	public List<SiteReviewForm> findContentsByName(String siteName, int start, int pageSize,String sortColumn, String sortOrder) {
		List<SiteReview> reviews =  siteReviewsDao.findContentsByName(Status.ACTIVE, siteName, start, pageSize, sortColumn, sortOrder);
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		for(SiteReview review : reviews){
			SiteReviewForm form = new SiteReviewForm();
			form.setSiteId(review.getSite().getId());
			form.setSiteName(review.getSite().getName());
			form.setUrl(review.getSite().getUrl());
			form.setTopicId(review.getTopics().getId());
			form.setScore(review.getScore());
			form.setUuid(review.getSite().getUuid());
			form.setUserId(review.getUser().getId());
			form.setExpertFirstName(review.getUser().getUserinfo().getFirstname());
			form.setExpertLastName(review.getUser().getUserinfo().getLastname());
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(review.getReviewStatus()));
			
			siteReviews.add(form);
		}
		return siteReviews;
	}
	@Override
	public int getTotalRatings_Name(String siteName) {
		return siteReviewsDao.getTotalRatings_Name(Status.ACTIVE, siteName, ReviewStatus.ONLINE);
	}
	
	@Override
	public List<SiteReviewForm> findContentsByTopic(String name, int topicId, int start, int pageSize,String sortColumn, String sortOrder) {
		if(name == null){
			name = "%";
		}else{
			name = name + "%";
		}
		
		List<SiteReview> reviews =  siteReviewsDao.findContentsByTopic(name, Status.ACTIVE, topicId, start, pageSize, sortColumn, sortOrder);
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		for(SiteReview review : reviews){
			SiteReviewForm form = new SiteReviewForm();
			form.setSiteId(review.getSite().getId());
			form.setSiteName(review.getSite().getName());
			form.setUrl(review.getSite().getUrl());
			form.setTopicId(review.getTopics().getId());
			form.setScore(review.getScore());
			form.setUuid(review.getSite().getUuid());
			form.setUserId(review.getUser().getId());
			form.setExpertFirstName(review.getUser().getUserinfo().getFirstname());
			form.setExpertLastName(review.getUser().getUserinfo().getLastname());
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(review.getReviewStatus()));
			
			siteReviews.add(form);
		}
		return siteReviews;
	}
	@Override
	public int getTotalRatings_TopicId(int topicId, String name) {
		return siteReviewsDao.getTotalRatings_TopicId(Status.ACTIVE, topicId, name, ReviewStatus.ONLINE);
	}
	
	@Override
	public List<SiteReviewForm> findContentsByExpert(long expertId, int start, int pageSize,String sortColumn, 
			String sortOrder, String siteName) {
		List<SiteReview> reviews =  siteReviewsDao.findContentsByExpert(Status.ACTIVE, expertId, start, pageSize,sortColumn, sortOrder, siteName);
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		for(SiteReview review : reviews){
			SiteReviewForm form = new SiteReviewForm();
			form.setSiteId(review.getSite().getId());
			form.setSiteName(review.getSite().getName());
			form.setUrl(review.getSite().getUrl());
			form.setTopicId(review.getTopics().getId());
			form.setScore(review.getScore());
			form.setUuid(review.getSite().getUuid());
			form.setUserId(review.getUser().getId());
			form.setExpertFirstName(review.getUser().getUserinfo().getFirstname());
			form.setExpertLastName(review.getUser().getUserinfo().getLastname());
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(review.getReviewStatus()));
			
			siteReviews.add(form);
		}
		return siteReviews;
	}
	@Override
	public int getTotalRatings_ExpertId(long expertId, String siteName) {
		return siteReviewsDao.getTotalRatings_ExpertId(Status.ACTIVE, expertId, siteName);
	}
	
		
	@Override
	public List<Category> getDomains() {		
		return categoryService.getActiveDomains();
	}
	
	@Override
	public List<Category> getCategories_DomainId(String domainId) {
		return categoryService.getCategories_domainId(domainId);
	}
	
	@Override
	public List<Category> getSubCategories_CategoryId(String categoryId) {
		return categoryService.getSubCategories_CategoryId(categoryId);
	}
	
	@Override
	public List<Topic> getTopics_SubCategoryId(String subCategoryId) {
		return topicService.getTopics_SubCategoryId(subCategoryId);
	}
	
	@Override
	public List<Topic> getSubTopics_TopicId(int topicId) {
		return topicService.getSubTopics(topicId);
	}
	
	@Override
	public List<User> getExperts() {
		return expertService.getExperts();
	}
	
	@Override
	public void updateSearchVector(SiteReviewForm siteReviewForm) {
		siteReviewsDao.updateSearchVector(siteReviewForm);
	}
	
	@Override
	public SiteReview insertSiteReview(SiteReviewForm siteReviewForm) {
		return siteReviewsDao.insertSiteReview(siteReviewForm);
	}
	
	@Override
	public List<SiteReviewForm> getSitesByKeyword(String keyword) {
		return siteReviewsDao.getSitesByKeyword(keyword);
	}

	@Override
	public SiteReviewForm getSiteReviewRatings_URL(String url) {
		return siteReviewsDao.getSiteReviewRatings_URL(url);
	}
	
	@Override
	public void setCheckedStatus(long sitereviewId) {
		siteReviewsDao.setCheckedStatus(sitereviewId, ReadStatus.READ);
	}

	@Override
	public List<SiteReviewForm> getSiteReviews_ReviewStatus(String name, int start, int pagesize, ReviewStatus status, 
			long userId, String sortColumn, String sortOrder) {
		List<Object[]> objects = null;
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		name = Utility.searchValueCheck(name);
		
		objects = siteReviewsDao.getSiteReviews_ReviewStatus(name, status, userId, Status.ACTIVE, start, pagesize, sortColumn,sortOrder);	
		for(Object[] obj : objects) {
			SiteReviewForm form = new SiteReviewForm();
			String siteId = obj[0]!=null ? obj[0].toString() : "";
			String siteName = obj[1]!=null ? obj[1].toString() : "";
			String url = obj[2]!=null ? obj[2].toString() : "";
			String topicId = obj[3]!=null ? obj[3].toString() : "";
			String expertFirstname = obj[4]!=null ? obj[4].toString() : "";
			String expertLastname = obj[5]!=null ? obj[5].toString() : "";
			String score = obj[6]!=null ? obj[6].toString() : "";
			String reviewStatus = obj[7]!=null ? obj[7].toString() : "";
			long expertId = obj[8]!=null ? Long.parseLong(obj[8].toString()) : 0;
				
			form.setSiteId(Long.parseLong(siteId));
			form.setSiteName(siteName);
			form.setUrl(url);
			form.setTopicId(Long.parseLong(topicId));
			form.setExpertFirstName(expertFirstname);
			form.setExpertLastName(expertLastname);
			form.setScore(Integer.parseInt(score));
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(Short.parseShort(reviewStatus)));
			form.setExpertId(expertId);
			siteReviews.add(form);
		}		

		return siteReviews;
	}

	@Override
	public int getTotalSiteReviews_ReviewStatus(String name, long userId, ReviewStatus status) {
		name = Utility.searchValueCheck(name);
		int count = siteReviewsDao.getTotalSiteReviews_ReviewStatus(name, status, userId,Status.ACTIVE);
		return count;
	}

	@Override
	public int getTotalSiteReviews_ReviewStatus_UserId(String name , long userId,ReviewStatus status) {
		name = Utility.searchValueCheck(name);
		return siteReviewsDao.getTotalSiteReviews_ReviewStatus_UserId(name , userId,Status.ACTIVE,status);
	}

	@Override
	public List<SiteReviewForm> getSiteReviews_ReviewStatus_UserId(String name , int start,int pagesize, ReviewStatus status, long userId,String sortOrder) {
		List<SiteReview> reviews = new ArrayList<>();
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		name = Utility.searchValueCheck(name);
		
		reviews =  siteReviewsDao.getSiteReviews_ReviewStatus_UserId(name ,Status.ACTIVE,start,pagesize,status,userId,sortOrder);
		
		for(SiteReview review : reviews){
			SiteReviewForm form = new SiteReviewForm();
			form.setSiteId(review.getSite().getId());
			form.setSiteName(review.getSite().getName());
			form.setUrl(review.getSite().getUrl());
			form.setTopicId(review.getTopics().getId());
			form.setScore(review.getScore());
			form.setUuid(review.getSite().getUuid());
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(review.getReviewStatus()));
			
			siteReviews.add(form);
		}return siteReviews;
	}

	@Override
	public SiteReview getSiteReviews_SiteId_TopicId(long siteId,int topicId) {
		return siteReviewsDao.getSiteReviews_SiteId_TopicId(Status.ACTIVE,siteId,topicId);
	}

	@Override
	public SiteStatusAndScoreForm getReviewStatus_TopicId_SiteId(int topicId, long siteId) {
		return siteReviewsDao.getReviewStatus_TopicId_SiteId(Status.ACTIVE,topicId,siteId);
	}

	@Override
	public String suggestReviewsBySiteName(String siteName, String type, long entityId) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			List<Site> sitesList = siteReviewsDao.getSiteNamesForReviewsAutoSuggest_Name(siteName, type, entityId, Status.ACTIVE);
			for(Site site: sitesList) {
				obj = new JSONObject();
				obj.put("name", site.getName());
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}
	
	@Override
	public String suggestSiteByNameNReviewStatusForMyPublishing(String siteName, long userId, String type) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			ReviewStatus reviewStatus = null;
			if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("REVISE")) {
				reviewStatus = ReviewStatus.REVISE;
			} else if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("APPROVEL")) {
				reviewStatus = ReviewStatus.APPROVEL;
			} else if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("INPROGRESS")) {
				reviewStatus = ReviewStatus.INPROGRESS;
			} else {
				reviewStatus = ReviewStatus.ONLINE;
			}
			
			List<Site> sitesList = siteReviewsDao.getSiteNamesForReviewsAutoSuggest_Name_ReviewStatus(siteName, userId, reviewStatus, Status.ACTIVE);
			for(Site site: sitesList) {
				obj = new JSONObject();
				obj.put("name", site.getName());
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}
	
	@Override
	public String suggestSiteByNameNReviewStatusForExpertPublishing(String siteName, long userId, String type) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			ReviewStatus reviewStatus = null;
			if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("REVISE")) {
				reviewStatus = ReviewStatus.REVISE;
			} else if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("APPROVEL")) {
				reviewStatus = ReviewStatus.APPROVEL;
			} else if(StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("INPROGRESS")) {
				reviewStatus = ReviewStatus.INPROGRESS;
			} else {
				reviewStatus = ReviewStatus.ONLINE;
			}
			
			List<String> sitesList = siteReviewsDao.getSiteNamesForReviewsAutoSuggestForExpertPublishing_Name_ReviewStatus(siteName, userId, reviewStatus, Status.ACTIVE);
			for(String name: sitesList) {
				obj = new JSONObject();
				obj.put("name", name);
				arr.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

	@Override
	public List<SiteReview> getSiteReviews_TopicId(int topicId) {
		return siteReviewsDao.getSiteReviews_TopicId(topicId, Status.ACTIVE);
	}
	
	@Override
	public boolean setContentStatusOffline(ReviewStatus offline, long siteReviewId) {
		int result = siteReviewsDao.setContentStatusOffline(Status.ACTIVE, offline, siteReviewId, ReviewStatus.ONLINE);
		if(result == 1){
			return true;
		}return false;
	}

	@Override
	public int getTotal_AllContentByReviewStatus(String name, ReviewStatus reviewStatus) {
		name = Utility.searchValueCheck(name);
		return siteReviewsDao.getTotal_AllContentByReviewStatus(name, reviewStatus, Status.ACTIVE);
	}

	@Override
	public List<SiteReviewForm> getAllContentsByReviewStatus(String name, ReviewStatus reviewStatus, int start, int pagesize,
			String sortColumn, String sortOrder) {
		List<Object[]> objects = null;
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		name = Utility.searchValueCheck(name);
		objects = siteReviewsDao.getAllContentsByReviewStatus(name, reviewStatus, start, pagesize, sortColumn, sortOrder, Status.ACTIVE);
		for(Object[] obj : objects) {
			SiteReviewForm form = new SiteReviewForm();
			long siteId = 			obj[0] != null ? (long) obj[0] : 0;
			String siteName =	 	obj[1] != null ? obj[1].toString() : "";
			String url =			obj[2] != null ? obj[2].toString() : "";
			int topicId = 			obj[3] != null ? (int) obj[3]: 0;
			String expertFirstname = obj[4] != null ? obj[4].toString() : "";
			String expertLastname = obj[5] != null ? obj[5].toString() : "";
			int score = 			obj[6] != null ? (int) obj[6] : 0;
			short status = 			obj[7] != null ? (short) obj[7] : 0;
			long expertId = 		obj[8] != null ? (long) obj[8] : 0;
			String roleName = 		obj[9] != null ? obj[9].toString() : "";
			
			form.setSiteId(siteId);
			form.setSiteName(siteName);
			form.setUrl(url);
			form.setTopicId(topicId);
			form.setExpertFirstName(expertFirstname);
			form.setExpertLastName(expertLastname);
			form.setScore(score);
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(status));
			form.setExpertId(expertId);
			form.setRoleName(roleName);
			siteReviews.add(form);
		}		

		return siteReviews;
	}

	@Override
	public List<SiteReview> getSiteReviews_UserId_ReviewStatus(long userId,ReviewStatus reviewStatus) {
		return siteReviewsDao.getSiteReviews_UserId_ReviewStatus(Status.ACTIVE, userId, reviewStatus);
	}

	@Override
	public int getExpertCoreRatings(User user) {
		return siteReviewsDao.getExpertCoreRatings(user, Status.ACTIVE, ReviewStatus.ONLINE);
	}

	@Override
	public int getExpertTotalRatings(User user) {
		return siteReviewsDao.getExpertTotalRatings(user, Status.ACTIVE, ReviewStatus.ONLINE);
	}
	
}
