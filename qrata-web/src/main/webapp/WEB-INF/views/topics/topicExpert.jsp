<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb"
	prefix="b"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#message').hide();
			$("input[type=button]").attr("disabled", "disabled");
			getExperts();
		});
		var flag = false;
		
		function assignExpert() {
			flag = true;
			$("input[type=button]").removeAttr("disabled");
			if($('#assignedExpert option').size() < 1){
				if($('#availableExpert :selected').size() == 1){
					$('#availableExpert option:selected').each(function() {
					    $(this).remove().appendTo("#assignedExpert");
					});	
				}else{
					alert("Please Assign One Expert To Topic");
				}
					
			} else {
				alert("Topic Already Assigned Expert ");
			}
		}
		
		function unAssignExpert() {
			flag = false;
			$("input[type=button]").removeAttr("disabled");
			$('#assignedExpert option:selected').each(function() {
			    $(this).remove().appendTo("#availableExpert");
				
			    var my_options = $("#availableExpert option");
				my_options.sort(function(a, b) {
					var aa = a.text.toUpperCase();
					var bb = b.text.toUpperCase();
				    if (aa > bb) return 1;
				    else if (aa < bb) return -1;
				    else return 0;
				});
				$("#availableExpert").empty().append( my_options );
			});
		}
		
		function submitTopicExpert() {
			var optionValue = $('#assignedExpert option').val();
			if(flag) {
				var input;
				if(optionValue != 0 && optionValue != null){
					input = $("<input>").attr("type", "hidden").attr("name", "expertsId").val(optionValue);	
				} else {
					input = $("<input>").attr("type", "hidden").attr("name", "expertsId").val("0");
				}
				$('#saveForm').append(input);
				document.forms["saveForm"].submit();
			} else {
				alert("Please Assign at Least One Expert");
			}
		}
		
		
		function searchAvailableExperts(term) {
			getExperts(term);
		}
		
		function getExperts(term) {
			if(!term) {
				term = "";
			}
			var url = $("#contxt").val()+"/admin/getAvailableExperts.htm?term="+term;
			$.ajax({
		  		type: "GET",
		  		url: url,
		  		dataType: "json",
		  		contentType: "application/json",
		  		cache: false,
		  		beforeSend: function() {
		  			var loading = "<option value='Please wait...'>Please wait...</option>";
		  			$("#availableExpert").empty().html(loading);
		  		},
		  		success: function(response) {
		  			generateHTML(response.obj);
		  		},
		  		error: function(xhr, status, error) {
		  			$("#availableExpert").empty();
			    }
			});
		}		
		function generateHTML(objList) {
			var options = null;
			for(var i in objList) {
				var obj = objList[i];
				options += "<option value='"+obj.id+"'>"+obj.fullName+"</option>";
			}
			$("#availableExpert").empty().html(options);
		}
	</script>
</head>
<body>
	<div class="row">
	<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Assignment has been done successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '5' }">
			<div class="success-box"><c:out value="DeAssignment has been done successfully" /></div>
		</c:when>
	</c:choose>
	</div>
	</div>
	<div class="row">
	<div id="message" class="success-box"></div>
	</div>
	<div class="row">
	<div class="col-md-12">
	<div class="date-text">&nbsp;</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-8 col-8">
	<c:choose>
		<c:when test="${not empty topic.parentTopic.id}">
			<ul class="shape-design">
			
				<li>Sub-Topic Assign</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Expert</li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="shape-design">
			
				<li>Topic Assign</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li>Expert</li>
			</ul>
		</c:otherwise>
	</c:choose>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button">
		<a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
			Go Back </a>
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
	<c:choose>
		<c:when test="${not empty topic.parentTopic.id}">
			<h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${topic.id}" type="ASSIGN" /></h5>
		</c:when>
		<c:otherwise>
			<h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${topic.id}" type="ASSIGN" /></h5>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
	<div class="assignebox">
		<form action="assignedExpert.htm" name="saveForm" id="saveForm">
			<table width="100%"
				class="table table-striped table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th width="47.5%" colspan="" class="table-heading">Available Experts</th>
						<th width="5%" colspan="" class="table-heading"></th>
						<th width="47.5%" class="table-heading">Assigned Experts</th>
					</tr>
				</thead>
		
				<tbody>
					<tr>
						<td width="50%" rowspan="3" align="center">
							<div style="float: left;width: 100%;" id="availableDiv">
								<div style="float: left;width: 100%">
									<input type="text" name="searchAvailable" id="searchAvailable" autocomplete="off" placeholder="Search expert"
									value="" onkeyup="searchAvailableExperts(this.value);" class="form-control"  />
								</div>
								<select name="availableExpert" id="availableExpert"
									multiple="multiple" style="width:100%; height: 300px;">
									<%-- <c:forEach var="user" items="${availableExperts}">
										<option value="${user.id}">${user.userinfo.lastname} ${user.userinfo.firstname}</option>
									</c:forEach> --%>
								</select>
							</div>
						</td>
		
						<td width="5%" style="vertical-align: middle;" rowspan="3"> 
							<div style="float: left;width: 100%;">
								<a href="javascript:;" onclick="assignExpert();"><i class="fa fa-arrow-right"></i></a>
							</div>
							<div style="float: left;width: 100%;padding-top: 100px;">
								<input type="hidden" name="topicId" id="topicId" value="${topic.id}" />
								<a href="javascript:;" onclick="unAssignExpert();"><i class="fa fa-arrow-left"></i></a>
							</div>
						</td>
		
						<td width="50%" rowspan="3" align="center">
							<div style="float: left;width: 100%;" id="assignedDiv">
								<div style="float: left;width: 100%;"> <div style="float: left;height: 2.9em;"></div> </div>
								<select name="assignedExpert" id="assignedExpert" style="width: 100%; height:300px;" multiple="multiple">
									<c:if test="${not empty assignedExpert}">
										<option value="${assignedExpert.id}">${assignedExpert.userinfo.lastname}&nbsp;${assignedExpert.userinfo.firstname}</option>
									</c:if>
								</select>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="text-align: center;">
				<input type="button" name="btn" class="btn btn-info" value="Submit" id="btnSubmit" onclick="submitTopicExpert();" >
			</div>
		</form>
	</div>
</body>
</html>