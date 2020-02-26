package com.insonix.qrata.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insonix.qrata.constants.Constants;
import com.insonix.qrata.constants.Constants.CategoryType;
import com.insonix.qrata.constants.ReviewStatus;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.CategoryDao;
import com.insonix.qrata.dao.SiteDao;
import com.insonix.qrata.entity.AddSiteForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.SiteReview;
import com.insonix.qrata.models.Topic;

@SuppressWarnings("unchecked")
@Repository("siteDao")
public class SiteDaoImpl extends BaseDao<Site> implements SiteDao {

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public List<Site> getSites(Status status) {
		Criteria criteria=getSession().createCriteria(Site.class);
		criteria.add(Restrictions.eq("status", status.getValue()));
		return criteria.list();
	}
	
	@Override
	public List<AddSiteForm> getAllSites(Status status, int start, int pageSize, String name,String sortField, String sortOrder) {
		List<AddSiteForm> sites = new ArrayList<>();
		AddSiteForm form = null;
		if(name != null)
			name = name.trim() + "%";
		else
			name = "%";
		
		String query = "";
		if(sortOrder == null && sortField == null){
			query = "SELECT s.id, s.name, s.url, s.uuid, t.id FROM Site s INNER JOIN s.topics t WHERE lower(s.name) LIKE lower(:name) " +
					" ORDER BY s.name ASC";
		}else{
			query = "SELECT s.id, s.name, s.url, s.uuid, t.id FROM Site s INNER JOIN s.topics t WHERE lower(s.name) LIKE lower(:name) " +
					" ORDER BY "+sortField+" "+sortOrder;
		}
		Query q = getSession().createQuery(query).setCacheable(true);
		q.setParameter("name", name);
		q.setMaxResults(pageSize);
		q.setFirstResult(start);
		
		List<Object[]> objects = q.list();
		for(Object[] obj : objects) {
			form = new AddSiteForm();
			String siteId = obj[0]!=null ? obj[0].toString() : "";
			String siteName = obj[1]!=null ? obj[1].toString() : "";
			String url = obj[2]!=null ? obj[2].toString() : "";
			String uuid = obj[3]!=null ? obj[3].toString() : "";
			String topicId = obj[4]!=null ? obj[4].toString() : "";
			form.setId(siteId);
			form.setName(siteName);
			form.setUrl(url);
			form.setSiteUuid(uuid);
			form.setTopicId(Integer.parseInt(topicId));
			sites.add(form);
		}		
		return sites;
	}

	@Override
	public Site getSite(String uuid) {
		Criteria criteria=getSession().createCriteria(Site.class);
		criteria.add(Restrictions.eq("uuid",uuid));
		return (Site)criteria.uniqueResult();
	}
	
	@Override
	public List<Site> searchSites_Name(String name, Status status) {
		Criteria criteria = getSession().createCriteria(Site.class);
		criteria.add(Restrictions.eq("status", status.getValue()));
		criteria.add(Restrictions.ilike("name", name+"%"));
		return criteria.list();
	}
	
	@Override
	public List<Site> getSites_CategoryId(int categoryId, Status status) {
		Category category = categoryDao.get(categoryId);
		Criteria criteria = getSession().createCriteria(Site.class);
		criteria.add(Restrictions.eq("status", status.getValue()));
		criteria.add(Restrictions.eq("category", category));		
		return criteria.list();
	}
	
	@Override
	public int getTotalSites(String name) {
		int count = 0;
		String nameClaue = "";
		if(name != null)
			nameClaue = " WHERE lower(s.name) LIKE lower(:name) ";

		String query = "SELECT COUNT(s.id) FROM Site s INNER JOIN s.topics t "+nameClaue;	
		Query q = getSession().createQuery(query).setCacheable(true);
		if(name != null)
			q.setParameter("name", name+"%");
		count = q.uniqueResult()!=null ? ((Long) q.uniqueResult()).intValue() : 0;
		return count;
	}
	
	@Override
	public int getTotalSites_Topics(String name, String topics) {
		int count = 0;
		
		String sql = "SELECT COUNT(s.id) FROM sites s " +
				"full join topics_sites ts on ts.site_id=s.id WHERE s.name ILIKE '"+name+"' and ts.topic_id in ("+topics+")";
		SQLQuery query = getSession().createSQLQuery(sql);
		count = query.uniqueResult()!=null ? ((BigInteger) query.uniqueResult()).intValue() : 0;
		return count;
	}

	@Override
	public List<AddSiteForm> getActiveSites_Topics(Status active, String name, int start, int pagesize, String topics) {
		List<AddSiteForm> sites = new ArrayList<>();
		AddSiteForm form = null;
		
		String sql = "SELECT s.id,s.name,s.url,s.uuid,ts.topic_id FROM sites s " +
				"full join topics_sites ts on ts.site_id=s.id WHERE s.name ILIKE '"+name+"' and ts.topic_id in ("+topics+") order by s.name LIMIT "+pagesize+" OFFSET "+start+" ";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> objects = query.list();
		for(Object[] obj : objects) {
			form = new AddSiteForm();
			String siteId = obj[0]!=null ? obj[0].toString() : "";
			String siteName = obj[1]!=null ? obj[1].toString() : "";
			String url = obj[2]!=null ? obj[2].toString() : "";
			String uuid = obj[3]!=null ? obj[3].toString() : "";
			String topicId = obj[4]!=null ? obj[4].toString() : "";
			form.setId(siteId);
			form.setName(siteName);
			form.setUrl(url);
			form.setSiteUuid(uuid);
			form.setTopicId(Integer.parseInt(topicId));
			sites.add(form);
		}		
		return sites;
	}

	@Override
	public boolean checkSite_URL_Topic(String url ,int topicId) {
		String hql = "SELECT s.id FROM Site s INNER JOIN s.topics t WHERE t.id = :topic_id and   LOWER(s.url) =  LOWER(:site_url)";
		Query query = getSession().createQuery(hql);
		query.setParameter("topic_id", topicId);
		query.setParameter("site_url", url);
		
		if(query.list().size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean checkSite_Name_Topic(int topicId, String name) {
		String hql = "SELECT s.id FROM Site s INNER JOIN s.topics t WHERE t.id = :topic_id and LOWER(s.name) = LOWER(:site_name)";
		Query query = getSession().createQuery(hql);
		query.setParameter("topic_id", topicId);
		query.setParameter("site_name", name);
		
		if(query.list().size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Object[]> getActiveSites_UserId(Status active, String name,int start, int pageSize, long userId,String sortOrder,String sortField) {
		String query = "";
		if(sortOrder == null){
				query = "SELECT s.id , s.name , s.url,  s.uuid , t.id , sr.score, sr.reviewStatus "
						+ "FROM Site s LEFT JOIN s.sitereviews sr "
						+ "INNER JOIN s.topics t "
						+ " WHERE lower(s.name) LIKE lower(:name)  " 
						+	" AND s.createdBy = :created_by ORDER BY s.name ASC";
		}else{
			if(sortField.equals("reviewStatus")){
				query = "SELECT s.id , s.name , s.url,  s.uuid , t.id , sr.score, sr.reviewStatus "
						+ "FROM Site s LEFT JOIN s.sitereviews sr "
						+ "INNER JOIN s.topics t "
						+ " WHERE lower(s.name) LIKE lower(:name)  " 
						+	" AND s.createdBy = :created_by";
			}else{
				query = "SELECT s.id , s.name , s.url,  s.uuid , t.id , sr.score, sr.reviewStatus "
						+ "FROM Site s LEFT JOIN s.sitereviews sr "
						+ "INNER JOIN s.topics t "
						+ " WHERE lower(s.name) LIKE lower(:name)  " 
						+	" AND s.createdBy = :created_by  ORDER BY "+sortField+" "+sortOrder;
			}
		}
		
		Query q = getSession().createQuery(query).setCacheable(true);
		q.setParameter("name", name);
		q.setParameter("created_by", userId);
		q.setMaxResults(pageSize);
		q.setFirstResult(start);
		
		return q.list();
	}

	@Override
	public int getTotalSites_UserId(String name, long userId,Status status) {
		int count = 0;
		Criteria crt = getSession().createCriteria(Site.class);
		crt.add(Restrictions.eq("createdBy", userId));
		crt.add(Restrictions.ilike("name", name));
		crt.setProjection(Projections.rowCount());
		count = crt.uniqueResult()!=null ? (Integer) crt.uniqueResult() : 0;
		return count;
	}

	@Override
	public List<Object[]> getActiveSites_Experts(Status active, String name, int start, int pagesize, 
			long userId, String sortField, String sortOrder) {		
		String sortName = "s.name";
		if(sortField != null && sortField.trim().equals("experts")){
			sortName = "info.firstname";
		}
		if(sortOrder == null ){
			sortOrder = "ASC";
		}
		
		String hql = "SELECT s.id , s.name , s.url,  s.uuid , s.createdBy , t.id , info.firstname , info.lastname, sr.score, sr.reviewStatus  "
				+ "FROM Site s LEFT JOIN s.sitereviews sr "
				+ "INNER JOIN s.topics t "
				+ "INNER JOIN t.topicExpertAssignment tea "
				+ "INNER JOIN tea.expert u "
				+ "INNER JOIN u.userinfo info "
				+ "WHERE tea.assignedBy.id = :user_id AND s.createdBy = tea.expert.id  "
				+ "AND LOWER(s.name) LIKE LOWER(:name) "
				+ "ORDER BY "+sortName+" "+sortOrder;
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("user_id", userId);
		query.setMaxResults(pagesize);
		query.setFirstResult(start);
		
		return query.list();
	}

	@Override
	public int getTotalSites_Experts(String name, long userId) {
		int count = 0;
		String hql = "SELECT COUNT(s.id) FROM Site s  JOIN s.topics t "
							+ "LEFT JOIN s.sitereviews sr "
							+" INNER JOIN t.topicExpertAssignment tea "
							+" INNER JOIN tea.expert u "
							+" WHERE tea.assignedBy.id = :user_id AND s.createdBy = tea.expert.id "
							+" AND LOWER(s.name) LIKE LOWER(:name)";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("user_id", userId);
		
		count = query.uniqueResult()!=null ? ((Long) query.uniqueResult()).intValue() : 0;
		return count;
	}

	@Override
	public int getTotalRatings_TopicId(Status active, String name, int topicId) {
		int count = 0;
		
		String sql = "SELECT COUNT(s.id) FROM sites s " +
				"full join topics_sites ts on ts.site_id=s.id WHERE s.name ILIKE '"+name+"' and ts.topic_id = "+topicId;
		
		SQLQuery query = getSession().createSQLQuery(sql);
		count = query.uniqueResult()!=null ? ((BigInteger) query.uniqueResult()).intValue() : 0;
		return count;
	}

	@Override
	public List<AddSiteForm> getActiveSites_Topic(Status active, String name,Topic topic, int start, int pagesize) {
		List<AddSiteForm> sites = new ArrayList<>();
		AddSiteForm form = null;
		
		String sql = "SELECT s.id,s.name,s.url,s.uuid,s.created_by, ts.topic_id FROM sites s " +
				"full join topics_sites ts on ts.site_id = s.id WHERE s.name ILIKE '"+name+"' and ts.topic_id = "+topic.getId()+" order by s.name LIMIT "+pagesize+" OFFSET "+start+" ";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> objects = query.list();
		for(Object[] obj : objects) {
			form = new AddSiteForm();
			String siteId = obj[0]!=null ? obj[0].toString() : "";
			String siteName = obj[1]!=null ? obj[1].toString() : "";
			String url = obj[2]!=null ? obj[2].toString() : "";
			String uuid = obj[3]!=null ? obj[3].toString() : "";
			String createdBy = obj[4]!=null ? obj[4].toString() : "";
			String topicId = obj[5]!=null ? obj[5].toString() : "";
			form.setId(siteId);
			form.setName(siteName);
			form.setUrl(url);
			form.setSiteUuid(uuid);
			form.setExpertid(Long.parseLong(createdBy));
			form.setTopicId(Integer.parseInt(topicId));
			sites.add(form);
		}		
		return sites;
	}

	@Override
	public List<Category> getAssignedDomainToExpert(long expertId, Status active, CategoryType domain) {
		String hql = "SELECT DISTINCT(d) FROM TopicExpertAssignment tea INNER JOIN tea.topic t INNER JOIN t.category sc " +
				"INNER JOIN sc.parentCategory c INNER JOIN c.parentCategory d " +
				"WHERE tea.expert.id = :expert_id " +
				"AND sc.categoryType = :subcategory_type AND sc.parentCategory IS NOT NULL " +
				"AND c.categoryType = :category_type AND c.parentCategory IS NOT NULL " +
				"AND d.categoryType = :domain_type AND d.parentCategory IS NULL";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("expert_id", expertId);
		query.setParameter("subcategory_type", Constants.CategoryType.SUBCATEGORY.getValue());
		query.setParameter("category_type", Constants.CategoryType.CATEGORY.getValue());
		query.setParameter("domain_type", Constants.CategoryType.DOMAIN.getValue());
		return query.list();
	}

	@Override
	public List<Site> getSiteNamesForAutoSuggest_Name(String name, long userId, Status status) {
		Criteria crt = getSession().createCriteria(Site.class, "s");
		crt.add(Restrictions.eq("s.status", status.getValue()));
		crt.setProjection(Projections.projectionList()
				.add(Projections.property("s.name"), "name"));
		crt.createAlias("s.topics", "topics");
		crt.add(Restrictions.ilike("s.name", name+"%"));
		crt.setMaxResults(10);
		crt.addOrder(Order.asc("s.name"));
		if(userId != 0) {
			crt.add(Restrictions.eq("s.createdBy", userId));
		}
		crt.setResultTransformer(Transformers.aliasToBean(Site.class));
		return crt.list();
	}
	
	@Override
	public List<String> getSiteNamesForAutoSuggestForExpertPublishing_Name(String name, long userId, 
			Status status) {
		String hql = "SELECT s.name FROM Site s  JOIN s.topics t "
				+" INNER JOIN t.topicExpertAssignment tea "
				+" INNER JOIN tea.expert u "
				+" WHERE tea.assignedBy.id = :user_id AND s.createdBy = tea.expert.id "
				+" AND lower(s.name) LIKE lower(:name) AND s.status = :status ORDER BY s.name";

		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name+"%");
		query.setParameter("user_id", userId);
		query.setParameter("status", status.getValue());
		query.setMaxResults(10);		
		return query.list();
	}

	@Override
	public int getTotalAllRatings_TopicId(Status active, int topicId, String name) {
		int count = 0;
		String hql = "SELECT COUNT(s.id)   "
							+ "FROM Site s LEFT JOIN s.sitereviews sr  "
							+ "JOIN s.topics t "
							+" INNER JOIN t.topicExpertAssignment tea "
							+" INNER JOIN tea.expert u "
							+" WHERE t.id = :topic_id AND s.createdBy = tea.expert.id "
							+" AND LOWER(s.name) LIKE LOWER(:name)";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("topic_id", topicId);
		
		count = query.uniqueResult()!=null ? ((Long) query.uniqueResult()).intValue() : 0;
		return count;
	}

	@Override
	public int getAllContentRatings_TopicId(Status active, int topicId, String name) {
		int count = 0;
		String hql = "SELECT COUNT(s.id)  "
				+ "FROM Site s LEFT JOIN s.sitereviews sr "
				+ "INNER JOIN s.topics t "
				+ "WHERE t.id = :topic_id  "
				+ "AND LOWER(s.name) LIKE LOWER(:name) ";
		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("topic_id", topicId);
		
		count = query.uniqueResult()!=null ? ((Long) query.uniqueResult()).intValue() : 0;
		return count;
	}
	
	@Override
	public List<Object[]> findAllContentsByTopic(String name, Status active, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId) {
		String sortName = "s.name";
		if(sortColumn == null){
			sortColumn = sortName;
		}
		if(sortOrder == null ){
			sortOrder = "ASC";
		}
		
		String hql = "SELECT s.id , s.name , s.url,  s.uuid , t.id , sr.score, sr.reviewStatus, info.firstname, info.lastname, u.id  "
				+ "FROM Site s LEFT JOIN s.sitereviews sr "
				+ "INNER JOIN s.topics t "
				+ "INNER JOIN t.topicExpertAssignment tea "
				+ "INNER JOIN tea.expert u "
				+ "INNER JOIN u.userinfo info "
				+ "WHERE t.id = :topic_id AND s.createdBy = tea.expert.id  "
				+ "AND LOWER(s.name) LIKE LOWER(:name) "
				+ "ORDER BY "+sortName+" "+sortOrder;

		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("topic_id", topicId);
		query.setMaxResults(pagesize);
		query.setFirstResult(start);
		
		return query.list();
	}
	
	@Override
	public List<Object[]> allContentsByTopic(String name, Status active, int topicId, int start, int pagesize, String sortColumn,
			String sortOrder, short roleId) {
		String sortName = "s.name";
		if(sortColumn == null){
			sortColumn = sortName;
		}
		if(sortOrder == null ){
			sortOrder = "ASC";
		}
		
		String hql = "SELECT s.id , s.name , s.url,  s.uuid , t.id , sr.score, sr.reviewStatus  "
				+ "FROM Site s LEFT JOIN s.sitereviews sr "
				+ "INNER JOIN s.topics t "
				+ "WHERE t.id = :topic_id  "
				+ "AND LOWER(s.name) LIKE LOWER(:name) "
				+ "ORDER BY "+sortName+" "+sortOrder;

		
		Query query = getSession().createQuery(hql).setCacheable(true);
		query.setParameter("name", name);
		query.setParameter("topic_id", topicId);
		query.setMaxResults(pagesize);
		query.setFirstResult(start);
		
		return query.list();
	}
	
	@Override
	public void updateContentLogoDetails(String imageName, String imagePath,
			long siteId) {
		Query q = getSession().createQuery("update Site s set s.imageName = :imageName,s.imagePath = :imagePath where s.id = :id");
		q.setParameter("imageName", imageName);
		q.setParameter("imagePath", imagePath);
		q.setParameter("id", siteId);
		q.executeUpdate();
	}

	@Override
	public long saveSiteUsingSQL(Site site) {
		try {
			String idQueryString = "SELECT nextval('sites_id_seq')";
			SQLQuery idQuery = getSession().createSQLQuery(idQueryString);
			BigInteger bigId = (BigInteger) idQuery.uniqueResult();
			long siteId = bigId.longValue();
			
			String sql = "INSERT INTO sites ( "
					+ "id, created_by, date_created, last_updated, status, uuid, name, "
					+ "url, category_id, image_name, image_path, rootsite_id, backup_rootsite_id) "
					+ "VALUES "
					+ "(:id, :created_by, :date_created, :date_updated, :status, :uuid, :name, "
					+ ":url, :category_id, :image_name, :image_path, :rootsite_id, :backup_rootsite_id)";
			
			SQLQuery query = getSession().createSQLQuery(sql);
			query.setParameter("id", siteId);
			query.setParameter("created_by", site.getCreatedBy());
			query.setParameter("date_created", site.getDateCreated());
			query.setParameter("date_updated", site.getLastUpdated());
			query.setParameter("status", site.getStatus());
			query.setParameter("uuid", site.getUuid());
			query.setParameter("name", site.getName());
			query.setParameter("url", site.getUrl());
			query.setParameter("category_id", site.getCategory().getId());
			query.setParameter("image_name", site.getImageName());
			query.setParameter("image_path", site.getImagePath());
			query.setParameter("rootsite_id", site.getRootSite().getId());
			query.setParameter("backup_rootsite_id", site.getRootSite().getId());
			
			query.executeUpdate();
			
			String joinSql = "INSERT INTO topics_sites ( "
					+ "topic_id, site_id )"
					+ "VALUES "
					+ "(:topic_id, :site_id)";
			
			SQLQuery joinQuery = getSession().createSQLQuery(joinSql);
			joinQuery.setParameter("topic_id", site.getTopics().get(0));
			joinQuery.setParameter("site_id", siteId);
			
			joinQuery.executeUpdate();
			
			return siteId;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Object[]> getOnlineSitesByTopic(Topic topic, ReviewStatus reviewStatus) {
		String hql = "SELECT s.id, s.name, s.url FROM Site s INNER JOIN s.sitereviews sr "
				+ "WHERE sr.reviewStatus = :review_status and sr.topics.id = :topic_id ";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("review_status", reviewStatus.getValue());
		query.setParameter("topic_id", topic.getId());
		
		return query.list();
	}

	@Override
	public List<Site> getLeafSites(long siteId) {
		String hql = "SELECT s.leafSites FROM Site s WHERE s.id = :site_id";
		Query query = getSession().createQuery(hql);
		query.setParameter("site_id", siteId);
		return query.list();
	}

	@Override
	public List<SiteReview> getSiteReviews(long siteId) {
		String hql = "SELECT s.sitereviews FROM Site s WHERE s.id = :site_id";
		Query query = getSession().createQuery(hql);
		query.setParameter("site_id", siteId);
		return query.list();
	}
}
