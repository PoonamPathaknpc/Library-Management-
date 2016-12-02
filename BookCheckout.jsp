<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="firstStyle.css">

<title>Book Checkout</title>
<header class="header">
<h1> Checkin Book</h1> 
</header>
</head>

<body>


<div class="Book Checkout">
<h2>Registration</h2>
<form action="BookLoan" method="post" name="frmReg" autocomplete="on">
<table class="books">
<tr>
<td>ISBN</td>
<td><input type="number" name="ISBN" size="20" maxlength="10" /></td>
</tr>

<tr>
<td>Card ID</td>
<td><input type="text" name="CardID" size="20" /></td>
</tr>

<td><input type="reset" value="Reset" ></td>
<td><input type="submit"  name="checkout" value="Checkout Book" ></td>
<td><input type="submit" name="checkin" value="Checkin Book"></td>
<td><input type="submit" name="pFine" value="Pay Fine"></td>
</tr>

</table>
</form>
</div>


<div class="books">
		<div class="bs-component" id="response_message">
		<c:if test="${not empty message}">
			<div class="alert alert-dismissible alert-success">
				${message }
			</div>
		</c:if>
		</div>
	</div>

</html>