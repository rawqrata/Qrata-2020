<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata- Sites</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			var rStatus = null;
			<c:choose>
				<c:when test="${status eq '1'}">
					rStatus = "ONLINE";
				</c:when>
				<c:when test="${status eq '2'}">
					rStatus = "APPROVEL";
				</c:when>
				<c:when test="${status eq '3'}">
					rStatus = "INPROGRESS";
				</c:when>
				<c:when test="${status eq '4'}">
					rStatus = "REVISE";
				</c:when>
			</c:choose>
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestSiteByNameNReviewStatusForMyPublishing.htm", 
					"reviewSearch", rStatus);
		});
	</script>
</head>
<body>
<div class="row">
<div class="col-md-12">
<div class="date-text">&nbsp;</div>
</div>
</div>
<div class="row">
<div class="col-md-8 col-8">
	<ul class="shape-design">
		<li>My Rating Data</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<c:choose>
			<c:when test="${status eq '1' }">
				<li class="none">Published Content</li>
				<c:set var="url" value="getReviewsByStatus.htm?reviewByStatus=${status}" />
			</c:when>
			<c:when test="${status eq '2' }">
				<li class="none">Pending Approval Content</li>
				<c:set var="url" value="getReviewsByStatus.htm?reviewByStatus=${status}" />
			</c:when>
			<c:when test="${status eq '3' }">
				<li class="none">In Progress Content</li>
				<c:set var="url" value="getReviewsByStatus.htm?reviewByStatus=${status}" />
			</c:when>
			<c:when test="${status eq '4' }">
				<li class="none">Rework Content</li>
				<c:set var="url" value="getReviewsByStatus.htm?reviewByStatus=${status}" />
			</c:when>
		</c:choose>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>

	
	<div class="reviewbox">
	<div class="row">
	           <div class="col-md-12">
	             <form:form cssClass="form-search" commandName="siteForm" action="${url}" autocomplete="off">
                  <div class="form-group">
                     <div class="input-group mb-2 serachinputbox">
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
                   </form:form>
               </div>
	</div>

	</div>

		
		<display:table id="siteReview" name="${siteReviewList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="getReviewsByStatus.htm" partialList="true" size="${totalRatings}">
			<display:column title="Content" sortable="true">
				<p><c:out value="${siteReview.siteName}" /></p> 
				<a href="<c:out value="${siteReview.url}" />" target="_newtab"><c:out value="${siteReview.url}" /></a>
				<p><b>
					<c:choose>
						<c:when test="${USERROLEID ne EXPERTID}">
							<a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a>
						</c:when>
						<c:otherwise>
							Root:
						</c:otherwise>
					</c:choose>
				</b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${siteReview.siteId}-${siteReview.topicId}" separator="&nbsp;&#62;&#62;&nbsp;" type="SITE"/></p>
			</display:column>
			<display:column title="Status" property="reviewStatusName" />
			<display:column title="Rating"> 
				<c:choose>
					<c:when test="${not empty siteReview.topicId}">
						<c:choose>
							<c:when test="${USERROLEID ne EXPERTID}">
								<c:if test="${status eq '3'}">
									<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=${status}&type=b">
										View &#62;&#62;
									</a>
								</c:if>
								<c:if test="${status eq '1'}">
									<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=${status}&type=c">
										View &#62;&#62;
									</a>
								</c:if>
							</c:when>
							
							<c:otherwise>
								<c:if test="${status eq '1'}">
									<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=${status}&type=e">
										View &#62;&#62;
									</a>
								</c:if>
								<c:if test="${status eq '2'}">
									<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=${status}&type=c">
										View &#62;&#62;
									</a>
								</c:if>
								<c:if test="${status eq '3'}">
									<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=${status}&type=b">
										View &#62;&#62;
									</a>
								</c:if>
								<c:if test="${status eq '4'}">
									<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&preview=1&view=${status}&type=d">
										View &#62;&#62;
									</a>
								</c:if>
							</c:otherwise>
						</c:choose>				
					</c:when>
				</c:choose>
			</display:column>
			
			<c:choose>
				<c:when test="${USERROLEID eq EXPERTID}">
					<c:if test="${status eq '3' or status eq '4'}">
						<display:column title="Delete">
							<a href="${ctx}/admin/deleteSite.htm?id=${siteReview.uuid}" onclick="return confirm('Warning: This deletes the content and its reviews permanently! Are you sure?')">
								<i class="icon-remove"></i>
							</a>
						</display:column>
					</c:if>
				</c:when>
				<c:otherwise>
					<display:column title="Delete">
						<a href="${ctx}/admin/deleteSite.htm?id=${siteReview.uuid}" onclick="return confirm('Warning: This deletes the content and its reviews permanently! Are you sure?')">
							<i class="icon-remove"></i>
						</a>
					</display:column>
				</c:otherwise>
			</c:choose>
			
			<display:column title="Score">
				<c:if test="${siteReview.score ne 0 and status eq '1'}">
					${siteReview.score}
				</c:if>
			</display:column>
		</display:table>
	
	
</body>
</html>