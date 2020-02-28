<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box">
				<c:out value="Criteria has been created successfully" />
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
		<li class="shape1">&nbsp;</li>
		<li>Criteria Mgmt</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Criteria Group</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Criteria List</li>
	</ul>
	<div class="back-button">
		<a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
			Go Back </a>
	</div>
	<div class="breadcrumb-style"><h5>Root//<b:breadcrumb separator="/" serialId="${id}" type="CRITERIA" /></h5></div>
	<div class="center-table">
		<span>
			To add a new Criteria to this list, <a href="addCriteria.htm?id=${id}">click here..</a> &nbsp;
		</span>
		
		<div class="table-responsive">
		<table width="100%"
			class="displayTable table table-striped table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<th width="30%" class="table-heading">Criteria Name</th>
					<th width="10%" class="table-heading">Edit</th>
					<th width="10%" class="table-heading">Delete</th>
					<th width="10%" class="table-heading">Preview</th>

				</tr>

			</thead>
			<tbody>
				<c:forEach var="criteria" items="${ratingCriteria}">
					<tr>
						<td>${criteria.name}</td>
						<td class="yes"><a
							href="${ctx}/admin/editCriteria.htm?id=${criteria.id}"><i
								class="fa fa-edit"></i></a></td>
 						<td class="yes"><a 
 							href="deleteCriteria.htm?id=${criteria.id}&categoryId=${criteria.parentRatingCriteria.id}" 
 							onclick="return confirm('Warning: This deletes the data from everywhere! are you sure ?')"><i
 								class="fa fa-trash"></i></a></td>
						<td><a
							href="${ctx}/admin/previewCriteriaCategory.htm?id=<c:out value='${criteria.id }'/>"><i
								class="fa fa-eye"></i></a></td>


					</tr>
				</c:forEach>


			</tbody>
		</table>
		</div>

	</div>
</body>
</html>