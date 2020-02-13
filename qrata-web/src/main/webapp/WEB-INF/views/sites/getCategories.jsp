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

</head>
<body>
	<div class="date-text">Loaded in 0.0213 seconds</div>
	<ul class="shape-design">
		<li>Ratings</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Find By Topic</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Categories</li>
	</ul>
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	<div class="breadcrumb-style"><h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" /></h5> </div>
	
	<div class="center-table">
	
		<form:form cssClass="form-search"  commandName="categoryForm" action="getCategories.htm?id=${id}" autocomplete="off">
			<div class="input-append">
				<form:input path="domainSearchVal" cssClass="span2 search-query" placeholder="Enter category name" />
				<button type="submit" class="btn"><i class="icon-search"></i></button>
			</div>
		</form:form>
		
		<display:table id="category" name="${categories}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalActiveCategories}" partialList="true" requestURI="getCategories.htm">
			<display:column title="Category Name" style="width: 540px;" sortable="true" sortName="name">
				<a href="${ctx}/admin/getSubCategories.htm?id=${category.id}">${category.name}</a>
			</display:column>
		</display:table>
	</div>
	
</body>
</html>