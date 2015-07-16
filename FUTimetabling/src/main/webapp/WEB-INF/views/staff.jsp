<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Staff page</title>
<script>
	onload = function() {

	}
</script>
</head>
<body>
	Only staff can access this page.
	<p>idToken: ${idToken}</p>
	<p>accessToken: ${accessToken}</p>
	<p>email: ${email}</p>
	<a href="<c:url value='/semesters' />">Manage Semesters</a>
	<br />
	<a href="<c:url value='/rooms' />">Manage Rooms</a>
	<br />
	<a href="<c:url value='/departments' />">Manage Departments</a>
	<br />
	<a href="<c:url value='/specializeds' />">Manage Specialized</a>
	<br />
	<a href="<c:url value='/teachers' />">Manage Teachers</a>
	<br />
	<a href="<c:url value='/students' />">Manage Students</a>
	<br />
	<a href="<c:url value='/courses' />">Manage Courses</a>
	<br />
	<a href="<c:url value='/classes' />">Manage Classes</a>
	<br />
	<a href="<c:url value='/programSemesters' />">Manage
		ProgramSemesters</a>
	<br />
	<a href="<c:url value='/programSemesterDetails' />">Manage
		ProgramSemesterDetails</a>
	<br />
	<a href="<c:url value='/classSemesters' />">Manage ClassSemesters</a>
	<br />
	<a href="<c:url value='/courseSemesters' />">Manage CourseSemesters</a>
	<br />
	<a href="<c:url value='/classCourseSemesters' />">Manage
		ClassCourseSemesters</a>
	<br />
	<a href="<c:url value='/classCourseStudentSemesters' />">Manage
		ClassCourseStudentSemesters</a>
	<br />
</body>
</html>