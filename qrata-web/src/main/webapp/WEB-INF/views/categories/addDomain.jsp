<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb" prefix="b"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <%-- <script src="${ctx}/resources/js/qrata.js" type="text/javascript"></script> --%>
      <script type="text/javascript"> 
         $(document).ready(function() {
         		$("#btnLogin").click(function(e) {
         			e.preventDefault();
         			 validateAddDomain();
         		});
         	
         	$("#addDomain").submit(function(e) {
         		e.preventDefault();
         		 validateAddDomain();
         	});
         });
         
         function validateAddDomain() {  
         	data = trimField(data);
         	
             var data=document.getElementById("domain").value;
             if(data == null || data == "") {  
                 alert("Name is required");  
                 return false;  
               }  
             
               var regExp = /^[-a-zA-Z0-9 &_:,.' ']+$/;
           	  if (!data.match(regExp)) {
           		alert("Special Characters are not allowed! Please provide only alphabets and numbers.");
           		return false;
           	} 
           	  
           	var myUrl = "existsDomain.htm";
           	  	
               if(data != "") {
                   $.ajax({
         	  		type: "GET",
         	  		url: myUrl,
         	  		data: {name : data},
         	  		dataType: "json",
         	  		contentType: "application/json",
         	  		success: function(response) {
         	  			if(response.exists) {
         	  				$("#msgDiv").html("<span style='color: red'>The domain name already exists.</span>");
         	  				return false;
         	  			} else {
         	  				$("#msgDiv").html("");
         	  				document.forms["addDomain"].submit();
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
         function existsDomain() {
         	var val = document.getElementById("domain").value.trim();
         	var myUrl = "existsDomain.htm";
         	$.ajax({
         		type: "GET",
         		url: myUrl,
         		data: {name : val},
         		dataType: "json",
         		contentType: "application/json",
         		success: function(response) {
         			if(response.exists) {
         				$("#msgDiv").html("<span style='color: red'>The domain name already exists.</span>");
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
               <li>Manage</li>
               <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
               <li>Domains</li>
               <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
               <li>Add Domain</li>
            </ul>
         </div>
         <div class="col-md-4 col-4">
            <div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
         </div>
      </div>
      <div class="row">
         <div class="col-md-12">
            <h5><a href="listDomains.htm">Root</a>://</h5>
         </div>
      </div>
      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Domain Info ID (auto
            assigned)
         </div>
         <div id="addDomainError"
            style="display: none; float: left; width: 370px; height: 30px; padding: 8px 10px 8px 24px;"
            class="error-none">You must fill in the field.</div>
         <form action="saveDomain.htm" method="post" id="addDomain" name="addDomain">
            <div class="row">
               <div class="col-md-12">
                  <div id="msgDiv" style="float: left;"></div>
                  <label>Name:</label>
                  <div class="form-group">
                     <input type="text" onblur="existsDomain(this.value);"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email" name="domain" id="domain"
                        value="" />
                     <input type="hidden" name="domain" id = "id" value="${id}"/>
                  </div>
               </div>
               <div class="col-md-12 text-center">
                  <input type="button" name="btn" value="Save" id="btnLogin"   />
               </div>
            </div>
         </form>
      </div>
   </body>
</html>