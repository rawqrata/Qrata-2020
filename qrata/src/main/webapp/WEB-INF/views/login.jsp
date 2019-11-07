<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false" %>
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
	<div class="main-section">
		<div class="container">
			<div class="back-logo-qrata">
				<img src="${ctx}/resources/images/back-bg-logo.png" />
			</div>

			<form name="f" action="<c:url value='/login.do'/>"
				method="POST" onsubmit="return validate();" >
				<!--login-->
				<div class="login-box">
				
					<div class="login-heading">Please enter your Backoffice ID
						and Password</div>
					<div class="login-section">
                        <c:if test="${not empty param.err}">
                            <div class="text-error"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
                        </c:if>
                        <c:if test="${not empty param.out}">
                            <div class="text-success">You've logged out successfully.</div>
                        </c:if>
                        <c:if test="${not empty param.time}">
                            <div class="text-warning">You've been logged out due to inactivity.</div>
                        </c:if>
                        <div class="defaul-login-form">
							<ul>
								<li><label>Backoffice ID:</label>
									<p>
										<input type="text"
											onblur="if (this.value=='') this.value='Backoffice ID or email';"
											onfocus="if (this.value=='Backoffice ID or email') this.value='';"
											class="input_border email" name="j_username" id="txtEmail"
											value="Backoffice ID or email" />
									</p>
									</li>
								<li><label>Password:</label>
									<p>
										<input type="password"
											onblur="if (this.value=='') this.value='password';"
											onfocus="if (this.value=='password') this.value='';"
											class="input_border" value="password" name="j_password"
											id="txtPassword" />
									</p> <span id="validatePassword" class="validation-action"></span>
								</li>
								<li><label></label><p class="forgot-password"><a href="forgetForm.htm">Forgot Password ?</a></p></li>
								<li><label></label>
									<span style="float:left;padding-top:7px;">
										<input type="submit" id="btnLogin" value="Login" name="submit" />
									</span>
								</li>
							</ul>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>