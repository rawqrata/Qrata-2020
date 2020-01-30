

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<c:import url="../../page.jsp"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>

    <body>

        <div class="col-md-offset-1 col-md-10 no-padding gray">

            <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;Go Back</a></div>

            <div class="col-md-offset-1 col-md-10">

                <div class="col-sm-12">

                    <h3>${siteReview.topicName}</h3>

                    <div class="col-sm-12">

                        <div class="col-sm-8"><a target="_blank" href="${siteReview.url}"><strong>${siteReview.siteName}</strong></a></div>

                        <div class="col-sm-4">
                            <a href="${ctx}/expert/expertBio.htm?id=${siteReview.expertId}"><b>${siteReview.expertFirstName} ${siteReview.expertLastName}</b></a>
                            <br />
                            <a href="${ctx}/criterias/siteReviewRatingCriteria.htm?id=${siteReview.id}"><b>Rating:${siteReview.score}</b></a>
                            <br />
                            <a href="${ctx}/criterias/ratingCriteria.htm"><b>Scoring Info</b></a>
                        </div>

                </div>

                </div>

                <div class="sitereview col-sm-12">

                    <div>

                        <div>
                            <h3>Description:</h3>
                            <div>${siteReview.description}</div>
                        </div>

                        <div>
                            <h3>Evaluation:</h3>
                            <div>${siteReview.evaluation}</div>
                        </div>

                        <div>
                            <h3>Strength:</h3>
                            <div>${siteReview.strength}</div>
                        </div>

                        <div>
                            <h3>Weakness:</h3>
                            <div>${siteReview.weakness}</div>
                        </div>

                    </div>


                    <div>

                        <c:choose>

                            <c:when test="${not empty siteReview.imageName}">
                                <a target="_blank" href="${siteReview.url}">
                                    <strong>
                                        <img alt="Content Logo" src="${ctx}/sites/getContentImage.htm?id=${siteReview.siteId}" style="width: 120px; height: 90px;" />
                                    </strong>
                                </a>
                            </c:when>

                            <c:otherwise>
                                <a target="_blank" href="${siteReview.url}">
                                    <strong><img src="${ctx}/resources/images/column-image.gif" /></strong>
                                </a>
                            </c:otherwise>

                        </c:choose>

                    </div>

                </div>

            </div>

         </div>

    </body>

</html>
