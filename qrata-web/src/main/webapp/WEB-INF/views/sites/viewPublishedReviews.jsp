<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb"
	prefix="b"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		CKEDITOR.replace( 'description', {
			toolbar : [],
			height: 100,
		});

		CKEDITOR.replace( 'evaluation', {
			toolbar : [],
			height: 100,
		});

		CKEDITOR.replace( 'strength', {
			toolbar : [],
			height: 100,
		});

		CKEDITOR.replace( 'weakness', {
			toolbar : [],
			height: 100,
		});
		
		var ratingArray = document.getElementsByName("ratings");
		for(var i = 0 ; i < ratingArray.length; i++){
			ratingArray[i].disabled = true;
		}
			
		CKEDITOR.editorConfig = function( config ){
				 config.readOnly = true;
		};
		CKEDITOR.instances['description'].config.readOnly = true;
		CKEDITOR.instances['evaluation'].config.readOnly = true;
		CKEDITOR.instances['strength'].config.readOnly = true;
		CKEDITOR.instances['weakness'].config.readOnly = true;
		/*
		document.getElementById("description").readOnly = true;
		document.getElementById("evaluation").readOnly = true;
		document.getElementById("strength").readOnly = true;
		document.getElementById("weakness").readOnly = true;*/
		
		$(".criteriaClass").click(function() {
			getCriteriaDescription($(this).attr("id"));
		});
	});	
	
	function goOffline(){
		siteReviewId = document.getElementById("siteReviewId").value;
		window.location.href = "contentOffline.htm?siteReviewId="+siteReviewId.trim();
	}
</script>
</head>
<body>
    <div class="date-text">&nbsp;</div>
	<ul class="shape-design">		
		<c:if test="${type eq 'c'}">
			<li>Ratings</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>New Ratings</li>
		</c:if>
		<c:if test="${type eq 'e' }">
			<li>Ratings</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Content By Name </li>
		</c:if>
		<c:if test="${type eq 'f' }">
			<li>Ratings</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Content By Topic</li>		
		</c:if>
		<c:if test="${type eq 'g' }">
			<li>Ratings</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Content By Sub Topic</li>
		</c:if>
		<c:if test="${type eq 'ex'}">
			<li>Ratings</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Content By Expert</li>
		</c:if>
		<c:if test="${type eq 'z' }">
			<li>My Rating Data</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Topics</li>
		</c:if>
		<c:if test="${type eq 'o' }">
			<li>My Rating Data</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Sub Topics</li>
		</c:if>
		<c:if test="${type eq 'ept'}">
			<li>Expert Rating Data</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Topics</li>		
		</c:if>
		<c:if test="${type eq 'epst'}">
			<li>Expert Rating Data</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Sub Topics</li>
		</c:if>
	
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Content Review</li>     
	</ul>
	<div class="back-button">
		<a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
			Go Back </a>
	</div>
	
	<div class="center-table">
		<div style="float: left; width: 96%; margin-left: 1px;" class="">
			<ul>
				<li style="display:block"><b>Content Name :</b> <c:out value="${site.name}"/></li>
				<li style="display:block"><b>Content URL :</b> <c:out value="${site.url}"/></li>
			</ul>
		</div>
		
		<div>
			<h5>
				<c:choose>
					<c:when test="${USERROLEID ne EXPERTID}">
						<a href="listDomains.htm">Root://</a>
					</c:when>
					<c:otherwise>
						Root://
					</c:otherwise>
				</c:choose>
				<b:breadcrumb serialId="${site.id}-${param.topicId}" separator="/"
					type="SITE" />
			</h5>
		</div>
		<div class="rating-information">
			<ul>
				<li style="display:block">Criteria importance is a number indicating how important a specific criterion in rating contents</li>
				<li style="margin-left:15px">1 (one) means a specific criterion is <u>not important</u> to content rated in your topic(s).</li>
				<li style="margin-left:15px">10 (ten) means a specific criterion is <u>extremely important</u> to content rated in your topic(s)</li>
			</ul>
		</div>
		<form class="none" action="saveSiteCriteriaRatings.htm" method="post" id="ratingsForm" name="ratingsForm">
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
												<select name="ratings" style="width: 109px;" id="ratings">
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
										
										<input type="hidden" name="siteReviewId" id="siteReviewId" value="${siteReview.id}" />
									</c:if>
								</c:forEach>
							</table>
						</td>
					</c:forEach>
				</tr>
			</table>
			
			<table width="100%"
				class="table table-striped table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th width="100%" class="table-heading">Evaluation Score Card</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>One Line Description&nbsp;<span style="color: red;font-weight:bold;">*</span></td>
					</tr>
					<tr>
						<td>
							
							<input type="hidden" name="context" id="context" value="${ctx}" />
							
							<textarea class="ckeditor" cols="10" id="description" name = "description"
								rows="10"><c:out value="${siteReview.description}" /></textarea>
								
						</td>
					</tr>
					<tr>
						<td>Evaluation&nbsp;<span style="color: red;font-weight:bold;">*</span></td>
					</tr>
					<tr>
						<td>
						<textarea class="ckeditor" cols="10" name="evaluation" id="evaluation"
								rows="10"><c:out value="${siteReview.evaluation}" /></textarea>
						</td>
					</tr>
					<tr>
						<td>Strength&nbsp;<span style="color: red;font-weight:bold;">*</span></td>
					</tr>
					<tr>
						<td>
							<textarea class="ckeditor" cols="10" name="strength" id="strength"
								rows="10"><c:out value="${siteReview.strength}" /></textarea>
						</td>
					</tr>
					<tr>
						<td>Weakness&nbsp;<span style="color: red;font-weight:bold;">*</span></td>
					</tr>
					<tr>
						<td>
							<textarea class="ckeditor" cols="10" name="weakness" id="weakness"
								rows="10"><c:out value="${siteReview.weakness}" /></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="text-align: center;">
				<input type="button" name="submitRatings" value="Print" id="btnLogin" onclick="window.print();"/>
				<input type="button" name="submitRatings" value="Click to Offline Content" id="btnLogin" onclick="goOffline()"/>
			</div>
		</form>
		
		<div style="float: left;width: 100%">
			&nbsp;
		</div>
	</div>
</body>
</html>