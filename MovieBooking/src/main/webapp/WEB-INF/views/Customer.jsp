<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 <%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Details</title>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 
	    <script>
	   		$(function(){
	   	    $("#dob").datepicker({
	   	        numberOfMonths: 2, 
	   	        maxDate:'0'
	   	})
	   		});

	   		function validateEmail(email) {
	   		  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	   		  return re.test(email);
	   		}

	   		function validate() {
	   		  $("#result").text("");
	   		  var email = $("#email").val();
	   		  if (validateEmail(email)) {
	   		    $("#result").text(email + " is valid :)");
	   		    $("#result").css("color", "green");
	   		  } else {
	   		    $("#result").text(email + " is not valid :(");
	   		    $("#result").css("color", "red");
	   		  }
	   		  return false;
	   		}

	   		$("form").bind("submit", validate);
	   		
	   		
	   		
	   </script>

</head>
<body>
<h1>Please enter all details correctly</h1>
<form:form action="customer.htm" commandName="customer" method="post">

<c:forTokens items="i" delims="${sessionScope.noOfTravellers}">
First Name:&nbsp;&nbsp;
    <form:input type="text" path="firstName" size="30" /> <font color="red"><form:errors path="firstName"/></font>
<br><br><br>
Last Name:&nbsp;&nbsp;
    <form:input type="text" path="lastName" size="30"/> <font color="red"><form:errors path="lastName"/></font>
<br><br><br>
Gender:&nbsp;&nbsp;
    <form:radiobutton value="M" path="gender" checked="checked"/>Male
    <form:radiobutton value="F" path="gender" />Female
<br><br><br>
Email:&nbsp;&nbsp;
    <form:input type="text" path="email" id='email' size="30"/> <font color="red"><form:errors path="email"/></font>
<br><br><br>
Date of Birth:&nbsp;&nbsp;
    <form:input type="text" path="dob" id="dob" size="30"/> <font color="red"><form:errors path="dob"/></font>
<br><br><br>
Phone Number:&nbsp;&nbsp;
    <td><form:input type="text" path="phonenum" size="30" /> <font color="red"><form:errors path="phonenum"/></font>
<br><br><br>
Address:&nbsp;&nbsp;
    <form:input type="textarea" path="address" size="30"/> <font color="red"><form:errors path="address"/></font>
<br><br><br>
</c:forTokens>
<br><br><br>
   <input type="submit" value="Save Details and Enter payment details" />

</form:form>
</body>
</html>