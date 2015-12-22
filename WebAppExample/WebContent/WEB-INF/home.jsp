<%@include file="include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
<script src="resources/js/dev.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div class="container">

	<div class="row">
		<form action="" method="POST" class="form-horizontal">
			 
			<div class="form-group <c:if test="${not empty errors.name}">has-error</c:if>">
				<label class="col-sm-1 control-label" for="name" >User name</label>
				<div class="col-sm-8">
					<input type="text" id="name" name="name" class="form-control" value="${answer.name}" placeholder="name"/>
					<c:if test="${not empty errors.name}"><span class="error">${errors.name}</span></c:if>
			   </div>
			</div>
			
			<div class="form-group <c:if test="${not empty errors.language}">has-error</c:if>">
				<label class="col-sm-1 control-label" for="language">Language</label>
				<div class="col-sm-8">
					<input type="text" id="language" name="language" class="form-control" value="${answer.language}" placeholder="programming language"/>
					<c:if test="${not empty errors.language}"><span class="error">${errors.language}</span></c:if>
			   </div>
			</div>
			
			<div class="form-group">
			 	<div class="col-sm-offset-1 col-sm-8">
					<input type="submit" class="btn btn-primary" value="Add answer">
				</div>
		    </div>
		</form>
</div>
	<div class="row">
	<h4>All answers:</h4>
		<table id="content" class="table table-bordered">
			<tr>
				<th>Name</th>
				<th>Language</th>
				<th></th>
			</tr>
			<c:forEach var="answer" items="${answers}">
				<tr id="${answer.id}">
					<td>${answer.name}</td>
					<td>${answer.language}</td>
					<td><a href="#" id="${answer.id}" class="remove-answer" >remove</a>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>