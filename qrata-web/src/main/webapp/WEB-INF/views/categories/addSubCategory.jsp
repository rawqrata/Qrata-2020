<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

$(document).ready(function() {
 		$("#btnLogin").click(function(e) {
 			e.preventDefault();
 			validateAddSubCategory();
 		});
		
	$("#addSubCategory").submit(function(e) {
		e.preventDefault();
		validateAddSubCategory();
	});
	
});
function validateAddSubCategory(){  
	//alert('hello');
    var data=document.getElementById("subCategoryName").value;  
    if(data == null || data == "") {  
        alert("Name is required");  
        return false;  
      }  
//       if(data != "" && data.indexOf(" ") == 0) {  
//   	      alert("First character of the name should not be a space.");  
//   	      return false;  
//   	 }
      data = trimField(data);
      
      var regExp = /^[-a-zA-Z0-9 &_:,.' ']+$/;
  	  if (!data.match(regExp)) {
  		alert("Special Characters are not allowed! Please provide only alphabets and numbers.");
  		return false;
  	} 
    	
     var myUrl="existsSubCategory.htm";
      if(data != "") {
    	  $.ajax({
 	  		type: "GET",
 	  		url: myUrl,
 	  		data:{name : data,id:document.getElementById("id").value },		
 	  		dataType: "json",
 	  		contentType: "application/json",
 	  		success: function(response) {
 	  			if(response.exists) {
 	  				$("#msgDiv").html("<span style='color: red'>The subcategory name already exists.</span>");
 	  				return false;
 	  			} else {
 	  				$("#msgDiv").html("");
 	  				document.forms["addSubCategory"].submit();
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
		 var myUrl="existsSubCategory.htm";
	 	$.ajax({
	 		type: "GET",
	 		url: myUrl,
		  	data:{name : val,id:document.getElementById("id").value },	
	 		dataType: "json",
	 		contentType: "application/json",
	 		success: function(response) {
	 			if(response.exists) {
	 				$("#msgDiv").html("<span style='color: red'>The subcategory name already exists.</span>");
	 			} else {
	 				$("#msgDiv").html("");
	 			}
	 		},
	 		  error: function() {
	 			 alert("error");
	           }
	 		
	 	});
	
	 	return false;
	 }
</script>
</head>
<body>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
<!-- 		<li class="shape1">&nbsp;</li> -->
<!-- 		<li>Manage</li> -->
<%-- 		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li> --%>
<!-- 		<li>Categories</li> -->
<%-- 		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li> --%>
<!-- 		<li>Add Sub Category</li> -->
<%-- 		<c:choose> --%>
    <c:choose>
	<c:when test="${type eq 'd'}">
		<li class="shape1">&nbsp;</li>
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Sub Categories</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Sub Category</li>
	</c:when>
	<c:otherwise>
		<li class="shape1">&nbsp;</li>
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Categories</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Sub Category</li>
	</c:otherwise>
		</c:choose>
	
	</ul>
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	<div class="breadcrumb-style"><h5><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" /></h5></div>
	<div class="center-table">
		<div class="bo-login-box">
			<div class="login-heading-inner">SubCategory Info ID (auto
				assigned)</div>
			<div class="site-infologin-section" style="width: 100%;">
				<div class="defaul-login-form2">
				<form action="saveSubCategory.htm" method="post" id="addSubCategory" name="addSubCategory">
					<ul>
						<li><label>Name:</label>
						<div id="msgDiv" style="float: left;"></div>
							<p>
								<input type="text" onblur="existsSubCategory(this.value);"
									onfocus="if (this.value=='email') this.value='';"
									class="input_border email" name="subCategoryName" id="subCategoryName"
									value="">
									<input type = "hidden" name = "id" id = "id" value="${id}">
							</p></li>

						<li><div style="text-align: center;">
								<input type="button" name="btn" value="Save" id="btnLogin" >
							</div></li>


					</ul>
						</form>
				</div>

			</div>
		</div>


	</div>
</body>
</html>