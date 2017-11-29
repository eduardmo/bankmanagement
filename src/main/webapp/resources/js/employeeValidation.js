/**
 * 
 */

$(function() {
	jQuery.validator.addMethod("greaterThan", function(value, element, params) {

		if (!/Invalid|NaN/.test(new Date(value))) {
			console.log($("'"+params+"'").val());
			return new Date(value) > new Date($(params).val());
		}
		return isNaN(value) && isNaN($(params).val())
				|| (Number(value) > Number($(params).val()));
	}, 'Second date must be greater than the first one.');
	jQuery.validator.addMethod("validPNC", function(value, element) {
		return /^[1-9]{1}[0-9]$/.test(value);
	}, "Only Numerical Characters Allowed!");
	jQuery.validator.addMethod("validCardNumber", function(value, element) {
		return /^[A-Z]{2}[0-9]{6}$/.test(value);
	}, "Invalid Id Card Number format!");
	$("#formAdd").validate({
		rules : {
			employeePersonalNumericalCode : {
				required : true,
				validPNC : true,
				minlength : 13,
				maxlength : 13,
			},
			employeeName : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			employeeIdCardNumber : {
				required : true,
				validCardNumber : true
			},
			username : {
				required : true,
				minlength : 5,
				maxlength : 15
			},
			password : {
				required : true,
				minlength : 6
			}
		},
		messages : {
			employeePersonalNumericalCode : {
				required : "Please enter a personal numerical code",
				minlength : "Exactly 13 characters allowed for the PNC!",
				maxlength : "Exactly 13 characters allowed for the PNC!"

			},
			employeeName : {
				required : "Name is required!",
				minlength : "Name has to be at least 3 characters!",
				maxlength : "Name can have atmost 30 characters!"
			},
			employeeIdCardNumber : {
				required : "Id Card Number is required!"
			},
			username : {
				required : "Username must be provided!",
				minlength : "Username has to be at least 5 characters!",
				maxlength : "Username can have atmost 15 characters!"
			},
			password : {
				required : "Password must be provided!",
				minlength : "Password has to be at least 6 characters!"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "employeePersonalNumericalCode":
				$("#pncError").html(error);
				break;
			case "employeeName":
				$("#nameError").html(error);
				break;
			case "employeeIdCardNumber":
				$("#idCardError").html(error);
				break;
			case "username":
				$("#usernameError").html(error);
				break;
			case "password":
				$("#passwordError").html(error);
				break;
			}
		}
	});
	$("#formEdit").validate({
		rules : {
			employeeName : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			employeeIdCardNumber : {
				required : true,
				validCardNumber : true
			},
			username : {
				required : true,
				minlength : 5,
				maxlength : 15
			}
		},
		messages : {
			employeeName : {
				required : "Name is required!",
				minlength : "Name has to be at least 3 characters!",
				maxlength : "Name can have atmost 30 characters!"
			},
			employeeIdCardNumber : {
				required : "Id Card Number is required!"
			},
			username : {
				required : "Username must be provided!",
				minlength : "Username has to be at least 5 characters!",
				maxlength : "Username can have atmost 15 characters!"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "employeeName":
				$("#nameError").html(error);
				break;
			case "employeeIdCardNumber":
				$("#idCardError").html(error);
				break;
			case "username":
				$("#usernameError").html(error);
				break;
			}
		}
	});
	$("#formActivities").validate({
		rules : {
			employeeActivityDate : {
				required : true
			},
			employeeActivityDate2 : {
				required : true,
				greaterThan: "#employeeActivityDate"
			}
		},
		messages : {
			employeeActivityDate : {
				required : "Start Date is required!"
			},
			employeeActivityDate2 : {
				required : "End Date required!"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "employeeActivityDate":
				$("#startDateError").html(error);
				break;
			case "employeeActivityDate2":
				$("#endDateError").html(error);
				break;
			}
		}
	});
});