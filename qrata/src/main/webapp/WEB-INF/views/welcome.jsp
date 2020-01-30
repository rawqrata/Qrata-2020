<%@page import="com.qrata.enums.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<c:import url="../header.jsp"/>
	
	<script type="text/javascript">
		$(document)
			.ready(
				function() {
					if ($.browser.msie
							&& $.browser.version.substr(0, 1) < 7) {
						$("li").has("ul").mouseover(
								function() {
									$(this).children("ul").css(
											"visibility", "visible");
								}).mouseout(
								function() {
									$(this).children("ul").css(
											"visibility", "hidden");
								});
					}
					$(".menu-gen-wrap")
							.prepend(
									'<div class="menu-gen-trigger"><span class="ico-mob">Menu</span></div>');
					$(".menu-gen-trigger").on("click", function() {
						$(".menu-gen").slideToggle();
					});
					var a = navigator.userAgent.match(/iPad/i) != null;
					if (a) {
						$(".menu-gen ul").addClass("no-transition");
					}

						/* Get Domain List */
					getDomains();

						/* Show Installation Tutorial According to Browser */
					//var iframeSrc = null;
					//if($.browser.safari) {
					//	iframeSrc = "http://www.youtube.com/embed/e9C6a2ETOKQ";
					//} else if($.browser.msie) {
					//	iframeSrc = "http://www.youtube.com/embed/cmQu4UcAnuQ";
					//} else if($.browser.chrome) {
					//	iframeSrc = "http://www.youtube.com/embed/TYbJ95G2_80";
					//} else {
					//	iframeSrc = "http://www.youtube.com/embed/BQOFC1oFFSU";
					//}
					
					//$("<iframe id='tutorial-iframe' src='"+iframeSrc+"' style='height: 300px;'></iframe>").appendTo("#youtube");
		});
	</script>

	<script type="text/javascript">
			$(document).ready(function() {
				<c:choose>
					<c:when test="${USERROLEID ne EXPERTID}">
						fetchCount();
					</c:when>
					<c:otherwise>
						fetchCountExpert();
					</c:otherwise>
				</c:choose>		
				
				/*
				 * Accordian Code
				 */
				$(".manage").click(function() {
					document.cookie = "activeTab=0;path=/";
				});
				$(".ratings").click(function() {
					document.cookie = "activeTab=1;path=/";
				});
				$(".myPublishingData").click(function() {
					document.cookie = "activeTab=2;path=/";
				});
				$(".expertPublishingData").click(function() {
					document.cookie = "activeTab=3;path=/";
				});
				$(".copyContents").click(function() {
					document.cookie = "activeTab=4;path=/";
				});
				$(".myPublishingDataExpert").click(function() {
					document.cookie = "activeTab=0;path=/";
				});


					/*
					 * Show Active Link Code
					 */
				$("#usersLink").click(function() {
					document.cookie = "activeLink=usersLink;path=/";
				});
				$("#domainsLink").click(function() {
					document.cookie = "activeLink=domainsLink;path=/";
				});
				$("#categoriesLink").click(function() {
					document.cookie = "activeLink=categoriesLink;path=/";
				});
				$("#subCategoriesLink").click(function() {
					document.cookie = "activeLink=subCategoriesLink;path=/";
				});
				$("#topicsLink").click(function() {
					document.cookie = "activeLink=topicsLink;path=/";
				});
				$("#subTopicsLink").click(function() {
					document.cookie = "activeLink=subTopicsLink;path=/";
				});
				$("#expertsLink").click(function() {
					document.cookie = "activeLink=expertsLink;path=/";
				});
				$("#editorsLink").click(function() {
					document.cookie = "activeLink=editorsLink;path=/";
				});
				$("#contentsLink").click(function() {
					document.cookie = "activeLink=contentsLink;path=/";
				});
				$("#criteriasLink").click(function() {
					document.cookie = "activeLink=criteriasLink;path=/";
				});

				
				$("#findNewLink").click(function() {
					document.cookie = "activeLink=findNewLink;path=/";
				});
				$("#findByNameLink").click(function() {
					document.cookie = "activeLink=findByNameLink;path=/";
				});
				$("#findByTopicLink").click(function() {
					document.cookie = "activeLink=findByTopicLink;path=/";
				});
				$("#findBySubTopicLink").click(function() {
					document.cookie = "activeLink=findBySubTopicLink;path=/";
				});
				$("#findByExpersLink").click(function() {
					document.cookie = "activeLink=findByExpersLink;path=/";
				});
				$("#findOfflineLink").click(function() {
					document.cookie = "activeLink=findOfflineLink;path=/";
				});

				
				$("#myRatingTopicsLink").click(function() {
					document.cookie = "activeLink=myRatingTopicsLink;path=/";
				});
				$("#myRatingSubTopicsLink").click(function() {
					document.cookie = "activeLink=myRatingSubTopicsLink;path=/";
				});
				$("#myRatingContentsLink").click(function() {
					document.cookie = "activeLink=myRatingContentsLink;path=/";
				});
				$("#myRatingInProgressLink").click(function() {
					document.cookie = "activeLink=myRatingInProgressLink;path=/";
				});
				$("#myRatingPublishedLink").click(function() {
					document.cookie = "activeLink=myRatingPublishedLink;path=/";
				});

				
				$("#expertRatingTopicsLink").click(function() {
					document.cookie = "activeLink=expertRatingTopicsLink;path=/";
				});
				$("#expertRatingSubTopicsLink").click(function() {
					document.cookie = "activeLink=expertRatingSubTopicsLink;path=/";
				});
				$("#expertRatingContentsLink").click(function() {
					document.cookie = "activeLink=expertRatingContentsLink;path=/";
				});
				$("#expertRatingInProgressLink").click(function() {
					document.cookie = "activeLink=expertRatingInProgressLink;path=/";
				});
				$("#expertRatingPendingApprovalLink").click(function() {
					document.cookie = "activeLink=expertRatingPendingApprovalLink;path=/";
				});
				$("#expertRatingReworkLink").click(function() {
					document.cookie = "activeLink=expertRatingReworkLink;path=/";
				});
				$("#expertRatingPublishedLink").click(function() {
					document.cookie = "activeLink=expertRatingPublishedLink;path=/";
				});


				$("#copyContentsLink").click(function() {
					document.cookie = "activeLink=copyContentsLink;path=/";
				});


				$("#myRatingTopicsLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingTopicsLinkExpert;path=/";
				});
				$("#myRatingSubTopicsLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingSubTopicsLinkExpert;path=/";
				});
				$("#myRatingContentsLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingContentsLinkExpert;path=/";
				});
				$("#myRatingInProgressLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingInProgressLinkExpert;path=/";
				});
				$("#myRatingPendingApprovalLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingPendingApprovalLinkExpert;path=/";
				});
				$("#myRatingReworkLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingReworkLinkExpert;path=/";
				});
				$("#myRatingPublishedLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingPublishedLinkExpert;path=/";
				});
				$("#myRatingFaqsLinkExpert").click(function() {
					document.cookie = "activeLink=myRatingFaqsLinkExpert;path=/";
				});
			});
		</script>
	



<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ADMINID" value="<%=Constants.Roles.ADMIN.getValue()%>" scope="application"></c:set>
<c:set var="EDITORID" value="<%=Constants.Roles.EDITOR.getValue()%>" scope="application"></c:set>
<c:set var="EXPERTID" value="<%=Constants.Roles.EXPERT.getValue()%>" scope="application"></c:set>
<c:set var="USERROLEID" value="${sessionScope.user.role.id}" scope="session"></c:set>

<body>
	<div class="date-text">&nbsp;</div>
	<div id="menu-bar">
		<!--menu bar left-->
		<c:if test="${USERROLEID ne EXPERTID}">
			<div id="menu">
					<div class="heading-text-new">Manage</div>
					<div id="menubody">
						<ul id="menulist">											
							<c:if test="${USERROLEID ne EDITORID}">					
								<li><a id="usersLink" href="${ctx}/admin/listUsers.htm" class="manage">- Users</a></li>
							</c:if>
							<li><a id="domainsLink" href="${ctx}/admin/listDomains.htm" class="manage">- Domains</a></li>
							<li><a id="categoriesLink" href="${ctx}/admin/allCategories.htm" class="manage">- Categories</a></li>
							<li><a id="subCategoriesLink" href="${ctx}/admin/allSubCategories.htm" class="manage">- Sub Categories</a></li>
							<li><a id="topicsLink" href="${ctx}/admin/allTopics.htm" class="manage">- Topics</a></li>
							<li><a id="subTopicsLink" href="${ctx}/admin/allSubTopics.htm" class="manage">- Sub Topics</a></li>
							<li><a id="expertsLink" href="${ctx}/admin/listExperts.htm" class="manage">- Experts</a></li>
							<li><a id="editorsLink" href="${ctx}/admin/listEditors.htm" class="manage">- Editors</a></li>							
							<li><a id="contentsLink" href="${ctx}/admin/listSites.htm" class="manage">- Content</a></li>
<%-- 						<c:if test="${USERROLEID eq ADMINID}"> --%>
                             <c:if test="${USERROLEID ne EDITORID}">		
								<li><a id="criteriasLink" href="${ctx}/admin/ratingCriteriaCategory.htm">- Criteria Mgmt</a></li>
								</c:if>
<%-- 						</c:if>					 --%>
							
						</ul>
					</div>
				
				<div id="menubottom"></div>
			</div>
			</c:if>
			<c:if test="${USERROLEID ne EXPERTID}">
				<div id="menu">
					<div class="heading-text-new">Ratings</div>
						<div id="menubody">					
							<ul id="menulist">						
								<li><a id="findNewLink" href="${ctx}/admin/findNewSiteRatings.htm" class="ratings">- Find New Ratings</a></li>
								<li><a id="findByNameLink" href="${ctx}/admin/findContentsByName.htm" class="ratings">- Find by Name </a></li>
								<li><a id="findByTopicLink" href="${ctx}/admin/getTopics.htm" class="ratings">- Find by Topic </a></li>
								<li><a id="findBySubTopicLink" href="${ctx}/admin/getSubTopics.htm" class="ratings">- Find by SubTopic </a></li>
								<li><a id="findByExpersLink" href="${ctx}/admin/getExperts.htm" class="ratings">- Find by Experts</a></li>	
								<li><a id="findOfflineLink" href="${ctx}/admin/findOfflineContents.htm" class="ratings">- Find Offline Content</a></li>					
							</ul>
						</div>
					<div id="menubottom"></div>
				</div>
			</c:if>
			<c:choose>
			<c:when test="${USERROLEID ne EXPERTID}">
				<div id="menu">
					<div class="heading-text-new">My Rating Data</div>
					<div id="menubody">
						<ul id="menulist">
							
							<li><a id="myRatingTopicsLink" href="${ctx}/admin/rateTopics_Editor.htm" class="myPublishingData">- Topics(<span id="rateTopics"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
							<li><a id="myRatingSubTopicsLink" href="${ctx}/admin/rateSubTopics_Editor.htm" class="myPublishingData">- Sub Topics(<span id="rateSubTopics"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
							<li><a id="myRatingContentsLink" href="${ctx}/admin/getContents.htm" class="myPublishingData">- Content(<span id="rateContents"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
							<li><a id="myRatingInProgressLink" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=3" class="myPublishingData">- In Progress Content(<span id="inProgress"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
							<li><a id="myRatingPublishedLink" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=1" class="myPublishingData">- Published Content(<span id="published"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
						</ul>
					</div>
					<div id="menubottom"></div>
				</div>
				<div id="menu">
					<div class="heading-text-new">Expert Rating Data</div>
					<div id="menubody">
						<ul id="menulist">
							<li><a id="expertRatingTopicsLink" href="${ctx}/admin/getAssignedTopicsToExperts.htm" class="expertPublishingData">- Topics(<span id="expertTopics"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
							<li><a id="expertRatingSubTopicsLink" href="${ctx}/admin/getAssignedSubTopicsToExperts.htm" class="expertPublishingData">- SubTopics(<span id="expertSubTopics"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
							<li><a id="expertRatingContentsLink" href="${ctx}/admin/getContentAssignedToExpert.htm" class="expertPublishingData">- Content(<span id="expertContents"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
							<li><a id="expertRatingInProgressLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=3" class="expertPublishingData">- In Progress(<span id="expertInProgress"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
							<li><a id="expertRatingPendingApprovalLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=2" class="expertPublishingData">- Pending Approval(<span id="expertPendingApproval"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
							<li><a id="expertRatingReworkLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=4" class="expertPublishingData">- Rework Content(<span id="expertRework"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
							<li><a id="expertRatingPublishedLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=1" class="expertPublishingData">- Published Content(<span id="expertPublished"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
						</ul>
					</div>
					<div id="menubottom"></div>
				</div>
				</c:when>
					<c:otherwise>
						<div class="heading-text-new">My Rating Data</div>
						<div id="menubody">
							<ul id="menulist">
								<li><a id="myRatingTopicsLinkExpert" href="${ctx}/admin/rateTopics_Expert.htm" class="myPublishingDataExpert">- Topics(<span id="expertTopics"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
								<li><a id="myRatingSubTopicsLinkExpert" href="${ctx}/admin/rateSubTopics_Expert.htm" class="myPublishingDataExpert">- Sub Topics(<span id="expertSubTopics"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
								<li><a id="myRatingContentsLinkExpert" href="${ctx}/admin/getContents.htm?role=3" class="myPublishingDataExpert">- Content(<span id="expertContents"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
								<li><a id="myRatingInProgressLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=3" class="myPublishingDataExpert">- In Progress(<span id="expertInProgress"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
								<li><a id="myRatingPendingApprovalLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=2" class="myPublishingDataExpert">- Pending Approval(<span id="expertPendingApproval"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
								<li><a id="myRatingReworkLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=4" class="myPublishingDataExpert">- Rework Content(<span id="expertRework"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
								<li><a id="myRatingPublishedLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=1" class="myPublishingDataExpert">- Published Content(<span id="expertPublished"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
								<li><a id="myRatingFaqsLinkExpert" href="${ctx}/admin/faq.htm">- Expert FAQs</a></li>
							</ul>
						</div>
						<div id="menubottom"></div>
					</c:otherwise>
				</c:choose>
				
				<c:if test="${USERROLEID ne EXPERTID}">
					<div id="menu" style="margin-top:-150px;">
						<div class="heading-text-new">Copying</div>
						<div id="menubody">
							<ul id="menulist">
								<li><a id="copyContentsLink" href="${ctx}/admin/copyContents.htm" class="copyContents">- Copy Content</a></li>
							</ul>
						</div>
						<div id="menubottom"></div>
					</div>
				</c:if>
			</div>
				
			<c:if test="${empty USERROLEID }">
				<div id="menu">
					<div class="heading-text-new3">Advertising</div>
					<div id="menubottom4"></div>
				</div>
				<div id="menu">
					<div class="heading-text-new3">Spell Check</div>
					<div id="menubottom4"></div>
				</div>
				<div id="menu">
					<div class="heading-text-new3">Front&#45;end Personalization</div>
					<div id="menubottom4"></div>
				</div>
				<div id="menu">
					<div class="heading-text-new3">Stats &#45; Summary</div>
					<div id="menubottom4"></div>
				</div>
				<div id="menu">
					<div class="heading-text-new3">Stats &#45; Session</div>
					<div id="menubottom4"></div>
				</div>
				<div id="menu">
					<div class="heading-text-new3">Stats &#45; Submitted Sites</div>
					<div id="menubottom4"></div>
				</div>
			</c:if>
	<c:import url="../footer.jsp"/>