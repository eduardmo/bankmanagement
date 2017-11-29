/**
 * 
 */

$(function() {
	jQuery.validator.addMethod("validPNC", function(value, element) {
		return /^[1-9]{1}[0-9]+$/.test(value);
	}, "Only Numerical Characters Allowed!");
	jQuery.validator.addMethod("validCardNumber", function(value, element) {
		console.log("ceva acolo sa fie" + value);
		return /^[A-Z]{2}[0-9]{6}$/.test(value);
	}, "Invalid Id Card Number format!");
	$("#formAdd").validate({
		rules : {
			customerPersonalNumericalCode : {
				required : true,
				validPNC : true,
				minlength : 13,
				maxlength : 13,
			},
			customerName : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			customerIdCardNumber : {
				required : true,
				validCardNumber : true
			},
			customerAddress : {
				required : true
			}
		},
		messages : {
			customerPersonalNumericalCode : {
				required : "Please enter a personal numerical code",
				minlength : "Exactly 13 characters allowed for the PNC!",
				maxlength : "Exactly 13 characters allowed for the PNC!"

			},
			customerName : {
				required : "Name is required!",
				minlength : "Name has to be at least 3 characters!",
				maxlength : "Name can have atmost 30 characters!"
			},
			customerIdCardNumber : {
				required : "Id Card Number is required!"
			},
			customerAddress : {
				required : "Address is required!"
			}
			
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "customerPersonalNumericalCode":
				$("#pncError").html(error);
				break;
			case "customerName":
				$("#nameError").html(error);
				break;
			case "customerIdCardNumber":
				$("#idCardError").html(error);
				break;
			case "customerAddress":
				$("#addressError").html(error);
				break;	
			}
		}
	});
	$("#formEdit").validate({
		rules : {
			customerName : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			customerIdCardNumber : {
				required : true,
				validCardNumber : true
			},
			customerAddress : {
				required : true
			}
		},
		messages : {
			customerName : {
				required : "Name is required!",
				minlength : "Name has to be at least 3 characters!",
				maxlength : "Name can have atmost 30 characters!"
			},
			customerIdCardNumber : {
				required : "Id Card Number is required!"
			},
			customerAddress : {
				required : "Address is required!"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "customerName":
				$("#nameError").html(error);
				break;
			case "customerIdCardNumber":
				$("#idCardError").html(error);
				break;
			case "customerAddress":
				$("#addressError").html(error);
				break;	
			}
		}
	});
});