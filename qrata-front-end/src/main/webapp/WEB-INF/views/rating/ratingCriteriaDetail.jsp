<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		${siteReview.topicName}
		<div class="well well-small">
			<a target="_blank" href="${siteReview.url}"><b>${siteReview.siteName}</b></a> <br />
			<a href="${ctx}/expert/expertBio.htm?id=${siteReview.expertId}"><b>${siteReview.expertFirstName}
					${siteReview.expertLastName}</b></a>-<a
				href="${ctx}/reviews/findSiteReviewByTopicDetail.htm?id=${siteReview.id}">Full
				Review</a>
		</div>
		<table width="100%" style="text-align: left;">
			<thead>
				<tr>
					<th colspan="1"><a href="${ctx}/criterias/ratingCriteria.htm">Rating Criteria</a></th>
					<th>Score</th>
					<th>Agree(%)</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="parentCriteria"
					items="${siteReview.parentCriteriaWithChildren[0]}">
					<tr>
						<td style="font-style: italic; background-color: #F5F5F5;" colspan="2"><c:out value="${parentCriteria.key}"></c:out></td>
						<td style="color: red bold; background-color: #F5F5F5;"><a class="red bold" href="${ctx}/criterias/ratingCriteriaVote.htm?id=${siteReview.id}">Vote Here</a> </td>						
					</tr>
					<c:forEach var="srrc" items="${parentCriteria.value}">
						<c:if test="${srrc.ratingCriteria.parentRatingCriteria.name eq parentCriteria.key}">
							<tr>
								<td><a href="${ctx}/criterias/ratingCriteria.htm#${srrc.ratingCriteria.name}"><c:out value="${srrc.ratingCriteria.name}"></c:out></a></td>
								<td><c:out value="${srrc.weight * 10}"></c:out></td>
								<td><c:out value="${srrc.votingPercentage}" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>