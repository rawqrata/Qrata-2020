<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <c:set var="ctx" value="${pageContext.request.contextPath}" />
      <script src="${ctx}/resources/js/qrata.js" type="text/javascript"></script>
      <Script>
         var domainName = "";
         var subString = window.location.search.substring(1);
         var subString = window.location.search.substring(1);
         var url = subString.split("url=");
         
         $(document).ready(function() {
         	$("#btnLogin").click(function(e) {
         		e.preventDefault();
         		 validateEditDomain();
         	});
         
         	domainName = document.getElementById("domain").value;
         
         	$("#editDomain").submit(function(e) {
         		e.preventDefault();
         		 validateEditDomain();
         	});
         	
         	document.getElementById("url").value = url[1];
         
         });
         function validateEditDomain(){
         	//alert('hello');
             var data=document.getElementById("domain").value;  
             if(data == null || data == "") {  
                 alert("Name is required");  
                 return false;  
               }  
             data = trimField(data);
         //       if(data != "" && data.indexOf(" ") == 0) {  
         //   	      alert("First character of the name should not be a space.");  
         //   	      return false;  
         //   	 }
               var regExp = /^[-a-zA-Z0-9&_:,.' ']+$/;
           	  if (!data.match(regExp)) {
           		alert("Special Characters are not allowed! Please provide only alphabets and numbers.");
           		return false;
           	} 
           	 
               if(data != "" && domainName.trim() != data.trim()) {
             	 // alert("in if");
             	  $.ajax({
          	  		type: "GET",
          	  		url: "existsDomain.htm?name="+data,
          	  		dataType: "json",
          	  		contentType: "application/json",
          	  		success: function(response) {
          	  			if(response.exists) {
          	  				//alert("hello");
          	  				$("#msgDiv").html("<span style='color: red'>The domain name already exists.</span>");
          	  				return false;
          	  			} else {
          	  				$("#msgDiv").html("");
          	  				document.forms["editDomain"].submit();
          	  				
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
         function existsDomain(val) {
         	//alert("in function");
         	 if(domainName.trim() != val.trim()){      
         			    $.ajax({
         				type: "GET",
         				url: "existsDomain.htm?name="+val+"&id="+id,
         				dataType: "json",
         				contentType: "application/json",
         				success: function(response) {
         					if(response.exists) {
         						//alert("123");
         						$("#msgDiv").html("<span style='color: red'>The domain name already exists.</span>");
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
         }
      </script>
      <script>
         function cancelAction(){
         	
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
               <li>Manage</li>
               <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
               <li>Domains</li>
               <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
               <li>Edit Domain</li>
            </ul>
         </div>
         <div class="col-md-4 col-4 text-right">
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
         <form:form action="editDomain.htm" commandName="domain" method="POST" id="editDomain" name="editDomain">
            <div class="row">
               <div class="col-md-12">
                  <div id="msgDiv"></div>
                  <label>Name:</label>
                  <div class="form-group">
                     <form:input  type="text" onblur="existsDomain(this.value);"
                        onfocus="if (this.value=='email') this.value='';"
                        class="input_border form-control email" path="name" id="domain" name="domain"
                        />
                     <form:input type="hidden" path="id" ></form:input>
                  </div>
               </div>
               <div class="col-md-12 text-center">
                  <input type="button" name="btn" value="Save" id="btnLogin" >
                  <input type="button" name="btn" onclick="javascript:historyButton()" value="Cancel" id="btnLogin" >
               </div>
               </ul>
               <input type="hidden" name ="prp" id ="prp" value="${prp}"/>
               <input type="hidden" name ="orp" id ="orp" value="${orp}"/>
               <input type="hidden" name ="sfrp" id ="sfrp" value="${sfrp}"/>
               <input type="hidden" name="sunrp" id="sunrp" value="${sunrp}"> 
            </div>
         </form:form>
      </div>
   </body>
</html>