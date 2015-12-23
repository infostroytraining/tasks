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
		<form id="answer-form" class="form-horizontal">
			 
			<div id="form-name" class="form-group">
				<label class="col-sm-1 control-label" for="name" >User name</label>
				<div id="name" class="col-sm-8">
					<input type="text" id="name" name="name" class="form-control" value="${answer.name}" placeholder="name"/>
			   </div>
			</div>
			
			<div id="form-language" class="form-group ">
				<label class="col-sm-1 control-label" for="language">Language</label>
				<div id="language" class="col-sm-8">
					<input type="text" id="language" name="language" class="form-control" value="${answer.language}" placeholder="programming language"/>
			   </div>
			</div>
			
			<div class="form-group">
			 	<div class="col-sm-offset-1 col-sm-8">
					<input class="btn btn-primary add-answer" type="button"  value="Add answer">
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
					<td> <a id="${answer.id}" class="remove-answer">remove</a> </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<!-- 
    <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Confirmation</h4>
                </div>
                <div class="modal-body">
                    <p>Do you really want to delete this answer?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" id="" class="btn btn-primary remove" data-dismiss="modal">Delete</button>
                </div>
            </div>
        </div>
    </div>
 -->
</body>
</html>