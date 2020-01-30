<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.qrata.enums.Constants"%>
<c:import url="../../page.jsp"/>
<c:set var="EXPERTID" value="<%=Constants.Roles.EXPERT.getValue()%>"></c:set>
<c:set var="USERROLEID" value="${sessionScope.user.role.id}"></c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata - Topics</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestTopicByNameAndTypeForMyPublishing.htm", "topicSearch", "TOPIC");
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
		<c:when test="${param.success eq '4' }">
			<div class="error-box"><c:out value="Rate the Topic Criteria Importance before adding contents" /></div>
		</c:when>
		<c:when test="${param.success eq '5' }">
			<div class="success-box"><c:out value="Ratings has been made successfully" /></div>
		</c:when>
	</c:choose>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>My Rating Data</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Topics</li>
	</ul>
	
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	
	<div class="center-table">
		<form:form cssClass="form-search" modelAttribute="topicForm" action="rateTopics_Editor.htm" autocomplete="off">
			<div class="input-append">
				<form:input path="topicSearchVal" cssClass="span2 search-query" placeholder="Enter topic name" 
					id="topicSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		</form:form>
		
		<div style="font-size: 12px;"> 
			&nbsp;
		</div>
		
		<display:table id="topic" name="${topicList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="rateTopics_Editor.htm" partialList="true" size="${totalTopics}">
			<display:column title="Topics" style="width: 460px" sortable="true" sortName="t.name">
				<p><c:out value="${topic.name}" /></p>
	        	<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${topic.subCatId}" type="CATEGORY" /></p>
			</display:column>			
			
			<display:column title="Add New Content">
				<c:if test="${topic.ratingStatus eq true}">
						<a href="#" onclick="javascript:window.location.href='addSite_Expert.htm?topicId=${topic.topicId}&categoryId=${topic.subCatId}&flag=1';">
						Add &#62;&#62; </a>		
				</c:if>
			</display:column>
		
			<display:column title="View Content" >
				<c:if test="${topic.sites eq true}">
					<a href="${ctx}/admin/allContentsByTopic.htm?id=${topic.topicId}">View &#62;&#62;</a>
				</c:if>
			</display:column>
			
			<display:column title="Rate Criteria Importance" sortable="true" sortName="ratings">
				<c:choose>
					<c:when test="${topic.ratingStatus ne true}">
						<a href="#" onclick="javascript:window.location.href='topicCriteriaRatings.htm?id=${topic.topicId}&status=0';">Rate Now &#62;&#62;</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="javascript:window.location.href='topicCriteriaRatings.htm?id=${topic.topicId}&status=1';">View &#62;&#62;</a>
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table>
		
	</div>

</body>
</html>