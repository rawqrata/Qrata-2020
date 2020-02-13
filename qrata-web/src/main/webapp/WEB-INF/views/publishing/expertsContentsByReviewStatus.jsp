<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata- Sites</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			var rStatus = null;
			<c:choose>
				<c:when test="${status eq '1'}">
					rStatus = "ONLINE";
				</c:when>
				<c:when test="${status eq '2'}">
					rStatus = "APPROVEL";
				</c:when>
				<c:when test="${status eq '3'}">
					rStatus = "INPROGRESS";
				</c:when>
				<c:when test="${status eq '4'}">
					rStatus = "REVISE";
				</c:when>
			</c:choose>
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestSiteByNameNReviewStatusForExpertPublishing.htm", 
					"reviewSearch", rStatus);
		});
	</script>
</head>
<body>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Expert Rating Data</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<c:choose>
			<c:when test="${status eq '1' }">
				<li class="none">Published Content</li>
				<c:set var="url" value="getExpertsContentsByReviewStatus.htm?reviewByStatus=${status}" />
			</c:when>
			<c:when test="${status eq '2' }">
				<li class="none">Pending Approval Content</li>
				<c:set var="url" value="getExpertsContentsByReviewStatus.htm?reviewByStatus=${status}" />
			</c:when>
			<c:when test="${status eq '3' }">
				<li class="none">In Progress Content</li>
				<c:set var="url" value="getExpertsContentsByReviewStatus.htm?reviewByStatus=${status}" />
			</c:when>
			<c:when test="${status eq '4' }">
				<li class="none">Rework Content</li>
				<c:set var="url" value="getExpertsContentsByReviewStatus.htm?reviewByStatus=${status}" />
			</c:when>
		</c:choose>
	</ul>

	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	<div class="center-table">
		  <form:form cssClass="form-search" commandName="siteForm" action="${url}" autocomplete="off">
			<div class="input-append">
				<form:input path="siteSearchVal" cssClass="span2 search-query" placeholder="Enter content name" 
					id="reviewSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		 </form:form>
	</div>

	<div class="center-table">
		
		<display:table id="siteReviewForm" name="${siteReviewList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="getExpertsContentsByReviewStatus.htm" partialList="true" size="${totalRatings}">
			<display:column title="Content" sortable="true" sortName="site.name">
				<p><c:out value="${siteReviewForm.siteName}" /></p> 
				<a href="<c:out value="${siteReviewForm.url}" />" target="_newtab"><c:out value="${siteReviewForm.url}" /></a>
				<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${siteReviewForm.siteId}-${siteReviewForm.topicId}" separator="&nbsp;&#62;&#62;&nbsp;" type="SITE"/></p>
			</display:column>
			<display:column title="Experts" sortable="true" sortName="userinfo.firstname"> 
				<a onclick="showAccordianAndActiveLink(0, 'expertsLink')" href="${ctx}/admin/expertBio.htm?id=<c:out value='${siteReviewForm.expertId}'/>"><c:out value=' ${siteReviewForm.expertFirstName}'/>&nbsp;
				<c:out value='${siteReviewForm.expertLastName}'/></a>
			</display:column>
			<display:column title="Status" property="reviewStatusName"/>
			<display:column title="Rating"> 
				<c:choose>
					<c:when test="${not empty siteReviewForm.topicId and siteReviewForm.reviewStatusName eq 'WAITING APPROVAL'}">
						<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReviewForm.siteId}&topicId=${siteReviewForm.topicId}&preview=0&type=v" >
	<!-- 					<i class="icon-ok"></i> -->View &#62;&#62;
						</a>
					</c:when>
					<c:when test="${not empty siteReviewForm.topicId and siteReviewForm.reviewStatusName eq 'REWORK' }">
						<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReviewForm.siteId}&topicId=${siteReviewForm.topicId}&preview=0&view=1&type=w" >
	<!-- 					<i class="icon-ok"></i> -->View &#62;&#62;
						</a>
					</c:when>
					<c:when test="${not empty siteReviewForm.topicId and siteReviewForm.reviewStatusName eq 'IN PROGRESS' }" >
					<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReviewForm.siteId}&topicId=${siteReviewForm.topicId}&preview=0&view=1&type=u" >
	<!-- 					<i class="icon-ok"></i> -->View &#62;&#62;
						</a>
						</c:when>
						<c:when test="${not empty siteReviewForm.topicId and siteReviewForm.reviewStatusName eq 'ONLINE' }" >
					<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReviewForm.siteId}&topicId=${siteReviewForm.topicId}&preview=0&view=1&type=x" >
	<!-- 					<i class="icon-ok"></i> -->View &#62;&#62;
						</a>
						</c:when>
							
						
				</c:choose>
			</display:column>
			<display:column title="Score">
				<c:if test="${siteReviewForm.score ne 0 and status eq '1' }">
					${siteReviewForm.score}
				</c:if>
			</display:column>
		</display:table>
	</div>
	
</body>
</html>