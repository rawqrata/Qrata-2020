package com.qrata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qrata.entity.AddSiteForm;
import com.qrata.entity.SiteReviewForm;
import com.qrata.enums.ReviewStatus;
import com.qrata.models.Category;
import com.qrata.models.Site;
import com.qrata.models.SiteReview;
import com.qrata.models.Topic;
import com.qrata.models.User;

@Service("SitesService")
public class SiteServiceImpl implements SiteService {

	@Override
	public Site getSite_Id(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Site sites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String saveOrUpdate(Site sites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Site sites) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateBulk(List<Site> sitesList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveSite(AddSiteForm form, long createdBy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Site> getActiveSites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(AddSiteForm form) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Site getSite(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> searchSites_Name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> getActiveSites_CategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddSiteForm> getAllActiveSites(int start, int pageSize, String name, String sortField,
			String sortOrder) {
		AddSiteForm addSiteForm = new AddSiteForm();
		addSiteForm.setId("1");
		addSiteForm.setCategoryId(1);
		addSiteForm.setCategoryName("sample");
		addSiteForm.setDomainId(1);
		addSiteForm.setExpertFirstName("kaldin");
		addSiteForm.setExpertid(2);
		addSiteForm.setTopicName("topic");
		addSiteForm.setName("sample name");
		
		List<AddSiteForm> list = new ArrayList<AddSiteForm>();
				list.add(addSiteForm);
		return list;
	}

	@Override
	public int getTotalSites(String name) {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public List<AddSiteForm> getActiveSites_Topics(String name, int start, int pagesize, List<Topic> topics) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSites_Topics(String name, List<Topic> topics) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkSite_URL_Topic(String url, int topicId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkSite_Name_Topic(int topicId, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AddSiteForm> getActiveSites_UserId(String name, int start, int pagesize, long userId, String sortOrder,
			String sortField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSites_UserId(String name, long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSites_Experts(String name, long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AddSiteForm> getActiveSites_Experts(String name, int start, int pagesize, long userId,
			String sortColumn, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fetchCountMyData(User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fetchCountExpertData(User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fetchCountExpert(User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalRatings_TopicId(String name, int topicId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AddSiteForm> getActiveSites_Topic(String name, Topic topic, int start, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAssignedDomainToExpert(long expertId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String suggestSiteByName(String name, long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String suggestSiteByNameForExpertPublishing(String name, long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalAllRatings_TopicId(int topicId, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SiteReviewForm> findAllContentsByTopic(String name, int topicId, int start, int pagesize,
			String sortColumn, String sortOrder, short roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteReviewForm> allContentsByTopic(String name, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllContentRatings_TopicId(int topicId, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long saveSiteUsingSQL(Site site) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SiteReviewForm> getOnlineSitesByTopic(Topic topic, ReviewStatus reviewStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> getLeafSites(Site site) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteReview> getSiteReviews(Site site) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeSiteLogo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
