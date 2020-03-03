<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata- Sites</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestTopicByNameAndType.htm", "topicSearch", "TOPIC");
		});
	</script>
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
		<li>Ratings</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li >Content By Topic</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Topics</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="topicbox">
		<form:form cssClass="form-search" commandName="topicForm" action="getTopics.htm" autocomplete="off">
		
		
				                <div class="row">
			               <div class="col-md-12">
                  <div class=:form-group">
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
               </div>
               </div>
				</form:form>
		
		<display:table id="topic" name="${topicList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
			style="margin-bottom:1%;margin-top:1%;" requestURI="getTopics.htm" defaultsort="1" sort="external" defaultorder="descending"  partialList="true" size="${totalTopics}">
			
			<display:column title="Topics" style="width: 570px" sortable="true" sortName="name">
				<c:out value="${topic.name}" />
	        	<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${topic.categoryId}" type="CATEGORY" /></p>
			</display:column>
		
			<display:column title="View Content Ratings">
				<a href="${ctx}/admin/findContentsByTopic.htm?id=${topic.topicId}"> ${topic.count} Content Ratings</a>
			</display:column>
		</display:table>
				
	</div>
	
</body>
</html>