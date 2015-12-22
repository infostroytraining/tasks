<%@include file="include/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thank you</title>
<link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
</head>
<body>
<b>${name}</b> , thanks for your answer!

<p>
	The most popular programming languages:
</p>

<table>
	<tr>
		<th>Language</th>
		<th>Popularity</th>
	</tr>
	
	<c:forEach var="entry" items="${statisticMap}">
		<tr>
			<td>${entry.key}</td>
			<td>${entry.value}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>