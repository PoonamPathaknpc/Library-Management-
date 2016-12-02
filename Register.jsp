<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="firstStyle.css">

<title> Browser account creation</title>
<header class="header">
<h1> Create new Borrower Account</h1> 
</header>
</head>

<body>


<div class="AccountCreation">
<h2>Registration</h2>
<form action="BorrowerRegister" method="post" name="frmReg" autocomplete="on">
<table class="books">
<tr>
<td>First Name:</td>
<td><input type="text" name="fName" size="20" /></td>
</tr>

<tr>
<td>Last Name:</td>
<td><input type="text" name="lName" size="20" /></td>
</tr>

<tr>
<td>SSN:</td>
<td><input type="text" name="ssn" size="20" /></td>
</tr>

<tr>
<td>Telephone Number</td>
<td><input type="tel" name="tnum"></td>
</tr>
<tr>

<tr>
<td>Home Address</td>
<td></td>
</tr>
<tr>

<tr>
<td>First Line of address</td>
<td><input type="input" name="fadd"></td>
</tr>
<tr>

<tr>
<td>County</td>
<td>
<input list="County" name="counties">
<datalist id="County">
<option value="Dallas">
<option value="Richardson">
<option value="Plano">
<option value="Garland">
<option value="Irving">
<option value="DeSoto">
</datalist>
</td>
</tr>
<tr>

<tr>
<td>Zipcode</td>
<td><input type="input" name="zcode"></td>
</tr>
<tr>


<tr>
<td><input type="reset" value="Reset" ></td>
<td><input type="submit" onClick="register()" value="Register" ></td>
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