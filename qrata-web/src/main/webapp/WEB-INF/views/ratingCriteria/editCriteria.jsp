<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="${ctx}/resources/ckeditor/ckeditor.js"></script>
<script src="${ctx}/resources/js/qrata.js" type="text/javascript"></script>
  
<script type="text/javascript"> 


$(document).ready(function() { }
);
		
function validateEditCriteria() {  
	//alert('hello');
// 	 var priority=document.getElementById("priority").value;  
//     if(priority== null ||priority == "") {  
//         alert("odering is required");  
//         return false;  
//       }  
//       if(priority != "" && priority.indexOf(" ") == 0) {  
//   	      alert("First character of the order should not be a space.");  
//   	      return false;  
//   	 }
//     var data=document.getElementById("ratingCriteria").value;  
//     if(data == null || data == "") {  
//         alert("Name is required");  
//         return false;  
//       }  
//     data=trimField(data);
//       if(data != "" && data.indexOf(" ") == 0) {  
//   	      alert("First character of the name should not be a space.");  
//   	      return false;  
//   	 }
    
  //var data1=document.getElementById("description").value; 
 var description = CKEDITOR.instances['description'].getData();
  	
    if(description == null || description == "") {  
        alert("Description is required");  
        return false;  
      }  
      if(description != "" && description.indexOf(" ") == 0) {  
  	      alert("First character of the description should not be a space.");  
  	      return false;  
  	 } 
     
      document.forms["editCriteria"].submit();
}
      
//       if(priority!= "") {
//     	// alert("heloo2");
// 	      $.ajax({
// 	  		type: "GET",
// 	  		url: "existsPriority.htm?priority="+priority+"&parentId="+document.getElementById("parentId").value+"&id="+document.getElementById("criteriaId").value,
// 	  		dataType: "json",
// 	  		contentType: "application/json",
// 	  		success: function(response) {
// 	  			if(response.exists) {
// 	  				//alert("in if");
// 	  				$("#msgDiv1").html("<span style='color: red'>The odering already exists.</span>");
// 	  				//$("#msgDiv1").html("<span style='color: red'>The odering already exists.</span>");
// 	  				return false;
// 	  			} 
// 	  			else {	  				
// 						$("#msgDiv1").html("");
// 						existsCriteriaForSubmit(data);
// 						return false;
// 	  			}
// 	  		},
// 	  		  error: function() {
// 	  			 //alert("error");
// 	           }
// 	  	  });  
//       }
//      return false;
//  }


// function existsCriteriaForSubmit(val) {
// 	//alert("hello3");
// 		$.ajax({
// 			type: "GET",
// 			url: "existsCriteria.htm?name="+val+"&id="+document.getElementById("criteriaId").value,
// 			dataType: "json",
// 			contentType: "application/json",
// 			success: function(response) {
// 				if(response.exists) {
// 					$("#msgDiv").html("<span style='color: red'>The criteria name already exists.</span>");
// 					//$("#msgDiv1").html("<span style='color: red'> The odering already exists.</span>");
// 					return false;
// 				} else {
// 					document.forms["editCriteria"].submit();
// 				}
// 			},
// 			  error: function() {
// 				 //alert("error");
// 	          }		
// 		});

// 		return false;
// 	}
</script>
<script type="text/javascript">
// 	function existsPriority(val, id) {
// 	//alert("hello");
// 		$.ajax({
// 			type: "GET",
// 			url: "existsPriority.htm?priority="+val+"&parentId="+document.getElementById("parentId").value+"&id="+id,
// 			dataType: "json",
// 			contentType: "application/json",
// 			success: function(response) {
				
// 				if(response.exists) {					
// 					$("#msgDiv1").html("<span style='color: red'>The odering already exists.</span>");
// 					//$("#msgDiv1").html("<span style='color: red'> The odering already exists.</span>");
// 				} else {
// 					$("#msgDiv1").html("");					
// 				}
// 			},
// 		  error: function() {
// 			 //alert("error");
//           }		
// 		});

// 		return false;
// 	}
	
	
// 	function existsCriteria(val, id) {
// 		//alert("hello1");
// 			$.ajax({
// 				type: "GET",
// 				url: "existsCriteria.htm?name="+val+"&id="+document.getElementById("criteriaId").value,
// 				dataType: "json",
// 				contentType: "application/json",
// 				success: function(response) {
// 					if(response.exists) {
// 						$("#msgDiv").html("<span style='color: red'>The criteria name already exists.</span>");
// 						//$("#msgDiv1").html("<span style='color: red'> The odering already exists.</span>");
// 					} else {
// 						$("#msgDiv").html("");
// 						//$("#msgDiv1").html("");
// 					}
// 				},
// 				  error: function() {
// 					 //alert("error");
// 		          }		
// 			});
// 			return false;
// 		}
</script>

</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Criteria has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
	</c:choose>
	</div>
	</div>
<div class="row">
<div class="col-md-12">
	<div class="date-text">&nbsp;</div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-8 col-8">
	<ul class="shape-design">
				<li>Criteria Mgmt</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Criteria Group</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Edit Criteria</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<div class="row">
	<div class="col-md-12"><h5>Root//<b:breadcrumb separator="/" serialId="${id}" type="CRITERIA" /></h5></div>
	</div>
	      <div class="form-box mt-4">
         <div class="login-heading-inner-form">Criteria Info ID (auto
				assigned)
         </div>
					
			
				<form:form action="editCriteria.htm" commandName="criteria"  enctype="multipart/form-data" method="POST" id="editCriteria" name="editCriteria" >
					
<!-- 						<li><label>Ordering:</label> -->
<!-- 					<div id="msgDiv1" style="float:top;"></div> -->
<!-- 					<p> -->
<%-- 					<input type="text"  onblur="existsPriority(this.value.toLowerCase(),'${criteria.id}');" --%>
<%-- 									class="input_border email"  name="priority" id="priority" value="${criteriaForm.priority}" /> --%>
										
<!-- 									</p></li> -->
<div class="row">
<div class="col-md-12">
<div id="msgDiv" ></div>
							<label>Name:</label>
							<div class="form-group">
								
								
									<form:input type="text" onblur="existsCriteria(this.value.toLowerCase(),'${criteria.id}');"
										class="input_border form-control email" path="name" id="ratingCriteria" readonly="true"/> 
										<form:input type="hidden" path="id" ></form:input>
										<input type="hidden" name="criteriaId" id="criteriaId" value="${criteria.id}" />
										<input type="hidden" name="parentId" id="parentId" value="${criteria.parentRatingCriteria.id}"/>
										</div>
								</div>
					<div class="col-md-12">					
				<label>	Description:</label>
			
							<div class="form-group">
				
							<%-- <div class="photo-box">
                          	<span class="pict-box">
                          	 <img src="${ctx}/resources/images/photo.png" alt=""  />
                                  </span>
                                  <span class="close-button"><a href="javascript:;"><i class="icon-remove"></i></a></span>
                                  <span class="upload-image"><input type="file" name="the_file" id="the_file" size="18"></span>
                              </div> --%>
							<textarea class="ckeditor" cols="10" id="description"
								name="description" rows="10">${criteriaForm.description}</textarea>
						<input type="hidden" name="textareaVal" id="textareaVal" value="" />	
						</div>			
			</div>
		
			<div class="col-md-12 text-center">	
				<input  type="button" name="btn" value="save" id="btnLogin" onclick="validateEditCriteria();"></div>
				
				</div>
					</form:form>

			

			
		</div>


	


</body>
</html>