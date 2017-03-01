//On load
$(function() {
	$('#computerName').on('input', function() {
		console.log($('#computerName').val());
	});
});