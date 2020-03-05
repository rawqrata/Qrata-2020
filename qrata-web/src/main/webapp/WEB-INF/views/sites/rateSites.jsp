<%@page import="com.insonix.qrata.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Qrata- Sites</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<c:set var="ADMINID" value="<%=Constants.Roles.ADMIN.getValue()%>"></c:set>
	<c:set var="EDITORID" value="<%=Constants.Roles.EDITOR.getValue()%>"></c:set>
	<c:set var="EXPERTID" value="<%=Constants.Roles.EXPERT.getValue()%>"></c:set>
	<c:set var="USERROLEID" value="${sessionScope.user.role.id}"></c:set>

	<script type="text/javascript">
		$(document).ready(function() {
			var autoCompleter = new CommonAutocompleter();
			autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestSiteByNameForMyPublishing.htm", "siteSearch");
		});
	</script>
</head>
<body>
<div class="row">
<div class="col-md-12">
	<c:choose>
		<c:when test="${param.success eq '1' }">
			<div class="success-box"><c:out value="Content has been created successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '2' }">
			<div class="success-box"><c:out value="The data has been deleted successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Data has been updated successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '4' }">
			<div class="error-box"><c:out value="" /></div>
		</c:when>
		<c:when test="${param.success eq '5' }">
			<div class="success-box"><c:out value="Ratings has been made successfully" /></div>
		</c:when>
		<c:when test="${param.success eq '6' }">
			<div class="error-box"><c:out value="You cannot rate the content unless the Topic concerned is rated. Please rate the Topic first." /></div>
		</c:when>
		<c:when test="${param.success eq '7' }">
			<div class="error-box"><c:out value="You cannot rate the content unless the Sub-Topic concerned is rated. Please rate the Sub-Topic first." /></div>
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
		<c:choose>
			<c:when test="${role eq 3 or USERROLEID eq EXPERTID}">
				<li>My Rating Data</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li class="none">Content</li>
				<c:set var="url" value="getContents.htm?role=3" />
			</c:when>
			<c:when test="${USERROLEID ne EXPERTID}">
				<li>My Rating Data</li>
				<li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
				<li class="none">Content</li>
				<c:set var="url" value="getContents.htm" />
			</c:when>
		</c:choose>
	</ul>
	</div>
	<div class="col-md-4 col-4">
		<div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
	</div>
	</div>

	<div class="sitebox">
	 	        <div class="row">
               <div class="col-md-9">
               <form:form cssClass="form-search" commandName="siteForm" action="${url}" autocomplete="off">
                  <div class="form-group">
                     <div class="input-group mb-2 serachinputbox">
                        <form:input path="siteSearchVal" cssClass="span2 form-control search-query" placeholder="Enter content name" 
					id="siteSearch" cssStyle="width: 310px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <button type="submit" class="btn">
                           <i class="fa fa-search"></i>
                           </button>
                           </span>
                        </div>
                     </div>
                  </div>
                   </form:form>
               </div>
               <div class="col-md-3 col-3 text-right">
               		    	<c:choose>
    		<c:when test="${USERROLEID ne EXPERTID}">
    			
					<span><a id="create-user" href="${ctx }/admin/addSite.htm"><i class="fa fa-plus" aria-hidden="true"></i> Add Content</a>
					</span>
				
    		</c:when>
    		<c:otherwise>
    			
					<span><a id="create-user" href="${ctx }/admin/addContent_Expert.htm"><i class="fa fa-plus" aria-hidden="true"></i> Add Content</a>
					</span>
				
    		</c:otherwise>
    	</c:choose>
               </div>
            </div>
	
		
		
                  
                  
		
				
			
                  
            

		
    

    			
		<display:table id="site" name="${sites}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" requestURI="getContents.htm" partialList="true" size="${totalSites}">
		
			<display:column title="Content" sortable="true" sortName="s.name" style="width: 410px">
				<p><c:out value="${site.name}" /></p> 
				<a href="<c:out value="${site.url}" />" target="_newtab"><c:out value="${site.url}" /></a>
				<p><b>
					<c:choose>
						<c:when test="${USERROLEID ne EXPERTID}">
							<a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a>
						</c:when>
						<c:otherwise>
							Root:
						</c:otherwise>
					</c:choose>
				</b>&#47;&#47;&nbsp;<b:breadcrumb serialId="${site.id}-${site.topicId}" separator="&nbsp;&#62;&#62;&nbsp;" type="SITE"/></p>
			</display:column>
			
			<display:column title="Review Status" property="status" sortName="reviewStatus" sortable="true" style="width: 102px"/>
			<c:choose>
				<c:when test="${site.reviewStatus ne true }">
					
					<display:column title="Ratings" sortName="ratings" sortable="true">
						<c:choose>
							<c:when test="${not empty site.topicId }">
								<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${site.id}&topicId=${site.topicId}&preview=1&type=a" >
			<!-- 					<i class="icon-ok"></i> -->Rate Now &#62;&#62;
								</a>
							</c:when>
						</c:choose>
					</display:column>	
			
				</c:when>
				<c:otherwise>
			
					<display:column title="Ratings" sortName="sr.score" sortable="true">
						<c:choose>
							<c:when test="${not empty site.topicId and site.status eq 'ONLINE'or site.status eq 'WAITING APPROVAL' or site.status eq 'OFFLINE'}">
								<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${site.id}&topicId=${site.topicId}&preview=1&view=1&type=a" >
				<!-- 					<i class="icon-ok"></i> -->View &#62;&#62;
								</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/admin/siteCriteriaRatings.htm?siteId=${site.id}&topicId=${site.topicId}&preview=1&type=a" >
				<!-- 				<i class="icon-ok"></i> -->View &#62;&#62;
								</a>
							</c:otherwise>
						</c:choose>
					</display:column>
			
				</c:otherwise>
			</c:choose>
			
			<display:column title="Score"> 
				<c:if test="${site.status eq 'ONLINE' and site.siteScore ne 0}">
						<c:out value="${site.siteScore}" />
				</c:if>
			</display:column>
			
			<display:column title="Edit">
				<c:choose>
					<c:when test="${USERROLEID eq EXPERTID }">
						<c:if test="${site.status eq 'NEW' or site.status eq 'IN PROGRESS' or site.status eq 'REWORK'}">
							<a href="${ctx}/admin/editSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="fa fa-edit"></i></a>
						</c:if>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/admin/editSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="fa fa-edit"></i></a>
					</c:otherwise>
				</c:choose>
			</display:column>
			
			<display:column title="Delete">
				<c:choose>
					<c:when test="${USERROLEID eq EXPERTID }">
						<c:if test="${site.status eq 'NEW' or site.status eq 'IN PROGRESS' or site.status eq 'REWORK'}">
								<a href="${ctx}/admin/deleteSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&gcd=y" 
									onclick="return confirm('Warning: This deletes the content and its reviews permanently! Are you sure?')">
									<i class="fa fa-trash"></i>
								</a>
						</c:if>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/admin/deleteSite.htm?id=${site.siteUuid}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&gcd=y" 
							onclick="return confirm('Warning: This deletes the content and its reviews permanently! Are you sure?')">
							<i class="fa fa-trash"></i>
						</a>
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table>
		
	</div>
	
</body>
</html>