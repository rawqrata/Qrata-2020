<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang='en'>
<head>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
	<meta name="viewport" content="width=300,user-scalable=false, minimum-scale=1.0, maximum-scale=1.0" />
	<meta content="authenticity_token" name="csrf-param" />
	<meta content="3e1HCMkvH7+WznljEft1TCKeta7xAMSTvj1guBHAzXs=" name="csrf-token" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<!-- <meta charset='utf-8' /> -->
	<meta name='description' content='QRata site' />
	<title>Qrata</title>
	
	<c:set var="ctx" value="${pageContext.request.contextPath}" scope="application"></c:set>
	
	<!--[if lt IE 9]>
		    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
		  <![endif]-->
	<link href="${ctx}/resources/images/logo.png" rel="SHORTCUT ICON" />
	
	<link href="${ctx}/resources/css/style.css" media="screen" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/jquery-ui-1.8.2.custom.css" rel="stylesheet" />
	<link href="${ctx}/resources/css/displaytag.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/nanoscroller.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/font-awesome.css" rel="stylesheet" type="text/css" />
		
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/qrata-common.js"></script>
	
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
					var iframeSrc = null;					
					if($.browser.safari) {
						iframeSrc = "http://www.youtube.com/embed/e9C6a2ETOKQ";
					} else if($.browser.msie) {
						iframeSrc = "http://www.youtube.com/embed/cmQu4UcAnuQ";
					} else if($.browser.chrome) {
						iframeSrc = "http://www.youtube.com/embed/TYbJ95G2_80";
					} else {
						iframeSrc = "http://www.youtube.com/embed/BQOFC1oFFSU";
					}
					
					$("<iframe id='tutorial-iframe' src='"+iframeSrc+"' style='height: 300px;'></iframe>").appendTo("#youtube");
		});
	</script>
	
	<decorator:head> </decorator:head>
	
</head>
<body>
	<div id='gradient'></div>
	<div id='body'>
		<input type="hidden" name="contxt" id="contxt" value="${ctx}" /> 
		<a href="${ctx}"><img alt="Logo" src="${ctx}/resources/images/logo-0aeeee66a325c39254b8f09010a42eae.png" /></a>
		<div id='hd'>
			<div class='container-fluid'>
				<div class='row-fluid'>
					<div class='offset4 span4 center-hd'>
					
						<a style="margin-left: 20px;" href="${ctx}">
							<img style="margin: 35px; display: inherit;" src="${ctx}/resources/images/q-rata-logo1.png" />
						</a>
<!-- 						<h1> -->
<%-- 							<a href="${ctx}"><span class='red'>Q</span><span --%>
<!-- 								class='yellow'>/</span>Rata</a><span>&reg;</span> -->
<!-- 						</h1> -->
						<div>
							<h1>World's Most Reliable Knowledge - Guaranteed</h1>
						</div>
					</div>
					<div class='span4 right-hd'>
						<div class="plugin-installer-button">
							<a href="http://crossrider.com/download/35386" style="text-decoration: none;">Install Qrata Plugin</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id='bd'>
			<div class='container-fluid'>
				<div class='row-fluid' id='row1'>
					<div class='span8'>
						<form accept-charset="UTF-8" action="${ctx}/reviews/search.htm" class="form-search" method="get">
							<div style="margin: 0; padding: 0; display: inline">
								<input name="utf8" type="hidden" value="&#x2713;" />
							</div>
							<div class='control-group'>
								<div class='controls'>
									<div class='search'>
										<input id="searchTerm" name="q" value="${q}" autocomplete="off" placeholder="Q/Search" type="text" />
										<button type='submit'>
											<i class='icon-search'></i>
										</button>
									</div>
								</div>
							</div>
						</form>
						<p class='qtab explore'>
							<span class='red'>Q</span><span class='yellow'>/</span>Explore
						</p>
					</div>
					
					<c:if test="${!hideDiv}">

						<div class='span4 video-box1'>
							<div class='ads'>
								<div id='youtube'>
									
								</div>
							</div>
						</div>
						
					</c:if>
					
				</div>
				<div class='row-fluid' id='row2'>
					<div class='span4' style="position: relative;">
						<div class='side-nav'>

							<div id="main">
								<div class="nano">
									<div style="right: -17px;" tabindex="0" class="content">
										<ul class="root-categories unstyled" id="top-bar1">

										</ul>
									</div>
								</div>
							</div>

							<nav class="menu-gen-wrap" id="top-bar"><!--Main Navigation Starts Here-->
							<ul class="menu-gen" id="top-bar-ul">

							</ul>
							</nav>

							<ul class='q-nav unstyled footer-nav1'>
								<li><a href="${ctx}/information.htm">Q/<span class="red">Info</span></a></li>
								<li><a href="javascript:;">Q/<span class="red">Apps</span></a></li>
								<li><a href="${ctx}/contact.htm">Q/<span class="red">Contact</span></a></li>
								<li><a href="${ctx}/feedbackOption.htm">Q/<span class="red">Feedback</span></a></li>
							</ul>
							<p>
								Where<br /> &nbsp;&nbsp;&nbsp;&nbsp;search<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ends &amp;<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
									class='red'>knowledge</span><br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
									class='red'>begins!</span><span class='trade'>&trade;</span>
							</p>
						</div>
					</div>
					<div class='span8 main-content'>
						<div class='row-fluid'>
							
							<decorator:body></decorator:body>

							<div class="span4 video-box" style="background-color: inherit; margin: 2% 0 4%; padding: 0px;">
								<div class='ads'>
									<div id="youtube">
										<iframe src='http://www.youtube.com/embed/TKqvKXOEay0' height="300px;"
											id="video-show"></iframe>
									</div>
									<p style="display: block;">Static partner ads.</p>
								</div>
							</div>
							<ul class="list footer-nav"
								style="list-style-type: none; display: none;">
								<li style="float: left;"><a href="javascript:;">Q/<span
										class="red">Info</span></a></li>
								<li style="float: left;"><a href="javascript:;">Q/<span
										class="red">Apps</span></a></li>
								<li style="float: left;"><a href="javascript:;">Q/<span
										class="red">Contact</span></a></li>
								<li style="float: left;"><a href="javascript:;">Q/<span
										class="red">Feedback</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id='ft'>
			<div class='container-fluid'>
				<div class='row-fluid'>
					<div class='span12'>
						<div class='bottom-ads'>Bottom ads.</div>
						<p style="display: block; text-align: center;">&copy; 2012 -
							13 Qrata LP All Rights Reserved.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>