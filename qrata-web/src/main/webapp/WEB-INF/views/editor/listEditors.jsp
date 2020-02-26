<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Qrata - Editors</title>

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestUserByNameOrRole.htm", "editorSearch", "EDITOR");
		});
	</script>
</head>
<body>
<div class="row">
<div class="date-text">&nbsp;</div>
</div>
<div class="row">
<div class="col-md-8 col-8">
 	<ul class="shape-design">
              <li>Manage</li>
			  <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
              <li class="none">Editors</li>
       	</ul>
            </div>   
            <div class="col-md-4 col-4">  	
         	<div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
         	</div>   
         	</div>
                 <div class="editorbox">
                		<form:form cssClass="form-search" commandName="userForm" action="listEditors.htm" autocomplete="off">
                		<div class="row">
                		
                								            <div class="col-md-12">
                  <div class=:form-group">
                     <div class="input-group mb-1 serachinputbox">
                    <form:input path="userSearchVal" cssClass="span2 form-control search-query" placeholder="Enter First Name or Last Name or Login or Email" 
						id="editorSearch" cssStyle="width: 310px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <button type="submit" class="btn">
                           <i class="fa fa-search"></i>
                           </button>
                           </span>
                        </div>
                     </div>
                  </div>
               </div>
                		
                		</div>
				
			</form:form>

    <display:table id="editor" name="${editors}" pagesize="10" class="displayTable" style="margin-bottom:1%;margin-top:1%;" requestURI="listEditors.htm"
    	defaultsort="1" sort="external" size = "${totalEditorSize }" partialList="true" defaultorder="descending">
    	<display:column title="First Name" property="firstName" sortable="true" sortName="userinfo.firstname" />
    	<display:column title="Last Name" property="lastName" sortable="true" sortName="userinfo.lastname" />
    	<display:column title="Login" property="userName" sortable="true" sortName="userName" />
    	<display:column title="Email" style="width:250px;">
    		<a href="mailto:${editor.email }" ><c:out value="${editor.email }" /></a>
    	</display:column>
    	<display:column title="Experts">
    	<a href="${ctx}/admin/allExperts_Editors.htm?id=<c:out value='${editor.id }'/>"> View &#62;&#62;</a>
    	</display:column>
    </display:table>

   </div>
</body>
</html>