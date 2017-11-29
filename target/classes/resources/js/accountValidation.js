/**
 * 
 */

$(function() {
	jQuery.validator.addMethod("validPNC", function(value, element) {
		return /^[1-9]{1}[0-9]+$/.test(value);
	}, "Only Numerical Characters Allowed!");
	jQuery.validator.addMethod("validFloat", function(value, element) {
		return /^[0-9]+\.[0-9]+$/.test(value);
	}, "Number must be positive float (i.e. 123.45)!");
	$("#formAdd").validate({
		rules : {
			ownerPNC : {
				required : true,
				validPNC : true,
				minlength : 13,
				maxlength : 13,
			},
			accountType : {
				required : true
			},
			amountOfMoney : {
				required : true,
				validFloat: true
			}
		},
		messages : {
			ownerPNC : {
				required : "Please enter a personal numerical code",
				minlength : "Exactly 13 characters allowed for the PNC!",
				maxlength : "Exactly 13 characters allowed for the PNC!"

			},
			accountType : {
				required : "Type is required!"
			},
			amountOfMoney : {
				required : "Accmount of money is required!"
			}
			
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "ownerPNC":
				$("#pncError").html(error);
				break;
			case "accountType":
				$("#typeError").html(error);
				break;
			case "amountOfMoney":
				$("#moneyError").html(error);
				break;
			}
		}
	});
	$("#formEdit").validate({
		rules : {
			accountType : {
				required : true
			},
			amountOfMoney : {
				required : true,
				validFloat: true
			}
		},
		messages : {
			accountType : {
				required : "Type is required!"
			},
			amountOfMoney : {
				required : "Accmount of money is required!"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "accountType":
				$("#typeError").html(error);
				break;
			case "amountOfMoney":
				$("#moneyError").html(error);
				break;	
			}
		}
	});
	$("#transferForm").validate({
		rules : {
			amount : {
				required : true,
				validFloat: true
			},
			accountToTransfer: {
				required: true
			},
			accountNumber:{
				required: true
			}
		},
		messages : {
			amountOfMoney : {
				required : "Accmount of money is required!"
			},
			accountToTransfer: {
				required: "Account is required"
			},
			accountNumber:{
				required: "Account is required"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "accountNumber":
				$("#accountNumberError").html(error);
				break;
			case "accountToTransfer":
				$("#accountToTransferError").html(error);
				break;
			case "amountOfMoney":
				$("#amountError").html(error);
				break;	
			}
		}
	});
	$("#withdrawForm").validate({
		rules : {
			amount : {
				required : true,
				validFloat: true
			},
			accountNumber:{
				required: true
			}
		},
		messages : {
			amountOfMoney : {
				required : "Accmount of money is required!"
			},
			accountNumber:{
				required: "Account is required"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "accountNumber":
				$("#accountNumberError").html(error);
				break;
			case "amountOfMoney":
				$("#amountError").html(error);
				break;	
			}
		}
	});
	$("#depositForm").validate({
		rules : {
			amount : {
				required : true,
				validFloat: true
			},
			accountNumber:{
				required: true
			}
		},
		messages : {
			amountOfMoney : {
				required : "Accmount of money is required!"
			},
			accountNumber:{
				required: "Account is required"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "accountNumber":
				$("#accountNumberError").html(error);
				break;
			case "amountOfMoney":
				$("#amountError").html(error);
				break;	
			}
		}
	});
	$("#utilityForm").validate({
		rules : {
			customerPersonalNumericalCode : {
				required : true
			},
			amount:{
				required: true,
				validFloat: true
			}
		},
		messages : {
			customerPersonalNumericalCode : {
				required : "Personal Numerical Code is Required"
			},
			amount:{
				required: "Amount of money is required!"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "customerPersonalNumericalCode":
				$("#customerPersonalNumericalCodeError").html(error);
				break;
			case "amount":
				$("#amountError").html(error);
				break;	
			}
		}
	});
});