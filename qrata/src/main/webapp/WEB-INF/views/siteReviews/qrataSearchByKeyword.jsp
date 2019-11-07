<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
	<c:import url="../../header.jsp"/>
<div style="margin-left:70px;">
                <display:table id="siteReview" name="${reviewsList}" class="displayTable table table-striped table-hover table-bordered table-condensed" style="margin-bottom:1%;margin-top:1%;" requestURI="${ctx}/reviews/search.htm">

                    <display:column title="" class="heat" style="padding-right: 8px;" headerClass="rating-column">

                        <a target="_blank" href="http://${siteReview.url}"><strong>${siteReview.siteName}</strong></a>

                        <br />

                        <a href="${ctx}/criterias/siteReviewRatingCriteria.htm?id=${siteReview.id}">Ratings:${siteReview.score}</a>

                        <div class="heat-container">

                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div class="notch"></div>
                            <div style="width:${siteReview.score}%;" class="heat"></div>

                        </div>

                    </display:column>

                    <display:column title="">
                        <c:set var="strLength" value="${fn:length(siteReview.description)}" scope="page" />

                        <c:choose>
                            <c:when test="${strLength gt 120}">
                                <c:out value="${fn:substring(siteReview.description, 0, 120)}" escapeXml="false" />...
                            </c:when>
                            <c:otherwise>
                                <c:out value="${siteReview.description}" escapeXml="false" />...
                            </c:otherwise>
                        </c:choose>


                        <a style="color: red" href="${ctx}/reviews/findSiteReviewByTopicDetail.htm?id=${siteReview.id}">Learn More Here</a>

                    </display:column>

                    <display:column title="" headerClass="img-column">
                        <c:choose>
                            <c:when test="${not empty siteReview.imageName}">
                                <img alt="Content Logo" src="${ctx}/sites/getContentImage.htm?id=${siteReview.siteId}" style="width: 120px;height: 90px;" />
                            </c:when>
                            <c:otherwise>
                                <img src="${ctx}/resources/images/column-image.gif" />
                            </c:otherwise>
                        </c:choose>
                    </display:column>

                </display:table>
</div>
<c:import url="../../footer.jsp"/>
        