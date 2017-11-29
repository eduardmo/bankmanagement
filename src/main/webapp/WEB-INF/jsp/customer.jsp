<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/customerValidation.js"></script>
<link href="resources/css/myCSS.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Management</title>
</head>
<body>
	<a href="user">Back</a>
	<h1>Customer Data</h1>

	<form:form action="customer.get" method="get" modelAttribute="customer">
		<table>
			<tr>
				<td class="error">${message}</td>
			</tr>
			<tr>
				<td>Personal Numerical Code</td>
				<td><form:input path="customerPersonalNumericalCode" /></td>
				<td><input type="submit" name="action" value="Search" /></td>
				<td><input type="submit" name="action" value="Show All" /></td>
			</tr>
		</table>
	</form:form>
	<form:form id="formAdd" action="customer.do" method="post"
		commandName="customer">
		<table>
			<tr id="pncError">
				<td class="error">${pncErrorMessage}</td>
			</tr>
			<tr id="nameError"></tr>
			<tr id="idCardError"></tr>
			<tr id="addressError"></tr>
		</table>
		<table border="1">
			<tr>
				<th>Personal Numerical Code</th>
				<th>Name</th>
				<th>ID Card Number</th>
				<th>Address</th>
			</tr>
			<tr>
				<td><form:input id="customerPersonalNumericalCode"
						name="customerPersonalNumericalCode"
						path="customerPersonalNumericalCode" /></td>
				<td><form:input name="customerName" path="customerName" /></td>
				<td><form:input name="customerIdCardNumber"
						path="customerIdCardNumber" /></td>
				<td><form:input name="customerAddress" path="customerAddress" /></td>
				<td><input type="submit" name="action" value="Add" /></td>
			</tr>
		</table>
	</form:form>
	<br>
	<c:set var="inputDisplay" value="${showEdit}" />
	<c:choose>
		<c:when test="${inputDisplay == false}">
			<table border="1">
				<tr>
					<th style="width: 179px;">Personal Numerical Code</th>
					<th style="width: 151px;">Name</th>
					<th style="width: 151px;">ID Card Number</th>
					<th style="width: 151px;">Address</th>
				</tr>
				<c:forEach items="${customerList}" var="customerl">
					<tr>
						<td>${customerl.customerPersonalNumericalCode}</td>
						<td>${customerl.customerName}</td>
						<td>${customerl.customerIdCardNumber}</td>
						<td>${customerl.customerAddress}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<form:form id="formEdit" action="customer.do" method="post"
				commandName="customer">
				<table border="1">
					<tr>
						<th>Personal Numerical Code</th>
						<th>Name</th>
						<th>ID Card Number</th>
						<th>Address</th>
					</tr>
					<tr>
						<td><form:input path="customerPersonalNumericalCode"
								readonly="true" cssStyle="border:0px; cursor:default;"
								value="${customerl.customerPersonalNumericalCode}" /></td>
						<td><form:input name="customerName" path="customerName"
								value="${customerl.customerName}" /></td>
						<td><form:input name="customerIdCardNumber"
								path="customerIdCardNumber"
								value="${customerl.customerIdCardNumber}" /></td>
						<td><form:input name="customerAddress" path="customerAddress"
								value="${customerl.customerAddress}" /></td>
						<td><input type="submit" name="action" value="Edit" /></td>

					</tr>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>

</body>
</html>