<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata - Assign Topics</title>
	
<%-- 	<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/resources/css/jqgrid/jquery-ui-1.8.2.custom.css" /> --%>
<%-- 	<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/resources/css/jqgrid/ui.jqgrid.css" /> --%>
	
<%-- 	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.9.1.js"></script> --%>
<%-- 	<script src="${ctx}/resources/js/jqgrid/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script> --%>
<%-- 	<script src="${ctx}/resources/js/jqgrid/i18n/grid.locale-en.js" type="text/javascript"></script> --%>
<%-- 	<script src="${ctx}/resources/js/jqgrid/jquery.jqGrid.js" type="text/javascript"></script> --%>

	<script type="text/javascript">
		jQuery().ready(function () {
			jQuery("#dataGrid").jqGrid({
			   	url: 'fetchGridData.htm',
				datatype: "xml",
				mtype: 'POST',
			   	colNames: ['Domain', 'Category', 'Sub Category', 'Topic', 'Sub Topic', ''],
			   	colModel: [
			   		{name:'domain', index:'domain', sortable:false},
			   		{name:'category', index:'category', sortable:false},
			   		{name:'subcategory', index:'subcategory', sortable:false},
			   		{name:'topic', index:'topic', sortable:false},
			   		{name:'subtopic', index:'subtopic', sortable:false},
			   		{name:'assign', index:'assign', sortable:false}
			   	],
			   	rowNum: 10,
			   	autowidth: true,
			   	rowList: [10,20,30],
			   	pager: '#pagerForGrid',
			   	sortname: 'domain',
			    viewrecords: true,
			    sortorder: "desc",
			    caption: "Subtopics Grid"
			}).navGrid('#pagerForGrid', {edit: false, add: false, del: false});
		});
		
		function openPopUp(subTopicId, subTopic) {
			$.ajax({
				url: "openPopup.htm?subTopicId="+subTopicId+"&subTopic="+subTopic,
				dataType: "json",
				contentType: "application/json",
				type: "GET",
				success: function(response) {
					var htm = response.htm;
					$.fancybox(htm);
				},
				error: function() {
					//alert("Error in callback");
				}
			});
		}
		
		function saveAssignment() {
			var subTopicId = $("#subTopicId").val();
			var expertId = $("#expert").val();
			var jsonObj = {"obj" : {subTopicId : subTopicId, expertId : expertId}};
 			var jsonStr = JSON.stringify(jsonObj);
			if(expertId != -1) {
				$.ajax({
					url: "saveAssignment.htm",
					data: jsonStr,
					dataType: "json",
					contentType: "application/json",
					type: "POST",
					success: function(response) {
						//alert(response);
					},
					error: function() {
						//alert("Error in callback");
					}
				});
			} else {
				alert("Please select the expert to whom topic should be assigned.");
				return false;
			}
		}
	</script>
	
</head>
<body>
<div class="row">
<div class="col-md-12">
	<div class="date-text">Loaded in 0.0213 seconds</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-8 col-8">
	<ul class="shape-design">
		<li>Topic Assign</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none"><a href="javascript:;">Experts</a></li>
	</ul>
	</div>
	<div class="col-md-4">
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="center-table" style="width: 74%">
		<div style="float: left; width: 100%">
			<table id="dataGrid" class="scroll"></table>
			<div id="pagerForGrid" class="scroll"></div>
		</div>
	</div>
	
</body>
</html>