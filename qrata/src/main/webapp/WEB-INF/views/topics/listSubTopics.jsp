<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>

    <body>

    <div class="col-lg-8 col-lg-offset-1">
        <h5><a href="listDomains.htm">Root</a>://<b:breadcrumb separator="/" serialId="${id}" type="SUBTOPIC" /></h5>
    </div>

        <div class="col-md-offset-1 col-md-10 gray">

            <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;Go Back</a></div>

            <div class="content-list">

                <display:table id="subTopic" name="${subTopicList}" class="displayTable table table-striped table-hover table-bordered table-condensed"
                    style="margin-bottom:1%;margin-top:1%;" requestURI="listSubTopics.htm">
                    <display:setProperty name="basic.msg.empty_list" value="No subtopics found to display"/>
                    <display:column style="width: 540px;" title="Sub Topics">
                        <a href="${ctx}/reviews/findSiteReviewByTopic.htm?id=${subTopic.topicId}">${subTopic.name}</a>
                    </display:column>
                </display:table>

            </div>

        </div>

    </body>

</html>