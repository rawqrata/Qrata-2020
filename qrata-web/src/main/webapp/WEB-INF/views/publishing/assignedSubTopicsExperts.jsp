<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestTopicByNameAndTypeForExpertPublishing.htm", "subTopicSearch", "SUBTOPIC");
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
	</c:choose>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Expert Rating Data</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Sub Topics</li>
	</ul>
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	<div class="center-table">
		<form:form cssClass="form-search" commandName="topicForm" action="getAssignedSubTopicsToExperts.htm" autocomplete="off">
			<div class="input-append">
				<form:input path="topicSearchVal" cssClass="span2 search-query" placeholder="Enter sub topic name" 
					id="subTopicSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		</form:form>
		
		<div style="font-size: 12px;"> 
			&nbsp;
		</div>
	</div>
	<div class="center-table">
	
		<br />
		<display:table id="subTopicExpertAssignment" name="${subTopicExpertAssignments}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" style="margin-bottom:1%;margin-top:1%;" 
			requestURI="getAssignedSubTopicsToExperts.htm" defaultsort="1" sort="external" defaultorder="descending" partialList="true" size="${totalSubTopicExpertAssignment}" >
			<display:column title="SubTopic Name" sortable="true" sortName="topic.name">
				${subTopicExpertAssignment.name}
				 <p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${subTopicExpertAssignment.parentTopicId}" type="TOPIC" /></p>
			</display:column>
			<display:column title="Ratings">
				<c:choose>
					<c:when test="${subTopicExpertAssignment.ratingStatus eq true }">
						<a href="#" onclick="javascript:window.location.href='subTopicCriteriaRatings.htm?id=${subTopicExpertAssignment.topicId}&rdv=est';">View &#62;&#62;</a>
					</c:when>
					<c:otherwise>
						<c:out value="Not Yet Rated" />
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="View Content">
				<a href="${ctx}/admin/findAllContentsByTopic.htm?id=${subTopicExpertAssignment.topicId}&roleId=3">View &#62;&#62;</a>
			</display:column>
			<display:column title="Experts" sortable="true" sortName="userinfo.firstname">
				<a href="${ctx}/admin/expertBio.htm?id=<c:out value='${subTopicExpertAssignment.expertId}'/>"><c:out value='${subTopicExpertAssignment.expertFirstName}'/>&nbsp;<c:out value=' ${subTopicExpertAssignment.expertLastName}'/></a>
			</display:column>
		</display:table>
	</div>
</body>
</html>