
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
                var body = document.getElementById("feedback_body").value;
                var name = document.getElementById("feedback_name").value;
                var email = document.getElementById("feedback_email").value;


                var zip_code = document.getElementById("feedback_zip_code").value;
                if (body == "" || email == "" || name == "" || zip_code == "") {
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

    <div class="col-md-offset-1 col-md-10 gray">

        <div class="col-md-offset-1 col-md-10">

            <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;">&laquo;&nbsp;Go Back</a></div>

                <h2 class="q-nav">
                    <span class="black">Q/</span><span class="red">Feedback</span>
                </h2>

                <c:if test="${kind eq 'suggestions'}">
                    <c:set var="title" value="Suggestions" />
                   <c:set var="content" value="suggestion" />
                    <c:set var="heading" value="Suggestion" />
                </c:if>

                <c:if test="${kind eq 'questions'}">
                    <c:set var="title" value="Questions" />
                      <c:set var="content" value="question" />
                    <c:set var="heading" value="Question" />
                </c:if>

                <c:if test="${kind eq 'problems'}">
                    <c:set var="title" value="Problems" />
                      <c:set var="content" value="problem" />
                    <c:set var="heading" value="Problem" />
                </c:if>

                <c:if test="${kind eq 'complaints'}">
                    <c:set var="title" value="Complaints" />
                      <c:set var="content" value="complaint" />
                    <c:set var="heading" value="Complaint" />
                </c:if>

                <c:if test="${kind eq 'brief survey'}">
                    <c:set var="title" value="Brief Survey" />
                      <c:set var="content" value="take 2 - 3 minutes to answer a couple of short questions" />
                    <c:set var="heading" value="Questions" />
                </c:if>

                <c:if test="${kind eq 'become a reviewer'}">
                    <c:set var="title" value="Become a Reviewer" />
                      <c:set var="content" value="expertise" />
                    <c:set var="heading" value="Expertise and background" />

                </c:if>

                <h3 class="q-nav">${title}</h3>

                <p>
                    We at <span class="qrata"><span class="red">Q</span><span
                        class="yellow">/</span>Rata</span> want to help you and improve our
                    service. Please state your ${content} below.
                </p>

                <p class="italic">
                    Just enter and submit the info below.<br> Thank You!
                </p>

                <form action="${ctx}/feedbackMail.htm" name="new_feedback"                 class="new_feedback" id="new_feedback" method="post" onsubmit="return validate()">

                    <div style="margin: 0; padding: 0; display: inline">
                        <input name="utf8" type="hidden" value="✓">
                        <input name="authenticity_token" type="hidden" value="axrndyytbQIB78IMyj75KrRiMGM/nxEP+ljlLrXuSqg=">
                    </div>

                    <fieldset>

                        <div class="control-group">
                            <label class="control-label" for="feedback_body">${heading}</label>
                            <div class="controls">
                                <textarea class="span8" cols="40" id="feedback_body" name="feedback_body" rows="4"></textarea>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="feedback_name">Your Name:</label>
                            <div class="controls">
                                <input class="span4" id="feedback_name" name="feedback_name" size="30" type="text">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="feedback_email">Your Email Address:</label>
                            <div class="controls">
                                <input class="span4" id="feedback_email" name="feedback_email" size="30" type="text">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="feedback_zip_code">Zip or Postal Code:</label>
                            <div class="controls">
                                <input class="span4" id="feedback_zip_code" name="feedback_zip_code" size="30" type="text">
                            </div>
                        </div>

                        <input id="feedback_kind" name="feedback_kind" type="hidden" value="${kind}">

                        <div class="submit" >
                            <input class="btn btn-primary" style="margin-top:10px;" name="commit" type="submit" value="Submit Now!"> <input id="kind" name="kind" type="hidden" value="${kind}">
                            <input id="heading" name="heading" type="hidden" value="${heading}">
                        </div>

                    </fieldset>

                </form>

            </div>

        </div>

    </body>

</html>