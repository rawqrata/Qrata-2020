<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <script src="${ctx}/resources/js/qrata.js" type="text/javascript"></script>
      <script type="text/javascript">
         function validate(){  
         	//alert('hello');
             var email = document.getElementById("txtEmail").value;
             if(email == null || email == "") {  
                 alert("Backoffice Id is required");  
                 return false;  
               }  
               if(email != "" && email.indexOf(" ") == 0) {  
           	      alert("First character of the backoffice id should not be a space.");  
           	      return false;  
           	 }
             var password=document.getElementById("txtPassword").value;  
             if(password == null || password == "") {  
                 alert("Password is required");  
                 return false;  
               }  
               if(password != "" && password.indexOf(" ") == 0) {  
           	      alert("First character of the password should not be a space.");  
           	      return false;  
           	 }
               var email=document.getElementById("txtEmail").value;
               if(!role.contains(email)){
             	  alert("invalid backoffice id");
               }
             
               return true;  
            }  
          
      </script>
   </head>
   <body>
      <section class="loginbox">
         <%-- 	<div class="back-logo-qrata">
            <img src="${ctx}/resources/images/back-bg-logo.png" />
            </div> --%>
         <div class="row no-gutters">
            <div class="col-md-6">
               <div class="left-side-infobox">
                  <div class="row">
                     <div class="col-md-12 pt-5 text-center">
                        <div class="measshetitle">
                           <div class="logo-t"><img  src="${ctx}/resources/images/logonew.png" alt="Q/Rata Logo" /></div>
                        </div>
                     </div>
                  </div>
                  <div class="heading-main"  style="background:url('${ctx}/resources/images/loginback.gif'); background-size:cover; width:100%;">
                     <span class="headtag">World's Most Reliable Knowledge - Guaranteed</span>
                  </div>
               </div>
            </div>
            <div class="col-md-6">
               <div class="right-side-form">
                  <div class="logo-qrata-new">
                  </div>
                  <!-- 	<div class="back-button" style="margin-top:1%;"><a href="loginForm.htm" >&#60;&#60; Go Back </a></div> -->
                  <div class="login-form">
                     <div class="top-logo-right">
                        <spn class="taglogin">Retrieve your password</spn>
                     </div>
                     <form name="f" action="recoverPassword.htm"  method="POST" onsubmit="return validate();" >
                        <c:choose>
                           <c:when test="${not empty error}">
                              <span style='color: red'>
                                 <c:out value="${error}" />
                              </span>
                           </c:when>
                           <c:when test="${not empty success}">
                              <span style='color: green'>
                                 <c:out value="${success}" />
                              </span>
                           </c:when>
                        </c:choose>
                        <c:if test="${not empty error}">
                           <div class="errorblock">
                           </div>
                        </c:if>
                        <h1 style="color:#3D3F42;padding-top:7px;">Please provide your Email Address to retrieve your password</h1>
                        <div class="row mt-4">
                           <div class="col-md-12 mb-2">
                              <label>Email Address:</label>
                              <div class=:form-group">
                                 <input type="text"
                                    onblur="if (this.value=='') this.value='email';"
                                    onfocus="if (this.value=='email') this.value='';"
                                    class="form-control email" name="emailId" id="txtEmail"
                                    value="email">
                              </div>
                           </div>
                           <div class="col-md-12 mb-2 text-center">
                              <input type="submit" id="btnLogin" value="Submit" name="submit">
                           </div>
                           <div class="col-md-12 text-center">
                              <span class="backlink"><a href="loginForm.htm">Go to login page &#62;&#62;</a></span>
                           </div>
                        </div>
                     </form>
                  </div>
                  <span class="copyright">
                  &copy; 2020 Qrata LP All Rights Reserved.
                  </span>
               </div>
            </div>
         </div>
      </section>
   </body>
</html>