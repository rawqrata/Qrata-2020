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
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestSiteByName.htm", "siteSearch");
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
	</div></div>
	
	
    <div class="row"><div class="col-md-12"><div class="date-text">&nbsp;</div></div></div>
	<div class="row">
	        <div class="col-md-8 col-8">
			<ul class="shape-design">
				<li>Manage</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li class="none">Content</li>
			</ul>
			</div>
			<div class="col-md-4 col-4">
			<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
			</div>
			</div>
	
	
	
	
	<div class="row">
		  <form:form cssClass="form-search" commandName="siteForm" action="listSites.htm" autocomplete="off">
			<div class="col-md-6 col-6 form-group">
				<form:input path="siteSearchVal" cssClass="span2 search-query form-control" placeholder="Enter content name" 
					id="siteSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		 </form:form>
    	
		<div class="col-md-6 col-6">
			<span><a id="create-user" class="btn btn-info" href="${ctx }/admin/addSite.htm"><i class="fa fa-plus"></i>	Content	</a></span>
		</div>
		
		<div class="table-responsive">
		<display:table id="site" name="${sites}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" requestURI="listSites.htm" defaultsort="1" sort="external" defaultorder="descending" partialList="true" size="${totalSites}">
			<display:column title="Content" sortable="true" sortName="s.name">
				<p><c:out value="${site.name}" /></p> 
				<a href="<c:out value="${site.url}" />" target="_newtab"><c:out value="${site.url}" /></a>
				<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${site.id}-${site.topicId}" separator=">>" type="SITE"/></p>
			</display:column>
			<display:column title="Edit">
				<a href="${ctx}/admin/editSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="fa fa-edit"></i></a>
			</display:column>
			<display:column title="Delete">
				<a href="${ctx}/admin/deleteSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}" 
					onclick="return confirm('Warning: This deletes the content and its reviews permanently! Are you sure?')">
					<i class="fa fa-trash"></i>
				</a>
			</display:column>
		</display:table>
		</div>

	</div>
	
</body>
</html>