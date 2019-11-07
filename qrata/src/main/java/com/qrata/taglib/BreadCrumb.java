package com.qrata.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qrata.models.Category;
import com.qrata.models.RatingCriteria;
import com.qrata.models.Site;
import com.qrata.models.Topic;
import com.qrata.models.User;
import com.qrata.respository.CategoryRepository;
import com.qrata.respository.RatingCriteriaRepository;
import com.qrata.respository.SiteRepository;
import com.qrata.respository.TopicRepository;

public class BreadCrumb extends TagSupport {
	/**
	 *
	 */
	private static final long serialVersionUID = 914795472510074889L;

	private String type;

	private String serialId;

	private String separator;

	private String breadcrumb;

	private String servletContext;

	private ApplicationContext context;

	private User user = null;

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private RatingCriteriaRepository  ratingrepo;
	

	@Override
	public int doStartTag() throws JspException {

		servletContext = pageContext.getServletContext().getContextPath();
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
	

		breadcrumb = "";
		JspWriter out = pageContext.getOut();

		if (type.equalsIgnoreCase("SITE")) {
			writeSiteCrumb();
		}

		if (type.equalsIgnoreCase("TOPIC")) {
			writeTopicCrumb();
		}

		if (type.equalsIgnoreCase("SUBTOPIC")) {
			writeSubTopicCrumb();
		}

		if (type.equalsIgnoreCase("ASSIGN")) {
			writeAssignCrumb();
		}

		if (type.equalsIgnoreCase("CATEGORY")) {
			writeCategoryCrumb();
		}

		if (type.equalsIgnoreCase("CRITERIA")) {
			writeCriteriaCrumb();
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

	private void writeSiteCrumb() {
		String[] ids = serialId.split("-");
		String siteId = ids[0];
		String topicId;
		Topic topic = null;
		if (ids.length == 2) {
			topicId = ids[1];
			topic=topicRepository.findById(Integer.parseInt(topicId)).get();
		}

		
		Site site = siteRepository.findById(Long.parseLong(siteId)).get();

		if (topic != null) {
			breadcrumb = separator + topic.getName();

			if (topic.getParentTopic() != null) {
				if (user != null && user.isExpert()) {
					breadcrumb = topic.getCategory().getParentCategory().getParentCategory().getName() + separator
							+ topic.getCategory().getParentCategory().getName() + separator
							+ topic.getCategory().getName() + separator
							+ topic.getParentTopic().getName() + breadcrumb;
				} else {
					breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id=" + topic.getCategory().getParentCategory().getParentCategory().getId() + "'>" + topic.getCategory().getParentCategory().getParentCategory().getName() + "</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategories.htm?id=" + topic.getCategory().getParentCategory().getId() + "'>" + topic.getCategory().getParentCategory().getName() + "</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id=" + topic.getCategory().getId() + "'>" + topic.getCategory().getName() + "</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubTopics.htm?id=" + topic.getParentTopic().getId() + "'>" + topic.getParentTopic().getName() + "</a>"
							+ breadcrumb;
				}
			}

			if (topic.getParentTopic() == null && topic.getCategory() != null) {
				if (user != null && user.isExpert()) {
					breadcrumb = topic.getCategory().getParentCategory().getParentCategory().getName() + separator
							+ topic.getCategory().getParentCategory().getName() + separator
							+ topic.getCategory().getName() + breadcrumb;
				} else {
					breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id=" + topic.getCategory().getParentCategory().getParentCategory().getId() + "'>" + topic.getCategory().getParentCategory().getParentCategory().getName() + "</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategories.htm?id=" + topic.getCategory().getParentCategory().getId() + "'>" + topic.getCategory().getParentCategory().getName() + "</a>"
							+ separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id=" + topic.getCategory().getId() + "'>" + topic.getCategory().getName() + "</a>"
							+ breadcrumb;
				}
			}
		} else {
			if (user != null && user.isExpert()) {
				breadcrumb = site.getCategory().getParentCategory().getName() + separator + site.getCategory().getName();
			}
			breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id=" + site.getCategory().getParentCategory().getId() + "'>" + site.getCategory().getParentCategory().getName() + "</a>"
					+ separator + "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategories.htm?id=" + site.getCategory().getId() + "'>" + site.getCategory().getName() + "</a>";
		}
	}

/*	private void writeTopicOrSubTopicCrumb() {

		TopicDao dao = context.getBean(TopicDao.class);
		Topic topic = dao.get(Integer.parseInt(serialId));
		boolean isSubTopic = topic.getParentTopic() != null;
		boolean isExpert = user != null && user.isExpert();

		if (isExpert) {
			breadcrumb = topic.getCategory().getParentCategory().getParentCategory().getName() + separator
					+ topic.getCategory().getParentCategory().getName() + separator
					+ topic.getCategory().getName() + separator
					+ topic.getName() + breadcrumb;
		} else {
			if(isSubTopic){
				writeSubTopicCrumb(topic);
			} else {
				writeTopicCrumb(topic);
			}
		}
	}*/

	private void writeTopicCrumb() {

		Category category = categoryRepository.findById(Integer.parseInt(serialId)).get();

		String level = "/categories";
		String baseUrl = servletContext + level + "/listCategories.htm?id={id}";
		String binding = "onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\"";

		breadcrumb = writeAnchorTag(
				binding,
				baseUrl.replace("{id}", "" +category.getParentCategory().getParentCategory().getId()),
				"Current domain",
				category.getParentCategory().getParentCategory().getName());
		breadcrumb += separator;

		baseUrl = servletContext + level + "/listSubCategories.htm?id={id}";
		breadcrumb += writeAnchorTag(
				binding,
				baseUrl.replace("{id}", "" + category.getParentCategory().getId()),
				"Current category",
				category.getParentCategory().getName());
		breadcrumb += separator;
		breadcrumb += writeSpanTag("Current subcategory", category.getName());

	}

	private void writeSubTopicCrumb() {

		Topic topic = topicRepository.findById(Integer.parseInt(serialId)).get();
		String level = "/categories";
		String baseUrl = servletContext + level + "/listCategories.htm?id={id}";
		String binding = "onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\"";

		breadcrumb = writeAnchorTag(
				binding,
				baseUrl.replace("{id}", "" + topic.getCategory().getParentCategory().getParentCategory().getId()),
				"Current domain",
				topic.getCategory().getParentCategory().getParentCategory().getName());
		breadcrumb += separator;

		baseUrl = servletContext + level + "/listSubCategories.htm?id={id}";
		breadcrumb += writeAnchorTag(
				binding,
				baseUrl.replace("{id}", "" + topic.getCategory().getParentCategory().getId()),
				"Current category",
				topic.getCategory().getParentCategory().getName());
		breadcrumb += separator;

		level = "/topics";
		baseUrl = servletContext + level + "/listTopics.htm?id={id}";
		breadcrumb += writeAnchorTag(
				binding,
				baseUrl.replace("{id}", "" + topic.getCategory().getId()),
				"Current subcategory",
				topic.getCategory().getName()); //subcategory
		breadcrumb += separator;
		breadcrumb += writeSpanTag("Current topic", topic.getName());

	}

	private void writeAssignCrumb(){
		Topic topic = topicRepository.findById(Integer.parseInt(serialId)).get();
		if(topic.getParentTopic() != null){
			breadcrumb = "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+topic.getParentTopic().getCategory().getParentCategory().getParentCategory().getId()+"'>"+topic.getParentTopic().getCategory().getParentCategory().getParentCategory().getName()+"</a>"
					+ separator
					+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategories.htm?id="+topic.getParentTopic().getCategory().getParentCategory().getId()+"'>"+topic.getParentTopic().getCategory().getParentCategory().getName()+"</a>"
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
					+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategories.htm?id="+topic.getCategory().getParentCategory().getId()+"'>"+topic.getCategory().getParentCategory().getName()+"</a>"
					+ separator
					+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+topic.getCategory().getId()+"'>"+topic.getCategory().getName()+"</a>"
					+ separator
					+ topic.getName()
					+ breadcrumb;
		}

	}

	private void writeCategoryCrumb(){
		Category category = categoryRepository.findById(Integer.parseInt("1")).get();

		if (category.getParentCategory() != null) {
			if(category.getParentCategory().getParentCategory() != null){
				if(user != null && user.isExpert()){
					breadcrumb = breadcrumb+category.getParentCategory().getParentCategory().getName()+separator
							+ category.getParentCategory().getName()+separator
							+ category.getName();
				}else{
					breadcrumb = breadcrumb
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+category.getParentCategory().getParentCategory().getId()+"'>"+category.getParentCategory().getParentCategory().getName()+"</a>"+separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategories.htm?id="+category.getParentCategory().getId()+"'>"+category.getParentCategory().getName()+"</a>"+separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listTopics.htm?id="+category.getId()+"'>"+category.getName()+"</a>";
				}
			}else{
				if(user!= null && user.isExpert()){
					breadcrumb = breadcrumb+category.getParentCategory().getName()+separator
							+ category.getName();
				}else{
					breadcrumb = breadcrumb
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+category.getParentCategory().getId()+"'>"+category.getParentCategory().getName()+"</a>"+separator
							+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listSubCategories.htm?id="+category.getId()+"'>"+category.getName()+"</a>";
				}
			}
		} else {
			if(user != null && user.isExpert()){
				breadcrumb = breadcrumb+category.getName();
			}else{
				breadcrumb = breadcrumb
						+ "<a onclick=\"showAccordianAndActiveLink(0, 'domainsLink')\" href='listCategories.htm?id="+category.getId()+"'>"+category.getName()+"</a>";
			}
		}
	}

	private void writeCriteriaCrumb(){
		RatingCriteria criteria = ratingrepo.findById((Integer.parseInt(serialId))).get();
		if (criteria.getParentRatingCriteria() == null) {
			breadcrumb = breadcrumb
					+ "<a href='ratingCriteria.htm?id="+criteria.getId()+"'>"+criteria.getName()+"</a>";
		}
	}

	private String writeAnchorTag(String binding, String href, String title, String text){
		String tag = "<a __$binding__ href='__$href__' title='__$title__'>__$text__</a>";

		if(binding != null) {
			tag = tag.replace("__$binding__", binding);
		}else{
			tag = tag.replace("__binding__", "");
		}

		tag = tag.replace("__$href__", href);
		tag = tag.replace("__$title__", title);
		tag = tag.replace("__$text__", text);

		return  tag;
	}

	private String writeSpanTag(String title, String text){
		String tag = "<span title='__$title__'>__$text__</span>";
		tag = tag.replace("__$title__", title);
		tag = tag.replace("__$text__", text);
		return tag;
	}
}
