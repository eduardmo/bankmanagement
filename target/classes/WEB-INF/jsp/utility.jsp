<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/accountValidation.js"></script>
<link href="resources/css/myCSS.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Utility Management</title>
</head>
<body>
	<a href="user">Back</a>
	<h1>Bill Processing</h1>
	<form:form id="utilityForm" action="utility.process" method="get">
		<table>
		<tr id="accountNumberError"></tr>
			<tr id="accountToTransferError"></tr>
			<tr id="amountError"></tr>
			<tr><td>${message1}</td>
			<td class="error">${message2}</td>
			</tr>
			<tr>
				<td>Pay From Customer </td>
				<td><input type="text" name="customerPersonalNumericalCode" value=""/></td>
				<td>amount </td>
				<td><input type="text" name="amount"></td>
				<td>bill </td>
				<td><input type="text" name="bill"></td>
				<td><input type="submit" name="action" value="Transfer" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>