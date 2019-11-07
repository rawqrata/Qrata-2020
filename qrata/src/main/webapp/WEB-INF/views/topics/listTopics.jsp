<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="b" uri="http://insonix.com/qrata/jsp/taglib/breadcrumb" %>
<%--<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>

    <body>

    <div class="col-lg-8 col-lg-offset-1">
        <h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="TOPIC" /></h5>
    </div>

        <div class="col-md-offset-1 col-md-10 gray">

            <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;Go Back</a></div>

            <div class="content-list">

                <display:table id="topic" name="${topicList}" class="displayTable table table-striped table-hover table-bordered table-condensed"
                    style="margin-bottom:1%;margin-top:1%;" requestURI="listTopics.htm">
                    <display:setProperty name="basic.msg.empty_list" value="No topics found to display"/>
                    <c:choose>
                    <c:when test="${topic.leafNode}">
                        <display:column style="width: 540px;" title="Topics">
                            <a href="${ctx}/reviews/findSiteReviewByTopic.htm?id=${topic.topicId}">${topic.name}</a>
                        </display:column>
                    </c:when>
                    <c:otherwise>
                        <display:column style="width: 540px;" title="Topics">
                            <a href="${ctx}/topics/listSubTopics.htm?id=${topic.topicId}">${topic.name}</a>
                        </display:column>
                    </c:otherwise>
                    </c:choose>

                </display:table>

            </div>

        </div>

    </body>

</html>