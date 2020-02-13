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
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestUserByNameOrRole.htm", "expertSearch", "EXPERT");
		});
	</script>
</head>
<body>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Ratings</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Content By Expert</li>
	</ul>

	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>

	<div class="center-table">
		<form:form cssClass="form-search" commandName="userForm" action="getExperts.htm" autocomplete="off">
			<div class="input-append">
				<form:input path="userSearchVal" cssClass="span2 search-query" placeholder="Enter First Name or Last Name or Login or Email" 
					id="expertSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn"><i class="icon-search"></i></button>
			</div>
		</form:form>
		
		<display:table id="expert" name="${experts}" class="displayTable" style="margin-bottom:1%;margin-top:1%;" size = "${totalExpertSize }" partialList="true"
				requestURI="getExperts.htm" pagesize="10" defaultsort="1" sort="external" defaultorder="descending">
			<display:column title="First Name"  sortable="true" sortName="userinfo.firstname" >
				<a onclick="showAccordianAndActiveLink(0, 'expertsLink')" href="${ctx}/admin/expertBio.htm?id=<c:out value='${expert.id}'/>"><c:out value='${expert.firstName}'/></a>
			</display:column>
			<display:column title="Last Name" sortable="true" sortName="userinfo.lastname" >
				<a onclick="showAccordianAndActiveLink(0, 'expertsLink')" href="${ctx}/admin/expertBio.htm?id=<c:out value='${expert.id}'/>"><c:out value='${expert.lastName}'/></a>
			</display:column>
			<display:column title="Login" property="userName" sortable="true" sortName="userName"/>
			<display:column title="">
				<a href="${ctx}/admin/findContentsByExpert.htm?id=${expert.id}"> ${expert.count} Content Ratings</a>
			</display:column>
		</display:table>
				
	</div>
	
</body>
</html>