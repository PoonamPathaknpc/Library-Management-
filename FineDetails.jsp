<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.dbproject.model.*"%>
<html>

<head>
<link rel="stylesheet" type="text/css" href="firstStyle.css">


<header class="header">
<h1> Fine Details</h1> 
</header>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



</head>

<body>

<table align="center">

<%

List searchResult=new ArrayList();
searchResult = (ArrayList<FINES>) request.getAttribute("FineDetails");


if(request.getAttribute("FineDetails")!=null && searchResult.size()>0 ){

%>

<h2 align="center">Book List</h2>

<tr>

<th>LOAN ID</th>
<th>FINE Amount </th>
<th>PAID</th>


</tr>

<%

for(int i=0;i<searchResult.size();i++){

FINES book=(FINES)searchResult.get(i);
if(book!=null ){
%>

<tr>

<td><%=book.getLoan_ID() %></td>

<td><%=book.getFine_Amt() %></td>

<td><%=book.getPAID() %></td>



</tr>

<%
}
}

}else{

%>

The Borrower has no fines

request.getAttribute("SResult")

<%}%>

</table>

</body>

</html>