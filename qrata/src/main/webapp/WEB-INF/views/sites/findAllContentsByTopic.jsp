<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata- Sites</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestReviewsBySiteName.htm", "reviewSearch", "RATINGSBYTOPIC", "${topic.id}");
		});
	</script>
</head>
<body>
	
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<c:choose>
			<c:when test="${USERROLEID ne EXPERTID}">
				<li>Expert Rating Data</li>
			</c:when>
			<c:otherwise>
				<li>My Rating Data</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${not empty topic.parentTopic.id}">
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Sub Topics</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Content Review</li>
			</c:when>
			<c:otherwise>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Topics</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Content Review</li>		
			</c:otherwise>
		</c:choose>		
	</ul>

	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	
	<div class="center-table">		
		<form:form cssClass="form-search" commandName="siteForm" action="findAllContentsByTopic.htm?id=${topic.id}" autocomplete="off" method="post">
			<div class="input-append">
				<form:input path="siteSearchVal" cssClass="span2 search-query" placeholder="Enter content name"
					id="reviewSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		 </form:form>
			 
		<display:table id="siteReview" name="${siteReviewList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" 
			requestURI="findAllContentsByTopic.htm" partialList="true" size="${totalRatings}">	
			<display:column title="Content" sortable="true" sortName="site.name" style="width: 480px">
				<p><c:out value="${siteReview.siteName}" /></p> 
				<a href="<c:out value="${siteReview.url}" />" target="_newtab"><c:out value="${siteReview.url}" /></a>
				<p><b>
					<c:choose>
						<c:when test="${USERROLEID ne EXPERTID}">
							<a href="listDomains.htm">Root:</a>
						</c:when>
						<c:otherwise>
							Root:
						</c:otherwise>
					</c:choose>
					</b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${siteReview.siteId}-${siteReview.topicId}" separator="&nbsp;&#62;&#62;&nbsp;" type="SITE"/></p>
			</display:column>
			
			<c:if test="${USERROLEID ne EXPERTID}">
				<display:column title="Expert" sortable="true" sortName="info.firstname" style="width: 100px"> 
					<a onclick="showAccordianAndActiveLink(0, 'expertsLink')" href="${ctx}/admin/expertBio.htm?id=<c:out value='${siteReview.userId}'/>"><c:out value='${siteReview.expertFirstName}'/>&nbsp;<c:out value='${siteReview.expertLastName}'/></a>
				</display:column>
			</c:if>
			
			<display:column title="Status" property="reviewStatusName" />
			<display:column title="Rating"> 
				<c:if test="${siteReview.score ne 0 and siteReview.reviewStatusName eq 'ONLINE'}">
					<c:out value="${siteReview.score}" />
				</c:if>
			</display:column>
			
			<display:column title="Preview">
				<c:choose>
					<c:when test="${USERROLEID eq EXPERTID}">
						<c:choose>
							<c:when test="${siteReview.reviewStatusName eq 'ONLINE' or siteReview.reviewStatusName eq 'WAITING APPROVAL' or siteReview.reviewStatusName eq 'OFFLINE'}">
								<c:choose>
									<c:when test="${not empty topic.parentTopic.id}">
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
											&preview=1&view=1&type=est"><i class="icon-eye-open"></i></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
											&preview=1&view=1&type=et"><i class="icon-eye-open"></i></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty topic.parentTopic.id}">
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
											&preview=1&view=11&type=est"><i class="icon-eye-open"></i></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
											&preview=1&view=11&type=et"><i class="icon-eye-open"></i></a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${siteReview.reviewStatusName eq 'ONLINE' or siteReview.reviewStatusName eq 'WAITING APPROVAL' or siteReview.reviewStatusName eq 'OFFLINE'}">
								<c:choose>
									<c:when test="${not empty topic.parentTopic.id}">
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
											&preview=1&view=1&type1=s&type=epst"><i class="icon-eye-open"></i></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
												&preview=1&view=1&type1=s&type=ept"><i class="icon-eye-open"></i></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty topic.parentTopic.id}">
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
											&preview=1&view=11&type1=s&type=epst"><i class="icon-eye-open"></i></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}
											&preview=1&view=11&type1=s&type=ept"><i class="icon-eye-open"></i></a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>								
			</display:column>
			
			
			<c:if test="${USERROLEID eq EXPERTID}">
				<display:column title="Edit">
					<c:if test="${siteReview.reviewStatusName eq 'NEW' or siteReview.reviewStatusName eq 'IN PROGRESS' or siteReview.reviewStatusName eq 'REWORK'}">
						<a href="${ctx}/admin/editSite.htm?id=${siteReview.uuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="icon-pencil"></i></a>
					</c:if>
				</display:column>
				
				<display:column title="Delete">
					<c:if test="${siteReview.reviewStatusName eq 'NEW' or siteReview.reviewStatusName eq 'IN PROGRESS' or siteReview.reviewStatusName eq 'REWORK'}">
							<a href="${ctx}/admin/deleteSite.htm?id=${siteReview.uuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&gcd=y" 
								onclick="return confirm('Warning: This deletes the content and its reviews permanently! Are you sure?')">
								<i class="icon-remove"></i>
							</a>
					</c:if>
				</display:column>
			</c:if>
		</display:table>
				
	</div>
	
</body>
</html>