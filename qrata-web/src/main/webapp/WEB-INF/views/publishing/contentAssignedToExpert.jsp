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
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestSiteByNameForExpertPublishing.htm", "siteSearch");
		});
	</script>
</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Content has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '4' }">
			<div class="error-box"><c:out value="" /></div>
		</c:when>
		<c:when test="${param.success eq '5' }">
			<div class="success-box"><c:out value="Ratings has been made successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '6' }">
			<div class="error-box"><c:out value="You cannot rate the content unless the Topic concerned is rated. Please rate the Topic first." /></div>
		</c:when>
		<c:when test="${param.success eq '7' }">
			<div class="error-box"><c:out value="You cannot rate the content unless the Sub-Topic concerned is rated. Please rate the Sub-Topic first." /></div>
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
	<c:choose>
		<c:when test="${breadcrumbStatus eq 1 }">
			<ul class="shape-design">
				<li>Manage</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li class="none">Topic</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li class="none">Content</li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="shape-design">
				<li>Expert Rating Data</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li class="none">Content</li>
			</ul>
		</c:otherwise>
	</c:choose>
	</div>
	<div class="col-md-4 col-4">
		<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="expertbox">
	
	<div class="row">
	
	           <div class="col-md-12">
	            <form:form cssClass="form-search" commandName="siteForm" action="getContentAssignedToExpert.htm" autocomplete="off">
                  <div class="form-group">
                     <div class="input-group mb-2 serachinputbox">
                        <form:input path="siteSearchVal" cssClass="span2 form-control search-query" placeholder="Enter content name" 
					id="siteSearch" cssStyle="width: 310px;" />
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

		 
    <div class="table-responsive">
    	<display:table id="site" name="${sites}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="getContentAssignedToExpert.htm" partialList="true" size="${totalSites}">
			<display:column title="Content" sortable="true" sortName="contents" style="width: 400px;">
				<p><c:out value="${site.name}" /></p> 
				<a href="<c:out value="${site.url}" />" target="_newtab"><c:out value="${site.url}" /></a>
				<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${site.id}-${site.topicId}" separator="&nbsp;&#62;&#62;&nbsp;" type="SITE"/></p>
			</display:column>
			<display:column title="Review Status" property="status" />
			<display:column title="Experts" sortable="true" sortName="experts" style="width: 95px;" >
				<a onclick="showAccordianAndActiveLink(0, 'expertsLink')" href="${ctx}/admin/expertBio.htm?id=<c:out value='${site.expertid}'/>"><c:out value=' ${site.expertFirstName}'/>&nbsp;<c:out value=' ${site.expertLastName}'/></a>
			</display:column>
			<display:column title="Rating"> 
				<c:choose>
					<c:when test="${not empty site.topicId and site.status eq 'WAITING APPROVAL' }">
						<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${site.id}&topicId=${site.topicId}&preview=0&type=t">
	<!-- 					<i class="icon-ok"></i> -->View &#62;&#62;
						</a>
					</c:when>
					<c:when test="${not empty site.topicId and site.status eq 'ONLINE' or site.status eq 'NEW' or site.status eq 'REWORK' or site.status eq 'OFFLINE' or site.status eq 'IN PROGRESS' }">
						<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${site.id}&topicId=${site.topicId}&preview=0&view=1&type=t" >
	<!-- 					<i class="icon-ok"></i> -->View &#62;&#62;
						</a>
					</c:when>
				</c:choose>
			</display:column>
			<display:column title="Score">
				<c:if test="${site.score ne 0 and site.status eq 'ONLINE'}">
					${site.score}
				</c:if>
			</display:column>
		</display:table>
		</div>
	</div>
	
</body>
</html>