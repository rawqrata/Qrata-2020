<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <script type="text/javascript">
         function validate() {  
             var email = document.getElementById("txtEmail").value;
             if(email != "" && email == "Backoffice ID or email") {  
           	      alert("Please enter Backoffice ID or email!");  
           	      return false;  
           	}
             if(email == null || email == "") {  
                 alert("Backoffice Id is required!");  
                 return false;  
             }  
             if(email != "" && email.indexOf(" ") == 0) {  
           	      alert("First character of the backoffice id should not be a space!");  
           	      return false;  
           	}
         	  	 
             var password = document.getElementById("txtPassword").value;  
             if(password == null || password == "") {
                 alert("Password is required!");  
                 return false;  
             }
         
             if(password != "" && password.indexOf(" ") == 0) {  
           	      alert("First character of the password should not be a space!");  
           	      return false;  
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
                  <div class="login-form">
                     <div class="top-logo-right">
                        <spn class="taglogin">Enter Backoffice ID and Password</spn>
                     </div>
                     <form name="f" action="<c:url value='j_spring_security_check'/>"
                        method="POST" onsubmit="return validate();" >
                        <!--login-->
                        <c:if test="${not empty msg && msg=='e'}">
                           <span style='color: red'>Your session has expired.Please login again.</span>
                        </c:if>
                        <c:if test="${not empty error}">
                           <div class="errorblock">
                              <span style='color: red'>ID or Password is incorrect.Please try again.</span>
                           </div>
                        </c:if>
                        <div class="row">
                           <div class="col-md-12">
                              <label>Backoffice ID:</label>
                              <div class=:form-group">
                                 <div class="input-group mb-2">
                                    <div class="input-group-prepend">
                                       <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                                    </div>
                                    <input type="text"
                                       onblur="if (this.value=='') this.value='Backoffice ID or email';"
                                       onfocus="if (this.value=='Backoffice ID or email') this.value='';"
                                       class="form-control email" name="j_username" id="txtEmail"
                                       value="Backoffice ID or email" />
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-12">
                              <label>Password:</label>
                              <div class=:form-group">
                                 <div class="input-group  mb-2">
                                    <div class="input-group-prepend">
                                       <span class="input-group-text"><i class="fa fa-key"></i></span>
                                    </div>
                                    <input type="password"
                                       onblur="if (this.value=='') this.value='password';"
                                       onfocus="if (this.value=='password') this.value='';"
                                       class="form-control" value="password" name="j_password"
                                       id="txtPassword" />
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-12">
                              <span id="validatePassword" class="validation-action"></span>
                           </div>
                           <div class="col-md-12">
                              <span class="forgot-password"><a href="forgetForm.htm">Forgot Password ?</a></span>
                           </div>
                           <div class="col-md-12 text-center">
                              <input type="submit" id="btnLogin" value="Login" name="submit" />
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