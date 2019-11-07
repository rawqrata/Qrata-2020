<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:import url="../../page.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>

    <body>

        <div class="col-md-offset-1 col-md-10 gray">

            <div class="col-md-offset-1 col-md-10">

                <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;Go Back</a></div>

                <div class="content-list">

                    <c:forEach var="criteria" items="${criteria}">

                        <h2>${criteria.name}</h2>

                        <c:forEach var="srrc" items="${criteria.childRatingCriterias}">

                            <c:if test="${srrc.parentRatingCriteria.name eq criteria.name}">

                                <h3><a href="${srrc.bookmark}">${srrc.name}</a></h3>

                                ${srrc.description}

                            </c:if>

                        </c:forEach>

                    </c:forEach>

                </div>

            </div>

        </div>

    </body>

</html>