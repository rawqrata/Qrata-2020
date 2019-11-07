<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.qrata.enums.Constants"%>
<c:import url="../../page.jsp"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Qrata- User Management</title>

<%-- <script src="${ctx}/resources/js/jquery.validVal-4.3.2.js" type="text/javascript"></script> --%>
<script src="${ctx}/resources/js/jquery.validVal-4.3.2-packed.js" type="text/javascript"></script>

<script type="text/javascript">
	function existsLogin(val) {
	//alert("hello");
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
			  alert("error");
           }
			
		});
	
		return false;
	}
	
	function existsEmail(val) {
	//alert("hello");
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
		<li class="shape1">&nbsp;</li>
	<c:choose>
	<c:when test="${ture}">
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>User</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add User</li>
		</c:when>
		<c:otherwise>
		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Editors</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Add Expert</li>
		</c:otherwise>
	</c:choose>
	</ul>
	<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	
	<div class="center-table">

		<div class="bo-login-box">
			<div class="login-heading-inner">Add/Edit BO User Accounts</div>
			
			<div class="bo-login-section">
				<div class="defaul-login-form1">
				
				<div id="addUserError"
				style="display: none; float: left; width: 370px; height: 30px; padding: 8px 10px 8px 24px;"
				class="error-none">You must fill in all of the fields.</div>
				
					<div style="text-align: left;">
						<form:form modelAttribute="userForm" action="saveUser.htm"
							method="post" name="addUser" class="form-wrapper" id="addUser">
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
											onblur="existsLogin(this.value);"
											onfocus="if (this.value=='email') this.value='';"
											class="input_border email required" name="login" id="login"
											value="" />
									</p></li>
								<li><label>Password:</label>
									<p>
										<form:password path="password"
											onblur="if (this.value=='') ;"
											onfocus="if (this.value=='password') this.value='';"
											class="input_border required" 
											name="userPassword" id="userPassword" />
									</p> <span id="validatePassword" class="validation-action"></span></li>
								<li><label>Re-enter Password:</label>
									<p>
										<form:password path="confirmPassword"
											onblur="if (this.value=='');"
											onfocus="if (this.value=='password') this.value='';"
											class="input_border required" 
											name="repassword" id="repassword" />
									</p> <span id="validatePassword" class="validation-action"></span></li>
								<li><label>Email Address:</label>
								<div id="msg1Div" style="float: left;"></div>
									<p>
										<form:input path="email"
											onblur="existsEmail(this.value);"
											onfocus="if (this.value=='email') this.value='';"
											class="input_border email required" name="email" id="email"
											value="email" />
									</p>
								</li>
                                   <%--   <form:checkboxes element="div class='spring-checkboxes'" items="${allRoles}" path="roles" /> --%>
								<li>
								</li>
								<li><input type="button" name="btn" value="Save" id="btnLogin" onclick="validateAddUser();"></li>
							</ul>
						</form:form>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>