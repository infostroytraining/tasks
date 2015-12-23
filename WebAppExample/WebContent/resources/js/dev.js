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
	
	function clearValidation(){
		$('span.error').remove();
		$('.form-group').removeClass('has-error');
	}
	
	$(document).on( "click", ".add-answer",
			function() {
				$.ajax({
					type : "POST",
					url : "add",
					data : $("#answer-form").serialize(),
					success : function(record) {
						console.log(record);
						clearValidation();
						// append received record to the end of the table
						var name_td = "<td>" + record.name + "</td>" ;
						var language_td = "<td>" + record.language + "</td>" ;
						var remove_td = "<td> <a id=" + record.id + " class=\"remove-answer\">remove</a> </td>";
						$('#content tr:last').after("<tr id=" + record.id + ">" + name_td + language_td + remove_td + "</tr>");
						
						//reset form with last input
						$('#answer-form').trigger("reset");
						addEventListener();
					},
					error : function(data) {
						console.log(data);
						clearValidation();
						$.each(data.responseJSON, function(key, value) {
							console.log(key);
							console.log(value);
							//append error message to the input field and add has-error class 
							$('#form-' + key).addClass("has-error");
							$('#' + key + ".col-sm-8").append("<span class='error'>" + value + "</span>");
						});
					}
				});
			});
	
    /*Uncomment to use modal window*/	
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
