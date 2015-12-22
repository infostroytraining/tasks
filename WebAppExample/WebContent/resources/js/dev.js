$(document).ready(function() {
	$('.remove').click(function() {
		var answer_id = $(this).attr('id');
		$.post("remove", {
			id : answer_id
		}).success(function(data) {
			$('table#content tr#' + answer_id).hide('slow');
			console.log(answer_id);
		});	
	});
	
	$(document).on("click", ".add-answer", function() {
		 $.ajax({
	           type: "POST",
	           url: "add",
	           data: $( "#answer-form" ).serialize(),
	           success: function(responseJson)
	           {
	        	   console.log("success");
	        	   console.log(responseJson);		
	           },
		 	   error: function(responseJson)
	           {
		 		   console.log("error");
	        	   console.log(responseJson);		
	           }
	         });
	});
});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
 * $('.remove-modal').click(function() { var id = $(this).attr('id');
 * $('button.remove').attr("id", id); });
 * 
 * $('button.remove').click(function() { var answer_id = $(this).attr('id');
 * $.post("remove", { id : answer_id }).success(function(data) {
 * $('table#content tr#' + answer_id).hide('slow'); console.log(answer_id); })
 * });
 */
