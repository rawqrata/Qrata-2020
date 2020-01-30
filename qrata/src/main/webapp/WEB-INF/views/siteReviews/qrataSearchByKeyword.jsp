<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
	<c:import url="../../header.jsp"/>
<div id="searchTable" class="container table custom_table">
<div class="row text-right remove-margin">
<a class="btn btn-primary" id="clearTable" href="javascript:void(0);">Clear</a>
</div>
<div class="row remove-margin">
<display:table id="siteReview" name="${reviewsList}" class="displayTable custom-table table table-striped table-hover table-bordered table-condensed" style="margin-bottom:1%;margin-top:1%;" requestURI="${ctx}/reviews/search.htm">

                    <display:column title="" class="heat" style="padding-right: 8px;" headerClass="rating-column">

                   <%--      <a target="_blank" href="http://${siteReview.url}"><strong>${siteReview.siteName}</strong></a> --%>

               
						<a href="${ctx}/criterias/siteReviewRatingCriteria.htm?id=${siteReview.item_name}"><b>${siteReview.item_name}</b></a>
                        <a href="${ctx}/criterias/siteReviewRatingCriteria.htm?id=${siteReview.item_id}">Ratings:${siteReview.ratings}</a>
                         
                       
                        <div class="heat-container">
                           <c:forEach begin="1" end="${(siteReview.ratings)/10 }" >
                            <div class="notch" style="background-color:red"></div>
                           </c:forEach>

                        </div>
                       

                    </display:column>

                    <display:column title="">
                        <c:set var="strLength" value="${fn:length(siteReview.item_description)}" scope="page" />

                        <c:choose>
                            <c:when test="${strLength gt 120}">
                                <c:out value="${fn:substring(siteReview.item_description, 0, 120)}" escapeXml="false" />...
                            </c:when>
                            <c:otherwise>
                                <c:out value="${siteReview.item_description}" escapeXml="false" />...
                            </c:otherwise>
                        </c:choose>


                        <a style="color: red" href="${ctx}/reviews/findSiteReviewByTopicDetail.htm?id=${siteReview.item_id}">Learn More Here</a>

                    </display:column>

                    <display:column title="" headerClass="img-column">
                        <c:choose>
                            <c:when test="${not empty siteReview.item_image}">
                                <img alt="Content Logo" src="${ctx}/resources/images/${siteReview.item_image}" style="width: 120px;height: 90px;" />
                            </c:when>
                            <c:otherwise>
                                <img src="${ctx}/resources/images/column-image.gif" />
                            </c:otherwise>
                        </c:choose>
                    </display:column>

                </display:table>
</div>
                
</div>
<c:import url="../../footer.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#clearTable").click(function(){
    $("#searchTable").hide();
    $("#searchTerm").val("");
    location.href="/homeLayout.htm"
    $("#searchTerm").focus();
    
  });
});
</script>