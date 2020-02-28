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
var topicName1="";
$(document).ready(function() {
 		$("#btnLogin").click(function(e) {
 			e.preventDefault();
 			validateEditTopic();
 		});
 		
 		topicName1=document.getElementById("topicName").value;
 		
	$("#editTopic").submit(function(e) {
		e.preventDefault();
		validateEditTopic();
	});
	
});
	function validateEditTopic() {
		
		var data = document.getElementById("topicName").value;
		if (data == null || data == "") {
			alert("Name is required");
			return false;
		}
		data = trimField(data);
// 		if (data != "" && data.indexOf(" ") == 0) {
// 			alert("First character of the name should not be a space.");
// 			return false;
//   		 } 
		  var regExp = /^[-a-zA-Z0-9&_:,.' ']+$/;
	  	  if (!data.match(regExp)) {
	  		alert("Special Characters are not allowed! Please provide only alphabets and numbers.");
	  		return false;
	  	} 
		var url = "";
		
		<c:choose>
			<c:when test="${empty topicRef.parentTopic}">
				url = "existsTopic.htm?name="+data+"&id="+$("#subCatId").val();
			</c:when>
			<c:otherwise>
				url = "existsSubTopic.htm?name="+data+"&id="+$("#topicId").val();
			</c:otherwise>
		</c:choose>
		//alert("validateEditTopic() : "+url);
		
     if(data != "" && topicName1.trim() != data.trim())
	      $.ajax({
	  		type: "GET",
	  		url: url,
	  		dataType: "json",
	  		contentType: "application/json",
	  		success: function(response) {
	  			//alert(response.exists);
	  			if(response.exists) {
	  				<c:choose>
	  				<c:when test="${empty topicRef.parentTopic}">
	  				$("#msgDiv").html("<span style='color: red'>The topic name already exists.</span>");
	  				</c:when>
	  				<c:otherwise>
	  				$("#msgDiv").html("<span style='color: red'>The subtopic name already exists.</span>");
	  				</c:otherwise>
	  			</c:choose>
	  			
	  				return false;
	  			} else {
	  				//alert("On Click Ajax");
	  				$("#msgDiv").html("");
	  				document.forms["editTopic"].submit();
	  			}
	  		},
	  		  error: function() {
	  			 alert("error");
	           }
	  	  });  
     }
</script>
<script type="text/javascript">
	function existsTopic(val) {
		//alert("hello1");
		if(topicName1.trim() != data.trim()){
		var url = "";
		<c:choose>
			<c:when test="${empty topicRef.parentTopic}">
				url = "existsTopic.htm?name="+val+"&id="+$("#subCatId").val();
			</c:when>
			<c:otherwise>
				url = "existsSubTopic.htm?name="+val+"&id="+$("#topicId").val();
			</c:otherwise>
		</c:choose>
		//alert("hello"+url);
			$.ajax({
				type: "GET",
				url: url,
				dataType: "json",
				contentType: "application/json",
				success: function(response) {
					if(response.exists) {
						<c:choose>
		  				<c:when test="${empty topicRef.parentTopic}">
		  				$("#msgDiv").html("<span style='color: red'>The topic name already exists.</span>");
		  				</c:when>
		  				<c:otherwise>
		  				$("#msgDiv").html("<span style='color: red'>The subtopic name already exists.</span>");
		  				</c:otherwise>
		  			</c:choose>
					} else {
						//alert("good");
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
		<c:choose>
	 <c:when test="${empty topicRef.parentTopic}">
		
			<li>Manage</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Topics</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Edit Topic</li>
	</c:when>
	<c:otherwise>
			
			<li>Manage</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>SubTopics</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Edit SubTopics</li>

	</c:otherwise>
			</c:choose>
		</ul>
		</div>
		<div class="col-md-4 col-4">
		
		<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;Go Back </a></div>
		</div>
		</div>
		<c:choose>
			<c:when test="${not empty topicRef.parentTopic}">
			<div class="row">
				<div class="col-md-12">
					<input type="hidden" name="topicId" id="topicId" value="${topicRef.parentTopic.id}" />
					<h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="TOPIC" /></h5>
				</div>
				</div>
			</c:when>
			<c:otherwise>
			<div class="row">
				<div class="col-md-12">
				<h5><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" /></h5>
				</div>
				</div>
			</c:otherwise>
		</c:choose>
	      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Topic Info ID (auto assigned)
         </div>
						
					<form:form action="editTopic.htm" commandName="topic" method="POST" id="editTopic" name="editTopic" >
					<div class="row">
						<div class="col-md-12">
								<div id="msgDiv"></div>
							<label>Name:</label>
							<div class="form-group">
						
								
									<form:input type="text" onblur="existsTopic(this.value);"
									onfocus="if (this.value=='email') this.value='';"
										class="input_border form-control email" path="name" id="topicName"
										/> 
										<form:input type="hidden" path="uuid" ></form:input>
										<form:input type="hidden" path="subCatId" name="subCatId" id="subCatId" value="${subCatId}"  />
										<form:input type="hidden" path="type" />
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
					</form:form>
			

			
		


	</div>
</body>
</html>