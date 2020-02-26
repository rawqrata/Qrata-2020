package com.insonix.qrata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insonix.qrata.constants.Status;
import com.insonix.qrata.dao.SiteReviewCommentsDao;
import com.insonix.qrata.entity.SiteReviewCommentsForm;
import com.insonix.qrata.models.SiteReviewComments;
import com.insonix.qrata.models.User;
import com.insonix.qrata.service.SiteReviewCommentsService;
import com.insonix.qrata.utility.Utility;

/**
 * @author Gurminder Singh
 *
 */
@Service("siteReviewCommentsService")
public class SiteReviewCommentsServiceImpl implements SiteReviewCommentsService {
	@Autowired
	SiteReviewCommentsDao siteReviewCommentsDao;
	
	
	@Override
	public String addComment(SiteReviewCommentsForm commentsForm, User user) {
		JSONObject responseObj = new JSONObject();
		try {
			SiteReviewComments siteReviewComment = siteReviewCommentsDao.addComment(commentsForm.getSiteReviewId(),
					commentsForm.getComment(), user);
			JSONObject obj = new JSONObject();
			if(siteReviewComment != null) {
				String fullName = siteReviewComment.getCommentedBy().getUserinfo().getFirstname() 
						+ " " + siteReviewComment.getCommentedBy().getUserinfo().getLastname();
				obj.put("id", siteReviewComment.getId());
				obj.put("userName", fullName);
				obj.put("comment", siteReviewComment.getComment());
				obj.put("dateCreated", Utility.formatDate("dd MMMM, yyyy 'at' hh:mm a", siteReviewComment.getDateCreated()));
			}
			
			responseObj.put("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}
	
	@Override
	public String fetchAllComments(long reviewId, long userId) {
		JSONObject responseObj = new JSONObject();
		try {
			JSONObject commentsForm = null;
			List<JSONObject> commentList = new ArrayList<>();
			siteReviewCommentsDao.updateReadStatus(userId);
			List<SiteReviewComments> comments = siteReviewCommentsDao.fetchAllComments(reviewId, Status.ACTIVE);
			for(SiteReviewComments siteReviewComments : comments) {
				commentsForm = new JSONObject();
				String fullName = siteReviewComments.getCommentedBy().getUserinfo().getFirstname() 
						+ " " + siteReviewComments.getCommentedBy().getUserinfo().getLastname();
				commentsForm.put("id", siteReviewComments.getId());
				commentsForm.put("userName", fullName);
				commentsForm.put("comment", siteReviewComments.getComment());
				commentsForm.put("dateCreated", Utility.formatDate("dd MMMM, yyyy 'at' hh:mm a", siteReviewComments.getDateCreated()));
				commentList.add(commentsForm);
			}
			
			responseObj.put("obj", commentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}
	
	@Override
	public String getUnreadCommentsCount(long reviewId, long userId) {
		JSONObject responseObj = new JSONObject();
		try {
			int count = siteReviewCommentsDao.getUnreadCommentsCount(reviewId, userId, Status.ACTIVE);			
			responseObj.put("total", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}
	
}
