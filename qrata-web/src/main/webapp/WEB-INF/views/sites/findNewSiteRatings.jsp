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
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestReviewsBySiteName.htm", "reviewSearch", "NEWRATINGS");
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
		<li>Ratings</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">New Ratings</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
</div>
	

	<div class="ratingbox">
		<form:form cssClass="form-search" commandName="siteForm" action="findNewSiteRatings.htm" autocomplete="off" method="post">
		                <div class="row">
			               <div class="col-md-12">
                  <div class=:form-group">
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
               </div>
               </div>

		</form:form>
		
		<display:table id="siteReview" name="${siteReviewList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="findNewSiteRatings.htm" partialList="true" size="${totalRatings}">
			<display:column title="Content" sortable="true" sortName="site.name" style="width: 480px">
				<p><c:out value="${siteReview.siteName}" /></p> 
				<a href="<c:out value="${siteReview.url}" />" target="_newtab"><c:out value="${siteReview.url}" /></a>
				<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${siteReview.siteId}-${siteReview.topicId}" separator="&nbsp;&#62;&#62;&nbsp;" type="SITE"/></p>
			</display:column>
			<display:column title="Expert" sortable="true" sortName="userinfo.firstname" style="width: 100px"> 
				<a onclick="showAccordianAndActiveLink(0, 'expertsLink')" href="${ctx}/admin/expertBio.htm?id=<c:out value='${siteReview.userId}'/>"><c:out value='${siteReview.expertFirstName}'/>&nbsp;<c:out value='${siteReview.expertLastName}'/></a>
			</display:column>
			<display:column title="Status" property="reviewStatusName" />
			<display:column title="Rating" property="score"> </display:column>
			<display:column title="Preview">
				<a href="${ctx}/admin/unreadSiteRatings.htm?siteId=${siteReview.siteId}&topicId=${siteReview.topicId}&checked=2&sitereviewId=${siteReview.id}"><i class="icon-eye-open"></i></a>
				
			</display:column>
		</display:table>
	</div>
	
</body>
</html>