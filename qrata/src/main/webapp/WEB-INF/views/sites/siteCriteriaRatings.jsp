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
<script type="text/javascript" src="${ctx}/resources/js/sitereview-comments.js"></script>
<script type="text/javascript" src="${ctx}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/resources/ckeditor/adapters/jquery.js"></script>
	
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

		<c:if test="${USERROLEID ne EXPERTID}">
			var ratingArray = document.getElementsByName("ratings");
			for(var i = 0 ; i < ratingArray.length; i++){
				ratingArray[i].disabled = true;
			}
		</c:if>  
		
		<c:if test="${not empty view}">
			var ratingArray = document.getElementsByName("ratings");
			for(var i = 0 ; i < ratingArray.length; i++){
				ratingArray[i].disabled = true;
			}
			
			//$('#description').ckeditorGet().config.readOnly = true;
			CKEDITOR.editorConfig = function( config ){
   				 config.readOnly = true;
			};
			
			CKEDITOR.instances['description'].config.readOnly = true;
			CKEDITOR.instances['evaluation'].config.readOnly = true;
			CKEDITOR.instances['strength'].config.readOnly = true;
			CKEDITOR.instances['weakness'].config.readOnly = true;
		</c:if>
		
		$(".criteriaClass").click(function() {
			getCriteriaDescription($(this).attr("id"));
		});		
	});
	
	function validateEvaluationScoreCard(value){
		var description = CKEDITOR.instances['description'].getData().trim();
		var evaluation = CKEDITOR.instances['evaluation'].getData().trim();
		var strength = CKEDITOR.instances['strength'].getData().trim();
		var weakness = CKEDITOR.instances['weakness'].getData().trim();
		
		if(description != null || evaluation != null || strength != null || weakness != null){
			if(description == "" || evaluation == "" || strength == "" || weakness == ""){
				alert("Please Fill All Mendatory Fields");
				return false;
			}else{
				var ratingArray = document.getElementsByName("ratings");
				for(var i = 0 ; i < ratingArray.length; i++) {
					ratingArray[i].disabled = false;
				}
				var input = $("<input>").attr("type", "hidden").attr("name", "submitRatings").val(value.trim());
				$("#ratingsForm").append(input);
				document.forms["ratingsForm"].submit();
			}
		}
	}
	
	function goOffline() {
		siteReviewId = document.getElementById("siteReviewId").value;
		window.location.href = "contentOffline.htm?siteReviewId="+siteReviewId.trim();
	}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${param.success eq '1'}">
			<div class="success-box">
				<c:out value="Ratings Save Successfull" />
			</div>
		</c:when>
	</c:choose>
    <div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li class="shape1">&nbsp;</li>
		<c:choose>
			<c:when test="${USERROLEID eq EXPERTID}">
				<li>My Rating Data</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<c:if test="${type eq 'et'}">
					<li>Topics</li>
				</c:if>
				<c:if test="${type eq 'est'}">
					<li>Sub Topics</li>
				</c:if>
				
				<c:if test="${type eq 'a'}">
					<li>Content</li>
				</c:if>
				<c:if test="${type eq 'b'}">
					<li>In Progress Content</li>
				</c:if>
				<c:if test="${type eq 'c'}">
					<li>Pending Approval Content</li>
				</c:if>
				<c:if test="${type eq 'd'}">
					<li>Rework Content</li>
				</c:if>
				<c:if test="${type eq 'e'}">
					<li>Published Content</li>
				</c:if>
				
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		        <li>Content Review</li>		     
			</c:when>
			<c:otherwise>
				<c:if test="${type eq 'r' }">
					<li>Ratings</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
					<li>Offline Content</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				</c:if>
				
				<c:if test="${type eq 't' }">
				    <li>Expert Rating Data</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
					<li>Content</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				</c:if>
				<c:if test="${type eq 'u' }">
				    <li>Expert Rating Data</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
					<li>In Progress Content</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				</c:if>
				<c:if test="${type eq 'v' }">
				    <li>Expert Rating Data</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
					<li>Pending Approval Content</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				</c:if>
				<c:if test="${type eq 'w' }">
				    <li>Expert Rating Data</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
					<li>Rework Content</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				</c:if>
				<c:if test="${type eq 'x' }">
				    <li>Expert Rating Data</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
					<li>Published Content</li>
					<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				</c:if>
				
				<li>Content Review</li>		     
			</c:otherwise>
		</c:choose>
		      
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
										
										<input type="hidden" name="criteriaId" value="${criteria.ratingCriteria.id}" />
										<input type="hidden" name="siteId" value="${site.id}" />
										<input type="hidden" name="siteRatingId" value="${criteria.id}" />
										<input type="hidden" name="topicId" value="${param.topicId}" />
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
							<input type="hidden" name="siteReviewId" id="siteReviewId" value="${siteReview.id}" />
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
			<c:if test="${empty view }">
				<c:choose>
					<c:when test="${USERROLEID eq EXPERTID}">
						<div style="text-align: center;">
							<input type="submit" name="submitRatings" value="Save & Edit Later" id="btnLogin" /> &nbsp;&nbsp;
							<input type="submit" name="submitRatings" value="Save & Continue" id="btnLogin" /> &nbsp;&nbsp;
							<input type="button" name="submitRating" value="Submit to Editor" id="btnLogin" onclick="validateEvaluationScoreCard(this.value)"/> &nbsp;&nbsp;
						</div>
					</c:when>
					<c:otherwise>
						<div style="text-align: center;">
							<input type="button" name="submitRatings" value="Publish" id="btnLogin" onclick="validateEvaluationScoreCard(this.value)"/> &nbsp;&nbsp;
							<input type="button" name="submitRatings" value="Rework" id="btnLogin" onclick="validateEvaluationScoreCard(this.value)"/> &nbsp;&nbsp;
						</div>
						
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${not empty view and USERROLEID ne EXPERTID and siteReview.readStatus == 1}">
				<div style="text-align: center;">
					<input type="button" name="submitRatings" value="Click to Offline Content" id="btnLogin" onclick="goOffline()"/>
				</div>
			</c:if>
			<div style="text-align: center;">
				<input type="button" name="submitRatings" value="Print" id="btnLogin" onclick="window.print();"/>
			</div>
		</form>
		
		<div style="float: left;width: 100%">
			&nbsp;
		</div>
		
		
						<!-- Comments Area -->
						
		<c:if test="${siteReview.id != 0}">
						
			<div id="comments-container" class="table-heading" style="float: left;width: 100%;">
				<b>
					<span style="float: left;">
						<%-- <c:choose>
							<c:when test="${not empty siteReview.siteReviewComments}">Comments(${fn:length(siteReview.siteReviewComments)})</c:when>
							<c:otherwise>Comments(0)</c:otherwise>
						</c:choose> --%> Comments
					</span>
					<span id="new-comments-span" style="float: left;margin-left: 30%;display: none;font-weight: lighter;"></span>
					<span style="float: right;">View/Add<img style="padding-left: 4px;cursor: pointer;" src="${ctx}/resources/images/plus-sign.png" id="show-hide"></span>
				</b>
				
				<div id="comments-area" style="float: left;width: 100%;display: none;">
					<div id="comments-view" style="float: left;width: 100%;"> </div>
					<div id="comments-loading" style="float: left;width: 100%;text-align: center;display: none;"> </div>
					
					<div id="comments-input" style="float: left;margin-top: 10px;margin-left: 10px;width: 100%;">
						<div style="float: left;width: 100%;">
							<textarea placeholder="Write something..." style="width: 95%;height: 65px;" id="text-input"></textarea>
						</div>
						<div style="float: right;width: 21%;margin-bottom: 7px;">
							<input type="button" name="comments-button" id="comments-button" value="Save Comment" />
						</div>
					</div>
				</div>
			</div>
		
		</c:if>
		
						<!-- Comments Area -->
		
		<div style="float: left;width: 100%">
			&nbsp;
		</div>
	</div>

</body>
</html>