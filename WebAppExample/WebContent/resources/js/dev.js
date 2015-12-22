$(document).ready(function() {
	$(".remove-answer").click(function() {
		var answer_id = $(this).attr('id');
		$.post("remove", {
			id : answer_id
		}).done(function(data) {
			$('table#content tr#' + answer_id).hide('slow');
			console.log(answer_id);
		})
	});
});