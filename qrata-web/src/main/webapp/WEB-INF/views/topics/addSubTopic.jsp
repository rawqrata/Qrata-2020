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
 			validateAddSubTopic();
 		});
		
	$("#addSubTopic").submit(function(e) {
		e.preventDefault();
		validateAddSubTopic();
	});
	
});
  
function validateAddSubTopic(){  
	//alert('hello');
    var data=document.getElementById("subTopicName").value;  
    if(data == null || data == "") {  
        alert("Name is required");  
        return false;  
      }  
    data = trimField(data);
//       if(data != "" && data.indexOf(" ") == 0) {  
//   	      alert("First character of the name should not be a space.");  
//   	      return false;  
//   	 }
      var regExp = /^[-a-zA-Z0-9 &_:,.' ']+$/;
  	  if (!data.match(regExp)) {
  		alert("Special Characters are not allowed! Please provide only alphabets and numbers.");
  		return false;
  	} 
    	
  	  var myUrl="existsSubTopic.htm";
     if(data != "") {
   	      $.ajax({
   	  		type: "GET",
   	     	url: myUrl,
	  	    data:{name : data,id:document.getElementById("id").value },
   	  		dataType: "json",
   	  		contentType: "application/json",
   	  		success: function(response) {
   	  			if(response.exists) {
   	  				$("#msgDiv").html("<span style='color: red'>The subtopic name already exists.</span>");
   	  				return false;
   	  			} else {
   	  				$("#msgDiv").html("");
   	  				document.forms["addSubTopic"].submit();
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
	   function existsSubTopic(val) {
		   var myUrl="existsSubTopic.htm";
		   	$.ajax({
		   		type: "GET",
		   		url: myUrl,
			  	data:{name : val,id:document.getElementById("id").value },
		   		dataType: "json",
		   		contentType: "application/json",
		   		success: function(response) {
		   			if(response.exists) {
		   				$("#msgDiv").html("<span style='color: red'>The subtopic name already exists.</span>");
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
<div class="row">
<div class="col-md-12">
<div class="date-text">&nbsp;</div>
</div>
</div>
	
	<div class="row">
	<div class="col-md-8 col-8">
	<ul class="shape-design">
<!-- 		<li class="shape1">&nbsp;</li> -->
<!-- 		<li>Manage</li> -->
<%-- 		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li> --%>
<!-- 		<li>Topics</li> -->
<%-- 		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li> --%>
<!-- 		<li>Add Sub-Topics</li> -->
			 <c:choose>
	<c:when test="${type eq 'c'}">
		
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Sub Topics</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Sub Topics</li>
	</c:when>
	<c:otherwise>
		
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Topics</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Sub Topic</li>
	</c:otherwise>
		</c:choose>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12">
	<h5><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="TOPIC" /></h5>
	</div>
	</div>
	
	      <div class="form-box mt-4">
         <div class="login-heading-inner-form">SubTopic Info ID (auto assigned)</div>
			
		
				
				<form action="saveSubTopic.htm" method="post" id="addSubTopic" name="addSubTopic">
				<div class="row">
				<div class="col-md-12">
				<div id="msgDiv"></div>
					<label>Name:</label>
					<div class="form-group">
						
							
								<input type="text" onblur="existsSubTopic(this.value);"
									onfocus="if (this.value=='email') this.value='';"
									class="input_border form-control email" name="subTopicName" id="subTopicName"
									value="">
									<input type="hidden" name="id" id="id" value="${id}">
							</div>
</div>
						<div class="col-md-12 text-center">
								<input type="button" name="btn" value="Save" id="btnLogin">
							</div>



					</div>
					</form>

			

			
		</div>


	
</body>
</html>