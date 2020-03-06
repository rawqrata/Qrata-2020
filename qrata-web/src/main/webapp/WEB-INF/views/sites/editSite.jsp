<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	p {
		float: left;
	}
</style>
<script type="text/javascript">
var domainID, categoryID, subCategoryID, topicID;
var contentName, contentUrl;
$(document).ready(function() {
	/* if('${subtopicId}' == ""){
		$('#optional').hide();
	}else{
		$('#optional').show();
	} 
	$('#optional').hide();
	var domainOptions = $("#domains option[value='${domainID}']");
	domainOptions.attr("selected" , true);
	getCategoriesByDomainId();
	*/
	contentName = document.getElementById("siteName").value;
	contentUrl = document.getElementById("txtEmail").value;
	
	$("#the_file").change(function() {
		validateImage("the_file");
	});
	
	$("#removeLogo").click(function() {
		if(confirm("Do you want to remove the content's logo?"))
			removeSiteLogo();
		else
			return false;
	});
});

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
        alert("Email is required");  
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
   
   /*
	function getCategoriesByDomainId() {
		var domainID = $('#domains option:selected').val();	
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
	  			var categoryOption = $("#categories option[value='${categoryId}']");
	  			categoryOption.attr("selected" , "selected");
	  			getSubCategoriesByCategoryId();
	  		},
	  		error: function(xhr,status,error) {
	 			 alert("Status: " + status);
	             alert("Error: " + error);
	             alert("xhr: " + xhr.readyState);
           }
  	   });
	}
	
	function getSubCategoriesByCategoryId() {
		categoryID = $('#categories option:selected').val();	
		if(categoryID != 'select' && categoryID != ""){
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
		  			var subcategoryOption = $("#subCategories option[value='${subcategoryId}']");
		  			subcategoryOption.attr("selected" , "selected");
		  			getTopicsBySubcategoryId();
		  			
		  		},
		  		error: function(xhr,status,error) {
	  			 	alert("Status: " + status);
	               alert("Error: " + error);
	               alert("xhr: " + xhr.readyState);
	           }
	  	  });  
		}else{
			alert("Please Select Valid Category");
		}
	}
	
	function getTopicsBySubcategoryId() {
		subCategoryID = $('#subCategories option:selected').val();	
		if(subCategoryID != 'select' && subCategoryID != ""){
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
		  			$('#topics').empty().append(response.options);
		  			var topicOption = $("#topics option[value='${topicId}']");
		  			topicOption.attr("selected" , "selected");
		  			if('${subtopicId}' != "") {
		  				//getSubTopicsByTopicId();
		  				setTimeout("getSubTopicsByTopicId()", 100);
		  			}
		  		},
		  		error: function(xhr,status,error) {
	  			 	alert("Status: " + status);
	               alert("Error: " + error);
	               alert("xhr: " + xhr.readyState);
	           }
	  	  });  
		} else {
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
  				alert("Status: " + status);
              	alert("Error: " + error);
              	alert("xhr: " + xhr.readyState);
           }
  	   });  
	}
	
	function getSubTopicsByTopicId() {
		topicID = $('#topics option:selected').val();
		if(topicID != 'select' && topicID != ""){
			$.ajax({
		  		type: "GET",
		  		url: "subTopicByTopicId.htm?id="+topicID.trim(),
		  		dataType: "json",
		  		contentType: "application/json",
		  		beforeSend: function() {
		  			$("#spanSubTopic").show();
		  			if($("#optional").css("display") == "none") {
		  				$("#spanTopic").show();
		  			}
		  		},
		  		complete: function() {
		  			$("#spanSubTopic").hide();
		  			$("#spanTopic").hide();
		  		},
		  		success: function(response) {
  					$("#spanTopic").hide();
		  			if(response.options.trim() != "") {
		  				validate = true;
		  				$('#subTopics').remove();
		  				$('#dynamicSubTopic').prepend('<select id=\'subTopics\' name=\'subTopicId\'>');
		  				$('#subTopics').empty().append(response.options);
			  			var subtopicOption = $("#subTopics option[value='${subtopicId}']");
			  			subtopicOption.attr("selected" , "selected");
		  				$('#optional').show();
		  				$('#subTopic').show();
		  			} else {
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
			  				alert("All of the Sub Topics under the selected Topic are assigned. Please create a Sub Topic to add content.");
		  				}
		  			}
		  		},
		  		error: function(xhr,status,error) {
	  			 	alert("Status: " + status);
	               alert("Error: " + error);
	               alert("xhr: " + xhr.readyState);
	           }
	  	  });  
		}else{
			$('#subTopics').remove();
			$('#optional').hide();
			alert("Please Select Valid Topic");
		}
	}
	
	var validate = false;

	function checkBreadcrumb(){
		var categoryID = $('#categories option:selected').val();
		var subCategoryId = $('#subCategories option:selected').val();
		var topicId = $('#topics option:selected').val();
		var subTopicID = $('#subTopics option:selected').val();
		
		if(categoryID != 0 && categoryID != "" && categoryID != null && categoryID != 'select'){
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
	  			 alert("Status: " + status);
	             alert("Error: " + error);
	             alert("xhr: " + xhr.readyState);
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
				alert("SubTopic Rate Criteria Importance before adding contents");
			}
			
		}else{
			var topicId = $('#topics option:selected').val();
			checkTopicOrSubTopicRatings(topicId);
			if(topicRatingsResult){
				checkSiteName(topicId);
			}else{
				alert("Topic Rate Criteria Importance before adding contents");
			}
		}
	}
*/
	
	function checkSiteName(){
		var siteId = <c:out value='${siteForm.id}' />;
		var name = $('#siteName').val();
		var result  = "failure";
		var validation = validateAddSite();
		if(contentName.trim() != name.trim() && validation){
			$.ajax({
		  		type: "GET",
		  		url: "checkSiteName.htm?id="+siteId+"&name="+name.trim(),
		  		dataType: "json",
		  		contentType: "application/json",
		  		async: false,
		  		success: function(response) {
		  			result  = response.result.toString();
		  			if(result == "NotExist"){
		  				CheckSiteURL(siteId, validation);
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
		}else{
			CheckSiteURL(siteId);
		}
	}

	function CheckSiteURL(id, validation){
		var url = $('#txtEmail').val();
		var result  = "failure";
		if(contentUrl.trim() != url.trim() && validation){
			$.ajax({
		  		type: "GET",
		  		url: "checkSiteURL.htm?id="+id+"&url="+url.trim(),
		  		dataType: "json",
		  		contentType: "application/json",
		  		async: false,
		  		success: function(response) {
		  			result  = response.result.toString();
		  			if(result == "NotExist"){
		  				$('#domains').removeAttr("disabled");
		  				$('#categories').removeAttr("disabled");
		  				$('#subCategories').removeAttr("disabled");
		  				$('#topics').removeAttr("disabled");
		  				<c:if test="${siteForm.subTopicId ne 0}">
		  					$('#subTopics').removeAttr("disabled");
		  				</c:if>
		  				
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
		}else{
			if(validation){
				$('#domains').removeAttr("disabled");
				$('#categories').removeAttr("disabled");
				$('#subCategories').removeAttr("disabled");
				$('#topics').removeAttr("disabled");
				<c:if test="${siteForm.subTopicId ne 0}">
					$('#subTopics').removeAttr("disabled");
				</c:if>
				document.forms["addSite"].submit();
			}
		}
	}
	
	function removeSiteLogo() {
		var siteId = <c:out value='${siteForm.id}' />;
		$.ajax({
	  		type: "GET",
	  		url: $("#contxt").val()+"/admin/removeSiteLogo.htm?id="+siteId,
	  		dataType: "json",
	  		contentType: "application/json",
	  		cache: false,
	  		beforeSend: function() {
	  			$("#spanLogo").show();
	  		},
	  		complete: function() {
	  			$("#spanLogo").hide();
	  		},
	  		success: function(response) {
	  			$("#spanLogo").hide();
	  			alert(response.msg);
	  			window.location.reload();
	  		},
	  		error: function(xhr, status, error, errorThrown) {
	  			var url = $("#contxt").val();
	  			if(status.toString() == 'parsererror'){
	  				window.location.href = url+"/loginForm.htm?msg=e";
	  			}
           }
  	   });
	}
	
	function submitForm() {
		checkSiteName();
	}
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
		<li class="shape1">&nbsp;</li>
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Content</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Edit Content</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	
	<div class="sitebox">
				      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Content ID (auto assigned)</div>
							<form:form commandName="siteForm" action="editSite.htm" enctype="multipart/form-data"
						method="post" class="form-wrapper" id="addSite" name="addSite">
						<div class="row">
						<div class="col-md-6">
							<label>Content Name:</label>
								<div class="form-group">
									<form:input path="name" class="input_border form-control email"  id="siteName" value="" />
									<form:input type="hidden" path="siteUuid" />
									</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
							<label>URL:</label>
							<form:input path="url" class="input_border form-control email" id="txtEmail" value="" />
							<i style="font-size: 10px">(Enter full URL example: http://www.example.com/index.htm)</i>
								</div>
							</div>
							<div class="col-md-6">
							<label>Logo:</label>
							<div class="form-group">
									<c:choose>
										<c:when test="${not empty siteForm.imageName}">
											<img alt="Content Image" src="${ctx}/admin/getContentImage.htm?siteId=${siteForm.id}" />
										</c:when>
										<c:otherwise>
											<img alt="Content Image" src="${ctx}/resources/images/column-image.gif" />
										</c:otherwise>
									</c:choose>
									<form:input type="file" path="siteLogo" id="the_file" size="18" cssClass="upload-browse" />
									
									<c:if test="${not empty siteForm.imageName}">
										<br/> <a id="removeLogo" href="javascript:;" style="text-decoration: none;cursor: pointer;">Remove</a>
										<span style="display: inline-block;margin-left: 8px;width: 50px;">
											<span style="display: none;width: 20px;" id="spanLogo">
												<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 16px;" />
											</span>
										</span>
									</c:if>
									</div>
							</div>
							<div class="col-md-6">
							<label>Domain:</label>	
								<div class="form-group">
									<select id="domains" name="domainId" disabled="disabled" class="form-control">
										<option value="${siteForm.domainId}">${siteForm.domainName}</option>
									</select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanDomain">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									</div>
							</div>
							<div class="col-md-6">
							<label>Category:</label>	
							<div class="form-group">
									<select id="categories" name="categoryId" disabled="disabled" class="form-control">
										<option value="${siteForm.categoryId}">${siteForm.categoryName}
									</select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanCategory">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									</div>
							</div>
							<div class="col-md-6">
							<label>Sub Category:</label>	
							<div class="form-group">
									<select id="subCategories" name="subCategoryId" disabled="disabled" class="form-control">
										<option value="${siteForm.subCategoryId}">${siteForm.subCategoryName}</option>
									</select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanSubCategory">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									</div>
							</div>
							<div class="col-md-6">
							<label>Topic:</label>	
							<div class="form-group">
									<select  id="topics" name="topicId" disabled="disabled" class="form-control">
										<option value="${siteForm.topicId}" >${siteForm.topicName}</option>
									</select>
									<span style="display: inline-block;margin-left: 8px;width: 50px;">
										<span style="display: none;width: 35px;" id="spanTopic">
											<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
										</span>
									</span>
									</div>
							</div>
						
						<c:if test="${siteForm.subTopicId ne 0}">
						<div class="col-md-6">
							<label>Sub Topic:</label>
							<div class="form-group">
								<select id="subTopics" name="subTopicId" disabled="disabled" class="form-control">
									<option value="${siteForm.subTopicId }">${siteForm.subTopicName }</option>
								</select>
								<span style="display: inline-block;margin-left: 8px;width: 50px;">
									<span style="display: none;width: 35px;" id="spanSubTopic">
										<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 25px;" />
									</span>
								</span>	
								</div>
							</div>
						</c:if>
							
							
							<div class="col-md-12 text-center">
							<input type="button" name="Edit Content" value="Save" style="background-color:grey" onclick="submitForm()"></div>
						
						<input type="hidden" name ="prp" id ="prp" value="${prp}"/>
						<input type="hidden" name ="orp" id ="orp" value="${orp}"/>
						<input type="hidden" name ="sfrp" id ="sfrp" value="${sfrp}"/>
						<input type="hidden" name="sunrp" id="sunrp" value="${sunrp}"> 
						</div>
					</form:form>
			
		
		</div>
	</div>

</body>
</html>