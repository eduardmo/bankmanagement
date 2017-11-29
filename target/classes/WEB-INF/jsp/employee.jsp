<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/employeeValidation.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			altFormat : "dd-MM-yyyy"
		});
		$("#datepicker2").datepicker({
			altFormat : "dd-MM-yyyy"
		});
	});
</script>
<link href="resources/css/myCSS.css" rel="stylesheet">
<link href="resources/css/jquery-ui.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management</title>
</head>
<br />
<a href="logout">Logout</a>
<body>
	<h1>Employee Data</h1>
	<form:form action="employee.get" method="get" modelAttribute="employee">
		<table>
			<tr>
				<td class="error">${message}</td>
			</tr>
			<tr>
				<td>Personal Numerical Code</td>
				<td><form:input path="employeePersonalNumericalCode" /></td>
				<td><input type="submit" name="action" value="Search" /></td>
				<td><input type="submit" name="action" value="Show All" /></td>
			</tr>
		</table>
	</form:form>
	<form:form id="formActivities" action="employee.activity" method="get"
		modelAttribute="employeeActivities">
		<table>
			<tr>
				<td class="error" style="width: 167px;">${activityMessage}</td>
			</tr>
			<tr id="startDateError"></tr>
			<tr id="endDateError"></tr>
			<tr>
				<th>Personal Numerical Code</th>
				<th>Start Date</th>
				<th>End Date</th>
			</tr>
			<tr>
				<td><form:input path="employeeActivityPersonalNumericalCode" /></td>
				<td><form:input name="employeeActivityDate" id="datepicker"
						path="employeeActivityDate" value=""></form:input></td>
				<td><form:input name="employeeActivityDate2" id="datepicker2"
						path="employeeActivityDate2" value=""></form:input></td>
				<td><input type="submit" name="action" value="Show Activities" /></td>
			</tr>
		</table>
		<table>
			<c:forEach items="${employeeActivityList}" var="activityList">
				<tr>
					<td>${activityList.employeeActivity}</td>
					<td>${activityList.employeeActivityDate}</td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
	<form:form id="formAdd" action="employee.do" method="post"
		modelAttribute="employee">
		<table>
			<tr id="pncError">
				<td class="error">${pncErrorMessage}</td>
			</tr>
			<tr id="nameError"></tr>
			<tr id="idCardError"></tr>
			<tr id="usernameError">
				<td class="error">${usernameErrorMessage}</td>
			</tr>
			<tr id="passwordError"></tr>
		</table>
		<table border="1">
			<tr>
				<th>Personal Numerical Code</th>
				<th>Name</th>
				<th>ID Card Number</th>
				<th>Username</th>
				<th>Password</th>
				<th>Role</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
			<tr>
				<td><form:input maxlength="13"
						id="employeePersonalNumericalCode"
						name="employeePersonalNumericalCode"
						path="employeePersonalNumericalCode" value="" />
					<div class="error"></div></td>
				<td><form:input maxlength="30" name="employeeName"
						path="employeeName" /></td>
				<td><form:input maxlength="8" name="employeeIdCardNumber"
						path="employeeIdCardNumber" /></td>
				<td><form:input maxlength="15" name="username" path="username" /></td>
				<td><form:input name="password" path="password" /></td>
				<td><form:select path="employeeRoleId">
						<c:forTokens items="ROLE_USER,ROLE_ADMIN" delims="," var="option">
							<option value="${option == 'ROLE_USER' ? 1 : 2}">${option}</option>
						</c:forTokens>
					</form:select></td>
				<td><form:select path="status">
						<c:forTokens items="ACTIVE,INACTIVE" delims="," var="option">
							<option value="${option == 'ACTIVE' ? 'ACTIVE' : 'INACTIVE'}">${option}</option>
						</c:forTokens>
					</form:select></td>
				<td><input type="submit" name="action" value="Add" /></td>
			</tr>
		</table>
	</form:form>
	<c:set var="inputDisplay" value="${showEdit}" />
	<c:choose>
		<c:when test="${inputDisplay == false}">
			<table border="1">
				<tr>
					<th>Personal Numerical Code</th>
					<th>Name</th>
					<th>ID Card Number</th>
					<th>Username</th>
					<th>Role</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${employeeList}" var="employeel">
					<tr>
						<td>${employeel.employeePersonalNumericalCode}</td>
						<td>${employeel.employeeName}</td>
						<td>${employeel.employeeIdCardNumber}</td>
						<td>${employeel.username}</td>
						<td>${employeel.employeeRole.getRoleName()}</td>
						<td>${employeel.status}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<form:form id="formEdit" action="employee.do" method="post"
				commandName="employee">
				<table border="1">
					<tr>
						<th>Personal Numerical Code</th>
						<th>Name</th>
						<th>ID Card Number</th>
						<th>Username</th>
						<th>Password</th>
						<th>Role</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
					<tr>
						<td><form:input path="employeePersonalNumericalCode"
								readonly="true"
								value="${employeel.employeePersonalNumericalCode}"
								cssStyle="border:0px; cursor:default;" /></td>
						<td><form:input name="employeeName" path="employeeName"
								value="${employeel.employeeName}" /></td>
						<td><form:input name="employeeIdCardNumber"
								path="employeeIdCardNumber"
								value="${employeel.employeeIdCardNumber}" /></td>
						<td><form:input name="username" path="username"
								value="${employeel.username}" /></td>
						<td><form:input name="password" path="password" /></td>
						<td><form:select path="employeeRoleId">
								<option
									value="${employeel.employeeRole.getRoleName() == 'ROLE_USER' ? 1 : 2}">${employeel.employeeRole.getRoleName()}</option>
								<option
									value="${employeel.employeeRole.getRoleName() == 'ROLE_USER' ? 2 : 1}">${employeel.employeeRole.getRoleName() == 'ROLE_ADMIN' ? 'ROLE_USER' : 'ROLE_ADMIN'}</option>
							</form:select></td>
						<td><form:select path="status">
								<option
									value="${employeel.status == 'ACTIVE' ? 'ACTIVE' : 'INACTIVE'}">${employeel.status}</option>
								<option
									value="${employeel.status == 'ACTIVE' ? 'INACTIVE' : 'ACTIVE'}">${employeel.status == 'INACTIVE' ? 'ACTIVE' : 'INACTIVE'}</option>
							</form:select></td>
						<td><input type="submit" name="action" value="Edit" /></td>
						<c:choose>
							<c:when
								test="${employeel.employeeRole.getRoleName() == 'ROLE_USER'}">
								<td><input type="submit" name="action" value="Delete" /></td>
							</c:when>
						</c:choose>
					</tr>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>