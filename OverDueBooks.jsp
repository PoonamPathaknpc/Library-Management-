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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>List of Over Due Books for the borrower</title>

</head>

<body>

<table align="center">

<%

List<OverdueBean> searchResult=new ArrayList();
searchResult = (ArrayList<OverdueBean>) request.getAttribute("OverdueR");


if(searchResult!=null && searchResult.size()>0 ){

%>

<h2 align="center">Book List</h2>

<tr>

<th>First Name</th>
<th>Last Name</th>
<th>Card ID</th>
<th>ISBN</th>


</tr>

<%

for(int i=0;i<searchResult.size();i++){

OverdueBean book=(OverdueBean)searchResult.get(i);

%>

<tr>
<td><%=book.getBorrower().getFname() %></td>
<td><%=book.getBorrower().getLname() %></td>
<td><%=book.getBorrower().getCard_ID() %></td>

<td><%=book.getB()%></td>


</tr>

<%

}

}else{

%>

Book Details for this search is not available

request.getAttribute("SResult")

<%}%>

</table>

</body>

</html>