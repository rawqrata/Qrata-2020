<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="span6 q-nav" style="margin-left:13%; width:48.93617021276595%;">
    <div class="align-right"><a href="#" onclick="history.back(); return false;">Go Back</a></div>
    <h2 class="q-nav"><span class="black">Q/</span><span class="red">Feedback</span></h2>
    <ul class="q-nav">
      <li><a href="${ctx}/feedback.htm?kind=suggestions">Suggestions</a></li>
      <li><a href="${ctx}/feedback.htm?kind=questions">Questions</a></li>
      <li><a href="${ctx}/feedback.htm?kind=problems">Problems</a></li>
      <li><a href="${ctx}/feedback.htm?kind=complaints">Complaints</a></li>
      <li><a href="${ctx}/feedback.htm?kind=brief survey">Brief Survey</a></li>
      <li><a href="${ctx}/feedback.htm?kind=become a reviewer">Become a Reviewer</a></li>
      <li><a href="${ctx}/cashContent.htm">Cash for Better Content</a></li>
    </ul>
  </div>
</body>
</html>