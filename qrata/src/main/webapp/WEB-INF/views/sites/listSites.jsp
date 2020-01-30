<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:import url="../../page.jsp"/>
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
    <div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Content</li>
	</ul>
    <form:form cssClass="form-search" modelAttribute="siteForm" action="listSites.htm" autocomplete="off">
        <div class="input-append" style="padding-top: 12px; padding-left: 10px;">
            <form:input path="siteSearchVal" cssClass="span2 search-query" placeholder="Enter content name" id="siteSearch" cssStyle="width: 260px;" />
            <button type="submit" class="btn">
                <i class="icon-search"></i>
            </button>
        </div>
        <div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
    </form:form>
	<div class="center-table">

		<div style="float: right; font-size: 12px; margin-left: 25px;">
			<span>
				<button onclick="location.href='${ctx}/admin/addSite.htm'" type="button">
        Add Content</button>
<%--                 <a id="create-user" href="${ctx }/admin/addSite.htm">Add Content<img style="padding-left: 4px;" src="${ctx }/resources/images/plus-sign.png"></a>
 --%>            </span>
		</div>
		
		<display:table id="site" name="${sites}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" requestURI="listSites.htm" defaultsort="1" sort="external" defaultorder="descending" partialList="true" size="${totalSites}">
			<display:column title="Content" sortable="true" sortName="s.name">
				<p><c:out value="${site.name}" /></p> 
				<a href="<c:out value="${site.url}" />" target="_newtab"><c:out value="${site.url}" /></a>
<%-- 				<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${site.id}-${site.topicId}" separator=">>" type="SITE"/></p>
 --%>			</display:column>
			<display:column title="Edit">
				<a href="${ctx}/admin/editSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="icon-pencil"></i></a>
			</display:column>
			<display:column title="Delete">
				<a href="${ctx}/admin/deleteSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}" 
					onclick="return confirm('Warning: This deletes the content and its reviews permanently! Are you sure?')">
					<i class="icon-remove"></i>
				</a>
			</display:column>
		</display:table>

	</div>
	
</body>
</html>