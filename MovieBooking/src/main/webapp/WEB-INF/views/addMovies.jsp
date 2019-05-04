<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Movie</title>
<br><br><br><br><br><br>
<style>
         body {
         background: radial-gradient(1.5em 6.28571em at 1.95em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 0 0, radial-gradient(1.5em 6.28571em at -0.45em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 1.5em 5.5em, radial-gradient(2.3em 4.57143em at 2.99em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.3) 50%, rgba(255, 255, 255, 0.3) 55%, rgba(255, 255, 255, 0) 55%) 0 0, radial-gradient(2.3em 4.57143em at -0.69em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.3) 50%, rgba(255, 255, 255, 0.3) 55%, rgba(255, 255, 255, 0) 55%) 2.3em 4em, radial-gradient(3.5em 6.28571em at 4.55em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 0 0, radial-gradient(3.5em 6.28571em at -1.05em, rgba(255, 255, 255, 0) 50%, rgba(255, 255, 255, 0.25) 50%, rgba(255, 255, 255, 0.25) 55%, rgba(255, 255, 255, 0) 55%) 3.5em 5.5em, radial-gradient(#15ffa5, #00ced1);
  background-color: mediumspringgreen;
  background-size: 1.5em 11em, 1.5em 11em, 2.3em 8em, 2.3em 8em, 3.5em 11em, 3.5em 11em, 100% 100%;
  background-repeat: repeat;
         text-align: center;
         font-family: garamond;
  		 font-size: 20px;
}
</style>
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
<form:form action="addMovies.htm" commandName="movie" method="post">

Movie Name:&nbsp;&nbsp;
<form:input type="text" path="movieName" size="30"/><font color="red"><form:errors path="movieName"/></font>
<br><br><br>
Production Id:&nbsp;&nbsp;
<form:input type="text" path="production_id" size="30"/><font color="red"><form:errors path="production_id"/></font>
<br><br><br>
Movie Location:&nbsp;&nbsp;
<form:input type="text" path="movieLocation" size="30"/><font color="red"><form:errors path="movieLocation"/></font>
<br><br><br>
Movie Time:&nbsp;&nbsp;
<form:input type="text" path="movieTime"  size="30"/><font color="red"><form:errors path="movieTime"/></font>
<br><br><br>
Movie Date:&nbsp;&nbsp;
<form:input type="text" path="movieDate" id="txtFromDate" size="30"/><font color="red"><form:errors path="movieDate"/></font>
<br><br><br>
Total Seats:&nbsp;&nbsp;
<form:input type="text" path="totalSeats" size="30"/><font color="red"><form:errors path="totalSeats"/></font>
<br><br><br>
Available Seats:&nbsp;&nbsp;
<form:input type="text" path="availableSeats" value="${requestScope.movie.availableSeats}" size="30"/><font color="red"><form:errors path="availableSeats"/></font>
<br><br><br>
Amount:&nbsp;&nbsp;
<form:input type="text" path="amount" size="30"/><font color="red"><form:errors path="amount"/></font>
<br><br><br>
<input type="submit" value="Add movie to database" />

</form:form>
</body>
</html>