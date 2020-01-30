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

<Script> 
$(document).ready(function() {
 		$("#btnLogin").click(function(e) {
 			e.preventDefault();
 			 validateEditCriteriaCategory();
 		});
		

	$("#editCriteriaCategory").submit(function(e) {
		e.preventDefault();
		 validateEditCriteriaCategory();
	});
	
});

function validateEditCriteriaCategory(){  
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
     if(data != "")
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
	  				document.forms["editCriteriaCategory"].submit();
	  			}
	  		},
	  		  error: function() {
	  			 alert("error");
	           }
	  	  });  
     }

</script>
<script type="text/javascript">
function existsCriteriaCategories(val) {
		//alert("hello1");
			$.ajax({
				type: "GET",
				url: "existsCriteriaCategories.htm?name="+val+"&id="+id,
				dataType: "json",
				contentType: "application/json",
				success: function(response) {
					if(response.exists) {
						$("#msgDiv").html("<span style='color: red'>The criteria group name already exists.</span>");
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

</script>
</head>
<body>
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box">
				<c:out value="Criteria Group has been created successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box">
				<c:out value="The data has been deleted successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box">
				<c:out value="Data has been updated successfully" />
			</div>
		</c:when>
	</c:choose>
	<div class="date-text">&nbsp;</div>
	<ul class="shape-design">
		<li class="shape1">&nbsp;</li>
		<li>Criteria Mgmt</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Criteria Group</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Edit Criteria Group</li>
	</ul>
	<div class="back-button">
		<a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
			Go Back </a>
	</div>
	<div class="breadcrumb-style"><h5>Root//</h5></div>
	<div class="center-table">
		<div class="bo-login-box">
			<div class="login-heading-inner">Criteria Info ID (auto
				assigned)</div>
			<div class="site-infologin-section" style="width: 100%;">
				<div class="defaul-login-form2">
				<form:form action="editCriteriaCategory.htm"  commandName="criteria" enctype="multipart/form-data" method="POST" id="editCriteriaCategory" name="editCriteriaCategory"  >
						<ul>
							
							<li><label>Name:</label>
							<div id="msgDiv" style="float: left;"></div>
							<p>
									<form:input type="text" onblur="existsCriteriaCategories(this.value.toLowerCase());"
										onfocus="if (this.value=='email') this.value='';"
										class="input_border email" id="criteria" name="cirteria" path="name"
										/> 
										<form:input type="hidden" path="id" ></form:input>
								</p></li></ul>
		
			<div style="text-align: left;">	
				<input type="button" name="btn" value="Save" id="btnLogin"></div>
					</form:form>

				</div>

			</div>
		</div>


	</div>


</body>
</html>