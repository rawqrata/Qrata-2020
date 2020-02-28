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
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${USERROLEID ne EXPERTID}">
			<c:set var="url" value="findContentsByTopic" />
		</c:when>
		<c:otherwise>
			<c:set var="url" value="findAllContentsByTopic" />
		</c:otherwise>
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
			<c:when test="${not empty topic.parentTopic.id}">
				<li>My Rating Data</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Sub Topics</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Content Review</li>
			</c:when>
			<c:otherwise>
			   <li>My Rating Data</li>
		       <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Topics</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		        <li>Content Review</li>		
			</c:otherwise>
		</c:choose>
		
	</ul>
</div>
<div class="col-md-4 col-4">
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="alltopicbox">		
		<form:form cssClass="form-search" commandName="siteForm" action="findAllContentsByTopic.htm?id=${topic.id}" autocomplete="off" method="post">
		<div class="row">
		
				            <div class="col-md-12">
                  <div class=:form-group">
                     <div class="input-group mb-1 serachinputbox">
                     <form:input path="siteSearchVal" cssClass="span2 form-control search-query" placeholder="Enter content name"
					id="reviewSearch" cssStyle="width: 310px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <button type="submit" class="btn">
                           <i class="fa fa-search"></i>
                           </button>
                           </span>
                        </div>
                     </div>
                  </div>
               </div>
		
		
		</div>
	
		 </form:form>
			 
		<display:table id="siteReview" name="${siteReviewList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="findContentsByTopic.htm" partialList="true" size="${totalRatings}">	
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
			
			<display:column title="Status" property="reviewStatusName" />
			<display:column title="Rating"> 
				<c:if test="${siteReview.score ne 0 and siteReview.reviewStatusName eq 'ONLINE'}">
					<c:out value="${siteReview.score}" />
				</c:if>
			</display:column>
			<display:column title="Preview">
			<c:choose>
			<c:when test="${not empty topic.parentTopic.id}">
			<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=11&type=o"><i class="icon-eye-open"></i></a>
			</c:when>
			<c:otherwise>
			<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=11&type=z"><i class="icon-eye-open"></i></a>
			</c:otherwise>
			</c:choose>
				
			</display:column>
		</display:table>
				
	</div>
	
</body>
</html>