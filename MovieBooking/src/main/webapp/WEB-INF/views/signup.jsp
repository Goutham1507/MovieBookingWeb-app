<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
    </head>
    <body>
    <script>

    	function registerUser(){
    		var isValid = true;
    		var node = document.getElementById("error");
    		
    		var txtContent = node.textContent;
    		
    		
    		if(txtContent=="Username already exists")
    			{
    			isValid = false;
    			document.getElementById("duplicateuser").innerHTML= "";
    			alert("Please enter unique username");
    			}
    		return isValid;
    		
    	}
    	function clearthefeild()
    	{
    		document.getElementById("duplicateuser").innerHTML= "";
    		document.getElementById("error").innerHTML= "";
    	}
    	
        //AJAX

        var xmlHttp;
        xmlHttp = GetXmlHttpObject();
        function checkUser() {
           if (xmlHttp == null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
            var username = document.getElementById("username").value;
            var query = "action=ajaxCheck&username="+username;

            xmlHttp.onreadystatechange = function stateChanged()
            {
                if (xmlHttp.readyState == 4)
                {
                    //alert(xmlHttp.responseText);
                    var json = JSON.parse(xmlHttp.responseText);
                    document.getElementById("error").innerHTML="";
                    document.getElementById("error").innerHTML = json.message;
                    
                }
            };
            xmlHttp.open("POST", "signup.htm", true);
            xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
           return false;
        }
        
         function GetXmlHttpObject()
        {
            var xmlHttp = null;
            try
            {
                // Firefox, Opera 8.0+, Safari
                xmlHttp = new XMLHttpRequest();
            } catch (e)
            {
                // Internet Explorer
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
        
        <h1>Sign up</h1>
        <form:form action="signup.htm?action=signup" onSubmit =" return registerUser()" commandName="users" method="post">
            <p>Username: <form:input type="text" path="userName" id="username" onclick="return clearthefeild()" onblur="return checkUser()" required="required" /></p>
            <div id="error" style="color:red"></div>
            <p>Password: <form:input type="password" path="password" required="required"/></p>
            <input type="submit" value="Sign Up">
            <div id="duplicateuser"></div>
        </form:form>
    </body>
</html>
