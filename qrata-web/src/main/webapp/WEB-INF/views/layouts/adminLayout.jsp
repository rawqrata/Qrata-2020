<%@page import="com.insonix.qrata.constants.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<c:set var="ADMINID" value="<%=Constants.Roles.ADMIN.getValue()%>" scope="application"></c:set>
	<c:set var="EDITORID" value="<%=Constants.Roles.EDITOR.getValue()%>" scope="application"></c:set>
	<c:set var="EXPERTID" value="<%=Constants.Roles.EXPERT.getValue()%>" scope="application"></c:set>
	<c:set var="USERROLEID" value="${sessionScope.user.role.id}" scope="session"></c:set>
	
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
	<meta name="viewport" content="width=320,user-scalable=false, minimum-scale=1.0, maximum-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Qrata</title>
	<link href="${ctx}/resources/images/logo.png" rel="SHORTCUT ICON" />
	
	<link href="${ctx}/resources/css/jquery-ui-1.8.2.custom-min.css" rel="stylesheet" />
	<link href="${ctx}/resources/css/style-min.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/displaytag-min.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/jquery-mediaTable-min.css" rel="stylesheet" />
	<link href="${ctx}/resources/css/jquery-fancybox-min.css" rel="stylesheet" />
	
	<script src="${ctx}/resources/js/jquery-1.8.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/jquery-mediaTable-min.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/jquery-ui-min.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/jquery.fancybox.pack.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/qrata.js" type="text/javascript"></script>
		
	<script type="text/javascript">
		$(document).ready(function() {
			$('.mediaTable').mediaTable();
			$('.article a').click(function(e) {
				e.preventDefault();
				$($(this).attr('href')).trigger('click');
				
			});
			
			<c:choose>
				<c:when test="${USERROLEID ne EXPERTID}">
					fetchCount();
				</c:when>
				<c:otherwise>
					fetchCountExpert();
				</c:otherwise>
			</c:choose>
			
			/*
			 * Back Button Cookie Check
			 */
			checkBackButton();
			
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

			implementAccordian();
			showActiveLink();
			//monkeyPatchAutoComplete();
		});
	</script>
	
	<script type="text/javascript">
		$(document).ready(
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
				if (a)
					$(".menu-gen ul").addClass("no-transition");
		});
	</script>
	
<decorator:head> </decorator:head>
</head>

<body>
	<div class="main-section">
		<div id="main-container">
			<input type="hidden" name="contxt" id="contxt" value="${ctx}" />
			<!--header-->

			<div class="header">
				<div class="header-container">
					<div class="left-top">
						<div class="logo">
							<a href="${ctx}/welcome.htm"><img src="${ctx}/resources/images/logo.png" alt="Q/Rata Logo" /></a>
						</div>
						<div class="welcome-text">
							<span>
								Welcome <c:out value="${sessionScope.user.role.name}"></c:out>!
							</span><br/>
							<span>
								<c:out value="${sessionScope.user.userinfo.firstname }"></c:out>&nbsp;<c:out value="${sessionScope.user.userinfo.lastname}"></c:out>
							</span>
						</div>
					</div>
					
					<div class="top-header" style="float:left; width: 290px;">
                         <div class="logo-qrata">
                         	<img src="${ctx}/resources/images/q-rata-logo.png" />
                         </div>
                         <div class="heading-text-top" style="width:auto;">World's Most Reliable Knowledge - Guaranteed</div>
                        </div>
					<div class="logout-button">
						<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
					</div>
<%-- 					<div class="main-button"><c:out value="${sessionScope.user.role.name}"></c:out> Area</div> --%>
				</div>
			</div>
			
			<!--main header-->
			<div class="container">
			
				<!--menu bar left-->
				<div class="left-section" style="float: left;margin-right: 22px;margin-top: 37px;width: 231px;font-size:12px;min-height: 400px;">
			        <h3 class="radius-border"><a href="../welcome.htm">Home</a></h3>
			        
			        <div id="accordion">									
					
						<c:if test="${USERROLEID ne EXPERTID}">
							<h3>Manage</h3>
								<div>
									<ul style="list-style-type:none; padding-left:0px; line-height:25px;">							
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
									  <c:if test="${USERROLEID ne EDITORID}">
								<li><a id="criteriasLink" href="${ctx}/admin/ratingCriteriaCategory.htm">- Criteria Mgmt</a></li>
								</c:if>
												
 									
									</ul>
								</div>
						</c:if>
										
						<c:if test="${USERROLEID ne EXPERTID}">
							<h3>Ratings</h3>
								<div>
									<ul style="list-style-type:none; padding-left:0px; line-height:25px;">											
										<li><a id="findNewLink" href="${ctx}/admin/findNewSiteRatings.htm" class="ratings">- Find New Ratings</a></li>
										<li><a id="findByNameLink" href="${ctx}/admin/findContentsByName.htm" class="ratings">- Find by Name </a></li>
										<li><a id="findByTopicLink" href="${ctx}/admin/getTopics.htm" class="ratings">- Find by Topic </a></li>
										<li><a id="findBySubTopicLink" href="${ctx}/admin/getSubTopics.htm" class="ratings">- Find by SubTopic </a></li>
										<li><a id="findByExpersLink" href="${ctx}/admin/getExperts.htm" class="ratings">- Find by Experts</a></li>
										<li><a id="findOfflineLink" href="${ctx}/admin/findOfflineContents.htm" class="ratings">- Find Offline Content</a></li>							
									</ul>
								</div>
						
						</c:if>
						
						<c:if test="${empty USERROLEID }">
							<li>
								<h1>
									<a href="javascript:;">Advertising</a>
								</h1>
							</li>
							<li>
								<h1>
									<a href="javascript:;">Spell Check</a>
								</h1>
							</li>
						</c:if>
					
						<c:choose>
							<c:when test="${USERROLEID ne EXPERTID}">
								<h3>My Rating Data</h3>
									<div>
										<ul style="list-style-type:none; padding-left:0px; line-height:25px;">
											<li><a id="myRatingTopicsLink" href="${ctx}/admin/rateTopics_Editor.htm" class="myPublishingData">- Topics(<span id="rateTopics"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
											<li><a id="myRatingSubTopicsLink" href="${ctx}/admin/rateSubTopics_Editor.htm" class="myPublishingData">- Sub Topics(<span id="rateSubTopics"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
											<li><a id="myRatingContentsLink" href="${ctx}/admin/getContents.htm" class="myPublishingData">- Content(<span id="rateContents"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
											<li><a id="myRatingInProgressLink" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=3" class="myPublishingData">- In Progress Content(<span id="inProgress"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>
											<li><a id="myRatingPublishedLink" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=1" class="myPublishingData">- Published Content(<span id="published"></span><span class="myDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." width="12" /></span>)</a></li>								
										</ul>
									</div>
							</c:when>
							<c:otherwise>
								<h3>My Rating Data</h3>
									<div>
										<ul style="list-style-type:none; padding-left:0px; line-height:25px;">									
											<li><a id="myRatingTopicsLinkExpert" href="${ctx}/admin/rateTopics_Expert.htm" class="myPublishingDataExpert">- Topics(<span id="expertTopics"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
											<li><a id="myRatingSubTopicsLinkExpert" href="${ctx}/admin/rateSubTopics_Expert.htm" class="myPublishingDataExpert">- Sub Topics(<span id="expertSubTopics"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
											<li><a id="myRatingContentsLinkExpert" href="${ctx}/admin/getContents.htm?role=3" class="myPublishingDataExpert">- Content(<span id="expertContents"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
											<li><a id="myRatingInProgressLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=3" class="myPublishingDataExpert">- In Progress(<span id="expertInProgress"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
											<li><a id="myRatingPendingApprovalLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=2" class="myPublishingDataExpert">- Pending Approval(<span id="expertPendingApproval"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
											<li><a id="myRatingReworkLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=4" class="myPublishingDataExpert">- Rework Content(<span id="expertRework"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
											<li><a id="myRatingPublishedLinkExpert" href="${ctx}/admin/getReviewsByStatus.htm?reviewByStatus=1" class="myPublishingDataExpert">- Published Content(<span id="expertPublished"></span><span class="loadingImgExpert" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>			
											<li><a id="myRatingFaqsLinkExpert" href="${ctx}/admin/faq.htm" class="myPublishingDataExpert">- Expert FAQs</a></li>							
										</ul>
									</div>
							</c:otherwise>
						</c:choose>
					
						<c:if test="${USERROLEID ne EXPERTID}">
							<h3>Expert Rating Data</h3>
								<div>
									<ul style="list-style-type:none; padding-left:0px; line-height:25px;">												
										<li><a id="expertRatingTopicsLink" href="${ctx}/admin/getAssignedTopicsToExperts.htm" class="expertPublishingData">- Topics(<span id="expertTopics"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
										<li><a id="expertRatingSubTopicsLink" href="${ctx}/admin/getAssignedSubTopicsToExperts.htm" class="expertPublishingData">- SubTopics(<span id="expertSubTopics"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
										<li><a id="expertRatingContentsLink" href="${ctx}/admin/getContentAssignedToExpert.htm" class="expertPublishingData">- Content(<span id="expertContents"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
										<li><a id="expertRatingInProgressLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=3" class="expertPublishingData">- In Progress(<span id="expertInProgress"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
										<li><a id="expertRatingPendingApprovalLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=2" class="expertPublishingData">- Pending Approval(<span id="expertPendingApproval"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
										<li><a id="expertRatingReworkLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=4" class="expertPublishingData">- Rework Content(<span id="expertRework"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>
										<li><a id="expertRatingPublishedLink" href="${ctx}/admin/getExpertsContentsByReviewStatus.htm?reviewByStatus=1" class="expertPublishingData">- Published Content(<span id="expertPublished"></span><span class="expertDataLoadingImg" style="display: none;"><img src="${ctx}/resources/images/fancybox_loading.gif" alt="..." style="width: 12px;" /></span>)</a></li>								
									</ul>
								</div>
						</c:if>
						
						<c:if test="${USERROLEID ne EXPERTID}">
							<h3>Copying</h3>
							<div>
								<ul style="list-style-type:none; padding-left:0px; line-height:25px;">
									<li><a id="copyContentsLink" href="${ctx}/admin/copyContents.htm" class="copyContents">- Copy Content</a></li>
								</ul>
							</div>
						</c:if>
					
						<c:if test="${empty USERROLEID}">
							<li>
								<h1>
									<a href="javascript:;">Status</a>
								</h1>
							</li>
							<li><a href="javascript:;">Summary</a></li>
							<li><a href="javascript:;">Session</a></li>
							<li><a href="javascript:;">Submitted Content</a></li>
							<li>&nbsp;</li>
						</c:if>
					
					</div>
				</div>
				
				<decorator:body></decorator:body>

			</div>

		</div>
	</div>
	<div id="footer"> &copy 2013 Qrata LP All Rights Reserved.</div>

</body>
</html>
