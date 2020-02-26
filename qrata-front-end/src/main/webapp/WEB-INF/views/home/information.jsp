<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <div class="span6">
    <div class="align-right"><a href="#" onclick="history.back(); return false;">Go Back</a></div>
    <h2 class="q-nav"><span class="black">Q/</span><span class="red">Info</span></h2>
    <ul>
    <li><a href="${ctx}/faq.htm"><b>?FAQ</b></a></li>
    <li><a href="${ctx}/expert/listExperts.htm"><b>Experts</b></a></li>
    <li><a href="${ctx}/criterias/ratingCriteria.htm"><b>Rating Criteria</b></a></li>
    <li><a  href="${ctx}/legal.htm"><b>Legal</b></a></li>
    
    </ul>
    
    </div>
</body>
</html>