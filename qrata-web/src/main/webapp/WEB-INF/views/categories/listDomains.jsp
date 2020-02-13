<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestCategoryByNameAndType.htm", "domainSearch", "DOMAIN");
		});
	</script>
</head>
<body>
	
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Domain has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
	</c:choose>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Domains</li>
	</ul>
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton();">&#60;&#60; Go Back </a></div>
	<div class="breadcrumb-style"> <h5><a href="listDomains.htm">Root</a>://</h5> </div>
	<div class="center-table">
		<form:form cssClass="form-search" commandName="categoryForm" action="listDomains.htm">
			<div class="input-append">
				<form:input path="domainSearchVal" cssClass="span2 search-query" placeholder="Enter domain name" 
					id="domainSearch" cssStyle="width: 310px;" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		</form:form>
	
		<span> To add a new Domain to this list, <a href="addDomain.htm">click here..</a></span> <br />
				
		<display:table id="domain" name="${domains}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom:1%;margin-top:1%;" requestURI="listDomains.htm" defaultsort="1" sort="external" defaultorder="descending"
				partialList="true"  size="${totalActiveDomains}">
				
			<display:column title="Domain Name" style="width: 540px;" sortable="true" sortName="name">
				<a href="${ctx}/admin/listCategories.htm?id=${domain.id}">${domain.name}</a>
			</display:column>
			<display:column title="Edit">
				<a href="editDomain.htm?id=${domain.id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="icon-pencil"></i></a>
			</display:column>
			<display:column title="Add Category">
				<a href="addCategory.htm?id=${domain.id}">Add</a>
			</display:column>
			<display:column title="Delete">
				<a href="deleteDomain.htm?id=${domain.id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}" 
					onclick="return confirm('Warning: This deletes all of the data under this domain (Category, Sub Category, Topic, Sub Topic, Content)! Are you sure?')">
					<i class="icon-remove"></i>
				</a>
			</display:column>
		</display:table>
			
		
	</div>
	
</body>
</html>
	