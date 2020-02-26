<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="span6">
		<div align="right" class="back-button">
			<a href="javascript:;" onclick="javascript:history.go(-1);">&#60;&#60;
				Go Back </a>
		</div>
	</div>
	<div class="span6" style="width: 978px; margin-left: 0px;">
		<label>${topic.name}</label>
		<display:table id="siteReview" name="${siteReviewList}" pagesize="10" class="displayTable table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom: 1%;margin-top: 1%;width: 100%;" requestURI="findSiteReviewByTopic.htm" partialList="true" size="${totalRatings}">
			<display:column class="heat" style="padding-right: 8px;" headerClass="rating-column">
				<a target="_blank" href="${siteReview.url}"><strong>${siteReview.siteName}</strong></a>
				<br />
				<a href="${ctx}/criterias/siteReviewRatingCriteria.htm?id=${siteReview.id}">Ratings:${siteReview.score}</a>
				<div class="heat-container">
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div class="notch"></div>
					<div style="width:${siteReview.score}%;" class="heat"></div>
				</div>
			</display:column>
			<display:column>
				<c:set var="strLength" value="${fn:length(siteReview.description)}" scope="page" />
				<c:choose>
					<c:when test="${strLength gt 300}">
						<c:out value="${fn:substring(siteReview.description, 0, 300)}" escapeXml="false" />...
					</c:when>
					<c:otherwise>
						<c:out value="${siteReview.description}" escapeXml="false" />...					
					</c:otherwise>
				</c:choose>
				<a style="color: red" href="${ctx}/reviews/findSiteReviewByTopicDetail.htm?id=${siteReview.id}">Learn More Here</a>
			</display:column>
			<display:column headerClass="img-column">
				<c:choose>
					<c:when test="${not empty siteReview.imageName}">
						<img alt="Content Logo" src="${ctx}/sites/getContentImage.htm?id=${siteReview.siteId}" style="width: 120px;height: 90px;" />
					</c:when>
					<c:otherwise>
						<img src="${ctx}/resources/images/column-image.gif" />
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table>
	</div>
</body>
</html>