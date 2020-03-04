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
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Criteria List</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;Go Back </a></div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12"><h5>Root//<b:breadcrumb separator="/" serialId="${id}" type="CRITERIA" /></h5></div>
	
		</div>
		<div class="row">
		<div class="col-md-12">
		<div class="table-responsive">
			<table width="100%"	class="table table-striped table-hover table-bordered table-condensed">			
			<thead>
				<tr>
				
<!-- 				    <th width="15%" class="table-heading">Ordering</th> -->
					<th width="50%" class="table-heading">Criteria Name</th>
					<th width="50%" class="table-heading">Description</th>
				</tr>

			</thead>
			<tbody>
			<tr>
<%-- 			<td>${priority }</td> --%>
			
			<td>${name }</td>
			
			 <td>${description}</td>
			 
			 </tr>
			</tbody>
		</table>      
        </div>
        </div>
        </div>
</body>
</html>