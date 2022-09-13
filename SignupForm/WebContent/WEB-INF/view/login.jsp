<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
	<form action="processLogin" method="get">
		Enter RollNo:
		<input type="number" name="rollNo" />
		Enter Password:
		<input type="text" name="password" />
		<input type="submit" value="Submit"/>	
	</form>
</body>
</html>