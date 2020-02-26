package com.insonix.qrata.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.insonix.qarata.modelutility.CustomSortComparator;
import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.CommonDao;
import com.insonix.qrata.dao.SiteDao;
import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.service.CopyContentService;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrata.service.SitesService;
import com.insonix.qrata.service.TopicExpertAssignmentService;
import com.insonix.qrata.service.TopicService;
import com.insonix.qrata.utility.QrataImagesUtil;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 *
 */
@Service("SitesService")
public class SitesServiceImpl implements SitesService {
	
	@Autowired
	SiteDao sitesDao;	
	@Autowired
	CategoryService categoryService;
	@Autowired
	TopicService topicService;
	@Autowired
	TopicExpertAssignmentService topicExpertAssignmentService;
	@Autowired
	SiteReviewsService siteReviewsService;
	@Autowired
	CommonDao commonDao;
	@Autowired
	CopyContentService copyContentService;
	
	CustomSortComparator<Site> customSort = new CustomSortComparator<Site>();

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SitesService#getSites_Id(int)
	 */
	@Override
	public Site getSites_Id(long id) {		
		return sitesDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SitesService#delete(com.insonix.qrata.models.Sites)
	 */
	@Override
	public boolean delete(Site sites) {
		try{
			sitesDao.delete(sites);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SitesService#save(com.insonix.qrata.models.Sites)
	 */
	@Override
	public String save(Site sites) {
		String id = sitesDao.save(sites);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SitesService#update(com.insonix.qrata.models.Sites)
	 */
	@Override
	public boolean update(Site sites) {
		try{
			sitesDao.update(sites);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SitesService#updateBulk(java.util.List)
	 */
	@Override
	public boolean updateBulk(List<Site> sitesList) {
		try{
			sitesDao.updateBulk(sitesList);
			return true;
		}catch(DataAccessException ex){
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SitesService#add(com.insonix.qrata.models.Sites)
	 */
	@Override
	public void add(Site sites) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.insonix.qrata.service.SitesService#saveSite(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean saveSite(AddSiteForm form, long createdBy) {
		Category category = categoryService.getCategory_Id(form.getCategoryId());
		Topic topic = null;
		if(form.getSubTopicId() == 0) {
			topic = topicService.getTopics_Id(form.getTopicId());			
			if(!topic.getLeafNode()) {
				topic.setLeafNode(true);
				topicService.update(topic);
			}			
		} else {
			topic = topicService.getTopics_Id(form.getSubTopicId());
		}
		
		Site site = new Site(form.getUrl(), form.getName(), category);
		site.setStatus(Status.ACTIVE.getValue());
		site.getTopics().add(topic);
		site.setCreatedBy(createdBy);
		site.setRootSite(null);
		String id = save(site);
		
		/*
		 * Save Logo
		 */
		long siteId = Long.parseLong(id);
		MultipartFile file = form.getSiteLogo();
		if (file != null && !file.isEmpty()) {
			try {				
				BufferedImage bImg = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
				bImg = Utility.resizeImage(bImg, 102, 102);
				
				String imageName = file.getOriginalFilename();
				String imagePath = "/Content-Pics/"+siteId+"/"+siteId+"_"+imageName;
				File contentPic = QrataImagesUtil.createContentPicFile(imageName, siteId);
				ImageIO.write(bImg, "png", contentPic);
				bImg.flush();
				
				sitesDao.updateContentLogoDetails(imageName, imagePath, siteId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(id.isEmpty()) {
			return false;
		} else {
			return true;
		}		
	}

	@Override
	public List<Site> getActiveSites() {		
		List<Site> sitesList = sitesDao.getSites(Status.ACTIVE);
		Collections.sort(sitesList, customSort);
		return sitesList;
	}
	
	@Override
	public List<AddSiteForm> getAllActiveSites(int start, int pageSize, String name,String sortField, String sortOrder) {
		name = Utility.searchValueCheck(name);
		return sitesDao.getAllSites(Status.ACTIVE, start, pageSize, name, sortField, sortOrder);
	}

	@Override
	public boolean delete(String uuid) {
		Site site = sitesDao.getSite(uuid);
		try{
			if(site.getImagePath() != null) {
				QrataImagesUtil.deleteContentPicFolder(site.getImageName(), site.getId());
			}
			
			sitesDao.delete(site);
			return true;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(AddSiteForm form) {	
		Site site = sitesDao.getSite(form.getSiteUuid());
		site.setName(form.getName());
		
		Category category = categoryService.getCategory_Id(form.getCategoryId());
		Topic topic = null;
		if(form.getSubTopicId() == 0) {
			topic = topicService.getTopics_Id(form.getTopicId());
		} else {
			topic = topicService.getTopics_Id(form.getSubTopicId());
		}
		
		//System.out.println("Topic ID : "+form.getTopicId()+"   Sub Topic ID : "+form.getSubTopicId());
		
		site.setCategory(category);
		site.setUrl(form.getUrl());
		site.getTopics().remove(site.getTopics().get(0));
		site.getTopics().add(topic);
		
		MultipartFile file = form.getSiteLogo();
		if (file != null && !file.isEmpty()) {
			try {
				if(site.getImagePath() != null) {
					QrataImagesUtil.deleteOldContentPicFile(site.getImageName(), site.getId());
				}
				
				BufferedImage bImg = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
				bImg = Utility.resizeImage(bImg, 102, 102);
				
				String imageName = file.getOriginalFilename();
				String imagePath = "/Content-Pics/"+site.getId()+"/"+site.getId()+"_"+imageName;
				File contentPic = QrataImagesUtil.createContentPicFile(imageName, site.getId());
				ImageIO.write(bImg, "png", contentPic);
				bImg.flush();
				
				site.setImageName(imageName);
				site.setImagePath(imagePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			sitesDao.update(site);
			if(site.getRootSite() != null){
				site.setRootSite(null);
			}else{
				List<Site> sites = this.getLeafSites(site);
				copyContentService.updateSitesNameAndURL(sites, site);
			}
			return true;
		} catch(DataAccessException ex) {
			return false;
		}
	}
	
	@Override
	public Site getSite(String uuid){
		return sitesDao.getSite(uuid);
	}
	
	@Override
	public List<Site> searchSites_Name(String name) {
		List<Site> sitesList = sitesDao.searchSites_Name(name, Status.ACTIVE);
		Collections.sort(sitesList, customSort);
		return sitesList;
	}
	
	@Override
	public List<Site> getActiveSites_CategoryId(int categoryId) {
		List<Site> sitesList = sitesDao.getSites_CategoryId(categoryId, Status.ACTIVE);
		Collections.sort(sitesList, customSort);
		return sitesList;
	}

	@Override
	public int getTotalSites(String name) {
		return sitesDao.getTotalSites(name);
	}

	@Override
	public int getTotalSites_Topics(String name , List<Topic> topics) {
		String topicsString = "";
		int count = 0;
		if(!topics.isEmpty()) {
			for(Topic topic : topics) {
				topicsString += (topic.getId()+",");
			}
			topicsString = topicsString.substring(0, topicsString.length()-1);
			if(name != null) {
				name += "%";
				count = sitesDao.getTotalSites_Topics(name , topicsString);
			} else {
				name = "%";
				count = sitesDao.getTotalSites_Topics(name , topicsString);
			}
		}
		return count;
	}
	
	@Override
	public List<AddSiteForm> getActiveSites_Topics(String name, int start, int pagesize, List<Topic> topics) {
		String topicsString = "";
		List<AddSiteForm> list = null;
		if(!topics.isEmpty()) {
			for(Topic topic : topics) {
				topicsString += (topic.getId()+",");
			}
			topicsString = topicsString.substring(0, topicsString.length()-1);
			if(name != null) {
				name += "%";
				list = sitesDao.getActiveSites_Topics(Status.ACTIVE, name, start, pagesize, topicsString);
			} else {
				name = "%";
				list = sitesDao.getActiveSites_Topics(Status.ACTIVE, name, start, pagesize, topicsString);
			}
		}
		return list;
	}

	@Override
	public boolean checkSite_URL_Topic(String url , int topicId) {
		return sitesDao.checkSite_URL_Topic(url,topicId);
	}
	
	@Override
	public boolean checkSite_Name_Topic(int topicId, String name) {
		return sitesDao.checkSite_Name_Topic(topicId,name);
	}

	@Override
	public List<AddSiteForm> getActiveSites_UserId(String name, int start,int pagesize, long userId,String sortOrder,String sortField) {
		List<AddSiteForm> sites = new ArrayList<>();
		List<Object[]> objects = null;
		name = Utility.searchValueCheck(name);
		
		objects = sitesDao.getActiveSites_UserId(Status.ACTIVE, name, start, pagesize, userId,sortOrder,sortField);
		
		for(Object[] obj : objects) {
			AddSiteForm form = new AddSiteForm();
			
			String siteId = obj[0]!=null ? obj[0].toString() : "";
			String siteName = obj[1]!=null ? obj[1].toString() : "";
			String url = obj[2]!=null ? obj[2].toString() : "";
			String uuid = obj[3]!=null ? obj[3].toString() : "";
			String topicId = obj[4]!=null ? obj[4].toString() : "";
			String siteScore = obj[5]!=null ? obj[5].toString() : "";
			String reviewStatus = obj[6]!=null ? obj[6].toString() : "";
			
			form.setId(siteId);
			form.setName(siteName);
			form.setUrl(url);
			form.setSiteUuid(uuid);
			form.setTopicId(Integer.parseInt(topicId));
			
			if(siteScore.isEmpty()){
				form.setSiteScore(0);
			}else{
				form.setSiteScore(Integer.parseInt(siteScore));
			}
			
			
			if(reviewStatus.isEmpty()){
				form.setReviewStatus(false);
				form.setStatus(ReviewStatus.getStatusByStatusId(ReviewStatus.NEW.getValue()));
			}else{
				form.setReviewStatus(true);
				form.setStatus(ReviewStatus.getStatusByStatusId(Short.parseShort(reviewStatus)));
			}
			sites.add(form);
		}		
		return sites;
	}

	@Override
	public int getTotalSites_UserId(String name, long userId) {
		int count = 0;
		name = Utility.searchValueCheck(name);
		count = sitesDao.getTotalSites_UserId(name , userId,Status.ACTIVE);
		return count;
	}

	@Override
	public int getTotalSites_Experts(String name, long userId) {
		int count = 0;
		name = Utility.searchValueCheck(name);
		count = sitesDao.getTotalSites_Experts(name , userId);
		return count;
	}

	@Override
	public List<AddSiteForm> getActiveSites_Experts(String name, int start,int pagesize,long userId, String sortColumn, String sortOrder) {
		List<Object[]> objects = new ArrayList<>();
		List<AddSiteForm> sites = new ArrayList<>();		
		name = Utility.searchValueCheck(name);
	
		objects = sitesDao.getActiveSites_Experts(Status.ACTIVE, name, start, pagesize, userId, sortColumn,sortOrder);
		
		for(Object[] obj : objects) {
			AddSiteForm form = new AddSiteForm();
			String siteId = obj[0]!=null ? obj[0].toString() : "";
			String siteName = obj[1]!=null ? obj[1].toString() : "";
			String url = obj[2]!=null ? obj[2].toString() : "";
			String uuid = obj[3]!=null ? obj[3].toString() : "";
			String createdBy = obj[4]!=null ? obj[4].toString() : "";
			String topicId = obj[5]!=null ? obj[5].toString() : "";
			String expertFirstname = obj[6]!=null ? obj[6].toString() : "";
			String expertLastname = obj[7]!=null ? obj[7].toString() : "";
			int score = obj[8]!=null ? (int) obj[8] : 0;
			short status = obj[9]!=null ? (short) obj[9] : 0;
				
			form.setId(siteId);
			form.setName(siteName);
			form.setUrl(url);
			form.setSiteUuid(uuid);
			form.setExpertid(Long.parseLong(createdBy));
			form.setTopicId(Integer.parseInt(topicId));
     		form.setExpertFirstName(expertFirstname);
			form.setExpertLastName(expertLastname);
			form.setScore(score);
			form.setStatus(ReviewStatus.getStatusByStatusId(status));
			sites.add(form);
		}		

		return sites;
	}
	
	
			/*
			 * Count Methods Start
			 */
	@Override
	public String fetchCountMyData(User loginUser) {
		JSONObject responseObj = new JSONObject();
		try {
			/*
			 * Rate Topics Count
			 */			
			List<Integer> assignedTopics = topicExpertAssignmentService.getTopicIds();
			int totalTopics = 0,  totalSubTopics = 0 , totalContents = 0, inProgressCount = 0, publishedCount = 0;
			if(!assignedTopics.isEmpty()){
				totalTopics  = topicService.getTotalTopics_EditorId(null, loginUser.getId());
				/*
				 * Rate Sub-Topics Count
				 */			
				totalSubTopics = topicService.getTotalSubTopics_Editor(null, loginUser.getId());
				/*
				 * Rate Contents Count
				 */			
				totalContents = getTotalSites_UserId(null, loginUser.getId());
				/*
				 * In-Progress Count
				 */			
				inProgressCount = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(null, loginUser.getId(), ReviewStatus.INPROGRESS);
				/*
				 * Published Count
				 */			
				publishedCount = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(null, loginUser.getId(), ReviewStatus.ONLINE);
			}
			responseObj.put("rateTopics", totalTopics);
			responseObj.put("rateSubTopics", totalSubTopics);
			responseObj.put("rateContents", totalContents);
			responseObj.put("inProgress", inProgressCount);
			responseObj.put("published", publishedCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}
	@Override
	public String fetchCountExpertData(User loginUser) {
		JSONObject responseObj = new JSONObject();
		try {
			/*
			 * Expert Topics Count
			 */			
			int totalExpertTopics = topicExpertAssignmentService.getTotalTopicExpertAssignment_AssignedBy(null, loginUser, Constants.Topics.TOPIC.getValue());
			responseObj.put("expertTopics", totalExpertTopics);
			/*
			 * Expert Sub Topics Count
			 */			
			int totalExpertSubTopics = topicExpertAssignmentService.getTotalTopicExpertAssignment_AssignedBy(null, loginUser, Constants.Topics.SUBTOPIC.getValue());
			responseObj.put("expertSubTopics", totalExpertSubTopics);
			/*
			 * Expert Contents Count
			 */			
//			List<Long> expertIds = topicExpertAssignmentService.getExpertIds_AssignedByUser(loginUser.getId());
			int totalExpertContents = getTotalSites_Experts(null, loginUser.getId());
			responseObj.put("expertContents", totalExpertContents);			
			/*
			 * Expert In-Progress Count
			 */
			int totalExpertInProgress = siteReviewsService.getTotalSiteReviews_ReviewStatus(null, loginUser.getId(), ReviewStatus.INPROGRESS);
			responseObj.put("expertInProgress", totalExpertInProgress);
			/*
			 * Expert Pending Approval Count
			 */
			int totalExpertPendingApproval = siteReviewsService.getTotalSiteReviews_ReviewStatus(null, loginUser.getId(), ReviewStatus.APPROVEL);
			responseObj.put("expertPendingApproval", totalExpertPendingApproval);
			/*
			 * Expert Re-Work Count
			 */
			int totalExpertRework = siteReviewsService.getTotalSiteReviews_ReviewStatus(null, loginUser.getId(), ReviewStatus.REVISE);
			responseObj.put("expertRework", totalExpertRework);
			/*
			 * Expert Published Count
			 */
			int totalExpertPublished = siteReviewsService.getTotalSiteReviews_ReviewStatus(null, loginUser.getId(), ReviewStatus.ONLINE);
			responseObj.put("expertPublished", totalExpertPublished);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}
	
	@Override
	public String fetchCountExpert(User loginUser) {
		JSONObject responseObj = new JSONObject();
		try {
			JSONObject expertPublishingObj = new JSONObject();				
			/*
			 * Expert Topics Count
			 */			
			int totalExpertTopics = topicExpertAssignmentService.getTotalTopics_ExpertId(null, loginUser.getId(), Constants.Topics.TOPIC.getValue());
			expertPublishingObj.put("expertTopics", totalExpertTopics);
			/*
			 * Expert Sub Topics Count
			 */			
			int totalExpertSubTopics = topicExpertAssignmentService.getTotalTopics_ExpertId(null, loginUser.getId(), Constants.Topics.SUBTOPIC.getValue());
			expertPublishingObj.put("expertSubTopics", totalExpertSubTopics);
			/*
			 * Expert Contents Count
			 */			
			int totalExpertContents = getTotalSites_UserId(null, loginUser.getId());
			expertPublishingObj.put("expertContents", totalExpertContents);			
			/*
			 * Expert In-Progress Count
			 */
			int totalExpertInProgress = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(null, loginUser.getId(), ReviewStatus.INPROGRESS);
			expertPublishingObj.put("expertInProgress", totalExpertInProgress);
			/*
			 * Expert Pending Approval Count
			 */
			int totalExpertPendingApproval = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(null, loginUser.getId(), ReviewStatus.APPROVEL);
			expertPublishingObj.put("expertPendingApproval", totalExpertPendingApproval);
			/*
			 * Expert Re-Work Count
			 */
			int totalExpertRework = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(null, loginUser.getId(), ReviewStatus.REVISE);
			expertPublishingObj.put("expertRework", totalExpertRework);
			/*
			 * Expert Published Count
			 */
			int totalExpertPublished = siteReviewsService.getTotalSiteReviews_ReviewStatus_UserId(null, loginUser.getId(), ReviewStatus.ONLINE);
			expertPublishingObj.put("expertPublished", totalExpertPublished);
						
			
			responseObj.put("responseObj", expertPublishingObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}

	@Override
	public int getTotalRatings_TopicId(String name, int topicId) {
		name = Utility.searchValueCheck(name);
		return sitesDao.getTotalRatings_TopicId(Status.ACTIVE,name,topicId);
	}

	@Override
	public List<AddSiteForm> getActiveSites_Topic(String name, Topic topic,int start, int pagesize) {
		name = Utility.searchValueCheck(name);
		return sitesDao.getActiveSites_Topic(Status.ACTIVE,name,topic,start,pagesize);
	}

	@Override
	public List<Category> getAssignedDomainToExpert(long expertId) {
		return sitesDao.getAssignedDomainToExpert(expertId, Status.ACTIVE, Constants.CategoryType.DOMAIN);
	}
	
	@Override
	public String suggestSiteByName(String name, long userId) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			List<Site> sitesList = sitesDao.getSiteNamesForAutoSuggest_Name(name, userId, Status.ACTIVE);
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
	public String suggestSiteByNameForExpertPublishing(String siteName, long userId) {
		JSONArray arr = new JSONArray();
		JSONObject obj = null;
		try {
			List<String> sitesList = sitesDao.getSiteNamesForAutoSuggestForExpertPublishing_Name(siteName, userId, Status.ACTIVE);
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
	public int getTotalAllRatings_TopicId(int topicId, String name) {
		name = Utility.searchValueCheck(name);
		return sitesDao.getTotalAllRatings_TopicId(Status.ACTIVE, topicId, name);
	}
	
	@Override
	public int getAllContentRatings_TopicId(int topicId, String name) {
		name = Utility.searchValueCheck(name);
		return sitesDao.getAllContentRatings_TopicId(Status.ACTIVE, topicId, name);
	}

	@Override
	public List<SiteReviewForm> findAllContentsByTopic(String name, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId) {
		name = Utility.searchValueCheck(name);
		
		List<Object[]> reviews =  sitesDao.findAllContentsByTopic(name, Status.ACTIVE, topicId, start, pagesize, sortColumn, sortOrder, roleId);
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		for(Object[] obj : reviews){
			long siteId = obj[0] !=null ? (long) obj[0] : 0;
			String siteName = obj[1] != null ? obj[1].toString() : "";
			String siteUrl = obj[2] != null ? obj[2].toString() : "";
			String uuid = obj[3] != null ? obj[3].toString() : "";
			int topicsId = obj[4] != null ? (int) obj[4] : 0;
			int score = obj[5] != null ? (int) obj[5] : 0;
			short reviewStatus = obj[6] != null ? (short) obj[6] : 0;
			String firstName = obj[7] != null ? obj[7].toString() : "";
			String lastName = obj[8] != null ? obj[8].toString() : "";
			long userId = obj[9] != null ? (long) obj[9] : 0;
			
			SiteReviewForm form = new SiteReviewForm();
			
			form.setSiteId(siteId);
			form.setSiteName(siteName);
			form.setUrl(siteUrl);
			form.setTopicId(topicsId);
			form.setScore(score);
			form.setUuid(uuid);
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(reviewStatus));
			form.setExpertFirstName(firstName);
			form.setExpertLastName(lastName);
			form.setUserId(userId);
			siteReviews.add(form);
		}
		return siteReviews;
	}
	
	@Override
	public List<SiteReviewForm> allContentsByTopic(String name, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId) {
		name = Utility.searchValueCheck(name);
		
		List<Object[]> reviews =  sitesDao.allContentsByTopic(name, Status.ACTIVE, topicId, start, pagesize, sortColumn, sortOrder, roleId);
		List<SiteReviewForm> siteReviews = new ArrayList<>();
		
		for(Object[] obj : reviews){
			long siteId = obj[0] !=null ? (long) obj[0] : 0;
			String siteName = obj[1] != null ? obj[1].toString() : "";
			String siteUrl = obj[2] != null ? obj[2].toString() : "";
			String uuid = obj[3] != null ? obj[3].toString() : "";
			int topicsId = obj[4] != null ? (int) obj[4] : 0;
			int score = obj[5] != null ? (int) obj[5] : 0;
			short reviewStatus = obj[6] != null ? (short) obj[6] : 0;
			
			SiteReviewForm form = new SiteReviewForm();
			
			form.setSiteId(siteId);
			form.setSiteName(siteName);
			form.setUrl(siteUrl);
			form.setTopicId(topicsId);
			form.setScore(score);
			form.setUuid(uuid);
			form.setReviewStatusName(ReviewStatus.getStatusByStatusId(reviewStatus));
			siteReviews.add(form);
		}
		return siteReviews;
	}

	@Override
	public long saveSiteUsingSQL(Site site) {
		return sitesDao.saveSiteUsingSQL(site);
	}

	@Override
	public List<SiteReviewForm> getOnlineSitesByTopic(Topic topic, ReviewStatus reviewStatus) {
		List<Object[]> objects = null;
		List<SiteReviewForm> forms = new ArrayList<>();
		
		objects = sitesDao.getOnlineSitesByTopic(topic, reviewStatus);
		
		for(Object[] obj : objects){
			long siteId 	= obj[0] != null ? (long) obj[0] : 0;
			String siteName	= obj[1] != null ? obj[1].toString() : "";
			String siteURL 	= obj[2] != null ? obj[2].toString() : "";
			
			SiteReviewForm form = new SiteReviewForm();
			form.setSiteId(siteId);
			form.setUrl(siteURL);
			form.setSiteName(siteName);
			
			forms.add(form);
		}
		return forms;
	}

	@Override
	public List<Site> getLeafSites(Site site) {
		return sitesDao.getLeafSites(site.getId());
	}

	@Override
	public List<SiteReview> getSiteReviews(Site site) {
		return sitesDao.getSiteReviews(site.getId());
	}
	
	@Override
	public String removeSiteLogo(Long id) {
		JSONObject obj = new JSONObject();
		try {
			Site site = sitesDao.get(id);
			if(site.getImagePath() != null) {
				QrataImagesUtil.deleteContentPicFolder(site.getImageName(), id);
			}
			site.setImageName(null);
			site.setImagePath(null);
			sitesDao.update(site);
			obj.put("msg", "Content logo has been removed successfully.");
		} catch (JSONException e) {
			try {
				obj.put("msg", "There is some problem removing the content logo. Please try again later.");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return obj.toString();
	}
}
