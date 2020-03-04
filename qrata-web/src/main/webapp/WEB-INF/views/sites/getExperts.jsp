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
		<li class="none">Content By Expert</li>
	</ul>
</div>
<div class="col-md-4 col-4">
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
</div>
	<div class="expertbox">
		<form:form cssClass="form-search" commandName="userForm" action="getExperts.htm" autocomplete="off">
		
					                <div class="row">
			               <div class="col-md-12">
                  <div class=:form-group">
                     <div class="input-group mb-2 serachinputbox">
                    	<form:input path="userSearchVal" cssClass="span2 form-control search-query" placeholder="Enter First Name or Last Name or Login or Email" 
					id="expertSearch" cssStyle="width: 310px;" />
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
			<div class="table-responsive">
		<display:table id="expert" name="${experts}" class="displayTable table table-striped table-hover table-bordered table-condensed" style="margin-bottom:1%;margin-top:1%;" size = "${totalExpertSize }" partialList="true"
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
				
	</div>
	
</body>
</html>