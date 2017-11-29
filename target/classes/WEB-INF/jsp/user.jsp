<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="logout">Logout</a>
<form:form action="useractions" method="get">
<table>
<tr>
<td><input type="submit" name="action" value="Customer Management" /></td>
<td><input type="submit" name="action" value="Account Management" /></td>
</tr>
<tr>
<td><input type="submit" name="action" value="Pay Utility Bills" /></td>
</tr>
</table>
</form:form>
</body>
</html>