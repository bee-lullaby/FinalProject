<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<p>
		Welcome to "Shop application".<br /> <i>${message}</i><br /> <a
			href="${pageContext.request.contextPath}/course/create.html">Create
			a new shop</a><br /> <a
			href="${pageContext.request.contextPath}/course/list.html">View all
			shops</a><br />
	</p>
</body>
</html>
