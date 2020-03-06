<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata- Expert Bio and Pix</title>
	<script type="text/javascript" src="${ctx}/resources/ckeditor/ckeditor.js"> </script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#the_file").change(function() {
				validateImage("the_file");
			});
			
			$("#editForm").submit(function(e) {
				if(!validateImage("the_file")) {
					return false;
				} else {
					return true;
				}
			});

			$("#removeLogo").click(function() {
				if(confirm("Are you sure you want to remove?"))
					removeExpertPic();
				else
					return false;
			});
		});

		function removeExpertPic() {
			var expertId = $("#expertId").val();
			$.ajax({
		  		type: "GET",
		  		url: $("#contxt").val()+"/admin/removeExpertPic.htm?id="+expertId,
		  		dataType: "json",
		  		contentType: "application/json",
		  		cache: false,
		  		beforeSend: function() {
		  			$("#spanLogo").show();
		  		},
		  		complete: function() {
		  			$("#spanLogo").hide();
		  		},
		  		success: function(response) {
		  			$("#spanLogo").hide();
		  			alert(response.msg);
		  			window.location.reload();
		  		},
		  		error: function(xhr, status, error, errorThrown) {
		  			if(status.toString() == 'parsererror'){
		  				window.location.href = $("#contxt").val()+"/loginForm.htm?msg=e";
		  			}
	           }
	  	   });
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
		<li>Expert BIO</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li>Edit Expert BIO</li>
	</ul>
	</div>
	<div class="col-md-4 col-4">
	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>
	<form:form commandName="bioForm" action="saveExpertBio.htm" enctype="multipart/form-data" method="POST" id="editForm">
		<div class="row">
		<div class="col-md-12">
			<form:input path="id" type="hidden" id="expertId" />
	
			<div class="user-photo">
				<div class="photo-box">
					<div style="margin-bottom: 6px; margin-left: 54px; width: 102px;">
						<span class="pict-box-user"> 
							<c:choose>
								<c:when test="${not empty bioForm.imageName}">
									<img alt="Expert Image" src="${ctx}/admin/getUserImage.htm?userId=${bioForm.id}" />
								</c:when>
								<c:otherwise>
									<img alt="Expert Image" src="${ctx}/resources/images/defult-image.png" />
								</c:otherwise>
							</c:choose>						
						</span>
						
						<c:if test="${not empty bioForm.imageName}">
							<a id="removeLogo" href="javascript:;" style="text-decoration: none;cursor: pointer;">Remove</a>
							<span style="display: inline-block;margin-left: 8px;width: 50px;">
								<span style="display: none;width: 20px;" id="spanLogo">
									<img src="${ctx}/resources/images/fancybox_loading.gif" alt="Loading..." style="width: 16px;" />
								</span>
							</span>
						</c:if>
					</div>
					 <span class="upload-image"> 
					 	<form:input type="file" path="file" id="the_file" size="18" cssClass="upload-browse" />
					 </span>
				</div>
			</div>
			</div>
			
				<div class="col-md-12">
						<label>Expert Bio</label>
				<div class="form-group">
				
								<%-- <div class="photo-box">
	                          	<span class="pict-box">
	                          	 <img src="${ctx}/resources/images/photo.png" alt=""  />
	                                  </span>
	                                  <span class="close-button"><a href="javascript:;"><i class="icon-remove"></i></a></span>
	                                  <span class="upload-image"><input type="file" name="the_file" id="the_file" size="18"></span>
	                              </div> --%>
								<form:textarea cssClass="ckeditor" cols="10" id="editor1"
									path="bio" rows="10"></form:textarea></td>
									</div>
				
	</div>
	<div class="col-md-12 text-center">
			<input type="submit" value="Save" />
		</div>
		
		<input type="hidden" name ="prp" id ="prp" value="${prp}"/>
		<input type="hidden" name ="orp" id ="orp" value="${orp}"/>
		<input type="hidden" name ="sfrp" id ="sfrp" value="${sfrp}"/>
		<input type="hidden" name="sunrp" id="sunrp" value="${sunrp}"> 
		</div>
	</form:form>
</body>
</html>