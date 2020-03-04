<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb"
	prefix="b"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	$(document).ready(function() {
		<c:if test="${reviewStatus eq 1}">
			var ratingArray = document.getElementsByName("weight");
			for(var i = 0 ; i < ratingArray.length; i++) {
				ratingArray[i].disabled = true;
			}
		</c:if>  
		
		$(".criteriaClass").click(function() {
			getCriteriaDescription($(this).attr("id"));
		});
	});
</script>
</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Ratings has been made successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '4' }">
			<div class="success-box"><c:out value="" /></div>
		</c:when>
	</c:choose>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
	<div class="date-text">&nbsp;</div>
	</div>
	</div>
	
	<div class="row">
	<div class="col-md-8 col-8">
	<ul class="shape-design">
				<c:choose>
			<c:when test="${USERROLEID eq EXPERTID}">
				<li>My Rating Data</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Topics</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Criteria Importance</li>
			</c:when>
			<c:otherwise>
				<li>Expert Rating Data</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Topics</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Content Review</li>
			</c:otherwise>
		</c:choose>
		
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button">
		<a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
			Go Back </a>
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
		<c:choose>
			<c:when test="${USERROLEID ne EXPERTID }">
				<h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${topic.category.id}" type="CATEGORY" /></h5>
			</c:when>
			<c:otherwise>
				<h5>Root://<b:breadcrumb separator="/" serialId="${topic.category.id}" type="CATEGORY" /></h5>	
			</c:otherwise>
		</c:choose>
	</div>
	</div>
	<div class="row no-gutters" >
	<div class="col-md-12 rating-information">
		<ul>
			<li style="display:block">Topic Criteria importance is a number indicating how important a specific criterion in rating contents your topic(s)</li>
			<li style="margin-left:15px">1 (one) means a specific criterion is <u>not important</u> to content rated in your topic(s).</li>
			<li style="margin-left:15px">10 (ten) means a specific criterion is <u>extremely important</u> to content rated in your topic(s)</li>
			
		</ul>
		</div>
	</div>
	
	<div class="criteriabox">
		<form action="saveTopicCriteriaRatings.htm" method="post">
			<table width="100%">
				<tr>
					<c:forEach var="parentCriteria" items="${parentCriteriaList}">
						<td>
							<table width="100%" class="table table-striped table-hover table-bordered table-condensed">
								<tr>
									<th colspan="2" style="text-align: center;">
										<c:out value="${parentCriteria.name}" />
									</th>
								</tr>
								
								<c:forEach var="criteria" items="${criteriaList}">
									<c:if test="${criteria.ratingCriteria.parentRatingCriteria.id eq parentCriteria.id}">
										<tr>
											<td>
												<p>
													<a style="cursor: pointer;" class="criteriaClass" id="${criteria.ratingCriteria.id}">
														<c:out value="${criteria.ratingCriteria.name}" />
													</a>
												</p>
											</td>
											<td>
												<select name="weight" style="width: 100px;">
													<c:if test="${criteria.weight == 0 }">
														<c:forEach var="i" begin="1" end="10" step="1">
															<option value="${i}">${i}</option>
														</c:forEach>
													</c:if>
													<c:if test="${criteria.weight != 0 }">
														<c:forEach var="i" begin="1" end="10" step="1"
															varStatus="count">
															<c:choose>
																<c:when test="${count.index == criteria.weight}">
																	<option value="${i}" selected>${i}</option>
																</c:when>
				
																<c:otherwise>
																	<option value="${i}">${i}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</c:if>
												</select>
											</td>
										</tr>
							
										<input type="hidden" name="criteriaId" value="${criteria.ratingCriteria.id}" />
										<input type="hidden" name="topicWeightId" value="${criteria.id}" />
										<input type="hidden" name="topicId" value="${topic.id}" />
									</c:if>
								</c:forEach>
								
							</table>
						</td>
					</c:forEach>
				</tr>
			</table>
			<input type="hidden" name="rdv" value="${rdv}">
			<c:if test="${reviewStatus ne 1 }">
				<div style="text-align: center;">
					<input type="submit" name="submit" value="Save" id="btnLogin">
				</div>
			</c:if>
		</form>
		
	</div>

</body>
</html>