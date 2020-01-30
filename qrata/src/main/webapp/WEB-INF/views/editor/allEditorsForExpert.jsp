<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata - Experts</title>

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestExpertByNameAndEditor.htm", "expertSearch", null, "${id}");
		});
	</script>
</head>

<body>

    <c:choose>
        <c:when test="${param.success eq '1' }">
            <div class="success-box"><c:out value="Expert has been created successfully" /></div>
        </c:when>
        <c:when test="${param.success eq '2' }">
            <div class="success-box"><c:out value="The data has been deleted successfully" /></div>
        </c:when>
        <c:when test="${param.success eq '3' }">
            <div class="success-box"><c:out value="Data has been updated successfully" /></div>
        </c:when>
    </c:choose>

    <div class="date-text">&nbsp;</div>

    <ul class="shape-design">
        <li>Manage</li>
            <li><img alt="breadcrumb arrow" src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
            <li>Experts</li>
            <li><img alt="breadcrumb arrow" src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
            <li>Editors</li>
    </ul>

    <div class="back-button"><a href="javascript:;" onclick="historyButton()">&#60;&#60; Go Back </a></div>

    <div class="center-table">

        <form:form cssClass="form-search" commandName="userForm" action="allEditorsForExpert.htm?id=${id}" autocomplete="off">
            <div class="input-append">
                <form:input path="userSearchVal" cssClass="span2 search-query" placeholder="Enter First Name or Last Name or Login or Email"
                    id="expertSearch" cssStyle="width: 310px;" />
                <button type="submit" class="btn">
                    <i class="icon-search"></i>
                </button>
            </div>
        </form:form>

        <c:if test="${USERROLEID eq EDITORID}">
            <div style="float: right;font-size: 12px;margin-left: 25px;margin-top: -22px;padding-top: 4px;">
                <span>
                    <a href="${ctx}/admin/addUser.htm">Add Expert<img style=" padding-left: 4px;" src="${ctx}/resources/images/plus-sign.png"></a>
                </span>
            </div>
        </c:if>

        <display:table id="editors" name="${editors}" class="displayTable" style="margin-bottom:1%;margin-top:1%;"
                       size="${totalEditorsSize}" requestURI="allEditorsForExpert.htm" pagesize="10" defaultsort="1"
                       sort="external" defaultorder="descending" partialList="true">

            <display:column title="First Name" property="firstName" sortable="true" sortName="tea.expert.userinfo.firstname" style="width:85px;"/>
            <display:column title="Last Name" property="lastName" sortable="true" sortName="tea.expert.userinfo.lastname" style="width:85px;"/>
            <display:column title="Login" property="userName" sortable="true" sortName="tea.expert.userName" style="width:85px;"/>
            <display:column title="Email" sortable="true" sortName="tea.expert.userinfo.email" style="width:135px;">
                <a href="mailto:${editors.email}"><c:out value="${editors.email}" /></a>
            </display:column>
            <display:column title="Preview">
                <a href="${ctx}/admin/expertBio.htm?id=<c:out value='${id}'/>&editorId=<c:out value='${editors.id}'/>">
                    <i class="icon-eye-open"></i>
                </a>
            </display:column>
        </display:table>

    </div>

</body>

</html>
 
