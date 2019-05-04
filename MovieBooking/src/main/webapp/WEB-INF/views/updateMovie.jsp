<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>
    
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
<title>Update Movie</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
 <script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
 <script>
 $(document).ready(function(){
	    $("#txtFromDate").datepicker({
	        numberOfMonths: 2,
	        minDate: 0,
	        onSelect: function(selected) {
	          $("#txtToDate").datepicker("option","minDate", selected)
	        }
	    });
	    $("#txtToDate").datepicker({ 
	        numberOfMonths: 2,
	        onSelect: function(selected) {
	           $("#txtFromDate").datepicker("option","maxDate", selected)
	        }
	    });  
	});

</script>
</head>
<body>
<form:form action="updateMovie.htm" commandName="movie" method="post">
<div style="overflow-x:auto;">
<table>
<tr>
<td>Movie ID</td>
<td><form:input type="text" path="movie_id" value="${requestScope.movie2.movie_id}" size="30"/><font color="red"><form:errors path="movie_id"/></font></td>
</tr>
<tr>
<td>Movie Name</td>
<td><form:input type="text" path="movieName" value="${requestScope.movie2.movieName}" size="30"/><font color="red"><form:errors path="movieName"/></font></td>
</tr>
<tr>
<td>Production Id</td>
<td><form:input type="text" path="production_id" value="${requestScope.movie2.production_id}" size="30"/><font color="red"><form:errors path="production_id"/></font></td>
</tr>
<tr>
<td>Movie Location</td>
<td><form:input type="text" path="movieLocation" value="${requestScope.movie2.movieLocation}" size="30"/><font color="red"><form:errors path="movieLocation"/></font></td>
</tr>
<tr>
<td>Movie Time</td>
<td><form:input type="text" path="movieTime" value="${requestScope.movie2.movieTime}" size="30"/><font color="red"><form:errors path="movieTime"/></font></td>
</tr>
<tr>
<td>Movie Date</td>
<td><form:input type="text" path="movieDate" value="${requestScope.movie2.movieDate}" id="txtToDate" size="30"/><font color="red"><form:errors path="movieDate"/></font></td>
</tr>
<tr>
<td>Total Seats</td>
<td><form:input type="text" path="totalSeats" value="${requestScope.movie2.totalSeats}" size="30" readonly="true"/><font color="red"><form:errors path="totalSeats"/></font></td>
</tr>
<tr>
<td>Available Seats</td>
<td><form:input type="text" path="availableSeats" value="${requestScope.movie2.availableSeats}" size="30"/><font color="red"><form:errors path="availableSeats"/></font></td>
</tr>
<tr>
<td>Amount</td>
<td><form:input type="text" path="amount" value="${requestScope.movie2.amount}" size="30"/><font color="red"><form:errors path="amount"/></font></td>
</tr>
<tr>
    <td colspan="2"><input type="submit" value="Update movie to database" /></td>
</tr>

</table>
</div>


</form:form>
<a href="adminHome.htm">Go Back to Menu Page</a>
</body>
</html>