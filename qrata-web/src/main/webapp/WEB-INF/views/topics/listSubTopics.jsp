<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestTopicByNameAndType.htm", "subTopicSearch", "SUBTOPIC", "${id}");
		});
	</script>
</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Sub Topic has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
	</c:choose>
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
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none">Sub-Topics</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12"><h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="TOPIC" /></h5></div>
	</div>
	
	<div class="topicbox">
		<form:form cssClass="form-search" commandName="topicForm" action="listSubTopics.htm?id=${id}" autocomplete="off">
		
		<div class="row">
				            <div class="col-md-12">
                  <div class=:form-group">
                     <div class="input-group mb-1 serachinputbox">
                      <form:input path="topicSearchVal" cssClass="span2 form-control search-query" placeholder="Enter sub topic name" 
					id="subTopicSearch" cssStyle="width: 310px;" />
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
		
	<span>To add a new Sub Topics to this list, <a href="addSubTopic.htm?id=${id}&type=c">click here..</a></span>
	<br />
	<display:table id="topic" name="${topicList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
		style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalSubActiveTopics}" partialList="true" requestURI="listSubTopics.htm" >
		<display:column title="Sub Topics" property="name" style="width: 400px;" sortable="true" sortName="name"/>
		
		<c:choose>
			<c:when test="${topic.applicableAssignment eq false and topic.expertId eq 0}">
				<display:column title="Expert">
					<a href="#" onclick="javascript:window.location.href='topicExpert.htm?id=${topic.topicId}';"> Not Applicable &#62;&#62;</a>
				</display:column>
			</c:when>
			<c:when test="${topic.assignStatus eq false}">
				<display:column title="Expert">
					<a href="#" onclick="javascript:window.location.href='topicExpert.htm?id=${topic.topicId}';"> Assign &#62;&#62;</a>
				</display:column>
			</c:when>
			<c:when test="${topic.assignStatus eq true}">
				<display:column title="Expert">
					<a href="${ctx}/admin/expertBio.htm?id=<c:out value='${topic.expertId }'/>">Assigned &#62;&#62;</a>
				</display:column>
			</c:when>
			<c:otherwise>
				<display:column title="Expert" />
			</c:otherwise>
		</c:choose>
		
		<display:column title="View Content">
				<a href="${ctx}/admin/findAllContentsByTopic.htm?id=${topic.topicId}">View &#62;&#62;</a>
		</display:column>
		
		<display:column title="Edit">
			<a href="${ctx}/admin/editSubTopic.htm?id=${topic.uuid}&subCatId=${id}
				&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="fa fa-edit"></i></a>
		</display:column>
		<display:column title="Delete">
			<a href="deleteSubTopics.htm?id=${topic.topicId}&topicId=${id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}" 
				onclick="return confirm('Warning: This deletes the data from everywhere! Are you sureThis deletes all of the data under this Sub Topic (Content)! Are you sure?')">
				 <i class="fa fa-trash"></i>
			</a>
		</display:column>
	</display:table>
	</div>
</body>
</html>