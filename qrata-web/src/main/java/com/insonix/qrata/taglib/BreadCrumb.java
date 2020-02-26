package com.insonix.qrata.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.dao.CategoryDao;
import com.insonix.qrata.dao.RatingCriteriaDao;
import com.insonix.qrata.dao.SiteDao;
import com.insonix.qrata.dao.TopicDao;
import com.insonix.qrata.models.Category;
import com.insonix.qrata.models.RatingCriteria;
import com.insonix.qrata.models.Site;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;

public class BreadCrumb extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 914795472510074889L;

	private String type;

	private String serialId;

	private String separator;

	@Override
	public int doStartTag() throws JspException {

		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
		HttpSession session = pageContext.getSession();
		
		User user = (User) session.getAttribute("user");
		
		String breadcrumb = "";

		JspWriter out = pageContext.getOut();

		if (type.equalsIgnoreCase("SITE")) {		
			String[] ids = serialId.split("-");
			String siteId = ids[0];
			String topicId = "";
			Topic topic = null;
			if(ids.length == 2) {
				topicId = ids[1];
				TopicDao dao = context.getBean(TopicDao.class);
				topic = dao.get(Integer.parseInt(topicId));
			}
			
			SiteDao dao = context.getBean(SiteDao.class);
			Site site = dao.get(Long.parseLong(siteId));

			if (topic != null) {
				breadcrumb = separator + topic.getName();

				if (topic.getParentTopic() != null) {
					if(user.getRole().getId() == Roles.EXPERT.getValue()){
						breadcrumb = topic.getCategory().getParentCategory().getParentCategory().getName()+separator 
								+topic.getCategory().getParentCategory().getName()+separator 
								+topic.getCategory().getName()+separator
								+topic.getParentTopic().getName()+breadcrumb;
					}else{
						breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+topic.getCategory().getParentCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getParentCategory().getName()+"</a>"
								+ separator 
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+topic.getCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getName()+"</a>"
								+ separator 
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+topic.getCategory().getId()+"'>"+topic.getCategory().getName()+"</a>"
								+ separator
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubTopics.htm?id="+topic.getParentTopic().getId()+"'>"+topic.getParentTopic().getName()+"</a>"
								+ breadcrumb;
					}
				}
				
				if (topic.getParentTopic() == null && topic.getCategory() != null) {
					if(user.getRole().getId() == Roles.EXPERT.getValue()){
						breadcrumb = topic.getCategory().getParentCategory().getParentCategory().getName()+separator 
								+ topic.getCategory().getParentCategory().getName()+separator
								+ topic.getCategory().getName()+ breadcrumb;
					}else{
						breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+topic.getCategory().getParentCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getParentCategory().getName()+"</a>"
								+ separator 
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+topic.getCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getName()+"</a>"
								+ separator
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+topic.getCategory().getId()+"'>"+topic.getCategory().getName()+"</a>"
								+ breadcrumb;
					}
				}
			} else {
				if(user.getRole().getId() == Roles.EXPERT.getValue()){
					breadcrumb = site.getCategory().getParentCategory().getName()+separator +site.getCategory().getName();
				}
				breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+site.getCategory().getParentCategory().getId()+"'>"+site.getCategory().getParentCategory().getName()+"</a>"
						+ separator + "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+site.getCategory().getId()+"'>"+site.getCategory().getName()+"</a>";
			}
		}

		if (type.equalsIgnoreCase("TOPIC")) {
			TopicDao dao = context.getBean(TopicDao.class);
			Topic topic = dao.get(Integer.parseInt(serialId));
			if (topic.getCategory() != null) {
				if(user.getRole().getId() == Roles.EXPERT.getValue()){
					breadcrumb = topic.getCategory().getParentCategory().getParentCategory().getName()+ separator
							+ topic.getCategory().getParentCategory().getName()+ separator
							+ topic.getCategory().getName()+ separator
							+ topic.getName()+ breadcrumb;
				}else{
					breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+topic.getCategory().getParentCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getParentCategory().getName()+"</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+topic.getCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getName()+"</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+topic.getCategory().getId()+"'>"+topic.getCategory().getName()+"</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubTopics.htm?id="+topic.getId()+"'>"+topic.getName()+"</a>"
							+ breadcrumb;
				}
			}
		}


		if (type.equalsIgnoreCase("ASSIGN")) {
			TopicDao dao = context.getBean(TopicDao.class);
			Topic topic = dao.get(Integer.parseInt(serialId));
			if(topic.getParentTopic() != null){
				breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+topic.getParentTopic().getCategory().getParentCategory().getParentCategory().getId()+"'>"+topic.getParentTopic().getCategory().getParentCategory().getParentCategory().getName()+"</a>"
						+ separator
						+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+topic.getParentTopic().getCategory().getParentCategory().getId()+"'>"+topic.getParentTopic().getCategory().getParentCategory().getName()+"</a>"
						+ separator
						+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+topic.getParentTopic().getCategory().getId()+"'>"+topic.getParentTopic().getCategory().getName()+"</a>"
						+ separator
						+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubTopics.htm?id="+topic.getParentTopic().getId()+"'>"+topic.getParentTopic().getName()+"</a>"
						+ separator
						+ topic.getName()
						+ breadcrumb;
			}else{
				breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+topic.getCategory().getParentCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getParentCategory().getName()+"</a>"
						+ separator
						+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+topic.getCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getName()+"</a>"
						+ separator
						+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+topic.getCategory().getId()+"'>"+topic.getCategory().getName()+"</a>"
						+ separator
						+ topic.getName()
						+ breadcrumb;
			}
		}
		
		if (type.equalsIgnoreCase("CATEGORY")) {
			CategoryDao dao = context.getBean(CategoryDao.class);
			Category category = dao.get(Integer.parseInt(serialId));
			if (category.getParentCategory() != null) {		
				if(category.getParentCategory().getParentCategory() != null){
					if(user.getRole().getId() == Roles.EXPERT.getValue()){
						breadcrumb = breadcrumb+category.getParentCategory().getParentCategory().getName()+separator
								+ category.getParentCategory().getName()+separator
								+ category.getName();
					}else{
						breadcrumb = breadcrumb
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+category.getParentCategory().getParentCategory().getId()+"'>"+category.getParentCategory().getParentCategory().getName()+"</a>"+separator
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+category.getParentCategory().getId()+"'>"+category.getParentCategory().getName()+"</a>"+separator
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+category.getId()+"'>"+category.getName()+"</a>";
					}
				}else{
					if(user.getRole().getId() == Roles.EXPERT.getValue()){
						breadcrumb = breadcrumb+category.getParentCategory().getName()+separator
								+ category.getName();
					}else{
						breadcrumb = breadcrumb
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+category.getParentCategory().getId()+"'>"+category.getParentCategory().getName()+"</a>"+separator
								+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategory.htm?id="+category.getId()+"'>"+category.getName()+"</a>";
					}
				}
			} else {
				if(user.getRole().getId() == Roles.EXPERT.getValue()){
					breadcrumb = breadcrumb+category.getName();
				}else{
					breadcrumb = breadcrumb
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+category.getId()+"'>"+category.getName()+"</a>";
				}
			}
		}
		
		if (type.equalsIgnoreCase("CRITERIA")) {
			RatingCriteriaDao dao = context.getBean(RatingCriteriaDao.class);
			RatingCriteria criteria = dao.get(Integer.parseInt(serialId));
			if (criteria.getParentRatingCriteria() == null) {
				breadcrumb = breadcrumb
						+ "<a href='ratingCriteria.htm?id="+criteria.getId()+"'>"+criteria.getName()+"</a>";
			}
		}
		try {
			out.print(breadcrumb);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doStartTag();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
