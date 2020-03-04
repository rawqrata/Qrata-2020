<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box">
				<c:out value="Criteria Category has been created successfully" />
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
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
	<div class="date-text">&nbsp;</div>
	</div>
</div>
	<div class="row">
	<div class="col-md-8 col-8">
		<ul class="shape-design">
		<li>Criteria Mgmt</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Criteria Group</li>
		</ul>
		</div>
		<div class="col-md-4 col-4">
		<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
		</div>

</div>
	<div class="row">
	<div class="col-md-12"><h5>Root//</h5></div>
	</div>
	<div class="row">
  <div class="col-md-12">
		<%-- <form:form cssClass="form-search" commandName="ratingCriteriaForm"
			action="searchCriteriaCategory.htm" autocomplete="off">
			<div class="input-append">
				<form:input path="criteriaSearchVal" cssClass="span2 search-query"
					placeholder="Enter criteria group name" />
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</div>
		</form:form> --%>

		<span>
 			To add a new Criteria Group to this list, <a href="addCriteriaCategory.htm">click here..</a>&nbsp;
		</span>
		
		<div class="table-responsive">
		<table width="100%"
			class="displayTable table table-striped table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<th width="80%" class="table-heading">Criteria Group Name</th>
					<th width="10%" class="table-heading">Edit</th>
<!-- 					<th width="10%" class="table-heading">Delete</th> -->

				</tr>

			</thead>
			<tbody>
				<c:forEach var="criteria" items="${criteria}">
					<tr>
						<td><a
							href="${ctx}/admin/ratingCriteria.htm?id=${criteria.id}">${criteria.name}</a></td>
 						<td class="yes"><a 
 							href="${ctx}/admin/editCriteriaCategory.htm?id=${criteria.id}"><i 
 								class="fa fa-edit"></i></a></td>
<!-- 						<td class="yes"><a -->
<%-- 							href="deleteCriteriaCategory.htm?id=${criteria.id}" --%>
<!-- 							onclick="return confirm('Warning: This deletes the data from everywhere! are you sure ?')"><i -->
<!-- 								class="icon-remove"></i></a></td> -->


					</tr>
				</c:forEach>


			</tbody>
		</table>
		</div>
</div>
	</div>
</body>
</html>