package com.qrata.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrata.entity.ItemDetailForm;
import com.qrata.entity.SiteReviewForm;
import com.qrata.entity.SiteStatusAndScoreForm;
import com.qrata.enums.ReadStatus;
import com.qrata.enums.ReviewStatus;
import com.qrata.enums.Status;
import com.qrata.models.Category;
import com.qrata.models.ItemDetails;
import com.qrata.models.SiteReview;
import com.qrata.models.SiteReviewRatingCriteria;
import com.qrata.models.SiteReviewRatingCriteriaVoting;
import com.qrata.models.Topic;
import com.qrata.models.User;
import com.qrata.respository.ItemDetailsRepository;
import com.qrata.respository.SiteReviewRatingCriteriaVotingRepository;
import com.qrata.respository.SiteReviewsRepository;


@Service
public class SiteReviewServiceImpl implements SiteReviewService {

	@Autowired
	private SiteReviewsRepository siteReviewsRepository;
	
	@Autowired
	private ItemDetailsRepository itemdetailsRepository;
	
	@Autowired
	private SiteReviewRatingCriteriaVotingRepository votingRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public SiteReview getSiteReview_Id(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteReviewForm getSiteReviewForm_Id(long id) {
		 SiteReview siteReview = siteReviewsRepository.findById(id).get();
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
		form.setExpertFirstName(/* siteReview.getUser().getUserinfo().getFirstname() */"amol");
		form.setExpertLastName(/* siteReview.getUser().getUserinfo().getLastname() */"patil");
		form.setExpertId(/* siteReview.getUser().getId() */1);

	        return form;
	}

	@Override
	public void delete(SiteReview siteReviews) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save(SiteReview siteReviews) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SiteReview siteReviews) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBulk(List<SiteReview> siteReviewsList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSearchVector(SiteReviewForm siteReviewForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SiteReview insertSiteReview(SiteReviewForm siteReviewForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteReviewForm> findNewSiteRatings(int start, int pageSize, String sortColumn, String sortOrder,
			String siteName) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SiteReview> criteriaQuery = criteriaBuilder.createQuery(SiteReview.class);
		Root<SiteReview> itemRoot = criteriaQuery.from(SiteReview.class);

		Predicate predicateForStatus
		  = criteriaBuilder.equal(itemRoot.get("status"), Status.ACTIVE.getValue());
		Predicate predicateForReadStatus
		  = criteriaBuilder.equal(itemRoot.get("readStatus"),  ReadStatus.UNREAD.getValue());
		Predicate predicateForReviewStatus
		  = criteriaBuilder.equal(itemRoot.get("reviewStatus"),ReviewStatus.ONLINE.getValue());
		Predicate predicateForName
		  =criteriaBuilder.like(itemRoot.get("site").get("name"),siteName+"%");
		
		Predicate and1 = criteriaBuilder.and(predicateForStatus);
		Predicate and2 = criteriaBuilder.and(predicateForReadStatus);
		Predicate and3 = criteriaBuilder.and(predicateForReviewStatus);
		Predicate and4;
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(and1);
		predicates.add(and2);
		predicates.add(and3);
		if (siteName != null) {
			and4=criteriaBuilder.and(predicateForName);
			predicates.add(and4);
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		
		List<Order> orderList = new ArrayList<>();
		if(sortOrder == null) {
			orderList.add(criteriaBuilder.asc(itemRoot.get("site").get("name")));
		} else {
			if(sortOrder.equals("asc")){
				orderList.add(criteriaBuilder.asc(itemRoot.get(sortColumn)));
			}else{
				orderList.add(criteriaBuilder.desc(itemRoot.get(sortColumn)));
			}
		}
		criteriaQuery.orderBy(orderList);	
		List<SiteReview>  reviews= entityManager.createQuery(criteriaQuery)
				 .setFirstResult(start)
		         .setMaxResults(pageSize)
		         .getResultList();
		
		List<SiteReviewForm> siteReviews = new ArrayList<>();

	        for (SiteReview review : reviews) {
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
		
		return 0;
	}

	@Override
	public List<SiteReviewForm> findContentsByName(String siteName, int start, int pageSize, String sortColumn,
			String sortOrder) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SiteReview> criteriaQuery = criteriaBuilder.createQuery(SiteReview.class);
		Root<SiteReview> itemRoot = criteriaQuery.from(SiteReview.class);

		Predicate predicateForStatus
		  = criteriaBuilder.equal(itemRoot.get("status"), Status.ACTIVE.getValue());
		Predicate predicateForReviewStatus
		  = criteriaBuilder.equal(itemRoot.get("reviewStatus"),ReviewStatus.ONLINE.getValue());
		Predicate predicateForName
		  =criteriaBuilder.like(itemRoot.get("site").get("name"),siteName+"%");
		
		Predicate and1 = criteriaBuilder.and(predicateForStatus);
		Predicate and2 = criteriaBuilder.and(predicateForReviewStatus);
		Predicate and3;
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(and1);
		predicates.add(and2);
		if (siteName != null) {
			and3=criteriaBuilder.and(predicateForName);
			predicates.add(and3);
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		
		List<Order> orderList = new ArrayList<>();
		if(sortOrder == null) {
			orderList.add(criteriaBuilder.asc(itemRoot.get("site").get("name")));
		} else {
			if(sortOrder.equals("asc")){
				orderList.add(criteriaBuilder.asc(itemRoot.get(sortColumn)));
			}else{
				orderList.add(criteriaBuilder.desc(itemRoot.get(sortColumn)));
			}
		}
		criteriaQuery.orderBy(orderList);	
		List<SiteReview>  reviews= entityManager.createQuery(criteriaQuery)
				 .setFirstResult(start)
		         .setMaxResults(pageSize)
		         .getResultList();
		
		 List<SiteReviewForm> siteReviews = new ArrayList<>();

	        for (SiteReview review : reviews) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SiteReviewForm> findContentsByTopic(String name, int topicId, int start, int pageSize,
			String sortColumn, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalRatings_TopicId(int topicId, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SiteReviewForm> findContentsByExpert(long expertId, int start, int pageSize, String sortColumn,
			String sortOrder, String siteName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalRatings_ExpertId(long expertId, String siteName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Category> getDomains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getCategories_DomainId(String domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getSubCategories_CategoryId(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopics_SubCategoryId(String subCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getSubTopics_TopicId(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getExperts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteReviewForm> getSitesByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteReviewForm getSiteReviewRatings_URL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCheckedStatus(long sitereviewId) {
		SiteReview siteReview =siteReviewsRepository.findById(sitereviewId).get();
		siteReview.setReadStatus(ReadStatus.READ.getValue());
		siteReviewsRepository.save(siteReview);
	}

	@Override
	public List<SiteReviewForm> getSiteReviews_ReviewStatus(String name, int start, int pagesize, ReviewStatus online,
			long userId, String sortColumn, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSiteReviews_ReviewStatus(String name, long userId, ReviewStatus online) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSiteReviews_ReviewStatus_UserId(String name, long userId, ReviewStatus online) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SiteReviewForm> getSiteReviews_ReviewStatus_UserId(String name, int start, int pagesize,
			ReviewStatus online, long userId, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteReview getSiteReviews_SiteId_TopicId(long id, int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteStatusAndScoreForm getReviewStatus_TopicId_SiteId(int topicId, long siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String suggestReviewsBySiteName(String siteName, String type, long entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String suggestSiteByNameNReviewStatusForMyPublishing(String siteName, long userId, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String suggestSiteByNameNReviewStatusForExpertPublishing(String siteName, long userId, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteReview> getSiteReviews_TopicId(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setContentStatusOffline(ReviewStatus offline, long siteReviewId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTotal_AllContentByReviewStatus(String name, ReviewStatus reviewStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SiteReviewForm> getAllContentsByReviewStatus(String name, ReviewStatus reviewStatus, int start,
			int pagesize, String sortColumn, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteReview> getSiteReviews_UserId_ReviewStatus(long id, ReviewStatus reviewStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExpertCoreRatings(User user) {
		int count = 0;
		count=siteReviewsRepository.getExpertCoreRatings(Status.ACTIVE.getValue(),ReviewStatus.ONLINE.getValue(),user.getId());
		return count;
	}

	@Override
	public SiteReviewForm getSiteReviewRatingCriteria_id(long id) {
		 SiteReview siteReview =siteReviewsRepository.findById(id).get();
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
	public int getExpertTotalRatings(User user) {
		int count = 0;
		count=siteReviewsRepository.getExpertCoreRatings(Status.ACTIVE.getValue(),ReviewStatus.ONLINE.getValue(),user.getId());
		return count;
	}

	@Override
	public SiteReviewRatingCriteria getSiteReviewRatingCriteriaById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteReviewForm> qrataSearch_Keyword(String keyword, int start, int pageSize) {
		 //first query for all words in search
        keyword = keyword.trim().replace(":", "").replace(" ", "&");
        List<SiteReview> exactMatchReviewsList =siteReviewsRepository.qrataSearchKeywords_SearchTerm(keyword);

        int exactMatchReviewsListSize = exactMatchReviewsList.size();
        pageSize = pageSize - exactMatchReviewsListSize;

        //if multiple word search, do an OR'd query
        if (keyword.contains("&")) {
			/*
			 * keyword = StringUtils.isNotEmpty(keyword) ? keyword.replaceAll("&", "|") :
			 * ""; List<SiteReview> wildCardMatchReviewsList =
			 * siteReviewsDao.qrataSearch_Keyword(keyword, start, pageSize);
			 * exactMatchReviewsList.addAll(wildCardMatchReviewsList);
			 */
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
	public List<ItemDetailForm> qrataSearch_Keywordnew(String keyword, int start, int pageSize) {
		 //first query for all words in search
        keyword = keyword.trim().replace(":", "").replace(" ", "&");
//        List<SiteReview> exactMatchReviewsList =siteReviewsRepository.qrataSearchKeywords_SearchTerm(keyword);
        List<ItemDetails> exactMatchReviewsList =itemdetailsRepository.qrataSearchKeywords_SearchTerm1(keyword);

        int exactMatchReviewsListSize = exactMatchReviewsList.size();
        pageSize = pageSize - exactMatchReviewsListSize;

        //if multiple word search, do an OR'd query
        if (keyword.contains("&")) {
			/*
			 * keyword = StringUtils.isNotEmpty(keyword) ? keyword.replaceAll("&", "|") :
			 * ""; List<SiteReview> wildCardMatchReviewsList =
			 * siteReviewsDao.qrataSearch_Keyword(keyword, start, pageSize);
			 * exactMatchReviewsList.addAll(wildCardMatchReviewsList);
			 */
        }

        List<ItemDetailForm> reviewsFormList = new ArrayList<>();
        ItemDetailForm form = null;
        for (ItemDetails sr : exactMatchReviewsList) {
            form = new ItemDetailForm();
            try {
                BeanUtils.copyProperties(form, sr);
                form.setCategoryID(sr.getCategoryID());
                form.setItem_id(sr.getItemid());
                form.setRatings(sr.getRatings());
                form.setItem_name(sr.getItemName());
                form.setItem_image(sr.getItemImage());
                form.setItem_description(sr.getItemDescription());
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
	public String qrataSearchKeywords_SearchTerm(String searchTerm) {
	
        searchTerm = searchTerm.trim();
        List<SiteReview> keywordsList = siteReviewsRepository.qrataSearchKeywords_SearchTerm(searchTerm);
        Set<String> compSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        JSONArray arr = new JSONArray();
        JSONObject obj = null;
        try {
            if (searchTerm.contains(" ")) {
                String[] searchWords = searchTerm.split(" ");
                searchTerm = searchWords[searchWords.length - 1];
            }

            if (keywordsList != null) {
                for (SiteReview description : keywordsList) {
                    if (StringUtils.isNotEmpty(description.getDescription())) {
                        String[] words = description.getDescription().split(" ");
                        int size = words.length > 10 ? 10 : words.length;
                        for (int i = 0; i < size; i++) {
                            if (words[i].toUpperCase().indexOf(searchTerm.toUpperCase()) == 0) {
                                compSet.add(words[i].toLowerCase().replaceAll("[-+.^:,']", ""));
                            }
                        }
                    }
                }
            }

            for (String suggestion : compSet) {
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
	public void addSiteReviewRatingCriteriaVotings(List<SiteReviewRatingCriteriaVoting> criteriaVotings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SiteReviewForm getSiteReviewRatingCriteriaVotingPercentage(SiteReviewForm siteReview) {
		 List<SiteReviewRatingCriteriaVoting> criterias = votingRepository.getSiteReviewRatingCriteriaVotingPercentage(siteReview.getId());
	        short totalSiteReviewRatingCriteria = 0;
	        short totalAgreeVoting = 0;
	        short votingPercentage = 0;

	        //set voting percentage in SiteReviewRatingCriteria
	        for (Map<String, Set<SiteReviewRatingCriteria>> map : siteReview.getParentCriteriaWithChildren()) {
	            for (Map.Entry<String, Set<SiteReviewRatingCriteria>> entry : map.entrySet()) {
	                Set<SiteReviewRatingCriteria> ratingCriterias = entry.getValue();
	                for (SiteReviewRatingCriteria criteria : ratingCriterias) {
	                    totalSiteReviewRatingCriteria = 0;
	                    totalAgreeVoting = 0;
	                    votingPercentage = 0;

	                    for (SiteReviewRatingCriteriaVoting critVoting : criterias) {
	                        if (critVoting.getSiteReviewRatingCriteria().getId() == criteria.getId()) {
	                            totalSiteReviewRatingCriteria++;
	                            if (critVoting.getCriteriaVotingDecision() == 1) {
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
