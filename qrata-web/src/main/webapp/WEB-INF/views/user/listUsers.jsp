<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
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
      <div class="row">
         <c:choose>
            <c:when test="${param.success eq '1' }">
               <div class="success-box">
                  <c:out value="User has been created successfully" />
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
      </div>
      <div class="row">
         <div class="col-md-12">
            <div class="date-text" >&nbsp;</div>
         </div>
      </div>
      <div class="row">
         <div class="col-md-8 col-8">
            <ul class="shape-design">
               <li>Manage</li>
               <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
               <li class="none"><b>Users</b></li>
            </ul>
         </div>
         <div class="col-md-4 col-4 text-right">
            <div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
         </div>
      </div>
      <div class="center-tables">
         <div class="row">
            <div class="col-md-7 col-8">
               <form:form cssClass="form-search" commandName="userForm" action="listUsers.htm" autocomplete="off">
                  <div class=:form-group">
                     <div class="input-group mb-2">
                        <form:input path="userSearchVal" cssClass="form-control span2 search-query" placeholder="Enter First Name or Last Name or Login or Email" 
                           id="userSearch" cssStyle="width: 320px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <i class="icon-search"></i> <i class="fa fa-search"></i>
                           </span>
                        </div>
                     </div>
                  </div>
               </form:form>
            </div>
            <div class="col-md-5 col-4 text-right">
               <span><a id="create-user" href="${ctx}/admin/addUser.htm"><i class="fa fa-plus" aria-hidden="true"></i> Add
               User
               </a></span>
            </div>
         </div>
         <div class="table-responsive">
            <display:table id="userList" name="${users}" pagesize="10" class="displayTable table table-striped" style="margin-bottom:1%;margin-top:1%;" 
               requestURI="listUsers.htm" partialList="true" size="${totalUsers}" defaultsort="1" sort="external" defaultorder="descending">
               <display:column property="userinfo.firstname" style="width: 85px;" title="First Name" sortable="true" sortName="userInfo.firstname" />
               <display:column property="userinfo.lastname" style="width: 85px;" title="Last Name" sortable="true" sortName="userInfo.lastname" />
               <display:column property="userName" style="width: 85px;" title="Login" sortable="true" sortName="userName" />
               <display:column title="Email" style="width: 115px;" sortable="true" sortName="userInfo.email">
                  <a href="mailto:${userList.userinfo.email}">${userList.userinfo.email}</a>
               </display:column>
               <c:forEach var="roles" items="${roles}" >
                  <display:column title="${roles.name}">
                     <c:choose>
                        <c:when test="${userList.role.id eq roles.id }">
                           <i class="fa fa-check"></i> 
                        </c:when>
                     </c:choose>
                  </display:column>
               </c:forEach>
               <display:column title="Edit" > 
                  <a id="editUser" href="editUser.htm?userId=${userList.id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}">
                  <i class="icon-pencil"></i><i class="fa fa-edit"></i>
                  </a>
               </display:column>
               <display:column title="Delete">
                  <a href="deleteUser.htm?id=${userList.userName}" class="customDialog" onclick="return confirm('Warning: This deletes the user permanently! Are you sure?')">
                  <i class="fa fa-trash"></i>
                  </a>
               </display:column>
            </display:table>
         </div>
      </div>
   </body>
</html>