<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.insonix.qrata.constants.Constants"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ADMINID" value="<%=Constants.Roles.ADMIN.getValue()%>"></c:set>
<c:set var="EDITORID" value="<%=Constants.Roles.EDITOR.getValue()%>"></c:set>
<c:set var="EXPERTID" value="<%=Constants.Roles.EXPERT.getValue()%>"></c:set>
<c:set var="USERROLEID" value="${sessionScope.user.role.id}"></c:set>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Qrata- User Management</title>
      <%-- <script src="${ctx}/resources/js/jquery.validVal-4.3.2.js" type="text/javascript"></script> --%>
      <script src="${ctx}/resources/js/jquery.validVal-4.3.2-packed.js"
         type="text/javascript"></script>
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
      <div class="row">
         <div class="col-md-12">
            <div class="date-text">&nbsp;</div>
         </div>
      </div>
      <div class="row">
         <div class="col-md-8 col-8">
            <ul class="shape-design">
               <li class="shape1">&nbsp;</li>
               <c:choose>
                  <c:when test="${USERROLEID ne EDITORID}">
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
         </div>
         <div class="col-md-4 col-4">
            <div class="back-button">
               <a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
               Go Back </a>
            </div>
         </div>
      </div>
      <div class="row">
         <div class="col-md-12">
            <div id="addUserError"
               style="display: none; float: left; width: 370px; height: 30px; padding: 8px 10px 8px 24px;"
               class="error-none">You must fill in all of the fields.</div>
         </div>
      </div>
      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Add/Edit BO User Accounts</div>
         <form:form commandName="userForm" action="saveUser.htm" method="post"
            name="addUser" class="form-wrapper" id="addUser">
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
                  <div id="msgDiv" style="float: left;"></div>
                  <label>Login:</label>
                  <div class="form-group">
                     <form:input path="userName" onblur="existsLogin(this.value);"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email required" name="login"
                        id="login" value="" />
                  </div>
               </div>
               <div class="col-md-6">
                  <label>Password:</label>
                  <div class="form-group">
                     <form:password path="password" onblur="if (this.value=='') ;"
                        onfocus="if (this.value=='password') this.value='';"
                        class="input_border form-control required" name="userPassword"
                        id="userPassword" />
                     <span id="validatePassword" class="validation-action"></span>
                  </div>
               </div>
               <div class="col-md-6">
                  <label>Re-enter Password:</label>
                  <div class="form-group">
                     <form:password path="confirmPassword"
                        onblur="if (this.value=='');"
                        onfocus="if (this.value=='password') this.value='';"
                        class="input_border form-control required" name="repassword"
                        id="repassword" />
                     <span id="validatePassword" class="validation-action"></span>
                  </div>
               </div>
               <div class="col-md-6">
                  <div id="msg1Div" style="float: left;"></div>
                  <label>Email Address:</label>
                  <div class="form-group">
                     <form:input path="email" onblur="existsEmail(this.value);"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email required" name="email"
                        id="email" value="email" />
                  </div>
               </div>
               <div class="col-md-6">
                  <c:forEach items="${roles}" var="role" varStatus="idx">
                     <label>
                        <c:choose>
                           <c:when test="${USERROLEID eq ADMINID}">
                              <c:choose>
                                 <c:when test="${idx.first}">
                                    <form:radiobutton path="roleId" cssClass="roles" id="roleId"
                                       value="${role.id}" checked="true" />
                                 </c:when>
                                 <c:otherwise>
                                    <form:radiobutton path="roleId" cssClass="roles" id="roleId"
                                       value="${role.id}" />
                                 </c:otherwise>
                              </c:choose>
                           </c:when>
                           <c:when test="${USERROLEID eq EDITORID}">
                              <c:if test="${idx.last}">
                                 <form:radiobutton path="roleId" cssClass="roles" id="roleId"
                                    value="${role.id}" checked="true" />
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
               <div class="col-md-6 text-right">
                  <input type="button" name="btn" value="Save" id="btnLogin"
                     onclick="validateAddUser();">
               </div>
            </div>
         </form:form>
      </div>
   </body>
</html>