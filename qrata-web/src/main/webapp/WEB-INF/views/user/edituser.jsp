<%@page import="com.insonix.qrata.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
            <div class="success-box">
               <c:out value="Data has been updated successfully" />
            </div>
         </c:when>
      </c:choose>
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
               <li>User</li>
               <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
               <li>Edit User</li>
            </ul>
         </div>
         <div class="col-md-4 col-4 text-right">
            <div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
         </div>
      </div>
      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Edit BO User Accounts</div>
         <form:form modelAttribute="userData" action="editUser.htm" method="post" name="addUser" id="addUser">
            <div class="row">
               <div class="col-md-6">
                  <label>First Name:</label>
                  <div class="form-group">
                     <form:input path="firstName" 
                        onblur="if (this.value=='') this.value='';"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email required" name="firstName"
                        id="firstName" value="" />
                  </div>
               </div>
               <div class="col-md-6">
                  <label>Last Name:</label>
                  <div class="form-group">
                     <form:input path="lastName"
                        onblur="if (this.value=='') this.value='';"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email required" name="lastName"
                        id="lastName" value="" />
                  </div>
               </div>
               <div class="col-md-6">
                  <label>Login:</label>
                  <div class="form-group">
                     <form:input path="userName"
                        readonly="true"
                        onblur="existsLogin(this.value);"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email required" name="login" id="login"
                        value="" />
                  </div>
               </div>
               <div class="col-md-6">
                  <label>Password:</label>
                  <div class="form-group">
                     <form:password path="password"
                        onblur="if (this.value=='');"
                        onfocus="if (this.value=='password') this.value='';"
                        class="input_border form-control required" 
                        name="userPassword" id="userPassword" value="" showPassword="true"/>
                     <span id="validatePassword" class="validation-action"></span>
                  </div>
               </div>
               <div class="col-md-6">
                  <label>Re-enter Password:</label>
                  <div class="form-group">
                     <form:password path="confirmPassword"
                        onblur="if (this.value=='') ;"
                        onfocus="if (this.value=='password') this.value='';"
                        class="input_border form-control required" 
                        name="repassword" id="repassword" value="${userData.password }" showPassword="true" />
                     <span id="validatePassword" class="validation-action"></span></li>
                  </div>
               </div>
               <div class="col-md-6">
                  <label>Email Address:</label>
                  <div class="form-group">
                     <form:input path="email"
                        onblur="existsEmail(this.value);"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email required" name="email" id="email"
                        value="" />
                  </div>
               </div>
               <div class="col-md-6">
                  <%--                                     <label><form:radiobutton path="roleId" value="1" />Admin Group</label> --%>
                  <%--                                     <label><form:radiobutton path="roleId" value="2" />Editor Group</label> --%>
                  <%--                                     <label><form:radiobutton path="roleId" value="3" />Expert Group</label> --%>
                  <c:forEach items="${roles}" var="role" varStatus="idx">
                     <label>
                        <c:choose>
                           <c:when test="${USERROLEID eq ADMINID}">
                              <c:choose>
                                 <c:when test="${idx.first}">
                                    <form:radiobutton path="roleId" cssClass="roles" id="roleId" value="${role.id}" checked="true" />
                                 </c:when>
                                 <c:otherwise>
                                    <form:radiobutton path="roleId" cssClass="roles" id="roleId" value="${role.id}" />
                                 </c:otherwise>
                              </c:choose>
                           </c:when>
                           <c:when test="${USERROLEID eq EDITORID}">
                              <c:if test="${idx.last}">
                                 <form:radiobutton path="roleId" cssClass="roles" id="roleId" value="${role.id}" checked="true" />
                              </c:if>
                           </c:when>
                        </c:choose>
                        <c:choose>
                           <c:when test="${USERROLEID eq ADMINID}">
                              <c:out value="${role.name}" />
                              Group
                           </c:when>
                           <c:when test="${USERROLEID eq EDITORID}">
                              <c:if test="${role.name eq 'EXPERT'}">
                                 <c:out value="${role.name}" />
                                 Group
                              </c:if>
                           </c:when>
                        </c:choose>
                     </label>
                  </c:forEach>
               </div>
               <form:hidden path="id"/>
               <div class="col-md-6 text-right">
                  <input type="button" name="btn" value="Save" id="btnLogin" onclick="submitForm();">
               </div>
               <!-- 								<li><input type="button" name="btn" value="Save" id="btnLogin" onclick="validateAddUser();"></li> -->
               <input type="hidden" name ="prp" id ="prp" value="${prp}"/>
               <input type="hidden" name ="orp" id ="orp" value="${orp}"/>
               <input type="hidden" name ="sfrp" id ="sfrp" value="${sfrp}"/>
               <input type="hidden" name="sunrp" id="sunrp" value="${sunrp}"> 
         </form:form>
         </div>
      </div>
      </div>
      </div>
   </body>
</html>