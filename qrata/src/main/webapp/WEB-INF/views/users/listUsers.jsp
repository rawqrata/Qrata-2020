
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:import url="../../header.jsp"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%-- <script src="${ctx}/resources/js/jquery-ui-1.10.1.custom.js"></script> --%>

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

<script type="text/javascript">
	$(document).ready(function() {
		var autoCompleter = new CommonAutocompleter();
		autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestUserByNameOrRole.htm", "userSearch");
	});
	
	$(function() {
		$(".customDialog").easyconfirm({dialog: $("#question")});
	});
</script>
<script>
	$(document)
			.ready(
					function() {

						if ($.browser.msie
								&& $.browser.version.substr(0, 1) < 7) {
							$("li").has("ul").mouseover(
									function() {
										$(this).children("ul").css(
												"visibility", "visible")
									}).mouseout(
									function() {
										$(this).children("ul").css(
												"visibility", "hidden")
									})
						}
						$(".menu-gen-wrap")
								.prepend(
										'<div class="menu-gen-trigger"><span class="ico-mob">Menu</span></div>');
						$(".menu-gen-trigger").on("click", function() {
							$(".menu-gen").slideToggle()
						});
						var a = navigator.userAgent.match(/iPad/i) != null;
						if (a)
							$(".menu-gen ul").addClass("no-transition")
					})
</script>

</head>
<body>
	<c:choose>
		<c:when test="${success eq '1' }">
			<div class="success-box"><c:out value="User has been created successfully" /></div>
		</c:when>
		<c:when test="${success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
	</c:choose>
	
	<div class="date-text" >&nbsp;</div>
	<ul class="shape-design">

		<li>Manage</li>
		<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
		<li class="none"><b>Users</b></li>
	</ul>
    <form:form cssClass="form-search" modelAttribute="userForm" action="listUsers.htm" autocomplete="off">
        <div class="input-append" style="padding-top:12px;padding-left:10px;">
            <form:input path="userSearchVal" cssClass="span2 search-query" placeholder="Enter First Name or Last Name or Login or Email"
                        id="userSearch" cssStyle="width: 250px;" />
            <button type="submit" class="btn">
                <i class="icon-search"></i>
            </button>
        </div>
        <div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
    </form:form>
	<div class="center-table">

		<div style="float: right; font-size: 12px; margin-left: 25px;">
			<span>
			<button onclick="location.href='${ctx}/admin/addUser.htm'" type="button">
        Add	User</button>
<%--                 <a id="create-user" href="${ctx}/admin/addUser.htm">Add	User<img style="padding-left: 4px;"	src="${ctx }/resources/images/plus-sign.png"></a>
 --%>            </span>
		</div>
		
		<display:table id="userList" name="${users}" pagesize="10" class="displayTable" style="margin-bottom:1%;margin-top:1%;width: 100%;" 
			requestURI="listUsers.htm" partialList="true" size="${totalUsers}" defaultsort="1" sort="external" defaultorder="descending">
			
			<display:column property="userinfo.firstname" style="width: 85px;" title="First Name" sortable="true" sortName="userInfo.firstname" />
			<display:column property="userinfo.lastname" style="width: 85px;" title="Last Name" sortable="true" sortName="userInfo.lastname" />
			<display:column property="userName" style="width: 85px;" title="Login" sortable="true" sortName="userName" />
			<display:column title="Email" style="width: 115px;" sortable="true" sortName="userInfo.email">
				<a href="mailto:${userList.userinfo.email}">${userList.userinfo.email}</a>
			</display:column>
			<c:forEach var="roles" items="${roles}" >
				<display:column title="${roles.name}">
                    <c:forEach var="rs" items="${userList.roles}" >
                        <c:choose>
                            <c:when test="${rs.id eq roles.id}">
                                <i class="icon-ok"></i>
                            </c:when>
                        </c:choose>
                    </c:forEach>
				</display:column>
			</c:forEach>
			<display:column title="Edit" > 
				<a id="editUser" href="editUser.htm?userId=${userList.id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}">
					<i class="icon-pencil"></i>
				</a>
			</display:column>
			<display:column title="Delete">
				<a href="deleteUser.htm?id=${userList.userName}" class="customDialog" onclick="return confirm('Warning: This deletes the user permanently! Are you sure?')">
					<i class="icon-remove"></i>
				</a>
			</display:column>
		</display:table>
		
	</div>
	

<c:import url="../../footer.jsp"/>