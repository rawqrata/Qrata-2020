package com.insonix.qrata.service;

import com.insonix.qrata.entity.SiteReviewCommentsForm;
import com.insonix.qrata.models.User;

/**
 * @author Gurminder Singh
 *
 */
public interface SiteReviewCommentsService {
	public String addComment(SiteReviewCommentsForm commentsForm, User user);
	public String fetchAllComments(long reviewId, long userId);
	public String getUnreadCommentsCount(long reviewId, long userId);
}
