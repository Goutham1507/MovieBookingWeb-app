<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
  border-collapse: collapse;
  width: 100%;
}
    body {
         background: radial-gradient(1.5em 6.28571em at 1.95em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 0 0, radial-gradient(1.5em 6.28571em at -0.45em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 1.5em 5.5em, radial-gradient(2.3em 4.57143em at 2.99em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.3) 50%, rgba(255, 255, 255, 0.3) 55%, rgba(255, 255, 255, 0) 55%) 0 0, radial-gradient(2.3em 4.57143em at -0.69em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.3) 50%, rgba(255, 255, 255, 0.3) 55%, rgba(255, 255, 255, 0) 55%) 2.3em 4em, radial-gradient(3.5em 6.28571em at 4.55em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 0 0, radial-gradient(3.5em 6.28571em at -1.05em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 3.5em 5.5em, radial-gradient(#15ffa5, #00ced1);
  background-color: mediumspringgreen;
  background-size: 1.5em 11em, 1.5em 11em, 2.3em 8em, 2.3em 8em, 3.5em 11em, 3.5em 11em, 100% 100%;
  background-repeat: repeat;eat;
         text-align: center;
         font-family: garamond;
  		 font-size: 20px;
}
th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {background-color: #f2f2f2;}
</style>
<meta charset="ISO-8859-1">
<title>Customers List</title>
</head>
<body>
<h3> Below are the Customers  details</h3>
<div style="overflow-x:auto;">
<table>
<tr>
	<th>Customer Name</th>
	<th>Customer Email</th>
	<th>Movie Name</th>
	<th>Movie Location</th>
	<th>Movie Time</th>
	<th>Date of Movie</th>
</tr>
<c:forEach var="ticketList" items="${sessionScope.ticketList}">
<tr>
	<td>${ticketList.customerDetails.firstName}</td>
	<td>${ticketList.customerDetails.email}</td>
	<td>${ticketList.movie.movieName}</td>
	<td>${ticketList.movie.movieLocation}</td>
	<td>${ticketList.movie.movieTime}</td>
    <td>${ticketList.movie.movieDate}</td>
</tr>
</c:forEach>
</table>
</div>
<br><br><br>
<h3><a href="adminHome.htm">Go Back to Menu Page</a></h3>
</body>
</html>