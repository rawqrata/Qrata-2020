<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang='en'>
<head>

<c:set var="ctx" value="${pageContext.request.contextPath}"
	scope="application"></c:set>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<meta name="viewport" content="minimum-scale=1.0, maximum-scale=1.0" />
<meta content="authenticity_token" name="csrf-param" />
<meta content="3e1HCMkvH7+WznljEft1TCKeta7xAMSTvj1guBHAzXs="
	name="csrf-token" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<!-- <meta charset='utf-8' /> -->
<meta name='description' content='QRata Site' />
<title>Qrata</title>
<!--[if lt IE 9]>
		    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
		  <![endif]-->
<link href="${ctx}/resources/images/logo.png" rel="SHORTCUT ICON" />

<%--<link href="${ctx}/css/style.css" media="screen" rel="stylesheet" type="text/css" />--%>
<link href='${ctx}/resources/css/bootstrap.css' rel="stylesheet" />
<link href='${ctx}/resources/css/jquery-ui-1.8.2.custom.css'
	rel="stylesheet" />
<link href='${ctx}/resources/css/displaytag.css' rel="stylesheet"
	type="text/css" />
<link href='${ctx}/resources/css/new-style.css' rel="stylesheet"
	type="text/css" />
<link href='${ctx}/resources/css/nanoscroller.css' rel="stylesheet"
	type="text/css" />

<script type="text/javascript"
	src="${ctx}/resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src='${ctx}/resources/js/jquery-migrate-1.2.1.js'></script>
<script type="text/javascript" src='${ctx}/resources/js/jquery-ui.js'></script>
<script type="text/javascript"
	src='${ctx}/resources/js/bootstrap.min.js'></script>
<script type="text/javascript" src='${ctx}/resources/js/qrata-common.js'></script>

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


</head>
<body>
	<div id="container" class="main-container">

		<div class="gradient"></div>

		<!-- header row -->
		<div class="row" id='hd'>

			<!-- logo -->
			<div class="col-md-3 hidden-sm hidden-xs logo">
				<a href='${ctx}/'><img alt="Logo"
					src="${ctx}/resources/images/logo-0aeeee66a325c39254b8f09010a42eae.png" /></a>
			</div>

			<!-- title and black banner -->
			<div class="col-xs-12 col-sm-10 col-md-6 center-hd">

				<div class="row">
					<div class="col-sm-offset-1 col-md-10" id='logo'>
						<a href="${ctx}/" class="logo-link"><span class='red'>Q</span><span
							class='yellow'>/</span><span>Rata</span></a><span class="trademark">&reg;</span>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-offset-1 col-md-10 tag-container">
						<h1 class="tag">World's Most Reliable Knowledge - Guaranteed</h1>
					</div>
				</div>

			</div>

			<div class='col-xs-12 col-md-3'>

				<div class="center-block">
					<button type="button" class="btn btn-default main-link"
						onclick="window.location.href='http://crossrider.com/download/35386'; return false;">
						<span>Install Qrata</span>&nbsp;<span
							class="glyphicon glyphicon-download"></span>
					</button>
					<button type="link" class="btn btn-default main-link"
						onclick="window.location.href='cashContent.htm;';return false;">
						<span>Cash for Better Content</span>&nbsp;<span
							class="glyphicon glyphicon-usd"></span>
					</button>
				</div>

			</div>

		</div>

		<div class="row" id='bd'>

			<!-- first column -->
			<div class="col-xs-12 col-sm-4 col-md-3">

				<div class="qexplore">
					<span class='red'>Q</span> <span class='yellow'>/</span> <span>Explore</span>
				</div>

				<div class="col-xs-12">

					<!-- <div class="nano"> -->
					<ul class="root-categories unstyled" id="top-bar1">

					</ul>
					<!-- </div> -->
				</div>

				<div class="col-sm-6 ray-small1 q-nav-container">

					<ul class='q-nav unstyled'>
						<li><a href="${ctx}/information.htm">Q/<span class="red">Info</span></a></li>
						<li><a href="javascript:;">Q/<span class="red">Apps</span></a></li>
						<li><a href="${ctx}/contact.htm">Q/<span class="red">Contact</span></a></li>
						<li><a href="${ctx}/feedbackOption.htm">Q/<span
								class="red">Feedback</span></a></li>
					</ul>

				</div>

				<div class="col-sm-6 ray-small q-nav-container ray-small2">

					<div class="col-md-6 hidden-xs hidden-sm q-nav tag2">
						<p class="p1">Where</p>
						<p class="p2">search</p>
						<p class="p3">ends &amp;</p>
						<p class="p4">
							<span class='red'>knowledge</span>
						</p>
						<p>
							<span class='red'>begins!</span><span class='trade'>&trade;</span>
						</p>
					</div>
				</div>

			</div>


			<!-- second large column -->
			<div class="col-sm-8 col-md-6 move-up">

				<!-- form with search box -->
				<div class="row">

					<div class="col-md-offset-1 col-md-10 no-padding">

						<form accept-charset="UTF-8" action="${ctx}/reviews/search.htm"
							class="form-search" method="get">

							<input type="hidden" name="contxt" id="contxt" value="${ctx}" />
							<div style="margin: 0; padding: 0; display: inline">
								<input name="utf8" type="hidden" value="&#x2713;" />
							</div>

							<div class="right-inner-addon">
								<input type="text" id="searchTerm" name="q" value="${q}"
									class="form-control" placeholder="Q/Search" />
								<button type="submit" class="btn btn-sm btn-default">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</div>

						</form>

					</div>
				</div>

				<div class="col-md-offset-9 col-md-2 qsearch">
					<span class="red">Q</span> <span class="yellow">/</span> <span>Search</span>
				</div>

				<!-- search results -->
				<div class="row no-padding" style="min-height: 400px">
					<decorator:body></decorator:body>
				</div>

			</div>

			<!-- third column -->
			<div class="col-sm-12 col-md-3 move-up">
				<iframe width="420" height="315"
					src="//www.youtube.com/embed/TKqvKXOEay0" frameborder="0"
					allowfullscreen></iframe>
				<div class='ads'>
					<p>Static partner ads.</p>
					<p>Static partner ads.</p>
					<p>Static partner ads.</p>
					<p>Static partner ads.</p>
				</div>
			</div>

		</div>

	</div>

	<div class="row" id='ft'>
		<div class="col-sm-12">
			<div class='bottom-ads'>Bottom ads.</div>
		</div>
		<div style="display: block; text-align: center;">&copy; 2012 -
			15 Qrata LP All Rights Reserved.</div>
	</div>

</body>

</html>

