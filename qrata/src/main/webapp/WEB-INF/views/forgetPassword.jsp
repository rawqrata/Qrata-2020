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
	<div class="main-section">
		<div class="container">
			<div class="back-logo-qrata">
				<img src="${ctx }/resources/images/back-bg-logo.png" />
			</div>
			<div class="back-button" style="margin-top:1%;"><a href="loginForm.htm" >&#60;&#60; Go Back </a></div>
			
			<form name="f" action="recoverPassword.htm"  method="POST" onsubmit="return validate();" >
				<!--login-->
				<div class="login-box">
				
					<div class="login-section">
					<c:choose>
						<c:when test="${not empty error}">
							<span style='color: red'><c:out value="${error}" /></span>
						</c:when>
						<c:when test="${not empty success}">
							<span style='color: green'><c:out value="${success}" /></span>
						</c:when>
					</c:choose>
					<c:if test="${not empty error}">
                         <div class="errorblock">
                            
                                
                            </div>
                      </c:if>
                      <h1 style="color:#3D3F42;padding-top:7px;">Please provide your Email Address to retrieve your password</h1>
						<div class="defaul-login-form">
							<ul>
								<li><label>Email Address:</label>
									<p>
										<input type="text"
											onblur="if (this.value=='') this.value='email';"
											onfocus="if (this.value=='email') this.value='';"
											class="input_border email" name="emailId" id="txtEmail"
											value="email">
									</p>
									</li>
										
								<li><label></label>
									<span style="float:left;padding-top:7px;">
										<input type="submit" id="btnLogin" value="Submit" name="submit">&nbsp;<a href="loginForm.htm">Go to login page &#62;&#62;</a>
									
									</span></li>
							</ul>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>