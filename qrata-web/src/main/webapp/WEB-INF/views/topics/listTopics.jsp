<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestTopicByNameAndType.htm", "topicSearch", "TOPIC", "${id}");
		});
	</script>
</head>
<body>
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Topic has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
		<c:when test="${param.success eq 'error' }">
			<div class="success-box"><c:out value="Topic Already Assigned or Topic have Contents or Ratings" /></div>
		</c:when>
	</c:choose>

	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Topics</li>
	</ul>
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	<br /><div class="breadcrumb-style"><h5>Root//<b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" /></h5></div>
	<div class="center-table">
		<form:form cssClass="form-search" commandName="topicForm" action="listTopics.htm?id=${id}" autocomplete="off">
			<div class="input-append">
				<form:input path="topicSearchVal" cssClass="span2 search-query" placeholder="Enter topic name" 
					id="topicSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		</form:form>
		
		<span>To add a new Topic to this list, <a
			href="addTopic.htm?id=${id}&type=c">click here..</a></span>
		<br />
		<display:table id="topic" name="${topicList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalActiveTopics}" 
			partialList="true" requestURI="listTopics.htm">
			
			<c:choose>
				<c:when test="${topic.childStatus eq true}">
					<display:column title="Topic Name" style="width: 400px;" sortable="true" sortName="name">
						<a href="listSubTopics.htm?id=${topic.topicId}">${topic.name}</a>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column title="Topic Name" style="width: 400px;" sortable="true" sortName="name">${topic.name}</display:column>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${topic.applicableAssignment eq false and topic.expertId eq 0}">
					<display:column title="Expert">
						<a href="#" onclick="javascript:window.location.href='topicExpert.htm?id=${topic.topicId}';"> Not Applicable &#62;&#62;</a>
					</display:column>
				</c:when>
				<c:when test="${topic.childStatus eq false and topic.assignStatus eq false}">
					<display:column title="Expert">
						<a href="#" onclick="javascript:window.location.href='topicExpert.htm?id=${topic.topicId}';"> Assign &#62;&#62;</a>
					</display:column>
				</c:when>
				<c:when test="${topic.assignStatus eq true and topic.childStatus eq false}">
					<display:column title="Expert">
						<a href="${ctx}/admin/expertBio.htm?id=<c:out value='${topic.expertId }'/>">Assigned &#62;&#62;</a>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column title="Expert" />
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${topic.childStatus eq false}">
					<display:column title="View Content">
						<a href="${ctx}/admin/allContentsByTopic.htm?id=${topic.topicId}">View &#62;&#62;</a>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column title="View Content" />
				</c:otherwise>
			</c:choose>
			
			<display:column title="Edit">
				<a href="${ctx}/admin/editTopic.htm?id=${topic.uuid}&subCatId=${id}
					&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="icon-pencil"></i></a>
			</display:column>
			
			<c:choose>
				<c:when test="${topic.leafNode eq false}">
					<display:column title="Add Sub Topic">
						<a href="addSubTopic.htm?id=${topic.topicId}&subCatId=${id}">Add</a>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column title="Add Sub Topic" />
				</c:otherwise>
			</c:choose>

			<display:column title="Delete">
				<a href="deleteTopics.htm?id=${topic.topicId}&SubCategoryId=${id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}" 
					onclick="return confirm('Warning:  This deletes all of the data under this Topic (Sub Topic, Content)! Are you sure?')">
					<i	class="icon-remove"></i>
				</a>
			</display:column>
		</display:table>
	</div>
</body>
</html>