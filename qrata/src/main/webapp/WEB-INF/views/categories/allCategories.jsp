<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb"
	prefix="b"%>
	<c:import url="../../page.jsp"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestCategoryByNameAndType.htm", "categorySearch", "CATEGORY");
		});
	</script>
</head>
<body>
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box">
				<c:out value="Category has been created successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box">
				<c:out value="The data has been deleted successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box">
				<c:out value="Data has been updated successfully" />
			</div>
		</c:when>
	</c:choose>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Categories</li>
	</ul>

    <form:form cssClass="form-search" modelAttribute="categoryForm" action="allCategories.htm" autocomplete="off">
        <div class="input-append" style="padding-top: 12px; padding-left: 10px;">
            <form:input path="domainSearchVal" cssClass="span2 search-query" placeholder="Enter category name"
                        id="categorySearch" cssStyle="width: 260px;" />
            <button type="submit" class="btn"><i class="icon-search"></i></button>
        </div>
        <div class="back-button">
            <a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
                Go Back </a>
        </div>
    </form:form>

	<div class="center-table">

		<div style="font-size: 12px;">
			<span>&nbsp;</span>
		</div>

		<display:table id="category" name="${categories}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalActiveCategories}" partialList="true" requestURI="allCategories.htm">
			<display:column title="Categories" style="width: 540px;" sortable="true" sortName="name">
				<a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="${ctx}/admin/listSubCategory.htm?id=${category.id}"
					style="color: #000; text-decoration: underline;">${category.name}</a>
				<p>
					<b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="${ctx}/admin/listCategories.htm?id=${category.parentCategory.id}">${category.parentCategory.name}</a>
				</p>
			</display:column>
			<display:column title="Edit">
				<a href="${ctx}/admin/editCategory.htm?id=${category.uuid}&domainId=${category.parentCategory.id}
					&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&ale=1"><i	class="icon-pencil"></i></a>
			</display:column>
			<display:column title="Add Sub Category">
				<a href="addSubCategory.htm?id=${category.id}&domainId=${id}">Add</a>
			</display:column>		
			<display:column title="Delete">
				<a href="deleteCategory.htm?id=${category.id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&ale=1"
					onclick="return confirm('Warning: This deletes all of the data under this Category(Sub Category, Topic, Sub Topic, Content)! Are you sure?')">
					<i class="icon-remove"></i>
				</a>
			</display:column>
		</display:table>

	</div>
</body>
</html>