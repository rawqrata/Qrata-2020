<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<script type="text/javascript">
	function setCharacter(searchCh) {
		document.getElementById("userSearchVal").value = searchCh;
		document.getElementById("userForm").submit();
	}
</script>
<body>
	<div class="span6">
		<h2 class="q-nav">
			<span class="black">Q/</span><span class="red">Info</span>
		</h2>
		<h3 class="q-nav">Experts</h3>
		<p>Find by Last Name</p>
		<form:form cssClass="form-search" commandName="userForm"
			action="listExperts.htm">
			<input type="hidden" name="userSearchVal" id="userSearchVal" value="">
        </form:form>

			<a href="JavaScript:;" onclick="setCharacter('A');">A</a>
			<a href="JavaScript:;" onclick="setCharacter('B');">B</a>
			<a href="JavaScript:;" onclick="setCharacter('C');">C</a>
			<a href="JavaScript:;" onclick="setCharacter('D');">D</a>
			<a href="JavaScript:;" onclick="setCharacter('E');">E</a>
			<a href="JavaScript:;" onclick="setCharacter('F');">F</a>
			<a href="JavaScript:;" onclick="setCharacter('G');">G</a>
			<a href="JavaScript:;" onclick="setCharacter('H');">H</a>
			<a href="JavaScript:;" onclick="setCharacter('I');">I</a>
			<a href="JavaScript:;" onclick="setCharacter('J');">J</a>
			<a href="JavaScript:;" onclick="setCharacter('K');">K</a>
			<a href="JavaScript:;" onclick="setCharacter('L');">L</a>
			<a href="JavaScript:;" onclick="setCharacter('M');">M</a>
			<a href="JavaScript:;" onclick="setCharacter('N');">N</a>
			<a href="JavaScript:;" onclick="setCharacter('O');">O</a>
			<a href="JavaScript:;" onclick="setCharacter('P');">P</a>
			<a href="JavaScript:;" onclick="setCharacter('Q');">Q</a>
			<a href="JavaScript:;" onclick="setCharacter('R');">R</a>
			<a href="JavaScript:;" onclick="setCharacter('S');">S</a>
			<a href="JavaScript:;" onclick="setCharacter('T');">T</a>
			<a href="JavaScript:;" onclick="setCharacter('U');">U</a>
			<a href="JavaScript:;" onclick="setCharacter('V');">V</a>
			<a href="JavaScript:;" onclick="setCharacter('W');">W</a>
			<a href="JavaScript:;" onclick="setCharacter('X');">X</a>
			<a href="JavaScript:;" onclick="setCharacter('Y');">Y</a>
			<a href="JavaScript:;" onclick="setCharacter('Z');">Z</a>
		

		<c:choose>
			<c:when test="${totalExpertSize eq 0}">
				<div style="display: none;"></div>
			</c:when>
			<c:otherwise>

				<div id="hide">
					<display:table id="expert" name="${experts}" class="displayTable"
						style="margin-bottom:1%;margin-top:1%;" size="${totalExpertSize }"
						partialList="true" requestURI="listExperts.htm" pagesize="10"
						defaultsort="1" defaultorder="descending" sort="external">
						<display:column style="width: 540px;">
							<a href="${ctx}/expert/expertBio.htm?id=${expert.id}"><b>${expert.firstName}
									${expert.lastName}</b></a>
							<br />
				             ${fn:substring(expert.bio, 0, 120)}  <a style="color: red;"
								href="${ctx}/expert/expertBio.htm?id=${expert.id}">Learn
								More Here</a>
						</display:column>
						<display:column style="width: 200px;">
							<c:choose>
			<c:when test="${not empty expert.imageName}">
				<img style="float: right;" alt="Expert Image"
					src="${ctx}/expert/getUserImage.htm?userId=${expert.id}" />
			</c:when>
			<c:otherwise>
				<img style="float: right;" alt="Expert Image"
					src="${ctx}/resources/images/defult-image.png" />
			</c:otherwise>
		</c:choose>
						</display:column>

					</display:table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>