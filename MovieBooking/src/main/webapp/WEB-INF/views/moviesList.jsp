<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
  background-repeat: repeat;
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Flights</title>
<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", 0);
%>
</head>
<body>
 <jsp:include page="menu.jsp"/>
 <a href="addMovies.htm"> Add movie </a>


 <h3>List of Movies are:</h3>
  <form action="updateMovies.htm" method="get">
  <div style="overflow-x:auto;">
<table>
<tr>
					<th>Movie Id </th>
					<th>Production Id</th>
					<td>movie Name</td>
					<th>Movie Location</th>
					<th>Movie Time</th>
					<th>Movie Date</th>
					<th>Total Seats</th>
					<th>Amount</th>

</tr>

<c:forEach var="movie" items="${sessionScope.movielist}">
<tr>
					<td>${movie.movie_id}</td>
                    <td>${movie.production_id}</td>
                    <td>${movie.movieName}</td>
                    <td>${movie.movieLocation}</td>
                    <td>${movie.movieTime}</td>
                    <td>${movie.movieDate}</td>
                    <td>${movie.availableSeats}</td>
                    <td>${movie.amount}</td>
                    <td><a href="updateMovies.htm?id=${movie.movie_id}&action=update">Update Movie</a></td>
                    <td><a href="deleteMovies.htm?id=${movie.movie_id}">Delete Movie</a></td>
</tr>
</c:forEach>
</table>
</div>
</form>
</body>
</html>