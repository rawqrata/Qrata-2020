package com.qrata.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrata.entity.SiteReviewForm;
import com.qrata.models.RatingCriteria;
import com.qrata.models.Site;
import com.qrata.models.SiteReview;
import com.qrata.models.SiteReviewRatingCriteria;
import com.qrata.models.Topic;
import com.qrata.models.User;
import com.qrata.respository.SiteReviewsRepository;

@Service
public class SiteReviewsRatingCriteriaServiceImpl implements SiteReviewsRatingCriteriaService {
	
	@Autowired
	private SiteReviewsRepository siteReviewsRepository;
	
	@Autowired
	private RatingCriteriaService ratingCriteriaService;

	@Override
	public SiteReviewRatingCriteria getSiteReviewsRatingCriteria_Id(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteReviewForm getSiteReviewsRatingCriteriaForm_Id(long id) {
		SiteReview siteReview = siteReviewsRepository.findById(id).get();
        SiteReviewForm form = new SiteReviewForm();
        form.setUrl(siteReview.getSite().getUrl());
        form.setId(siteReview.getId());
        form.setTopicName(siteReview.getTopics().getName());
        form.setScore(siteReview.getScore());
        form.setSiteName(siteReview.getSite().getName());
		form.setExpertFirstName(/* siteReview.getUser().getUserinfo().getFirstname() */"amol ");
		form.setExpertLastName(/* siteReview.getUser().getUserinfo().getLastname() */"patil");
		form.setExpertId(/* siteReview.getUser().getId() */1);
        List<RatingCriteria> list = ratingCriteriaService.findAllParentCriteria();
        List<Map<String, Set<SiteReviewRatingCriteria>>> parentList = new ArrayList<>();
        LinkedHashMap<String, Set<SiteReviewRatingCriteria>> map = new LinkedHashMap<>();
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
	public boolean delete(SiteReviewRatingCriteria siteReviewsRatingCriteria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String save(SiteReviewRatingCriteria siteReviewsRatingCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(SiteReviewRatingCriteria siteReviewsRatingCriteria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBulk(List<SiteReviewRatingCriteria> siteReviewsRatingCriteriaList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SiteReviewRatingCriteria> getSiteReviewsRatingCriterias(Topic topic, Site site, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveSiteReviewsRatingCriteria(int[] ratings, int[] criteriaIds, int[] siteRatingIds, int topicId,
			int siteId, int siteReviewId, String description, String evaluation, User loginUser, String weakness,
			String strength, short reviewStatus, long createdBy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBulk(List<SiteReviewRatingCriteria> siteReviewRatingCriterias) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateSitesScores(int topicId, int[] criteriaId, int[] topicWeightId, int[] weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getSiteRatingCriteria(long newSiteId, long reviewId, int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteReviewRatingCriteria saveBySQL(SiteReviewRatingCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUsingSQL(SiteReviewRatingCriteria siteReviewRatingCriteria) {
		// TODO Auto-generated method stub
		
	}

}
