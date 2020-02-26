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
		});
		var flag = false;
		var sourceValidate = destinationValidate = false;
		var destOptions;
		var counter = 0;
		var copyContentIds = [];
		function getCategoriesByDomainId(val, sourceId) {
			domainID = val;
			if(domainID != 0) {
				$.ajax({
			  		type: "GET",
			  		url: "categoriesByDomainId.htm?id="+domainID.trim(),
			  		dataType: "json",
			  		contentType: "application/json",
			  		success: function(response) {
			  			if(sourceId == "domainSrc"){
			  				$('#categorySrc').empty().append(response.options);
				  			categoryID = $('#categorySrc option:selected').val();
			  			}else{
			  				$('#categoryDest').empty().append(response.options);
				  			categoryID = $('#categoryDest option:selected').val();
			  			}
			  			
			  		},
			  		error: function(xhr,status,error) {
			  			var url = $("#contxt").val();
			  			if(status.toString() == 'parsererror'){
			  				window.location.href = url+"/loginForm.htm?msg=e";
			  			}
		            }
		  	    });
			}
		}
		
		function getSubCategoriesByCategoryId(val, sourceId) {
			categoryID = val;
			if(categoryID != 'select'){
				$.ajax({
			  		type: "GET",
			  		url: "subCategoriesByCategoryId.htm?id="+categoryID.trim(),
			  		dataType: "json",
			  		contentType: "application/json",
			  		success: function(response) {
						if(sourceId == "categorySrc"){
							$('#subCategorySrc').empty().append(response.options);
				  			subCategoryID = $('#subCategorySrc option:selected').val();	
						}else{
							$('#subCategoryDest').empty().append(response.options);
				  			subCategoryID = $('#subCategoryDest option:selected').val();
						}
			  		},
			  		error: function(xhr,status,error) {
					  			var url = $("#contxt").val();
					  			if(status.toString() == 'parsererror'){
					  				window.location.href = url+"/loginForm.htm?msg=e";
					  			}
				           }
				  	  });
			}else{
				alert("Please Select Valid Category");
			}
			  
		}
		
		
		function getAllTopicsBySubCategoryId(val, sourceId) {
			subCategoryID = val;
			if(subCategoryID != 'select'){
				$.ajax({
			  		type: "GET",
			  		url: "allTopicsBySubCategoryId.htm?subCatId="+subCategoryID.trim(),
			  		dataType: "json",
			  		contentType: "application/json",
			  		success: function(response) {
						if(sourceId == "subCategorySrc"){
							if(response.options != ""){
								$('#topicSrc').empty().append(response.options);
					  			subCategoryID = $('#topicSrc option:selected').val();
							}else{
								alert("None of the Source Topics are give Criterion Importance. Copying can be done only after the Criterion Importance are given for the concerned Source Topic.");
							}
								
						}else{
							if(response.options != ""){
								$('#topicDest').empty().append(response.options);
					  			subCategoryID = $('#topicDest option:selected').val();
							}else{
								alert("None of the Destination Topics are give Criterion Importance. Copying can be done only after the Criterion Importance are given for the concerned Destination Topic.");
							}
							
						}
			  		},
			  		error: function(xhr,status,error) {
				  			var url = $("#contxt").val();
				  			if(status.toString() == 'parsererror'){
				  				window.location.href = url+"/loginForm.htm?msg=e";
				  			}
			           }
			  	   });
			}else{
				alert("Please Select Valid Sub Category");
			}
			  
		}
		
		function getSubTopicsOrContentsByTopicId(val, sourceId) {
			topicID = val;
			if(topicID != 'select'){
				$.ajax({
			  		type: "GET",
			  		url: "subTopicsOrContentsByTopicId.htm?topicId="+topicID.trim(),
			  		dataType: "json",
			  		contentType: "application/json",
			  		success: function(response) {
						if(response.result == "contents"){
							if(sourceId == "topicSrc"){
								sourceValidate = false;
								$('#subTopicSrc').remove();
								$('#sourceContents').empty().append(response.options);
							}else{
								destinationValidate = false;
								$('#subTopicDest').remove();
								$('#destinationContents').empty().append(response.options);
							}
						}else{
							if(sourceId == "topicSrc"){
								sourceValidate = true;
								$('#subTopicSrc').remove();
								$('#sourceContents').empty();
				  				$('#subTopicSrcSelect').append('<select id=\'subTopicSrc\' name=\'subTopicSrc\' onchange=\'getSubTopicsContent(this.value, this.id)\' >');
				  				if(response.options != ""){
				  					$('#subTopicSrc').empty().append(response.options);
				  				}else{
				  					alert("None of the Source Sub Topics are give Criterion Importance. Copying can be done only after the Criterion Importance are given for the concerned Source Sub Topic.");
				  				}
							}else{
								destinationValidate = true;
								$('#subTopicDest').remove();
								$('#destinationContents').empty();
				  				$('#subTopicDestSelect').append('<select id=\'subTopicDest\' name=\'subTopicDest\' onchange=\'getSubTopicsContent(this.value, this.id)\' >');
								if(response.options != ""){
									$('#subTopicDest').empty().append(response.options);
				  				}else{
				  					alert("None of the Destination Sub Topics are give Criterion Importance. Copying can be done only after the Criterion Importance are given for the concerned Destination Sub Topic.");
				  				}	
							}
						}
			  		},
			  		error: function(xhr,status,error) {
					  			var url = $("#contxt").val();
					  			if(status.toString() == 'parsererror'){
					  				window.location.href = url+"/loginForm.htm?msg=e";
					  			}
				           }
				  	  });
			}else{
				alert("Please Select Valid Topic");
			}
			  
		}
		
		function getSubTopicsContent(val, sourceId) {
			if(val != 'select'){
				$.ajax({
			  		type: "GET",
			  		url: "subTopicsOrContentsByTopicId.htm?topicId="+val.trim(),
			  		dataType: "json",
			  		contentType: "application/json",
			  		success: function(response) {
						if(response.result == "contents"){
							if(sourceId == "subTopicSrc"){
								$('#sourceContents').empty().append(response.options);
							}else{
								$('#destinationContents').empty().append(response.options);
							}
						}
			  		},
			  		error: function(xhr,status,error) {
					  			var url = $("#contxt").val();
					  			if(status.toString() == 'parsererror'){
					  				window.location.href = url+"/loginForm.htm?msg=e";
					  			}
				           }
				  	  });
			}else{
				alert("Please Select Valid Sub Topic");
			}
			  
		}
		
		
		function copyContent() {
			destOptions = $('#destinationContents option');
			var srcSelectedOptions = $('#sourceContents option:selected').clone();
		
			copyContentIds = [];
			counter = 0;
			var flag = true;
			var text = "These Contents are already Assigned to Destination : \n";
			
			 $.map(destOptions, function(option){
			     var optionValue = option.value;
			     srcSelectedOptions.each(function(){
			      if($(this).val().trim() == optionValue.trim()){
			       flag = false;
			       text += $(this).text().trim()+"\n";
			      }
			     });
			 });
			
			 if(flag){
				 $.map(srcSelectedOptions, function(option){
						copyContentIds[counter] = option.value;
						counter++;
					});	
					checkExistContentsInTopic(copyContentIds);
			 }else{
				 alert(text);
			 }
		}
		
		function deleteContent() {
			var destOptions = $('#destinationContents option:selected');
			destOptions.remove();
			destOptions.each(function(){
				for(var i = 0; i < copyContentIds.length; i++){
					if(copyContentIds[i] == $(this).val()){
						copyContentIds[i] = 0;
					}
				}
			});
		}
		
		function checkExistContentsInTopic(copyContentIds){
			var topicId;
			if(sourceValidate){
				topicId = $('#subTopicDest option:selected').val();
			}else{
				topicId = $('#topicDest option:selected').val();
			}
			if(topicId != "" && topicId != 'select'){
				$.ajax({
			  		type: "GET",
			  		url: "checkExistContentsInTopic.htm?topicId="+topicId.trim()+"&contentsId="+copyContentIds,
			  		dataType: "json",
			  		contentType: "application/json",
			  		async: false,
			  		success: function(response) {
			  			$('#destinationContents').append(response.result);
						if(response.error != ""){
							alert("These contents already exist in Destination : \n"+response.error);
						}
			  		},
			  		error: function(xhr,status,error) {
					  			var url = $("#contxt").val();
					  			if(status.toString() == 'parsererror'){
					  				window.location.href = url+"/loginForm.htm?msg=e";
					  			}
				           }
				 });
			}else{
				alert("Please Select Valid Topic");
			}
		}
		
		function checkSourceBreadcrumb(){
			var categoryIdSrc = $('#categorySrc option:selected').val();
			var subCategoryIdSrc = $('#subCategorySrc option:selected').val();
			var topicIdSrc = $('#topicSrc option:selected').val();
			var subTopicSrcId = $('#subTopicSrc option:selected').val();
			
			if(categoryIdSrc != 0 && categoryIdSrc != "" && categoryIdSrc != null && categoryIdSrc != 'select'){
				if(subCategoryIdSrc != 0 && subCategoryIdSrc != "" && subCategoryIdSrc != null && subCategoryIdSrc != 'select'){
					if(topicIdSrc != 0 && topicIdSrc != "" && topicIdSrc != null && topicIdSrc != 'select'){
						if(sourceValidate){
							if(subTopicSrcId != 0 && subTopicSrcId != "" && subTopicSrcId != null && subTopicSrcId != 'select'){
								checkDestinationBreadcrumb();
							}else{
								alert("Please Select Valid Source Sub Topic");
							}
						}else{
							checkDestinationBreadcrumb();
						}
					}else{
						alert("Please Select Valid Source Topic");
					}
				}else{
					alert("Please Select Valid Source Sub Category");
				}
			}else{
				alert("Please Select Valid Source Category");
			}
		}
		
		function checkDestinationBreadcrumb(){
			var categoryIdDest = $('#categoryDest option:selected').val();
			var subCategoryIdDest = $('#subCategoryDest option:selected').val();
			var topicIdDest = $('#topicDest option:selected').val();
			var subTopicDestId = $('#subTopicDest option:selected').val();
			
			if(categoryIdDest != 0 && categoryIdDest != "" && categoryIdDest != null && categoryIdDest != 'select'){
				if(subCategoryIdDest != 0 && subCategoryIdDest != "" && subCategoryIdDest != null && subCategoryIdDest != 'select'){
					if(topicIdDest != 0 && topicIdDest != "" && topicIdDest != null && topicIdDest != 'select'){
						if(destinationValidate){
							if(subTopicDestId != 0 && subTopicDestId != "" && subTopicDestId != null && subTopicDestId != 'select'){
								submitCopyContent();	
							}else{
								alert("Please Select Valid Destination Sub Topic");
							}
						}else{
							submitCopyContent();	
						}
					}else{
						alert("Please Select Valid Destination Topic");
					}
				}else{
					alert("Please Select Valid Destination Sub Category");
				}
			}else{
				alert("Please Select Valid Destination Category");
			}
		}
		
		function submitCopyContent() {
			//alert("Print Contetnt Id Array : "+copyContentIds.join('\n'));
			var input = $("<input>").attr("type", "hidden").attr("name", "copyContentIds").val(copyContentIds);
			$('#copyContents').append(input);
			document.forms["copyContents"].submit();
		}
		
	</script>
</head>
<body>
	
	<c:choose>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Assignment has been done successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '5' }">
			<div class="success-box"><c:out value="DeAssignment has been done successfully" /></div>
		</c:when>
	</c:choose>
	<div id="message" class="success-box"></div>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li>Copying</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Copy Content</li>
	</ul>
	
	<div class="back-button">
		<a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
			Go Back </a>
	</div>
	<c:choose>
		<c:when test="${not empty topic.parentTopic.id}">
			<div class="breadcrumb-style"><h5><a href="listDomains.htm">Root</a>://</h5></div>
		</c:when>
	</c:choose>
	<div class="center-table">
		<form:form action="copyContents.htm" name="copyContents" id="copyContents" commandName="copyContentForm" method="POST">
		<table width="100%"
				class="table table-striped table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th width="47.5%" colspan="" class="table-heading">Source Bredcrumb</th>
						<th width="47.5%" class="table-heading">Destination Bredcrumb</th>
					</tr>
				</thead>
		
				<tbody>
					<tr>
						<td>
							<label style="font-weight: bold;">Domain&#58;</label>
							<form:select path="domainIdSrc" onchange="getCategoriesByDomainId(this.value, this.id)" id="domainSrc">
								<form:option value="0">Select Domain</form:option>
								<form:options items="${domains}" itemValue="id" itemLabel="name" />
							</form:select>
						</td>
						<td>
							<label style="font-weight: bold;">Domain&#58;</label>
							<form:select path="domainIdDest" onchange="getCategoriesByDomainId(this.value, this.id)" id="domainDest">
								<form:option value="0">Select Domain</form:option>
								<form:options items="${domains}" itemValue="id" itemLabel="name" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td>
							<label style="font-weight: bold;">Category&#58;</label>
							<form:select  path="categoryIdSrc"  id="categorySrc" onchange="getSubCategoriesByCategoryId(this.value, this.id)"></form:select>
						</td>
						<td>
							<label style="font-weight: bold;">Category&#58;</label>
							<form:select  path="categoryIdDest"  id="categoryDest" onchange="getSubCategoriesByCategoryId(this.value, this.id)"></form:select>
						</td>
					</tr>
					
					<tr>
						<td>
							<label style="font-weight: bold;">Sub Category&#58;</label>
							<form:select path="subCategoryIdSrc"  id="subCategorySrc" onchange="getAllTopicsBySubCategoryId(this.value, this.id)"></form:select>
						</td>
						<td>
							<label style="font-weight: bold;">Sub Category&#58;</label>
							<form:select path="subCategoryIdDest"  id="subCategoryDest" onchange="getAllTopicsBySubCategoryId(this.value, this.id)"></form:select>
						</td>
					</tr>
					
					<tr>
						<td>
							<label style="font-weight: bold;">Topic&#58;</label>
							<form:select path="topicIdSrc"  id="topicSrc" onchange="getSubTopicsOrContentsByTopicId(this.value, this.id)"></form:select>
						</td>
						<td>
							<label style="font-weight: bold;">Topic&#58;</label>
							<form:select path="topicIdDest"  id="topicDest" onchange="getSubTopicsOrContentsByTopicId(this.value, this.id)"></form:select>
						</td>
					</tr>
					
					<tr>
						<td id="subTopicSrcSelect">
							<label style="font-weight: bold;">Sub Topic&#58;</label>
						</td>
						<td id="subTopicDestSelect">
							<label style="font-weight: bold;">Sub Topic&#58;</label>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table width="100%"
				class="table table-striped table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<th width="47.5%" colspan="" class="table-heading">Contents</th>
						<th width="5%" colspan="" class="table-heading"></th>
						<th width="47.5%" class="table-heading">Contents</th>
					</tr>
				</thead>
		
				<tbody>
					<tr>
						<td width="50%" rowspan="3" align="center">
							<form:select path="sourceContents" id="sourceContents" multiple="multiple" style="width:100%; height: 300px;">
							</form:select>
						</td>
		
						<td width="5%" style="vertical-align: middle;" rowspan="3"> 
							<div style="float: left;width: 100%;padding-bottom: 100px;">
								<a href="javascript:;" onclick="copyContent()"><i class="icon-arrow-right"></i></a>
							</div>
							<div style="float: left;width: 100%;padding-top: 100px;">
								<a href="javascript:;" onclick="deleteContent();"><i class="icon-arrow-left"></i></a>
							</div>
						</td>
		
						<td width="50%" rowspan="3" align="center">
							<form:select path="destinationContents" id="destinationContents" style="width: 100%; height:300px;" multiple="multiple">
							</form:select>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="text-align: center;">
				<input type="button" name="btn" value="Submit" id="btnSubmit" onclick="checkSourceBreadcrumb();" >
			</div>
		</form:form>
	</div>
</body>
</html>