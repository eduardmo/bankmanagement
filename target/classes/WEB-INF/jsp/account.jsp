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
<title>Account Management</title>
</head>
<body>
	<a href="user">Back</a>
	<h1>Account Data</h1>
	<form:form action="account.get" method="get" modelAttribute="account">
		<table>
			<tr>
				<td class="error">${message}</td>
			</tr>
			<tr>
				<td>Account Number</td>
				<td><form:input path="accountNumber" value=""/></td>
				<td><input type="submit" name="action" value="Search" /></td>
				<td><input type="submit" name="action" value="Show All" /></td>
			</tr>
		</table>
	</form:form>
	<form:form id="transferForm" action="account.transfer" method="get" modelAttribute="account">
		<table>
		<tr id="accountNumberError"></tr>
			<tr id="accountToTransferError"></tr>
			<tr id="amountError"></tr>
			<tr><td>${message1}</td>
			<td class="error">${message2}</td>
			</tr>
			<tr>
				<td>Transfer from account</td>
				<td><form:input name="accountNumber" path="accountNumber" value=""/></td>
				<td>to account</td>
				<td><input type="text" name="accountToTransfer"></td>
				<td>amount</td>
				<td><input type="text" name="amount"></td>
				<td><input type="submit" name="action" value="Transfer" /></td>
			</tr>
		</table>
	</form:form>
	<form:form id="withdrawForm" action="account.withdraw" method="get" modelAttribute="account">
		<table>
			<tr id="accountNumberError"></tr>
			<tr id="amountError"></tr>
		</table>
		<table>
			<tr><td>${message3}</td>
			<td class="error">${message4}</td>
			</tr>
			<tr>
				<td>Withdraw from account</td>
				<td><form:input name="accountNumber" path="accountNumber" value=""/></td>
				<td>amount</td>
				<td><input type="text" name="amount"></td>
				<td><input type="submit" name="action" value="Withdraw" /></td>
			</tr>
		</table>
	</form:form>
	<form:form id="depositForm" action="account.deposit" method="get" modelAttribute="account">
		<table>
			<tr id="accountNumberError"></tr>
			<tr id="amountError"></tr>
		</table>
		<table>
			<tr><td>${message5}</td>
			<td class="error">${message6}</td>
			</tr>
			<tr>
				<td>Deposit to account</td>
				<td><form:input name="accountNumber" path="accountNumber" value=""/></td>
				<td>amount</td>
				<td><input type="text" name="amount"></td>
				<td><input type="submit" name="action" value="Deposit" /></td>
			</tr>
		</table>
	</form:form>
	<form:form id="formAdd" action="account.do" method="post" commandName="account">
		<table>
			<tr id="pncError">
				<td class="error">${pncErrorMessage}</td>
			</tr>
			<tr id="typeError"></tr>
			<tr id="moneyError"></tr>
		</table>
		<table>
			<tr>
				<td>Account Type</td>
				<td>Money</td>
				<td>Owner PNC</td>
			</tr>
			<tr>
				<td><form:input name="accountType" path="accountType" /></td>
				<td><form:input name="amountOfMoney" path="amountOfMoney" /></td>
				<td><form:input name="ownerPNC" path="ownerPNC" /></td>
				<td><input type="submit" name="action" value="Add" /></td>
			</tr>
		</table>
	</form:form>
	<br>
	<form:form action="account.getAll" method="get" modelAttribute="account">
		<table>
			<tr>
				<td class="error">${messagePNC}</td>
			</tr>
			<tr>
				<td>Customer PNC</td>
				<td><form:input path="ownerPNC" /></td>
				<td><input type="submit" name="action" value="Show All Accounts" /></td>
			</tr>
		</table>
	</form:form>
	<c:set var="inputDisplay" value="${showEdit}" />
	<c:choose>
		<c:when test="${inputDisplay == false}">
			<table border="1">
				<tr>
					<th>Account Number</th>
					<th>Owner Personal Numerical Code</th>
					<th>Type</th>
					<th>Balance</th>
					<th>Creation Date</th>
				</tr>
				<c:forEach items="${accountList}" var="accountl">
					<tr>
						<td>${accountl.accountNumber}</td>
						<td>${accountl.ownerPNC}</td>
						<td>${accountl.accountType}</td>
						<td>${accountl.amountOfMoney}</td>
						<td>${accountl.creationDate}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<form:form id="formEdit" action="account.do" method="post"
				commandName="account">
				<table border="1">
					<tr>
						<th>Account Number</th>
						<th>Owner Personal Numerical Code</th>
						<th>Type</th>
						<th>Balance</th>
						<th>Creation Date</th>
					</tr>
					<tr>
						<td><form:input path="accountNumber" readonly="true"
								cssStyle="border:0px; cursor:default;"
								value="${accountl.accountNumber}" /></td>
						<td><form:input name="ownerPNC" path="ownerPNC"
								readonly="true" cssStyle="border:0px; cursor:default;"
								value="${accountl.ownerPNC}" /></td>
						<td><form:input name="accountType" path="accountType"
								value="${accountl.accountType}" /></td>
						<td><form:input name="amountOfMoney" path="amountOfMoney"
								value="${accountl.amountOfMoney}" /></td>
						<td><form:input name="creationDate" path="creationDate"
								readonly="true" cssStyle="border:0px; cursor:default;"
								value="${accountl.creationDate}" /></td>
						<td><input type="submit" name="action" value="Edit" /></td>
						<td><input type="submit" name="action" value="Delete" /></td>
					</tr>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>