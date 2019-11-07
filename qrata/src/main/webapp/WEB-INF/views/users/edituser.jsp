<%@page import="com.qrata.enums.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:import url="../../page.jsp"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata- User Management</title>
	
	<script type="text/javascript">
	var userName = "empty";
	var email = "empty";
	var subString = window.location.search.substring(1);
	var url = subString.split("url=");
	
	$(document).ready(function() {
		userName = document.getElementById("login").value;
		email = document.getElementById("email").value;
		
		document.getElementById("url").value = url[1];
	});
	function existsLogin(val) {
	//alert("hello");
	//alert(userName);
	if(userName != val){
		$.ajax({
			type: "GET",
			url: "existsLogin.htm?name="+val,
			dataType: "json",
			contentType: "application/json",
			success: function(response) {
				if(response.exists) {
					$("#msgDiv").html("<span style='color: red'>This login already exists.</span>");
				} else {
					$("#msgDiv").html("");
				}
			},
		   error: function() {
			  //alert("error");
           }
			
		});
	}
	
		return false;
	}
	
	function existsEmail(val) {
	//alert("hello");
	//alert(email);
	if(email != val){
		$.ajax({
			type: "GET",
			url: "existsEmail.htm?email="+val,
			dataType: "json",
			contentType: "application/json",
			success: function(response) {
				if(response.exists) {
					$("#msg1Div").html("<span style='color: red'>This email already exists.</span>");
				} else {
					$("#msg1Div").html("");
				}
			},
			  error: function() {
				// alert("error");
	          }
			
		});
	}
		return false;
	}
	
	function validateEditUser() {
		if (!checkRequired("addUser")) {
			$("#addUserError").text("You must fill in all of the fields.");
			$("#addUserError").show();
			alert("You must fill in all of the fields.");
			return false;
		}

		if (!validateEmail($("#email").val())) {
			$("#addUserError").text("Please enter a valid email ID.");
			$("#addUserError").show();
			$("#email").focus();
			alert("Please enter a valid emailId.");
			return false;
		}
		if($("#login").val().trim().indexOf(" ") > 0
				|| $("#login").val().indexOf(" ") >= 0){
			$("#addUserError").text("Special character and blank spaces are not allowed at Login field");
			$("#addUserError").show();
			$("#login").focus();
			alert("Spaces in login are not allowed.");
			return false;		
		}
		if(!validateFirstName($("#firstName").val())){
			$("#addUserError").text("First name and Last name should be of only alphabets");
			$("#addUserError").show();
			alert("Numeric values in Firstname & Lastname are not allowed.");
			
		    return false;
		}
		if(!validateLastName($("#lastName").val())){
			$("#addUserError").text("First name and Last name should be of only alphabets");
			$("#addUserError").show();
			alert("Numeric values in Firstname & Lastname are not allowed.");
			return false;
		}

		if ($("#userPassword").val().trim().length < 6
				|| $("#userPassword").val().trim().indexOf(" ") > 0
				|| $("#userPassword").val().indexOf(" ") >= 0) {
			$("#addUserError")
					.text("Your password must be at least 6 characters long and should not contain space(s). Please try another.");
			$("#addUserError").show();
			alert("Your password must be at least 6 characters long and should not contain space(s). Please try another.");
			return false;
		}
		if ($("#userPassword").val().trim() != $("#repassword").val().trim()) {
			$("#addUserError")
					.text("Your Password and Re-Enter password should be same.");
			$("#addUserError").show();
			alert("Your Password and Re-Enter password should be same.");
			return false;
		}
		return true;
	}
		
	function submitForm(){
		//alert(validateEditUser());
		if(validateEditUser()){
			document.forms["addUser"].submit();
		}else{
			//alert("Please Enter Valid Values");
		}
	}
</script>
</head>
<body>
		<c:choose>
			<c:when test="${param.success eq '3' }">
				<div class="success-box"><c:out value="Data has been updated successfully" /></div>
			</c:when>
		</c:choose>

						<div class="date-text">&nbsp;</div>
                        <ul class="shape-design">
                               	<li class="shape1">&nbsp;</li>
								<li>Manage</li>
								<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
								<li>User</li>
								<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
								<li>Edit User</li>
                        </ul>
                    	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>	
                	
 			 <div class="center-table">
                          
                        <div class="bo-login-box">
                                    	<div class="login-heading-inner">Edit BO User Accounts</div>
                                        	<div class="bo-login-section">
                                            	<div class="defaul-login-form1">
                                            	<div style="text-align:left;">
                     <form:form modelAttribute="userData" action="editUser.htm" method="post" name="addUser" id="addUser">
						<ul>
							<li><label>First Name:</label>
								
									<p>
										<form:input path="firstName" 
											onblur="if (this.value=='') this.value='';"
											onfocus="if (this.value=='email') this.value='';"
											class="input_border email required" name="firstName"
											id="firstName" value="" />
									</p></li>
								<li><label>Last Name:</label>
									<p>
										<form:input path="lastName"
											onblur="if (this.value=='') this.value='';"
											onfocus="if (this.value=='email') this.value='';"
											class="input_border email required" name="lastName"
											id="lastName" value="" />
									</p></li>
								<li><label>Login:</label>
								<div id="msgDiv" style="float: left;"></div>
									<p>
										<form:input path="userName"
										    readonly="true"
											onblur="existsLogin(this.value);"
											onfocus="if (this.value=='email') this.value='';"
											class="input_border email required" name="login" id="login"
											value="" />
									</p></li>
								<li><label>Password:</label>
									<p>
										<form:password path="password"
											onblur="if (this.value=='');"
											onfocus="if (this.value=='password') this.value='';"
											class="input_border"
											name="userPassword" id="userPassword" value="" showPassword="true"/>
									</p> <span id="validatePassword" class="validation-action"></span></li>
								<li><label>Re-enter Password:</label>
									<p>
										<form:password path="confirmPassword"
											onblur="if (this.value=='') ;"
											onfocus="if (this.value=='password') this.value='';"
											class="input_border"
											name="repassword" id="repassword" value="${userData.password }" showPassword="true" />
									</p> <span id="validatePassword" class="validation-action"></span></li>
								<li><label>Email Address:</label>
								<div id="msg1Div" style="float: left;"></div>
									<p>
										<form:input path="email"
											onblur="existsEmail(this.value);"
											onfocus="if (this.value=='email') this.value='';"
											class="input_border email required" name="email" id="email"
											value="" />
									</p>
								</li>
								<li>
                                   <%--  <form:checkboxes element="div class='spring-checkboxes'"  path="roles" items="${allRoles}"/> --%>
                                </li> 
                                <form:hidden path="id"/>
                                
                                <li>
									<li><input type="submit" name="btn" value="Save" id="btnLogin" onclick="submitForm();"></li>
								</li>
<!-- 								<li><input type="button" name="btn" value="Save" id="btnLogin" onclick="validateAddUser();"></li> -->
							</ul>
                         <!-- not sure these are necessary; no sorting/ordering going on here -->
							<input type="hidden" name ="prp" id ="prp" value="${prp}"/>
							<input type="hidden" name ="orp" id ="orp" value="${orp}"/>
							<input type="hidden" name ="sfrp" id ="sfrp" value="${sfrp}"/>
							<input type="hidden" name="sunrp" id="sunrp" value="${sunrp}"> 
						</form:form>
					</div>
				</div>
              </div>
             </div>
                        </div>
</body>
</html>