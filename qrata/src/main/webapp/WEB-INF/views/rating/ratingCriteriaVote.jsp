<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../page.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
		
function validate(){
	var chx = document.getElementsByTagName('input');
	for (var i=0; i<chx.length; i++) {
		if (chx[i].type == 'radio' && chx[i].checked) {
  			return confirm("Are you sure this is your final vote?");
  		} 
	 }
	alert("Check atleast one");
	return false;
}
</script>
</head>
<body>
<div class="col-md-offset-1 col-md-10 no-padding gray">
    <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;Go Back</a></div>
		${siteReview.topicName}
		<div class="well well-small">
			<a href="${siteReview.url}"><b>${siteReview.siteName}</b></a> <br />
			<a href="${ctx}/expert/expertBio.htm?id=${siteReview.expertId}"><b>${siteReview.expertFirstName}
					${siteReview.expertLastName}</b></a>-<a
				href="${ctx}/reviews/findSiteReviewByTopicDetail.htm?id=${siteReview.id}">Full
				Review</a>
		</div>
		<form method="POST" name="form" id="form"  onsubmit="return validate()" action="${ctx}/criterias/saveRatingCriteriaUserVote.htm"  >
		<input type="hidden" name="id" value="${siteReview.id}">
			<table width="100%" style="text-align: left;">
				<thead>
				
					<tr>
						<th colspan="1"><a href="${ctx}/criterias/ratingCriteria.htm">Rating
								Criteria</a></th>
						<th>Score</th>
						<th>Agree</th>
						<th>Disagree</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="parentCriteria"
						items="${siteReview.parentCriteriaWithChildren[0]}">
						<tr>
							<td style="font-style: italic; background-color: #F5F5F5;" colspan="4"><c:out value="${parentCriteria.key}"></c:out></td>
						</tr>
						<c:forEach var="srrc" items="${parentCriteria.value}">
						<c:if test="${srrc.ratingCriteria.parentRatingCriteria.name eq parentCriteria.key}">

							<tr>
								<td><c:out value="${srrc.ratingCriteria.name}"></c:out></td>
								<td><c:out value="${srrc.weight*10}"></c:out></td>
								<td><input type="radio" id="radio${srrc.ratingCriteria.id}" name="radio${srrc.id}" value="1"></td>
								<td><input type="radio" id="radio${srrc.ratingCriteria.id}" name="radio${srrc.id}" value="0"></td>
							</tr>
							</c:if>
							<input type="hidden" name="srrcId" value="${srrc.id}" />
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
			<br/>
			<div align="right">
				<input type="submit" value="Submit Now!" />
			</div>
		</form>
	</div>
</body>
</html>