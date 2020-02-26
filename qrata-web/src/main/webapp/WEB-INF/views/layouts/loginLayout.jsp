<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
   <c:set var="ctx" value="${pageContext.request.contextPath}" />
   <head>
      <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;"/>
      <meta name="viewport" content="width=320,user-scalable=false, minimum-scale=1.0, maximum-scale=1.0" />
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <title>Qrata</title>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="${ctx}/resources/css/bootstrap4/bootstrap.min.css">
      <script src="${ctx}/resources/js/bootstrap/jquery.min.js"></script>
      <script src="${ctx}/resources/js/bootstrap/popper.min.js"></script>
      <script src="${ctx}/resources/js/bootstrap/bootstrap.min.js"></script>
      <script src="https://rawgit.com/JulianLaval/canvas-particle-network/master/particle-network.min.js" type="text/javascript"></script>
      <link href="${ctx}/resources/images/logo.png" rel="SHORTCUT ICON" />
      <link href="${ctx}/resources/css/style-min.css" rel="stylesheet" type="text/css" />
      <link href="${ctx}/resources/css/jquery-mediaTable-min.css" rel="stylesheet" type="text/css" />
      <script src="${ctx}/resources/js/jquery-1.8.2.min.js" type="text/javascript"></script>
      <script src="${ctx}/resources/js/jquery-mediaTable-min.js"></script>
      <script type="text/javascript">
         $(document).ready(function(){
         	$('.mediaTable').mediaTable();
         <!--	$('#create').click(function(){      $('.mediaTable').mediaTable()     }); -->
         <!--	$('#destroy').click(function(){     $('.mediaTable').mediaTable('destroy')     });
         	-->
         	$('.article a').click(function(e){
         		e.preventDefault();
         		$($(this).attr('href')).trigger('click');
         	});
         	
         });
      </script>
      <script type="text/javascript">
         $(document).ready(function() {			 
         if ($.browser.msie && $.browser.version.substr(0, 1) < 7) {
             $("li").has("ul").mouseover(function () {
                 $(this).children("ul").css("visibility", "visible");
             }).mouseout(function () {
                 $(this).children("ul").css("visibility", "hidden");
             });
         }
         $(".menu-gen-wrap").prepend('<div class="menu-gen-trigger"><span class="ico-mob">Menu</span></div>');
         $(".menu-gen-trigger").on("click", function () {
             $(".menu-gen").slideToggle()
         });
         var a = navigator.userAgent.match(/iPad/i) != null;
         if (a) $(".menu-gen ul").addClass("no-transition");
         });
      </script> 
      <decorator:head></decorator:head>
   </head>
   <body>
      <div class="container-login">
         <decorator:body></decorator:body>
      </div>
      <!--  <div id="footer"> &copy; 2013 Qrata LP All Rights Reserved.</div> -->
   </body>
</html>