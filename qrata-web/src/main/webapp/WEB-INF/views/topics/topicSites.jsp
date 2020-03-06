



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb" prefix="b" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<script type="text/javascript">
		
		$(document).ready(function() {
			$('#message').hide();
			//$("input[type=button]").attr("disabled", "disabled");
		});
		
		var flag = false;
		function assignSites() {
			flag = true;
			$("input[type=button]").removeAttr("disabled");
			$('#availableSites option:selected').each(function() {
			    $(this).remove().appendTo("#assignedSites");
			});
			/*
			var option =  $('#availableExpert option:selected');
			$('#availableExpert option:selected').remove();
			$('#assignedExpert').append('<option value='+option.val()+'>'+option.text()+'</option>');*/
		}
		
		function unAssignSites() {
			flag = true;
			//alert(flag);
			$("input[type=button]").removeAttr("disabled");
			$('#assignedSites option:selected').each(function() {
			    $(this).remove().appendTo("#availableSites");
			});
			/*
			var option =  $('#assignedExpert option:selected');
			$('#assignedExpert option:selected').remove();
			$('#availableExpert').append('<option value='+option.val()+'>'+option.text()+'</option>');*/
		}
		
		function submitSiteTopic(){
			var optionValues = [];
			$('#assignedSites option').each(function() {
			    optionValues.push($(this).val());
			});
			//alert(optionValues);
			if(flag){
				var input;
				if(optionValues.length != 0){
					input = $("<input>").attr("type", "hidden").attr("name", "sitesId").val(optionValues);	
				}else{
					input = $("<input>").attr("type", "hidden").attr("name", "sitesId").val("empty,");
				}
				$('#saveForm').append(input);
				document.forms["saveForm"].submit();
			}
		}
	</script>
</head>
<body>
		<c:choose>
			<c:when test="${param.success eq '3' }">
				<div class="success-box"><c:out value="Assignment has been done successfully" /></div>
			</c:when>
			<c:when test="${param.success eq '5' }">
				<div class="success-box"><c:out value="All DeAssignment has been done successfully" /></div>
			</c:when>
		</c:choose>
		<div id="message" class="success-box"></div>
			<div class="date-text">Loaded in 0.0213 seconds</div>
			<c:choose>
		<c:when test="${not empty topic.parentTopic.id}">
			<ul class="shape-design">
				<li class="shape1">&nbsp;</li>
				<li>Sub-Topic Assign</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Contents</li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="shape-design">
				<li class="shape1">&nbsp;</li>
				<li>Topic Assign</li>
				<li>&#62;&#62;</li>
				<li>Contents</li>
			</ul>
		</c:otherwise>
	</c:choose>
			<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
			<c:choose>
				<c:when test="${not empty topicSite.parentTopic.id }">
					<div class="breadcrumb-style"><h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${topic.parentTopic.id}" type="TOPIC" /></h5></div>
				</c:when>
				<c:otherwise>
					<div class="breadcrumb-style"><h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${topic.category.id}" type="CATEGORY" /></h5></div>
				</c:otherwise>
			</c:choose>
		<div class="center-table">	
		<form action="assignedSiteTopic.htm" name="saveForm" id="saveForm">
		<table width="100%"
			class="table table-striped table-hover table-bordered table-condensed">
			<thead>
				<tr>					
					<th colspan="2"  class="table-heading">Available Content</th>
					<th width="48%" class="table-heading">Assigned Content</th>
				</tr>
			</thead>
					
			<tbody>
				<tr>
					<td width="47%" rowspan="3" align="center">
						<div style="float: left;" id="availableDiv">
							<select name="availableSites" id="availableSites" multiple="multiple" style="height: 200px;">
								<c:forEach var="site" items="${availableSites}">
									<option value="${site.id}">${site.name} (${site.url})</option>
								</c:forEach>
							</select>	
						</div>					
					</td>
				
					
	               <td width="5%"> <a href="javascript:;" onclick="assignSites();"><i class="icon-arrow-right"></i></a></td>
	               
					<td width="48%" rowspan="3" align="center">
						<div style="float: left;" id="assignedDiv">
							<select name="assignedSites" id="assignedSites" multiple="multiple" style="height: 200px;">
								<c:forEach var="site" items="${assignedSites}">
									<option value="${site.id}">${site.name} (${site.url})</option>
								</c:forEach>
							</select>
						</div>
				 </td>
			  </tr>
			  
			  <tr>
				<td>&nbsp; <input type="hidden" name="topicId" id="topicId" value="${topic.id}" /> </td>
              </tr>
				<tr>
					<td><a href="javascript:;" onclick="unAssignSites();"><i class="icon-arrow-left"></i></a></td>
            	</tr>			  
        	</tbody>
		</table>
			<div style="text-align: center;">
				<input type="button" name="btn" value="Submit" id="btnSubmit" onclick="submitSiteTopic();" >
			</div>
		</form>
	</div>
</body>
</html>