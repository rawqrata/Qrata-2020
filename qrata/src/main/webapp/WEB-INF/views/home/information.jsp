<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:import url="../../page.jsp"/>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>

    <body>

        <div class="col-md-offset-1 col-md-10 gray">

            <div class="col-md-offset-1 col-md-10">

                <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;">&laquo;&nbsp;Go Back</a></div>

                <h2 class="q-nav"><span class="black">Q/</span><span class="red">Info</span></h2>

                <ul class="list-group list faq">
                    <li class="list-group-item" ><a href="${ctx}/faq.htm">? FAQs</a></li>
                    <li class="list-group-item"><a href="${ctx}/expert/listExperts.htm">Experts</a></li>
                    <li class="list-group-item"><a href="${ctx}/criterias/ratingCriteria.htm">Rating Criteria</a></li>
                    <li class="list-group-item"><a  href="${ctx}/legal.htm">Legal</a></li>
                </ul>

            </div>

        </div>

    </body>

</html>