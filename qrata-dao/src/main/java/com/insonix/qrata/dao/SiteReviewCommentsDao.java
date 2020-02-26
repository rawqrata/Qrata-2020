package com.insonix.qrata.dao;

import java.util.List;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.models.SiteReviewComments;
import com.insonix.qrata.models.User;

/**
 * @author Gurminder Singh
 *
 */
public interface SiteReviewCommentsDao extends IBaseDao<SiteReviewComments> {
	public SiteReviewComments addComment(long siteReviewId, String comment, User user);
	public List<SiteReviewComments> fetchAllComments(long reviewId, Status status);
	public void updateReadStatus(long userId);
	public int getUnreadCommentsCount(long reviewId, long userId, Status status);
}
