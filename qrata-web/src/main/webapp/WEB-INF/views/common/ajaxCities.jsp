<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<option value="">Select</option>
<c:forEach items="${cities}" var="city">
<option value="${city.cityId}"><c:out value="${city.name}" /></option>
</c:forEach>