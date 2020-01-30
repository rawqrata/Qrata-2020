<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<c:import url="../../page.jsp"/>
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

	<div class="center-table">
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
<!-- 		<li class="shape1">&nbsp;</li> -->
<!-- 		<li>Manage</li> -->
<%-- 		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li> --%>
<!-- 		<li>Topics</li> -->
<%-- 		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li> --%>
<!-- 		<li>Add Sub-Topics</li> -->
			 <c:choose>
	<c:when test="${type eq 'c'}">
		<li class="shape1">&nbsp;</li>
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Sub Topics</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Sub Topics</li>
	</c:when>
	<c:otherwise>
		<li class="shape1">&nbsp;</li>
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Topics</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Sub Topic</li>
	</c:otherwise>
		</c:choose>
	</ul>
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
<%-- 	<div class="breadcrumb-style"><h5><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="TOPIC" /></h5></div>
 --%>	
		<div class="bo-login-box">
			<div class="login-heading-inner">SubTopic Info ID (auto
				assigned)</div>
			<div class="site-infologin-section" style="width: 100%;">
				<div class="defaul-login-form2">
				<form action="saveSubTopic.htm" method="post" id="addSubTopic" name="addSubTopic">
					<ul>
						<li><label>Name:</label>
						<div id="msgDiv" style="float: left;"></div>
							<p>
								<input type="text" onblur="existsSubTopic(this.value);"
									onfocus="if (this.value=='email') this.value='';"
									class="input_border email" name="subTopicName" id="subTopicName"
									value="">
									<input type="hidden" name="id" id="id" value="${id}">
							</p></li>

						<li><div style="text-align: center;">
								<input type="button" name="btn" value="Save" id="btnLogin">
							</div></li>


					</ul>
					</form>

				</div>

			</div>
		</div>


	</div>
</body>
</html>