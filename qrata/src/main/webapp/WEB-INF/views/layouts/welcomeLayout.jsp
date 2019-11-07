<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
	<meta name="viewport" content="width=320,user-scalable=false, minimum-scale=1.0, maximum-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Qrata</title>
	<link href="${ctx}/resources/images/logo.png" rel="SHORTCUT ICON" />
	
	<link href="${ctx}/resources/css/jquery-ui-1.8.2.custom-min.css" rel="stylesheet" />
	<link href="${ctx}/resources/css/style-min.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/jquery-mediaTable-min.css" rel="stylesheet" />
	
	<script src="${ctx}/resources/js/jquery-1.8.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/jquery-mediaTable-min.js"></script>
	<script src="${ctx}/resources/js/qrata.js" type="text/javascript"></script>
						
	<decorator:head></decorator:head>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.mediaTable').mediaTable();
			$('.article a').click(function(e) {
				e.preventDefault();
				$($(this).attr('href')).trigger('click');
			});
		});
	</script>
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
					if (a)
						$(".menu-gen ul").addClass("no-transition");
				});
	</script>

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
								<c:out value="${sessionScope.user.userinfo.firstname}"></c:out>&nbsp;<c:out value="${sessionScope.user.userinfo.lastname}"></c:out>
							</span>
						</div>
					</div>
					<div class="top-header" style="float:left; width: 290px;">
                         <div class="logo-qrata"><img src="${ctx }/resources/images/q-rata-logo.png" /></div>
                             <div class="heading-text-top" style="width:auto;">World's Most Reliable Knowledge - Guaranteed</div>
                        </div>

					<div class="logout-button">
						<a href="<c:url value="/logout"/>">Logout</a>
					</div>
<%-- 					<div class="main-button"><c:out value="${sessionScope.user.role.name}"></c:out> Area</div> --%>

				</div>
			</div>
			
			<decorator:body></decorator:body>
		</div>
	</div>
	<div id="footer"> &copy; 2014 Qrata LP All Rights Reserved.</div>

</body>
</html>
