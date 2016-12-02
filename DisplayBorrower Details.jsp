<%@page import="com.dbproject.model.BORROWER"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="firstStyle.css">
<header class="header">
<h1> Borrower details </h1> 
<a div id="signin" href="login.html" role="button" tabindex="0">Login</a>
</header>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<%
BORROWER Bowdetails = (BORROWER) request.getAttribute("borrower");	


if(Bowdetails!=null ){
		
%>
<h2>Borrower details</h2>


<h2 align="center">Borrower Details</h2>
<table align="center">

<tr>

<th>Card ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>SSN</th>
<th>Address</th>
<th>Phone</th>
</tr>


<tr>

<td><%=Bowdetails.getCard_ID() %></td>

<td><%=Bowdetails.getFname() %></td>

<td><%=Bowdetails.getLname() %></td>
<td><%=Bowdetails.getSSN() %></td>

<td><%=Bowdetails.getAddress() %></td>
<td><%=Bowdetails.getPhone() %></td>

</tr>

<%

}

else{

%>

Book Details for this search is not available

request.getAttribute("SResult")

<%}%>

</table>
</div>

</body>
</html>