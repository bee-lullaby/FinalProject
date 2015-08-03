<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty error}">
	<div style="display: none" id="messageError">${error}</div>
	<script>_errorNotify();</script>
</c:if>
<c:if test="${not empty success}">
	<div style="display: none" id="messageSuccess">${success}</div>
	<script>_successNotify();</script>
</c:if>