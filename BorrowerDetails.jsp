<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.dbproject.model.*"%>


<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="firstStyle.css">
<head>
<header class="header">
<h1> Borrower details </h1> 

</header>
</head>

<body>

<div class="bowdetails">


<form action="BorrowerDetails" method="post" name="frm" autocomplete="on">
<table class="books">

<tr>
<td>Please enter the Card Id</td>
<td><input type="text" name="cardid" size="20" /></td>
</tr>

<tr>
<td><input type="reset" value="Reset" ></td>
<td><input type="submit"  name="BowD" value="View Borrower Details" ></td>
<td><input type="submit" name="OverDB" value="View OverDue Books"></td>
<td><input type="submit" name="Fine" value="View Fine Details"></td>
</tr>
</table>
</form>
</div>
<div class="col-lg-4">
		<div class="bs-component" id="response_message">
		<c:if test="${not empty message}">
			<div class="alert alert-dismissible alert-success">
				${message }
			</div>
		</c:if>
		</div>
	</div>

</body>



</html>