<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="span6" style="width: 48.9362%%;">
<div align="right" class="back-button"><a href="javascript:;" onclick="javascript:history.go(-1);">&#60;&#60; Go Back </a></div>
<display:table id="subTopic" name="${subTopicList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" size="${totalSubActiveTopics}" partialList="true" requestURI="listSubTopics.htm">
			<display:column style="width: 540px;" title="Sub Topics">
				<a href="${ctx}/reviews/findSiteReviewByTopic.htm?id=${subTopic.topicId}">${subTopic.name}</a>
			</display:column>
		</display:table>
		</div>
</body>
</html>