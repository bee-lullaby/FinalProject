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
	<a href="<c:url value='/staff/semesters' />">Manage Semesters</a>
	<br />
	<a href="<c:url value='/staff/rooms' />">Manage Rooms</a>
	<br />
	<a href="<c:url value='/staff/departments' />">Manage Departments</a>
	<br />
	<a href="<c:url value='/staff/specializeds' />">Manage Specialized</a>
	<br />
	<a href="<c:url value='/staff/teachers' />">Manage Teachers</a>
	<br />
	<a href="<c:url value='/staff/students' />">Manage Students</a>
	<br />
	<a href="<c:url value='/staff/courses' />">Manage Courses</a>
	<br />
	<a href="<c:url value='/staff/classes' />">Manage Classes</a>
	<br />
	<a href="<c:url value='/staff/programSemesters' />">Manage
		ProgramSemesters</a>
	<br />
	<a href="<c:url value='/staff/programSemesterDetails' />">Manage
		ProgramSemesterDetails</a>
	<br />
	<a href="<c:url value='/staff/classSemesters' />">Manage
		ClassSemesters</a>
	<br />
	<a href="<c:url value='/staff/courseSemesters' />">Manage
		CourseSemesters</a>
	<br />
	<a href="<c:url value='/staff/classCourseSemesters' />">Manage
		ClassCourseSemesters</a>
	<br />
	<a href="<c:url value='/staff/classCourseStudentSemesters' />">Manage
		ClassCourseStudentSemesters</a>
	<br />
</body>
</html>