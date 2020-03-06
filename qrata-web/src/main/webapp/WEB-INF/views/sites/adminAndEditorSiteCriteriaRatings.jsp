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
</script>
</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box">
				<c:out value="Content has been created successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box">
				<c:out value="The data has been deleted successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box">
				<c:out value="Data has been updated successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '4' }">
			<div class="error-box">
				<c:out value="" />
			</div>
		</c:when>
		<c:when test="${param.success eq '5' }">
			<div class="success-box">
				<c:out value="Ratings has been made successfully" />
			</div>
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
		<li class="shape1">&nbsp;</li>
		<c:if test="${type eq 'a'}">
			<li>My Rating Data</li>		
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Content</li>
		</c:if>
		<c:if test="${type eq 'b'}">
			<li>My Rating Data</li>		
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>In Progress Content</li>
		</c:if>
		<c:if test="${type eq 'c'}">
			<li>My Rating Data</li>		
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Published Content</li>
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
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button">
		<a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
			Go Back </a>
	</div>
	</div>
</div>
	<div class="editorbox">
		<div style="float: left; width: 96%; margin-left: 1px;" class="">
			<ul>
				<li style="display:block"><b>Content Name :</b> <c:out value="${site.name}"/></li>
				<li style="display:block"><b>Content URL :</b> <c:out value="${site.url}"/></li>
			</ul>
		</div>
		
		<div>
			<h5>
				<a href="listDomains.htm">Root</a>://
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
										<input type="hidden" name="siteReviewId" value="${siteReview.id}" />
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
						<td><textarea class="ckeditor" cols="10" id="description" name = "description"
								name="description" rows="10"><c:out value="${siteReview.description}" escapeXml="false"/></textarea>
						</td>
					</tr>
					<tr>
						<td>Evaluation&nbsp;<span style="color: red;font-weight:bold;">*</span></td>
					</tr>
					<tr>
						<td><textarea class="ckeditor" cols="10" name="evaluation" id="evaluation"
								name="description" rows="10"><c:out value="${siteReview.evaluation}" escapeXml="false" /></textarea>
						</td>
					</tr>
					<tr>
						<td>Strength&nbsp;<span style="color: red;font-weight:bold;">*</span></td>
					</tr>
					<tr>
						<td><textarea class="ckeditor" cols="10" name="strength" id="strength"
								name="description" rows="10"><c:out value="${siteReview.strength}" escapeXml="false"/></textarea>
						</td>
					</tr>
					<tr>
						<td>Weakness&nbsp;<span style="color: red;font-weight:bold;">*</span></td>
					</tr>
					<tr>
						<td><textarea class="ckeditor" cols="10" name="weakness" id="weakness"
								name="description" rows="10"><c:out value="${siteReview.weakness}" escapeXml="false"  /></textarea>
						</td>
					</tr>
					
				</tbody>
			</table>
			<input type = "hidden" name = "redirect" value = "0" />
			<c:choose>
				<c:when test="${USERROLEID ne EXPERTID}">
					<div style="text-align: center;">
						<input type="submit" name="submitRatings" value="Save" id="btnLogin" /> &nbsp;&nbsp;
						<input type="submit" name="submitRatings" value="Save & Continue" id="btnLogin" /> &nbsp;&nbsp;
						<input type="button" name="submitRatings" value="Publish" id="btnLogin" onclick="validateEvaluationScoreCard(this.value)"/> &nbsp;&nbsp;
						<input type="button" name="submitRatings" value="Print" id="btnLogin" onclick="window.print();"/>
					</div>
				</c:when>
			</c:choose>
		</form>
		
	</div>

</body>
</html>