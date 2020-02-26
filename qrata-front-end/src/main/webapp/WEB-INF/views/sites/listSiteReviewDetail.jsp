

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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
	</div>
	<div class="span6" style="width: 978px; margin-left: 0px;">

		<h3>${siteReview.topicName}</h3>
		<div style="float: left; margin-left: 17px; width: 79%;">
			<div style="width: 40%; float: left;">
				<a target="_blank" href="${siteReview.url}"><strong>${siteReview.siteName}</strong></a>

			</div>
			<div style="width: 16%; float: right;">

				<a href="${ctx}/expert/expertBio.htm?id=${siteReview.expertId}"><b>${siteReview.expertFirstName}
						${siteReview.expertLastName}</b></a> <br /> <a
					href="${ctx}/criterias/siteReviewRatingCriteria.htm?id=${siteReview.id}"><b>Rating:${siteReview.score}</b></a><br />
				<a href="${ctx}/criterias/ratingCriteria.htm"><b>Scoring
						Info</b></a>
			</div>
			<br />
			<div style="float: left; margin-top: 14px;">
				<h3>Description:</h3>
				${siteReview.description}

				<h3>Evaluation:</h3>
				${siteReview.evaluation}

				<h3>Strength:</h3>
				${siteReview.strength}

				<h3>Weakness:</h3>
				${siteReview.weakness}
			</div>
		</div>
		<div style="float: left; margin-left: 30px;">
			<c:choose>
				<c:when test="${not empty siteReview.imageName}">
					<a target="_blank" href="${siteReview.url}"><strong><img
							alt="Content Logo"
							src="${ctx}/sites/getContentImage.htm?id=${siteReview.siteId}"
							style="width: 120px; height: 90px;" /></strong></a>
				</c:when>
				<c:otherwise>
					<a target="_blank" href="${siteReview.url}"><strong><img
							src="${ctx}/resources/images/column-image.gif" /></strong></a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
</html>