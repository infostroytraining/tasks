<%@include file="WEB-INF/include/head.jsp" %>
<head>
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