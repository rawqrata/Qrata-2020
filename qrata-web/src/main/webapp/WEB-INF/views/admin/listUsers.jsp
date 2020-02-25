<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}/resources/js/jquery-ui-1.10.1.custom.css" />
<script src="${ctx}/resources/js/jquery-ui-1.10.1.custom.js"></script>
 <style>
.ui-autocomplete-category {
font-weight: bold;
padding: .2em .4em;
margin: .8em 0 .2em;
line-height: 1.5;
position: absolute;
}
element.style {
    display: none;
    left: 334.5px;
    position: absolute;
    top: 166px;
    width: 166px;
}
</style>
 <script>
$.widget( "custom.catcomplete", $.ui.autocomplete, {
_renderMenu: function( ul, items ) {
var that = this,
currentCategory = "";
$.each( items, function( index, item ) {
if ( item.category != currentCategory ) {
ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
currentCategory = item.category;
}
that._renderItemData( ul, item );
});
}
});
</script>

 <script type="text/javascript">
      
  $(document).ready(function(){
	 		var firstName = "";
    	   	  $("#searchUser").keyup(function(){
    	   	   firstName = $(this).val();
    	   	   
    	   	   $.get("${ctx}/admin/getCategorizedUsers.htm?name=" + firstName , function(data) {
    	   		var jsonData = JSON.parse(data);
				
    	   		$( "#searchUser" ).catcomplete({
    				source: jsonData
    			});
    			
    	   	   });

    	   	});
 }); 

 </script>
  
</head>
<body>
			<ul class="shape-design">
				<li>Manage</li>
				<li>&#62;&#62;</li>
				<li class="none">Users</li>
			</ul>

			<div class="date-text">&nbsp;</div>
			<div class="center-table">
				<div style="overflow: hidden; width: 60%;">
					<div class="input-append">
						<input type="text" class="span2 search-query" name="searchUser" id="searchUser">
						<button type="submit" class="btn">
							<i class="icon-search"></i>
						</button>
						
					</div>
					
				</div>
				<div style="float: right;   font-size: 12px;   margin-left: 25px;   margin-top: -22px;  padding-top: 4px;"> <span><a href="addUser.htm">Add User<img style=" padding-left: 4px;" src="${ctx }/resources/images/plus-sign.png"></a></span></div>
				<table class="mediaTable table table-striped" style="background-color: #E7E7E4;">
					<thead>
						<tr>
							<th class="essential persist">First Name</th>
							<th class="essential">Last Name</th>
							<th class="optional">Login</th>
							<th class="essential">Email</th>
		
							<c:forEach var="roles" items="${roles}">
							
								<th class="optional">${roles.name}</th>
						
							</c:forEach>
							<th>Edit</th>
							<th>Delete</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="users" items="${users}">
							<tr bgcolor="#F0F3F2">
								<c:set var="userInfo" value="${users.userinfo}"></c:set>
								<td>${userInfo.firstname}</td>
								<td class="yes">${userInfo.lastname}</td>
								<td class="yes">${users.userName}</td>
								<td class="yes"><a href="mailto:${userInfo.email}">${userInfo.email}</a></td>
								<c:forEach var="roles" items="${roles}">
								<td>
									<c:choose>										
										<c:when test="${users.roleId.id eq roles.id }">
											<i class="icon-ok"></i>
										</c:when>
									</c:choose>
								</td>
								</c:forEach>
								<td class="yes"><a href="#"
									onclick="javascript:window.location.href='editUser.htm?userName=${users.userName}';"><i
										class="icon-pencil"></i></a></td>
								<td class="yes"><a href="deleteUser.htm?id=${users.userName}"
									onclick="return confirm('Warning: This deletes the data from everywhere! Are you sure?')"><i
										class="icon-remove"></i></a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>


			</div>

		<!-- </div> -->

	<!-- </div> -->
</body>


</html>