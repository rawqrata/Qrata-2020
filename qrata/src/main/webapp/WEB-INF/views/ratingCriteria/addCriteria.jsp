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
$(document).ready(function() {
	CKEDITOR.replace( 'description',
		    {
		        toolbar : 'Basic',
		    });
});

function validateAddCriteria() {  
	//alert('hello');
	 var priority=document.getElementById("priority").value;  
    if(priority== null ||priority == "") {  
        alert("odering is required");  
        return false;  
      }  
      if(priority != "" && priority.indexOf(" ") == 0) {  
  	      alert("First character of the order should not be a space.");  
  	      return false;  
  	 }
    var data=document.getElementById("ratingCriteriaName").value;  
    if(data == null || data == "") {  
        alert("Name is required");  
        return false;  
      }  
    data = trimField(data);
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
    
      
      if(priority!= "") {
    	// alert("heloo2");
	      $.ajax({
	  		type: "GET",
	  		url: "existsPriority.htm?priority="+priority+"&parentId="+document.getElementById("id").value+"&id=0",
	  		dataType: "json",
	  		contentType: "application/json",
	  		success: function(response) {
	  			if(response.exists) {
	  				//alert("in if");
	  				$("#msgDiv1").html("<span style='color: red'>The odering already exists.</span>");
	  				//$("#msgDiv1").html("<span style='color: red'>The odering already exists.</span>");
	  				return false;
	  			} 
	  			else {
	  				
						$("#msgDiv1").html("");
						existsCriteriaForSubmit(data);
						return false;
	  			}
	  		},
	  		  error: function() {
	  			 //alert("error");
	           }
	  	  });  
      }
     return false;
 }


function existsCriteriaForSubmit(val) {
	//alert("hello3 "+val);
		$.ajax({
			type: "GET",
			url: "existsCriteria.htm?name="+val+"&id="+document.getElementById("id").value,
			dataType: "json",
			contentType: "application/json",
			success: function(response) {
				//alert(response.exists);
				if(response.exists) {
					//alert("submit");
					$("#msgDiv").html("<span style='color: red'>The criteria name already exists.</span>");
					return false;
					//$("#msgDiv1").html("<span style='color: red'> The odering already exists.</span>");
				} else {
					document.forms["addCriteria"].submit();
				}
			},
			  error: function() {
				 //alert("error");
	          }		
		});


		return false;
	}
</script>
<script type="text/javascript">
	function existsPriority(val) {
		//alert("hello");
		$
				.ajax({
					type : "GET",
					url : "existsPriority.htm?priority=" + val + "&parentId="
							+ document.getElementById("id").value + "&id=0",
					dataType : "json",
					contentType : "application/json",
					success : function(response) {

						if (response.exists) {
							$("#msgDiv1")
									.html(
											"<span style='color: red'>The odering already exists.</span>");
							//$("#msgDiv1").html("<span style='color: red'> The odering already exists.</span>");
						} else {
							$("#msgDiv1").html("");
						}
					},
					error : function() {
						//alert("error");
					}
				});

		return false;
	}

	function existsCriteria(val) {
		//alert("hello1");
		$
				.ajax({
					type : "GET",
					url : "existsCriteria.htm?name=" + val+"&id="+document.getElementById("id").value,
					dataType : "json",
					contentType : "application/json",
					success : function(response) {
						if (response.exists) {
							$("#msgDiv")
									.html(
											"<span style='color: red'>The criteria name already exists.</span>");
							//$("#msgDiv1").html("<span style='color: red'> The odering already exists.</span>");
						} else {
							$("#msgDiv").html("");
							//$("#msgDiv1").html("");
						}
					},
					error : function() {
						//alert("error");
					}
				});
		return false;
	}
</script>

</head>

<body>
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box">
				<c:out value="Criteria has been created successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box">
				<c:out value="The data has been deleted successfully" />
			</div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box">
				<c:out value="Data has been updated successfully" />
			</div>
		</c:when>
	</c:choose>
	<div class="center-table">
		<div class="date-text">&nbsp;</div>
		<ul class="shape-design">
			<li class="shape1">&nbsp;</li>
			<li>Criteria Mgmt</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Criteria Group</li>
			<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
			<li>Add Criteria</li>
		</ul>
		<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;Go Back </a></div>
		<div class="breadcrumb-style"><h5>Root//<b:breadcrumb separator="/" serialId="${id}" type="CRITERIA" /></h5></div>
		<div class="bo-login-box">
			<div class="login-heading-inner">Criteria Info ID (auto
				assigned)</div>
			<div class="site-infologin-section" style="width: 100%;">
				<div class="defaul-login-form2">
					<div id="addDomainError"
						style="display: none; float: left; width: 370px; height: 30px; padding: 8px 10px 8px 24px;"
						class="error-none">You must fill in the field.</div>
					<form:form action="saveCriteria.htm" method="post" id="addCriteria" name="addCriteria">
						<ul>
							<li><label>Ordering:</label>
								<div id="msgDiv1" style="float: top;"></div>
								<p>
									<input type="text" onblur="existsPriority(this.value);"
										onfocus="if (this.value=='email') this.value='';"
										class="input_border email" name="priority" id="priority"
										value="" />

								</p></li>


							<li><label>Name:</label>
								<div id="msgDiv" style="float: left;"></div>
								<p>
									<input type="text" onblur="existsCriteria(this.value);"
										onfocus="if (this.value=='email') this.value='';"
										class="input_border email" name="ratingCriteriaName"
										id="ratingCriteriaName" value="" /> <input type="hidden"
										name="id" id="id" value="${id}" />

								</p></li>
						</ul>

						<tr>
							<th colspan="2" class="essential persist" style="font-weight:bold;">Description:</th>
						</tr>
						<tbody>
							<tr bgcolor="#F0F3F2">
								<td width="50%" valign="top"><p>
										<%-- <div class="photo-box">
=======
				<div id="addDomainError"
				style="display: none; float: left; width: 370px; height: 30px; padding: 8px 10px 8px 24px;"
				class="error-none">You must fill in the field.</div>
				<form:form action="saveCriteria.htm" method="post" id="addCriteria" name="addCriteria">
					<ul>
					<li><label>Ordering:</label>
					<div id="msgDiv1" style="float:top;"></div>
					<p>
					<input type="text" onblur="existsPriority(this.value);"
									onfocus="if (this.value=='email') this.value='';"
									class="input_border email" name="priority" id="priority" value="" />
										
									</p></li>
					
					
						<li><label>Name:</label>
							<div id="msgDiv" style="float: left;"></div>
							<p>
								<input type="text" onblur="existsCriteria(this.value.toLowerCase());"
									onfocus="if (this.value=='email') this.value='';"
									class="input_border email" name="ratingCriteriaName" id="ratingCriteriaName"
									value="" />
									<input type="hidden" name="id" id="id" value="${id}" />
								
							</p>
							
						</li></ul>
						
							<tr>
					<b><th colspan="2" class="essential persist">Description:</th></b>
				</tr>
				<tbody>
				<tr bgcolor="#F0F3F2">
					<td width="50%" valign="top"><p>
								<%-- <div class="photo-box">
>>>>>>> 4395884dcac1c39ed5daf5f3079150dcb2507d2a
                          	<span class="pict-box">
                          	 <img src="${ctx}/resources/images/photo.png" alt=""  />
                                  </span>
                                  <span class="close-button"><a href="javascript:;"><i class="icon-remove"></i></a></span>
                                  <span class="upload-image"><input type="file" name="the_file" id="the_file" size="18"></span>
                              </div> --%>
										<textarea class="ckeditor" cols="10" id="description"
											name="description" rows="10"></textarea>

										<input type="hidden" name="textareaVal" id="textareaVal"
											value="" /></td>


							</tr>
						</tbody>
						<input type="button" name="btn" value="Save" id="btnLogin"
							onclick="validateAddCriteria();" />

					</form:form>
				</div>

			</div>
		</div>


	</div>
</body>
</html>