<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Qrata - FAQ</title>
</head>
<body>
<div class="date-text">&nbsp;</div>
<ul class="shape-design">
		<li>My Rating Data</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">FAQs</li>
	</ul>
<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
<div  class="rating-information" style="float: left">
<p>Expert FAQs provide a step-by-step guide to completing the evaluation and rating process. You can also use individual FAQs to jump to questions that will answer specific questions. In addition to these FAQs, you can always call (808-295-9076) or email your Editor ( editor@qrata.com ) for clarification or additional information.</p>
 <br/>
There are four steps in evaluating & rating websites:
<br/>

I. Enter URL addresses and Names of each recommended site.
<br/>
II. Decide Importance of Criteria for each topic you cover.
<br/>
III. Evaluate each recommended site.
<br/>
IV. Send Evaluation of each recommended site to the editor.
</div>
</body>
</html>