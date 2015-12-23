$(document).ready(function() {
	
	function addEventListener(){
		$('.remove-answer').click(function() {
			var answer_id = $(this).attr('id');
			$.post("remove", {
						id : answer_id
					}).success(function(data) {
						$('table#content tr#' + answer_id).hide('slow');
						console.log(answer_id);
					});
				});
	}
	addEventListener();
	
	$(document).on( "click", ".add-answer",
			function() {
				$.ajax({
					type : "POST",
					url : "add",
					data : $("#answer-form").serialize(),
					success : function(record) {
						console.log(record);
						var name_td = "<td>" + record.name + "</td>" ;
						var language_td = "<td>" + record.language + "</td>" ;
						var remove_td = "<td> <a id=" + record.id + " class=\"remove-answer\">remove</a> </td>";
						$('#content tr:last').after("<tr id=" + record.id + ">" + name_td + language_td + remove_td + "</tr>");
						$('#answer-form').trigger("reset");
						addEventListener();
					},
					error : function(data) {
						console.log(data);
						// var result = JSON.parse(responseJson.responseText);
						$.each(data.responseJSON, function(key, value) {
							console.log(key);
							console.log(value);
							$('#form-' + key).addClass("has-error");
							$('#' + key + ".col-sm-8").append("<span class='error'>" + value + "</span>");
						});
					}
				});
			});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
		  $('.remove-answer').click(function() { 
			 var id = $(this).attr('id');
			 console.log(id);
			 $('button.remove').attr("id", id); 
		  });
		  
		  $('button.remove').click(function() {
			  var answer_id = $(this).attr('id');
			  $.post("remove", { 
				  id : answer_id 
			   }).success(function(data) {
				   	$('table#content tr#' + answer_id).hide('slow'); console.log(answer_id); })
		  });*/
});
