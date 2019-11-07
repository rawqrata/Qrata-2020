<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:import url="../../page.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>

    <body>

        <div class="col-md-offset-1 col-md-10 no-padding gray">

            <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;Go Back</a></div>

            <div class="col-md-offset-1 col-md-10">

                <h2>${form.firstName} ${form.lastName}</h2>

                <c:choose>
                    <c:when test="${not empty form.imageName}">
                        <img style="float: right;" alt="Expert Image" src="${ctx}/expert/getUserImage.htm?userId=${form.id}" />
                    </c:when>
                    <c:otherwise>
                        <img style="float: right;" alt="Expert Image" src="${ctx}/resources/images/default-image.png" />
                    </c:otherwise>
                </c:choose>

                <c:set var="strLength" value="${fn:length(form.bio)}" scope="page" />

                <div >
                    <c:out value="${fn:substring(form.bio, 0, 285)}" escapeXml="false" />
                </div>

                <div><c:out value="${fn:substring(form.bio, 285, strLength)}" escapeXml="false" /></div>

                <%-- 		<p>${form.bio}</p> --%>

            </div>

        </div>

    </body>

</html>