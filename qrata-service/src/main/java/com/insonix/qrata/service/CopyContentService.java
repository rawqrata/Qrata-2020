package com.insonix.qrata.service;

import java.util.List;

import com.insonix.qrata.entity.SiteReviewForm;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;

/**
 * @author Harmeet Singh
 *
 */

public interface CopyContentService {

	public void saveCopyContents(String[] contentsIds, Category category, Topic topic);

	public void updateSiteReviewsCopyContent(List<Site> sites, SiteReviewForm form, int[] siteRatingIds, int[] ratings);

	public void updateSitesNameAndURL(List<Site> sites, Site site);
	
}
