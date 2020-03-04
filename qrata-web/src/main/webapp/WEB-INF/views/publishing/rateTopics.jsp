<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.insonix.qrata.constants.Constants"%>
<c:set var="EXPERTID" value="<%=Constants.Roles.EXPERT.getValue()%>"></c:set>
<c:set var="USERROLEID" value="${sessionScope.user.role.id}"></c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata - Topics</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestTopicByNameAndTypeForExpert.htm", "topicSearch", "TOPIC");
		});
	</script>
</head>
<body>
<div class="row">
<div class="col-md-12">

	<c:choose>
		<c:when test="${param.success eq '4' }">
			<div class="error-box"><c:out value="Rate the Topic Criteria Importance before adding contents" /></div>
		</c:when>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Ratings has been made successfully" /></div>
		</c:when>
		</c:choose>
<%-- 		<c:when test="${param.success eq '4' }"> --%>
<%-- 			<div class="success-box"><c:out value="" /></div> --%>
<%-- 		</c:when> --%>
<%-- 	</c:choose> --%>
</div>
</div>
<div class="row">
<div class="col-md-12">
<div class="date-text">&nbsp;</div>
</div>
</div>
<div class="row">
<div class="col-md-8 col-8">
	<ul class="shape-design">
		<li>My Rating Data</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Topics</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="expertbox">
	<div class="row">
	
	           <div class="col-md-12">
	           <form:form cssClass="form-search" commandName="topicForm" action="rateTopics_Expert.htm" autocomplete="off">
                  <div class="form-group">
                     <div class="input-group mb-2 serachinputbox">
                        <form:input path="topicSearchVal" cssClass="span2 form-control search-query" placeholder="Enter topic name" 
					id="topicSearch" cssStyle="width: 310px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <button type="submit" class="btn">
                           <i class="fa fa-search"></i>
                           </button>
                           </span>
                        </div>
                     </div>
                  </div>
                  </form:form>
               </div>
	
	</div>

		
	
		<div class="table-responsive">
		<display:table id="topic" name="${topicList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="rateTopics_Expert.htm" partialList="true" size="${totalTopics}">
			<display:column title="Topics" sortable="true" sortName="name" style="width: 430px">
				<c:out value="${topic.name}" />
				<p><b>Root:</b>&#47;&#47;&nbsp;<b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${topic.subCatId}" type="CATEGORY" /></p>
			</display:column>
			<display:column title="Editor" property="editorName" />
			<display:column title="Add New Content">
				<c:if test="${topic.ratingStatus eq true}">
					<a href="#" onclick="javascript:window.location.href='addSite_Expert.htm?topicId=${topic.topicId}&categoryId=${topic.categoryId}&flag=2';">
						Add &#62;&#62; </a>
				</c:if>
			</display:column>
			<display:column title="View Content">
				<a href="${ctx}/admin/findAllContentsByTopic.htm?id=${topic.topicId}&roleId=3">View &#62;&#62;</a>
				</display:column>
			<display:column title="Rate Criteria Importance" sortable="true" sortName="ratings">
				<c:choose>
					<c:when test="${topic.ratingStatus ne true}">
						<a href="#" onclick="javascript:window.location.href='topicCriteriaRatings.htm?id=${topic.topicId}&status=0';">Rate Now &#62;&#62;</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="javascript:window.location.href='topicCriteriaRatings.htm?id=${topic.topicId}&status=1&reviewStatus=1';">View &#62;&#62;</a>	
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table>
		</div>
		
	</div>

</body>
</html>