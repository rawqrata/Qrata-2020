<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"> 
 var subCategoryName="";
$(document).ready(function() {
 		$("#btnLogin").click(function(e) {
 			e.preventDefault();
 			validateEditSubCategory();
 		});
 		
 		subCategoryName=document.getElementById("name").value;
		
	$("#editSubCategory").submit(function(e) {
		e.preventDefault();
		validateEditSubCategory();
	});
	
});
  
function validateEditSubCategory(){  
	//alert('hello');
    var data=document.getElementById("name").value;  
    if(data == null || data == "") {  
        alert("Name is required");  
        return false;  
      }  
      data = trimField(data);
//       if(data != "" && data.indexOf(" ") == 0) {  
//   	      alert("First character of the name should not be a space.");  
//   	      return false;  
//   	 }  
      var regExp = /^[-a-zA-Z0-9&_:,.' ']+$/;
  	  if (!data.match(regExp)) {
  		alert("Special Characters are not allowed! Please provide only alphabets and numbers.");
  		return false;
  	} 
  
      if(data != "" && subCategoryName.trim() != data.trim()){
    	   $.ajax({
 	  		type: "GET",
 	  		url: "existsSubCategory.htm?name="+data+"&id="+document.getElementById("id").value,
 	  		dataType: "json",
 	  		contentType: "application/json",
 	  		success: function(response) {
 	  			if(response.exists) {
 	  				$("#msgDiv").html("<span style='color: red'>The Subcategory name already exists.</span>");
 	  				return false;
 	  			} else {
 	  				$("#msgDiv").html(" ");
 	  				document.forms["editSubCategory"].submit();
 	  			}
 	  		},
 	  		  error: function() {
 	  			 alert("error");
 	           }
 	  	  });  
      }
   }  
 </script>
<script type="text/javascript">
function existsSubCategory(val) {
		//alert("hello1");
		if(subCategoryName.trim() != data.trim()){
			$.ajax({
				type: "GET",
				url: "existsSubCategory.htm?name="+val+"&id="+document.getElementById("id").value,
				dataType: "json",
				contentType: "application/json",
				success: function(response) {
					if(response.exists) {
						$("#msgDiv").html("<span style='color: red'>The Subcategory name already exists.</span>");
						//$("#msgDiv1").html("<span style='color: red'> The odering already exists.</span>");
					} else {
						$("#msgDiv").html("");
						//$("#msgDiv1").html("");
					}
				},
				  error: function() {
					 //alert("error");
		          }		
			});
			return false;
		}
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
		<li>Sub Categories</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Edit Sub Category</li>
	</ul>
	<div class="col-md-4 col-4">
		<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12"><h5><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" /></h5></div>
	</div>
	<div class="catbox">
		      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Category Info ID (auto
            assigned)
         </div>
			
			
				
				<form action="editSubCategory.htm" name="editSubCategory" id="editSubCategory" method="post">
				<div class="row">
				<div class="col-md-12">
				<div id="msgDiv"></div>
						<label>Name:</label>
						<div class="form-group">
						
							
								<input  type="text" onblur="existsSubCategory(this.value);"
									onfocus="if (this.value=='email') this.value='';" name="name"  value="${category}"
									class="input_border form-control email" id="name"/>
									<input type="hidden" value="${subCategoryId}" name="subCategoryId" id="subCategoryId"/>
									<input type="hidden" name="categoryId" id="id" value="${id}">
									</div>
						
</div>
						<div class="col-md-12 text-center">
								<input type="button" name="btn" value="Save" id="btnLogin" >
								<input type="button" name="btn" onclick="javascript:historyButton()" value="Cancel" id="btnLogin" >
							</div>
					
					<input type="hidden" name ="prp" id ="prp" value="${prp}"/>
					<input type="hidden" name ="orp" id ="orp" value="${orp}"/>
					<input type="hidden" name ="sfrp" id ="sfrp" value="${sfrp}"/>
					<input type="hidden" name="sunrp" id="sunrp" value="${sunrp}"> 
					<input type="hidden" name="ale" id="ale" value="${ale}">
					</div>
				</form>

				

			
		</div>


	</div>
</body>
</html>