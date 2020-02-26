/**
 * Author Gurminder Singh
 * Date Created 07-May-2013 4:48:17 PM
 */
package com.insonix.qrataapi.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.SiteReviewRatingCriteria;
import com.insonix.qrata.service.SiteReviewsService;
import com.insonix.qrataapi.api.SiteReviewsApi;
import com.insonix.qrataapi.model.SiteRatingsInfo;
import com.insonix.qrataapi.model.SiteReviewsInfo;
import com.insonix.qrataapi.model.SiteScoreInfo;

@Service("siteReviewsApiImpl")
@CrossOriginResourceSharing(allowAllOrigins=true)
public class SiteReviewsApiImpl implements SiteReviewsApi {
	Logger log = Logger.getLogger(getClass());
	@Autowired
	SiteReviewsService siteReviewsService;


	/* (non-Javadoc)
	 * @see com.insonix.qrataapi.api.SiteReviewsApi#getSiteReviews(java.lang.String[])
	 */
	@Override
	public Response getSitesByKeyword(String search) {
		log.info("Executing getSitesByKeyword(String search)");
		SiteScoreInfo siteScoreInfo = new SiteScoreInfo();
		try {
			List<SiteReviewForm> siteReviewsList = siteReviewsService.getSitesByKeyword(search);
			if(!siteReviewsList.isEmpty()) {
				List<SiteReviewsInfo> responseList = new ArrayList<>();
				SiteReviewsInfo siteReviewsInfo = null;
				for(SiteReviewForm siteReviewForm : siteReviewsList) {
					siteReviewsInfo = new SiteReviewsInfo();
					siteReviewsInfo.setUrl(siteReviewForm.getUrl());
					siteReviewsInfo.setScore(siteReviewForm.getScore());
					responseList.add(siteReviewsInfo);
				}			
				siteScoreInfo.setResponse(responseList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		log.info("Returning response");
		return Response.ok().entity(gson.toJson(siteScoreInfo)).build();
	}
	
	
	/* (non-Javadoc)
	 * @see com.insonix.qrataapi.api.SiteReviewsApi#getSiteReviewRatings(java.lang.String[])
	 */
	@Override	
	public Response getSiteReviewRatings(String[] urls, String token) {
		log.info("Executing getSiteReviewRatings(String[] urls, String token)");
		SiteRatingsInfo siteRatingsInfo = null;
		try {
			if(urls != null && urls.length != 0) {
				if(StringUtils.isEmpty(token)) {
					token = null;
				}
				siteRatingsInfo = new SiteRatingsInfo();
				List<Map<String, Object>> responseList = new ArrayList<>();
				for(String url : urls) {
					SiteReviewForm siteReviewForm = siteReviewsService.getSiteReviewRatings_URL(url);
					if(siteReviewForm != null) {
						Map<String, Object> map = new HashMap<>();
						map.put("id", siteReviewForm.getId());
						map.put("score", siteReviewForm.getScore());
						map.put("description", siteReviewForm.getDescription());
						map.put("evaluation", siteReviewForm.getEvaluation());
						map.put("url", siteReviewForm.getUrl());
						map.put("created_at", siteReviewForm.getDateCreated());
						map.put("updated_at", siteReviewForm.getDateUpdated());
						map.put("strengths", siteReviewForm.getStrength());
						map.put("weaknesses", siteReviewForm.getWeakness());
						map.put("expert_id", siteReviewForm.getExpertId());
						
						Set<SiteReviewRatingCriteria> criterias = siteReviewForm.getSiteReviewRatingCriterias();
						for(SiteReviewRatingCriteria criteria : criterias) {
							String name = alterCriteriaNames(criteria);							
							map.put(name, criteria.getWeight());
						}
						
						responseList.add(map);
					} else {
						responseList.add(null);
					}
				}
				
				siteRatingsInfo.setResponse(responseList);
				siteRatingsInfo.setToken(token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		log.info("Returning response");
		return Response.ok().entity(gson.toJson(siteRatingsInfo)).build();
	}


	/**
	 * @param criteria
	 * @return
	 */
	private String alterCriteriaNames(SiteReviewRatingCriteria criteria) {
		String name;
		switch(criteria.getRatingCriteria().getName()) {
			case "Internal & External Links" :
				name = "links";
				break;
			case "Accuracy & Consistency" :
				name = "accuracy";
				break;
			case "Navigation" :
				name = "navigation";
				break;
			case "Completeness" :
				name = "completeness";
				break;
			case "Output Features" :
				name = "features";
				break;
			case "Currency" :
				name = "currency";
				break;
			case "Privacy" :
				name = "privacy";
				break;
			case "Richness of Content" :
				name = "richness";
				break;
			case "Searching & Browsing" :
				name = "searching";
				break;
			case "Scope & Coverage" :
				name = "coverage";
				break;
			case "Speed & Availability" :
				name = "speed";
				break;
			default :
				name = "size";
				break;
		}
		return name;
	}
		
}
