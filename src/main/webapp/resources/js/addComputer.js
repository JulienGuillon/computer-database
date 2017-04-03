//On load
var nameIsValid = false;
var dateIsValid = true;
$(function() { 
	$('#computerName').on('input', function() {
		var input=$(this);
		var regex = /^[a-zA-Z0-9\\s._ -]+$/;
		var is_valid = regex.test(input.val());
		if(is_valid) {
			$("#nameError").css("display", "none");
	         nameIsValid = true;
	    }
		else {
			$("#nameError").css("display", "inline");
			$("#nameError").css("color", "red");
			nameIsValid = false;
		}
		validate();
	});
	
	$('#discontinued').on('input', function() {
		var input=$(this);
		if(!$('#introduced').val() || new Date($('#introduced').val()).getTime() > new Date($('#discontinued').val()).getTime()) {
				$("#dateError").css("display", "inline");
				$("#dateError").css("color", "red");
				dateIsValid = false;
			}
			else {
				$("#dateError").css("display", "none");
				dateIsValid = true;
			}
		validate();
	});
});

function validate() {
    console.log(nameIsValid);
    if (nameIsValid && dateIsValid) {
        $("#validAdd").removeClass("disabled");
    } else {
        $("#validAdd").addClass("disabled");
    }
}