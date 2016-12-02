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

<title> Book Search Results</title>
<header class="header">
<h1> Book Search Results</h1> 
</header>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Search Book</title>

</head>

<body>

<table align="center">

<%

List searchResult=new ArrayList();
searchResult = (ArrayList<Book_AuthorBean>) request.getAttribute("SResult");


if(searchResult!=null && searchResult.size()>0 ){

%>

<h2 align="center">Book List</h2>

<tr>

<th>ISBN</th>
<th>Title</th>
<th>Authors</th>
<th>Availability</th>

</tr>

<%

for(int i=0;i<searchResult.size();i++){

Book_AuthorBean book=(Book_AuthorBean)searchResult.get(i);

%>

<tr>

<td><%=book.getISBN() %></td>

<td><%=book.getTitle() %></td>

<td><%=book.getAuthor_Name() %></td>

<td><%=book.getQuantity() %></td>

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