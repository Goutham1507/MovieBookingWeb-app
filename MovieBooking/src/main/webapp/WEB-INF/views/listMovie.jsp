<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie List</title>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>

var xmlHttp;
xmlHttp = GetXmlHttpObject();

function checkSeats()
{
	if (xmlHttp == null)
    {
        alert("Your browser does not support AJAX!");
        return;
    }
    var check = document.getElementById("link").href;   
    var Movie = check.split('=');

    var query = "mid="+movieId[1];
    
    xmlHttp.onreadystatechange = function stateChanged()
    {
    	
        if (xmlHttp.readyState == 4)
        {
        	document.getElementById("error").innerHTML=xmlHttp.responseText;
			var node = document.getElementById("error");
    		
    		var txtContent = node.textContent;
    		
    		
        	if(txtContent=="Seats are available")
        	{
        		location.href=("viewCart.htm");
        	}
        }
    };
    xmlHttp.open("POST", "addToCart.htm", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(query);
 	return false;
 	
	
}

	
	
	 function GetXmlHttpObject()
     {
         var xmlHttp = null;
         try
         {
          
             xmlHttp = new XMLHttpRequest();
         } catch (e)
         {
         
             try
             {
                 xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
             } catch (e)
             {
                 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
             }
         }
         return xmlHttp;
     }
     


</script>
</head>
<body>


<div id="content">

<c:choose>
            <c:when test="${!empty sessionScope.username}">
                <jsp:include page="menu.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="menu2.jsp"/>
            </c:otherwise>
</c:choose>
        <h2>Following Movies are available</h2>
        <div style="overflow-x:auto;">
            <table >
            <tr>
            	<th>Movie Number</th>
                <th>Movie Name</th>
                <th>Production Id</th>
                <th>Price</th>
                <th>Movie time</th>
                <th>Movie Location </th>
                <th>Total Seats</th>
                <th>Available Seats</th>
                <td>Amount</td>
            </tr>
		<c:forEach var="movielist" items="${sessionScope.movielist}">
                <tr>
                   	<td>${movielist.movie_id}</td>
                    <td>${movielist.production_id}</td>
                    <td>${movielist.movieName}</td>
                    <td>${movielist.movieLocation}</td>
                    <td>${movielist.movieTime}</td>
                    <td>${movielist.movieDate}</td>
                    <td>${movielist.totalSeats}</td>
                    <td>${movielist.availableSeats}</td>                    
                    <td>${movielist.amount}</td>
                    <td><a href="addToCart.htm?mid=${movielist.movie_id}"  id="link">Book Ticket</a></td>
                </tr>   
            </c:forEach>
        </table>
        </div>
                    <div id="error" style="color:red"></div>
</div>       
</body>
</html>