<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<c:import url="../../page.jsp"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
   
<style type="text/css">
	p {
		float: left;
	}
</style>
<script type="text/javascript">
var domainID, categoryID, subCategoryID, topicID;

$(document).ready(function() {
	$('#optional').hide();
	domainID = $('#domains option:selected').val();
	$('#subTopic').hide();
	
	$("#the_file").change(function() {
		validateImage("the_file");
	});
});

var result = false;
var topicRatingsResult = false; 

function validateAddSite() {	
    var data=document.getElementById("siteName").value;  
    if(data == null || data == "") {  
        alert("Name is required");  
        return false;  
      }  
      if(data != "" && data.indexOf(" ") == 0) {  
  	      alert("First character of the name should not be a space.");  
  	      return false;  
  	 }
    var data1=document.getElementById("txtEmail").value; 
    if(data1 == null || data1 == "") {  
        alert("URL is required");  
        return false;  
      }  
      if(data1 != "" && data1.indexOf(" ") == 0) {  
  	      alert("First character of the email should not be a space.");  
  	      return false;  
  	 }
      
      var url=document.getElementById("txtEmail").value;
      var pattern= "^(http[s]?:\\/\\/(www\\.)?|ftp:\\/\\/(www\\.)){1}([0-9A-Za-z-\\.@:%_\+~#=]+)+((\\.[a-zA-Z]{2,3})+)(/(.)*)?(\\?(.)*)?";
    
      if (!url.trim().match(pattern)) {
    	  alert("URL invalid. Try again.");
          return false;
      }
      
      if($("#the_file").val() != "") {
	      if(!validateImage("the_file")) {
	  	  	return false;
	  	  }
      }
    
      return true;  
   }  
   
			
	function getCategoriesByDomainId() {
		domainID = $('#domains option:selected').val();
		if(domainID !== 0) {
			$.ajax({
		  		type: "GET",
		  		url: "categoriesByDomainId.htm?id="+domainID.trim(),
		  		dataType: "json",
		  		contentType: "application/json",
		  		beforeSend: function() {
		  			$("#spanCategory").show();
		  		},
		  		complete: function() {
		  			$("#spanCategory").hide();
		  		},
		  		success: function(response) {
		  			$('#categories').empty().append(response.options);
		  			categoryID = $('#categories option:selected').val();
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
	
	function getSubCategoriesByCategoryId() {
		categoryID = $('#categories option:selected').val();
		if(categoryID != 'select'){
			$.ajax({
		  		type: "GET",
		  		url: "subCategoriesByCategoryId.htm?id="+categoryID.trim(),
		  		dataType: "json",
		  		contentType: "application/json",
		  		beforeSend: function() {
		  			$("#spanSubCategory").show();
		  		},
		  		complete: function() {
		  			$("#spanSubCategory").hide();
		  		},
		  		success: function(response) {
		  			$('#subCategories').empty().append(response.options);
		  			subCategoryID = $('#subCategories option:selected').val();
		  			$('#subCategory').show();
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
	
	function getTopicsBySubcategoryId() {
		subCategoryID = $('#subCategories option:selected').val();	
		if(subCategoryID != 'select'){
			$.ajax({
		  		type: "GET",
		  		url: "topicsBySubcategoryId.htm?id="+subCategoryID.trim(),
		  		dataType: "json",
		  		contentType: "application/json",
		  		beforeSend: function() {
		  			$("#spanTopic").show();
		  		},
		  		complete: function() {
		  			$("#spanTopic").hide();
		  		},
		  		success: function(response) {
		  			if(response.options != ""){
		  				$('#topics').empty().append(response.options);
		  				topicID = $('#topics option:selected').val();
		  				$('#topic').show();
		  			}else{
		  				alert(" All of the Topics under the selected Sub Category are assigned. Please create a Topic to add content.");
		  				$('#topics').empty().append("");
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
	
	var leafNode = false;
	function checkTopicIsLeafNode(topicId){
		$.ajax({
	  		type: "GET",
	  		url: "checkLeafNode.htm?topicId="+topicId.trim(),
	  		async: false,
	  		dataType: "json",
	  		contentType: "application/json",
	  		success: function(response) {
	  			leafNode = response.leafNode;
	  		},
	  		error: function(xhr,status,error) {
	  			var url = $("#contxt").val();
	  			if(status.toString() == 'parsererror'){
	  				window.location.href = url+"/loginForm.htm?msg=e";
	  			}
	        }
	  	  });  
	}
	
	var validate = false;
	function getSubTopicsByTopicId() {
		topicID = $('#topics option:selected').val();
		if(topicID != 'select') {
			$.ajax({
		  		type: "GET",
		  		url: "subTopicByTopicId.htm?id="+topicID.trim(),
		  		async: false,
		  		dataType: "json",
		  		contentType: "application/json",
		  		beforeSend: function() {
		  			$("#spanSubTopic").show();
		  			$("#spanTopic").show();
		  		},
		  		complete: function() {
		  			$("#spanSubTopic").hide();
		  			$("#spanTopic").hide();
		  		},
		  		success: function(response) {
		  			if(response.options.trim() != "") {
		  				validate = true;
		  				$('#subTopics').remove();
		  				$('#dynamicSubTopic').prepend('<select id=\'subTopics\' name=\'subTopicId\'>');
		  				$('#subTopics').empty().append(response.options);
		  				$('#optional').show();
		  				$('#subTopic').show();
		  				$("#spanTopic").hide();
		  			}else{
		  				checkTopicIsLeafNode(topicID);
		  				if(leafNode){
		  					validate = false;
		  					$('#subTopics').remove();
			  				$('#optional').hide();
		  				}else{
		  					validate = true;
		  					$('#subTopics').remove();
			  				$('#dynamicSubTopic').prepend('<select id=\'subTopics\' name=\'subTopicId\'>');
			  				$('#subTopics').empty().append(response.options);
			  				$('#optional').show();
			  				$('#subTopic').show();
		  					$("#spanTopic").hide();
			  				alert("All of the Sub Topics under the selected Topic are assigned. Please create a Sub Topic to add content.");
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
			$('#subTopics').remove();
			$('#optional').hide();
			alert("Please Select Valid Topic ");
		}
	}
		
	function checkBreadcrumb(){
		categoryID = $('#categories option:selected').val();
		subCategoryId = $('#subCategories option:selected').val();
		topicId = $('#topics option:selected').val();
		var subTopicID = $('#subTopics option:selected').val();
		
		if(categoryID != 0 && categoryID != "" && categoryID != null	){
			if(subCategoryId != 0 && subCategoryId != "" && subCategoryId != null && subCategoryId != 'select'){
				if(topicId != 0 && topicId != "" && topicId != null && topicId != 'select'){
					if(validate){
						if(subTopicID != 0 && subTopicID != "" && subTopicID != null && subTopicID != 'select'){
							var validatedSite = validateAddSite();
							if(validatedSite) {
								sendAjaxRequests();
							}
						}else{
							alert("Please Select Valid Values");
						}
					}else{
						var validatedSite = validateAddSite();
						if(validatedSite) {
							sendAjaxRequests();
						}
					}
				}else{
					alert("Please Select Valid Values");
				}
			}else{
				alert("Please Select Valid Values");
			}
		}else{
			alert("Please Select Valid Values");
		}
	}

	function checkTopicOrSubTopicRatings(id){
		$.ajax({
	  		type: "GET",
	  		url: "checkTopicOrSubTopicRatings.htm?id="+id.trim(),
	  		dataType: "json",
	  		contentType: "application/json",
	  		async: false,
	  		success: function(response) {
		  		if(response.result == true){
		  			topicRatingsResult =  true;
		  		}else{
					topicRatingsResult = false;
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
	
	
	
	function sendAjaxRequests(){
		if(validate){
			var subTopicID = $('#subTopics option:selected').val();
			checkTopicOrSubTopicRatings(subTopicID);
			if(topicRatingsResult){
				checkSiteName(subTopicID);
			}else{
				alert("Rate the Sub-Topic Criteria Importance before adding contents");
			}
			
		}else{
			var topicId = $('#topics option:selected').val();
			checkTopicOrSubTopicRatings(topicId);
			if(topicRatingsResult){
				checkSiteName(topicId);
			}else{
				alert("Rate the Topic Criteria Importance before adding contents");
			}
		}
	}

	function checkSiteName(id){
		var name = $('#siteName').val();
		var result  = "failure";
		$.ajax({
	  		type: "GET",
	  		url: "checkSiteName.htm?id="+id.trim()+"&name="+name.trim(),
	  		dataType: "json",
	  		contentType: "application/json",
	  		async: false,
	  		success: function(response) {
	  			result  = response.result.toString();
	  			if(result == "NotExist"){
	  				CheckSiteURL(id);
	  			}else{
	  				alert("Content name already exist here - please rename to be more specific. \n i.e instead of \"WebMD - Lung Cancer\" rename to - \"WebMD - Lung Cancer Symptoms\" ");
	  				
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

	
	function CheckSiteURL(id){
		var url = $('#txtEmail').val();
		var result  = "failure";
		$.ajax({
	  		type: "GET",
	  		url: "checkSiteURL.htm?id="+id.trim()+"&url="+url.trim(),
	  		dataType: "json",
	  		contentType: "application/json",
	  		async: false,
	  		success: function(response) {
	  			result  = response.result.toString();
	  			if(result == "NotExist"){
	  				document.forms["addSite"].submit();	
	  			}else{
	  				alert("This Content URL Already Exist In This Breadcrumb.");
	  				
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
	
	function submitForm() {
		checkBreadcrumb();
	}
	
	function addCategory(){
		window.location.href = "addCategory.htm?id="+domainID;
	}
	
	function addSubCategory(){
		window.location.href = "addSubCategory.htm?id="+categoryID;
	}
	
	function addTopic(){
		window.location.href = "addTopic.htm?id="+subCategoryID+"&catId="+categoryID;
	}
	
	function addSubTopic(){
		window.location.href = "addSubTopic.htm?id="+topicID;
	}
</Script>

</head>
<body>
	<div class="date-text">&nbsp;</div>
	
	<ul class="shape-design">
		<li class="shape1">&nbsp;</li>
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Contents</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Contents</li>
	</ul>

	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	<div class="breadcrumb-style"><h5><a href="listDomains.htm">Root</a>://</h5></div>
	<div class="center-table">
		<div class="bo-login-box">
			<div class="login-heading-inner">Content ID (auto
				assigned)</div>
			<div class="site-infologin-section">
				<div class="defaul-login-form2">
					<form:form modelAttribute="siteForm" action="addSite.htm" enctype="multipart/form-data"
						method="post" class="form-wrapper" id="addSite" name="addSite">
					
							<tr><td><label>Content Name:</label></td>
								<td>
									<form:input path="name" class="input_border email"  id="siteName" name="siteName"
										value="" />
								</td>
							</tr>
							<tr><td><label>URL:</label></td>
								<td>
									<form:input path="url" class="input_border email"  id="txtEmail" value="" />
								</td>
							</tr>
							<tr style="margin: -16px 0px 0px 105px;">
								<i style="font-size: 10px">(Enter full URL example: http://www.example.com/index.htm)</i>
							</tr>
							<tr><td><label>Logo:</label></td>
								<td>
									<form:input type="file" path="siteLogo" id="the_file" size="18" cssClass="upload-browse" />
								</td>
							</tr>
							
							<tr><td>label>Domain:</label></td>	
								<td>
									<form:select path="domainId" onchange="getCategoriesByDomainId();" id="domains">
										<form:option value="0">Select Domain</form:option>		
										<form:options items="${domains}" itemValue="id" itemLabel="name" />
									</form:select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanDomain">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									<a href="addDomain.htm" style="margin-left: 10px;font-size: 11px;">Add Domain&nbsp;&#62;&#62;</a>									
								</td>
							</tr>
							<tr><td><label>Category:</label>	</td>
								<td>
									<form:select  path="categoryId"  id="categories" onchange="getSubCategoriesByCategoryId()"></form:select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanCategory">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									<a href="javascript:;" onclick = "addCategory()" style="margin-left: 10px;font-size: 11px;">Add Category&nbsp;&#62;&#62;</a>
								</td>
							</tr>
							<tr><td><label>Sub Category:</label></td>	
								<td>
									<form:select path="subCategoryId"  id="subCategories" onchange="getTopicsBySubcategoryId()"></form:select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanSubCategory">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									<a id = "subCategory" href="javascript:;" onclick = "addSubCategory()" style="margin-left: 10px;font-size: 11px;">Add Sub Category&nbsp;&#62;&#62;</a>
								</td>
							</tr>
							<tr><td><label>Topic:</label></td>	
								<td>
									<form:select path="topicId"  id="topics" onchange="getSubTopicsByTopicId()"></form:select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanTopic">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									<a id = "topic" href="javascript:;" onclick = "addTopic()" style="margin-left: 10px;font-size: 11px;">Add Topic&nbsp;&#62;&#62;</a>
								</td>
							</tr>
							<tr id="optional"><td><label>Sub Topic:</label></td>	
								<td id="dynamicSubTopic">
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanSubTopic">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									<a id = "subTopic" href="javascript:;" onclick = "addSubTopic()" style="margin-left: 10px;font-size: 11px;">Add Sub Topic&nbsp;&#62;&#62;</a>
								<td>
							</tr>
							<tr><input type="button" name="Add Content" value="Save" id="btnLogin" onclick="submitForm()"></tr>
						
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>