<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="<c:url value='/logout' />">Logout</a>
<br />
<a href="<c:url value='/back' />">Back</a>
<br />
<c:if test="${not empty error}">
	Error: ${error}
</c:if>