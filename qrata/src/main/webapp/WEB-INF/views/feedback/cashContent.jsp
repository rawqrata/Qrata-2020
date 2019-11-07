<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:import url="../../page.jsp"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>Insert title here</title>

        <script type="text/javascript">

            function validate() {
                var topic = document.getElementById("topic").value;
                var site = document.getElementById("site_name").value;
                var url = document.getElementById("site_url").value;
                var email = document.getElementById("email").value;
                var name=document.getElementById("name");
                var zip_code = document.getElementById("zip_code").value;

                if (topic == ""||site==""||url=="" || email == "" || name == "" || zip_code == "") {
                    alert("Attention! Please be sure to fill the fields in correctly.");
                    return false;
                }
                var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
                 if (!email.match(reg)) {
                    alert("Enter valid email address");
                    return false;
                }
                return true;
            }
        </script>

    </head>

    <body>

        <div class="col-md-offset-1 col-md-10 no-padding gray">

            <div class="col-md-offset-1 col-md-10">

                <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;">Go Back</a></div>

                <h2>Cash for Better Content</h2>

                <p>We at <span class="qrata"><span class="red">Q</span><span class="yellow">/</span>Rata</span> know sites, apps and videos change, new  ones come online all the time, and even the best expert can miss something.</p>
                <p>Show us a site, app or video we find superior to what we now have and that improves <span class="qrata"><span class="red">Q</span><span class="yellow">/</span>Rata</span> and we will send you a cash reward.*</p>
                <p class="italic">Just enter and submit the info below.</p>

                <form action="${ctx}/feedbackMailCashContent.htm" class="new_entry" id="new_entry" method="post" onsubmit="return validate()">

                    <div style="margin:0;padding:0;display:inline">
                        <input name="utf8" type="hidden" value="✓">
                        <input name="authenticity_token" type="hidden" value="axrndyytbQIB78IMyj75KrRiMGM/nxEP+ljlLrXuSqg=">
                    </div>

                    <fieldset>

                            <div class="control-group">
                                <label class="control-label" for="entry_topic">Topic/Subject Area:</label><div class="controls">
                                <input class="span4" id="topic" name="topic" size="30" type="text"></div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="entry_site_name">Site, App or Video Name:</label>
                                <div class="controls"><input class="span4" id="site_name" name="site_name" size="30" type="text"></div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="entry_site_url">Site, App or Video URL:</label>
                                <div class="controls"><input class="span4" id="site_url" name="site_url" size="30" type="text"></div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="entry_name">Your Name:</label>
                                <div class="controls"><input class="span4" id="name" name="name" size="30" type="text"></div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="entry_email">Your Email Address:</label>
                                <div class="controls"><input class="span4" id="email" name="email" size="30" type="text"></div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="entry_zip_code">Zip or Postal Code:</label>
                                <div class="controls"><input class="span4" id="zip_code" name="zip_code" size="30" type="text"></div>
                            </div>

                            <div class="submit">
                                <input class="btn btn-primary" name="commit" type="submit" value="Submit Now!">
                            </div>

                        </fieldset>

                </form>

            </div>

        </div>

    </body>

</html>