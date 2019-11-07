<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>

    <body>

        <div class="col-md-offset-1 col-md-10 no-padding gray">

            <label>CONTENTS</label>

            <div class="col-md-offset-10"><a href="#" onclick="history.back(); return false;"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;Go Back</a></div>

            <display:table id="sites" name="${sites}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
			    style="margin-bottom:1%;margin-top:1%;" requestURI="findContentsByTopic.htm">

                <display:column style="width: 540px;" title="Content">
				    <a href="${ctx}/admin/findContentsByTopic.htm?id=${sites.id}">${sites.name}</a>
				    <br/>
				    <strong>"${sites.url}</strong>
                </display:column>
            </display:table>

            </div>

        </div>

    </body>

</html>