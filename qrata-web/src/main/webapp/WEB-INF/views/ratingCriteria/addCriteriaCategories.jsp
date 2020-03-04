<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="${ctx}/resources/ckeditor/ckeditor.js"></script>

<script src="${ctx}/resources/js/qrata.js" type="text/javascript"></script>
<script type="text/javascript"> 
	$(document).ready(function() {
 		$("#btnLogin").click(function(e) {
 			e.preventDefault();
 			validateAddCriteriaCategories();
 		});
		
		$("#addCriteriaCategories").submit(function(e) {
			e.preventDefault();
			validateAddCriteriaCategories();
		});
		
	});

function validateAddCriteriaCategories() {  
	//alert('hello');
    var data=document.getElementById("criteria").value;  
    if(data == null || data == "") {  
        alert("Name is required");  
        return false;  
      }  
    data = trimField(data);
//       if(data != "" && data.indexOf(" ") == 0) {  
//   	      alert("First character of the name should not be a space.");  
//   	      return false;  
//   	 }
      
      
      if(data != "") {
	      $.ajax({
	  		type: "GET",
	  		url: "existsCriteriaCategories.htm?name="+data,
	  		dataType: "json",
	  		contentType: "application/json",
	  		success: function(response) {
	  			if(response.exists) {
	  				$("#msgDiv").html("<span style='color: red'>The criteria group name already exists.</span>");
	  				return false;
	  			} else {
	  				$("#msgDiv").html("");
	  				document.forms["addCriteriaCategories"].submit();
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
function existsCriteriaCategories(val) {
	$.ajax({
		type: "GET",
		url: "existsCriteriaCategories.htm?name="+val,
		dataType: "json",
		contentType: "application/json",
		success: function(response) {
			if(response.exists) { 
				$("#msgDiv").html("<span style='color: red'>The criteria group name already exists.</span>");
			}  else {
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
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Criteria Category has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
	</c:choose>
	</div>
	</div>
<div class="row">
<div class="col-md-12">
	<div class="date-text">&nbsp;</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-8 col-8">
	<ul class="shape-design">
		<li>Criteria Mgmt</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Criteria Group</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Criteria Group</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12"><h5>Root//</h5></div>
	</div>
			      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Criteria Info ID (auto
				assigned)
         </div>
		
			<div class="row">
				<div class="col-md-12">
				<div id="addDomainError"class="error-none">You must fill in the field.</div>
				</div>
				</div>
				<form action="saveCriteriaCategory.htm" method="post" id="addCriteriaCategories" name="addCriteriaCategories"  >
				<div class="row">
				<div class="col-md-12">
				<div id="msgDiv"></div>
						<label>Name:</label>
						<div class="form-group">
							
						
								<input type="text" onblur="existsCriteriaCategories(this.value);"
									onfocus="if (this.value=='email') this.value='';"
									class="input_border form-control email" name="criteriaCategory" id="criteria"
									value=""/>
									<input type="hidden" name="criteria" id = "id" value="${id}"/>
					</div>
							</div>
			               <div class="col-md-12 text-center">
								<input type="button" name="btn" value="Save" id="btnLogin"  />
							</div>
							
</div>

						</form>
			
</div>
		
		



</body>
</html>