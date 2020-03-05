<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	$(document).ready(function() {
		$('#optional').hide();
		$('#link2').hide();
		
		<c:if test="${flag eq 1}">
			$("#the_file").change(function() {
				validateImage("the_file");
			});
		</c:if>
	});
	
	var result = false;
	
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
	      
	      var url = document.getElementById("txtEmail").value;
	      var pattern = "^(http[s]?:\\/\\/(www\\.)?|ftp:\\/\\/(www\\.)){1}([0-9A-Za-z-\\.@:%_\+~#=]+)+((\\.[a-zA-Z]{2,3})+)(/(.)*)?(\\?(.)*)?";
	 	  if (!trimField(url).match(pattern)) {
	    	  alert("URL invalid. Try again.");
	          return false; 
	      }
	      
	      <c:if test="${flag eq 1}">
		      	if($("#the_file").val() != "") {
				      if(!validateImage("the_file")) {
					  	return false;
					  }
		      	}
		  </c:if>
	    
	      return true;  
	   }  
	   
		var urlResult = "";
		var nameResult = "";
		
		function checkSiteURL(id){
			var url = $('#txtEmail').val();
			$.ajax({
		  		type: "GET",
		  		url: "checkSiteURL.htm?id="+id.trim()+"&url="+url.trim(),
		  		dataType: "json",
		  		async: false,
		  		contentType: "application/json",
		  		success: function(response) {
		  			urlResult  = response.result.toString();
		  			if(urlResult != "NotExist"){
		  				alert("This Content URL Already Exist In This Breadcrumb");
		  			}
		  		},
		  		error: function(xhr,status,error) {
		  			alert("Status: " + status);
		            alert("Error: " + error);
		            alert("xhr: " + xhr.readyState);
			    }
			}); 
		}
		
		function checkSiteName(id){
			var name = $('#siteName').val();
			$.ajax({
		  		type: "GET",
		  		url: "checkSiteName.htm?id="+id.trim()+"&name="+name.trim(),
		  		dataType: "json",
		  		async: false,
		  		contentType: "application/json",
		  		success: function(response) {
		  			nameResult  = response.result.toString();
		  			if(nameResult != "NotExist"){
		  				alert("Content name already exist here - please rename to be more specific. \n i.e instead of \"WebMD - Lung Cancer\" rename to - \"WebMD - Lung Cancer Symptoms\" ");
		  			}
		  		},
		  		error: function(xhr,status,error) {
		  			alert("Status: " + status);
		            alert("Error: " + error);
		            alert("xhr: " + xhr.readyState);
			    }
			}); 	
		}
		
		function checkSiteValidation(){
			var topicId = $('#topic').val();
			var result = validateAddSite();
			if(result){
				checkSiteURL(topicId);
				checkSiteName(topicId);
				if(nameResult == "NotExist" && urlResult == "NotExist"){
					document.forms["addSite"].submit();	
				}
			}
		}
		
		function submitForm(){
			checkSiteValidation();
		}
</script>

</head>
<body>
<div class="row">
<div class="col-md-8 col-8">
	<ul class="shape-design">
		<li class="shape1">&nbsp;</li>
		<li>My Rating Data</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Content</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
</div>
	<div class="row">
	<c:choose>
		<c:when test="${not empty topic.parentTopic.id }">
			<div class="col-md-12"><h5>Root://<b:breadcrumb separator="/" serialId="${topic.parentTopic.id}" type="TOPIC" />/${topic.name}</h5></div>
		</c:when>
		<c:otherwise>
			<div class="col-md-12"><h5>Root://<b:breadcrumb separator="/" serialId="${topic.category.id}" type="CATEGORY" />/${topic.name}</h5></div>
		</c:otherwise>
	</c:choose>
	</div>
	<div class="expertbox">
	  <div class="form-box mt-4">
         <div class="login-heading-inner-form">Content ID (auto assigned)</div>
		
			
				
					<form:form commandName="siteForm" action="addSite.htm" enctype="multipart/form-data"
						method="post" class="form-wrapper" id="addSite" name="addSite">
					<div class="row">
					<div class="col-md-6">
							<label>Content Name:</label>
							<div class="form-group">
									<form:input path="name" class="input_border form-control email"  id="siteName" name="siteName" value="" />
									</div>
									</div>	
									<div class="col-md-6">				
							<label>URL:</label>
							<div class="form-group">
									<form:input path="url" class="input_border form-control email"  id="txtEmail" value="" />
									<i style="font-size: 10px">(Enter full URL example: http://www.example.com/index.htm)</i>
									</div>
							</div>
				
							
							<c:if test="${flag eq 1}">
							<div class="col-md-6">
								<label>Logo:</label>
									<div class="form-group">
										<form:input type="file" class="form-control" path="siteLogo" id="the_file" size="18" cssClass="upload-browse" />
										</div>
									</div>
								
							</c:if>
								
								<form:hidden path="categoryId"  id="category"/>
								<form:hidden path="topicId"  id="topic"/>
								<div class="col-md-12 text-center">
							<input type="button"  name="Add Content" value="Save" id="btnLogin" onclick="submitForm()">
							</div>
						</ul>
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