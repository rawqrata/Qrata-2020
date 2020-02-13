<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="span6">
		<div class="align-right">
			<a href="#" onclick="history.back(); return false;">« Back</a>
		</div>
		<h2 class="q-nav">
			<span class="black">Q/</span><span class="red">Info</span>
		</h2>
		<h3 class="q-nav">Rating Criteria</h3>
	<c:forEach var="criteria" items="${criteria}">

			<h2>${criteria.name}</h2>
			<c:forEach var="srrc" items="${criteria.childRatingCriterias}">
				<c:if
					test="${srrc.parentRatingCriteria.name eq criteria.name}">

					<a id="${srrc.name}"></a>
					<h3>${srrc.name}</h3>
					
						${srrc.description}

				</c:if>
			</c:forEach>

		</c:forEach>
	</div>
</body>
</html>