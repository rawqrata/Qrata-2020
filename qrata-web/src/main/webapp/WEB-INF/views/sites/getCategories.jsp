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
<div class="row">
<div class="col-md-12">
	<div class="date-text">Loaded in 0.0213 seconds</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-8 col-8">
	<ul class="shape-design">
		<li>Ratings</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Find By Topic</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Categories</li>
	</ul>
	</div>
	<div class="col-md-4 col-md-4">
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12"><h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" /></h5> </div>
	</div>
	
	<div class="catbox">
	
		<form:form cssClass="form-search"  commandName="categoryForm" action="getCategories.htm?id=${id}" autocomplete="off">
		<div class="row">
				   <div class="col-md-12">
                  <div class="form-group">
                     <div class="input-group mb-2 serachinputbox">
                    	<form:input path="domainSearchVal" cssClass="span2 search-query" placeholder="Enter category name" />
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
		
		<display:table id="category" name="${categories}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalActiveCategories}" partialList="true" requestURI="getCategories.htm">
			<display:column title="Category Name" style="width: 540px;" sortable="true" sortName="name">
				<a href="${ctx}/admin/getSubCategories.htm?id=${category.id}">${category.name}</a>
			</display:column>
		</display:table>
	</div>
	
</body>
</html>