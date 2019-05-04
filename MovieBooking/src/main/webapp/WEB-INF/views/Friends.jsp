<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friends Information</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<form action="customer.htm" action="get">
Number of Friends : <input type="number" name="noofFriends" required />
<input type="submit" value="Go"/>
</form>
</body>
</html>