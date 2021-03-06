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
<title>Qrata - Topics</title>

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestTopicByNameAndType.htm", "subTopicSearch", "SUBTOPIC");
		});
	</script>
</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Topic has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '4' }">
			<div class="error-box "><c:out value="Not available for assignment as Contents are already added by an Editor or Admin" /></div>
		</c:when>
		<c:when test="${param.success eq '5' }">
			<div class="success-box"><c:out value="Ratings has been made successfully" /></div>
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
	<div class="topicbox mt-2">
		<form:form cssClass="form-search" commandName="topicForm" action="allSubTopics.htm" autocomplete="off">
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
		
		
		
		<display:table id="subTopic" name="${subTopicList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" requestURI="allSubTopics.htm" defaultsort="1" sort="external" defaultorder="descending" partialList="true" size="${totalSubTopics}">
			
			<display:column title="Sub Topics" style="width: 460px" sortable="true" sortName="t.name">
				<c:out value="${subTopic.name}" />
	            <p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${subTopic.parentTopicId}" type="TOPIC" /></p>
			</display:column>
			
			<c:choose>
				<c:when test="${subTopic.applicableAssignment eq false and subTopic.expertId eq 0}">
					<display:column title="Expert">
						<a href="#" onclick="javascript:window.location.href='topicExpert.htm?id=${subTopic.topicId}';"> Not Applicable &#62;&#62;</a>
					</display:column>
				</c:when>
				<c:when test="${subTopic.assignStatus eq false and subTopic.applicableAssignment eq true}">
					<display:column title="Expert">
						<a href="#" onclick="javascript:window.location.href='topicExpert.htm?id=${subTopic.topicId}';"> Assign &#62;&#62;</a>
					</display:column>
				</c:when>
				<c:when test="${subTopic.assignStatus eq true}">
					<display:column title="Expert">
						<a onclick="showAccordianAndActiveLink(0, 'expertsLink')" href="${ctx}/admin/expertBio.htm?id=<c:out value='${subTopic.expertId }'/>">Assigned &#62;&#62;</a>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column title="Expert" />
				</c:otherwise>
			</c:choose>
			<display:column title="View Content">
				<a href="${ctx}/admin/findAllContentsByTopic.htm?id=${subTopic.topicId}"><i class="fa fa-eye"></i></a>
			</display:column>
			
			<display:column title="Edit">
				<a href="${ctx}/admin/editSubTopic.htm?id=${subTopic.uuid}&subCatId=${subTopic.parentTopicId}
					&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&ale=2"><i class="fa fa-edit"></i></a>
			</display:column>
			<display:column title="Delete">
				<a href="deleteSubTopics.htm?id=${subTopic.topicId}&topicId=${subTopic.parentTopicId}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&ale=1" 
					onclick="return confirm('Warning: This deletes all of the data under this Sub Topic (Content)! Are you sure?')">
					   <i class="fa fa-trash"></i>
				</a>
			</display:column>
		</display:table>
	</div>

</body>
</html>