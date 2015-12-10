<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>

	<c:forEach items="${errors}" var="error">
	 <font color="red">${error}</font>
	</c:forEach>


	<form action="" method="POST">
		<p>
			<label for="name">User name</label>
			<input type="text" id="name" name="name" value="${answer.name}"/>
		</p>
		<p>
			<label for="language">Language</label>
			<input type="text" id="language" name="language" value="${answer.language}"/>
		</p>
		<input type="submit" value="Add answer">
	</form>
</body>
</html>