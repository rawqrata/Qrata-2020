<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb" prefix="b" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="row">
	<c:choose>
		<c:when test="${param.success eq '3' }">
			<div class="success-box"><c:out value="Assignment has been done successfully" /></div>
		</c:when>
	</c:choose>
	</div>
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
                                <li class="none">Expert BIO</li>
                        	</ul>
                        	</div>
                        	<div class="col-md-4 col-4">
                   <div class="back-button"><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
                   </div>
                	</div>
                    <div class="mediaBoxT">

						<table class="mediaTable" style="background-color:#E7E7E4;">
							<thead>
					        	<tr>
									<th colspan="2" class="essential persist"> ${userInfo.firstname} ${userInfo.lastname } </th>
								</tr>
							</thead>
							<tbody>
								<tr bgcolor="#F0F3F2">
									<td width="20%" valign="top"><p>
				                    	  <div class="photo-box-user">
					                          	<span class="pict-box-user">
					                          		<c:choose>
														<c:when test="${not empty userInfo.imageName}">
															<img alt="Expert Image" src="${ctx}/admin/getUserImage.htm?userId=${userInfo.user.id}" />
														</c:when>
														<c:otherwise>
															<img alt="Expert Image" src="${ctx}/resources/images/defult-image.png" />
														</c:otherwise>
													</c:choose>
					                            </span>
				                           </div>
				                         ${userInfo.bio}
				                    </td>					
							    </tr>
							  </tbody>
						</table>
							
			<display:table id="topic" name="${topics}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
				style="margin-bottom:1%;margin-top:1%;" requestURI="expertBio.htm">
				<display:column title="Topics & Sub Topics">
					<c:out value="${topic.name}" />
					<c:choose>
						<c:when test="${not empty topic.parentTopic.id}">
							<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${topic.parentTopic.id}" type="TOPIC" /></p>
						</c:when>
						<c:otherwise>
							<p><b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;<b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${topic.category.id}" type="CATEGORY" /></p>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="View Content">
					<a href='${ctx}/admin/findContentsByTopic.htm?id=${topic.id}'><i class="fa fa-eye"></i></a>
				</display:column>
			</display:table>
							                        
                    </div>
</body>
</html>