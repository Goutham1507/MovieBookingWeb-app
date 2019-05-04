<%@ page errorPage="error.jsp" %>
<div style="float:right">
    Welcome ${sessionScope.username} | 
    <a href="logout.htm?action=logout">Logout</a> | 
    <a href="viewCart.htm?action=viewcart">Cart</a>
</div>