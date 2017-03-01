//On load
$(function() {
	$('#computerName').on('input', function() {
		var input=$(this);
		var regex = /^[a-z0-9\\s._ -]*$/;
		var is_valid = regex.test(input.val());
		if(is_valid) {
			$("#nameError").css("display", "none");
		}
		else {
			$("#nameError").css("display", "inline");
			$("#nameError").css("color", "red");
		}
	});
	
	$('#discontinued').on('input', function() {
		var input=$(this);
		if(!$('#introduced').val() || new Date($('#introduced').val()).getTime() > new Date($('#discontinued').val()).getTime()) {
				$("#dateError").css("display", "inline");
				$("#dateError").css("color", "red");
			}
			else {
				$("#dateError").css("display", "none");
			}
	});
});